
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>전화번호 입력</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

 <div class="container mt-3">
<button type="button" class="btn btn-success" onclick="/index">홈</button>  
<h2>전화번호부 수정</h2>
  <form action="/phupdateproc">
    <div class="mb-3 mt-3">
      <label for="num">번호:</label>
      <input type="text" class="form-control" id="ph_num" name="ph_num" readonly value="${update.idx}">
    </div>
    <div class="mb-3 mt-3">
      <label for="name">이름:</label>
      <input type="text" class="form-control" id="name" name="name" value="${update.name }">
    </div>
    <div class="mb-3 mt-3">
        <label for="전화번호">전화번호:</label>
        <input type="text" class="form-control" id="hp" name="hp" value="${update.hp }">
      </div>
      <div class="mb-3 mt-3">
        <label for="memo">메모:</label>
        <input type="text" class="form-control" id="memo" name="memo" value="${update.memo }">
      </div>
    
    <input type="submit" class="btn btn-primary" value="전화번호수정하기">
  </form>
  <button class="btn btn-primary" onclick="location.href='list.jsp'">수정취소하기</button>
</div>


</body>
</html>




