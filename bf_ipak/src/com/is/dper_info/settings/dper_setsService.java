package com.is.dper_info.settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.RefData;

public class dper_setsService {
	public static List<RefData> getAllCurrency(String alias){
		return com.is.utils.RefDataService.getRefData("select kod data, kod||'  '||kod_b||'  '||namev label " +
				"from s_val", alias);
	}
	
	public static List<RefData> getAllVeoper(String alias){
		return com.is.utils.RefDataService.getRefData("select  kod_o data, name_o label from S_VEOPER S WHERE S.KOD_O BETWEEN 42201 AND 42299 AND ACT ='A' and S.KOD_O not in ("+
				" select /*+ index(sv XUK_S_VEOPER) */ dv.VALUE1 from  ss_dper_dop dv where dv.id_dper=6)", alias);
	}
    public static List<RefData> getSortStr(String alias){
		return com.is.utils.RefDataService.getRefData("select /*+ index(S_str XUK_S_STR)*/ code_str data, name label "+
    " from  S_str where act='A' and code_str not in ("+
    " select ds.id_str from dper_region_str ds )", alias);
	}
    public static List<RefData> getGroupCountries(String reg_id, String alias){
    	
		return com.is.utils.RefDataService.getRefData("select code_str data, name label from s_str ss "+
                        "where ss.sng=? order by code_str",reg_id, alias);
	}
    
    public static List<RefData> getSS_dper(String alias){
    	
		return com.is.utils.RefDataService.getRefData("Select id data, name label from ss_Dper t order by 1 ", alias);
	}
    
    public static List<RefData> getMbranches(String alias){
    	return com.is.utils.RefDataService.getRefData("select code data,code||' - '||t.name  label from ss_subsidiary t where branch='00394' and STATE='A'", alias);
    }
    
    public static List<RefData> getAccountType(String alias){
    	return com.is.utils.RefDataService.getRefData("select id data,id||'-'||value1 label from ss_dper_dop where id_dper=3 and state='A'", alias);
    }
    public static List<RefData> getKonvert(String alias){
    	return com.is.utils.RefDataService.getRefData("Select id data, id||' '||coment label from ss_dper_dop where id_dper=9", alias);
    }
    
    public static List<RefData> getTypeOper(String alias){
    	return com.is.utils.RefDataService.getRefData("select ID data ,id||'-'||VALUE1 label from ss_dper_dop where ID_DPER=4", alias);
    }
    
    public static List<RefData> getTypeAcc(String alias){
    	return com.is.utils.RefDataService.getRefData("select ID data,id||'-'||VALUE1 label from ss_dper_dop where ID_DPER=3", alias);
    }
    public static List<RefData> getTypeDoc(String alias){
    	return com.is.utils.RefDataService.getRefData("select kod_doc data,kod_doc||chr(149)||naim_doc label from v_typedoc where act='A' Union Select '66' id, '66'||chr(149)||'Дебетовый мемориальный ордер' name from dual", alias);
    }
    public static List<RefData> getCashsym(String alias){
    	return com.is.utils.RefDataService.getRefData("select  ''data , name label from ss_cash_symbols where rownum=1 union select code_symbol data , code_symbol||chr(149)||name  from ss_cash_symbols order by 1", alias);
    }
    
    public static List<RefData> getSumproc(String alias){
    	return com.is.utils.RefDataService.getRefData("select id data, id||'-'||value1 label from ss_dper_dop where id_dper=8", alias);
    }
    
    public static List<RefData> getFxdeal(String alias){
    	return com.is.utils.RefDataService.getRefData("select id data,id||'-'||name label from ss_deal_fxdoc order by 1", alias);
    }
    
