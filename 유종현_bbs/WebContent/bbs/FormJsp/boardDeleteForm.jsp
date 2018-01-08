<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<!-- js 파일 불러오기 -->
<script src="/Hyundai2/bbs/js/boardDelete.js"></script>
<%
	//freeBoard.jsp파일에서 받아온 값
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	int ref = Integer.parseInt(request.getParameter("ref"));
%>
<div class="formdelete">
	 <h2>자유게시판 글 삭제</h2>
     <ul id="write_form_ul">        
        <li>
        	<label for="passwd"><span class="spancss">비밀번호</span></label>
          	<input id="passwd" name="passwd" type="password" 
           	size="20" placeholder="6~16자 숫자/문자" maxlength="16">
           	<input type="hidden" id="num" value="<%=num%>">
           	<input type="hidden" id="ref" value="<%=ref%>">
           	<!-- 받아온 값들을 히든에 넣어서 DB서버로 보낸다. -->
        </li>        
        <li id="write_btn">
          <button id="delete" value="<%=pageNum%>">삭제</button>
          <button id="d_cancle" value="<%=pageNum%>">취소</button> 
     	</li>
     </ul>
  </div>

