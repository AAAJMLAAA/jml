package com.jml.mybatis.presql;

import java.lang.reflect.Field;

public class DynamicSql {
	public static <T>  String createSelectSql(T t) throws Exception
	{
		Class<? extends Object> class1 = t.getClass();
		StringBuilder sqlBuilder = new  StringBuilder();
		sqlBuilder.append("SELECT * FROM").append(" ").append(class1.getSimpleName());
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields)
		{
			field.setAccessible(true);
			String name = field.getName();
			Object object = field.get(t);
			if (object != null && !"".equals(object))
			{
				sqlBuilder.append(" AND ").append(name).append(" = ").append("#{").append(name).append("}");	
			}
			
		}
		
		return sqlBuilder.toString().replaceFirst(" AND ", " WHERE ");
	}
	
	/**
	 * 	sqlBuilder.append("<where>");
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields)
		{
			field.setAccessible(true);
			String name = field.getName();
			sqlBuilder.append("<if test=\"").append(name).append(" != null  and ").append(name).append(" != ''\">");
				sqlBuilder.append(" AND ").append(name).append(" = ").append("#{").append(name).append("}");
			sqlBuilder.append("</if>");
		}
		sqlBuilder.append("</where>");
	 */
}
