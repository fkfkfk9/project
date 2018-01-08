var i = 1;
$(document).ready(function(){
	$("#goodsForm").ajaxForm({
		success: function(data, status){
			$("#goodsInfo").html(data + "<br>");			
   		}
    });
	$("#addBtn").click(function(){
		i++;
		var appChild = "<input type='file' id='file2' name='file2'>";
		$("#file_li").append(appChild);
	});
});//END 익명함수 --------------------------------------------->