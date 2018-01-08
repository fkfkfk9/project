<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/mobilesoftware/js/lib/topLogin.js"></script>
	<!-- 호서대 로고 이미지  절대경로 -->
	<div id="logo"><a href="/mobilesoftware/index.do"><img src="/mobilesoftware/img/hoseo_logo.png" border="0"></a></div>
	
	<!-- 모바일 소프트웨어학과 텍스트 미이지 -->
	<div id="moto"><img src="/mobilesoftware/img/mobile_software_text.png"></div>
	
	<!-- 로그인/회원가입부분 -->
	<div id="top_login">
	<c:if test="${empty sessionScope.id}"><!-- 세션에 아이디가 없을 때 즉 로그인하기 전 -->
		<a href="/mobilesoftware/top/loginForm.do">로그인</a>		
	</c:if>
	<c:if test="${!empty sessionScope.id}"><!-- 세션에 아이디가 지정된 경우 즉 로그인 후 -->
		<a href="/mobilesoftware/top/logout.do">로그아웃 </a>&nbsp;&nbsp;&nbsp;
		<c:if test="${sessionScope.m_level == 3}"><!-- 학생의 레벨은 1 직원의 경우 레벨이 3이다. -->			
			<a href="/mobilesoftware/top/joinForm.do"> 회원가입</a><!-- 회원가입은 전공과 직원만 가능 -->
		</c:if>
		<c:if test="${sessionScope.m_level != 3}"><!-- 직원이 아닌경우 본인의 정보수정을 할 수 있다. -->			
			<a href="/mobilesoftware/top/updateForm.do"> 회원정보수정</a><!-- 회원가입은 전공과 직원만 가능 -->
		</c:if>
	</c:if><br>	
	<!-- 전체 게시판에서 검색 하는 기능 을 위한 input -->
	<!-- 누를시 /top/allSearch.do로 이동 -->
	<input type="text" id="searchtext">
	<input type="button" value="검색" id="searchBtn">
	</div>
	

