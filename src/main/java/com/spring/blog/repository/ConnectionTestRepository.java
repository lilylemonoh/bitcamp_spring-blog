package com.spring.blog.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 빈 컨테이너에 MyBatis 관리 파일로서 적재
public interface ConnectionTestRepository {

    // getNow()실행 시 호출할 SQL 구문은 xml 파일 내부에 작성합니다.
    String getNow();

}
