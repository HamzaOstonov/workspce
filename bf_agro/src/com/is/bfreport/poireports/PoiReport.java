package com.is.bfreport.poireports;

import java.sql.Connection;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.util.media.AMedia;

public  abstract class PoiReport {

	public PoiReport() {
		super();

	}

	public  abstract AMedia getRepmd(Map<String, Object> params, Connection c, String templf, String outfl);
	
	private static void replaceval(HSSFSheet sheet, String cellContent, String newChar){
	    for(Row row : sheet) {
	            //System.out.println("row "+row.getRowNum());
	        for(Cell cell : row) {
	                //System.out.println("cell "+cell.getColumnIndex());
	            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
	                if(cell.getRichStringCellValue().getString ().contains(cellContent) );{
	                        cell.setCellValue(     cell.getStringCellValue().replace(cellContent, newChar));
	                }
	            }
	        }
	    }

	}

}
