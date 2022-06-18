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

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body>
	<table id="">
		<tr>
			<td>
				<a href=""><img src="" width="170" height="75" id=""></a>
			</td>
			<td>
				<a href="" class="">메뉴1</a>
			</td>
			<td>
				<a href="" class="">메뉴2</a>
			</td>
			<td>
				<a href="" class="">메뉴3</a>
			</td>
			<td>
				<a href="" class="">더보기</a>
			</td>
			<c:choose>
				<c:when test="${isLogOn == true && fr_id != null}"> 
								
					<td class="">님</td>
					<div id="">
						<c:if test="${fr_class == 02 회원 }">
							<a href=""><input type="button" value="내정보" class=""/></a>
							<a href=""><input type="button" value="예약내역" class=""/></a>
							<a href="" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>								
						</c:if>
						<c:if test="${fr_class == 11 등록 오너 }">
							<a href=""><input type="button" value="내정보" class=""/></a>
							<a href=""><input type="button" value="예약내역" class=""/></a>
							<a href=""><input type="button" value="업체관리" class=""/></a>
							<a href="" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
						</c:if>
						<c:if test="${fr_class == 01 미등록 오너 }">
							<a href=""><input type="button" value="내정보" class=""/></a>
							<a href=""><input type="button" value="업체등록" class=""/></a>
							<a href="" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
						</c:if>
						<c:if test="${fr_class == 00 관리자 }">
							<a href=""><input type="button" value="업체 승인 관리" class=""/></a>
							<a href=""><input type="button" value="승인 업체 관리" class=""/></a>
							<a href="" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class=""/></a>
						</c:if>
					</div>
				</c:when>
				<c:otherwise>
					<td>
						<a href="" class="">로그인</a> <!-- 로그인 페이지로 연결 -->
					</td>
					<td>
						<a href="" class="">회원가입</a> <!-- 로그인 페이지로 연결 -->
					</td>
				</c:otherwise>
			</c:choose>				
		</tr>
	</table>
</body>
</html>