<%@page import="bbs.board.bean.*"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8");%>
<!-- js 파일 불러오기 -->
<script src="/Hyundai2/bbs/js/freeboard.js"></script>


<% 
	String id = (String)session.getAttribute("id"); 
	int pageSize = 5;//한페이지에 표시할 글의 수 
	SimpleDateFormat sdf = 
	new SimpleDateFormat("yyyy/MM/dd HH:mm");//날짜데이터 표시형식지정
	String pageNum = request.getParameter("pageNum");//화면에 표시할 페이지번호
	//사용자가 선택한 페이지 번호
	if (pageNum == null) //페이지번호가 없으면 1페이지의 내용이 화면에 표시
	    pageNum = "1";
	int currentPage = Integer.parseInt(pageNum);//페이지의 넘버를 숫자로 파싱
	/*전체 글의 숫자를 불러옴*/
	List<BoardBeanDb> articleList = null;	
	BoardDAODb bdd = BoardDAODb.getInstance();
	int articleCnt = bdd.getArticleCnt();//전체 글의 수를 얻어냄
	/*END--------------*/
	if(articleCnt == (currentPage-1)*pageSize)
   		currentPage -=1;
	int startRow = (currentPage - 1) * pageSize + 1;
	//현재 페이지에서의 시작글번호 즉 현재 페이지에서 시작되는 데이터를 의미
	try{
		if(articleCnt > 0){//데이터가 있을 경우
			articleList = bdd.getArticle(startRow, pageSize);
			//지정한 시작데이터와 데이터크기를 전송
		}
		if(articleList.isEmpty())//테이블에 저장된 글이 없으면, 전체글 수 : 0
			articleCnt = 0;
	}catch(Exception e){}
%>
<% if(id.equals("") || id == null){ %>
<script>
	alert("로그인을 하셔야 합니다.");
	window.location.href("index.jsp");
</script>
<% }else{ %>
	<h2>글 목록(전체 글 : <%=articleCnt %>) </h2>
	<img id="write_img_btn" src="/Hyundai2/bbs/img/menu02_write.png">
	<% if (articleCnt == 0){//게시판에 글이 없는 경우%>
	<div id="list_article" class="free_list">
  		<ul><li><p>게시판에 저장된 글이 없습니다.</p></li></ul>
	</div>
	<%}else{ %>
	<div id="list_article" class="free_list">
  	<% 
  	//글목록을 반복처리
  	for (int i = 0 ; i < articleList.size() ; i++) {
     	BoardBeanDb article = articleList.get(i); %>
    <!-- 나중에 jquery를 사용하기 위해 id를 주는대 i를 붙여 고유값을 유지 
    	 만약 class를 이용한다면 모든 ul에 여백이 생겨 댓글만 효과를 못줌--> 		
	<ul class="article_ul" id="<%="write_ul_id"+i %>">
	<%//re_level이 있다면 즉 댓글이라면 댓글의 레벨에 따라 iw값을 준다.
		int iw=0; 
		if(article.getRe_level()>0){
	  		iw=2*(article.getRe_level());
	%>	
    <%}//END IF %>
    <%-- iw는 re_level이 있을때만 값을 주므로 0이 아니라면 댓글이다.
    	 댓글일 경우 jquery를 통해 ul에 왼족 공백을 줘 댓글임을 표시한다.
    	 여백은 iw를 곱해서 댓글 레벨마다 더 여백값이 증가한다. --%>
    <%if(iw != 0){ %>    	
	<script>$('#<%="write_ul_id"+i %>').css('padding-left', '<%=iw*25%>px');</script>
	<%}//END IF 댓글일 경우 %>  
    	<!-- 아이디, 제목, 등록 날짜 출력 -->
		<li class="write_li" ><span class="writer_id"><%String writer = article.getWriter();
	       out.println(writer.substring(0, 4) + "****");%></span>
	       <b><span class="writer_sbuject"><%=article.getSubject()%></span></b>
	       <span class="write_date"><%=sdf.format(article.getReg_date())%></span></li>
	 	<!-- END 아이디, 제목 출력 -->
	    <!-- 본문 출력 -->
    	<% 
     		int num = article.getNum();
	  		int ref = article.getRef();
	  		int re_step = article.getRe_step();
	   		int re_level = article.getRe_level();
		%>
		<pre class="write_pre"><%=article.getContent()%></pre><br>		     	
     	<!-- END 본문 출력 -->
     	<!-- 버튼 출력 -->     
     	<%if(!article.getSubject().equals("삭제된 글")){%>	
	   		<%try{
	  			id = (String)session.getAttribute("id");
	    		}catch(Exception e){}%>
			<%if(id.equals(writer)) {%>
	 			<button id=<%="edit"+i %> 
	  			name="<%=num+","+pageNum%>" onclick="edit(this)">수정</button>
	 			<button id="delete" 
	  			name="<%=num+","+pageNum+","+ref%>" onclick="del(this)">삭제</button>
			<%}else{ %>
	 			<button id="reply" 
	  			name="<%=num+","+ref+","+re_step+","+re_level+","+pageNum%>" 
	 			onclick="reply(this)">댓글쓰기</button> 			
			<%}//END IF~ELSE 버튼 종류%>
		<%}//END 삭제된글이 아닐 경우에 출력 %>     	
     	<!-- END 버튼 출력 -->
     </ul>
	<%}//END FOR %>
	</div>	
	<%}//END 게시판에 글이 있는 경우 ELSE %>
<% }//END 로그인이 되어있을 경우 ELSE %>

