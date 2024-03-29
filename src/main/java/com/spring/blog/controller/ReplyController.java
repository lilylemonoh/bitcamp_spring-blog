package com.spring.blog.controller;

import com.spring.blog.dto.ReplyResponseDTO;
import com.spring.blog.dto.ReplyCreateRequestDTO;
import com.spring.blog.dto.ReplyUpdateRequestDTO;
import com.spring.blog.entity.Reply;
import com.spring.blog.exception.NotFoundReplyByReplyIdException;
import com.spring.blog.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Reply>> findAllReplies(@PathVariable long blogId){
        // 서비스에서 리플 목록을 들고 옵니다.
//        List<ReplyResponseDTO> replies = replyService.findAllByBlogId(blogId);
        List<Reply> replies = replyService.findAllByBlogId(blogId);

        return ResponseEntity
                .ok() // 200 코드
                .body(replies); // 리플목록
        // 두 줄을 합쳐서 .ok(replies);로 써도 잘 돌아간다.(상태코드와 body에 전송할 데이터 같이 작성)
    }

    //replyId를 주소에 포함시켜서 요청하면 해당 번호 댓글 정보를 json으로 리턴하는 메서드
    // 예시) /reply/5 -> replyId 변수에 5가 대입되도록 주소 설정 및 메서드 선언
    @GetMapping("/{replyId}")
    public ResponseEntity<?> findByReplyId(@PathVariable long replyId){

        // 서비스에서 특정 번호 리플을 가져옵니다.
//        ReplyResponseDTO replyFindByIdDTO = replyService.findByReplyId(replyId);

        Reply replyFindByIdDTO = replyService.findByReplyId(replyId);
        if(replyFindByIdDTO == null ) {
            try {
                throw new NotFoundReplyByReplyIdException("없는 댓글 번호를 조회했습니다");
            } catch (NotFoundReplyByReplyIdException e) {
                e.printStackTrace();
                return new ResponseEntity<>("찾는 댓글 번호가 없습니다", HttpStatus.NOT_FOUND);
            }
        }
//        return new ResponseEntity<ReplyFindByIdDTO>(replyFindByIdDTO, HttpStatus.OK);
//          위와 같은 코드임
            return ResponseEntity
                    .ok(replyFindByIdDTO);
    }

    // post 방식으로 /reply 주소로 요청이 들어왔을 때 실행되는 메서드 insertReply()를 작성해주세요.
    @PostMapping("")
    // RestController는 데이터를 json으로 주고받음. 따라서 @RequestBody 를 이용해 json으로 들어온 데이터를 역직렬화 하도록 설정
//    public ResponseEntity<String> insertReply(@RequestBody ReplyCreateRequestDTO replyInsertDTO){
        public ResponseEntity<String> insertReply(@RequestBody Reply replyInsertDTO){

        replyService.save(replyInsertDTO);

        return ResponseEntity
                .ok("댓글 등록이 잘 되었습니다.");
    }


    // delete 방식으로 /reply/{댓글번호} 주소로 요청이 들어왔을 때 실행되는 메서드 deleteReply()를 작성해주세요

    @DeleteMapping(value = {"/{replyId}", "/{replyId}/"})
    public ResponseEntity<String> deleteReply(@PathVariable long replyId){

        replyService.deleteByReplyId(replyId);

        return ResponseEntity
                .ok("댓글 삭제가 완료되었습니다");


    }

    // 수정로직은 put, patch 메서드로 /reply/{댓글번호} 주소로
    // ReplyUpdateRequestDTO를 requestBody로 받아 요청처리를 하게 만들어주세요.

//    @PutMapping("/{replyId}")
//    @PatchMapping("/{replyId}")
    // 이렇게 쓰니까 테스트 통과를 못해서... PutMapping을 주석 처리하니까 통과했다(테스트코드에서 patch사용)
    // PUT, PATCH 두 메서드 다 가능하게 하려면 아래 방식으로 작성해야 한다.
//    @RequestMapping(value="/{replyId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
//    public ResponseEntity<String> updateReply(@PathVariable long replyId,
//                                              @RequestBody ReplyUpdateRequestDTO replyUpdateRequestDTO){
//
//        // json 데이터에 replyId를 포함하는 대신 url에 포함시켰으므로 requestBody에 추가해줘야 함
//
//        System.out.println("주입 전" + replyUpdateRequestDTO);
//        replyUpdateRequestDTO.setReplyId(replyId);
//        replyService.update(replyUpdateRequestDTO);
//        System.out.println("주입 후" + replyUpdateRequestDTO);
//
//        return ResponseEntity
//                .ok("댓글 수정이 완료되었습니다");
//
//    }

    @RequestMapping(value="/{replyId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateReply(@PathVariable long replyId,
                                              @RequestBody Reply replyUpdateRequestDTO){

        // json 데이터에 replyId를 포함하는 대신 url에 포함시켰으므로 requestBody에 추가해줘야 함

        System.out.println("주입 전" + replyUpdateRequestDTO);
        replyUpdateRequestDTO.setReplyId(replyId);
        replyService.update(replyUpdateRequestDTO);
        System.out.println("주입 후" + replyUpdateRequestDTO);

        return ResponseEntity
                .ok("댓글 수정이 완료되었습니다");

    }



}
