<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 스크립트 불러오기 -->
<script src="/mobilesoftware/js/login/join_form.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/member.css">
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">

<c:if test="${sessionScope.m_level == 3 && !empty sessionScope.id}"><!-- 관리자만 회원가입이 가능하다. -->			
<div id="content">
	<div id="col1">		
	</div> <!-- end of col1 -->

	<div id="col2">        
		<div id="title">
			<img src="/mobilesoftware/img/member/title_join.gif">
		</div>

		<div id="form_join">
			<div id="join1">
				<ul>
				<li>* 아이디</li>
				<li>* 비밀번호</li>
				<li>* 비밀번호 확인</li>
				<li>* 이름</li>
				<li>* 휴대폰</li>
				<li>&nbsp;&nbsp;&nbsp;이메일</li>
				<li>* 직책</li>
				</ul>
			</div><!-- end of join1 -->
			
			<div id="join2">
				<ul>
				<li id="li_id"><div id="id1"><input type="text" name="id" id="id"></div><div id="id2">4~12자의 영문 소문자, 숫자와 특수기호(_) 만 사용할 수 있습니다.</div></li>
				<li><input type="password" name="pass" id="pass"></li>
				<li id="li_pass_confirm"><input type="password" name="pass_confirm" id="pass_confirm"></li>
				<li><input type="text" name="name" id="name"></li>
				<li><select class="hp" name="hp1" id="hp1"> 
	              <option value='010'>010</option>
	              <option value='011'>011</option>
	              <option value='016'>016</option>
	              <option value='017'>017</option>
	              <option value='018'>018</option>
	              <option value='019'>019</option>
	              </select>  - <input type="text" class="hp" name="hp2" id="hp2"> - <input type="text" class="hp" name="hp3" id="hp3"></li>
				<li><input type="text" id="email1" name="email1"> @ <input type="text" id="email2" name="email2"></li>
				<li><select class="level" name="m_level" id="m_level"> 
	              <option value='1'>학생</option>
	              <option value='2'>교수</option>
	              <option value='3'>관리자</option>
	              </select>
				</li>
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
<c:if test="${sessionScope.m_level != 3 || empty sessionScope.id}"><!-- 관리자가 아닌사람이 들어왔을 경우 -->
	<meta http-equiv="Refresh" content="0;url=/mobilesoftware/index.do">
</c:if>