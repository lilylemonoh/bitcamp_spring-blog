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
        <form action="/login" method="POST">
            <div class="col-3">
                <!--아이디는 username, 비밀번호는 password로 고정 (필드에는 username이 없음- 스프링시큐리티에 맞춘 것이다.)
                토큰 기반에서는 엔터티에서 사용하는 로그인 명칭으로 바꿉니다.-->
                <input type="text" name="loginId" placeholder="아이디">
            </div>
            <div class="col-3">
                <input type="password" name="password" placeholder="비밀번호">
            </div>
            <input type="submit" value="로그인하기"><br>
            <a href="/signup"> 회원가입하기 </a>

        </form>

        <!-- 소셜로그인은 /oauth2/authorization/서비스명
        형식으로 링크를 걸어주시면 됩니다. -->
        <a href="/oauth2/authorization/google">구글 로그인</a>
    </div>



</body>
</html>