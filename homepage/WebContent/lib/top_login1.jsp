<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 호서대 로고 이미지  절대경로 -->
	<div id="logo"><a href="/homepage/index.php"><img src="/homepage/img/hoseo_logo.png" border="0"></a></div>
	
	<!-- 모바일 소프트웨어학과 텍스트 미이지 -->
	<div id="moto"><img src="/homepage/img/mobile_software_text.png"></div>
	
	<!-- 로그인/회원가입부분. 미완성 -->
	<div id="top_login">
	
	<%
		if(true)//!$userid)
		{
			%>
				<a href="/homepage/login/login_form.jsp">로그인</a>  
			<%
		} //지울부분 미완성이라 주석과함꼐 넣음%>
		<%-- 
		else
		{
			%>
				<%=$username%> (level:<%=$userlevel%>) | 
					<a href="/homepage/login/logout.php">로그아웃</a> | <a href="./login/member_form_modify.php">정보수정</a>
					
					<%if($userlevel == 3) {%>
						|<a href="/homepage/member/member_form.php"> 회원가입</a>
					<%}%>
			<%
		}
	%>			--%>
	
	<!-- 홈페이지의 게시글 검색 form. 미완성. -->
	<form>
		<input width = "100">
	</form>
	 </div>

