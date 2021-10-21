package kr.or.ddit.chart.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.chart.service.GoogleChartService;

@RequestMapping(value="/chart/*")
@Controller
public class chartMainController {
	@Autowired
	GoogleChartService googleChartService;
	
	@RequestMapping("/chartMain")	
	public String chartMain() {
		//forwarding
		return "chart/chartMain";
	}
	
	@RequestMapping("/chart01Multi")
	public String chart01Multi() {
		//forwarding
		return "chart/chart01Multi";
	}
	@RequestMapping("/chart02")
	public String chart02() {
		//forwarding
		return "chart/chart02";
	}
	@ResponseBody
	@RequestMapping("/chart02_money")
	public JSONObject chart02_money() {
		//DB를 통해 List로 가져온 데이터를 변경해준 JSON데이터를 리턴
		return this.googleChartService.getChartData();
	}
	
}
