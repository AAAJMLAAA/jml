package com.jml.mybatis.presql;

import java.util.List;

import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/**
 * @author jinmingliang
 *
 */
public class BaseDao {
	private ThreadLocal<SqlSession> sessions = new ThreadLocal<SqlSession>();
	private SqlSessionFactory factory;
	public <T> void init(T t) throws Exception {
		BuilderMapperStatement.addStatement(t);
		if (factory == null)
		{
			factory = BuilderMapperStatement.factory;
		}
	}
	public <T> List<T> selectList(T t) {
		SqlSession session = getSqlSession();
		List<T> selectList = session.selectList(t.getClass().getName() + "." + SqlCommandType.SELECT.name(),t);
		session.close();
		return selectList;
	}
	
	private SqlSession getSqlSession()
	{
		SqlSession session = sessions.get();
		if (session == null)
		{
			session = factory.openSession();
			sessions.set(session);
		}
		return session;
	}
}
