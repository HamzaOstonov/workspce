package com.is.bfreport.poireports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;

public class Testrep extends PoiReport {

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		 AMedia repmd = null;
		
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select b.bank_name_,m.bank_id, m.bank_name from s_mfo m, s_bank b where b.bank_type=m.bank_type");

			FileInputStream file = new FileInputStream(new File(templf));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rownum = 5;
			Cell cell;
			
			
			
			while (rs.next()) {
				
				Row row = sheet.createRow(rownum++);
				for (int cellnum = 0; cellnum<3;cellnum++){
				   cell = row.createCell(cellnum);
				   cell.setCellValue(rs.getString(cellnum+1));
				}
				
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//File fl = new File(outfl);
			//FileOutputStream out =  new FileOutputStream(fl);
		    workbook.write(out);
		    out.close();
		    byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl+".xls", "xls", "application/vnd.ms-excel", arr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		
		return repmd;
	}


	


}
