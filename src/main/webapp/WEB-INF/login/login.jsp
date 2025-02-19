<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="/css/login.css" rel="stylesheet" type="text/css">
<script>
function loginchk() {
	var id = document.getElementById("id");
	var password = document.getElementById("password");
	if(id.value==""){
	alert("아이디가 비어있습니다")
	id.focus(); return false;
	}
	if(password.value==""){
		alert("비밀번호가 비어있습니다")
		password.focus(); return false;
		}
	else {
		return true;
	}
}
</script>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        ul{
            list-style: none;
        }
    </style>
</head>
<body>
    <div id="loginform">
        <form action="/loginProc" onsubmit="loginchk()">
            <h1>LOGIN</h1>
        <ul>
            <li>아이디</li>
            <li><input type="text" name="id" id="id"></li>
            <li>비밀번호</li>
            <li><input type="password" name="password" id="password"></li>
            <li><input type="checkbox" name="check" id="check">로그인유지</li>
            <li><input type="submit" value="로그인"></li>
            
        </ul>
       </form>
      		 <li><a href="/member/joinmember">회원가입</a>|ID/PW찾기</li>
        </div>
</body>
</html>