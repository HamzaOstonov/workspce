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
import java.util.List;

import com.is.ConnectionPool;
import com.is.LtLogger;
//import com.is.customer.TietoCustomer;
//import com.is.customer.CustomerService;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class TclientService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? and t.bank_c=? ";
    private static String msql ="SELECT * FROM izd_clients cl";


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
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

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
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

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
					     "ac.tranz_acct, "+
					     ""+
					     "t.card, "+
					     "t.status1, "+
					     "t.status2, "+
					     "t.cond_set, "+
					     "t.client_id client, "+
					     "t.ctime, "+
					     "t.expiry1 ab_expirity, "+
					     ""+
					     "a.product, "+
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
                                        rs.getString("name")
                        ));
                }
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
								     "ac.tranz_acct, "+
								     ""+
								     "t.card, "+
								     "t.status1, "+
								     "t.status2, "+
								     "t.cond_set, "+
								     "t.client_id client, "+
								     "t.ctime, "+
								     "t.expiry1 ab_expirity, "+
								     ""+
								     "a.product, "+
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
                                        rs.getString("name")//,
                                        //rs.getString("product")
                        ));
                }
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
					     "t.expiry1 ab_expirity, "+
					     ""+
					     "a.product, "+
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
                                        rs.getString("cond_set")
                                        //rs.getString("product")
                        );
                
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
                        //tietocardsetting.setAcc_name_postfix(acc_name_postfix)(rs.getInt("acc_group_fil"));
                }
        } catch (Exception e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
              //  com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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
                res.setCode(-1);
                res.setName(e.getMessage());
        } finally {
                ConnectionPool.close(c);
        }
        return res;
    }
	
	public static boolean check_card(String new_card_type, List<AccInfo> cards, String alias)
	{
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
              //  com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                return false;
        } finally {
                ConnectionPool.close(c);
        }		
		return true;
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
															       " bf_actions      act"+
															
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return res;

}
	
}
