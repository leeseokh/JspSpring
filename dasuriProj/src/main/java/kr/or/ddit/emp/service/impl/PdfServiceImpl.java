package kr.or.ddit.emp.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kr.or.ddit.emp.service.PdfService;
import kr.or.ddit.emp.vo.EmpVO;

@Service
public class PdfServiceImpl implements PdfService {
	//pdf 파일을 생성해주는 메소드
	@Override
	public String createPdf(List<EmpVO> empVoList) {
		//empVoList를 pdf문서로 잘 만들어졌는지 결과를 저장해줌
		String makePdfResult = "";
		
		try {
			//pdf 문서를 만들어주는 객체 생성
			Document document = new Document();
			
			String path = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\resources\\pdf\\";
			//어디에? 만들어지나?
			//writer객체는 pdf 문서를 작성해주는 작성자. 싱글톤 패턴으로 객체 생성
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "empList.pdf")
					);
			//객체를 메모리에 올림
			document.open();
			
			//한글폰트를 처리해줘야 함
			//windows > fonts 폴더의 한글폰트 사용(malgun.ttf)
			BaseFont baseFont = BaseFont.createFont("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dasuriProj\\src\\main\\webapp\\resources\\font\\H2PORL.TTF",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			//폰트의 설정(폰트 크기를 11로 설정)
			Font font = new Font(baseFont, 11);
			
			//5개의 셀 테이블 객체를 생성함
			PdfPTable pdfPTable = new PdfPTable(5);
			
			//타이틀 처리. 한글 글꼴이므로 위에서 설정한 font객체를 사용
			Chunk chunk = new Chunk("다수리 직원 목록",font);
			//문단처리(타이틀 문장을 가운데 정렬)
			Paragraph paragraph = new Paragraph(chunk);
			//가로방향(행방향)에서 가운데로 정렬함
			paragraph.setAlignment(Element.ALIGN_CENTER);
			//document : pdf 문서 자체
			document.add(paragraph);
			//타이틀과 목록 간의 여백 처리(개행 - 다음줄로 이동)
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			//번호, 이름, 주소, 연락처, 월급 header 추가------------------------
			PdfPCell cellRnum = new PdfPCell(new Phrase("번호",font));
			//번호 글자를 가운데 정렬 처리
			cellRnum.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cellNm = new PdfPCell(new Phrase("이름",font));
			//번호 글자를 가운데 정렬 처리
			cellNm.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cellAddr = new PdfPCell(new Phrase("주소",font));
			//번호 글자를 가운데 정렬 처리
			cellAddr.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cellPne = new PdfPCell(new Phrase("연락처",font));
			//번호 글자를 가운데 정렬 처리
			cellPne.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cellSal = new PdfPCell(new Phrase("월급",font));
			//번호 글자를 가운데 정렬 처리
			cellSal.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			//document 내의 table객체에 cell을 추가해줌
			pdfPTable.addCell(cellRnum);	//번호
			pdfPTable.addCell(cellNm);	//이름
			pdfPTable.addCell(cellAddr);	//주소
			pdfPTable.addCell(cellPne);	//연락처
			pdfPTable.addCell(cellSal);	//월급
			
			//empVoList => List<EmpVO> empVoList
			for(int i=0;i<empVoList.size();i++) {
				//List에서 1행씩 끄집어 내면.. EmpVo가 나옴
				EmpVO empVo = empVoList.get(i);
				//번호. 생성자 Phrase(String, String)
				PdfPCell cellVoRnum = new PdfPCell(new Phrase(""+empVo.getRnum(),font));
				//이름.
				PdfPCell cellVoNm = new PdfPCell(new Phrase(""+empVo.getNm(),font));
				//주소
				PdfPCell cellVoAddr = new PdfPCell(new Phrase(""+empVo.getAddr(),font));
				//연락처
				PdfPCell cellVoPne = new PdfPCell(new Phrase(""+empVo.getPne(),font));
				//월급
				PdfPCell cellVoSal = new PdfPCell(new Phrase(""+empVo.getSal(),font));
				
				//PdfPTable 객체에 5개의 cell을 넣어줌
				pdfPTable.addCell(cellVoRnum);
				pdfPTable.addCell(cellVoNm);
				pdfPTable.addCell(cellVoAddr);
				pdfPTable.addCell(cellVoPne);
				pdfPTable.addCell(cellVoSal);
			}// end for
			//document(pdf문서 자체) 객체에 직원 목록이 담긴 cell집합을 넣어줌
			document.add(pdfPTable);
			//객체를 닫아줌
			document.close();
			
			makePdfResult = "ok";
		}catch (Exception ex) {
			ex.printStackTrace();
			
			makePdfResult = "error";
		}
		return makePdfResult;
	}
}











