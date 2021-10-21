package kr.or.ddit.repSer.vo;

//수리서비스테이블VO
public class RepSerVO {
	private int empNum;
	private int cusNum;
	private String carNum;
	private int serNum;
	private int repCost;
	private int repTme;
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public int getCusNum() {
		return cusNum;
	}
	public void setCusNum(int cusNum) {
		this.cusNum = cusNum;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public int getSerNum() {
		return serNum;
	}
	public void setSerNum(int serNum) {
		this.serNum = serNum;
	}
	public int getRepCost() {
		return repCost;
	}
	public void setRepCost(int repCost) {
		this.repCost = repCost;
	}
	public int getRepTme() {
		return repTme;
	}
	public void setRepTme(int repTme) {
		this.repTme = repTme;
	}
	@Override
	public String toString() {
		return "RepSerVO [empNum=" + empNum + ", cusNum=" + cusNum + ", carNum=" + carNum + ", serNum=" + serNum
				+ ", repCost=" + repCost + ", repTme=" + repTme + "]";
	}
	
	
}
