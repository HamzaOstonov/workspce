package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.service.AssistantService;
import com.is.utils.CheckNull;

public class ApplSum extends PoiReport {
	private final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private final static String emptyString = "";
	private Credentials cred;

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia media = null;
		
		//System.out.println("------MAP--4:"+params.get("un").toString());
		try {
			ISLogger.getLogger().error("fayl_params1:"+templf);
			ISLogger.getLogger().error("Verify_fayl1: "+templf.contains("ApplSum.doc"));
			
			POIFSFileSystem fs = new POIFSFileSystem(
					new FileInputStream(templf));
			
			ISLogger.getLogger().error("fayl_params2:"+templf);
			ISLogger.getLogger().error("Verify_fayl2: "+templf.contains("ApplSum.doc"));
				
			HWPFDocument doc = new HWPFDocument(fs);
			CallableStatement infoinit = c.prepareCall("{call info.init()}");
			infoinit.execute();
			//System.out.println("------MAP--5:"+params.get("un").toString());
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setInt(1, Integer.valueOf((int) Double.parseDouble(params.get("book_id")+"")));
			//cs.setDouble(1, Double.valueOf((String) params.get("book_id")));
			
			//System.out.println("file_name_:"+this.fileName);
			if(templf.contains("ApplSum.doc")){
			cs.setString(2, "ApplSum.doc");
			}
			else {
				cs.setString(2,"SD_Dep_sum.doc");
			}
			
			cs.setString(3, "10");
			cs.setString(4,  params.get("branch").toString() /*"00394"*/);
			//cs.setInt(5,  params.get("book_id") /*638017*/);
			cs.setInt(5, Integer.valueOf((int) Double.parseDouble(params.get("book_id")+"")));
			cs.setString(6, null);
			cs.execute();
			c.commit();
			//System.out.println("------MAP--6:"+params.get("un").toString());
			/*Range range1 = doc.getRange();*/
			StringBuilder par = new StringBuilder("000");
			String id1 = par.append(params.get("branch").toString()).toString();
			PreparedStatement ps1=c.prepareStatement("select * from client_j h where h.branch=? and h.id=?");
			ps1.setString(1, params.get("branch").toString());
			ps1.setString(2, id1);

			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
              Range range1=doc.getRange();
              String headofmName = rs1.getString("DIRECTOR_NAME");
			  replaceText("<HEADOFBANK>",headofmName,range1);
			  String bName = rs1.getString("NAME");
			  replaceText("<BNAME>",bName,range1);
			  String AccountantName = rs1.getString("CHIEF_ACCOUNTER_NAME");
			  replaceText("<CHIEFACCOUNTANT_NAME>",AccountantName,range1);
			  /*String acNumber = rs1.getString("ACCOUNT");
			  replaceText("<ACCOUNTB>",acNumber,range1);*/
			  String pos_adress = rs1.getString("POST_ADDRESS");
			  replaceText("<BANK_POST_ADRESS>",pos_adress,range1);
			  String taxNumber = rs1.getString("NUMBER_TAX_REGISTRATION");
			  replaceText("<BANK_TAX_NUMBER>",taxNumber,range1);
			  String phoneNumber = rs1.getString("PHONE");
			  replaceText("<BANK_PHONE>",phoneNumber,range1);
			}
			
			

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, params.get("book_id"));
			ps.setString(2, params.get("branch").toString());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Range range = doc.getRange();

				String branch = rs.getString("branch");

				String id = rs.getString("id");

				Date open_date = rs.getDate("date_open");
				replaceText("<DATE_OPEN>",(open_date != null) ? df.format(open_date) : "",range);

				String name = rs.getString("name");
				replaceText("<NAME>",name,range);
				String num1=rs.getString("NUM");
				replaceText("<NUM> ",num1,range);
				
				String summa=rs.getString("SUMMA");
				replaceText("<SUMMA>",summa,range);
				
				String summa_str=rs.getString("SUMMA_STR");
				replaceText("<SUMMA_STR>",summa_str,range);
				
				String acc=rs.getString("ACCOUNT");
				replaceText("<ACCOUNT>",acc,range);
				
				String br=rs.getString("BRANCH");
				replaceText("<BRANCH>",br,range);
				
				String pers=rs.getString("PERC");
				replaceText("<PERC>",((pers.equals(".1"))?"0.1":pers),range);
				System.out.println("pers:"+pers);
				
				String mon=rs.getString("SROC");
				replaceText("<MON>",((mon.equals("0"))?"Талаб килиб олингунча":mon),range);
				System.out.println("mon:"+mon);
				
				String client_id = rs.getString("client_id");

				String client_code = rs.getString("client_code");

				String resident_code = rs.getString("resident_code");

				String pass_type = rs.getString("pass_type");

				String pass_ser = rs.getString("pass_ser");
				replaceText("<PASS_SER>",pass_ser,range);

				String pass_num = rs.getString("pass_num");
				replaceText("<PASS_NUM>",pass_num,range);

				String pass_reg = rs.getString("pass_reg");
				replaceText("<PASS_REG>",pass_reg,range);

				Date pass_date = rs.getDate("pass_date");
				replaceText("<PASS_DATE>",(pass_date != null) ? df.format(pass_date) : emptyString, range);

				Date born_date = rs.getDate("born_date");
				replaceText("<BORN_DATE>",(born_date != null) ? df.format(born_date) : emptyString, range);

				String address = rs.getString("address");
				replaceText("<ADDRESS>",address,range);

				Date ins_date = rs.getDate("ins_date");

				String emp_code = rs.getString("emp_code");
				
				//String name_bank=AssistantService.getInfoBankName();

				String code_citizenship = rs.getString("code_citizenship");

				String birth_place = rs.getString("birth_place");

				String code_adr_region = rs.getString("code_adr_region");

				String code_adr_distr = rs.getString("code_adr_distr");

				String phone_home = rs.getString("phone_home");
				replaceText("<PHONE_HOME>",phone_home,range);

				String phone_mobile = rs.getString("phone_mobile");
				replaceText("<PHONE_MOBILE>",phone_mobile,range);
				String type_document = rs.getString("type_document");

				String name_type_doc = rs.getString("name_type_doc");

				String filial = rs.getString("filial");

				String dep = rs.getString("dep");

				String name_dep = rs.getString("name_dep");
				int ind1=(name_dep.contains("("))?(name_dep.lastIndexOf("(")):(name_dep.length());
				String name_dep1=name_dep.substring(0,ind1);
				replaceText("<NAME_DEP>",name_dep1,range);

				String num = rs.getString("num");

				String b_id = rs.getString("b_id");

				String type_calc = rs.getString("type_calc");

				Date date_open = rs.getDate("date_open");

				Date date_close = rs.getDate("date_close");
				System.out.println("date_close:"+date_close);
				DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
				String strdate="null";
				if(date_close==null){
					strdate="Талаб килиб олингунча";
				}
				else{
					strdate=dateformat.format(date_close);
				}
				
				replaceText("<DEADLINE>",strdate,range);

				String saldo = rs.getString("saldo");

				String state = rs.getString("state");

				String account = rs.getString("account");
				replaceText("<ACCOUNT>",account,range);

				String type_calc_show = rs.getString("type_calc_show");

				String regime = rs.getString("regime");

				String dog_num = rs.getString("dog_num");

				Date dog_dat = rs.getDate("dog_dat");

				/*Date date = rs.getDate("date");
				
				replaceText("<DATE>",(date != null) ? df.format(date) : emptyString, range);*/

				String dep_type = rs.getString("dep_type");
				replaceText("<DEP_TYPE>",dep_type,range);

				String currency_name = rs.getString("currency_name");
				replaceText("<CURRENCY_NAME>",currency_name,range);

				String clerk_ser = rs.getString("clerk_ser");
				replaceText("<CLERK_SER>",clerk_ser,range);
				String clerk_num = rs.getString("clerk_num");
				replaceText("<CLERK_NUM>",clerk_num,range);

				Date passport_date_expiration = rs
						.getDate("passport_date_expiration");

				String name_citizenship = rs.getString("name_citizenship");

				String email_address = rs.getString("email_address");
				
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.write(out);
			media = new AMedia(outfl + ".doc", "doc", "application/msword",
					out.toByteArray());
			out.close();
			fs.close();
			
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			
			ConnectionPool.close(c);
		}
		return media;
	}

	public static void replaceText(String arg0, String arg1, Range range) {
		if (arg1 != null)
			range.replaceText(arg0, arg1);
	}
	
	/*private static void replaceText(String findText, String replaceText,Range r1){
        for (int i = 0; i < r1.numSections(); ++i ) { 
            Section s = r1.getSection(i); 
            for (int x = 0; x < s.numParagraphs(); x++) { 
                Paragraph p = s.getParagraph(x); 
                for (int z = 0; z < p.numCharacterRuns(); z++) { 
                    CharacterRun run = p.getCharacterRun(z); 
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    } 
                }
            }
        } 
    }*/

	public void getReport(String reportClass, String template, String outFile,
			Map<String, Object> params) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(params.get("un").toString(),
					params.get("pwd").toString(), params.get("alias")
							.toString());
			PoiReport report = (PoiReport) Class.forName(reportClass)
					.newInstance();
			AMedia media = report.getRepmd(params, c, Executions.getCurrent()
					.getDesktop().getWebApp().getRealPath(template), outFile);
			System.out.println("7878788");
			
			Filedownload.save(media.getByteData(), "application/msword",
					outFile + ".doc");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
}
