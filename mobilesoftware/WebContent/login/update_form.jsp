<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 스크립트 불러오기 -->
<script src="/mobilesoftware/js/login/update_form.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/member.css">
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">

<c:if test="${sessionScope.m_level < 3 && !empty sessionScope.id}"><!-- 관리자가 아닌사람, 로그인된 사람만 가능 -->			
<div id="content">
	<div id="col1">		
	</div> <!-- end of col1 -->

	<div id="col2">        
		<div id="title">
			<h3>회원정보수정</h3>
		</div>

		<div id="form_join">
			<div id="join1">
				<ul>
				<li>* 아이디</li>
				<li>* 기존 비밀번호</li>
				<li>* 새 비밀번호</li>
				<li>* 이름</li>
				<li>* 휴대폰</li>
				<li>&nbsp;&nbsp;&nbsp;이메일</li>
				<li>* 직책</li>
				</ul>
			</div><!-- end of join1 -->
			
			<div id="join2">
				<ul><!-- 회원가입폼을 그대로 가져와 조금만 수정하여 사용 -->
					<li><div id="id1"><input type="text" disabled="disabled" value="${sessionScope.id }"></div>
					<!-- 아이디는 바꿀 수 없기 때문에 보여주기만 하고 값은 히든으로 넣어준다. -->
					<input type="hidden" id="id" name="id" value= ${sessionScope.id } ></li>									
					<li id="li_pass"><input type="password" name="pass" id="pass"></li>
					<!-- 암호란에 jquery를 사용하기 위해 li에 id를 부여 -->
					<li><input type="password" name="pass_confirm" id="pass_confirm"></li>
					<li><input type="text" name="name" id="name" value="${name }"></li>
					<li><input type="text" class="hp" name="hp1" id="hp1" value="${hp1 }"> 
		               - <input type="text" class="hp" name="hp2" id="hp2" value="${hp2 }">
		               - <input type="text" class="hp" name="hp3" id="hp3" value="${hp3 }"></li>
					<li><input type="text" id="email1" name="email1" value="${email1 }"> 
					@ <input type="text" id="email2" name="email2" value="${email2 }"></li>
					<li><input type="text" value="${m_level_val }" disabled="disabled"></li>
				</ul>
			</div><!-- end of join2 -->
			
			<div class="clear"></div>
			<div id="must"> * 는 필수 입력항목입니다.^^</div>
			
		</div><!-- end of form_join -->

		<div id="button"><img id="memberinsert" src="../img/member/button_save.gif" />&nbsp;&nbsp;
		                 <a href="/mobilesoftware/index.do"><img src="../img/member/button_reset.gif" /></a>
		</div>	    
	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</c:if>
<c:if test="${sessionScope.m_level == 3 || empty sessionScope.m_level || empty sessionScope.id}">
<!-- 관리자인 경우, 세션에 데이터가 없을 경우 -->
	<meta http-equiv="Refresh" content="0;url=/mobilesoftware/index.do">
</c:if>