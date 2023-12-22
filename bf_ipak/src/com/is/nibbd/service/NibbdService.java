package com.is.nibbd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.is.ConnectionPool;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.models.SsNibbd;

public class NibbdService {
	private String alias;
	private String un;
	private String pw;
	
	private Logger log = Logger.getLogger(NibbdService.class.getName());
	
	private NibbdService(String alias,String un,String pw) {
		this.alias = alias;
		this.un = un;
		this.pw = pw;
	}
	
	public static NibbdService getInstatance(String alias,String un,String pw) {
		return new NibbdService(alias, un, pw);
	}
	
	
	

	public void rollbackDoAction(Nibbd nibbd) {
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			
			cs = c.prepareCall(SqlScripts.SET_PARAM.getSql());
			acs = c.prepareCall(SqlScripts.DO_ACTION.getSql());
			ccs = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
			ccs.execute();
			
			cs.setString(1, "ID");cs.setString(2, nibbd.getParent_id());cs.execute();
			
			acs.setInt(1, Util.inInts(nibbd.getQuery_num(), 1,5,9) ? 1 : 2);
			acs.setInt(2, 1);
			acs.setInt(3, 26);
			acs.execute();
			c.commit();
			
		} catch (Exception e) {
			log.error(e.getStackTrace());
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(ccs);
			DbUtils.closeStmt(acs);
			DbUtils.closeStmt(cs);
		}
	}
	
}
