package bean.menu06;

import java.sql.Timestamp;

public class OtoRippleDTOBean {
/*
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
*/
	private int oRnum;
	private String id;
	private String name;
	private Timestamp reg_date;
	private int ref;
	private int re_step;
	private int re_level;
	private String content;
	
	public int getoRnum() {
		return oRnum;
	}
	public void setoRnum(int oRnum) {
		this.oRnum = oRnum;
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
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
