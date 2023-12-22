package com.is.dper_info.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.bpri.utils.Utils;
import com.is.dper_info.Dper_infoClient;
import com.is.dper_info.Dper_infoViewCtrl;
import com.is.dper_info.model.Oper_info;
import com.is.dper_info.model.dper_books;
import com.is.dper_info.model.dper_info;
import com.is.utils.CheckNull;

public class dper_infoService {

    
    
    public static String getGrCode(String str, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	String res = "";
    	try {
			c = ConnectionPool.getConnection(alias);
    		prep = c.prepareStatement(
    				"select id from dper_region " +
    				"where id=(" +
    				"select distinct id_region from dper_region_str where id_str=?)");
    		prep.setString(1,str);
    		ResultSet rs = prep.executeQuery();
    		while(rs.next()){
    			res=rs.getString("id");
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    
    public static int getState3(String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	int res = 0;
    	try {
    		c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select value1 from ss_dper_dop t where t.id=1 and t.id_dper=5");
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    /***********************************************************************************************
     * 
     * 
     * 
     ************************************************************************************************88 */
    public static java.util.Date info_getday(String alias){
    	Connection c = null;
    	CallableStatement init = null;
    	CallableStatement getday = null;
    	java.util.Date res = null; 
    	try {
			c = ConnectionPool.getConnection(alias);
	    	init = c.prepareCall("{ call info.init() }");
	    	init.execute();
	    	getday = c.prepareCall("{? =  call info.getday() }");
	    	getday.registerOutParameter(1, java.sql.Types.DATE);
	    	getday.execute();
	    	res = new java.util.Date(getday.getDate(1).getTime());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.close(c);
		}
		return res;
    }
    public static String getHeader_Id(String alias, String branch){
    	Connection c = null;
    	PreparedStatement prep = null;
    	String res = "";
    	try {
    		c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select header_id from s_mfo where bank_id=?");
			prep.setString(1, branch);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				res = rs.getString("header_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
			ConnectionPool.close(c);
		}
		return res;
    }
    
    public static String getMbranch_code(String alias){
    	Connection c= null;
    	CallableStatement callab = null;
    	String res = "";
    	try {
    		c = ConnectionPool.getConnection(alias);
			callab = c.prepareCall("{? =  call Dper.GetCodeMBranch()}");
			callab.registerOutParameter(1, java.sql.Types.VARCHAR);
			callab.execute();
			res = callab.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    public static String getId(String un, String pwd, String alias){
    	Connection c= null;
    	CallableStatement call = null;
    	String res = "";
    	try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			call = c.prepareCall("{? = call param.getparam(?) }");
			call.registerOutParameter(1, java.sql.Types.VARCHAR);
			call.setString(2, "CUR_ID");
			call.execute();
			res = call.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
    }
    /*****************************************************************************************************
     * 
     * validation of input data
     **************************************************************************************************** */
    
    public static boolean isValidCode(String spr, String code, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	ResultSet rs = null;
    	String sql = "select count(*) count from ";
    	if(spr.equals("s_str")){
    		sql += "s_str where CODE_STR=? and act='A'";
    	}else if(spr.equals("distr")){
    		sql += "s_distr where distr=?";
    	}else if(spr.equals("u1f2")){
    		sql += "ss_ui_status where id=?";
    	}else if(spr.equals("doc_type")){
    		sql += "s_passport where kod_pas=?";
    	}
    	boolean res = false;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement(sql);
			prep.setString(1, code);
			rs = prep.executeQuery();
			if(rs.next()){
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static boolean isValidCode_dper_dop(int id_dper, String id, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	ResultSet rs = null;
    	String sql = "select count(*) from ss_dper_dop where id_dper=? ";
    	if(id_dper == 7){
    		sql += "and value1 = ?";
    	}
    	else {
    		sql += "and id = ?";
    	}
    	boolean res = true;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement(sql);
			prep.setInt(1, id_dper);
			prep.setString(2, id);
			rs = prep.executeQuery();
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
    
    
    public static boolean canVeoper(String veoper, String currency, String kind, String alias ){
    	boolean res = true;
    	Connection c = null;
    	PreparedStatement prstmt = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		prstmt = c.prepareStatement("select count(*) c from dper_oper t  " +
					" where t.VEOPER=? and t.CURRENCY=? and t.kind=?");
    		prstmt.setString(1, veoper);
    		prstmt.setString(2, currency);
    		prstmt.setString(3, kind);
    		ResultSet rs = prstmt.executeQuery();
    		while(rs.next()){
    		res = rs.getInt("c")>0?true:false;
    		}
    	} catch (SQLException e){
    		e.printStackTrace();
    	}
		finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    
    public static boolean convertValidation(String val,String eval,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = false;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select id from ss_dper_dop t where " +
					"t.id_dper=? and t.value1=? and t.value2=?");
			prep.setInt(1, 9);
			prep.setString(2, val);
			prep.setString(3, eval);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getInt("id")>0?true:false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return res;
    }
    
    public static boolean veoperConvertValid(String veoper,String kind,String eval,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	boolean res = false;
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select count(*) from dper_scale t " +
					"where t.kind=? and t.veoper=? and t.currency=?");
			prep.setString(1, kind);
			prep.setString(2, veoper);
			prep.setString(3, eval);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getInt(1)>0?true:false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return res;
    }
    
    public boolean transClient(String name, String alias){
//    	String fio = aclient_i.getValue();
//		if(aclient_i2.getValue().equals("")){
//			fio+=" "+aclient_i2.getValue();
//		}
//		fio+=" "+aclient_i3.getValue();
    	Connection c = null;
    	CallableStatement init = null;
    	CallableStatement calst = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		init = c.prepareCall("{ call info.init() }");
			init.execute();
			calst = c.prepareCall("{ call transclient_ut(?)}");
			calst.setString(1, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
    	return true;
    }

    /***************************************************************************************************
     *
     * connection for dper_infoClient
     * 
     **************************************************************************************************** */
    
     public static Dper_infoClient canClient(String seria, String num, String alias){
    	 Dper_infoClient dperClient = null;
    	 Connection c = null;
    	 CallableStatement init = null;
    	 PreparedStatement prep = null;
    	 boolean res = false;
    	 try {
    		 c = ConnectionPool.getConnection(alias);
    		 prep = c.prepareStatement("select client_i2, client_i3, client_i10, client_i12, client_i11date, rezident from dper_info where client_i8 = ? and client_i9 = ?");
    		 prep.setString(1, seria);
    		 prep.setString(2, num);
    		 
    		 ResultSet rs = prep.executeQuery();
//    		 dperClient = new ArrayList<dper_infoClient>();
    		 while(rs.next()) {
    			 dperClient = new Dper_infoClient(
    					 
    					 rs.getString("client_i2"),
    					 rs.getString("client_i3"),
    					 rs.getString("client_i10"),
    					 rs.getString("client_i12"),
    					 alias, rs.getDate("client_i11date"),
    					 rs.getString("rezident"),
    					 alias
    					 );
    					 
    		 }
    	 } catch(SQLException e) {
    		 e.printStackTrace();
    	 } finally {
    		 ConnectionPool.close(c);
    	 }
    	 return dperClient;
    
     }
    
    
    /***************************************************************************************************
     * 
     * 
     **************************************************************************************************** */
    
    
    public static List<Oper_info> getPurposes(dper_info dper,String alias){
    	List<Oper_info> opers = null;
    	Connection c = null;
    	PreparedStatement prep = null;
    	String sql = "select Nazn,Purpose,CASHSYMD,CASHSYM,val_d,val_c,typeoper from DPER_OPER "+
	      " where VEOPER=? and CURRENCY = ? and KIND=?"+
	      " and konvert in (select id from ss_dper_dop t where t.id_dper=?"+
	      " and t.value1=? and t.value2=?) order by id";
    	try {
    		System.out.println("Veoper  ==  " + dper.getVeoper());
    		System.out.println("Currensy  ==  " + dper.getCurrency());
    		System.out.println("Kind  ==  " + dper.getKind());
    		System.out.println("getEval  ==  " + dper.getEval());
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement(sql);
			prep.setString(1,dper.getVeoper());
			prep.setString(2,dper.getCurrency());
			prep.setString(3,dper.getKind());
			prep.setInt(4,9);
			prep.setString(5,dper.getCurrency());
			prep.setString(6,dper.getEval());
			ResultSet rs = prep.executeQuery();
			opers = new ArrayList<Oper_info>();
			while(rs.next()){
				opers.add(new Oper_info(
						rs.getString("Nazn"),
						rs.getString("Purpose"),
						rs.getString("CASHSYMD"),
						rs.getString("CASHSYM"),
						rs.getString("val_d"),
						rs.getString("val_c"),
						rs.getInt("typeoper")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
    	return opers;
    }
    
    
    
    
    /***************************************************************************************************
     * 
     ************************************************************************************************* */
//    public static List<RefData> showNameClient(String doc_num, String alias){
//    	List<RefData> list = new ArrayList<RefData>();
//    	RefData cl =null;
//    	Connection c = null;
//    	PreparedStatement prep = null;
//    	try {
//    		c = ConnectionPool.getConnection(alias);
//			prep = c.prepareStatement("select t.ID id , "+
//			           "decode(t.state,2,'(2-Утверж) ',3,'(3-Закрыт) ')||t.name||chr(149)||t.PASSPORT_SERIAL||chr(149)||t.PASSPORT_NUMBER||chr(149)  name  "+
//			           "from client_p t where t.passport_number =? and t.state in(?,?) order by state,id");
//			prep.setString(1, doc_num);
//			prep.setInt(2,2);
//			prep.setInt(3,3);
//			ResultSet rs = prep.executeQuery();
//			while(rs.next()){
//				cl = new RefData(rs.getString("id"),
//								rs.getString("name"));
//				list.add(cl);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			ConnectionPool.close(c);
//		}
//		return list;
//    }
    public static String user_mbranchCode(String alias){
    	Connection c = null;
    	CallableStatement init = null;
    	PreparedStatement prep1 = null;
    	ResultSet rs;
    	String res = "";
    	try {
    		c = ConnectionPool.getConnection(Dper_infoViewCtrl.un, Dper_infoViewCtrl.pw, alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
    		prep1 = c.prepareStatement("select code from ss_subsidiary_user where id_user=info.getempid");
			rs = prep1.executeQuery();
			if(rs.next()){
				res = rs.getString(1);
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return res;
    }
    public static int user_mbranchCount(String alias){
    	Connection c = null;
    	CallableStatement init = null;
    	PreparedStatement prep1 = null;
    	ResultSet rs;
    	int res = 0;
    	try {
    		c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
    		prep1 = c.prepareStatement("select count(*) from ss_subsidiary_user where id_user=info.getempid");
			rs = prep1.executeQuery();
			if(rs.next()){
				res = rs.getInt(1);
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return res;
    }

    
    
    
    
    
    /*****************************************************************************************************
     * for filling operations table 
     * 
     *************************************************************************************************** */
    public static Map<String,String> getOperName(){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("1","взнос наличными");
    	map.put("2","расход наличными");
    	map.put("3","списание на транзитный счет");
    	map.put("4","зачисление с транзитного счета");
    	map.put("5","комиссия банка");
    	return map;
    }
    public static List<dper_books> getDper_books(String info_id,String alias){
    	List<dper_books> list = new ArrayList<dper_books>();
    	Map<String,String> opername = getOperName();
    	dper_books books;
    	Connection c = null;
    	PreparedStatement prep = null;
    	String sql = "select info_id,id_general,typeoper,acc_d,acc_c,summa,comments " +
    			"from V_DPER_BOOKS where info_id=?";
    	try{
	    	c = ConnectionPool.getConnection(alias);
	    	prep = c.prepareStatement(sql);
	        prep.setString(1, info_id);
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	        	books = new dper_books();
	            books.setInfo_id(rs.getInt("info_id"));
	            books.setGeneral_id(rs.getInt("id_general"));
	            String toper = rs.getString("typeoper");
	            books.setTypeoper(opername.get(toper));
	            books.setAcc_d(rs.getString("acc_d"));
	            books.setAcc_c(rs.getString("acc_c"));
	            books.setSumma(rs.getInt("summa"));
	            books.setComments(rs.getString("comments"));
	            list.add(books);
	        }
		} catch (Exception e) {
		        e.printStackTrace();
		} finally {
		        ConnectionPool.close(c);
		}
		return list;
    }
    
    public static String getDistrByBranch(Connection c,String branch) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String res = null;
        try {
          ps = c.prepareStatement("select distr from s_mfo where bank_id=?");
          ps.setString(1, branch);
          rs = ps.executeQuery();
          if(rs.next()){
            res = rs.getString(1);
            //res = ""
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        finally {
           Utils.close(ps);
           Utils.close(rs);
        }
        return res;
      }
    
}
