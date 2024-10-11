package com.is.munisCB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.munisCB.MCBLogger1;
import com.is.utils.CheckNull;

public class MunisSrv1 {
	private final static int req = 0;
	private final static int res = 1;
	public static com.cb.munis.Payer getPayer(String branch, int user_id, int operation_id){
		Connection c = null;
		CallableStatement cs = null;
		com.cb.munis.Payer payer = new com.cb.munis.Payer();
	    try {
	    	
	    	c = ConnectionPool.getConnection();
            cs = c.prepareCall("{ call bf_com.GET_KASSA(?,?,?,?,?,?,?) }");
            cs.setString(1, branch);
            cs.setInt(2, user_id);
            cs.setInt(3, operation_id);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.execute();
            payer.setBranch(cs.getString(4));
            payer.setAccount(cs.getString(5));
	        payer.setName(cs.getString(6));
	        payer.setInn(cs.getString(7));
            
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return 	payer;
	}
	
	public static String getHeadBranch(String bank_id){
		Connection c = null;
		String branch = "";
	    try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("select header_id from s_mfo where bank_id = ? ");
            ps.setString(1, bank_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
            	branch = rs.getString("header_id");
            }
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return 	branch;
	}
	
	public static com.cb.munis.Settlement getSettlement(int srv_id){
		Connection c = null;
		com.cb.munis.Settlement settlement = new com.cb.munis.Settlement();
	    try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from BF_MUNIS_CBSERVICES_LIST t where com_ser_id = ? ");
            ps.setInt(1, srv_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
            	settlement.setCode(rs.getString("ser_id"));
            	settlement.setSupplier(rs.getString("supplier_id"));
            	//System.out.println(settlement.getCode() + " --- " + settlement.getSupplier());
            }
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return 	settlement;
	}
	
	public static com.cb.munis.Purpose getPurpose(int srv_id){
		Connection c = null;
		com.cb.munis.Purpose purpose = new com.cb.munis.Purpose();
	    try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("select t.*,c.purpose_code,c.purpose_template from BF_MUNIS_CBSERVICES_LIST t, BF_COM_SERVICES_LIST s, BF_COM_CUSTOMERS c " +
            											"where t.com_ser_id = ? and s.id = t.com_ser_id and c.id = s.customerj_id ");
            ps.setInt(1, srv_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
            	purpose.setCode(rs.getString("purpose_code"));
            	purpose.setText(rs.getString("purpose_template"));
            }
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return 	purpose;
	}
	
	public static String getBF_SETS(String id){
		Connection c = null;
		String param = null;
	    try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("select value from BF_SETS where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
            	param = rs.getString("value");
            }
            System.out.println("param = "+param);
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return param;
	}
	
	public static Boolean getBF_SETS_Boolean(String id){
		Connection c = null;
		Boolean param = null;
	    try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("select value from BF_SETS where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
            	param = rs.getBoolean("value");
            }
	    } catch (Exception e) {
	        e.printStackTrace(); MCBLogger1.getLogger().error(CheckNull.getPstr(e));   
	    } finally {
	        ConnectionPool.close(c);
	    }
		return param;
	}
	
}
