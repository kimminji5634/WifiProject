<%@page import="test.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="test.DBcon"%>
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
	   // webapp_WEB-INF에 lib폴더 밑에 mariadb-java-client를 가져와야 web에서도 실행된다 
	   DBcon dbCon = new DBcon();
       List<Wifi> memberList = dbCon.list();   
	%>
	
	
</body>
</html>