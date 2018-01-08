var status = true;

$(document).ready(function(){
	
	$("#updatecontents").click(function(){
		checkIt();
		
		if(status){
			var pageNum = $('#hidden_pageNum').val();
			var query = {
					   subject:$("#subject").val(),
				       content:$("#content").val(),
				       passwd:$("#passwd").val(),				       
				       num:$("#num").val()
			};//END JOSN------------------------------->
			$.ajax({
	  		     type: "POST",
	  		     url: "/Hyundai2/UpdateBoard",
	  		     data: query,
	  		     success: function(data){
	  		    	 if(data == 1){//글수정 성공
	  		    		 alert("글이 수정되었습니다.");
	  		    		 var query = "Jsp/freeBoard.jsp?pageNum="+pageNum;
	  		    		 $("#contents").load(query);
	  		         }else if(data == 0){	  		        	 
	  		        	 alert("암호가 틀렸습니다. 다시 입력해주세요.");
	  		        	 $("#passwd").val("");
	  		        	 $("#passwd").focus();
	  		     	 }else alert("죄송합니다. 서버에 문제가 생겼습니다.");
	  		     }
	  		  });//END AJAX---------------------------------------->
		}//END IF------------------------------------------------->
	});//저장버튼 클릭이벤트 --------------------------------------------------->	
	
	$("#u_cancle").click(function(){
		var pageNum = $("#hidden_pageNum").val();
    	var query = "Jsp/freeBoard.jsp?pageNum="+pageNum;
		$("#contents").load(query);
	});//취소버튼 클릭이벤트 --------------------------------------------------->
});

function checkIt(){//글쓰기 폼 입력 체크
	status = true;
	if(!$.trim($("#subject").val())){
		alert("제목을 입력하세요.");		
		$("#subject").focus();
		status = false;//스테이터스의 값을 바꿔줌으로써 ajax함수로의 진입을 막는다.
		return false;//리턴값을 false를 주면 함수가 종료되면서 밑의 코드를 진행 안한다.
	}	
	if(!$.trim($("#content").val())){
		alert("내용을 입력하세요.");		
		$("#content").focus();
		status = false;
		return false;
	}
	if(!$.trim($("#passwd").val())){
		alert("비밀번호를 입력하세요.");		
		$("#passwd").focus();
		status = false;
		return false;
	}
}