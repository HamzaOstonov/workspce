package com.is.tf.policy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.is.ConnectionPool;
import com.is.tf.Accreditivsumchange.Accreditivsumchange;
import com.is.tf.Accreditivsumchange.AccreditivsumchangeService;
import com.is.tf.Accreditivtimechange.Accreditivtimechange;
import com.is.tf.Accreditivtimechange.AccreditivtimechangeService;
import com.is.tf.policysumchange.Policysumchange;
import com.is.tf.policysumchange.PolicysumchangeService;
import com.is.tf.policytimechange.Policytimechange;
import com.is.tf.policytimechange.PolicytimechangeService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class PolicyService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Policy ";


    public List<Policy> getPolicy()  {

            List<Policy> list = new ArrayList<Policy>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Policy");
                    while (rs.next()) {
                            list.add(new Policy(
                                            rs.getLong("id"),
                                            rs.getString("p1t32"),
                                            rs.getString("p0t32"),
                                            rs.getString("p2t32"),
                                            rs.getString("p3t32"),
                                            rs.getString("p4t32"),
                                            rs.getDate("p5t32"),
                                            rs.getString("p6t32"),
                                            rs.getDouble("p7t32"),
                                            rs.getDouble("p8t32"),
                                            rs.getDouble("p9t32"),
                                            rs.getDouble("p10t32"),
                                            rs.getDate("p11t32"),
                                            rs.getString("p12t32"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p13t32"),
                                            rs.getString("p16t32"),
                                            rs.getDate("p17t32"),
                                            rs.getString("p100t32")
                                           // rs.getString("p101t32")
                                            
                            ));
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

    private static List<FilterField> getFilterFields(PolicyFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t32=?",filter.getP1t32()));
          }
          if(!CheckNull.isEmpty(filter.getP0t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t32=?",filter.getP0t32()));
          }
          if(!CheckNull.isEmpty(filter.getP2t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t32=?",filter.getP2t32()));
          }
          if(!CheckNull.isEmpty(filter.getP3t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t32=?",filter.getP3t32()));
          }
          if(!CheckNull.isEmpty(filter.getP4t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t32=?",filter.getP4t32()));
          }
          if(!CheckNull.isEmpty(filter.getP5t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t32=?",filter.getP5t32()));
          }
          if(!CheckNull.isEmpty(filter.getP6t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t32=?",filter.getP6t32()));
          }
          if(!CheckNull.isEmpty(filter.getP7t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t32=?",filter.getP7t32()));
          }
          if(!CheckNull.isEmpty(filter.getP8t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t32=?",filter.getP8t32()));
          }
          if(!CheckNull.isEmpty(filter.getP9t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t32=?",filter.getP9t32()));
          }
          if(!CheckNull.isEmpty(filter.getP10t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t32=?",filter.getP10t32()));
          }
          if(!CheckNull.isEmpty(filter.getP11t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t32=?",filter.getP11t32()));
          }
          if(!CheckNull.isEmpty(filter.getP12t32())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t32=?",filter.getP12t32()));
          }
          if(!CheckNull.isEmpty(filter.getId_contract())){
              flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
           }
          if(!CheckNull.isEmpty(filter.getP13t32())){
              flfields.add(new FilterField(getCond(flfields)+ "p13t32=?",filter.getP13t32()));
      }
          if(!CheckNull.isEmpty(filter.getP16t32())){
              flfields.add(new FilterField(getCond(flfields)+ "p16t32=?",filter.getP16t32()));
      }
          if(!CheckNull.isEmpty(filter.getP17t32())){
              flfields.add(new FilterField(getCond(flfields)+ "p17t32=?",filter.getP17t32()));
      }
          if(!CheckNull.isEmpty(filter.getP100t32())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t32=?",filter.getP100t32()));
      }
  
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(PolicyFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Policy ");
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



    public static List<Policy> getPolicysFl(int pageIndex, int pageSize, PolicyFilter filter)  {

            List<Policy> list = new ArrayList<Policy>();
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
            System.out.println(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new Policy(
                                            rs.getLong("id"),
                                            rs.getString("p1t32"),
                                            rs.getString("p0t32"),
                                            rs.getString("p2t32"),
                                            rs.getString("p3t32"),
                                            rs.getString("p4t32"),
                                            rs.getDate("p5t32"),
                                            rs.getString("p6t32"),
                                            rs.getDouble("p7t32"),
                                            rs.getDouble("p8t32"),
                                            rs.getDouble("p9t32"),
                                            rs.getDouble("p10t32"),
                                            rs.getDate("p11t32"),
                                            rs.getString("p12t32"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p13t32"),
                                            rs.getString("p16t32"),
                                            rs.getDate("p17t32"),
                                            rs.getString("p100t32"),
                                            ///rs.getString("p101t32")
                                            PolicysumchangeService.getPolicysumchange(rs.getString("p3t32"),rs.getLong("id_contract")),
                                            PolicytimechangeService.getPolicytimechange(rs.getString("p3t32"),rs.getLong("id_contract"))
                                          
                            
                            
                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Policy getPolicy(int policyId) {

            Policy policy = new Policy();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_policy WHERE id=?");
                    ps.setInt(1, policyId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policy = new Policy();
                            
                            policy.setId(rs.getLong("id"));
                            
                            policy.setP0t32(rs.getString("p1t32"));
                            policy.setP1t32(rs.getString("p0t32"));
                            policy.setP2t32(rs.getString("p2t32"));
                            policy.setP3t32(rs.getString("p3t32"));
                            policy.setP4t32(rs.getString("p4t32"));
                            policy.setP5t32(rs.getDate("p5t32"));
                            policy.setP6t32(rs.getString("p6t32"));
                            policy.setP7t32(rs.getDouble("p7t32"));
                            policy.setP8t32(rs.getDouble("p8t32"));
                            policy.setP9t32(rs.getDouble("p9t32"));
                            policy.setP10t32(rs.getDouble("p10t32"));
                            policy.setP11t32(rs.getDate("p11t32"));
                            policy.setP12t32(rs.getString("p12t32"));
                            policy.setP13t32(rs.getString("p13t32"));
                            policy.setP16t32(rs.getString("p16t32"));
                            policy.setP17t32(rs.getDate("p17t32"));
                            policy.setP100t32(rs.getString("p100t32"));
                            //policy.setP101t32(rs.getString("p101t32"));
                            
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return policy;
    }

    public static Policy create(Policy policy)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_TF_policy.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policy.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_policy (id,  p1t32,p0t32, p2t32, p3t32, p4t32, p5t32, p6t32, p7t32, p8t32, p9t32, p10t32, p11t32, p12t32, id_contract, p13t32, p16t32, p17t32, p100t32, p101t32 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,policy.getId());
                    
                    ps.setString(2,policy.getP1t32());
                    ps.setString(3,policy.getP0t32());
                    ps.setString(4,policy.getP2t32());
                    ps.setString(5,policy.getP3t32());
                    ps.setString(6,policy.getP4t32());
                    ps.setDate(7,new java.sql.Date(policy.getP5t32().getTime()));
                    ps.setString(8,policy.getP6t32());
                    ps.setDouble(9,policy.getP7t32());
                    ps.setDouble(10,policy.getP8t32());
                    ps.setDouble(11,policy.getP9t32());
                    ps.setDouble(12,policy.getP10t32());
                    ps.setDate(13,new java.sql.Date(policy.getP11t32().getTime()));
                    ps.setString(14,policy.getP12t32());
                    ps.setLong(15,policy.getId_contract());
                    ps.setString(16,policy.getP13t32());
                    ps.setString(17,policy.getP16t32());
                    ps.setDate(18,new java.sql.Date(policy.getP17t32().getTime()));
                    ps.setString(19,policy.getP100t32());
                   // ps.setString(20,policy.getP101t32());
                    
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return policy;
    }

    public static void update(Policy policy)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_policy SET id=?,  p1t32=?, p0t32=?,p2t32=?, p3t32=?, p4t32=?, p5t32=?, p6t32=?, p7t32=?, p8t32=?, p9t32=?, p10t32=?, p11t32=?, p12t32=?  WHERE id=?");
                    
                    ps.setLong(1,policy.getId());
                    
                    ps.setString(2,policy.getP1t32());
                    ps.setString(3,policy.getP0t32());
                    ps.setString(4,policy.getP2t32());
                    ps.setString(5,policy.getP3t32());
                    ps.setString(6,policy.getP4t32());
                    ps.setDate(7,new java.sql.Date(policy.getP5t32().getTime()));
                    ps.setString(8,policy.getP6t32());
                    ps.setDouble(9,policy.getP7t32());
                    ps.setDouble(10,policy.getP8t32());
                    ps.setDouble(11,policy.getP9t32());
                    ps.setDouble(12,policy.getP10t32());
                    ps.setDate(13,new java.sql.Date(policy.getP11t32().getTime()));
                    ps.setString(14,policy.getP12t32());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Policy policy, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_policy WHERE id_contract=?");
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    
    
    
    
    
    
  
    
    
    public static com.sbs.service.Policy create(com.sbs.service.Policy policy, String P1t32, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        //int a=0;
        Long aid=new Long("0");
        try {
                c = ConnectionPool.getConnection();
                
                ps = c.prepareStatement("SELECT SQ_TF_Policy.Nextval id FROM DUAL");
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                
                aid=rs.getLong("id");
                }
                ps = c.prepareStatement("INSERT INTO TF_policy (id,  p1t32, p0t32,  p2t32, p3t32, p4t32, p5t32, p6t32, p7t32, p8t32, p9t32, p10t32, p11t32, p12t32, id_contract, p13t32, p16t32, p17t32, p100t32) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1t32);
                ps.setInt(3,policy.getP0T32()!=null? policy.getP0T32():0);
                ps.setString(4,policy.getP2T32());
                ps.setString(5,policy.getP3T32());
                ps.setString(6,policy.getP4T32());
                ps.setDate(7,policy.getP5T32() !=null? new java.sql.Date(policy.getP5T32().getTimeInMillis()):null);
                ps.setString(8,policy.getP6T32());
                ps.setDouble(9,policy.getP7T32()!=null? policy.getP7T32():null);
                ps.setDouble(10,policy.getP8T32()!=null? policy.getP8T32():null);
                ps.setDouble(11,policy.getP9T32()!=null? policy.getP9T32():null);
                ps.setDouble(12,policy.getP10T32()!=null? policy.getP10T32():null);
                ps.setDate(13,policy.getP11T32()!=null? new java.sql.Date(policy.getP11T32().getTimeInMillis()):null);
                if (policy.getP12T32()!=null){
                ps.setInt(14,policy.getP12T32());
                } else {
                	ps.setString(14,null);
                }
                ps.setLong(15,id_contract);
                ps.setString(16,policy.getP13T32());
                ps.setShort(17,policy.getP16T32());
                ps.setDate(18,new java.sql.Date(policy.getP17T32().getTimeInMillis()));
                ps.setShort(19,policy.getP100T32());
                //ps.setString(16,policy.getP101T32());
                ps.executeUpdate();
                
                PolicysumchangeService.remove(new Policysumchange(), policy.getP3T32(), id_contract);
                if (policy.getPolicySumChanges()!=null){
                for (int i=0;i<policy.getPolicySumChanges().length;i++){
                PolicysumchangeService.create2(policy.getPolicySumChanges()[i], policy.getP3T32() , id_contract);
                    }
                }
               
                PolicytimechangeService.remove(new Policytimechange(),policy.getP3T32(), id_contract);
                if (policy.getPolicyTimeChanges()!=null){
                for (int i=0;i<policy.getPolicyTimeChanges().length;i++){
                PolicytimechangeService.create2(policy.getPolicyTimeChanges()[i],policy.getP3T32() , id_contract);
                    }
                }
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return policy;
} 
    
   
    
}
