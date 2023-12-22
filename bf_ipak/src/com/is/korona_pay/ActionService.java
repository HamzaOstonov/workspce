package com.is.korona_pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.dper_info.model.Oper_info;
import com.is.dper_info.model.dper_info;

import com.is.ISLogger;

public class ActionService {
	
	public static void changeState(String uin) {		
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			
			ps = c.prepareStatement(
					"update korona_pay_transfers set STATE = '9' where UIN=?");
			ps.setString(1, uin);
			ps.executeUpdate();
			c.commit();			
		} catch (Exception e) {
			ISLogger.getLogger().error("changeState >> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
	}
	
	public static String change(String uin, String absclientid) {		
		Connection c = null;
		PreparedStatement ps = null;
		String res = "Данные изменены";
		try {
			c = ConnectionPool.getConnection();
			
			ps = c.prepareStatement(
					"update korona_pay_transfers set ABSCLIENTID = ? where UIN=?");
			ps.setString(1, absclientid);
			ps.setString(2, uin);
			ps.executeUpdate();
			c.commit();			
		} catch (Exception e) {
			ISLogger.getLogger().error("changeState >> " + e.getMessage());
			res = e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		
		return res;
	}
	
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
	
}
