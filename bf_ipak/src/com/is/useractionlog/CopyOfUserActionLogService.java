package com.is.useractionlog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class CopyOfUserActionLogService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="select * from (SELECT * FROM BF_USER_ACTIONS_LOG order by id desc) ";



    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            }else
            return " where ";
    }

    private static List<FilterField> getFilterFields(UserActionLogFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getUser_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "user_id=?",filter.getUser_id()));
          }
          if(!CheckNull.isEmpty(filter.getUser_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "user_name=?",filter.getUser_name()));
          }
          if(!CheckNull.isEmpty(filter.getIp_address())){
                  flfields.add(new FilterField(getCond(flfields)+ "ip_address=?",filter.getIp_address()));
          }
          if(!CheckNull.isEmpty(filter.getAction_date_from())){
                  flfields.add(new FilterField(getCond(flfields)+ "action_date=?",filter.getAction_date_from()));
          }
          if(!CheckNull.isEmpty(filter.getAct_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "act_type=?",filter.getAct_type()));
          }
          if(!CheckNull.isEmpty(filter.getEntity_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "entity_type=?",filter.getEntity_type()));
          }
          if(!CheckNull.isEmpty(filter.getEntity_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "entity_id=?",filter.getEntity_id()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(UserActionLogFilter filter,String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_USER_ACTIONS_LOG ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias);
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



    public static List<UserActionLog> getUserActionLogsFl(int pageIndex, int pageSize, UserActionLogFilter filter, String alias)  {

            List<UserActionLog> list = new ArrayList<UserActionLog>();
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
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new UserActionLog(
                                            rs.getLong("id"),
                                            rs.getString("branch"),
                                            rs.getInt("user_id"),
                                            rs.getString("user_name"),
                                            rs.getString("ip_address"),
                                            rs.getDate("action_date"),
                                            rs.getInt("act_type"),
                                            rs.getInt("entity_type"),
                                            rs.getString("entity_id")));
                    }
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }





}
