package kr.or.ddit.emp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.emp.vo.EmpVO;

@Repository
public class EmpDao {
	//root-context.xml
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	//EMP 테이블로 insert
	public int insert(EmpVO empVo) {
		//return 몇 건이 들어갔는가..
		return this.sqlSessionTemplate.insert("emp.insert", empVo);
	}
}
