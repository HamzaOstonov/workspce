package com.is.korona_pay;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.bpri.utils.Utils;
import com.is.dper_info.model.dper_info;
import com.is.dper_info.service.dper_infoService;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class TransactionService {

	public static Res insertNewData(dper_info dper, String un, String pwd, String alias, Connection c)
	{
    	System.out.println("Begin: ");
    	
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		System.out.println("SimpleDateFormat" + df);
		
		
		
		System.out.println("Connection = " + c);
		ISLogger.getLogger().error("1 - Connection");
		
		CallableStatement inf = null;
		
		System.out.println("CallableStatement = " + inf);
		ISLogger.getLogger().error("2 - CallableStatement");
		
		CallableStatement set_param = null;
		
		System.out.println("CallableStatement = " + set_param);
		ISLogger.getLogger().error("3 - set param");
		
		CallableStatement new_data = null;
		
		System.out.println("CallableStatement = " + new_data);
		ISLogger.getLogger().error("4 - new_data");
		
		CallableStatement clear = null;
		
		System.out.println("CallableStatement = " + clear);
		ISLogger.getLogger().error("5 - clear");
		
		Res res = null;
		
		System.out.println("Res = " + res);
		ISLogger.getLogger().error("6 - res");
		
		try
		{
			
			System.out.println("ñ = " + c);
			ISLogger.getLogger().error("7:    " + c);
			
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			
			System.out.println("inf = " + inf);
			ISLogger.getLogger().error("8  :==  "+inf);
			
			set_param = c.prepareCall("{ call Param.SetParam(?,?) }");
			
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("9 =  "+ set_param);
			
			new_data = c.prepareCall("{ call Dper.New_Data() }");
			
			
			
			clear = c.prepareCall("{ call Param.clearparam() }");
			
			
			
			clear.execute();
			
			
			
			set_param.setString(1, "DATE");
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("10"+ "set_param = " + set_param);
			set_param.setString(2, df.format(dper.getV_date()));
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("11"+"set_param = " + set_param);
			set_param.execute();
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("12"+"set_param = " + set_param);
			set_param.setString(1, "VEOPER");
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("13"+"set_param = " + set_param);
			set_param.setString(2, dper.getVeoper()); 
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("14"+"set_param = " + set_param);
			set_param.execute();			
			System.out.println("set_param = " + set_param);
			ISLogger.getLogger().error("15"+"set_param = " + set_param);
			
			String veoper_cb = dper.getVeoper();
			
			if (veoper_cb.length() == 6) {
				veoper_cb = veoper_cb.substring(2);
			}
			System.out.println("veoper_cb = " + veoper_cb);
			ISLogger.getLogger().error("16 veoper_cb" + veoper_cb);
			
			set_param.setString(1, "VEOPER_CB");
			set_param.setString(2, veoper_cb);
			ISLogger.getLogger().error("17 = "+ veoper_cb);
			set_param.execute();
			set_param.setString(1, "KIND");
			set_param.setString(2, dper.getKind());
			ISLogger.getLogger().error("18 = "+dper.getKind());
			set_param.execute();
			set_param.setString(1, "STRS");
			set_param.setString(2, dper.getStrs());
			ISLogger.getLogger().error("19 = "+dper.getStrs());
			set_param.execute();
			set_param.setString(1, "STRR");
			set_param.setString(2, dper.getStrr());
			ISLogger.getLogger().error("20 = "+dper.getStrr());
			set_param.execute();
			set_param.setString(1, "DISTR");
			set_param.setString(2, dper.getDistr());
			ISLogger.getLogger().error("21 = "+dper.getDistr());
			set_param.execute();
			set_param.setString(1, "VAL");
			set_param.setString(2, dper.getCurrency());
			System.out.println("Dper.getCurrency ==== " + dper.getCurrency());
			ISLogger.getLogger().error("22 = "+dper.getCurrency());
			set_param.execute();
			set_param.setString(1, "EVAL");
			set_param.setString(2, dper.getEval());
			System.out.println("dper.getEval === " + dper.getEval());
			ISLogger.getLogger().error("23 = "+dper.getEval());
			set_param.execute();
			
			
			set_param.setString(1, "SUMMA1");
			System.out.println("dper.getSumma() === " + dper.getSumma());
			ISLogger.getLogger().error("24 = "+dper.getSumma());
			set_param.setLong(2, withCents2(dper.getSumma()));
			
			System.out.println("WITH CENTS1 === " + withCents2(dper.getSumma()));
			ISLogger.getLogger().error("25 = "+withCents2(dper.getSumma()));
			set_param.execute();
			
			
			set_param.setString(1, "SUMMA2");
			set_param.setLong(2,0);
			set_param.setLong(2, withCents2(dper.getSumma2()));			
			System.out.println("WITH CENTS2 === " + withCents2(dper.getSumma2()));
			ISLogger.getLogger().error("26 = "+withCents2(dper.getSumma2()));
			set_param.execute();
			
			
			set_param.setString(1, "SUMMA3");
			set_param.setLong(2, withCents2(dper.getSumma3()));
			System.out.println("WITH CENTS3 === " + withCents2(dper.getSumma3()));
			ISLogger.getLogger().error("27 = "+withCents2(dper.getSumma3()));
			set_param.execute();
			
			
			set_param.setString(1, "SUMMA4");
			set_param.setLong(2, withCents2(dper.getSumma4()));
			System.out.println("WITH CENTS4 === " + withCents2(dper.getSumma4()));
			ISLogger.getLogger().error("28 = "+withCents2(dper.getSumma4()));
			set_param.execute();
			
			
			set_param.setString(1, "SUMMA5");
			set_param.setLong(2, withCents2(dper.getSumma5()));
			System.out.println("WITH CENTS5 === " + withCents2(dper.getSumma5()));
			ISLogger.getLogger().error("29 = "+withCents2(dper.getSumma5()));
			set_param.execute();
			
			
			set_param.setString(1, "PROFIT");
			set_param.setLong(2, withCents2(dper.getProfit()));
			ISLogger.getLogger().error("30 = "+withCents2(dper.getProfit()));
			set_param.execute();
			
			
			set_param.setString(1, "CLIENT");
			set_param.setString(2, dper.getClient());
			ISLogger.getLogger().error("31 = "+dper.getClient());
			set_param.execute();
			set_param.setString(1, "CLIENTN1");
			set_param.setString(2, dper.getClient_name1());
			ISLogger.getLogger().error("32 = "+dper.getClient_name1());
			set_param.execute();
			set_param.setString(1, "CLIENTN2");
			set_param.setString(2, dper.getClient_name2());
			ISLogger.getLogger().error("33 = "+dper.getClient_name2());
			set_param.execute();
			set_param.setString(1, "CLIENTN3");
			set_param.setString(2, dper.getClient_name3());
			ISLogger.getLogger().error("34 = "+dper.getClient_name3());
			set_param.execute();
			set_param.setString(1, "REZIDENT");
			set_param.setString(2, dper.getRezident());
			ISLogger.getLogger().error("35 = "+dper.getRezident());
			set_param.execute();
			
			
			set_param.setString(1, "DOC");
			set_param.setString(2, dper.getDoc_id());
			ISLogger.getLogger().error("36 = "+dper.getDoc_id());
			set_param.execute();
			set_param.setString(1, "DOCSER");
			set_param.setString(2, dper.getDoc_series());
			ISLogger.getLogger().error("37 = "+dper.getDoc_series());
			set_param.execute();
			set_param.setString(1, "DOCNUM");
			set_param.setString(2, dper.getDoc_number());
			ISLogger.getLogger().error("38 = "+dper.getDoc_number());
			set_param.execute();
			set_param.setString(1, "DOCISSUE");
			set_param.setString(2, dper.getDoc_issue());
			ISLogger.getLogger().error("39 = "+dper.getDoc_issue());
			set_param.execute();
			set_param.setString(1, "DATEISSUE");
			set_param.setString(2, dper.getDoc_date_issue()!=null?df.format(dper.getDoc_date_issue()):null);
			ISLogger.getLogger().error("40 = "+dper.getDoc_date_issue()!=null?df.format(dper.getDoc_date_issue()):null);
			set_param.execute();
			
			
			
			set_param.setString(1, "CLIENT_I");
			set_param.setString(2, dper.getClient_i());
			ISLogger.getLogger().error("41 = "+dper.getClient_i());
			set_param.execute();
			set_param.setString(1, "CLIENT_I2");
			set_param.setString(2, dper.getClient_i2());
			ISLogger.getLogger().error("42 = "+ dper.getClient_i2());
			set_param.execute();
			set_param.setString(1, "CLIENT_I3");
			set_param.setString(2, dper.getClient_i3());
			ISLogger.getLogger().error("43 = "+ dper.getClient_i3());
			set_param.execute();
			set_param.setString(1, "CLIENT_I4");
			set_param.setString(2, dper.getClient_i4());
			ISLogger.getLogger().error("44 = "+dper.getClient_i4());
			set_param.execute();
			set_param.setString(1, "CLIENT_I5");
			set_param.setString(2, dper.getClient_i5());
			ISLogger.getLogger().error("45 = "+ dper.getClient_i5());
			set_param.execute();
			set_param.setString(1, "CLIENT_I8");
			set_param.setString(2, dper.getClient_i8());
			ISLogger.getLogger().error("46 = "+ dper.getClient_i8());
			set_param.execute();
			set_param.setString(1, "CLIENT_I9");
			set_param.setString(2, dper.getClient_i9());
			ISLogger.getLogger().error("47 = "+ dper.getClient_i9());
			set_param.execute();
			set_param.setString(1, "CLIENT_I10");
			set_param.setString(2, dper.getClient_i10());
			ISLogger.getLogger().error("48 = "+dper.getClient_i10());
			set_param.execute();
			set_param.setString(1, "CLIENT_I11DATE");
			set_param.setString(2, (dper.getClient_i11date()!=null?df.format(dper.getClient_i11date()):""));
			ISLogger.getLogger().error("49 = "+(dper.getClient_i11date()!=null?df.format(dper.getClient_i11date()):""));
			set_param.execute();
			set_param.setString(1, "CLIENT_I12");
			set_param.setString(2, dper.getClient_i12());
			ISLogger.getLogger().error("50 = "+dper.getClient_i12());
			set_param.execute();
			set_param.setString(1, "CLIENT_I13CODE_STR");
			set_param.setString(2, dper.getClient_i13code_str());
			ISLogger.getLogger().error("51 = "+dper.getClient_i13code_str());
			set_param.execute();
			
			
			set_param.setString(1, "POST_ADDRESS");
			set_param.setString(2, dper.getPost_address());
			ISLogger.getLogger().error("52 = "+dper.getPost_address());
			set_param.execute();
			set_param.setString(1, "BIRTHDAY");
			set_param.setString(2, (dper.getBirthday()!=null?df.format(dper.getBirthday()):""));     //df.format(dper.getBirthday()
			ISLogger.getLogger().error("53 = "+ (dper.getBirthday()!=null?df.format(dper.getBirthday()):""));
			set_param.execute();
			set_param.setString(1, "GROUPREG");
			set_param.setString(2, dper.getClient_grstr());
			ISLogger.getLogger().error("54 = "+dper.getClient_grstr());
			set_param.execute();
			set_param.setString(1, "STATE");
			set_param.setLong(2, dper_infoService.getState3(alias));
			ISLogger.getLogger().error("55 = "+dper_infoService.getState3(alias));
			set_param.execute();
			//System.out.println("set_param.execute() = " + set_param.execute());
			
			set_param.setString(1, "MTCN");
			set_param.setString(2, dper.getMtcn());
			ISLogger.getLogger().error("56 = "+dper.getMtcn());
			set_param.execute();
			set_param.setString(1, "U1F2");
			set_param.setInt(2, dper.getU1f2());
			ISLogger.getLogger().error("57 = "+dper.getU1f2());
			set_param.execute();
			
			
			set_param.setString(1, "CENTSUMMA");
			set_param.setBigDecimal(2, dper.getCentsumma());
			System.out.println("CENTSUMMA:  " + dper.getCentsumma());
			ISLogger.getLogger().error("58 = "+ dper.getCentsumma());
			set_param.execute();
			
			
			set_param.setString(1, "REGION_OFFSHOR");
			set_param.setString(2, dper.getRegion_offshor());
			ISLogger.getLogger().error("59 = "+dper.getRegion_offshor());
			set_param.execute();
			
			
			set_param.setString(1, "PURPOSE_1");
			set_param.setString(2, dper.getPurpose_1());
			ISLogger.getLogger().error("60 = "+dper.getPurpose_1());
			set_param.execute();
			set_param.setString(1, "PURPOSE_2");
			set_param.setString(2, dper.getPurpose_2());
			ISLogger.getLogger().error("61 = "+dper.getPurpose_2());
			set_param.execute();
			set_param.setString(1, "PURPOSE_3");
			set_param.setString(2, dper.getPurpose_3());
			ISLogger.getLogger().error("62 = "+dper.getPurpose_3());
			set_param.execute();
			set_param.setString(1, "PURPOSE_4");
			set_param.setString(2, dper.getPurpose_4());
			ISLogger.getLogger().error("63 = "+dper.getPurpose_4());
			set_param.execute();
			
			set_param.setString(1, "PURPOSE_5");
			set_param.setString(2, dper.getPurpose_5());
			ISLogger.getLogger().error("64 = "+dper.getPurpose_5());
			set_param.execute();
			
			set_param.setString(1, "PURPOSE_6");
			set_param.setString(2, dper.getPurpose_6());
			ISLogger.getLogger().error("65 = "+dper.getPurpose_6());
			set_param.execute();
			
			set_param.setString(1, "ACC_DEP");
			set_param.setString(2, dper.getAcc_dep());
			ISLogger.getLogger().error("66 = "+dper.getAcc_dep());
			set_param.execute();
			
			new_data.execute();
			//System.out.println("new_data.execute() = " + new_data.execute());
			c.commit();
			System.out.println("c.commit() = " + c);
			res = new Res(0,"ok");
			System.out.println("res code = " + res.getCode());
			ISLogger.getLogger().error("67 = "+res.getCode());
			System.out.println("res message = " + res.getName());
			ISLogger.getLogger().error("68 = "+res.getName());
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error("Oshibka v BD : "+e.getMessage()+" , message:"+com.is.utils.CheckNull.getPstr(e));
			res = new Res(1, "Oshibka v BD : "+e.getMessage());
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
			//ConnectionPool.close(c);
		}
			
		return res;
	}
	
	public static Res deleteDper(long dper_id,String alias){
    	Connection c = null;
    	CallableStatement cs = null;
    	Res res = new Res();
    	try {
			c = ConnectionPool.getConnection();
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
    
    private static long withCents2(BigDecimal num){
    	System.out.println("num" + num);
    	return (num).longValue();
    }
   
    
    public static String getId(Connection c){
    	CallableStatement call = null;
    	String res = "";
    	try {
			call = c.prepareCall("{? = call param.getparam(?) }");
			call.registerOutParameter(1, java.sql.Types.VARCHAR);
			call.setString(2, "CUR_ID");
			call.execute();
			res = call.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			 Utils.close(call);
		        //ConnectionPool.close(c);
		         
			}
		return res;
    }
    
    public static String getMbranch_code(Connection c){
    	CallableStatement callab = null;
    	String res = "";
    	try {
			callab = c.prepareCall("{? =  call Dper.GetCodeMBranch()}");
			callab.registerOutParameter(1, java.sql.Types.VARCHAR);
			callab.execute();
			res = callab.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			 Utils.close(callab);
	        //ConnectionPool.close(c);
	         
		}
    	return res;
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
           Utils.close(rs);
           Utils.close(ps);
        }
        return res;
      }
    
    /* ------------------------------------------------------------ */
    
    public static int calcPercent(dper_info dper, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	CallableStatement init = null;
    	int res = 0;
    	try {
			c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
			prep = c.prepareStatement("select dper.CalcPercent(?,?,?,?,?) from dual");
			prep.setString(1, dper.getVeoper());
			prep.setString(2, dper.getEval());
			prep.setString(3, dper.getKind());
			System.out.println("dper.strr: " + dper.getStrr());
			System.out.println("getGrCode: " + getGrCode(dper.getStrr(), alias) );
			String citizenship = citizenship(dper.getStrr(), c);
			System.out.println("citizenship: " + citizenship);
			prep.setString(4, getGrCode(citizenship, alias));
			prep.setBigDecimal(5, dper.getSumma2().multiply(new BigDecimal(100)));
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getInt(1);
				System.out.println("res = " + res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println("res == " + res);
		return res;
    }
	
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
    
    public static String citizenship(String citizenship, Connection c) {

		String uzb = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select s.code_str from s_str s where s.alpha_3 = ?");
			ps.setString(1, citizenship);
			rs = ps.executeQuery();
			if (rs.next()) {
				uzb = rs.getString("code_str");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return uzb;
	}
       
    
}
