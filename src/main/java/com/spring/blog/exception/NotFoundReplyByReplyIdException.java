package com.spring.blog.exception;

public class NotFoundReplyByReplyIdException extends RuntimeException{

    // 생성자에 에러 사유를 전달할 수 있도록 메시지를 적습니다.
    public NotFoundReplyByReplyIdException(String message) {
        super(message);
    }
}
