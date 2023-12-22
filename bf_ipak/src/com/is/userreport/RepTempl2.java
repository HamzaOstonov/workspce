package com.is.userreport;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.zkoss.util.media.AMedia;

public class RepTempl2 {

	public static AMedia getRepmdKoroni(Map<String, Object> params, String templf, String outfl) {

		AMedia repmd = null;

		try {
			
			Map<String, String> param = getParams(params);
			
			FileInputStream fis = new FileInputStream(templf);
			XWPFDocument xdoc = new XWPFDocument(fis);

			for (XWPFTable table : xdoc.getTables()) {
				for (XWPFTableRow row : table.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								if (text != null && text.length() > 0) {
									String msg = param.get(text.trim());
									if (msg != null) {
										text = text.replace(text.trim(), msg);
										r.setText(text, 0);
									}
								}
							}
						}
					}
				}
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			xdoc.write(out);

			out.close();
			byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl + ".docx", "docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document", arr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return repmd;
	}
	
	public static AMedia getRepmdAriza(Map<String, Object> params, String templf, String outfl) {

		AMedia repmd = null;

		try {
			
			Map<String, String> param = getParams(params);
			
			FileInputStream fis = new FileInputStream(templf);
			XWPFDocument xdoc = new XWPFDocument(fis);
            
			for (XWPFTable table : xdoc.getTables()) {
				for (XWPFTableRow row : table.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								// System.out.println("text = " + text);
								if (text != null && text.length() > 0) {
									String msg = param.get(text.trim());
									// System.out.println("msg = " + msg);
									if (msg != null) {
										text = text.replace(text.trim(), msg);
										r.setText(text, 0);
									}
								}
							}
						}
					}
				}
			}
			
			
			for (XWPFParagraph p : xdoc.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					String text = r.getText(0);
					 System.out.println("text = " + text);
					if (text != null && text.length() > 0) {
						String msg = param.get(text.trim());
						 System.out.println("msg = " + msg);
						if (msg != null) {
							text = text.replace(text.trim(), msg);
							r.setText(text, 0);
						}
					}
				}
			}
			
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			xdoc.write(out);

			out.close();
			byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl + ".docx", "docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document", arr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return repmd;
	}
	
	public static AMedia getRepmdAriza2(Map<String, Object> params, String templf, String outfl) {

		AMedia repmd = null;

		try {
			
			Map<String, String> param = getParams(params);
			
			FileInputStream fis = new FileInputStream(templf);
			XWPFDocument xdoc = new XWPFDocument(fis);
            
			for (XWPFParagraph p : xdoc.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					String text = r.getText(0);
					 System.out.println("text = " + text);
					if (text != null && text.length() > 0) {
						String msg = param.get(text.trim());
						 System.out.println("msg = " + msg);
						if (msg != null) {
							text = text.replace(text.trim(), msg);
							r.setText(text, 0);
						}
					}
				}
			}
				
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			xdoc.write(out);

			out.close();
			byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl + ".docx", "docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document", arr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return repmd;
	}

	public static Map<String, String> getParams(Map<String, Object> params) {

		Map<String, String> map = new HashMap<String, String>();

		List<String> keys = new ArrayList<String>();
		for (String key : params.keySet()) {
			keys.add(key);
		}

		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i) != null) {
				map.put("<" + keys.get(i).toUpperCase() + ">", params.get(keys.get(i)) + "");
			}
		}

		return map;
	}

}
