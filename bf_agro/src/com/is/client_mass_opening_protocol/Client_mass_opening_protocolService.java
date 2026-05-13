package com.is.client_mass_opening_protocol;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;



public class Client_mass_opening_protocolService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM  Client_mass_opening_protocol ";


        public List<Client_mass_opening_protocol> getClient_mass_opening_protocol()  {

                List<Client_mass_opening_protocol> list = new ArrayList<Client_mass_opening_protocol>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM  Client_mass_opening_protocol");
                        while (rs.next()) {
                                list.add(new Client_mass_opening_protocol(
                                                rs.getInt("id"),
                                                rs.getString("client_id"),
                                                rs.getString("score_res"),
                                                rs.getString("opening_res"),
                                                rs.getString("opening_uzcard"),
                                                rs.getString("opening_humo"),
                                                rs.getInt("status_score"),
                                                rs.getInt("status_opening"),
                                                rs.getInt("status_uzcard"),
                                                rs.getInt("status_humo")));
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

        private static List<FilterField> getFilterFields(Client_mass_opening_protocolFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getClient_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "client_id=?",filter.getClient_id()));
              }
              if(!CheckNull.isEmpty(filter.getScore_res())){
                      flfields.add(new FilterField(getCond(flfields)+ "score_res=?",filter.getScore_res()));
              }
              if(!CheckNull.isEmpty(filter.getOpening_res())){
                      flfields.add(new FilterField(getCond(flfields)+ "opening_res=?",filter.getOpening_res()));
              }
              if(!CheckNull.isEmpty(filter.getOpening_uzcard())){
                      flfields.add(new FilterField(getCond(flfields)+ "opening_uzcard=?",filter.getOpening_uzcard()));
              }
              if(!CheckNull.isEmpty(filter.getOpening_humo())){
                      flfields.add(new FilterField(getCond(flfields)+ "opening_humo=?",filter.getOpening_humo()));
              }
              if(!CheckNull.isEmpty(filter.getStatus_score())){
                      flfields.add(new FilterField(getCond(flfields)+ "status_score=?",filter.getStatus_score()));
              }
              if(!CheckNull.isEmpty(filter.getStatus_opening())){
                      flfields.add(new FilterField(getCond(flfields)+ "status_opening=?",filter.getStatus_opening()));
              }
              if(!CheckNull.isEmpty(filter.getStatus_uzcard())){
                      flfields.add(new FilterField(getCond(flfields)+ "status_uzcard=?",filter.getStatus_uzcard()));
              }
              if(!CheckNull.isEmpty(filter.getStatus_humo())){
                      flfields.add(new FilterField(getCond(flfields)+ "status_humo=?",filter.getStatus_humo()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(Client_mass_opening_protocolFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM  Client_mass_opening_protocol ");
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



        public static List<Client_mass_opening_protocol> getClient_mass_opening_protocolsFl(int pageIndex, int pageSize, Client_mass_opening_protocolFilter filter)  {

                List<Client_mass_opening_protocol> list = new ArrayList<Client_mass_opening_protocol>();
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
                                list.add(new Client_mass_opening_protocol(
                                                rs.getInt("id"),
                                                rs.getString("client_id"),
                                                rs.getString("score_res"),
                                                rs.getString("opening_res"),
                                                rs.getString("opening_uzcard"),
                                                rs.getString("opening_humo"),
                                                rs.getInt("status_score"),
                                                rs.getInt("status_opening"),
                                                rs.getInt("status_uzcard"),
                                                rs.getInt("status_humo")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public Client_mass_opening_protocol getClient_mass_opening_protocol(int client_mass_opening_protocolId) {

                Client_mass_opening_protocol client_mass_opening_protocol = new Client_mass_opening_protocol();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM  client_mass_opening_protocol WHERE id=?");
                        ps.setInt(1, client_mass_opening_protocolId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                client_mass_opening_protocol = new Client_mass_opening_protocol();
                                
                                client_mass_opening_protocol.setId(rs.getInt("id"));
                                client_mass_opening_protocol.setClient_id(rs.getString("client_id"));
                                client_mass_opening_protocol.setScore_res(rs.getString("score_res"));
                                client_mass_opening_protocol.setOpening_res(rs.getString("opening_res"));
                                client_mass_opening_protocol.setOpening_uzcard(rs.getString("opening_uzcard"));
                                client_mass_opening_protocol.setOpening_humo(rs.getString("opening_humo"));
                                client_mass_opening_protocol.setStatus_score(rs.getInt("status_score"));
                                client_mass_opening_protocol.setStatus_opening(rs.getInt("status_opening"));
                                client_mass_opening_protocol.setStatus_uzcard(rs.getInt("status_uzcard"));
                                client_mass_opening_protocol.setStatus_humo(rs.getInt("status_humo"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return client_mass_opening_protocol;
        }

        public static Client_mass_opening_protocol create(Client_mass_opening_protocol client_mass_opening_protocol)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_client_mass_opening_protocol.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                client_mass_opening_protocol.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO  client_mass_opening_protocol (id, client_id, score_res, opening_res, opening_uzcard, opening_humo, status_score, status_opening, status_uzcard, status_humo, ) VALUES (?,?,?,?,?,?,?,?,?,?,)");
                        
                        ps.setInt(1,client_mass_opening_protocol.getId());
                        ps.setString(2,client_mass_opening_protocol.getClient_id());
                        ps.setString(3,client_mass_opening_protocol.getScore_res());
                        ps.setString(4,client_mass_opening_protocol.getOpening_res());
                        ps.setString(5,client_mass_opening_protocol.getOpening_uzcard());
                        ps.setString(6,client_mass_opening_protocol.getOpening_humo());
                        ps.setInt(7,client_mass_opening_protocol.getStatus_score());
                        ps.setInt(8,client_mass_opening_protocol.getStatus_opening());
                        ps.setInt(9,client_mass_opening_protocol.getStatus_uzcard());
                        ps.setInt(10,client_mass_opening_protocol.getStatus_humo());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return client_mass_opening_protocol;
        }

        public static void update(Client_mass_opening_protocol client_mass_opening_protocol)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE  client_mass_opening_protocol SET id=?, client_id=?, score_res=?, opening_res=?, opening_uzcard=?, opening_humo=?, status_score=?, status_opening=?, status_uzcard=?, status_humo=?,  WHERE id=?");
                        
                        ps.setInt(1,client_mass_opening_protocol.getId());
                        ps.setString(2,client_mass_opening_protocol.getClient_id());
                        ps.setString(3,client_mass_opening_protocol.getScore_res());
                        ps.setString(4,client_mass_opening_protocol.getOpening_res());
                        ps.setString(5,client_mass_opening_protocol.getOpening_uzcard());
                        ps.setString(6,client_mass_opening_protocol.getOpening_humo());
                        ps.setInt(7,client_mass_opening_protocol.getStatus_score());
                        ps.setInt(8,client_mass_opening_protocol.getStatus_opening());
                        ps.setInt(9,client_mass_opening_protocol.getStatus_uzcard());
                        ps.setInt(10,client_mass_opening_protocol.getStatus_humo());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(Client_mass_opening_protocol client_mass_opening_protocol)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM  client_mass_opening_protocol WHERE id=?");
                        ps.setLong(1, client_mass_opening_protocol.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }
}