    public static List<RefData> getCashsymd(String cashsymp,String val,String alias){
    	StringBuilder sql = new StringBuilder(); 
    	sql.append("select null data, '' label  from ss_cash_symbols_desc  union "+ 
               " select  t.id data, t.id||chr(149)||t.name label "+
               " from ss_cash_symbols_desc t where trim(t.cash_code)=?");
    	if(val!=null){
    		sql.append(" and trim(t.currency)=?");
    	}else {
    		sql.append(" and trim(t.currency) is null");
    	}
    	List<RefData> list = new ArrayList<RefData>();
    	Connection c = null;
    	PreparedStatement prep = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement(sql.toString());
			prep.setString(1, cashsymp);
			if(val !=null){
				prep.setString(2, val);
			}
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				list.add(new RefData(rs.getString("data"), rs.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.close(c);
		}
    	return list;
    }
    
    
   
    public static List<Ss_dper_dop> getSS_dper_dop(String id_dper,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	List<Ss_dper_dop> list = new ArrayList<Ss_dper_dop>();
    	Ss_dper_dop item;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("Select t.*,rowid from ss_Dper_dop t where t.id_dper=? order by 1,2");
			prep.setString(1, id_dper);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				item = new Ss_dper_dop();
				item.setId(rs.getInt("id"));
				item.setValue1(rs.getString("value1"));
				item.setValue2(rs.getString("value2"));
				item.setComent(rs.getString("coment"));
				item.setState(rs.getString("state"));
				item.setRowid(rs.getString("rowid"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return list;
	}
    
    public static boolean insertspr(Ss_dper_dop dop, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("insert into ss_dper_dop values(?,?,?,?,null,null,?,?)");
			prep.setInt(1, dop.getId_dper());
			prep.setInt(2, dop.getId());
			prep.setString(3, dop.getValue1());
			prep.setString(4, dop.getValue2());
			prep.setString(5, dop.getComent());
			prep.setString(6, dop.getState());
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean updatespr(Ss_dper_dop dop, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("update ss_dper_dop set id_dper=?,id=?,value1=?,value2=?,state=?,coment=? where rowid=?");
			prep.setInt(1, dop.getId_dper());
			prep.setInt(2, dop.getId());
			prep.setString(3, dop.getValue1());
			prep.setString(4, dop.getValue2());
			prep.setString(5, dop.getComent());
			prep.setString(6, dop.getState());
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
	public static boolean deletespr(String rowid, String alias){
		Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from ss_dper_dop_str where rowid=?");
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
	}
    /*********************************************************************************************
     * 
     * spravochniki
     ********************************************************************************************** */
    public static boolean removeCountry(String str_id, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from dper_region_str where id=?");
			prep.setString(1, str_id);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean insertCountry(String reg_id, String str_id, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("insert into dper_region_str values(?,?)");
			prep.setString(1, reg_id);
			prep.setString(2, str_id);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean insertGroup(String name, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("insert into DPER_REGION( select nvl(max(id),0)+1,? from DPER_REGION)");
			prep.setString(1, name);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    
    public static boolean deleteGroup(String group_id, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from DPER_REGION where id=?");
			prep.setString(1, group_id);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    /*************************************************************************************8
     * currencies & veoper
     * 
     *************************************************************************************** */
   
    
    public static boolean insert_dper_dop(int id_dper,RefData data, String alias){
    	if(id_dper!=6 || id_dper!=7){return false;}
    	Connection c= null;
    	PreparedStatement prep = null;//id_dper = 6 veoper
    	ResultSet rs = null;
    	boolean res = true;			  //id_dper = 7 currency
    	int next_id = 0;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select nvl(max(id),0)+1 val from ss_dper_dop where id_dper=?");
			prep.setInt(1, id_dper);
			rs = prep.executeQuery();
			if(rs.next()){
				next_id = rs.getInt(1);
			}
			prep.close();rs.close();
			prep = c.prepareStatement("insert into ss_dper_dop values(?,?,?,?,?,?,?,?)");
			prep.setInt(1, id_dper);
			
			if(id_dper == 7){
				prep.setInt(2, next_id);
				String[] arr = data.getLabel().split(" ");
				if(arr.length>1)prep.setString(3,arr[1]);
				if(arr.length>2)prep.setString(4,arr[2]);
			}else if(id_dper == 6){
				prep.setString(2, data.getData());
				prep.setString(3,data.getLabel());
				prep.setString(4,null);
			}
			prep.setString(5,null);
			prep.setString(6,null);
			prep.setString(7,null);
			prep.setString(8, "A");
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    } 
    public static boolean remove_dper_dop(Ss_dper_dop data, String alias){
    	Connection c= null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from ss_dper_dop where " +
	    			"id_dper = ? and id=? and value1=?");
			prep.setInt(1, data.getId_dper());
			prep.setInt(2, data.getId());
			prep.setString(3, data.getValue1());
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    
    /*************************************************************************************
     * scales
     * 
     *************************************************************************************** */
    public static List<Scales> getScales(Scales sc, String alias){
    	List<Scales> list = new ArrayList<Scales>();
    	Scales scale;
    	Connection c = null;
    	PreparedStatement prep = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("SELECT T.*,T.ROWID FROM DPER_SCALE T"+ 
							            " WHERE T.VEOPER=?"+
							            " AND TRIM(T.CURRENCY)=?"+
										" AND T.KIND=?"+
										" AND T.REGION=?"+
										" ORDER BY 8");
			prep.setString(1, sc.getVeoper());
			prep.setString(2, sc.getCurrency());
			prep.setLong(3, sc.getKind());
			prep.setLong(4, sc.getRegion());
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				scale = new Scales();
				scale.setScale(rs.getLong("scale"));
				scale.setSumma(rs.getLong("summa"));
				scale.setPercent(rs.getLong("percent"));
				scale.setCommission_profit_percent(rs.getLong("commission_profit_percent"));
				scale.setCommission_profit_summa(rs.getLong("commission_profit_summa"));
				scale.setProfit_percent(rs.getLong("profit_percent"));
				scale.setProfit_summa(rs.getLong("profit_summa"));
				scale.setRowid(rs.getString("ROWID"));
				list.add(scale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
    	return list;
    }
    
    public static boolean updateScales(Scales sc, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("UPDATE dper_scale SET veoper=?, currency=?," +
					" kind=?, region=?, scale=?, percent=?, summa=?," +
					" commission_profit_percent=?, commission_profit_summa=?," +
					" profit_percent=?, profit_summa=?  WHERE rowid=?");
			prep.setLong(1, sc.getScale());
			prep.setLong(2, sc.getSumma());
			prep.setDouble(3, sc.getPercent());
			prep.setLong(4, sc.getCommission_profit_percent());
			prep.setLong(5, sc.getCommission_profit_summa());
			prep.setLong(6, sc.getProfit_percent());
			prep.setLong(7, sc.getProfit_summa());
			prep.setString(8, sc.getRowid());
            prep.executeUpdate();
            c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean insertScales(Scales sc, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("INSERT INTO DPER_SCALE (VEOPER,CURRENCY,KIND,REGION,SCALE,PERCENT,"+
		            "SUMMA,COMMISSION_PROFIT_PERCENT,COMMISSION_PROFIT_SUMMA,"+
		            "PROFIT_PERCENT,PROFIT_SUMMA) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			prep.setString(1, sc.getVeoper());
			prep.setString(2, sc.getCurrency());
			prep.setLong(3, sc.getKind());
			prep.setLong(4, sc.getRegion());
			prep.setLong(5, sc.getScale());
			prep.setDouble(6, sc.getPercent());
			prep.setLong(7, sc.getSumma());
			prep.setLong(8, sc.getCommission_profit_percent());
			prep.setLong(9, sc.getCommission_profit_summa());
			prep.setLong(10, sc.getProfit_percent());
			prep.setLong(11, sc.getProfit_summa());
            prep.executeUpdate();
            c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean deleteScales(Scales sc, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from dper_scale where rowid=?");
			prep.setString(1, sc.getRowid());
            prep.executeUpdate();
            c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    /*************************************************************************************
     * настройка счетов
     * 
     ************************************************************************************ */
    public static List<AccountType> getAccounts(String branch,String mbranch,String veoper,String val,String alias){
    	List<AccountType> list = new ArrayList<AccountType>();
    	Connection c = null;
    	PreparedStatement prep = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("Select id,account, value1,a.rowid from dper_Acc a, ss_dper_dop t "+
        " where a.typeacc = t.id and t.id_dper=3"+
        " and a.BRANCH=?"+
        " and a.MBRANCH=?"+
        " and a.VEOPER=?"+
        " and trim(a.CURRENCY)=?");
			prep.setString(1, branch);
			prep.setString(2, mbranch);
			prep.setString(3, veoper);
			prep.setString(4, val);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				list.add(new AccountType(rs.getInt("id"), rs.getString("account"), rs.getString("value1"), rs.getString("rowid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.close(c);
		}
		return list;
    }
    public static boolean checkAcc(String acc, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = false;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select count(*) from account where id=? and state = ? and rownum<?");
			prep.setString(1, acc);
			prep.setInt(2, 2);
			prep.setInt(3, 2);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getInt(1)>0?true:false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    
    public static boolean insertDper_Acc(String branch,String mbranch,String veoper, String val,String typeAcc,String acc,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("insert into dper_Acc values(?,?,?,?,?,?)");
			prep.setString(1, branch);
			prep.setString(2, mbranch);
			prep.setString(3, veoper);
			prep.setString(4, val);
			prep.setInt(5, Integer.parseInt(typeAcc));
			prep.setString(6, acc);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    
    public static boolean updateDper_Acc(String acc, String typeAcc,String rowid,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("update dper_Acc Set"+
           " TypeAcc=?,"+
           " Account=?"+
           " where rowid=?");
			prep.setString(1, acc);
			prep.setInt(2, Integer.parseInt(typeAcc));
			prep.setString(3, rowid);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean deleteDper_Acc(String rowid,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("delete from dper_Acc where RowId=?");
			prep.setString(3, rowid);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    /****************************************************************************************************
     * 
     * dper operations
     * 
     **************************************************************************************************** */
    public static List<v_dper_oper> getOpers(String veoper, String currenc, String kind, String konvert,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	v_dper_oper oper;
    	String tmp = "";
    	List<v_dper_oper> list = new ArrayList<v_dper_oper>();
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select do.*  from v_dper_oper do " +
					" where do.VEOPER=? " +
					" and trim(do.CURRENCY)=?" +
					" and do.kind=?" +
					" and do.KONVERT=?" +
					" order by do.kind,do.id");
			prep.setString(1, veoper);
			prep.setString(2, currenc);
			prep.setInt(3, Integer.parseInt(kind));
			prep.setInt(4, Integer.parseInt(konvert));
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				oper = new v_dper_oper();
				oper.setRowid(rs.getString("rowid"));
                oper.setFkind(rs.getString("fkind"));
                oper.setFtypeoper(rs.getString("ftypeoper"));
                oper.setFacc_d(rs.getString("facc_d"));
                oper.setFacc_c(rs.getString("facc_c"));
                oper.setVeoper(rs.getLong("veoper"));
                oper.setCurrency(rs.getString("currency"));
                oper.setKind(rs.getLong("kind"));
                oper.setTypeoper(rs.getLong("typeoper"));
                oper.setId(rs.getLong("id"));
                oper.setAcc_d(rs.getLong("acc_d"));
                oper.setVal_d(rs.getString("val_d"));
                oper.setAcc_c(rs.getLong("acc_c"));
                oper.setVal_c(rs.getString("val_c"));
                oper.setPurpose(rs.getString("purpose"));
                oper.setFx_deal(rs.getLong("fx_deal"));
                oper.setTypedoc(rs.getString("typedoc"));
                oper.setCashsym(rs.getString("cashsym"));
                tmp = rs.getString("cashsymd");
                oper.setCashsymd(tmp==null?"":tmp);
                oper.setSumproc(rs.getLong("sumproc"));
                oper.setKonvert(rs.getLong("konvert"));
                oper.setNazn(rs.getString("nazn"));
                 list.add(oper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return list;
    }
    
    public static boolean deleteOper(String rowid, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("Delete Dper_Oper where Rowid =?");
			prep.setString(3, rowid);
			prep.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean updateOper(v_dper_oper oper,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update Dper_Oper o set "+
			       " o.typeoper=?, o.id=?,"+
			       " o.acc_d=?, o.val_d=?, o.acc_c=?, o.val_c=?, o.purpose=?,"+
			       " o.fx_deal=?, o.typedoc=?, o.cashsym=?, o.CASHSYMD=?, o.SUMPROC=?,"+
			       " o.NAZN=? "+
			       " where rowid=?");
			ps.setLong(1,oper.getVeoper());
            ps.setString(2,oper.getCurrency());
            ps.setLong(3,oper.getKind());
            ps.setLong(4,oper.getTypeoper());
            ps.setLong(5,oper.getId());
            ps.setLong(6,oper.getAcc_d());
            ps.setString(7,oper.getVal_d());
            ps.setLong(8,oper.getAcc_c());
            ps.setString(9,oper.getVal_c());
            ps.setString(10,oper.getPurpose());
            ps.setLong(11,oper.getFx_deal());
            ps.setString(12,oper.getTypedoc());
            ps.setString(13,oper.getCashsym());
            ps.setString(14,oper.getCashsymd());
            ps.setLong(15,oper.getSumproc());
            ps.setString(16,oper.getNazn());
            ps.setString(17,oper.getRowid());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    private static long getNextId(long veoper, String currnc, long kind, long konvert,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	long res = 0;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select nvl(max(id),0)+1 from Dper_Oper "+
								        " where VEOPER=?" +
								        " and trim(CURRENCY)=?"+
								        " and kind=?"+
								        " and KONVERT=?");
			ps.setLong(1, veoper);
			ps.setString(2, currnc);
			ps.setLong(3, kind);
			ps.setLong(4, konvert);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean insertOper(v_dper_oper oper,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("insert into Dper_Oper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1,oper.getVeoper());
            ps.setString(2,oper.getCurrency());
            ps.setLong(3,oper.getKind());
            ps.setLong(4,oper.getTypeoper());
            ps.setLong(5,getNextId(oper.getVeoper(), oper.getCurrency(), oper.getKind(), oper.getKonvert(), alias));
            ps.setLong(6,oper.getAcc_d());
            ps.setString(7,oper.getVal_d());
            ps.setLong(8,oper.getAcc_c());
            ps.setString(9,oper.getVal_c());
            ps.setString(10,oper.getPurpose());
            ps.setLong(11,oper.getFx_deal());
            ps.setString(12,oper.getTypedoc());
            ps.setString(13,oper.getCashsym());
            ps.setString(14,oper.getCashsymd());
            ps.setLong(15,oper.getSumproc());
            ps.setLong(16,oper.getKonvert());
            ps.setString(17,oper.getNazn());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
}
