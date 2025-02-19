<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
html, body{
    height: 100%;
    margin: 0;
    display: flex;
    flex-direction: column;
}

header{
    flex: 1;
    width: 100%;
    background-color: blue;
}

.container{
    flex: 1;
    display: flex;
    flex-direction: row;
}

aside{
    flex: 1;
    background-color: green;
}

main{
    flex: 1;
    background-color: blue;
}
</style>

</head>
<body>
<header>
    <form action="insert">
    <div>책 제목:<input type="text"></div>
    <div>설명:<input type="text"></div>
    <div>가격:<input type="text"></div>
    </form>
</header>

<div class="container">
    <aside>
        <form action="ser">
        <div>Search<input type="text"></div>
        </form>
    </aside>

    <main>
    <form action="/view" hidden="true">
    <div>책 제목:<input type="text"></div>
    <div>설명:<input type="text"></div>
    <div>가격:<input type="text"></div>
    </form>
    </main>
</div>
</body>
</html>
