package com.is.tf.generalpayments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class Tf_general_paymentService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM tf_general_payments ";


	public static List<Tf_general_payment> getTf_general_payment()  {
		List<Tf_general_payment> list = new ArrayList<Tf_general_payment>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tf_general_payments");
			while (rs.next()) {
				list.add(new Tf_general_payment(
						rs.getLong("id"),
						rs.getString("idn"),
						rs.getString("object_type"),
						rs.getString("object_id"),
						rs.getString("sub_object_id"),
						rs.getString("branch"),
						rs.getLong("general_id"),
						rs.getBigDecimal("summa"),
						rs.getBigDecimal("summa_idn"),
						rs.getString("client_id"),
						rs.getString("account"),
						rs.getString("inn"),
						rs.getLong("state")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Res doAction(String un,String pw, Tf_general_payment tf_general_payment,int actionid) {
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
			if(!CheckNull.isEmpty(tf_general_payment.getId())){
				cs.setString(1, "ID");  cs.setLong(2,tf_general_payment.getId()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getIdn())){
				cs.setString(1, "IDN");  cs.setString(2,tf_general_payment.getIdn()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getObject_type())){
				cs.setString(1, "OBJECT_TYPE");  cs.setString(2,tf_general_payment.getObject_type()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getObject_id())){
				cs.setString(1, "OBJECT_ID");  cs.setString(2,tf_general_payment.getObject_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getSub_object_id())){
				cs.setString(1, "SUB_OBJECT_ID");  cs.setString(2,tf_general_payment.getSub_object_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getBranch())){
				cs.setString(1, "BRANCH");  cs.setString(2,tf_general_payment.getBranch()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getGeneral_id())){
				cs.setString(1, "GENERAL_ID");  cs.setLong(2,tf_general_payment.getGeneral_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getSumma().doubleValue())){
				cs.setString(1, "SUMMA");  cs.setBigDecimal(2,tf_general_payment.getSumma()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getSumma_idn().doubleValue())){
				cs.setString(1, "SUMMA_IDN");  cs.setBigDecimal(2,tf_general_payment.getSumma_idn()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getClient_id())){
				cs.setString(1, "CLIENT_ID");  cs.setString(2,tf_general_payment.getClient_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getAccount())){
				cs.setString(1, "ACCOUNT");  cs.setString(2,tf_general_payment.getAccount()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getInn())){
				cs.setString(1, "INN");  cs.setString(2,tf_general_payment.getInn()); cs.execute();
			}
			if(!CheckNull.isEmpty(tf_general_payment.getState())){
				cs.setString(1, "STATE");  cs.setLong(2,tf_general_payment.getState()); cs.execute();
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
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_general_payments WHERE branch=? and id=?");
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

	private static List<FilterField> getFilterFields(Tf_general_paymentFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getId())){
			flfields.add(new FilterField(getCond(flfields)+ "id = ?", filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getIdn())){
			flfields.add(new FilterField(getCond(flfields)+ "idn = ?", filter.getIdn()));
		}
		if(!CheckNull.isEmpty(filter.getObject_type())){
			flfields.add(new FilterField(getCond(flfields)+ "object_type = ?", filter.getObject_type()));
		}
		if(!CheckNull.isEmpty(filter.getObject_id())){
			flfields.add(new FilterField(getCond(flfields)+ "object_id = ?", filter.getObject_id()));
		}
		if(!CheckNull.isEmpty(filter.getSub_object_id())){
			flfields.add(new FilterField(getCond(flfields)+ "sub_object_id = ?", filter.getSub_object_id()));
		}
		if(!CheckNull.isEmpty(filter.getBranch())){
			flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
		}
		if(!CheckNull.isEmpty(filter.getGeneral_id())){
			flfields.add(new FilterField(getCond(flfields)+ "general_id = ?", filter.getGeneral_id()));
		}
		if(!CheckNull.isEmpty(filter.getSumma())){
			flfields.add(new FilterField(getCond(flfields)+ "summa = ?", filter.getSumma()));
		}
		if(!CheckNull.isEmpty(filter.getSumma_idn())){
			flfields.add(new FilterField(getCond(flfields)+ "summa_idn = ?", filter.getSumma_idn()));
		}
		if(!CheckNull.isEmpty(filter.getClient_id())){
			flfields.add(new FilterField(getCond(flfields)+ "client_id = ?", filter.getClient_id()));
		}
		if(!CheckNull.isEmpty(filter.getAccount())){
			flfields.add(new FilterField(getCond(flfields)+ "account = ?", filter.getAccount()));
		}
		if(!CheckNull.isEmpty(filter.getInn())){
			flfields.add(new FilterField(getCond(flfields)+ "inn = ?", filter.getInn()));
		}
		if(!CheckNull.isEmpty(filter.getState())){
			flfields.add(new FilterField(getCond(flfields)+ "state = ?", filter.getState()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(Tf_general_paymentFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM tf_general_payments ");
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

	public static List<Tf_general_payment> getTf_general_paymentsFl(int pageIndex, int pageSize, Tf_general_paymentFilter filter)  {
		List<Tf_general_payment> list = new ArrayList<Tf_general_payment>();
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
				list.add(new Tf_general_payment(
						rs.getLong("id"),
						rs.getString("idn"),
						rs.getString("object_type"),
						rs.getString("object_id"),
						rs.getString("sub_object_id"),
						rs.getString("branch"),
						rs.getLong("general_id"),
						rs.getBigDecimal("summa"),
						rs.getBigDecimal("summa_idn"),
						rs.getString("client_id"),
						rs.getString("account"),
						rs.getString("inn"),
						rs.getLong("state")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Tf_general_payment getTf_general_payment(int tf_general_paymentId) {
		Tf_general_payment tf_general_payment = new Tf_general_payment();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_general_payments WHERE id=?");
			ps.setLong(1, tf_general_paymentId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tf_general_payment = new Tf_general_payment();
				tf_general_payment.setId(rs.getLong("id"));
				tf_general_payment.setIdn(rs.getString("idn"));
				tf_general_payment.setObject_type(rs.getString("object_type"));
				tf_general_payment.setObject_id(rs.getString("object_id"));
				tf_general_payment.setSub_object_id(rs.getString("sub_object_id"));
				tf_general_payment.setBranch(rs.getString("branch"));
				tf_general_payment.setGeneral_id(rs.getLong("general_id"));
				tf_general_payment.setSumma(rs.getBigDecimal("summa"));
				tf_general_payment.setSumma_idn(rs.getBigDecimal("summa_idn"));
				tf_general_payment.setClient_id(rs.getString("client_id"));
				tf_general_payment.setAccount(rs.getString("account"));
				tf_general_payment.setInn(rs.getString("inn"));
				tf_general_payment.setState(rs.getLong("state"));
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return tf_general_payment;
	}

	public static Res create(Tf_general_payment tf_general_payment)  {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_tf_general_payments.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tf_general_payment.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO tf_general_payments (id, idn, object_type, object_id, sub_object_id, branch, general_id, summa, summa_idn, client_id, account, inn, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, tf_general_payment.getId());
			ps.setString(2, tf_general_payment.getIdn());
			ps.setString(3, tf_general_payment.getObject_type());
			ps.setString(4, tf_general_payment.getObject_id());
			ps.setString(5, tf_general_payment.getSub_object_id());
			ps.setString(6, tf_general_payment.getBranch());
			ps.setLong(7, tf_general_payment.getGeneral_id());
			ps.setBigDecimal(8, tf_general_payment.getSumma());
			ps.setBigDecimal(9, tf_general_payment.getSumma_idn());
			ps.setString(10, tf_general_payment.getClient_id());
			ps.setString(11, tf_general_payment.getAccount());
			ps.setString(12, tf_general_payment.getInn());
			ps.setLong(13, tf_general_payment.getState());
			int r = ps.executeUpdate();
			if (r == 1) {
				c.commit();
				res = new Res(0, tf_general_payment.getId()+"");
			} else {
				c.rollback();
				res = new Res(1, "“очна€ выборка не соответствует действительному ("+r+")");
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			try {c.rollback();} catch (Exception ex) {ex.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(ex));}
			res = new Res(1, CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static void update(Tf_general_payment tf_general_payment)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_general_payments SET id = ?, idn = ?, object_type = ?, object_id = ?, sub_object_id = ?, branch = ?, general_id = ?, summa = ?, summa_idn = ?, client_id = ?, account = ?, inn = ?, state = ?  WHERE id=?");
			ps.setLong(1, tf_general_payment.getId());
			ps.setString(2, tf_general_payment.getIdn());
			ps.setString(3, tf_general_payment.getObject_type());
			ps.setString(4, tf_general_payment.getObject_id());
			ps.setString(5, tf_general_payment.getSub_object_id());
			ps.setString(6, tf_general_payment.getBranch());
			ps.setLong(7, tf_general_payment.getGeneral_id());
			ps.setBigDecimal(8, tf_general_payment.getSumma());
			ps.setBigDecimal(9, tf_general_payment.getSumma_idn());
			ps.setString(10, tf_general_payment.getClient_id());
			ps.setString(11, tf_general_payment.getAccount());
			ps.setString(12, tf_general_payment.getInn());
			ps.setLong(13, tf_general_payment.getState());
			ps.setLong(14, tf_general_payment.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(Tf_general_payment tf_general_payment)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_general_payments WHERE id=?");
			ps.setLong(1, tf_general_payment.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

}