package com.is.bpri.user_actions;

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

public class User_actionsService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "Select * from (select log.*,act.name act_name from bpr_user_actions_log log,bpr_user_actions act where log.act_type=act.id order by log.id desc)";
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
	
	private static List<FilterField> getFilterFields(User_actionsFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(filter.getAction_date()!=null){
			flfields.add(new FilterField(getCond(flfields) + "action_date=to_date(?,'dd.MM.yyyy')", filter.getAction_date()));
		}
		if(filter.getAct_type()!=null){
			flfields.add(new FilterField(getCond(flfields) + "act_type=?", filter.getAct_type()));
		}
		if(filter.getClient_id()!=null){
			flfields.add(new FilterField(getCond(flfields) + "client_id=?",filter.getClient_id()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}
	
	public static int getCount(User_actionsFilter filter, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_user_actions_log ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
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
	
	public static List<User_actions> getBproductsFl(int pageIndex, int pageSize,
			User_actionsFilter filter, String alias) {
		List<User_actions> list = new ArrayList<User_actions>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				System.out.println((params + 1)+" "+ flFields.get(params).getColobject());
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			ISLogger.getLogger().error("Тут селект "+sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new User_actions(
						rs.getInt("id"),
							rs.getInt("user_id"),
								rs.getString("user_name"),
									rs.getDate("action_date"),
										rs.getInt("act_type"),
											rs.getInt("change_id"),
												rs.getString("act_name"),
													rs.getString("client_id")));
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
	
	public static List<RefData> getUser_actions(String branch) {
		return Utils.getRefData("select * from bpr_user_actions order by id",branch);
	}
}
