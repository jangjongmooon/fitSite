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
<script>
// 페이지를 불러올 때 휴일 지정 유무에 따라 버튼 보여주기
window.onload=function() {
	var cnt = "<c:out value='${revCnt}'/>"
	if(cnt == 0) {
		$('#dayOff').show();
		$('#dayOn').hide();
	} else {
		$('#dayOn').show();
		$('#dayOff').hide();
	}
}

$(document).ready(function() {	
	// 파라메터로 받아온 날짜 변수 저장해서 사용하기.
var url = window.location.search;
var param = new URLSearchParams(url);
var fr_reservation_date = param.get('fr_reservation_date');


//ajax로 가져온 데이터 클릭 이벤트 안될 시 $(".reservationBtn").click(function() ==> $(document).on('click', '.reservationBtn', function()
	$(document).on('click', '.reservationBtn', function(){
	    
	    var reservationBtn = $(this);
	    var row = reservationBtn.parents();
	    var tr = row.children();
	    var mfr_room_no   = tr.eq(0).text();
	    var tr = '';
	    
	    $.ajax({
	       type:      "POST",                              
	        url:        "${contextPath}/revUserList.do",       
	        dataType:   "json",
	        data:      {fr_room_no: mfr_room_no , fr_reservation_date: fr_reservation_date},
	        async:      false,
	        success:   function(data) {
	           tr='<table><tr>'+
	            '<th>예약일자</th>'+
	            '<th>회원이름</th>'+
	            '<th>예약정원</th></tr>';
	            
	            if(data.length>=1){
	            $(data).each(function(r, item){
			              tr+='<tr><td>'+ data[r].fr_reservation_date + '</td>'+
			              '<td>'+ data[r].fr_name + '</td>'+
			              '<td>'+ data[r].fr_reservation_person_no+'</td></tr>';
	            });
	            }
	            
	     		 else{
		            tr+='<tr><td colspan=3>예약된 회원이 없습니다.</td></tr>';	
	            	}
	           $("#reservatioinList").html(tr);
	        }
	    });    
	});
	
	$(document).on('click', '#dayOff', function(){
		var arr = new Array();
		<c:forEach items="${revRoomList}" var="revRoomList">
			arr.push({
				fr_room_no: "${revRoomList.fr_room_no}"
			});
		</c:forEach>
		if(arr.length > 0) {
			alert("예약된 룸이 있습니다");
			return false;
		}
	});
	
});
</script>
<body>
	<div style="width:100%; height:100%; overflow:scroll;">
		<div style="width:100%; height:4%;">
			<!-- 휴일 지정 버튼 누르면 휴일지정 버튼 안보여주고 휴일취소버튼 보여주기 <> 반대로도 활성화 시키기 
				 휴일 지정은 예약된 정보가 없는 경우만 가능
				 휴일 지정하면 달력에 날짜표시 빨간색으로 변경하기 -->
			<c:if test="${revCnt==0 }">휴일 아님</c:if>
			<c:if test="${revCnt>=1 }">휴일 맞아</c:if>
			<a href="${contextPath}/restDay.do?fr_reservation_date=${fr_reservation_date}" 
				onclick="return confirm('[${fr_reservation_date}]일을 휴일 지정 하시겠습니까?');" class="">
				<button type="button" style="float:right;" id="dayOff">휴일 지정</button>
			</a>
			<a href="${contextPath}/restDeleteDay.do?fr_reservation_date=${fr_reservation_date}" 
				onclick="return confirm('[${fr_reservation_date}]일을 휴일 지정 하시겠습니까?');" class="">
				<button type="button" style="float:right;" id="dayOn" >휴일 취소</button>
			</a>
		</div>
		<div class="reservationPossible">
			<span>예약 가능</span><hr/>
			<!-- for문 조건:예약가능한 -->
			<c:forEach var="frRoomNoList" items="${frRoomNoList}">
				<div style="min-height:120px;">
					<c:if test="${frRoomNoList.fr_room_image != null}">
						<img id="loomImage" usemap="" width="300" height="120" src="${contextPath}/roomImg/${frRoomNoList.fr_no}/${frRoomNoList.fr_room_image}" style="float:left;"/>
					</c:if>
					<c:if test="${frRoomNoList.fr_room_image == null}">
						<img id="loomImage" usemap="" width="300" height="120" src="${contextPath}/roomImg/imsi/logo.png" style="float:left;"/>
					</c:if>
					<span class="">룸이름 : </span>
					<span class="">${frRoomNoList.fr_room_name}</span><br/>							
					<span class="">룸정원 : </span>
					<span class="">${frRoomNoList.fr_room_person_no}</span>
					<div>				
					<span class="test"></span>
						<a href="${contextPath}/ownerRevOk.do?fr_room_no=${frRoomNoList.fr_room_no}&fr_reservation_date=${fr_reservation_date}" 
												onclick="return confirm('[${frRoomNoList.fr_room_name}] 룸을 예약 처리 하시겠습니까?');" class="">
												<button type="button" id="">오프라인 예약</button></a>
					</div>
				</div>	
			</c:forEach>	
		</div>
					
		<div class="reservatioinImpossible">
			<span>예약 완료</span><hr/>
			<!-- for문 조건:예약불가능한 -->
			<c:forEach var="revRoomList" items="${revRoomList}">
				<div style="min-height:120px;">
					<c:if test="${revRoomList.fr_room_image != null}">
						<img id="loomImage" usemap="" width="300" height="120" src="${contextPath}/roomImg/${revRoomList.fr_no}/${revRoomList.fr_room_image}" style="float:left;"/>
					</c:if>
					<c:if test="${revRoomList.fr_room_image == null}">
						<img id="loomImage" usemap="" width="300" height="120" src="${contextPath}/roomImg/imsi/logo.png" style="float:left;"/>
					</c:if>
					<span class="">룸이름 : </span>
					<span class="">${revRoomList.fr_room_name}</span><br/>	
					<span class="">룸정원 : </span>
					<span class="">${revRoomList.fr_room_person_no} 명</span>	
					<div>
					<span style="display:none;">${revRoomList.fr_room_no}</span>
									  			 <button type="button" class="reservationBtn">예약현황</button>
						<a href="${contextPath}/cancleRev.do?fr_room_no=${revRoomList.fr_room_no}&fr_reservation_date=${fr_reservation_date}"
									  			 onclick="return confirm('[${revRoomList.fr_room_name}] 룸을 예약 취소 하시겠습니까?');" class="">
									  			 <button type="button" id="">예약취소</button></a>
					</div>
				</div>
			</c:forEach>
		</div>
			<div id="reservatioinList"></div>
	</div>
	
</body>
</html>