<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--  <form action ="loginProc method="post">  구버전-->
 <form th:action ="@{/Login}" method="post"> <!--  신버전(자기자신) --> 
<input type="email" name = email id = email placeholder="아이디"><p>
<input type="password" name = "password" id = "password" placeholder="비번">
<input type="submit" value="로그인">
<p>
</form>

</body>
</html>