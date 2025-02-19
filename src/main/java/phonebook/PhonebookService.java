package phonebook;

import java.util.List;

import org.springframework.stereotype.Service;

import controller.MemberVO;
@Service
public class PhonebookService {
 
	//PhonebookOracleDAO dao=new PhonebookOracleDAO();
	PhonebookOracleDAO dao=new PhonebookOracleDAO();
	public Pagelist pageList(int currentPage) {
		/* 아래의 9가지 정보를 구해야한다.
		totalCount : 데이터베이스에서 가져옴
		countPerPage : 정하면 됨
		totalPage : 계산처리
		currentPage : 넘겨받은 값 철리
		startPage : 계산처리
		endPage : 계산처리
		startRow : 현재페이지로 글의 시작번호 계산
		endRow : 현재페이지로 글의 마지막번호 계산
		List<PhonebookVO> list : 데이터베이스에서 가져옴
		
		위의 9가지 정보를 구한다음 이 내용을 PageList객체에 한번에 담아서 전송
		*/
		
		/*-----------------------------*/
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
		
		List<PhonebookVO> list=dao.pageList(currentPage, countPerPage); 

		
		
		
		Pagelist pagelist=new Pagelist();
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

	public List<PhonebookVO> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public List<PhonebookVO> pageList(int currentPage, int countPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(PhonebookVO phonebook) {
		return dao.update(phonebook);
		
	}

	public PhonebookVO findone(int id) {
		return dao.findOne(id);
		// TODO Auto-generated method stub
		
	}

	public int del(int num) {
		return dao.del(num);
		// TODO Auto-generated method stub
		
	}
	public int insert(String name,String number,String hp) {
		PhonebookVO vo = new PhonebookVO(name,number,hp);
	System.out.println(vo.toString());
		return dao.insert(vo);
	}

	public Pagelist search(String field,String query,int currentPage) {
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
		
		List<PhonebookVO> list=dao.search(field,query,currentPage, countPerPage); 

		
		
		
		Pagelist pagelist=new Pagelist();
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
