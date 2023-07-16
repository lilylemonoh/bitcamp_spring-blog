package com.spring.blog.service;

import com.spring.blog.dto.ReplyResponseDTO;
import com.spring.blog.dto.ReplyCreateRequestDTO;
import com.spring.blog.dto.ReplyUpdateRequestDTO;
import com.spring.blog.entity.Reply;
import com.spring.blog.repository.ReplyJPARepository;
import com.spring.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    ReplyRepository replyRepository;
    ReplyJPARepository replyJPARepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository, ReplyJPARepository replyJPARepository){
        this.replyRepository = replyRepository;
        this.replyJPARepository = replyJPARepository;
    }

    // JPA 실습 중에 사용하는 기간에 전체적으로 DTO를 쓰던 로직을 전부 Reply를 쓰도록 해 주시면 됩니다.

//    @Override
//    public List<ReplyResponseDTO> findAllByBlogId(long blogId) {
//        return replyRepository.findAllByBlogId(blogId);
//    }

    @Override
    public List<Reply> findAllByBlogId(long blogId) {
        return replyJPARepository.findAllByBlogId(blogId);
    }


//    @Override
//    public ReplyResponseDTO findByReplyId(long replyId) {
//        return replyRepository.findByReplyId(replyId);
//    }

    @Override
    public Reply findByReplyId(long replyId) {
        return replyJPARepository.findById(replyId).get();
    }

//    @Override
//    public void deleteByReplyId(long replyId) {
//        replyRepository.deleteByReplyId(replyId);
//    }

    @Override
    public void deleteByReplyId(long replyId) {
        replyJPARepository.deleteById(replyId);
    }

//    @Override
//    public void save(ReplyCreateRequestDTO replyInsertDTO) {
//        replyRepository.save(replyInsertDTO);
//    }

    @Override
    public void save(Reply reply) {
        replyJPARepository.save(reply);
    }

    //    @Override
//    public void update(ReplyUpdateRequestDTO replyUpdateRequestDTO) {
//        replyRepository.update(replyUpdateRequestDTO);
//    }

    @Override
    public void update(Reply reply) {

        Reply updatedReply = replyJPARepository.findById(reply.getReplyId()).get();
        updatedReply.setReplyWriter(reply.getReplyWriter());
        updatedReply.setReplyContent(reply.getReplyContent());
        replyJPARepository.save(updatedReply);
    }


}
