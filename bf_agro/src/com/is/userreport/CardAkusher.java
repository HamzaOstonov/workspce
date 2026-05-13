package com.is.userreport;



import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;

/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;*/
import com.is.ConnectionPool;


public class CardAkusher  extends UserReport{

	@Override
	
	
	public AMedia getRepmd(Map<String, Object> params,
			String templf, String outfl) {
		 AMedia repmd = null;
			
			try {
				
				
				 POIFSFileSystem fs = new  POIFSFileSystem(new FileInputStream(templf));  
				 HWPFDocument doc = new HWPFDocument(fs);
				 List<String> keys = new ArrayList<String>();
			        for(String key: params.keySet()) {
			          keys.add(key);
			        }
			    
			        for(int i = 0; i < params.size(); i++)
			          doc = replaceText(doc, "@" + keys.get(i), params.get(keys.get(i)).toString());
	            
				
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
			
			return repmd;
	}
	public static Map<String, Object> objToMap(Object obj, Map<String, Object> map) throws Exception {
	    Map<String, Object> result = new HashMap<String, Object>();
	        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
	        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	            Method reader = pd.getReadMethod();
	            if (reader != null)
	                result.put(pd.getName(), reader.invoke(obj));
	        }
	        
	        map.putAll(result);
	        return map;
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

