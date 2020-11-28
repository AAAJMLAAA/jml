package com.jml.mybatis.sql;

import java.lang.reflect.Field;

public class DynamicSql {
	public static <T>  String createSelectOrDeleteSql(T t,String sql) throws Exception
	{
		Class<? extends Object> class1 = t.getClass();
		StringBuilder sqlBuilder = new  StringBuilder();
		sqlBuilder.append(sql).append(" ").append(class1.getSimpleName());
	
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields)
		{
			/**
			 * 基本数据类型不能是泛型
			 */
			field.setAccessible(true);
			// String typeName = field.getGenericType().getTypeName();
			String name = field.getName();
			Object object = field.get(t);
			if (object != null)
			{
				sqlBuilder.append(" AND ").append(name).append(" = ").append("'").append(object).append("'");
			}
		}
		
		return sqlBuilder.toString().replaceFirst(" AND ", " WHERE ");
	}
	
	public static <T>  String createUpdateSql(T t,String sql) throws Exception
	{
		Class<? extends Object> class1 = t.getClass();
		StringBuilder sqlBuilder = new  StringBuilder();
		sqlBuilder.append(sql).append(" ").append(class1.getSimpleName()) .append(" SET ");
	
		Field[] declaredFields = class1.getDeclaredFields();
		String id = null;
		for (Field field : declaredFields)
		{
			field.setAccessible(true);
			String name = field.getName();
			Object object = field.get(t);
			if (object != null)
			{
				if ("id".equals(name))
				{
					id = object.toString();
				}
				sqlBuilder.append(name).append(" = ").append("'").append(object).append("'").append(", ");
			}
		}
		int lastIndexOf = sqlBuilder.lastIndexOf(",");
		if (lastIndexOf == -1)
		{
			return "";
		}
		String substring = sqlBuilder.substring(0,sqlBuilder.lastIndexOf(","));
		
		if (id != null && id != "")
		{
			substring = substring + " WHERE ID = " + id;
		}
		
		return substring;
	}
	
	public static <T>  String createInsertSql(T t,String sql) throws Exception
	{
		Class<? extends Object> class1 = t.getClass();
		StringBuilder sqlBuilder = new  StringBuilder();
		sqlBuilder.append(sql).append(" ").append(class1.getSimpleName()) .append(" (");
	
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("VALUES(");
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields)
		{
			field.setAccessible(true);
			String name = field.getName();
			Object object = field.get(t);
			if (object != null)
			{
				sqlBuilder.append(name).append(",");
				stringBuilder.append("'").append(object).append("'").append(",");
			}
		}
		int lastIndexOf = sqlBuilder.lastIndexOf(",");
		if (lastIndexOf == -1)
		{
			return "";
		}
		
		return sqlBuilder.replace(sqlBuilder.lastIndexOf(","), sqlBuilder.lastIndexOf(",")+1, ")").append(" ").append(stringBuilder.replace(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf(",")+1, ")")).toString();
	}

}
