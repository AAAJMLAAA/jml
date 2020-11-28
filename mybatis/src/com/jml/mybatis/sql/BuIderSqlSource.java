package com.jml.mybatis.sql;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

import com.jml.mybatis.enums.SqlEnum;

/**
 * 创建原始sql
 * @author jinmingliang
 *
 */
public class BuIderSqlSource{

	public static <T> SqlSource createInsertSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		return languageDriver.createSqlSource(configuration, SqlEnum.INSERT.createSql(t), t.getClass());
	}
	
	public static <T> SqlSource createSelectSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		return languageDriver.createSqlSource(configuration, SqlEnum.SELECT.createSql(t), t.getClass());
	}
	
	public static <T> SqlSource createUpdateSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		return languageDriver.createSqlSource(configuration, SqlEnum.UPDATE.createSql(t), t.getClass());
	}
	
	public static <T> SqlSource createDeleteSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		return languageDriver.createSqlSource(configuration,SqlEnum.DELETE.createSql(t), t.getClass());
	}
	
}
