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
<div class="container">
    <form action="/blog/update" method="POST">

        <div class="row">
            <div class="col-3">
                <label for="writer" class="form-label">글쓴이</label>
                <input type="text" class="form-control" id="writer" name="writer" value="${blog.writer}" readonly>
            </div>
            <div class="col-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="blogTitle" value="${blog.blogTitle}">
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <label for="content" class="form-label">본문</label>
                <textarea class="form-control" id="content" name="blogContent" rows="20">${blog.blogContent}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <input type="hidden" name="blogId" value="${blog.blogId}">
                <input type="submit" class="btn btn-primary" value="글쓰기">
            </div>
        </div>
    </form>

</div><!--.container-->
</body>
</html>