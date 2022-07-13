<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>선택한 업체룸 목록</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<script>
function readURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#addd').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
$(document).ready(function() {
	
	$(".addRoomBtn").on("click", function() {
		$('#addRoomInfoFormField').show();
		
	});
	
	
	// 기입한 룸정보에 대한 유효성 검사
	$("#addBtn").on("click", function() {
	   
	   
	   document.addRoomInfoForm.action="${contextPath}/addRoomInfoGo.do";
	   document.addRoomInfoForm.submit();   
	   
	});
	   
});


$(document).on('click', '.deleteRoom', function(){
	if (confirm("정말 방을 삭제 하시겠습니까?") == true) {
		var deleteRoom = $(this);
		
		var row = deleteRoom.parent();
		var tr	= row.children();
		
		var rfr_room_no		= tr.eq(0).text();
		var rfr_no			= tr.eq(1).text();
		var rfr_room_name	= tr.eq(2).text();
		alert(rfr_room_no+ ", " + rfr_no);
		
		$.ajax({
			type:		"POST",
			url:		"${contextPath}/room/delete.do",
			dataType:	"json",
			async:		false,
			success:	function(data) {
				alert(rfr_room_name + "방이 삭제 처리 되었습니다.");
				location.reload();
			}
		});
	}else{
		return;
	}
});
</script>

<body>
<div id="lookRoomListField">	
	<div class="addRoomInfoField">
		<button type="button" class="addRoomBtn">룸 추가</button>
	</div>	
	<div class="roomListField"> 
		<c:forEach var="roomList" items="${roomList}" varStatus="status">
			<div class="lookRoomView1Field">
			<c:if test="${status.index % 2 == 0}">
				<div class="lookRoomView1">
					<c:if test="${roomList.fr_room_image != null}">
						<img src="${contextPath}/roomImg/${roomList.fr_no}/${roomList.fr_room_image}" width="300" height="120" style="float:left;">
					</c:if>
					<c:if test="${roomList.fr_room_image == null}">
						<img src="${contextPath}/roomImg/imsi/logo.png" width="300" height="100" style="float:left;">
					</c:if>
					<br/>
					<span>룸 이름  </span>
					<br/>
					${roomList.fr_room_name}
					<br/>
					<br/>
					<span>룸 정원 : </span>${roomList.fr_room_person_no}
					<div class="roomListBtnField">
						<button type="button" class="roomListBtn">수정</button>
						<button type="button" class="deleteRoom roomListBtn">삭제</button>
					</div>
				</div>
			</c:if>
			</div>
			<div class="lookRoomView2Field">
			<c:if test="${status.index % 2 == 1}">
				<div class="lookRoomView2">
					<c:if test="${roomList.fr_room_image != null}">
						<img src="${contextPath}/roomImg/${roomList.fr_no}/${roomList.fr_room_image}" width="300" height="100" style="float:left;">
					</c:if>
					<c:if test="${roomList.fr_room_image == null}">
						<img src="${contextPath}/roomImg/imsi/logo.png" width="300" height="100" style="float:left;">
					</c:if>
					<span>룸 이름 : </span>${roomList.fr_room_name}<br/>
					<span>룸 정원 : </span>${roomList.fr_room_person_no}
					<div>
						<button type="button" class="">수정</button>
						<button type="button" class="deleteRoom">삭제</button>
					</div>
				</div>
			</c:if>
			</div>
		</c:forEach>			 
	</div>	

	<div id="addRoomInfoFormField" style="display:none;">
		<form name="addRoomInfoForm" method="post" enctype="multipart/form-data">
			<input class="" type="text" name="fr_no" value="${room_no}" readonly style="display:none;"/>
			<table class="addRoomInfoFormTable">	
				<tr style="float:left;">
					<td colspan=2>
						<span class="">[미리보기]</span><br/>
						<div class="">
						<input id="asd" type="file" name="fr_room_image" onchange="readURL(this);" style="display:none;"/>
						</div>	
						<img id="addd" width="220" height="120" style="float:left;"/>
						<div>
							<label for="asd" class="">▶룸 이미지 첨부</label>
						</div>
					</td>
				</tr>
				<tr style="float:right;">
					<td class="roomInfoTd">
						<br>
						<br>
						<span>룸 이름</span>
						<input class="roomNameNo" type="text" name="fr_room_name" id="fr_room_name" placeholder="룸 이름을 입력해 주세요."/>	
						<br>
						<br>
						<span>룸 정원 </span>
						<input class="roomNameNo" type="number" min="1" max="100" name="fr_room_person_no" id="fr_personNo" value="1"/>
					</td>
				</tr>	
				<tr>
					<td colspan=2>	
						<button id="addBtn" class="roomInfoBtn">룸 추가하기</button>
						<button type="button" class="roomInfoBtn" onclick="location.href=''">취소하기</button>
					</td>
				</tr>	
			</table>	
		</form>
	</div>
</div>
</body>
</html>