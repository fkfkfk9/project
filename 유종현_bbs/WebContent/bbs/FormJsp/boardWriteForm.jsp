<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<!-- js 파일 불러오기 본인의 프로젝트명을 넣는다. -->
<script src="/Hyundai2/bbs/js/boardWrite.js"></script>
<% 
//제목글의 경우 갖는 값
int num=0,ref=1,re_step=0,re_level=0; 
int pageNum = 1;

try{//댓글의 경우 갖는 값 
   if(request.getParameter("num")!=null){//댓글
     //제목글의 글번호,그룹화번호, 그룹화내의 순서, 들여쓰기 정도가
     //freeBoard.jsp페이지에서 넘어옴
     num=Integer.parseInt(request.getParameter("num"));
	 ref=Integer.parseInt(request.getParameter("ref"));
	 re_step=Integer.parseInt(request.getParameter("re_step"));
	 re_level=Integer.parseInt(request.getParameter("re_level"));
	 pageNum=Integer.parseInt(request.getParameter("pageNum"));
   }//END IF-------------------------------------------------->
%>
<!-- DB서버로 보내야 하기 때문에 히든으로 값을 넣어 보낸다. -->
<input type="hidden" id="num" value="<%=num%>">
<input type="hidden" id="ref" value="<%=ref%>">
<input type="hidden" id="re_step" value="<%=re_step%>">
<input type="hidden" id="re_level" value="<%=re_level%>">
<div class="formwrite">
	 <h2>자유게시판 글쓰기</h2>
     <ul id="write_form_ul">
        <li id="li_subject">
        	<label for="subject"><span class="spancss">제목</span></label>
            <input id="subject" name="subject" type="text" 
              size="50" placeholder="글의 제목" maxlength="50">
        </li>
        <li>
        	<label for="content"><span class="spancss">내용</span></label>
            <textarea id="content" rows="13" cols="50"></textarea>
        </li>        
        <li>
        	<label for="passwd"><span class="spancss">비밀번호</span></label>
          	<input id="passwd" name="passwd" type="password" 
           	size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>        
        <li id="write_btn">
          <img src="/Hyundai2/bbs/img/button_save.gif"  id="writecontents">
          <img src="/Hyundai2/bbs/img/button_reset.gif" id="cancle">
          <input type="hidden" id="hidden_pageNum" name="hidden_pageNum" value="<%=pageNum %>"> 
     	</li>
     </ul>
  </div>
 <%}catch(Exception e){}%>     