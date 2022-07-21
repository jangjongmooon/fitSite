<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="${contextPath}/css/foodroom.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<script>
	
	function fnc_process() { // 아이디 중복 검사
		
       	var tid 		= document.getElementById('fr_id');
        var id_RegExpCn = /^[a-zA-Z0-9]{4,12}$/;
	    var	_id1		= $("#fr_id").val();    
	        
				$.ajax({	// 아이디 중복 체크 기능
						type:		"post",
						url:		"${contextPath}/checkId.do",
						dataType:	"json",
						async : 	false,
						data:		{fr_id: _id1},	
						success:	function(data) {
							
							// 서버에서 전송된 결과에 따라 메시지를 표시한다.
						
							if (tid.value == '' || tid.value == null) {				// 아이디 입력란이 공백인 경우.
								$("#idChkMsg").html("아이디를 입력해주세요.");
								$("#idChkMsg").css("color","white")
								$("#signUpBtn").prop("disabled", true);
								$("#signUpBtn").css("color","#1de2e2")
								$("#signUpBtn").css("background","white")
							} else if (!id_RegExpCn.test(tid.value)) {				// 아이디에 특수문자나 공백이 들어간 경우.
								$("#idChkMsg").html("영문&숫자 4~12자리만 가능합니다");
								$("#idChkMsg").css("color","pink")
								$("#signUpBtn").prop("disabled", true);
								$("#signUpBtn").css("color","#1de2e2")
								$("#signUpBtn").css("background","white")
					        } else if ($.trim(data) == 0) {							// 아이디가 DB와 겹치지 않는 경우.
								$("#idChkMsg").html("등록 가능한 아이디 입니다.");	
								$("#idChkMsg").css("color","skyblue")
								$("#signUpBtn").prop("disabled", false);
								$("#signUpBtn").css("color","white")
								$("#signUpBtn").css("background","#1de2e2")
							} else {												// 아이디가 DB와 겹치는 경우.
								$("#idChkMsg").html("이미 사용중인 아이디 입니다.");
								$("#idChkMsg").css("color","pink")
								$("#signUpBtn").prop("disabled", true);
								$("#signUpBtn").css("color","#1de2e2")
								$("#signUpBtn").css("background","white")
							}
						}
				});
	}

	function pwTest() {
		
		   var RegExp = /^[a-zA-Z0-9]{4,12}$/; // 비밀번호 유효성 검사
		   var objPwd = document.getElementById("fr_pwd"); // 비밀번호 입력값 받기
		   
		   if(!RegExp.test(objPwd.value)) {
			  $("#pwdSuccess").html("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
			  $("#pwdSuccess").css("color","green")
		   }
		   else if($("#fr_pwd").val() == $("#rePwd").val() && $("#fr_pwd").val()!="" ) {
		      $("#pwdSuccess").html("비밀번호가 일치합니다.");
		      $("#pwdSuccess").css("color","blue")
		   }
		   else if($("#fr_pwd").val() != $("#rePwd").val()) {
		      $("#pwdSuccess").html("비밀번호가 일치하지 않습니다.");
		      $("#pwdSuccess").css("color","red")
		   }
		
	}

	$(document).ready(function() {
		
		$('#mail-Check-Btn').click(function() {
			var eamil = $('#fr_email1').val() + $('#fr_email2').val(); // 이메일 주소값 얻어오기!
			document.getElementById('fr_email').value = document.getElementById('fr_email1').value + document.getElementById('fr_email2').value
			alert('완성된 이메일 : ' + eamil); // 이메일 오는지 확인
			var checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'get',
				url : '<c:url value ="/mailCheck.do?email="/>'+eamil, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
				success : function (data) {
					console.log("data : " +  data);
					checkInput.attr('disabled',false);
					code =data;
					alert('인증번호가 전송되었습니다.')
				}			
			}); // end ajax
		}); // end send eamil
		
		// 인증번호 비교 
		// blur -> focus가 벗어나는 경우 발생
		$('.mail-check-input').blur(function () {
			const inputCode = $(this).val();
			const $resultMsg = $('#mail-check-warn');
			
			if(inputCode === code){
				$resultMsg.html('인증번호가 일치합니다.');
				$resultMsg.css('color','green');
				$('#mail-Check-Btn').attr('disabled',true);
				$('#userEamil1').attr('readonly',true);
				$('#userEamil2').attr('readonly',true);
				$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
		        $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
			}else{
				$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
				$resultMsg.css('color','red');
			}
		});
		
		$("#signUpBtn").on("click", function() {
			
			var RegExp = /^[a-zA-Z0-9]{4,12}$/; // 비밀번호 유효성 검사		
		    var n_RegExp = /^[가-힣]{2,12}$/; //이름 유효성검사
		    var p_RegExp = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/; // 연락처 유효성 검사
		    
		    var objPwd     = document.getElementById("fr_pwd"); 	 // 비밀번호 입력값 받기
		    var objName    = document.getElementById("fr_name"); 	 // 이름 입력값 받기
		    var objPnumber = document.getElementById("fr_p_number"); // 연락처 입력값 받기		  
		    
		    // 비밀번호 유효성 검사 영역
			if($("#fr_pwd").val() == '') {
				alert("비밀번호를 입력하십시오");
				$("#ef_pwd").focus();
				return false;
			}
			if(!RegExp.test(objPwd.value)) {
	            alert("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");   
	            $("#fr_pwd").focus();
	            return false;
	        }
			if($("#rePwd").val() == '') {
				alert("비밀번호 확인을 입력하십시오");
				$("#repwd").focus();
				return false;
			}
			if($("#fr_pwd").val() != $("#rePwd").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#repwd").focus();
				return false;
			}		
			
			// 이름 유효성 검사 영역
			if($("#fr_name").val() == '') {
				alert("이름을 입력하십시오");
				$("#fr_name").focus();
				return false;
			}
			if(!n_RegExp.test(objName.value)) {
	            alert("이름에 특수문자,영어,숫자는 사용할수 없습니다. 한글만 입력하여주세요.");
	            $("#fr_name").focus();
	            return false;
	        }
			
			// 연락처 유효성 검사 영역
			if($("#fr_p_number").val() == '') {
				alert("연락처를 입력하십시오");
				$("#fr_p_number").focus();
				return false;
			}
			if(!p_RegExp.test(objPnumber.value)) {
		         alert("올바른 연락처를 입력해 주세요. ex)010-9999-9999");
		         $("#fr_p_number").focus();
		         return false;
		     }
	        
			document.signUpForm.action = "${contextPath}/signUp.do";
			document.signUpForm.submit();
		
		}); // submit버튼		
		
	}); // function	
	

