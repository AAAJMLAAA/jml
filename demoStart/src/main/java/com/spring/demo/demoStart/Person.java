package com.spring.demo.demoStart;

public class Person {
	private String name;
	private String pwd;
	
	Person(DemoProperties demoProperties)
	{
		this.name = demoProperties.getName();
		this.pwd = demoProperties.getPwd();
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
	
	
}
