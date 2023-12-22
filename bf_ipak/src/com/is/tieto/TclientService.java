package com.is.tieto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.LtLogger;
//import com.is.customer.Customer;
//import com.is.customer.CustomerService;
import com.is.account.Account;
import com.is.trpayti.Ti_Duplicate;
import com.is.trpayti.Ti_DuplicateDebit;
import com.is.trpayti.Ti_Duplicate_Other;
import com.is.userti.Action;
import com.is.utilsti.CheckNull;
import com.is.utilsti.FilterField;
import com.is.utilsti.Res;

public class TclientService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? and t.bank_c=? ";
    private static String msql ="SELECT * FROM izd_clients cl";
    private static String v_bankc;
    private static String v_groupc;
    private static String v_HO;
    
    public static List<Tclient> getTclient(String serial_no)  {

            List<Tclient> list = new ArrayList<Tclient>();
            Connection c = null;

            try {
                    c = ConnectionPool.getTConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM izd_clients cl where cl.serial_no =? and cl.bank_c='01'");
                    ps.setString(1, serial_no);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new Tclient(
                                            rs.getString("client"),
                                            rs.getString("bank_c"),
                                            rs.getString("client_b"),
                                            rs.getString("cl_type"),
                                            rs.getString("cln_cat"),
                                            rs.getDate("rec_date"),
                                            rs.getString("f_names"),
                                            rs.getString("surname"),
                                            rs.getString("title"),
                                            rs.getString("m_name"),
                                            rs.getDate("b_date"),
                                            rs.getString("r_street"),
                                            rs.getString("r_city"),
                                            rs.getString("r_cntry"),
                                            rs.getString("usrid"),
                                            rs.getDate("ctime"),
                                            rs.getString("status"),
                                            rs.getString("search_name"),
                                            rs.getString("sex"),
                                            rs.getString("serial_no"),
                                            rs.getDate("doc_since"),
                                            rs.getString("issued_by"),
                                            rs.getDate("status_change_date"),
                                            rs.getString("id_card"),
                                            rs.getString("resident"),
                                            rs.getString("person_code"),
                                            rs.getString("r_mob_phone"),
                                            rs.getString("r_phone"),
                                            rs.getString("r_e_mails")));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    
    
    
    public static Tclient getTclient_by_id(String client_id)  {

        Tclient list = null;
        Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM izd_clients cl where cl.client =? and cl.bank_c='01'");
                ps.setString(1, client_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list = new Tclient(
                                        rs.getString("client"),
                                        rs.getString("bank_c"),
                                        rs.getString("client_b"),
                                        rs.getString("cl_type"),
                                        rs.getString("cln_cat"),
                                        rs.getDate("rec_date"),
                                        rs.getString("f_names"),
                                        rs.getString("surname"),
                                        rs.getString("title"),
                                        rs.getString("m_name"),
                                        rs.getDate("b_date"),
                                        rs.getString("r_street"),
                                        rs.getString("r_city"),
                                        rs.getString("r_cntry"),
                                        rs.getString("usrid"),
                                        rs.getDate("ctime"),
                                        rs.getString("status"),
                                        rs.getString("search_name"),
                                        rs.getString("sex"),
                                        rs.getString("serial_no"),
                                        rs.getDate("doc_since"),
                                        rs.getString("issued_by"),
                                        rs.getDate("status_change_date"),
                                        rs.getString("id_card"),
                                        rs.getString("resident"),
                                        rs.getString("person_code"),
                                        rs.getString("r_mob_phone"),
                                        rs.getString("r_phone"),
                                        rs.getString("r_e_mails"));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
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

    private static List<FilterField> getFilterFields(TclientFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getClient())){
                  flfields.add(new FilterField(getCond(flfields)+ "client=?",filter.getClient()));
          }
          if(!CheckNull.isEmpty(filter.getBank_c())){
                  flfields.add(new FilterField(getCond(flfields)+ "bank_c=?",filter.getBank_c()));
          }
          if(!CheckNull.isEmpty(filter.getClient_b())){
                  flfields.add(new FilterField(getCond(flfields)+ "client_b=?",filter.getClient_b()));
          }
          if(!CheckNull.isEmpty(filter.getCl_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "cl_type=?",filter.getCl_type()));
          }
          if(!CheckNull.isEmpty(filter.getCln_cat())){
                  flfields.add(new FilterField(getCond(flfields)+ "cln_cat=?",filter.getCln_cat()));
          }
          if(!CheckNull.isEmpty(filter.getRec_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "rec_date=?",new java.sql.Date(filter.getRec_date().getTime())));
          }
          if(!CheckNull.isEmpty(filter.getF_names())){
                  flfields.add(new FilterField(getCond(flfields)+ "upper(f_names) like ?","%"+filter.getF_names().toUpperCase()+"%"));
          }
          if(!CheckNull.isEmpty(filter.getSurname())){
                  flfields.add(new FilterField(getCond(flfields)+ "upper(surname) like ?","%"+filter.getSurname().toUpperCase()+"%"));
          }
          if(!CheckNull.isEmpty(filter.getTitle())){
                  flfields.add(new FilterField(getCond(flfields)+ "title=?",filter.getTitle()));
          }
          if(!CheckNull.isEmpty(filter.getM_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "m_name=?",filter.getM_name()));
          }
          if(!CheckNull.isEmpty(filter.getB_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "b_date=?",new java.sql.Date( filter.getB_date().getTime())));
          }
          if(!CheckNull.isEmpty(filter.getR_street())){
                  flfields.add(new FilterField(getCond(flfields)+ "r_street=?",filter.getR_street()));
          }
          if(!CheckNull.isEmpty(filter.getR_city())){
                  flfields.add(new FilterField(getCond(flfields)+ "r_city=?",filter.getR_city()));
          }
          if(!CheckNull.isEmpty(filter.getR_cntry())){
                  flfields.add(new FilterField(getCond(flfields)+ "r_cntry=?",filter.getR_cntry()));
          }
          if(!CheckNull.isEmpty(filter.getUsrid())){
                  flfields.add(new FilterField(getCond(flfields)+ "usrid=?",filter.getUsrid()));
          }
          if(!CheckNull.isEmpty(filter.getCtime())){
                  flfields.add(new FilterField(getCond(flfields)+ "ctime=?",filter.getCtime()));
          }
          if(!CheckNull.isEmpty(filter.getStatus())){
                  flfields.add(new FilterField(getCond(flfields)+ "status=?",filter.getStatus()));
          }
          if(!CheckNull.isEmpty(filter.getSearch_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "upper(search_name) like ? ","%"+filter.getSearch_name().toUpperCase()+"%"));
          }
          if(!CheckNull.isEmpty(filter.getSex())){
                  flfields.add(new FilterField(getCond(flfields)+ "sex=?",filter.getSex()));
          }
          if(!CheckNull.isEmpty(filter.getSerial_no())){
                  flfields.add(new FilterField(getCond(flfields)+ "serial_no=?",filter.getSerial_no()));
          }
          if(!CheckNull.isEmpty(filter.getDoc_since())){
                  flfields.add(new FilterField(getCond(flfields)+ "doc_since=?",filter.getDoc_since()));
          }
          if(!CheckNull.isEmpty(filter.getIssued_by())){
                  flfields.add(new FilterField(getCond(flfields)+ "issued_by=?",filter.getIssued_by()));
          }
          if(!CheckNull.isEmpty(filter.getCard())){
              flfields.add(new FilterField(getCond(flfields)+ "exists (select 'x' from izd_cards ic where ic.client_id=cl.client and ic.card like ?) ",filter.getCard()));
          }

          
          if(!CheckNull.isEmpty(filter.getStatus_change_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "status_change_date=?",filter.getStatus_change_date()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(TclientFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM izd_clients cl");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getTConnection();
                PreparedStatement ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    n = rs.getInt(1);
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<Tclient> getTclientsFl(int pageIndex, int pageSize, TclientFilter filter, String alias)  {

            List<Tclient> list = new ArrayList<Tclient>();
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

//System.out.println(sql.toString());
            try {
                    c = ConnectionPool.getTConnection();
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);
                    
          //          String halias = CustomerService.get_alias_ho(alias);
                    ps.setString(params++,"01");

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new Tclient(
                                            rs.getString("client"),
                                            rs.getString("bank_c"),
                                            rs.getString("client_b"),
                                            rs.getString("cl_type"),
                                            rs.getString("cln_cat"),
                                            rs.getDate("rec_date"),
                                            rs.getString("f_names"),
                                            rs.getString("surname"),
                                            rs.getString("title"),
                                            rs.getString("m_name"),
                                            rs.getDate("b_date"),
                                            rs.getString("r_street"),
                                            rs.getString("r_city"),
                                            rs.getString("r_cntry"),
                                            rs.getString("usrid"),
                                            rs.getDate("ctime"),
                                            rs.getString("status"),
                                            rs.getString("search_name"),
                                            rs.getString("sex"),
                                            rs.getString("serial_no"),
                                            rs.getDate("doc_since"),
                                            rs.getString("issued_by"),
                                            rs.getDate("status_change_date"),
                                            rs.getString("id_card"),
                                            rs.getString("resident"),
                                            rs.getString("person_code"),
                                            rs.getString("r_mob_phone"),
                                            rs.getString("r_phone"),
                                            rs.getString("r_e_mails")));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }

    public static List<AccInfo> getAccInfo(String client)  {

        List<AccInfo> list = new ArrayList<AccInfo>();
        Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
               // PreparedStatement ps = c.prepareStatement("SELECT * FROM IZD_ACC_INFO where bank_c='01' and client=?");
                
               /* PreparedStatement ps = c.prepareStatement("SELECT a.account_no, a.client,a.card_acct,a.bank_c,a.groupc,a.ctime,a.ac_status,a.cl_status,a.acc_prty,"+
                "a.c_accnt_type,a.ccy,c.expiry1 ab_expirity,a.f_names,a.surname,a.city,a.street,a.tranz_acct,a.product,c.card,c.status1,c.status2,c.cond_set "+
                "FROM IZD_ACC_INFO a,izd_cards c "+
                "where c.cl_acct_key=a.tab_key and client=? and a.bank_c='01' and a.groupc='02' and c.bank_c='01' and c.groupc='02'");
                */
                PreparedStatement ps = c.prepareStatement(
   					 "select "+
					     "ac.account_no, "+
					     "ac.card_acct, "+
					     "ac.ac_status, "+
					     "ac.cl_status, "+
					     "ac.acc_prty, "+
					     "ac.c_accnt_type, "+
					     "ac.ccy, "+
					     "ac.f_names, "+
					     "ac.surname, "+
					     "ac.city, "+
					     "ac.street, "+
					     "ac.tranz_acct, " +
					     "a.agre_nom, "+
					     ""+
					     "t.card, "+
					     "t.status1, "+
					     "t.status2, "+
					     "t.cond_set, "+
					     "t.client_id client, "+
					     "t.ctime, "+
					     "t.expiry1 ab_expirity, " +
					     "t.agreement_key, "+
					     "t.stop_cause, "+
					     ""+
					     "a.product, " +
					     "a.contract, "+
					     "p.name "+
					     ""+
					"from izd_cards t, "+
					     "izd_agreement a, "+
					     "izd_offered_products p, "+
					     "izd_acc_info ac "+
					     ""+
							"where a.agre_nom = t.agreement_key "+
						      "and t.bank_c = '01' "+
						      //"and t.groupc = '02' "+ // originali bu
						      //"and t.groupc = '01' "+ // bu ofisdagi test bazada ishlashi uchun
						      "and t.groupc = '"+getV_groupc()+"' "+ // 
						      "and a.bank_c = '01' "+
						      //"and a.groupc = '02' "+ // originali bu
						      //"and a.groupc = '01' "+ // bu ofisdagi test bazada ishlashi uchun
						      "and a.groupc = '"+getV_groupc()+"' "+ //
						      "and p.bank_c = '01' "+
						      //"and p.groupc = '02' "+ // originali bu
						      //"and p.groupc = '01' "+ //bu ofisdagi test bazada ishlashi uchun
						      "and p.groupc = '"+getV_groupc()+"' "+ //
						      "and ac.bank_c = '01' "+
						      //"and ac.groupc = '02' "+ // originali bu
						      //"and ac.groupc = '01' "+  //bu ofisdagi test bazada ishlashi uchun
						      "and ac.groupc = '"+getV_groupc()+"' "+  //
						      "and p.code = a.product "+
						      "and t.cl_acct_key=ac.tab_key "+
						      "and t.client_id = ?"
						      );

                
                ps.setString(1, client);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new AccInfo(
                                        rs.getLong("account_no"),
                                        rs.getString("client"),
                                        rs.getString("card_acct"),
                                      //  rs.getString("bank_c"),
                                      //  rs.getString("groupc"),
                                        rs.getDate("ctime"),
                                        rs.getString("ac_status"),
                                        rs.getString("cl_status"),
                                        rs.getString("acc_prty"),
                                        rs.getString("c_accnt_type"),
                                        rs.getString("ccy"),
                                        rs.getDate("ab_expirity"),
                                        rs.getString("f_names"),
                                        rs.getString("surname"),
                                        rs.getString("city"),
                                        rs.getString("street"),
                                        rs.getString("tranz_acct"),
                                        rs.getString("card"),
                                        rs.getString("status1"),
                                        rs.getString("status2"),
                                        rs.getString("product"),
                                        rs.getString("name"),
                                        rs.getInt("agreement_key"),
                                        rs.getString("agre_nom"),
                                        rs.getString("contract"),
                                        rs.getString("stop_cause")
                        ));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
    
    public static List<AccInfo> getAccInfo_active(String client)  {

        List<AccInfo> list = new ArrayList<AccInfo>();
        Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
               // PreparedStatement ps = c.prepareStatement("SELECT * FROM IZD_ACC_INFO where bank_c='01' and client=?");
                
               /* PreparedStatement ps = c.prepareStatement("SELECT a.account_no, a.client,a.card_acct,a.bank_c,a.groupc,a.ctime,a.ac_status,a.cl_status,a.acc_prty,"+
                "a.c_accnt_type,a.ccy,c.expiry1 ab_expirity,a.f_names,a.surname,a.city,a.street,a.tranz_acct,a.product,c.card,c.status1,c.status2,c.cond_set "+
                "FROM IZD_ACC_INFO a,izd_cards c "+
                "where c.cl_acct_key=a.tab_key and client=? and c.status1='0' and a.bank_c='01' and a.groupc='02' and c.bank_c='01' and c.groupc='02'");
                */
                PreparedStatement ps = c.prepareStatement(
                					 "select "+
								     "ac.account_no, "+
								     "ac.card_acct, "+
								     "ac.ac_status, "+
								     "ac.cl_status, "+
								     "ac.acc_prty, "+
								     "ac.c_accnt_type, "+
								     "ac.ccy, "+
								     "ac.f_names, "+
								     "ac.surname, "+
								     "ac.city, "+
								     "ac.street, "+
								     "ac.tranz_acct, " +
								     "a.agre_nom, "+
								     ""+
								     "t.card, "+
								     "t.status1, "+
								     "t.status2, "+
								     "t.cond_set, "+
								     "t.client_id client, "+
								     "t.ctime, "+
								     "t.agreement_key, "+
								     "t.expiry1 ab_expirity, "+
								     "t.stop_cause, "+
								     ""+
								     "a.product, "+
								     "p.name, " +
								     "a.contract "+
								     " "+
								" from izd_cards t, "+
								     "izd_agreement a, "+
								     "izd_offered_products p, "+
								     "izd_acc_info ac "+
								     ""+
								"where a.agre_nom = t.agreement_key "+
								      "and t.bank_c = '01' "+
								      "and t.groupc = '02' "+
								      "and a.bank_c = '01' "+
								      "and a.groupc = '02' "+
								      "and p.bank_c = '01' "+
								      "and p.groupc = '02' "+
								      "and ac.bank_c = '01' "+
								      "and ac.groupc = '02' "+
								      "and p.code = a.product "+
								      "and t.cl_acct_key=ac.tab_key "+
								      "and t.status1='0' "+
								      "and t.client_id = ?"
								      );
                ps.setString(1, client);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new AccInfo(
                                        rs.getLong("account_no"),
                                        rs.getString("client"),
                                        rs.getString("card_acct"),
                                      //  rs.getString("bank_c"),
                                      //  rs.getString("groupc"),
                                        rs.getDate("ctime"),
                                        rs.getString("ac_status"),
                                        rs.getString("cl_status"),
                                        rs.getString("acc_prty"),
                                        rs.getString("c_accnt_type"),
                                        rs.getString("ccy"),
                                        rs.getDate("ab_expirity"),
                                        rs.getString("f_names"),
                                        rs.getString("surname"),
                                        rs.getString("city"),
                                        rs.getString("street"),
                                        rs.getString("tranz_acct"),
                                        rs.getString("card"),
                                        rs.getString("status1"),
                                        rs.getString("status2"),
                                        rs.getString("product"),
                                        rs.getString("name"),
                                        //rs.getString("product")
                                        rs.getInt("agreement_key"),
                                        rs.getString("agre_nom"),
                                        rs.getString("contract"),
                                        rs.getString("stop_cause")
                        ));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
    
    
    
    
    public static AccInfo getAccInfoByCard(String Card_acct)  {

        //List<AccInfo> list = new ArrayList<AccInfo>();
    	AccInfo res = null;
        Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
               // PreparedStatement ps = c.prepareStatement("SELECT * FROM IZD_ACC_INFO where bank_c='01' and client=?");
                
           /*     PreparedStatement ps = c.prepareStatement("SELECT a.account_no, a.client,a.card_acct,a.bank_c,a.groupc,a.ctime,a.ac_status,a.cl_status,a.acc_prty,"+
                "a.c_accnt_type,a.ccy,c.expiry1 ab_expirity,a.f_names,a.surname,a.city,a.street,a.tranz_acct,a.product,c.card,c.status1,c.status2,c.cond_set "+
                "FROM IZD_ACC_INFO a,izd_cards c "+
                "where c.cl_acct_key=a.tab_key and a.card_acct=? and a.bank_c='01' and a.groupc='02' and c.bank_c='01' and c.groupc='02'");
                */
                
                PreparedStatement ps = c.prepareStatement(
   					 "select "+
					     "ac.account_no, "+
					     "ac.card_acct, "+
					     "ac.ac_status, "+
					     "ac.cl_status, "+
					     "ac.acc_prty, "+
					     "ac.c_accnt_type, "+
					     "ac.ccy, "+
					     "ac.f_names, "+
					     "ac.surname, "+
					     "ac.city, "+
					     "ac.street, "+
					     "ac.tranz_acct, "+
					     ""+
					     "t.card, "+
					     "t.status1, "+
					     "t.status2, "+
					     "t.cond_set, "+
					     "t.client_id client, "+
					     "t.ctime, "+
					     "t.agreement_key, "+
					     "t.expiry1 ab_expirity, "+
					     "t.stop_cause, "+
					     ""+
					     "a.product, " +
					     "a.contract, "+
					     "p.name "+
					     ""+
					"from izd_cards t, "+
					     "izd_agreement a, "+
					     "izd_offered_products p, "+
					     "izd_acc_info ac "+
					     ""+
					"where a.agre_nom = t.agreement_key "+
					      "and t.bank_c = '01' "+
					      "and t.groupc = '02' "+
					      "and a.bank_c = '01' "+
					      "and a.groupc = '02' "+
					      "and p.bank_c = '01' "+
					      "and p.groupc = '02' "+
					      "and ac.bank_c = '01' "+
					      "and ac.groupc = '02' "+
					      "and p.code = a.product "+
					      "and t.cl_acct_key=ac.tab_key "+
					      "and t.status1='0' "+
					      "and ac.card_acct = ?"
					      );
                
                
                ps.setString(1, Card_acct);
                ResultSet rs = ps.executeQuery();
               
                        		res = new AccInfo(
                                        rs.getLong("account_no"),
                                        rs.getString("client"),
                                        rs.getString("card_acct"),
                                      //  rs.getString("bank_c"),
                                      //  rs.getString("groupc"),
                                        rs.getDate("ctime"),
                                        rs.getString("ac_status"),
                                        rs.getString("cl_status"),
                                        rs.getString("acc_prty"),
                                        rs.getString("c_accnt_type"),
                                        rs.getString("ccy"),
                                        rs.getDate("ab_expirity"),
                                        rs.getString("f_names"),
                                        rs.getString("surname"),
                                        rs.getString("city"),
                                        rs.getString("street"),
                                        rs.getString("tranz_acct"),
                                        rs.getString("card"),
                                        rs.getString("status1"),
                                        rs.getString("status2"),
                                        rs.getString("cond_set"),
                                        rs.getInt("agreement_key"),
                                        rs.getString("contract"),
                                        rs.getString("stop_cause")
                                        //rs.getString("product")
                        );
                
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return res;

}
    
    
    
    
 
    
    
    public static TietoCardSetting getTietoCardSetting(int tietocardsettingId, String alias) {

        TietoCardSetting tietocardsetting = new TietoCardSetting();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_TIETO_CARD_SETTING WHERE code=?");
                ps.setInt(1, tietocardsettingId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        tietocardsetting = new TietoCardSetting();
                        
                        tietocardsetting.setCode(rs.getInt("code"));
                        tietocardsetting.setName(rs.getString("name"));
                        tietocardsetting.setBin(rs.getString("bin"));
                        tietocardsetting.setRisk_level(rs.getString("risk_level"));
                        tietocardsetting.setFinancial_conditions(rs.getString("financial_conditions"));
                        tietocardsetting.setMinimum_balance(rs.getBigDecimal("minimum_balance"));
                        tietocardsetting.setId_chip_app(rs.getBigDecimal("id_chip_app"));
                        tietocardsetting.setId_order_account(rs.getString("id_order_account"));
                        tietocardsetting.setGroup_c(rs.getString("groupc"));
                        tietocardsetting.setBank_c(rs.getString("bank_c"));
                        tietocardsetting.setCard_condition(rs.getString("card_condition"));
                        tietocardsetting.setDesign_id(rs.getBigDecimal("design_id"));
                        tietocardsetting.setAcc_name_postfix(rs.getString("acc_postfix"));
                        tietocardsetting.setHo_acc_group(rs.getInt("acc_group_head"));
                        tietocardsetting.setBr_acc_group(rs.getInt("acc_group_fil"));
                        tietocardsetting.setId_order_max(rs.getString("id_order_max"));
                        tietocardsetting.setAllow_multiple_card_per_acc(rs.getInt("allow_multiple_cards_per_acc"));
                        tietocardsetting.setRange_id(rs.getInt("range_id"));
                        //tietocardsetting.setAcc_name_postfix(acc_name_postfix)(rs.getInt("acc_group_fil"));
                }
        } catch (Exception e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return tietocardsetting;
}
    
    
    public static String getkass_acc(String branch, String alias) {

        String res = "";
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select acc.account res from BF_TR_ACC acc, BF_TR_TEMPLATE t " +
                		"where t.operation_id = 1 " +
                		"and acc.acc_template_id = t.acc_dt " +
                		"and ROWNUM = 1 " +
                		"and acc.branch = ? "+
                		"order by t.ord");
                ps.setString(1, branch);
                
                ResultSet rs = ps.executeQuery();
                rs.next();
                res = rs.getString("res");
        } catch (Exception e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return res;
}
    
    
	public static String check_user(String alias, String Branch_id, String User_id)
    {
		String res="";
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		
    		CallableStatement proc = c.prepareCall("{ ? = call BF.CHECK_CUSTOMER(?, ?) }");
    		
			proc.registerOutParameter(1, Types.VARCHAR);
			
			proc.setString(2, Branch_id);
			proc.setString(3, User_id);
			
			proc.execute();
			
			
			res = (String)proc.getObject(1);
			
			//System.out.println("id:"+res);
			
    		
    	} catch (Exception e) {
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
              //  e.printStackTrace();
                res="";
        } finally {
                ConnectionPool.close(c);
        }
        return res;
    }

	public static Res check_allowed_card_action(int deal_group, int deal_id, int action_id, String card, String alias)
    {
		Res res = new Res(0,"");
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		
    		CallableStatement proc = c.prepareCall("{call BF.CHECK_ALLOWED_CARD_ACTION(?, ?, ?, ?) }");
			
			proc.setInt(1, deal_group);
			proc.setInt(2, deal_id);
			proc.setInt(3, action_id);
			proc.setString(4, card);
			
			proc.execute();
			
    	} catch (Exception e) {
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
                res.setCode(-1);
                res.setName(e.getMessage());
        } finally {
                ConnectionPool.close(c);
        }
        return res;
    }
	
	public static Res check_card(String new_card_type, List<AccInfo> cards, String alias)
	{
		Res res = new Res(0,"");
		String qstring="";
		
		if (cards.size()!=0)
		{
			for (int i = 0; i < cards.size()-1; i++) {
				qstring+=cards.get(i).getCard_type();
				qstring+=",";
			}
			qstring+=cards.get(cards.size()-1).getCard_type();
		}
		else qstring = null;
		//System.out.println("argument:"+qstring);
	
	//	String res="";
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		
    		CallableStatement proc = c.prepareCall("{ call BF.CHECK_OPEN_CARD(?,?) }");
    		
    		proc.setString(1, new_card_type);
			proc.setString(2, qstring);
			
			proc.execute();
			
    		
    	} catch (Exception e) {
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
              //  e.printStackTrace();
    			res.setCode(-1);
    			res.setName(e.getMessage());
                return res;
        } finally {
                ConnectionPool.close(c);
        }		
		return res;
	}

	public static List<Action> getActions(int userid, String user_branch, String alias) {

        List<Action> list = new ArrayList<Action>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias );
                //System.out.println("select a.* from bf_actions a where a.mid=11 and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="+userid+" and r.actionid=a.id)");
                      
                PreparedStatement ps = c.prepareStatement("Select act.*"+

															" From   BF_USER_ROLES   UR,"+
															       " BF_ROLE_ACTIONS RA,"+
															       " bf_actions act"+
															
															" Where  UR.USERID = ?"+
															       " and UR.BRANCH = ?"+
															       " and RA.ROLEID = UR.ROLEID"+
															       " and act.id = ra.actionid"+
															       " and act.deal_id = ra.deal_id"+
															       " and RA.MID = 11"+
															       " and act.mid = RA.MID");
                //ps.setInt(1, state_begin);
                //ps.setInt(2, deal_id);
                ps.setInt(1, userid);
                ps.setString(2, user_branch);
                ResultSet rs = ps.executeQuery();
              
                while (rs.next()) {
                        list.add(new Action(
                                rs.getInt("id"),
                                rs.getInt("mid"),
                                rs.getString("name"),
                                rs.getString("icon"),
                                rs.getInt("deal_id"),
                                rs.getInt("rep_type_id")));

                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                LtLogger.getLogger().error(CheckNull.getPstr(e));
                
        } finally {
                ConnectionPool.close(c);
        }
        return list;

    }
	
	public static String getCountryNameISO3(String ISO3)  {

        String res = null;
        Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
                PreparedStatement ps = c.prepareStatement("SELECT name_en FROM base_country where iso3 = ?");
                ps.setString(1, ISO3);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                	res = rs.getString("name_en");
                }
                if (res == null) res = ISO3;
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return res;

}
	
	public static Agreement getCardAgreement(int agre_nom)
	{
		Agreement res = new Agreement();
		Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM izd_agreement t where t.agre_nom = ?");
                ps.setInt(1, agre_nom);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                	res.setAGRE_NOM(agre_nom);
                	res.setB_BR_ID(rs.getInt("b_br_id"));
                	res.setBANK_CODE(rs.getString("BANK_CODE"));
                	res.setBINCOD(rs.getString("BINCOD"));
                	res.setBRANCH(rs.getString("BRANCH"));
                	res.setCITY(rs.getString("CITY"));
                	res.setCLIENT(rs.getString("CLIENT"));
                	res.setCOMENT(rs.getString("COMENT"));
                	res.setCONTRACT(rs.getString("CONTRACT"));
                	res.setCOUNTRY(rs.getString("COUNTRY"));
                	res.setCTIME(rs.getDate("CTIME"));
                	res.setDISTRIB_MODE(rs.getString("DISTRIB_MODE"));
                	res.setE_MAILS(rs.getString("E_MAILS"));
                	res.setENROLLED(rs.getDate("ENROLLED"));
                	res.setIN_FILE_NUM(rs.getInt("IN_FILE_NUM"));
                	res.setISURANCE_TYPE(rs.getString("ISURANCE_TYPE"));
                	res.setOFFICE(rs.getString("OFFICE"));
                	res.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                	res.setPOST_IND(rs.getString("POST_IND"));
                	res.setPRODUCT(rs.getString("PRODUCT"));
                	res.setREP_LANG(rs.getString("REP_LANG"));
                	res.setRISK_LEVEL(rs.getString("RISK_LEVEL"));
                	res.setSTATUS(rs.getString("STATUS"));
                	res.setSTREET(rs.getString("STREET"));
                	res.setU_COD4(rs.getString("U_COD4"));
                	res.setU_CODE5(rs.getString("U_CODE5"));
                	res.setU_CODE6(rs.getString("U_CODE6"));
                	res.setU_FIELD3(rs.getString("U_FIELD3"));
                	res.setU_FIELD4(rs.getString("U_FIELD4"));
                	res.setUSRID(rs.getString("USRID"));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
		return res;
	}
	
	public static String get_tieto_card_name(String card, String alias)
	{
		String res = null;
    	Connection c = null;
    	
    	try
		{
			c = ConnectionPool.getTConnection();
			
			PreparedStatement ps = c.prepareStatement("select * from izd_cards t where t.card = ? and t.bank_c = ? and t.groupc = ?");
			ps.setString(1, card);
			ps.setString(2, ConnectionPool.getValue("BANK_C", alias));
			ps.setString(3, ConnectionPool.getValue("GROUPC", alias));
			
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("card_name");
			}
			
		} catch (SQLException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
    	
    	return res;
	}
	
	
	public static String get_report_file(int deal_id, int action_id, String alias)
	{
		String res = new String();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select t.report res from BF_TIETO_TR_ACTION_REPORT t " +
					" where t.deal_id = ? and t.action_id = ?");
			ps.setInt(1, deal_id);
			ps.setInt(2, action_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getString("res");
			
		} catch (SQLException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static HashMap<String, String> get_client_ti_acc(String client,
			String product)
	{
		HashMap<String, String> res = new HashMap<String, String>();
		
		Connection c = null;

        try {
                c = ConnectionPool.getTConnection();
                
                PreparedStatement ps = c.prepareStatement(
   					 "select "+
					     "ac.account_no, "+
					     "ac.card_acct, "+
					     "ac.ac_status, "+
					     "ac.cl_status, "+
					     "ac.acc_prty, "+
					     "ac.c_accnt_type, "+
					     "ac.ccy, "+
					     "ac.f_names, "+
					     "ac.surname, "+
					     "ac.city, "+
					     "ac.street, "+
					     "ac.tranz_acct, " +
					     "a.agre_nom, "+
					     ""+
					     "t.card, "+
					     "t.status1, "+
					     "t.status2, "+
					     "t.cond_set, "+
					     "t.client_id client, "+
					     "t.ctime, "+
					     "t.expiry1 ab_expirity, " +
					     "t.agreement_key, "+
					     ""+
					     "a.product, " +
					     "a.contract, "+
					     "p.name "+
					     ""+
					"from izd_cards t, "+
					     "izd_agreement a, "+
					     "izd_offered_products p, "+
					     "izd_acc_info ac "+
					     ""+
					"where a.agre_nom = t.agreement_key "+
					      "and t.bank_c = '01' "+
					      "and t.groupc = '02' "+
					      "and a.bank_c = '01' "+
					      "and a.groupc = '02' "+
					      "and p.bank_c = '01' "+
					      "and p.groupc = '02' "+
					      "and ac.bank_c = '01' "+
					      "and ac.groupc = '02' "+
					      "and p.code = a.product "+
					      "and t.cl_acct_key=ac.tab_key "+
					      "and t.client_id = ? " +
					      "and a.product = ?"
					      );
                
                ps.setString(1, client);
                ps.setString(2, product);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                	if (!(res.containsKey(rs.getString("tranz_acct"))))
                	res.put(rs.getString("tranz_acct"), "");
                }
        } catch (SQLException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
                
		return res;
	}
	
	public static String get_Curr_acc(String branch, String alias)
    {
            String res = null;
            Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("select t.account res from bf_tr_acc t where t.acc_template_id=2 and t.acc_mfo = ?");
                ps.setString(1, branch);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                        res = rs.getString("res");
                }
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
            return res;
    }
	
	public static String[] getCourse(String alias)
    {
            String[] res = new String[2];
            Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("select * from (select t.course outcourse from ss_course t where t.course_type = 4 and t.currency = '840' and t.act = 'A') out_course,\r\n" + 
                		"(select t.course incourse from ss_course t where t.course_type = 5 and t.currency = '840' and t.act = 'A') in_course");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                        res[0] = rs.getString("outcourse");
                        res[1] = rs.getString("incourse");
                }
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
            return res;
    }
	
	public static String getUser_name(int user_id, String branch, String alias)
    {
            String res = null;
            Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("select user_name_trans from bf_users_fullname_transleat where user_id = ? and branch = ?");
                ps.setString(1, user_id+"");
                ps.setString(2, branch);
                ResultSet rs = ps.executeQuery();
                //rs.next();
                if (rs != null) {
                	  while (rs.next()) {
                		  res = rs.getString("user_name_trans");
                	  }
                }
        } catch (SQLException e) {
        	e.printStackTrace();
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
            return res;
    }
	
	static double FMod(double x, double y)
	{
	  return x - Math.floor(x / y) * y;
	}

	 public static String F2MoneyUz(double d, String cur_name, String cur2_name)
		{
			final double[] T1 = {1000000000000.00, 1000000000.00, 1000000.00, 1000.00, 1.00};
			final String[] T2 = {"trillion", "milliard", "million", "ming", cur_name};
			final String[] T3 = {"", "", "", "", ""};
			final String[] T4 = {"", "", "", "", ""};
			final String[] T5 = {"", "", "", "", ""};
			final double[] T6 = {900, 800, 700, 600, 500, 400, 300, 200, 100, 90,
					80, 70, 60, 50, 40, 30, 20, 19, 18, 17, 16, 15,
					14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
			final String[] T7 = {"toqqiz yuz", "sakkiz yuz", "yetti yuz", "olti yuz", "besh yuz", "tort yuz",
					"uch yuz", "ikki yuz", "bir yuz", "toqson", "sakson", "yetmish",
					"oltmish", "ellik", "qirq", "ottiz", "yigirma",
					"on toqqiz", "on sakkiz", "on yetti", "on olti",
					"o'n besh", "on tort", "on uch", "on ikki",
					"on bir", "on", "toqqiz", "sakkiz", "yetti", "olti",
					"besh", "tort", "uch", "ikki", "bir"};
			String s = "";
			int i = 0, j = 0, k = 0;
			double n = 0.0, n1 = 0.0, t = 0.0;
			
			if (d > 1000000000000000.00)
				return Double.toString(d);
			else
			{
				n = Math.abs(d);
				k = (int)Math.round(FMod(n, 1) * 100);
				n = Math.floor(n);
				if (d < 0)
					s += "minus ";
				if (n == 0)
					s += "nol ";
				i = 0;
				while (n > 0)
				{
					if (n >= T1[i])
					{
						t = Math.floor(n / T1[i]);
						n = FMod(n, T1[i]);
						j = 0;
						while (t > 0)
						{
							if (t >= T6[j])
							{
								n1 = t;
								t = t - T6[j];
								if (i == 3 && (j == 34 || j == 35))
								{
									if (j == 34)
										s += "ikki";
									else
										s += "bir";
								}
								else
									s += T7[j];
								s += " ";
							}
							j++;
						}
						s += T2[i];
						if (n1 == 1)
							s += T3[i];
						else if (n1 < 5)
							s += T4[i];
						else
							s += T5[i];
						s += " ";
					}
					i++;
				}
				if (i != 5)
					s += cur_name+" ";
				s += Integer.toString(k) + " "+cur2_name+".";
			}
			return s = s.substring(0, 1).toUpperCase() + s.substring(1);
		}

	 public static String SeqTieto(String alias)  {
		 String seq = "";
         Connection c = null;
         PreparedStatement ps = null;
         ResultSet rs=null;
         try {
                 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("SELECT SEQ_TIETO.NEXTVAL id FROM DUAL");
                 rs = ps.executeQuery();
                 if (rs.next()) {
                	 seq = rs.getString("id");
                	 StringBuffer s1 = new StringBuffer();
                		if(seq.length()<8) {
                			for (int i = 0; i < 8-seq.length(); i++) {
                				s1.append("0");
                			}
                			seq = s1.append(seq).toString();
                		} 
                 }
               
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
         	ConnectionPool.close(c);
         }
         return seq;
 }
	 
	 public static String SeqTietoDebit(String alias)  {
		 String seq = "";
         Connection c = null;
         PreparedStatement ps = null;
         ResultSet rs=null;
         try {
                 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("SELECT SEQ_TIETO_DEBIT.NEXTVAL id FROM DUAL");
                 rs = ps.executeQuery();
                 if (rs.next()) {
                	 seq = rs.getString("id");
                	 StringBuffer s1 = new StringBuffer();
                		if(seq.length()<8) {
                			for (int i = 0; i < 8-seq.length(); i++) {
                				s1.append("0");
                			}
                			seq = s1.append(seq).toString();
                		} 
                 }
                 
                
                 
                 
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
         	ConnectionPool.close(c);
         }
         return seq;
 }
	 
	 public static Ti_Duplicate createDuplicate(Ti_Duplicate duplicate, String alias)  {
         Connection c = null;
         PreparedStatement ps = null;
         try {
        	 	 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("INSERT INTO ti_duplicate (id, branch, bank_name, name2, my_branch, inclient_name1, inclient_name2, inclient_name3, v_date, "
                 		+ "post_address, esumma6, summa6, uzpsumma6, uzpesumma6, psumma6, karta_num, deal_id, act_id) VALUES (getTietoId(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setString(1,duplicate.getMy_branch());
                 ps.setString(2,duplicate.getBranch());
                 ps.setString(3,duplicate.getBank_name());
                 ps.setString(4,duplicate.getName2());
                 ps.setString(5,duplicate.getMy_branch());
                 ps.setString(6,duplicate.getInclient_name1());
                 ps.setString(7,duplicate.getInclient_name2());
                 ps.setString(8,duplicate.getInclient_name3());
                 ps.setString(9,duplicate.getV_date());
                 ps.setString(10,duplicate.getPost_address());
                 ps.setString(11,duplicate.getEsumma6());
                 ps.setString(12,duplicate.getSumma6());
                 ps.setString(13,duplicate.getUzpsumma6());
                 ps.setString(14,duplicate.getUzpesumma6());
                 ps.setString(15,duplicate.getPsumma6());
                 ps.setString(16,duplicate.getKarta_num());
                 ps.setInt(17,duplicate.getDeal_id());
                 ps.setInt(18,duplicate.getAct_id());
                 
                 ps.executeUpdate();
                // c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return duplicate;
 }
	 
	 public static Ti_Duplicate createDuplicateFilial(Ti_Duplicate duplicate, String alias)  {
         Connection c = null;
         PreparedStatement ps = null;
         try {
        	 	 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("INSERT INTO ti_duplicate_filial (id, branch, bank_name, name2, my_branch, inclient_name1, inclient_name2, inclient_name3, v_date, "
                 		+ "post_address, esumma6, summa6, uzpsumma6, uzpesumma6, psumma6, karta_num, deal_id, act_id) VALUES (getTietoIdFilial(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setString(1,duplicate.getMy_branch());
                 ps.setString(2,duplicate.getBranch());
                 ps.setString(3,duplicate.getBank_name());
                 ps.setString(4,duplicate.getName2());
                 ps.setString(5,duplicate.getMy_branch());
                 ps.setString(6,duplicate.getInclient_name1());
                 ps.setString(7,duplicate.getInclient_name2());
                 ps.setString(8,duplicate.getInclient_name3());
                 ps.setString(9,duplicate.getV_date());
                 ps.setString(10,duplicate.getPost_address());
                 ps.setString(11,duplicate.getEsumma6());
                 ps.setString(12,duplicate.getSumma6());
                 ps.setString(13,duplicate.getUzpsumma6());
                 ps.setString(14,duplicate.getUzpesumma6());
                 ps.setString(15,duplicate.getPsumma6());
                 ps.setString(16,duplicate.getKarta_num());
                 ps.setInt(17,duplicate.getDeal_id());
                 ps.setInt(18,duplicate.getAct_id());
                 
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return duplicate;
 }
	 
	 public static Ti_DuplicateDebit createDuplicateDebit(Ti_DuplicateDebit duplicate_debit, String alias)  {
         Connection c = null;
         PreparedStatement ps = null;
         try {
        	 	 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("INSERT INTO ti_duplicate_debit (id, branch, bank_name, name2, my_branch, inclient_name1, inclient_name2, inclient_name3, v_date, "
                 		+ "post_address, esumma6, summa6, uzpsumma6, uzpesumma6, psumma6, karta_num, deal_id, act_id) VALUES (getTietoIdDebit(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setString(1,duplicate_debit.getMy_branch());
                 ps.setString(2,duplicate_debit.getBranch());
                 ps.setString(3,duplicate_debit.getBank_name());
                 ps.setString(4,duplicate_debit.getName2());
                 ps.setString(5,duplicate_debit.getMy_branch());
                 ps.setString(6,duplicate_debit.getInclient_name1());
                 ps.setString(7,duplicate_debit.getInclient_name2());
                 ps.setString(8,duplicate_debit.getInclient_name3());
                 ps.setString(9,duplicate_debit.getV_date());
                 ps.setString(10,duplicate_debit.getPost_address());
                 ps.setString(11,duplicate_debit.getEsumma6());
                 ps.setString(12,duplicate_debit.getSumma6());
                 ps.setString(13,duplicate_debit.getUzpsumma6());
                 ps.setString(14,duplicate_debit.getUzpesumma6());
                 ps.setString(15,duplicate_debit.getPsumma6());
                 ps.setString(16,duplicate_debit.getKarta_num());
                 ps.setInt(17,duplicate_debit.getDeal_id());
                 ps.setInt(18,duplicate_debit.getAct_id());
                 
                 ps.executeUpdate();
                // c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return duplicate_debit;
 }
	 
	 public static Ti_DuplicateDebit createDuplicateDebitFilial(Ti_DuplicateDebit duplicate_debit, String alias)  {
         Connection c = null;
         PreparedStatement ps = null;
         try {
        	 	 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("INSERT INTO ti_duplicate_debit_filial (id, branch, bank_name, name2, my_branch, inclient_name1, inclient_name2, inclient_name3, v_date, "
                 		+ "post_address, esumma6, summa6, uzpsumma6, uzpesumma6, psumma6, karta_num, deal_id, act_id) VALUES (getTietoIdDebitFilial(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setString(1,duplicate_debit.getMy_branch());
                 ps.setString(2,duplicate_debit.getBranch());
                 ps.setString(3,duplicate_debit.getBank_name());
                 ps.setString(4,duplicate_debit.getName2());
                 ps.setString(5,duplicate_debit.getMy_branch());
                 ps.setString(6,duplicate_debit.getInclient_name1());
                 ps.setString(7,duplicate_debit.getInclient_name2());
                 ps.setString(8,duplicate_debit.getInclient_name3());
                 ps.setString(9,duplicate_debit.getV_date());
                 ps.setString(10,duplicate_debit.getPost_address());
                 ps.setString(11,duplicate_debit.getEsumma6());
                 ps.setString(12,duplicate_debit.getSumma6());
                 ps.setString(13,duplicate_debit.getUzpsumma6());
                 ps.setString(14,duplicate_debit.getUzpesumma6());
                 ps.setString(15,duplicate_debit.getPsumma6());
                 ps.setString(16,duplicate_debit.getKarta_num());
                 ps.setInt(17,duplicate_debit.getDeal_id());
                 ps.setInt(18,duplicate_debit.getAct_id());
                 
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return duplicate_debit;
 }
	 
	 public static Ti_Duplicate_Other createDuplicateOther(Ti_Duplicate_Other duplicate_other, String alias)  {
         Connection c = null;
         PreparedStatement ps = null;
         try {
        	 	 c = ConnectionPool.getConnection(alias);
                 ps = c.prepareStatement("INSERT INTO ti_duplicate_other (id, branch, report_file, name2, my_branch, inclient_name1, v_date, "
                 		+ "deal_id, act_id) VALUES (?,?,?,?,?,?,?,?,?)");
                 
                 ps.setInt(1,duplicate_other.getId());
                 ps.setString(2,duplicate_other.getBranch());
                 ps.setString(3,duplicate_other.getReport_file());
                 ps.setString(4,duplicate_other.getName2());
                 ps.setString(5,duplicate_other.getMy_branch());
                 ps.setString(6,duplicate_other.getInclient_name1());
                 ps.setString(7,duplicate_other.getV_date());
                 ps.setInt(8,duplicate_other.getDeal_id());
                 ps.setInt(9,duplicate_other.getAct_id());
                 
                 ps.executeUpdate();
                // c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return duplicate_other;
 }

	 public static Ti_Duplicate getDuplicate(int id, String alias)
	    {
		 		Ti_Duplicate d = null;
	            Connection c = null;

	        try {
	        		System.out.println("seq = "+id);
	                c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select * from ti_duplicate where id = ?");
	                ps.setInt(1, id);
	                ResultSet rs = ps.executeQuery();
	                rs.next();
	                
	                System.out.println(rs.getInt("id"));
	                System.out.println(rs.getString("branch"));
	                System.out.println(rs.getString("bank_name"));
	                System.out.println(rs.getString("name2"));
	                System.out.println(rs.getString("my_branch"));
	                System.out.println(rs.getString("inclient_name1"));
	                System.out.println(rs.getString("inclient_name2"));
	                System.out.println(rs.getString("inclient_name3"));
	                System.out.println(rs.getString("v_date"));
	                System.out.println(rs.getString("post_address"));
	                System.out.println(rs.getString("esumma6"));
	                System.out.println(rs.getString("summa6"));
	                System.out.println(rs.getString("psumma6"));
	                System.out.println(rs.getString("uzpsumma6"));
	                System.out.println(rs.getString("uzpesumma6"));
	                System.out.println(rs.getString("karta_num"));
	                System.out.println(rs.getInt("deal_id"));
	                System.out.println(rs.getInt("act_id"));
	                
	                        d.setId(id);
	                        d.setBranch(rs.getString("branch"));
	                        d.setBank_name(rs.getString("bank_name"));
	                        d.setName2(rs.getString("name2"));
	                        d.setMy_branch(rs.getString("my_branch"));
	                        d.setInclient_name1(rs.getString("inclient_name1"));
	                        d.setInclient_name2(rs.getString("inclient_name2"));
	                        d.setInclient_name3(rs.getString("inclient_name3"));
	                        d.setV_date(rs.getString("v_date"));
	                        d.setPost_address(rs.getString("post_address"));
	                        d.setEsumma6(rs.getString("esumma6"));
	                        d.setSumma6(rs.getString("summa6"));
	                        d.setPsumma6(rs.getString("psumma6"));
	                        d.setUzpsumma6(rs.getString("uzpsumma6"));
	                        d.setUzpesumma6(rs.getString("uzpesumma6"));
	                        d.setKarta_num(rs.getString("karta_num"));
	                        d.setDeal_id(rs.getInt("deal_id"));
	                        d.setAct_id(rs.getInt("act_id"));
	                        
	                
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return d;
	    }
	 
	 public static Ti_DuplicateDebit getDuplicateDebit(int id, String alias)
	    {
		 Ti_DuplicateDebit dd = null;
	            Connection c = null;

	        try {
	        		System.out.println("seq = "+id);
	                c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select * from ti_duplicate_debit where id = ?");
	                ps.setInt(1, id);
	                ResultSet rs = ps.executeQuery();
	                rs.next();
	                
	                dd.setId(rs.getInt("id"));
	                dd.setBranch(rs.getString("branch"));
                    dd.setBank_name(rs.getString("bank_name"));
                    dd.setName2(rs.getString("name2"));
                    dd.setMy_branch(rs.getString("my_branch"));
                    dd.setInclient_name1(rs.getString("inclient_name1"));
                    dd.setInclient_name2(rs.getString("inclient_name2"));
                    dd.setInclient_name3(rs.getString("inclient_name3"));
                    dd.setV_date(rs.getString("v_date"));
                    dd.setPost_address(rs.getString("post_address"));
                    dd.setEsumma6(rs.getString("esumma6"));
                    dd.setSumma6(rs.getString("summa6"));
                    dd.setPsumma6(rs.getString("psumma6"));
                    dd.setUzpsumma6(rs.getString("uzpsumma6"));
                    dd.setUzpesumma6(rs.getString("uzpesumma6"));
                    dd.setKarta_num(rs.getString("karta_num"));
                    dd.setDeal_id(rs.getInt("deal_id"));
                    dd.setAct_id(rs.getInt("act_id"));
	                
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return dd;
	    }
	 
	 
	 public static int getDuplicateMaxId(String alias, String branch)
	    {
		 		Connection c = null;
	            int maxId = 0;
	        try {
	        		c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select max(id) id from ti_duplicate where my_branch = ?");
	                ps.setString(1,branch);
	                ResultSet rs = ps.executeQuery();
	                rs.next();

	                        maxId = rs.getInt("id");
	                        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return maxId;
	    }
	 
	 public static int getDuplicateFilialMaxId(String alias, String branch)
	    {
		 		Connection c = null;
	            int maxId = 0;
	        try {
	        		c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select max(id) id from ti_duplicate_filial where my_branch = ? ");
	                ps.setString(1,branch);
	                ResultSet rs = ps.executeQuery();
	                rs.next();

	                        maxId = rs.getInt("id");
	                        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return maxId;
	    }
	 
	 public static int getDuplicateDebitMaxId(String alias, String branch)
	    {
		 		Connection c = null;
	            int maxId = 0;
	        try {
	        		c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select max(id) id from ti_duplicate_debit where my_branch = ?");
	                ps.setString(1, branch);
	                ResultSet rs = ps.executeQuery();
	                rs.next();

	                        maxId = rs.getInt("id");
	                        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return maxId;
	    }
	 
	 public static int getDuplicateDebitFilialMaxId(String alias, String branch)
	    {
		 		Connection c = null;
	            int maxId = 0;
	        try {
	        		c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select max(id) id from ti_duplicate_debit_filial where my_branch = ? ");
	                ps.setString(1,branch);
	                ResultSet rs = ps.executeQuery();
	                rs.next();

	                        maxId = rs.getInt("id");
	                        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return maxId;
	    }
	 
	 public static int getDuplicateOtherMaxId(String alias)
	    {
		 		Connection c = null;
	            int maxId = 0;
	        try {
	        		c = ConnectionPool.getConnection(alias);
	                PreparedStatement ps = c.prepareStatement("select max(id) id from ti_duplicate_other");
	                ResultSet rs = ps.executeQuery();
	                rs.next();

	                        maxId = rs.getInt("id");
	                        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	                ConnectionPool.close(c);
	        }
	            return maxId;
	    }




	public static void setV_bankc(String v_bankc) {
		TclientService.v_bankc = v_bankc;
	}




	public static String getV_bankc() {
		if (v_bankc == null) {
			v_bankc = ConnectionPool.getValue("BANK_C");
			return v_bankc;
		} else {
		  return v_bankc;
		}
	}




	public static void setV_groupc(String v_groupc) {
		TclientService.v_groupc = v_groupc;
	}



	public static String getV_groupc() {
		if (v_groupc == null) {
			v_groupc = ConnectionPool.getValue("GROUPC");
			return v_groupc;
		} else {
		  return v_groupc;
		}
	}




	public static void setV_HO(String v_HO) {
		TclientService.v_HO = v_HO;
	}




	public static String getV_HO() {
		if (v_HO == null) {
			v_HO = ConnectionPool.getValue("HO");
			return v_HO;
		} else {
		  return v_HO;
		}
	
	}
	 
}
