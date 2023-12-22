package com.is.dper_info.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class DperBigReport extends PoiReport {
	//Sheet 1,2,3,4
	private String countries = "select a.act,a.sng,a.code_str,a.char_code,UPPER(a.name) name,c.veoper_inrep,c.rezident,"+
           " c.rez_count,   c.rez_sum,"+
           " c.norez_count, c.norez_sum"+
           " from "+
           " (select b.region_,b.strana_code_str,b.strana_char_code,b.strana_name,b.veoper_inrep,b.rezident,"+
           " sum(decode(b.rezident,1,b.count_,2,0)) rez_count,   sum(decode(b.rezident,1,b.summa_,2,0))/100 rez_sum,"+
           " sum(decode(b.rezident,2,b.count_,1,0)) norez_count, sum(decode(b.rezident,2,b.summa_,1,0))/100 norez_sum"+
           " from dper_bigrep b"+
           " where ses_id=USERENV('SESSIONID') and b.rep_sign=1"+
           " and b.kind=?"+
           " group by b.region_,b.strana_code_str,b.strana_char_code,b.strana_name,b.veoper_inrep,b.rezident"+
           " ) c, s_str a"+
           " where a.code_str= c.strana_code_str (+) and a.code_str<>'860' and a.act='A'"+
           " and a.sng <8"+
           " order by a.sng,a.name,c.veoper_inrep,c.rezident";
	//Sheet 5,6,7,8
	private String regions = "select a.REGION_ID,UPPER(a.region_nam) name,c.veoper_inrep,c.rezident,"+
           " c.rez_count,   c.rez_sum,"+
           " c.norez_count, c.norez_sum"+
           " from "+
           " (select d.REGION_ID, b.veoper_inrep,b.rezident,"+
           " sum(decode(b.rezident,1,b.count_,2,0)) rez_count,   sum(decode(b.rezident,1,b.summa_,2,0))/100 rez_sum,"+
           " sum(decode(b.rezident,2,b.count_,1,0)) norez_count, sum(decode(b.rezident,2,b.summa_,1,0))/100 norez_sum"+
           " from dper_bigrep b,s_mfo d"+
           " where ses_id=USERENV('SESSIONID') and b.rep_sign=1"+
           " and b.kind=?"+
           " and b.branch=d.bank_id"+
           " and b.strana_code_str<>'860'"+
           " group by d.REGION_ID,b.veoper_inrep,b.rezident"+
           " ) c, s_region a"+
           " where a.REGION_ID = c.REGION_ID (+) and a.act='A'"+
           " order by name,c.veoper_inrep,c.rezident";
	
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia repmd = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.util.Date utilDate = (Date) params.get("date");
		
		FileInputStream file;
		try {
			file = new FileInputStream(new File(templf));
			HSSFWorkbook workbook = new HSSFWorkbook(file);  
			HSSFSheet sheet1 = workbook.getSheetAt(0);
			HSSFSheet sheet2 = workbook.getSheetAt(1);
			HSSFSheet sheet3 = workbook.getSheetAt(2);
			HSSFSheet sheet4 = workbook.getSheetAt(3);
			HSSFSheet sheet5 = workbook.getSheetAt(4);
			HSSFSheet sheet6 = workbook.getSheetAt(5);
			HSSFSheet sheet7 = workbook.getSheetAt(6);
			HSSFSheet sheet8 = workbook.getSheetAt(7);
			
			
			getBigRep(c, utilDate);
			//отчет по странам: сумма а, количество в
			fillSheets(c, sheet1, sheet3, true, 0 ,utilDate);
			//отчет по странам: сумма из, количество из
			fillSheets(c, sheet2, sheet4, true, 1 ,utilDate);
			//отчет по регионам: сумма а, количество в
			fillSheets(c, sheet5, sheet7, false, 0 ,utilDate);
			//отчет по регионам: сумма из, количество из
			fillSheets(c, sheet6, sheet8, false, 1 ,utilDate);
			
			deleteBigRep(c);
			
			
			sheet1.setForceFormulaRecalculation(true);
			sheet2.setForceFormulaRecalculation(true);
			sheet3.setForceFormulaRecalculation(true);
			sheet4.setForceFormulaRecalculation(true);
			sheet5.setForceFormulaRecalculation(true);
			sheet6.setForceFormulaRecalculation(true);
			sheet7.setForceFormulaRecalculation(true);
			sheet8.setForceFormulaRecalculation(true);
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
		    workbook.write(out);
		    out.close();
		    byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl, "xls", "application/vnd.ms-excel", arr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			ConnectionPool.close(c);
		}
		
		
		return repmd;
	}
	
	
	
	private void fillSheets(Connection c,HSSFSheet sh1,HSSFSheet sh2,boolean isCountries, int kind,Date date){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Row row = null;
		Cell cell = null;
		int rownum = isCountries?9:8;//isCountries = true отчет по странам
									//isCountries = false отчет по регионам Узбекистана
		int cellnum;
		try {
			ps = c.prepareStatement(isCountries?countries:regions);
			ps.setInt(1, kind);//kind = 0 - отправить, kind = 1 - получить
			rs = ps.executeQuery();
			
			while(rs.next()){
				cellnum = 6+3*rs.getInt("veoper_inrep");
			/************************
			 *	sheets 1,2,5,6		*
  			 *	сумма 				*
			 ************************/
				
				row = sh1.getRow(rownum);
				setDateToSheet(sh1, date);
				if (row == null) {
					row = sh1.createRow(rownum);
				}
				
				cell = row.getCell(cellnum);
				if (cell == null) {
					cell = row.createCell(cellnum);
				}
				cell.setCellValue(rs.getString("rez_sum"));
				
				cell = row.getCell(cellnum+1);
				if (cell == null) {
					cell = row.createCell(cellnum+1);
				}
        		cell.setCellValue(rs.getString("norez_sum"));
	        	
				/*********************************
	 			*	sheets 3,4,7,8					*
	  			*	количество 						*
				 ********************************/       		
        		row = sh2.getRow(rownum);
        		setDateToSheet(sh2,date);
				if (row == null) {
					row = sh1.createRow(rownum);
				}
				
				cell = row.getCell(cellnum);
				if (cell == null) {
					cell = row.createCell(cellnum);
				}
				cell.setCellValue(rs.getString("rez_count"));
				
				cell = row.getCell(cellnum+1);
				if (cell == null) {
					cell = row.createCell(cellnum+1);
				}
        		cell.setCellValue(rs.getString("norez_count"));
        		
        		
        		rownum++;
			}
			
			
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}finally {
			if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
	}
	
	private void setDateToSheet(HSSFSheet sheet, Date date){
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Row row = sheet.getRow(2);
		if(row == null){
			row = sheet.createRow(2);
		}
		Cell cell = row.getCell(1);
		if(cell == null){
			cell = row.createCell(1);
		}
		cell.setCellValue(df.format(date));
	}
	private Res getBigRep(Connection c,Date date){
		CallableStatement cs = null;
		ResultSet rs = null;
		Res res = null;
		try {
			cs = c.prepareCall("{ call dper2.GetBigRep_xls(?,0) }");
			cs.setDate(1, new java.sql.Date(date.getTime()));
			cs.execute();
			c.commit();
			res = new Res(0,"ok");
		} catch (SQLException e) {
			res = new Res(1,e.getMessage());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			if(cs != null){try {cs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return res;
	}
	
	private void deleteBigRep(Connection c){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareCall("delete from dper_bigrep where ses_id=USERENV('SESSIONID')");
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
	}
}
