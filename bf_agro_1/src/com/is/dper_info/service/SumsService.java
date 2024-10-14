package com.is.dper_info.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.dper_info.model.dper_info;

public class SumsService {
	
	public static String getTotalsum(String client, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	CallableStatement init = null;
    	String res = "0";
    	try {
    		c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
			prep = c.prepareStatement("select round(sum(dper.GetSUM(di.currency,'840',di.summa,2))/100,2) val " +
					"from dper_info di where di.branch=info.getbranch " +
					"and di.v_date =info.getday " +
					"and di.client=? " +
					"and di.currency<>'000' " +
					"and di.kind=0 " +
					"and di.STATE in (1,2,3,20)");
			prep.setString(1, client);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getString("val");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return res;
    }
        
    public static String getPerInSums(BigDecimal sum, String currency, String alias){
    	Connection c = null;
    	CallableStatement calst = null;
    	CallableStatement init = null;
    	String res = "";
    	try {
    		c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
    		calst = c.prepareCall("{?= call Info.GetEqual(?,?,?,?)}");
			calst.registerOutParameter(1, java.sql.Types.VARCHAR);
			calst.setBigDecimal(2,sum);
			calst.setString(3, currency);
			calst.setString(4, "000");
			calst.setString(5, "1");
			calst.execute();
			res = calst.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return res;
    } 
    
    public static String getMinMax(int spr,int id, String alias){
    	Connection c = null;
    	PreparedStatement prstmt = null;
    	String res = "";
    	try {
    		c = ConnectionPool.getConnection(alias);
    		prstmt = c.prepareStatement("select VALUE2 from ss_dper_dop " +
					"where ID_DPER=? and id=?");
    		prstmt.setInt(1, spr);
    		prstmt.setInt(2, id);
    		ResultSet rs = prstmt.executeQuery();
    		while(rs.next()){
    		res = rs.getString("value2");
    		}
    	} catch (SQLException e){
    		e.printStackTrace();
    	}
		finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    
    public static double forTotalSum(String val, String eval, String summa, String alias){
    	Connection c = null;
    	CallableStatement init = null;
    	PreparedStatement prep = null;
    	double res = 0;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		init = c.prepareCall("{ call info.init() }");
    		init.execute();
    		BigDecimal getSum = dper_getSum(val, "840", summa, alias);
    		prep = c.prepareStatement("select round(sum(?)/?,?) val from dual");
    		prep.setBigDecimal(1,getSum.multiply(new BigDecimal(100)));//select round( sum(  dper.GetSUM(:PS1,:PS2,:P1,:P2)  )/:P3,:P4 ) from dual
    		prep.setInt(2,100);
    		prep.setInt(3,2);
    		ResultSet rs = prep.executeQuery();
			while(rs.next()){
				res = rs.getDouble("val");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
    	return res;
    }
    
    public static BigDecimal dper_getSum(String val,String eval, String summa, String alias){
    	Connection c = null;
    	CallableStatement init = null;
    	PreparedStatement prep = null;
    	BigDecimal res = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		init = c.prepareCall("{ call info.init() }");
    		init.execute();
    		prep = c.prepareStatement("select dper.GetSUM(?,?,?,?) val from dual");
    		prep.setString(1,val);
    		prep.setString(2,eval);
    		long tmp = (long)(Double.valueOf(summa)*100);
    		prep.setLong(3,tmp);
    		prep.setInt(4,2);
    		ResultSet rs = prep.executeQuery();
			while(rs.next()){
				res = rs.getBigDecimal("val");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
    	return res!=null?res.divide(new BigDecimal(100)):BigDecimal.ZERO;
    }
    
    public static long calcPercent(dper_info dper, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	CallableStatement init = null;
    	long res = 0;
    	try {
			c = ConnectionPool.getConnection(alias);
			init = c.prepareCall("{ call info.init() }");
			init.execute();
			prep = c.prepareStatement("select dper.CalcPercent(?,?,?,?,?) from dual");
			prep.setString(1, dper.getVeoper());
			prep.setString(2, dper.getEval());
			prep.setString(3, dper.getKind());
			prep.setString(4, dper.getClient_grstr());
			prep.setBigDecimal(5, dper.getSumma2().multiply(new BigDecimal(100)));
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				res = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res/100;
    }
    
    public static String getCourse(String currency, Date date,String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	String res = "";
    	try {
			c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("select /*+ INDEX_DESC(c XPK_SS_COURSE)*/ c.scale ||' * ' || c.course/100  val from ss_course c " +
					"where c.course_type=? and c.currency=? and c.date_act <=to_date(?,'dd.mm.yyyy')  and rownum<?");
			prep.setInt(1,1);//to_date(?,'dd.MM.yyyy')
			prep.setString(2, currency);
			prep.setDate(3, date);
			prep.setInt(4, 2);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				res = rs.getString(1);
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return res;
    }
}
