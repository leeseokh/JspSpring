package kr.or.ddit.site.vo;
//SITE 테이블 용
public class SiteVO {
	private int siteNum;
	private String siteNm;
	private String addr;
	private String phnNum;
	private String contAmt;
	private String inPeo;
	private String stDt;
	private String exComDt;
	private String etc;
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhnNum() {
		return phnNum;
	}
	public void setPhnNum(String phnNum) {
		this.phnNum = phnNum;
	}
	public String getContAmt() {
		return contAmt;
	}
	public void setContAmt(String contAmt) {
		this.contAmt = contAmt;
	}
	public String getInPeo() {
		return inPeo;
	}
	public void setInPeo(String inPeo) {
		this.inPeo = inPeo;
	}
	public String getStDt() {
		return stDt;
	}
	public void setStDt(String stDt) {
		this.stDt = stDt;
	}
	public String getExComDt() {
		return exComDt;
	}
	public void setExComDt(String exComDt) {
		this.exComDt = exComDt;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	@Override
	public String toString() {
		return "SiteVO [siteNum=" + siteNum + ", siteNm=" + siteNm + ", addr=" + addr + ", phnNum=" + phnNum
				+ ", contAmt=" + contAmt + ", inPeo=" + inPeo + ", stDt=" + stDt + ", exComDt=" + exComDt + ", etc="
				+ etc + "]";
	}
	
	
}
