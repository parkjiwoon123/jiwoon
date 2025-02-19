<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Price Tracker</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        select, input[type="url"], button {
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
        }
        td[data-product-id] {
        cursor: pointer;
        text-decoration: underline;
        color: blue;
    }
        .url-input { display: flex; justify-content: center; align-items: center; }
        .price-graph { margin-top: 20px; width: 100%; height: 400px; border: 1px solid #ddd; border-radius: 4px; }
        .alert { padding: 10px; margin-top: 20px; border-radius: 4px; }
        .alert-success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .alert-error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
<div class="navbar">
    <% String userId = (String) session.getAttribute("id"); if(userId != null && !userId.equals("")){ %>
        <a href="/userDetail"><%= userId %></a>
        <a href="/logout">로그아웃</a>
    <% } else { %>
        <a href="/moalogin">로그인</a>
    <% } %>
    <a href="#">오류신고</a>
</div>
<div class="container">
    <h1>Price Tracker</h1>
    <form method="post" action="/input" id="fetch-form">
        <div class="url-input">
            <select name="store" id="store-select">
                <option value="1">쿠팡</option>
                <option value="2">G마켓</option>
                <option value="3">11번가</option>
            </select>
            <input type="url" placeholder="Enter website URL..." id="url" name="url">
            <input type="hidden" id="username" name="username" value="<%= userId != null ? userId : "" %>">
            <button type="submit" id="fetch-price">Fetch Price</button>
        </div>
    </form>
    <div class="price-list">
        <table>
            <thead>
                <tr>
                    <th><a href="<c:url value='/look' ><c:param name='username' value='${sessionScope.id}' /></c:url>">물품 이름</a></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <!-- 상품 이름 클릭시 fetchAndDrawGraph 함수를 호출하도록 수정 -->
<td data-product-id="${product.productId}">${product.productName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="graph"></div>
    <div class="price-graph" id="price-graph"></div>
    <div class="alert alert-success" id="success-message" style="display: none;">Price fetched and saved successfully!</div>
    <div class="alert alert-error" id="error-message" style="display: none;">Error fetching price. Please try again.</div>
</div>
<script>
console.log('productList contents:', <c:out value="${productList}" escapeXml="false"/>);
function fetchAndDrawGraph(itemId) {
    if (!itemId) {
        console.error("Item ID is not provided!");
        return;
    }
    
    console.log("Fetching data for item:", itemId);
    fetch(`/priceData?itemId=${itemId}`)
    .then(response => {
        return response.text();  // 응답 내용을 텍스트로 받아옴
    })
    .then(text => {
        console.log("Raw response:", text);
        return JSON.parse(text);  // 수동으로 JSON으로 변환
    })
    .then(data => {
        console.log("Parsed data:", data);
        drawGraph(data);
    })
    .catch(error => {
        console.error("There was a problem with the fetch operation:", error.message);
    });
}

function drawGraph(data) {
    const ctx = document.getElementById('price-graph').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.dates,
            datasets: [{
                label: '가격 변동',
                data: data.prices,
                borderColor: 'blue',
                fill: false
            }]
        },
        options: {
            scales: {
                x: {
                    type: 'time',
                    time: {
                        parser: 'YYYY-MM-DD',
                        tooltipFormat: 'll'
                    }
                }
            }
        }
    });
}
document.addEventListener("DOMContentLoaded", function() {
    const products = document.querySelectorAll("td[data-product-id]");
    products.forEach(product => {
        product.addEventListener("click", function() {
            const itemId = this.getAttribute('data-product-id');
            fetchAndDrawGraph(itemId);
        });
    });
});
</script>
</body>
</html>
