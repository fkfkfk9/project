--Ȩ������ ȸ�� DB
create table member (
  id   			varchar2(15) not null, --���̵� 
  pass  		varchar2(60) not null, --��ȣ
  name  		varchar2(20) not null, --�̸�
  hp    		varchar2(20)  not null,--�ڵ���
  email 		varchar2(80),		   --�̸���
  regist_day 	date,				   --������
  m_level 		NUMBER,				   --���ѷ��� ex)�л�1, ����2
  primary key(id)
 );
 insert into member values('admin','1111','������','041-533-3219','heseomobil@gmail.com',sysdate,3);
 -----------------------------------------------------------
--�а� �Խ���
create table majorBoard (
   mBnum 		NUMBER 			not null, --�Խ��� ��ȣ �ο�
   id 			varchar2(15) 	not null, --�ۼ��� id
   name  		varchar2(20) 	not null, --�ۼ��� �̸�
   subject 		varchar2(100) 	not null, --����
   content		varchar2(4000) 	not null, --����
   regist_day 	date,			  		  --�ۼ���
   classes 		varchar2(20),			  --����
   hit 			NUMBER,					  --��ȸ��
   primary key(mBnum),
   CONSTRAINT  FK_MMB_ID  FOREIGN KEY(id)  
   REFERENCES member(id)
);
create sequence mb_seq
increment by 1
start with 1;
--------------------------------------------------------------
--�а� ����
create table majorRipple(
	mRnum		NUMBER 			primary key,
	id 			varchar2(15) 	not null, --�ۼ��� id
    name  		varchar2(20) 	not null, --�ۼ��� �̸�
	reg_date 	date			NOT NULL,
	--������ �Խ��� ����÷�
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--������ �Խ��� ����÷� END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MMR_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence mr_seq
increment by 1
start with 1;
--------------------------------------------------------
--�а� �Խ���
create table notice (
   nnum 		NUMBER 			not null, --�Խ��� ��ȣ �ο�
   id 			varchar2(15) 	not null, --�ۼ��� id
   name  		varchar2(20) 	not null, --�ۼ��� �̸�
   subject 		varchar2(100) 	not null, --����
   content		varchar2(4000) 	not null, --����
   regist_day 	date,			  		  --�ۼ���
   topic 		varchar2(20),			  --��������
   hit 			NUMBER,					  --��ȸ��
   primary key(nnum),
   CONSTRAINT  FK_MN_ID  FOREIGN KEY(id)  
   REFERENCES member(id)
);
create sequence n_seq
increment by 1
start with 1;
------------------------------------------------------
create table galley (
  gnum    	varchar2(15) 	not null,	--�Խ��� ��ȣ
  id  		varchar2(15) 	not null,	
  name 		varchar2(20) 	not null,
  path  	varchar2(100) 	not null,	--������ ����� ���
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
--������
create table scribble(
	snum		NUMBER 			primary key,
	id 			varchar2(15) 	not null, --�ۼ��� id
    name  		varchar2(20) 	not null, --�ۼ��� �̸�
	reg_date 	date			NOT NULL,
	--������ �Խ��� ����÷�
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--������ �Խ��� ����÷� END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MS_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence s_seq
increment by 1
start with 1;
----------------------------------------------
--�����ϴ� ����
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
--1:1����
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
	id 			varchar2(15) 	not null, --�ۼ��� id
    name  		varchar2(20) 	not null, --�ۼ��� �̸�
	reg_date 	date			NOT NULL,
	--������ �Խ��� ����÷�
	ref			NUMBER			NOT NULL,
	re_step		NUMBER			NOT NULL,
	re_level	NUMBER			NOT NULL,
	--������ �Խ��� ����÷� END--------------
	content 	clob			NOT NULL,
	CONSTRAINT  FK_MOR_ID  FOREIGN KEY(id)  
   	REFERENCES member(id)
);
create sequence or_seq
increment by 1
start with 1;





  