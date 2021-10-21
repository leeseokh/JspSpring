package kr.or.ddit.emp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.or.ddit.emp.vo.EmpVO;

//interface : 소통을 위한 매개체
//클래스가 implements를 하여 구현을 해줘야 함(메소드-버튼, 필드-아이템)
//int a = 10; -> public abstract int a = 0;(수정이 불가능)
public interface EmpService {
	//메소드 시그니처
	public int insert(EmpVO empVo);
	//직원번호를 조건으로 하여 직원의 정보를 리턴
	public EmpVO selectEmp(String empNum);
	//직원번호(empNum)를 조건으로 하여 empVo에 담긴 대로 직원정보를 update 함
	public int update(EmpVO empVo);
	//직원번호(String empNum)를 조건으로 하여 해당직원의 데이터 삭제
	public int delete(String empNum);
	//직원 목록 가져오기
	public List<EmpVO> selectEmpList(String currentPage);
	//EMP 테이블의 카디널리티
	public int empTotal();
	//로그인
	public EmpVO login(EmpVO empVo, HttpSession session);
	//모든 직원 목록 가져오기
	public List<EmpVO> selectEmpAllList();	
}



