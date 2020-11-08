package springftl.dataSoure;

/**
 * 数据库的信息
 * @author jinmingliang
 *
 */
public class DataSourceInfo {
	/**数据库的url*/
	private String url;
	/**数据库的驱动*/
	private String driverClassName;
	/**数据库的用户名*/
	private String userName;
	/**数据库的密码*/
	private String password;
	/**数据库的包名*/
	private String packageName;
	/**数据库的表名*/
	private String tableName;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}	
}
