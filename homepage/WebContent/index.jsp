<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>호서대학교 모바일 소프트웨어</title>
		<link rel="stylesheet" type="text/css" href="css/common.css">
	</head>
	<body>
		<div id="wrap">
			<!-- 사진2개, 로그인, 회원가입 -->						
			<div id="header">
				<jsp:include page="./lib/top_login1.jsp" flush="false" />
			</div>  <!-- end of header -->
			
			<!-- 위 메뉴 6가지 -->
			<div id="menu">
				<jsp:include page="./lib/top_menu1.jsp" flush="false" />
			</div>  <!-- end of menu --> 
			
			<!-- 중앙에 사진 -->
			<div id="content">	
			<!--<iframe src="./slide/iframe_index.html" frameborder="0" width="1500" height="380"" 
			scrolling="no" seamless></iframe>-->
				<div id="main_img"><img src="./img/main_img.png" width="100%" height="350"></div>
			</div> <!-- end of content -->
			
			<!-- 아래 side_menu(공지사항, 포토갤러리 등) -->
			<div id = "center">	
				<jsp:include page="./side_menu/update.jsp" flush="false" />
			</div><!-- end of center -->
			
			<!-- 가장 아래 주소와 페이지이동 form -->	
			<div id = "bottom">		
				<jsp:include page="./bottom/bottom.jsp" flush="false" />
			</div> <!-- end of bottom -->			
		</div><!-- END of wrap -->
	</body>
</html>
