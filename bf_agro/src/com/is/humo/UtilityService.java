package com.is.humo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.clienta.SqlScripts;
import com.is.clienta.utils.DbUtils;
import com.is.utils.CheckNull;

public class UtilityService {
	private String alias;
	private String branch;
	
	private static Logger logger = Logger.getLogger(UtilityService.class);
	
	public UtilityService(String alias) {
		this.alias = alias;
	}
	
	public static UtilityService getInstance(String alias) {
		return new UtilityService(alias);
	}
	
	public boolean isMfo(String mfo) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if(alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			ps = c.prepareStatement("select count(*) from ss_dblink_branch where branch=?");
			ps.setString(1, mfo);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);;
		}
		
		return count > 0;
	}
	
	public int roleProtocol(String eventid, String addRole, String removeRole) {
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			ps = c.prepareStatement("insert into SAP_ROLES_PROTOCOL(branch, id" +
															", emp_id,emp_login" +
															", event_id,add_role" +
															", remove_role) " +
												"values(info.getBranch, seq_sap_prot.nextval" +
												", info.getEmpId(), info.getUserName(info.getBranch,info.getEmpId())" +
												",?,?,?)");
			ps.setString(1, eventid);
			ps.setString(2, addRole);
			ps.setString(3, removeRole);
			
			count = ps.executeUpdate();
			c.commit();
			
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(cs);
		}
		return count;
	}
	
}
