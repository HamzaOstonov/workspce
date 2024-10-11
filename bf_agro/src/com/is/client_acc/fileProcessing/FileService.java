package com.is.client_acc.fileProcessing;

import com.is.ConnectionPool;
import com.is.ISLogger;
//import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import oracle.jdbc.OracleTypes;

public class FileService {

	private static String psql1 = "SELECT t.* FROM(SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (";
	private static String psql2 = " ) s ) t WHERE ROWNUM <= ? order by id desc) t WHERE t.rwnm >= ?";
	private static String msql = "select * from (select * from v_clientacc_files order by id desc)";

	private static String accMSql = "select cp.family||' '||cp.first_name||' '||cp.patronymic fio, a.id bank_account,"
			+ "cp.pinfl, cp.id, a.branch "
			+ "from client_p cp, account a "
			+ "where cp.branch=nvl(?, cp.branch) "
			+ "and a.branch=cp.branch "
			+ "and a.client=cp.id "
			+ "and cp.state=2 "
			+ "and exists "
			+ "(select 1 from client "
			+ "where branch=cp.branch "
			+ "and id_client=cp.id "
			+ "and code_type='08' "
			+ "and code_subject='P') "
			+ "and a.state=2 "
			+ "and a.s_out=0 "
			+ "and a.acc_bal=? "
			+ "and not exists  "
			+ "(select 1 from saldo "
			+ "where branch=a.branch "
			+ "and id=a.id "
			+ "and (dt!=0 or ct!=0) "
			+ "and v_date>=to_date(?,'dd.mm.yyyy')-? "
			+ " ) order by cp.branch, a.id ";
	private static String accPSql2 = ") s) t WHERE ROWNUM <= ? order by branch, bank_account) t WHERE t.rwnm >= ?";

	private static String accCntSql = "select count(*) cnt "
			+ "from client_p cp, account a "
			+ "where cp.branch=nvl(?, cp.branch) " + "and a.branch=cp.branch "
			+ "and a.client=cp.id " + "and cp.state=2 " + "and exists "
			+ "(select 1 from client " + "where branch=cp.branch "
			+ "and id_client=cp.id " + "and code_type='08' "
			+ "and code_subject='P') " + "and a.state=2 " + "and a.s_out=0 "
			+ "and a.acc_bal=? "
			+ "and not exists  " + "(select 1 from saldo "
			+ "where branch=a.branch " + "and id=a.id "
			+ "and (dt!=0 or ct!=0) "
			+ "and v_date>=to_date(?,'dd.mm.yyyy')-? " + " )";
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public static PreparedStatement ps_setparam = null;
	private static List<RefData> listCodeAccBal;

	public FileService() {
	}

