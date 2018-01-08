package bean.menu03;

import java.sql.Timestamp;

public class NoticeDTOBean {
/*
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
*/
	private int nnum;
	private String id;
	private String name;
	private String subject;
	private String content;
	private Timestamp regist_day;
	private String topic;
	private int hit;
	
	public int getNnum() {
		return nnum;
	}
	public void setNnum(int nnum) {
		this.nnum = nnum;
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
	public Timestamp getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(Timestamp regist_day) {
		this.regist_day = regist_day;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}	
}
