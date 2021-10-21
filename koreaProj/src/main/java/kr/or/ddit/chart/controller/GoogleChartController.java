package kr.or.ddit.chart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


//json을 리턴한는 method가 있을 경우 사용(Spring 4.0부터사용가능)
//만약 Controller 어노테이션 사용 시 메소드에 ResponseBody를 사용하면 됨.
@RestController
public class GoogleChartController {
   
   @RequestMapping(value = "/chart/chart01")
   public ModelAndView  chart01() {
      
      return new ModelAndView("chart/chart01");
   }
}