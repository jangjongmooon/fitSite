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
</head>
<body>
<div class="">
	
	<div><button type="button" id="" onclick=>룸 추가</button></div>
		
	<div><span class="">승인된 업체목록</span>
	
		<c:forEach var="" items="">
			<div style="width:200px; height:150px; border:1px solid red; float:left;"><img id="originalImage" usemap="" width="120" height="120" src=""/></div>
			<div style="width:200px; height:150px; border:1px solid red; float:left;">
				<table id="">
				<tr>
					<td><span class="mypageText">룸이름 : </span></td>
					<td><span class="mypageResultText"></span></td>
				</tr>
				<tr>
					<td><span class="mypageText">룸정원 : </span></td>
					<td><span class="mypageResultText"></span></td>
				</tr>

			</table>
				<div>
					<button type="button" id="" onclick=>수정</button>
					<button type="button" id="" onclick=>삭제</button>
				</div>
			</div>	
		</c:forEach>			 
	</div>	



</div>
</body>
</html>