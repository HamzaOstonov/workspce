import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.zkoss.util.media.AMedia;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.is.ConnectionPool;
import com.is.bfreport.poireports.PoiReport;

public class Clapp  extends PoiReport{

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		 AMedia repmd = null;
			
			try {
				
				 POIFSFileSystem fs = new  POIFSFileSystem(new FileInputStream(templf));  
				 HWPFDocument doc = new HWPFDocument(fs);
				c = ConnectionPool.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM client_p where id=?");
	            ps.setString(1, (String) params.get("p_client"));
            
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()){
	            	//doc = replaceText(doc, "$NAME", rs.getString("NAME"));
	            	//saveWord(filePath, doc);
	            	Range range = doc.getRange();
	                range.replaceText("$NAME", rs.getString("NAME"));

	            	
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
	
	private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
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