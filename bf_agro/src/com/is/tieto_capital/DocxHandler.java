package com.is.tieto_capital;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.zkoss.zul.Filedownload;

import com.is.ISLogger;
import com.is.utils.CheckNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DocxHandler implements OfficeDocumentsHandler{
	
	private XWPFDocument document;
	private static final Logger logger = new ISLogger().getLogger();

	@Override
	public void init(String path) {
		try {
			InputStream in = new FileInputStream(path);
			try {
				document = new XWPFDocument(in);
			} catch (IOException e) {
				logger.error(CheckNull.getPstr(e));
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					logger.error(CheckNull.getPstr(e));
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(CheckNull.getPstr(e));
		}
	}

	@Override
	public void textReplace(Map<String, String> replaceMap) {
		List<XWPFParagraph> paragraphs = document.getParagraphs();

	    for (XWPFParagraph xwpfParagraph : paragraphs) {
	    	for(Map.Entry<String, String> replace : replaceMap.entrySet()){
	    		if (StringUtils.contains(xwpfParagraph.getText(), replace.getKey())) {
	    	        String replacedText = StringUtils.replace(xwpfParagraph.getText(), replace.getKey(), replace.getValue());


	    	        while(!xwpfParagraph.getRuns().isEmpty()) {
	    	            xwpfParagraph.removeRun(0);
	    	        }

	    	        String[] replacementText = StringUtils.split(replacedText, "\n");

	    	        for (int j = 0; j < replacementText.length; j++) {
	    	            String part = replacementText[j];

	    	            XWPFRun newRun = xwpfParagraph.insertNewRun(j);
	    	            newRun.setText(part);

	    	            if (j+1 < replacementText.length) {
	    	                newRun.addCarriageReturn();
	    	            }
	    	        } 
	    	    }
	    	}
	    }
	}

	@Override
	public void saveDocument(String path) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(path);
			try {
				document.write(out);
			} catch (IOException e) {
				logger.error(CheckNull.getPstr(e));
			}
		} catch (FileNotFoundException e) {
			logger.error(CheckNull.getPstr(e));
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				logger.error(CheckNull.getPstr(e));
			}
		}
	}

	@Override
	public void close() {
		try {
			document.close();
		} catch (IOException e) {
			logger.error(CheckNull.getPstr(e));
		}
	}
	
	@Override
	  public void downloadDocument(String name) {
	    ByteArrayOutputStream os = null;
	    os = new ByteArrayOutputStream();
	      try {
	      document.write(os);
	      Filedownload.save(os.toByteArray(), "application/vnd.openxmlformats-officedocument.wordprocessingml.document", name);
	    } catch (IOException e) {
	      ISLogger.getLogger().error(CheckNull.getPstr(e));
	    } 
	  }

}
