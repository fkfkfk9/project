<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>fkfkfk9 Blog</title>
    <!-- 여러 디바이스에서 사용하기 위한 설정 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- css파일을 불러온다. -->
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
    <link rel="stylesheet" type="text/css" href="css/html_css_styles.css" />
    <!-- JQuery를 불러온다. 집/study/ 학원 /Hyundai2/ 본인 프로젝트명을 적는다.-->
    <script src="/Hyundai2/bbs/js/jquery-3.2.1.min.js"></script>
    <script src="/Hyundai2/bbs/js/dropdown.js"></script>
    
</head>
<body>
    <!-- 레이아웃이 들어갈 블럭을 만든다. -->
    <div id="container">
        <!-- 시맨틱 태그로 분류해준다. -->
        <!-- 제목 부분 -->
        <header>
            <h1><a href="index.jsp">fkfkfk9 CODE학습</a></h1>
        </header>
        <!-- 네비게이션 메뉴 부분이다. -->
        <nav>            
            <ul>
                <li><a id="select" href="index.jsp">HOME<br /></a></li>
                <li><a id="notice">공지사항<br /></a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">WEB</a>
                    <div class="dropdown-content">
                        <a href="#">HTML</a>
                        <a href="#">CSS</a>
                        <a href="#">JAVASCRIPT</a>
                        <a href="#">JQUERY</a>
                    </div>
                </li>                                
                <li><a id="freeboard">자유게시판<br /></a></li>
                <li class="dropdown" id="customer" >
                    <a href="javascript:void(0)" class="dropbtn">고객지원</a>
                    <div class="dropdown-content">
                        <a id="logout">로그아웃</a>
                        <a id="updatemember">회원정보변경</a>
                    </div>
                </li>       
            </ul>
        </nav>
        <script>
            $('#select').css('background', '#f6f8c5');
        </script>
        <!-- 본문 부분 -->
        <section>
            <!-- 본문의 내용이 들어간다. JQuery에서 load되는 부분-->
            <article id ="contents">
            	<jsp:include page="FormJsp/loginForm.jsp"/>                
            </article>
        </section>
        <!-- 사이트의 정보를 적어준다. -->        
        <footer>
            <p> 호서대학교 졸업 경기도 안양시 거주중 </p>
            <address>fkfkfk999@gmail.com</address>
        </footer>
    </div>
</body>
</html>
