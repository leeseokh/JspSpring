package kr.or.ddit.wk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesController {
	@RequestMapping(value="/tiles/body1")
	public String tiles1() {
		//forwarding
		return "test1/body1";
	}
}
