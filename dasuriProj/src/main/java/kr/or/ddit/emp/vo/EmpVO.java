package kr.or.ddit.emp.vo;

//직원테이블용 VO(EMP)
public class EmpVO {
	private int rnum;	//글번호
	private int empNum;
	private String nm;
	private String addr;
	private String pne;
	private int sal;
	private String pwd;	//비밀번호
	private String adminYn;	//관리자 여부
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPne() {
		return pne;
	}
	public void setPne(String pne) {
		this.pne = pne;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getAdminYn() {
		return adminYn;
	}
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}
	@Override
	public String toString() {
		return "EmpVO [rnum=" + rnum + ", empNum=" + empNum + ", nm=" + nm + ", addr=" + addr + ", pne=" + pne
				+ ", sal=" + sal + ", pwd=" + pwd + ", adminYn=" + adminYn + "]";
	}
	
	
	
	
}
