package kr.or.ddit.chart.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoogleChartDao {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<Map<String, Object>> cartMoney(){
      // namespace.id
      return this.sqlSessionTemplate.selectList("chart.cartMoney");
   }
}