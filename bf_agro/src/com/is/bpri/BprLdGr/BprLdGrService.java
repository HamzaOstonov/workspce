package com.is.bpri.BprLdGr;

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
import com.is.utils.RefData;
import com.is.utils.Res;

public class BprLdGrService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = " select * from (SELECT * FROM bpr_ld_gr order by oper_id) ";
	private static String grafsql = " select * from (SELECT distinct vlgt.oper_id operIdValue, vlgt.name nameValue, blg.* FROM bpr_ld_gr blg, v_ld_gr_type vlgt where  vlgt.oper_id=blg.oper_id and vlgt.exp_id = blg.exp_id order by blg.bpr_id ) ";
	private static String addsql = " select * from (select * from v_ld_gr_type a where (a.oper_id=1 and exp_id=0) or (a.oper_id=2 and exp_id=0) or (a.oper_id=4 and exp_id=1) or (a.oper_id=13 and exp_id=0) order by a.oper_id, a.exp_id) ";

	public static List<RefData> getOperId(String branch) {
		return Utils.getRefData("select a.oper_id data,a.oper_id||'-'||a.name label from v_ld_gr_type a where a.oper_id<200 order by a.oper_id, a.exp_id ",branch);
	}

	public static List<RefData> getGrafMethod(String branch) {
		return Utils.getRefData("select id data,id||'-'|| name label from ss_ld_graf_method order by id",branch);
	}

	public static List<RefData> getExpType(String branch) {
		return Utils.getRefData("select id data,id||'-'|| name label from ss_ld_exp_type where id<200 order by id",branch);
	}

	public static List<RefData> getPayPeriod(String branch) {
		return Utils.getRefData("select id data,name label from ss_ld_pay_period order by id",branch);
	}

	public static List<RefData> getDateType(String branch) {
		return Utils.getRefData("select id data,id||'-'|| name label from ss_bpr_date_type order by data",branch);
	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(BprLdGrFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getOper_id())) {
			flfields.add(new FilterField(getCond(flfields) + "oper_id=?",filter.getOper_id()));
		}
		if (!CheckNull.isEmpty(filter.getExp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "exp_id=?", filter.getExp_id()));
		}
		if (!CheckNull.isEmpty(filter.getGraf_method())) {
			flfields.add(new FilterField(getCond(flfields) + "graf_method=?",filter.getGraf_method()));
		}
		if (!CheckNull.isEmpty(filter.getNum())) {
			flfields.add(new FilterField(getCond(flfields) + "num=?", filter.getNum()));
		}
		if (!CheckNull.isEmpty(filter.getPay_period())) {
			flfields.add(new FilterField(getCond(flfields) + "pay_period=?",filter.getPay_period()));
		}
		if (!CheckNull.isEmpty(filter.getDay())) {
			flfields.add(new FilterField(getCond(flfields) + "day=?", filter.getDay()));
		}
		if (!CheckNull.isEmpty(filter.getDate_from())) {
			flfields.add(new FilterField(getCond(flfields) + "date_from=?",filter.getDate_from()));
		}
		if (!CheckNull.isEmpty(filter.getDate_to())) {
			flfields.add(new FilterField(getCond(flfields) + "date_to=?",filter.getDate_to()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	private static List<FilterField> getFilterFieldsGrType(BprGrTypeFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getOper_id())) {
			flfields.add(new FilterField(getCond(flfields) + "oper_id=?",filter.getOper_id()));
		}
		if (!CheckNull.isEmpty(filter.getExp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "exp_id=?", filter.getExp_id()));
		}
		if (!CheckNull.isEmpty(filter.getGraf_method())) {
			flfields.add(new FilterField(getCond(flfields) + "graf_method=?",filter.getGraf_method()));
		}

		if (!CheckNull.isEmpty(filter.getNum())) {
			flfields.add(new FilterField(getCond(flfields) + "num=?", filter.getNum()));
		}
		if (!CheckNull.isEmpty(filter.getPay_period())) {
			flfields.add(new FilterField(getCond(flfields) + "pay_period=?",filter.getPay_period()));
		}
		if (!CheckNull.isEmpty(filter.getDay())) {
			flfields.add(new FilterField(getCond(flfields) + "day=?", filter.getDay()));
		}
		if (!CheckNull.isEmpty(filter.getDate_from())) {
			flfields.add(new FilterField(getCond(flfields) + "date_from=?",filter.getDate_from()));
		}
		if (!CheckNull.isEmpty(filter.getDate_to())) {
			flfields.add(new FilterField(getCond(flfields) + "date_to=?",filter.getDate_to()));
		}
		if (!CheckNull.isEmpty(filter.getGr_type())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getGr_type()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	private static List<FilterField> getFilterFieldsAddGrType(BprGrTypeFilterAdd filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getOPerId())) {
			flfields.add(new FilterField(getCond(flfields) + "oper_id=?",filter.getOPerId()));
		}
		if (!CheckNull.isEmpty(filter.getExpId())) {
			flfields.add(new FilterField(getCond(flfields) + "exp_id=?", filter.getExpId()));
		}
		if (!CheckNull.isEmpty(filter.getGr_type())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getGr_type()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(BprLdGrFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ld_gr ");
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
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

	public static int getCountGrTypeAdd(BprGrTypeFilterAdd filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFieldsAddGrType(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM V_LD_GR_TYPE ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
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

	public static int getCountGrType(BprGrTypeFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFieldsGrType(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM V_LD_GR_TYPE ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
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

	public static List<BprLdGr> getLdGrsFl(int pageIndex, int pageSize,BprLdGrFilter filter, String alias) {
		List<BprLdGr> list = new ArrayList<BprLdGr>();
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
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprLdGr(rs.getInt("id"), rs.getInt("bpr_id"), rs
						.getString("oper_id"), rs.getString("exp_id"), rs
						.getString("graf_method"), rs.getString("num"), rs
						.getString("pay_period"), rs.getString("day"), rs
						.getString("date_from"), rs.getString("date_to")));
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

	public static List<BprGrType> getLdGrTypeFl(int pageIndex, int pageSize,BprGrTypeFilter filter, String alias) {
		List<BprGrType> list = new ArrayList<BprGrType>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFieldsGrType(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(grafsql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprGrType(rs.getInt("id"), rs.getInt("bpr_id"), rs
						.getInt("oper_id"), rs.getInt("exp_id"), rs
						.getInt("graf_method"), rs.getInt("num"), rs
						.getInt("pay_period"), rs.getInt("day"), rs
						.getInt("date_from"), rs.getInt("date_to"), rs
						.getString("nameValue")));
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

	public static List<BprGrTypeAdd> getLdGrTypeFlAdd(int pageIndex,int pageSize, BprGrTypeFilterAdd filter, String alias) {
		List<BprGrTypeAdd> list = new ArrayList<BprGrTypeAdd>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFieldsAddGrType(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(addsql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprGrTypeAdd(rs.getInt("oper_id"), rs
						.getInt("exp_id"), rs.getString("name")));
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

	public static BprLdGr create(BprLdGr ldgr, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_bpr_ld_gr.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				ldgr.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_ld_gr (id, bpr_id, oper_id, exp_id, graf_method, num, pay_period, day, date_from, date_to) VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, ldgr.getId());
			ps.setInt(2, ldgr.getBpr_id());
			ps.setString(3, ldgr.getOper_id());
			ps.setString(4, ldgr.getExp_id());
			ps.setString(5, ldgr.getGraf_method());
			ps.setString(6, ldgr.getNum());
			ps.setString(7, ldgr.getPay_period());
			ps.setString(8, ldgr.getDay());
			ps.setString(9, ldgr.getDate_from());
			ps.setString(10, ldgr.getDate_to());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return ldgr;
	}

	public static void updateLdGr(BprLdGr ldgr, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_gr SET graf_method=?, num=?, pay_period=?, day=?, date_from=?, date_to=?   WHERE id=? and bpr_id=? and oper_id=? and exp_id=? ");
			ps.setString(1, ldgr.getGraf_method());
			ps.setString(2, ldgr.getNum());
			ps.setString(3, ldgr.getPay_period());
			ps.setString(4, ldgr.getDay());
			ps.setString(5, ldgr.getDate_from());
			ps.setString(6, ldgr.getDate_to());
			ps.setInt(7, ldgr.getId());
			ps.setInt(8, ldgr.getBpr_id());
			ps.setString(9, ldgr.getOper_id());
			ps.setString(10, ldgr.getExp_id());
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
	
	public static void remove(int id,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from bpr_ld_gr where bpr_ld_gr.id=?");
			ps.setInt(1, id);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
}
