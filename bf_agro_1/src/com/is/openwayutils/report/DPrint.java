package com.is.openwayutils.report;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.type.OrientationEnum;

import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;

public class DPrint {
	
	public static AMedia getRepHtml(String name, String source,Map<String, Object> params, String alias ){
		AMedia repmd = null;
		Connection c = null;
        try{
        	c = ConnectionPool.getConnection(alias);
        	final byte[] buf = JasperRunManager.runReportToHtmlFile(source, params, c).getBytes();
        	final InputStream mediais = new ByteArrayInputStream(buf);
		    repmd = new AMedia(name, "html", "text/html", mediais);
             
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return repmd;		
			
	}
	
	public static AMedia getRepPdf(String name, String source,Map<String, Object> params, String alias){
		AMedia repmd = null;
		Connection c = null;
        try{
        	c = ConnectionPool.getConnection(alias);
			final byte[] buf = JasperRunManager.runReportToPdf(source, params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
			repmd = new AMedia(name, "pdf", "application/pdf", mediais);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return repmd;		
	}
	
	
	public static AMedia getRepPdf(String un, String pwd, String name, String source,Map<String, Object> params, String alias){
		AMedia repmd = null;
		Connection c = null;
        try{
        	c = ConnectionPool.getConnection(un, pwd, alias);
        	
        	
        	//PreparedStatement ps = c.prepareStatement("")
        	
        	
        	
			final byte[] buf = JasperRunManager.runReportToPdf(source, params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
			repmd = new AMedia(name, "pdf", "application/pdf", mediais);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return repmd;		
	}



}
