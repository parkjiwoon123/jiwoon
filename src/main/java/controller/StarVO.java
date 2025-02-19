package controller;

public class StarVO {
    private String naver;
    private String kakao;
    private String gung;
    private String buk;
	public StarVO(String naver, String kakao, String gung, String buk) {
		super();
		this.naver = naver;
		this.kakao = kakao;
		this.gung = gung;
		this.buk = buk;
	}
	public String getNaver() {
		return naver;
	}
	public void setNaver(String naver) {
		this.naver = naver;
	}
	public String getKakao() {
		return kakao;
	}
	public void setKakao(String kakao) {
		this.kakao = kakao;
	}
	public String getGung() {
		return gung;
	}
	public void setGung(String gung) {
		this.gung = gung;
	}
	public String getBuk() {
		return buk;
	}
	public void setBuk(String buk) {
		this.buk = buk;
	}
	@Override
	public String toString() {
		return "StarVO [naver=" + naver + ", kakao=" + kakao + ", gung=" + gung + ", buk=" + buk + "]";
	}
    
    
}
