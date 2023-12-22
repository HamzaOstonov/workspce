package com.is.tf.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class TransferService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql ="SELECT * FROM TF_Transfer ";


      public List<Transfer> getTransfer()  {

              List<Transfer> list = new ArrayList<Transfer>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Transfer");
                      while (rs.next()) {
                              list.add(new Transfer(
                                              rs.getLong("id"),
                                              rs.getString("p1t29"),
                                              rs.getString("p0t29"),
                                              rs.getString("p2t29"),
                                              rs.getString("p3t29"),
                                              rs.getString("p4t29"),
                                              rs.getString("p5t29"),
                                              rs.getString("p6t29"),
                                              rs.getString("p7t29"),
                                              rs.getString("p8t29"),
                                              rs.getString("p9t29"),
                                              rs.getString("p10t29"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p11t29"),
                                              rs.getString("p12t29"),
                                              rs.getString("p13t29"),
                                              rs.getString("p14t29"),
                                              rs.getDate("p15t29"),
                                              rs.getString("p16t29"),
                                              rs.getString("p100t29")
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

      private static List<FilterField> getFilterFields(TransferFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                    flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
            }
            if(!CheckNull.isEmpty(filter.getP1t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p1t29=?",filter.getP1t29()));
            }
            if(!CheckNull.isEmpty(filter.getP0t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p0t29=?",filter.getP0t29()));
            }
            if(!CheckNull.isEmpty(filter.getP2t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p2t29=?",filter.getP2t29()));
            }
            if(!CheckNull.isEmpty(filter.getP3t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p3t29=?",filter.getP3t29()));
            }
            if(!CheckNull.isEmpty(filter.getP4t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p4t29=?",filter.getP4t29()));
            }
            if(!CheckNull.isEmpty(filter.getP5t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p5t29=?",filter.getP5t29()));
            }
            if(!CheckNull.isEmpty(filter.getP6t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p6t29=?",filter.getP6t29()));
            }
            if(!CheckNull.isEmpty(filter.getP7t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p7t29=?",filter.getP7t29()));
            }
            if(!CheckNull.isEmpty(filter.getP8t29())){
                    flfields.add(new FilterField(getCond(flfields)+ "p8t29=?",filter.getP8t29()));
            }
          

            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(TransferFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Transfer ");
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



      public static List<Transfer> getTransfersFl(int pageIndex, int pageSize, TransferFilter filter)  {

              List<Transfer> list = new ArrayList<Transfer>();
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
                              list.add(new Transfer(
                                              rs.getLong("id"),
                                              rs.getString("p1t29"),
                                              rs.getString("p0t29"),
                                              rs.getString("p2t29"),
                                              rs.getString("p3t29"),
                                              rs.getString("p4t29"),
                                              rs.getString("p5t29"),
                                              rs.getString("p6t29"),
                                              rs.getString("p7t29"),
                                              rs.getString("p8t29"),
                                              rs.getString("p9t29"),
                                              rs.getString("p10t29"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p11t29"),
                                              rs.getString("p12t29"),
                                              rs.getString("p13t29"),
                                              rs.getString("p14t29"),
                                              rs.getDate("p15t29"),
                                              rs.getString("p16t29"),
                                              rs.getString("p100t29")
                                              ));
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Transfer getTransfer(int transferId) {

              Transfer transfer = new Transfer();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_transfer WHERE id=?");
                      ps.setInt(1, transferId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              transfer = new Transfer();
                              
                              transfer.setId(rs.getLong("id"));
                              transfer.setP1t29(rs.getString("p1t29"));
                              transfer.setP0t29(rs.getString("p0t29"));
                              transfer.setP2t29(rs.getString("p2t29"));
                              transfer.setP3t29(rs.getString("p3t29"));
                              transfer.setP4t29(rs.getString("p4t29"));
                              transfer.setP5t29(rs.getString("p5t29"));
                              transfer.setP6t29(rs.getString("p6t29"));
                              transfer.setP7t29(rs.getString("p7t29"));
                              transfer.setP8t29(rs.getString("p8t29"));
                              transfer.setP9t29(rs.getString("p9t29"));
                              transfer.setP10t29(rs.getString("p10t29"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return transfer;
      }

      public static Transfer create(Transfer transfer)  {

              Connection c = null;
              PreparedStatement ps = null;
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SQ_TF_transfer.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              transfer.setId(rs.getLong("id"));
                      }
                      ps = c.prepareStatement("INSERT INTO TF_transfer (id, p1t29, p0t29, p2t29, p3t29, p4t29, p5t29, p6t29, p7t29, p8t29, p9t29, p10t29 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,transfer.getId());
                      ps.setString(2,transfer.getP1t29());
                      ps.setString(3,transfer.getP0t29());
                      ps.setString(4,transfer.getP2t29());
                      ps.setString(5,transfer.getP3t29());
                      ps.setString(6,transfer.getP4t29());
                      ps.setString(7,transfer.getP5t29());
                      ps.setString(8,transfer.getP6t29());
                      ps.setString(9,transfer.getP7t29());
                      ps.setString(10,transfer.getP8t29());
                      ps.setString(11,transfer.getP9t29());
                      ps.setString(12,transfer.getP10t29());
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return transfer;
      }

      public static void update(Transfer transfer)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_transfer SET id=?, p1t29=?, p0t29=?, p2t29=?, p3t29=?, p4t29=?, p5t29=?, p6t29=?, p7t29=?, p8t29=?, p9t29=?, p10t29=?,  WHERE id=?");
                      
                      ps.setLong(1,transfer.getId());
                      ps.setString(2,transfer.getP1t29());
                      ps.setString(3,transfer.getP0t29());
                      ps.setString(4,transfer.getP2t29());
                      ps.setString(5,transfer.getP3t29());
                      ps.setString(6,transfer.getP4t29());
                      ps.setString(7,transfer.getP5t29());
                      ps.setString(8,transfer.getP6t29());
                      ps.setString(9,transfer.getP7t29());
                      ps.setString(10,transfer.getP8t29());
                      ps.setString(11,transfer.getP9t29());
                      ps.setString(12,transfer.getP10t29());
                      ps.executeUpdate();
                      c.commit();
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }

      }

      public static void remove(Transfer transfer, Long id_conract)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_transfer WHERE id_contract=?");
                      ps.setLong(1, id_conract);
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
      }








      public static Res create(com.sbs.service.Transfer transfer, String P1T29, Long id_contract)  {
    	  Res res = new Res();
          Connection c = null;
          PreparedStatement ps = null;
          Long aid=new Long ("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_transfer.NEXTVAL id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                  aid=(rs.getLong("id"));
                  }
                  ps = c.prepareStatement("INSERT INTO TF_transfer (id, p1t29, p0t29, p2t29, p3t29, p5t29, p6t29, p7t29, p8t29,id_contract, p9t29, p10t29, p11t29, p12t29, p13t29, p15t29, p16t29, p100t29 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  
                  ps.setLong(1,aid);
                  ps.setString(2,P1T29);
                  ps.setInt(3,transfer.getP0T29()!=null? transfer.getP0T29():0);
                  ps.setInt(4,transfer.getP2T29()!=null? transfer.getP2T29():null);
                  ps.setString(5,transfer.getP3T29()!=null? transfer.getP3T29():null);
                  //ps.setString(6,transfer.getP4T29());
                  ps.setString(6,transfer.getP5T29());
                  ps.setString(7,transfer.getP6T29());
                  ps.setString(8,transfer.getP7T29());
                  ps.setInt(9,transfer.getP8T29()!=null? transfer.getP8T29():0);
                  ps.setLong(10,id_contract);
                  ps.setString(11,transfer.getP9T29());
                  ps.setString(12,transfer.getP10T29());
                  if (transfer.getP11T29()!=null){
                  ps.setShort(13,transfer.getP11T29());
                  } else {
                	  ps.setString(13,null);
                  }
                  if (transfer.getP12T29()!=null){
                      ps.setShort(14,transfer.getP12T29());
                      } else {
                    	  ps.setString(14,null);
                      }
                  ps.setString(15,transfer.getP13T29()!=null?transfer.getP13T29():null);
                  ps.setDate(16,transfer.getP15T29()!=null?new java.sql.Date(transfer.getP15T29().getTimeInMillis()):null);
                  ps.setString(17,transfer.getP16T29()!=null?transfer.getP16T29():null);
                  ps.setShort(18,transfer.getP100T29());
                  if (ps.executeUpdate() == 1) {
         				c.commit();
         				res = new Res(0, "Ok");
         				System.out.println("Ok");
         			} else {
         				c.rollback();
         				res = new Res(1, "Вставка невозможно!");
         			    System.out.println("Вставка невозможно!");
         			}
          } catch (Exception e) {
                  e.printStackTrace();

          } finally {
                  ConnectionPool.close(c);
          }
          return res;
  }


}
