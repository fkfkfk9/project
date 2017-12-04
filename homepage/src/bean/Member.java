package bean;

public class Member {
	private String id;
	private String pass;
	private String name;
	private String hp;
	private String email;
	private String regist_day;
	private int level;
	
	public String getId() {	return id;}
	public void setId(String id) {	this.id = id;}
	public String getPass() {		return pass;	}
	public void setPass(String pass) {	this.pass = pass;	}
	public String getName() {	return name;	}
	public void setName(String name) {	this.name = name;	}
	public String getHp() {	return hp;	}
	public void setHp(String hp) {	this.hp = hp;	}
	public String getEmail() {	return email;	}
	public void setEmail(String email) {	this.email = email;	}
	public String getRegist_day() {	return regist_day;	}
	public void setRegist_day(String regist_day) {	this.regist_day = regist_day;	}
	public int getLevel() {	return level;	}
	public void setLevel(int level) {	this.level = level;	}	
}