</script>

<body>
	<div id="signUpField">
		<div class="signUpFieldLogo"><a href="${contextPath}/index.do" class="signUpFieldLogo">FOODROOM</a></div>
		<form id="signUpForm" name="signUpForm" method="post">	
			<table id="signUpTable">
				<tr>
					<td colspan=2>
						<label><input type="radio" name="fr_class" id="" value="13" checked /> 일반회원</label>
						<label><input type="radio" name="fr_class" id="" value="02" /> 업체</label>					
					</td>
				</tr>
				<tr>
					<td><input type="text" name="fr_id" id="fr_id" class="joinInput signUpInfo" placeholder="아이디 입력" oninput="fnc_process();"/></td>	
				</tr>
				<tr>
					<td id="idChkMsg">아이디를 입력해주세요.</td>
				</tr>
				<tr>
					<td><input type="password" name="fr_pwd" id="fr_pwd" class="signUpInfo" placeholder="비밀번호 입력" oninput="pwTest();" /></td>
				</tr>
				<tr>
					<td><input type="password" name="rePwd" id="rePwd" class="signUpInfo" placeholder="비밀번호 확인" oninput="pwTest();" /></td>
				</tr>
				<tr>
					<td id="pwdSuccess">비밀번호를 입력해주세요.</td>
				</tr>
				<tr>
					<td><input type="text" name="fr_name" id="fr_name" placeholder="이름 입력" class="signUpInfo" /></td>
				</tr>
				<tr>
					<td><input type="text" name="fr_p_number" id="fr_p_number" placeholder="연락처 입력" class="signUpInfo" /></td>
				</tr>
				<tr>
					<td>
						<input type="text" name="fr_email1" id="fr_email1" placeholder="이메일 입력" class="signUpInfo" />
						<select name="fr_email2" id="fr_email2">
							<option>@naver.com</option>
							<option>@daum.net</option>
							<option>@gmail.com</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
					</td>
				</tr>
				<tr>
					<td><input class="from-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요" disabled="disabled" maxlength="6"></td>					
				</tr>
				<tr>
					<td><span id="mail-check-warn"></span></td>
				</tr>
				<tr>
					<td><input type="hidden" name="fr_email" id="fr_email" /></td>
				</tr>
				<tr>
					<td><button type="button" id="signUpBtn" disabled>가입하기</button></td>					
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
