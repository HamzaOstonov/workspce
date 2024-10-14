package com.is.bpri.bpr_ld_account;

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

public class BprLdAccountService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from bpr_ld_account ";
	static List<RefData> Acc_type_id_ = null;

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(BprLdAccountFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_type_id())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_type_id=?",
					filter.getAcc_type_id()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "account=?",
					filter.getAccount()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(BprLdAccountFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ld_account ");
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

	public static List<BprLdAccount> getBprLdAccountsFl(int pageIndex,
			int pageSize, BprLdAccountFilter filter, String alias) {
		List<BprLdAccount> list = new ArrayList<BprLdAccount>();
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
				list.add(new BprLdAccount(
						rs.getInt("bpr_id"),
							rs.getString("acc_type_id"),
								rs.getString("account"),
									rs.getInt("is_open"),
										rs.getString("acc_order"),
											rs.getString("acc_group_id")));
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

	public static BprLdAccount create(BprLdAccount bprldaccount, String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO bpr_ld_account (bpr_id, acc_type_id, account, is_open,acc_order,acc_group_id) VALUES (?,?,?,?,?,?)");
			ps.setInt(1, bprldaccount.getBpr_id());
			ps.setString(2, bprldaccount.getAcc_type_id());
			ps.setString(3, bprldaccount.getAccount());
			ps.setInt(4, bprldaccount.getIs_open());
			ps.setString(5, bprldaccount.getAcc_order());
			ps.setString(6, bprldaccount.getAcc_group_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error("getBprId = " + bprldaccount.getBpr_id());
			ISLogger.getLogger().error("getAcc_type_id = " + bprldaccount.getAcc_type_id());
			ISLogger.getLogger().error("getAccount = " + bprldaccount.getAccount());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return bprldaccount;
	}

	public static void update(BprLdAccount bprldaccount,String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_account SET acc_type_id=?, account=?, acc_order=?,acc_group_id=? WHERE bpr_id=? and acc_type_id=?");
			ps.setString(1, bprldaccount.getAcc_type_id());
			ps.setString(2, bprldaccount.getAccount());
			ps.setString(3, bprldaccount.getAcc_order());
			ps.setString(4, bprldaccount.getAcc_group_id());
			ps.setInt(5, bprldaccount.getBpr_id());
			ps.setString(6, bprldaccount.getAcc_type_id());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static void remove(BprLdAccount current,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_account where bpr_ld_account.bpr_id=? and bpr_ld_account.acc_type_id=?");
			ps.setInt(1, current.getBpr_id());
			ps.setString(2, current.getAcc_type_id());
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
	
	public static List<RefData> getAcc_type_id(String branch) {
		String select = "select ss_ld_acc_type.id data,ss_ld_acc_type.id||' - '||ss_ld_acc_type.name label from ss_ld_acc_type where ss_ld_acc_type.id<200 order by data";
		if (Acc_type_id_ == null) {
			Acc_type_id_ = Utils.getRefData(select, branch);
		}
		return Acc_type_id_;
	}
}
