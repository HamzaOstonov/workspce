package com.is.providers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;



public class MunisService {
	
	public static Payment getPayment(Credentials cr,long id){
		Connection c = null;
		Payment mpay = new Payment();
	    try {
            c = ConnectionPool.getConnection(cr.getUn(),cr.getPw());
            PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_com_jpayments WHERE transaction_id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	mpay.setId( rs.getLong("id"));
            	mpay.setTr_id( rs.getInt("transaction_id"));
            	mpay.setBranch(rs.getString("branch_id"));
            	mpay.setTerminal_id(rs.getString("subbranch_id"));
            	mpay.setProvider_id(rs.getInt("customerj_id"));
            	mpay.setService_id(rs.getInt("services_list_id"));
                mpay.setAmount(rs.getInt("full_amount"));
                mpay.setState(rs.getInt("state"));
                mpay.setP_name(rs.getString("p_name"));
                mpay.setP_number(rs.getString("p_number"));
                mpay.setFrom_date(rs.getDate("from_date"));
                mpay.setTo_date(rs.getDate("to_date"));
                mpay.setFrom_value(rs.getString("from_value"));
                mpay.setTo_value(rs.getString("to_value"));
            	
            }
	    } catch (Exception e) {
	            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	            
	    } finally {
	            ConnectionPool.close(c);
	    }
		
		
		return 	mpay;
	}
	
	public static List<Payment> getListPayment(Credentials cr,Date startDate, Date endDate){
		Connection c = null;
		List<Payment> list = new ArrayList<Payment>();
		Payment mpay = new Payment();
	    try {
            c = ConnectionPool.getConnection(cr.getUn(),cr.getPw());
            PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_com_jpayments WHERE date_complete between ? and ?");
            ps.setDate(1, CheckNull.d2sql(startDate));
            ps.setDate(2, CheckNull.d2sql(endDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	mpay = new Payment();
            	mpay.setId( rs.getLong("id"));
            	mpay.setTr_id( rs.getInt("transaction_id"));
            	mpay.setBranch(rs.getString("branch_id"));
            	mpay.setTerminal_id(rs.getString("subbranch_id"));
            	mpay.setProvider_id(rs.getInt("customerj_id"));
            	mpay.setService_id(rs.getInt("services_list_id"));
                mpay.setAmount(rs.getInt("full_amount"));
                mpay.setState(rs.getInt("state"));
                mpay.setP_name(rs.getString("p_name"));
                mpay.setP_number(rs.getString("p_number"));
                mpay.setFrom_date(rs.getDate("from_date"));
                mpay.setTo_date(rs.getDate("to_date"));
                mpay.setFrom_value(rs.getString("from_value"));
                mpay.setTo_value(rs.getString("to_value"));
                list.add(mpay);
            }
	    } catch (Exception e) {
	            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	            
	    } finally {
	            ConnectionPool.close(c);
	    }
		
		
		return 	list;
	}
	
	public static ResR getPerPayment(Credentials cr,Date startDate, Date endDate){
		Connection c = null;
		ResR rr = new ResR();
		
	    try {
            c = ConnectionPool.getConnection(cr.getUn(),cr.getPw());
            PreparedStatement ps = c.prepareStatement("SELECT sum(amount) amnt,count(*) ct FROM bf_com_jpayments WHERE date_complete between ? and ?");
            ps.setDate(1, CheckNull.d2sql(startDate));
            ps.setDate(2, CheckNull.d2sql(endDate));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	rr.setAmount( rs.getLong("amnt"));
            	rr.setQuantity(rs.getInt("cn"));
            }
	    } catch (Exception e) {
	            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	            
	    } finally {
	            ConnectionPool.close(c);
	    }
		
		
		return 	rr;
	}

}
