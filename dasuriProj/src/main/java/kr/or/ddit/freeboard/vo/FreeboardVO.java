package kr.or.ddit.freeboard.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//자바빈 클래스
public class FreeboardVO {
	private int rnum;	//일련번호
	private int lvl;	//계층형 게시판 레벨
	private int num;	//글번호
	private String title; //글제목
	private String writer;	//작성자
	private int pNum;	//부모글번호
	private String content;	//글내용
	
	private MultipartFile attach;	//첨부파일
	private String attachPath;	//첨부파일경로
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	@Override
	public String toString() {
		return "FreeboardVO [rnum=" + rnum + ", lvl=" + lvl + ", num=" + num + ", title=" + title + ", writer=" + writer
				+ ", pNum=" + pNum + ", content=" + content + ", attach=" + attach + ", attachPath=" + attachPath + "]";
	}
	
	
	
	
}
