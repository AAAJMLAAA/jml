package springftl.sql;

import java.io.Serializable;
import java.util.List;

public class SqlTable implements Serializable{
	/**
	 * /**
	 * @Description: 
	 * @date: 2020年11月8日 上午10:26:56
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 表的信息
	 */
	private String tableMesage;
	
	/**
	 * 字段名
	 */
	private List<SqlField> sqlFields;

	/**
	 * 包名
	 */
	private String packageName;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableMesage() {
		return tableMesage;
	}

	public void setTableMesage(String tableMesage) {
		this.tableMesage = tableMesage;
	}

	public List<SqlField> getSqlFields() {
		return sqlFields;
	}

	public void setSqlFields(List<SqlField> sqlFields) {
		this.sqlFields = sqlFields;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
}
