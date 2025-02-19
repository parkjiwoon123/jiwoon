package replyboard;
//sql문에 있는 필드를 참조하여 java변경
//기본생성자, 필드생성자, get/set함수, toString 재정의

import java.util.Date;

public class BoardVO {
	private int idx;
	private String title;
	private String content;
	private int readcount;
	private int parentid;
	private int tab;
	private String writeid;
	private String writename;
	private Date writeday;
	private String filename;
	private String isdel;
	private String kind;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	public BoardVO(int idx, String title, String content, int readcount, int parentid, int tab, String writeid,
			String writename, Date writeday, String filename, String isdel, String kind) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.parentid = parentid;
		this.tab = tab;
		this.writeid = writeid;
		this.writename = writename;
		this.writeday = writeday;
		this.filename = filename;
		this.isdel = isdel;
		this.kind = kind;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public String getWriteid() {
		return writeid;
	}
	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}
	public String getWritename() {
		return writename;
	}
	public void setWritename(String writename) {
		this.writename = writename;
	}
	public Date getWriteday() {
		return writeday;
	}
	public void setWriteday(Date writeday) {
		this.writeday = writeday;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", title=" + title + ", content=" + content + ", readcount=" + readcount
				+ ", parentid=" + parentid + ", tab=" + tab + ", writeid=" + writeid + ", writename=" + writename
				+ ", writeday=" + writeday + ", filename=" + filename + ", isdel=" + isdel + ", kind=" + kind + "]";
	}
	
}
