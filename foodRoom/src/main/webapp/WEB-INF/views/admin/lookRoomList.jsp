<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>승인된 업체관리</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="">
	
	<button type="button" id="" onclick="location.href=''">룸 추가</button>

    <!-- 업체등록 승인요청란 -->
		
	<span class="">승인된 업체목록</span>		
               		
		<c:forEach var="" items="">
			<div><img id="originalImage" usemap="#test" width="120" height="120" src=""/></div>
			<div></div>
			<button type="button" id="" onclick="location.href=''">수정</button>
			<button type="button" id="" onclick="location.href=''">삭제</button>
				
					 
		</c:forEach>

	

</div>
</body>
</html>