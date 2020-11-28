package com.jml.mybatis.test.pojo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.result.DefaultMapResultHandler;
import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;

public class mySqlSession implements SqlSession {
	private Configuration configuration;
	private Executor executor;
	private boolean autoCommit;
	private boolean dirty;

	public mySqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
		this.configuration = configuration;
		this.executor = executor;
		this.dirty = false;
		this.autoCommit = autoCommit;
	}

	public mySqlSession(Configuration configuration, Executor executor) {
		this(configuration, executor, false);
	}

	public <T> T selectOne(String statement) {
		return this.selectOne(statement, (Object) null);
	}

	public <T> T selectOne(String statement, Object parameter) {
		List<T> list = this.selectList(statement, parameter);
		if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() > 1) {
			throw new TooManyResultsException(
					"Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
		} else {
			return null;
		}
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return this.selectMap(statement, (Object) null, mapKey, RowBounds.DEFAULT);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		return this.selectMap(statement, parameter, mapKey, RowBounds.DEFAULT);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		List<?> list = this.selectList(statement, parameter, rowBounds);
		DefaultMapResultHandler<K, V> mapResultHandler = new DefaultMapResultHandler(mapKey,
				this.configuration.getObjectFactory(), this.configuration.getObjectWrapperFactory());
		DefaultResultContext context = new DefaultResultContext();
		Iterator i$ = list.iterator();

		while (i$.hasNext()) {
			Object o = i$.next();
			context.nextResultObject(o);
			mapResultHandler.handleResult(context);
		}

		Map<K, V> selectedMap = mapResultHandler.getMappedResults();
		return selectedMap;
	}

	public <E> List<E> selectList(String statement) {
		return this.selectList(statement, (Object) null);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return this.selectList(statement, parameter, RowBounds.DEFAULT);
	}

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		List var6;
		try {
			// 动态的创建这个对象
			MappedStatement ms = this.configuration.getMappedStatement(statement);
			List<E> result = this.executor.query(ms, this.wrapCollection(parameter), rowBounds,
					Executor.NO_RESULT_HANDLER);
			var6 = result;
		} catch (Exception var10) {
			throw ExceptionFactory.wrapException("Error querying database.  Cause: " + var10, var10);
		} finally {
			ErrorContext.instance().reset();
		}

		return var6;
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		this.select(statement, parameter, RowBounds.DEFAULT, handler);
	}

	public void select(String statement, ResultHandler handler) {
		this.select(statement, (Object) null, RowBounds.DEFAULT, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		try {
			MappedStatement ms = this.configuration.getMappedStatement(statement);
			this.executor.query(ms, this.wrapCollection(parameter), rowBounds, handler);
		} catch (Exception var9) {
			throw ExceptionFactory.wrapException("Error querying database.  Cause: " + var9, var9);
		} finally {
			ErrorContext.instance().reset();
		}

	}

	public int insert(String statement) {
		return this.insert(statement, (Object) null);
	}

	public int insert(String statement, Object parameter) {
		return this.update(statement, parameter);
	}

	public int update(String statement) {
		return this.update(statement, (Object) null);
	}

	public int update(String statement, Object parameter) {
		int var4;
		try {
			this.dirty = true;
			MappedStatement ms = this.configuration.getMappedStatement(statement);
			var4 = this.executor.update(ms, this.wrapCollection(parameter));
		} catch (Exception var8) {
			throw ExceptionFactory.wrapException("Error updating database.  Cause: " + var8, var8);
		} finally {
			ErrorContext.instance().reset();
		}

		return var4;
	}

	public int delete(String statement) {
		return this.update(statement, (Object) null);
	}

	public int delete(String statement, Object parameter) {
		return this.update(statement, parameter);
	}

	public void commit() {
		this.commit(false);
	}

	public void commit(boolean force) {
		try {
			this.executor.commit(this.isCommitOrRollbackRequired(force));
			this.dirty = false;
		} catch (Exception var6) {
			throw ExceptionFactory.wrapException("Error committing transaction.  Cause: " + var6, var6);
		} finally {
			ErrorContext.instance().reset();
		}

	}

	public void rollback() {
		this.rollback(false);
	}

	public void rollback(boolean force) {
		try {
			this.executor.rollback(this.isCommitOrRollbackRequired(force));
			this.dirty = false;
		} catch (Exception var6) {
			throw ExceptionFactory.wrapException("Error rolling back transaction.  Cause: " + var6, var6);
		} finally {
			ErrorContext.instance().reset();
		}

	}

	public List<BatchResult> flushStatements() {
		List var1;
		try {
			var1 = this.executor.flushStatements();
		} catch (Exception var5) {
			throw ExceptionFactory.wrapException("Error flushing statements.  Cause: " + var5, var5);
		} finally {
			ErrorContext.instance().reset();
		}

		return var1;
	}

	public void close() {
		try {
			this.executor.close(this.isCommitOrRollbackRequired(false));
			this.dirty = false;
		} finally {
			ErrorContext.instance().reset();
		}

	}

	public Configuration getConfiguration() {
		return this.configuration;
	}

	public <T> T getMapper(Class<T> type) {
		return this.configuration.getMapper(type, this);
	}

	public Connection getConnection() {
		try {
			return this.executor.getTransaction().getConnection();
		} catch (SQLException var2) {
			throw ExceptionFactory.wrapException("Error getting a new connection.  Cause: " + var2, var2);
		}
	}

	public void clearCache() {
		this.executor.clearLocalCache();
	}

	private boolean isCommitOrRollbackRequired(boolean force) {
		return !this.autoCommit && this.dirty || force;
	}

	private Object wrapCollection(Object object) {
		StrictMap map;
		if (object instanceof List) {
			map = new StrictMap();
			map.put("list", object);
			return map;
		} else if (object != null && object.getClass().isArray()) {
			map = new StrictMap();
			map.put("array", object);
			return map;
		} else {
			return object;
		}
	}
}