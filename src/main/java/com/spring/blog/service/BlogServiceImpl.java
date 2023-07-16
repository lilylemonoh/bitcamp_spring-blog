package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogJPARepository;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.ReplyJPARepository;
import com.spring.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // 빈 컨테이너에 적재
public class BlogServiceImpl implements BlogService{

    BlogRepository blogRepository;

    ReplyRepository replyRepository;

    BlogJPARepository blogJPARepository;
    ReplyJPARepository replyJPARepository;

    @Autowired // 생성자 주입이 속도가 더 빠름
    public BlogServiceImpl(BlogRepository blogRepository, ReplyRepository replyRepository, BlogJPARepository blogJPARepository
    , ReplyJPARepository replyJPARepository) {
        this.blogRepository = blogRepository;
        this.replyRepository = replyRepository;
        this.blogJPARepository = blogJPARepository;
        this.replyJPARepository = replyJPARepository;
    }

//    @Override
//    public List<Blog> findAll() {
//        // List<Blog> blogList = blogRepository.findAll();
//        // return blogList;
////        return blogRepository.findAll(); <- MyBatis를 활용한 전체 글 가져오기
//
//        return blogJPARepository.findAll(); // <- JPA를 활용한 전체 글 가져오기
//    }

    @Override
    public Page<Blog> findAll(Long pageNum) {

        int calibratedPageNum = getCalibratedPageNum(pageNum);
        // 페이징 처리에 관련된 정보를 먼저 객체로 생성합니다.
        Pageable pageable = PageRequest.of((calibratedPageNum - 1),10);
        // 생성된 페이징 정보를 파라미터로 제공해서 findAll()을 호출합니다.
        return blogJPARepository.findAll(pageable);

    }

    // 보정된 pageNum을 가공해주는 메서드
    public int getCalibratedPageNum(Long pageNum){
        // 사용자가 아무것도 안 넣은 경우, 음수나 0을 넣은 경우
        if(pageNum == null || pageNum <= 0L) {
            pageNum = 1L;
            return pageNum.intValue();
        }
            // 총 페이지 개수를 구하는 로직
            int totalPagesCount = (int) Math.ceil(blogJPARepository.count() / 10.0);

            pageNum = pageNum > totalPagesCount ? totalPagesCount : pageNum;

        return pageNum.intValue();

    }



    public Blog findById(long blogId) {
//        return blogRepository.findById(blogId);<- MyBatis

        // JPA의 findById는 Optional을 리턴하므로, 일반 객체로 만들기 위해 뒤에 .get()를 사용합니다.
        // Optional은 참조형 변수에 대해서 null 검사 및 처리를 쉽게 할 수 있도록 제공하는 제네릭입니다.
        // JPA는 Optional을 쓰는 것을 권장하기 위해 리턴 자료형으로 강제해뒀습니다.
        return blogJPARepository.findById(blogId).get(); //<- JPA

    }

    @Transactional // 둘 다 실행되든지 둘 다 실행 안 되든지...
    @Override
    public void deleteById(long blogId) {
//        replyRepository.deleteByBlogId(blogId);
//        blogRepository.deleteById(blogId); <- MyBatis

        // 댓글 삭제가 진행되도록 deleteAllByBlogId()를 ReplyJPARepository에 선언하기
        replyJPARepository.deleteAllByBlogId(blogId);

        blogJPARepository.deleteById(blogId); //<- JPA


    }

    @Override
    public void save(Blog blog) {

//        blogRepository.save(blog); <- MyBatis

        blogJPARepository.save(blog); //<- JPA
    }

    @Override
    public void update(Blog blog) {
        //JPA의 수정은, findById()를 이용해 얻어온 엔티티 클래스의 객체 내부 내용물을 수정한 다음
        // 해당 요소를 save()해서 이뤄집니다
//        blogRepository.update(blog);<- MyBatis

        Blog updatedBlog = blogJPARepository.findById(blog.getBlogId()).get(); // 준영속 상태
        updatedBlog.setBlogTitle(blog.getBlogTitle()); // 커맨드 객체에 들어온 타이틀로 수정
        updatedBlog.setBlogContent(blog.getBlogContent()); // 커맨드 객체에 들어온 컨텐츠로 수정
        blogJPARepository.save(updatedBlog);

    }


}
