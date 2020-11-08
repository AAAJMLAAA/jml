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
public class SqlMessage2 {
	/** 数据库的信息*/
	private DataSourceInfo dataSourceInfo;
	/**数据库的元数据*/
	private  DatabaseMetaData metaData;
	/**数据的连接信息*/
	private  Connection conn;
	
	public SqlMessage2(DataSourceInfo dataSourceInfo) throws Exception {
		this.dataSourceInfo = dataSourceInfo;
		initTable();
	}


	private void initTable() throws Exception {
		Class.forName(dataSourceInfo.getDriverClassName());
		this.conn = DriverManager.getConnection(dataSourceInfo.getUrl(), dataSourceInfo.getUserName(), dataSourceInfo.getPassword());
		// 获取元数据
		this.metaData = conn.getMetaData();
	}

	/**
	 * @Description: 获取主键
	 * @date: 2020年9月2日 下午9:12:22
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	private List<String> getPrimaryKey(String tableName) throws SQLException {
		List<String> lists = new ArrayList<>();
		// 获取主键
		ResultSet primaryKeys = this.metaData.getPrimaryKeys(null, "root",tableName);
		while (primaryKeys.next()) {
			String COLUMN_NAME = primaryKeys.getString("COLUMN_NAME");
			lists.add(COLUMN_NAME);
		}

		return lists;
	}

	private List<SqlField> getTableFields(String tableName) throws SQLException {
		List<String> primaryKey = getPrimaryKey(tableName);
		List<SqlField> sqlFields = new ArrayList<>();
		// 通过表名获取列名
		ResultSet columns = this.metaData.getColumns(null, "root", tableName, "%"); // new																		// 获取视图
	
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

	public  Map<String,List<SqlField>>  getTables() throws Exception {
		Map<String,List<SqlField>> map = new HashMap<>();
		Map<String, String> tableComment = getTableComment(conn.getCatalog());
	//	ResultSet tables = metaData.getTables(null, "root", "%", new String[] { "TABLE" });
		 ResultSet tables = metaData.getTables(conn.getCatalog(), "root", "%", new String[] { "TABLE" });
	//	ResultSet tables = metaData.getTables(conn.getCatalog(), null, null, null);
		while (tables.next()) {
			// 获取到表名
			String TABLE_NAME = tables.getString("TABLE_NAME");
			// String TABLE_TYPE = tables.getString("TABLE_TYPE");
			//	String REMARKS = tables.getString("REMARKS");
			
			
			map.put(TABLE_NAME+": "+tableComment.get(TABLE_NAME), getTableFields(TABLE_NAME));
		}
		
		if (this.conn != null)
		{
			this.conn.close();
		}
		return map;
	}
	
	public  Map<String,String> getTableComment(String databaseName) throws SQLException, ClassNotFoundException
	{
		Map<String,String> map = new HashMap<>();
		Class.forName(dataSourceInfo.getDriverClassName());
		String url = dataSourceInfo.getUrl().replace(databaseName, "information_schema");
		Connection connection = DriverManager.getConnection(url, dataSourceInfo.getUserName(), dataSourceInfo.getPassword());
		Statement statement = connection.createStatement();
		String sql = "select table_name,table_comment from TABLES where TABLE_SCHEMA='"+databaseName+"'";
		ResultSet executeQuery = statement.executeQuery(sql);
		while (executeQuery.next())
		{
			// 获取到表名
			String TABLE_NAME = executeQuery.getString("table_name");
			String REMARKS = executeQuery.getString("table_comment");
			map.put(TABLE_NAME, REMARKS);
		}
		
		if (connection != null)
		{
			connection.close();	
		}
		return map;
	}
	
}
