package kr.or.ddit.emp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.emp.vo.EmpVO;

@Repository
public class EmpDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(EmpVO empVo) {
		//namespace.id
		return this.sqlSessionTemplate.insert("emp.insert", empVo);
	}
	
	//직원번호를 조건으로 하여 직원의 정보를 리턴
	public EmpVO selectEmp(String empNum) {
		//namespace.id
		return this.sqlSessionTemplate.selectOne("emp.selectEmp", empNum);
	}
	
	//직원번호(empNum)를 조건으로 하여 empVo에 담긴 대로 직원정보를 update 함
	public int update(EmpVO empVo) {
		//namespace.id
		return this.sqlSessionTemplate.update("emp.update", empVo);
	}
	
	//직원번호(String empNum)를 조건으로 하여 해당직원의 데이터 삭제
	public int delete(String empNum) {
		//namespace.id
		return this.sqlSessionTemplate.delete("emp.delete", empNum);
	}
	
	//직원 목록 가져오기 
	public List<EmpVO> selectEmpList(String currentPage){
		return this.sqlSessionTemplate.selectList("emp.selectEmpList", currentPage);
	}
	
	//EMP테이블의 카디널리티
	public int empTotal() {
		return this.sqlSessionTemplate.selectOne("emp.empTotal");
	}
	
	//로그인
	public EmpVO login(EmpVO empVo) {
		return this.sqlSessionTemplate.selectOne("emp.login", empVo);
	}
	
	//모든 직원 목록 가져오기
	public List<EmpVO> selectEmpAllList(){
		//namespace.id
		return this.sqlSessionTemplate.selectList("emp.selectEmpAllList");
	}
}








