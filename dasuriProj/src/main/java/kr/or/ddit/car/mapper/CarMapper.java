package kr.or.ddit.car.mapper;

import java.util.List;

import kr.or.ddit.car.vo.CarVO;

public interface CarMapper {
	int insertPost(List<CarVO> voNew);
}
