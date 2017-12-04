<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 	
<%@ page import="db.DbInfo" %> 	
	
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="member" class="bean.Member" scope="page">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>
	
<%	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DbInfo di = new DbInfo();
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sql = "select id, pass from member where id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, member.getId());
		rs = ps.executeQuery();
		
		String getid = rs.getString("id");
	}catch(SQLException e){}
	finally{
		if(rs != null) rs.close();
		if(ps != null) rs.close();
		if(conn != null) rs.close();
	}
%>