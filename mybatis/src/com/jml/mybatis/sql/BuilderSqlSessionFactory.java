package com.jml.mybatis.sql;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BuilderSqlSessionFactory {

	private SqlSessionFactory factory;
	
	public BuilderSqlSessionFactory() throws IOException
	{
		try(InputStream is = Resources.getResourceAsStream("myabtis.xml");)
		{
			// 使用工厂设计模式
			this.factory = new SqlSessionFactoryBuilder().build(is);	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public SqlSessionFactory build()
	{
		return this.factory;
	}
}
