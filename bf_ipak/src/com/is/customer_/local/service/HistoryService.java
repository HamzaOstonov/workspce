package com.is.customer_.local.service;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.local.model.History;
import com.is.utils.CheckNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
	private SessionAttributes sessionAttributes;

	private HistoryService(SessionAttributes sessionAttributes){
		this.sessionAttributes = sessionAttributes;
	}
	
	public static HistoryService getInstance(SessionAttributes sessionAttributes){
		return new HistoryService(sessionAttributes);
	}
	
	public List<History> getHistory(Customer customer) {
		Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		List<History> list = new ArrayList<History>();
		try {
			c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            ps = c.prepareStatement("SELECT * FROM CLIENT_P_HISTORY WHERE ID = ? AND ACTION_ID > 0");
			ps.setString(1, customer.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new History(
				        rs.getDate("date_open"),
                        rs.getDate("date_close"),
                        rs.getDate("date_correct"),
						rs.getTimestamp("date_time"),
                        rs.getInt("action_id"),
                        rs.getInt("emp_id")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static String getActionName(int action_id) {
		Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		String name = null;
		try {
			c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT NAME FROM ACTION_CLIENT WHERE DEAL_ID = 2 AND ID = ?");
			ps.setInt(1, action_id);
			rs = ps.executeQuery();
			if (rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
		    DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return name;
	}

	public static String getEmpName(int emp_id, String alias) {
		Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		String name = null;
		try {
			c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("SELECT FULL_NAME FROM USERS WHERE ID = ?");
			ps.setInt(1, emp_id);
            rs = ps.executeQuery();
			if (rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
		    DbUtils.closeResultSet(rs);
		    DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return name;
	}
}
