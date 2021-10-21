package kr.or.ddit.car.vo;

import java.util.List;

//자동차테이블VO
public class CarVO {
	private String carNum;
	private String mk;
	private int py;
	private int driDist;
	private String cusNum;
	private List<CarVO> carVo;
	
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getMk() {
		return mk;
	}
	public void setMk(String mk) {
		this.mk = mk;
	}
	public int getPy() {
		return py;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public int getDriDist() {
		return driDist;
	}
	public void setDriDist(int driDist) {
		this.driDist = driDist;
	}
	public String getCusNum() {
		return cusNum;
	}
	public void setCusNum(String cusNum) {
		this.cusNum = cusNum;
	}
	public List<CarVO> getCarVo() {
		return carVo;
	}
	public void setCarVo(List<CarVO> carVo) {
		this.carVo = carVo;
	}
	@Override
	public String toString() {
		return "CarVO [carNum=" + carNum + ", mk=" + mk + ", py=" + py + ", driDist=" + driDist + ", cusNum=" + cusNum
				+ ", carVo=" + carVo + "]";
	}
	
	
}
