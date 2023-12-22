package com.is.qr_online.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TransactionService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM qr_transaction ";
	
	//private Map<String, String> main = new HashMap<String, String>();
    private Map<String, String> code40 = new HashMap<String, String>();
    private Map<String, String> code62 = new HashMap<String, String>();
    private Map<String, String> code63 = new HashMap<String, String>();

	public List<Transaction> getTransaction() {

		List<Transaction> list = new ArrayList<Transaction>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM qr_transaction");
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getString("qr_merchant_id"), rs.getString("id"),
						rs.getString("currency"), rs.getString("amount"), rs.getString("category"),
						rs.getString("product_code"), rs.getString("product_name"), rs.getString("fee_type"),
						rs.getString("fee_amount"), rs.getString("fee_percent"), rs.getString("result_code"),
						rs.getString("result_message"), rs.getString("format"));
				transaction.setCode_type(rs.getInt("code_type"));
				list.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(TransactionFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getMerchant_id())) {
			flfields.add(new FilterField(getCond(flfields) + "qr_merchant_id=?", filter.getMerchant_id()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?", filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getAmount())) {
			flfields.add(new FilterField(getCond(flfields) + "amount=?", filter.getAmount()));
		}
		if (!CheckNull.isEmpty(filter.getCategory())) {
			flfields.add(new FilterField(getCond(flfields) + "category=?", filter.getCategory()));
		}
		if (!CheckNull.isEmpty(filter.getProduct_code())) {
			flfields.add(new FilterField(getCond(flfields) + "product_code=?", filter.getProduct_code()));
		}
		if (!CheckNull.isEmpty(filter.getProduct_name())) {
			flfields.add(new FilterField(getCond(flfields) + "product_name=?", filter.getProduct_name()));
		}
		if (!CheckNull.isEmpty(filter.getFee_type())) {
			flfields.add(new FilterField(getCond(flfields) + "fee_type=?", filter.getFee_type()));
		}
		if (!CheckNull.isEmpty(filter.getFee_amount())) {
			flfields.add(new FilterField(getCond(flfields) + "fee_amount=?", filter.getFee_amount()));
		}
		if (!CheckNull.isEmpty(filter.getFee_percent())) {
			flfields.add(new FilterField(getCond(flfields) + "fee_percent=?", filter.getFee_percent()));
		}
		if (!CheckNull.isEmpty(filter.getResult_code())) {
			flfields.add(new FilterField(getCond(flfields) + "result_code=?", filter.getResult_code()));
		}
		if (!CheckNull.isEmpty(filter.getResult_message())) {
			flfields.add(new FilterField(getCond(flfields) + "result_message=?", filter.getResult_message()));
		}
		if (!CheckNull.isEmpty(filter.getFormat())) {
			flfields.add(new FilterField(getCond(flfields) + "format=?", filter.getFormat()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(getCond(flfields) + "code_type=?", filter.getCode_type()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(TransactionFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM qr_transaction ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Transaction> getTransactionsFl(int pageIndex, int pageSize, TransactionFilter filter) {

		List<Transaction> list = new ArrayList<Transaction>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getString("qr_merchant_id"), rs.getString("id"),
						rs.getString("currency"), rs.getString("amount"), rs.getString("category"),
						rs.getString("product_code"), rs.getString("product_name"), rs.getString("fee_type"),
						rs.getString("fee_amount"), rs.getString("fee_percent"), rs.getString("result_code"),
						rs.getString("result_message"), rs.getString("format"));
				transaction.setCode_type(rs.getInt("code_type"));
				list.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public Transaction getTransaction(int transactionId) {

		Transaction transaction = new Transaction();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM qr_transaction WHERE id=?");
			ps.setInt(1, transactionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				transaction = new Transaction();

				transaction.setMerchant_id(rs.getString("qr_merchant_id"));
				transaction.setId(rs.getString("id"));
				transaction.setCurrency(rs.getString("currency"));
				transaction.setAmount(rs.getString("amount"));
				transaction.setCategory(rs.getString("category"));
				transaction.setProduct_code(rs.getString("product_code"));
				transaction.setProduct_name(rs.getString("product_name"));
				transaction.setFee_type(rs.getString("fee_type"));
				transaction.setFee_amount(rs.getString("fee_amount"));
				transaction.setFee_percent(rs.getString("fee_percent"));
				transaction.setResult_code(rs.getString("result_code"));
				transaction.setResult_message(rs.getString("result_message"));
				transaction.setFormat(rs.getString("format"));
				transaction.setCode_type(rs.getInt("code_type"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return transaction;
	}

	public static Transaction create(Transaction transaction) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT sq_qr_transaction.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				transaction.setId(rs.getString("id"));
			}
			ps = c.prepareStatement("INSERT INTO qr_transaction (qr_merchant_id, id, currency, "
					+ "amount, category, product_code, product_name, fee_type, fee_amount, "
					+ "fee_percent, format, code_type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, transaction.getMerchant_id());
			ps.setString(2, transaction.getId());
			ps.setString(3, transaction.getCurrency());
			ps.setString(4, transaction.getAmount());
			ps.setString(5, transaction.getCategory());
			ps.setString(6, transaction.getProduct_code());
			ps.setString(7, transaction.getProduct_name());
			ps.setString(8, transaction.getFee_type());
			ps.setString(9, transaction.getFee_amount());
			ps.setString(10, transaction.getFee_percent());
			ps.setString(11, transaction.getFormat());
			ps.setInt(12, transaction.getCode_type());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return transaction;
	}

	public static void update(Transaction transaction) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"UPDATE qr_transaction SET format=?, currency=?, amount=?, category=?, product_code=?, product_name=?, fee_type=?, fee_amount=?, fee_percent=?, code_type=? WHERE id=?");

			ps.setString(1, transaction.getFormat());
			ps.setString(2, transaction.getCurrency());
			ps.setString(3, transaction.getAmount());
			ps.setString(4, transaction.getCategory());
			ps.setString(5, transaction.getProduct_code());
			ps.setString(6, transaction.getProduct_name());
			ps.setString(7, transaction.getFee_type());
			ps.setString(8, transaction.getFee_amount());
			ps.setString(9, transaction.getFee_percent());
			ps.setInt(10, transaction.getCode_type());
			ps.setString(11, transaction.getId());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void updateMessage(String id, String message, String code, String qr,String Qr_id,String Descr) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE qr_transaction SET result_message=?, result_code=?, qr_data=?, qr_id=?, descr=? WHERE id=?");

			ps.setString(1, message);
			ps.setString(2, code);
			ps.setString(3, qr);
			ps.setString(4, Qr_id);
			ps.setString(5, Descr);
			ps.setString(6, id);

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error("Update_message_qr: "+e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}

	}

	public static String getQrCode(String transaction_id) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String res = "";

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select q.qr_data qr_code from qr_transaction q where q.id=?");
			ps.setString(1, transaction_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("qr_code");
			}
		} catch (Exception e) {
			e.printStackTrace();
			com.is.ISLogger.getLogger().warn("oshibka:" + e.getMessage());
		} finally {
			try {

				if (ps != null) {

					ps.close();
				}

				if (rs != null) {

					rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

			ConnectionPool.close(c);
		}

		//System.out.println("QR_CODE:" + res);
		return res;
	}

	public static void saveDocument(XWPFDocument document, String documentPath) throws Exception {
		FileOutputStream documentOutputStream = null;
		try {
			ISLogger.getLogger().error("saveDocument: " + documentPath);
			documentOutputStream = new FileOutputStream(new File(documentPath));
			document.write(documentOutputStream);
		} catch (Exception e) {
			throw new Exception("Unable to save document '" + documentPath + "' due to the exception:\n" + e);
		} finally {
			if (documentOutputStream != null) {
				documentOutputStream.close();
			}
		}
	}

	public static void remove(Transaction transaction) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM qr_transaction WHERE id=?");
			ps.setString(1, transaction.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void create(Transaction transaction, String merchant_id) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT sq_qr_transaction.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				transaction.setId(rs.getString("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO qr_transaction (QR_MERCHANT_ID,id,currency,amount,category,product_code,"
							+ "product_name,fee_type,fee_amount,fee_percent) " + "VALUES (?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, merchant_id);
			ps.setString(2, transaction.getId());
			ps.setString(3, transaction.getCurrency());
			ps.setString(4, transaction.getAmount());
			ps.setString(5, transaction.getCategory());
			ps.setString(6, transaction.getProduct_code());
			ps.setString(7, transaction.getProduct_name());
			ps.setString(8, transaction.getFee_type());
			ps.setString(9, transaction.getFee_amount());
			ps.setString(10, transaction.getFee_percent());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {

			ConnectionPool.close(c);
		}
	}

	public static String getBank(String branch) {

		String res = "";
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select m.bank_type from s_mfo m where m.bank_id=?");
			ps.setString(1, branch);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				res = rs.getString("bank_type");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;

	}
	public void trimStr(String qr_str, Map<String, String> map) {
        if (qr_str.length() == 0) return;
        String code = qr_str.substring(0, 2);
        qr_str = qr_str.substring(2);
        String length = qr_str.substring(0, 2);
        qr_str = qr_str.substring(2);
        String value = qr_str.substring(0, Integer.parseInt(length));
        qr_str = qr_str.substring(value.length());
        if (code.equals("40")) {
            System.out.println("40 in");
            trimStr(value, code40);
            System.out.println("40 quit");
        }
        if (code.equals("62")) {
            trimStr(value, code62);
        }
        if (code.equals("64")) {
            trimStr(value, code63);
        }
        System.out.println(code + " : " + length + " : " + value);
        map.put(code, value);
        trimStr(qr_str, map);
    }

}
