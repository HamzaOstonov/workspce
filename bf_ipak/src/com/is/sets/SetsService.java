/* Decompiler 356ms, total 1170ms, lines 565 */
package com.is.sets;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;

public class SetsService {
   private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
   private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
   private static String msql = "SELECT * FROM BF_SETS ";

   public static List<Sets> getBF_SETS() {
      List<Sets> list = new ArrayList();
      Connection c = null;
      Statement s = null;
      ResultSet rs = null;

      try {
         c = ConnectionPool.getConnection();
         s = c.createStatement();
         rs = s.executeQuery("SELECT * FROM BF_SETS");

         while(rs.next()) {
            list.add(new Sets(rs.getString("branch"), rs.getString("id"), rs.getString("value"), rs.getString("name"), rs.getLong("editable")));
         }
      } catch (SQLException var21) {
         var21.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var21));
      } finally {
         try {
            if (s != null) {
               s.close();
            }
         } catch (Exception var20) {
         }

         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var19) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var18) {
         }

      }

      return list;
   }

   public static Res doAction(Sets BF_SETS, int actionid) {
      Res res = null;
      new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;

      try {
         c = ConnectionPool.getConnection();
         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         ccs.execute();
         ccs = c.prepareCall("{? = call Param.getparam('ID') }");
         if (!CheckNull.isEmpty(BF_SETS.getBranch())) {
            cs.setString(1, "BRANCH");
            cs.setString(2, BF_SETS.getBranch());
            cs.execute();
         }

         if (!CheckNull.isEmpty(BF_SETS.getId())) {
            cs.setString(1, "ID");
            cs.setString(2, BF_SETS.getId());
            cs.execute();
         }

         if (!CheckNull.isEmpty(BF_SETS.getValue())) {
            cs.setString(1, "VALUE");
            cs.setString(2, BF_SETS.getValue());
            cs.execute();
         }

         if (!CheckNull.isEmpty(BF_SETS.getName())) {
            cs.setString(1, "NAME");
            cs.setString(2, BF_SETS.getName());
            cs.execute();
         }

         if (!CheckNull.isEmpty(BF_SETS.getEditable())) {
            cs.setString(1, "EDITABLE");
            cs.setLong(2, BF_SETS.getEditable());
            cs.execute();
         }

         acs.setInt(1, 2);
         acs.setInt(2, 2);
         acs.setInt(3, actionid);
         acs.execute();
         c.commit();
         ccs.execute();
         res = new Res(0, ccs.getString(1));
      } catch (Exception var12) {
         res = new Res(-1, var12.getMessage());
      } finally {
         com.is.ConnectionPool.close(c);
      }

      return res;
   }

   public static String doAction(String branch, String id, int actionid) {
      String res = "";
      SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;

      try {
         c = ConnectionPool.getConnection();
         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_SETS WHERE branch=? and id=?");
         ps.setString(1, branch);
         ps.setString(2, id);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            ccs.execute();

            for(int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
               String cn = rs.getMetaData().getColumnName(i);
               if (rs.getString(cn) != null) {
                  cs.setString(1, cn);
                  if (rs.getMetaData().getColumnTypeName(i).equals("DATE")) {
                     cs.setString(2, bdf.format(rs.getDate(cn)));
                  } else {
                     cs.setString(2, rs.getString(cn));
                  }

                  cs.execute();
               }
            }

            acs.setInt(1, 2);
            acs.setInt(2, 2);
            acs.setInt(3, actionid);
            acs.execute();
            c.commit();
         }
      } catch (Exception var16) {
         res = var16.getMessage();
      } finally {
         com.is.ConnectionPool.close(c);
      }

      return res;
   }

   private static String getCond(List<FilterField> flfields) {
      return flfields.size() > 0 ? " and " : " where ";
   }

   private static List<FilterField> getFilterFields(SetsFilter filter) {
      List<FilterField> flfields = new ArrayList();
      if (!CheckNull.isEmpty(filter.getBranch())) {
         flfields.add(new FilterField(getCond(flfields) + "branch = ?", filter.getBranch()));
      }

      if (!CheckNull.isEmpty(filter.getId())) {
         flfields.add(new FilterField(getCond(flfields) + "id like ?", filter.getId()));
      }

      if (!CheckNull.isEmpty(filter.getValue())) {
         flfields.add(new FilterField(getCond(flfields) + "value = ?", filter.getValue()));
      }

      if (!CheckNull.isEmpty(filter.getName())) {
         flfields.add(new FilterField(getCond(flfields) + "name like ?", filter.getName()));
      }

      if (!CheckNull.isEmpty(filter.getEditable())) {
         flfields.add(new FilterField(getCond(flfields) + "editable = ?", filter.getEditable()));
      }

      flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
      return flfields;
   }

   public static int getCount(SetsFilter filter, String _table) {
      Connection c = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int n = 0;
      List<FilterField> flFields = getFilterFields(filter);
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT count(*) ct FROM " + _table);
      int k;
      if (flFields.size() > 0) {
         for(k = 0; k < flFields.size(); ++k) {
            sql.append(((FilterField)flFields.get(k)).getSqlwhere());
         }
      }

      try {
         if (_table.toLowerCase().equalsIgnoreCase("BF_SETS")) {
            c = ConnectionPool.getConnection();
         } else {
            c = com.is.ConnectionPool.getConnection();
         }

         ps = c.prepareStatement(sql.toString());

         for(k = 0; k < flFields.size(); ++k) {
            ps.setObject(k + 1, ((FilterField)flFields.get(k)).getColobject());
         }

         rs = ps.executeQuery();
         if (rs.next()) {
            n = rs.getInt(1);
         }
      } catch (SQLException var25) {
         var25.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var25));
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var24) {
         }

         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var23) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var22) {
         }

      }

      return n;
   }

   public static List<Sets> getKlb_setssFl(int pageIndex, int pageSize, SetsFilter filter, String _table) {
      List<Sets> list = new ArrayList();
      Connection c = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int v_lowerbound = pageIndex + 1;
      int v_upperbound = v_lowerbound + pageSize - 1;
      List<FilterField> flFields = getFilterFields(filter);
      StringBuffer sql = new StringBuffer();
      sql.append(psql1);
      sql.append("SELECT * FROM " + _table);
      if (flFields.size() > 0) {
         for(int i = 0; i < flFields.size(); ++i) {
            sql.append(((FilterField)flFields.get(i)).getSqlwhere());
         }
      }

      sql.append(psql2);

      try {
         if (_table.toLowerCase().equalsIgnoreCase("BF_SETS")) {
            c = ConnectionPool.getConnection();
         } else {
            c = com.is.ConnectionPool.getConnection();
         }

         ps = c.prepareStatement(sql.toString());

         int params;
         for(params = 0; params < flFields.size(); ++params) {
            ps.setObject(params + 1, ((FilterField)flFields.get(params)).getColobject());
         }

         ++params;
         ps.setInt(params++, v_upperbound);
         ps.setInt(params++, v_lowerbound);
         rs = ps.executeQuery();

         while(rs.next()) {
            list.add(new Sets(rs.getString("branch"), rs.getString("id"), rs.getString("value"), rs.getString("name"), rs.getLong("editable")));
         }
      } catch (SQLException var30) {
         var30.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var30));
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var29) {
         }

         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var28) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var27) {
         }

      }

      return list;
   }

   public static Sets getBF_SETS(int BF_SETSId) {
      Sets BF_SETS = new Sets();
      Connection c = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         c = ConnectionPool.getConnection();
         ps = c.prepareStatement("SELECT * FROM BF_SETS WHERE id=?");
         ps.setLong(1, (long)BF_SETSId);
         rs = ps.executeQuery();
         if (rs.next()) {
            BF_SETS = new Sets();
            BF_SETS.setBranch(rs.getString("branch"));
            BF_SETS.setId(rs.getString("id"));
            BF_SETS.setValue(rs.getString("value"));
            BF_SETS.setName(rs.getString("name"));
            BF_SETS.setEditable(rs.getLong("editable"));
         }
      } catch (Exception var22) {
         var22.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var22));
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var21) {
         }

         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var20) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var19) {
         }

      }

      return BF_SETS;
   }

   public static Res create(Sets BF_SETS, String _table) {
      new Res();
      Connection c = null;
      PreparedStatement ps = null;

      Res res;
      try {
         c = ConnectionPool.getConnection();
         ps = c.prepareStatement("INSERT INTO " + _table + " (branch, id, value, name, editable) VALUES (?, ?, ?, ?, ?)");
         ps.setString(1, BF_SETS.getBranch());
         ps.setString(2, BF_SETS.getId());
         ps.setString(3, BF_SETS.getValue());
         ps.setString(4, BF_SETS.getName());
         ps.setLong(5, BF_SETS.getEditable());
         if (ps.executeUpdate() == 1) {
            c.commit();
            res = new Res(0, "Ok");
         } else {
            c.rollback();
            res = new Res(1, " оличество записей дл€ сохранени€ не соответствует указанному!");
         }
      } catch (Exception var21) {
         var21.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var21));

         try {
            c.rollback();
         } catch (Exception var20) {
         }

         if (CheckNull.isEmpty(var21.getMessage())) {
            res = new Res(1, "Other error! " + CheckNull.getPstr(var21));
         } else {
            res = new Res(1, "—охранение невозможно! " + var21.getMessage());
         }
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var19) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var18) {
         }

      }

      return res;
   }

   public static Res update(Sets BF_SETS, String _table) {
      new Res();
      Connection c = null;
      PreparedStatement ps = null;

      Res res;
      try {
         c = ConnectionPool.getConnection();
         ps = c.prepareStatement("UPDATE " + _table + " SET branch = ?, id = ?, value = ?, name = ?, editable = ?  WHERE id=?");
         ps.setString(1, BF_SETS.getBranch());
         ps.setString(2, BF_SETS.getId());
         ps.setString(3, BF_SETS.getValue());
         ps.setString(4, BF_SETS.getName());
         ps.setLong(5, BF_SETS.getEditable());
         ps.setString(6, BF_SETS.getId());
         if (ps.executeUpdate() == 1) {
            c.commit();
            res = new Res(0, "Ok");
         } else {
            c.rollback();
            res = new Res(1, " оличество записей дл€ сохранени€ не соответствует указанному!");
         }
      } catch (SQLException var21) {
         var21.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var21));

         try {
            c.rollback();
         } catch (Exception var20) {
         }

         if (CheckNull.isEmpty(var21.getMessage())) {
            res = new Res(1, "Other error! " + CheckNull.getPstr(var21));
         } else {
            res = new Res(1, "—охранение невозможно! " + var21.getMessage());
         }
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var19) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var18) {
         }

      }

      return res;
   }

   public static Res remove(Sets BF_SETS, String _table) {
      new Res();
      Connection c = null;
      PreparedStatement ps = null;

      Res res;
      try {
         c = ConnectionPool.getConnection();
         ps = c.prepareStatement("DELETE FROM " + _table + " WHERE id=?");
         ps.setString(1, BF_SETS.getId());
         if (ps.executeUpdate() == 1) {
            c.commit();
            res = new Res(0, "Ok");
         } else {
            c.rollback();
            res = new Res(1, " оличество записей дл€ удалени€ не соответствует указанному!");
         }
      } catch (Exception var21) {
         var21.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(var21));

         try {
            c.rollback();
         } catch (Exception var20) {
         }

         if (CheckNull.isEmpty(var21.getMessage())) {
            res = new Res(1, "Other error! " + CheckNull.getPstr(var21));
         } else {
            res = new Res(1, "”даление невозможно! " + var21.getMessage());
         }
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var19) {
         }

         com.is.ConnectionPool.close(c);

         try {
            if (c != null) {
               c.close();
            }
         } catch (Exception var18) {
         }

      }

      return res;
   }
}