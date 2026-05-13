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

public class CredOrS extends PoiReport{
	private final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private final static String emptyString = "";
	
	private CredOrS(){}
	
	public static CredOrS getInstance(){return new CredOrS();}
	
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia repMd = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;
		try{
			fs = new POIFSFileSystem(new FileInputStream(templf));
			workbook = new HSSFWorkbook(fs); 
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
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
			
			if (rs.next()){
				String branch = rs.getString("branch");

				String id = rs.getString("id");

				Date open_date = rs.getDate("open_date");

				String name = rs.getString("name");
				fillRow(sheet,5,1,name);

				String client_id = rs.getString("client_id");

				String client_code = rs.getString("client_code");

				String resident_code = rs.getString("resident_code");

				String pass_type = rs.getString("pass_type");

				String pass_ser = rs.getString("pass_ser");

				String pass_num = rs.getString("pass_num");

				String pass_reg = rs.getString("pass_reg");

				Date pass_date = rs.getDate("pass_date");

				Date born_date = rs.getDate("born_date");

				String address = rs.getString("address");

				Date ins_date = rs.getDate("ins_date");

				String emp_code = rs.getString("emp_code");

				String code_citizenship = rs.getString("code_citizenship");

				String birth_place = rs.getString("birth_place");

				String code_adr_region = rs.getString("code_adr_region");

				String code_adr_distr = rs.getString("code_adr_distr");

				String phone_home = rs.getString("phone_home");

				String phone_mobile = rs.getString("phone_mobile");

				String type_document = rs.getString("type_document");

				String name_type_doc = rs.getString("name_type_doc");

				String filial = rs.getString("filial");

				String dep = rs.getString("dep");

				String name_dep = rs.getString("name_dep");

				String num = rs.getString("num");

				String b_id = rs.getString("b_id");

				String type_calc = rs.getString("type_calc");

				Date date_open = rs.getDate("date_open");

				Date date_close = rs.getDate("date_close");

				String saldo = rs.getString("saldo");

				String state = rs.getString("state");

				String account = rs.getString("account");
				

				String type_calc_show = rs.getString("type_calc_show");

				String regime = rs.getString("regime");

				String dog_num = rs.getString("dog_num");

				Date dog_dat = rs.getDate("dog_dat");

				String dep_type = rs.getString("dep_type");

				String currency_name = rs.getString("currency_name");

				String clerk_ser = rs.getString("clerk_ser");

				String clerk_num = rs.getString("clerk_num");

				Date passport_date_expiration = rs
						.getDate("passport_date_expiration");

				String name_citizenship = rs.getString("name_citizenship");

				String email_address = rs.getString("email_address");
				
				String accountc = rs.getString("accountc");
				fillRow(sheet,32,3,"12312321");
			}
			sheet.setForceFormulaRecalculation(true);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			repMd = new AMedia(outfl,"xls","application/vnd.ms-excel",out.toByteArray());
			workbook.close();
			fs.close();
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
			cell.removeCellComment();
			cell.removeHyperlink();
			cell.setCellValue(value);
	}
	
	public void getReport(String reportClass,String template,String outFile,Map<String,Object> params){
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
