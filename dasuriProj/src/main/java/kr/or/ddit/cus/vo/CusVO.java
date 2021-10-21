package kr.or.ddit.cus.vo;

import org.springframework.web.multipart.MultipartFile;

public class CusVO {
	
	private int cusNum;	//고객 일련 번호
	private String cusNm;	//고객 명
	private String zipcode;	//구)우편번호 + 신)도로명주소번호
	private String addr1;	//주소
	private String addr2;	//상세 주소
	private String pne;	//연락처
	private String pwd;	//비밀 번호
	private String cusDetail;	//고객 상세 정보
	private MultipartFile cusImage;	//첨부파일
	private String cusImageUpload;	//CUS 테이블의 CUS_IMAGE 폴더에 들어갈 서버의 이미지 경로
	private int rnum;	//행번호
	
	public String getCusImageUpload() {
		return cusImageUpload;
	}
	public void setCusImageUpload(String cusImageUpload) {
		this.cusImageUpload = cusImageUpload;
	}
	public int getCusNum() {
		return cusNum;
	}
	public void setCusNum(int cusNum) {
		this.cusNum = cusNum;
	}
	public String getCusNm() {
		return cusNm;
	}
	public void setCusNm(String cusNm) {
		this.cusNm = cusNm;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPne() {
		return pne;
	}
	public void setPne(String pne) {
		this.pne = pne;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCusDetail() {
		return cusDetail;
	}
	public void setCusDetail(String cusDetail) {
		this.cusDetail = cusDetail;
	}
	public MultipartFile getCusImage() {
		return cusImage;
	}
	public void setCusImage(MultipartFile cusImage) {
		this.cusImage = cusImage;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	@Override
	public String toString() {
		return "CusVO [cusNum=" + cusNum + ", cusNm=" + cusNm + ", zipcode=" + zipcode + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", pne=" + pne + ", pwd=" + pwd + ", cusDetail=" + cusDetail + ", cusImage=" + cusImage
				+ ", cusImageUpload=" + cusImageUpload + ", rnum=" + rnum + "]";
	}
	
}
