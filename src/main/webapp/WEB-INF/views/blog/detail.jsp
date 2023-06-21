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


    <div class="row">
        <div id="replies">


        </div>
    </div>


    </div><!--.container-->
    <script>
        // 글 구성에 필요한 글번호를 자바스크립트 변수에 저장
        let blogId = "${blog.blogId}";

        // blogId를 받아 전체 데이터를 JS 내부로 가져오는 함수 선언
        function getAllReplies(id) {
            let url = `http://localhost:8080/reply/\${id}/all`;
            //<%-- jsp 내부에서 자바스크립트 쓸 때 백틱 문자열 사용할 때(리터럴 형식)
            // ${}를 쓰려면 앞에 백슬래시 붙여야 한다.
            // 자바스크립트의 id 변수가 필요한 건데, 백슬래시 안 쓰면
            // jsp 내부의 id변수로 해석해버림 (우선순위가 jsp가 높음)--%>

            let str = ""; // 받아온 json을 표현할 html 코드를 저장할 문자열 srt 선언

            fetch(url, {method:'get'}) // get 방식으로 위 주소에 요청 넣기
            .then((res) => res.json()) // 응답 받은 요소 중 json만 뽑기
            .then((replies) => { // 뽑아온 json으로 처리작업하기
                console.log(replies);
            //    for(reply of replies) {
            //    console.log(reply);
            //    console.log("-------");
            //        str += `<h3> 글쓴이 : \${reply.replyWriter},
            //            댓글내용 : \${reply.replyContent}</h3>`;
            //    }

                // .map()을 이용한 간결한 반복문 처리
                replies.map((reply, i) => { // 첫 파라미터 : 반복대상자료,
                    // 두 번째 파라미터 : 순번
                    str += `<h3> \${i+1}번째 댓글 || 글쓴이 : \${reply.replyWriter},
                        댓글내용 : \${reply.replyContent}</h3>`;
                }

                )
                
                console.log(str); // 저장된 태그 확인
                // #(id) replies 요소를 변수에 저장해주세요
                const $replies = document.getElementById('replies');
                // 저장된 #replies의 innerHTML에 str을 대입해 실제 화면에 출력되게 해주세요
                $replies.innerHTML = str;
            });
        }
        // 함수 호출
        getAllReplies(blogId);


    </script>

</body>
</html>