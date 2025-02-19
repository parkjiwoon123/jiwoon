<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script></head>
<body>
<main>
<div class="container mt-3">
<h2>전화번호부 추가</h2>
<form action="/phwriteProc" method="post" enctype="multipart/form-data">

<lable for="idx" hidden>등록번호:</lable>
<input type="text" id="idx" name="idx" class="form-control" disabled="disabled" hidden>

<lable for="writeName">이름</lable>
<%-- <input type="text" id="writeName" name="writeName" class="form-control" readonly value="${id}"> --%>
<input type="text" id="name" name="name" class="form-control" readonly value="admin">
<lable for="title">전화 번호</lable>
<input type="text" id="number" name="number" class="form-control">

<lable for="title">주소</lable>
<input type="text" id="hp" name="hp" class="form-control">

<button type="submit" class="btn btn-primary">전화번호 추가</button> <!-- 파일업로드 시 중복되지 않는 형태로 dao구성 -->
<button type="button" class="btn btn-primary">취소</button>
</form>
</div>
</main>
</body>
</html>