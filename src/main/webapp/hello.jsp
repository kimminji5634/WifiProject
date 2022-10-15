<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jsp 파일은 프로그램이 가능한 동적 페이지이다 -->
<%
	out.println(java.time.LocalDateTime.now());
// 내 컴퓨터에 올리는 것 => commit, 소스를 공유하려면 => push, 내 소스 히스토리만 보고 싶으면 내부적인 commit만 하면 됨
%>
</body>
</html>