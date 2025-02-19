<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- pagelist라는 객체의 데이터만 표현(VIEW) -->
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
		consol.log(kind);
		document.querySelector("#kind").innerText = kind;
	}

	function search() {
        let selectElement = document.querySelector("#kind");
		let field = selectElement.options[selectElement.selectedIndex].value;
		
		let query = document.querySelector("#search").value;
		location.href = "/boardsearch?field=" + encodeURIComponent(field) + "&query="
				+ encodeURIComponent(query);
	}

  </script>
  <script>
  $(function(){
	  $('tr').click(function(){
		  //alert('test')
		  //선택된 tr중 아래 태그의 td를 찾고, 0번째인 td안의 값을 찾는다.
		  var idx=$(this).find("td").eq(0).text();
		  //alert(idx);
		  if(idx!=""){
		  location.href="/findView?idx="+idx;
	  	  }
	  	   	
	  })
  });
  </script>
  
  <style>
  /* 리스트에 마우스를 올릴 때 노란색을 변경 */
  tr:hover{
  background-color:yellow;
  }
  
  /* 테이블의 첫번째 열의 background-color설정 */
  thead tr:first-child{background-color:lightGray;}
  </style>  

<body>
<main>
<div>
<h2>일반게시판123</h2>
 <div class="input-group mt-3 mb-3">
<select name="kind" id="kind" class="btn btn-primary dropdown-toggle">
     <option value="">Select Search</option>
     <option value="writename">글쓴이</option>
     <option value="title">제목</option>
     <option value="content">내용</option>
</select>

  
<input type="text" class="form-control" placeholder="enter search value" name="search" id="search">
<button type="button" class="btn btn-success" onclick="search()">Search</button>
</div>


<table class="table">
<thead>


<tr>
<th>글번호123</th>
<th>제목</th>
<th>글쓴이</th>
<th>날짜</th>
<th>조회수</th>
</tr>
</thead>
<c:forEach var="board" items="${pagelist.list}">
<tbody>



<c:choose> 
<c:when test="${board.isdel eq 1}">
<td><td>삭제되었습니다.</td></td><td></td><td></td><td></td>
</c:when>
<c:otherwise>
<!-- <tr onmouseover="style.backgroundColor='yellow'" 
onmouseout="style.backgroundColor='white'"> -->
<tr onclick="event.cancelBubble=true">
<td>${board.idx}</td>
<td>	

<!-- 댓글의 깊이 처리 (tab : 반복문)-->
<c:forEach begin="1" end="${board.tab}">
<img src="/img/reply_icon.gif" style="widht:42px;height:15px">
</c:forEach>
${board.title}
<!-- 파일업로드 여부 이미지 처리(filename:조건문) -->
<c:if test="${not empty board.filename}">
<a href="#" download><img src="/img/file.png" style="widht:42px;height:20px"></a>
</c:if>

</td>
<td>${board.writename}</td>
<td>${board.writeday}</td>
<td>${board.readcount}</td>
</tr>
</c:otherwise>
</c:choose>
</tbody>
</c:forEach>
</table>

<div>
<ul class="pagination">

<!-- 이전 페이지로 이동하는 링크를 생성 -->
<c:if test="${pagelist.startPage>5}">
<li class="page-item"><a class="page-link" href="?currentPage=${pagelist.startPage-5}">이전페이지</a></li>
</c:if>

<!-- 페이지 번호를 생성하는 반복문 시작 페이지부터 끝 페이지까지의 범위에서 각 페이지 번호를 생성  -->
<c:forEach var="i" begin="${pagelist.startPage}" end="${pagelist.endPage}" step="1">
<c:choose>
<c:when test="${pagelist.currentPage==i}">
<li class="page-item active"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
</c:when>
<c:otherwise>
<li class="page-item"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
</c:otherwise>
</c:choose>
</c:forEach>

<!-- 다음 페이지로 이동하는 링크를 생성 -->
<c:if test="${pagelist.totalCount>0 && pagelist.endPage<pagelist.totalPage}">
<li class="page-item"><a class="page-link" href="?currentPage=${pagelist.endPage+1}">다음페이지</a></li>
</c:if>
</ul>
</div>


<button type="button" class="btn btn-success" onclick="location.href='/write'">글쓰기</button>
</div>
</main>
</body>
</html>