var status = true;
var check_stat = true;

$(document).ready(function(){
	$("#pass").blur(function(){//포커스가 벗어났을때 자동으로 해당계정의 암호인지 체크	
		if($.trim($("#pass").val())){			
			check_stat = true;
			var jsonv = {id : $("#id").val(), pass : $("#pass").val()};
			//json문법표현 var 변수명 = {key : value, key : value };			
			$.ajax({
				type:"POST",
				url:"/mobilesoftware/login/passCheck.do",
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.		
					var str1 = '<p id="ck">';//returnIntCheck페이지에 미리 적어놓은 <p id="ck">를 정의한다.
			    	var loc = data.indexOf(str1);//returnIntCheck페이지의 내용중 위에 정아한 str1을 찾는다.
			    	var len = str1.length;
			    	var check = data.substr(loc+len,1);//str1이후의 내용이 리턴값이기 때문에 가져온다.
					if(check == 1){								
						$('#pcheckspan').remove();//혹시 추가된 문구가 있다면 지워준다.
						$('#li_pass').append("<span id=\"pcheckspan\">암호가 일치합니다. 다음은 새 암호를 입력하시오.</span>");			
						//성공메세지를 생성해준다.
						$('#pcheckspan').css('color', 'green');//성공했으므로 초록색으로
					}else if(check != 1){					
						$('#pcheckspan').remove();
						$('#li_pass').append("<span id=\"pcheckspan\">암호가 틀렸습니다. 다시 입력하세요.</span>");
						$('#pcheckspan').css('color', 'red');
						$('#pass').val("");
						$('#pass').focus();
						check_stat = false;
					}//end else-------------------->
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}else{				
			$('#pcheckspan').remove();
			$('#li_pass').append("<span id=\"pcheckspan\">암호를 입력하지 않았습니다. 암호를 입력하세요.</span>");
			$('#pcheckspan').css('color', 'red');
			$('#pass').focus();
			check_stat = false;
		}
	});/*------------------------- END 암호검사 ----------------------------*/
	
	$("#memberinsert").click(function(){
		checkIt();
		
		if(status){
			var jsonv = {id : $("#id").val(), pass : $("#pass_confirm").val(), name : $("#name").val(), 
					hp : $("#hp1").val()+'-'+$("#hp2").val()+'-'+$("#hp3").val(), 
					email : $("#email1").val()+'@'+$("#email2").val()
			};/*-------------------- END JSON ---------------------*/
				
			$.ajax({
				type:"POST",
				url:"/mobilesoftware/login/UpdatePro.do",
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.	
					var str1 = '<p id="ck">';//returnIntCheck페이지에 미리 적어놓은 <p id="ck">를 정의한다.
			    	var loc = data.indexOf(str1);//returnIntCheck페이지의 내용중 위에 정아한 str1을 찾는다.
			    	var len = str1.length;
			    	var check = data.substr(loc+len,1);//str1이후의 내용이 리턴값이기 때문에 가져온다.
					if(check == 1){
						alert("정보수정이 완료되었습니다.!");
						window.location.assign("/mobilesoftware/index.do");
					}else{ alert("실패 나중에 다시 시도해 주세요.");}
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}/*--------------------- END IF --------------------------*/
	});/*------------------------- END 회원가입버튼 ----------------------------*/
});/*-------------------------------------------END 익명함수---------------------------------------------*/

function checkIt(){//회원정보수정 폼 입력 체크
	status = true;	
	if(!check_stat){
		alert("틀린 암호 입니다. 다시입력 하세요.");
		status = false;
		return false;
	}
	if(!$.trim($("#pass").val())){
		alert("비민번호를 입력하세요.");		
		$("#pass").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#pass_confirm").val())){
		alert("비민번호를 입력하세요.");		
		$("#pass_confirm").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#name").val())){
		alert("이름을 입력하세요.");		
		$("#name").focus();
		status = false;
		return false;
	}	
	if(!$.trim($("#hp1").val())){
		alert("전화번호를 입력하세요.");		
		$("#hp1").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#hp2").val())){
		alert("전화번호를 입력하세요.");		
		$("#hp2").focus();
		status = false;
		return false;
	}	
	if(!$.trim($("#hp3").val())){
		alert("전화번호를 입력하세요.");		
		$("#hp3").focus();
		status = false;
		return false;
	}
}