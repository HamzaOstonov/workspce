package com.is.dper_info.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.dper_info.Dper_infoViewCtrl;
import com.is.dper_info.model.dper_info;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class TransactionsService {
	public static int count_in_general(String id, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	CallableStatement init = null;
    	int res = 0;
    	String sql = "select count(state) count from general g"+
        " where branch=info.getbranch"+
        " and ((PARENT_ID =? and PARENT_GROUP_ID=?)"+
                        " or (PARENT_GROUP_ID =?"+
                        " and PARENT_ID in (select Id_general from dper_books"+
                             " where info_id=? )))";
    	try {
			c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
			prep = c.prepareStatement(sql);
			prep.setString(1, id);
			prep.setInt(2, 57);
			prep.setInt(3, 14);
			prep.setString(4, id);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				res = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    
    private static Map<String,String> statesMap(){
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("1", "1");
    	map.put("2", "");
    	map.put("3", "22");
    	map.put("4", "2");
    	map.put("5", "22");
    	map.put("6", "2");
    	map.put("7", "3");
    	return map;
    }
    
    public static String getErr_msg(String id_data,String actionCode, String branch, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	StringBuffer res = new StringBuffer();
    	Map<String,String> map = statesMap();
    	try {
			c = ConnectionPool.getConnection(alias);
	    	prep = c.prepareStatement("select * from general g " +
	    			"where branch=? and " +
	    			"ID in (select Id_general from dper_books " +
	    			"where info_id=? and substr(acc_d,6,3)=substr(acc_c,6,3) )");
	    	prep.setString(1, branch);
	    	prep.setString(2, id_data);
	    	ResultSet rs = prep.executeQuery();
	    	while(rs.next()){
	    		if(!rs.getString("state").equals(map.get(actionCode))){
	    			res.append("Документ").append(id_data).
	    			append(" \"").append(rs.getString("purpose")).append(" \" ID=").
	    			append(rs.getString("id")).append(" состояние=").append(rs.getString("state"));
	    			break;
	    		}
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return res.toString();
    }
    public static String dper_provodka(int action, String alert, String id_data, String alias){
    	String res = "";
    	Connection c = null;
		CallableStatement inf = null;
		CallableStatement set_param = null;
		CallableStatement provodka = null;
		CallableStatement clear = null;
		String proc = "";
		switch(action){
			case(1):
				proc = "Dper.Podtverdit_data()";
			break;
			case(2):
				proc = "Dper.Utverdit_data()";
			break;
			case(3):
				proc = "Dper.Utv_Prov_data()";
			break;
			case(4):
				proc = "Dper.Provesti_data()";
			break;
			case(5):
				proc = "Dper.Cancl_Podtverd_data()";
			break;
			case(6):
				proc = "Dper.Cancl_Utverd_data()";
			break;
			case(7):
				proc = "Dper.Cancl_Provesti_data()";
			break;
		}
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			
			System.out.println("Connection => " + c);
			clear = c.prepareCall("{ call Param.clearparam() }");
			set_param = c.prepareCall("{ call Param.SetParam(?,?) }");
			provodka = c.prepareCall("{ call "+proc+" }");
			clear.execute();
			set_param.setString(1, id_data);
			set_param.execute();
			provodka.execute();
			c.commit();
		} catch (Exception e)
		{
			res = "Проверьте состояние документов, не удалось"+alert+" "+e.getMessage();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
    }
    
    public static Res insertNewData(dper_info dper, String alias)
	{
    	System.out.println("Begin: ");
    	
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		System.out.println("SimpleDateFormat" + df);
		
		Connection c = null;
		
		System.out.println("Connection = " + c);
		
		CallableStatement inf = null;
		
		System.out.println("CallableStatement = " + inf);
		
		CallableStatement set_param = null;
		
		System.out.println("CallableStatement = " + set_param);
		
		CallableStatement new_data = null;
		
		System.out.println("CallableStatement = " + new_data);
		
		CallableStatement clear = null;
		
		System.out.println("CallableStatement = " + clear);
		
		Res res = null;
		
		System.out.println("Res = " + res);
		
		try
		{
			c = ConnectionPool.getConnection(Dper_infoViewCtrl.un, Dper_infoViewCtrl.pw, alias);
			
			System.out.println("с = " + c);
			
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			
			System.out.println("inf = " + inf);
			
			set_param = c.prepareCall("{ call Param.SetParam(?,?) }");
			
			System.out.println("set_param = " + set_param);
			
			new_data = c.prepareCall("{ call Dper.New_Data() }");
			
			//System.out.println("new_data = " + new_data);
			
			clear = c.prepareCall("{ call Param.clearparam() }");
			
			
			
			clear.execute();
			//System.out.println("сlear = " + clear);
			
			
			set_param.setString(1, "DATE");
			System.out.println("set_param = " + set_param);
			set_param.setString(2, df.format(dper.getV_date()));
			System.out.println("set_param = " + set_param);
			set_param.execute();
			System.out.println("set_param = " + set_param);
			set_param.setString(1, "VEOPER");
			System.out.println("set_param = " + set_param);
			set_param.setString(2, dper.getVeoper()); 
			System.out.println("set_param = " + set_param);
			set_param.execute();			
			System.out.println("set_param = " + set_param);
			
			String veoper_cb = dper.getVeoper();
			
			if (veoper_cb.length() == 6) {
				veoper_cb = veoper_cb.substring(2);
			}
			System.out.println("veoper_cb = " + veoper_cb);
			
			set_param.setString(1, "VEOPER_CB");
			set_param.setString(2, veoper_cb);
			set_param.execute();
			set_param.setString(1, "KIND");
			set_param.setString(2, dper.getKind());
			set_param.execute();
			set_param.setString(1, "STRS");
			set_param.setString(2, dper.getStrs());
			set_param.execute();
			set_param.setString(1, "STRR");
			set_param.setString(2, dper.getStrr());
			set_param.execute();
			set_param.setString(1, "DISTR");
			set_param.setString(2, dper.getDistr());
			set_param.execute();
			set_param.setString(1, "VAL");
			set_param.setString(2, dper.getCurrency());
			System.out.println("Dper.getCurrency ==== " + dper.getCurrency());
			set_param.execute();
			set_param.setString(1, "EVAL");
			set_param.setString(2, dper.getEval());
			System.out.println("dper.getEval === " + dper.getEval());
			set_param.execute();
			set_param.setString(1, "SUMMA1");
			System.out.println("dper.getSumma() === " + dper.getSumma());
			set_param.setLong(2, withCents(dper.getSumma()));
			// System.out.println("WITH CENTS1 === " + dper.getSumma()!=null?withCents(dper.getSumma()):0);
			System.out.println("WITH CENTS2 === " + withCents(dper.getSumma()));
			set_param.execute();
			set_param.setString(1, "SUMMA2");
			set_param.setLong(2,0);
			set_param.setLong(2, dper.getSumma2()!=null?withCents(dper.getSumma2()):0);			
			//System.out.println("summa 2 = "+dper.getSumma2()!=null?withCents(dper.getSumma2()):0);
			set_param.execute();
			set_param.setString(1, "SUMMA3");
			set_param.setLong(2, withCents(dper.getSumma3()));
			set_param.execute();
			set_param.setString(1, "SUMMA4");
			set_param.setLong(2, dper.getSumma4()!=null?withCents(dper.getSumma4()):0);
			set_param.execute();
			set_param.setString(1, "SUMMA5");
			set_param.setLong(2, dper.getSumma5()!=null?withCents(dper.getSumma5()):0);
			set_param.execute();
			set_param.setString(1, "PROFIT");
			set_param.setLong(2, withCents(dper.getProfit()));
			set_param.execute();
			set_param.setString(1, "CLIENT");
			set_param.setString(2, dper.getClient());
			set_param.execute();
			set_param.setString(1, "CLIENTN1");
			set_param.setString(2, dper.getClient_name1());
			set_param.execute();
			set_param.setString(1, "CLIENTN2");
			set_param.setString(2, dper.getClient_name2());
			set_param.execute();
			set_param.setString(1, "CLIENTN3");
			set_param.setString(2, dper.getClient_name3());
			set_param.execute();
			set_param.setString(1, "REZIDENT");
			set_param.setString(2, dper.getRezident());
			set_param.execute();
			set_param.setString(1, "DOC");
			set_param.setString(2, dper.getDoc_id());
			set_param.execute();
			set_param.setString(1, "DOCSER");
			set_param.setString(2, dper.getDoc_series());
			set_param.execute();
			set_param.setString(1, "DOCNUM");
			set_param.setString(2, dper.getDoc_number());
			set_param.execute();
			set_param.setString(1, "DOCISSUE");
			set_param.setString(2, dper.getDoc_issue());
			set_param.execute();
			set_param.setString(1, "DATEISSUE");
			set_param.setString(2, dper.getDoc_date_issue()!=null?df.format(dper.getDoc_date_issue()):null);
			set_param.execute();
			set_param.setString(1, "CLIENT_I");
			set_param.setString(2, dper.getClient_i());
			set_param.execute();
			set_param.setString(1, "CLIENT_I2");
			set_param.setString(2, dper.getClient_i2());
			set_param.execute();
			set_param.setString(1, "CLIENT_I3");
			set_param.setString(2, dper.getClient_i3());
			set_param.execute();
			set_param.setString(1, "CLIENT_I4");
			set_param.setString(2, dper.getClient_i4());
			set_param.execute();
			set_param.setString(1, "CLIENT_I5");
			set_param.setString(2, dper.getClient_i5());
			set_param.execute();
			set_param.setString(1, "CLIENT_I8");
			set_param.setString(2, dper.getClient_i8());
			set_param.execute();
			set_param.setString(1, "CLIENT_I9");
			set_param.setString(2, dper.getClient_i9());
			set_param.execute();
			set_param.setString(1, "CLIENT_I10");
			set_param.setString(2, dper.getClient_i10());
			set_param.execute();
			set_param.setString(1, "CLIENT_I11DATE");
			set_param.setString(2, (dper.getClient_i11date()!=null?df.format(dper.getClient_i11date()):""));
			set_param.execute();
			set_param.setString(1, "CLIENT_I12");
			set_param.setString(2, dper.getClient_i12());
			set_param.execute();
			set_param.setString(1, "CLIENT_I13CODE_STR");
			set_param.setString(2, dper.getClient_i13code_str());
			set_param.execute();
			set_param.setString(1, "POST_ADDRESS");
			set_param.setString(2, dper.getPost_address());
			set_param.execute();
			set_param.setString(1, "BIRTHDAY");
			set_param.setString(2, (dper.getBirthday()!=null?df.format(dper.getBirthday()):""));     //df.format(dper.getBirthday()
			set_param.execute();
			set_param.setString(1, "GROUPREG");
			set_param.setString(2, dper.getClient_grstr());
			set_param.execute();
			set_param.setString(1, "STATE");
			set_param.setLong(2, dper_infoService.getState3(alias));
			set_param.execute();
			//System.out.println("set_param.execute() = " + set_param.execute());
			
			set_param.setString(1, "MTCN");
			set_param.setString(2, dper.getMtcn());
			set_param.execute();
			set_param.setString(1, "U1F2");
			set_param.setInt(2, dper.getU1f2());
			set_param.execute();
			set_param.setString(1, "CENTSUMMA");
			set_param.setBigDecimal(2, dper.getCentsumma());
			set_param.execute();
			set_param.setString(1, "REGION_OFFSHOR");
			set_param.setString(2, dper.getRegion_offshor());
			set_param.execute();
			
			
			set_param.setString(1, "PURPOSE_1");
			set_param.setString(2, dper.getPurpose_1());
			set_param.execute();
			set_param.setString(1, "PURPOSE_2");
			set_param.setString(2, dper.getPurpose_2());
			set_param.execute();
			set_param.setString(1, "PURPOSE_3");
			set_param.setString(2, dper.getPurpose_3());
			set_param.execute();
			set_param.setString(1, "PURPOSE_4");
			set_param.setString(2, dper.getPurpose_4());
			set_param.execute();
			set_param.setString(1, "PURPOSE_5");
			set_param.setString(2, dper.getPurpose_5());
			set_param.execute();
			set_param.setString(1, "PURPOSE_6");
			set_param.setString(2, dper.getPurpose_6());
			set_param.execute();
			
			/*
			System.out.println("purp ==== " + dper.getPurpose_1());
			System.out.println("          " + dper.getPurpose_2());
			System.out.println("          " + dper.getPurpose_3());
			System.out.println("          " + dper.getPurpose_4());
			System.out.println("          " + dper.getPurpose_5());
			System.out.println("          " + dper.getPurpose_6());*/
			
			
			set_param.setString(1, "ACC_DEP");
			set_param.setString(2, dper.getAcc_dep());
			set_param.execute();
			
			new_data.execute();
			//System.out.println("new_data.execute() = " + new_data.execute());
			c.commit();
			System.out.println("c.commit() = " + c);
			res = new Res(0,"ok");
			System.out.println("res code = " + res.getCode());
			System.out.println("res message = " + res.getName());
		}
		catch (Exception e)
		{
			res = new Res(1, e.getMessage());
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				System.out.println("c.rollback() = insertNewData");
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally
		{
			ConnectionPool.close(c);
		}
			
		return res;
	}
    
    public static Res deleteDper(long dper_id,String alias){
    	Connection c = null;
    	CallableStatement cs = null;
    	Res res = new Res();
    	try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call Dper.Del_data(?) }");
			cs.setLong(1, dper_id);
			cs.execute();
			c.commit();
			res.setCode(0);
			res.setName("ok");
		} catch (SQLException e) {
			res.setCode(1);
			res.setName(e.getMessage());
			e.printStackTrace();
		}
    	return res;
    }
    
    private static long withCents(BigDecimal num){
    	System.out.println("num" + num);
    	System.out.println("withCents ====== " + (num!=null?num.multiply(new BigDecimal(100)):0));
    	return (num!=null?num.multiply(new BigDecimal(100)):0).longValue();
    }
}
