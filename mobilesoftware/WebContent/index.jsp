<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>호서대학교 모바일 소프트웨어</title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">
		<script src="/mobilesoftware/js/jquery-3.2.1.min.js"></script>
	</head>
	<body>
		<div id="wrap">
			<!-- 사진2개, 로그인, 회원가입 로그인시 변경 -->						
			<header>
				<jsp:include page="./lib/top_login1.jsp" flush="false" />
			</header>  <!-- end of header -->
			
			<!-- 위 메뉴 6가지 -->
			<nav>
				<jsp:include page="./lib/top_menu1.jsp" flush="false" />
			</nav>  <!-- end of menu --> 
			
			<!-- 본문 매번 바뀌는 부분 -->
			<section>		
				<jsp:include page="${cont}"/>
			</section><!-- end of section -->
			
			<!-- 가장 아래 주소와 페이지이동 form -->	
			<footer>		
				<jsp:include page="./lib/bottom.jsp" flush="false" />
			</footer> <!-- end of footer -->			
		</div><!-- END of wrap -->
	</body>
</html>
