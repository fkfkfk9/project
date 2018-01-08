package bean.menu03;

import java.sql.Timestamp;

public class NoticeDTOBean {
/*
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
