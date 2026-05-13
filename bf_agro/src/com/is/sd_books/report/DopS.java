package com.is.sd_books.report;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class DopS extends PoiReport {
	private final static SimpleDateFormat df = new SimpleDateFormat(
			"dd-MM-yyyy");
	private final static String emptyString = "";

	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia media = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(
					new FileInputStream(templf));
			HWPFDocument doc = new HWPFDocument(fs);
			CallableStatement infoinit = c.prepareCall("{call info.init()}");
			infoinit.executeUpdate();
			CallableStatement cs = c
					.prepareCall(" { call sd_ww_service.all_parameters(?,?,?,?,?,?) } ");
			cs.setInt(1, 12345678);
			cs.setString(2, "ApplSum.doc");
			cs.setString(3, null);
			cs.setString(4, /* params.get("branch").toString() */"00394");
			cs.setInt(5, /* params.get("book_id") */638017);
			cs.setString(6, null);
			cs.execute();
			c.commit();

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SD_TEMPLATES WHERE SES_ID = ? AND BRANCH = ?");
			ps.setObject(1, params.get("book_id"));
			ps.setString(2, params.get("branch").toString());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Range range = doc.getRange();

				String branch = rs.getString("branch");

				String id = rs.getString("id");

				Date open_date = rs.getDate("open_date");

				String name = rs.getString("name");
				replaceText("<NAME>", name, range);

				String client_id = rs.getString("client_id");

				String client_code = rs.getString("client_code");

				String resident_code = rs.getString("resident_code");

				String pass_type = rs.getString("pass_type");

				String pass_ser = rs.getString("pass_ser");
				replaceText("<PASS_SER>", pass_ser, range);

				String pass_num = rs.getString("pass_num");
				replaceText("<PASS_NUM>", pass_num, range);

				String pass_reg = rs.getString("pass_reg");
				replaceText("<PASS_REG>", pass_reg, range);

				Date pass_date = rs.getDate("pass_date");
				replaceText("<PASS_DATE>",
						(pass_date != null) ? df.format(pass_date)
								: emptyString, range);

				Date born_date = rs.getDate("born_date");
				replaceText("<BORN_DATE>",
						(born_date != null) ? df.format(born_date)
								: emptyString, range);

				String address = rs.getString("address");
				replaceText("<ADDRESS>", address, range);

				Date ins_date = rs.getDate("ins_date");

				String emp_code = rs.getString("emp_code");

				String code_citizenship = rs.getString("code_citizenship");

				String birth_place = rs.getString("birth_place");

				String code_adr_region = rs.getString("code_adr_region");

				String code_adr_distr = rs.getString("code_adr_distr");

				String phone_home = rs.getString("phone_home");

				String phone_mobile = rs.getString("phone_mobile");

				String type_document = rs.getString("type_document");

				String name_type_doc = rs.getString("name_type_doc");

				String filial = rs.getString("filial");

				String dep = rs.getString("dep");

				String name_dep = rs.getString("name_dep");

				String num = rs.getString("num");

				String b_id = rs.getString("b_id");

				String type_calc = rs.getString("type_calc");

				Date date_open = rs.getDate("date_open");

				Date date_close = rs.getDate("date_close");

				String saldo = rs.getString("saldo");

				String state = rs.getString("state");

				String account = rs.getString("account");
				replaceText("<ACCOUNT>", account, range);

				String type_calc_show = rs.getString("type_calc_show");

				String regime = rs.getString("regime");

				String dog_num = rs.getString("dog_num");

				Date dog_dat = rs.getDate("dog_dat");

				Date date = rs.getDate("date");

				replaceText("<DATE>", (date != null) ? df.format(date)
						: emptyString, range);

				String dep_type = rs.getString("dep_type");
				replaceText("<DEP_TYPE>", dep_type, range);

				String currency_name = rs.getString("currency_name");
				replaceText("<CURRENCY_NAME>", currency_name, range);

				String clerk_ser = rs.getString("clerk_ser");

				String clerk_num = rs.getString("clerk_num");
				replaceText("<CLERK_NUM>", clerk_num, range);

				Date passport_date_expiration = rs
						.getDate("passport_date_expiration");

				String name_citizenship = rs.getString("name_citizenship");

				String email_address = rs.getString("email_address");

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				doc.write(out);
				media = new AMedia(outfl + ".doc", "doc", "application/msword",
						out.toByteArray());
				out.close();
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return media;
	}

	public static void replaceText(String arg0, String arg1, Range range) {
		if (arg1 != null)
			range.replaceText(arg0, arg1);
		else
			range.replaceText(arg0, "");
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
