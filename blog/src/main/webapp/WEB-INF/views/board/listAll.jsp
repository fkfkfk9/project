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
            	<h2>글 목록(전체 글 : ${aticleCnt}) </h2>
            	<a href="/board/create${pageBtn.makeQuery(pageBtn.paging.pageNum)}&searchType=${paging.searchType}&keyword=${paging.keyword}">
            	<img id="write_img_btn" src="/resources/img/menu02_write.png"></a>
            	<div id="search_div">
            	<select name="searchType">
					<option value="n"
						<c:out value="${paging.searchType == null?'selected':''}"/>>
						---</option>
					<option value="s"
						<c:out value="${paging.searchType eq 's'?'selected':''}"/>>
						제목</option>
					<option value="c"
						<c:out value="${paging.searchType eq 'c'?'selected':''}"/>>
						내용</option>
					<option value="w"
						<c:out value="${paging.searchType eq 'w'?'selected':''}"/>>
						작성자</option>
					<option value="sc"
						<c:out value="${paging.searchType eq 'sc'?'selected':''}"/>>
						제목 + 내용</option>
					<option value="scw"
						<c:out value="${paging.searchType eq 'scw'?'selected':''}"/>>
						모두</option>
				</select> <input type="text" name='keyword' id="keywordInput"
					value='${paging.keyword }'>
				<button id='searchBtn'>Search</button>
            	</div>
            	<c:if test="${articleCnt == 0}">
            		<div id="list_article" class="free_list">
				  		<ul><li><p>게시판에 저장된 글이 없습니다.</p></li></ul>
					</div>
            	</c:if>
            	<c:if test="${articleCnt != 0}">
            		<div id="list_article" class="free_list">
            			<c:forEach var="list" items="${list}">
            				<ul class="article_ul">
            					<li class="write_li" ><span class="writer_id">${list.num}
            					&nbsp;&nbsp;&nbsp;${list.writer }</span>
						        <b><span class="writer_sbuject">
						        <a href='/board/read${pageBtn.makeQuery(pageBtn.paging.pageNum)}&num=${list.num}&searchType=${paging.searchType}&keyword=${paging.keyword}'>
						        ${list.subject}</a></span></b>
						        <span class="write_date">${list.reg_date }&nbsp;&nbsp;&nbsp;
						        ${list.readcount }</span></li>
            				</ul>
            			</c:forEach>
            		</div>
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
								<li><a
									href="${pageBtn.endPage +1}">다음</a></li>
							</c:if>
						</ul><!-- end of  pagination-->
					</div><!-- end of  pagingbtn-->
            	</c:if>     
            </article>
        </section>
        <!-- 페이지 버튼 클릭시 Controller로 정보를 보내기 위한 form태그 -->
        <form id="PagingForm">
		  <input type='hidden' name="pageNum" value='${pageBtn.paging.pageSize}'>
		  <input type='hidden' name="pageSize" value='${pageBtn.paging.pageSize}'>
		  <input type='hidden' name="searchType" value='${paging.searchType}'>
		  <input type='hidden' name="keyword" value='${paging.keyword}'>
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
			$(document).ready(function(){
				$('#searchBtn').click(function(){
					self.location = "listAll"
						+ '${pageBtn.makeQuery(1)}'
						+ "&searchType="
						+ $("select option:selected").val()
						+ "&keyword=" + $('#keywordInput').val();
				});
			});
		</script>
        
        <!-- 사이트의 정보를 적어준다. -->        
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
    </div>
</body>
</html>