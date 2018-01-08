var status = true;
var check_stat = true;

$(document).ready(function(){
	$("#id").blur(function(){//포커스가 벗어났을때		
		if($.trim($("#id").val())){
			check_stat = true;
			var jsonv = {id : $("#id").val()};
			//json문법표현 var 변수명 = {key : value, key : value };			
			$.ajax({
				type:"POST",
				url:"/Hyundai2/IdCheck",//학원 Hyundai2  집 study
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.					
					if(data == 1){
						$('#checkspan').remove();//혹시 기존에 실패했을 경우 기존 구문을 삭제
						$('#id_li').append("<span id=\"checkspan\">사용하실 수 있는 아이디 입니다.</span>")
						//성공메세지를 생성해준다.
						$('#checkspan').css('color', 'green');//성공했으므로 초록색으로
					}else if(data == -1){
						$('#checkspan').remove();
						$('#id_li').append("<span id=\"checkspan\">이미 있는 아이디 입니다.</span>")
						$('#checkspan').css('color', 'red');
						$('#id').val("");
						$('#id').focus();
						check_stat = false;
					}//end else-------------------->
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}else{
			$('#checkspan').remove();
			$('#id_li').append("<span id=\"checkspan\">아이디를 입력하세요.</span>")
			$('#checkspan').css('color', 'red');
			$('#id').focus();
			check_stat = false;
		}
	});/*------------------------- END 아이디 중복검사 ----------------------------*/
	
	$("#insertmember").click(function(){
		checkIt();
		
		if(status){
			var jsonv = {id : $("#id").val(), passwd : $("#passwd").val(),
					name : $("#name").val(), addr : $("#addr").val(), tel : $("#tel").val()
			};/*-------------------- END JSON ---------------------*/
				
			$.ajax({
				type:"POST",
				url:"/Hyundai2/InsertMember",//학원 Hyundai2  집 study
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.	
					if(data == 1){
						alert("회원가입 성공! 로그인 해주세요.");
						window.location.assign("index.jsp");
					}else{ alert("회원가입 실패 나중에 다시 시도해 주세요.");}
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}/*--------------------- END IF --------------------------*/
	});/*------------------------- END 회원가입버튼 ----------------------------*/
});/*------------------------------------ END 익명함수 ---------------------------------- */	

function checkIt(){//회원가입 폼 입력 체크
	status = true;
	if(!$.trim($("#id").val())){
		alert("아이디를 입력하세요.");			
		$("#id").focus();
		status = false;//스테이터스의 값을 바꿔줌으로써 ajax함수로의 진입을 막는다.
		return false;//리턴값을 false를 주면 함수가 종료되면서 밑의 코드를 진행 안한다.
	}
	if(!check_stat){
		alert("아이디 중복검사를 하세요.");
		status = false;
		return false;
	}
	if(!$.trim($("#passwd").val())){
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