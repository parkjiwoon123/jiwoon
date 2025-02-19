package replyboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import phonebook.PhonebookVO;

public class OracleBoardDAO implements BoardDAOInter{
	//앞으로 구현해야할 사항:
	//1.데이터베이스 접속 시 pool 또는 싱글톤을 적용
	//2.라이브러리 추가(maven을 통해 라이브러리 관리)
	//3.@AutoWire를 이용하여 객체주입-spring에서 처리
	
	Connection conn; 


    public OracleBoardDAO() {
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection details
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Properties props = new Properties();
            props.setProperty("user", "system");
            props.setProperty("password", "1234");
            props.setProperty("oracle.jdbc.defaultNChar", "true");

            // Establish the connection with Unicode settings
            conn = DriverManager.getConnection(url, props);
            System.out.println("Database connection successful.");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        OracleBoardDAO dao = new OracleBoardDAO();
        // Use the connection for database operations
    }
	

	
	@Override
	//댓글이 아닌 글쓰기
	 public int insert(BoardVO board) {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("insert into board(idx,title,content,readcount,");
            sql.append("parentid, tab,");
            sql.append("writeid,writename,writeday,");
            sql.append("filename,isdel,kind)");
            sql.append(" values(board_idx_seq.nextval,?,?,0,");
            sql.append("0,0,");
            sql.append("?,?,sysdate,");
            sql.append("?,0,'일반게시판')");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriteid());
            ps.setString(4, board.getWritename());
            ps.setString(5, board.getFilename()); // full경로 or 파일명 선택

            // Print the SQL statement with parameters
            String executedSql = sql.toString();
            executedSql = executedSql.replaceFirst("\\?", "'" + board.getTitle() + "'");
            executedSql = executedSql.replaceFirst("\\?", "'" + board.getContent() + "'");
            executedSql = executedSql.replaceFirst("\\?", "'" + board.getWriteid() + "'");
            executedSql = executedSql.replaceFirst("\\?", "'" + board.getWritename() + "'");
            executedSql = executedSql.replaceFirst("\\?", "'" + board.getFilename() + "'");

            System.out.println("Executing SQL: " + executedSql);

            int result = ps.executeUpdate();
            ps.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


