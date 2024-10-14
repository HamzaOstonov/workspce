package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

public class DebOrS extends PoiReport{
	private final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private final static String emptyString = "";
	
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia repMd = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;
		try{
			fs = new POIFSFileSystem(new FileInputStream(templf));
			workbook = new HSSFWorkbook(fs); 
			HSSFSheet sheet = workbook.getSheetAt(1);
			
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setInt(1, 12345678);
			cs.setString(2, "ApplSum.doc");
			cs.setString(3, null);
			cs.setString(4, /* params.get("branch").toString() */"00394");
			cs.setInt(5, /* params.get("book_id") */638017);
			cs.setString(6, null);
			cs.execute();
			c.commit();

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, params.get("book_id"));
			ps.setString(2, params.get("branch").toString());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				String id = rs.getString("id");
				fillRow(sheet,3,1,id);
				
				String name = rs.getString("name");
				fillRow(sheet,4,1,name);
				
				Date birthday = rs.getDate("birthday");
				String birth_place = rs.getString("birth_place");
				fillRow(sheet,5,1,((birthday != null) ? df.format(birthday) : emptyString) + "," + birth_place);
				
				String region = emptyString;
				fillRow(sheet,6,1,region);
				
				String citizen = rs.getString("code_citizenship");
				fillRow(sheet,7,1,citizen);
				
				String type_document = rs.getString("type_document");
				fillRow(sheet,10,2,type_document);
				
				String doc_ser = rs.getString("passport_serial");
				fillRow(sheet,11,2,doc_ser);
				
				String doc_num = rs.getString("passport_number");
				fillRow(sheet,12,2,doc_num);
				
				Date date_reg = rs.getDate("passport_date_registration");
				fillRow(sheet,13,2,(date_reg != null) ? df.format(date_reg) : emptyString);
				
				Date date_exp = rs.getDate("passport_date_expiration");
				fillRow(sheet,14,2,(date_exp != null) ? df.format(date_exp) : emptyString);
				
				String passport_place_registration = rs.getString("passport_place_registration");
				fillRow(sheet,15,2,passport_place_registration);
				
				String inn = rs.getString("number_tax_registration");
				fillRow(sheet,16,1,inn);
				
				String phone_home = rs.getString("phone_home");
				fillRow(sheet,17,2,phone_home);
				
				String phone_mobile = rs.getString("phone_mobile");
				fillRow(sheet,18,2,phone_mobile);
				
				String phone = emptyString;
				fillRow(sheet,19,2,phone);
				
				String som_opers = emptyString;
				fillRow(sheet,20,1,som_opers);
				
				String pod_opers = emptyString;
				fillRow(sheet,21,1,pod_opers);
				
				String add_data = emptyString;
				fillRow(sheet,22,1,add_data);
				
				String job_information = emptyString;
				fillRow(sheet,23,1,job_information);
				
				String account_open_purpose = emptyString;
				fillRow(sheet,24,1,account_open_purpose);
				
				String date_first_acc = emptyString;
				fillRow(sheet,25,1,date_first_acc);
				
				String emp_account_open_name = emptyString;
				fillRow(sheet,26,1,emp_account_open_name);
				
				String emp_account_confirm_name = emptyString;
				fillRow(sheet,27,1,emp_account_confirm_name);
				
				String accountOpen = emptyString;
				fillRow(sheet,28,1,accountOpen);
				
				String risk_degree_name = emptyString;
				fillRow(sheet,29,1,risk_degree_name);
				
				String risk_date = emptyString;
				fillRow(sheet,29,1,sheet.getRow(29).getCell(2) + risk_date);
				
				String risk_degree_detail = emptyString;
				fillRow(sheet,30,1,risk_degree_detail);
				
				String date_open = emptyString;
				fillRow(sheet,31,1,date_open);
				
				String date_change = emptyString;
				fillRow(sheet,32,1,date_change);
				
				String date_last_save = emptyString;
				fillRow(sheet,33,1,date_last_save);
				
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			repMd = new AMedia(outfl,"xls","application/vnd.ms-excel",out.toByteArray());
			workbook.close();
		}
		catch(Exception e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return repMd;
	}
	private void fillRow(HSSFSheet sheet,int rowNum, int cellNum,String value){
		HSSFRow row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		if (cell!=null)
			cell.setCellValue(value);
	}
	
	public void getReport(String reportClass,String template,String outFile,HashMap<String,Object> params){
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
