package bbs.member.bean;

import java.sql.Timestamp;

public class MemberBeanDb {//DTO
	/*
	ID 			VARCHAR2(50 BYTE), 
	PASSWD 		VARCHAR2(60 BYTE) 	NOT NULL, 
	NAME 		VARCHAR2(20 BYTE) 	NOT NULL, 
	REG_DATE 	DATE 				NOT NULL, 
	ADDR 		VARCHAR2(100 BYTE) 	NOT NULL, 
	TEL 		VARCHAR2(20 BYTE) 	NOT NULL, 
	PRIMARY KEY (ID)
	*/
	private String id;
	private String passwd;
	private String name;
	private Timestamp reg_date;
	private String addr;
	private String tel;
	
	public String getId() {	return id;}
	public void setId(String id) {	this.id = id;}
	public String getPasswd() {	return passwd;}
	public void setPasswd(String passwd) {	this.passwd = passwd;}
	public String getName() {	return name;}	
	public void setName(String name) {	this.name = name;}
	public Timestamp getReg_date() {	return reg_date;}
	public void setReg_date(Timestamp reg_date) {	this.reg_date = reg_date;}
	public String getAddr() {	return addr;}
	public void setAddr(String addr) {	this.addr = addr;}
	public String getTel() {	return tel;	}
	public void setTel(String tel) {	this.tel = tel;	}
}
