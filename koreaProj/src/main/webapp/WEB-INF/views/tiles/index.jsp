<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/tiles.css" />
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<tiles:insertAttribute name="header" />
<div class="container">
	<tiles:insertAttribute name="aside" />
	<tiles:insertAttribute name="body" />
</div>
<tiles:insertAttribute name="footer" />

</body>
</html>