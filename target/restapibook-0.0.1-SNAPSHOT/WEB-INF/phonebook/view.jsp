<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>전화번호 입력</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

  
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="/ico/favicon.ico">
    <script src="/js/script.js"></script>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/9cdaf5fe87.js" crossorigin="anonymous"></script>
     <link href="/css/style_flex.css" rel="stylesheet" type="text/css">
     <link href="/css/substyle.css" rel="stylesheet" type="text/css">
</head>
<style>
/* style.css */

body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
}

.container {
    width: 80%; /* Reduce the width to 80% of the parent element */
    margin-left: auto; /* Center the container horizontally */
    margin-right: auto; /* Center the container horizontally */
    padding-left: 20px; /* Add some space to the left of the content */
    background-color: #f8f9fa;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

h2 {
    font-size: 2em;
    color: #333;
    margin-bottom: 20px;
}

form {
    display: flex;
    flex-direction: column;
}

form lable {
    font-size: 1.2em;
    color: #555;
    margin-bottom: 5px;
}

form input[type="text"] {
    font-size: 1em;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ddd;
}

.btn {
    cursor: pointer;
    padding: 5px 10px;
    margin-top: 10px;
    color: #fff;
    background-color: #007bff;
    border: none;
    transition: background-color 0.3s;
    width: 10%; /* set width to 10% of the container */
    text-align: center; /* center the text on the button */
    display: block; /* make the button a block element */
    margin-right: auto; /* align the button to the left */
}


.btn:hover {
    background-color: #0056b3;
}

</style>


<body>    
<main>
<div class="container mt-3">
<h2 id="headertitle">전화번호부 상세보기[수정/삭제/댓글달기]</h2>
<!-- <form action="/updateProc" method="post" enctype="multipart/form-data"> -->
<form action="/phupdateProc" method="post">

<lable for="idx">전화번호부 등록 번호:</lable>
<input type="text" id="idx" name="idx" class="form-control" readonly value="${board.idx}">

<lable for="writeName">이름</lable>
<input type="text" id="writename" name="name" class="form-control" readonly value="${board.name}">

<lable for="title">번호</lable>
<input type="text" id="title" name="hp" class="form-control" readonly value="${board.hp}">

<lable for="content">메모</lable>
<input type="text" id="content" name="memo" class="form-control" readonly value="${board.memo}">



<input type="button" class="btn btn-primary" value="수정">
<input type="button" class="btn btn-primary" value="삭제">

<input type="button" class="btn btn-primary" value="취소">


</form>
</div>
</main>
</body>
<script>
//수정 : /updateForm 
//삭제 : /deleteProc
//댓글쓰기 : /replyForm
//취소 : /pageList
$(function(){
	$("input:button").click(function(){
		if($(this).val()=="수정"){
			//alert('수정버튼클릭')
			$("#headertitle").html("글 수정하기")
			$("#title").removeAttr("readonly");
			$("#content").removeAttr("readonly");
			$("#filename").prop("type","file");
			$(this).prop("value","수정하기");
			//$("input[value='삭제']").prop("value","test");
			$("input[value='삭제']").attr("type","hidden");
			$("input[value='댓글쓰기']").attr("type","hidden");
			//$(this).prop("type","submit");//페이지이동문제발생
		}else if($(this).val()=="삭제"){
			//alert('삭제버튼클릭')
			var okis=confirm("정말 삭제하시겠습니까?");
			if(okis) location.href="/del?idx=${board.idx}"
			//else location.href="/";
		}else if($(this).val()=="댓글쓰기"){
			//alert('댓글쓰기버튼클릭')
			location.href="/replyForm?idx=${board.idx}"
		}else if($(this).val()=="취소"){
			//alert('취소버튼클릭')
			location.href="/"
		}else if($(this).val()=="수정하기"){
			$(this).prop("type","submit");
		}
	});
})

</script>
</html>