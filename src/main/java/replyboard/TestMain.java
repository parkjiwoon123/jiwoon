package replyboard;

import java.util.List;

public class TestMain {
	//二쇱쓽�궗�빆: main�븿�닔瑜� �떎�뻾�븷 �븣 lib�뿉 �씪�씠釉뚮윭由щ�� �꽔�뒗�떎.
	public static void main(String[] args) {
		//board �엯�젰�뀒�뒪�듃
		/*
		BoardVO board=new BoardVO();
		board.setTitle("dao test以�");
		board.setContent("dao瑜� �옉�꽦以묒뿉 �엳�뒿�땲�떎.");
		board.setWriteid("admin");
		board.setWritename("湲몃룞�씠");
		board.setFilename("a.txt");
		int result=new OracleBoardDAO().insert(board);
		System.out.println(result);
		
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into board(idx,title,content,readcount,");
		sql.append("parentid, tab,");
		sql.append("filename,isdel,kind)");
		sql.append(" values(board_idx_seq.nextval,?,?,0,");
		sql.append("0,0,");
		sql.append("?,?,sysdate,");
		sql.append("?,0,'�씪諛섍쾶�떆�뙋')");
		
		System.out.println(sql);
		*/
		
		//List<BoardVO> list=new OracleBoardDAO().findAll();
		//System.out.println(list);
		
		//System.out.println(new OracleBoardDAO().findOneById(1));
		System.out.println(new OracleBoardDAO().pageList(2,10));
		
		
	}

}
