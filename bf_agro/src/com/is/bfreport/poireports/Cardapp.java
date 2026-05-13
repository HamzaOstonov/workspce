package com.is.bfreport.poireports;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
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

import com.is.ConnectionPool;
import com.is.bfreport.poireports.PoiReport;

public class Cardapp  extends PoiReport{

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		 AMedia repmd = null;
			
			try {
				
				 POIFSFileSystem fs = new  POIFSFileSystem(new FileInputStream(templf));  
				 HWPFDocument doc = new HWPFDocument(fs);
				c = ConnectionPool.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT a.branch," +	
						"a.app_id," +
						"d.contract_id," +
						"c.customer_id, c.inn," +
						"t.surname, t.first_name," +
						" t.second_name, " +
						"s.primary_phone," +
						"s.mobile_phone," +
						"t.birth_date,cp." +
						"post_address, " +
						"cp.passport_serial," +
						"cp.passport_number," +
						"s.address_line1," +
						"s.address_line2," +
						"(select m.region_nam from s_region m where m.region_id=s.region) region," +
						"(select name from s_str where code_str=s.country) country,s.country," +
						"t.p_id_number," +
						" t.p_id_series," +
						" cc.card_number," +
						" d.embossed_ch_name," +
						"t.p_id_authority,cc.card_number newcard_number, " +
						"d.embossed_ch_name newembossed_ch_name," +
						"(select bb.name from card_client_work vv ," +
						" client bb where vv.customer_id=bb.id_client and vv.branch=a.branch and vv.branch = bb.branch and vv.employee_id=cp.id and rownum=1 ) working  " +
						"      from card_applications     a,app_card_customer     c," +
						"card_person      " +
						"     t,card_cardholder_cards cc,card   d," +
						"APP_CARD_CH_ADDRESS   s, client_p  " +
						"  cp where a.branch=c.branch and c.branch=t.branch and " +
						"t.branch=cc.branch and cc.branch=d.branch and a.branch=s.branch and" +
						" a.branch='01066' and a.app_id = c.app_id and s.app_id=c.app_id and" +
						" c.customer_id = " +
						"t.customer_id and c.customer_id = cp.id and t.person_id =" +
						" cc.person_id and cc.card_number =" +
						" d.card_number and a.app_id = ?");
	            ps.setString(1, (String) params.get("p_client"));
            
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()){
	            	//doc = replaceText(doc, "$NAME", rs.getString("NAME"));
	            	//saveWord(filePath, doc);
	            	Range range = doc.getRange();
	                range.replaceText("<surname>", rs.getString("surname"));
	                range.replaceText("<first_name><", rs.getString("first_name"));
	                range.replaceText("<second_name>", rs.getString("second_name"));
	                range.replaceText("<passport_serial>",rs.getString("passport_serial"));
	                range.replaceText("<passport_number>", rs.getString("passport_number"));
	                range.replaceText("<card_number>", rs.getString("card_number"));
	                range.replaceText("<post_address>", rs.getString("post_address"));
	                range.replaceText("<newcard_number>", rs.getString("newcard_number"));
	                range.replaceText("<working>", rs.getString("working"));
	                range.replaceText("<birth_date>", rs.getString("birth_date"));
	            }
	            
	            
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				doc.write(out);
			    
			    out.close();
			    byte[] arr = out.toByteArray();
				repmd = new AMedia(outfl+".doc", "doc", "application/msword", arr);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionPool.close(c);
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

