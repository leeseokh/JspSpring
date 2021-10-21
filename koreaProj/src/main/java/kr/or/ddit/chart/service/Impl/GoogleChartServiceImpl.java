package kr.or.ddit.chart.service.Impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.chart.dao.GoogleChartDao;
import kr.or.ddit.chart.service.GoogleChartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService{
   private static Logger logger = LoggerFactory.getLogger(GoogleChartServiceImpl.class);
   
   // DI(의존성주입), IoC(제어의 역전)
   @Autowired
   GoogleChartDao gooleChartDao;
   
   @Override
   public JSONObject getChartData() {
      List<Map<String,Object>> list = this.gooleChartDao.cartMoney();
      
      // list 데이터가 없을 경우 오류가 발생하므로 이를 방지
      if(list.size()> 0 ) {
         logger.info("list : " + list.get(0).toString());
      }
      
      // 0. 리턴할 json 객체 
      // List -> JSON
      JSONObject data = new JSONObject(); // {}
      
      // 1. cols 배열에 넣기
      // JSON 컬럼 객체 --------------------------
      JSONObject col1 = new JSONObject(); // 상품명
      JSONObject col2 = new JSONObject(); // 판매금액
      // JSON 배열 객체
      JSONArray title = new JSONArray();
      col1.put("label","상품명");
      col1.put("type","string");
      col2.put("label","판매금액");
      col2.put("type","number");
      // 타이틀 행에 컬럼 추가
      title.add(col1);
      title.add(col2);
      // json 객체에 타이틀 행 추가
      data.put("cols", title);
      
      //2. rows배역에 넣기
      JSONArray body = new JSONArray();
      for(Map<String,Object> map : list) {
         JSONObject prodName = new JSONObject(); // 상품명 객체
         prodName.put("v", map.get("PRODNAME")); // DB에서 가져온 상품명을 JSONObject에 넣음
         
         JSONObject money = new JSONObject(); // 상품금액 객체
         money.put("v", map.get("MONEY")); // DB에서 가져온 판매금액을 JSONObject에 넣음
         
         JSONArray row = new JSONArray();
         row.add(prodName);
         row.add(money);
         
         JSONObject cell = new JSONObject();
         cell.put("c", row);
         
         // 레코드 1개 추가
         body.add(cell);
      }// body 처리 끝
      data.put("rows", body);
      
      return data;
   }
}