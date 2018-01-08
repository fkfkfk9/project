<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.board.bean.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!-- js 파일 불러오기 -->
<script src="/Hyundai2/bbs/js/boardUpdate.js"></script>
<%
 //수정할 글의 번호와 수정할 글이 위치한 페이지 번호를 얻어냄
 int num = Integer.parseInt(request.getParameter("num"));
 String pageNum = request.getParameter("pageNum");

 BoardDAODb bdd = BoardDAODb.getInstance();
 //주어진 글번호에 해당하는 수정할 글을 가져옴 
 BoardBeanDb bbd =  bdd.updateGetInfo(num);
%>
<div class="formfreeupdate">
	 <h2>자유게시판 글 수정</h2>
     <ul id="write_form_ul">
        <li id="li_subject">
        	<label for="subject"><span class="spancss">제목</span></label>
            <input id="subject" name="subject" type="text" 
              size="50" value="<%=bbd.getSubject() %>" maxlength="50">
            <input type="hidden" id="num" value="<%=num%>">  
        </li>
        <li>
        	<label for="content"><span class="spancss">내용</span></label>
            <textarea id="content" rows="13" cols="50"><%=bbd.getContent() %></textarea>
        </li>        
        <li>
        	<label for="passwd"><span class="spancss">비밀번호</span></label>
          	<input id="passwd" name="passwd" type="password" 
           	size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>        
        <li id="write_btn">
          <img src="/Hyundai2/bbs/img/button_save.gif"  id="updatecontents">
          <img src="/Hyundai2/bbs/img/button_reset.gif" id="u_cancle">
          <input type="hidden" id="hidden_pageNum" name="hidden_pageNum" value="<%=pageNum %>"> 
     	</li>
     </ul>
  </div>
