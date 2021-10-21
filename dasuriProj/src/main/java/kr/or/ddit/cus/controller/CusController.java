package kr.or.ddit.cus.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.cus.service.CusService;
import kr.or.ddit.cus.vo.CusVO;
import kr.or.ddit.util.Pagination;

@Controller
@RequestMapping("/cus")
public class CusController {
	private static Logger logger = LoggerFactory.getLogger(CusController.class);
	
	@Autowired
	CusService cusService;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(@RequestParam(defaultValue = "1") String currentPage 
			, @RequestParam Map<String,Object> map
			, Model model)
			 throws Exception{
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
		
		return "cus/list";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		//forwarding
		return "cus/insert";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPost(@ModelAttribute CusVO cusVo) throws Exception {
//		int cusNum = this.cusService.insert(cusVo);
		logger.info("cusVo : " + cusVo);
		
		
		
		//--------------------파일 업로드 시작 ------------------------------------
		String uploadFolder 
		= "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\resources\\upload\\";
		
		// ..../upload/2021/08/13/ 폴더가 없으면  생성
		File uploadPath = new File(uploadFolder, getFolder());
		logger.info("uploadPath : " + uploadPath);
		//해당 폴더가 없다면...
		if(uploadPath.exists()==false) {
			// /upload/ 폴더의 하위폴더들(.mkdirs()) 생성
			uploadPath.mkdirs();
		}
		
		//***업로드된 이미지의 정보 처리*****
		//cusVo 객체의 cusImage 필드를 가져옴(MultipartFile 형)
		MultipartFile multipartFile = cusVo.getCusImage();
		//이미지 파일의 실제 명을 구함(경로 포함)
		String uploadFileName = multipartFile.getOriginalFilename();
		logger.info("uploadFileName : " + uploadFileName);
		//이미지 파일의 경로를 제거=>실제 파일명만 남김
		// .....\\upload\\test.jpg => +1 => t의 위치. substring(시작부터 끝까지)=>test.jpg
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		logger.info("파일명만 남은 uploadFileName : " + uploadFileName);
		
		//동명이파일을 처리
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		//파일을 저장 계획 수립. uploadPath : ..../upload/2021/08/13/
						//uploadfileName : uuid처리String_test.jpg
		File saveFile = new File(uploadPath, uploadFileName);
		
		try {
			//파일 저장 계획을 실행(파일 복사)
			multipartFile.transferTo(saveFile);
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}//end catch
		//---------------파일 업로드 끝 -----------------------------------------
		//CUS테이블의 CUS_IMAGE 컬럼의 데이터로 들어갈 서버상의 이미지 경로 
		String cusImageUpload = "/upload/" + getFolder() + "/" + uploadFileName;
		cusImageUpload = cusImageUpload.replace("\\", "/");
		logger.info("cusImageUpload : " + cusImageUpload);
		//cusVo 객체에 cusImageUpload(서버상의 이미지 경로) 등록
		cusVo.setCusImageUpload(cusImageUpload);
		
		int cusNum = this.cusService.insert(cusVo);
		logger.info("cusNum : " + cusNum);
		
		//redirect(GET방식)
		//return "redirect:/cus/detail/cusNum
		return "redirect:/cus/detail?cusNum="+cusNum;
	}
	//고객(CUS) 상세 정보 
	//RequestParam어노테이션의 value : 넘어오는 파라미터의 name값
	//required=false : cusNum 파라미터가 반드시 있을 필요는 없음(true가 기본. 생략 시 true)
	//defaultValue : 기본값 세팅
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(@RequestParam(value="cusNum", required=false) String cusNum,
			Model model) throws Exception {
		
		logger.info("cusNum : " + cusNum);
		//고객의 상세 정보를 가져옴
		CusVO cusVo = this.cusService.detail(cusNum);
		logger.info("cusVo : " + cusVo.toString());
		
		model.addAttribute("cusVo", cusVo);
		
		//forwarding
		return "cus/detail";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatePost(@ModelAttribute CusVO cusVo, 
			@RequestParam(value="cusImageCheck",required=false) String cusImageCheck) throws Exception {
		logger.info("cusVo : " + cusVo.toString() + ", cusImageCheck : " + cusImageCheck);
		
		//<input type="checkbox" name="cusImageCheck" id="cusImageCheck" />
		if(cusImageCheck==null) {//이미지 변경(x)
			int result = this.cusService.update(cusVo);
		}else {//이미지 변경(o)
			//--------------------파일 업로드 시작 ------------------------------------
			String uploadFolder 
			= "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\resources\\upload\\";
			
			//연도 월 일 폴더 처리
			File uploadPath = new File(uploadFolder, getFolder());
			logger.info("uploadPath : " + uploadPath);
			//해당 폴더가 없다면 생성
			if(uploadPath.exists()==false) {
				uploadPath.mkdirs();
			}
			
			//이미지 파일 객체를 가져옴
			MultipartFile multipartFile = cusVo.getCusImage();
			//파일의 이름을 가져옴(경로까지 포함)
			String uploadFileName = multipartFile.getOriginalFilename();
			//순수 파일 이름만 추출(경로 제외)
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			logger.info("uploadfileName : " + uploadFileName);
			
			//UUID 처리
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			//파일 복사를 위한 최종 계획 수립(어디로?, 파일명?)
			File saveFile = new File(uploadPath, uploadFileName);
			
			//CUS테이블의 CUS_IMAGE 컬럼의 데이터로 들어갈 서버상의 이미지 경로 
			String cusImageUpload = "/upload/" + getFolder() + "/" + uploadFileName;
			cusImageUpload = cusImageUpload.replace("\\", "/");
			logger.info("cusImageUpload : " + cusImageUpload);
			//cusVo 객체에 cusImageUpload(서버상의 이미지 경로) 등록
			cusVo.setCusImageUpload(cusImageUpload);
			
			try {
				//파일 복사 계획을 수행
				multipartFile.transferTo(saveFile);
				//고객정보 변경내역을 DB에 저장
				int result = this.cusService.updateWithImage(cusVo);
			}catch (Exception ex) {
				logger.error(ex.getMessage());
			}//end catch
		}//end if
		
		
		return "redirect:/cus/detail?cusNum="+cusVo.getCusNum();
	}
	
	//고객(CUS) 정보 삭제
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String deletePost(@RequestParam(value="cusNum", required=true) String cusNum) {
		logger.info("cusNum : " + cusNum);
		
		//삭제하기 전에 해당 자동차(CAR) 정보가 있는지 체크
		int countCar = this.cusService.selectCountCar(cusNum);
		
		if(countCar>0) {//자식테이블에 해당 컬럼의 데이터가 참조하고 있으므로 삭제로직을 수행하지 않음
			return "redirect:/cus/detail?cusNum="+cusNum+"&message=childfound";
		}else {//삭제 로직 수행
			if(cusNum!=null) {
				int result = this.cusService.delete(cusNum);
				return "redirect:/cus/list";
			}else {
				return "redirect:/cus/detail?cusNum="+cusNum;
			}
		}
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









