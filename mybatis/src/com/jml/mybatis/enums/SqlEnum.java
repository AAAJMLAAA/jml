package com.jml.mybatis.enums;

import com.jml.mybatis.sql.DynamicSql;

public enum SqlEnum {
	DELETE {
		@Override
		public <T> String createSql(T t) throws Exception{
			// TODO Auto-generated method stub
			return DynamicSql.createSelectOrDeleteSql(t," DELETE FROM ");
		}
	},UPDATE {
		@Override
		public <T> String createSql(T t) throws Exception{
			return DynamicSql.createUpdateSql(t, "UPDATE ");
		}
	},SELECT {
		@Override
		public <T> String createSql(T t) throws Exception {
			return DynamicSql.createSelectOrDeleteSql(t, "SELECT * FROM ");
		}
	},INSERT {
		@Override
		public <T> String createSql(T t) throws Exception{
			return DynamicSql.createInsertSql(t,"INSERT INTO ");
		}
	};
	
	public abstract <T> String createSql(T t) throws Exception;


}
