package kr.or.ddit.wk.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.wk.vo.WkVO;

@Repository
public class WkDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//근무(WK) 테이블에 데이터를 입력. 어떤 사원이 어떤 사업장에 근무하는지..
	public int insert(WkVO wkVo) {
		return this.sqlSessionTemplate.insert("wk.insert", wkVo);
	}
	
	//사원 정보 목록
	public List<EmpVO> selectEmpList(Map<String, Object> map){
		//map : {"currentPage","3"}
		return this.sqlSessionTemplate.selectList("wk.selectEmpList", map);
	}
	
	//사업장 정보 목록
	public List<SiteVO> selectSiteList(Map<String, Object> siteMap){
		return this.sqlSessionTemplate.selectList("wk.selectSiteList", siteMap);
	}
	
	//사원 상세 정보 empNum=1
	public EmpVO selectEmpDetail(int empNum) {
		return this.sqlSessionTemplate.selectOne("wk.selectEmpDetail", empNum);
	}
	
	//특정삭제 1 >> 사원 번호 : map.get("empNum"));
	public int deleteEmp(Map<String,Object> empNum) {
		return this.sqlSessionTemplate.delete("wk.deleteEmp", empNum);
	}
	
	//특정삭제 2 >> 특정 사원의 정보가 존재하는지?
	public int selectCheckWk(Map<String,Object> map) {
		//존재하면 1 이상, 없으면 0
		return this.sqlSessionTemplate.selectOne("wk.selectCheckWk", map);
	}
	
	//특정삭제 1 >> 사업장 번호 : map.get("empNum"));
	public int deleteSite(Map<String,Object> siteNum) {
		return this.sqlSessionTemplate.delete("wk.deleteSite", siteNum);
	}
	
	//특정삭제 2 >> 특정 사업장의 정보가 존재하는지?
	public int selectCheckSite(Map<String,Object> map) {
		//존재하면 1 이상, 없으면 0
		return this.sqlSessionTemplate.selectOne("wk.selectCheckSite", map);
	}
	
	//사원의 모든 인원수 구하기
	public int selectCountEmp() {
		return this.sqlSessionTemplate.selectOne("wk.selectCountEmp");
	}

	public int selectCountSite() {
		return this.sqlSessionTemplate.selectOne("wk.selectCountSite");
	}
	
}










