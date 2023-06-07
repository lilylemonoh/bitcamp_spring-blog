<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- CSS only -->
    <style>
        div {
            border : 1px solid black;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <!-- 강사님 코드 -->
    <div class="row first-row">
        <div class="col-1">
            글번호
        </div>
        <div class="col-1">
            ${blog.blogId}
        </div>
        <div class="col-1">
            글제목
        </div>
        <div class="col-5">
            ${blog.blogTitle}
        </div>
        <div class="col-1">
            작성자
        </div>
        <div class="col-1">
            ${blog.writer}
        </div>
        <div class="col-1">
            조회수
        </div>
        <div class="col-1">
            ${blog.blogCount}
        </div>
    </div>
    <div class="row second-row">
        <div class="col-1">
            작성일
        </div>
        <div class="col-5">
            ${blog.publishedAt}
        </div>
        <div class="col-1">
            수정일
        </div>
        <div class="col-5">
            ${blog.updatedAt}
        </div>
    </div>
    <div class="row third-row">
        <div class="col-1">
            본문
        </div>
        <div class="col-11">
            ${blog.blogContent}
        </div>
    </div>


    <!-- 내가 짠 것 주석처리
    <div class="row">
    <h3 class="text-center">${blog.blogTitle}</h3>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>${blog.blogId}</th>
            <th>${blog.writer}</th>
            <th>작성 : ${blog.publishedAt}</th>
            <th>수정 : ${blog.updatedAt}</th>
            <th>${blog.blogCount} 조회</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="5">${blog.blogContent}</td>
        </tr>
        </tbody>
    </table>
-->





    <div class="row fourth-row">
        <div class="col">
            <a href="/blog/list"><button class="btn btn-secondary">목록으로</button></a>
        </div>
        <div class="col">
            <form action="/blog/delete" method="POST">
                <input type="hidden" name="blogId" value="${blog.blogId}">
                <input type ="submit" class="btn btn-warning" value="삭제하기">
            </form>
        </div>
        <div class="col">
            <form action="/blog/updateform" method="POST">
                <input type="hidden" name="blogId" value="${blog.blogId}">
                <input type ="submit" class="btn btn-info" value="수정하기">
            </form>
        </div>
    </div>












</div><!--.container-->
</body>
</html>