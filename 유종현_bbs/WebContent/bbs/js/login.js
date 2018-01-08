var status = true;

$(document).ready(function(){
	
	//회원가입 버튼 클릭시 익명함수
	$('#register').click(function(){
		$("#contents").load("FormJsp/registerForm.jsp");
	});//END Register ---------------------------------------------------->
		
	$('#login').click(function(){//login이라는 id를 가지고 있는것을 클릭하였을때 작동
		checkIt();
		
		if(status){
			//loginPro.jsp 파일을 ajax를 사용하여 요청하기위한 작업
			//아이디와 암호를 json으로 만들어 전송한다.
			var jsonv = {id : $("#id").val(), passwd : $("#passwd").val()};
			//json문법표현 var 변수명 = {key : value, key : value };			
			$.ajax({
				type:"POST",
				url:"/Hyundai2/LoingCheck",//학원 Hyundai2  집 study
				data:jsonv,
				success:function(data){//data는 리턴값 변수 서블릿의의 결과를 받는다.					
					if(data == 1){
						$("#contents").load("FormJsp/loginForm.jsp");
						//로그인이 성공한다면 #main_auth위치에 loginForm.jsp을 출력한다.
					}else if(data == 0){
						alert("비밀번호가 틀렸습니다.");
						$("#passwd").val("");//val()은 값을 가져오지만 val("")는 내용을지움
						$("#passwd").focus();
					}else if(data == -1){
						alert("아이디가 틀렸습니다.");
						$("#id").val("");//val()은 값을 가져오지만 val("")는 내용을지움
						$("#id").focus();
					}//end else-------------------->
				}//end 리턴함수 -------------------------------->
			});//end ajax---------------------------------------->
		}//end if------------------------------------------------------>
	});//end 로그인 클릭----------------------------------------------------->
});//end 익명함수----------------------------------------------------------------->

function checkIt(){//아이디 암호 입력 체크
	status = true;
	if(!$.trim($("#id").val())){//'"구분없이 사용가능 val()는 #id의값
		//즉 id라는 id를 가진 곳의 값을 가져온다.
		alert("아이디를 입력하세요.");		
		$("#id").focus();
		status = false;//스테이터스의 값을 바꿔줌으로써 ajax함수로의 진입을 막는다.
		return false;//리턴값을 false를 주면 함수가 종료되면서 밑의 코드를 진행 안한다.
	}
	if(!$.trim($("#passwd").val())){
		alert("비민번호를 입력하세요.");		
		$("#passwd").focus();
		status = false;
		return false;
	}	
}