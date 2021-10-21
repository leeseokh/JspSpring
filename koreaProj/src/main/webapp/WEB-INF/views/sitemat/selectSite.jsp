<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사업장 검색</title>
<script type="text/javascript">
function fn_parentIn(p_siteNum){
	//alert("p_siteNum : " + p_siteNum);
	//부모창의 id가 siteNum인 요소를 찾아서 value에 넣음
	opener.document.getElementById("siteNum").value = p_siteNum;
	//창을 닫음
	self.close();
}

function fn_chk(){
	//id가 siteNm인 요소를 txtSiteNm 변수에 저장
	var txtSiteNm = document.getElementById("siteNm");
	//txtSiteNm 요소의 값이 없을 경우 경고창을 띄우고 submit이 안되도록 false를 리턴함
	if(txtSiteNm.value==""){
		alert('검색할 사업장 명을 입력해주세요');
		//해당 요소에 커서를 위치함
		txtSiteNm.focus();
		return false;
	}
	//if문을 통과하면 submit이 되도록 true를 리턴함
	return true;
}
</script>
</head>
<body>
<h2>사업장 검색</h2>
<!-- onsubmit : form이 submit되기 전에 해당 함수를 먼저 실행. -->
<form method="POST" action="/sitemat/selectSite" onsubmit="return fn_chk();">
사업장 명 : <input type="text" name="siteNm" id="siteNm" />&nbsp;
		<input type="submit" value="검색" />
</form><br />
<table border="1" style="width:100%;">
<tr>
	<th>사업장번호</th>
	<th>사업장 명</th>
	<th>주소</th>
</tr>
<!-- siteNum=2021001, siteNm=대전 사업장, addr=대전, phnNum=010-123-1234, 
contAmt=22, inPeo=33, stDt=2021, exComDt=2022, etc=<p>테스트</p> -->
<c:forEach var="siteVo" items="${siteVoList}">
<tr>
	<td><a href="javascript:fn_parentIn('${siteVo.siteNum}')">${siteVo.siteNum}</a></td>
	<td>${siteVo.siteNm}</td>
	<td>${siteVo.addr}</td>
</tr>
</c:forEach>
</table>
<input type="button" value="닫기" onclick="javascript:self.close();" />
</body>
</html>


