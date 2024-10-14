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
import com.is.sd_books.model.Circulate;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.report.basic.ExcelReport;
import com.is.utils.CheckNull;

public class DebitOrder extends ExcelReport {
	private Credentials credentials;
    private Circulate cir;
	public DebitOrder(Credentials credentials,Circulate cir) {
		this.credentials = credentials;
		this.cir=cir;
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
					credentials.getPassword(), credentials.getAlias());
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setInt(1, this.getBookId());
			ps.setString(2, this.getBranch());
			ps.executeUpdate();
			c.commit();
           
			//PreparedStatement ps1=c.prepareStatement(sql);
			CallableStatement css = c.prepareCall("{call info.init()}");
			css.execute();

			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setObject(1, this.getBookId());
			ISLogger.getLogger().error("DebitOrder_book_id : "+this.getBookId());
			cs.setString(2, this.getName());
			ISLogger.getLogger().error("DebitOrder_Name : "+this.getName());
			cs.setString(3, "10");
			cs.setString(4, this.getBranch());
			ISLogger.getLogger().error("DebitOrder_Branch : "+this.getBranch());
			cs.setObject(5, this.getBookId());
			ISLogger.getLogger().error("DebitOrder_Book_id2 : "+this.getBookId());
			//cs.setObject(5, this.cir.getId().longValue());
			cs.setObject(6, this.cir.getId().longValue());
			ISLogger.getLogger().error("DebitOrder_CIR_GETID : "+this.cir.getId().longValue());
			//System.out.println("rrerere:"+cir.getId().longValue());
			//System.out.println("imya_fayl: "+this.getName());
			
			cs.execute();
			c.commit();
			ps = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, this.getBookId());
			ps.setString(2, this.getBranch());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String branch = rs.getString("branch");
				replaceText(sheet,3,1,"<BRANCH>",branch);
				
				String branch1 = rs.getString("branch");
				replaceText(sheet,32,1,"<BRANCH>",branch1);
				
				String account = rs.getString("account");
				replaceText(sheet,3,4,"<ACCOUNT>",account);
				
				String account1 = rs.getString("account");
				replaceText(sheet,32,4,"<ACCOUNT>",account1);
				
				String filial = rs.getString("filial");
				replaceText(sheet,6,0,"<FILiAL>",filial);
				
				String client_code1=rs.getString("client_code");
				replaceText(sheet,0,3,"<CLIENT_CODE>",client_code1);
				
				String client_code2=rs.getString("client_code");
				replaceText(sheet,0,6,"<CLIENT_CODE>",client_code2);
				
				String client_code3=rs.getString("client_code");
				replaceText(sheet,28,3,"<CLIENT_CODE>",client_code3);
				
				String client_code4=rs.getString("client_code");
				replaceText(sheet,28,6,"<CLIENT_CODE>",client_code4);
				
				String name_dep1=rs.getString("name_dep");
				int ind1=(name_dep1.contains("("))?(name_dep1.lastIndexOf("(")):(name_dep1.length());
				String name_dep2=name_dep1.substring(0,ind1);
				replaceText(sheet,4,2,"<NAME_DEP>",name_dep2);
				
				String name_dep3=rs.getString("name_dep");
				int ind2=(name_dep3.contains("("))?(name_dep3.lastIndexOf("(")):(name_dep3.length());
				String name_dep4=name_dep2.substring(0,ind2);
				replaceText(sheet,33,2,"<NAME_DEP>",name_dep4);
								
				String account_db=rs.getString("account");
				replaceText(sheet,4,4,"<ACCOUNTD>",account_db);
				//Date open
				String summa_oper=rs.getString("SUMMA");
				replaceText(sheet,11,1,"<SUMMA>",summa_oper);
				
				String summa_oper4=rs.getString("SUMMA");
				replaceText(sheet,40,1,"<SUMMA>",summa_oper4);
				 
				String summa_oper_str=rs.getString("SUMMA_STR");
				replaceText(sheet,14,0,"<SUMMA_STR>",summa_oper_str);

				String summa_oper_str4=rs.getString("SUMMA_STR");
				replaceText(sheet,43,0,"<SUMMA_STR>",summa_oper_str4);
				
				
				
				String summa_saldo=rs.getString("saldo");
				replaceText(sheet,19,1,"<SALDO>",summa_saldo);
				
				String summa_saldo2=rs.getString("saldo");
				replaceText(sheet,48,1,"<SALDO>",summa_saldo2);
				
				String summa_oper1=rs.getString("SUMMA");
				replaceText(sheet,21,0,"<SUMMA>",summa_oper1);
				String summa_oper2=rs.getString("SUMMA");
				replaceText(sheet,50,0,"<SUMMA>",summa_oper2);
				
				String summa_oper_str2=rs.getString("SUMMA_STR");
				replaceText(sheet,22,0,"<SUMMA_STR>",summa_oper_str2);
				String summa_oper_str3=rs.getString("SUMMA_STR");
				replaceText(sheet,51,0,"<SUMMA_STR>",summa_oper_str3);
				
				Date oper_date =rs.getDate("DATE_OPER");
				replaceText(sheet,5,2,"<DATE>",oper_date != null ? df.format(oper_date):"");
				//fillRow(sheet,5,2,oper_date != null ? df.format(oper_date):"");
				
				//Date oper_date1 =rs.getDate("DATE_OPER");
				//fillRow(sheet,34,2,oper_date1 != null ? df.format(oper_date1):"");
				replaceText(sheet,34,2,"<DATE>",oper_date != null ? df.format(oper_date):"");
				
				String name = rs.getString("name");
				fillRow(sheet,7,0,name);
				
				String name1 = rs.getString("name");
				fillRow(sheet,36,0,name1);
				
				String pass_ser = rs.getString("pass_ser");
				replaceText(sheet,9,0,"<PASS_SER>",pass_ser);
				
				String pass_num = rs.getString("pass_num");
				replaceText(sheet,9,0,"<PASS_NUM>",pass_num);
				
				Date pass_date = rs.getDate("pass_date");
				replaceText(sheet,9,0,"<PASS_DATE>",pass_date != null ? df.format(pass_date): "");
				
				String pass_reg = rs.getString("pass_reg");
				replaceText(sheet,9,0,"<PASS_REG>",pass_reg);
				
				String pass_ser1 = rs.getString("pass_ser");
				replaceText(sheet,38,0,"<PASS_SER>",pass_ser1);
				
				String pass_num1 = rs.getString("pass_num");
				replaceText(sheet,38,0,"<PASS_NUM>",pass_num1);
				
				Date pass_date1 = rs.getDate("pass_date");
				replaceText(sheet,38,0,"<PASS_DATE>",pass_date1 != null ? df.format(pass_date1): "");
				
				String pass_reg1 = rs.getString("pass_reg");
				replaceText(sheet,38,0,"<PASS_REG>",pass_reg1);
											
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
