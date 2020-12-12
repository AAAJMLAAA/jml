package com.spring.demo.demoStart.pojo;

import java.util.Date;

public class Student {
	/**主键*/
	private String id;
	/**姓名*/
	private String name;
	/**密码*/
	private String pwd;
	/**出现年月*/
	private Date birthday;
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
