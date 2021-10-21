<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사원정보 입력 폼</title>
</head>
<body>
<form method="post" action="/emp/insert">
<h2>사원정보 입력</h2>
사원명 : <input type="text" name="empNm" /><br />
주소 : <input type="text" name="zipCode" /><br />
<input type="text" name="addr1" />&nbsp;<input type="text" name="addr2" /><br />
전화번호 : <input type="text" name="phnNum" /><br />
직급 : 
<select name="pos">
	<!-- posList의 size만큼 반복하면서 pos 변수에 하나씩 넣고 option으로 보여줌 -->
	<c:forEach var="pos" items="${posList}">
		<c:if test="${pos=='사원'}"><c:set var="sel" value="selected"></c:set></c:if>
		<c:if test="${pos!='사원'}"><c:set var="sel" value=""></c:set></c:if>
		<option value="${pos}" ${sel}>${pos}</option>
	</c:forEach>
</select>
<br />
부서 명 : 
<select name="deptNm">
	<c:forEach var="deptNm" items="${deptNmList}">
		<option value="${deptNm}">${deptNm}</option>
	</c:forEach>
</select>
<br />
<input type="submit" value="저장" />&nbsp;&nbsp;
<input type="button" value="닫기" onclick="javascript:self.close();" />
</form>
</body>
</html>





