package com.is.bfreport.poireports;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;
import com.is.bfreport.poireports.PoiReport;
import com.is.utils.CheckNull;


public class EnthFirst extends PoiReport {

	public void myParam(){
		java.net.URL path = this.getClass().getClassLoader().getResource("");
		System.out.println(path);
	}
	
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
			AMedia repmd = null;
			CallableStatement cs = null;
			
			try {
				PreparedStatement ps1 = c.prepareStatement("select * from ss_dblink_branch where branch = ?");
				ps1.setString(1, (String) params.get("MFO"));
				ResultSet rs1 = ps1.executeQuery();
				if (rs1.next()){
					cs = c.prepareCall("alter session set current_schema = " + rs1.getString("USER_NAME"));
					cs.execute();
				}
				try {
					POIFSFileSystem fs = new  POIFSFileSystem(new FileInputStream(templf));				
					HWPFDocument doc = new HWPFDocument(fs);
					//c = ConnectionPool.getConnection();
					PreparedStatement ps = c.prepareStatement("select *  from client_j where branch = ? and id = ? ");
					ps.setString(1, (String) params.get("MFO"));
					ps.setString(2, (String) params.get("client_id"));
					System.out.println((String) params.get("client_id"));
					ResultSet rs = ps.executeQuery();
					System.out.println((String) params.get("MFO"));
					if (rs.next()){
						//doc = replaceText(doc, "$NAME", rs.getString("NAME"));
						//saveWord(filePath, doc);
						Range range = doc.getRange();       
		            	range.replaceText("<NAME>", CheckNull.isEmpty(rs.getString("NAME"))?"":rs.getString("NAME").toUpperCase());
		                range.replaceText("<J_DIRECTOR_NAME>", CheckNull.isEmpty(rs.getString("DIRECTOR_NAME"))?"":rs.getString("DIRECTOR_NAME"));
		                range.replaceText("<J_ACCOUNT>", CheckNull.isEmpty(rs.getString("ACCOUNT"))?"":rs.getString("ACCOUNT"));
		                range.replaceText("<J_NUMBER_TAX_REGISTRATION>",CheckNull.isEmpty(rs.getString("NUMBER_TAX_REGISTRATION"))?"":rs.getString("NUMBER_TAX_REGISTRATION"));
		                range.replaceText("<J_POST_ADDRESS>", CheckNull.isEmpty(rs.getString("POST_ADDRESS"))?"":rs.getString("POST_ADDRESS"));
		                range.replaceText("<BRANCH>", CheckNull.isEmpty(rs.getString("BRANCH"))?"":rs.getString("BRANCH"));
		                range.replaceText("<PHONE>", CheckNull.isEmpty(rs.getString("PHONE"))?"":rs.getString("PHONE"));
		                range.replaceText("<FAX>", CheckNull.isEmpty(rs.getString("FAX"))?"":rs.getString("FAX"));
		            	
		            }
					
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					doc.write(out);
				    
				    out.close();
				    byte[] arr = out.toByteArray();
					repmd = new AMedia(outfl+".doc", "doc", "application/msword", arr);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//ConnectionPool.close(c);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return repmd;
	}
	
	@SuppressWarnings("unused")
	private static HWPFDocument replaceText(final HWPFDocument doc, final String findText, final String replaceText){
        Range r1 = doc.getRange(); 

        for (int i = 0; i < r1.numSections(); ++i ) { 
            Section s = r1.getSection(i); 
            for (int x = 0; x < s.numParagraphs(); x++) { 
                Paragraph p = s.getParagraph(x); 
                for (int z = 0; z < p.numCharacterRuns(); z++) { 
                    CharacterRun run = p.getCharacterRun(z); 
                    String text = run.text();
                    if(text.contains(findText)) {
                    	try {
                        run.replaceText(findText, replaceText);
                    	} catch (Exception e) {e.printStackTrace();}
                    } 
                }
            }
        } 
        return doc;
    }
	

}
