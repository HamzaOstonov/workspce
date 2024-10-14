package com.is.bpri.bproductDesc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class bproduct_descService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "SELECT * FROM (select bd.id, bd.detail_group, bd.detail_id, bd.branch from bproduct_desc bd, bproduct b where bd.id = b.id and bd.state < 90) ";

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(bproduct_descFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getDetail_group())) {
			flfields.add(new FilterField(getCond(flfields) + "detail_group=?",
					filter.getDetail_group()));
		}
		if (!CheckNull.isEmpty(filter.getDetail_id())) {
			flfields.add(new FilterField(getCond(flfields) + "detail_id=?",
					filter.getDetail_id()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter
					.getBranch()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(bproduct_descFilter filter) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bproduct_desc ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<bproduct_desc> getbproduct_descsFl(int pageIndex,
			int pageSize, bproduct_descFilter filter) {
		List<bproduct_desc> list = new ArrayList<bproduct_desc>();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			System.out.println(sql);
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
				System.out.println(flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new bproduct_desc(rs.getInt("id"),
						rs.getInt("detail_group"),
						rs.getString("detail_id"), 
						rs.getString("branch")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Res update(bproduct_desc bproduct_desc) {
		Connection c = null;
		Res res = new Res();
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE bproduct_desc SET detail_id=? WHERE id=? and detail_group=? ");
			ps.setString(1, bproduct_desc.getDetail_id());
			ps.setInt(2, bproduct_desc.getId());
			ps.setInt(3, bproduct_desc.getDetail_group());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<bproduct_desc> getBproductDescParams(int id,
			int detail_group) {
		List<bproduct_desc> list = new ArrayList<bproduct_desc>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(" select b.id, b.detail_group, b.detail_id, b.branch from bproduct_desc b where id = ? and detail_group = ? ");
			ps.setInt(1, id);
			ps.setInt(2, detail_group);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new bproduct_desc(
						rs.getInt("id"),
							rs.getInt("detail_group"),
								rs.getString("detail_id"),
									rs.getString("branch")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
}
