package kr.or.ddit.siteMat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.siteMat.service.SiteMatService;
import kr.or.ddit.siteMat.vo.SiteMatVO;

@RequestMapping(value="/sitemat")
@Controller
public class SiteMatController {
	private static Logger logger = LoggerFactory.getLogger(SiteMatController.class);
	
	@Autowired
	SiteMatService siteMatService;
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		List<Map<String,Object>> list = this.siteMatService.selectConMatInfo();
		
		logger.info("list : " + list);
		
		model.addAttribute("list", list);
		
		//forwarding
		return "sitemat/insert";
	}
	//사업장 자재 정보 등록
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertPost(@ModelAttribute SiteMatVO siteMatVo,
			Model model) {
		logger.info("siteMatVo : " + siteMatVo);
		
		SiteMatVO vo = this.siteMatService.insert(siteMatVo);
		
		//사업장 자재 목록의 검색 조건으로 사업장명과 자재명을 일단 빈값으로 처리=>모든 자재 목록 가져옴
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("siteNm", "");
		map.put("itemNm", "");
		//사업장 자재 목록(사업장명, 자재명 포함)
		List<Map<String,Object>> siteMatList = this.siteMatService.siteMatList(map);
		// {siteNum=2021001, matNmCd=MAT001, cnt=28, purDt=2021/08/03, siteNm=대전 사업장
		//, itemNm=CCTV, purPri=1000000}
		logger.info("siteMatList : " + siteMatList.get(0).toString());
		
		model.addAttribute("siteMatVo", vo);
		model.addAttribute("siteMatList", siteMatList);
		
		//forwarding
		return "sitemat/list";
	}
	
	//사업장 검색(사업장번호(site_num), 사업장 명(site_nm), 주소(addr)
	@RequestMapping(value="/selectSite",method=RequestMethod.GET)
	public String selectSite(@RequestParam Map<String,Object> map,
			Model model) {
		map.put("siteNm", "");
		List<SiteVO> siteVoList = this.siteMatService.selectSiteList(map);
		logger.info("siteVoList : " + siteVoList.get(0).toString());
		
		model.addAttribute("siteVoList", siteVoList);
		
		//forwarding
		return "sitemat/selectSite";
	}
	@RequestMapping(value="/selectSite",method=RequestMethod.POST)
	public String selectSitePost(@RequestParam Map<String,Object> map,
			Model model) {
		//map : {siteNm=대전}
		logger.info("map : " + map);
		List<SiteVO> siteVoList = this.siteMatService.selectSiteList(map);
		logger.info("siteVoList : " + siteVoList.get(0).toString());
		
		model.addAttribute("siteVoList", siteVoList);
		
		//forwarding
		return "sitemat/selectSite";
	}
	//사업장 자재 목록 보기
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		//사업장 자재 목록의 검색 조건으로 사업장명과 자재명을 일단 빈값으로 처리=>모든 자재 목록 가져옴
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("siteNm", "");
		map.put("itemNm", "");
		//사업장 자재 목록(사업장명, 자재명 포함)
		List<Map<String,Object>> siteMatList = this.siteMatService.siteMatList(map);
		// {siteNum=2021001, matNmCd=MAT001, cnt=28, purDt=2021/08/03, siteNm=대전 사업장
		//, itemNm=CCTV, purPri=1000000}
		logger.info("siteMatList : " + siteMatList.get(0).toString());
		
		model.addAttribute("siteMatList", siteMatList);
		
		//forwarding
		return "sitemat/list";
	}
	
	//사업장 자재 목록 검색
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String listPost(@RequestParam Map<String,Object> map,
			Model model) {
		//map : {caseWhere=siteNm, keyword=대전}
		logger.info("map : " + map.toString());
		
		//사업장 자재 목록(사업장명, 자재명 포함)
		List<Map<String,Object>> siteMatList = this.siteMatService.siteMatList(map);
		// {siteNum=2021001, matNmCd=MAT001, cnt=28, purDt=2021/08/03, siteNm=대전 사업장
		//, itemNm=CCTV, purPri=1000000}
		if(siteMatList.size()>0) {
			//select 결과가 없다면 .get(0)에서 오류 발생
			logger.info("siteMatList : " + siteMatList.get(0).toString());
		}
		
		model.addAttribute("siteMatList", siteMatList);
		//list.jsp로부터 요청된 HTTP 파라미터가 map으로 담겨있으므로 이를 다시 map으로 담아
		//forwarding 시 함께 전달되도록 함
		model.addAttribute("map", map);
		
		//forwarding
		return "sitemat/list";
	}
}











