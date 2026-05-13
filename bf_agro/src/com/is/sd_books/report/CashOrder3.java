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

public class CashOrder3 extends ExcelReport {

	private Credentials credentials;

	public CashOrder3(Credentials credentials) {
		this.credentials = credentials;
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
			c = ConnectionPool.getConnection(credentials.getLogin(),
					credentials.getPassword(), credentials.getPassword());
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setInt(1, this.getBookId());
			ps.setString(2, this.getBranch());
			ps.executeUpdate();
			c.commit();
			
			CallableStatement css = c.prepareCall("{call info.init()}");
			css.execute();
			
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
			ps = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, this.getBookId());
			ps.setString(2, this.getBranch());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String branch = rs.getString("branch");
				replaceText(sheet,5,0,"<BRANCH>",branch);
				
				String filial = rs.getString("filial");
				replaceText(sheet,6,0,"<FILIAL>",filial);
				
				Date open_date = rs.getDate("open_date");
				fillRow(sheet,9,0,open_date != null ? df.format(open_date):"");
				
				String name_dep = rs.getString("name_dep");
				replaceText(sheet,11,0,"<NAME_DEP>",name_dep);
				
				String name = rs.getString("name");
				replaceText(sheet,13,0,"<NAME>",name);
				
				String pass_type = rs.getString("pass_type");
				replaceText(sheet,15,0,"<PASS_TYPE>",pass_type);
				
				String pass_ser = rs.getString("pass_ser");
				replaceText(sheet,15,0,"<PASS_SER>",pass_ser);
				
				String pass_num = rs.getString("pass_num");
				replaceText(sheet,15,0,"<PASS_NUM>",pass_num);
				
				String summa = rs.getString("summa");
				replaceText(sheet,19,0,"<SUMMA>",summa);
				
				String summa_str = rs.getString("summa_str");
				replaceText(sheet,20,0,"<SUMMA_STR>",summa_str);
				
				String account = rs.getString("account");
				replaceText(sheet,22,0,"<ACCOUNT>",account);
				
				String username = getUsername(c);
				replaceText(sheet,24,0,"<USERNAME>",username);
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
