<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    
    <link href="/css/style_flex.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="/ico/favicon.ico">
   
    <script src="/js/script.js"></script>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/9cdaf5fe87.js" crossorigin="anonymous"></script>
    
</head>
<body>
<jsp:include page="/WEB-INF/home/header.jsp" />
<jsp:include page="/WEB-INF/${mainpage}"/>
<jsp:include page="/WEB-INF/home/footer.jsp"/>
</body>
</html>


