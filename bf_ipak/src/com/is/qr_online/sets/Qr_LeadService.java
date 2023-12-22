package com.is.qr_online.sets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class Qr_LeadService {
	
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM qr_lead_sets ";

    public List<Qr_lead_sets> getQr_lead_sets()  {

        List<Qr_lead_sets> list = new ArrayList<Qr_lead_sets>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM TF_qr_lead_sets");
                while (rs.next()) {
                        list.add(new Qr_lead_sets(
                        		        rs.getString("payee_id"), 
                                        rs.getString("id"),                        		
                                        rs.getString("branch_dt"),
                                        rs.getString("account_dt"),
                                        rs.getString("branch_ct"),
                                        rs.getString("account_ct"),
                                        rs.getString("inn_ct"),
                                        rs.getString("purpose_code"),
                                        rs.getString("purpose")));
                }
        } catch (SQLException e) {
                e.printStackTrace();
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
    
    private static List<FilterField> getFilterFields(Qr_lead_setsFilter filter){
        List<FilterField> flfields = new ArrayList<FilterField>();
     if(!CheckNull.isEmpty(filter.getPayee_id())){
            flfields.add(new FilterField(getCond(flfields)+ "qr_payee_id=?",filter.getPayee_id()));
    }

      if(!CheckNull.isEmpty(filter.getBranch_dt())){
              flfields.add(new FilterField(getCond(flfields)+ "branch_dt=?",filter.getBranch_dt()));
      }
      if(!CheckNull.isEmpty(filter.getAccount_dt())){
              flfields.add(new FilterField(getCond(flfields)+ "account_dt=?",filter.getAccount_dt()));
      }
      
      if(!CheckNull.isEmpty(filter.getBranch_ct())){
              flfields.add(new FilterField(getCond(flfields)+ "branch_ct=?",filter.getBranch_ct()));
      }
      if(!CheckNull.isEmpty(filter.getAccount_ct())){
              flfields.add(new FilterField(getCond(flfields)+ "account_ct=?",filter.getAccount_ct()));
      }
      if(!CheckNull.isEmpty(filter.getInn_ct())){
              flfields.add(new FilterField(getCond(flfields)+ "inn_ct=?",filter.getInn_ct()));
      }
      if(!CheckNull.isEmpty(filter.getPurpose_code())){
              flfields.add(new FilterField(getCond(flfields)+ "purpose_code=?",filter.getPurpose_code()));
      }
      if(!CheckNull.isEmpty(filter.getPurpose())){
              flfields.add(new FilterField(getCond(flfields)+ "purpose=?",filter.getPurpose()));
      }

      flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

        return flfields;
}
    
    public static int getCount(Qr_lead_setsFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM qr_lead_sets ");
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
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}
  
    public static List<Qr_lead_sets> getQr_lead_setsFl(int pageIndex, int pageSize, Qr_lead_setsFilter filter)  {

        List<Qr_lead_sets> list = new ArrayList<Qr_lead_sets>();
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
                        list.add(new Qr_lead_sets(
                        		        rs.getString("qr_payee_id"),
                        		        rs.getString("id"),
                                        rs.getString("branch_dt"),
                                        rs.getString("account_dt"),
                                        rs.getString("branch_ct"),
                                        rs.getString("account_ct"),
                                        rs.getString("inn_ct"),
                                        rs.getString("purpose_code"),
                                        rs.getString("purpose")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
    public Qr_lead_sets getQr_lead_sets(String Qr_lead_setsId) {

        Qr_lead_sets qr_lead_sets = new Qr_lead_sets();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM qr_lead_sets WHERE id=?");
                ps.setString(1, Qr_lead_setsId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        qr_lead_sets = new Qr_lead_sets();
                        qr_lead_sets.setPayee_id(rs.getString("qr_payee_id"));
                        qr_lead_sets.setBranch_dt(rs.getString("branch_dt"));
                        qr_lead_sets.setAccount_dt(rs.getString("account_dt"));
                        qr_lead_sets.setBranch_ct(rs.getString("branch_ct"));
                        qr_lead_sets.setAccount_ct(rs.getString("account_ct"));
                        qr_lead_sets.setInn_ct(rs.getString("inn_ct"));
                        qr_lead_sets.setPurpose_code(rs.getString("purpose_code"));
                        qr_lead_sets.setPurpose(rs.getString("purpose"));
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return qr_lead_sets;
}
    public static Qr_lead_sets create(Qr_lead_sets qr_lead_sets)  {

        Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_qr_lead_sets.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        qr_lead_sets.setId(rs.getString("id"));
                }
                ps = c.prepareStatement("INSERT INTO qr_lead_sets (qr_payee_id,id,branch_dt, account_dt,branch_ct, account_ct, inn_ct, purpose_code, purpose) VALUES (?,?,?,?,?,?,?,?,?)");
                
                ps.setString(1,qr_lead_sets.getPayee_id());
                ps.setString(2,qr_lead_sets.getId());
                ps.setString(3,qr_lead_sets.getBranch_dt());
                ps.setString(4,qr_lead_sets.getAccount_dt());
                ps.setString(5,qr_lead_sets.getBranch_ct());
                ps.setString(6,qr_lead_sets.getAccount_ct());
                ps.setString(7,qr_lead_sets.getInn_ct());
                ps.setString(8,qr_lead_sets.getPurpose_code());
                ps.setString(9,qr_lead_sets.getPurpose());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return qr_lead_sets;
}
    public static void update(Qr_lead_sets qr_lead_sets)  {

        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("UPDATE qr_lead_sets SET qr_payee_id=?, branch_dt=?, account_dt=?,branch_ct=?, account_ct=?, inn_ct=?, purpose_code=?, purpose=?,  WHERE id=?");
                
                ps.setString(1,qr_lead_sets.getPayee_id());               
                ps.setString(2,qr_lead_sets.getBranch_dt());
                ps.setString(3,qr_lead_sets.getAccount_dt());
                ps.setString(4,qr_lead_sets.getBranch_ct());
                ps.setString(5,qr_lead_sets.getAccount_ct());
                ps.setString(6,qr_lead_sets.getInn_ct());
                ps.setString(7,qr_lead_sets.getPurpose_code());
                ps.setString(8,qr_lead_sets.getPurpose());
                ps.setString(9,qr_lead_sets.getPayee_id());
                ps.executeUpdate();
                c.commit();
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }

}
    public static void remove(Qr_lead_sets qr_lead_sets)  {

        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("DELETE FROM qr_lead_sets WHERE id=?");
                ps.setString(1, qr_lead_sets.getId());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
}

    public static String getAccAndBr(String payee_id,String neededInfo) throws SQLException{
    	
    	List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String info = "";
    	   	
    	try{
    	c = ConnectionPool.getConnection();
    	ps = c.prepareStatement("select q.account,q.branch from qr_payee q where q.inn=?");
        ps.setString(1, payee_id);
        rs = ps.executeQuery();
        if (rs.next()) {
        	ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			Map<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			res.add(row);
		}

		for (int j = 0; j < res.size(); j++) {
			
			if (res.get(j).containsKey("ACCOUNT") && neededInfo.equals("acc_dt")) {
				info = (String) res.get(j).get("ACCOUNT");
            }else if (res.get(j).containsKey("BRANCH")&& neededInfo.equals("branch_dt")) {
				info = (String) res.get(j).get("BRANCH");
		
		        }
		            }
    	}
    	catch (Exception e) {
            e.printStackTrace();
    } finally {
             	
            ConnectionPool.close(c);
    }
    	
   return info; 	
    }
}
