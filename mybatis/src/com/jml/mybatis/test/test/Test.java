package com.jml.mybatis.test.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jml.mybatis.test.pojo.Flower;


public class Test {
	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("myabtis.xml");
		//使用工厂设计模式
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		//生产SqlSession
		SqlSession session=factory.openSession();
		Flower flower2 =new Flower();
		flower2.setName("34");
		List<Flower> list = session.selectList("text.pojo.selAll",flower2);
		for (Flower flower : list) {
			System.out.println(flower.toString());
		}
		
		session.close();
	}
}
