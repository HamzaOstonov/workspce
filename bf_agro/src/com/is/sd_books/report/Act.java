package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.utils.CheckNull;

public class Act extends PoiReport {

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia media = null;
		POIFSFileSystem fs = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(templf));
			HWPFDocument doc = new HWPFDocument(fs);
			
			cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setObject(1, params.get("book_id"));
			cs.setString(2, "ApplSum.doc");
			cs.setString(3, null);
			cs.setString(4,params.get("branch").toString());
			cs.setObject(5,params.get("book_id"));
			cs.setString(6, null);
			cs.execute();
			c.commit();
			
			ps = c.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, params.get("book_id"));
			ps.setString(2, params.get("branch").toString());
			
			rs = ps.executeQuery();
			if (rs.next()){
				
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.write(out);
			media = new AMedia(outfl + ".doc", "doc", "application/msword",
					out.toByteArray());
			out.close();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			try {
				fs.close();
			} catch (IOException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			ConnectionPool.close(rs);
			ConnectionPool.close(ps);
			ConnectionPool.close(cs);
		}
		return null;
	}

	public static void replaceText(String arg0, String arg1, Range range) {
		if (arg1 != null)
			range.replaceText(arg0, arg1);
	}

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
			Filedownload.save(media.getByteData(), "application/msword",
					outFile + ".doc");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

}
