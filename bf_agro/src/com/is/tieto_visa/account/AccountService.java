package com.is.tieto_visa.account;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.tieto_visa.customer.CustomerService;
import com.is.tieto_visa.tieto.TclientService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountService {
   private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
   private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
   private static String msql = "SELECT * FROM Account ";

   public List<Account> getAccount(String alias) {
      List<Account> list = new ArrayList();
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         Statement s = c.createStatement();
         ResultSet rs = s.executeQuery("SELECT * FROM Account");

         while(rs.next()) {
            list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state")));
         }
      } catch (SQLException var9) {
         LtLogger.getLogger().error(CheckNull.getPstr(var9));
         var9.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return list;
   }

   public static List<AccountService.actions_for_acc> getactions_for_acc(int state, String alias) {
      List<AccountService.actions_for_acc> list = new ArrayList();
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id");
         ps.setInt(1, state);
         ResultSet rs = ps.executeQuery();

         while(rs.next()) {
            list.add(new AccountService.actions_for_acc(rs.getInt("deal_group"), rs.getInt("deal_id"), rs.getInt("action_id"), rs.getString("name")));
         }
      } catch (SQLException var9) {
         LtLogger.getLogger().error(CheckNull.getPstr(var9));
         var9.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return list;
   }

   private static String getCond(List<FilterField> flfields) {
      return flfields.size() > 0 ? " and " : " where ";
   }

   private static List<FilterField> getFilterFields(AccountFilter filter) {
      List<FilterField> flfields = new ArrayList();
      if (!CheckNull.isEmpty(filter.getBranch())) {
         flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
      }

      if (!CheckNull.isEmpty(filter.getId())) {
         flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
      }

      if (!CheckNull.isEmpty(filter.getAcc_bal())) {
         flfields.add(new FilterField(getCond(flfields) + "acc_bal=?", filter.getAcc_bal()));
      }

      if (!CheckNull.isEmpty(filter.getCurrency())) {
         flfields.add(new FilterField(getCond(flfields) + "currency=?", filter.getCurrency()));
      }

      if (!CheckNull.isEmpty(filter.getClient())) {
         flfields.add(new FilterField(getCond(flfields) + "client=?", filter.getClient()));
      }

      if (!CheckNull.isEmpty(filter.getId_order())) {
         flfields.add(new FilterField(getCond(flfields) + "id_order=?", filter.getId_order()));
      }

      if (!CheckNull.isEmpty(filter.getName())) {
         flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getName()));
      }

      if (!CheckNull.isEmpty(filter.getSgn())) {
         flfields.add(new FilterField(getCond(flfields) + "sgn=?", filter.getSgn()));
      }

      if (!CheckNull.isEmpty(filter.getBal())) {
         flfields.add(new FilterField(getCond(flfields) + "bal=?", filter.getBal()));
      }

      if (!CheckNull.isEmpty(filter.getSign_registr())) {
         flfields.add(new FilterField(getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
      }

      if (!CheckNull.isEmpty(filter.getS_in())) {
         flfields.add(new FilterField(getCond(flfields) + "s_in=?", filter.getS_in()));
      }

      if (!CheckNull.isEmpty(filter.getS_out())) {
         flfields.add(new FilterField(getCond(flfields) + "s_out=?", filter.getS_out()));
      }

      if (!CheckNull.isEmpty(filter.getDt())) {
         flfields.add(new FilterField(getCond(flfields) + "dt=?", filter.getDt()));
      }

      if (!CheckNull.isEmpty(filter.getCt())) {
         flfields.add(new FilterField(getCond(flfields) + "ct=?", filter.getCt()));
      }

      if (!CheckNull.isEmpty(filter.getS_in_tmp())) {
         flfields.add(new FilterField(getCond(flfields) + "s_in_tmp=?", filter.getS_in_tmp()));
      }

      if (!CheckNull.isEmpty(filter.getS_out_tmp())) {
         flfields.add(new FilterField(getCond(flfields) + "s_out_tmp=?", filter.getS_out_tmp()));
      }

      if (!CheckNull.isEmpty(filter.getDt_tmp())) {
         flfields.add(new FilterField(getCond(flfields) + "dt_tmp=?", filter.getDt_tmp()));
      }

      if (!CheckNull.isEmpty(filter.getCt_tmp())) {
         flfields.add(new FilterField(getCond(flfields) + "ct_tmp=?", filter.getCt_tmp()));
      }

      if (!CheckNull.isEmpty(filter.getL_date())) {
         flfields.add(new FilterField(getCond(flfields) + "l_date=?", filter.getL_date()));
      }

      if (!CheckNull.isEmpty(filter.getDate_open())) {
         flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
      }

      if (!CheckNull.isEmpty(filter.getDate_close())) {
         flfields.add(new FilterField(getCond(flfields) + "date_close=?", filter.getDate_close()));
      }

      if (!CheckNull.isEmpty(filter.getAcc_group_id())) {
         flfields.add(new FilterField(getCond(flfields) + "acc_group_id=?", filter.getAcc_group_id()));
      }

      if (!CheckNull.isEmpty(filter.getState())) {
         flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
      }

      flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
      return flfields;
   }

   public static int getCount(AccountFilter filter, String alias) {
      Connection c = null;
      int n = 0;
      List<FilterField> flFields = getFilterFields(filter);
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT count(*) ct FROM Account ");
      if (flFields.size() > 0) {
         for(int i = 0; i < flFields.size(); ++i) {
            sql.append(((FilterField)flFields.get(i)).getSqlwhere());
         }
      }

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement(sql.toString());

         for(int k = 0; k < flFields.size(); ++k) {
            ps.setObject(k + 1, ((FilterField)flFields.get(k)).getColobject());
         }

         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            n = rs.getInt(1);
         }
      } catch (SQLException var11) {
         LtLogger.getLogger().error(CheckNull.getPstr(var11));
         var11.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return n;
   }

   public static List<Account> getAccountsFl(int pageIndex, int pageSize, AccountFilter filter, String alias) {
      List<Account> list = new ArrayList();
      Connection c = null;
      int v_lowerbound = pageIndex + 1;
      int v_upperbound = v_lowerbound + pageSize - 1;
      List<FilterField> flFields = getFilterFields(filter);
      StringBuffer sql = new StringBuffer();
      sql.append(psql1);
      sql.append(msql);
      if (flFields.size() > 0) {
         for(int i = 0; i < flFields.size(); ++i) {
            sql.append(((FilterField)flFields.get(i)).getSqlwhere());
         }
      }

      sql.append(psql2);

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement(sql.toString());

         int params;
         for(params = 0; params < flFields.size(); ++params) {
            ps.setObject(params + 1, ((FilterField)flFields.get(params)).getColobject());
         }

         ++params;
         ps.setInt(params++, v_upperbound);
         ps.setInt(params++, v_lowerbound);
         ResultSet rs = ps.executeQuery();

         while(rs.next()) {
            list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state")));
         }
      } catch (SQLException var16) {
         LtLogger.getLogger().error(CheckNull.getPstr(var16));
         var16.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return list;
   }

   public static String doAction1(String un, String pw, String branch, String id, int actionid, String alias) {
      String res = "";
      String halias = CustomerService.get_alias_ho(alias);
      Connection c = null;

      try {
         if (halias.compareTo(alias) == 0) {
            c = ConnectionPool.getConnection(alias);
         } else {
            c = ConnectionPool.getConnection(un, pw, alias);
         }

         PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE branch=? and id=?");
         ps.setString(1, branch);
         ps.setString(2, id);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            ConnectionPool.doAction(c, rs, 2, 2, actionid);
            c.commit();
         }
      } catch (Exception var14) {
         LtLogger.getLogger().error(CheckNull.getPstr(var14));
         res = var14.getMessage();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static Res doAction(String un, String pw, Account account, int actionid, String alias, Boolean selfBranch) {
      Res res = null;
      new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      String halias = CustomerService.get_alias_ho(alias);
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;
      CallableStatement csi = null;

      try {
         if (halias.compareTo(alias) == 0 && !selfBranch) {
            c = ConnectionPool.getConnection(alias);
         } else {
            c = ConnectionPool.getConnection(un, pw, alias);
         }

         csi = c.prepareCall("{ call info.init() }");
         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         ccs.execute();
         ccs = c.prepareCall("{? = call Param.getparam('ID') }");
         ccs.registerOutParameter(1, 12);
         cs.setString(1, "BRANCH");
         cs.setString(2, account.getBranch());
         cs.execute();
         if (!CheckNull.isEmpty(account.getId())) {
            cs.setString(1, "ID");
            cs.setString(2, account.getId());
            cs.execute();
         }

         cs.setString(1, "ACC_BAL");
         cs.setString(2, account.getAcc_bal());
         cs.execute();
         cs.setString(1, "CURRENCY");
         cs.setString(2, account.getCurrency());
         cs.execute();
         cs.setString(1, "CLIENT");
         cs.setString(2, account.getClient());
         cs.execute();
         cs.setString(1, "ID_ORDER");
         cs.setString(2, account.getId_order());
         cs.execute();
         cs.setString(1, "NAME");
         cs.setString(2, account.getName());
         cs.execute();
         cs.setString(1, "SGN");
         cs.setString(2, account.getSgn());
         cs.execute();
         cs.setString(1, "BAL");
         cs.setString(2, account.getBal());
         cs.execute();
         cs.setString(1, "SIGN_REGISTR");
         cs.setString(2, String.valueOf(account.getSign_registr()));
         cs.execute();
         acs.setInt(1, 2);
         acs.setInt(2, 2);
         acs.setInt(3, actionid);
         csi.execute();
         acs.execute();
         c.commit();
         ccs.execute();
         res = new Res(0, ccs.getString(1));
      } catch (Exception var18) {
         LtLogger.getLogger().error(CheckNull.getPstr(var18));
         res = new Res(-1, var18.getMessage());
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static Res doAction_br(String un, String pw, Account account, int actionid1, int actionid2, String alias, Boolean selfBranch) {
      Res res = null;
      new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      String halias = CustomerService.get_alias_ho(alias);
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;
      CallableStatement csi = null;

      try {
         try {
            if (halias.compareTo(alias) == 0 && !selfBranch) {
               c = ConnectionPool.getConnection(alias);
            } else {
               c = ConnectionPool.getConnection(un, pw, alias);
            }

            csi = c.prepareCall("{ call info.init() }");
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{? = call Param.getparam('ID') }");
            ccs.registerOutParameter(1, 12);
            cs.setString(1, "BRANCH");
            cs.setString(2, account.getBranch());
            cs.execute();
            if (!CheckNull.isEmpty(account.getId())) {
               cs.setString(1, "ID");
               cs.setString(2, account.getId());
               cs.execute();
            }

            cs.setString(1, "ACC_BAL");
            cs.setString(2, account.getAcc_bal());
            cs.execute();
            cs.setString(1, "CURRENCY");
            cs.setString(2, account.getCurrency());
            cs.execute();
            cs.setString(1, "CLIENT");
            cs.setString(2, account.getClient());
            cs.execute();
            cs.setString(1, "ID_ORDER");
            cs.setString(2, account.getId_order());
            cs.execute();
            cs.setString(1, "NAME");
            cs.setString(2, account.getName());
            cs.execute();
            cs.setString(1, "SGN");
            cs.setString(2, account.getSgn());
            cs.execute();
            cs.setString(1, "BAL");
            cs.setString(2, account.getBal());
            cs.execute();
            cs.setString(1, "SIGN_REGISTR");
            cs.setInt(2, account.getSign_registr());
            cs.execute();
            cs.setString(1, "ACC_GROUP_ID");
            cs.setInt(2, account.getAcc_group_id());
            cs.execute();
            acs.setInt(1, 2);
            acs.setInt(2, 2);
            acs.setInt(3, actionid1);
            csi.execute();
            acs.execute();
            ccs.execute();
            res = new Res(0, ccs.getString(1));
            if (actionid2 != 0) {
               acs.setInt(3, actionid2);
               acs.execute();
            }

            c.commit();
         } catch (Exception var23) {
            LtLogger.getLogger().error(CheckNull.getPstr(var23));

            try {
               c.rollback();
            } catch (Exception var22) {
               res = new Res(-1, var22.getMessage());
               Res var18 = res;
               return var18;
            }

            res = new Res(-1, var23.getMessage());
         }

         return res;
      } finally {
         ConnectionPool.close(c);
      }
   }

   public static Res doAction_acc(String un, String pw, int deal_group_id, int deal_id, Account account, int actionid, String alias, Boolean selfBranch) {
      Res res = null;
      new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      String halias = CustomerService.get_alias_ho(alias);
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;
      CallableStatement csi = null;

      try {
         if (halias.compareTo(alias) == 0 && !selfBranch) {
            c = ConnectionPool.getConnection(alias);
         } else {
            c = ConnectionPool.getConnection(un, pw, alias);
         }

         csi = c.prepareCall("{ call info.init() }");
         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         ccs.execute();
         ccs = c.prepareCall("{? = call Param.getparam('ID') }");
         ccs.registerOutParameter(1, 12);
         cs.setString(1, "BRANCH");
         cs.setString(2, account.getBranch());
         cs.execute();
         if (!CheckNull.isEmpty(account.getId())) {
            cs.setString(1, "ID");
            cs.setString(2, account.getId());
            cs.execute();
         }

         acs.setInt(1, deal_group_id);
         acs.setInt(2, deal_id);
         acs.setInt(3, actionid);
         csi.execute();
         acs.execute();
         c.commit();
         ccs.execute();
         res = new Res(0, ccs.getString(1));
      } catch (Exception var20) {
         LtLogger.getLogger().error(CheckNull.getPstr(var20));
         res = new Res(-1, var20.getMessage());
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static Res doAction_acc_ho(String un, String pw, int deal_group_id, int deal_id, Account account, int actionid, String alias, Boolean selfBranch) {
      Res res = null;
      new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      String halias = CustomerService.get_alias_ho(alias);
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;
      CallableStatement csi = null;

      try {
         if (halias.compareTo(alias) == 0 && !selfBranch) {
            c = ConnectionPool.getConnection(alias);
         } else {
            c = ConnectionPool.getConnection(un, pw, alias);
         }

         csi = c.prepareCall("{ call info.init() }");
         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         ccs.execute();
         ccs = c.prepareCall("{? = call Param.getparam('ID') }");
         ccs.registerOutParameter(1, 12);
         cs.setString(1, "BRANCH");
         cs.setString(2, account.getBranch());
         cs.execute();
         if (!CheckNull.isEmpty(account.getId())) {
            cs.setString(1, "ID");
            cs.setString(2, account.getId());
            cs.execute();
            if (account.getBranch().compareTo(ConnectionPool.getValue("HO", alias)) == 0 && account.getAcc_bal().compareTo("20206") == 0) {
               cs.setString(1, "ACC_GROUP_ID");
               cs.setString(2, "101");
               cs.execute();
            }
         }

         acs.setInt(1, deal_group_id);
         acs.setInt(2, deal_id);
         acs.setInt(3, actionid);
         csi.execute();
         acs.execute();
         c.commit();
         ccs.execute();
         res = new Res(0, ccs.getString(1));
      } catch (Exception var23) {
         LtLogger.getLogger().error(CheckNull.getPstr(var23));
         res = new Res(-1, var23.getMessage());

         try {
            c.rollback();
         } catch (SQLException var22) {
            var22.printStackTrace();
         }
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static String doAction(String un, String pw, String branch, String id, int actionid, String alias, Boolean selfBranch) {
      String res = "";
      SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
      Connection c = null;
      String halias = CustomerService.get_alias_ho(alias);
      CallableStatement cs = null;
      CallableStatement acs = null;
      CallableStatement ccs = null;

      try {
         if (halias.compareTo(alias) == 0 && !selfBranch) {
            c = ConnectionPool.getConnection(alias);
         } else {
            c = ConnectionPool.getConnection(un, pw, alias);
         }

         cs = c.prepareCall("{ call Param.SetParam(?,?) }");
         acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
         ccs = c.prepareCall("{ call Param.clearparam() }");
         PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE branch=? and id=?");
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
      } catch (Exception var21) {
         LtLogger.getLogger().error(CheckNull.getPstr(var21));
         res = var21.getMessage();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static String getCardAcc(String un, String pw, String bal, String client, String curr, String ido, String name, String alias, Boolean selfBranch) {
      Connection c = null;
      String res = "";

      try {
         c = ConnectionPool.getConnection(alias);
         System.out.println("sql: select * from account a where a.acc_bal=" + bal + " and a.client=" + client + " and a.currency=" + curr + " and a.id_order=" + ido);
         PreparedStatement ps = c.prepareStatement("select * from account a where a.acc_bal=? and a.client=? and a.currency=? and a.id_order=?");
         ps.setString(1, bal);
         ps.setString(2, client);
         ps.setString(3, curr);
         ps.setString(4, ido);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            if (rs.getInt("state") == 2) {
               res = rs.getString("id");
            }
         } else {
            Account acc = new Account();
            acc.setBal("B");
            acc.setSgn("P");
            acc.setAcc_bal(bal);
            acc.setCurrency(curr);
            acc.setId_order(ido);
            acc.setName(name);
            acc.setClient(client);
            acc.setSign_registr(2);
            Res dres = doAction(un, pw, acc, 1, alias, selfBranch);
            acc.setId(dres.getName());
            dres = doAction(un, pw, acc, 2, alias, selfBranch);
            res = dres.getName();
         }
      } catch (SQLException var18) {
         LtLogger.getLogger().error(CheckNull.getPstr(var18));
         var18.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static Res Get_acc_hole(String acc_bal, String first, String last, String client_id, String branch, String alias) {
      Res res = new Res();
      res.setCode(0);

      try {
         Connection c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t where t.client = ? and t.branch = ? and t.acc_bal = ? and TO_NUMBER(t.id_order) >= TO_NUMBER(?) and TO_NUMBER(t.id_order) < TO_NUMBER(?)and t.currency = '840'");
         ps.setString(1, client_id);
         ps.setString(2, branch);
         ps.setString(3, acc_bal);
         ps.setString(4, first);
         ps.setString(5, last);
         ResultSet rs = ps.executeQuery();
         res.setName(first);
         if (rs.next() && rs.getString("res") != null && rs.getString("res").compareTo("") != 0) {
            res.setName(rs.getString("res"));
         }
      } catch (SQLException var10) {
         var10.printStackTrace();
         LtLogger.getLogger().error(CheckNull.getPstr(var10));
         res.setCode(-1);
         res.setName(var10.getMessage());
      }

      return res;
   }

   public static Res doAction_create_acc_in_br(String un, String pw, String bal, String client, String curr, String ido, String name, int group, String alias, String branch, Boolean selfBranch) {
      Res ores = new Res();
      Account acc = new Account();
      Res dres = null;
      ores.setCode(0);
      ores.setName((String)null);
      if (ido == null) {
         ido = get_ord(bal, curr, client, branch, alias);
      }

      acc.setBal("B");
      acc.setSgn("P");
      acc.setAcc_bal(bal);
      acc.setCurrency(curr);
      acc.setId_order(ido);
      acc.setAcc_group_id(group);
      acc.setName(name);
      acc.setClient(client);
      acc.setBranch(branch);
      acc.setSign_registr(2);
      dres = doAction_br(un, pw, acc, 1, 2, alias, selfBranch);
      return dres;
   }

   public static String get_ord(String bal, String cur, String client_id, String branch, String alias) {
      String res = null;
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("select info.GetNewAccOrder(?, ?, ?, ?) NEW_ACC_ORDER from dual");
         ps.setString(1, bal);
         ps.setString(2, cur);
         ps.setString(3, client_id);
         ps.setString(4, branch);
         ResultSet rs = ps.executeQuery();
         rs.next();
         res = rs.getString("NEW_ACC_ORDER");
      } catch (SQLException var12) {
         LtLogger.getLogger().error(CheckNull.getPstr(var12));
         var12.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static List<Account> getClAccount(String client_id, String branch, String alias) {
      List<Account> list = new ArrayList();
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("SELECT A.*, S.name state_desc FROM Account A, State_account S where A.branch=? and A.client=? and S.deal_id=2 and S.id = A.state");
         ps.setString(1, branch);
         ps.setString(2, client_id);
         ResultSet rs = ps.executeQuery();

         while(rs.next()) {
            list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state"), rs.getString("state_desc")));
         }
      } catch (SQLException var10) {
         LtLogger.getLogger().error(CheckNull.getPstr(var10));
         var10.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return list;
   }

   public static String get_account_state_caption(int state, String alias) {
      String res = null;
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("select r.name from state_account r where r.deal_id = '2' and r.id = ?");
         ps.setInt(1, state);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            res = rs.getString("name");
         }
      } catch (SQLException var9) {
         LtLogger.getLogger().error(CheckNull.getPstr(var9));
         var9.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static List<Account> get_card_accounts(String client_id, String branch, String card_code) {
      List<Account> res = new ArrayList();
      Connection c = null;

      try {
         c = ConnectionPool.getConnection("iy00444");
         PreparedStatement ps = c.prepareStatement("select * from account t where t.client = ? and t.branch = '00444' and t.acc_bal = '22618' and TO_NUMBER(t.id_order) >= (select s.id_order_account from bf_visa_card_setting s where s.code = ?)and TO_NUMBER(t.id_order) <= (select s.id_order_max from bf_visa_card_setting s where s.code = ?) and t.currency = '840'");
         ps.setString(1, client_id);
         ps.setString(2, card_code);
         ps.setString(3, card_code);
         ResultSet rs = ps.executeQuery();

         while(rs.next()) {
            res.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state")));
         }
      } catch (SQLException var10) {
         LtLogger.getLogger().error(CheckNull.getPstr(var10));
         var10.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static List<Account> get_card_accounts_new_card(String client_id, String branch, String card_code, String t_client_id) {
      List<Account> res = new ArrayList();
      Connection c = null;

      try {
         c = ConnectionPool.getConnection("iy00444");
         PreparedStatement ps_st = c.prepareStatement("select t.allow_multiple_cards_per_acc from bf_visa_card_setting t where t.code = ?");
         ps_st.setString(1, card_code);
         ResultSet rs_st = ps_st.executeQuery();
         rs_st.next();
         boolean allowed_multiple = rs_st.getInt("allow_multiple_cards_per_acc") == 1;
         HashMap<String, String> client_accounts_for_this_product = TclientService.get_client_ti_acc(t_client_id, card_code);
         PreparedStatement ps = c.prepareStatement("select * from account t where t.client = ? and t.branch = '00444' and t.acc_bal = '22618' and TO_NUMBER(t.id_order) >= (select s.id_order_account from bf_visa_card_setting s where s.code = ?)and TO_NUMBER(t.id_order) <= (select s.id_order_max from bf_visa_card_setting s where s.code = ?) and t.currency = '840'");
         ps.setString(1, client_id);
         ps.setString(2, card_code);
         ps.setString(3, card_code);
         ResultSet rs = ps.executeQuery();

         while(true) {
            do {
               if (!rs.next()) {
                  return res;
               }
            } while(!allowed_multiple && client_accounts_for_this_product.containsKey(rs.getString("id")));

            res.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state")));
         }
      } catch (SQLException var15) {
         LtLogger.getLogger().error(CheckNull.getPstr(var15));
         var15.printStackTrace();
      } finally {
         ConnectionPool.close(c);
      }

      return res;
   }

   public static Account get_account(String id, String branch, String alias) {
      Account res = null;
      Connection c = null;

      try {
         c = ConnectionPool.getConnection(alias);
         PreparedStatement ps = c.prepareStatement("select * from account t where t.id = ? and t.branch = ?");
         ps.setString(1, id);
         ps.setString(2, branch);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            res = new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state"));
         }
      } catch (SQLException var7) {
         LtLogger.getLogger().error(CheckNull.getPstr(var7));
         var7.printStackTrace();
      }

      return res;
   }

   public static Account getAccount(String accoun_n, String alias, String branch, Connection c) {
      Account list = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = c.prepareStatement("SELECT * FROM Account t where t.id = ? and t.branch = ?");
         ps.setString(1, accoun_n);
         ps.setString(2, branch);
         rs = ps.executeQuery();
         if (rs.next()) {
            list = new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state"));
         }
      } catch (SQLException var16) {
         LtLogger.getLogger().error(CheckNull.getPstr(var16));
         var16.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }

            if (rs != null) {
               rs.close();
            }
         } catch (Exception var15) {
         }

      }

      return list;
   }

   public static String multimoneytostr(BigDecimal amount, String language, String currency, Connection c) {
      String res = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = c.prepareStatement("select bf_money_to_str.MultiMoneyToStr(?, ?, ?) res from dual");
         ps.setBigDecimal(1, amount);
         ps.setString(2, language);
         ps.setString(3, currency);
         rs = ps.executeQuery();
         if (rs.next()) {
            res = rs.getString("res");
         }
      } catch (Exception var16) {
         LtLogger.getLogger().error(CheckNull.getPstr(var16));
         var16.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }

            if (rs != null) {
               rs.close();
            }
         } catch (Exception var15) {
         }

      }

      return res;
   }

   public static double GetCourse(String currency_1, String currency_2, Connection c) {
      double res = 0.0D;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = c.prepareStatement("select info.GetCourse(?, ?, 1, sysdate) res from dual");
         ps.setString(1, currency_1);
         ps.setString(2, currency_2);
         rs = ps.executeQuery();
         if (rs.next()) {
            res = rs.getDouble("res");
         }
      } catch (Exception var16) {
         LtLogger.getLogger().error(CheckNull.getPstr(var16));
         var16.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }

            if (rs != null) {
               rs.close();
            }
         } catch (Exception var15) {
         }

      }

      return res;
   }

   public static String GetCur20206(String client_id, String branch, Connection c) {
      String res = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = c.prepareStatement("select t.cur_acc res from bf_visa_customers t where t.head_customer_id = ? and t.branch = ?");
         ps.setString(1, client_id);
         ps.setString(2, branch);
         rs = ps.executeQuery();
         if (rs.next()) {
            res = rs.getString("res");
         }
      } catch (Exception var15) {
         LtLogger.getLogger().error(CheckNull.getPstr(var15));
         var15.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }

            if (rs != null) {
               rs.close();
            }
         } catch (Exception var14) {
         }

      }

      return res;
   }

   public static String GetCur20206_tclient(String client_id, String branch, Connection c) {
      String res = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = c.prepareStatement("select t.cur_acc res from bf_visa_customers t where t.tieto_customer_id = ? and t.branch = ?");
         ps.setString(1, client_id);
         ps.setString(2, branch);
         rs = ps.executeQuery();
         if (rs.next()) {
            res = rs.getString("res");
         }
      } catch (Exception var15) {
         LtLogger.getLogger().error(CheckNull.getPstr(var15));
         var15.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }

            if (rs != null) {
               rs.close();
            }
         } catch (Exception var14) {
         }

      }

      return res;
   }

   public static class actions_for_acc {
      private int deal_group;
      private int deal_id;
      private int action_id;
      private String name;

      public int getDeal_group() {
         return this.deal_group;
      }

      public void setDeal_group(int deal_group) {
         this.deal_group = deal_group;
      }

      public int getDeal_id() {
         return this.deal_id;
      }

      public void setDeal_id(int deal_id) {
         this.deal_id = deal_id;
      }

      public int getAction_id() {
         return this.action_id;
      }

      public void setAction_id(int action_id) {
         this.action_id = action_id;
      }

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public actions_for_acc(int deal_group, int deal_id, int action_id, String name) {
         this.deal_group = deal_group;
         this.deal_id = deal_id;
         this.action_id = action_id;
         this.name = name;
      }
   }
}
