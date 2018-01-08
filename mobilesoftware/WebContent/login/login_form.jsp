<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 스크립트 불러오기 -->
<script src="/mobilesoftware/js/login/login_form.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/login.css">
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">
<div id="content"><!-- css작업을 하기위해 만듬 -->
	<div id="col1">
	</div> <!-- end of col1 -->
	<div id="col2">
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
								id="id" autofocus required></li>
							<li><input type="password" name="pass" id="pass"
								class="login_input" required></li>
						</ul>
					</div><!-- end of id_pw_input -->
					<div id="login_button">
						<br>
						<input type="image" id="login_btn" src="../img/login/login_button.png">
					</div><!-- end of login_button -->
				</div><!-- end of id_input_button -->
				<div class="clear"></div>
				<div id="login_line"></div>
			</div><!-- end of login2 -->
		</div><!-- end of login_form -->	
	</div><!-- end of col2 -->
</div><!-- end of content -->
