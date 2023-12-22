package com.is.nibbd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.nibbd.models.SsNibbd;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class NibbdDictionaries {
	private static Logger logger = Logger.getLogger(NibbdDictionaries.class);
	private String alias;
	@Getter private Map<String, String> nibbdStates;
	@Getter private Map<String, String> clientCodeLetter;
	@Getter private Map<String, String> clientLetterCode;
	@Getter List<RefData> nibbdStatesList;
	@Getter List<SsNibbd> ssnibbdList;
	
	private NibbdDictionaries(String alias) {
		this.alias = alias;
		initLists();
	}
	
	public static NibbdDictionaries instance(String alias) {
		return new NibbdDictionaries(alias);
	}
	
	private void initLists() {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			clientCodeLetter = getClientCodeLetter(c, alias);
			clientLetterCode = getClientLetterCode(c, alias);
			nibbdStatesList = getNibbdStatesList(c, alias);
			nibbdStates = Util.listToMap(nibbdStatesList);
			ssnibbdList = getSSNibbd(c);
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	private List<RefData> getNibbdStatesList(Connection c, String alias){
		return c != null
				?DbUtils.getRefData(c, SqlScripts.NIBBD_STATE.getSql())
				:RefDataService.getRefData(SqlScripts.NIBBD_STATE.getSql(), alias);
	}
	private HashMap<String, String> getClientCodeLetter(Connection c, String alias){
		return c != null 
				?DbUtils.getHRefData(c, SqlScripts.CLIENT_CODE_LETTER.getSql())
				:RefDataService.getHRefData(SqlScripts.CLIENT_CODE_LETTER.getSql(), alias);
	}
	private HashMap<String, String> getClientLetterCode(Connection c, String alias){
		return c != null 
				?DbUtils.getHRefData(c, SqlScripts.CLIENT_LETTER_CODE.getSql())
				:RefDataService.getHRefData(SqlScripts.CLIENT_LETTER_CODE.getSql(), alias);
	}
	
	private List<SsNibbd> getSSNibbd(Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SsNibbd> list = new ArrayList<SsNibbd>();
		try {
			ps = c.prepareStatement("select * from ss_nibbd");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new SsNibbd(rs.getInt("QUERYNO")
									,rs.getInt("FIELDNO")
									,rs.getString("ID")
									,rs.getString("VALUE")
									,rs.getString("ACT")));	
			}
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return list;
	}
}
