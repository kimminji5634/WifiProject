<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="test.Member"%>
<%@ page import = "test.Wifi" %> 
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%> 
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@page import="test.DBcon"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    bord
    er: 1px solid #444444;
    text-align: center;
  }
</style>
</head>
<body>
	
<h1> 위치 히스토리 목록 </h1>
<a href= "home.jsp">홈</a> |
<a href= "load-history.jsp">위치 히스토리 목록</a> |
<a href= "load-api.jsp">Open API 와이파이 정보 가져오기</a> 

<br></br>
	

<div class="bd-example">
  <table class="table">
  
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">X좌표</th>
      <th scope="col">Y좌표</th>
      <th scope="col">조회일자</th>
      <th scope="col">비고</th>
    </tr>
    
  </thead>
  <tbody>
<%
	DBcon dbCon = new DBcon();
	List<Member> memberList = dbCon.memberSelect();
%>
  <%
for(Member m : memberList){
%>
    <tr>
	   <td><%= m.getId() %></td>
	   <td><%= m.getLAT() %></td>
	   <td><%= m.getLNT() %></td>
       <td><%= m.getSelectDate() %></td>
       <td><input type = "button"  class="btn btn-primary mb-2" value = "삭제"></td>
	</tr>
 <%
}
%>
  </tbody>

  </table>
</div>


</body>

</body>
</html>