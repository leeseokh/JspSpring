package kr.or.ddit.siteMat.vo;
//SITE_MAT 테이블 용
public class SiteMatVO {
	private int siteNum;
	private String matNmCd;
	private String cnt;
	private String purPri;
	private String purDt;
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public String getMatNmCd() {
		return matNmCd;
	}
	public void setMatNmCd(String matNmCd) {
		this.matNmCd = matNmCd;
	}
	
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getPurPri() {
		return purPri;
	}
	public void setPurPri(String purPri) {
		this.purPri = purPri;
	}
	public String getPurDt() {
		return purDt;
	}
	public void setPurDt(String purDt) {
		this.purDt = purDt;
	}
	@Override
	public String toString() {
		return "SiteMatVO [siteNum=" + siteNum + ", matNmCd=" + matNmCd + ", cnt=" + cnt
				+ ", purPri=" + purPri + ", purDt=" + purDt + "]";
	}
	
	
}
