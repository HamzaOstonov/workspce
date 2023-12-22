package com.is.base.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;


/**
 * Created by root on 12.06.2017.
 * 12:20
 */
public class FileUtil {
    private static final Map<String, String> fileExtensionMap;
    private static final Map<String,String> fileMimeTypesMap;

    static {
        fileExtensionMap = new HashMap<String, String>();
        // MS Office
        fileExtensionMap.put("doc", "application/msword");
        fileExtensionMap.put("dot", "application/msword");
        fileExtensionMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        fileExtensionMap.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
        fileExtensionMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
        fileExtensionMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
        fileExtensionMap.put("xls", "application/vnd.ms-excel");
        fileExtensionMap.put("xlt", "application/vnd.ms-excel");
        fileExtensionMap.put("xla", "application/vnd.ms-excel");
        fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
        fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
        fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
        fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
        fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
        fileExtensionMap.put("ppt", "application/vnd.ms-powerpoint");
        fileExtensionMap.put("pot", "application/vnd.ms-powerpoint");
        fileExtensionMap.put("pps", "application/vnd.ms-powerpoint");
        fileExtensionMap.put("ppa", "application/vnd.ms-powerpoint");
        fileExtensionMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        fileExtensionMap.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
        fileExtensionMap.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
        fileExtensionMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
        fileExtensionMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        fileExtensionMap.put("potm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        fileExtensionMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
        // Open Office
        fileExtensionMap.put("odt", "application/vnd.oasis.opendocument.text");
        fileExtensionMap.put("ott", "application/vnd.oasis.opendocument.text-template");
        fileExtensionMap.put("oth", "application/vnd.oasis.opendocument.text-web");
        fileExtensionMap.put("odm", "application/vnd.oasis.opendocument.text-master");
        fileExtensionMap.put("odg", "application/vnd.oasis.opendocument.graphics");
        fileExtensionMap.put("otg", "application/vnd.oasis.opendocument.graphics-template");
        fileExtensionMap.put("odp", "application/vnd.oasis.opendocument.presentation");
        fileExtensionMap.put("otp", "application/vnd.oasis.opendocument.presentation-template");
        fileExtensionMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        fileExtensionMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
        fileExtensionMap.put("odc", "application/vnd.oasis.opendocument.chart");
        fileExtensionMap.put("odf", "application/vnd.oasis.opendocument.formula");
        fileExtensionMap.put("odb", "application/vnd.oasis.opendocument.database");
        fileExtensionMap.put("odi", "application/vnd.oasis.opendocument.image");
        fileExtensionMap.put("oxt", "application/vnd.openofficeorg.extension");
        // Other
        fileExtensionMap.put("txt", "text/plain");
        fileExtensionMap.put("rtf", "application/rtf");
        fileExtensionMap.put("pdf", "application/pdf");

        fileMimeTypesMap = new HashMap<String, String>();
        fileMimeTypesMap.put("application/msword","doc");
        fileMimeTypesMap.put("application/msword","dot");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.template", "dotx");
        fileMimeTypesMap.put("application/vnd.ms-word.document.macroEnabled.12", "docm");
        fileMimeTypesMap.put("application/vnd.ms-word.template.macroEnabled.12", "dotm");
        fileMimeTypesMap.put("application/vnd.ms-excel", "xls");
        fileMimeTypesMap.put("application/vnd.ms-excel", "xlt");
        fileMimeTypesMap.put("application/vnd.ms-excel", "xla");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.template", "xltx");
        fileMimeTypesMap.put("application/vnd.ms-excel.sheet.macroEnabled.12", "xlsm");
        fileMimeTypesMap.put("application/vnd.ms-excel.template.macroEnabled.12", "xltm");
        fileMimeTypesMap.put("application/vnd.ms-excel.addin.macroEnabled.12", "xlam");
        fileMimeTypesMap.put("application/vnd.ms-excel.sheet.binary.macroEnabled.12", "xlsb");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint", "ppt");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint", "pot");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint", "pps");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint", "ppa");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.presentationml.template", "potx");
        fileMimeTypesMap.put("application/vnd.openxmlformats-officedocument.presentationml.slideshow", "ppsx");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint.addin.macroEnabled.12", "ppam");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint.presentation.macroEnabled.12", "pptm");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint.presentation.macroEnabled.12", "potm");
        fileMimeTypesMap.put("application/vnd.ms-powerpoint.slideshow.macroEnabled.12", "ppsm");
        // Open Office
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.text", "odt");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.text-template", "ott");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.text-web", "oth");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.text-master", "odm");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.graphics", "odg");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.graphics-template", "otg");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.presentation", "odp");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.spreadsheet", "ods");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.presentation-template", "otp");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.spreadsheet-template", "ots");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.chart", "odc");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.formula", "odf");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.database", "odb");
        fileMimeTypesMap.put("application/vnd.oasis.opendocument.image", "odi");
        fileMimeTypesMap.put("application/vnd.openofficeorg.extension", "oxt");
        // Other
        fileMimeTypesMap.put("text/plain", "txt");
        fileMimeTypesMap.put("application/rtf", "rtf");
        fileMimeTypesMap.put("application/pdf", "pdf");

        // Images
        fileMimeTypesMap.put("image/jpeg","jpeg");
        fileMimeTypesMap.put("image/bmp","bmp");
    }

    public static String getExtension(String contentType){
        return fileMimeTypesMap.get(contentType);
    }

    public static String getMimeType(String extension){
        return fileExtensionMap.get(extension);
    }

    public static boolean isValidName(String text)
    {
        Pattern pattern = Pattern.compile("^(?!(?:CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(?:\\.[^.]*)?$)[a-zA-Z0-9\\.]+$");
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;
    }
    
//    public static byte[] getBytesFromInputStream(InputStream io) {
//		ByteArrayOutputStream out = null;
//		byte[] bb  = null;
//		try {
//			out = new ByteArrayOutputStream();
//			byte[] buffer = new byte[1024];
//			int b = 0;
//			while ((b = io.read(buffer)) != -1) {
//				out.write(buffer);
//			}
//			bb = out.toByteArray();
//		} catch (Exception e) {
//			
//		} finally {
//			try {
//				io.close();
//				out.close();
//			} catch (Exception e) {}
//		}
//		return bb;
		
		
//	}
    
    public static byte[] encodeBase64(byte[] data) {
    	return Base64.encodeBase64(data);
    }
    
    public static byte[] decodeBase64(byte[] data) {
    	return Base64.encodeBase64(data);
    }
    
    public static void main(String[] args) {
    	
	}
}
