package kr.or.ddit.freeboard.service;

import java.util.List;

import kr.or.ddit.freeboard.vo.FreeboardVO;

public interface FreeboardService {
	//메소드 시그니처
	public List<FreeboardVO> list() throws Exception;
	//자유게시판(FreeboardVO) 상세보기
	public FreeboardVO detail(String rnum) throws Exception;
	//자유게시판 변경
	public int update(FreeboardVO freeboardVo);
	//자유게시판 댓글 입력
	public int reInsert(FreeboardVO freeboardVo);
	//자유게시판 댓글 목록
	public List<FreeboardVO> reList(String pNum);
}
