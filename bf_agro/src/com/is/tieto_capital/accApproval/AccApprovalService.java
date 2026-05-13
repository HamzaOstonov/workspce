package com.is.tieto_capital.accApproval;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_capital.Constants;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

import java.sql.*;

public class AccApprovalService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM bf_tieto_acc_approve ";

	public List<AccApproval> getAccApproval() {

		List<AccApproval> list = new ArrayList<AccApproval>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM bf_tieto_acc_approve");
			while (rs.next()) {
				list.add(new AccApproval(rs.getLong("id"), rs.getString("tietoAccount"), rs.getString("branch"),
						rs.getLong("state_id"), rs.getString("fullname"), rs.getDate("birthday"),
						rs.getString("passport_serial"), rs.getString("passport_number"), rs.getString("bank_id"),
						rs.getString("tieto_id"), rs.getString("deal_group_id"),
						rs.getString("deal_id"), rs.getString("action_id"), rs.getLong("approval_type_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(AccApprovalFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "tietoAccount=?", filter.getAccount()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getState_id())) {
			flfields.add(new FilterField(getCond(flfields) + "state_id=?", filter.getState_id()));
		}
		if (!CheckNull.isEmpty(filter.getFullname())) {
			flfields.add(new FilterField(getCond(flfields) + "fullname=?", filter.getFullname()));
		}
		if (!CheckNull.isEmpty(filter.getBirthday())) {
			flfields.add(new FilterField(getCond(flfields) + "birthday=?", filter.getBirthday()));
		}
		if (!CheckNull.isEmpty(filter.getPassport_serial())) {
			flfields.add(new FilterField(getCond(flfields) + "passport_serial=?", filter.getPassport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getPassport_number())) {
			flfields.add(new FilterField(getCond(flfields) + "passport_number=?", filter.getPassport_number()));
		}
		if (!CheckNull.isEmpty(filter.getBank_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bank_id=?", filter.getBank_id()));
		}
		if (!CheckNull.isEmpty(filter.getTieto_id())) {
			flfields.add(new FilterField(getCond(flfields) + "tieto_id=?", filter.getTieto_id()));
		}
		if (!CheckNull.isEmpty(filter.getDeal_group_id())) {
			flfields.add(new FilterField(getCond(flfields) + "deal_group_id=?", filter.getDeal_group_id()));
		}
		if (!CheckNull.isEmpty(filter.getDeal_id())) {
			flfields.add(new FilterField(getCond(flfields) + "deal_id=?", filter.getDeal_id()));
		}
		if (!CheckNull.isEmpty(filter.getAction_id())) {
			flfields.add(new FilterField(getCond(flfields) + "action_id=?", filter.getAction_id()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(AccApprovalFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bf_tieto_acc_approve ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		
        if(filter.getAccess().equals("confirm")) {
        	sql.append(" AND state_id = 1 ");
        }
        else if(filter.getAccess().equals("approve")) {
        	sql.append(" AND state_id = 2 ");
        }
        
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<AccApproval> getAccApprovalsFl(int pageIndex, int pageSize, AccApprovalFilter filter) {

		List<AccApproval> list = new ArrayList<AccApproval>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		
        if(filter.getAccess().equals("confirm")) {
        	sql.append(" AND state_id = 1 ");
        }
        else if(filter.getAccess().equals("approve")) {
        	sql.append(" AND state_id = 2 ");
        }
		
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AccApproval(rs.getLong("id"), rs.getString("tietoAccount"), rs.getString("branch"),
						rs.getLong("state_id"), rs.getString("fullname"), rs.getDate("birthday"),
						rs.getString("passport_serial"), rs.getString("passport_number"), rs.getString("bank_id"),
						rs.getString("tieto_id"), rs.getString("deal_group_id"),
						rs.getString("deal_id"), rs.getString("action_id"), rs.getLong("approval_type_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static AccApproval getAccApproval(long accapprovalId) {

		AccApproval accapproval = new AccApproval();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_tieto_acc_approve WHERE id=?");
			ps.setLong(1, accapprovalId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				accapproval = new AccApproval();

				accapproval.setId(rs.getLong("id"));
				accapproval.setAccount(rs.getString("tietoAccount"));
				accapproval.setBranch(rs.getString("branch"));
				accapproval.setState_id(rs.getLong("state_id"));
				accapproval.setFullname(rs.getString("fullname"));
				accapproval.setBirthday(rs.getDate("birthday"));
				accapproval.setPassport_serial(rs.getString("passport_serial"));
				accapproval.setPassport_number(rs.getString("passport_number"));
				accapproval.setBank_id(rs.getString("bank_id"));
				accapproval.setTieto_id(rs.getString("tieto_id"));
				accapproval.setDeal_group_id(rs.getString("deal_group_id"));
				accapproval.setDeal_id(rs.getString("deal_id"));
				accapproval.setAction_id(rs.getString("action_id"));
				accapproval.setApproval_type_id(rs.getLong("approval_type_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return accapproval;
	}
	
	
	public static TiAccApprovalAction[] getActions(Long stateBeginId) {
	       
		TiAccApprovalAction[] action = new TiAccApprovalAction[Constants.SIZE_OF_ACTION_ARRAY];		
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement(
        		"SELECT a.* FROM ti_acc_approval_actions a, ti_acc_approval_trans t " +
        		"WHERE t.action_id = a.id " +
        		"AND t.state_begin_id = ?");
        	ps.setLong(1, stateBeginId);
        	
        	rs = ps.executeQuery();
        	for(int i = 0; rs.next() && (i < Constants.SIZE_OF_ACTION_ARRAY); ++i) {
        		action[i] = new TiAccApprovalAction();
        		action[i].setId(rs.getInt("id"));
        		action[i].setName(rs.getString("name"));
        		action[i].setAction_method(rs.getString("action_method"));
        	}

        } 
        catch (SQLException e) {
        	ISLogger.getLogger().error("getActions() error:\n" + CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        }
        
        return action;
	}
	
	
	
	public static String accAction(Long id, String alias, int typeApprove, int stateConfirm) {
        Connection c = null;
        PreparedStatement ps = null;
        String result = null;
        
        try {
        	c = ConnectionPool.getConnection(alias);
        	ps = c.prepareStatement("UPDATE bf_tieto_acc_approve SET approval_type_id = ?, state_id = ? WHERE id = ?");
        	ps.setInt(1, typeApprove);
        	ps.setInt(2, stateConfirm);
        	ps.setLong(3, id);
        	
        	if(ps.executeUpdate() != 1) {
        		c.rollback();
        		result = "Error: two or more records were updated";
        	}
        	else {
        		c.commit();
        		result = "Успешно";
        	}

        } 
        catch (SQLException e) {
        	ISLogger.getLogger().error(CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        	try {
				ps.close();
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
        }
		
		return result;
	}
	
}
