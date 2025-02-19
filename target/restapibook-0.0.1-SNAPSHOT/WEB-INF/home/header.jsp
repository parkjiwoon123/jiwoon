<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="icon" href="/ico/favicon.ico">
<script src="/js/script.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/9726b89eb1.js" crossorigin="anonymous"></script>
<link href="/css/style_flex.css" rel="stylesheet" type="text/css">
<link href="/css/style.css" rel="stylesheet" type="text/css">

<header class="myHeader">
<nav class="navbar">
    <div class="navbar_logo"><i class="fa-sharp fa-solid fa-paw" onclick="location.href='/'"></i></div> 
    <ul class="navbar_menu">
        <li onclick="location.href='/company'">회사소개</li>
        <li onclick="location.href='/chat'">채팅방</a></li>
        <li onclick="location.href='/phonebook'">전화번호부</li>
        <li onclick="location.href='/gallery'">갤러리</a></li>
        <li onclick="location.href='/board'">게시판</a></li>
        <li onclick="location.href='/star'">자사 제품 별점</a></li>
    </ul>
    <ul class="navbar_icon">
        <li><i class="fas fa-camera"></i></li>
        <li><i class="fas fa-envelope" onclick="location.href='/mail'"></i></li>
        <!-- <li><a href="/login/login.html"><i class="fas fa-user-plus"></i></a></li> -->
        <!-- <li><i class="fas fa-user-plus" onclick="location.href='/login'"></i></li> -->
         <%if(session.getAttribute("id")==null){%>
        <li><i class="fas fa-user-plus"  onclick="location.href='/login'"></i></li>
        <%}else{%>
        <li><a href="/logout">[로그아웃]</a></li>
        <li><a href="/update">[회원정보수정]</a></li>
        <%}%>
    </ul>
    <a class="navbar_toogleBtn" href="#">
    <i class="fas fa-bars"></i>
    </a>
</nav>
</header>
