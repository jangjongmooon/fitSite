<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>업체등록 승인페이지</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="">

    <!-- 업체등록 승인요청란 -->
		
		<span class="">* 업체등록 승인요청</span>		
        <table class="">           		
			<tr>
				<th><b>아이디</b></th>
				<th><b>업체명</b></th>
				<th><b>업체주소</b></th>
				<th><b>업체연락처</b></th>
				<th><b>업체주메뉴</b></th>
				<th><b>가입승인</b></th>
			</tr>
			<c:forEach var="" items="">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<!--  
					<td><a href="" 
						   onclick="return confirm('[${fr_store_N}] 업체를 승인 하시겠습니까?');" class="">승인</a></td>
					-->
				</tr>
			</c:forEach>
		</table>
	

</div>
</body>
</html>