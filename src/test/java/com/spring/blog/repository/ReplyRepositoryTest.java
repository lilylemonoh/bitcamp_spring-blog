package com.spring.blog.repository;

import com.spring.blog.dto.ReplyFindByIdDTO;
import com.spring.blog.dto.ReplyInsertDTO;
import com.spring.blog.dto.ReplyUpdateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired // 테스트 코드에서는 필드 주입을 써도 무방합니다.
    ReplyRepository replyRepository;

    @Test
    @Transactional
    @DisplayName("2번 글에 연동된 댓글 개수가 4개인지 확인")
    public void findAllByBlogIdTest(){
        // given : 2번 글을 조회하기 위한 fixture 저장
        long blogId = 2;
        // when : findAllByBlogId() 호출 및 결과 자료 저장
        List<ReplyFindByIdDTO> result = replyRepository.findAllByBlogId(blogId);
        // then : 2번 글에 연동된 댓글이 4개일 것이라고 단언
        assertEquals(4, result.size());
    }

    @Test
    @Transactional
    @DisplayName("댓글번호 3번의 id는 3, 작성자는 바둑이인지 확인")
    public void findByReplyIdTest(){
        // given : 3번 댓글 조회를 위한 fixture
        long replyId = 3;
        // when : 호출 및 결과 저장
        ReplyFindByIdDTO result = replyRepository.findByReplyId(replyId);
        // then : id는 3번, 글쓴이는 바둑이일 것이다
        assertEquals(3, result.getReplyId());
        assertEquals("바둑이", result.getReplyWriter());
    }

    @Test
    @Transactional
    @DisplayName("댓글번호 2번을 삭제한 다음, 2번 글 전체 댓글 개수가 3개인지, 그리고 2번으로 재조회 시 null인지 여부")
    public void deleteByReplyIdTest(){
        //given : 2번 댓글, 2번 글 조회를 위한 fixture
        long replyId = 2;
        long blogId = 2;
        // when : 2번 댓글 삭제
        replyRepository.deleteByReplyId(replyId);
        // then : 2번 글의 전체 댓글 개수 3개, 2번으로 재조회 시 null
        assertEquals(3, replyRepository.findAllByBlogId(blogId).size());
        assertThat(replyRepository.findByReplyId(replyId)).isNull();
    }

    @Test
    @Transactional
    @DisplayName("픽스쳐를 이용해 INSERT후, 전체 데이터를 가져와서 마지막 인덱스 번호 요소를 얻어와서" +
            "입력했던 fixture와 비교하면 같다")
    public void saveTest(){
        // given : fixture 세팅한 다음 ReplyInsertDTO 생성 후 멤버변수 초기화
        int blogId = 1;
        String replyWriter = "스프링";
        String replyContent = "댓글 인서트 테스트 중";
        ReplyInsertDTO replyInsertDTO = ReplyInsertDTO.builder()
                .blogId(blogId)
                .replyWriter(replyWriter)
                .replyContent(replyContent)
                .build();
        // when : insert 실행
        replyRepository.save(replyInsertDTO);
        // then : blogId번 글의 전체 댓글을 가지고 온 다음 마지막 인덱스 요소만 변수에 저장한 다음
        // getter를 이용해 위에서 넣은 fixture와 일치하는지 체크
        List<ReplyFindByIdDTO> replyList = replyRepository.findAllByBlogId(blogId);
        int lastIndex = replyList.size()-1;
        assertEquals(replyWriter, replyList.get(lastIndex).getReplyWriter());

        // 강사님 코드----
        List<ReplyFindByIdDTO> resultList = replyRepository.findAllByBlogId(blogId);
        // resultList의 개수 -1이 마지막 인덱스 번호이므로, resultList에서 마지막 인덱스 요소만 가져오기
        ReplyFindByIdDTO result = resultList.get(resultList.size()-1);
        // 단언문 작성
        assertEquals(replyWriter,result.getReplyWriter());
        assertEquals(replyContent, result.getReplyContent());
    }

    @Test
    @Transactional
    @DisplayName("fixture로 수정할 댓글쓴이, 댓글내용, 3번 replyId를 지정합니다. " +
            "수정 후 3번자료를 DB에서 꺼내 fixture비교 및 published_at과 updated_at이 다른지 확인")
    public void updateTest(){
        // given
        long replyId = 3;
        String replyWriter = "멍멍이";
        String replyContent = "댓글 수정 테스트입니다";
        ReplyUpdateDTO replyUpdateDTO = ReplyUpdateDTO.builder()
                .replyId(replyId)
                .replyWriter(replyWriter)
                .replyContent(replyContent)
                .build();
        // when
        replyRepository.update(replyUpdateDTO);
        // then
        ReplyFindByIdDTO result = replyRepository.findByReplyId(replyId);
        assertEquals(replyContent, result.getReplyContent());
        assertEquals(replyWriter, result.getReplyWriter());
//        assertNotEquals(result.getUpdatedAt(), result.getPublishedAt()); - 내가 짠 코드
        assertTrue(result.getUpdatedAt().isAfter(result.getPublishedAt()));
        //updatedAt이 publishedAt보다 이후 시점(after)이다
    }

}
