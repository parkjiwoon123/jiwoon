<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>일반 게시판</title>
<!-- 부스트랩 사용 전 jquery 스크립트가 먼저 위치해야 오류 발생 안함 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>
    function selectkind(kind) {
        console.log(kind);
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
          var idx=$(this).find("td").eq(0).text();
          if(idx!=""){
              location.href="/findView?idx="+idx;
          }
      })
  });
</script>

<style>
  /* 리스트에 마우스를 올릴 때 배경색을 노란색으로 변경 */
  tr:hover{
      background-color:yellow;
  }
  
  /* 테이블의 첫 번째 열의 배경색 설정 */
  thead tr:first-child {
      background-color:lightGray;
  }
</style>  

<body>
<main>
<div>
<h2>일반게시판</h2>
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
    <th>글번호</th>
    <th>제목</th>
    <th>글쓴이</th>
    <th>날짜</th>
    <th>조회수</th>
</tr>
</thead>
<tbody>
<c:forEach var="board" items="${pagelist.list}">
    <c:choose> 
        <c:when test="${board.isdel eq 'Y'}">
            <tr>
                <td colspan="5">삭제되었습니다.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr onclick="event.cancelBubble=true">
                <td>${board.idx}</td>
                <td>
                    <!-- 댓글의 깊이 처리 (tab : 반복문)-->
                    <c:forEach begin="1" end="${board.tab}">
                        <img src="/img/reply_icon.gif" style="width:42px;height:15px">
                    </c:forEach>
                    ${board.title}
                    <!-- 파일업로드 여부 이미지 처리 (filename: 조건문) -->
                    <c:if test="${not empty board.filename}">
                        <a href="#" download><img src="/img/file.png" style="width:42px;height:20px"></a>
                    </c:if>
                </td>
                <td>${board.writename}</td>
                <td>${board.writeday}</td>
                <td>${board.readcount}</td>
            </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</tbody>
</table>

<div>
<ul class="pagination">
    <!-- 이전 페이지로 이동하는 링크를 생성 -->
    <c:if test="${pagelist.startPage > 5}">
        <li class="page-item"><a class="page-link" href="?currentPage=${pagelist.startPage-5}">이전 페이지</a></li>
    </c:if>
    
    <!-- 페이지 번호를 생성하는 반복문 시작 페이지부터 끝 페이지까지의 범위에서 각 페이지 번호를 생성 -->
    <c:forEach var="i" begin="${pagelist.startPage}" end="${pagelist.endPage}" step="1">
        <c:choose>
            <c:when test="${pagelist.currentPage == i}">
                <li class="page-item active"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    <!-- 다음 페이지로 이동하는 링크를 생성 -->
    <c:if test="${pagelist.totalCount > 0 && pagelist.endPage < pagelist.totalPage}">
        <li class="page-item"><a class="page-link" href="?currentPage=${pagelist.endPage + 1}">다음 페이지</a></li>
    </c:if>
</ul>
</div>

<button type="button" class="btn btn-success" onclick="location.href='/write'">글쓰기</button>
</div>
</main>
</body>
</html>