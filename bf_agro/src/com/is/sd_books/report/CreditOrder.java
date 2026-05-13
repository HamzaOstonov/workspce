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
import com.is.sd_books.report.basic.ExcelReport;
import com.is.utils.CheckNull;

public class CreditOrder extends ExcelReport {
	private String un;
	private String pwd;
	private String alias;
	private Circulate cir1;
	private Long general_id;
	public CreditOrder(String un,String pwd,String alias,Circulate cir1,Long general_id){
		super();
		this.un = un;
		this.pwd = pwd;
		this.alias = alias;
		this.cir1=cir1;
		this.general_id=general_id;
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
			Long gen_id=this.general_id;
			System.out.println("gen_id:"+gen_id.toString());
			fillRow(sheet,1,4,gen_id.toString()==null?"":gen_id.toString());
			fillRow(sheet,21,5,gen_id.toString()==null?"":gen_id.toString());
			fillRow(sheet,41,5,gen_id.toString()==null?"":gen_id.toString());
			c = ConnectionPool.getConnection(un,pwd,alias);
			PreparedStatement ps = c.prepareStatement("DELETE FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setInt(1, this.getBookId());
			ps.setString(2, this.getBranch());
			ps.executeUpdate();
			c.commit();
			
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setObject(1, this.getBookId());
			ISLogger.getLogger().error("CreditOrder_book_id : "+this.getBookId());
			cs.setString(2, this.getName());
			ISLogger.getLogger().error("CreditOrder_NAME : "+this.getName());
			cs.setString(3, "10");
			cs.setString(4, this.getBranch());
			ISLogger.getLogger().error("CreditOrder_Branch : "+this.getBranch());
			cs.setObject(5, this.getBookId());
			ISLogger.getLogger().error("CreditOrder_BOOK_ID2 : "+this.getBookId());
			cs.setObject(6, this.cir1.getId().longValue());
			ISLogger.getLogger().error("CreditOrder_GETID : "+this.cir1.getId().longValue());
			cs.execute();
			c.commit();
			ps = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, this.getBookId());
			ps.setString(2, this.getBranch());

			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				String branch = rs.getString("branch");
				//fillRow(sheet,2,1,branch);
				replaceText(sheet,2,1,"<BRANCH>",branch);
				
				String branch1 = rs.getString("branch");
				//fillRow(sheet,2,1,branch);
				replaceText(sheet,23,2,"<BRANCH>",branch1);				
				
				String account = rs.getString("account");
				replaceText(sheet,2,4,"<ACCOUNTC>",account);
				replaceText(sheet,25,1,"<ACCOUNTC>",account);
								
				
				/*String id=rs.getString("ID");
				replaceText(sheet,1,3,"<ACCOUNTC>",account);
*/				
				Date oper_date = rs.getDate("DATE_OPER");
				replaceText(sheet,3,2,"<DATE>",oper_date != null ? df.format(oper_date):"");
				replaceText(sheet,34,2,"<DATE>",oper_date != null ? df.format(oper_date):"");
				replaceText(sheet,25,2,"<DATE>",oper_date != null ? df.format(oper_date):"");
				replaceText(sheet,44,3,"<DATE>",oper_date != null ? df.format(oper_date):"");
				//fillRow(sheet,3,2,oper_date != null ? df.format(oper_date):"");
				//fillRow(sheet,34,2,oper_date != null ? df.format(oper_date):"");
				//fillRow(sheet,25,0,oper_date != null ? df.format(oper_date) : "");
				//fillRow(sheet,44,3,oper_date!=null ? df.format(oper_date):"");
				
				String name = rs.getString("name");
				fillRow(sheet,4,0,name);
				
				String summa = rs.getString("summa");
				fillRow(sheet,7,0,summa);
				fillRow(sheet,31,0,summa);
				
				String summa_str = rs.getString("summa_str");
				fillRow(sheet,7,1,summa_str);
				fillRow(sheet,31,1,summa_str);
				
				String name_dep1 = rs.getString("name_dep");
				int ind1=(name_dep1.contains("("))?(name_dep1.lastIndexOf("(")):(name_dep1.length());
				System.out.println("idnd:"+ind1);
				String name_dep2=name_dep1.substring(0,ind1);
				System.out.println("name_dep2:"+name_dep2);
				replaceText(sheet,9,2,"<NAME_DEP>",name_dep2);

				String summa1 = rs.getString("summa");
				fillRow(sheet,15,1,summa1);
				String saldo1 = rs.getString("saldo");
				fillRow(sheet,15,4,saldo1);

				
				fillRow(sheet,11,1,name);
				
				fillRow(sheet,14,1,summa);
				
				String saldo = rs.getString("saldo");
				fillRow(sheet,14,4,saldo);
				fillRow(sheet,36,4,saldo);
				
				String currency_name = rs.getString("currency_name");
				fillRow(sheet,14,5,currency_name);
				
				//String username
				
				//fillRow(sheet,22,0,branch);
				
				/*String filial = rs.getString("filial");
				fillRow(sheet,23,0,filial);
				*/
				
				
				String name_dep = rs.getString("name_dep");
				int ind2=(name_dep.contains("("))?(name_dep.lastIndexOf("(")):(name_dep.length());
				String name_dep3=name_dep.substring(0,ind2);
				replaceText(sheet,26,0,"<NAME_DEP>",name_dep3);
				replaceText(sheet,33,2,"<NAME_DEP>",name_dep3);
				replaceText(sheet,46,2,"<NAME_DEP>",name_dep3);
				
				fillRow(sheet,27,0,name);
				
				String pass_type = rs.getString("pass_type");
				replaceText(sheet,51,0,"<PASS_TYPE>",pass_type);
				
				String pass_ser = rs.getString("pass_ser");
				replaceText(sheet,51,0,"<PASS_SER>",pass_ser);
				
				String pass_num = rs.getString("pass_num");
				replaceText(sheet,51,0,"<PASS_NUM>",pass_num);

				Date pass_date = rs.getDate("pass_date");
				
				replaceText(sheet,51,0,"<PASS_DATE>",pass_date != null ? df.format(pass_date): "");
				
				String pass_reg = rs.getString("pass_reg");
				replaceText(sheet,51,0,"<PASS_REG>",pass_reg);
				
				//replaceText(sheet,34,0,"<ACCOUNT>",account);
				
				fillRow(sheet,42,2,branch);
				
				replaceText(sheet,44,3,"<BRANCH>",branch);
				
				//replaceText(sheet,45,4,"<FILIAL>",filial);
				
				
				
				replaceText(sheet,46,1,"<ACCOUNT>",account);
								
				fillRow(sheet,48,1,name);
				
				fillRow(sheet,53,2,summa);
				
				fillRow(sheet,54,0,summa_str);
				/*
				fillRow(sheet,50,2,summa);
				
				fillRow(sheet,51,1,summa_str);
				*/
				//fillRow(sheet,53,2,open_date != null ? df.format(open_date):"");
				
				//fillRow(sheet,55,1,summa);
				
				//fillRow(sheet,54,2,currency_name);
				
				fillRow(sheet,56,2,account);
				fillRow(sheet,58,4,saldo);
				
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
