package com.is.account.sevice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.account.model.Account;
import com.is.base.History;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class AccountService {
    private static Logger logger = Logger.getLogger(AccountService.class);

    private String alias;
    private String branch;
    private String un;
    private String pw;

    private AccountService(String branch, String alias, String un, String pw) {
        this.branch = branch;
        this.alias = alias;
        this.un = un;
        this.pw = pw;
    }

    public static AccountService getInstance(String branch, String alias, String un, String pw) {
        return new AccountService(branch, alias, un, pw);
    }

    public Map<Integer, String> getAvailableActions(Account acc) {
        Map<Integer, String> list = new HashMap<Integer, String>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {//"select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id"
            /*c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("select aa.id data, aa.name label " +
                    "from trans_account tc, action_account aa " +
                    "where tc.deal_id = ? " +
                    "and state_begin = ? " +
                    "and aa.deal_id = ? " +
                    "and aa.id = tc.action_id " +
                    "and aa.manual=1");*/
        	c=ConnectionPool.getConnection(un, pw, alias);
            ps = c.prepareStatement("select vta.action_id data, aa.name label from v_trans_account vta, " +
                    "action_account aa " +
                    "where vta.deal_id = ? " +
                    "and vta.state_begin = ? " +
                    "and aa.deal_id = vta.deal_id " +
                    "and aa.id = vta.action_id");
            ps.setInt(1, acc.getSign_registr());
            ps.setInt(2, acc.getState());
            //ps.setInt(3, acc.getSign_registr());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("data"),
                        rs.getString("label"));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return list;
    }


    public Res doAction(Account account, int actionid) {
        Res res = null;
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement csi = null;
        try {
            c = ConnectionPool.getConnection(un, pw, alias);
            csi = c.prepareCall("{ call info.init() }");
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{ call Param.clearparam() }");
            ccs.execute();
            ccs = c.prepareCall("{? = call Param.getparam('ID') }");
            ccs.registerOutParameter(1, java.sql.Types.VARCHAR);

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
            cs.setString(2, account.getName() != null ? account.getName() : "NEW ACCOUNT");
            cs.execute();
            cs.setString(1, "SGN");
            cs.setString(2, account.getSgn() != null ? account.getSgn() : "A");
            cs.execute();
            cs.setString(1, "BAL");
            cs.setString(2, account.getBal() != null ? account.getBal() : "B");
            cs.execute();
            cs.setString(1, "SIGN_REGISTR");
            cs.setInt(2, account.getSign_registr());
            cs.execute();
            cs.setString(1, "ACC_GROUP_ID");
            cs.setString(2, account.getAcc_group_id());
            cs.execute();

            acs.setInt(1, 2);
            acs.setInt(2, account.getSign_registr());//2
            acs.setInt(3, actionid);
            //logger.error("Account error " + String.format("groupId = %d, dealType = %d, action = %d\n",
             //       2, account.getSign_registr(), actionid));
            csi.execute();
            acs.execute();
            ccs.execute();


            //boolean isConfirmed = actionid == 2
            //        && account.getSign_registr() == 2
            //        &&
            //        !ClientUtil.isClientConfirmed(c, account.getClient());
            //logger.error("Account Action " + actionid + account);
            //logger.error("\n isConfirmed " + isConfirmed);
            /*if(isConfirmed) {
                try {
                    ClientJ byStringId = ClientDao.
                            getInstance(alias).
                            getItemByStringId(account.getBranch(),
                                    account.getClient());
                    logger.error("\n byString iD ");
                    SapHandler.makeSapRequest(
                            byStringId, 2);
                }
                catch (Exception e){
                    logger.error(CheckNull.getPstr(e));
                    throw e;
                }
			}*/
            c.commit();
            res = new Res(0, ccs.getString(1));
            if (actionid == 6) {
                account.setId(null);
            }

        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
            DbUtils.rollback(c);
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(acs);
            DbUtils.closeStmt(ccs);
            DbUtils.closeStmt(csi);
            ConnectionPool.close(c);
        }
        return res;
    }


    public String getNewAccOrder(Account acc) {
        String idOrder = acc.getSign_registr() == 3 ? "002" : "001";

        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            cs.execute();
            ps = c.prepareStatement("select info.getnewaccorder(?,?,?) from dual");
            ps.setString(1, acc.getAcc_bal());
            ps.setString(2, acc.getCurrency());
            ps.setString(3, acc.getClient());
            rs = ps.executeQuery();
            if (rs.next()) {
                idOrder = rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return idOrder;
    }

    public List<History> getHistory(String cl_id) {
        return DbUtils.getHistory(SqlScripts.ACCOUNT_HISTORY.getSql(), cl_id, branch, alias);
    }

    public String getClientname(String branchId, String clientId) {
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String name = null;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("select name from client where branch=? and id_client=?");
            ps.setString(1, branchId);
            ps.setString(2, clientId);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
            ConnectionPool.close(c);
        }
        return name;
    }
}
