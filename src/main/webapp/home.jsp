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
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    text-align: center;
  }
</style>
</head>
<body>

	
<h1> 와이파이 정보 구하기 </h1>
<a href= "home.jsp">홈 위치</a> |
<a href= "history.jsp">위치 히스토리 목록</a> |
<a href= "load-api.jsp">Open API 와이파이 정보 가져오기</a> 

<p></p>

  <div class="form-group mb-2">
    <label for="staticEmail2" class="sr-only">LAT:</label>
    <input type="text" class="form-control-plaintext" id="lat" value="", name = "LAT"> ,

    <label for="staticEmail2" class="sr-only">LNT:</label>
    <input type="text" class="form-control-plaintext" id="lnt" value="">
    
    <input type = "button"  class="btn btn-primary mb-2" value = "내 위치 가져오기" onclick = "getLocation()">
    <input type = "button"  class="btn btn-primary mb-2" value = "근처 WIPI 정보 보기" onclick = "location.href='areaWifi.jsp'">
  </div>

<p></p>


<div class="bd-example">
  <table class="table">
  
      <thead>
    <tr>
      <th scope="col">거리(Km)</th>
      <th scope="col">관리번호</th>
      <th scope="col">자치구</th>
      <th scope="col">와이파이명</th>
      <th scope="col">도로명주소</th>
      <th scope="col">상세주소</th>
      <th scope="col">설치위치(층)</th>
      <th scope="col">설치유형</th>
      <th scope="col">설치기관</th>
      <th scope="col">서비스구분</th>
      <th scope="col">망종류</th>
      <th scope="col">설치년도</th>
      <th scope="col">실내외구분</th>
      <th scope="col">WIFI접속환경</th>
      <th scope="col">X좌표</th>
      <th scope="col">Y좌표</th>
      <th scope="col">작업일자</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td colspan = "17">위치 정보를 입력한 후에 조회해 주세요.</td>
      
    </tr>
  </tbody>
  </table>
</div>
</body>
</html>



<script>
//내 위경도 값 가져오는 함수
function getLocation() {
	  if (navigator.geolocation) { // GPS를 지원하면
	    navigator.geolocation.getCurrentPosition(function(position) {
	    	lat.value = position.coords.latitude;
	    	lnt.value = position.coords.longitude;
	    	//location.href="areaWifi.jsp?lat="+lat.value+"&lnt="+lat.value
	    	//alert(position.coords.latitude + ' ' + position.coords.longitude);
	    }, function(error) {
	      console.error(error);
	    }, {
	      enableHighAccuracy: false,
	      maximumAge: 0,
	      timeout: Infinity
	    });
	  } else {
	    alert('GPS를 지원하지 않습니다');
	  }
	}
</script>
	
	