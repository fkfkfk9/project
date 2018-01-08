create table tbl_member(
    ID 			VARCHAR2(50 BYTE),
    PASSWD 		VARCHAR2(60 BYTE) 	NOT NULL, 
    NAME 		VARCHAR2(20 BYTE) 	NOT NULL,
    REG_DATE 	DATE 				NOT NULL,
	ADDR 		VARCHAR2(100 BYTE) 	NOT NULL, 
    TEL 		VARCHAR2(20 BYTE) 	NOT NULL, 
	PRIMARY KEY (ID)
);
 
create table tbl_board(
    NUM       NUMBER        primary key,
    WRITER    VARCHAR2(50)  NOT NULL , 
    SUBJECT   VARCHAR2(50)  NOT NULL, 
	PASSWD    VARCHAR2(60)  NOT NULL, 
	REG_DATE  DATE          NOT NULL,         
	READCOUNT NUMBER        default 0,       
	REF       NUMBER        NOT NULL,       
	RE_STEP   NUMBER        NOT NULL,       
	RE_LEVEL  NUMBER        NOT NULL,        
	CONTENT   CLOB          NOT NULL,         
	IP        VARCHAR2(30)  NOT NULL  
);

select * from tbl_board;
insert into tbl_board(num, writer,subject,passwd,reg_date,re_step,re_level, content, ip, ref) 
		values(board_seq.nextval,'user01','제목','1111',sysdate,0,0,'내용','127.0.0.1',board_seq.currval);	
select count(*) from tbl_board;
	

	