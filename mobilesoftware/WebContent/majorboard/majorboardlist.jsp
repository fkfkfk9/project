<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- css파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/menu02.css">
<link rel="stylesheet" type="text/css" href="/mobilesoftware/css/common.css">

<img src="/mobilesoftware/img/menu01/menu01_img.png" border="0" width="1500" height="200">

<!-- 왼쪽메뉴와 컨텐츠 부분-->
<div id="sub_body1">
  <div id="menu02_left_menu">
	<jsp:include page="./menu02_leftmenu.jsp" flush="false" />	          	
  </div><!-- end of Left_menu -->  
  <div id = "menu02_content">
  	<div id="title">
  		<c:if test="${empty grade}"><!-- 학년 정보가 없다면 메인페이지로 이동 -->
  			<meta http-equiv="Refresh" content="0;url=/mobilesoftware/index.do">
  		</c:if>
  		<!---------- request로 보낸 grade와 sub에 따라 타이틀 변경 ---------->
  		<c:if test="${grade=='1학년' && sub=='C언어'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub01.png"><hr/>
		</c:if>
		<c:if test="${grade=='1학년' && sub=='컴퓨터 구조'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub02.png"><hr/>
		</c:if>
		<c:if test="${grade=='2학년' && sub=='JAVA'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub03.png"><hr/>
		</c:if>
		<c:if test="${grade=='2학년' && sub=='알고리즘'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub04.png"><hr/>
		</c:if>		
		<c:if test="${grade=='3학년' && sub=='운영체제'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub05.png"><hr/>
		</c:if>
		<c:if test="${grade=='3학년' && sub=='네트워크'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub06.png"><hr/>
		</c:if>
		<c:if test="${grade=='4학년'}">
			<img src="/mobilesoftware/img/menu02/menu02_sub07.png"><hr/>
		</c:if>	
	</div>		
	<form  name="board_form" method="post" action="list.php?mode=search"> 
		<div id="list_search">
			<div id="list_search1">▷ 총 ${articleCnt } 개의 게시물이 있습니다.  </div>
			<div id="list_search2"><img src="/mobilesoftware/img/menu02/menu02_select.png"></div><br>
			<div id="list_search3">
				<select name="find">
                   <option value='subject'>제목</option>
                   <option value='content'>내용</option>
                   <option value='name'>이름</option>
				</select></div>
			<div id="list_search4"><input type="text" name="search"></div>
			<div id="list_search5"><input type="image" src="/mobilesoftware/img/menu02/menu02_button01.png"></div>
		</div><!-- end of list_search -->
	</form>			
	<!-- <div class="clear"></div> -->
	<c:if test="${articleCnt == 0}"><!-- 데이터가 하나도 없을 경우 출력문 -->
	<div id="list_top_title">
		<table>
			<thead>
				<tr>
					<th id ="lst01_th"><img src="/mobilesoftware/img/menu02/menu02_list01.png"></th>
					<th id ="lst02_th"><img src="/mobilesoftware/img/menu02/menu02_list02.png"></th>
					<th id ="lst03_th"><img src="/mobilesoftware/img/menu02/menu02_list03.png"></th>
					<th id ="lst04_th"><img src="/mobilesoftware/img/menu02/menu02_list04.png"></th>
					<th id ="lst05_th"><img src="/mobilesoftware/img/menu02/menu02_list05.png"></th>
				</tr>
			</thead>
			<tbody>
				<tr><td></td><td><p>게시판에 저장된 글이 없습니다.</p></td></tr>
			</tbody>
		</table>
	</div>
  	</c:if>
   	<c:if test="${articleCnt != 0}"><!-- 데이터가 있을경우 데이터를 출력해준다. -->
	<div id="list_top_title">
		<table>
			<thead>
				<tr>
					<th id ="lst01_th"><img src="/mobilesoftware/img/menu02/menu02_list01.png"></th>
					<th id ="lst02_th"><img src="/mobilesoftware/img/menu02/menu02_list02.png"></th>
					<th id ="lst03_th"><img src="/mobilesoftware/img/menu02/menu02_list03.png"></th>
					<th id ="lst04_th"><img src="/mobilesoftware/img/menu02/menu02_list04.png"></th>
					<th id ="lst05_th"><img src="/mobilesoftware/img/menu02/menu02_list05.png"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articleList}">		
				<tr>
				<td class="list_td">${article.mBnum }</td>
				<td><a href="view.do?num=${article.mBnum}&sub=${sub}">${article.subject }</a></td>
				<td class="list_td">${article.name }</td>
				<td class="list_td">${article.regist_day}</td>
				<td class="list_td">${article.hit }</td>		
				</tr>
				</c:forEach>
			</tbody>
		</table>		
	</div><!-- end of list_top_title -->
	<div class="pagingbtn">				
		<ul class="pagination">
			<c:if test="${pageBtn.prev}">
				<li><a href="${pageBtn.startPage - 1}">이전</a></li>
			</c:if>
			<c:forEach begin="${pageBtn.startPage }"
				end="${pageBtn.endPage }" var="idx">
				<li
					<c:out value="${pageBtn.paging.pageNum == idx?'class =active':''}"/>>
					<a href="${idx}">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageBtn.next && pageBtn.endPage > 0}">
				<li><a href="${pageBtn.endPage +1}">다음</a></li>
			</c:if>
		</ul><!-- end of  pagination-->
	</div><!-- end of  pagingbtn-->
	</c:if>	
  </div><!-- end of menu02_content -->
</div><!-- end of sub_body1 -->  

	<form id="PagingForm">
	  <input type='hidden' name="pageNum" value='${pageBtn.paging.pageSize}'>
	  <input type='hidden' name="pageSize" value='${pageBtn.paging.pageSize}'>
	</form>
	<!-- End of form 이후 자바스크립트에서 변화를 준다. -->
	<!-- 페이지 버튼 클릭시 진행될 내용 -->
	<script>
		$('.active').css('background', '#bfbfff');
		var result = '${msg}';
	
		if (result == 'SUCCESS') {
			alert("처리가 완료되었습니다.");
		}
		
		$(".pagination li a").on("click", function(event){
			
			event.preventDefault(); 
			
			var targetPage = $(this).attr("href");
			
			var pageingForm = $("#PagingForm");
			pageingForm.find("[name='pageNum']").val(targetPage);
			pageingForm.attr("action","/board/listAll").attr("method", "get");
			pageingForm.submit();
		});	
	</script>
