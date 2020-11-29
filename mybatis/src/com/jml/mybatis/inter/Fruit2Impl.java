package com.jml.mybatis.inter;

public class Fruit2Impl implements Fruit2 {

	@Override
	public String test() {
		return "test";
	}

	@Override
	public String test(String name) {
		return "test" +name;
	}

	@Override
	public String test(String name, String pwd) {
		return "test" +name+" "+pwd;
	}

}
