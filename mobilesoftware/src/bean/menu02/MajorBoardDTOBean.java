package bean.menu02;

import java.sql.Date;

public class MajorBoardDTOBean {
	/*
		mBnum 		NUMBER 			not null, --게시판 번호 부여
   		id 			varchar2(15) 	not null, --작성자 id
   		name  		varchar2(20) 	not null, --작성자 이름
   		subject 	varchar2(100) 	not null, --제목
   		content		varchar2(4000) 	not null, --내용
   		regist_day 	date,			  		  --작성일
   		classes		varchar2(20),			  --과목
   		hit 		NUMBER,					  --조회수
   		primary key(mBnum),
   		CONSTRAINT  FK_MMB_ID  FOREIGN KEY(id)  
   		REFERENCES member(id)
	*/
	private int mBnum;
	private String id;
	private String name;
	private String subject;
	private String content;
	private Date regist_day;
	private String classes;
	private int hit;
	
	public int getmBnum() {
		return mBnum;
	}
	public void setmBnum(int mBnum) {
		this.mBnum = mBnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(Date regist_day) {
		this.regist_day = regist_day;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}	
}
