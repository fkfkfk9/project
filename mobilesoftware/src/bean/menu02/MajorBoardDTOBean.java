package bean.menu02;

import java.sql.Date;

public class MajorBoardDTOBean {
	/*
		mBnum 		NUMBER 			not null, --�Խ��� ��ȣ �ο�
   		id 			varchar2(15) 	not null, --�ۼ��� id
   		name  		varchar2(20) 	not null, --�ۼ��� �̸�
   		subject 	varchar2(100) 	not null, --����
   		content		varchar2(4000) 	not null, --����
   		regist_day 	date,			  		  --�ۼ���
   		classes		varchar2(20),			  --����
   		hit 		NUMBER,					  --��ȸ��
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
