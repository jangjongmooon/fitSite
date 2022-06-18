<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="myPageField">
		<span>MyPage</span>
		<form method="post" action="${contextPath}/imsi/myPageUpdateForm.do" id="">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" id="" name="" value="아이디" readonly/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id="" name="" value="이름" readonly/></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" id="" name="" value="연락처" readonly/></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" id="" name="" value="이메일" readonly/></td>
				</tr>
				<tr>	
					<td><input type="submit" value="수정하기"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>