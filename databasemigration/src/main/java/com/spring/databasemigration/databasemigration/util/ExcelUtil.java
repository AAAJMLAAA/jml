package com.spring.databasemigration.databasemigration.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	public static int firstRow = 0;
	public static int lastRow = 0;
	public static int firstColumn = 0;
	public static int lastColumn = 0;
	public static int index = 0;

	public static void changeSort(Sheet sheetAt) {
		if (sheetAt.getNumMergedRegions() > index)
		{
			CellRangeAddress cellRangeAddress = sheetAt.getMergedRegion(index);
			firstRow = cellRangeAddress.getFirstRow();
			lastRow = cellRangeAddress.getLastRow();
			firstColumn = cellRangeAddress.getFirstColumn();
			lastColumn = cellRangeAddress.getLastColumn();
			
			System.out.println();
		}
		
	}

	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(new File("C:/Users/19506/Desktop/23.xls"));
		Workbook workbook = new HSSFWorkbook(in);
		Sheet sheetAt = workbook.getSheetAt(0);
		int lastRowNum = sheetAt.getLastRowNum() + 1;
		 StringBuffer stringBuffer = new StringBuffer();
		 changeSort(sheetAt);
		 stringBuffer.append("<table border=\"8\" align=\"center\">");
		 for (int x = 0; x < lastRowNum; x++) {
		 Row row = sheetAt.getRow(x);
		 stringBuffer.append("<tr>");
		 int lastCellNum = row.getPhysicalNumberOfCells();
		 if (firstRow == x){
			 
			 for (int y = 0; y < lastCellNum; y++) {
					Cell cell = row.getCell(y);
					// 遇到合并的数据
					if (firstColumn == y)
					 {
						 System.out.println("合并了");
					 }
				 }
		 }
		
		 stringBuffer.append("</table>");
		 System.out.println(stringBuffer.toString());
	}
}
