package kr.or.ddit.siteMat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.siteMat.dao.SiteMatDao;
import kr.or.ddit.siteMat.service.SiteMatService;
import kr.or.ddit.siteMat.vo.SiteMatVO;

@Service
public class SiteMatServiceImpl implements SiteMatService{
	@Autowired
	SiteMatDao siteMatDao;
	@Override
	public SiteMatVO insert(SiteMatVO siteMatVo){
		int affectRowCount = this.siteMatDao.insert(siteMatVo);
		if(affectRowCount>0) {//insert완료
			return siteMatVo;
		}else {//insert 실패
			return null;
		}
	}
	@Override
	public List<Map<String, Object>> selectConMatInfo(){
		return this.siteMatDao.selectConMatInfo();
	}
	
	//사업장 검색 창에서 사용되는 사업장 검색 쿼리
	@Override
	public List<SiteVO> selectSiteList(Map<String,Object> map){
		return this.siteMatDao.selectSiteList(map);
	}
	
	//사업장 자재 목록
	@Override
	public List<Map<String, Object>> siteMatList(Map<String, Object> map){
		return this.siteMatDao.siteMatList(map);
	}
}








