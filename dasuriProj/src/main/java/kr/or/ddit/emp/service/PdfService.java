package kr.or.ddit.emp.service;

import java.util.List;

import kr.or.ddit.emp.vo.EmpVO;

public interface PdfService {
	//pdf 파일을 생성해주는 메소드
	public String createPdf(List<EmpVO> empVo);
}
