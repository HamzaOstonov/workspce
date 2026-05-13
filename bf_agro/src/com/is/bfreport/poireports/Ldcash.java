package com.is.bfreport.poireports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;



import oracle.jdbc.OracleTypes;

import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.bfreport.poireports.PoiReport;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;

public class Ldcash extends PoiReport{

	@SuppressWarnings("null")
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		 AMedia repmd = null;
			
			try {
				//c = ConnectionPool.getConnection();
				

				FileInputStream file = new FileInputStream(new File(templf));// Взяли темлейт
				HSSFWorkbook workbook = new HSSFWorkbook(file);  //открыли книгу
				HSSFSheet sheet = workbook.getSheetAt(0); // Выбрали нужный лист

				
				//------------------------------------------------------------------------------------
				/*
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select b.bank_name_,m.bank_id, m.bank_name from s_mfo m, s_bank b where b.bank_type=m.bank_type");
                 */
				
				CallableStatement cs = c.prepareCall("{ call rep_ldf_payment.rep_05(?,?) }");
	    		cs.registerOutParameter(1, OracleTypes.CURSOR);
	    		java.util.Date rdt = (Date) params.get("p_rep_date");
	    		cs.setDate(2,new java.sql.Date(  rdt.getTime() ));
	    		cs.execute();
	    		ResultSet rs = (ResultSet)cs.getObject(1);

				int rownum = 10;
				Row row = sheet.getRow(rownum++);
				Cell cell;
				
				//rs = s.executeQuery("select name,birthday,post_address,phone_home,passport_serial,passport_number from client_p");
				rownum = 8;		
				while (rs.next()) {
					row = sheet.getRow(rownum++);
					
					cell = row.getCell(1);				
					cell.setCellValue(rs.getString("Наименование филиалов"));                  

					cell = row.getCell(2);	
				    cell.setCellValue(rs.getInt("Кол-во без.нал.Тек.месяц"));
					
				    cell = row.getCell(3);
					cell.setCellValue(rs.getInt("Сумма без.нал.Тек.месяц"));
					
					cell = row.getCell(4);
					cell.setCellValue(rs.getInt("Кол-во нал.Тек.месяц"));
					
					cell = row.getCell(5);
					cell.setCellValue(rs.getInt("Сумма нал.Тек.месяц"));
					
					cell = row.getCell(6);
					cell.setCellValue(rs.getInt("Кол-во всего Тек.месяц"));
					cell = row.getCell(7);
					cell.setCellValue(rs.getInt("Сумма всего Тек.месяц"));
					cell = row.getCell(8);
					cell.setCellValue(rs.getInt("Кол-во без.нал.Тек."));
					cell = row.getCell(9);
					cell.setCellValue(rs.getInt("Сумма без.нал.Тек."));
					cell = row.getCell(10);
					cell.setCellValue(rs.getInt("Кол-во нал.Тек."));
					cell = row.getCell(11);
					cell.setCellValue(rs.getInt("Сумма нал.Тек."));
					cell = row.getCell(12);
					cell.setCellValue(rs.getInt("Кол-во в доллар Тек."));
					cell = row.getCell(13);
					cell.setCellValue(rs.getInt("Сумма в доллар Тек."));
					cell = row.getCell(14);
					cell.setCellValue(rs.getInt("Кол-во всего Тек."));
					cell = row.getCell(15);
					cell.setCellValue(rs.getInt("Сумма всего Тек."));
					cell = row.getCell(16);
					cell.setCellValue(rs.getInt("Кол-во без.нал.Проср."));
					cell = row.getCell(17);
					cell.setCellValue(rs.getInt("Сумма без.нал.Проср."));
					cell = row.getCell(18);
					cell.setCellValue(rs.getInt("Кол-во нал.Проср."));
					cell = row.getCell(19);
					cell.setCellValue(rs.getInt("Сумма нал.Проср."));
					cell = row.getCell(20);
					cell.setCellValue(rs.getInt("Кол-во в доллар Проср."));
					cell = row.getCell(21);
					cell.setCellValue(rs.getInt("Сумма в доллар Проср."));
					cell = row.getCell(22);
					cell.setCellValue(rs.getInt("Кол-во всего Проср."));
					cell = row.getCell(23);
					cell.setCellValue(rs.getInt("Сумма всего Проср."));
				}
				
			sheet.setForceFormulaRecalculation(true);
			
//-----------------------------------------------------------------------------------------------------				
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
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
