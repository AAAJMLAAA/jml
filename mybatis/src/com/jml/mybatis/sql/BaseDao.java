package com.jml.mybatis.sql;

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

	public <T> void insert(T t) {
		SqlSession session = getSqlSession();
		session.insert(t.getClass().getName() + "." + SqlCommandType.INSERT.name());
		session.commit();
		session.close();
	}

	public <T> void update(T t) {
		SqlSession session = getSqlSession();
		session.update(t.getClass().getName() + "." + SqlCommandType.UPDATE.name());
		session.commit();
		session.close();
	}

	public <T> void delete(T t) {
		SqlSession session = getSqlSession();
		session.delete(t.getClass().getName() + "." + SqlCommandType.DELETE.name());
		session.commit();
		session.close();
	}

	public <T> List<T> selectList(T t) {
		SqlSession session = getSqlSession();
		List<T> selectList = session.selectList(t.getClass().getName() + "." + SqlCommandType.SELECT.name());
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
