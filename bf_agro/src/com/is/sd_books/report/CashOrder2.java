package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.report.basic.ExcelReport;
import com.is.utils.CheckNull;

public class CashOrder2 extends ExcelReport{
	
	public CashOrder2(Credentials credentials){
	}
	
	@Override
	public AMedia getAmedia() {
		AMedia media = null;
		Connection c = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;

		try {
			fs = new POIFSFileSystem(new FileInputStream(this.getPath()));
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setInt(1, this.getBookId());
			ps.setString(2, this.getBranch());
			ps.executeUpdate();
			c.commit();
			
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setObject(1, this.getBookId());
			cs.setString(2, "CredOrS.xls");
			cs.setString(3, null);
			cs.setString(4, this.getBranch());
			cs.setObject(5, this.getBookId());
			cs.setString(6, null);
			cs.execute();
			c.commit();
			ps = c
					.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, this.getBookId());
			ps.setString(2, this.getBranch());

			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				String branch = rs.getString("branch");
				fillRow(sheet,2,1,branch);
				
				String account = rs.getString("account");
				replaceText(sheet,2,3,"<ACCOUNT>",account);
				
				Date open_date = rs.getDate("open_date");
				fillRow(sheet,4,1,open_date != null ? df.format(open_date):"");
				
				String name = rs.getString("name");
				fillRow(sheet,5,0,name);
				
				String summa = rs.getString("summa");
				fillRow(sheet,8,0,summa);
				
				String summa_str = rs.getString("summa_str");
				fillRow(sheet,8,1,summa_str);
				
				fillRow(sheet,11,1,name);
				
				fillRow(sheet,14,1,summa);
				
				String saldo = rs.getString("saldo");
				fillRow(sheet,14,4,saldo);
				
				String currency_name = rs.getString("currency_name");
				fillRow(sheet,14,5,currency_name);
				
				//String username
				
				fillRow(sheet,22,0,branch);
				
				String filial = rs.getString("filial");
				fillRow(sheet,23,0,filial);
				
				fillRow(sheet,25,0,open_date != null ? df.format(open_date) : "");
				
				String name_dep = rs.getString("name_dep");
				fillRow(sheet,26,0,name_dep);
				
				fillRow(sheet,27,0,name);
				
				String pass_type = rs.getString("pass_type");
				replaceText(sheet,29,0,"<PASS_TYPE>",pass_type);
				
				fillRow(sheet,31,1,summa);
				
				fillRow(sheet,32,0,summa_str);
				
				replaceText(sheet,34,0,"<ACCOUNT>",account);
				
				fillRow(sheet,44,1,branch);
				
				replaceText(sheet,44,3,"<BRANCH>",branch);
				
				replaceText(sheet,45,4,"<FILIAL>",filial);
				
				fillRow(sheet,46,0,open_date!=null ? df.format(open_date):"");
				
				replaceText(sheet,46,1,"<ACCOUNT>",account);
				
				replaceText(sheet,46,2,"<ACCOUNT>",account);
				
				fillRow(sheet,47,0,name);
				
				fillRow(sheet,48,1,name);
				
				fillRow(sheet,50,0,summa);
				
				fillRow(sheet,50,1,summa_str);
				
				fillRow(sheet,50,2,summa);
				
				fillRow(sheet,51,1,summa_str);
				
				fillRow(sheet,53,2,open_date != null ? df.format(open_date):"");
				
				fillRow(sheet,55,1,summa);
				
				fillRow(sheet,54,2,currency_name);
				
				fillRow(sheet,57,1,saldo);
				
				fillRow(sheet,58,2,currency_name);
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			media = new AMedia(this.getPath(), "xls",
					"application/vnd.ms-excel", out.toByteArray());
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} catch (IOException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
			try {
				workbook.close();
				fs.close();
			} catch (IOException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		return media;
	}
}
