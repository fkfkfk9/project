<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>fkfkfk9 Blog</title>  
    <script src="/resources/js/create.js"></script>
</head>
<body>
    <!-- 레이아웃이 들어갈 블럭을 만든다. -->
    <div id="container">
        <!-- 시맨틱 태그로 분류해준다. -->
        <!-- 제목 부분 -->
        <header>
            <jsp:include page="../include/header.jsp" flush="false" />
        </header>
        <!-- 네비게이션 메뉴 부분이다. -->
        <nav>            
            <jsp:include page="../include/nav.jsp" flush="false" />
        </nav>
        <script>
            $('#select').css('background', '#f6f8c5');
        </script>
        <!-- 본문 부분 -->
        <section>
            <!-- 본문의 내용이 들어간다. JQuery에서 load되는 부분-->
            <article id ="contents">
            <form role="form" method="POST">            
            	<div class="formwrite">
					 <h2>자유게시판 글쓰기</h2>
				     <ul id="write_form_ul">
				        <li id="li_subject">
				        	<label for="subject"><span class="spancss">제목</span></label>
				            <input id="subject" name="subject" type="text" 
				              size="50" placeholder="글의 제목" maxlength="50">
				        </li>
				        <li>
				        	<label for="writer"><span class="spancss">글쓴이</span></label>
				            <input id="writer" name="writer" type="text" 
				              size="20" placeholder="ID" maxlength="20">
				        </li>
				        <li>
				        	<label for="content"><span class="spancss">내용</span></label>
				            <textarea id="content" name="content" rows="13" cols="50"></textarea>
				        </li>        
				        <li>
				        	<label for="passwd"><span class="spancss">비밀번호</span></label>
				          	<input id="passwd" name="passwd" type="password" 
				           	size="20" placeholder="6~16자 숫자/문자" maxlength="16">
				        </li>        
				        <li id="write_btn">
				          <img src="/resources/img/button_save.gif" id="createform">
				          <script>
				          $(document).ready(function(){
				        		var formObj = $("form[role='form']");
				        		$("#createform").click(function() {
				        			formObj.submit();
				        		});
				        	});
				          </script>
				          <a href="/board/listAll?pageNum=${paging.pageNum}&pageSize=${paging.pageSize}
				          &searchType=${paging.searchType}&keyword=${paging.keyword}">
				          <img src="/resources/img/button_reset.gif"></a>
				          <%-- <input type="hidden" id="hidden_pageNum" name="hidden_pageNum" value="<%=pageNum %>">  --%>
				     	</li>
				     </ul>
				  </div>
			</form>
            </article>
        </section>
        <!-- 사이트의 정보를 적어준다. -->        
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
    </div>
</body>
</html>