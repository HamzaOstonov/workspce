package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.Circulate;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.report.basic.ExcelReport;
import com.is.sd_books.service.AssistantService;
import com.is.utils.CheckNull;

public class SdBook_prn2 extends ExcelReport {
	private Credentials credentials;
    private Circulate cir;
    
	public SdBook_prn2(Credentials credentials,Circulate cir) {
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
			CellStyle lockedCellStyle=workbook.createCellStyle();
			lockedCellStyle.setLocked(true);
			
			HSSFSheet sheet = workbook.getSheetAt(0);
			sheet.protectSheet("qwerty852369&?_%!");
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
			ISLogger.getLogger().error("SdBook_prn2_book_id : "+this.getBookId());
			cs.setString(2, this.getName());
			ISLogger.getLogger().error("SdBook_prn2_Name : "+this.getName());
			cs.setString(3, "10");
			cs.setString(4, this.getBranch());
			ISLogger.getLogger().error("SdBook_prn2_Branch : "+this.getBranch());
			cs.setObject(5, this.getBookId());
			ISLogger.getLogger().error("SdBook_prn2_BOOK_ID2 : "+this.getBookId());
			//cs.setObject(5, this.cir.getId().longValue());
			cs.setObject(6, this.cir.getId().longValue());
			ISLogger.getLogger().error("SdBook_prn2_CIRGETID : "+this.cir.getId().longValue());
			
			
			cs.execute();
			c.commit();
			
			
			System.out.println("Code_oper :"+this.cir.getFx_code());
			
			if(this.cir.getFx_code()==41||this.cir.getFx_code()==42||this.cir.getFx_code()==50||this.cir.getFx_code()==51){
				replaceText(sheet,22,4,"<OPER_N>","Открытие безнал");
				
			}
			else {
				replaceText(sheet,22,4,"<OPER_N>","Открытие нал");
			}
			
			//BigDecimal perc=this.cir.getPrc_saldo();
			
			System.out.println("OST_PROTSENT :"+this.cir.getPrc_saldo().toString());
			replaceText(sheet,22,28,"<PROC>",this.cir.getPrc_saldo().toString());
						
			ps = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, this.getBookId());
			ps.setString(2, this.getBranch());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String account1 = rs.getString("ACCOUNT");
				replaceText(sheet,0,15,"<ACCOUNT>",account1);
				
				String summa = rs.getString("SUMMA");
				replaceText(sheet,6,12,"<PRIHOD>",summa);
				
				String summa_str = rs.getString("SUMMA_STR");
				
				 String delims = (summa_str.contains(" ")==true?" ":".");
	              
	              ISLogger.getLogger().error("summa_str :"+summa_str.contains(" "));
	              
	              StringTokenizer st = new StringTokenizer(summa_str, delims);
	              System.out.println("Num of Token = " + st.countTokens());
	              String[] myArr= new String[st.countTokens()];
	              String res="";
	              String res2="";
	              for(int i=0; i<myArr.length && st.hasMoreTokens(); i++){
	                  myArr[i]=st.nextToken();
	                  if(res.length()<46) {
	              	  res += myArr[i]+" ";
	                      }
	                  else {
				      res2 +=myArr[i]+" "; 
				    	    
	                  }
				        }
	              
	              ISLogger.getLogger().error("res_SdBook_prn2 :"+res);
				  ISLogger.getLogger().error("res_2_SdBook_prn2 :"+res2);
	              System.out.println("length1 :"+res);
		          System.out.println("length2 :"+res2);
	              
		          replaceText(sheet,9,2,"<SUM2>",res);
		          replaceText(sheet,12,2,"<SUM3>",(res2==" "?" ":res2));
	          
		          java.sql.Date date_open = rs.getDate("DATE_OPEN");
		          System.out.println("date_open :"+date_open);
		         		          
		          replaceText(sheet,22,0,"<DATA>",date_open != null ? df.format(date_open):"");
		          
		          replaceText(sheet,22,8,"<PRIHOD>",summa);
		          String saldo = rs.getString("SALDO");

		          replaceText(sheet,22,21,"<SALDO>",saldo);
		         // String proc = rs.getString("PERC");
		          
		          
		          
		          	
		          
		          
				/*			
				String name3="";
				String name4="";
				int ind4=name_bookKeep.lastIndexOf(" ");

				if(name_bookKeep.length()>33){
					name3=name_bookKeep.substring(0,ind4);
					name4=name_bookKeep.substring(ind4,(name_bookKeep.length()-1));
					if(name3.length()>33){
						name3=name3.substring(0, name3.indexOf(" "));
						name4=name_bookKeep.substring(name3.indexOf(" "), (name_bookKeep.length()-1));
					}
				}
				else{
					name3=name_bookKeep;
				}
				ISLogger.getLogger().error("name_3_SdBook_prn2 :"+name3);
				ISLogger.getLogger().error("name_4_SdBook_prn2 :"+name4);
				replaceText(sheet,12,22,"<FIO>",name3);
				replaceText(sheet,15,20,"<FIO2>",name4);
				
				String account = rs.getString("account");
				replaceText(sheet,18,23,"<ACCOUNT>",account);
								
				String sval=rs.getString("CURRENCY_NAME");
				replaceText(sheet,21,22,"<SVAL>",sval);
				

				String name_dep1=rs.getString("name_dep");
				int ind1=(name_dep1.contains("("))?(name_dep1.lastIndexOf("(")):(name_dep1.length());
				String name_dep2=name_dep1.substring(0,ind1);
				replaceText(sheet,24,22,"<DEPNAME1>",name_dep2);
														
					*/										
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
