<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>fkfkfk9 Blog</title>  
</head>
<body>
    <!-- 레이아웃이 들어갈 블럭을 만든다. -->
    <div id="container">
        <!-- 시맨틱 태그로 분류해준다. -->
        <!-- 제목 부분 -->
        <header>
            <jsp:include page="./include/header.jsp" flush="false" />
        </header>
        <!-- 네비게이션 메뉴 부분이다. -->
        <nav>            
            <jsp:include page="./include/nav.jsp" flush="false" />
        </nav>
        <script>
            $('#select').css('background', '#f6f8c5');
        </script>
        <!-- 본문 부분 -->
        <section>
            <!-- 본문의 내용이 들어간다. JQuery에서 load되는 부분-->
            <article id ="contents">
            	<h2>${sessionScope.id }님 환영합니다. </h2>
				  <p>HTML&CSS, JAVASCRIPT&JQUERY, ORACLE, JAVA는 현재 서울현대직업전문학교에서 교육받으며 실습한 코드</p>
				  <br /><br />
				  <p>HTML&CSS는 웹 표준에 맞는 웹페이지 설계와 기본적인 디자인을 실습</p>
				  <br /><br />
				  <p>JAVASCRIPT&JQUERY는 웹페이지의 이벤트를 제어할 수 있는 기능 실습</p>
				  <br /><br />
				  <p>ORACLE은 DB에 대한 기본적인 내용과 SQL구문, PL_SQL구문을 실습</p>
				  <br /><br />
				  <p>JAVA는 기본적인 자바 문법과 이클립스 사용법, 객체지향 프로그래밍에 대한 내용을 실습</p>         
            </article>
        </section>
        <!-- 사이트의 정보를 적어준다. -->        
        <footer>
            <jsp:include page="./include/footer.jsp" flush="false" />
        </footer>
    </div>
</body>
</html>
