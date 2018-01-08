var status = true;

$(document).ready(function(){
	$("#delete").click(function(){
		checkIt();
		
		if(status){
			var pageNum = $("#delete").val();
			var jsonv = {
					passwd:$("#passwd").val(),
				    num:$("#num").val(),
				    ref:$("#ref").val()
			};//END JSON---------------------->
			
			$.ajax({
	  		     type: "POST",
	  		     url: "/Hyundai2/DeleteBoard",
	  		     data: jsonv,
	  		     success: function(data){
	  		    	 if(data == 1){//글추가 성공
	  		    		 alert("글이 삭제되었습니다.");
	  		    		 var query = "Jsp/freeBoard.jsp?pageNum="+pageNum+"&";
	  		    		 $("#contents").load(query);
	  		         }else{
	  		        	 alert("암호가 일치하지 않습니다.");
	  		        	 $("#passwd").focus();
	  		         }
	  		     }//END 리턴함수
	  		  });//END AJAX---------------------------------------->
		}//END IF status-------------------------------------------->
	});//삭제버튼 클릭이벤트 --------------------------------------------------->
	
	$("#d_cancle").click(function(){
		var pageNum = $("#d_cancle").val();
    	var query = "Jsp/freeBoard.jsp?pageNum="+pageNum;
		$("#contents").load(query);
	});//취소버튼 클릭이벤트 --------------------------------------------------->
});//end 익명함수----------------------------------------------------------------->

function checkIt(){//글쓰기 폼 입력 체크
	status = true;	
	if(!$.trim($("#passwd").val())){
		alert("비밀번호를 입력하세요.");		
		$("#passwd").focus();
		status = false;
		return false;
	}
}