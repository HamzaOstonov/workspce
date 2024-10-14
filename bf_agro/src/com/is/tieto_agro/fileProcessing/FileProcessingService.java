package com.is.tieto_agro.fileProcessing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.db_utils.ConnectionUtils;
import com.is.db_utils.DBServiceUtils;
import org.apache.log4j.Logger;
import com.is.ConnectionPool;
import accounting_transaction_2.Transaction_service;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class FileProcessingService {
    private PreparedStatement updatePaydocStatePs = null;
    private PreparedStatement selectNotAcceptedPaydocsPs = null;
    private PreparedStatement selectPaydocsByFilePs = null;
    private PreparedStatement sessionInitPs = null;
    private Statement sessionInitPsSt = null;
    private CallableStatement isGoUserPS = null;
    private ArrayList<Statement> allStatements = null;

    private void initPreparedStatements(Connection connection) throws SQLException {
        allStatements = null;
        if (selectNotAcceptedPaydocsPs == null) {
            selectNotAcceptedPaydocsPs = connection.prepareStatement(
                    "SELECT DISTINCT pay_id, branch FROM bf_tr_paydocs WHERE (state_id = ? OR state_id = ?) AND parent_group_id=? ORDER BY branch");
            allStatements.add(selectNotAcceptedPaydocsPs);
        }
        if (selectPaydocsByFilePs == null) {
            selectPaydocsByFilePs = connection.prepareStatement(
                    "SELECT DISTINCT pay_id, branch FROM bf_tr_paydocs b WHERE (state_id = ? OR state_id = ?) AND b.parent_group_id=? AND b.parent_id IN (SELECT id FROM tieto_expt_records r WHERE r.tieto_file_id = ?) ORDER BY branch");
            allStatements.add(selectPaydocsByFilePs);
        }
        if (updatePaydocStatePs == null) {
            updatePaydocStatePs = connection.prepareStatement("UPDATE bf_tr_paydocs SET STATE_ID=? WHERE PAY_ID = ? ");
            allStatements.add(updatePaydocStatePs);
        }
        if (sessionInitPs == null) {
            sessionInitPs = connection
                    .prepareStatement("SELECT t.user_name result FROM ss_dblink_branch t WHERE t.branch = ?");
            allStatements.add(sessionInitPs);
        }
        if (sessionInitPsSt == null) {
            sessionInitPsSt = connection.createStatement();
            allStatements.add(sessionInitPsSt);
        }
        if (isGoUserPS == null) {
            isGoUserPS = connection.prepareCall("{call is_go_user(?,?)}");
            allStatements.add(isGoUserPS);
        }
    }

    private static final Logger log = Logger.getLogger(FileProcessing.class);

    public List<RefData> getFileTypes(String alias) {
        List<RefData> result = RefDataService
                .getRefData(
                        "select f.name label, tff.id data from fr_file f, tieto_files tff where f.id in "
                                + "(select tf.fr_file_id from tieto_files tf where id in "
                                + "(SELECT DISTINCT(tieto_file_id) FROM tieto_expt_records WHERE id IN "
                                + "(SELECT parent_id FROM bf_tr_paydocs WHERE parent_group_id = "
                                + Constants.PARENT_GROUP_ID + " AND (state_id=" + Constants.STATE_ADDED
                                + " OR state_id= " + Constants.STATE_ERROR + ")) ))" + " and tff. fr_file_id= f.id",
                        alias);
        for (RefData aResult : result) {
            aResult.setLabel(aResult.getLabel().substring(aResult.getLabel().lastIndexOf('\\') + 1));
        }
        result.add(new RefData("0", "Все"));
        return result;
    }

    private void updatePaydocState(Long id, Long state) throws SQLException {
        updatePaydocStatePs.setLong(1, state);
        updatePaydocStatePs.setLong(2, id);
        updatePaydocStatePs.executeQuery();
    }

    private ResultSet selectNotAcceptedPaydocs(Long fileId) throws SQLException {
        ResultSet resultSet;

        if (fileId == null) {
            selectNotAcceptedPaydocsPs.setLong(1, Constants.STATE_ADDED);
            selectNotAcceptedPaydocsPs.setLong(2, Constants.STATE_ERROR);
            selectNotAcceptedPaydocsPs.setLong(3, Constants.PARENT_GROUP_ID);
            resultSet = selectNotAcceptedPaydocsPs.executeQuery();
        } else {
            selectPaydocsByFilePs.setLong(1, Constants.STATE_ADDED);
            selectPaydocsByFilePs.setLong(2, Constants.STATE_ERROR);
            selectPaydocsByFilePs.setLong(3, Constants.PARENT_GROUP_ID);
            selectPaydocsByFilePs.setLong(4, fileId);
            resultSet = selectPaydocsByFilePs.executeQuery();
        }

        return resultSet;
    }

    private ResultSet selectBranch(String branch) throws SQLException {
        ResultSet resultSet;

        sessionInitPs.setString(1, branch);
        resultSet = sessionInitPs.executeQuery();

        return resultSet;
    }

    private void alterSession(Connection c, String alias, String user) throws SQLException {
        initPreparedStatements(c);
        sessionInitPsSt.executeUpdate("ALTER SESSION SET nls_date_format='dd.mm.yyyy'");
        sessionInitPsSt.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
        isGoUserPS.setString(1, user.toUpperCase());
        isGoUserPS.setInt(2, 99999);
        isGoUserPS.execute();
        sessionInitPsSt.execute("{call info.init()}");
    }

    private void alter_session_init(Connection c, String branch, String user) throws Exception {
        String alias;
        ResultSet rs = null;

        try {

            initPreparedStatements(c);
            rs = selectBranch(branch);

            if (rs.next()) {
                alias = rs.getString("result");
            } else {
                throw new Exception("Wrong branch: " + branch);
            }

            alterSession(c, alias, user);

        } catch (Exception e) {
            log.error(CheckNull.getPstr(e));
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void executeTransactions(String userName, String password, String alias, Long fileId) {
        Connection connection = null;
        ResultSet resultSet = null;
        Long payId;
        String currentBranch = null;
        try {
            connection = ConnectionPool.getConnection(userName, password, alias);
            initPreparedStatements(connection);
            Transaction_service transactionService = new Transaction_service(connection);
            resultSet = selectNotAcceptedPaydocs(fileId);

            while (resultSet.next()) {
                payId = resultSet.getLong("pay_id");
                try {
                    if (!resultSet.getString("branch").equals(currentBranch)) {
                        currentBranch = resultSet.getString("branch");
                        alter_session_init(connection, currentBranch, userName);
                    }
                    transactionService.execute_transaction_action(payId, 19);
                    try {
                        updatePaydocState(payId, Constants.STATE_ACCEPTED);

                    } catch (SQLException e1) {
                        log.error(CheckNull.getPstr(e1));
                    }
                } catch (Exception e) {
                    connection.rollback();
                    try {
                        updatePaydocState(payId, Constants.STATE_ERROR);
                    } catch (SQLException e1) {
                        log.error(CheckNull.getPstr(e1));
                    }

                    log.error(CheckNull.getPstr(e));
                } finally {
                    connection.commit();
                }
            }
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));
        } finally {
            try {
                ConnectionUtils.closeConnection(resultSet, allStatements, connection);
            } catch (SQLException e) {
                log.error(CheckNull.getPstr(e));
            }
        }
    }

    private String getCond(List<FilterField> flfields) {
        if (flfields.size() > 0) {
            return " and ";
        } else
            return " where ";
    }

    private List<FilterField> getFilterFields(FileProcessingFilter filter) {
        List<FilterField> flfields = new ArrayList<FilterField>();
        filter.setParent_group_id(Constants.PARENT_GROUP_ID);
        filter.setState_id(Constants.STATE_ADDED);

        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getBranch())) {
            flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
        }
        if (!CheckNull.isEmpty(filter.getD_date())) {
            flfields.add(
                    new FilterField(getCond(flfields) + "d_date=?", new java.sql.Date(filter.getD_date().getTime())));
        }
        if (!CheckNull.isEmpty(filter.getBank_cl())) {
            flfields.add(new FilterField(getCond(flfields) + "bank_cl=?", filter.getBank_cl()));
        }
        if (!CheckNull.isEmpty(filter.getAcc_cl())) {
            flfields.add(new FilterField(getCond(flfields) + "acc_cl=?", filter.getAcc_cl()));
        }
        if (!CheckNull.isEmpty(filter.getName_cl())) {
            flfields.add(new FilterField(getCond(flfields) + "name_cl=?", filter.getName_cl()));
        }
        if (!CheckNull.isEmpty(filter.getBank_co())) {
            flfields.add(new FilterField(getCond(flfields) + "bank_co=?", filter.getBank_co()));
        }
        if (!CheckNull.isEmpty(filter.getAcc_co())) {
            flfields.add(new FilterField(getCond(flfields) + "acc_co=?", filter.getAcc_co()));
        }
        if (!CheckNull.isEmpty(filter.getName_co())) {
            flfields.add(new FilterField(getCond(flfields) + "name_co=?", filter.getName_co()));
        }
        if (!CheckNull.isEmpty(filter.getSumma())) {
            flfields.add(new FilterField(getCond(flfields) + "summa=?", filter.getSumma()));
        }
        if (!CheckNull.isEmpty(filter.getPurpose())) {
            flfields.add(new FilterField(getCond(flfields) + "purpose=?", filter.getPurpose()));
        }
        if (!CheckNull.isEmpty(filter.getType_doc())) {
            flfields.add(new FilterField(getCond(flfields) + "type_doc=?", filter.getType_doc()));
        }
        if (!CheckNull.isEmpty(filter.getPdc())) {
            flfields.add(new FilterField(getCond(flfields) + "pdc=?", filter.getPdc()));
        }
        if (!CheckNull.isEmpty(filter.getParent_group_id())) {
            flfields.add(new FilterField(getCond(flfields) + "parent_group_id=?", filter.getParent_group_id()));
        }
        if (!CheckNull.isEmpty(filter.getParent_id())) {
            flfields.add(new FilterField(getCond(flfields) + "parent_id=?", filter.getParent_id()));
        }
        if (!CheckNull.isEmpty(filter.getDeal_id())) {
            flfields.add(new FilterField(getCond(flfields) + "deal_id=?", filter.getDeal_id()));
        }
        if (!CheckNull.isEmpty(filter.getState_id())) {
            flfields.add(new FilterField(getCond(flfields) + "(state_id=? OR state_id=4)", filter.getState_id()));
        }
        if (!CheckNull.isEmpty(filter.getFileId()) && !filter.getFileId().equals("0")) {
            flfields.add(new FilterField(
                    getCond(flfields) + "(parent_id IN ( SELECT id FROM tieto_expt_records WHERE tieto_file_id = ?))",
                    filter.getFileId()));
        }

        return flfields;
    }

    public List<FileProcessing> getFileProcessingFl(int pageIndex, int pageSize, FileProcessingFilter filter) {

        List<FileProcessing> list = new ArrayList<FileProcessing>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields = getFilterFields(filter);

        StringBuilder sql = new StringBuilder();
        String psql1 = "SELECT t.* FROM(SELECT t.*,rownum rwnm FROM (SELECT * FROM (";
        sql.append(psql1);
        String msql = "SELECT * FROM bf_tr_paydocs ";
        sql.append(msql);
        if (flFields.size() > 0) {
            for (FilterField flField : flFields) {
                sql.append(flField.getSqlwhere());
            }
        }
        String psql2 = " ) s ) t WHERE rownum <= ?) t  WHERE t.rwnm >= ?";
        sql.append(psql2);

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(sql.toString());
            for (params = 0; params < flFields.size(); params++) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            params++;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params, v_lowerbound);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FileProcessing(rs.getLong("id"), rs.getLong("pay_id"), rs.getString("branch"),
                        rs.getDate("d_date"), rs.getString("bank_cl"), rs.getString("acc_cl"), rs.getString("name_cl"),
                        rs.getString("bank_co"), rs.getString("acc_co"), rs.getString("name_co"), rs.getLong("summa"),
                        rs.getString("purpose"), rs.getString("type_doc"), rs.getString("pdc"),
                        rs.getLong("parent_group_id"), rs.getLong("parent_id"), rs.getString("cash_code"),
                        rs.getLong("id_transh_purp"), rs.getString("schema_name"), rs.getLong("ord"),
                        rs.getString("g_branch"), rs.getLong("g_docid"), rs.getString("purp_code"),
                        rs.getLong("pay_type"), rs.getLong("trans_type"), rs.getLong("acc_dt_id"),
                        rs.getLong("acc_ct_id"), rs.getLong("deal_group_id"), rs.getLong("deal_id"),
                        rs.getLong("parent_deal_id"), rs.getLong("state_id")));
            }
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));

        } finally {
            try {
                ConnectionUtils.closeConnection(rs, ps, c);
            } catch (SQLException e) {
                log.error(CheckNull.getPstr(e));
            }
        }
        return list;
    }

    public int getCount(FileProcessingFilter filter) {
        int n = 0;
        List<FilterField> flFields = getFilterFields(filter);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) ct FROM bf_tr_paydocs ");
        if (flFields.size() > 0) {
            for (FilterField flField : flFields) {
                sql.append(flField.getSqlwhere());
            }
        }
        try {
            n = DBServiceUtils.getSizeByFilter(sql, flFields);
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));

        }

        return n;
    }

}
