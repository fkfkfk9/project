<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 중앙에 사진 -->			
<div id="content"><!-- 다시 메인페이지를 열때 경로문제로 절대경로를 사용 main_img, side_menu 전부 적용된다. -->
	<div id="main_img"><img src="/mobilesoftware/img/main_img.png" width="100%" height="350"></div>
</div> <!-- end of content -->

<!-- 아래 side_menu(공지사항, 포토갤러리 등) -->
<div id = "center">	
	<jsp:include page="side_menu/update.jsp" flush="false" />
</div><!-- end of center -->