package com.is.qr_online.merchant;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MerchantService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM qr_merchant ";

	public List<Merchant> getMerchant() {

		List<Merchant> list = new ArrayList<Merchant>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM qr_Merchant");
			while (rs.next()) {
				list.add(new Merchant(rs.getString("qr_payee_id"), rs.getString("id"), rs.getString("activity"),
						rs.getString("name"), rs.getString("country"), rs.getString("city"),
						rs.getString("postal_code"), rs.getString("phone_number"), rs.getString("email")));
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

	private static List<FilterField> getFilterFields(MerchantFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getPayee_id())) {
			flfields.add(new FilterField(getCond(flfields) + "qr_payee_id=?", filter.getPayee_id()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getActivity())) {
			flfields.add(new FilterField(getCond(flfields) + "activity=?", filter.getActivity()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getCountry())) {
			flfields.add(new FilterField(getCond(flfields) + "country=?", filter.getCountry().substring(0, 2)));
		}
		if (!CheckNull.isEmpty(filter.getCity())) {
			flfields.add(new FilterField(getCond(flfields) + "city=?", filter.getCity()));
		}
		if (!CheckNull.isEmpty(filter.getPostal_code())) {
			flfields.add(new FilterField(getCond(flfields) + "postal_code=?", filter.getPostal_code()));
		}
		if (!CheckNull.isEmpty(filter.getPhone_number())) {
			flfields.add(new FilterField(getCond(flfields) + "phone_number=?", filter.getPhone_number()));
		}
		if (!CheckNull.isEmpty(filter.getEmail())) {
			flfields.add(new FilterField(getCond(flfields) + "email=?", filter.getEmail()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(MerchantFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM qr_merchant ");
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

	public static List<Merchant> getMerchantsFl(int pageIndex, int pageSize, MerchantFilter filter) {

		List<Merchant> list = new ArrayList<Merchant>();
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
				list.add(new Merchant(rs.getString("qr_payee_id"), rs.getString("id"), rs.getString("activity"),
						rs.getString("name"), rs.getString("country"), rs.getString("city"),
						rs.getString("postal_code"), rs.getString("phone_number"), rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Merchant getMerchant(String merchantId) {

		Merchant merchant = new Merchant();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM qr_merchant WHERE id=?");
			ps.setString(1, merchantId);
			rs = ps.executeQuery();
			if (rs.next()) {
				merchant = new Merchant();

				merchant.setPayee_id(rs.getString("qr_payee_id"));
				merchant.setId(rs.getString("id"));
				merchant.setActivity(rs.getString("activity"));
				merchant.setName(rs.getString("name"));
				merchant.setCountry(rs.getString("country"));
				merchant.setCity(rs.getString("city"));
				merchant.setPostal_code(rs.getString("postal_code"));
				merchant.setPhone_number(rs.getString("phone_number"));
				merchant.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return merchant;
	}

	
	public static Merchant create(Merchant merchant) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT sq_qr_merchant.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				merchant.setId(rs.getString("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO qr_merchant (qr_payee_id, id, activity, name, country, city, postal_code, phone_number, email) VALUES (?,?,?,?,?,?,?,?,?)");

			System.out.println(merchant.getCountry());
			ps.setString(1, merchant.getPayee_id());
			ps.setString(2, merchant.getId());
			ps.setString(3, merchant.getActivity());
			ps.setString(4, merchant.getName());
			ps.setString(5, merchant.getCountry());
			ps.setString(6, merchant.getCity());
			ps.setString(7, merchant.getPostal_code());
			ps.setString(8, merchant.getPhone_number());
			ps.setString(9, merchant.getEmail());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return merchant;
	}

	public static void update(Merchant merchant) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE qr_merchant SET activity=?, name=?, country=?, city=?, postal_code=?, phone_number=?, email=?  WHERE id=?");

			ps.setString(1, merchant.getActivity());
			ps.setString(2, merchant.getName());
			ps.setString(3, merchant.getCountry());
			ps.setString(4, merchant.getCity());
			ps.setString(5, merchant.getPostal_code());
			ps.setString(6, merchant.getPhone_number());
			ps.setString(7, merchant.getEmail());
			ps.setString(8, merchant.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void update(String payee_id, String id) {

		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update qr_merchant set qr_payee_id=? where qr_payee_id=?");
			ps.setString(1, payee_id);
			ps.setString(2, id);
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}

	}

	
	
	public static void remove(Merchant merchant) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM qr_merchant WHERE id=?");
			ps.setString(1, merchant.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static Merchant create(Merchant merchant, String inn) {

		Connection c = null;
				
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT sq_qr_merchant.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				merchant.setId(rs.getString("id"));
			}
			ps = c.prepareStatement("INSERT INTO qr_merchant (QR_PAYEE_ID,id,activity,name,"
					+ "country,city,postal_code,phone_number,email) " + "VALUES (?,?,?,?,?,?,?,?,?)");

			ps.setString(1, inn);
			ps.setString(2, merchant.getId());
			ps.setString(3, merchant.getActivity());
			ps.setString(4, merchant.getName());
			ps.setString(5, merchant.getCountry());
			ps.setString(6, merchant.getCity());
			ps.setString(7, merchant.getPostal_code());
			ps.setString(8, merchant.getPhone_number());
			ps.setString(9, merchant.getEmail());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return merchant;
	}

	public static boolean getMerchant(Merchant merchant, String payee_id) {

		Connection c = null;
		boolean isHave = false;

		try {
			int count = 0;
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from qr_merchant m where "
					+ "m.qr_payee_id=? and m.activity=? and m.name=? and m.country=? "
					+ "and m.city=? and m.postal_code=? and m.phone_number=? and m.email=?");
			ps.setString(1, payee_id);
			ps.setString(2, merchant.getActivity());
			ps.setString(3, merchant.getName());
			ps.setString(4, merchant.getCountry());
			ps.setString(5, merchant.getCity());
			ps.setString(6, merchant.getPostal_code());
			ps.setString(7, merchant.getPhone_number());
			ps.setString(8, merchant.getEmail());

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
	
	public static String getMerchantID(Merchant merchant, String payee_id) {

		Connection c = null;
		String merchant_id = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select id from qr_merchant m where "
					+ "m.qr_payee_id=? and m.activity=? and m.name=? and m.country=? "
					+ "and m.city=? and m.postal_code=? and m.phone_number=? and m.email=?");
			ps.setString(1, payee_id);
			ps.setString(2, merchant.getActivity());
			ps.setString(3, merchant.getName());
			ps.setString(4, merchant.getCountry());
			ps.setString(5, merchant.getCity());
			ps.setString(6, merchant.getPostal_code());
			ps.setString(7, merchant.getPhone_number());
			ps.setString(8, merchant.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				merchant_id = rs.getString("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return merchant_id;
	}

}
