package replyboard;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class BoardService {
	OracleBoardDAO dao=new OracleBoardDAO();
	
	public BoardpageList pageList(int currentPage, int countPerPage) {
		/**********전체게시물수(dao), 페이지당 글의 수(전달값) *****/		
		int totalCount = dao.totalCount(); //전체 게시물 수
		//int countPerPage = 10; //페이지당 글의 수 (전달받은값에 있음)
		/**********전체페이지수(연산)**********/
		int totalPage = 0; //전체페이지 수
		if(totalCount%countPerPage!=0) {
			totalPage=totalCount/countPerPage+1;
		}else {
			totalPage=totalCount/countPerPage;
		}
		/*********시작페이지, 끝페이지(연산)******/
		int startPage = 0; //시작페이지
		int endPage = 0; //끝페이지
		if ((currentPage / 5) < 2) {
		    startPage = 1;
		    endPage = startPage + 4;
		    if ((currentPage % 5) != 0) {
		        startPage = (currentPage / 5) * 5 + 1;
		        endPage = Math.min(startPage + 4, totalPage);
		    }
		} else {
		    startPage = (currentPage / 5) * 5 + 1;
		    endPage = Math.min(startPage + 4, totalPage);
		}
		/*********현재페이지(전달값), 글의시작번호, 글의끝번호(연산)*********/
		//int currentPage = 1; //현재페이지
		int startRow = (currentPage - 1) * countPerPage + 1;//글의 시작번호
		int endRow = currentPage * countPerPage;//글의 끝번호
		/*********게시글목록(dao)***********/
		List<BoardVO> list=dao.pageList(currentPage, countPerPage);
		/***************************/
		//위에서 구한 내용을 PageList객체에 담아서 전송
		//전체 게시물 수, 페이지당글의수, 
		//전체페이지, 시작페이지, 끝페이지, 현재페이지,
		//글의시작번호,글의끝번호,글목록
		BoardpageList pagelist=new BoardpageList();
		pagelist.setTotalCount(totalCount);
		pagelist.setCountPerPage(countPerPage);
		pagelist.setTotalPage(totalPage);
		pagelist.setStartPage(startPage);
		pagelist.setEndPage(endPage);
		pagelist.setCurrentPage(currentPage);
		pagelist.setStartRow(startRow);
		pagelist.setEndRow(endRow);
		pagelist.setList(list);
		return  pagelist;
	}
	public BoardVO findOneById(int idx) {
		dao.readcountUP(idx); //조회수1증가
		return dao.findOneById(idx);
	}
	public int insert(BoardVO board) {
		return dao.insert(board);
		
	}
	public int update(BoardVO board) {
		return dao.update(board.getIdx(), board);
		
	}
		
	
	public void del(int idx) {
		dao.delete(idx);
		
	}
	public int insertreply(BoardVO board) {
	
		return dao.insertreply(board);
		// TODO Auto-generated method stub
		
	}
	public BoardpageList search(String field, String query, Integer currentPage) {
		// TODO Auto-generated method stub
		int totalCount;
		totalCount = dao.totalCount();
		int countPerPage;
		countPerPage = 10;
		int totalPage = 0;
		if(totalCount % countPerPage == 0 ) {
			totalPage = totalCount/countPerPage;
		}
		else {
			totalPage = (totalCount / countPerPage) +1 ;
		}
		
		int startPage;
		int endPage;
		if ((currentPage / 5) < 2) {
		    startPage = 1;
		    endPage = startPage + 4;
		    if ((currentPage % 5) != 0) {
		        startPage = (currentPage / 5) * 5 + 1;
		        endPage = Math.min(startPage + 4, totalPage);
		    }
		} else {
		    startPage = (currentPage / 5) * 5 + 1;
		    endPage = Math.min(startPage + 4, totalPage);
		}
		int startRow =0; 
		int endRow =0; 
		startRow = (currentPage - 1) * countPerPage + 1;
		endRow = currentPage * countPerPage;
		
		List<BoardVO> list=dao.serpageList(field,query,currentPage, countPerPage); 

		
		
		
		BoardpageList pagelist=new BoardpageList();
		pagelist.setTotalCount(totalCount);
		pagelist.setCountPerPage(countPerPage);
		pagelist.setTotalPage(totalPage);
		pagelist.setCurrentPage(currentPage);
		pagelist.setStartPage(startPage);
		pagelist.setEndPage(endPage);
		pagelist.setStartRow(startRow);
		pagelist.setEndRow(endRow);
		pagelist.setList(list);
		
		return pagelist;
	
	}


}
