package kr.or.ddit.emp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.emp.dao.EmpDao;
import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.vo.EmpVO;

import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDao empDao;
	//EMP 테이블에 insert
	@Override
	public int insert(EmpVO empVo) {
		//EMP 테이블에 insert 성공 시 0보다 큼
		int affectRowcount = this.empDao.insert(empVo);
		if(affectRowcount>0) {	//성공
			//EMP 테이블의 기본키를 return
			//SELECT NVL(MAX(EMP_NUM),0)+1 FROM EMP 이것이 setEmpNum()
			//처리가 되었음
			return empVo.getEmpNum();
		}else {//실패
			return 0;
		}
	}
}



