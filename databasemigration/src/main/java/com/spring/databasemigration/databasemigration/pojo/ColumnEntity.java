package com.spring.databasemigration.databasemigration.pojo;

/**
 * 表名
 * 
 * @author jinmingliang
 *
 */
public class ColumnEntity {
	/** 列名 */
	private String columnName;
	/**是否可以为null*/
	private String nullAble;
	/**数据类型*/
	private String dataType;
	/**长度 日期的默认为0*/
	private String maxLength;
	/**是否为主键*/
	private String priKey;
	/**备注*/
	private String columnComment;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getNullAble() {
		return nullAble;
	}
	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public String getPriKey() {
		return priKey;
	}
	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	
	@Override
	public String toString() {
		return "ColumnEntity [columnName=" + columnName + ", nullAble=" + nullAble + ", dataType=" + dataType
				+ ", maxLength=" + maxLength + ", priKey=" + priKey + ", columnComment=" + columnComment + "]";
	}

}
