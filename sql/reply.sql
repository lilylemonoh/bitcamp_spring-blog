##
CREATE TABLE reply(
                      reply_id int primary key auto_increment,
                      blog_id int not null,
                      reply_writer varchar(40) not null,
                      reply_content varchar(200) not null,
                      published_at datetime default now(),
                      updated_at datetime default now()
);

# 외래키 설정
# blog_id에는 기존에 존재하는 글의 blog_id만 들어가야 한다.
alter table reply add constraint fk_reply foreign key (blog_id) references blog(blog_id);

# 더미 데이터 입력 (TEST DB ONLY)
INSERT INTO reply VALUES(null, 2, "댓글쓴사람", "댓글 테스트 중입니다", now(), now()),
(null, 2, "짹짹이", "짹짹짹", now(), now()),
(null, 2, "바둑이", "멍멍멍", now(), now()),
(null, 2, "야옹이", "야옹야옹야옹", now(), now()),
(null, 3, "개발고수", "REST 서버 개발 공부중입니다", now(), now());
