package com.is.qr_online.payee;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;

public class PayeeService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM qr_payee ";
	private static String orderby = " ";

	public List<Payee> getPayee() {

		List<Payee> list = new ArrayList<Payee>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.executeUpdate();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM qr_payee q WHERE q.branch=info.GetBranch");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Payee(rs.getString("branch"), rs.getString("inn"), rs.getString("account"),
						rs.getString("name"), rs.getString("mobile_phone"), rs.getString("res_regqrclient_code"),
						rs.getString("res_setclientphone_code")));
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
		} else {
			return " where ";
		}
	}

	private static List<FilterField> getFilterFields(PayeeFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getInn())) {
			flfields.add(new FilterField(getCond(flfields) + "inn=?", filter.getInn()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "account=?", filter.getAccount()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<? and branch=info.GetBranch", 1001));

		return flfields;
	}

	public static int getCount(PayeeFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM qr_payee ");
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

	public static List<Payee> getPayeesFl(int pageIndex, int pageSize, PayeeFilter filter) {

		List<Payee> list = new ArrayList<Payee>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		System.out.println("78787: "+psql1);
		sql.append(msql);
		System.out.println("78788: "+msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		/* ----------------------------- */
		sql.append(orderby);
		/* ----------------------------- */

		sql.append(psql2);
		System.out.println("78789: "+psql2);

		try {
			c = ConnectionPool.getConnection();
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.executeUpdate();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			System.out.println("retretretre: "+sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Payee(rs.getString("branch"), rs.getString("inn"), rs.getString("account"),
						rs.getString("name"), rs.getString("mobile_phone"), rs.getString("res_regqrclient_code"),
						rs.getString("res_setclientphone_code")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error("Oshibka_payee_getClients :"+e.getMessage());

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static boolean getPayee(Payee payee) {

		Connection c = null;
		boolean isHave = false;

		try {
			int count = 0;
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM qr_payee WHERE inn=? and account=?");
			ps.setString(1, payee.getInn());
			ps.setString(2, payee.getAccount());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count++;
			}

			System.out.println(count);
			if (count > 0) {
				isHave = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return isHave;
	}

	public static Payee getPayee(String payee_inn) {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Payee payee = new Payee();

		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM qr_payee WHERE inn=?");
			ps.setString(1, payee_inn);
			rs = ps.executeQuery();
			if (rs.next()) {
				payee = new Payee();
				payee.setInn(rs.getString("inn"));
				payee.setAccount(rs.getString("account"));
				payee.setBranch(rs.getString("branch"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return payee;
	}

	public static String getNamePayee(String p_inn) {
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String result = null;
		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM qr_payee WHERE inn=?");
			ps.setString(1, p_inn);
			rs = ps.executeQuery();
			if (rs.next()) {

				result = rs.getString("name");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("payee_name: " + e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return result;
	}

	public static Payee create(Payee payee) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT qr_payee_seq.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				payee.setDocnum(rs.getString("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO qr_payee (branch, inn, account, name,mobile_phone,docnum) VALUES (?,?,?,?,?,?)");

			ps.setString(1, payee.getBranch());
			ps.setString(2, payee.getInn());
			ps.setString(3, payee.getAccount());
			ps.setString(4, payee.getName());
			ps.setString(5, payee.getMobile_phone());
			ps.setString(6, payee.getDocnum());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return payee;
	}

	public static String getDocnum(String inn, String branch) {
		String result = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT docnum FROM qr_payee q where q.inn=? and q.branch=?");
			ps.setString(1, inn);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString("docnum");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return result;
	}

	public static boolean update(Payee payee) {

		Connection c = null;
		PreparedStatement ps = null;
		boolean check = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE qr_payee SET inn=?, account=?,mobile_phone=? WHERE inn=?");

			System.out.println(payee.getInn());
			System.out.println(payee.getAccount());
			// System.out.println(payee.getId());

			ps.setString(1, payee.getInn());
			ps.setString(2, payee.getAccount());
			ps.setString(3, payee.getMobile_phone());
			ps.setString(4, payee.getInn());
			ps.executeUpdate();
			c.commit();
			check = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
			check = false;
		} finally {
			ConnectionPool.close(c);
		}

		return check;
	}

	public static void remove(Payee payee) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM qr_payee WHERE inn=?");
			ps.setString(1, payee.getInn());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static String orderBy(int index, String tablename) {
		if (index == 1) {
			orderby = " order by " + tablename + " asc";
		} else if (index == 2) {
			orderby = " order by " + tablename + " desc";
		} else {
			orderby = " ";
		}
		return orderby;
	}

	public static void updateResRegQrClient(String inn, String branch, String code) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE qr_payee q SET q.res_regqrclient_code=? WHERE q.branch=? and q.inn=?");

			ps.setString(1, code);
			ps.setString(2, branch);
			ps.setString(3, inn);

			ps.executeUpdate();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void updateResClientPhone(String inn, String branch, String code) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE qr_payee q SET q.res_setclientphone_code=? WHERE q.branch=? and q.inn=?");

			ps.setString(1, code);
			ps.setString(2, branch);
			ps.setString(3, inn);

			ps.executeUpdate();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());

		} finally {
			ConnectionPool.close(c);

		}

	}
}