	public List<File> getFile() {
		List<File> list = new ArrayList();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from v_clientacc_files");

			while (rs.next()) {
				list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"),
						rs.getInt("file_type_id"), rs.getInt("state_id"), rs
								.getString("file_name"), rs
								.getDate("file_date"), rs
								.getString("file_state_name")));
			}
		} catch (SQLException var8) {
			var8.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return list;
	}

	public static File getFile(long fileId) {
		File file = new File();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM v_clientacc_files WHERE id = ?");
			ps.setLong(1, fileId);

			System.out.println("fileId:" + fileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				file.setId(rs.getLong("id"));
				file.setFr_file_id(rs.getLong("fr_file_id"));
				file.setFile_type_id(rs.getInt("file_type_id"));
				file.setState_id(rs.getInt("state_id"));
				file.setFile_name(rs.getString("file_name"));
				file.setFile_date(rs.getDate("file_date"));
				file.setFile_state_name(rs.getString("file_state_name"));
			}
		} catch (SQLException var10) {
			var10.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return file;
	}

	private static String getCond(List<FilterField> flfields) {
		return flfields.size() > 0 ? " and " : " where ";
	}

	private static List<FilterField> getFilterFields(FileFilter filter) {
		List<FilterField> flfields = new ArrayList();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id = ?", filter
					.getId()));
		}

		if (!CheckNull.isEmpty(filter.getFr_file_id())) {
			flfields.add(new FilterField(getCond(flfields) + "fr_file_id = ?",
					filter.getFr_file_id()));
		}

		if (!CheckNull.isEmpty(filter.getFile_type_id())) {
			flfields.add(new FilterField(
					getCond(flfields) + "file_type_id = ?", filter
							.getFile_type_id()));
		}

		if (!CheckNull.isEmpty(filter.getState_id())) {
			flfields.add(new FilterField(getCond(flfields) + "state_id = ?",
					filter.getState_id()));
		}

		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(getCond(flfields) + "file_name = ?",
					filter.getFile_name()));
		}

		if (!CheckNull.isEmpty(filter.getFile_date())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "trunc(file_date) = ?", df.format(filter.getFile_date())));
		}

		if (!CheckNull.isEmpty(filter.getFile_state_name())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "file_state_name = ?", filter.getFile_state_name()));
		}

		// flfields.add(new FilterField(getCond(flfields) + "rownum < ?",
		// 1001));
		return flfields;
	}

	public static int getCount(FileFilter filter) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM clientacc_files");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(((FilterField) flFields.get(i)).getSqlwhere());
			}
		}

		try {
			c = ConnectionPool.getConnection();
			System.out.println("SQL 77777 " + sql.toString());
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); ++k) {
				ps.setObject((k + 1),
						((FilterField) flFields.get(k)).getColobject());

				System.out.println("index:" + (k + 1) + " value:"
						+ ((FilterField) flFields.get(k)).getColobject());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException var10) {
			var10.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return n;
	}

	public static List<File> getFilesFl(int pageIndex, int pageSize,
			FileFilter filter) {
		List<File> list = new ArrayList();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);

		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(((FilterField) flFields.get(i)).getSqlwhere());
			}
		}

		sql.append(psql2);
		System.out.println(sql.toString());

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			int params;
			for (params = 0; params < flFields.size(); ++params) {
				ps.setObject(params + 1,
						((FilterField) flFields.get(params)).getColobject());
				System.out.println("Param " + params + "   "
						+ flFields.get(params).getColobject());
			}

			++params;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"),
						rs.getInt("file_type_id"), rs.getInt("state_id"), rs
								.getString("file_name"), rs
								.getDate("file_date"), rs
								.getString("file_state_name")));
				System.out.println("Record " + rs.getString("file_name"));
			}
		} catch (SQLException var15) {
			var15.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return list;
	}

	public static void updateState_id(long id, int state_id) {
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE clientacc_files SET state_id = ?, WHERE id = ?");
			ps.setInt(1, state_id);
			ps.setLong(2, id);
			ps.executeUpdate();
			c.commit();
		} catch (SQLException var8) {
			var8.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

	}

	public static String getRecordStateName(String id) {
		String recordStateName = "";
		Connection c = null;

		try {

			System.out.println("Stat id: fileService line 220:" + id);
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT name FROM closeacc_file_RECORDS_STATES WHERE id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				recordStateName = rs.getString("name");
			}

		} catch (SQLException var8) {
			var8.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return recordStateName;
	}

	//
	// public static String getRecordErrorText(long id,long fileType) {
	// String recordErrorText = "Успешно!";
	// Connection c = null;
	//
	// try {
	// if(fileType==1L){
	//
	// c = ConnectionPool.getConnection();
	// PreparedStatement ps =
	// c.prepareStatement("SELECT text FROM empc_b_records_prc_errors WHERE record_id = ?");
	// ps.setLong(1, id);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// recordErrorText = rs.getString("text");
	// }
	//
	// }
	//
	// else{
	//
	//
	// c = ConnectionPool.getConnection();
	// PreparedStatement ps =
	// c.prepareStatement("SELECT text FROM empc_expt_records_prc_errors WHERE record_id = ?");
	// ps.setLong(1, id);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// recordErrorText = rs.getString("text");
	// }
	//
	// }
	//
	// } catch (SQLException var9) {
	// var9.printStackTrace();
	// } finally {
	// ConnectionPool.close(c);
	// }
	//
	// return recordErrorText;
	// }

	public static EmpcExptRecord getBRecord(long recordId) {
		EmpcExptRecord empcExptRecord = new EmpcExptRecord();
		Connection c = null;

		try {

			System.out.println("getBRecord fileService line 284");
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
			ps.setLong(1, recordId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				if (!rs.getString("tran_type").equals("110")) {

					empcExptRecord = new EmpcExptRecord();
					empcExptRecord.setId(rs.getLong("id"));
					empcExptRecord.setEmpc_file_id(rs.getLong("empc_file_id"));
					empcExptRecord.setState_id(rs.getDouble("state_id"));
					empcExptRecord.setRecord_type(rs.getString("record_type"));
					empcExptRecord.setLine_number(rs.getDouble("line_number"));
					empcExptRecord.setClient(rs.getString("client"));
					empcExptRecord.setCard_acct(rs.getString("card_acct"));
					empcExptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
					empcExptRecord.setCard(rs.getString("card"));
					empcExptRecord.setSlip_nr(rs.getString("slip_nr"));
					empcExptRecord.setRef_number(rs.getString("ref_number"));
					empcExptRecord.setTran_date_time(rs
							.getDate("tran_date_time"));
					empcExptRecord.setRec_date(rs.getDate("rec_date"));
					empcExptRecord.setPost_date(rs.getDate("post_date"));
					empcExptRecord.setDeal_desc(rs.getString("deal_desc"));
					empcExptRecord.setTran_type(rs.getString("tran_type"));
					empcExptRecord.setDeb_cred(rs.getString("deb_cred"));
					empcExptRecord.setTran_ccy(rs.getString("tran_ccy"));
					empcExptRecord.setTran_amt(rs.getLong("tran_amt"));
					empcExptRecord.setAccnt_amt(rs.getLong("accnt_amt"));
					empcExptRecord.setTerminal(rs.getString("terminal"));
					empcExptRecord.setMcc_code(rs.getString("mcc_code"));
					empcExptRecord.setMerchant(rs.getString("merchant"));
					empcExptRecord.setAbvr_name(rs.getString("abvr_name"));
					empcExptRecord.setCountry(rs.getString("country"));
					empcExptRecord.setCity(rs.getString("city"));
					empcExptRecord.setProc_id(rs.getLong("proc_id"));
					empcExptRecord.setInternal_no(rs.getLong("internal_no"));
					empcExptRecord.setProduct(rs.getString("product"));
					empcExptRecord.setIss_mfo(rs.getString("iss_mfo"));
					empcExptRecord.setTerm_id(rs.getString("term_id"));
					empcExptRecord.setTranz_acct(rs.getString("tranz_acct"));

					System.out
							.println("tran_type:" + rs.getString("tran_type"));

				}
			}
		} catch (Exception var9) {
			var9.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return empcExptRecord;
	}

	public static EmpcExptRecord getEmpcExptRecord(long recordId) {
		EmpcExptRecord empcExptRecord = new EmpcExptRecord();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM visa_onus_records WHERE id = ? ");
			ps.setLong(1, recordId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				if (!rs.getString("tran_type").equals("110")) {

					empcExptRecord = new EmpcExptRecord();
					empcExptRecord.setId(rs.getLong("id"));
					empcExptRecord.setEmpc_file_id(rs.getLong("empc_file_id"));
					empcExptRecord.setState_id(rs.getDouble("state_id"));
					empcExptRecord.setRecord_type(rs.getString("record_type"));
					empcExptRecord.setLine_number(rs.getDouble("line_number"));
					empcExptRecord.setClient(rs.getString("client"));
					empcExptRecord.setCard_acct(rs.getString("card_acct"));
					empcExptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
					empcExptRecord.setCard(rs.getString("card"));
					empcExptRecord.setSlip_nr(rs.getString("slip_nr"));
					empcExptRecord.setRef_number(rs.getString("ref_number"));
					empcExptRecord.setTran_date_time(rs
							.getDate("tran_date_time"));
					empcExptRecord.setRec_date(rs.getDate("rec_date"));
					empcExptRecord.setPost_date(rs.getDate("post_date"));
					empcExptRecord.setDeal_desc(rs.getString("deal_desc"));
					empcExptRecord.setTran_type(rs.getString("tran_type"));
					empcExptRecord.setDeb_cred(rs.getString("deb_cred"));
					empcExptRecord.setTran_ccy(rs.getString("tran_ccy"));
					empcExptRecord.setTran_amt(rs.getLong("tran_amt"));
					empcExptRecord.setAccnt_amt(rs.getLong("accnt_amt"));
					empcExptRecord.setTerminal(rs.getString("terminal"));
					empcExptRecord.setMcc_code(rs.getString("mcc_code"));
					empcExptRecord.setMerchant(rs.getString("merchant"));
					empcExptRecord.setAbvr_name(rs.getString("abvr_name"));
					empcExptRecord.setCountry(rs.getString("country"));
					empcExptRecord.setCity(rs.getString("city"));
					empcExptRecord.setProc_id(rs.getLong("proc_id"));
					empcExptRecord.setInternal_no(rs.getLong("internal_no"));
					empcExptRecord.setProduct(rs.getString("product"));
					empcExptRecord.setIss_mfo(rs.getString("iss_mfo"));
					empcExptRecord.setTerm_id(rs.getString("term_id"));
					empcExptRecord.setTranz_acct(rs.getString("tranz_acct"));

				}
			}
		} catch (Exception var9) {
			var9.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return empcExptRecord;
	}

	public static Long getFileType(long empc_file_id) {
		Long fileTypeId = 0L;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();

			PreparedStatement ps = c
					.prepareStatement("SELECT file_type_id FROM v_clientacc_files r WHERE r.id = ?");
			ps.setLong(1, empc_file_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				fileTypeId = rs.getLong("file_type_id");
			}
		} catch (Exception var9) {
			var9.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		System.out.println("getFileType a " + fileTypeId);

		return fileTypeId;
	}

	// public static List<EmpcExptRecord> getFileRecords(long empc_file_id) {
	// List<EmpcExptRecord> list = new ArrayList();
	// Connection c = null;
	//
	// try {
	// c = ConnectionPool.getConnection();
	// PreparedStatement ps =
	// c.prepareStatement("SELECT r.id, r.state_id, r.slip_nr, r.tran_amt, r.mcc_code, r.merchant, r.term_id FROM empc_expt_records r WHERE empc_file_id = ?");
	// ps.setLong(1, empc_file_id);
	// ResultSet rs = ps.executeQuery();
	//
	// while(rs.next()) {
	// list.add(new EmpcExptRecord(rs.getLong("id"), rs.getDouble("state_id"),
	// rs.getString("slip_nr"), rs.getLong("tran_amt"),
	// rs.getString("mcc_code"), rs.getString("merchant"),
	// rs.getString("term_id")));
	// }
	// } catch (Exception var9) {
	// var9.printStackTrace();
	// } finally {
	// ConnectionPool.close(c);
	// }
	//
	// return list;
	// }

	public static List<HumoFileRecords> getFileRecords(long empc_file_id,
			long fileType) {

		List<HumoFileRecords> list = new ArrayList();
		Connection c = null;

		try {

			if (fileType == 1L) { // closeacc
				c = ConnectionPool.getConnection();

				PreparedStatement ps = c
						.prepareStatement("select r.id,r.state_id,r.line_number,r.agreement_numver,r.dbo_closed_dt,r.doc_fio,"
								+ "r.bank_account,r.pinfl,r.nciid,r.branch,r.clientacc_file_id,"
								+ "(select e.text from closeacc_file_RECORDS_ERRORS e where r.id = e.record_id) errText,"
								+ "s.name from closeacc_file_RECORDS r,closeacc_file_records_states s where r.clientacc_FILE_ID = ? and r.state_id=s.id "
								+ "order by state_id desc");
				ps.setLong(1, empc_file_id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HumoFileRecords tmp = new HumoFileRecords();
					tmp.setId(rs.getLong("id"));
					tmp.setState_id(rs.getInt("state_id"));
					tmp.setLine_number(rs.getLong("line_number"));
					tmp.setState(rs.getString("name"));
					tmp.setAgreement_numver(rs.getString("agreement_numver"));
					tmp.setDbo_closed_dt(rs.getString("dbo_closed_dt"));
					tmp.setDoc_fio(rs.getString("doc_fio"));
					tmp.setBank_account(rs.getString("bank_account"));
					tmp.setPinfl(rs.getString("pinfl"));
					tmp.setNciid(rs.getString("nciid"));
					tmp.setBranch(rs.getString("branch"));
					tmp.setErrorText(rs.getString("errtext"));
					tmp.setClientacc_file_id(rs.getLong("clientacc_file_id"));
					list.add(tmp);
				}
			} else if (fileType == 3L) { // iias
				c = ConnectionPool.getConnection();
				PreparedStatement ps = c
						.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,r.tran_amt,r.tran_date_time,r.tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT, (select e.text from visa_onus_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_onus_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
				ps.setLong(1, empc_file_id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new HumoFileRecords(rs.getLong("id"), rs
							.getString("name"), rs.getString("merchant"), rs
							.getString("abvr_name"), rs.getString("card"), rs
							.getString("tranz_acct"), rs.getString("term_id"),
							rs.getString("mcc_code"),
							rs.getString("tran_type"), rs
									.getString("accnt_amt"), rs
									.getString("tran_date_time"), rs
									.getString("errText"), rs
									.getString("tran_amt2"), rs
									.getString("tran_amt")));
				}
			} else if (fileType == 2L) { // expt-agro
				c = ConnectionPool.getConnection();
				PreparedStatement ps = c
						.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,r.tran_amt,r.tran_date_time,r.tran_amt2,r.client,r.card_acct,r.accnt_ccy,r.slip_nr,r.ref_number,r.rec_date,r.post_date,r.deal_desc,r.deb_cred,r.tran_ccy,r.tran_amt,r.accnt_amt,r.terminal,r.mcc_code,r.country,r.city,r.proc_id,r.internal_no,r.product,r.iss_mfo,r.term_id,r.tran_type2,r.tr_code,r.tr_fee,r.fee,r.surcharge,r.card_cond_set,r.amount, (select e.text from visa_expt_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_expt_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
				ps.setLong(1, empc_file_id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HumoFileRecords tmp = new HumoFileRecords();
					tmp.setId(rs.getLong("id"));
					tmp.setState(rs.getString("name"));
					tmp.setMerchant(rs.getString("merchant"));
					tmp.setAbvr_name(rs.getString("abvr_name"));
					tmp.setCard(rs.getString("card"));
					tmp.setTranz_acct(rs.getString("tranz_acct"));
					tmp.setTermId(rs.getString("term_id"));
					tmp.setMcc(rs.getString("mcc_code"));
					tmp.setTran_type(rs.getString("tran_type"));
					tmp.setSumma(rs.getString("tran_amt"));
					tmp.setTran_date_time(rs.getString("tran_date_time"));
					tmp.setErrorText(rs.getString("errText"));
					tmp.setTran_amt2(rs.getString("tran_amt2"));

					tmp.setClient(rs.getString("client"));
					tmp.setCard_acct(rs.getString("card_acct"));
					tmp.setAccnt_ccy(rs.getString("accnt_ccy"));
					tmp.setSlip_nr(rs.getString("slip_nr"));
					tmp.setRef_number(rs.getString("ref_number"));
					tmp.setRec_date(rs.getString("rec_date"));
					tmp.setPost_date(rs.getString("post_date"));
					tmp.setDeal_desc(rs.getString("deal_desc"));
					tmp.setDeb_cred(rs.getString("deb_cred"));
					tmp.setTran_ccy(rs.getString("tran_ccy"));
					tmp.setTran_amt(rs.getString("tran_amt"));
					tmp.setAccnt_amt(rs.getString("accnt_amt"));
					tmp.setTerminal(rs.getString("terminal"));
					tmp.setMcc_code(rs.getString("mcc_code"));
					tmp.setCountry(rs.getString("country"));
					tmp.setCity(rs.getString("city"));
					tmp.setProc_id(rs.getString("proc_id"));
					tmp.setInternal_no(rs.getString("internal_no"));
					tmp.setProduct(rs.getString("product"));
					tmp.setIss_mfo(rs.getString("iss_mfo"));
					tmp.setTerm_id(rs.getString("term_id"));
					tmp.setTran_type2(rs.getString("tran_type2"));
					tmp.setTr_code(rs.getString("tr_code"));
					tmp.setTr_fee(rs.getString("tr_fee"));
					tmp.setFee(rs.getString("fee"));
					tmp.setSurcharge(rs.getString("surcharge"));
					tmp.setCard_cond_set(rs.getString("card_cond_set"));
					tmp.setAmount(rs.getString("amount"));

					list.add(tmp);
				}
			} else if (fileType == 4L) { // trbeq-ipak

				c = ConnectionPool.getConnection();
				PreparedStatement ps = c
						.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,r.tran_amt,r.tran_date_time,r.tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT,(select e.text from visa_trbeq_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_trbeq_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
				ps.setLong(1, empc_file_id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					list.add(new HumoFileRecords(rs.getLong("id"), rs
							.getString("name"), rs.getString("merchant"), rs
							.getString("abvr_name"), rs.getString("card"), rs
							.getString("tranz_acct"), rs.getString("term_id"),
							rs.getString("mcc_code"),
							rs.getString("tran_type"), rs
									.getString("accnt_amt"), rs
									.getString("tran_date_time"), rs
									.getString("errText"), rs
									.getString("tran_amt2"), rs
									.getString("tran_amt")));
				}
			} else {

				c = ConnectionPool.getConnection();
				PreparedStatement ps = c
						.prepareStatement("SELECT r.id, r.state_id, r.mcc_code, r.merchant,r.abvr_name,r.card,r.tranz_acct, r.term_id,r.tran_type,r.tran_amt,r.tran_date_time,(select e.text from EMPC_EXPT_RECORDS_PRC_ERRORS e where r.id = e.record_id) errText,s.name,r.tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT FROM empc_expt_records r,EMPC_FILE_STATE s WHERE r.empc_file_id = ? and r.state_id=s.id order by state_id desc");
				ps.setLong(1, empc_file_id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					if (!rs.getString("tran_type").equals("110")) {
						list.add(new HumoFileRecords(rs.getLong("id"), rs
								.getString("name"), rs.getString("merchant"),
								rs.getString("abvr_name"),
								rs.getString("card"), rs
										.getString("tranz_acct"), rs
										.getString("term_id"), rs
										.getString("mcc_code"), rs
										.getString("tran_type"), rs
										.getString("accnt_amt"), rs
										.getString("tran_date_time"), rs
										.getString("errText"), rs
										.getString("tran_amt2"), rs
										.getString("tran_amt")));

					}
				}

			}
		} catch (Exception var9) {
			var9.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		System.out.println("list.toString()=" + list.toString());

		return list;
	}

	public static String customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}

	public static String getTietoDirectory(long id) {
		String tietoDir = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT dir FROM fr_files_directory WHERE id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				tietoDir = rs.getString("dir");
			}
		} catch (SQLException var10) {
			var10.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return tietoDir;
	}

	public static Long getFileState(long fileId) {
		long state = 1L;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT f.state_id FROM clientacc_files f WHERE f.id = ?");
			ps.setLong(1, fileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				state = rs.getLong("state_id");
			}
		} catch (SQLException var11) {
			var11.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return state;
	}

	public static Long getRecordState(long recordId) {
		long state = 1L;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT r.state_id FROM closeacc_file_RECORDS r WHERE r.id = ?");
			ps.setLong(1, recordId);
			rs = ps.executeQuery();
			if (rs.next()) {
				state = rs.getLong("state_id");
			}
		} catch (SQLException var11) {
			var11.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return state;
	}

	public static VisaOnusRecord getEXPT_record(long recordId) {
		VisaOnusRecord exptRecord = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
			ps.setLong(1, recordId);
			rs = ps.executeQuery();
			if (rs.next()) {
				exptRecord = new VisaOnusRecord();
				exptRecord.setId(rs.getLong("id"));
				exptRecord.setEmpc_file_id(rs.getLong("empc_file_id"));
				exptRecord.setState_id(rs.getInt("state_id"));
				exptRecord.setRecord_type(rs.getString("record_type"));
				exptRecord.setLine_number(rs.getLong("line_number"));
				exptRecord.setClient(rs.getString("client"));
				exptRecord.setCard_acct(rs.getString("card_acct"));
				exptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
				exptRecord.setCard(rs.getString("card"));
				exptRecord.setSlip_nr(rs.getString("slip_nr"));
				exptRecord.setRef_number(rs.getString("ref_number"));
				exptRecord.setTran_date_time(rs.getDate("tran_date_time"));
				exptRecord.setRec_date(rs.getDate("rec_date"));
				exptRecord.setPost_date(rs.getDate("post_date"));
				exptRecord.setDeal_desc(rs.getString("deal_desc"));
				exptRecord.setTran_type(rs.getString("tran_type"));
				exptRecord.setDeb_cred(rs.getString("deb_cred"));
				exptRecord.setTran_ccy(rs.getString("tran_ccy"));
				exptRecord.setTran_amt(rs.getLong("tran_amt"));
				exptRecord.setAccnt_amt(rs.getLong("accnt_amt"));
				exptRecord.setTerminal(rs.getString("terminal"));
				exptRecord.setMcc_code(rs.getString("mcc_code"));
				exptRecord.setMerchant(rs.getString("merchant"));
				exptRecord.setAbvr_name(rs.getString("abvr_name"));
				exptRecord.setCountry(rs.getString("country"));
				exptRecord.setCity(rs.getString("city"));
				exptRecord.setProc_id(rs.getLong("proc_id"));
				exptRecord.setInternal_no(rs.getLong("internal_no"));
				exptRecord.setProduct(rs.getLong("product"));
				exptRecord.setIss_mfo(rs.getString("iss_mfo"));
				exptRecord.setTerm_id(rs.getString("term_id"));
				exptRecord.setTranz_acct(rs.getString("tranz_acct"));
			}
		} catch (Exception var10) {
			var10.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return exptRecord;
	}

	public static void setParam(Connection cMFO, String name, String value)
			throws SQLException {

		ps_setparam = cMFO.prepareStatement("{call param.setParam(?, ?)}");

		ps_setparam.setString(1, name);
		ps_setparam.setString(2, value);
		ps_setparam.execute();

	}

	// public static String getParam(Connection cMFO,String value) throws
	// SQLException{
	//
	//
	// CallableStatement ps_getparam = null;
	// String id=null;
	// ps_getparam =
	// cMFO.prepareCall("{? = call Param.getparam('"+value+"') }");
	// ps_getparam.registerOutParameter(1, java.sql.Types.VARCHAR);
	//
	// id=ps_getparam.getString(1);
	// ISLogger.getLogger().info(value+":"+id);
	// System.out.println(value+":"+id);
	//
	// return id;
	// }

	public static String getAmountAgro(Long fileId, String un, String pwd,
			String alias, String fileName) throws SQLException {

		Long amount = null;
		String error = "Клиринг завершился";
		Connection c = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {

			String clirInst = ConnectionPool.getValue("clir_inst");
			//
			if (clirInst.equals("clir")) {

				// c = ConnectionPool.getConnection_cliring();
				// c=getTimerConnection();

				Class.forName("oracle.jdbc.driver.OracleDriver");
				c = DriverManager
						.getConnection(
								"jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 172.16.10.5)(PORT = 1521)))(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = PHBY1)))",
								"munis", "qazwsx123");

			} else if (clirInst.equals("bank")) {
				c = ConnectionPool.getConnection(un, pwd, alias);
			}

			else {
				ISLogger.getLogger().error("clir_inst не найден");
			}

			if (UpdateFileCliren(fileId.toString(), alias) != 0) {
				//

				System.out.println(UpdateFileCliren(fileId.toString(), alias));
				ISLogger.getLogger().error(
						"UpdateFileCliren:"
								+ UpdateFileCliren(fileId.toString(), alias));

				setParam(c, "PARENT_GROUP_ID", "198");
				setParam(c, "GROUP_ID", "198");
				setParam(c, "PARENT_DEAL_ID", "106");
				setParam(c, "ACTION_ID", "0");
				setParam(c, "S_DEAL_ID", "106");
				setParam(c, "FILE_NAME", fileName);

				cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
				System.out.println("call HUMO_CLEARING");
				ISLogger.getLogger().error("call HUMO_CLEARING");
				cs.setLong(1, fileId);
				cs.execute();

			} else {
				error = "Номер файла не поменялься";
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			error = CheckNull.getPstr(e);
		} finally {
			if (c != null) {
				c.commit();
				// resetTimerConnection();
				ConnectionPool.close(c);
			}
		}
		return error;
	}

	public static String getAmount(Long fileId, String un, String pwd,
			String alias, String fileName) throws SQLException {

		Long amount = null;
		String error = "Клиринг завершился";
		Connection c = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {

			String clirInst = ConnectionPool.getValue("clir_inst");
			//
			if (clirInst.equals("clir")) {

				c = ConnectionPool.getConnection_cliring();

			} else if (clirInst.equals("bank")) {
				c = ConnectionPool.getConnection(un, pwd, alias);
			}

			else {
				ISLogger.getLogger().error("clir_inst не найден");
			}

			setParam(c, "PARENT_GROUP_ID", "198");
			setParam(c, "GROUP_ID", "198");
			setParam(c, "PARENT_DEAL_ID", "106");
			setParam(c, "ACTION_ID", "0");
			setParam(c, "S_DEAL_ID", "106");
			setParam(c, "FILE_NAME", fileName);

			cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
			System.out.println("call HUMO_CLEARING");
			ISLogger.getLogger().error("call HUMO_CLEARING");
			cs.setLong(1, fileId);
			cs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			error = CheckNull.getPstr(e);
		} finally {
			if (c != null) {
				c.commit();

				// resetTimerConnection();
				ConnectionPool.close(c);
			}
		}
		return error;
	}

	//

	public static String getCountSuccessEXPT(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=2");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountSuccessB(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=2");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountCommonB(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=?");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountCommonEXPT(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.tran_type<>'110'");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountErrorEXPT(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=3");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountErrorB(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=3");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getProcessInfo(Connection c, String empcFileId,
			String fileTypeId) {

		String processInfo = null;

		try {

			if (fileTypeId.equals("1")) {
				processInfo = "Общий: " + getCountCommonB(c, empcFileId)
						+ " обработана: "
						+ getCountSuccessB(c, empcFileId) + " ошибка: "
						+ getCountErrorB(c, empcFileId);
			} else {
				processInfo = "Общий: "
						+ getCountCommonEXPT(c, empcFileId)
						+ " обработана: "
						+ getCountSuccessEXPT(c, empcFileId)
						+ " ошибка: " + getCountErrorEXPT(c, empcFileId);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return processInfo;
	}

	// public static String Processing(Connection c, String fileId, String
	// fileType){
	// String err=null;
	// try{
	//
	// setParam(c, "FILE_ID", fileId);
	// setParam(c, "FILE_TYPE", fileType);
	// PROCESSING_FILE(c);
	//
	// }catch(Exception e)
	// {
	// try {c.rollback();} catch(SQLException
	// exeption){ISLogger.getLogger().error(e);}
	// ISLogger.getLogger().error("Processing HUMO file error: " +
	// CheckNull.getPstr(e));
	// err=CheckNull.getPstr(e);
	// }
	// finally
	// {
	//
	// if(c != null) try {c.close();} catch (SQLException e)
	// {ISLogger.getLogger().error(e);}
	// }
	//
	// return err;
	//
	// }

	public static String HUMO_PROCESSING(Connection c, String fileId)
			throws SQLException {
		String error = null;
		try {

			System.out.println("CreateJob start FilesSERVICE LINE 908 ");
			CallableStatement cs = null;

			// ISLogger.getLogger().error("HUMO: call info.init()");
			// cs = c.prepareCall("{ call info.init() }");
			cs = c.prepareCall("{call proc_clientacc_processing.SepCrDoc(?)}");
			// cs = c.prepareCall("{call HUMO_PROCESSING.CreateJob(?)}");
			cs.setString(1, fileId);
			cs.execute();
			System.out.println("CreateJob end FilesSERVICE LINE 913 ");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			error = CheckNull.getPstr(e);
		}
		return error;
	}

	public static String clientacc_prepare_file(Connection c, String fileId)
			throws SQLException {
		String error = null;
		try {

			CallableStatement cs = null;

			// ISLogger.getLogger().error("HUMO: call info.init()");
			// cs = c.prepareCall("{ call info.init() }");
			cs = c.prepareCall("{call proc_clientacc_processing.start_output_file(?)}");
			// cs = c.prepareCall("{call HUMO_PROCESSING.CreateJob(?)}");
			cs.setString(1, fileId);
			cs.execute();
			System.out.println("start_output_file end ");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			error = CheckNull.getPstr(e);
		}
		return error;
	}

	public static String getCountBState1(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=1");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static String getCountEXPTStat1(Connection c, String empcFileId) {
		String count = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=1 and t.tran_type<>'110'");
			ps.setString(1, empcFileId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getString("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public static boolean isProcessingFile(Connection c, String empcFileId) {
		boolean process = false;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select * from user_scheduler_jobs where job_name = ?");
			ps.setString(1, "HUMO_JOB" + empcFileId);
			System.out.println("HUMO_JOB" + empcFileId);
			rs = ps.executeQuery();

			while (rs.next()) {
				process = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return process;
	}

	public static List<RefData> getState(String branch) {
		return getRefData(
				"select id data, name label from clientacc_FILE_STATE order by id",
				branch);
	}

	public static List<RefData> getRefData(String sql, String branch) {
		List list = new LinkedList();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static int UpdateFileCliren(final String file, String alias) {

		PreparedStatement ps = null;
		Connection c = null;
		int updeteResult = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bank9004.sim_clir_humo set file_id=?");
			ps.setString(1, file);
			updeteResult = ps.executeUpdate();
			ISLogger.getLogger().error(
					"UpdateFileCliren: " + file + "updated:" + updeteResult);
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			updeteResult = 0;
			return updeteResult;
		} finally {
			// Utils.close(ps);
			ConnectionPool.close(c);
		}
		return updeteResult;
	}

	// public static Connection getTimerConnection() throws SQLException {
	// try {
	// Context context = new InitialContext();
	// Context envContext = (Context) context.lookup("java:/comp/env");
	// DataSource datasource = (DataSource) envContext.lookup("jdbc/clearing");
	// if (datasource == null) {
	// throw new Exception("No DataSource");
	// }
	//
	// PoolConfiguration p = datasource.getPoolProperties();
	// p.setRemoveAbandonedTimeout(1800);
	// datasource.setPoolProperties(p);
	// Connection c = datasource.getConnection();
	//
	// return c;
	// } catch (Exception e) {
	// ISLogger.getLogger().error(e.getStackTrace());
	// throw new RuntimeException("Database Not Available.");
	// }
	// }
	//
	// public static void resetTimerConnection() throws SQLException {
	// try {
	// Context context = new InitialContext();
	// Context envContext = (Context) context.lookup("java:/comp/env");
	// DataSource datasource = (DataSource) envContext.lookup("jdbc/clearing");
	// if (datasource == null) {
	// throw new Exception("No DataSource");
	// }
	//
	// PoolConfiguration p = datasource.getPoolProperties();
	// p.setRemoveAbandonedTimeout(60);
	// datasource.setPoolProperties(p);
	//
	// } catch (Exception e) {
	// ISLogger.getLogger().error(e.getStackTrace());
	// throw new RuntimeException("Database Not Available.");
	// }
	// }

	public static List<AccInfo> getAccList(int pageIndex, int pageSize,
			String pBranch, String pCodeBal, int pDays, String pCurrDate, String alias) {

		final List<AccInfo> list = new ArrayList<AccInfo>();
		Connection c = null;
		int v_lowerbound = pageIndex * pageSize + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;

		// List<FilterField> flFields = getFilterFields(filter);
		final StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(accMSql);
		/*
		 * if (flFields.size() > 0) { for (int i = 0; i < flFields.size(); ++i)
		 * { sql.append(((FilterField) flFields.get(i)).getSqlwhere()); } }
		 */
		sql.append(accPSql2);
		System.out.println(sql.toString());

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			ps.setString(1, pBranch);
			ps.setString(2, pCodeBal);
			ps.setString(3, pCurrDate);
			ps.setInt(4, pDays);
			// int params=0;
			/*
			 * for (params = 0; params < flFields.size(); ++params) {
			 * ps.setObject(params + 1, ((FilterField)
			 * flFields.get(params)).getColobject());
			 * System.out.println("Param " + params + "   " +
			 * flFields.get(params).getColobject()); }
			 */
			// ++params;
			ps.setInt(5, v_upperbound);
			ps.setInt(6, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				final AccInfo acc = new AccInfo();
				acc.setBranch(rs.getString("branch"));
				acc.setBank_account(rs.getString("bank_account"));
				acc.setFio(rs.getString("fio"));
				acc.setPinfl(rs.getString("pinfl"));

				list.add(acc);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error(
					"getAccList SQLException =>" + e.getMessage());
			return list;
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		close(rs);
		close(ps);
		ConnectionPool.close(c);
		return list;
	}

	public static int getAccCount(String pBranch, String pCodeBal, int pDays, String pCurrDate,
			String alias) {
		Connection c = null;
		int n = 0;
		// List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(accCntSql);
		// if (flFields.size() > 0) {
		// for (int i = 0; i < flFields.size(); ++i) {
		// sql.append(((FilterField) flFields.get(i)).getSqlwhere());
		// }
		// }
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			System.out.println("SQL accCntSql " + sql.toString());
			ps = c.prepareStatement(sql.toString());
			ps.setString(1, pBranch);
			ps.setString(2, pCodeBal);
			ps.setString(3, pCurrDate);
			ps.setInt(4, pDays);
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException var10) {
			var10.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		return n;
	}

	public static Date getInfoDate(final String alias) {
		Date date = new Date();
		Connection c = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			if (alias != null && !alias.equals("")) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection();
			}
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if (rs.next()) {
				date = rs.getDate(1);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return date;
		} finally {
			close(rs);
			close(st);
			close(cs);
			ConnectionPool.close(c);
		}
		close(rs);
		close(st);
		close(cs);
		ConnectionPool.close(c);
		return date;
	}

	public static Res InsertFilterResults(ClientaccFilter filter, String alias) {
		Res res = new Res();
		res.setCode(1);
		String blockSql="declare "+ 
    "vfilter_id number;  "+
    "vEmpcFileId number; "+
    "line_number number:=0; "+
    "vBranch varchar2(5):=?; "+ 
	"vAccBal varchar2(5):=?; "+
	"vCurDate varchar2(10):=?; "+
	"vDays  number:=?; "+
    "vEmpId number:=?; "+  
    "vNumDoc varchar2(20):=?; "+
    "vDateDoc varchar2(10):=?; "+   
    "vPurpose varchar2(80):=?; "+
"begin  "+
"  select SEQ_clientacc_FILter.nextval, seq_clientacc_files.nextval into vfilter_id, vEmpcFileId from dual; "+

"  insert into clientacc_FILter(id,branch,BAL_CODE,DAYS,EMP_ID,NUM_DOC,bank_date,DATE_DOC,purpose)  "+
" values (vfilter_id, vBranch, vAccBal, vDays, vEmpId, vNumDoc, to_date(vCurDate,'dd.mm.yyyy'), to_date(vDateDoc,'dd.mm.yyyy'),vPurpose); "+

"  insert into clientacc_files(id,file_type_id,state_id,filter_id) "+
  "values (vEmpcFileId, 1, 1, vfilter_id);  "+
 
  "insert into closeacc_file_records (  "+
  "        id, clientacc_file_id,  "+
  "        state_id, line_number, "+
  "        branch, dbo_closed_dt, "+ 
  "        doc_fio, bank_account, "+
  "        pinfl,nciid  "+
  "        )  "+
  "select seq_closeacc_file_records.nextval, vEmpcFileId, 1, rownum, a.branch, null, null, a.id, "+
  "    null, a.client   "+
  "    from account a  "+
  "    where a.branch=nvl(vBranch, a.branch) "+ 
  "    and a.acc_bal=vAccBal  "+
  "    and a.state=2  "+
  "    and a.s_out=0  "+
  "    and exists  "+
  "    (select 1 from client "+ 
  "   where branch=a.branch  "+
  "    and id_client=a.client  "+
  "    and code_type='08'  "+
  "    and code_subject='P')  "+
  "    and not exists   "+
  "    (select 1 from saldo "+ 
  "    where branch=a.branch  "+
  "    and id=a.id  "+
  "    and (dt!=0 or ct!=0) "+ 
  "    and v_date>=to_date(vCurDate,'dd.mm.yyyy')-vDays "+ 
  "     );  "+
  "  ?:=sql%rowcount; "+  
  "?:=vEmpcFileId; "+
  "end;";
				
		Connection c = null;
		CallableStatement cs = null;
		//ResultSet rs = null;
		//Statement st = null;
		try {
			if (alias != null && !alias.equals("")) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection();
			}
			cs = c.prepareCall(blockSql);
			cs.setString (1, filter.getBranch());
			cs.setString (2, filter.getBal_code());
			cs.setString (3, df.format(filter.getBank_date()) );
			cs.setInt (4, filter.getDays());
			cs.setString(5, filter.getEmp_id());
			cs.setString(6, filter.getNum_doc());
			cs.setString (7, df.format(filter.getDate_doc()) );
			cs.setString(8, filter.getPurpose());
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.registerOutParameter(10, Types.VARCHAR);
			cs.execute();
			System.out.println("Result = " + cs.getObject(9)+"-"+cs.getObject(10));
			
			if (c != null) 
			  c.commit();
			//st = c.createStatement();
			//rs = st.executeQuery("select info.GetDay from dual");
			//if (rs.next()) {
			//	date = rs.getDate(1);
			//}
			res.setCode(0);
			res.setName(""+cs.getObject(10));
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(1);
			res.setName(e.getMessage());
			try 
			{  c.rollback();}
			catch(Exception e2)
			{}
			return res;
		} finally {
			//close(rs);
			//close(st);
			close(cs);
			ConnectionPool.close(c);
		}
		//close(rs);
		//close(st);
		close(cs);
		ConnectionPool.close(c);
		return res;
	}
	
	public static Res UpdateFilterResults(int pFileId,  String alias) {
		Res res = new Res();
		res.setCode(1);
		String blockSql="declare "+
"  vpinfl client_p.pinfl%type; "+
"  vFio varchar2(80); "+
"  vFileId number:=?;  "+
"begin "+
"for rec in "+ 
  "(select branch, nciid, clientacc_file_id from "+
  "closeacc_file_records "+
"where clientacc_file_id = vFileId "+
"and doc_fio is null) "+
"loop "+
  "begin "+
   " select pinfl, substr(trim(family||' '||first_name||' '||patronymic),1,80) "+ 
   "   into vPinfl, vFio  "+
   "   from client_p where branch=rec.branch and id=rec.nciid; "+
   " update closeacc_file_records set  "+
   "   pinfl=vPinfl, doc_fio=vFio "+
   "  where clientacc_file_id=rec.clientacc_file_id "+
   "   and branch=rec.branch and nciid=rec.nciid; "+
  "exception "+
   " when no_data_found then "+ 
   "   null; "+
  "end;   "+
"end loop; "+
"end;";
		
		Connection c = null;
		CallableStatement cs = null;
		//ResultSet rs = null;
		//Statement st = null;
		try {
			if (alias != null && !alias.equals("")) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection();
			}
			cs = c.prepareCall(blockSql);
			cs.setInt (1, pFileId);
			cs.execute();
			
			//cs2 = c.prepareCall(blockSql2);
			//cs2.execute();
			
			if (c != null) 
			  c.commit();
			//st = c.createStatement();
			//rs = st.executeQuery("select info.GetDay from dual");
			//if (rs.next()) {
			//	date = rs.getDate(1);
			//}
			res.setCode(0);
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(1);
			res.setName(e.getMessage());
			try 
			{  c.rollback();}
			catch(Exception e2)
			{}
			return res;
		} finally {
			//close(rs);
			//close(st);
			close(cs);
			ConnectionPool.close(c);
		}
		//close(rs);
		//close(st);
		close(cs);
		ConnectionPool.close(c);
		return res;
	}
	
	
	public static List<RefData> getCodeBalBank(String alias) {
		return getRefData(
				"select code_bal data, code_bal label from ss_closeacc_code_bal order by code_bal",
				alias);
	}

	public static List<RefData> getCodeBalCentralbank(String alias) {
		if (listCodeAccBal == null || listCodeAccBal.size() == 0)
			listCodeAccBal = getRefData(
					"select code_b data, name_s label from s_account t where kod_k='08' and act='A' order by code_b",
					alias);
		return listCodeAccBal;
	}

	public static boolean isEnabledAccBal(String pAccBal, String alias) {
		List<RefData> vList = getCodeBalCentralbank(alias);
		for (int i = 0; i < vList.size(); i++) {
			// System.out.println(vList.get(i));
			if (vList.get(i).getData().equals(pAccBal)
					|| vList.get(i).getData() == pAccBal)
				return true;
		}
		return false;
	}

	public static void close(final CallableStatement cs) {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
		}
	}

	public static void close(final PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
		}
	}

	public static void close(final Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
		}
	}

	public static void close(final ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
		}
	}

}
