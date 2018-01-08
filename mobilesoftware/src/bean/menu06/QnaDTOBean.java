package bean.menu06;

import java.sql.Timestamp;

public class QnaDTOBean {
/*
   qnum NUMBER not null,
   id 		varchar2(15) 	not null,
   name  	varchar2(10) 	not null,
   subject 	varchar2(100) 	not null,
   content 	varchar2(4000) 	not null,
   path 	varchar2(100) 	not null,
   reg_date	date,
   hit 		NUMBER,
   primary key(qnum)
*/
	private int qnum;
	private String id;
	private String name;
	private String subject;
	private String content;
	private String path;
	private Timestamp reg_date;
	private int hit;
	
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
