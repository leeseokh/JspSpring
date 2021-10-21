package kr.or.ddit.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.car.mapper.CarMapper;
import kr.or.ddit.car.service.CarService;
import kr.or.ddit.car.vo.CarVO;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarMapper carMapper;
	
	@Override
	public int insertPost(List<CarVO> voNew) {
		return carMapper.insertPost(voNew);
	}
}


