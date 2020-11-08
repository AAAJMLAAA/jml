package springftl.file;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.Template;
import springftl.dataSoure.DataSourceInfo;
import springftl.sql.SqlField;
import springftl.sql.SqlMessage;
import springftl.sql.SqlTable;

/**
 * 输出文件
 * 
 * @author jinmingliang
 *
 */
public class WriteFile {

	private final static  String TARGET_PATH = "E:\\module\\text";
	private final static String  TEMPLATE_PATH = "D:\\jml_work\\202001\\eclipse\\springftl\\src\\main\\resources\\templates\\bgcode";
	
	@SuppressWarnings("deprecation")
	public WriteFile(DataSourceInfo dataSourceInfo) throws Exception {
		SqlMessage sqlMessage = new SqlMessage(dataSourceInfo);
		List<SqlField> getTableFields = sqlMessage.getSqlFields();

		Configuration conf = new Configuration();
		// 加载模板文件(模板的路径)
		conf.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
		// 定义数据
		SqlTable sqlTable = new SqlTable();
		sqlTable.setPackageName(dataSourceInfo.getPackageName());
		sqlTable.setSqlFields(getTableFields);
		sqlTable.setTableName(dataSourceInfo.getTableName());
		sqlTable.setTableMesage(sqlMessage.getTableComment().get(sqlTable.getTableName()));
		dataConversion(sqlTable);
		// 加载模板
		writeFile(TEMPLATE_PATH, dataSourceInfo.getTableName(), TARGET_PATH, conf,sqlTable);
	}

	/**
	 * @Description: 类型转化
	 * @date: 2020年11月8日 下午1:32:00
	 * @param type
	 * @return
	 */
	private static String typeConversion(String type) {
		switch (type) {
		case "CHAR":
			return "String";
		case "VARCHAR":
			return "String";
		case "LONGBLOB":
			return "byte[]";
		case "MEDIUMBLOB":
			return "byte[]";
		case "BLOB":
			return "byte[]";
		case "BINARY":
			return "byte[]";
		case "TINYBLOB":
			return "byte[]";
		case "INTEGER":
			return "Integer";
		case "BIGINT":
			return "Long";
		case "FLOAT":
			return "Float";
		case "DOUBLE":
			return "Double";
		case "DECIMAL":
			return "BigDecimal";
		case "DATE":
			return "Date";
		case "DATETIME":
			return "Date";
		case "TIMESTAMP":
			return "Date";
		default:
			return "";
		}
	}

	private static String upperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private static String strConversion(String str) {
		String lowerCase = str.toLowerCase();
		StringBuilder stringBuilder = new StringBuilder();
		String[] split = lowerCase.split("_");
		for (String s2 : split) {
			stringBuilder.append(upperCase(s2));
		}

		return stringBuilder.toString();
	}

	private static void dataConversion(SqlTable sqlTable) {
		sqlTable.setPackageName(sqlTable.getPackageName().toLowerCase());
		sqlTable.setTableName(strConversion(sqlTable.getTableName()));
		List<SqlField> sqlFields = sqlTable.getSqlFields();
		for (SqlField sqlField : sqlFields) {
			// 字段名的转化
			sqlField.setColumnName(strConversion(sqlField.getColumnName()));
			// 数据类型的转化
			sqlField.setColumnType(typeConversion(sqlField.getColumnType()));
		}
	}

	/**
	 * @Description:
	 * @date: 2020年11月8日 上午11:56:07
	 * @param dirpath
	 *            模板的目录
	 * @param tableName
	 *            表名
	 * @throws Exception
	 */
	private static void writeFile(String templatepath, String tableName, String targetpath, Configuration conf,
			SqlTable sqlTable) throws Exception {
		File file = new File(templatepath);
		IteratorFile(file, conf, sqlTable, targetpath, templatepath);
	}

	private static void IteratorFile(File file, Configuration conf, SqlTable sqlTable, String targetpath,
			String templatepath) throws Exception {
		if (file.exists()) {
			File fileTarget = null;
			File[] listFiles = file.listFiles();
			for (File fileSon : listFiles) {
				String path = fileSon.getPath().replace(templatepath, targetpath);
				if (fileSon.isDirectory()) {
					fileTarget = new File(path);
					if (!fileTarget.exists()) {
						fileTarget.mkdirs();
					}
					IteratorFile(fileSon, conf, sqlTable, targetpath, templatepath);
				} else {
					String replace = fileSon.getPath().replace(templatepath, "");
					Template template = conf.getTemplate(replace);
					int lastIndexOf2 = path.lastIndexOf(File.separator);
					int lastIndexOf = path.lastIndexOf(File.separator);
					String substring = path.substring(lastIndexOf);
					String pathSon = "";
					if (substring.contains("pojo")) {
						pathSon = path.substring(0, lastIndexOf2) + File.separator + sqlTable.getTableName() + ".java";
					} else if (substring.contains("dao")) {
						pathSon = path.substring(0, lastIndexOf2) + File.separator + sqlTable.getTableName()
								+ "Dao.java";
					} else if (substring.contains("service")) {
						pathSon = path.substring(0, lastIndexOf2) + File.separator + sqlTable.getTableName()
								+ "Service.java";
					} else if (substring.contains("impl")) {
						pathSon = path.substring(0, lastIndexOf2) + File.separator + sqlTable.getTableName()
								+ "ServiceImpl.java";
					} else if (substring.contains("controller")) {
						pathSon = path.substring(0, lastIndexOf2) + File.separator + sqlTable.getTableName()
								+ "Controller.java";
					}

					try (Writer out = new FileWriter(pathSon);) {
						template.process(sqlTable, out);
					} catch (Exception e) {
						throw new Exception("生成出错!");
					}
				}
			}
		}
	}
}