	@Override
	public List<BoardVO> findAll() {
	    List<BoardVO> list = new ArrayList<>();
	    String sql = "SELECT * FROM board";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            int idx = rs.getInt("idx");
	            String title = rs.getString("title");
	            String content = rs.getString("content");
	            int readcount = rs.getInt("readcount");
	            int parentid = rs.getInt("parentid");
	            int tab = rs.getInt("tab");
	            String writeid = rs.getString("writeid");
	            String writename = rs.getString("writename");
	            Date writeday = rs.getDate("writeday");
	            String filename = rs.getString("filename");
	            String isdel = rs.getString("isdel"); // Changed to getString
	            String kind = rs.getString("kind");
	            
	            BoardVO board = new BoardVO(idx, title, content, readcount, parentid, tab, writeid, writename, writeday, filename, isdel, kind);
	            list.add(board);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return list;
	}
	@Override //BoardVO 외부에 변수설정, 조건처리, while->if, idx전달받은 것 사용, list필요없는사항
	public BoardVO findOneById(int idx) {
		try {
			String sql="select * from board where idx=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ResultSet rs=ps.executeQuery();
			BoardVO board=null;
			if(rs.next()) {
				//int idx=rs.getInt("idx");
				String title=rs.getString("title");
				String content=rs.getString("content");;
				int readcount=rs.getInt("readcount");
				int parentid=rs.getInt("parentid");
				int tab=rs.getInt("tab");
				String writeid=rs.getString("writeid");
				String writename=rs.getString("writename");
				Date writeday=rs.getDate("writeday");
				String filename=rs.getString("filename");
				String isdel=rs.getString("isdel");
				String kind=rs.getString("kind");
				board
				=new BoardVO(idx, title, content, readcount, parentid, tab, writeid, writename, writeday, filename, isdel, kind);
				System.out.println(board.toString());
			}
			rs.close();
			ps.close();
			
			return board;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(int idx, BoardVO board) {
		try {
			String sql="update board set title=?, content=? where idx=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,board.getTitle());
			ps.setString(2,board.getContent());
			ps.setInt(3, idx);
			int result=ps.executeUpdate();
			ps.close();
			
			return result;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int idx) {
		try {
			String sql="update board set isdel = 1 where idx = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			int result=ps.executeUpdate();
			ps.close();
			
			return result;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//댓글입력
	@Override //힌트:댓글은 parentid, tab(부모글, 댓글깊이)중요한 부분
	public int replyInsert(BoardVO board) {
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("insert into board(idx,title,content,readcount,");
			sql.append("parentid, tab,");
			sql.append("writeid,writename,writeday,");
			sql.append("filename,isdel,kind)");
			sql.append(" values(board_idx_seq.nextval,?,?,0,");
			sql.append("?,?,");
			sql.append("?,?,sysdate,");
			sql.append("?,0,'일반게시판')");
			
			PreparedStatement ps=conn.prepareStatement(sql.toString());
			ps.setString(1,board.getTitle());
			ps.setString(2,board.getContent());
			ps.setInt(3,board.getParentid());
			ps.setInt(4,board.getTab());
			ps.setString(5,board.getWriteid());
			ps.setString(6,board.getWritename());
			ps.setString(7,board.getFilename()); //full경로 or 파일명 선택
			int result=ps.executeUpdate();
			ps.close();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<BoardVO> pageList(int currentPage,int countPerPage){
		try {
		int startRow = (currentPage - 1) * countPerPage + 1;//글의 시작번호
		int endRow = currentPage * countPerPage;//글의 끝번호
		
		StringBuffer sql=new StringBuffer();
			sql.append("select rownum, t.* from ");
			sql.append("(select rownum r, t1.* from ");
			sql.append("(select * from board start with parentid=0 connect ");
			sql.append("by prior idx=parentid order siblings by idx desc) t1 ");
			sql.append("where rownum<=? order by rownum desc) t ");
			sql.append("where rownum<=? order by r asc");
		
		PreparedStatement ps=conn.prepareStatement(sql.toString());
		//첫번째 ?는 게시글의 마지막 번호를 의미를 하고
		//뒤집어서 10개의 게시물만 얻어오면 되므로 두번째 ?는 페이지당 게시물로 입력
		ps.setInt(1, endRow);
		//두번째 ?는 10개시물이 1page이므로 결과중 10개만 출력
		ps.setInt(2, countPerPage); 
		//Q.마지막페이지의 게시물이 10개 되지 않는 경우 해당게시물의 수만큼 입력값을 변경
		//예)86개의 게시물이 있는 경우(요청한 페이지는 9페이지 요청, 총페이지는 9페이지)
		//2번째 입력하는 값은 6개만 출력
		
		int totalPage=0;
		if(totalCount()%countPerPage!=0) {
			totalPage=totalCount()/countPerPage+1;
		}else {
			totalPage=totalCount()/countPerPage;
		}
		
		if(totalPage==currentPage) {
			ps.setInt(2, totalCount()%countPerPage);
		}
			
			
			
			
		ResultSet rs = ps.executeQuery();

		//*****BoardVO을 리스트로 저장할 수 있는 객체(단일객체 vo생성)
		List<BoardVO> list=new ArrayList<BoardVO>();
		while(rs.next()){
			int idx=rs.getInt("idx");
			String title=rs.getString("title");
			String content=rs.getString("content");;
			int readcount=rs.getInt("readcount");
			int parentid=rs.getInt("parentid");
			int tab=rs.getInt("tab");
			String writeid=rs.getString("writeid");
			String writename=rs.getString("writename");
			Date writeday=rs.getDate("writeday");
			String filename=rs.getString("filename");
			String isdel=rs.getString("isdel");
			String kind=rs.getString("kind");
			list.add(new BoardVO(idx, title, content, readcount, parentid, tab, writeid, writename, writeday, filename, isdel, kind));
		}
		ps.close();
		rs.close();
	
		return list;
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	//전체게시물수 확인하는 함수
		public int totalCount() {
			int totalCount=0;
			//전체게시물 수
			String sql = "select count(*) from board";
			try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt("count(*)");
			}
			rs.close();
			ps.close();
			
			return totalCount;
			}catch(Exception e) {
				e.printStackTrace();
			}

			return totalCount;
		}

		@Override
		//조회수 1증가하는 함수
		public int readcountUP(int idx) {
		try {
			
		String sql="update board set readcount=readcount+1 where idx=?";
			
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1,idx);
		int result=ps.executeUpdate();
		ps.close();
		
		return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
			return 0;
	  }
		
		public int insertreply(BoardVO board) {
			try {
				/*
				String sql="insert into board(idx,title,content,readcount,"
						+ "parentid, tab,"
						+ "writeid,writename,writeday,"
						+ "filename,isdel,kind)"
						+ " values(board_idx_seq.nextval,?,?,0,"
						+ "0,0,"
						+ "?,?,sysdate,"
						+ "?,0,'일반게시판')";
				*/
				StringBuffer sql=new StringBuffer();
				sql.append("insert into board(idx,title,content,readcount,");
				sql.append("parentid, tab,");
				sql.append("writeid,writename,writeday,");
				sql.append("filename,isdel,kind)");
				sql.append(" values(board_idx_seq.nextval,?,?,0,");
				sql.append("?,?,");
				sql.append("?,?,sysdate,");
				sql.append("?,0,'일반게시판')");
				
				PreparedStatement ps=conn.prepareStatement(sql.toString());
				ps.setString(1,board.getTitle());
				ps.setString(2,board.getContent());
				ps.setInt(3,board.getParentid());
				ps.setInt(4, board.getTab());
				ps.setString(5,board.getWriteid());
				ps.setString(6,board.getWritename());
				ps.setString(7,board.getFilename());
				
				int result=ps.executeUpdate();
				ps.close();
				
				return result;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		public List<BoardVO> serpageList(String field, String query, int currentPage, int countPerPage){
		    try {
		        int startRow = (currentPage - 1) * countPerPage + 1;
		        int endRow = currentPage * countPerPage;

		        StringBuilder sql = new StringBuilder();
		        sql.append("select * from ");
		        sql.append("(select rownum as r, t.* from ");
		        sql.append("(select * from (select * from board where lower(" + field + ") like lower(?) order by idx desc) start with parentid=0 connect by prior idx=parentid) t ");
		        sql.append("where rownum <= ? order by rownum desc) ");
		        sql.append("where r >= ? order by r asc");



		        PreparedStatement ps = conn.prepareStatement(sql.toString());
		        ps.setString(1, "%" + query + "%");
		        ps.setInt(2, endRow);
		        ps.setInt(3, startRow);

		        int totalPage=0;
		        if(totalCount() % countPerPage != 0) {
		            totalPage = totalCount() / countPerPage + 1;
		        } else {
		            totalPage = totalCount() / countPerPage;
		        }

		        if(totalPage == currentPage) {
		            ps.setInt(2, totalCount() % countPerPage);
		        }

		        ResultSet rs = ps.executeQuery();
		        List<BoardVO> list = new ArrayList<>();
		        while(rs.next()){
		            int idx = rs.getInt("idx");
		            String title = rs.getString("title");
		            String content = rs.getString("content");
		            int readcount = rs.getInt("readcount");
		            int parentid = rs.getInt("parentid");
		            int tab = rs.getInt("tab");
		            String writeid = rs.getString("writeid");
		            String writename = rs.getString("writename");
		            Date writeday = rs.getDate("writeday");
		            String filename = rs.getString("filename");
		            String isdel = rs.getString("isdel");
		            String kind = rs.getString("kind");
		            list.add(new BoardVO(idx, title, content, readcount, parentid, tab, writeid, writename, writeday, filename, isdel, kind));
		        }
		        ps.close();
		        rs.close();
		        return list;
		    }catch(Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}


}
