<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<title>구글차트 보이기(JSON)</title>
<script type="text/javascript">
//구글차트 라이브러리 로딩(메모리에 올림)
//google 객체는 위의 라이브러리에서 불러온 것임
google.load("visualization","1",{"packages":["corechart"]});
//로딩이 완료되면 drawChart 함수 호출
google.setOnLoadCallback(drawChart);
google.setOnLoadCallback(drawChart2);
function drawChart(){
      var jsonData = $.ajax({
         url:"/json/sampleData.json",
         dataType:"json",// 리턴되는 데이터형식
         async:false // 비동기(새로고침x)
      }).responseText;
      console.log(jsonData);
      //받아온 json데이터를 google 객체를 통해 데이터 테이블로 생성함
      var data = new google.visualization.DataTable(jsonData);
      //var chart = new google.visualization.PieChart(
      var chart = new google.visualization.LineChart(
            document.getElementById("chart_div"));
      //차트 객체. draw(데이터 테이블, 옵션)
      //curveType:"function=>곡선 처리"
      chart.draw(data,{
        title:"차트 예제01",
        curveType:"function",
        width:600,
        height:450
      });
      
   }
   
function drawChart2(){
    var jsonData = $.ajax({
       url:"/json/sampleData2.json",
       dataType:"json",// 리턴되는 데이터형식
       async:false // 비동기(새로고침x)
    }).responseText;
    console.log(jsonData);
    //받아온 json데이터를 google 객체를 통해 데이터 테이블로 생성함
    var data = new google.visualization.DataTable(jsonData);
    //var chart = new google.visualization.PieChart(
    var chart = new google.visualization.LineChart(
          document.getElementById("chart_div2"));
    //차트 객체. draw(데이터 테이블, 옵션)
    //curveType:"function=>곡선 처리"
    chart.draw(data,{
      title:"차트 예제01",
//      curveType:"function",
      width:600,
      height:450
    });
    
 }
</script>
</head>
<body>
<div id="chart_div"></div> <br>
<div id="chart_div2"></div>
</body>
</html>