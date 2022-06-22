<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>룸정보 수정하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script> 
	// jQuery를 이용하여 이미지 파일을 첨부할 때 미리보기 기능을 구현한다. 		
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$(document).ready(function() {
		
		   // 기입한 룸정보에 대한 유효성 검사
		   $("#reviseRoomInfoBtn").on("click", function() {
		      
		      
		      document.reviseRoomInfoForm.action="";
		      document.reviseRoomInfoForm.submit();   
		      
		   });
		   
		});
	</script>
</head>

<body>
	<div class="">
		<p/>
		<span class="">룸정보 수정</span><p/>
		<div class="">
			<form name="reviseRoomInfoForm" method="post" enctype="multipart/form-data">
				<div style="display:none;"><input type="text" name="" value="">업체번호</div>
			
				<table class="">
					<tr>
						<td class="">룸 이름 : </td>
						<td><input class="" type="text" maxlength="94" name="fr_room_name" id="fr_room_name" value="" placeholder="룸 이름을 입력해 주세요."/></td>
						<td class="">룸 정원</td>
						<td><input class="" type="text" maxlength="94" name="fr_personNo" id="fr_personNo" value="" placeholder="룸 정원을 입력해 주세요."/></td>
					</tr>		
					<tr>
						<td class=""> <!-- viewSide -->
							<span class="">현재사진</span><br/>
							<!-- 파일 첨부 버튼 누르고 아무것도 선택안한 경우, 경로 값이 null이 되는데 그 때 원래 value를 넣어주기 위함 -->	
							<input type="hidden" name="originalImageFileName" value=""/>
							<!-- 외부 경로 이미지 불러오기 -->
							<a href="" target="_blank" class="imgLinkText">[확대보기]</a>
							<img id="" usemap="#test" width="120" height="120" src=""/><br/>							
							<map name="test">
								<area shape="default" coords="10,8,150,292" href="" target="_blank"/>
							</map>
							
							<input type="file" name="" id="" onchange="readURL(this);" style="display:none;" disabled/>
							<label for="" class="">▶파일 첨부</label>
						</td>
					</tr>	
					<tr>
						<td class="">
							<div class="">
								<br/>
								<button id="reviseRoomInfoBtn" class="">룸 수정하기</button>
								<button type="button" id="" onclick="location.href=''">취소하기</button>
							</div>
						</td>
					</tr>	
				</table>	
			</form>
		</div>
	</div>
</body>
</html>