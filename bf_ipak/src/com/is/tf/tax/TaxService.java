package com.is.tf.tax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tf.currency.RefCurrencyData;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

public class TaxService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Tax ";


    public List<Tax> getTax()  {

            List<Tax> list = new ArrayList<Tax>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Tax");
                    while (rs.next()) {
                            list.add(new Tax(
                                            rs.getLong("id"),
                                            rs.getString("p1t39"),
                                            rs.getString("p0t39"),
                                            rs.getDate("p2t39"),
                                            rs.getString("p3t39"),
                                            rs.getString("p4t39"),
                                            rs.getDouble("p5t39"),
                                            rs.getDouble("p6t39"),
                                            rs.getDouble("p7t39"),
                                            rs.getDouble("p8t39"),
                                            rs.getDouble("p9t39"),
                                            rs.getString("p10t39"),
                                            rs.getString("p11t39"),
                                            rs.getString("p12t39"),
                                            rs.getDate("p17t39")));
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

    private static List<FilterField> getFilterFields(TaxFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t39=?",filter.getP1t39()));
          }
          if(!CheckNull.isEmpty(filter.getP0t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t39=?",filter.getP0t39()));
          }
          if(!CheckNull.isEmpty(filter.getP2t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t39=?",filter.getP2t39()));
          }
          if(!CheckNull.isEmpty(filter.getP3t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t39=?",filter.getP3t39()));
          }
          if(!CheckNull.isEmpty(filter.getP4t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t39=?",filter.getP4t39()));
          }
          if(!CheckNull.isEmpty(filter.getP5t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t39=?",filter.getP5t39()));
          }
          if(!CheckNull.isEmpty(filter.getP6t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t39=?",filter.getP6t39()));
          }
          if(!CheckNull.isEmpty(filter.getP7t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t39=?",filter.getP7t39()));
          }
          if(!CheckNull.isEmpty(filter.getP8t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t39=?",filter.getP8t39()));
          }
          if(!CheckNull.isEmpty(filter.getP9t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t39=?",filter.getP9t39()));
          }
          if(!CheckNull.isEmpty(filter.getP10t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t39=?",filter.getP10t39()));
          }
          if(!CheckNull.isEmpty(filter.getP11t39())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t39=?",filter.getP11t39()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(TaxFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Tax ");
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



    public static List<Tax> getTaxsFl(int pageIndex, int pageSize, TaxFilter filter)  {

            List<Tax> list = new ArrayList<Tax>();
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
                            list.add(new Tax(
                                            rs.getLong("id"),
                                            rs.getString("p1t39"),
                                            rs.getString("p0t39"),
                                            rs.getDate("p2t39"),
                                            rs.getString("p3t39"),
                                            rs.getString("p4t39"),
                                            rs.getDouble("p5t39"),
                                            rs.getDouble("p6t39"),
                                            rs.getDouble("p7t39"),
                                            rs.getDouble("p8t39"),
                                            rs.getDouble("p9t39"),
                                            rs.getString("p10t39"),
                                            rs.getString("p11t39"),
                                            rs.getString("p12t39"),
                                            rs.getDate("p17t39")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Tax getTax(int taxId) {

            Tax tax = new Tax();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_tax WHERE id=?");
                    ps.setInt(1, taxId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            tax = new Tax();
                            
                            tax.setId(rs.getLong("id"));
                            tax.setP1t39(rs.getString("p1t39"));
                            tax.setP0t39(rs.getString("p0t39"));
                            tax.setP2t39(rs.getDate("p2t39"));
                            tax.setP3t39(rs.getString("p3t39"));
                            tax.setP4t39(rs.getString("p4t39"));
                            tax.setP5t39(rs.getDouble("p5t39"));
                            tax.setP6t39(rs.getDouble("p6t39"));
                            tax.setP7t39(rs.getDouble("p7t39"));
                            tax.setP8t39(rs.getDouble("p8t39"));
                            tax.setP9t39(rs.getDouble("p9t39"));
                            tax.setP10t39(rs.getString("p10t39"));
                            tax.setP11t39(rs.getString("p11t39"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return tax;
    }

    public static Tax create(Tax tax)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_tax.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            tax.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_tax (id, p1t39, p0t39, p2t39, p3t39, p4t39, p5t39, p6t39, p7t39, p8t39, p9t39, p10t39, p11t39) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,tax.getId());
                    ps.setString(2,tax.getP1t39());
                    ps.setString(3,tax.getP0t39());
                    ps.setDate(4,new java.sql.Date(tax.getP2t39().getTime()));
                    ps.setString(5,tax.getP3t39());
                    ps.setString(6,tax.getP4t39());
                    ps.setDouble(7,tax.getP5t39());
                    ps.setDouble(8,tax.getP6t39());
                    ps.setDouble(9,tax.getP7t39());
                    ps.setDouble(10,tax.getP8t39());
                    ps.setDouble(11,tax.getP9t39());
                    ps.setString(12,tax.getP10t39());
                    ps.setString(13,tax.getP11t39());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return tax;
    }

    public static void update(Tax tax)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_tax SET id=?, p1t39=?, p0t39=?, p2t39=?, p3t39=?, p4t39=?, p5t39=?, p6t39=?, p7t39=?, p8t39=?, p9t39=?, p10t39=?, p11t39=?,  WHERE id=?");
                    
                    ps.setLong(1,tax.getId());
                    ps.setString(2,tax.getP1t39());
                    ps.setString(3,tax.getP0t39());
                    ps.setDate(4,new java.sql.Date(tax.getP2t39().getTime()));
                    ps.setString(5,tax.getP3t39());
                    ps.setString(6,tax.getP4t39());
                    ps.setDouble(7,tax.getP5t39());
                    ps.setDouble(8,tax.getP6t39());
                    ps.setDouble(9,tax.getP7t39());
                    ps.setDouble(10,tax.getP8t39());
                    ps.setDouble(11,tax.getP9t39());
                    ps.setString(12,tax.getP10t39());
                    ps.setString(13,tax.getP11t39());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Tax tax, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_tax WHERE id_contract=?");
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

    
    
    
    
    
    public static com.sbs.service.Tax create(com.sbs.service.Tax tax, String P1T39, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_tax.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_tax (id, p1t39, p0t39, p2t39, p3t39, p4t39, p5t39, p6t39, p7t39, p8t39, p9t39, p10t39, p11t39,id_contract, p12t39, p17t39, p100t39) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T39);
                ps.setInt(3,tax.getP0T39()!=null? tax.getP0T39():0);
                ps.setDate(4,new java.sql.Date(tax.getP2T39().getTimeInMillis()));
                ps.setString(5,tax.getP3T39());
                ps.setString(6,tax.getP4T39());
                ps.setDouble(7,tax.getP5T39());
                ps.setDouble(8,tax.getP6T39());
                ps.setDouble(9,tax.getP7T39());
                ps.setDouble(10,tax.getP8T39()!=null? tax.getP8T39():0);
                ps.setDouble(11,tax.getP9T39()!=null? tax.getP9T39():0);
                ps.setString(12,tax.getP10T39());
                ps.setInt(13,tax.getP11T39()!=null? tax.getP11T39():0);
                ps.setLong(14,id_contract);
                ps.setString(15,tax.getP12T39());
                ps.setDate(16,new java.sql.Date(tax.getP17T39().getTimeInMillis()));
                ps.setShort(17,tax.getP100T39());
                //ps.setString(18,tax.getP101T39().toString());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return tax;
}
    
    public static RefData getRefData(String val, List<RefData> dp) {
		RefData res = null;
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getData())) {
	                res = dp.get(i);
	    }    }    }
	    return res;
	}
    
    public static RefCurrencyData getRefCurrencyData(String val, List<RefCurrencyData> dp) {
    	RefCurrencyData res = null;
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getKod())) {
	                res = dp.get(i);
	    }    }    }
	    return res;
	}
    
}
