<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>호서대학교 모바일 소프트웨어</title>
		<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
		<link href="../css/login.css" rel="stylesheet" type="text/css" media="all">
	</head>
	<body>
		<div id="wrap">
			<!-- 사진2개, 로그인, 회원가입 -->						
			<div id="header">
				<jsp:include page="../lib/top_login1.jsp" flush="false" />
			</div>  <!-- end of header -->
			
			<!-- 위 메뉴 6가지 -->
			<div id="menu">
				<jsp:include page="../lib/top_menu1.jsp" flush="false" />
			</div>  <!-- end of menu --> 
			
			<div id = "content">
				<div id="col1">
				</div> <!-- end of col1 -->
				
				<div id="col2">
				<form name="member_form" method="post" action="login.jsp">
					<div id="title">
						<h2><img src="../img/login/title_login.gif"></h2>
					</div><!-- end of title -->

					<div id="login_form">
						<h3><img src="../img/login/login_msg.png"></h3>
						<div class="clear"></div>
						<div id="login1">
							<img src="../img/login/login_key.png">
						</div><!-- end of login1 -->
						<div id="login2">
							<div id="id_input_button">
								<div id="id_pw_title">
									<ul>
										<li><img src="../img/login/id_title.png"></li>
										<li><img src="../img/login/pw_title.png"></li>
									</ul>
								</div><!-- end of id_pw_title -->
								<div id="id_pw_input">
									<ul>
										<li><input type="text" name="id" class="login_input"
											autofocus required></li>
										<li><input type="password" name="pass"
											class="login_input" required></li>
									</ul>
								</div><!-- end of id_pw_input -->
								<div id="login_button">
									<br>
									<input type="image" src="../img/login/login_button.png">
								</div><!-- end of login_button -->
							</div><!-- end of id_input_button -->
							<div class="clear"></div>
							<div id="login_line"></div>
						</div><!-- end of login2 -->
					</div><!-- end of login_form -->
				</form><!-- end of form_login -->
			</div><!-- end of col2 -->
			</div><!-- end of content -->						
		</div><!-- END of wrap -->
	</body>
</html>
