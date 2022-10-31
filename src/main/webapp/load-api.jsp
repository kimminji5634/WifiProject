<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "test.DBcon" %>   
<%@ page import = "test.Wifi" %> 
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%> 
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {text-align: center;}
</style>
</head>
<body>
<%
DBcon dbCon = new DBcon();
Wifi wifi = new Wifi();
dbCon.register(wifi);
%>

<h1>14497개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<a href= "home.jsp">홈으로 가기</a> 
</body>
</html>