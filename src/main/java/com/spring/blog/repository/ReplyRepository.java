package com.spring.blog.repository;

import com.spring.blog.dto.ReplyFindByIdDTO;
import com.spring.blog.dto.ReplyInsertDTO;
import com.spring.blog.dto.ReplyUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyRepository {

    List<ReplyFindByIdDTO> findAllByBlogId(long blogId);

    // 댓글 번호 입력 시 특정 댓글 하나만 가져오는 메서드 findByReplyId를 선언해주세요.
    // 댓글 삭제, 댓글 수정을 위해 필요
    ReplyFindByIdDTO findByReplyId(long replyId);

    void deleteByReplyId(long replyId);

    // 삽입구문은 ReplyInsertDTO를 이용하여 save 메서드 정의
    // ReplyInsertDTO에 내장된 멤버변수인 blogId(몇번 글에), replyWriter(누가),
    // replyContent(무슨 내용) 들을 전달해서 INSERT 구분을 완성시키기 위함
    void save(ReplyInsertDTO replyInsertDTO);

    // 수정로직은 ReplyUpdateDTO를 이용해서 update 메서드를 호출해 처리합니다.
    // 수정로직은 replyId를 WHERE 절에 집어넣고, replyWriter, replyContent의 내용을 업데이트해주고
    // updatedAt 역시 now()로 바꿔줍니다.
    void update(ReplyUpdateDTO replyUpdateDTO);

}
