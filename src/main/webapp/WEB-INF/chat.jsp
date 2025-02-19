<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<head>
    <title>1:1 Chat</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <style>
.main {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 200px);
}

.container {
    border: 1px solid #000;
    width: 750px;
    height: 450px;
    padding: 15px;
    margin: 0 auto;
    margin-top: 50px;
}

#list {
    height: 300px;
    width: 600px;
    overflow: auto;
    padding: 15px;
}

.user-input-section {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

input[type="text"] {
    width: 70%;
    box-shadow: 0 0 10px 2px rgba(0,0,0,.1);
    border: none;
    padding: 10px;
    margin-right: 10px;
}

button[type="button"] {
    width: 15%;
    border: none;
    padding: 10px;
    border-radius: 5px;
    color: #fff;
    font-weight: bold;
    cursor: pointer;
}

#btnConnect {
    background-color: #4CAF50;
}

#btnConnect:hover {
    background-color: #45a049;
}

#btnDisconnect {
    background-color: #f44336;
}

#btnDisconnect:hover {
    background-color: #e33326;
}

.msg {
    width: 100%;
    box-shadow: 0 0 10px 2px rgba(0,0,0,.1);
    border: none;
    padding: 10px;
}
    </style>
</head>
<body>
 <div class="main">
 <div class="container">
    <h1 class="page-header">Chat</h1>      
    
    <table class="table table-bordered">
        <tr>
            <td class="user-input-section">
                <input type="text" name="user" id="user" class="form-control" placeholder="유저명" value="${id}">
                <button type="button" class="btn btn-default" id="btnConnect">연결</button>
                <button type="button" class="btn btn-default" id="btnDisconnect" disabled>종료</button>
            </td>
        </tr>
        <tr>
            <td colspan="2"><div id="list"></div></td>
        </tr>
        <tr>
            <td colspan="2"><input type="text" name="msg" id="msg" placeholder="대화 내용을 입력하세요." class="form-control msg" disabled></td>
        </tr>
    </table>
    
</div>
</div>
    <script>
let url = "wss://11a1aa041c7a.ngrok.app/chat";
let ws;
//let url = "ws://localhost:8080/chat";

//let url = "ws://localhost:8080/chat";
$('#btnConnect').click(function() {
    if ($('#user').val().trim() != '') {
  
            ws = new WebSocket(url);
            ws.onopen = function (evt) {
                print($('#user').val(), '입장했습니다.');
                ws.send('1#' + $('#user').val() + '#');
                $('#user').attr('readonly', true);
                $('#btnConnect').attr('disabled', true);
                $('#btnDisconnect').attr('disabled', false);
                $('#msg').attr('disabled', false);
                $('#msg').focus();
            };
            ws.onmessage = function (evt) {
                console.log("Message received:", evt.data);
                
                let parts = evt.data.split("#");
                
               
                let no = parts[0];
                let user = parts[1];
                let txt = parts.length > 2 ? parts[2] : "";

                if (no == '1') {
                    print2(user);
                } else if (no == '2') {
                    print(user, txt);
                } else if (no == '3') {
                    print3(user);
                }

                $('#list').scrollTop($('#list').prop('scrollHeight'));
            };
            ws.onclose = function (evt) {
                console.log('소켓이 닫힙니다.');
            };
            ws.onerror = function (evt) {
                console.log(evt.data);
            };
        }
     else {
        alert('유저명을 입력하세요.');
        $('#user').focus();
    }
});

function print(user, txt) {
    let temp = '';
    temp += '<div style="margin-bottom:3px;">';
    temp += '[' + user + '] ';
    temp += txt;
    temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
    temp += '</div>';
    $('#list').append(temp);
    $('#list').scrollTop($('#list').prop('scrollHeight')); 
}
    
function print2(user) {
    let temp = '';
    temp += '<div style="margin-bottom:3px;">';
    temp += "'" + user + "' 이(가) 접속했습니다." ;
    temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
    temp += '</div>';
    $('#list').append(temp);
    $('#list').scrollTop($('#list').prop('scrollHeight')); 
}

function print3(user) {
    let temp = '';
    temp += '<div style="margin-bottom:3px;">';
    temp += "'" + user + "' 이(가) 종료했습니다." ;
    temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
    temp += '</div>';
    $('#list').append(temp);
    $('#list').scrollTop($('#list').prop('scrollHeight')); 
}

$('#user').keydown(function() {
    if (event.keyCode == 13) {
        $('#btnConnect').click();
    }
});
        
$('#msg').keydown(function() {
    if (event.keyCode == 13) {
        ws.send('2#' + $('#user').val() + '#' + $('#msg').val());
        $('#msg').val('');
    }
});

$('#btnDisconnect').click(function() {
    if (ws != null) {
        ws.send('3#' + $('#user').val() + '#');
        ws.close();
        ws = null;
        $('#user').attr('readonly', false);
        $('#btnConnect').attr('disabled', false);
        $('#btnDisconnect').attr('disabled', true);
        $('#msg').attr('disabled', true);
    }
});
window.addEventListener('beforeunload', function (e) {
    e.preventDefault();
    e.returnValue = '채팅 세션이 종료될 수 있습니다. 페이지를 새로고침하시겠습니까?';
});

    </script>
</body>
</html>
