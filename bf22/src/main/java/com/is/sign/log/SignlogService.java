package com.is.sign.log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sign.Res;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import org.apache.log4j.Logger;


public class SignlogService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM BF_SIGN_LOG ";
	private static Logger log = ISLogger.getLogger();

	public static List<Signlog> getSignlog()  {
		List<Signlog> list = new ArrayList<Signlog>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM BF_SIGN_LOG");
			while (rs.next()) {
				list.add(new Signlog(
						rs.getLong("id"),
						rs.getDate("dttime"),
						rs.getInt("group_id"),
						rs.getInt("deal_id"),
						rs.getInt("action_id"),
						rs.getLong("object_id"),
						rs.getString("sign_text"),
						rs.getString("sign_data"),
						rs.getInt("key_type"),
						rs.getString("key_code"),
						rs.getString("key_sn"),
						rs.getString("branch"),
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("scheme"),
						rs.getInt("state"),
						rs.getString("err_msg")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Res doAction(String un,String pw, Signlog signlog,int actionid) {
         Res res =null;
         SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
         Connection c = null;
         CallableStatement cs = null;
         CallableStatement acs = null;
         CallableStatement ccs = null;
         try {
        	 c = ConnectionPool.getConnection(un,pw);
        	 cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	 acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	 ccs = c.prepareCall("{ call Param.clearparam() }");
        	 ccs.execute();
        	 ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			if(!CheckNull.isEmpty(signlog.getId())){
				cs.setString(1, "ID");  cs.setLong(2,signlog.getId()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getDttime())){
				cs.setString(1, "DTTIME");  cs.setDate(2,new java.sql.Date(signlog.getDttime().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getGroup_id())){
				cs.setString(1, "GROUP_ID");  cs.setLong(2,signlog.getGroup_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getDeal_id())){
				cs.setString(1, "DEAL_ID");  cs.setLong(2,signlog.getDeal_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getAction_id())){
				cs.setString(1, "ACTION_ID");  cs.setLong(2,signlog.getAction_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getObject_id())){
				cs.setString(1, "OBJECT_ID");  cs.setLong(2,signlog.getObject_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getSign_text())){
				cs.setString(1, "SIGN_TEXT");  cs.setString(2,signlog.getSign_text()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getSign_data())){
				cs.setString(1, "SIGN_DATA");  cs.setString(2,signlog.getSign_data()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getKey_type())){
				cs.setString(1, "KEY_TYPE");  cs.setLong(2,signlog.getKey_type()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getKey_code())){
				cs.setString(1, "KEY_CODE");  cs.setString(2,signlog.getKey_code()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getKey_sn())){
				cs.setString(1, "KEY_SN");  cs.setString(2,signlog.getKey_sn()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getBranch())){
				cs.setString(1, "BRANCH");  cs.setString(2,signlog.getBranch()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getUser_id())){
				cs.setString(1, "USER_ID");  cs.setLong(2,signlog.getUser_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getUsername())){
				cs.setString(1, "USERNAME");  cs.setString(2,signlog.getUsername()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getScheme())){
				cs.setString(1, "SCHEME");  cs.setString(2,signlog.getScheme()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getState())){
				cs.setString(1, "STATE");  cs.setLong(2,signlog.getState()); cs.execute();
			}
			if(!CheckNull.isEmpty(signlog.getErr_msg())){
				cs.setString(1, "ERR_MSG");  cs.setString(2,signlog.getErr_msg()); cs.execute();
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
        	c = ConnectionPool.getConnection(un,pw);
        	cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	ccs = c.prepareCall("{ call Param.clearparam() }");
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_SIGN_LOG WHERE branch=? and id=?");
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
        	// e.printStackTrace(); log.error(CheckNull.getPstr(e));
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

	private static List<FilterField> getFilterFields(SignlogFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getId())){
			flfields.add(new FilterField(getCond(flfields)+ "id = ?", filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getDttime())){
			flfields.add(new FilterField(getCond(flfields)+ "dttime = ?", filter.getDttime()));
		}
		if(!CheckNull.isEmpty(filter.getGroup_id())){
			flfields.add(new FilterField(getCond(flfields)+ "group_id = ?", filter.getGroup_id()));
		}
		if(!CheckNull.isEmpty(filter.getDeal_id())){
			flfields.add(new FilterField(getCond(flfields)+ "deal_id = ?", filter.getDeal_id()));
		}
		if(!CheckNull.isEmpty(filter.getAction_id())){
			flfields.add(new FilterField(getCond(flfields)+ "action_id = ?", filter.getAction_id()));
		}
		if(!CheckNull.isEmpty(filter.getObject_id())){
			flfields.add(new FilterField(getCond(flfields)+ "object_id = ?", filter.getObject_id()));
		}
		if(!CheckNull.isEmpty(filter.getSign_text())){
			flfields.add(new FilterField(getCond(flfields)+ "sign_text = ?", filter.getSign_text()));
		}
		if(!CheckNull.isEmpty(filter.getSign_data())){
			flfields.add(new FilterField(getCond(flfields)+ "sign_data = ?", filter.getSign_data()));
		}
		if(!CheckNull.isEmpty(filter.getKey_type())){
			flfields.add(new FilterField(getCond(flfields)+ "key_type = ?", filter.getKey_type()));
		}
		if(!CheckNull.isEmpty(filter.getKey_code())){
			flfields.add(new FilterField(getCond(flfields)+ "key_code = ?", filter.getKey_code()));
		}
		if(!CheckNull.isEmpty(filter.getKey_sn())){
			flfields.add(new FilterField(getCond(flfields)+ "key_sn = ?", filter.getKey_sn()));
		}
		if(!CheckNull.isEmpty(filter.getBranch())){
			flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
		}
		if(!CheckNull.isEmpty(filter.getUser_id())){
			flfields.add(new FilterField(getCond(flfields)+ "user_id = ?", filter.getUser_id()));
		}
		if(!CheckNull.isEmpty(filter.getUsername())){
			flfields.add(new FilterField(getCond(flfields)+ "username = ?", filter.getUsername()));
		}
		if(!CheckNull.isEmpty(filter.getScheme())){
			flfields.add(new FilterField(getCond(flfields)+ "scheme = ?", filter.getScheme()));
		}
		if(!CheckNull.isEmpty(filter.getState())){
			flfields.add(new FilterField(getCond(flfields)+ "state = ?", filter.getState()));
		}
		if(!CheckNull.isEmpty(filter.getErr_msg())){
			flfields.add(new FilterField(getCond(flfields)+ "err_msg = ?", filter.getErr_msg()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(SignlogFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_SIGN_LOG ");
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
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<Signlog> getSignlogsFl(int pageIndex, int pageSize, SignlogFilter filter)  {
		List<Signlog> list = new ArrayList<Signlog>();
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
				list.add(new Signlog(
						rs.getLong("id"),
						rs.getDate("dttime"),
						rs.getInt("group_id"),
						rs.getInt("deal_id"),
						rs.getInt("action_id"),
						rs.getLong("object_id"),
						rs.getString("sign_text"),
						rs.getString("sign_data"),
						rs.getInt("key_type"),
						rs.getString("key_code"),
						rs.getString("key_sn"),
						rs.getString("branch"),
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("scheme"),
						rs.getInt("state"),
						rs.getString("err_msg")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Signlog getSignlog(int signlogId) {
		Signlog signlog = new Signlog();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_SIGN_LOG WHERE id=?");
			ps.setLong(1, signlogId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				signlog = new Signlog();
				signlog.setId(rs.getLong("id"));
				signlog.setDttime(rs.getDate("dttime"));
				signlog.setGroup_id(rs.getInt("group_id"));
				signlog.setDeal_id(rs.getInt("deal_id"));
				signlog.setAction_id(rs.getInt("action_id"));
				signlog.setObject_id(rs.getLong("object_id"));
				signlog.setSign_text(rs.getString("sign_text"));
				signlog.setSign_data(rs.getString("sign_data"));
				signlog.setKey_type(rs.getInt("key_type"));
				signlog.setKey_code(rs.getString("key_code"));
				signlog.setKey_sn(rs.getString("key_sn"));
				signlog.setBranch(rs.getString("branch"));
				signlog.setUser_id(rs.getInt("user_id"));
				signlog.setUsername(rs.getString("username"));
				signlog.setScheme(rs.getString("scheme"));
				signlog.setState(rs.getInt("state"));
				signlog.setErr_msg(rs.getString("err_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return signlog;
	}

	public static Res create(Signlog signlog)  {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_BF_SIGN_LOG.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				signlog.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO BF_SIGN_LOG (id, dttime, group_id, deal_id, action_id, object_id, sign_text, sign_data, key_type, key_code, key_sn, branch, user_id, username, scheme, state, err_msg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, signlog.getId());
			ps.setDate(2, new java.sql.Date(signlog.getDttime().getTime()));
			ps.setLong(3, signlog.getGroup_id());
			ps.setLong(4, signlog.getDeal_id());
			ps.setLong(5, signlog.getAction_id());
			ps.setLong(6, signlog.getObject_id());
			ps.setString(7, signlog.getSign_text());
			ps.setString(8, signlog.getSign_data());
			ps.setLong(9, signlog.getKey_type());
			ps.setString(10, signlog.getKey_code());
			ps.setString(11, signlog.getKey_sn());
			ps.setString(12, signlog.getBranch());
			ps.setLong(13, signlog.getUser_id());
			ps.setString(14, signlog.getUsername());
			ps.setString(15, signlog.getScheme());
			ps.setLong(16, signlog.getState());
			ps.setString(17, signlog.getErr_msg());
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok", signlog.getId());
			} else {
				c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res update(Signlog signlog)  {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_SIGN_LOG SET id = ?, dttime = ?, group_id = ?, deal_id = ?, action_id = ?, object_id = ?, sign_text = ?, sign_data = ?, key_type = ?, key_code = ?, key_sn = ?, branch = ?, user_id = ?, username = ?, scheme = ?, state = ?, err_msg = ?  WHERE id=?");
			ps.setLong(1, signlog.getId());
			ps.setDate(2, new java.sql.Date(signlog.getDttime().getTime()));
			ps.setLong(3, signlog.getGroup_id());
			ps.setLong(4, signlog.getDeal_id());
			ps.setLong(5, signlog.getAction_id());
			ps.setLong(6, signlog.getObject_id());
			ps.setString(7, signlog.getSign_text());
			ps.setString(8, signlog.getSign_data());
			ps.setLong(9, signlog.getKey_type());
			ps.setString(10, signlog.getKey_code());
			ps.setString(11, signlog.getKey_sn());
			ps.setString(12, signlog.getBranch());
			ps.setLong(13, signlog.getUser_id());
			ps.setString(14, signlog.getUsername());
			ps.setString(15, signlog.getScheme());
			ps.setLong(16, signlog.getState());
			ps.setString(17, signlog.getErr_msg());
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok");
			} else {
				c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res updateStateAndErr(Long sign_log_id, int group_id, int deal_id, int action_id, Long object_id, int state, String err_message) {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_SIGN_LOG SET state = ?, err_msg = ?  WHERE id = ? and group_id = ? and deal_id = ? and action_id = ? and object_id = ? and state = 0 ");
			ps.setInt(6, state);
			ps.setString(7, err_message);
			ps.setLong(1, sign_log_id);
			ps.setInt(2, group_id);
			ps.setInt(3, deal_id);
			ps.setInt(4, action_id);
			ps.setLong(5, object_id);
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok");
			} else {
				c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res updateStateAndErr(Connection c, Long sign_log_id, int group_id, int deal_id, int action_id, Long object_id, int state, String err_message) throws Exception{
		Res res = new Res();
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE BF_SIGN_LOG SET state = ?, err_msg = ? WHERE id = ? and group_id = ? and deal_id = ? and action_id = ? and object_id = ? ");
			ps.setInt(1, state);
			ps.setString(2, (err_message.length()>2000)?err_message.substring(0, 1999):err_message);
			ps.setLong(3, sign_log_id);
			ps.setInt(4, group_id);
			ps.setInt(5, deal_id);
			ps.setInt(6, action_id);
			ps.setLong(7, object_id);
			if (ps.executeUpdate() == 1) {
				//c.commit();
				res = new Res(0, "Ok");
			} else {
				//c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
			throw new Exception("Изменение невозможно! "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		} finally {
			//ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res updateStateSignErr(Long sign_log_id, int group_id, int deal_id, int action_id, Long object_id, int state, String sign_data, String err_message) {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_SIGN_LOG SET state = ?, err_msg = ?, sign_data = ?  WHERE id = ? and group_id = ? and deal_id = ? and action_id = ? and object_id = ? and state = 0 ");
			ps.setInt(1, state);
			ps.setString(2, (err_message.length()>2000)?err_message.substring(0, 1999):err_message);
			ps.setString(3, sign_data);
			ps.setLong(4, sign_log_id);
			ps.setInt(5, group_id);
			ps.setInt(6, deal_id);
			ps.setInt(7, action_id);
			ps.setLong(8, object_id);
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok");
			} else {
				c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res updateToErr(Long sign_log_id, Long object_id, int state, String err_message) {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_SIGN_LOG SET state = ?, err_msg = ?  WHERE id = ? and object_id = ?");
			ps.setInt(1, state);
			ps.setString(2, (err_message.length()>2000)?err_message.substring(0, 1999):err_message);
			ps.setLong(3, sign_log_id);
			ps.setLong(4, object_id);
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok");
			} else {
				c.rollback();
				res = new Res(1, "Количество записей не соответствует действительному!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static void remove(Signlog signlog)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_SIGN_LOG WHERE id=?");
			ps.setLong(1, signlog.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
}