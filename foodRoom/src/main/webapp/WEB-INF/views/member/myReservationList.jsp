<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약현황</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script>
$(document).on('click', '.cancleReservation', function(){
	if (confirm("정말 예약을 취소 하시겠습니까?") == true) {
		var deleteRoom = $(this);
		
		var row = cancleReservation.parent();
		var tr	= row.children();
		
		var rfr_room_no		= tr.eq(0).text();
		alert(rfr_room_no+ ", " + rfr_no);
		
		$.ajax({
			type:		"POST",
			url:		"${contextPath}/",
			dataType:	"json",
			async:		false,
			success:	function(data){
				alert(rfr_room_no + "예약이 취소 되었습니다.");
				location.reload();
			}
		});
	}else{
		return;
	}
});

</script>

<body>
<div class="">	
		
	<div><span class="">예약현황</span></div> 
        		
	<c:forEach var="myReservationList" items="${myReservationList}" varStatus="status">		
		<div style="border: 1px solid black; min-height: 100px;">
			<c:if test="${myReservationList.fr_room_image != null}">
				<img src="${contextPath}/roomImg/${myReservationList.fr_no}/${myReservationList.fr_room_image}" width="300" height="100" style="float:left;">
			</c:if>
			<c:if test="${myReservationList.fr_room_image == null}">
				<img src="${contextPath}/roomImg/imsi/logo.png" width="300" height="100" style="float:left;">
			</c:if>
			<span>룸 번호		: </span>${myReservationList.fr_room_no}<br/>
			<span>예약인원  	: </span>${myReservationList.fr_reservation_person_no}<br/>
			<span>예약일자 	: </span>${myReservationList.fr_reservation_date}
			<span>예약자	 	: </span>${myReservationList.fr_name}
			<div>
				<span style="display:none;">${myReservationList.fr_room_no}</span>
				<button type="button" class="">수정</button>
				<button type="button" class="cancleReservation">예약취소</button>
			</div>
		</div>
	</c:forEach>		 		 
	
</div>
</body>
</html>