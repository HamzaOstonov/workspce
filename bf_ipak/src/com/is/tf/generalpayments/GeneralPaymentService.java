package com.is.tf.generalpayments;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class GeneralPaymentService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
    private static String msql ="SELECT * FROM Account ";

    public List<Account> getAccounts(String alias)  {
    	List<Account> list = new ArrayList<Account>();
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		Statement s = c.createStatement();
    		ResultSet rs = s.executeQuery("SELECT * FROM Account");
    		while (rs.next()) {
    			list.add(new Account(
    					rs.getString("branch"),
    					rs.getString("id"),
    					rs.getString("acc_bal"),
    					rs.getString("currency"),
    					rs.getString("client"),
    					rs.getString("id_order"),
    					rs.getString("name"),
    					rs.getString("sgn"),
    					rs.getString("bal"),
    					rs.getInt("sign_registr"),
    					rs.getLong("s_in"),
    					rs.getLong("s_out"),
    					rs.getLong("dt"),
    					rs.getLong("ct"),
    					rs.getLong("s_in_tmp"),
    					rs.getLong("s_out_tmp"),
    					rs.getLong("dt_tmp"),
    					rs.getLong("ct_tmp"),
    					rs.getDate("l_date"),
    					rs.getDate("date_open"),
    					rs.getDate("date_close"),
    					rs.getInt("acc_group_id"),
    					rs.getInt("state")));
    		}	
    	} catch (SQLException e) {
    		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    	} finally {
    		ConnectionPool.close(c);
    	}
    	return list;
    }
    
    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            }else
            return " where ";
    }

    private static List<FilterField> getFilterFields(AccountFilter filter){
    	List<FilterField> flfields = new ArrayList<FilterField>();
    	if(!CheckNull.isEmpty(filter.getBranch())){
    		flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
    	}	
    	if(!CheckNull.isEmpty(filter.getId())){
    		flfields.add(new FilterField(getCond(flfields)+ "id like ?",filter.getId()+"%"));
    	}
    	if(!CheckNull.isEmpty(filter.getAcc_bal())){
    		flfields.add(new FilterField(getCond(flfields)+ "acc_bal=?",filter.getAcc_bal()));
    	}
    	if(!CheckNull.isEmpty(filter.getCurrency())){
    		flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
    	}
    	if(!CheckNull.isEmpty(filter.getClient())){
    		flfields.add(new FilterField(getCond(flfields)+ "client=?",filter.getClient()));
    	}
    	if(!CheckNull.isEmpty(filter.getId_order())){
    		flfields.add(new FilterField(getCond(flfields)+ "id_order=?",filter.getId_order()));
    	}
    	if(!CheckNull.isEmpty(filter.getName())){
    		flfields.add(new FilterField(getCond(flfields)+ "upper(name) like ?",filter.getName().toUpperCase()+"%"));
    	}
    	if(!CheckNull.isEmpty(filter.getSgn())){
    		flfields.add(new FilterField(getCond(flfields)+ "sgn=?",filter.getSgn()));
    	}
    	if(!CheckNull.isEmpty(filter.getBal())){
    		flfields.add(new FilterField(getCond(flfields)+ "bal=?",filter.getBal()));
    	}
    	if(!CheckNull.isEmpty(filter.getSign_registr())){
    		flfields.add(new FilterField(getCond(flfields)+ "sign_registr=?",filter.getSign_registr()));
    	}
    	if(!CheckNull.isEmpty(filter.getS_in())){
    		flfields.add(new FilterField(getCond(flfields)+ "s_in=?",filter.getS_in()));
    	}
    	if(!CheckNull.isEmpty(filter.getS_out())){
    		flfields.add(new FilterField(getCond(flfields)+ "s_out=?",filter.getS_out()));
    	}
    	if(!CheckNull.isEmpty(filter.getDt())){
    		flfields.add(new FilterField(getCond(flfields)+ "dt=?",filter.getDt()));
    	}
    	if(!CheckNull.isEmpty(filter.getCt())){
    		flfields.add(new FilterField(getCond(flfields)+ "ct=?",filter.getCt()));
    	}
    	if(!CheckNull.isEmpty(filter.getS_in_tmp())){
    		flfields.add(new FilterField(getCond(flfields)+ "s_in_tmp=?",filter.getS_in_tmp()));
    	}
    	if(!CheckNull.isEmpty(filter.getS_out_tmp())){
    		flfields.add(new FilterField(getCond(flfields)+ "s_out_tmp=?",filter.getS_out_tmp()));
    	}
    	if(!CheckNull.isEmpty(filter.getDt_tmp())){
    		flfields.add(new FilterField(getCond(flfields)+ "dt_tmp=?",filter.getDt_tmp()));
    	}
    	if(!CheckNull.isEmpty(filter.getCt_tmp())){
    		flfields.add(new FilterField(getCond(flfields)+ "ct_tmp=?",filter.getCt_tmp()));
    	}
    	if(!CheckNull.isEmpty(filter.getL_date())){
    		flfields.add(new FilterField(getCond(flfields)+ "l_date=?",filter.getL_date()));
    	}
    	if(!CheckNull.isEmpty(filter.getDate_open())){
    		flfields.add(new FilterField(getCond(flfields)+ "date_open=?",filter.getDate_open()));
    	}
    	if(!CheckNull.isEmpty(filter.getDate_close())){
    		flfields.add(new FilterField(getCond(flfields)+ "date_close=?",filter.getDate_close()));
    	}
    	if(!CheckNull.isEmpty(filter.getAcc_group_id())){
    		flfields.add(new FilterField(getCond(flfields)+ "acc_group_id=?",filter.getAcc_group_id()));
    	}
    	if(!CheckNull.isEmpty(filter.getState())){
    		flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
    	}
    	flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
    	return flfields;
    }

    public static int getCount(AccountFilter filter, String alias)  {
        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(*) from (select * from account a "+
    			   " where (a.branch, a.client) in ( "+
    			   " 		select j.branch, j.id from client_j j where j.number_tax_registration = ? and state = 2) "+
    			   " and a.state = 2) ");
        if(flFields.size()>0){
        	for(int i=0;i<flFields.size();i++){
        		sql.append(flFields.get(i).getSqlwhere());
        	}
        }
        try {
        	c = ConnectionPool.getConnection(alias);
        	PreparedStatement ps = c.prepareStatement(sql.toString());
        	ps.setString(1, filter.getInn());
    		for(int k=0;k<flFields.size();k++){
        		ps.setObject(k+2, flFields.get(k).getColobject());
        	}
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		n = rs.getInt(1);
        	}
        } catch (SQLException e) {
        	com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return n;
    }	

    public static List<Account> getAccountsFl(int pageIndex, int pageSize, AccountFilter filter, String alias)  {
    	List<Account> list = new ArrayList<Account>();
    	Connection c = null;
    	int v_lowerbound = pageIndex + 1;
    	int v_upperbound = v_lowerbound + pageSize - 1;
    	int params;
    	List<FilterField> flFields =getFilterFields(filter);
    	
    	StringBuffer sql = new StringBuffer();
    	sql.append(psql1);
    	sql.append(" select * from (select * from account a "+
    			   " where (a.branch, a.client) in ( "+
    			   " 		select j.branch, j.id from client_j j where j.number_tax_registration = ? and state = 2) "+
    			   " and a.state = 2) ");
    	if(flFields.size()>0){
    		for(int i=0;i<flFields.size();i++){
    			sql.append(flFields.get(i).getSqlwhere());
    		}
    	}
    	sql.append(" order by id ");
        sql.append(psql2);
        try {
    		c = ConnectionPool.getConnection(alias);
    		PreparedStatement ps = c.prepareStatement(sql.toString());
    		ps.setString(1, filter.getInn());
    		for(params=0;params<flFields.size();params++){
    			ps.setObject(params+2, flFields.get(params).getColobject());
    		}
    		params++;
    		params++;
    		ps.setInt(params++,v_upperbound);
    		ps.setInt(params++,v_lowerbound);
    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			list.add(new Account(
    					rs.getString("branch"),
    					rs.getString("id"),
    					rs.getString("acc_bal"),
    					rs.getString("currency"),
    					rs.getString("client"),
    					rs.getString("id_order"),
    					rs.getString("name"),
    					rs.getString("sgn"),
    					rs.getString("bal"),
    					rs.getInt("sign_registr"),
    					rs.getLong("s_in"),
    					rs.getLong("s_out"),
    					rs.getLong("dt"),
    					rs.getLong("ct"),
    					rs.getLong("s_in_tmp"),
    					rs.getLong("s_out_tmp"),
    					rs.getLong("dt_tmp"),
    					rs.getLong("ct_tmp"),
    					rs.getDate("l_date"),
    					rs.getDate("date_open"),
    					rs.getDate("date_close"),
    					rs.getInt("acc_group_id"),
    					rs.getInt("state")));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace(); com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    	} finally {
    		ConnectionPool.close(c);
    	}
    	return list;
    }
    
    public static List<General> getGeneral(String un, String pw, String alias)  {
		List<General> list = new ArrayList<General>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM (select g.*, s.name state_desc from general g, state_general s where s.deal_id = g.s_deal_id and s.id = g.state)");
			while (rs.next()) {
				list.add(new General(
						rs.getLong("id"),
						rs.getString("branch"),
						rs.getString("doc_num"),
						rs.getDate("d_date"),
						rs.getString("bank_cl"),
						rs.getString("acc_cl"),
						rs.getString("name_cl"),
						rs.getString("bank_co"),
						rs.getString("acc_co"),
						rs.getString("name_co"),
						rs.getString("purpose"),
						rs.getBigDecimal("summa").divide(new BigDecimal("100")),
						rs.getString("currency"),
						rs.getString("type_doc"),
						rs.getLong("s_deal_id"),
						rs.getDate("v_date"),
						rs.getString("pdc"),
						rs.getString("cash_code"),
						rs.getLong("state"),
						rs.getLong("parent_group_id"),
						rs.getLong("parent_id"),
						rs.getLong("child_id"),
						rs.getLong("kod_err"),
						rs.getString("file_name"),
						rs.getLong("err_general"),
						rs.getLong("emp_id"),
						rs.getLong("id_transh"),
						rs.getLong("id_transh_purp"),
						rs.getDate("val_date"),
						rs.getString("state_desc")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().equals(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
    
    private static List<FilterField> getFilterFields(GeneralFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getId())){
			flfields.add(new FilterField(getCond(flfields)+ "id = ?", filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getBranch())){
			flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
		}
		if(!CheckNull.isEmpty(filter.getDoc_num())){
			flfields.add(new FilterField(getCond(flfields)+ "doc_num = ?", filter.getDoc_num()));
		}
		if(!CheckNull.isEmpty(filter.getD_date())){
			flfields.add(new FilterField(getCond(flfields)+ "d_date = ?", filter.getD_date()));
		}
		if(!CheckNull.isEmpty(filter.getBank_cl())){
			flfields.add(new FilterField(getCond(flfields)+ "bank_cl = ?", filter.getBank_cl()));
		}
		if(!CheckNull.isEmpty(filter.getAcc_cl())){
			flfields.add(new FilterField(getCond(flfields)+ "acc_cl like ?", filter.getAcc_cl()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getName_cl())){
			flfields.add(new FilterField(getCond(flfields)+ "name_cl = ?", filter.getName_cl()));
		}
		if(!CheckNull.isEmpty(filter.getBank_co())){
			flfields.add(new FilterField(getCond(flfields)+ "bank_co = ?", filter.getBank_co()));
		}
		if(!CheckNull.isEmpty(filter.getAcc_co())){
			flfields.add(new FilterField(getCond(flfields)+ "acc_co like ?", filter.getAcc_co()+"%"));
		}
		if(!CheckNull.isEmpty(filter.getName_co())){
			flfields.add(new FilterField(getCond(flfields)+ "name_co = ?", filter.getName_co()));
		}
		if(!CheckNull.isEmpty(filter.getPurpose())){
			flfields.add(new FilterField(getCond(flfields)+ "purpose = ?", filter.getPurpose()));
		}
		if(filter.getSumma() != null && !CheckNull.isEmpty(filter.getSumma())){
			flfields.add(new FilterField(getCond(flfields)+ "summa = ?", filter.getSumma()));
		}
		if(!CheckNull.isEmpty(filter.getCurrency())){
			flfields.add(new FilterField(getCond(flfields)+ "currency = ?", filter.getCurrency()));
		}
		if(!CheckNull.isEmpty(filter.getType_doc())){
			flfields.add(new FilterField(getCond(flfields)+ "type_doc = ?", filter.getType_doc()));
		}
		if(!CheckNull.isEmpty(filter.getS_deal_id())){
			flfields.add(new FilterField(getCond(flfields)+ "s_deal_id = ?", filter.getS_deal_id()));
		}
		/*
		if(!CheckNull.isEmpty(filter.getV_date_from())){
			flfields.add(new FilterField(getCond(flfields)+ "v_date >= ?", filter.getV_date_from()));
		}
		if(!CheckNull.isEmpty(filter.getV_date_to())){
			flfields.add(new FilterField(getCond(flfields)+ "v_date <= ?", filter.getV_date_to()));
		}
		*/
		if(!CheckNull.isEmpty(filter.getPdc())){
			flfields.add(new FilterField(getCond(flfields)+ "pdc = ?", filter.getPdc()));
		}
		if(!CheckNull.isEmpty(filter.getCash_code())){
			flfields.add(new FilterField(getCond(flfields)+ "cash_code = ?", filter.getCash_code()));
		}
		if(!CheckNull.isEmpty(filter.getState())){
			flfields.add(new FilterField(getCond(flfields)+ "state = ?", filter.getState()));
		}
		if(!CheckNull.isEmpty(filter.getParent_group_id())){
			flfields.add(new FilterField(getCond(flfields)+ "parent_group_id = ?", filter.getParent_group_id()));
		}
		if(!CheckNull.isEmpty(filter.getParent_id())){
			flfields.add(new FilterField(getCond(flfields)+ "parent_id = ?", filter.getParent_id()));
		}
		if(!CheckNull.isEmpty(filter.getChild_id())){
			flfields.add(new FilterField(getCond(flfields)+ "child_id = ?", filter.getChild_id()));
		}
		if(!CheckNull.isEmpty(filter.getKod_err())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_err = ?", filter.getKod_err()));
		}
		if(!CheckNull.isEmpty(filter.getFile_name())){
			flfields.add(new FilterField(getCond(flfields)+ "file_name = ?", filter.getFile_name()));
		}
		if(!CheckNull.isEmpty(filter.getErr_general())){
			flfields.add(new FilterField(getCond(flfields)+ "err_general = ?", filter.getErr_general()));
		}
		if(!CheckNull.isEmpty(filter.getEmp_id())){
			flfields.add(new FilterField(getCond(flfields)+ "emp_id = ?", filter.getEmp_id()));
		}
		if(!CheckNull.isEmpty(filter.getId_transh())){
			flfields.add(new FilterField(getCond(flfields)+ "id_transh = ?", filter.getId_transh()));
		}
		if(!CheckNull.isEmpty(filter.getId_transh_purp())){
			flfields.add(new FilterField(getCond(flfields)+ "id_transh_purp = ?", filter.getId_transh_purp()));
		}
		if(!CheckNull.isEmpty(filter.getVal_date())){
			flfields.add(new FilterField(getCond(flfields)+ "val_date = ?", filter.getVal_date()));
		}
		if(!CheckNull.isEmpty(filter.getState_desc())){
			flfields.add(new FilterField(getCond(flfields)+ "state_desc = ?", filter.getState_desc()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(GeneralFilter filter, String alias)  {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ("+
				"SELECT gg.* FROM ( ");
		if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
			sql.append(" select  g.* "+///*+ INDEX(g, xk_gen_b_cl) */
					" from general g "+ 
					" where  "+
					" g.branch = ? "+
					" and v_date between trunc(?) and trunc(?)  "+
					" and bank_cl = ? and  acc_cl = ? and state = 3"+
					" union all "+
					" select /*+ INDEX(g, xk_gen_arh_b_d_cl) */ g.* "+ 
					" from general_arh g "+
					" where  "+
					" g.branch = ? "+
					" and v_date between trunc(?) and trunc(?)  "+
					" and bank_cl = ? and  acc_cl = ?  and state = 3");
		} else {
			sql.append(" select  g.* "+///*+ INDEX(g, xk_gen_b_cl) */
					" from general g "+ 
					" where  "+
					" g.branch = ? "+
					" and v_date between trunc(?) and trunc(?)  "+
					" and bank_co = ? and  acc_co = ?  and state = 3"+
					" union all "+
					" select /*+ INDEX(g, xk_gen_arh_b_d_cl) */ g.* "+ 
					" from general_arh g "+
					" where  "+
					" g.branch = ? "+
					" and v_date between trunc(?) and trunc(?)  "+
					" and bank_co = ? and  acc_co = ?  and state = 3");
		}	
		sql.append(" ) gg, "+
				   " (select t.branch, t.general_id, sum(t.summa_idn) summa_idn  "+
				   "  from tf_general_payments t "+
				   "  where t.branch = ? and t.account = ?  and state >= 0"+
				   "  group by t.branch, t.general_id) tf "+
				   " where tf.branch (+) = gg.branch and tf.general_id (+) = gg.id and gg.summa - (case when tf.summa_idn is null then 0 else tf.summa_idn end)> 0 "+
				   " ) ");
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			/*
			System.out.println(filter.getBranch());
			System.out.println(filter.getV_date_from());
			System.out.println(filter.getV_date_to());
			System.out.println(filter.getBank_cl());
			System.out.println(filter.getAcc_cl());
			System.out.println(filter.getBank_co());
			System.out.println(filter.getAcc_co());
			System.out.println(sql.toString());
			*/
			ps.setString(1, filter.getBranch());
			ps.setDate(2, new java.sql.Date(filter.getV_date_from().getTime()));
			ps.setDate(3, new java.sql.Date(filter.getV_date_to().getTime()));
			if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
				ps.setString(4, filter.getBank_cl());
				ps.setString(5, filter.getAcc_cl());
			} else {
				ps.setString(4, filter.getBank_co());
				ps.setString(5, filter.getAcc_co());
			}
			ps.setString(6, filter.getBranch());
			ps.setDate(7, new java.sql.Date(filter.getV_date_from().getTime()));
			ps.setDate(8, new java.sql.Date(filter.getV_date_to().getTime()));
			if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
				ps.setString(9, filter.getBank_cl());
				ps.setString(10, filter.getAcc_cl());
				ps.setString(11, filter.getBank_cl());
				ps.setString(12, filter.getAcc_cl());
				
			} else {
				ps.setString(9, filter.getBank_co());
				ps.setString(10, filter.getAcc_co());
				ps.setString(11, filter.getBank_co());
				ps.setString(12, filter.getAcc_co());
				
			}
			for(int k=0;k<flFields.size();k++){
				ps.setObject(k+13, flFields.get(k).getColobject());
				//System.out.println("k+11 = "+(k+11));
			}
			//System.out.println(sql.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().equals(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<General> getGeneralsFl(int pageIndex, int pageSize, GeneralFilter filter, String alias)  {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		List<General> list = new ArrayList<General>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append("SELECT * FROM ( "+
				"select /*+ INDEX(s, XPK_STATE_GENERAL) */ gn.*, s.name state_desc from ( "+
				"SELECT gg.*, gg.summa - (case when tf.summa_idn is null then 0 else tf.summa_idn end) summa_idn FROM ( ");
		if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
			sql.append(" select  g.* "+///*+ INDEX(g, xk_gen_b_cl) */
					   " from general g "+ 
					   " where  "+
					   " g.branch = ? "+
					   " and v_date between ? and ?  "+
					   " and bank_cl = ? and  acc_cl = ?  and state = 3"+
					   " union all "+
					   " select  g.* "+///*+ INDEX(g, xk_gen_arh_b_d_cl) */ 
					   " from general_arh g "+
					   " where  "+
					   " g.branch = ? "+
					   " and v_date between ? and ?  "+
					   " and bank_cl = ? and  acc_cl = ?  and state = 3");
		} else {
			sql.append(" select /*+ INDEX(g, xk_gen_b_cl) */ g.* "+
					   " from general g "+ 
					   " where  "+
					   " g.branch = ? "+
					   " and v_date between ? and ?  "+
					   " and bank_co = ? and  acc_co = ?  and state = 3"+
					   " union all "+
					   " select /*+ INDEX(g, xk_gen_arh_b_d_cl) */ g.* "+ 
					   " from general_arh g "+
					   " where  "+
					   " g.branch = ? "+
					   " and v_date between ? and ?  "+
					   " and bank_co = ? and  acc_co = ?  and state = 3");
		}
		sql.append(" ) gg, "+
				   " (select t.branch, t.general_id, sum(t.summa_idn) summa_idn  "+
				   "  from tf_general_payments t "+
				   "  where t.branch = ? and t.account = ? and state >= 0"+
				   "  group by t.branch, t.general_id) tf "+
				   " where tf.branch (+) = gg.branch and tf.general_id (+) = gg.id and gg.summa - (case when tf.summa_idn is null then 0 else tf.summa_idn end)> 0 "+
				   " ) gn, state_general s where s.deal_id = gn.s_deal_id and s.id = gn.state order by gn.id desc ) ");
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			ps.setString(1, filter.getBranch());
			ps.setDate(2, new java.sql.Date(filter.getV_date_from().getTime()));
			ps.setDate(3, new java.sql.Date(filter.getV_date_to().getTime()));
			if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
				ps.setString(4, filter.getBank_cl());
				ps.setString(5, filter.getAcc_cl());
			} else {
				ps.setString(4, filter.getBank_co());
				ps.setString(5, filter.getAcc_co());
			}
			ps.setString(6, filter.getBranch());
			ps.setDate(7, new java.sql.Date(filter.getV_date_from().getTime()));
			ps.setDate(8, new java.sql.Date(filter.getV_date_to().getTime()));
			if (!CheckNull.isEmpty(filter.getBank_cl()) && !CheckNull.isEmpty(filter.getAcc_cl())) {
				ps.setString(9, filter.getBank_cl());
				ps.setString(10, filter.getAcc_cl());
				ps.setString(11, filter.getBank_cl());
				ps.setString(12, filter.getAcc_cl());
				
			} else {
				ps.setString(9, filter.getBank_co());
				ps.setString(10, filter.getAcc_co());
				ps.setString(11, filter.getBank_co());
				ps.setString(12, filter.getAcc_co());
				
			}
			for(params=0;params<flFields.size();params++){
				ps.setObject(params+13, flFields.get(params).getColobject());
			}
			params = params+13;
			ps.setInt(params++,v_upperbound);
			ps.setInt(params++,v_lowerbound);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new General(
						rs.getLong("id"),
						rs.getString("branch"),
						rs.getString("doc_num"),
						rs.getDate("d_date"),
						rs.getString("bank_cl"),
						rs.getString("acc_cl"),
						rs.getString("name_cl"),
						rs.getString("bank_co"),
						rs.getString("acc_co"),
						rs.getString("name_co"),
						rs.getString("purpose"),
						rs.getBigDecimal("summa_idn").divide(new BigDecimal("100")),
						rs.getString("currency"),
						rs.getString("type_doc"),
						rs.getLong("s_deal_id"),
						rs.getDate("v_date"),
						rs.getString("pdc"),
						rs.getString("cash_code"),
						rs.getLong("state"),
						rs.getLong("parent_group_id"),
						rs.getLong("parent_id"),
						rs.getLong("child_id"),
						rs.getLong("kod_err"),
						rs.getString("file_name"),
						rs.getLong("err_general"),
						rs.getLong("emp_id"),
						rs.getLong("id_transh"),
						rs.getLong("id_transh_purp"),
						rs.getDate("val_date"),
						rs.getString("state_desc")));
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().equals(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static Date addDays(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, count);
	    return cal.getTime();
	}
    
	public static Date addMonths(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MONTH, count);
	    return cal.getTime();
	}
    
    public static List<RefData> geCurMfo(String branch) {
        return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf where smf.bank_type = (select smf1.bank_type from s_mfo smf1 where smf1.bank_id =  (select VALUE from bf_sets bs where bs.id ='HO')) and act = 'A' order by smf.bank_id", branch);
    }
    
    public static List<RefData> getMfoAll(String branch) {
        return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf where act = 'A' order by smf.bank_id", branch);
    }
    
}