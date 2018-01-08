package bean.menu04;

import java.sql.Timestamp;

public class GalleyDTOBean {
/*
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
*/
	private int gnum;
	private String id;
	private String name;
	private String path;
	private String subject;
	private String content;
	private Timestamp reg_date;
	private int hit;
	
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
