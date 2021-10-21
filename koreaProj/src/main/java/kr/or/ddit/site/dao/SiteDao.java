package kr.or.ddit.site.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.site.vo.SiteVO;

@Repository
public class SiteDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Site 테이블로 insert
	public int insert(SiteVO siteVo) {
		//return 몇 개가 insert 되었는가..
		return this.sqlSessionTemplate.insert("site.insert", siteVo);
	}
}
