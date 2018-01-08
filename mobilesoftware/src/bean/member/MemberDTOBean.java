package bean.member;

import java.sql.Timestamp;

public class MemberDTOBean {
	/*
		id   		varchar2(15) not null, --���̵� PK
  		pass  		varchar2(15) not null, --��ȣ
  		name  		varchar2(20) not null, --�̸�
  		hp    		varchar2(20)  not null,--�ڵ���
  		email 		varchar2(80),		   --�̸���
  		regist_day 	date,				   --������
  		m_level 	NUMBER,				   --���ѷ��� ex)�л�1, ����2
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
