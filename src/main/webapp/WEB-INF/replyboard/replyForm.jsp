<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- pagelist라는 객체의 데이터만 표현(VIEW) -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
</head>
<body>
<main>
<div class="container mt-3">
<h2 id="headertitle">댓글쓰기</h2>
<form action="/replyFormProc" method="post" accept-charset="UTF-8">

<lable for="writeName">작성자</lable>
<input type="text" id="writename" name="writename" class="form-control" value="kim" readonly>

<lable for="title">제목</lable>
<input type="text" id="title" name="title" class="form-control" value="re:${board.title}">

<lable for="content">내용</lable>
<input type="text" id="content" name="content" class="form-control">

<lable for="file">첨부파일</lable>
<input type="file" id="filename" name="filename" class="form-control">

<button type="submit" class="btn btn-primary">댓글쓰기</button>
<button type="button" class="btn btn-primary" onclick="#">취소</button>

<input hidden type="text" name="parentid" value="${parent.idx}">
<input hidden type="text" name="tab" value="${parent.tab}">
</form>
</div>
</main>
</body>
</html>
