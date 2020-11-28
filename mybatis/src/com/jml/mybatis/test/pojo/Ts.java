package com.jml.mybatis.test.pojo;

import java.util.Map;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.session.Configuration;

public class Ts {
	public BoundSql getBoundSql(Object parameterObject,Configuration configuration) {
		DynamicContext context = new DynamicContext(configuration, parameterObject);
		//rootSqlNode.apply(context);
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
		Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
		// 运行时根据参数解析SQL
		SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
		for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
		boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
		}
		return boundSql;
		}
}
