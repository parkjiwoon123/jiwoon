<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML>
<html>
	<head>
		<title>동물밥 천국에 오신결 환영합니다!</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link href="/css/style_flex.css" rel="stylesheet" type="text/css">
        <link href="/css/style.css" rel="stylesheet" type="text/css">
        <link href="/css/substyle.css" rel="stylesheet" type="text/css">
        <style>
            .star-rating {
                font-size: 1.5em; 
                font-weight: bold; 
                color: #f39c12; 
                background-color: #f9e79f; 
                padding: 5px; 
                border-radius: 5px;
            }
        </style>
	</head>
	<body>
	    <header style="text-align: center;"></header>
	    <section id="banner">
            <h2><strong>사료천국의</strong> 실제로 판매되고 있지 않은 제품의 실시간 평점입니다</h2>
            <p>실시간 웹 스프래핑 평점입니다.</p>
            <ul class="actions">
                <li><a href="/star" class="button special">별점 다시 가져오기</a></li>
            </ul>
        </section>
        <section id="one" class="wrapper special">
            <div class="inner">
                <header class="major">
                    <h2>판매되고 있는(실제가 아닌 카카오 무작위 별점)제품의 평점</h2>
                </header>
                <div class="features">
                    <div class="feature">
                        <img src="/starimg/img1.jpg" alt="Image1" style="max-width: 100%; height: auto;">
                        <h3>도비 카카오맵 경복식당</h3>
                        <p class="star-rating">${star.naver}</p>
                    </div>
                    <div class="feature">
                        <img src="/starimg/img2.jpg" alt="Image2" style="max-width: 100%; height: auto;">
                        <h3>홈도그 카카오맵 제일콩집</h3>
                        <p class="star-rating">${star.kakao}</p>
                    </div>
                    <div class="feature">
                        <img src="/starimg/img3.jpg" alt="Image3" style="max-width: 100%; height: auto;">
                        <h3>건강백서 카카오맵 청년감자탕</h3>
                        <p class="star-rating">${star.gung}</p>
                    </div>
                    <div class="feature">
                        <img src="/starimg/img4.jpg" alt="Image4" style="max-width: 100%; height: auto;">
                        <h3>투펫 카카오맵 닭한마리 </h3>
                        <p class="star-rating">${star.buk}</p>
                    </div>
                </div>
            </div>
        </section>
        <section id="two" class="wrapper style2 special">
            <div class="inner narrow">
                <header>
                    <h2>Get in touch</h2>
                </header>
                <form class="grid-form" method="post" action="#">
                    <div class="form-control narrow">
                        <label for="name">Name</label>
                        <input name="name" id="name" type="text">
                    </div>
                    <div class="form-control narrow">
                        <label for="email">Email</label>
                        <input name="email" id="email" type="email">
                    </div>
                    <div class="form-control">
                        <label for="message">Message</label>
                        <textarea name="message" id="message" rows="4"></textarea>
                    </div>
                    <ul class="actions">
                        <li><input value="Send Message" type="submit"></li>
                    </ul>
                </form>
            </div>
        </section>
        <section id="copyright" class="wrapper style2 special">
            Site made with: <a href="https://templated.co/">Templated</a>.
        </section>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/skel.min.js"></script>
        <script src="assets/js/util.js"></script>
        <script src="assets/js/main.js"></script>
	</body>
</html>
