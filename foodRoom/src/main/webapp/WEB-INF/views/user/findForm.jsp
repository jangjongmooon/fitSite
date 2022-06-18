<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디/비밀번호 찾기</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<script>
function findViewDisplay(){
    if($('input:radio[id=findId]').is(':checked')){
        $('#pwdFindTable').hide();
        $('#idFindTable').show();
    }else{
        $('#pwdFindTable').show();
        $('#idFindTable').hide();
    }
}
</script>

<body>
	<div class="findField">
		<span>아이디 / 비밀번호 찾기</span><br/>
		<input type="radio" name="findView" id="findId"  onchange="findViewDisplay()" checked/> 아이디 찾기
		<input type="radio" name="findView" id="findPwd" onchange="findViewDisplay()" /> 비밀번호 찾기
		<table id="idFindTable">
			<tr>
				<td>이름</td>
				<td><input type="text" name="" id="" placeholder="이름 입력" /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="" id="" placeholder="이메일 입력" /></td>
			</tr>
			<tr>
				<td><button type="button" class="">아이디 찾기</button></td>
			</tr>
			<tr>
				<td id="findIdView">찾은 아이디 보여주는 영역</td>
			</tr>
		</table>
		<table id="pwdFindTable" style="display:none;">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="" id="" placeholder="아이디 입력" /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="" id="" placeholder="이메일 입력" /></td>
			</tr>
			<tr>
				<td><button type="button" class="">비밀번호 찾기</button></td>
			</tr>
			<tr>
				<td id="findPwdView">찾은 비밀번호 보여주는 영역</td>
			</tr>
		</table>
	</div>
</body>
</html>