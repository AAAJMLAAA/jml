package com.spring.databasemigration.databasemigration.vo;

import java.util.List;

import com.spring.databasemigration.databasemigration.pojo.ColumnEntity;
import com.spring.databasemigration.databasemigration.pojo.TableEntity;

public class TableVo {
	/**
	 * 表名
	 */
	private TableEntity tableEntity;
	 
	private List<ColumnEntity> columnEntitys;

	public TableEntity getTableEntity() {
		return tableEntity;
	}

	public void setTableEntity(TableEntity tableEntity) {
		this.tableEntity = tableEntity;
	}

	public List<ColumnEntity> getColumnEntitys() {
		return columnEntitys;
	}

	public void setColumnEntitys(List<ColumnEntity> columnEntitys) {
		this.columnEntitys = columnEntitys;
	}
}
