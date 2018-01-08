$(document).ready(function(){
	
	$('#write_img_btn').click(function(){		
		$("#contents").load("FormJsp/boardWriteForm.jsp");
	});
});//end 익명함수----------------------------------------------------------------->
/*
	댓글, 수정, 삭제가 클릭이벤트 처리를 안하고 함수를 이용하여 각각의 폼에서 온클릭으로 작동하는것은
	현재 태그에 id가 있다고 해도 for문을 이용하여 같은 id로 10개를 찍기 때문에 정확한 id를 선택해서
	가져오는것이 불분명하다. 그렇기 때문에 각각의 태그에서 직접 onclick를 해준다.
*/
function reply(replyBtn){//자유게시판 댓글쓰기 버튼 클릭시
	var rStr = replyBtn.name;//name에 넣어둔 값들을 받아온다.
	var arr = rStr.split(",");//,를 제외하고 배열화 한다.
	//댓글쓰기에 필요한 정보를 갖고 writeForm.jsp 페이지 로드
	var query = "FormJsp/boardWriteForm.jsp?num="+arr[0]+"&ref="+arr[1];
	query += "&re_step="+arr[2]+"&re_level="+arr[3]+"&pageNum="+arr[4];
	$("#contents").load(query);
}

function edit(editBtn){//자유게시판에서 수정버튼 클릭시
	var rStr = editBtn.name;//버튼에 넣어놓은 name값을 받아온다.
	var arr = rStr.split(",");//,를 기준으로 , 빼고 배열화
	//글번호와 페이지번호를 갖고 updateForm.jsp 페이지 로드
	var query = "FormJsp/boardUpdateForm.jsp?num="+arr[0];
	query += "&pageNum="+arr[1];
	$("#contents").load(query);
}

function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "FormJsp/boardDeleteForm.jsp?num="+arr[0];
	query += "&pageNum="+arr[1]+"&ref="+arr[2];
	$("#contents").load(query);
}

//페이지 이동 버튼을 누르면 자동으로 실행
//페이지 버튼마다 onclick를 설정 해놔 실행 가능
function numberBtn(jumpBtn){
	var rStr = jumpBtn.name;//해당 버튼의 네임을 가져옴
	var query = "Jsp/freeBoard.jsp?pageNum="+rStr;
	//네임을 변수로 붙여 주소로 전송시켜 DB의 값을 다르게 불러올 수 있다.
	$("#contents").load(query);
}
