<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>    
<!-- js 파일 불러오기 -->
<script src="/Hyundai2/bbs/js/login.js"></script>
<!-- 학원 /Hyundai2/ 집 /study/ 본인 프로젝트명으로 수정 -->

<%
String id ="";
try{
	//id세션 속성의 값을 얻어내서 id변수에 저장
	//인증된 사용자의 경우  id세션 속성의 값 null또는 공백이 아님
	id = (String)session.getAttribute("id");
%>

<%if(id == null || id.equals("")){ //인증되지 않은 사용자 영역 즉 로그인이 안된경우%>
  <div class="formlogin">  
     <ul>
     	<li><h2>로그인을 하셔야 이용 가능합니다.</h2></li>
     	<li><label for="id"><span class="spancss">아이디</span></label>
            <input id="id" name="id" type="email" size="20" 
              maxlength="50" placeholder="fkfkfk9@naver.com">
        </li>      
        <li><label for="passwd"><span class="spancss">비밀번호</span></label>
            <input id="passwd" name="passwd" type="password" 
              size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>
        <li>
        	<span class="spancss"></span>
            <button id="login">로그인</button>
            <button id="register">회원가입</button>
        </li>   
     </ul>
  </div>
  <script><%-- 로그인이 되지 않을경우 고객지원 텝을 숨긴다. --%>
   $('#customer').hide();
  </script>
<%}else{//인증된 사용자 영역 즉 로그인이 되어있을 때%>
  <h2><%=id %>님 환영합니다. </h2>
  <p>HTML&CSS, JAVASCRIPT&JQUERY, ORACLE, JAVA는 현재 서울현대직업전문학교에서 교육받으며 실습한 코드</p>
  <br /><br />
  <p>HTML&CSS는 웹 표준에 맞는 웹페이지 설계와 기본적인 디자인을 실습</p>
  <br /><br />
  <p>JAVASCRIPT&JQUERY는 웹페이지의 이벤트를 제어할 수 있는 기능 실습</p>
  <br /><br />
  <p>ORACLE은 DB에 대한 기본적인 내용과 SQL구문, PL_SQL구문을 실습</p>
  <br /><br />
  <p>JAVA는 기본적인 자바 문법과 이클립스 사용법, 객체지향 프로그래밍에 대한 내용을 실습</p>
  <script><%-- 로그인이 되면 고객지원 텝을 보여준다. --%>
   $('#customer').show();
  </script>
<%}}catch(Exception e){e.printStackTrace();}%>
    