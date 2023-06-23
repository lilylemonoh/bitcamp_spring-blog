package com.spring.blog.service;

import com.spring.blog.dto.ReplyResponseDTO;
import com.spring.blog.dto.ReplyCreateRequestDTO;
import com.spring.blog.dto.ReplyUpdateRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyServiceTest {

    // 서비스 객체 세팅해주세요
    @Autowired
    ReplyService replyService;

    // findAllByBlogIdTest()는 ReplyRepositoryTest 코드를 참조해서 작성해주세요
    @Test
    @Transactional
    @DisplayName("2번 글의 댓글을 찾으면 개수가 4개이다")
    public void findAllByBlogIdTest(){
        //given : fixture 세팅
        long blogId = 2;
        //when : 2번글의 댓글 전부 가져오기
        List<ReplyResponseDTO> replyList = replyService.findAllByBlogId(blogId);
        //then : 개수는 4개일 것
        assertEquals(4, replyList.size());
    }

    @Test
    @Transactional
    @DisplayName("댓글 번호 5번으로 조회 시 댓글쓴이는 개발고수이고, replyId는 5일 것이다")
    public void findByReplyIdTest(){
        //given : fixture 세팅
        long replyId = 5;
        String replyWriter = "개발고수";
        //when : 댓글번호 5번 가져오기
        ReplyResponseDTO result = replyService.findByReplyId(replyId);
        //then : 댓글쓴이는 개발고수
        assertEquals(replyWriter, result.getReplyWriter());
        assertEquals(replyId, result.getReplyId());
    }

    @Test
    @Transactional
    @DisplayName("댓글번호 4번 삭제 후 2번 글 전체 댓글 3개인지 확인, 4번으로 조회 시 null인지 확인")
    public void deleteByReplyIdTest(){
        //given: fixture 세팅
        long replyId = 4;
        long blogId = 2;
        //when: 삭제 로직 실행
        replyService.deleteByReplyId(replyId);
        //then: 2번 글의 개수 3개, 댓글번호 4번으로 조회하면 null
        assertEquals(3, replyService.findAllByBlogId(blogId).size());
        assertNull(replyService.findByReplyId(replyId));
    }

    @Test
    @Transactional
    @DisplayName("3번 글에 댓글 추가 시 3번 글의 댓글 2개, 입력된 픽스쳐와 멤버변수 일치")
    public void saveTest(){
        //given : save할 객체 생성
        long blogId = 3;
        String replyWriter = "테스트";
        String replyContent = "서비스 레이어 단위 테스트 중";
        ReplyCreateRequestDTO replyInsertDTO = ReplyCreateRequestDTO.builder()
                .blogId(blogId)
                .replyWriter(replyWriter)
                .replyContent(replyContent)
                .build();
        //when: save 로직 실행
        replyService.save(replyInsertDTO);
        //then : 댓글쓴이, 댓글 내용 일치여부 확인

        List<ReplyResponseDTO> replyList = replyService.findAllByBlogId(blogId);
        int lastIndex = replyList.size()-1;
        assertEquals(2, replyList.size());
        assertEquals(replyContent, replyList.get(lastIndex).getReplyContent());
        assertEquals(replyWriter, replyList.get(lastIndex).getReplyWriter());

        // 강사님 코드
        ReplyResponseDTO result = replyList.get(replyList.size()-1);
        assertEquals(replyWriter, result.getReplyWriter());
        assertEquals(replyContent, result.getReplyContent());
    }

    @Test
    @Transactional
    @DisplayName("1번 댓글수정 후 1번 댓글 자료를 꺼내 fixture 비교, published_at과 updated_at이 " +
            "다른지 확인")
    public void updateTest() {
        //given : 수정할 fixture 세팅
        long replyId = 1;
        String replyWriter = "수수정";
        String replyContent = "수정 수정";
//        ReplyUpdateDTO replyUpdateDTO = ReplyUpdateDTO.builder()
//                .replyId(replyId)
//                .replyWriter(replyWriter)
//                .replyContent(replyContent).build();
        //간만에 세터로
        ReplyUpdateRequestDTO replyUpdateDTO = new ReplyUpdateRequestDTO();
        replyUpdateDTO.setReplyId(replyId);
        replyUpdateDTO.setReplyWriter(replyWriter);
        replyUpdateDTO.setReplyContent(replyContent);
        //when : 수정 로직 실행
        replyService.update(replyUpdateDTO);
        //then
        ReplyResponseDTO result = replyService.findByReplyId(replyId);
        assertEquals(replyWriter, result.getReplyWriter());
        assertEquals(replyContent, result.getReplyContent());
        assertTrue(result.getUpdatedAt().isAfter(result.getPublishedAt()));
    }


}
