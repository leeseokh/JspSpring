package kr.or.ddit.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.vo.EmpVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/emp")
@Controller
public class EmpController {
	private static Logger logger = LoggerFactory.getLogger(EmpController.class); 
	
	@Autowired
	EmpService empService;
	// /emp/insert <- GET 방식
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		//직급 list
		List<String> posList = new ArrayList<String>();
		posList.add("사장");posList.add("부장");posList.add("과장");
		posList.add("대리");posList.add("사원");
		
		//부서 list
		List<String> deptNmList = new ArrayList<String>();
		deptNmList.add("인사부");deptNmList.add("개발부");deptNmList.add("회계부");
		
		model.addAttribute("posList", posList);
		model.addAttribute("deptNmList", deptNmList);
		
		//forwarding. emp폴더의 insert.jsp를 forwarding
		return "emp/insert";
	}
	
	// /emp/insert <- POST 방식
	// insert.jsp로부터 HTTP파라미터의 name값들을 EmpVO의 필드와 일치
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertPost(@ModelAttribute EmpVO empVo, Model model) {
		logger.info("empVo : " + empVo.toString());
		//insert 후에 기본키 데이터인 empNum을 리턴받음
		int empNum = this.empService.insert(empVo);
		model.addAttribute("empNum", empNum);
		//forwarding
		return "emp/insertSuccess";
	}
}








