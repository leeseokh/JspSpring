package kr.or.ddit.siteMat.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.siteMat.vo.SiteMatVO;

public interface SiteMatService {
	//메소드 시그니처
	public SiteMatVO insert(SiteMatVO siteMatVo);

	public List<Map<String, Object>> selectConMatInfo();
	
	//사업장 검색 창에서 사용되는 사업장 검색 쿼리
	public List<SiteVO> selectSiteList(Map<String, Object> map);

	//사업장 자재 목록
	public List<Map<String, Object>> siteMatList(Map<String, Object> map);
	
}
