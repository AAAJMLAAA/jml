package com.jml.mybatis.presql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.LanguageDriverRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

public class BuilderMapperStatement {
	public static SqlSessionFactory factory = null;

	private static List<ResultMap> getStatementResultMaps(Configuration configuration, Class<?> resultType,
			String statementId) {
		List<ResultMap> resultMaps = new ArrayList<ResultMap>();
		if (resultType != null) {
			ResultMap inlineResultMap = new ResultMap.Builder(configuration, statementId + "-Inline", resultType,
					new ArrayList<ResultMapping>(), null).build();
			resultMaps.add(inlineResultMap);
		}
		return resultMaps;
	}
	
	public static <T> void addStatement(T t) throws Exception {
		factory = new BuilderSqlSessionFactory().build();
		Class<? extends Object> class1 = t.getClass();
		Configuration configuration = factory.getConfiguration();
		String databaseId = configuration.getDatabaseId();
		LanguageDriverRegistry languageRegistry = configuration.getLanguageRegistry();
		LanguageDriver languageDriver = languageRegistry.getDefaultDriver();
		Map<SqlCommandType,SqlSource> maps = new HashMap<>();
		maps.put(SqlCommandType.SELECT, BuIderPreSqlSource.createSelectSqlSource(languageDriver, configuration, t));
		for (Map.Entry<SqlCommandType, SqlSource> map : maps.entrySet())
		{
			MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, class1.getName()+"."+map.getKey().name(),
					map.getValue(), map.getKey())
							.databaseId(databaseId)
							.lang(languageDriver)
							.resultSetType(ResultSetType.SCROLL_SENSITIVE)
							.resultMaps(getStatementResultMaps(configuration, class1, class1.getName()+"."+map.getKey().name()))
							;
			MappedStatement build = statementBuilder.build();
			configuration.addMappedStatement(build);
		}
		
	}

}
