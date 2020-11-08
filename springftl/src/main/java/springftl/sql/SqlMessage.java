package springftl.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import springftl.dataSoure.DataSourceInfo;

/**
 * 获取字段的信息
 * @author jinmingliang
 *
 */
public class SqlMessage {
	/** 数据库的信息*/
	private DataSourceInfo dataSourceInfo;
	/**数据库的元数据*/
	private  DatabaseMetaData metaData;
	/**数据的连接信息*/
	private  Connection conn;
	
	private  List<SqlField> sqlFields;
	
	public  List<SqlField> getSqlFields()
	{
		return sqlFields;
	}
	
	public SqlMessage(DataSourceInfo dataSourceInfo) throws Exception {
		this.dataSourceInfo = dataSourceInfo;
		initTable();
	}


	private void initTable() throws Exception {
		Class.forName(dataSourceInfo.getDriverClassName());
		this.conn = DriverManager.getConnection(dataSourceInfo.getUrl(), dataSourceInfo.getUserName(), dataSourceInfo.getPassword());
		// 获取元数据
		this.metaData = conn.getMetaData();
		if (!checkTable())
		{
			throw new Exception("表不存在！");
		}
		
		this.sqlFields = this.getTableFields();
		
		if (conn != null)
		{
			conn.close();
		}
	}

	/**
	 * @Description: 获取主键
	 * @date: 2020年9月2日 下午9:12:22
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	private List<String> getPrimaryKey() throws SQLException {
		List<String> lists = new ArrayList<>();
		// 获取主键
		ResultSet primaryKeys = this.metaData.getPrimaryKeys(null, "root", dataSourceInfo.getTableName());
		while (primaryKeys.next()) {
			String COLUMN_NAME = primaryKeys.getString("COLUMN_NAME");
			lists.add(COLUMN_NAME);
		}

		return lists;
	}

	private List<SqlField> getTableFields() throws SQLException {
		List<String> primaryKey = getPrimaryKey();
		List<SqlField> sqlFields = new ArrayList<>();
		// 通过表名获取列名
		ResultSet columns = this.metaData.getColumns(null, "root", dataSourceInfo.getTableName(), "%"); // new																		// 获取视图
	
		SqlField sqlField = null;
		int index = 0;
		while (columns.next()) {
			sqlField = new SqlField();
			sqlField.setId(++index);
			sqlField.setColumnName(columns.getString("COLUMN_NAME"));
			sqlField.setColumnSize(columns.getString("COLUMN_SIZE"));
			sqlField.setColumnType(columns.getString("TYPE_NAME"));
			sqlField.setComment(columns.getString("REMARKS"));
			sqlField.setPriKey(primaryKey.contains(sqlField.getColumnName()) ? 0 : 1);
			sqlFields.add(sqlField);
		}

		return sqlFields;
	}

	private  boolean  checkTable() throws SQLException {
		ResultSet tables = metaData.getTables(null, "root", "%", new String[] { "TABLE" });
		while (tables.next()) {
			// 获取到表名
			String TABLE_NAME = tables.getString("TABLE_NAME");
			if (TABLE_NAME.equals(dataSourceInfo.getTableName()))
			{
				return true; 	
			}
		}
		
		return false;
	}
	
	
	public  Map<String,String> getTableComment() throws SQLException, ClassNotFoundException
	{
		Map<String,String> map = new HashMap<>();
		Class.forName(dataSourceInfo.getDriverClassName());
		String url = dataSourceInfo.getUrl().replace(this.conn.getCatalog(), "information_schema");
		Connection connection = DriverManager.getConnection(url, dataSourceInfo.getUserName(), dataSourceInfo.getPassword());
		Statement statement = connection.createStatement();
		String sql = "select table_name,table_comment from TABLES where TABLE_SCHEMA='"+this.conn.getCatalog()+"'";
		ResultSet executeQuery = statement.executeQuery(sql);
		while (executeQuery.next())
		{
			// 获取到表名
			String TABLE_NAME = executeQuery.getString("table_name");
			String REMARKS = executeQuery.getString("table_comment");
			map.put(TABLE_NAME, REMARKS);
		}
		
		return map;
	}
}
