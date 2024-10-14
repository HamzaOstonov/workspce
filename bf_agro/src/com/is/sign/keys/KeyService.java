package com.is.sign.keys;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class KeyService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM (select /*+INDEX_DESC(k XPK_BF_KEYS)*/ k.*, u.full_name||' ('||u.id||' - '||u.user_name||')' user_name  from BF_KEYS k, v_bf_bank_users u where u.branch (+)= k.branch and u.id (+)= k.emp_id) ";
	private static Logger log = ISLogger.getLogger();

	public static Key getUser_key(User user) {
		return getUser_key(user.getBranch(), user.getId(), user.getUser_name());
	}
	
	public static Key getUser_key(String branch, int user_id, String username) {
		Key user_key = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT k.*, uk.branch user_branch, uk.user_id, uk.username user_name FROM BF_USER_KEYs uk, BF_KEYS k WHERE uk.branch = ? and uk.user_id = ? and uk.username = ? and k.key_type = uk.key_type and k.key_code = uk.key_code and k.key_sn = uk.key_sn");
			ps.setString(1, branch);
			ps.setLong(2, user_id);
			ps.setString(3, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user_key = new Key();
				//user_key.setUsername(rs.getString("username"));
				user_key.setKey_type(rs.getInt("key_type"));
				user_key.setKey_code(rs.getString("key_code"));
				user_key.setKey_sn(rs.getString("key_sn"));
				user_key.setVersion(rs.getString("version"));
				user_key.setSignature_algoritm(rs.getString("signature_algoritm"));
				user_key.setKey_size(rs.getLong("key_size"));
				user_key.setIssuer(rs.getString("issuer"));
				user_key.setPkey(rs.getString("pkey"));
				user_key.setSign_failure(rs.getLong("sign_failure"));
				user_key.setKey_expired(rs.getDate("key_expired"));
				user_key.setActivate_date(rs.getDate("activate_date"));
				user_key.setDeactivate_date(rs.getDate("deactivate_date"));
				user_key.setBranch(rs.getString("branch"));
				user_key.setEmp_id(rs.getInt("emp_id"));
				user_key.setState(rs.getInt("state"));
				user_key.setUser_branch(rs.getString("user_branch"));
				user_key.setUser_id(rs.getInt("user_id"));
				user_key.setUser_name(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			try {if (ps != null) ps.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			try {if (rs != null) rs.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			ConnectionPool.close(c);
		}
		return user_key;
	}
	
	public static List<Key> getUser_key()  {
		List<Key> list = new ArrayList<Key>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM BF_KEYS");
			while (rs.next()) {
				list.add(new Key(
						rs.getInt("key_type"),
						rs.getString("key_code"),
						rs.getString("key_sn"),
						rs.getString("key_certn"),
						rs.getString("version"),
						rs.getString("signature_algoritm"),
						rs.getLong("key_size"),
						rs.getString("issuer"),
						rs.getString("pkey"),
						rs.getLong("sign_failure"),
						rs.getDate("key_expired"),
						rs.getDate("activate_date"),
						rs.getDate("deactivate_date"),
						rs.getString("branch"),
						rs.getInt("emp_id"),
						rs.getInt("state")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Res doAction(String un,String pw, Key user_key,int actionid) {
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
			/*if(!CheckNull.isEmpty(user_key.getUsername())){
				cs.setString(1, "USERNAME");  cs.setString(2,user_key.getUsername()); cs.execute();
			}*/
			if(!CheckNull.isEmpty(user_key.getKey_type())){
				cs.setString(1, "KEY_TYPE");  cs.setLong(2,user_key.getKey_type()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getKey_code())){
				cs.setString(1, "KEY_CODE");  cs.setString(2,user_key.getKey_code()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getKey_sn())){
				cs.setString(1, "KEY_SN");  cs.setString(2,user_key.getKey_sn()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getVersion())){
				cs.setString(1, "VERSION");  cs.setString(2,user_key.getVersion()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getSignature_algoritm())){
				cs.setString(1, "SIGNATURE_ALGORITM");  cs.setString(2,user_key.getSignature_algoritm()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getKey_size())){
				cs.setString(1, "KEY_SIZE");  cs.setLong(2,user_key.getKey_size()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getIssuer())){
				cs.setString(1, "ISSUER");  cs.setString(2,user_key.getIssuer()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getPkey())){
				cs.setString(1, "PKEY");  cs.setString(2,user_key.getPkey()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getSign_failure())){
				cs.setString(1, "SIGN_FAILURE");  cs.setLong(2,user_key.getSign_failure()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getKey_expired())){
				cs.setString(1, "KEY_EXPIRED");  cs.setDate(2,new java.sql.Date(user_key.getKey_expired().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getActivate_date())){
				cs.setString(1, "ACTIVATE_DATE");  cs.setDate(2,new java.sql.Date(user_key.getActivate_date().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getDeactivate_date())){
				cs.setString(1, "DEACTIVATE_DATE");  cs.setDate(2,new java.sql.Date(user_key.getDeactivate_date().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getBranch())){
				cs.setString(1, "BRANCH");  cs.setString(2,user_key.getBranch()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getEmp_id())){
				cs.setString(1, "EMP_ID");  cs.setLong(2,user_key.getEmp_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(user_key.getState())){
				cs.setString(1, "STATE");  cs.setLong(2,user_key.getState()); cs.execute();
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
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_KEYS WHERE branch=? and id=?");
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

	private static List<FilterField> getFilterFields(KeyFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		/*if(!CheckNull.isEmpty(filter.getUsername())){
			flfields.add(new FilterField(getCond(flfields)+ "username = ?", filter.getUsername()));
		}*/
		if(!CheckNull.isEmpty(filter.getKey_type())){
			flfields.add(new FilterField(getCond(flfields)+ "key_type = ?", filter.getKey_type()));
		}
		if(!CheckNull.isEmpty(filter.getKey_code())){
			flfields.add(new FilterField(getCond(flfields)+ "key_code like ?", filter.getKey_code()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getKey_sn())){
			flfields.add(new FilterField(getCond(flfields)+ "key_sn like ?", filter.getKey_sn()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getKey_certn())){
			flfields.add(new FilterField(getCond(flfields)+ "key_certn like ?", filter.getKey_certn()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getVersion())){
			flfields.add(new FilterField(getCond(flfields)+ "version = ?", filter.getVersion()));
		}
		if(!CheckNull.isEmpty(filter.getSignature_algoritm())){
			flfields.add(new FilterField(getCond(flfields)+ "signature_algoritm = ?", filter.getSignature_algoritm()));
		}
		if(!CheckNull.isEmpty(filter.getKey_size())){
			flfields.add(new FilterField(getCond(flfields)+ "key_size = ?", filter.getKey_size()));
		}
		if(!CheckNull.isEmpty(filter.getIssuer())){
			flfields.add(new FilterField(getCond(flfields)+ "issuer = ?", filter.getIssuer()));
		}
		if(!CheckNull.isEmpty(filter.getPkey())){
			flfields.add(new FilterField(getCond(flfields)+ "pkey = ?", filter.getPkey()));
		}
		if(!CheckNull.isEmpty(filter.getSign_failure())){
			flfields.add(new FilterField(getCond(flfields)+ "sign_failure = ?", filter.getSign_failure()));
		}
		if(!CheckNull.isEmpty(filter.getKey_expired())){
			flfields.add(new FilterField(getCond(flfields)+ "key_expired = ?", new java.sql.Date(filter.getKey_expired().getTime())));
		}
		if(!CheckNull.isEmpty(filter.getActivate_date())){
			flfields.add(new FilterField(getCond(flfields)+ "activate_date = ?", new java.sql.Date(filter.getActivate_date().getTime())));
		}
		if(!CheckNull.isEmpty(filter.getDeactivate_date())){
			flfields.add(new FilterField(getCond(flfields)+ "deactivate_date = ?", new java.sql.Date(filter.getDeactivate_date().getTime())));
		}
		if(!CheckNull.isEmpty(filter.getBranch())){
			flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
		}
		if(!CheckNull.isEmpty(filter.getEmp_id())){
			flfields.add(new FilterField(getCond(flfields)+ "emp_id = ?", filter.getEmp_id()));
		}
		if(!CheckNull.isEmpty(filter.getState())){
			flfields.add(new FilterField(getCond(flfields)+ "state = ?", filter.getState()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(KeyFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_KEYS ");
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

	public static List<Key> getUser_keysFl(int pageIndex, int pageSize, KeyFilter filter)  {
		List<Key> list = new ArrayList<Key>();
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
				list.add(new Key(
						//rs.getString("username"),
						rs.getInt("key_type"),
						rs.getString("key_code"),
						rs.getString("key_sn"),
						rs.getString("key_certn"),
						rs.getString("version"),
						rs.getString("signature_algoritm"),
						rs.getLong("key_size"),
						rs.getString("issuer"),
						rs.getString("pkey"),
						rs.getLong("sign_failure"),
						rs.getDate("key_expired"),
						rs.getDate("activate_date"),
						rs.getDate("deactivate_date"),
						rs.getString("branch"),
						rs.getInt("emp_id"),
						rs.getInt("state"),
						rs.getString("user_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Key getUser_key(int user_keyId) {
		Key user_key = new Key();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_KEYS WHERE id=?");
			ps.setLong(1, user_keyId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user_key = new Key();
				//user_key.setUsername(rs.getString("username"));
				user_key.setKey_type(rs.getInt("key_type"));
				user_key.setKey_code(rs.getString("key_code"));
				user_key.setKey_sn(rs.getString("key_sn"));
				user_key.setVersion(rs.getString("version"));
				user_key.setSignature_algoritm(rs.getString("signature_algoritm"));
				user_key.setKey_size(rs.getLong("key_size"));
				user_key.setIssuer(rs.getString("issuer"));
				user_key.setPkey(rs.getString("pkey"));
				user_key.setSign_failure(rs.getLong("sign_failure"));
				user_key.setKey_expired(rs.getDate("key_expired"));
				user_key.setActivate_date(rs.getDate("activate_date"));
				user_key.setDeactivate_date(rs.getDate("deactivate_date"));
				user_key.setBranch(rs.getString("branch"));
				user_key.setEmp_id(rs.getInt("emp_id"));
				user_key.setState(rs.getInt("state"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return user_key;
	}

	public static Res create(Key user_key)  {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			/*ps = c.prepareStatement("SELECT SEQ_BF_KEYS.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user_key.setId(rs.getLong("id"));
			}*/
			ps = c.prepareStatement("INSERT INTO BF_KEYS (key_type, key_code, key_sn, version, signature_algoritm, key_size, issuer, pkey, sign_failure, key_expired, activate_date, deactivate_date, branch, emp_id, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			//ps.setString(1, user_key.getUsername());
			ps.setLong(1, user_key.getKey_type());
			ps.setString(2, user_key.getKey_code());
			ps.setString(3, user_key.getKey_sn());
			ps.setString(4, user_key.getVersion());
			ps.setString(5, user_key.getSignature_algoritm());
			ps.setLong(6, CheckNull.isEmpty(user_key.getKey_size())?0L:user_key.getKey_size());
			ps.setString(7, user_key.getIssuer());
			ps.setString(8, user_key.getPkey());
			ps.setLong(9, user_key.getSign_failure());
			ps.setDate(10, CheckNull.isEmpty(user_key.getKey_expired())?null:new java.sql.Date(user_key.getKey_expired().getTime()));
			ps.setDate(11, CheckNull.isEmpty(user_key.getActivate_date())?null:new java.sql.Date(user_key.getActivate_date().getTime()));
			ps.setDate(12, CheckNull.isEmpty(user_key.getDeactivate_date())?null:new java.sql.Date(user_key.getDeactivate_date().getTime()));
			ps.setString(13, user_key.getBranch());
			ps.setLong(14, user_key.getEmp_id());
			ps.setLong(15, user_key.getState());

			if (ps.executeUpdate() == 1) {
				res = new Res(0, "Ключ зарегестрирован в системе");
				c.commit();
			} else {
				c.rollback();
				throw new Exception("Количество записей не соответствует действительному!");
			}
			
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, "При регистрации ключа произошла ошибка: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res create(List<Key> user_keys)  {
		Connection c = null;
		Res res = new Res();
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("DELETE FROM BF_KEYS");
			ps.executeUpdate();
			ps = c.prepareStatement("INSERT INTO BF_KEYS (key_type, key_code, key_sn, version, signature_algoritm, key_size, issuer, pkey, sign_failure, key_expired, activate_date, deactivate_date, branch, emp_id, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			// ps.setString(1, user_key.getUsername());
			for (int i = 0; i < user_keys.size(); i++) {
				ps.setLong(1, user_keys.get(i).getKey_type());
				ps.setString(2, user_keys.get(i).getKey_code());
				ps.setString(3, user_keys.get(i).getKey_sn());
				ps.setString(4, user_keys.get(i).getVersion());
				ps.setString(5, user_keys.get(i).getSignature_algoritm());
				ps.setLong(6, CheckNull.isEmpty(user_keys.get(i).getKey_size())?0L:user_keys.get(i).getKey_size());
				ps.setString(7, user_keys.get(i).getIssuer());
				ps.setString(8, user_keys.get(i).getPkey());
				ps.setLong(9, user_keys.get(i).getSign_failure());
				ps.setDate(10, CheckNull.isEmpty(user_keys.get(i).getKey_expired())?null:new java.sql.Date(user_keys.get(i).getKey_expired().getTime()));
				ps.setDate(11, CheckNull.isEmpty(user_keys.get(i).getActivate_date())?null:new java.sql.Date(user_keys.get(i).getActivate_date().getTime()));
				ps.setDate(12, CheckNull.isEmpty(user_keys.get(i).getDeactivate_date())?null:new java.sql.Date(user_keys.get(i).getDeactivate_date().getTime()));
				ps.setString(13, user_keys.get(i).getBranch());
				ps.setLong(14, user_keys.get(i).getEmp_id());
				ps.setLong(15, user_keys.get(i).getState());
				if (ps.executeUpdate() != 1) {
					throw new Exception("Сохранение невозможно! Количество записей не соответствует действительности!");
				}
			}
			c.commit();
			res = new Res(0,"Ok");
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try{c.rollback();} catch (Exception ex) {ex.printStackTrace();}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			//if (s != null) try {s.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			if (ps != null) try {ps.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res update(Key user_key)  {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_KEYS SET version = ?, signature_algoritm = ?, key_size = ?, issuer = ?, pkey = ?, sign_failure = ?, key_expired = ?, activate_date = ?, deactivate_date = ?, branch = ?, emp_id = ?, state = ?  WHERE key_type = ? and key_code = ? and key_sn = ? ");
			//ps.setString(1, user_key.getUsername());
			ps.setString(1, user_key.getVersion());
			ps.setString(2, user_key.getSignature_algoritm());
			ps.setLong(3, CheckNull.isEmpty(user_key.getKey_size())?0L:user_key.getKey_size());
			ps.setString(4, user_key.getIssuer());
			ps.setString(5, user_key.getPkey());
			ps.setLong(6, user_key.getSign_failure());
			ps.setDate(7, CheckNull.isEmpty(user_key.getKey_expired())?null:new java.sql.Date(user_key.getKey_expired().getTime()));
			ps.setDate(8, CheckNull.isEmpty(user_key.getActivate_date())?null:new java.sql.Date(user_key.getActivate_date().getTime()));
			ps.setDate(9, CheckNull.isEmpty(user_key.getDeactivate_date())?null:new java.sql.Date(user_key.getDeactivate_date().getTime()));
			ps.setString(10, user_key.getBranch());
			ps.setLong(11, user_key.getEmp_id());
			ps.setLong(12, user_key.getState());
			ps.setLong(13, user_key.getKey_type());
			ps.setString(14, user_key.getKey_code());
			ps.setString(15, user_key.getKey_sn());
			
			if (ps.executeUpdate() == 1) {
				res = new Res(0, "Ключ успешно изменен в системе");
				c.commit();
			} else {
				c.rollback();
				throw new Exception("Количество записей не соответствует действительному!");
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); log.error(CheckNull.getPstr(ex));}
			res = new Res(1, "При изменении ключа произошла ошибка: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static void remove(Key user_key)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_KEYS WHERE id=?");
			//ps.setLong(1, user_key.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	public static Res updateMDB(String driver, String dbName, String userName, String password)  {
		Res res = new Res();
		Connection c = null;
		Connection clbc = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Key> list = new ArrayList<Key>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			driver = CheckNull.isEmpty(driver)?"net.ucanaccess.jdbc.UcanaccessDriver":driver;
            userName = CheckNull.isEmpty(userName)?"":userName;
            password = CheckNull.isEmpty(password)?"":password;
            //System.out.println("driver = "+driver+"; dbName = "+dbName);
            Class.forName(driver);
            c = DriverManager.getConnection(dbName);
            c.setReadOnly(false);
            ps = c.prepareStatement(
            		"SELECT k.*, u.ParentID, u.ChildItem, u.Name, u.EMail, u.Passport, u.PassportIssued, u.Post, u.OrgName, u.OrgAddr, "+
            		"u.RegDate, u.UsrType, u.UsrState, u.DelDate, u.Modfied, u.MdfAdminID, u.AppData, u.ReadBD, u.ModifyBD, u.Flags "+
            		"FROM (select * from TBLCryptKey k where exists ( "+
							"select 'x' from ( "+
								"select k1.UserId, max(k1.KeyId) as KeyId "+
								"FROM TBLCryptKey k1 "+
								"group by k1.UserId) k2 "+
						"where k.UserId = k2.UserId and k.KeyId = k2.KeyId))  AS k "+
					"INNER JOIN TBLCryptUser u "+
					"ON k.UserID = u.UserID");
            rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Key(
						//rs.getString("username"),
						1,//rs.getLong("key_type"),
						rs.getString("UserID"),//key_code
						rs.getString("DeviceID"),//key_sn
						"",//key_certn
						rs.getString("KeyType"),//version
						rs.getString("AlgoritmId"),//signature_algoritm
						512L,//rs.getLong("key_size"),
						"DVS",//rs.getString("issuer"),
						rs.getString("CrtfSign"),//pkey
						(df.parse(rs.getString("DateTo")).getTime() < new java.util.Date().getTime())?0L:1L,//rs.getLong("sign_failure"),
						df.parse(rs.getString("DateTo")),//key_expired
						df.parse(rs.getString("DateFrom")),//activate_date
						df.parse(rs.getString("DateTo")),//deactivate_date
						"00000",//rs.getString("branch"),
						1,//rs.getLong("emp_id"),
						(df.parse(rs.getString("DateTo")).getTime() < new java.util.Date().getTime())?0:1));// rs.getLong("state")
				//System.out.println("Column 'UserID' has value: " + rs.getString("UserID")+ "; Column 'KeyID' has value: " + rs.getString("KeyID")+";");
				if (list.size() > 100) {
					res = create(list);
					if (res.getCode() == 0) {
						list = new ArrayList<Key>();
					} else {
						throw new Exception("Обновление невозможно! "+res.getName());
					}
				}
			}
			if (list.size() > 0) {
				res = create(list);
				if (res.getCode() == 0) {
					list = new ArrayList<Key>();
				} else {
					throw new Exception("Обновление невозможно! "+res.getName());
				}
			} 
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			if (ps != null) try {ps.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			if (rs != null) try {rs.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			ConnectionPool.close(c);
		}
		return res;
		/*
		try {
		    Table table = DatabaseBuilder.open(new File("D:\\Work\\Archive\\dvsmdb1\\dvsmdb1.mdb")).getTable("TBLCryptKey");
		    for(Row row : table) {
		      System.out.println("Column 'UserID' has value: " + row.get("UserID")+ "; Column 'KeyID' has value: " + row.get("KeyID")+";");
		    }
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		}
		*/
	}
	
	public static String getKeyOut(User user, int key_type)  {
		String res = "'-----'";
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM BF_USER_KEYS where branch = ? and user_id = ? and username = ? and key_type = ?");
			ps.setString(1, user.getBranch());
			ps.setLong(2, user.getId());
			ps.setString(3, user.getUser_name());
			ps.setInt(4, key_type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res += ",'"+rs.getString("key_code")+"'";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res createUserKey(User user, Key key)  {
		Res res = new Res();
		Connection c = null;
		Connection cdvs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			if (key.getKey_type() == 1) {
				cdvs = client_bank_common.ConnectionPool.getConnection_dvs();
				ps = cdvs.prepareStatement("select count(*) cnt from user_tables t WHERE t.table_name = ? OR t.table_name = ? ");
				ps.setString(1, "IN_"+key.getKey_code().toUpperCase());
				ps.setString(2, "OUT_"+key.getKey_code().toUpperCase());
				rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getInt("cnt") != 2) {
						cs = cdvs.prepareCall("{ call pkg_dvs_manager.create_in_out(?) }");
						cs.setString(1, key.getKey_code().toUpperCase());
						cs.execute();
					}
				}
			}
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("DELETE FROM BF_USER_KEYS where branch = ? and user_id = ? and username = ? and key_type = ? ");
			ps.setString(1, user.getBranch());
			ps.setLong(2, user.getId());
			ps.setString(3, user.getUser_name());
			ps.setLong(4, key.getKey_type());
			ps.executeUpdate();
			
			ps = c.prepareStatement("INSERT INTO BF_USER_KEYS (branch,user_id,username,key_type,key_code,key_sn,check_key,state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, user.getBranch());
			ps.setLong(2, user.getId());
			ps.setString(3, user.getUser_name());
			ps.setLong(4, key.getKey_type());
			ps.setString(5, key.getKey_code());
			ps.setString(6, key.getKey_sn());
			ps.setLong(7, 0L);
			ps.setLong(8, 1L);
			if (ps.executeUpdate() == 1) {
				c.commit();
				res = new Res(0, "Ok");
			} else {
				throw new Exception("Сохранение невозможно! Количество записей не соответствует действительности!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try{c.rollback();} catch (Exception ex) {ex.printStackTrace();}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			if (ps != null) try {ps.close();} catch (Exception e) {e.printStackTrace();}
			if (cs != null) try {cs.close();} catch (Exception e) {e.printStackTrace();}
			if (rs != null) try {rs.close();} catch (Exception e) {e.printStackTrace();}
			ConnectionPool.close(c);
			if (cdvs != null) ConnectionPool.close(cdvs);
		}
		return res;
	}
	
	public static Res removeUserKey(User user, Key key)  {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("DELETE FROM BF_USER_KEYS where branch = ? and user_id = ? and username = ? and key_type = ? ");
			ps.setString(1, user.getBranch());
			ps.setLong(2, user.getId());
			ps.setString(3, user.getUser_name());
			ps.setLong(4, key.getKey_type());
			ps.executeUpdate();
			c.commit();
			res = new Res(0, "Ok");
		} catch (Exception e) {
			e.printStackTrace();
			try{c.rollback();} catch (Exception ex) {ex.printStackTrace();}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			if (ps != null) try {ps.close();} catch (Exception e) {e.printStackTrace();}
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static List<RefData> getKeyTypes(String alias) {
	    return RefDataService.getRefData("select id data, description||' ('||id||')' label from ss_bf_key_types where id in (select value from bf_sets where id = 'SIGN_TYPE')", alias);
	}
	
	public static List<RefData> getKeyStates(/*String alias*/) {
		List<RefData> list = new ArrayList<RefData>();
		list.add(new RefData("0", "Неактивен"));
		list.add(new RefData("1", "Активен"));
		return list;//RefDataService.getRefData("select 0 data, 'Неактивен' label from dual union all select 1 data, 'Aктивен' label from dual", alias);
	}
	
	public static List<RefData> getKeySignStates(/*String alias*/) {
		List<RefData> list = new ArrayList<RefData>();
		list.add(new RefData("0", "Подпись забракована"));
		list.add(new RefData("1", "Подпись валидна"));
		return list;//RefDataService.getRefData("select 0 data, 'Неактивен' label from dual union all select 1 data, 'Aктивен' label from dual", alias);
	}
	
	public static List<RefData> getCurMfo(String alias) {
	    return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf where smf.bank_type = (select VALUE from bf_sets bs where bs.id ='BANK_TYPE')", alias);
	}
	
	
	
	public static void clearForm(Object comp) {
		Constraint ct;
		try{
			if (comp instanceof org.zkoss.zul.Textbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Textbox) comp).getId()+"; org.zkoss.zul.Textbox");
				ct =((org.zkoss.zul.Textbox)comp).getConstraint();
				((org.zkoss.zul.Textbox)comp).setConstraint("");
				((org.zkoss.zul.Textbox)comp).setValue(null);
				((org.zkoss.zul.Textbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Intbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Intbox) comp).getId()+"; org.zkoss.zul.Intbox");
				ct =((org.zkoss.zul.Intbox)comp).getConstraint();
				((org.zkoss.zul.Intbox)comp).setConstraint("");
				((org.zkoss.zul.Intbox)comp).setValue(null);
				((org.zkoss.zul.Intbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Longbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Longbox) comp).getId()+"; org.zkoss.zul.Longbox");
				ct =((org.zkoss.zul.Longbox)comp).getConstraint();
				((org.zkoss.zul.Longbox)comp).setConstraint("");
				((org.zkoss.zul.Longbox)comp).setValue(null);
				((org.zkoss.zul.Longbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Decimalbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Decimalbox) comp).getId()+"; org.zkoss.zul.Decimalbox");
				ct =((org.zkoss.zul.Decimalbox)comp).getConstraint();
				((org.zkoss.zul.Decimalbox)comp).setConstraint("");
				((org.zkoss.zul.Decimalbox)comp).setValue(BigDecimal.ZERO);
				((org.zkoss.zul.Decimalbox)comp).setRawValue(null);
				((org.zkoss.zul.Decimalbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Datebox) {
				//System.out.println("Name :" +((org.zkoss.zul.Datebox) comp).getId()+"; org.zkoss.zul.Datebox");
				//((Datebox)ie).setValue(null);
				ct =((Datebox)comp).getConstraint();
				((org.zkoss.zul.Datebox)comp).setConstraint("");
				((org.zkoss.zul.Datebox)comp).setText(null);
				((org.zkoss.zul.Datebox)comp).setConstraint(ct);
			} else if (comp instanceof com.is.utils.RefCBox) {
				//System.out.println("Name :" +((com.is.utils.RefCBox) comp).getId()+"; com.is.utils.RefCBox");
				ct =((com.is.utils.RefCBox)comp).getConstraint();
				((com.is.utils.RefCBox)comp).setConstraint("");
				((com.is.utils.RefCBox)comp).setValue(null);
				((com.is.utils.RefCBox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Div) {
				for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Hbox) {
				for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Vbox) {
				for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Grid) {
				for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Row) {
				for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Cell) {
				for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Groupbox) {
				for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
					clearForm(obj);
				};
			}
		}catch(Exception e){
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		}
    }
	
	public static String lvalue(String val, List<RefData> dp) {
    	String res = "";
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getData())) {
	                res = dp.get(i).getLabel();
	    }    }    }
	    return res;
	}
}