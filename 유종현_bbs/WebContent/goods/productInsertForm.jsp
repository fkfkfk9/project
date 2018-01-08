<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 등록</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<!-- js파일 불러오기 -->
<script src="../js/jquery.form.min.js"></script> 
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="goods.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" href="style.css"/>
<meta charset="UTF-8">
</head>
<body>
	<h1>제품등록 페이지</h1>
	<form action="productRegisterOk.jsp" id="goodsForm"
		method="post" enctype="multipart/form-data">
	<ul><!-- 제품등록페이지 전체범위 -->			
		<!-- 제품코드 -->
		<li>
			<label for="p_id"><span class="spancss">제품코드 : </span></label>
            <input id="p_id" name="p_id" type="text" 
              size="20" placeholder="1~4자리 숫자" maxlength="10">
		</li>		
		<!-- 제품명 -->
		<li>
			<label for="p_name"><span class="spancss">제품명 : </span></label>
            <input id="p_name" name="p_name" type="text" 
              size="30" placeholder="8자가 넘지 않는 제품이름" maxlength="20">
		</li>		
		<!-- 제품 가격 -->
		<li>
			<label for="p_price"><span class="spancss">제품가격 : </span></label>
            <input id="p_price" name="p_price" type="text" 
              size="15" placeholder="10000" maxlength="10">원
		</li>		
		<!-- 제품 이미지 -->
		<li id="file_li">
			<p class="cau">※ 파일선택기에서 ctrl, shift키를 눌러 파일을 다중선택 하십시오.</p>
    		<label for="file1">파일선택</label>
    		<input type="file" id="file1" name="file1">
    		<input type="button" id="addBtn" name="addBtn" value="파일 추가">
		</li>		
		<!-- 전송버튼 -->
		<li>    
    		<input type="submit" id="goodsBtn" value="제품 등록">	
    	</li>		
	</ul>
	</form>
	<div id="goodsInfo"></div>
</body>
</html>