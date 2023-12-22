package com.is.sinov;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class sinovService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM Sinov ";

	public List<sinov> getSinov() {

		List<sinov> list = new ArrayList<sinov>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM sinov");
			while (rs.next()) {
				list.add(new sinov(rs.getString("id"), rs.getString("branch"), rs.getString("name")));
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

	private static List<FilterField> getFilterFields(sinovFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getName()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(sinovFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM sinov ");
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

	public static List<sinov> getSinovsFl(int pageIndex, int pageSize, sinovFilter filter) {

		List<sinov> list = new ArrayList<sinov>();
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
				list.add(new sinov(rs.getString("id"), rs.getString("branch"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public sinov getSinov(int sinovId) {

		sinov sinov = new sinov();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM sinov WHERE id=?");
			ps.setInt(1, sinovId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sinov = new sinov();

				sinov.setId(rs.getString("id"));
				sinov.setBranch(rs.getString("branch"));
				sinov.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return sinov;
	}

	public static sinov create(sinov sinov) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT seq_sinov.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sinov.setId(rs.getString("id"));
			}
			ps = c.prepareStatement("INSERT INTO sinov (id, branch, name) VALUES (?,?,?)");

			ps.setString(1, sinov.getId());
			ps.setString(2, sinov.getBranch());
			ps.setString(3, sinov.getName());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return sinov;
	}

	public static void update(sinov sinov) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE sinov SET branch=?, name=? WHERE id=?");

			ps.setString(1, sinov.getBranch());
			ps.setString(2, sinov.getName());
			ps.setString(3, sinov.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void remove(sinov sinov) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM sinov WHERE id=?");
			ps.setString(1, sinov.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
}
