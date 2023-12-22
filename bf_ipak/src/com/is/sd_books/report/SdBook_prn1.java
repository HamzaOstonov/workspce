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
//import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.Circulate;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.report.basic.ExcelReport;
import com.is.sd_books.service.AssistantService;
import com.is.utils.CheckNull;

public class SdBook_prn1 extends ExcelReport {
	private Credentials credentials;
    private Circulate cir;
	public SdBook_prn1(Credentials credentials,Circulate cir) {
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
			ISLogger.getLogger().error("all_parameters_book_id : "+this.getBookId());
			cs.setString(2, this.getName());
			ISLogger.getLogger().error("all_parameters_Name : "+this.getName());
			cs.setString(3, "10");
			cs.setString(4, this.getBranch());
			ISLogger.getLogger().error("all_parameters_Branch : "+this.getBranch());
			cs.setObject(5, this.getBookId());
			ISLogger.getLogger().error("all_parameters_Book_ID : "+this.getBookId());
			//cs.setObject(5, this.cir.getId().longValue());
			cs.setObject(6, this.cir.getId().longValue());
			ISLogger.getLogger().error("all_parameters_cirgetID : "+this.cir.getId().longValue());
			//System.out.println("rrerere:"+cir.getId().longValue());
			//System.out.println("imya_fayl: "+this.getName());
			
			cs.execute();	
			c.commit();
			ISLogger.getLogger().error("user_id_Abstract_report_SdBook_prn1 :"+this.getUid());
			System.out.println("user_id_Abstract_report :"+this.getUid());
			String user_name=AssistantService.getUserById(this.getUid(), credentials.getAlias());
			replaceText(sheet,30,22,"<USER_NAME>",user_name);
			
			StringBuilder par = new StringBuilder("000");
			String id1 = par.append(this.getBranch()).toString();
			PreparedStatement ps1=c.prepareStatement("select * from client_j h where h.branch=? and h.id=?");
			ps1.setString(1, this.getBranch().toString());
			ps1.setString(2, id1);

			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
              //Range range1=doc.getRange();
              String headofmName = rs1.getString("DIRECTOR_NAME");

              String delims = (headofmName.contains(" ")==true?" ":".");
              
              ISLogger.getLogger().error("Director_name :"+headofmName.contains(" "));
              ISLogger.getLogger().error("Director_name :"+headofmName);
              StringTokenizer st = new StringTokenizer(headofmName, delims);
              System.out.println("Num of Token = " + st.countTokens());
              String[] myArr= new String[st.countTokens()];
              for(int i=0; i<myArr.length && st.hasMoreTokens(); i++)
                  myArr[i]=st.nextToken();
              StringBuffer sb= new StringBuffer();
              sb.append(myArr[0]);
              sb.append(" ");
              sb.append(myArr[1].substring(0, 1).toUpperCase());
              sb.append(".");
              sb.append(myArr[2]==null?"":myArr[2].substring(0,1).toUpperCase());
              
              String headname1=sb.toString();
              
              replaceText(sheet,37,23,"<HEADOFBANK>",headname1);
             
              String AccountantName = rs1.getString("CHIEF_ACCOUNTER_NAME");
              String delims1= (AccountantName.contains(" ")==true?" ":".");
              ISLogger.getLogger().error("AccountantName :"+AccountantName.contains(" "));
              ISLogger.getLogger().error("AccountantName :"+AccountantName);
              StringTokenizer st1 = new StringTokenizer(AccountantName, delims1);
              System.out.println("Num of Token_1 = " + st1.countTokens());
              String[] myArr1= new String[st1.countTokens()];
              for(int i=0; i<myArr1.length && st1.hasMoreTokens(); i++)
                  myArr1[i]=st1.nextToken();
              StringBuffer sb1= new StringBuffer();
              sb1.append(myArr1[0]);
              sb1.append(" ");
              sb1.append(myArr1[1].substring(0, 1).toUpperCase());
              sb1.append(".");
              sb1.append(myArr1[2]==null?"":myArr1[2].substring(0,1).toUpperCase());
              
              String account_name = sb1.toString();
              replaceText(sheet,43,24,"<CHIEFACCOUNTANT_NAME>",account_name); 
              ISLogger.getLogger().error("CHIEFACCOUNTANT_NAME :--- replaced");
              String bname1="";
              String bname2="";
              String bName = rs1.getString("NAME");
              ISLogger.getLogger().error("Name_Bank :"+bName);
             if(bName.contains("ÀÃÐÎÁÀÍÊ")){
              int ind1=bName.indexOf("\"ÀÃÐÎÁÀÍÊ\"");
              ISLogger.getLogger().error("Name_Bank_ind1 :"+ind1);
              String bName_sub = bName.substring(ind1);
              ISLogger.getLogger().error("Name_Bank_substr :"+bName_sub+" ,length: "+bName_sub.length());
          //    int ind2=ind1+(bName.lastIndexOf("ÀÒÁ")+1);
              int ind3 = bName_sub.lastIndexOf(" ");
              String bName_sliced =bName_sub.substring(0, ind3);
              ISLogger.getLogger().error("bName_sliced :"+bName_sliced);
			  if(bName_sliced.length()>48){
				  int ind4 =  bName_sliced.lastIndexOf(" ");
			   bname1=bName_sliced.substring(0, ind4);
			   bname2=bName_sliced.substring(ind4);
			  }
			  else{
			   bname1 = bName_sliced;
			   bname2 = bName_sub.substring(ind3);
			  }
             }
             String bname4=(bname1==""?bName:bname1);
             ISLogger.getLogger().error("bName_sliced_bname4 :"+bname4);
			 replaceText(sheet,6,18,"<MINNAME>",bname4);
			 
			 String bname5=(bname2!=""?bname2:"");
			 ISLogger.getLogger().error("bName_sliced_bname5 :"+bname5);
			 replaceText(sheet,9,20,"<FILIALNAME>",bname5);
			  			  
			}
			
			PreparedStatement ps2 = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps2.setLong(1, this.getBookId());
			ps2.setString(2, this.getBranch());
			
			ResultSet rs = ps2.executeQuery();
		
			if (rs.next()) {
				String name_bookKeep = rs.getString("NAME");
				ISLogger.getLogger().error("name_bookKeep :"+name_bookKeep);
				String name3="";
				String name4="";
				int ind4=name_bookKeep.lastIndexOf(" ");

				if(name_bookKeep.length()>30){
					name3=name_bookKeep.substring(0,ind4);
					name4=name_bookKeep.substring(ind4);
					if(name3.length()>33){
						name3=name3.substring(0, name3.indexOf(" "));
						name4=name_bookKeep.substring(name3.indexOf(" "));
					}
				}
				else{
					name3=name_bookKeep;
				}
				ISLogger.getLogger().error("name_3_SdBook_prn1 :"+name3);
				ISLogger.getLogger().error("name_4_SdBook_prn1 :"+name4);
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
														
				replaceText(sheet,27,20,"<DEPNAME2>","");											
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
