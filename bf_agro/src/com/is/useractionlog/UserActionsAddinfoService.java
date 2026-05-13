package com.is.useractionlog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class UserActionsAddinfoService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM BF_USER_ACTIONS_ADDINFO ";


	public static List<UserActionsAddinfo> getUserActionsAddinfo()  {
		List<UserActionsAddinfo> list = new ArrayList<UserActionsAddinfo>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM BF_USER_ACTIONS_ADDINFO");
			while (rs.next()) {
				list.add(new UserActionsAddinfo(
						rs.getLong("id"),
						rs.getString("a_key"),
						rs.getString("a_value")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<UserActionsAddinfo> getUserActionsAddinfo(Connection c, Long id)  {
		List<UserActionsAddinfo> list = new ArrayList<UserActionsAddinfo>();
		Statement s = null;
		ResultSet rs = null;	
		try {
			s = c.createStatement();
			rs = s.executeQuery("select a.id, t.name_ru a_key, a.a_value from BF_USER_ACTIONS_ADDINFO a, BF_USER_ACT_ADDINFO_LABELS t where a.id = "+id+" and t.name_ru is not null and t.key_id = a.a_key order by t.id_order, t.key_id ");
			while (rs.next()) {
				list.add(new UserActionsAddinfo(
						rs.getLong("id"),
						rs.getString("a_key"),
						rs.getString("a_value")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {if (s != null) s.close();} catch (Exception e) {e.printStackTrace();}
			try {if (rs != null) s.close();} catch (Exception e) {e.printStackTrace();}
		}
		return list;
	}

	public static Res doAction(String un,String pw, UserActionsAddinfo useractionsaddinfo,int actionid) {
         Res res =null;
         SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
         Connection c = null;
         CallableStatement cs = null;
         CallableStatement acs = null;
         CallableStatement ccs = null;
         try {
        	 c = ConnectionPool.getConnection();
        	 cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	 acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	 ccs = c.prepareCall("{ call Param.clearparam() }");
        	 ccs.execute();
        	 ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			if(!CheckNull.isEmpty(useractionsaddinfo.getId())){
				cs.setString(1, "ID");  cs.setLong(2,useractionsaddinfo.getId()); cs.execute();
			}
			if(!CheckNull.isEmpty(useractionsaddinfo.getA_key())){
				cs.setString(1, "A_KEY");  cs.setString(2,useractionsaddinfo.getA_key()); cs.execute();
			}
			if(!CheckNull.isEmpty(useractionsaddinfo.getA_value())){
				cs.setString(1, "A_VALUE");  cs.setString(2,useractionsaddinfo.getA_value()); cs.execute();
			}

        	 acs.setInt(1, 2);
        	 acs.setInt(2, 2);
        	 acs.setInt(3,actionid);
        	 acs.execute();
        	 c.commit();
        	 ccs.execute();
        	 res = new Res(0,ccs.getString(1));
         } catch (Exception e) {
             res = new Res(-1, e.getMessage());
         } finally {
        	 ConnectionPool.close(c);
         }
         return res;
    }

	public static String doAction(String un,String pw, String branch, String id,int actionid) {
		String res ="";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
        try {
        	c = ConnectionPool.getConnection();
        	cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	ccs = c.prepareCall("{ call Param.clearparam() }");
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_USER_ACTIONS_ADDINFO WHERE branch=? and id=?");
        	ps.setString(1, branch);
        	ps.setString(2, id);
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		ccs.execute();
        		for (int i=1;  i<=rs.getMetaData().getColumnCount();i++){
        			cn = rs.getMetaData().getColumnName(i);
        			// System.out.println(cn+"  "+ rs.getMetaData().getColumnTypeName(i));
        			if( rs.getString(cn)!=null){
        				cs.setString(1, cn);
        				if (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
        					cs.setString(2,bdf.format(rs.getDate(cn)));
        				}else{
        					cs.setString(2,rs.getString(cn));
        				}
        				cs.execute();
        			}
        		}
        		acs.setInt(1, 2);
        		acs.setInt(2, 2);
        		acs.setInt(3,actionid);
        		acs.execute();
        		c.commit();
        	}
        } catch (Exception e) {
        	// e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
        	res = e.getMessage();
        } finally {
        	ConnectionPool.close(c);
        }
        return res;
	}

	private static String getCond(List<FilterField> flfields){
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}

	private static List<FilterField> getFilterFields(UserActionsAddinfoFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getId())){
			flfields.add(new FilterField(getCond(flfields)+ "id = ?", filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getA_key())){
			flfields.add(new FilterField(getCond(flfields)+ "a_key = ?", filter.getA_key()));
		}
		if(!CheckNull.isEmpty(filter.getA_value())){
			flfields.add(new FilterField(getCond(flfields)+ "a_value = ?", filter.getA_value()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(UserActionsAddinfoFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_USER_ACTIONS_ADDINFO ");
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for(int k=0;k<flFields.size();k++){
				ps.setObject(k+1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<UserActionsAddinfo> getUserActionsAddinfosFl(int pageIndex, int pageSize, UserActionsAddinfoFilter filter)  {
		List<UserActionsAddinfo> list = new ArrayList<UserActionsAddinfo>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for(params=0;params<flFields.size();params++){
				ps.setObject(params+1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++,v_upperbound);
			ps.setInt(params++,v_lowerbound);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserActionsAddinfo(
						rs.getLong("id"),
						rs.getString("a_key"),
						rs.getString("a_value")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static UserActionsAddinfo getUserActionsAddinfo(int useractionsaddinfoId) {
		UserActionsAddinfo useractionsaddinfo = new UserActionsAddinfo();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_USER_ACTIONS_ADDINFO WHERE id=?");
			ps.setLong(1, useractionsaddinfoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				useractionsaddinfo = new UserActionsAddinfo();
				useractionsaddinfo.setId(rs.getLong("id"));
				useractionsaddinfo.setA_key(rs.getString("a_key"));
				useractionsaddinfo.setA_value(rs.getString("a_value"));
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return useractionsaddinfo;
	}

	public static UserActionsAddinfo create(UserActionsAddinfo useractionsaddinfo)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_BF_USER_ACTIONS_ADDINFO.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				useractionsaddinfo.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO BF_USER_ACTIONS_ADDINFO (id, a_key, a_value) VALUES (?, ?, ?)");
				ps.setLong(1, useractionsaddinfo.getId());
				ps.setString(2, useractionsaddinfo.getA_key());
				ps.setString(3, useractionsaddinfo.getA_value());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return useractionsaddinfo;
	}

	public static void update(UserActionsAddinfo useractionsaddinfo)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_USER_ACTIONS_ADDINFO SET id = ?, a_key = ?, a_value = ?  WHERE id=?");
				ps.setLong(1, useractionsaddinfo.getId());
				ps.setString(2, useractionsaddinfo.getA_key());
				ps.setString(3, useractionsaddinfo.getA_value());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(UserActionsAddinfo useractionsaddinfo)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_USER_ACTIONS_ADDINFO WHERE id=?");
			ps.setLong(1, useractionsaddinfo.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

}
