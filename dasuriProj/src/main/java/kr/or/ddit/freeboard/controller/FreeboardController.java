package kr.or.ddit.freeboard.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.freeboard.service.FreeboardService;
import kr.or.ddit.freeboard.vo.FreeboardVO;

@Controller
public class FreeboardController {
	private static Logger logger = LoggerFactory.getLogger(FreeboardController.class);
	
	@Autowired
	FreeboardService freeboardService;
	
	//자유게시판 목록(계층형)
	@RequestMapping(value="/freeboard/list",method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		List<FreeboardVO> freeboardVo = this.freeboardService.list();
		
		logger.info("freeboardVo : " + freeboardVo);
		
		model.addAttribute("freeboardVo", freeboardVo);
		
		//forwarding
		return "freeboard/list";
	}
	
	@RequestMapping(value="/freeboard/insert",method=RequestMethod.GET)
	public String insert() {
		//forwarding
		return "freeboard/insert";
	}
	
	@RequestMapping(value="/freeboard/insert",method=RequestMethod.POST)
	public String insertPost(@ModelAttribute FreeboardVO freeboardVo) {
		//redirect
		return "redirect:/freeboard/detail?num=1";
	}
	
	//location.href = "/freeboard/detail?rnum="+rnum;
	@RequestMapping(value="/freeboard/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam String rnum,
			ModelAndView mav) throws Exception {
		//select
		logger.info("rnum : " + rnum);
		//상세페이지
		FreeboardVO freeboardVo = this.freeboardService.detail(rnum);
		logger.info("freeboardVo : " + freeboardVo.toString());
		//댓글목록(부모글번호 : freeboardVo.getpNum())
		List<FreeboardVO> reList = this.freeboardService.reList(rnum+"");
		System.out.println("reList : " + reList.toString());
		
		mav.addObject("freeboardVo", freeboardVo);	//상세글
		mav.addObject("reList", reList);	//댓글 목록
		mav.setViewName("freeboard/detail");
		
		//forwarding
		return mav;
	}
	//자유게시판 글 변경
	@RequestMapping(value="/freeboard/update",method=RequestMethod.POST)
	public String update(@ModelAttribute FreeboardVO freeboardVo) {
		logger.info("freeboardVo : " + freeboardVo.toString());
		
		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\resources\\upload\\";
		
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		MultipartFile multipartFile = freeboardVo.getAttach();
		//경로포함 path
		String uploadFileName = multipartFile.getOriginalFilename();
		//파일명만 추출
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		logger.info("uploadFileName : " + uploadFileName);
		
		UUID uuid= UUID.randomUUID();
		//uuid + "_" +  파일명
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		//파일 복사 계획수립
		File saveFile = new File(uploadPath, uploadFileName);
		try {
			//물리적으로 파일 복사
			multipartFile.transferTo(saveFile);
			
			//FREEBOARD테이블의 ATTACH 컬럼의 데이터로 들어갈 서버상의 이미지 경로 
			String attachPath = "/upload/" + getFolder() + "/" + uploadFileName;
			attachPath = attachPath.replace("\\", "/");
			logger.info("attachPath : " + attachPath);
			//cusVo 객체에 cusImageUpload(서버상의 이미지 경로) 등록
			freeboardVo.setAttachPath(attachPath);
			
			int result = this.freeboardService.update(freeboardVo);
			logger.info("result : " + result);
		}catch(Exception ex) {
			logger.info(ex.getMessage());
		}//end catch
		
		
		//redirect
		return "redirect:/freeboard/detail?rnum="+freeboardVo.getNum();
	}
	
	//Requestbody : POST 방식으로 넘어온 HTTP 파미터를 VO에 매핑해줌
	//VO를 JSON으로 return
	//bindingResult : 오류 내역 확인
	@ResponseBody
	@RequestMapping(value="/freeboard/reInsertPost",method=RequestMethod.POST)
	public FreeboardVO reInsertPost(@RequestBody FreeboardVO freeboardVo,
			BindingResult bindingResult) {
		
		logger.info("freeboardVo : " + freeboardVo);
		
		//자유게시판 댓글 입력
		int result = this.freeboardService.reInsert(freeboardVo);
		logger.info("result : " + result);
		
		logger.info("bindingResult.hasErrors() : " + bindingResult.hasErrors());
		
		return freeboardVo;
	}
	
	//파일업로드 연도 월 일 폴더 처리
	// /upload/2021/08/13/
	public String getFolder() {
		//날짜 포맷 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//오늘 날짜 객체(java.util)
		Date date = new Date();
		//오늘 날짜를 yyyy-MM-dd 형식으로 처리
		String str = sdf.format(date);
		//연/월/일 단위의 폴더를 생성
		return str.replace("-", File.separator);		
	}
}





