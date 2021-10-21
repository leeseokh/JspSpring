package kr.or.ddit.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value="/tiles/body1")
	public String tiles1() {
		//forwarding
		return "test1/body1";
	}
	
	@RequestMapping(value="/")
	public String adminlteIndex() {
		//forwarding
		return "index";
	}
	
	//404오류 처리 페이지
	@RequestMapping(value="/error/error404")
	public String error404() {
		return "error/error404";
	}
	
	//500오류 처리 페이지
	@RequestMapping(value="/error/error500")
	public String error500() {
		return "error/error500";
	}
	
}
