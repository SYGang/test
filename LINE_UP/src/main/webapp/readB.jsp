<%@page import="com.peisia.jsp.board.dto.Dto"%>
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
Dto d=(Dto)request.getAttribute("post");
String position = request.getParameter("position");
%>
포지션: <%=d.position%>
<hr>
선수이름: <%=d.name%>
소속구단: <%=d.team%>
<hr>
타격: <%=d.a_tool%>
파워: <%=d.b_tool%>
선구: <%=d.c_tool%>
주루: <%=d.d_tool%>
수비: <%=d.e_tool%>
<hr>
<a href="/board/del?position=<%=position%>&no=<%=d.no%>">삭제</a>
<a href="/board/edit_insert?position=<%=position%>&no=<%=d.no%>">수정</a>
<a href="/board/list?position=<%=position%>">리스트로</a>
<a href="/">홈으로</a>
</body>
</html>