<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>
<title>사업장 정보 등록</title>
<script type="text/javascript">
$(function(){
	alert("jquery 동작!");
});
</script>
</head>
<body>
<!-- * Smart Editor 
1. Smart Editor
 WYSIWYG Editor(위지윅 에디터, what you see is what you get)
 - 사용자가 현재 화면에서 보고 있는 내용과 동일한 html code를 생성하는 에디터
 - CKEditor, SummerNote, 네이버, 다음 에디터 등
 CKEditor
 - http://ckeditor.com
 - 현재 CKEditor5 버전이 최신 버전이지만 안정화된 버전인 CKEditor 4 버전으로 실습
 - 이미지 업로드를 위해서는 별도의 작업이 필요
-->
<h2>사업장 정보 등록</h2>

<form name="frm" method="post" action="/site/insert">
사업장 명 : <input type='text' name='siteNm' /><br />
주소 : <input type='text' name='addr' /><br />
전화 번호 : <input type='text' name='phnNum' /><br />
공사 금액 : <input type='text' name='contAmt' /><br />
투입 인원 : <input type='text' name='inPeo' /><br />
시공 일자 : <input type='text' name='stDt' /><br />
예상 완공 일자 : <input type='text' name='exComDt' /><br />
<div style="width:50%;">
비고 : <textarea rows="10" cols="50" name="etc" id="description"></textarea>
</div> 
<br />
<input type="submit" value="입력" /> 
</form>
<script type="text/javascript">
CKEDITOR.replace("description");
</script>
</body>
</html>

