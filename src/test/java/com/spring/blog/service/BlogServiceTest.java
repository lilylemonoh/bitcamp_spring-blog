package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Test
    @Transactional // 이 테스트의 결과가 디비 커밋을 하지 않음
    public void findAllTest(){
        // given : 없음

        // when : 전체 데이터 가져오기
        List<Blog> blogList = blogService.findAll();
        // then : 길이가 3일 것이다.
//        assertEquals(3, blogList.size()); 아래 코드와 동일함
        assertThat(blogList.size()).isEqualTo(3); //import assertj ...
    }


    //findById를 직접 테스트하는 코드를 작성해주세요. @Transactional 어노테이션도 추가해주세요.
    // select이므로 @Transactional을 안 붙여도 되는데 붙이는 이유?
    // 1. 테스트 코드들의 일관성을 위해서
    // 2. 트랜잭션의 ACID 원칙을 위해서 - 격리성 원칙을 지키기 위해. INSERT 구문을 실행하는 중 SELECT가 끼어들거나 하지 않도록.
    @Test
    @Transactional
    public void findByIdTest(){
        // given : 조회할 번호인 2번 변수에 저장, 예상되는 글쓴이, 본문정보 저장
        long blogId = 2;
        String writer = "2번유저";
        String blogTitle = "2번제목";
        // when : DB에서 2번 유저 얻어오기
        Blog blog = blogService.findById(blogId);
        // then : 얻어온 유저의 번호는 blogId 변수, 글쓴이는 writer 변수, 제목은 blogTitle변수에 든 값일 것이다.
        assertThat(blog.getWriter()).isEqualTo(writer);
        assertThat(blog.getBlogId()).isEqualTo(blogId);
        assertEquals(blogTitle, blog.getBlogTitle());
    }

    //deleteById에 대해서 테스트코드 작성
    @Test
    @Transactional
    // @Commit // 트랜잭션 적용된 테스트의 결과를 커밋해서 디비에 반영하도록 만듦 (트랜잭션이 걸렸을 때만 사용)
    public void deleteByIdTest(){
        // given : 삭제할 번호인 2번 변수에 저장
        long blogId = 2;
        // when : DB에서 2번 유저 삭제하기
        blogService.deleteById(blogId);
        // then : 2번으로 조회하면 null, 전체 개수 2개
        assertNull(blogService.findById(blogId));
        assertEquals(2, blogService.findAll().size());
    }

    // 저장로직에 대해서 테스트코드를 빌더패턴을 써서 작성해주세요.
    @Test
    @Transactional
    public void saveTest(){
        // given : Blog 객체에 필요데이터인 writer, blogTitle, blogContent를 주입해 builder패턴으로 생성합니다.
        String writer = "추가할 글쓴이";
        String blogTitle = "추가할 제목";
        String blogContent = "추가할 내용";
        Blog blog = Blog.builder()
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();
        int lastBlogIndex = 0;
        // when : save()를 호출해 DB에 저장합니다.
        blogService.save(blog);
        // then : 전체 요소의 개수가 4개인지 확인하고, 현재 얻어온 마지막 포스팅의 writer, blogTitle, blogContent가
        // 생성 시 사용한 자료와 일치하는지 확인
        assertEquals(4, blogService.findAll().size());
        assertEquals(writer, blogService.findAll().get(lastBlogIndex).getWriter());
        assertEquals(blogTitle,  blogService.findAll().get(lastBlogIndex).getBlogTitle());
        assertEquals(blogContent,  blogService.findAll().get(lastBlogIndex).getBlogContent());
    }

    // update() 메서드를 테스트하는 코드를 작성해주세요
    @Test
    @Transactional
    public void updateTest(){
        //given : 2번 글을 수정하기 위해 blogId, blogTitle, blogContent의 fixture 생성하여 빌더패턴으로 Blog 객체 생성
        long blogId = 2;
        String blogTitle = "2번제목 수정";
        String blogContent = "2번내용 수정";
        Blog blog = Blog.builder()
                .blogId(blogId)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();
        // when : 수정한 Blog 객체 DB에 반영
        blogService.update(blog);
        // then : blogId번 글을 가져와서 blogTitle, blogContent가 수정을 위한 픽스쳐와 동일하다고 단언.
        assertEquals(blogId, blogService.findById(blogId).getBlogId());
        assertEquals(blogTitle,blogService.findById(blogId).getBlogTitle());
        assertEquals(blogContent, blogService.findById(blogId).getBlogContent());
    }


}
