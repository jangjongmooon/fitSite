<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약내역</title>
	<link href="${contextPath}/css/foodroom.css" rel="stylesheet">    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<button type="button" id="">휴일 지정</button>	
		<button type="button" id="">취소</button>	
	</div>
	<div>
		<table id="reservationFormField">
			<tr>
				<td>
					<div id="reservationPossible">
						<span>예약 가능</span>
						
						<div style="width:200px; height:150px; border:1px solid red; "><img id="loomImage" usemap="" width="120" height="120" src=""/></div>
						<div style="width:200px; height:150px; border:1px solid red; ">
							<table id="">
								<tr>
									<td><span class="">룸이름 : </span></td>
									<td><span class=""></span></td>
								</tr>
								<tr>
									<td><span class="">룸정원 : </span></td>
									<td><span class=""></span></td>
								</tr>
							</table>
							<button type="button" id="">오프라인 예약</button>
						</div>		
					</div>
				</td>
			</tr>
			<tr>
				<td>						
					<div id="reservatioinImpossible">
						<span>예약 완료</span>
							
						<div style="width:200px; height:150px; border:1px solid red; "><img id="loomImage" usemap="" width="120" height="120" src=""/></div>
						<div style="width:200px; height:150px; border:1px solid red; ">
							<table id="">
								<tr>
									<td><span class="">룸이름 : </span></td>
									<td><span class=""></span></td>
								</tr>
								<tr>
									<td><span class="">룸정원 : </span></td>
									<td><span class=""></span></td>
								</tr>
							</table>
							<button type="button" id="">예약현황</button>
							<button type="button" id="">예약취소</button>
						</div>
					</div>
				</td>
			</tr>		
		</table>
	</div>
	<div id="reservatioinList" style="display:none;">
		<table>
			<tr>
				<th>예약 일자</th>
				<th>예약 회원</th>
				<th>연락처</th>
			</tr>
			<!-- for문 -->
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	
	</div>
</body>
</html>