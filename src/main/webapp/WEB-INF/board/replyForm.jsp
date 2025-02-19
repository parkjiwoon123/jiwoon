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
