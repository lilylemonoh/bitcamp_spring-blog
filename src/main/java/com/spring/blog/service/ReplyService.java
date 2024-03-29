package com.spring.blog.service;

import com.spring.blog.dto.ReplyResponseDTO;
import com.spring.blog.dto.ReplyCreateRequestDTO;
import com.spring.blog.dto.ReplyUpdateRequestDTO;
import com.spring.blog.entity.Reply;

import java.util.List;

public interface ReplyService {

    // 글 번호 입력 시 전체 조회해서 리턴해주는 findAllByBlogId() 메서드 정의
//    List<ReplyResponseDTO> findAllByBlogId(long blogId); // <--- myBatis
    List<Reply> findAllByBlogId(long blogId); // <--- jpa

    // 단일 댓글 번호 입력 시, 댓글 정보를 리턴해 주는 findByReplyId() 메서드 정의
//    ReplyResponseDTO findByReplyId(long replyId);

    Reply findByReplyId(long replyId);

    // 댓글번호 입력 시 삭제되도록 해주는 deleteByReplyId() 메서드 정의
    void deleteByReplyId(long replyId);

    // insert 용도로 정의한 DTO를 넘겨서 save() 메서드 정의
//    void save(ReplyCreateRequestDTO replyInsertDTO);

    void save(Reply reply);

    // update 용도로 정의한 DTO를 넘겨서 uppdate 메서드 정의
//    void update(ReplyUpdateRequestDTO replyUpdateDTO);

    void update(Reply reply);

}
