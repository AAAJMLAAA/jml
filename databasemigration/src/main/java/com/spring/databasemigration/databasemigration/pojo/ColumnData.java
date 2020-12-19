package com.spring.databasemigration.databasemigration.pojo;

import java.util.List;

public class ColumnData {
	/** 表名 */
	private String tableName;
	/** 字段的值 */
	List<List<String>> fildDatas;
	/** 列名 */
	List<String> fildNames;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<List<String>> getFildDatas() {
		return fildDatas;
	}

	public void setFildDatas(List<List<String>> fildDatas) {
		this.fildDatas = fildDatas;
	}

	public List<String> getFildNames() {
		return fildNames;
	}

	public void setFildNames(List<String> fildNames) {
		this.fildNames = fildNames;
	}
	
	

}
