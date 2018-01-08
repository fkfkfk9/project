<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>    
<!-- 회원가입 페이지에서 사용되는 스크립트를 불러온다. 학원 /Hyundai2/ 집 /study/-->
<script src="/Hyundai2/bbs/js/register.js"></script>

  <div class="formjoin">
     <ul>
     	<li><h2>회원가입</h2></li>
        <li id="id_li">
        	<label for="id"><span class="spancss">아이디</span></label>
            <input id="id" name="id" type="email" size="20" 
              maxlength="50" placeholder="fkfkfk9@naver.com">            
        </li>
        <li>
        	<label for="passwd"><span class="spancss">비밀번호</span></label>
            <input id="passwd" name="passwd" type="password" 
              size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        </li>
        <li>
        	<label for="name"><span class="spancss">이름</span></label>
            <input id="name" name="name" type="text" 
              size="20" placeholder="ex) 홍길동" maxlength="16">
        </li>
        <li>
        	<label for="addr"><span class="spancss">주소</span></label>
            <input id="addr" name="addr" type="text" 
              size="30" placeholder="ex) 경기도 안양시 동안구 관양동" maxlength="30">
        </li>
        <li>
        	<label for="tel"><span class="spancss">전화번호</span></label>
            <input id="tel" name="tel" type="text" 
              size="20" placeholder="ex) 010-111-2222" maxlength="20">
        </li>
        <li>
            <span></span><button id="insertmember">회원가입</button>
     	</li>
     </ul>
  </div>

    