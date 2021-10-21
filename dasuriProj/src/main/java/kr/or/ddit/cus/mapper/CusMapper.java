package kr.or.ddit.cus.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.cus.vo.CusVO;

public interface CusMapper {
	int insert(CusVO cusVo);
	
	//고객 정보 목록
	List<CusVO> selectCusList(Map<String, Object> map);
	
	//고객 인원수
	int cusTotal(Map<String, Object> map);
	
	CusVO detail(String cusNum);
	//고객정보 수정(이미지 변경 안함)
	int update(CusVO cusVo);
	
	//고객(CUS)정보 변경(고객의 이미지 포함)
	int updateWithImage(CusVO cusVo);
	
	//고객(CUS)정보 삭제
	int delete(String cusNum);
	
	//특정 고객이 소유하고 있는 자동차 댓수
	int selectCountCar(String cusNum);
}







