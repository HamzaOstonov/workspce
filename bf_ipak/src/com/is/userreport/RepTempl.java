package com.is.userreport;



import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.zkoss.util.media.AMedia;


public class RepTempl  {

	//@Override
	
	
	public static AMedia getRepmd(Map<String, Object> params,
			String templf, String outfl) {
		 AMedia repmd = null;
			
			try {
				
				
				 POIFSFileSystem fs = new  POIFSFileSystem(new FileInputStream(templf));  
				 HWPFDocument doc = new HWPFDocument(fs);
				 
				 List<String> keys = new ArrayList<String>();
			        for(String key: params.keySet()) {
			          keys.add(key);
			        }
			    
			        for(int i = 0; i < keys.size(); i++){
			           if (keys.get(i)!=null)	{
			        	 //  System.out.println("key "+keys.get(i));
			           //  System.out.println("key "+keys.get(i)+" value "+params.get(keys.get(i)));
			        	  doc = replaceText(doc, "<" + keys.get(i).toUpperCase()+">", params.get(keys.get(i))+"");
			        	  //doc = replaceText2(doc, "<" + keys.get(i).toUpperCase()+">", params.get(keys.get(i))+"");
			           }
			        }
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				doc.write(out);
			    
			    out.close();
			    byte[] arr = out.toByteArray();
				repmd = new AMedia(outfl+".doc", "doc", "application/msword", arr);
				//repmd = new AMedia(outfl+".docx", "docx", "application/msword", arr);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//ConnectionPool.close(c);
			}
			
			return repmd;
	}
	public static Map<String, Object> objToMap(Object obj, Map<String, Object> map) throws IntrospectionException {
	    Map<String, Object> result = new HashMap<String, Object>();
	        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
	        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	            Method reader = pd.getReadMethod();
	            if (reader != null)
					try {
						result.put(pd.getName(), reader.invoke(obj));
					} catch (Exception e) {
						
						e.printStackTrace();
					
					}
	        }
	        
	        map.putAll(result);
	        return map;
	  }
	@SuppressWarnings("unused")
	private static HWPFDocument replaceText(final HWPFDocument doc, final String findText, final String replaceText){
        Range r1 = doc.getRange(); 
      System.out.println("replaceText "+findText+"  "+replaceText);
      
    
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



