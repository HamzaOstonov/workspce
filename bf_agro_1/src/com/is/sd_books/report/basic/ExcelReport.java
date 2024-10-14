package com.is.sd_books.report.basic;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Filedownload;

public abstract class ExcelReport extends AbstractReport {
	public ExcelReport() {
		super();
	}
	
	public abstract AMedia getAmedia();

	@Override
	public void getReport() {
		AMedia media = getAmedia();
		Filedownload.save(media.getByteData(), "application/vnd.ms-excel",
				this.getFileName() + ".xls");

	}

	protected void fillRow(HSSFSheet sheet, int rowIndex, int cellNum,
			String value) {
		HSSFRow row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(cellNum);
		if (cell != null)
			cell.setCellValue(value);
	}

	protected void replaceText(HSSFSheet sheet, int rowIndex, int cellNum,
			String oldString, String newString) {
		HSSFRow row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(cellNum);
		if (cell != null) {
			String cellValue = cell.getStringCellValue();
			cellValue = cellValue.replace(oldString, newString);
			cell.setCellValue(cellValue);
		}
	}

}