<%-- 페이징 --%>  
<div id="jump">
<%
if (articleCnt > 0) {//데이터가 있을 경우
   int pageCount = articleCnt / pageSize + ( articleCnt % pageSize == 0 ? 0 : 1);
   //pageCount는 총 페이지의 크기를 의미한다. 총데이터를 사이즈로 나누어 총 몇페이지까지 나오는지 계산
   int startPage = 1;//초기화
		
   if(currentPage % pageSize != 0)//현재 페이지와 페이지 사이즈를 나눈값에 나머지가 생길경우
      startPage = (int)(currentPage/pageSize)*pageSize + 1;
   	  //클릭한 페이지가 선택한 페이지를 넘을때마다 페이지 사이즈만큼 시작 페이지가 증가함
   	  //ex)5를 설정하면 1~5까진 1로시작 5초과 10이하는 6으로 시작
   else//나머지가 없을 경우 즉 사이즈가 5인대 클릭한 페이지도 5의 배수일 경우
      startPage = ((int)(currentPage/pageSize)-1)*pageSize + 1;
	
   int pageBlock = 5; //페이지들의 블럭단위 지정
   int endPage = startPage + pageBlock-1;
   //endPage는 표시할 마지막 페이지로 startPage의 숫자에 원하는 페이지 크기를 더해서 구해준다.
   //즉 pageSize=5, pageBlock = 5 일때 선택한 페이지가 8이라면 startPage는 6 endPage는 6+5-1로 10이다.
   //결국 게시글 맨아래 숫자버튼이 6~10까지 5개 존재하게 되는것이다.
   
   if (endPage > pageCount) endPage = pageCount;
   //데이터의 총페이지보다 최종페이지가 더 많을수가 없으므로 최종페이지가 데이터의 총 페이지를 넘는 순간 값이 같아지게한다.
        
   if (startPage > pageBlock) {%>
   <!-- 선택한 페이지의 시작 페이지가 페이지 블록보다 크다면 이전페이지가 존재하므로 이전 버튼을 생성한다. -->
      <button id="juP" name="<%=startPage - pageBlock%>" 
          onclick="numberBtn(this)" class="w2">이전</button>
      &nbsp;
<% }//반복문을 통해 지정한 시작페이지부터 마지막 페이지까지 숫자 버튼을 생성해준다.
   for (int i = startPage ; i <= endPage ; i++) {  
     if(currentPage == i){%>
     <!-- 선택된 페이지 즉 선택한것과 i와 같으므로 선택된 페이지는 다른 디자인 효과를 준다. -->
      <button id="ju" name="<%=i%>" 
         onclick="numberBtn(this)" class="w1"><%=i%></button>
    <!-- 버튼의 이름과 벨류를 i로 지정함으로서 매 반복시마다 다른이름의 버튼을 생성 -->
    <%}else{ %>
    <!-- 내용자체는 위와 같지만 선택되지 않은 버튼들이기 때문에 스타일 효과가 없다. --> 
      <button id="ju" name="<%=i%>" 
         onclick="numberBtn(this)" class="w"><%=i%></button>
    <%}%>
      &nbsp; 
<% }
   if (endPage < pageCount) {  %>
   <!-- 선택한 페이지의 블록단위에서 (7을 선택했다면 6~10) 마지막 페이지의 번호보다 총페이지의 번호가 크다면
   		다음페이지가 존재함으로 다음버튼을 생성시켜 준다. -->
      &nbsp;
      <button id="juN" name="<%=startPage + pageBlock%>" 
           onclick="numberBtn(this)" class="w2">다음</button>
<%
   }
}//END IF articleCnt(데이터의 갯수)없지 않을경우 즉 데이터가 존재할 경우
%>
</div>