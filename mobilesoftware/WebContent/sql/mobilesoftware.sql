--홈페이지 회원 DB
create table member (
  id   			varchar2(15) not null, --아이디 
  pass  		varchar2(60) not null, --암호
  name  		varchar2(20) not null, --이름
  hp    		varchar2(20)  not null,--핸드폰
  email 		varchar2(80),		   --이메일
  regist_day 	date,				   --가입일
  m_level 		NUMBER,				   --권한레벨 ex)학생1, 직원2
  primary key(id)
 );
 insert into member values('admin','1111','관리자','041-533-3219','heseomobil@gmail.com',sysdate,3);
 -----------------------------------------------------------
--학과 게시판
create table majorBoard (
   mBnum 		NUMBER 			not null, --게시판 번호 부여
   id 			varchar2(15) 	not null, --작성자 id
   name  		varchar2(20) 	not null, --작성자 이름
   subject 		varchar2(100) 	not null, --제목
   content		varchar2(4000) 	not null, --내용
   regist_day 	date,			  		  --작성일
   classes 		varchar2(20),			  --과목
   hit 			NUMBER,					  --조회수
   primary key(mBnum),
   CONSTRAINT  FK_MMB_ID  FOREIGN KEY(id)  
   REFERENCES member(id)
);
create sequence mb_seq
increment by 1
start with 1;
--------------------------------------------------------------
--학과 리플
create table majorRipple(
	mRnum		NUMBER 			primary key,
	id 			varchar2(15) 	not null, --작성자 id
    name  		varchar2(20) 	not null, --작성자 이름
	reg_date 	date			NOT NULL,
	--계층형 게시판 사용컬럼
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--계층형 게시판 사용컬럼 END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MMR_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence mr_seq
increment by 1
start with 1;
--------------------------------------------------------
--학과 게시판
create table notice (
   nnum 		NUMBER 			not null, --게시판 번호 부여
   id 			varchar2(15) 	not null, --작성자 id
   name  		varchar2(20) 	not null, --작성자 이름
   subject 		varchar2(100) 	not null, --제목
   content		varchar2(4000) 	not null, --내용
   regist_day 	date,			  		  --작성일
   topic 		varchar2(20),			  --공지유형
   hit 			NUMBER,					  --조회수
   primary key(nnum),
   CONSTRAINT  FK_MN_ID  FOREIGN KEY(id)  
   REFERENCES member(id)
);
create sequence n_seq
increment by 1
start with 1;
------------------------------------------------------
create table galley (
  gnum    	varchar2(15) 	not null,	--게시판 번호
  id  		varchar2(15) 	not null,	
  name 		varchar2(20) 	not null,
  path  	varchar2(100) 	not null,	--서버에 저장된 경로
  subject  	varchar2(20)  	not null,
  content 	varchar2(200),
  reg_date 	date,
  hit 		NUMBER,
  primary key(gnum),
  CONSTRAINT  FK_MG_ID  FOREIGN KEY(id)  
  REFERENCES member(id)
);
create sequence g_seq
increment by 1
start with 1;
-------------------------------------------------------
--낙서장
create table scribble(
	snum		NUMBER 			primary key,
	id 			varchar2(15) 	not null, --작성자 id
    name  		varchar2(20) 	not null, --작성자 이름
	reg_date 	date			NOT NULL,
	--계층형 게시판 사용컬럼
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--계층형 게시판 사용컬럼 END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MS_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence s_seq
increment by 1
start with 1;
----------------------------------------------
--자주하는 질문
create table qna (
   qnum NUMBER not null,
   id 		varchar2(15) 	not null,
   name  	varchar2(10) 	not null,
   subject 	varchar2(100) 	not null,
   content 	varchar2(4000) 	not null,
   path 	varchar2(100) 	not null,
   reg_date	date,
   hit 		NUMBER,
   primary key(qnum)
);
create sequence q_seq
increment by 1
start with 1;
----------------------------------------------
--1:1문의
create table onetoone (
   onum 	NUMBER not null,
   id 		varchar2(15) not null,
   s_id 	varchar2(15) not null,
   name  	varchar2(10) not null,
   title 	varchar2(100) not null,
   content 	varchar2(4000) not null,
   path 	varchar2(100) not null,
   e_check 	varchar2(1) not null,
   reg_date date,
   hit 		NUMBER,
   primary key(onum),
   CONSTRAINT  FK_MO_ID  FOREIGN KEY(id)  
   REFERENCES member(id)
);
create sequence o_seq
increment by 1
start with 1;

create table otoRipple(
	oRnum		NUMBER 			primary key,
	id 			varchar2(15) 	not null, --작성자 id
    name  		varchar2(20) 	not null, --작성자 이름
	reg_date 	date			NOT NULL,
	--계층형 게시판 사용컬럼
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--계층형 게시판 사용컬럼 END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MOR_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence or_seq
increment by 1
start with 1;





  