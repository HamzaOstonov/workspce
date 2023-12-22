package com.is.account.spec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Action;
import com.is.base.History;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;

public class SpecAccService {
	 
	private static Logger logger = Logger.getLogger(SpecAccService.class);
	
	public static List<RefData> getSpecAccTypes(Connection c){
 		return DbUtils.getRefData(c,"select id data, name label from ss_specialacc order by id");
 	}
 	public static List<History> getHistory(String clientId,String branch, String alias){
 		return DbUtils.getHistory(SqlScripts.SPECACC_HISTORY.getSql(), clientId, branch, alias);
 	}
 	//public static List<Action> getAction(String alias){
 	//	return DbUtils.getActionData("select deal_id, id, null name, null manual from v_action_specialacc", alias);
 	//}
 	
 	public static List<Action> getAction(String alias, int userId) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    List<Action> list = new ArrayList<Action>();
	    String sql;
		try {
	        c = ConnectionPool.getConnection(alias);
	        //list=DbUtils.getActionData(c, "select deal_id, id, null name, null manual from v_action_specialacc");
			sql="select deal_id, id from action_specialacc where manual=1 and id in " +
                    "(select action_id from role_actions where group_id=217 and role_id in " +
                    "(select role_id from user_roles where user_id=?))";
	        ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()){
				list.add(new Action(rs.getInt("deal_id"), rs.getInt("id"), "", 0));
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
}
