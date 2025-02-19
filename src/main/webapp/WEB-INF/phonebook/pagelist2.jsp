<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<link href="/css/substyle.css" rel="stylesheet" type="text/css">
    <link href="/css/style_flex.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="/ico/favicon.ico">
    <script src="/js/script.js"></script>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/9cdaf5fe87.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>일반 게시판123</title>
  <!-- 부스트랩사용 전 jquery 스크립트가 먼저 위치해야 오류발생안함 -->
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function selectkind(kind) {
		//alert(kind);
		document.querySelector("#searchbtn").innerText = kind;
	}

	function search() {
		kind = document.querySelector("#searchbtn").innerText;
		//alert(kind);
		svalue = document.querySelector("#search").value;
		//alert(svalue);
		location.href = "/phonebook/searchList.jsp?kind=" + kind + "&search="
				+ svalue
	}
</script>

</head>
<body>

	<div class="container mt-3">
		<h2>전화번호부</h2>

		<div class="input-group mt-3 mb-3">
  <select name="kind" id="kind" class="btn btn-primary dropdown-toggle">
     <option value="">Select Search</option>
     <option value="title">Title</option>
     <option value="writer">Author</option>
     <option value="date">Date</option>
  </select>
  



<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
	function selectkind(kind) {
		//alert(kind);
		document.querySelector("#searchbtn").innerText = kind;
	}

	function search() {
		kind = document.querySelector("#searchbtn").innerText;
		//alert(kind);
		svalue = document.querySelector("#search").value;
		//alert(svalue);
		location.href = "/phonebook/searchList.jsp?kind=" + kind + "&search="
				+ svalue
	}
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container mt-3">
		<h2>전화번호부</h2>

		<div class="input-group mt-3 mb-3">
  <select name="kind" id="kind" class="btn btn-primary dropdown-toggle">
     <option value="">Select Search</option>
     <option value="title">Title</option>
     <option value="writer">Author</option>
     <option value="date">Date</option>
  </select>
  
  <input type="text" class="form-control" placeholder="enter search value" name="search" id="search">
  <button type="button" class="btn btn-success" onclick="search()">Search</button>
</div>



		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>메모</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pb" items="${pagelist.list}">
  				  <tr onclick="location.href='view?idx=${pb.idx}'">
   				     <td>${pb.idx} </td>
    				 <td>${pb.name}</td>
        			 <td>${pb.hp} </td>
        			 <td>${pb.memo}</td>
   				  </tr> 
				</c:forEach>
			</tbody>
		</table>

		<div class="container mt-3">
			<ul class="pagination">
			<!-- 이전 페이지로 이동하는 링크를 생성 -->
				
				<c:if test="${pagelist.startPage>5}">
				<li class="page-item"><a class="page-link"
					href="?currentPage=${pagelist.startPage-5}">Previous</a></li>
				</c:if>

			<!-- 페이지 번호를 생성하는 반복문
				시작 페이지부터 끝 페이지까지의 범위에서 각 페이지 번호를 생성  -->
				<c:forEach var="i" begin="${pagelist.startPage}" 
				end="${pagelist.endPage}" step="1">
				<c:choose>
				<c:when test="${pagelist.currentPage == i}">
				<li class="page-item active"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?currentPage=${i}">${i}</a></li>
				</c:otherwise>
				</c:choose>
				</c:forEach>

			<!-- 다음 페이지로 이동하는 링크를 생성 -->
				
				<c:if test="${pagelist.totalCount>0 && pagelist.endPage<pagelist.totalPage}">
    				<li class="page-item"><a class="page-link" href="?currentPage=${pagelist.endPage+1}">Next</a></li>
    			</c:if>
				


			</ul>
		</div>
</body>
</html>="enter search value" name="search" id="search">
  <button type="button" class="btn btn-success" onclick="search()">Search</button>
</div>



		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>메모</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pb" items="${pagelist.list}">
  				  <tr onclick="location.href='view?idx=${pb.idx}'">
   				     <td>${pb.idx} </td>
    				 <td>${pb.name}</td>
        			 <td>${pb.hp} </td>
        			 <td>${pb.memo}</td>
   				  </tr> 
				</c:forEach>
			</tbody>
		</table>

		<div class="container mt-3">
			<ul class="pagination">
			<!-- 이전 페이지로 이동하는 링크를 생성 -->
				
				<c:if test="${pagelist.startPage>5}">
				<li class="page-item"><a class="page-link"
					href="?currentPage=${pagelist.startPage-5}">Previous</a></li>
				</c:if>

			<!-- 페이지 번호를 생성하는 반복문
				시작 페이지부터 끝 페이지까지의 범위에서 각 페이지 번호를 생성  -->
				<c:forEach var="i" begin="${pagelist.startPage}" 
				end="${pagelist.endPage}" step="1">
				<c:choose>
				<c:when test="${pagelist.currentPage == i}">
				<li class="page-item active"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?currentPage=${i}">${i}</a></li>
				</c:otherwise>
				</c:choose>
				</c:forEach>

			<!-- 다음 페이지로 이동하는 링크를 생성 -->
				
				<c:if test="${pagelist.totalCount>0 && pagelist.endPage<pagelist.totalPage}">
    				<li class="page-item"><a class="page-link" href="?currentPage=${pagelist.endPage+1}">Next</a></li>
    			</c:if>
				


			</ul>
		</div>
</body>
</html> --%>