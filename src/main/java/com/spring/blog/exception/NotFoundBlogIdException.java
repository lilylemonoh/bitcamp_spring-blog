package com.spring.blog.exception;

public class NotFoundBlogIdException extends RuntimeException{
    //RuntimeException을 상속받아서 언체크드 익셉션으로 만든다.

    // 생성자에 에러 사유를 전달할 수 있도록 메시지를 적습니다.
    public NotFoundBlogIdException(String message) {
        super(message);
    }
}
