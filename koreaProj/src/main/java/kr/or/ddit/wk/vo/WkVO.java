package kr.or.ddit.wk.vo;

import java.util.Date;

//WK 테이블 용
public class WkVO {
	private int empNum;	//사원 번호
	private int siteNum;	//사업장 번호
	private Date wkStartDt;	//근무 시작 일자
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public Date getWkStartDt() {
		return wkStartDt;
	}
	public void setWkStartDt(Date wkStartDt) {
		this.wkStartDt = wkStartDt;
	}
	@Override
	public String toString() {
		return "WkVO [empNum=" + empNum + ", siteNum=" + siteNum + ", wkStartDt=" + wkStartDt + "]";
	}
	
}
