package com.is.account.sevice;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.account.model.Account;
import com.is.account.model.AccountFilter;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class AccountDao implements Dao<Account> {
	private static Logger logger = Logger.getLogger(AccountDao.class);
	private String alias;
	private AccountFilter filter;
	// private Connection con;
	private int count;
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	// private static String msql = "SELECT
	// branch,id,client,name,s_in,s_out,dt,ct,l_date,state FROM Account a";
	private static String msql = "SELECT branch,id,client,name,info.GetSumScale(s_in,currency) s_in,info.GetSumScale(s_out,currency) s_out,info.GetSumScale(dt,currency) dt,info.GetSumScale(ct,currency) ct,l_date,state FROM Account a";

	// (rs.getString("branch"),
	// rs.getString("id"),
	// rs.getString("client"),
	// rs.getString("name"),
	// rs.getBigDecimal("s_in").divide(hundred),
	// rs.getBigDecimal("s_out").divide(hundred),
	// rs.getBigDecimal("dt").divide(hundred),
	// rs.getBigDecimal("ct").divide(hundred),
	// rs.getDate("l_date"),
	// rs.getInt("state")));
	private AccountDao(String alias) {
		super();
		this.alias = alias;
	}

	public static AccountDao getInstance(String alias) {
		return new AccountDao(alias);
	}

	@Override
	public List<FilterField> getFilterFields() {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.id=?", filter.getId()));
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.id like ?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_bal())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.acc_bal=?", filter.getAcc_bal()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.currency=?", filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getClient())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.client=?", filter.getClient()));
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.client like ?", filter.getClient()));
		}
		if (!CheckNull.isEmpty(filter.getId_order())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.id_order=?", filter.getId_order()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.name=?", filter.getName()));
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(a.name) like ?", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getSgn())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.sgn=?", filter.getSgn()));
		}
		if (!CheckNull.isEmpty(filter.getBal())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.bal=?", filter.getBal()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.sign_registr=?", filter.getSign_registr()));
		}
		if (filter.getS_in() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.s_in=?", filter.getS_in()));
		}
		if (filter.getS_out() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.s_out=?", filter.getS_out()));
		}
		if (filter.getDt() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.dt=?", filter.getDt()));
		}
		if (filter.getCt() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.ct=?", filter.getCt()));
		}
		if (filter.getS_in_tmp() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.s_in_tmp=?", filter.getS_in_tmp()));
		}
		if (filter.getS_out_tmp() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.s_out_tmp=?", filter.getS_out_tmp()));
		}
		if (filter.getDt_tmp() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.dt_tmp=?", filter.getDt_tmp()));
		}
		if (filter.getCt_tmp() != null) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.ct_tmp=?", filter.getCt_tmp()));
		}
		if (!CheckNull.isEmpty(filter.getL_date())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.l_date=?", filter.getL_date()));
        	flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.l_date=?", Util.sqlDate(filter.getL_date())));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.date_open=?", filter.getDate_open()));
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.date_open=?", Util.sqlDate(filter.getDate_open())));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			//flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.date_close=?", filter.getDate_close()));
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.date_close=?", Util.sqlDate(filter.getDate_close())));
		}
		if (!CheckNull.isEmpty(filter.getAcc_group_id())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.acc_group_id=?", filter.getAcc_group_id()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.state=?", filter.getState()));
		}

		flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	// самостоятельный вызов нигде не используется,
	// поэтому count беру вместе с getList
	@Override
	public int getCount() {
		return count;
	}

	private void getCountWithConnection(Connection c) {
		List<FilterField> flFields = getFilterFields();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM Account a");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());

		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
	}

	@Override
	public List<Account> getList() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Account> list = new ArrayList<Account>();
		int params;
		BigDecimal hundred = new BigDecimal(100);
		List<FilterField> flFields = getFilterFields();

		StringBuffer sql = new StringBuffer(msql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			//for (params = 0; params < flFields.size(); params++) {
			//	ps.setObject(params + 1, flFields.get(params).getColobject());
			//}
            for (params = 0; params < flFields.size(); params++) {
                Object obj = flFields.get(params).getColobject();
                if (obj instanceof java.util.Date) {
                    ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
                    continue;
                }
                if (obj instanceof String) {
                    //ps.setString(params + 1, ((String) obj).toUpperCase());
                	ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
                    continue;
                }
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("client"),
						rs.getString("name"),
						// rs.getBigDecimal("s_in").divide(hundred),
						// rs.getBigDecimal("s_out").divide(hundred),
						// rs.getBigDecimal("dt").divide(hundred),
						// rs.getBigDecimal("ct").divide(hundred),
						rs.getBigDecimal("s_in"), rs.getBigDecimal("s_out"), rs.getBigDecimal("dt"),
						rs.getBigDecimal("ct"), rs.getDate("l_date"), rs.getInt("state")));
			}
			getCountWithConnection(c);
		} catch (SQLException e) {
			logger.error(e.getStackTrace());

		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		return list;
	}

	@Override
	public List<Account> getListWithPaging(int pageIndex, int pageSize) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Account> list = new ArrayList<Account>();
		// int v_lowerbound = pageIndex + 1;
		// int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		BigDecimal hundred = new BigDecimal(100);
		List<FilterField> flFields = getFilterFields();

		StringBuffer sql = new StringBuffer(msql);
		// sql.append(psql1);
		// sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		// sql.append(psql2);

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			// params++;
			// ps.setInt(params++, v_upperbound);
			// ps.setInt(params++, v_lowerbound);

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("client"),
						rs.getString("name"), rs.getBigDecimal("s_in").divide(hundred),
						rs.getBigDecimal("s_out").divide(hundred), rs.getBigDecimal("dt").divide(hundred),
						rs.getBigDecimal("ct").divide(hundred), rs.getDate("l_date"), rs.getInt("state")));
			}
			getCountWithConnection(c);
		} catch (SQLException e) {
			logger.error(e.getStackTrace());

		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		return list;
	}

	@Override
	public Account getItemByStringId(String branch, String accId) {
		Account acc = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(
					"SELECT branch,id,acc_bal,currency,client,id_order,name,sgn,bal,sign_registr,info.GetSumScale(s_in, CURRENCY) s_in,info.GetSumScale(s_out, CURRENCY) s_out,info.GetSumScale(dt, CURRENCY) dt,info.GetSumScale(ct, CURRENCY) ct,info.GetSumScale(s_in_tmp, CURRENCY) s_in_tmp,info.GetSumScale(s_out_tmp, CURRENCY) s_out_tmp,info.GetSumScale(dt_tmp, CURRENCY) dt_tmp,info.GetSumScale(ct_tmp, CURRENCY) ct_tmp,l_date,date_open,date_close,acc_group_id,state FROM Account where branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, accId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				acc = new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"),
						rs.getString("currency"), rs.getString("client"), rs.getString("id_order"),
						rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"),
						rs.getBigDecimal("s_in"), rs.getBigDecimal("s_out"), rs.getBigDecimal("dt"),
						rs.getBigDecimal("ct"), rs.getBigDecimal("s_in_tmp"), rs.getBigDecimal("s_out_tmp"),
						rs.getBigDecimal("dt_tmp"), rs.getBigDecimal("ct_tmp"), rs.getDate("l_date"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getString("acc_group_id"),
						rs.getInt("state"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return acc;
	}

	@Override
	public int update(Account item) {
		return 0;
	}

	@Override
	public int remove(Account item) {
		return 0;
	}

	@Override
	public Account create(Account item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getItemByLongId(String branch, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getByClientId(Connection c, String accId) {
		return getItemByStringId(null, accId);
	}

	@Override
	public <T1 extends Account> void setFilter(T1 filter) {
		this.filter = (AccountFilter) filter;
	}

	@Override
	public Account create(Connection c, Account item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Connection c, Account item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Connection c, Account item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account getItemByLongId(Connection c, String branch, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getItemByStringId(Connection c, String branch, String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
