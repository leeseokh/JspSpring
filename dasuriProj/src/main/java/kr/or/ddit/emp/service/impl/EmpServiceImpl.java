package kr.or.ddit.emp.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.or.ddit.emp.dao.EmpDao;
import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.vo.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {
//	@Autowired
	@Inject
	EmpDao empDao;
	
	//직원번호가 리턴됨
	@Override
	public int insert(EmpVO empVo) {
		int result = this.empDao.insert(empVo);
		if(result>0) {//insert가 잘 되었다면..
			//방금 입력된 직원의 번호를 get
			return empVo.getEmpNum();
		}else {//insert가 안되었다면..
			return 0;
		}
	}
	
	//직원번호를 조건으로 하여 직원의 정보를 리턴
	@Override
	public EmpVO selectEmp(String empNum) {
		return this.empDao.selectEmp(empNum);
	}
	
	//직원번호(empNum)를 조건으로 하여 empVo에 담긴 대로 직원정보를 update 함
	@Override
	public int update(EmpVO empVo) {
		return this.empDao.update(empVo);
	}
	
	//직원번호(String empNum)를 조건으로 하여 해당직원의 데이터 삭제
	@Override
	public int delete(String empNum) {
		return this.empDao.delete(empNum);
	}
	
	//직원 목록 가져오기
	@Override
	public List<EmpVO> selectEmpList(String currentPage){
		return this.empDao.selectEmpList(currentPage);
	}
	
	//EMP테이블의 카디널리티
	@Override
	public int empTotal() {
		return this.empDao.empTotal();
	}
	
	//로그인
	@Override
	public EmpVO login(EmpVO empVo, HttpSession session) {
		//직원번호(empNum), 비밀번호(pwd)를 통해 select한 결과를 empVoResult 객체에 담음
		EmpVO empVoResult = this.empDao.login(empVo);
		System.out.println("empVoResult : " + empVoResult);
		
		if(empVoResult!=null) {//입력값에 해당되는 직원이 있으면..
			//세션변수에 정보를 입력(직원번호, 직원명)
			session.setAttribute("empNum", empVoResult.getEmpNum());
			session.setAttribute("nm", empVoResult.getNm());
			session.setAttribute("adminYn", empVoResult.getAdminYn());	//관리자 여부
			
			return empVoResult;
		}else {	//로그인 실패
			return null;
		}
	}
	
	//모든 직원 목록 가져오기
	@Override
	public List<EmpVO> selectEmpAllList(){
		return this.empDao.selectEmpAllList();
	}
}












