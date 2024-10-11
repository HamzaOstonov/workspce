package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.utils.CheckNull;

public class Shahsiy2015 extends PoiReport{
	private final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private final static String emptyString = "";
	
	private Shahsiy2015(){}
	
	public static Shahsiy2015 getInstance(){
		return new Shahsiy2015();
	}
	
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia repMd = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;
		try{
			fs = new POIFSFileSystem(new FileInputStream(templf));
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			CallableStatement cs = c.prepareCall("{call sd_ww_service.all_parameters(?,?,?,?,?,?)}");
			cs.setObject(1, params.get("book_id"));
			cs.setString(2, "CredOrS.xls");
			cs.setString(3, null);
			cs.setString(4, params.get("branch").toString());
			cs.setObject(5, params.get("book_id"));
			cs.setString(6, null);
			cs.execute();
			c.commit();
			
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, params.get("book_id"));
			ps.setString(2, params.get("branch").toString());
			
			ResultSet rs = ps.executeQuery();
			if	(rs.next()){
				String finname = null;
				
				String minname = null;
				
				String account = rs.getString("account");
				
				String fio = rs.getString("name");
				
				String address = rs.getString("address");
				
				String dateborn = rs.getString("born_date");
				
				String phone = rs.getString("phone");
				
				String sroc = null;
				
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			repMd = new AMedia(outfl,"xls","application/vnd.ms-excel",out.toByteArray());
		}
		catch (Exception e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			try {
				workbook.close();
				fs.close();
			} catch (IOException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		return repMd;
	}
	
	private void fillRow(HSSFSheet sheet,int rowNum, int cellNum,String value){
		HSSFRow row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		if (cell!=null)
			cell.removeCellComment();
			cell.removeHyperlink();
			cell.setCellValue(value);
	}
	
	public void getReport(String reportClass, String template, String outFile,Map<String,Object> params){
		Connection c = null;
		PoiReport poiReport = null;
		AMedia repMd = null;
		try{
			c = ConnectionPool.getConnection();
			poiReport = (PoiReport) Class.forName(reportClass).newInstance();
			repMd = poiReport.getRepmd(params, c, Executions.getCurrent().getDesktop().getWebApp().getRealPath(template),outFile);
			Filedownload.save(repMd.getByteData(),"application/vnd.ms-excel",outFile + ".xls");
		}
		catch(Exception e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
	}
}
