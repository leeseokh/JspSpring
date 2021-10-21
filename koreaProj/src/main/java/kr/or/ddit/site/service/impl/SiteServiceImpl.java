package kr.or.ddit.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.site.dao.SiteDao;
import kr.or.ddit.site.service.SiteService;
import kr.or.ddit.site.vo.SiteVO;

@Service
public class SiteServiceImpl implements SiteService {
	@Autowired
	SiteDao siteDao;
	
	//SITE 테이블에 insert
	@Override
	public int insert(SiteVO siteVo) {
		//return : insert된 행의 개수
		int affectRowCount = this.siteDao.insert(siteVo);
		if(affectRowCount>0) {//insert가 되면
			//<selectKey order="BEFORE" keyProperty="siteNum" resultType="int">
			//방금 insert된 기본키 데이터를 GET
			return siteVo.getSiteNum();
		}else { //insert가 실패
			return 0;
		}
	}
}




