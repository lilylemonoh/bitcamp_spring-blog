package com.spring.blog.controller;

import com.spring.blog.dto.ReplyFindByIdDTO;
import com.spring.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    // 컨트롤러는 서비스를 호출합니다.
    private ReplyService replyService;

    public ReplyController(ReplyService replyService){
        this.replyService = replyService;
    }

    // 글 번호에 맞는 전체 댓글을 가져오는 메서드
    // 어떤 자원에 접근할 것인지만 uri에 명시 (메서드가 행동을 결정함)
    // http://localahost:8080/reply/{번호}/all
    // http://localhost:8080/reply/300/all => blogId 파라미터에 300이 전달된 것으로 간주한다.
    @GetMapping("/{blogId}/all")
    //rest 서버는 응답 시 응답 코드와 응답 객체를 넘기기 때문에
    //ResponseEntity<자료형>을 리턴합니다.
    public ResponseEntity<List<ReplyFindByIdDTO>> findAllReplies(@PathVariable long blogId){
        // 서비스에서 리플 목록을 들고 옵니다.
        List<ReplyFindByIdDTO> replies = replyService.findAllByBlogId(blogId);

        return ResponseEntity
                .ok() // 200 코드
                .body(replies); // 리플목록
    }



}
