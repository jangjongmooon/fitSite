<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<body>
	<div id="signUpField">
		<span>회원가입</span>
		<form id="signUpForm" name="" method="post">	
			<table id="signUpTable">
				<tr>
					<td>
						<label><input type="radio" name="class" id="" value="13" checked /> 일반회원</label>
						<label><input type="radio" name="class" id="" value="02" /> 업체</label>					
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="" id="" /></td>	
				</tr>
				<tr>
					<td><button type="button" class="">아이디 중복 체크</button></td>
					<td><div id="">중복체크 결과</div></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="" id="" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="" id="" /></td>
				</tr>
				<tr>
					<td><div id="">비밀번호 일치 확인 영역</div></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="" id="" /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" name="" id="" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="" id="" /></td>
				</tr>
				<tr>
					<td><button type="button" id="" onclick="location.href='${contextPath}/imsi/loginForm.do'">가입취소</button></td>
					<td><button type="button" id="" >가입하기</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>