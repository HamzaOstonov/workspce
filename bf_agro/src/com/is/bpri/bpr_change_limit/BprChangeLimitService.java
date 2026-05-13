package com.is.bpri.bpr_change_limit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class BprChangeLimitService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from bpr_change_limit ";
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(BprChangeLimitFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getMonth_value())) {
			flfields.add(new FilterField(getCond(flfields) + "month_value=?",
					filter.getMonth_value()));
		}
		if (!CheckNull.isEmpty(filter.getProcent())) {
			flfields.add(new FilterField(getCond(flfields) + "procent=?",
					filter.getProcent()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<? order by month_value", 1001));

		return flfields;
	}

	public static int getCount(BprChangeLimitFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_change_limit ");
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

	public static List<BprChangeLimit> getbpr_change_limitsFl(int pageIndex,
			int pageSize, BprChangeLimitFilter filter) {

		List<BprChangeLimit> list = new ArrayList<BprChangeLimit>();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprChangeLimit(
						rs.getInt("id"), 
						rs.getString("bpr_id"),
						rs.getString("month_value"),
						rs.getString("procent"),
						rs.getString("day")));
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

	public static BprChangeLimit create(BprChangeLimit bpr_change_limit,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_bpr_change_limit.NEXTVAL id FROM DUAL ");
			rs = ps.executeQuery();
			if (rs.next()) {
				bpr_change_limit.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_change_limit (id, bpr_id, month_value, procent,day) VALUES (?,?,?,?,?) ");
			ps.setInt(1, bpr_change_limit.getId());
			ps.setString(2, bpr_change_limit.getBpr_id());
			ps.setString(3, bpr_change_limit.getMonth_value());
			ps.setString(4, bpr_change_limit.getProcent());
			ps.setString(5, bpr_change_limit.getDay());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return bpr_change_limit;
	}

	public static void update(BprChangeLimit bpr_change_limit,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_change_limit SET month_value=?, procent=?, day=? WHERE id=? and bpr_id=? ");
			ps.setString(1, bpr_change_limit.getMonth_value());
			ps.setString(2, bpr_change_limit.getProcent());
			ps.setString(3, bpr_change_limit.getDay());
			ps.setInt(4, bpr_change_limit.getId());
			ps.setString(5, bpr_change_limit.getBpr_id());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(BprChangeLimit bpr_change_limit,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_change_limit where id=? ");
			ps.setInt(1, bpr_change_limit.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static List<BprChangeLimit> getLimitValues(int bpr_id) {
		List<BprChangeLimit> list = new ArrayList<BprChangeLimit>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(" select * from bpr_change_limit where bpr_id=? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprChangeLimit(rs.getInt("id"), rs
						.getString("bpr_id"), rs.getString("month_value"), rs
						.getString("procent"),
						rs.getString("day")));
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
