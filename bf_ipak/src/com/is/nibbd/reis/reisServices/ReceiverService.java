package com.is.nibbd.reis.reisServices;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.nibbd.models.Nibbd;

public class ReceiverService {
	private static ReceiverService instance;
	private Connection c;
	private boolean useOpenedCon;
	private String alias;
	private String branch;

	private ReceiverService(String alias) {
		this.alias = alias;
	}

	public static ReceiverService getInstance(String alias) {
		if (instance == null) {
			instance = new ReceiverService(alias);
		}
		return instance;
	}

	public void closeConnect() {
		ConnectionPool.close(c);
		useOpenedCon = false;
	}

	public int getCount(int state) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select count(*) from nibbd where branch=? and reis_no=? and state=? ");
			ps.setInt(1, state);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return count;
	}

	public void insertQuery(Nibbd nibbd) {
		PreparedStatement ps = null;
		CallableStatement cal = null;
		ResultSet rs = null;
		long str_no = 0;
		try {
			if (!useOpenedCon || c == null) {
				c = ConnectionPool.getConnection(alias);
			}
			cal = c.prepareCall("{ call info.init()}");
			cal.execute();

			ps = c.prepareStatement(" select seq_nibbd_str_no_" + branch + ".nextval from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				str_no = rs.getLong(1);
			}

			ps = c.prepareStatement(
					"INSERT INTO nibbd (branch, str_no, " + "query_no, query_inp, " + "parent_id, state, "
							+ "query_date,id_client ) " + "VALUES (info.getBranch,?,?,?,1,info.getDay,?)");

			ps.setDouble(1, str_no);
			ps.setDouble(2, nibbd.getQuery_num());
			ps.setString(3, nibbd.makeQuery());
			ps.setLong(4, nibbd.getNumeric_id());
			ps.setString(5, nibbd.getId_client());
			ps.executeUpdate();
			c.commit();

		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(cal);
			if (!useOpenedCon) {
				ConnectionPool.close(c);
			}
		}
	}

	/*
	 * public int updateToState2_(Nibbd nibbd){ Connection c = null;
	 * PreparedStatement ps = null; CallableStatement cs = null; int count = 0;
	 * try { c = ConnectionPool.getConnection(alias); cs = c.prepareCall(
	 * "{ call info.init()}"); cs.execute(); // for(Nibbd n: list) { ps =
	 * c.prepareStatement(
	 * "update nibbd set branch=?, reis_no=?,query_date=?,state=2 where state=1"
	 * ); ps.setString(1, branch); ps.setString(2, nibbd.getReis_num()); Date
	 * date = nibbd.getQuery_date() == null ? DbUtils.getOperDay(c) :
	 * nibbd.getQuery_date(); ps.setDate(3, new java.sql.Date(date.getTime()));
	 * ps.executeUpdate(); // ps.close(); // } c.commit(); } catch (SQLException
	 * e) { ISLogger.getLogger().error(e.getStackTrace()); e.printStackTrace();
	 * } finally { DbUtils.closeStmt(ps); DbUtils.closeStmt(cs);
	 * ConnectionPool.close(c); } return count; }
	 */

	public int updateToState3(Nibbd item) {
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init()}");
			cs.execute();
			ps = c.prepareStatement("update nibbd set query_out=?,state=3 "
					+ "where branch=? and str_no=? and query_no=? " + "and reis_no=? and query_date<=? and state=2");

			ps.setString(1, item.getQuery_out());
			ps.setString(2, branch);
			ps.setLong(3, item.getStr_num());
			ps.setInt(4, item.getQuery_num());
			ps.setString(5, item.getReis_num());
			// Date date = item.getQuery_date() == null ?
			// CommonService.getOperDay(c) : item.getQuery_date();
			ps.setDate(6, new java.sql.Date(item.getQuery_date().getTime()));
			count = ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}

		return count;
	}

}
