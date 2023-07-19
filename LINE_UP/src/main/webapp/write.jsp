<%@page import="com.peisia.jsp.board.dao.DaoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String category = request.getParameter("category");
%>
<form action="/board/write">
	<input type="hidden" name="category" value="<%=category%>">
	<input name="name" placeholder="선수이름">
	<input name="team" placeholder="소속구단">
	<input name="a_tool" placeholder="">
	<input name="b_tool" placeholder="">
	<input name="c_tool" placeholder="">
	<input name="d_tool" placeholder="">
	<input name="e_tool" placeholder="">
	<input type="submit" value="쓰기">
</form>
</body>
</html>