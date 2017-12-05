<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 	
<%@ page import="db.DbInfo" %> 	
	
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="member" class="bean.Member" scope="page">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>

<%	
	String id = "";
	String name = "";
	int level = 0;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DbInfo di = new DbInfo();
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(di.getURL(), di.getID(), di.getPW());
		String sql = "select id, pass, name, m_level from member where id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, member.getId());
		rs = ps.executeQuery();
		
		if(rs.next()){
			if(rs.getString(2).equals(member.getPass())){
				session.setAttribute("userid", rs.getString(1));
				session.setAttribute("username", rs.getString(3));
				session.setAttribute("userlevel", rs.getInt(4));
				response.sendRedirect("../index.jsp");
			}else{%>
				<script>
	            	window.alert('틀린 비밀번호 입니다.')
	            	history.go(-1)
	          	</script>
			<%}
		}else{%>
			<script>
            	window.alert('등록되지 않은 아이디입니다.')
            	history.go(-1)
          	</script>
		<%}
	}catch(SQLException e){}
	finally{
		try{if(rs != null) rs.close();}catch(SQLException e){}
		try{if(ps != null) ps.close();}catch(SQLException e){}
		try{if(conn != null) conn.close();}catch(SQLException e){}
	}	
%>
<h1><%=di.getID() %></h1>
<h1><%=id %></h1>
<h1><%=name %></h1>
<h1><%=level %></h1>