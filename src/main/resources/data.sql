/*
 카테고리 테이블
 */
INSERT INTO category(name, created_at) VALUES('스마트폰', NOW()) ;
INSERT INTO category(name, created_at) VALUES('컴퓨터', NOW()) ;
INSERT INTO category(name, created_at) VALUES('키오스크', NOW()) ;
INSERT INTO category(name, created_at) VALUES('외국어', NOW()) ;
INSERT INTO category(name, created_at) VALUES('운동', NOW()) ;
INSERT INTO category(name, created_at) VALUES('공예', NOW()) ;
INSERT INTO category(name, created_at) VALUES('부동산', NOW()) ;
INSERT INTO category(name, created_at) VALUES('심리', NOW()) ;
INSERT INTO category(name, created_at) VALUES('취업/창업', NOW()) ;
INSERT INTO category(name, created_at) VALUES('요리', NOW()) ;
INSERT INTO category(name, created_at) VALUES('악기', NOW()) ;
INSERT INTO category(name, created_at) VALUES('자산', NOW()) ;

/*
 세부카테고리 테이블
 */
INSERT INTO sub_category( name, category_id, created_at) VALUES('기본 설정(앱 설치, 환경설정 등)', 1, NOW()) ;
INSERT INTO sub_category( name, category_id, created_at) VALUES('기본 활용(카메라,갤러리 등)', 1, NOW()) ;
INSERT INTO sub_category( name, category_id, created_at) VALUES('실생활 활용(카카오톡,모바일 주문, 배달 앱 등)', 1, NOW()) ;
INSERT INTO sub_category( name, category_id, created_at) VALUES('생활 영어', 4, NOW()) ;


-- 교육 테이블
INSERT INTO lesson(created_at, approved, current_count, description,
                   gather_end_date, gather_start_date,
                   lecture_week_day, lesson_end_date, lesson_end_time, lesson_start_date, lesson_start_time,
                   lesson_type, limit_count, place, price, refund, supplies, title, sub_category_id, gather_status, price_status)
VALUES (NOW(),'APPROVED', 1, '카카오톡의 다양한 기능을 알아보는 과정',
        "2024-12-31", "2024-01-01",
        'MONDAY', NOW(), NOW(), NOW(), NOW(),
        'ONLINE', 30, '노원50플러스센터', 20000, '환불규정', '스마트폰', '카카오톡, 생활에 유용한 앱 활용', 1, TRUE, TRUE);

INSERT INTO lesson(created_at, approved, current_count, description,
                   gather_end_date, gather_start_date,
                   lecture_week_day, lesson_end_date, lesson_end_time, lesson_start_date, lesson_start_time,
                   lesson_type, limit_count, place, price, refund, supplies, title, sub_category_id, gather_status, price_status)
VALUES (NOW(), 'REVIEWING', 1, '일반적 표현을 위한 주요 어휘 및 관련 문장 학습',
        NOW(), NOW(),
        'FRIDAY', NOW(), NOW(), NOW(), NOW(),
        'ONLINE', 30, '동작50플러스센터', 15000, '환불규정', '필기구', 'YOU CAN SPEAK 일상표현 영어', 4, FALSE, TRUE);

INSERT INTO lesson(created_at, approved, current_count, description,
                   gather_end_date, gather_start_date,
                   lecture_week_day, lesson_end_date, lesson_end_time, lesson_start_date, lesson_start_time,
                   lesson_type, limit_count, place, price, refund, supplies, title, sub_category_id, gather_status, price_status)
VALUES (NOW(), 'APPROVED', 1, '내 손안에 들어온 작은 컴퓨터_스마트폰을 일상생활에 잘 활용하는 방법',
        NOW(), NOW(),
        'TUESDAY', NOW(), NOW(), NOW(), NOW(),
        'OFFLINE', 30, '서대문50플러스센터', 0, '환불규정', '스마트폰, 개인 충전기', '생활 스마트폰-처음시작', 1, TRUE, FALSE);

-- 강사 테이블
INSERT INTO teacher(birth, gender, name) VALUES (1988, 'WOMAN', '서정희');
INSERT INTO teacher(birth, gender, name) VALUES (1973, 'MAN', '김진형');

INSERT INTO teacher(birth, gender, name) VALUES (1992, 'WOMAN', '정희정');
INSERT INTO teacher(birth, gender, name) VALUES (2001, 'MAN', '조현호');

