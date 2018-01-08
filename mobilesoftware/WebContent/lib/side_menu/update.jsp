<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 	
	side_menu에 Update 라고 적힌 이미지 파일 정의와 side_menu의 기능이 적힌 jsp파일 include.
-->

<div id = "update_img"><img src = "/mobilesoftware/img/side_menu/update_text.png"></div>

<div id = "side_menu">
	<div id = "side1">
		<jsp:include page="side1.jsp" flush="false" />		
	</div>
	<div id = "side2">
		<jsp:include page="side2.jsp" flush="false" />			
	</div>
	<div id = "side3">
		<jsp:include page="side3.jsp" flush="false" />			
	</div>
	<div id = "direct">
		<jsp:include page="direct.jsp" flush="false" />				
	</div>
</div>