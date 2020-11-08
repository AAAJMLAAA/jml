package springftl.word;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import springftl.dataSoure.DataSourceInfo;
import springftl.sql.SqlField;
import springftl.sql.SqlMessage2;


public class WordTemplate {
	public static void getWordByte(DataSourceInfo dataSourceInfo,HttpServletResponse response) throws Exception {
		SqlMessage2 sqlMessage2 = new SqlMessage2(dataSourceInfo);
		Map<String, List<SqlField>> tables = sqlMessage2.getTables();
		XWPFDocument document = new XWPFDocument();
		for (Map.Entry<String, List<SqlField>> map : tables.entrySet()) {
			List<SqlField> value = map.getValue();
			// 创建主题
			createTitleParagraph(document, map.getKey());
			// 创建表格
			createTableParagraph(document, value, 5);
		}

		document.write(response.getOutputStream());
	}

	/**
	 * 创建表格的标题样式
	 * 
	 * @param document
	 * @Author Huangxiaocong 2018年12月16日 下午5:28:38
	 */
	public static void createTitleParagraph(XWPFDocument document, String tableName) {
		XWPFParagraph titleParagraph = document.createParagraph(); // 新建一个标题段落对象（就是一段文字）
		titleParagraph.setAlignment(ParagraphAlignment.CENTER);// 样式居中
		XWPFRun titleFun = titleParagraph.createRun(); // 创建文本对象
		titleFun.setText(tableName); // 设置标题的名字
		titleFun.setBold(true); // 加粗
		titleFun.setColor("000000");// 设置颜色
		titleFun.setFontSize(25); // 字体大小
		titleFun.setFontFamily("宋体");// 设置字体
		titleFun.addBreak(); // 换行
	}

	/**
	 * 设置表格
	 * 
	 * @param document
	 * @param rows
	 * @param cols
	 * @Author Huangxiaocong 2018年12月16日
	 */
	public static void createTableParagraph(XWPFDocument document, List<SqlField> values, int cells) {
		XWPFTable infoTable = document.createTable(values.size(), 0);
		// 创建列数
		List<XWPFTableRow> rows2 = infoTable.getRows();
		int x = 0;
		for (XWPFTableRow xWPFTableRow : rows2) {
			if (x == 0) // 标题行
			{
				createCell(xWPFTableRow, "列名");
				createCell(xWPFTableRow, "类型");
				createCell(xWPFTableRow, "长度");
				createCell(xWPFTableRow, "主键");
				createCell(xWPFTableRow, "注释");
			} else {
				SqlField sqlField = values.get(x - 1);
				createCell(xWPFTableRow, sqlField.getColumnName());
				createCell(xWPFTableRow, sqlField.getColumnType());
				createCell(xWPFTableRow, sqlField.getColumnSize());
				createCell(xWPFTableRow, sqlField.getPriKey() == 0 ? "是" : "");
				createCell(xWPFTableRow, sqlField.getComment());
			}
			x++;
		}
	}

	public static void createCell(XWPFTableRow xWPFTableRow, String value) {
		XWPFTableCell addNewTableCell = xWPFTableRow.addNewTableCell();
		addNewTableCell.setText(value);
		CTTcPr tcpr = addNewTableCell.getCTTc().addNewTcPr();
		// 表格宽度
		CTTblWidth cellw = tcpr.addNewTcW();
		cellw.setType(STTblWidth.DXA);
		cellw.setW(BigInteger.valueOf(3600));
		// 垂直居中
		CTVerticalJc va = tcpr.addNewVAlign();
		va.setVal(STVerticalJc.CENTER);
	}
}
