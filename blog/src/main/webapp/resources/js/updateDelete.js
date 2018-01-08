var status = true;

$(document).ready(function(){
	$("#deletemember").click(function(){
		var jsonv = {id : $("#id").val()};		
		$.ajax({
			type:"POST",
			url:"/Hyundai2/DeleteMember",//학원 Hyundai2  집 study
			data:jsonv,
			success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.					
				if(data == 1){
					alert("삭제성공");
					window.location.assign("index.jsp");
				}else if(data == -1){
					alert("삭제에 실패하였습니다. 나중에 다시 시도해주십시오.")
				}//end else-------------------->
			}//end 리턴함수 -------------------------------->
		});//end ajax----------------------------------------------->
	});//end 회원탈퇴버튼 ----------------------------------------------------->
	
	$("#updateInfo").click(function(){
		checkIt();
		
		if(status){
			var jsonv = {
					id : $("#id").val(), passwd : $("#passwd").val(),
					newpasswd : $("#newpasswd").val(), name : $("#name").val(),
					reg_date : $("#reg_date").val(), addr : $("#addr").val(),
					tel : $("#tel").val()
			};/*-------------------- END JSON ---------------------*/
				
			$.ajax({
				type:"POST",
				url:"/Hyundai2/UpdateMember",//학원 Hyundai2  집 study
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.	
					if(data == 1){
						alert("회원 정보가 수정되었습니다.");
						window.location.assign("index.jsp");
					}else{ alert("정보수정 실패 실패 나중에 다시 시도해 주세요.");}
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}/*--------------------- END IF --------------------------*/	
	});//end 정보수정버튼 ------------------------------------------------------>
});

function checkIt(){//회원가입 폼 입력 체크
	status = true;
	if(!$.trim($("#passwd").val())){
		alert("비민번호를 입력하세요.");		
		$("#passwd").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#newpasswd").val())){
		alert("비민번호를 입력하세요.");		
		$("#passwd").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#name").val())){
		alert("이름을 입력하세요.");		
		$("#name").focus();
		status = false;
		return false;
	}	
	if(!$.trim($("#addr").val())){
		alert("주소를 입력하세요.");		
		$("#addr").focus();
		status = false;
		return false;
	}	
	if(!$.trim($("#tel").val())){
		alert("전화번호를 입력하세요.");		
		$("#tel").focus();
		status = false;
		return false;
	}
}