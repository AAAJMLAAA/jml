package com.jml.mybatis.test.pojo;

import java.lang.reflect.Field;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

public class BuIderSqlSource{

	public static <T> SqlSource createSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		return languageDriver.createSqlSource(configuration, createSql(t), t.getClass());
	}
	
	private static <T>  String createSql(T t) throws Exception
	{
		Class<? extends Object> class1 = t.getClass();
		StringBuilder sqlBuilder = new  StringBuilder();
		sqlBuilder.append("SELECT * FROM ").append(class1.getSimpleName());
	
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields)
		{
			/**
			 * 基本数据类型不能是泛型
			 */
			field.setAccessible(true);
		//	String typeName = field.getGenericType().getTypeName();
			String name = field.getName();
			Object object = field.get(t);
			if (object != null)
			{
				sqlBuilder.append(" AND ").append(name).append(" = ").append("'").append(object).append("'");
			}
		}
		
		return sqlBuilder.toString().replaceFirst(" AND ", " WHERE ");
		
	}
}
