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

    ${blogList}
<div class="container">
    <table class="table">
      <thead>
        <tr>
          <th>번호</th>
          <th>작성자</th>
          <th>제목</th>
          <th>내용</th>
          <th>작성일</th>
          <th>수정일</th>
          <th>조회수</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="blog" items="${blogList}">
          <tr>
            <td>${blog.blogId}</td>
            <td>${blog.writer}</td>
            <td>${blog.blogTitle}</td>
            <td>${blog.blogContent}</td>
            <td>${blog.publishedAt}</td>
            <td>${blog.updatedAt}</td>
            <td>${blog.blogCount}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>