var status = true;
var check_stat = true;

$(document).ready(function(){
	$("#id").blur(function(){//포커스가 벗어났을때 id를 작성하고 넘어가면 자동으로 중복체크	
		if($.trim($("#id").val())){			
			check_stat = true;
			var jsonv = {id : $("#id").val()};
			//json문법표현 var 변수명 = {key : value, key : value };			
			$.ajax({
				type:"POST",
				url:"/mobilesoftware/login/idCheck.do",
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.		
					var str1 = '<p id="ck">';//returnIntCheck페이지에 미리 적어놓은 <p id="ck">를 정의한다.
			    	var loc = data.indexOf(str1);//returnIntCheck페이지의 내용중 위에 정아한 str1을 찾는다.
			    	var len = str1.length;
			    	var check = data.substr(loc+len,1);//str1이후의 내용이 리턴값이기 때문에 가져온다.
					if(check == 1){		
						$('#id2').empty();//처음 id 가이드라인 삭제 
						$('#checkspan').remove();//혹시 추가된 문구가 있다면 지워준다.
						$('#li_id').append("<span id=\"checkspan\">사용가능한 id입니다.</span>");			
						//성공메세지를 생성해준다.
						$('#checkspan').css('color', 'green');//성공했으므로 초록색으로
					}else if(check != 1){				
						$('#id2').empty();	
						$('#checkspan').remove();
						$('#li_id').append("<span id=\"checkspan\">이미 존재하는 id입니다. 다시 입력하세요.</span>");
						$('#checkspan').css('color', 'red');
						$('#id').val("");
						$('#id').focus();
						check_stat = false;
					}//end else-------------------->
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}else{
			$('#id2').empty();			
			$('#checkspan').remove();
			$('#li_id').append("<span id=\"checkspan\">id를 입력하지 않았습니다. id를 입력하세요.</span>");
			$('#checkspan').css('color', 'red');
			$('#id').focus();
			check_stat = false;
		}
	});/*------------------------- END 아이디 중복검사 ----------------------------*/
	$("#pass_confirm").blur(function(){//비밀번호 확인에서 포커스가 벗어났을때		
		if($('#pass').val()==$('#pass_confirm').val()){
			$('#passspan').remove();
			$('#li_pass_confirm').append("<span id=\"passspan\">암호가 일치합니다. 다음작업을 계속하세요.</span>");
			$('#passspan').css('color', 'green');
		}//end if 암호와 암호확인이 같을 때 ----------------------------------->
		else{
			$('#passspan').remove();
			$('#li_pass_confirm').append("<span id=\"passspan\">입력한 암호와 다릅니다. 다시 입력하세요.</span>");
			$('#passspan').css('color', 'red');
			$('#pass_confirm').val("");
		}//end if 암호와 암호확인이 다를 때 ----------------------------------->
	});/*------------------------- 비밀번호값 비교 ----------------------------*/
	
	$("#memberinsert").click(function(){
		checkIt();
		
		if(status){
			var jsonv = {id : $("#id").val(), pass : $("#pass").val(), name : $("#name").val(), 
					hp : $("#hp1").val()+'-'+$("#hp2").val()+'-'+$("#hp3").val(), 
					email : $("#email1").val()+'@'+$("#email2").val(), m_level : $('#m_level').val()
			};/*-------------------- END JSON ---------------------*/
				
			$.ajax({
				type:"POST",
				url:"/mobilesoftware/login/joinPro.do",
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.	
					var str1 = '<p id="ck">';//returnIntCheck페이지에 미리 적어놓은 <p id="ck">를 정의한다.
			    	var loc = data.indexOf(str1);//returnIntCheck페이지의 내용중 위에 정아한 str1을 찾는다.
			    	var len = str1.length;
			    	var check = data.substr(loc+len,1);//str1이후의 내용이 리턴값이기 때문에 가져온다.
					if(check == 1){
						alert("회원가입 성공!");
						window.location.assign("/mobilesoftware/index.do");
					}else{ alert("회원가입 실패 나중에 다시 시도해 주세요.");}
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}/*--------------------- END IF --------------------------*/
	});/*------------------------- END 회원가입버튼 ----------------------------*/
	
});//END 익명함수---------------------------------------------------------------------------------------------->

function checkIt(){//회원가입 폼 입력 체크
	status = true;
	if(!$.trim($("#id").val())){
		alert("아이디를 입력하세요.");			
		$("#id").focus();
		status = false;//스테이터스의 값을 바꿔줌으로써 ajax함수로의 진입을 막는다.
		return false;//리턴값을 false를 주면 함수가 종료되면서 밑의 코드를 진행 안한다.
	}
	if(!check_stat){
		alert("중복된 아이디 입니다. 다시입력 하세요.");
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