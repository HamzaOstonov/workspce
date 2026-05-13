package com.is.sd_books.report.basic;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Main {
	public static void main (String[] args) throws IOException{
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;

		try {
			fs = new POIFSFileSystem(new FileInputStream("C:\\Documents and Settings\\jamol\\Desktop\\CreditOrder.xls"));
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			HSSFRow row = sheet.getRow(29);
			Cell cell = row.getCell(0);
			if (cell != null) {
				String cellValue = cell.getStringCellValue();
				cellValue = cellValue.replace("<PASS_TYPE>", "6");
				cell.setCellValue(cellValue);
			}
			System.out.println(cell.getStringCellValue());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			workbook.close();
			fs.close();
		}
	}
}
