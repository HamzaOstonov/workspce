package com.is.bpri;


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
import com.is.utils.RefData;
import com.is.utils.RefDataService;



public class LdProductService {
	
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM bpr_ld_char ";


    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            }else
            return " where ";
    }

    private static List<FilterField> getFilterFields(LdProductFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();



          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getLd_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "ld_num=?",filter.getLd_num()));
          }
          if(!CheckNull.isEmpty(filter.getCrc_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "crc_num=?",filter.getCrc_num()));
          }
          if(!CheckNull.isEmpty(filter.getShifr_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "shifr_id=?",filter.getShifr_id()));
          }
          if(!CheckNull.isEmpty(filter.getProd_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "prod_name=?",filter.getProd_name()));
          }
          if(!CheckNull.isEmpty(filter.getSred_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "sred_id=?",filter.getSred_id()));
          }
          if(!CheckNull.isEmpty(filter.getTarget())){
                  flfields.add(new FilterField(getCond(flfields)+ "target=?",filter.getTarget()));
          }
          if(!CheckNull.isEmpty(filter.getCalc_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "calc_id=?",filter.getCalc_id()));
          }
          if(!CheckNull.isEmpty(filter.getTerm_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "term_type=?",filter.getTerm_type()));
          }
          if(!CheckNull.isEmpty(filter.getType_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "type_id=?",filter.getType_id()));
          }
          if(!CheckNull.isEmpty(filter.getKred_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "kred_id=?",filter.getKred_id()));
          }
          if(!CheckNull.isEmpty(filter.getKlass_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "klass_id=?",filter.getKlass_id()));
          }
          if(!CheckNull.isEmpty(filter.getStatus())){
                  flfields.add(new FilterField(getCond(flfields)+ "status=?",filter.getStatus()));
          }
          if(!CheckNull.isEmpty(filter.getKlassp_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "klassp_id=?",filter.getKlassp_id()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(LdProductFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM bpr_ld_char ");
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
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<LdProduct> getLdProductsFl(int pageIndex, int pageSize, LdProductFilter filter, String alias)  {

            List<LdProduct> list = new ArrayList<LdProduct>();
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
                            list.add(new LdProduct(
                                            rs.getInt("bpr_id"),
                                            rs.getString("currency"),
                                            rs.getString("ld_num"),
                                            rs.getString("crc_num"),
                                            rs.getString("shifr_id"),
                                            rs.getString("prod_name"),
                                            rs.getString("sred_id"),
                                            rs.getString("target"),
                                            rs.getInt("calc_id"),
                                            rs.getInt("term_type"),
                                            rs.getInt("type_id"),
                                            rs.getString("kred_id"),
                                            rs.getString("klass_id"),
                                            rs.getInt("status"),
                                            rs.getString("klassp_id")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public LdProduct getLdProduct(int ldproductId) {

            LdProduct ldproduct = new LdProduct();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM bpr_ld_char WHERE id=?");
                    ps.setInt(1, ldproductId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ldproduct = new LdProduct();
                            
                            ldproduct.setId(rs.getInt("id"));
                            ldproduct.setCurrency(rs.getString("currency"));
                            ldproduct.setLd_num(rs.getString("ld_num"));
                            ldproduct.setCrc_num(rs.getString("crc_num"));
                            ldproduct.setShifr_id(rs.getString("shifr_id"));
                            ldproduct.setProd_name(rs.getString("prod_name"));
                            ldproduct.setSred_id(rs.getString("sred_id"));
                            ldproduct.setTarget(rs.getString("target"));
                            ldproduct.setCalc_id(rs.getInt("calc_id"));
                            ldproduct.setTerm_type(rs.getInt("term_type"));
                            ldproduct.setType_id(rs.getInt("type_id"));
                            ldproduct.setKred_id(rs.getString("kred_id"));
                            ldproduct.setKlass_id(rs.getString("klass_id"));
                            ldproduct.setStatus(rs.getInt("status"));
                            ldproduct.setKlassp_id(rs.getString("klassp_id"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return ldproduct;
    }

    public static LdProduct create(LdProduct ldproduct, String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_bpr_ld_char.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ldproduct.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO bpr_ld_char (bpr_id, currency, ld_num, crc_num, shifr_id, prod_name, sred_id, target, calc_id, term_type, type_id, kred_id, klass_id, status, klassp_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,ldproduct.getId());
                    ps.setString(2,ldproduct.getCurrency());
                    ps.setString(3,ldproduct.getLd_num());
                    ps.setString(4,ldproduct.getCrc_num());
                    ps.setString(5,ldproduct.getShifr_id());
                    ps.setString(6,ldproduct.getProd_name());
                    ps.setString(7,ldproduct.getSred_id());
                    ps.setString(8,ldproduct.getTarget());
                    ps.setInt(9,ldproduct.getCalc_id());
                    ps.setInt(10,ldproduct.getTerm_type());
                    ps.setInt(11,ldproduct.getType_id());
                    ps.setString(12,ldproduct.getKred_id());
                    ps.setString(13,ldproduct.getKlass_id());
                    ps.setInt(14,ldproduct.getStatus());
                    ps.setString(15,ldproduct.getKlassp_id());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return ldproduct;
    }

    public static void update(LdProduct ldproduct, String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE bpr_ld_char SET currency=?, ld_num=?, crc_num=?, shifr_id=?, prod_name=?, sred_id=?, target=?, calc_id=?, term_type=?, type_id=?, kred_id=?, klass_id=?, status=?, klassp_id=?  WHERE bpr_id=?");
                    
                    ps.setInt(15,ldproduct.getId());
                    ps.setString(1,ldproduct.getCurrency());
                    ps.setString(2,ldproduct.getLd_num());
                    ps.setString(3,ldproduct.getCrc_num());
                    ps.setString(4,ldproduct.getShifr_id());
                    ps.setString(5,ldproduct.getProd_name());
                    ps.setString(6,ldproduct.getSred_id());
                    ps.setString(7,ldproduct.getTarget());
                    ps.setInt(8,ldproduct.getCalc_id());
                    ps.setInt(9,ldproduct.getTerm_type());
                    ps.setInt(10,ldproduct.getType_id());
                    ps.setString(11,ldproduct.getKred_id());
                    ps.setString(12,ldproduct.getKlass_id());
                    ps.setInt(13,ldproduct.getStatus());
                    ps.setString(14,ldproduct.getKlassp_id());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(LdProduct ldproduct, String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM bpr_ld_char WHERE bpr_id=?");
                    ps.setInt(1, ldproduct.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    
    ///---------------Справочники------------------------------------

    public static List<RefData> getSSDBLinkBranch(String branch) {
                   return RefDataService.getRefData("select  user_name data, branch||'-'||name label from ss_dblink_branch order by label ", branch);
       }


    public static List<RefData> getStatus(String branch)  /// Статус кредита
    {
      return RefDataService.getRefData("select id data, id||' - '||name label from ss_ld_status order by data", branch);
    }

    public static List<RefData> getState(String branch)   ///   Состояние кредита
    {
      return RefDataService.getRefData("select id data, id ||' - '||name  label from state_ldform order by data", branch);
    }
    public static List<RefData> getScredit(String branch)  //виды кредитования
    {
      return RefDataService.getRefData("select kred_id data, kred_id ||' - '||kred_name  label from s_credit order by data", branch);
    }
    public static List<RefData> getSScredit(String branch)  /// Шифр кредита Свой
    {
      return RefDataService.getRefData("select kred_id data, kred_id ||' - '||kred_name  label from ss_credit order by data", branch);
    }
    public static List<RefData> getSshifr(String branch)  /// Шифр целевого назначения
    {
      return RefDataService.getRefData("select kod_gr || kod_pgr || shifr_id  data , kod_gr || kod_pgr || shifr_id || ' - ' ||shifr_name  label from s_shifr", branch);
    }
    public static List<RefData> getSsred(String branch) /// средство обеспечения
    {
      return RefDataService.getRefData("select sred_id  data , sred_id || ' - ' ||sred_name  label from s_sred", branch);
    }
    public static List<RefData> getSklass(String branch)  /// клас кредита
    {
      return RefDataService.getRefData("select klass_id data , klass_name label from s_klass order by data", branch);
    }
    public static List<RefData> getSsrokkr(String branch) ///срок кредита
    {
      return RefDataService.getRefData("select kod_kr data, type_kr label from s_srokkr order by data", branch);
    }
    public static List<RefData> getSklassp(String branch) ///Класс качества
    {
      return RefDataService.getRefData("select klassp_id data, klass_name label from s_klassp order by data", branch);
    }
    public static List<RefData> getSint(String branch) ///Интервал срочности
    {
      return RefDataService.getRefData("select kod_int data, name_int label from s_int order by data", branch);
    }
    public static List<RefData> getCalcmetod(String branch) ///Метод начисления процентов
    {
      return RefDataService.getRefData("select id data, name label from ss_ld_calc_method order by data", branch);
    }
    public static List<RefData> getSS_type_ans(String branch) ///Статус наращивания процентов
    {
      return RefDataService.getRefData("select code data,name label from ss_type_ans order by data", branch);
    }


    //-----------------конец Справочников------------------------




}
