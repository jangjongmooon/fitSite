<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>선택한 업체룸 목록</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="">
	
	<div>룸 목록</div>
	
	<div><a href="${contextPath}/goAddRoomInfoPage.do?fr_no=${fr_no}" 
					   class=""><button type="button">룸 추가</button></a></div>
		
	<div>
		<c:forEach var="roomList" items="${roomList}" varStatus="status">
			<c:if test="${status.index % 2 == 0}">
				<div class="lookRoomView1">
					<img src="" width="300" height="100" style="float:left;">
					<span>룸 이름 : </span>${roomList.fr_room_name}<br/>
					<span>룸 정원 : </span>${roomList.fr_room_person_no}
					<div>
						<button type="button" class="">수정</button>
						<button type="button" class="">삭제</button>
					</div>
				</div>
			</c:if>
			<c:if test="${status.index % 2 == 1}">
				<div class="lookRoomView2">
					<img src="" width="300" height="100" style="float:left;">
					<span>룸 이름 : </span>${roomList.fr_room_name}<br/>
					<span>룸 정원 : </span>${roomList.fr_room_person_no}
					<div>
						<button type="button" class="">수정</button>
						<button type="button" class="">삭제</button>
					</div>
				</div>
			</c:if>
		</c:forEach>			 
	</div>	

</div>
</body>
</html>