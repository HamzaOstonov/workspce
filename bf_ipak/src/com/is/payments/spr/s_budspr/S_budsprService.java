package com.is.payments.spr.s_budspr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

public class S_budsprService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM (select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id) ";
	private static Logger log = ISLogger.getLogger();
	
	public static S_budspr getS_budspr(String account) {
		S_budspr s_budspr = new S_budspr();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
                    "select b.*,k.bank_id,k.account bankacc, " +
                            "k.inn bankinn, " +
                            "k.name bankaccname  " +
                            "from s_budspr b, " +
                            "s_kaznspr k " +
                            "where b.account = ? and b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id ");
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s_budspr = new S_budspr();
				s_budspr.setNci_id(rs.getString("nci_id"));
				s_budspr.setTreasure_id(rs.getString("treasure_id"));
				s_budspr.setAccount(rs.getString("account"));
				s_budspr.setInn(rs.getString("inn"));
				s_budspr.setName(rs.getString("name"));
				s_budspr.setDate_open(rs.getDate("date_open"));
				s_budspr.setDate_close(rs.getDate("date_close"));
				s_budspr.setAct(rs.getString("act"));
				s_budspr.setBank_id(rs.getString("bank_id"));
				s_budspr.setBankacc(rs.getString("bankacc"));
				s_budspr.setBankinn(rs.getString("bankinn"));
				s_budspr.setBankaccname(rs.getString("bankaccname"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return s_budspr;
	}
	
	public static S_budspr getS_budspr(Connection c, String account) {
		S_budspr s_budspr = new S_budspr();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.account = ? and b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id ");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				s_budspr = new S_budspr();
				s_budspr.setNci_id(rs.getString("nci_id"));
				s_budspr.setTreasure_id(rs.getString("treasure_id"));
				s_budspr.setAccount(rs.getString("account"));
				s_budspr.setInn(rs.getString("inn"));
				s_budspr.setName(rs.getString("name"));
				s_budspr.setDate_open(rs.getDate("date_open"));
				s_budspr.setDate_close(rs.getDate("date_close"));
				s_budspr.setAct(rs.getString("act"));
				s_budspr.setBank_id(rs.getString("bank_id"));
				s_budspr.setBankacc(rs.getString("bankacc"));
				s_budspr.setBankinn(rs.getString("bankinn"));
				s_budspr.setBankaccname(rs.getString("bankaccname"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e)); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try{if (ps != null) ps.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			try{if (rs != null) rs.close();} catch (Exception e) {e.printStackTrace(); log.error(CheckNull.getPstr(e));}
			//ConnectionPool.close(c);
		}
		return s_budspr;
	}
	
	public static List<S_budspr> getS_budspr()  {
		List<S_budspr> list = new ArrayList<S_budspr>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id");
			while (rs.next()) {
				list.add(new S_budspr(
						rs.getString("nci_id"),
						rs.getString("treasure_id"),
						rs.getString("account"),
						rs.getString("inn"),
						rs.getString("name"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
						rs.getString("bank_id"),
						rs.getString("bankacc"),
						rs.getString("bankinn"),
						rs.getString("bankaccname")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
/*
	public static Res doAction(String un,String pw, S_budspr s_budspr,int actionid) {
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
			if(!CheckNull.isEmpty(s_budspr.getNci_id())){
				cs.setString(1, "NCI_ID");  cs.setString(2,s_budspr.getNci_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getTreasure_id())){
				cs.setString(1, "TREASURE_ID");  cs.setString(2,s_budspr.getTreasure_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getAccount())){
				cs.setString(1, "ACCOUNT");  cs.setString(2,s_budspr.getAccount()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getInn())){
				cs.setString(1, "INN");  cs.setString(2,s_budspr.getInn()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getName())){
				cs.setString(1, "NAME");  cs.setString(2,s_budspr.getName()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getDate_open())){
				cs.setString(1, "DATE_OPEN");  cs.setDate(2,new java.sql.Date(s_budspr.getDate_open().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getDate_close())){
				cs.setString(1, "DATE_CLOSE");  cs.setDate(2,new java.sql.Date(s_budspr.getDate_close().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getAct())){
				cs.setString(1, "ACT");  cs.setString(2,s_budspr.getAct()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getBank_id())){
				cs.setString(1, "BANK_ID");  cs.setString(2,s_budspr.getBank_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getBankacc())){
				cs.setString(1, "BANKACC");  cs.setString(2,s_budspr.getBankacc()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getBankinn())){
				cs.setString(1, "BANKINN");  cs.setString(2,s_budspr.getBankinn()); cs.execute();
			}
			if(!CheckNull.isEmpty(s_budspr.getBankaccname())){
				cs.setString(1, "BANKACCNAME");  cs.setString(2,s_budspr.getBankaccname()); cs.execute();
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
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id WHERE branch=? and id=?");
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
*/
	private static String getCond(List<FilterField> flfields){
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}

	private static List<FilterField> getFilterFields(S_budsprFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getNci_id())){
			flfields.add(new FilterField(getCond(flfields)+ "nci_id = ?", filter.getNci_id()));
		}
		if(!CheckNull.isEmpty(filter.getTreasure_id())){
			flfields.add(new FilterField(getCond(flfields)+ "treasure_id = ?", filter.getTreasure_id()));
		}
		if(!CheckNull.isEmpty(filter.getAccount())){
			flfields.add(new FilterField(getCond(flfields)+ "account like ?", filter.getAccount()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getInn())){
			flfields.add(new FilterField(getCond(flfields)+ "inn like ?", filter.getInn()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getName())){
			flfields.add(new FilterField(getCond(flfields)+ "name like ?", filter.getName()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getDate_open())){
			flfields.add(new FilterField(getCond(flfields)+ "date_open = ?", filter.getDate_open()));
		}
		if(!CheckNull.isEmpty(filter.getDate_close())){
			flfields.add(new FilterField(getCond(flfields)+ "date_close = ?", filter.getDate_close()));
		}
		if(!CheckNull.isEmpty(filter.getAct())){
			flfields.add(new FilterField(getCond(flfields)+ "act = ?", filter.getAct()));
		}
		if(!CheckNull.isEmpty(filter.getBank_id())){
			flfields.add(new FilterField(getCond(flfields)+ "bank_id = ?", filter.getBank_id()));
		}
		if(!CheckNull.isEmpty(filter.getBankacc())){
			flfields.add(new FilterField(getCond(flfields)+ "bankacc = ?", filter.getBankacc()));
		}
		if(!CheckNull.isEmpty(filter.getBankinn())){
			flfields.add(new FilterField(getCond(flfields)+ "bankinn = ?", filter.getBankinn()));
		}
		if(!CheckNull.isEmpty(filter.getBankaccname())){
			flfields.add(new FilterField(getCond(flfields)+ "bankaccname = ?", filter.getBankaccname()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(S_budsprFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM (select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id) ");
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

	public static List<S_budspr> getS_budsprsFl(int pageIndex, int pageSize, S_budsprFilter filter)  {
		List<S_budspr> list = new ArrayList<S_budspr>();
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
				list.add(new S_budspr(
						rs.getString("nci_id"),
						rs.getString("treasure_id"),
						rs.getString("account"),
						rs.getString("inn"),
						rs.getString("name"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
						rs.getString("bank_id"),
						rs.getString("bankacc"),
						rs.getString("bankinn"),
						rs.getString("bankaccname")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static S_budspr getS_budspr(int s_budsprId) {
		S_budspr s_budspr = new S_budspr();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id WHERE id=?");
			ps.setLong(1, s_budsprId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s_budspr = new S_budspr();
				s_budspr.setNci_id(rs.getString("nci_id"));
				s_budspr.setTreasure_id(rs.getString("treasure_id"));
				s_budspr.setAccount(rs.getString("account"));
				s_budspr.setInn(rs.getString("inn"));
				s_budspr.setName(rs.getString("name"));
				s_budspr.setDate_open(rs.getDate("date_open"));
				s_budspr.setDate_close(rs.getDate("date_close"));
				s_budspr.setAct(rs.getString("act"));
				s_budspr.setBank_id(rs.getString("bank_id"));
				s_budspr.setBankacc(rs.getString("bankacc"));
				s_budspr.setBankinn(rs.getString("bankinn"));
				s_budspr.setBankaccname(rs.getString("bankaccname"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return s_budspr;
	}
/*
	public static S_budspr create(S_budspr s_budspr)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s_budspr.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id (nci_id, treasure_id, account, inn, name, date_open, date_close, act, bank_id, bankacc, bankinn, bankaccname) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, s_budspr.getNci_id());
				ps.setString(2, s_budspr.getTreasure_id());
				ps.setString(3, s_budspr.getAccount());
				ps.setString(4, s_budspr.getInn());
				ps.setString(5, s_budspr.getName());
				ps.setDate(6, new java.sql.Date(s_budspr.getDate_open().getTime()));
				ps.setDate(7, new java.sql.Date(s_budspr.getDate_close().getTime()));
				ps.setString(8, s_budspr.getAct());
				ps.setString(9, s_budspr.getBank_id());
				ps.setString(10, s_budspr.getBankacc());
				ps.setString(11, s_budspr.getBankinn());
				ps.setString(12, s_budspr.getBankaccname());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return s_budspr;
	}

	public static void update(S_budspr s_budspr)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id SET nci_id = ?, treasure_id = ?, account = ?, inn = ?, name = ?, date_open = ?, date_close = ?, act = ?, bank_id = ?, bankacc = ?, bankinn = ?, bankaccname = ?  WHERE id=?");
				ps.setString(1, s_budspr.getNci_id());
				ps.setString(2, s_budspr.getTreasure_id());
				ps.setString(3, s_budspr.getAccount());
				ps.setString(4, s_budspr.getInn());
				ps.setString(5, s_budspr.getName());
				ps.setDate(6, new java.sql.Date(s_budspr.getDate_open().getTime()));
				ps.setDate(7, new java.sql.Date(s_budspr.getDate_close().getTime()));
				ps.setString(8, s_budspr.getAct());
				ps.setString(9, s_budspr.getBank_id());
				ps.setString(10, s_budspr.getBankacc());
				ps.setString(11, s_budspr.getBankinn());
				ps.setString(12, s_budspr.getBankaccname());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(S_budspr s_budspr)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM select b.*,k.bank_id,k.account bankacc, k.inn bankinn, k.name bankaccname  from s_budspr b, s_kaznspr k where b.act = 'A' and k.act = 'A' and k.treasure_id = b.treasure_id WHERE id=?");
			ps.setLong(1, s_budspr.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
*/
	public static List<RefData> getPageSizes()  {
		List<RefData> res = new ArrayList<RefData>();
		for (int i = 1; i < 11; i++) {
			res.add(new RefData(""+i, ""+i));
		} 
		return res;
	}
}
