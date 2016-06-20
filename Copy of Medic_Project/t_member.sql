
/* Drop Tables */

DROP TABLE t_article CASCADE CONSTRAINTS;
DROP TABLE t_comment CASCADE CONSTRAINTS;
DROP TABLE t_follow CASCADE CONSTRAINTS;
DROP TABLE t_member CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_article;
DROP SEQUENCE seq_comment;
DROP SEQUENCE seq_follow;




/* Create Sequences */

CREATE SEQUENCE seq_article;
CREATE SEQUENCE seq_comment;
CREATE SEQUENCE seq_follow;



/* Create Tables */

CREATE TABLE t_article
(
	no number DEFAULT seq_article.nextval() NOT NULL UNIQUE,
	content varchar2(300) NOT NULL,
	user_id varchar2(20) NOT NULL,
	image varchar2(150),
	video varchar2(150),
	-- 위도,세로
	latitude number,
	-- 경도, 가로
	longitude number,
	hashtag varchar2(200),
	category varchar2(10) NOT NULL,
	good number DEFAULT 0,
	regdate date DEFAULT sysdate,
	PRIMARY KEY (no)
);


CREATE TABLE t_comment
(
	no number NOT NULL UNIQUE,
	article_no number NOT NULL,
	user_id varchar2(20) NOT NULL,
	content varchar2(300) NOT NULL,
	regdate date DEFAULT sysdate,
	PRIMARY KEY (no)
);


CREATE TABLE t_follow
(
	no number NOT NULL UNIQUE,
	user_id varchar2(20) NOT NULL,
	follow_id varchar2(20) NOT NULL,
	PRIMARY KEY (no)
);


CREATE TABLE t_member
(
	user_id varchar2(20) NOT NULL UNIQUE,
	user_name varchar2(50) NOT NULL,
	user_email varchar2(40) NOT NULL,
	user_gender varchar2(10) NOT NULL,
	user_birth date,
	user_photo varchar2(150),
	regdate date DEFAULT sysdate,
	PRIMARY KEY (user_id)
);



/* Comments */

COMMENT ON COLUMN t_article.latitude IS '위도,세로';
COMMENT ON COLUMN t_article.longitude IS '경도, 가로';

select * from t_follow;



