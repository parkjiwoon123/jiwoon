package replyboard;

import java.util.List;

public class BoardpageList {
	int totalCount = 0; //��ü �Խù� ��
	int countPerPage = 10; //�������� ���� ��
	int totalPage = 0; //��ü������ ��
	int startPage = 0; //����������
	int endPage = 0; //��������
	int currentPage = 1; //����������
	int startRow = (currentPage - 1) * countPerPage + 1;//���� ���۹�ȣ
	int endRow = currentPage * countPerPage;//���� ����ȣ
	
	List<BoardVO> list;
	
	public BoardpageList() {
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
	public List<BoardVO> getList() {
		return list;
	}
	public void setList(List<BoardVO> list) {
		this.list = list;
	}
	
	
}
