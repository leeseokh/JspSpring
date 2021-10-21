<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/resources/js/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/style/jquery-ui.css">
<link rel="stylesheet" href="/resources/style/style.css">
<script src="/resources/js/jquery-1.12.4.js"></script>
<script src="/resources/js/jquery-ui.js"></script>
<title>사업장 자재 정보 등록</title>
<script type="text/javascript">
$(function(){
	$(":button").on("click",function(){
		//사업장 검색
		window.open("/sitemat/selectSite","owin","width=700px,height=700px,scrollbars=yes");
	});
	
	$("#datepicker").datepicker({
		dateFormat:'yy-mm-dd',
		changeYear:true,
		changeMonth:true,
		showMonthAfterYear:true,
		dayNamesMin:['일','월','화','수','목','금','토'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
});

function fn_chk(){
	//사업장 번호 요소
	var v_siteNum = document.getElementById("siteNum");
	//구입 가격 요소
	var v_purPri = document.getElementById("purPri");
	//구입 일자 요소
	var v_datepicker = document.getElementById("datepicker");
	
	if(v_siteNum.value==""){
		alert("사업장 번호를 입력해주세요");
		return false;
	}
	if(v_purPri.value==""){
		alert("구입 가격를 입력해주세요");
		return false;
	}
	if(v_datepicker.value==""){
		alert("구입 일자를 입력해주세요");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<h2>사업장 자재 정보 등록</h2>
<form method="post" action="/sitemat/insert" onsubmit="return fn_chk();">
사업장 번호 : <input type="text" name="siteNum" id="siteNum" />&nbsp;
<input type="button" value="사업장 검색" />
<br />
자재 명 코드 : <select name="matNmCd">
<c:forEach var="list" items="${list}">
	<option value="${list.MAT_NM_CD}">${list.ITEM_NM}</option>
</c:forEach>
</select> <br />
<!-- 수량 최소 1부터 시작해서 100까지 중에서 1개를 선택 -->
수량 :
<select name="cnt">
<!-- var : 변수, begin : 시작, end : 종료, step : 증가량, varStatus => stat.index / stat.count -->
<c:forEach var="i" begin="1" end="100" step="1" varStatus="stat">
	<option value="${i}">${i}</option>
</c:forEach>
</select>
<br />
구입 가격 : <input type="text" name="purPri" id="purPri" /><br />
<!-- jquery에 datepicker -->
구입 일자 : <input type="text" name="purDt" id="datepicker" /><br />
<input type="submit" value="등록" />
</form>
</body>
</html>