-- 교육-강사 테이블
INSERT INTO lecture_teacher(lesson_id, teacher_id) VALUES (1, 2);
INSERT INTO lecture_teacher(lesson_id, teacher_id) VALUES (1, 1);
INSERT INTO lecture_teacher(lesson_id, teacher_id) VALUES (2, 3);
INSERT INTO lecture_teacher(lesson_id, teacher_id) VALUES (3, 2);

INSERT INTO file(name, path, represent, lesson_id, member_id) VALUES ('test', 'resources/static/test.png', TRUE, 1, null);
INSERT INTO file(name, path, represent, lesson_id, member_id) VALUES ('test1-f', 'resources/static/test2.png', FALSE, 1, null);

INSERT INTO file(name, lesson_id, member_id) VALUES ('test2', 2, null);
INSERT INTO file(name, lesson_id, member_id) VALUES ('test3', 3, null);

INSERT IGNORE INTO member(id, created_at, updated_at, address, birth_year, email, gender, mail_agree, name, password, phone_num, refresh_token, role, sms_agree, social_id, social_type)
VALUES (1, '2024-01-26 23:50:17.000000', '2024-01-26 23:50:19.000000', '주소', 1999, 'ji@naver.com', 'MALE', true, '이륾', 'qwerty', '010-1234-4646', 'sdadfasdfasdf', 'USER', true, 'asd', 'KAKAO');
INSERT IGNORE INTO member(id, created_at, updated_at, address, birth_year, email, gender, mail_agree, name, password, phone_num, refresh_token, role, sms_agree, social_id, social_type)
VALUES (2, '2024-01-26 23:50:17.000000', '2024-01-26 23:50:19.000000', '주소', 1999, 'ji@naver.com', 'MALE', true, '이륾2', 'qwerty', '010-1234-4646', 'sdadfasdfasdf', 'USER', true, 'asd', 'KAKAO');
INSERT IGNORE INTO member(id, created_at, updated_at, address, birth_year, email, gender, mail_agree, name, password, phone_num, refresh_token, role, sms_agree, social_id, social_type)
VALUES (3, '2024-01-26 23:50:17.000000', '2024-01-26 23:50:19.000000', '주소', 1999, 'ji@naver.com', 'MALE', true, '이륾3', 'qwerty', '010-1234-4646', 'sdadfasdfasdf', 'USER', true, 'asd', 'KAKAO');

INSERT INTO community(created_at, body, category, title, member_id, views) VALUES (NOW(), '소올 내용인디', 'TOGETHER', '소올 제목', 1, 10);
INSERT INTO community(body, category, title, member_id, views) VALUES ('소올 내용인디2', 'QUESTION', '소올 제목', 2, 122);
INSERT INTO community(body, category, title, member_id, views) VALUES ('소올 내용인디3', 'TOGETHER', '소올 제목', 3, 1233);

INSERT INTO community_comment(content, community_id, member_id) VALUES ("1번글 댓글",1, 2);
INSERT INTO community_comment(content, community_id, member_id) VALUES ("2번글 댓글",2, 3);
INSERT INTO community_comment(content, community_id, member_id) VALUES ("3번글 댓글",3, 1);

INSERT INTO news(created_at, category, author, content, title) VALUES ('2024-01-26 23:50:19.000000', 'BOARD', '친절한 쩡아쌤', '생활뉴스 글1', '생활뉴스 제목1');
INSERT INTO news(created_at, category, author, content, title) VALUES ('2024-01-27 23:50:19.000000', 'HEALTH', '친절한 쩡아쌤', '생활뉴스 글2', '생활뉴스 제목2');
INSERT INTO news(created_at, category, author, content, title) VALUES ('2024-01-25 23:50:19.000000','LIFE', '친절한 쩡아쌤', '생활뉴스 글3', '생활뉴스 제목3');
INSERT INTO news(created_at, category, author, content, title) VALUES ('2024-01-23 23:50:19.000000','RECRUIT', '친절한 쩡아쌤', '생활뉴스 글4', '생활뉴스 제목4');

INSERT INTO file(name, lesson_id, member_id, news_id) VALUES ('뉴스 테스트', null, null, 1);
INSERT INTO file(name, path, represent, lesson_id, member_id, news_id) VALUES ('test', 'resources/static/test.png', FALSE, null, null, 1);

INSERT INTO file(name, lesson_id, member_id, news_id) VALUES ('뉴스 테스트', null, null, 2);
INSERT INTO file(name, lesson_id, member_id, news_id) VALUES ('뉴스 테스트', null, null, 3);
INSERT INTO file(name, lesson_id, member_id, news_id) VALUES ('뉴스 테스트', null, null, 4);