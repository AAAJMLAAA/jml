package com.spring.databasemigration.databasemigration.pojo;

/**
 * 表名
 * 
 * @author jinmingliang
 *
 */
public class TableEntity {
	/** 表名 */
	private String tableName;
	/** 备注 */
	private String tableComment;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}
