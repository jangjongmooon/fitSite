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
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="">

	<form id="" name="" method="post">
		<select name="" id="">
			<option value="fr_store_N" selected>업체명</option>
			<option value="fr_address">업체주소</option>
		</select>
		<input class="" type="text" name="" maxlength=100/>
		<button type="button" id="" >검색</button>
		
	</form>

    <!-- 업체등록 승인요청란 -->
		
	<span class="">승인된 업체목록</span>		
       <table class="">           		
		<tr>
			<th><b>업체주소</b></th>
			<th><b>업체명</b></th>
			<th><b>룸정보 보기</b></th>
		</tr>
		<c:forEach var="" items="">
			<tr>

				<td></td>
				<td></td>
				<td class=""><a href="" class="">아이콘 넣기</a></td>	 

		</c:forEach>
	</table>
	

</div>
</body>
</html>