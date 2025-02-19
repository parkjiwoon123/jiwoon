package replyboard;

import java.util.List;

//�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 媛��옣 湲곕낯�씠 �릺�뒗 紐낅졊
//�엯�젰, �쟾泥댁텧�젰, �꽑�깮異쒕젰, �꽑�깮�닔�젙, �꽑�깮�궘�젣
public interface BoardDAOInter {
	//湲곕낯�쟻�씤 �궗�빆
	public int insert(BoardVO board); //�엯�젰
	public List<BoardVO> findAll();//�쟾泥댁텧�젰
	public BoardVO findOneById(int idx);//�꽑�깮異쒕젰
	public int update(int idx,BoardVO board);//�꽑�깮�닔�젙
	public int delete(int idx);//�꽑�깮�궘�젣
	
	//異붽��쟻�씤�궗�빆
	public int replyInsert(BoardVO board); //�뙎湲��엯�젰
	public int readcountUP(int idx); //조회수 1증가
}
