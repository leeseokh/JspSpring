package kr.or.ddit.car.controller;

import java.util.ArrayList;
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

import kr.or.ddit.car.service.CarService;
import kr.or.ddit.car.vo.CarVO;
import kr.or.ddit.cus.service.CusService;
import kr.or.ddit.cus.vo.CusVO;
import kr.or.ddit.util.Pagination;

//RestController : JSON용
@RequestMapping(value="/car/*")
@Controller
public class CarController {
	private static Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	private CusService cusService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		//forwarding
		return "car/list";
	}
	
	//자동차 등록 form
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {		
		//forwarding
		return "car/insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertPost(@ModelAttribute CarVO carVo) {
		
		System.out.println("carVo : " + carVo.toString());
		
		//고객번호
		String cusNum = carVo.getCusNum();
		
		//before vo
		List<CarVO> vo = carVo.getCarVo();
		//after vo(cusNum 값을 넣어줌)
		List<CarVO> voNew = new ArrayList<CarVO>();
		
		for(CarVO row : vo) {
			row.setCusNum(cusNum);
			System.out.print(row.getCusNum());
			System.out.print(row.getCarNum());
			System.out.print(row.getMk());
			System.out.print(row.getPy());
			System.out.print(row.getDriDist());
			
			voNew.add(row);
		}
		//고객의 자동차 정보 insert
		int result = this.carService.insertPost(voNew);
		
		//forwarding
		return "car/insert";
	}
	
	@RequestMapping(value="/popup/cusList",method=RequestMethod.GET)
	public String cusList(@RequestParam(defaultValue = "1") String currentPage 
			, @RequestParam Map<String,Object> map
			, Model model) throws Exception {
//		logger.info("map : " + map);
		//최초에 /cus/list 요청 시 map은 null이므로 null처리를 함
		if(map.get("currentPage")==null) {
			logger.info("currentPage null");
			map.put("currentPage", "1");
		}
		if(map.get("selSearch")==null) {
			logger.info("selSearch null");
			map.put("selSearch", "");
		}
		if(map.get("keyword")==null) {
			logger.info("keyword null");
			map.put("keyword", "");
		}
		
		//고객 목록
		List<CusVO> list = this.cusService.selectCusList(map);
		
		//고객 인원수
		int total = this.cusService.cusTotal(map);
		int startNum = Integer.parseInt(currentPage) * 15 - 14 ;
		int endNum = Integer.parseInt(currentPage) * 15;
		if(endNum > total) {
			endNum = total;
		}
		
		model.addAttribute("list", list);
		
		Map<String, Object> pageInfo =  new HashMap<String, Object>();
		pageInfo.put("total", total);
		pageInfo.put("startNum", startNum);
		pageInfo.put("endNum", endNum);
		model.addAttribute("pageInfo", pageInfo);
		
		Pagination pagination = new Pagination(total, Integer.parseInt(currentPage), 15);
		model.addAttribute("paging", pagination);
		
		//forwarding
		return "car/popup/cusList";
	}
}









