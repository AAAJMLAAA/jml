package com.jml.mybatis.presql;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

/**
 * 这里只是单纯的构建sql
 * @author jinmingliang
 *
 */
public class BuIderPreSqlSource{
	
	public static <T> SqlSource createSelectSqlSource(LanguageDriver languageDriver,Configuration configuration,T t) throws Exception
	{
		 SqlSource sqlSource = languageDriver.createSqlSource(configuration, DynamicSql.createSelectSql(t), t.getClass());
		 return sqlSource;
	}
}
