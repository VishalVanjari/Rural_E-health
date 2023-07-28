<%@page import="com.rathod.dbconnect.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Case</title>
</head>
<body>
<%
	int did=Integer.parseInt(request.getParameter("cid"));
	Connection con = ConnectDB.getConnect();
	PreparedStatement ps1 = con.prepareStatement("delete from case_tbl where cid=?");
	ps1.setInt(1,did);
	int i = ps1.executeUpdate();
	if(i>0){
		response.sendRedirect("deleteCase.jsp");
	}else{
		response.sendRedirect("failed.html");
	}
%>
</body>
</html>