package com.is.nibbd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.nibbd.models.Protocol;

public class ProtocolService {
	private String alias;
	private ProtocolService(String alias) {
		this.alias = alias;
	}
	
	public static ProtocolService getInstance(String alias) {
		return new ProtocolService(alias);
	}
	
	public List<Protocol> getList(Date oper_date) {
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List<Protocol> list = new ArrayList<Protocol>();
		
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			ps = c.prepareStatement("select * from nibbd_error " +
					"where e_branch=info.getBranch " +
						"and to_date(e_date_sys,'dd.mm.yyyy')=to_Date(?,'dd.mm.yyyy') " +
					"order by e_date_oper desc");
			ps.setDate(1, oper_date != null? new java.sql.Date(oper_date.getTime()):null);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Protocol(rs.getString("e_branch")
									, rs.getString("e_protocol")
									, rs.getTimestamp("e_date_oper")));
			}
			
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
		}
		return list;
	}
}
