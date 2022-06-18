<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지 변경하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="myPageUpdateField">
		<span>MyPage Update</span>
		<form method="post" name="myPageUpdateForm" id="">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" id="" name="" value="아이디" readonly/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="" name="" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="" name="repwd" /></td>
				</tr>
				<tr>
					<td id="pwdSuccess">비밀번호 일치 확인란</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id="" name="" value="이름" /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" id="" name="" value="연락처" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" id="" name="" value="이메일" /></td>
				</tr>
				<tr>	
					<td><button type="button" id="myPageUpdateBtn">수정완료</button></td>
				</tr>
			</table>
		</form>
	</div>	
</body>
</html>