package com.is.payments.spr.s_kaznacc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

import org.apache.log4j.Logger;

public class KaznaccService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM " +
            "(select s.*, k.bank_id accbranch, k.account acc, k.inn accinn, k.name accname  " +
            "from s_spr_97 s, (select * from s_kaznspr k where rownum = 1) k where s.act != 'Z') ";
	private static Logger log = ISLogger.getLogger();
	
	public static List<Kaznacc> getKaznaccByAccount(String kod_acc, String kod_uns) {
		List<Kaznacc> kaznacclist = new ArrayList<Kaznacc>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE kod_acc=? and kod_uns = ? and act = 'A' ");
			ps.setString(1, kod_acc);
			ps.setString(2, kod_uns);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kaznacclist.add(new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act")));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacclist;
	}
	
	public static Kaznacc getKaznacc(String branch, String acc, String budgetacc) {
		Connection c = null;
		Kaznacc kaznacc = new Kaznacc();
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"SELECT * FROM (select s.*, k.bank_id accbranch, k.account acc, k.inn     accinn, k.name    accname from s_spr_97 s, (select * from s_kaznspr k where rownum = 1) k  where s.budget = ? and s.act = 'A') "+
					"where (accbranch = ? and acc = ?) "+
					"or (? is null and ? is null) "+
					"or ((?, ?) in (select bank_co, acc_co from ss_notin_spr97))");
			//PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE budget = ? and act = 'A' or ((?, ?) in (select bank_co, acc_co from ss_notin_spr97)) ");
			ps.setString(1, budgetacc);
			//ps.setString(2, acc.substring(0,5));
			//ps.setString(3, acc.substring(17,20));
			ps.setString(2, branch);
			ps.setString(3, acc);
			ps.setString(4, branch);
			ps.setString(5, acc);
			ps.setString(6, branch);
			ps.setString(7, acc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
						rs.getString("accbranch"),
						rs.getString("acc"),
						rs.getString("accinn"),
						rs.getString("accname"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacc;
	}
	
	public static Kaznacc getKaznacc(String kod_acc, String kod_uns) {
		Connection c = null;
		Kaznacc kaznacc = new Kaznacc();
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE kod_acc=? and kod_uns = ? and act = 'A' ");
			ps.setString(1, kod_acc);
			ps.setString(2, kod_uns);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacc;
	}
	
	public static Kaznacc getKaznaccName(String branch, String acc) {
		Connection c = null;
		Kaznacc kaznacc = new Kaznacc();
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM s_kaznspr sk where sk.bank_id = ? " +
                    "and sk.account = ? and sk.act = 'A' ");
			ps.setString(1, branch);
			ps.setString(2, acc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("treasure_id"),
						"",
						"",
						"",
						"",
						"",
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
						rs.getString("bank_id"),
						rs.getString("account"),
						rs.getString("inn"),
						rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacc;
	}
	
	public static Kaznacc getKaznacc(Connection c, String kod_acc, String kod_uns) {
		Kaznacc kaznacc = new Kaznacc();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE kod_acc=? and kod_uns = ? and act = 'A' ");
			ps.setString(1, kod_acc);
			ps.setString(2, kod_uns);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
		}
		return kaznacc;
	}
	
	public static List<Kaznacc> getKaznaccByBudget(String budgetacc) {
		List<Kaznacc> kaznacclist = new ArrayList<Kaznacc>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT a.*," +
                    "b.bank_id accbranch,b.account acc,b.inn accinn,b.name accname " +
                        "FROM S_SPR_97 a, " +
                    "S_KAZNSPR b WHERE a.budget=? and a.act = 'A' ");
			ps.setString(1, budgetacc);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kaznacclist.add(new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
                        rs.getString("accbranch"),
                        rs.getString("acc"),
                        rs.getString("accinn"),
                        rs.getString("accname")));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacclist;
	}
	
	public static Kaznacc getKaznaccByBudget(Connection c, String budgetacc) {
		Kaznacc kaznacc = new Kaznacc();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE budget=? and act = 'A' ");
			ps.setString(1, budgetacc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
		}
		return kaznacc;
	}
	
	public static List<Kaznacc> getKaznacc()  {
		List<Kaznacc> list = new ArrayList<Kaznacc>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM S_SPR_97");
			while (rs.next()) {
				list.add(new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Res doAction(String alias, Kaznacc kaznacc,int actionid) {
         Res res =null;
         SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
         Connection c = null;
         CallableStatement cs = null;
         CallableStatement acs = null;
         CallableStatement ccs = null;
         try {
        	 c = ConnectionPool.getConnection(alias);
             cs = c.prepareCall("{call info.init()}");
             cs.execute();
        	 cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	 acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	 ccs = c.prepareCall("{ call Param.clearparam() }");
        	 ccs.execute();
        	 ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			if(!CheckNull.isEmpty(kaznacc.getNci_id())){
				cs.setString(1, "NCI_ID");  cs.setString(2,kaznacc.getNci_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getBudget())){
				cs.setString(1, "BUDGET");  cs.setString(2,kaznacc.getBudget()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getKod_doh())){
				cs.setString(1, "KOD_DOH");  cs.setString(2,kaznacc.getKod_doh()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getKod_soato())){
				cs.setString(1, "KOD_SOATO");  cs.setString(2,kaznacc.getKod_soato()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getKod_acc())){
				cs.setString(1, "KOD_ACC");  cs.setString(2,kaznacc.getKod_acc()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getKod_uns())){
				cs.setString(1, "KOD_UNS");  cs.setString(2,kaznacc.getKod_uns()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getNamebudget())){
				cs.setString(1, "NAMEBUDGET");  cs.setString(2,kaznacc.getNamebudget()); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getDate_open())){
				cs.setString(1, "DATE_OPEN");  cs.setDate(2,new java.sql.Date(kaznacc.getDate_open().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getDate_close())){
				cs.setString(1, "DATE_CLOSE");  cs.setDate(2,new java.sql.Date(kaznacc.getDate_close().getTime())); cs.execute();
			}
			if(!CheckNull.isEmpty(kaznacc.getAct())){
				cs.setString(1, "ACT");  cs.setString(2,kaznacc.getAct()); cs.execute();
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

	public static String doAction(String alias, String branch, String id,int actionid) {
		String res ="";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
        try {
        	c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall("{call info.init()}");
            cs.execute();
        	cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	ccs = c.prepareCall("{ call Param.clearparam() }");
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE branch=? and id=?");
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

	private static List<FilterField> getFilterFields(KaznaccFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getNci_id())){
			flfields.add(new FilterField(getCond(flfields)+ "nci_id = ?", filter.getNci_id()));
		}
		if(!CheckNull.isEmpty(filter.getBudget())){
			flfields.add(new FilterField(getCond(flfields)+ "budget like ?", filter.getBudget()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getKod_doh())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_doh = ?", filter.getKod_doh()));
		}
		if(!CheckNull.isEmpty(filter.getKod_soato())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_soato = ?", filter.getKod_soato()));
		}
		if(!CheckNull.isEmpty(filter.getKod_acc())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_acc like ?", filter.getKod_acc()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getKod_uns())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_uns like ?", filter.getKod_uns()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getNamebudget())){
			flfields.add(new FilterField(getCond(flfields)+ "namebudget like ?", filter.getNamebudget()+"%"));
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

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(KaznaccFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM S_SPR_97 ");
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

	public static List<Kaznacc> getKaznaccsFl(int pageIndex, int pageSize, KaznaccFilter filter)  {
		List<Kaznacc> list = new ArrayList<Kaznacc>();
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
				list.add(new Kaznacc(
						rs.getString("nci_id"),
						rs.getString("budget"),
						rs.getString("kod_doh"),
						rs.getString("kod_soato"),
						rs.getString("kod_acc"),
						rs.getString("kod_uns"),
						rs.getString("namebudget"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getString("act"),
						rs.getString("accbranch"),
						rs.getString("acc"),
						rs.getString("accinn"),
						rs.getString("accname")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Kaznacc getKaznacc(int kaznaccId) {
		Kaznacc kaznacc = new Kaznacc();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM S_SPR_97 WHERE id=?");
			ps.setLong(1, kaznaccId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kaznacc = new Kaznacc();
				kaznacc.setNci_id(rs.getString("nci_id"));
				kaznacc.setBudget(rs.getString("budget"));
				kaznacc.setKod_doh(rs.getString("kod_doh"));
				kaznacc.setKod_soato(rs.getString("kod_soato"));
				kaznacc.setKod_acc(rs.getString("kod_acc"));
				kaznacc.setKod_uns(rs.getString("kod_uns"));
				kaznacc.setNamebudget(rs.getString("namebudget"));
				kaznacc.setDate_open(rs.getDate("date_open"));
				kaznacc.setDate_close(rs.getDate("date_close"));
				kaznacc.setAct(rs.getString("act"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacc;
	}

	/*public static Kaznacc create(Kaznacc kaznacc)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_S_SPR_97.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//kaznacc.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO S_SPR_97 (nci_id, budget, kod_doh, kod_soato, kod_acc, kod_uns, namebudget, date_open, date_close, act) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, kaznacc.getNci_id());
				ps.setString(2, kaznacc.getBudget());
				ps.setString(3, kaznacc.getKod_doh());
				ps.setString(4, kaznacc.getKod_soato());
				ps.setString(5, kaznacc.getKod_acc());
				ps.setString(6, kaznacc.getKod_uns());
				ps.setString(7, kaznacc.getNamebudget());
				ps.setDate(8, new java.sql.Date(kaznacc.getDate_open().getTime()));
				ps.setDate(9, new java.sql.Date(kaznacc.getDate_close().getTime()));
				ps.setString(10, kaznacc.getAct());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return kaznacc;
	}

	public static void update(Kaznacc kaznacc)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE S_SPR_97 SET nci_id = ?, budget = ?, kod_doh = ?, kod_soato = ?, kod_acc = ?, kod_uns = ?, namebudget = ?, date_open = ?, date_close = ?, act = ?  WHERE id=?");
				ps.setString(1, kaznacc.getNci_id());
				ps.setString(2, kaznacc.getBudget());
				ps.setString(3, kaznacc.getKod_doh());
				ps.setString(4, kaznacc.getKod_soato());
				ps.setString(5, kaznacc.getKod_acc());
				ps.setString(6, kaznacc.getKod_uns());
				ps.setString(7, kaznacc.getNamebudget());
				ps.setDate(8, new java.sql.Date(kaznacc.getDate_open().getTime()));
				ps.setDate(9, new java.sql.Date(kaznacc.getDate_close().getTime()));
				ps.setString(10, kaznacc.getAct());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(Kaznacc kaznacc)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM S_SPR_97 WHERE id=?");
			//ps.setLong(1, kaznacc.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}*/
	
	public static List<RefData> getPageSizes()  {
		List<RefData> res = new ArrayList<RefData>();
		for (int i = 1; i < 11; i++) {
			res.add(new RefData(""+i, ""+i));
		} 
		return res;
	}


}
