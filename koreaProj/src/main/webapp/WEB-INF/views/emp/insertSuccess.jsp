<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사원 정보 입력 성공</title>
</head>
<body>

<c:if test="${empNum!=null}">
<script type="text/javascript">
alert("등록완료!");
//부모창을 refresh한 후
opener.parent.location.reload();
// 창을 닫음
self.close();
</script>
</c:if>
<c:if test="${empNum==null}">
<script type="text/javascript">
alert("등록실패!");
//이전 화면으로 이동
history.go(-1);
</script>
</c:if>

</body>
</html>