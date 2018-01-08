$(document).ready(function(){
	$('#logout').click(function(){
		$("#contents").load("Jsp/logOut.jsp");
	});
	
	$('#customer').click(function(){
		$("#contents").load("FormJsp/updateForm.jsp");
	});
	
	$('#notice').click(function(){
		$("#contents").load("Jsp/noticeBoard.jsp");
	});
	
	$('#freeboard').click(function(){
		window.location.assign("/board/listAll");
	});
});//end 익명함수----------------------------------------------------------------->