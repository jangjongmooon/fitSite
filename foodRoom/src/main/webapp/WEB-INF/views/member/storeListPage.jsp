<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" 		value="${pageContext.request.contextPath}"/>
<c:set var="findBigStore"		value="${findBigStore}"/>
<c:set var="findSmallStore"		value="${findSmallStore}"/>
<c:set var="personNo"		    value="${personNo}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약하기</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<script type="text/javascript">	
	$(function() {
	    $( '#chkAll' ).click( function() {
	      $('.target01').children().prop( 'checked', this.checked );
	    });
	});	
	$(document).on('click', '#searchInfo', function() {
		//alert("gd");
		var cnt = $("input[id=fr_menu]:checkbox:checked").length;
		//alert(cnt);
		if(cnt == 0) {
			alert("메뉴를 하나이상 선택해 주세요");
			return
		} else {
		    document.searchMenuInfo.action="${contextPath}/detailFindStoreList.do";
	        document.searchMenuInfo.submit();  
		}
		
	});
</script>
 
<body>
<div class="storeListPageField">
	<!-- 지역검색 -->
	<div id="menu">
		<ul class="main1">
			<li><a href="#">지역&#9660;</a>
				<ul class="main2">
					<li><a href="${contextPath}/findStoreList.do?findBigStore=서울&findSmallStore=모두">서울</a>
						<ul class="main3">
							<li><a href="${contextPath}/findStoreList.do?findBigStore=서울&findSmallStore=강동">강동</a></li>
							<li><a href="${contextPath}/findStoreList.do?findBigStore=서울&findSmallStore=강서">강서</a></li>
							<li><a href="${contextPath}/findStoreList.do?findBigStore=서울&findSmallStore=강남">강남</a></li>
							<li><a href="${contextPath}/findStoreList.do?findBigStore=서울&findSmallStore=강북">강북</a></li>
						</ul>
					</li>
					<li><a href="#">경기</a>

					</li>
					<li><a href="#">지방1</a>

					</li>
					<li><a href="#">지방2</a>

					</li>	
				</ul>
			</li>
		</ul>
	</div>
	<c:if test="${findBigStore != null}">
		<div class="searchMenuInfoField">
			<form method="post" class="" name="searchMenuInfo">		
				<input type="hidden" name="findBigStore" value="${findBigStore}" />
				<input type="hidden" name="findSmallStore" value="${findSmallStore}" />
				<div class="target01">
					<input type="checkbox" id="chkAll" /><label for="chkAll">All</label>
					<input type="checkbox" name="menu1" id="fr_menu" value="한식"/>한식
					<input type="checkbox" name="menu2" id="fr_menu" value="중식"/>중식
					<input type="checkbox" name="menu3" id="fr_menu" value="일식"/>일식
					<input type="checkbox" name="menu4" id="fr_menu" value="양식"/>양식
					<input type="checkbox" name="menu5" id="fr_menu" value="기타"/>기타
				</div>
				예약가능인원 : <input type="number" min="1" max="20" name="personNo" value="1">
				<input type="button" id="searchInfo" value="검색">		
			</form>
		</div>
	</c:if>
    <!-- 업체목록 -->
    <div class="storeList">		
			
		<table class="storeListTable">           		
			<tr>
				<th><b>주소</b></th>
				<th><b>업체명</b></th>
				<th><b>연락처</b></th>
				<th><b>주메뉴</b></th>
			</tr>
			<c:forEach var="storeList" items="${storeList}">
				<tr>
					<td>${storeList.fr_address.substring(7)}</td>
					<c:if test="${personNo != null}"> 
						<td><a href="${contextPath}/selectStoreRoomListForm.do?fr_no=${storeList.fr_no}&personNo=${personNo}">${storeList.fr_store_name}</a></td>
					</c:if>
					<c:if test="${personNo == null}"> 
						<td><a href="${contextPath}/selectStoreRoomListForm.do?fr_no=${storeList.fr_no}&personNo=0">${storeList.fr_store_name}</a></td>
					</c:if>
					<td>${storeList.fr_store_p_number}</td>
					<td>${storeList.fr_menu}</td>
				</tr>  				
			</c:forEach>
		</table>
	</div>	

</div>
</body>
</html>