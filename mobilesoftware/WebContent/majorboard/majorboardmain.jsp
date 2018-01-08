<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- css파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/menu02.css">
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">

<img src="/mobilesoftware/img/menu01/menu01_img.png" border="0" width="1500" height="200">

<!-- 왼쪽메뉴와 컨텐츠 부분-->
<div id="sub_body1">
       <div id="menu02_left_menu">
		<jsp:include page="./menu02_leftmenu.jsp" flush="false" />	          	
       </div><!-- end of Left_menu -->  
		 
	<div id = "menu02_content">           
		<img src = "/mobilesoftware/img/menu02/menu02_gr01.png"><BR><hr/>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=1&sub=1">C언어</a><br>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=1&sub=2">컴퓨터 구조</a><br>
		<BR><BR>
		<img src = "/mobilesoftware/img/menu02/menu02_gr02.png"><BR><hr/>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=2&sub=1">JAVA</a><br>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=2&sub=2">알고리즘</a><br>
		<br><br>
		<img src = "/mobilesoftware/img/menu02/menu02_gr03.png"><BR><hr/>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=3&sub=1">운영체제</a><br>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=3&sub=2">네트워크</a><br>
		<br><br>
		<img src = "/mobilesoftware/img/menu02/menu02_gr04.png"><BR><hr/>
		<a href = "/mobilesoftware/menu02/majorboard.do?grade=4&sub=1">졸업 프로젝트</a><br>
    </div><!-- end of menu02_content -->  
</div><!-- end of sub_body1 -->  