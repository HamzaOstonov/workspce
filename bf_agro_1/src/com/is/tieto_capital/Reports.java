package com.is.tieto_capital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Reports {

	public void getRepotClient(String templatePath, String outFilePath, ReportFields fields) {

		DocxHandler handler = new DocxHandler();
		Map<String, String> replacements = new HashMap<String, String>();
		
		handler.init(templatePath);
		fillReplacements(replacements, fields);
		handler.textReplace(replacements);
//		handler.saveDocument(outFilePath);
		handler.downloadDocument(outFilePath);
		handler.close();
		

		return;
	}
	
	private void fillReplacements(Map<String, String> replacements, ReportFields fields){
		long curTime = System.currentTimeMillis();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date sysdate = new Date(curTime);
		String currency = "Долларах США";
		Connection c = null;
		
		try {
			c = ConnectionPool.getConnection();
			replacements.put("<mfo>", getMfo(c, fields.getMfo()));
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally {
			try {
				c.close();
				c = null;
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		replacements.put("<fio>", fields.getFio());
		replacements.put("<currency>", currency);
		replacements.put("<tietoAccount>", fields.getAccount());
		replacements.put("<date>", df.format(sysdate));
		replacements.put("<address>", fields.getAddress());
		replacements.put("<home_phone>", fields.getHome_phone());
		replacements.put("<email>", fields.getE_mail());
		replacements.put("<mobile_phone>", fields.getMobile_phone());
		replacements.put("<passport_serial>", fields.getPassport_serial());
		replacements.put("<passport_from>", fields.getPassport_from());
		replacements.put("<passport_date>", fields.getPassport_date());
		replacements.put("<customer_birthday>", fields.getCustomer_birthday());
	}

	private String getMfo(Connection c, String branch) {
		String mfo = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = c.prepareStatement("select bank_name from s_mfo where bank_id = ?");
			ps.setString(1, branch);

			rs = ps.executeQuery();

			if (rs.next()) {
				mfo = rs.getString("bank_name");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}

		return mfo;
	}

}