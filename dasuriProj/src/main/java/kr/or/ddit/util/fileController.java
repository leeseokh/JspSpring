package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class fileController {
	private static Logger logger = LoggerFactory.getLogger(fileController.class);
	
	//springframework.core.io.Resource
	//APPLICATION_OCTET_STREAM_VALUE : MIME 타입의 파일로 요청이 옴
	@ResponseBody
	@RequestMapping(value="/download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName){
		///resources//upload/2021/08/23/106ea1df-8e72-4c65-8ae4-8ee82d4ac4d4_강아지3.jpg
		logger.info("download file : " + fileName);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<String> list = new ArrayList<String>();
		//
		Resource resource = new FileSystemResource(
				"D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\" 
				+ fileName);
		
		logger.info("resource : " + resource);
		
		//resource파일 객체의 파일 명을 가져옴
		String resourceName = resource.getFilename();
		logger.info("resourceName : " + resourceName);
		
		//org.springframeword.http.HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		
		try {			
			//파일 다운로드 시 저장되는 이름은 Content-Disposition을 이용하여 지정함.
			//파일 이름에 대한 문자열 처리를 통해 파일 이름이 한글인 경우 저장 시 깨지는 문제를 막기 위함
			//Content-Disposition : 
			//			웹페이지에서 HTTP 프로토콜이 응답하는 데이터를 어떻게 표시하는지 알려주는 Header
			//attachment : 사용자의 로컬에 다운로드 할 수 있도록 함.
			//filename : file의 이름을 알려주는 header
			headers.add("Content-Disposition", "attachment;filename=" 
					+ new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		//resource : 파일 객체
		//headers : jsp로 return 시 header에 넣어서 보낸다는 의미
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
}





