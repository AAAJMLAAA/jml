package springftl.sql;

public class SqlField {
	/**
	 * 标识
	 */
	private int id; 
	
	/**
	 * 列名
	 */
	private String columnName;
	
	/**
	 * 类型
	 */
	private String columnType; 
	
	/**
	 * 列的大小
	 */
	private String columnSize; 
	
	/**
	 * 0 是 1不是
	 */
	private int priKey;
	/**
	 * 注释
	 */
	private String comment; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
	public int getPriKey() {
		return priKey;
	}
	public void setPriKey(int priKey) {
		this.priKey = priKey;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "SqlField [id=" + id + ", columnName=" + columnName + ", columnType=" + columnType + ", columnSize="
				+ columnSize + ", priKey=" + priKey + ", comment=" + comment + "]";
	}
}
