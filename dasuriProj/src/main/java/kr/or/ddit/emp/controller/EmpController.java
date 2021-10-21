package kr.or.ddit.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.service.PdfService;
import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.util.Pagination;

//RestController 어노테이션 : JSON데이터를 리턴하는 메소드가 있을 경우 사용
@RequestMapping(value="/emp/*")
@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	@Autowired
	PdfService pdfService;
	
	private static Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue="1") String currentPage) {
		//직원 목록 가져오기
		List<EmpVO> list = this.empService.selectEmpList(currentPage);
		//전체 글의 행수(카디널리티)
		int total = this.empService.empTotal();
		int startNum = Integer.parseInt(currentPage) * 10 - 9;
		int endNum = Integer.parseInt(currentPage) * 10;
		//endNum 보정 작업
		if(endNum>total) {
			endNum = total;
		}
		
		//테이블 왼쪽 하단의 Showing 1(시작글번호) to 10(종료글번호) of 57(전체행의 개수-카디널리티) entries
		Map<String,Object> entriesMap = new HashMap<String, Object>();
		entriesMap.put("startNum", startNum);
		entriesMap.put("endNum", endNum);
		entriesMap.put("totalNum", total);
		
		//Pagination 처리
		Pagination paging = new Pagination(total, Integer.parseInt(currentPage), 10);
		
		//데이터를 담음
		model.addAttribute("list", list);
		model.addAttribute("entriesMap", entriesMap);
		model.addAttribute("paging", paging);
		
		//forwarding
		return "emp/list";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		//forwarding
		return "emp/insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertPost(@ModelAttribute EmpVO empVo,
			Model model) {
		//리턴된 값은? 입력된 직원의 직원번호
		int empNum = this.empService.insert(empVo);
		
		//필요는 없지만 반복연습 차원..
		model.addAttribute("empNum", empNum);
		
		//return "emp/detail";
		//return "redirect:/emp/detail?empNum="+empNum;
		//URI를 요청하는 것이므로 redirect를 씀.
		return "redirect:/emp/detail/"+empNum;
	}
	
	//경로 패턴 지정
	@RequestMapping("/detail/{empNum}")
	public ModelAndView detail(@PathVariable("empNum") String empNum,
			ModelAndView mav) {
		
		logger.info("empNum : " + empNum);
		//empNum : 직원번호
		EmpVO  empVo = this.empService.selectEmp(empNum);
		
		//-------주소를 우편번호와 주소로 분리 시작-----------
		String addr = empVo.getAddr();
		//스페이스바로써 split하여 배열로 만듦
		String[] addrArr = addr.split(" ");
		//배열의 크기만큼 결과 배열을 만듦
		String[] result = new String[2];
		result[1] = "";
		
		for(int i=0;i<addrArr.length;i++) {
			if(i==0) {
				result[i] = addrArr[i];//우편번호
			}else {
				if(addrArr[i]!=null) {
					result[1] += addrArr[i] + " ";
				}
			}
		}
		System.out.println(result[0]);//우편번호
		System.out.println(result[1]);//나머지주소
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("addr1", result[0]);//우편번호
		map.put("addr2", result[1]);//나머지주소
		//-------주소를 우편번호와 주소로 분리 끝-----------
		
		//model
		mav.addObject("empVo", empVo);
		mav.addObject("map", map);
		//view. forwarding
		mav.setViewName("emp/detail");
		
		return mav;
	}
	
	//ModelAttribute어노테이션 : 
	//		jsp의 form 안에 있는 모든 요소들 중 
	//		EmpVO의 필드명과 매핑되는 정보들을 알아서 찾아내어 empVo에 set해줌
	//직원정보 수정
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatePost(@ModelAttribute EmpVO empVo) {
		String empNum = empVo.getEmpNum() + "";
		logger.info("updatePost -> empVo : " + empVo);
		logger.info("updatePost -> empNum : " + empNum);
		
		int result = this.empService.update(empVo);
		String status = "";
		if(result>0) {//수정 완료
			status = "ok";
		}else {//수정 실패
			status = "fail";
		}
		
		//redirect : 파라미터(o), model데이터(x)
		return "redirect:/emp/detail/"+empNum+"?result="+status;
	}
	
	//<input type="text" name="empNum" id="empNum2" value="..." />
	@RequestMapping(value="/emp/delete",method=RequestMethod.POST)
	public String deletePost(@RequestParam String empNum) {
		int result = this.empService.delete(empNum);
		String status = "";
		if(result>0) {//삭제 성공
			//redirect : 파라미터(o), model데이터(x)
			return "redirect:/emp/list";
		}else {//삭제 실패
			status = "delFail";
			return "redirect:/emp/detail/"+empNum+"?result="+status;	
		}
	}
	//.jsp에서 오는 HTTP 파라미터 => empNum=202108001, pwd=1234
	//RequestParam Map<String,Object> map
	//ModelAttribute EmpVO empVo
	@RequestMapping(value="/emp/login",method=RequestMethod.POST)
	public ModelAndView loginPost(@ModelAttribute EmpVO empVo,
			HttpSession session, ModelAndView mav) {
		//로그인 성공 => empVo 객체가 넘어오고, 로그인 실패 => null이 넘어옴
		EmpVO vo = this.empService.login(empVo, session);
		
		logger.info("empVo : " + empVo.toString());
		
		mav.setViewName("redirect:/emp/list");
		
		return mav;
	}
	
	//로그아웃
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout(HttpSession session) {
		//모든 세션을 로그아웃 함
		session.invalidate();
		
		return "redirect:/emp/list";
	}
	
	//직원 목록을 pdf 다운로드
	@ResponseBody
	@RequestMapping(value="/pdfDown",method=RequestMethod.GET)
	public String pdfDown() {
		//pdfDownload 처리
		
		List<EmpVO> empAllList = this.empService.selectEmpAllList();
		logger.info("empAllList : " + empAllList.get(0).toString());
		
		//모든 직원 목록을 파라미터로 던져서 pdf문서로 만들겠다.
		//D:\A_TeachingMaterial\6.JspSpring\workspace\dasuriProj\src\main\webapp\resources\pdf\
		//makePdfResult => pdf문서생성 성공 : ok, pdf문서생성 실패 : error
		String makePdfResult = this.pdfService.createPdf(empAllList);
		logger.info("makePdfResult : " + makePdfResult);
		
		//redirect
		return makePdfResult;
	}
}













