
# Blog Table create query
CREATE TABLE IF NOT EXISTS blog(
    blog_id int auto_increment primary key,
    writer varchar(16) not null,
    blog_title varchar(200) not null,
    blog_content varchar(4000) not null,
    published_at datetime default now(),
    updated_at datetime default now(),
    blog_count int default 0
    );

# Dummy Data input query (TEST DB ONLY)
INSERT INTO blog VALUES
                     (null, '1번유저', '1번제목', '1번본문', now(), now(), null),
                     (null, '2번유저', '2번제목', '2번본문', now(), now(), null),
                     (null, '3번유저', '3번제목', '3번본문', now(), now(), null);