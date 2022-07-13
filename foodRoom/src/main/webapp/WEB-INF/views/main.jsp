<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인 페이지</title>
	<link href="${contextPath}/css/foodroom.css" rel="stylesheet"> 
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>	  
	<c:choose>
		<c:when test="${result =='regiSuccess'}">
			<script>
			window.onload=function() {
				alert("이미 등록을 완료하셨습니다.\n승인을 기다려 주세요.");
			}
			</script>
		</c:when>	
	</c:choose>
</head>

<body>

</body>
</html>