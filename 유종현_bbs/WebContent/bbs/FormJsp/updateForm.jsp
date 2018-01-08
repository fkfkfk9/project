<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.member.bean.*" %>
<!-- 회원정보수정 페이지에서 사용되는 스크립트를 불러온다. 학원 /Hyundai2/ 집 /study/-->
<script src="/Hyundai2/bbs/js/updateDelete.js"></script>
<% request.setCharacterEncoding("utf-8");%>

<% String id = (String)session.getAttribute("id");//로그인이 되었는지 확인한다. %>
<%
	MemberDAODb mdao = MemberDAODb.getInstance();
	MemberBeanDb mbd = mdao.MemberInfo(id);
%>
<% if(id.equals("") || id == null) {//로그인이 되어있지 않은경우%>
<script>window.location.href("index.jsp");</script>
<%}else{//로그인이 되어있는경우 %>
  <div class="formupdate">
     <ul>
     	<li><h2><%=id %>님 정보수정</h2></li>
     	<li><input type="hidden" id="id" name ="id" value="<%=id %>"></li>
        <li>
        	<label for="passwd"><span class="spancss">현재 비밀번호</span></label>
            <input id="passwd" name="passwd" type="password" 
              size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>
        <li>
        	<label for="newpasswd"><span class="spancss">변경 할 비밀번호</span></label>
            <input id="newpasswd" name="newpasswd" type="password" 
              size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>        
        <li>
        	<label for="name"><span class="spancss">이름</span></label>
            <input id="name" name="name" type="text"  
              size="20" value="<%=mbd.getName() %>" maxlength="16">
        </li>
        <li><span class="spancss">가입일</span><%=mbd.getReg_date() %>
        	<input type="hidden"name ="reg_date" id = "reg_date" 
        	value="<%=mbd.getReg_date() %>">
        </li>
        <li>
        	<label for="addr"><span class="spancss">주소</span></label>
            <input id="addr" name="addr" type="text" 
              size="30" value="<%=mbd.getAddr() %>" maxlength="30">
        </li>
        <li>
        	<label for="tel"><span class="spancss">전화번호</span></label>
            <input id="tel" name="tel" type="text" 
              size="20" value="<%=mbd.getTel() %>" maxlength="20">
        </li>
        <li>
            <span class="spancss"></span><button id="updateInfo">정보변경</button>
            <button id="deletemember">회원탈퇴</button>
     	</li>
     </ul>
  </div>
<% } %>
    