<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	<link href="${contextPath}/css/foodroom.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</head>

<script>
</script>

<body>
	<table class="topTable">
		<tr>
			<td>
				<a href="${contextPath}/index.do"><img src="${contextPath}/css/images/1.PNG" width="190" height="90" class="topMenuLogo"></a>
			</td>
			<td>
				<a href="" class="topMenuBtn1 topMenuBtn">메뉴1</a>
				<a href="" class="topMenuBtn2 topMenuBtn">메뉴2</a>
				<a href="" class="topMenuBtn3 topMenuBtn">메뉴3</a>
				<a href="" class="topMenuBtnPlus topMenuBtn">더보기	&#9660;</a>
			</td>	
				<c:choose>
					<c:when test="${isLogOn == true && fr_id != null}"> 
					<td class="dropdown">		
						<span class="userName topMenuBtn">${fr_name}님	&#9660;</span>
						<div class="infoView">
							<c:if test="${fr_class == 13}">
								<a href="${contextPath}/goMyPage.do"><input type="button" value="내정보" class=""/></a>
								<a href=""><input type="button" value="예약내역" class=""/></a>
								<a href="${contextPath}/goReservationStore.do"><input type="button" value="예약하기" class=""/></a>
								<a href="${contextPath}/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>								
							</c:if>
							<c:if test="${fr_class == 12}">
								<a href="${contextPath}/goMyPage.do"><input type="button" value="내정보" class=""/></a>
								<a href="${contextPath}/reservation.do"><input type="button" value="예약내역" class=""/></a>
								<a href=""><input type="button" value="업체관리" class=""/></a>
								<a href="${contextPath}/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
							</c:if>
							<c:if test="${fr_class == 02}">
								<a href="${contextPath}/goMyPage.do"><input type="button" value="내정보" class=""/></a>
								<a href="${contextPath}/goRegiFoodRoomPage.do"><input type="button" value="업체등록" class="" id="regiFormBtn"/></a>
								<a href="${contextPath}/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
							</c:if>
							<c:if test="${fr_class == 01}">
								<a href="${contextPath}/goApproveFoodRoomPage.do"><input type="button" value="업체 승인" class=""/></a>
								<a href="${contextPath}/goManageFoodRoomPage.do"><input type="button" value="승인 업체 관리" class=""/></a>
								<a href="${contextPath}/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
							</c:if>
						</div>
					</td>		
					</c:when>
				<c:otherwise>
					<td>
						<a href="${contextPath}/goLoginPage.do" class="topMenuLoginBtn topMenuBtn">로그인</a> <!-- 로그인 페이지로 연결 -->
					</td>
				</c:otherwise>
				</c:choose>			
		</tr>
	</table>
</body>
</html>