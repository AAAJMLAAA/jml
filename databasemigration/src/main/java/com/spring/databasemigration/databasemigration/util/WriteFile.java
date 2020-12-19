package com.spring.databasemigration.databasemigration.util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.databasemigration.databasemigration.concast.TableConcast;
import com.spring.databasemigration.databasemigration.pojo.ColumnData;
import com.spring.databasemigration.databasemigration.vo.TableVo;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 输出文件
 * 
 * @author jinmingliang
 *
 */
public class WriteFile {
	
	@SuppressWarnings("deprecation")
	public static void WriteToFile(List<TableVo> tableVos) throws Exception {
		Configuration conf = new Configuration();
		// 加载模板文件(模板的路径)
		conf.setDirectoryForTemplateLoading(new File(TableConcast.TEMPLATE_PATH));
		Template template = conf.getTemplate("/table2.ftl");
		Writer out = new FileWriter(TableConcast.TARGET_PATH);
		Map<String,List<TableVo>> map = new HashMap<>();
		map.put("tableVos", tableVos);
		template.process(map, out);
	}
	
	@SuppressWarnings("deprecation")
	public static void WriteToFileData(List<ColumnData> columnDatas) throws Exception {
		Configuration conf = new Configuration();
		// 加载模板文件(模板的路径)
		conf.setDirectoryForTemplateLoading(new File(TableConcast.TEMPLATE_PATH));
		Template template = conf.getTemplate("/sql.ftl");
		Writer out = new FileWriter(TableConcast.TARGET_PATH2);
		Map<String,List<ColumnData>> map = new HashMap<>();
		map.put("columnDatas", columnDatas);
		template.process(map, out);
	}

}
