package kr.or.ddit.freeboard.mapper;

import java.util.List;

import kr.or.ddit.freeboard.vo.FreeboardVO;

public interface FreeboardMapper {
	List<FreeboardVO> list();
	
	FreeboardVO detail(String rnum);
	
	int update(FreeboardVO freeboardVo);
	
	int reInsert(FreeboardVO freeboardVo);
	
	List<FreeboardVO> reList(String pNum);
}
