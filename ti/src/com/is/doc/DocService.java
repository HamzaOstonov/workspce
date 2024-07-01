package com.is.doc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;



public class DocService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM general ";

/*
        public List<Doc> getDoc()  {

                List<Doc> list = new ArrayList<Doc>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM Doc");
                        while (rs.next()) {
                                list.add(new Doc(
                                                rs.getDouble("id"),
                                                rs.getString("branch"),
                                                rs.getString("doc_num"),
                                                rs.getDate("d_date"),
                                                rs.getString("bank_cl"),
                                                rs.getString("acc_cl"),
                                                rs.getString("name_cl"),
                                                rs.getString("bank_co"),
                                                rs.getString("acc_co"),
                                                rs.getString("name_co"),
                                                rs.getString("purpose"),
                                                rs.getDouble("summa"),
                                                rs.getString("currency"),
                                                rs.getString("type_doc"),
                                                rs.getDouble("s_deal_id"),
                                                rs.getDate("v_date"),
                                                rs.getString("pdc"),
                                                rs.getString("cash_code"),
                                                rs.getDouble("state"),
                                                rs.getDouble("parent_group_id"),
                                                rs.getDouble("parent_id"),
                                                rs.getDouble("child_id"),
                                                rs.getDouble("kod_err"),
                                                rs.getString("file_name"),
                                                rs.getDouble("err_general"),
                                                rs.getDouble("emp_id"),
                                                rs.getDouble("id_transh"),
                                                rs.getDouble("id_transh_purp"),
                                                rs.getDate("val_date"),));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }
*/
       public static String doAction(String un,String pw, String branch, String id,int actionid) {
            String res ="";
            SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
            Connection c = null;
            CallableStatement cs = null;
            CallableStatement acs = null;
            CallableStatement ccs = null;
            String cn;
        try {
                c = ConnectionPool.getConnection(un,pw);
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{ call Param.clearparam() }");


                PreparedStatement ps = c.prepareStatement("SELECT * FROM general WHERE branch=? and id=?");
                ps.setString(1, branch);
                ps.setString(2, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        ccs.execute();
                         for (int i=1;  i<=rs.getMetaData().getColumnCount();i++){
                                 cn = rs.getMetaData().getColumnName(i);
                                // System.out.println(cn+"  "+ rs.getMetaData().getColumnTypeName(i));
                                   if( rs.getString(cn)!=null){
                                       cs.setString(1, cn);
                                       if (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
                                          cs.setString(2,bdf.format(rs.getDate(cn)));
                                       }else{
                                          cs.setString(2,rs.getString(cn));
                                       }
                                       cs.execute();
                                   }
                         }

                     acs.setInt(1, 2);
                     acs.setInt(2, 2);
                     acs.setInt(3,actionid);
                     acs.execute();
                     c.commit();
                }
        } catch (Exception e) {
               // e.printStackTrace();
                res = e.getMessage();
        } finally {
                ConnectionPool.close(c);
        }
        return res;
}


        private static String getCond(List<FilterField> flfields){
                if(flfields.size()>0){
                        return " and ";
                }else
                return " where ";
        }

        private static List<FilterField> getFilterFields(DocFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getBranch())){
                      flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
              }
              if(!CheckNull.isEmpty(filter.getDoc_num())){
                      flfields.add(new FilterField(getCond(flfields)+ "doc_num=?",filter.getDoc_num()));
              }
              if(!CheckNull.isEmpty(filter.getD_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "d_date=?",filter.getD_date()));
              }
              if(!CheckNull.isEmpty(filter.getBank_cl())){
                      flfields.add(new FilterField(getCond(flfields)+ "bank_cl=?",filter.getBank_cl()));
              }
              if(!CheckNull.isEmpty(filter.getAcc_cl())){
                      flfields.add(new FilterField(getCond(flfields)+ "acc_cl=?",filter.getAcc_cl()));
              }
              if(!CheckNull.isEmpty(filter.getName_cl())){
                      flfields.add(new FilterField(getCond(flfields)+ "name_cl=?",filter.getName_cl()));
              }
              if(!CheckNull.isEmpty(filter.getBank_co())){
                      flfields.add(new FilterField(getCond(flfields)+ "bank_co=?",filter.getBank_co()));
              }
              if(!CheckNull.isEmpty(filter.getAcc_co())){
                      flfields.add(new FilterField(getCond(flfields)+ "acc_co=?",filter.getAcc_co()));
              }
              if(!CheckNull.isEmpty(filter.getName_co())){
                      flfields.add(new FilterField(getCond(flfields)+ "name_co=?",filter.getName_co()));
              }
              if(!CheckNull.isEmpty(filter.getPurpose())){
                      flfields.add(new FilterField(getCond(flfields)+ "purpose=?",filter.getPurpose()));
              }
              if(!CheckNull.isEmpty(filter.getSumma())){
                      flfields.add(new FilterField(getCond(flfields)+ "summa=?",filter.getSumma()));
              }
              if(!CheckNull.isEmpty(filter.getCurrency())){
                      flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
              }
              if(!CheckNull.isEmpty(filter.getType_doc())){
                      flfields.add(new FilterField(getCond(flfields)+ "type_doc=?",filter.getType_doc()));
              }
              if(!CheckNull.isEmpty(filter.getS_deal_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "s_deal_id=?",filter.getS_deal_id()));
              }
              if(!CheckNull.isEmpty(filter.getV_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "v_date=?",filter.getV_date()));
              }
              if(!CheckNull.isEmpty(filter.getPdc())){
                      flfields.add(new FilterField(getCond(flfields)+ "pdc=?",filter.getPdc()));
              }
              if(!CheckNull.isEmpty(filter.getCash_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "cash_code=?",filter.getCash_code()));
              }
              if(!CheckNull.isEmpty(filter.getState())){
                      flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
              }
              if(!CheckNull.isEmpty(filter.getParent_group_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "parent_group_id=?",filter.getParent_group_id()));
              }
              if(!CheckNull.isEmpty(filter.getParent_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "parent_id=?",filter.getParent_id()));
              }
              if(!CheckNull.isEmpty(filter.getChild_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "child_id=?",filter.getChild_id()));
              }
              if(!CheckNull.isEmpty(filter.getKod_err())){
                      flfields.add(new FilterField(getCond(flfields)+ "kod_err=?",filter.getKod_err()));
              }
              if(!CheckNull.isEmpty(filter.getFile_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "file_name=?",filter.getFile_name()));
              }
              if(!CheckNull.isEmpty(filter.getErr_general())){
                      flfields.add(new FilterField(getCond(flfields)+ "err_general=?",filter.getErr_general()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_id=?",filter.getEmp_id()));
              }
              if(!CheckNull.isEmpty(filter.getId_transh())){
                      flfields.add(new FilterField(getCond(flfields)+ "id_transh=?",filter.getId_transh()));
              }
              if(!CheckNull.isEmpty(filter.getId_transh_purp())){
                      flfields.add(new FilterField(getCond(flfields)+ "id_transh_purp=?",filter.getId_transh_purp()));
              }
              if(!CheckNull.isEmpty(filter.getVal_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "val_date=?",filter.getVal_date()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(DocFilter filter, String alias)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM general ");
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



        public static List<Doc> getDocsFl(int pageIndex, int pageSize, DocFilter filter, String alias)  {

                List<Doc> list = new ArrayList<Doc>();
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
                                list.add(new Doc(
                                                rs.getLong("id"),
                                                rs.getString("branch"),
                                                rs.getString("doc_num"),
                                                rs.getDate("d_date"),
                                                rs.getString("bank_cl"),
                                                rs.getString("acc_cl"),
                                                rs.getString("name_cl"),
                                                rs.getString("bank_co"),
                                                rs.getString("acc_co"),
                                                rs.getString("name_co"),
                                                rs.getString("purpose"),
                                                rs.getLong("summa"),
                                                rs.getString("currency"),
                                                rs.getString("type_doc"),
                                                rs.getInt("s_deal_id"),
                                                rs.getDate("v_date"),
                                                rs.getString("pdc"),
                                                rs.getString("cash_code"),
                                                rs.getInt("state"),
                                                rs.getInt("parent_group_id"),
                                                rs.getLong("parent_id"),
                                                rs.getLong("child_id"),
                                                rs.getInt("kod_err"),
                                                rs.getString("file_name"),
                                                rs.getInt("err_general"),
                                                rs.getInt("emp_id"),
                                                rs.getInt("id_transh"),
                                                rs.getInt("id_transh_purp"),
                                                rs.getDate("val_date")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }

/*
        public Doc getDoc(int docId) {

                Doc doc = new Doc();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM doc WHERE doc_id=?");
                        ps.setInt(1, docId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                doc = new Doc();
                                
                                doc.setId(rs.getDouble("id"));
                                doc.setBranch(rs.getString("branch"));
                                doc.setDoc_num(rs.getString("doc_num"));
                                doc.setD_date(rs.getDate("d_date"));
                                doc.setBank_cl(rs.getString("bank_cl"));
                                doc.setAcc_cl(rs.getString("acc_cl"));
                                doc.setName_cl(rs.getString("name_cl"));
                                doc.setBank_co(rs.getString("bank_co"));
                                doc.setAcc_co(rs.getString("acc_co"));
                                doc.setName_co(rs.getString("name_co"));
                                doc.setPurpose(rs.getString("purpose"));
                                doc.setSumma(rs.getDouble("summa"));
                                doc.setCurrency(rs.getString("currency"));
                                doc.setType_doc(rs.getString("type_doc"));
                                doc.setS_deal_id(rs.getDouble("s_deal_id"));
                                doc.setV_date(rs.getDate("v_date"));
                                doc.setPdc(rs.getString("pdc"));
                                doc.setCash_code(rs.getString("cash_code"));
                                doc.setState(rs.getDouble("state"));
                                doc.setParentGroupId(rs.getDouble("parent_group_id"));
                                doc.setParentId(rs.getDouble("parent_id"));
                                doc.setChild_id(rs.getDouble("child_id"));
                                doc.setKod_err(rs.getDouble("kod_err"));
                                doc.setFile_name(rs.getString("file_name"));
                                doc.setErr_general(rs.getDouble("err_general"));
                                doc.setEmp_id(rs.getDouble("emp_id"));
                                doc.setId_transh(rs.getDouble("id_transh"));
                                doc.setId_transh_purp(rs.getDouble("id_transh_purp"));
                                doc.setVal_date(rs.getDate("val_date"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return doc;
        }

        public static Doc create(Doc doc)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SEQ_doc.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                doc.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO doc (id, branch, doc_num, d_date, bank_cl, acc_cl, name_cl, bank_co, acc_co, name_co, purpose, summa, currency, type_doc, s_deal_id, v_date, pdc, cash_code, state, parent_group_id, parent_id, child_id, kod_err, file_name, err_general, emp_id, id_transh, id_transh_purp, val_date, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                        
                        ps.setDouble(1,doc.getId());
                        ps.setString(2,doc.getBranch());
                        ps.setString(3,doc.getDoc_num());
                        ps.setDate(4,doc.getD_date());
                        ps.setString(5,doc.getBank_cl());
                        ps.setString(6,doc.getAcc_cl());
                        ps.setString(7,doc.getName_cl());
                        ps.setString(8,doc.getBank_co());
                        ps.setString(9,doc.getAcc_co());
                        ps.setString(10,doc.getName_co());
                        ps.setString(11,doc.getPurpose());
                        ps.setDouble(12,doc.getSumma());
                        ps.setString(13,doc.getCurrency());
                        ps.setString(14,doc.getType_doc());
                        ps.setDouble(15,doc.getS_deal_id());
                        ps.setDate(16,doc.getV_date());
                        ps.setString(17,doc.getPdc());
                        ps.setString(18,doc.getCash_code());
                        ps.setDouble(19,doc.getState());
                        ps.setDouble(20,doc.getParentGroupId());
                        ps.setDouble(21,doc.getParentId());
                        ps.setDouble(22,doc.getChild_id());
                        ps.setDouble(23,doc.getKod_err());
                        ps.setString(24,doc.getFile_name());
                        ps.setDouble(25,doc.getErr_general());
                        ps.setDouble(26,doc.getEmp_id());
                        ps.setDouble(27,doc.getId_transh());
                        ps.setDouble(28,doc.getId_transh_purp());
                        ps.setDate(29,doc.getVal_date());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return doc;
        }

        public static void update(Doc doc)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE doc SET id=?, branch=?, doc_num=?, d_date=?, bank_cl=?, acc_cl=?, name_cl=?, bank_co=?, acc_co=?, name_co=?, purpose=?, summa=?, currency=?, type_doc=?, s_deal_id=?, v_date=?, pdc=?, cash_code=?, state=?, parent_group_id=?, parent_id=?, child_id=?, kod_err=?, file_name=?, err_general=?, emp_id=?, id_transh=?, id_transh_purp=?, val_date=?,  WHERE doc_id=?");
                        
                        ps.setDouble(1,doc.getId());
                        ps.setString(2,doc.getBranch());
                        ps.setString(3,doc.getDoc_num());
                        ps.setDate(4,doc.getD_date());
                        ps.setString(5,doc.getBank_cl());
                        ps.setString(6,doc.getAcc_cl());
                        ps.setString(7,doc.getName_cl());
                        ps.setString(8,doc.getBank_co());
                        ps.setString(9,doc.getAcc_co());
                        ps.setString(10,doc.getName_co());
                        ps.setString(11,doc.getPurpose());
                        ps.setDouble(12,doc.getSumma());
                        ps.setString(13,doc.getCurrency());
                        ps.setString(14,doc.getType_doc());
                        ps.setDouble(15,doc.getS_deal_id());
                        ps.setDate(16,doc.getV_date());
                        ps.setString(17,doc.getPdc());
                        ps.setString(18,doc.getCash_code());
                        ps.setDouble(19,doc.getState());
                        ps.setDouble(20,doc.getParentGroupId());
                        ps.setDouble(21,doc.getParentId());
                        ps.setDouble(22,doc.getChild_id());
                        ps.setDouble(23,doc.getKod_err());
                        ps.setString(24,doc.getFile_name());
                        ps.setDouble(25,doc.getErr_general());
                        ps.setDouble(26,doc.getEmp_id());
                        ps.setDouble(27,doc.getId_transh());
                        ps.setDouble(28,doc.getId_transh_purp());
                        ps.setDate(29,doc.getVal_date());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();
                        throw new Exception(e);
                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(Doc doc)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM doc WHERE doc_id=?");
                        ps.setInt(1, doc.getdocId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }
*/

}


