package com.is.clients.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.clients.models.History;
import com.is.clients.models.SsSpecial;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class SpecCltService {
	
	private static Logger logger = Logger.getLogger(SpecCltService.class);

	public static List<RefData> getSpechars(String alias){
		return RefDataService.getRefData("select id data, name label from ss_specialclt order by id", alias);
	}

	public static List<History> getHistory(String clientId,String branch, String alias){
		return DbUtils.getHistory(SqlScripts.SPECHAR_HISTORY.getSql(), clientId, branch, alias);
	}
	
	public static List<SsSpecial> getSsSpecial(String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SsSpecial> list = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from ss_specialclt");
			rs = ps.executeQuery();
			list = new ArrayList<SsSpecial>();
			while (rs.next()){
				list.add(new SsSpecial(
										rs.getInt("id"),
										rs.getString("name"), 
										rs.getString("mask"),
										rs.getString("limit"),
										rs.getInt("manual")));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
//	public static void insertSapData(Connection c,SpecClt spechar){
//		PreparedStatement ps = null;
//		try {
//			ps = c.prepareStatement("INSERT INTO specialclt (branch, id_special, id_client, value, prim ) VALUES (?,?,?,?,?)");
//			ps.setString(1, spechar.getBranch());
//			ps.setString(2, spechar.getId_special());
//			ps.setString(3, spechar.getId_client());
//			ps.setString(4, spechar.getValue());
//			ps.setString(5, spechar.getPrim());
//			ps.executeUpdate();
//			c.commit();
//		} catch (SQLException e) {
//			logger.error(e.getStackTrace());
//			e.printStackTrace();
//		}
//	}
}
