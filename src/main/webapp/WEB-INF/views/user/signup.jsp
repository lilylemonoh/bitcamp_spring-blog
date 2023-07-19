<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class=".container">
    <form action="/signup" method="POST">
        <div><input type="text" name="loginId" placeholder="아이디를 입력하세요" required></div>
        <div><input type="email" name="email" placeholder="이메일을 입력하세요" required></div>
        <div><input type="password" name="password" placeholder="비밀번호를 입력하세요" required></div>
        <div><input type="submit" class="btn btn-primary" value="회원가입하기"></div>
    </form>
</div>
</body>
</html>