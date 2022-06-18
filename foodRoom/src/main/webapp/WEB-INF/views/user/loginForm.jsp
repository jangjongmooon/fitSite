<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 페이지</title>
	
	<style>

	</style>
	
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<body>

<div id="loginField">
	<form method="post" name="" id="loginForm">
		<table id="loginTable">
			<tr>
				<th><span>로그인</span></th>
			</tr>
			<tr>
				<td><span>ID</span></td>
				<td><input type="text" name="" id="" placeholder="ID 입력" /></td>
			</tr>
			<tr>
				<td><span>PWD</span></td>
				<td><input type="password" name="" id="" placeholder="PWD 입력" /></td>
			</tr>
			<tr>
				<td><button type="button" class="">로그인</button></td>
			</tr>
			<tr>
				<td><a href="${contextPath}/imsi/findForm.do" onclick="window.open(this.href,'_blank','width=500,height=500, scrollbars=no, resizable=no');return false;" class="">아이디/비밀번호 찾기</a></td>
				<td><a href="${contextPath}/imsi/signUpForm.do" class="">회원가입</a></td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>