<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>업체등록 페이지</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<body>
	<div id="signUpField">
		<span>업체등록</span>
		<form id="signUpForm" name="" method="post">	
			<table id="signUpTable">
				<tr style="display:none">
					<td>아이디</td>
					<td><input type="text" name="fr_id" id="" value=""/></td>	
				</tr>
				<tr>
					<td>업체명</td>
					<td><input type="text" name="fr_store_N" id="" /></td>	
				</tr>
				<tr>
					<td>업체주소</td>
					<td><input type="text" name="fr_address" id="" /></td>
				</tr>
				<tr>
					<td>업체연락처</td>
					<td><input type="text" name="fr_sp_number" id="" /></td>
				</tr>	
				<tr>
					<td>
						<span class="">업체 주메뉴</span>
					</td>
					<td>
						<select name="fr_menu" id="">
							<option value="한식" selected>한식</option>
							<option value="중식">중식</option>
							<option value="일식">일식</option>
							<option value="양식">양식</option>	
							<option value="기타">기타</option>
						</select>			
					</td>
				</tr>

				<tr>
					<td><button type="button" id="" onclick="location.href='${contextPath}/imsi/main.do'">등록취소</button></td>
					<td><button type="button" id="" >등록요청</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>