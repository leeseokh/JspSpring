<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사업장 자재 목록</title>
<script type="text/javascript">
function fn_showAll(){
	location.href="/sitemat/list";
}
</script>
</head>
<body>
<h2>사업장 자재 목록</h2>
<!-- map : {caseWhere=siteNm, keyword=대전} -->
<form method="post" action="/sitemat/list">
<c:set var="chk" value="${map.caseWhere}" />
<select name="caseWhere">
	<option value="all" <c:if test="${chk=='all'}">selected</c:if>>전체</option>
	<option value="siteNm" <c:if test="${chk=='siteNm'}">selected</c:if>>사업장명</option>
	<option value="itemNm" <c:if test="${chk=='itemNm'}">selected</c:if>>자재명</option>
</select>&nbsp;
<input type="text" name="keyword" value="${map.keyword}" />&nbsp;
<input type="submit" value="검색" />&nbsp;
<input type="button" value="전체보기" onclick="fn_showAll();"/>
</form><br />
<table border="1" style="width:100%;">
<thead>
	<tr>
		<th>사업장명</th>
		<th>자재명</th>
		<th>수량</th>
		<th>구입가격</th>
		<th>구입일자</th>	
	</tr>
</thead>
<!-- siteNum=2021001, matNmCd=MAT001, cnt=28, purDt=2021/08/03, siteNm=대전 사업장
	, itemNm=CCTV, purPri=1000000} -->
<tbody>
<c:forEach var="list" items="${siteMatList}">
	<tr>
		<td>${list.siteNm}</td>
		<td>${list.itemNm}</td>
		<td>${list.cnt}</td>
		<td>${list.purPri}</td>
		<td>${list.purDt}</td>
	</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>