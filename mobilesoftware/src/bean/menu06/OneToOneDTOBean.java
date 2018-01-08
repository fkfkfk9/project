package bean.menu06;

import java.sql.Timestamp;

public class OneToOneDTOBean {
/*
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
*/
	private int onum;
	private String id;
	private String s_id;
	private String name;
	private String title;
	private String content;
	private String path;
	private String e_check;
	private Timestamp reg_date;
	private int hit;
	
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getE_check() {
		return e_check;
	}
	public void setE_check(String e_check) {
		this.e_check = e_check;
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
