package com.spring.databasemigration.databasemigration.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import com.spring.databasemigration.databasemigration.concast.TableConcast;
import com.spring.databasemigration.databasemigration.config.DataSourceContext;
import com.spring.databasemigration.databasemigration.dao.TableDao;
import com.spring.databasemigration.databasemigration.dao.UserDao;
import com.spring.databasemigration.databasemigration.pojo.ColumnData;
import com.spring.databasemigration.databasemigration.pojo.ColumnEntity;
import com.spring.databasemigration.databasemigration.pojo.TableEntity;
import com.spring.databasemigration.databasemigration.service.TableService;
import com.spring.databasemigration.databasemigration.util.WriteFile;
import com.spring.databasemigration.databasemigration.vo.TableVo;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableDao tableDao;
	//@Autowired
//	private Environment env;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private List<TableEntity> listTable() {
		return tableDao.listTable();
	}

	
	private List<ColumnEntity> listTableColumn(String tableName) {
		List<ColumnEntity> listTableColumn = tableDao.listTableColumn(tableName);
		for (ColumnEntity columnEntity : listTableColumn) {
			if ("int".equals(columnEntity.getDataType())) {
				columnEntity.setMaxLength("11");
			} else if ("date".equals(columnEntity.getDataType())) {
				columnEntity.setMaxLength("0");
			}
		}
		return listTableColumn;
	}

	private List<TableVo> createTableStructureSql() {
		List<TableVo> tableVos = new ArrayList<>();
		List<TableEntity> listTable = listTable();
		listTable.stream().forEach(tableEntity -> {
			List<ColumnEntity> listTableColumn = listTableColumn(tableEntity.getTableName());
			TableVo tableVo = new TableVo();
			tableVo.setColumnEntitys(listTableColumn);
			tableVo.setTableEntity(tableEntity);
			tableVos.add(tableVo);
		});

		return tableVos;
	}

	/**
	 * 直接使用mybatis的
	 * 
	 * @throws Exception
	 */
//	private void runSql(String sqlPath) throws Exception {
//		// 不用@Value获取值v这里只能执行一个命令 循环读
//		String className = env.getProperty("mysql.spring.datasource.driver-class-name");
//		String url = env.getProperty("mysql.spring.datasource.url");
//		String userName = env.getProperty("mysql.spring.datasource.username");
//		String pwd = env.getProperty("mysql.spring.datasource.password");
//		Class.forName(className);
//		Connection connection = DriverManager.getConnection(url, userName, pwd);
//		try (Reader reader = new FileReader(new File(sqlPath));) {
//			ScriptRunner scriptRunner = new ScriptRunner(connection);
//			scriptRunner.setAutoCommit(false);
//			scriptRunner.setStopOnError(true);
//			// scriptRunner.setFullLineDelimiter(true);
//			// scriptRunner.setDelimiter(",");
//			scriptRunner.setSendFullScript(true);
//			scriptRunner.runScript(reader);
//			scriptRunner.closeConnection();
//			connection.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	@Override
	public void dataTranslation() throws Exception {
		// 生成表结构文件
		List<TableVo> tableVoSql = createTableStructureSql();
		WriteFile.WriteToFile(tableVoSql);
		// 生成表数据
		List<ColumnData> selectName = selectName();
		WriteFile.WriteToFileData(selectName);
		
//		String className = env.getProperty("mysql.spring.datasource.driver-class-name");
//		String url = env.getProperty("mysql.spring.datasource.url");
//		String userName = env.getProperty("mysql.spring.datasource.username");
//		String pwd = env.getProperty("mysql.spring.datasource.password");
//		Class.forName(className);
	//	Connection connection = DriverManager.getConnection(url, userName, pwd);
		
		DataSourceContext.setDataSource("mysqlDataSource");
		// 这个会报连接关闭的呃问题
		// Connection connection = SqlSessionTemplate.getConnection();
		SqlSession openSession = sqlSessionFactory.openSession();
		Connection connection = openSession.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		   System.out.println("URL:"+metaData.getURL()+";");
		   System.out.println("UserName:"+metaData.getUserName()+";");
		// 执行表结构
		FileSystemResource rc = new FileSystemResource(TableConcast.TARGET_PATH);
		EncodedResource er = new EncodedResource(rc, "utf-8");
		ScriptUtils.executeSqlScript(connection, er);
		
		// 执行表数据
		FileSystemResource rc2 = new FileSystemResource(TableConcast.TARGET_PATH2);
		EncodedResource er2 = new EncodedResource(rc2, "utf-8");
		ScriptUtils.executeSqlScript(connection, er2);
		connection.close();
	}

	
	private  List<ColumnData>  selectName() {
		List<ColumnData> columnDatas = new ArrayList<>(); 
		List<TableEntity> listTable = listTable();
		listTable.stream().forEach(t->{
			List<Map<String,Object>>  selectName = tableDao.selectName(t.getTableName());
			ColumnData columnData = new ColumnData();
			columnData.setTableName(t.getTableName());
			List<String> listNames = new ArrayList<>();
			List<List<String>> listDatass = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			selectName.stream().forEach(m->{
				
				List<String> listDatas = new ArrayList<>();
				for(Map.Entry<String, Object> map : m.entrySet())
				{
					if (listNames.size() < m.size())
					{
						listNames.add(map.getKey());
					}
					Object value = map.getValue();
					if (value instanceof Date)
					{
						listDatas.add(sdf.format(value));
					}else
					{
						listDatas.add(value.toString());
					}
				}
				
				listDatass.add(listDatas);
			});
			columnData.setFildDatas(listDatass);
			columnData.setFildNames(listNames);
			
			columnDatas.add(columnData);
		});
	
		return columnDatas;
	}
}
