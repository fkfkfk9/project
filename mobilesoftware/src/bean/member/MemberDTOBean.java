package bean.member;

import java.sql.Timestamp;

public class MemberDTOBean {
	/*
		id   		varchar2(15) not null, --아이디 PK
  		pass  		varchar2(15) not null, --암호
  		name  		varchar2(20) not null, --이름
  		hp    		varchar2(20)  not null,--핸드폰
  		email 		varchar2(80),		   --이메일
  		regist_day 	date,				   --가입일
  		m_level 	NUMBER,				   --권한레벨 ex)학생1, 직원2
	*/
	private String id;
	private String pass;
	private String name;
	private String hp;
	private String email;
	private Timestamp regist_day;
	private int m_level;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(Timestamp regist_day) {
		this.regist_day = regist_day;
	}
	public int getM_level() {
		return m_level;
	}
	public void setM_level(int m_level) {
		this.m_level = m_level;
	}	
}
