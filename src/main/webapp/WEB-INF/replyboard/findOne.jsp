<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta charset="UTF-8">
<title>일반 게시판</title>


<link href="/css/view.css" rel="stylesheet" type="text/css">
    <link href="/css/style_flex.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="/ico/favicon.ico">
    <script src="/js/script.js"></script>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/9cdaf5fe87.js" crossorigin="anonymous"></script>

    
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
<h2 id="headertitle">글 상세보기[수정/삭제/댓글달기]-조회수[${board.readcount}]</h2>
<!-- <form action="/updateProc" method="post" enctype="multipart/form-data"> -->
<form action="/updateProc" method="post">

<lable for="idx">글번호:</lable>
<input type="text" id="idx" name="idx" class="form-control" readonly value="${board.idx}">

<lable for="writeName">작성자</lable>
<input type="text" id="writename" name="writename" class="form-control" readonly value="${board.writename}">

<lable for="title">제목</lable>
<input type="text" id="title" name="title" class="form-control" readonly value="${board.title}">

<lable for="content">내용</lable>
<input type="text" id="content" name="content" class="form-control" readonly value="${board.content}">

<lable for="file">첨부파일</lable>
<input type="text" id="filename" name="filename" class="form-control" readonly value="${board.filename}">


<input type="button" class="btn btn-primary" value="수정">
<input type="button" class="btn btn-primary" value="삭제">
<input type="button" class="btn btn-primary" value="댓글쓰기">
<input type="button" class="btn btn-primary" value="취소">
<!-- 댓글쓰기할때 parentid, tab값을 전달해야하므로 숨겨놓는다. -->
<input type="input" name="parenetid" value="${board.idx}" hidden>
<input type="input" name="tab" value="${board.tab}" hidden>
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
			if(okis) location.href="/deleteProc?idx=${board.idx}"
			//else location.href="/";
		}else if($(this).val()=="댓글쓰기"){
			//alert('댓글쓰기버튼클릭')
			location.href="/replyForm?idx=${board.idx}"
		}else if($(this).val()=="취소"){
			//alert('취소버튼클릭')
			location.href="/pageList"
		}else if($(this).val()=="수정하기"){
			$(this).prop("type","submit");
		}
	});
})

</script>
</html>