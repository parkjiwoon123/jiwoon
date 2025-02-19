package phonebook;

import java.util.List;

public class Pagelist {
	//위의 두개로 나올 수 있는 내용(전체 페이지수, 시작페이지, 끝페이지)
	int totalCount = 0; //전체 게시물 수
	int countPerPage = 10; //페이지당 글의 수
	int totalPage = 0; //전체페이지 수
	int startPage = 0; //시작페이지
	int endPage = 0; //끝페이지
	int currentPage = 1; //현재페이지
	int startRow = (currentPage - 1) * countPerPage + 1;//글의 시작번호
	int endRow = currentPage * countPerPage;//글의 끝번호
	List<PhonebookVO> list;
	public Pagelist() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public List<PhonebookVO> getList() {
		return list;
	}
	public void setList(List<PhonebookVO> list) {
		this.list = list;
	}
	
}
