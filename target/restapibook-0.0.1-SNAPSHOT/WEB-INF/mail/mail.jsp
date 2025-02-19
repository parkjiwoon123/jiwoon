<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Email Form</title>
    <style>
        main {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }
      
        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }
        form input[type="text"], form input[type="email"], form textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        form button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<main>
    <header>관리자에게 하고싶은 말</header>
    <form id="emailForm" action="/mailproc" method="POST">
		
        <input type="text" name="title" placeholder="title" required>
        <textarea name="msg" placeholder="Message" rows="5" required></textarea>
        <button type="submit">Send Email</button>
    </form>

</main>
</html>
