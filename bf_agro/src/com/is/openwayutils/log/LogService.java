package com.is.openwayutils.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.openwayutils.account.Account;
import com.is.openway.trpay.TrPay;
import com.is.openway.trpay.TrPayFilter;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.FilterField;

	public class LogService
	{
		/*private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by ID desc";
	    private static String msql ="SELECT * FROM bf_user_actions_log P ";
	*/
		
		private static String psql1 ="Select TB2.* From   (Select ROWNUM AS RN, TB1.* From (";
	    private static String psql2 ="ORDER  BY UAL.ACTION_DATE DESC) TB1) TB2 where  TB2.RN <= ? and TB2.RN >= ?";
	    private static String msql ="Select UAL.* From   BF_USER_ACTIONS_LOG UAL ";
		
	    private static String getCond(List<FilterField> flfields, LogFilter filter){
	        if(flfields.size()>0){
	                return " and ";
	        }else
	        return " where ";
	}
    
    private static List<FilterField> getFilterFields(LogFilter filter)
    {
    	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        List<FilterField> flfields = new ArrayList<FilterField>();

        
	    if(!CheckNull.isEmpty(filter.getBranch())){
	            flfields.add(new FilterField(getCond(flfields, filter)+ " branch=? ",filter.getBranch()));
	    }
	    if(!CheckNull.isEmpty(filter.getUname())){
	            flfields.add(new FilterField(getCond(flfields, filter)+ " UPPER(user_name) like(?) ",filter.getUname().toUpperCase()));
	    }
	    if(!CheckNull.isEmpty(filter.getEntity_id())){
            flfields.add(new FilterField(getCond(flfields, filter)+ " UPPER(entity_id) like(?) ",filter.getEntity_id().toUpperCase()));
	    }
	    if(!CheckNull.isEmpty(filter.getFrom_date())){
            flfields.add(new FilterField(getCond(flfields, filter)+ " action_date>=(TO_DATE(?, 'dd.MM.yyyy')) ",df.format(filter.getFrom_date())));
	    }
	    if(!CheckNull.isEmpty(filter.getTo_date())){
            flfields.add(new FilterField(getCond(flfields, filter)+ " action_date<=(TO_DATE(?, 'dd.MM.yyyy')) ",df.format(filter.getTo_date())));
	    }
	    
	    return flfields;
    }
	    
    public static int getCount(LogFilter filter, String alias)
    {
        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM bf_user_actions_log ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias );
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
    
    public static List<Log> getLogFl(int pageIndex, int pageSize, LogFilter filter,String alias )  {

        List<Log> list = new ArrayList<Log>();
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
                c = ConnectionPool.getConnection( alias );
                PreparedStatement ps = c.prepareStatement(sql.toString());
                for(params=0;params<flFields.size();params++){
                ps.setObject(params+1, flFields.get(params).getColobject());
                }
                params++;
                ps.setInt(params++,v_upperbound);
                ps.setInt(params++,v_lowerbound);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Log(
                                        rs.getString("user_id"),
                                        rs.getString("user_name"),
                                        rs.getString("branch"),
                                        rs.getString("ip_address"),
                                        //rs.getTimestamp("action_date"),
                                        rs.getTimestamp("action_date"),
                                        rs.getInt("act_type"),
                                        rs.getInt("entity_type"),
                                        rs.getString("entity_id")
                        ));
                        
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;

    }
    /*
    public static List<Log> get_logs(String branch, String Uname, String Ip_addr, int act_type, String message, Date from_d, Date to_d, String alias)
    {
    	List<Log> res = new ArrayList<Log>();
    	
    	Connection c = null;
       
        try {
        	c = ConnectionPool.getConnection(alias);
        	
            PreparedStatement ps = c.prepareStatement("Select TB2.* "+
            		"From   (Select CEIL(ROWNUM / 5) AS STR, "+
            		"               TB.* "+
            		"        From   (Select "+
            		"                 "+
            		"                 UAL.ID, "+
            		"                 UAL.BRANCH AS BRANCH, "+
            		"                 UAL.USER_ID, "+
            		"                 UPPER(UAL.USER_NAME)as user_name, "+
            		"                 UAL.IP_ADDRESS, "+
            		"                 UAL.ACTION_DATE, "+
            		"                 UPPER(ACT.DESCRIPTION) AS GROUP_ACTION, "+
            		"                 UAL.ENTITY_ID AS ACTION_MESSAGE "+
            		"                From   BF_USER_ACTIONS_LOG UAL, "+
            		"                       BF_USER_ACTIONS_TYPE ACT, "+
            		"                       S_MFO SMF, "+
            		"                       (Select ('00444') AS BRANCH, "+
            		"                               ('1354') USER_ID, "+
            		"                               UPPER('sabitov_s') AS USER_NAME, "+
            		"                               ('127.0.0.1') AS IP_ADDRESS, "+
            		"                               ('7') AS ACT_TYPE, "+
            		"                               UPPER('%621045ARNGWJ3081%') AS MESSAGE, "+
            		"                               (TO_DATE('03.01.2014', 'dd.MM.yyyy')) AS DATE_FROM, "+
            		"                               (TO_DATE('03.01.2014', 'dd.MM.yyyy')) AS DATE_TO "+
            		"                        From   DUAL) FILTR "+
            		"                Where  UAL.ACT_TYPE = "+
            		"                       DECODE(ACT.ID, NULL, UAL.ACT_TYPE, ACT.ID) "+
            		"                       and SMF.BANK_ID = UAL.BRANCH "+
            		"                       and "+
            		"                       ACT.ID = "+
            		"                       DECODE(FILTR.ACT_TYPE, NULL, ACT.ID, FILTR.ACT_TYPE) "+
            		"                       and "+
            		"                       UAL.BRANCH = "+
            		"                       DECODE(FILTR.BRANCH, NULL, UAL.BRANCH, FILTR.BRANCH) "+
            		"                       and "+
            		"                       UAL.USER_ID = "+
            		"                       DECODE(FILTR.USER_ID, NULL, UAL.USER_ID, FILTR.USER_ID) "+
            		"                       and UPPER(UAL.USER_NAME) LIKE (FILTR.USER_NAME) "+
            		"                       and UAL.IP_ADDRESS = "+
            		"                       DECODE(FILTR.IP_ADDRESS, "+
            		"                                  NULL, "+
            		"                                  UAL.IP_ADDRESS, "+
            		"                                  FILTR.IP_ADDRESS) "+
            		"                       and UPPER(UAL.ENTITY_ID) LIKE FILTR.MESSAGE "+
            		"                       and UAL.ACTION_DATE >= FILTR.DATE_FROM "+
            		"                       and UAL.ACTION_DATE <= FILTR.DATE_TO + 1 "+
            		"                ORDER  BY UAL.ACTION_DATE DESC) TB) TB2 "+
            		"Where  TB2.STR = 1 ");
            
           
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
            	res.add(new Log(
            			rs.getString("USER_ID"),
            			rs.getString("user_name"),
            			rs.getString("BRANCH"),
            			rs.getString("IP_ADDRESS"),
            			rs.getDate("ACTION_DATE"),
            			rs.getString("GROUP_ACTION"),
            			rs.getString("ACTION_MESSAGE")
            			));
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
    	return res;
    }*/
    
}
