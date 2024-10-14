package com.is.globalTieto.confirmRefill;

import accounting_transaction_2.Transaction_service;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.db_utils.ConnectionUtils;
import com.is.db_utils.DBServiceUtils;
import com.is.globalTieto.Constants;
import com.is.globalTieto.tietoModels.ExecTransactionRequest;
import com.is.globalTieto.tietoModels.ResponseInfo;
import com.is.globalTieto.tietoModels.ResponseInfoHolder;
import com.is.globalTieto.tietoModels.Settings;
import com.is.globalTieto.webServices.KapitalWebService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmRefillService {
    private KapitalWebService service;
    private static Logger log = Logger.getLogger(ConfirmRefillService.class);

    ConfirmRefillService(){
        service = new KapitalWebService();
    }

    public Map<Long, String> getRefillStates() {
        Map<Long, String> result = new HashMap<Long, String>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM bf_tieto_refill_state");
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getLong("id"), rs.getString("description"));
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

        return result;
    }

    private Settings getRefillSettings(){
        Connection c;
        PreparedStatement ps;
        ResultSet rs;

        Settings result = new Settings();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM bf_ti_refill_sets");
            rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                String value = rs.getString("value");
                if(name.equals(Constants.FIELD_PAYMENT_MODE)){
                    result.setPaymentMode(value);
                }else if(name.equals(Constants.FIELD_ACC_CCY)){
                    result.setAccCcy(value);
                }else if(name.equals(Constants.FIELD_TRANZ_CCY)){
                    result.setTranzCcy(value);
                }else if(name.equals(Constants.FIELD_TRANZ_TYPE)){
                    result.setTranzType(value);
                }
            }
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));
        }

        return result;
    }

    public ResponseInfo tietoRefill(String cardAccount, Long sum){
        ExecTransactionRequest request = new ExecTransactionRequest();
        ResponseInfoHolder response = new ResponseInfoHolder();
        Settings settings = getRefillSettings();
        request.setCard_acct(cardAccount);
		request.setPayment_mode(settings.getPaymentMode());
		request.setCard_acct_ccy(settings.getAccCcy());
		request.setTran_type(settings.getTranzType());
        request.setTran_ccy(settings.getTranzCcy());
        request.setTran_amnt(BigDecimal.valueOf(sum));

        service.executeTransaction(request, response);
        return response.value;
    }

    public static int getCource(String currency, int courceType){
        Connection c = null;
        int result = 1;
        try{

            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("{call info.initday(sysdate)}");
            ps.executeUpdate();


            ps = c.prepareStatement("select Info.GetEqual(1,?,'000',?) state from dual");
            ps.setString(1, currency);
            ps.setInt(2, courceType);
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getInt("state");
            ps.close();
            rs.close();

        }
        catch(SQLException e){
            log.error(CheckNull.getPstr(e));
        }
        finally{

            ConnectionPool.close(c);
        }
        return result;
    }

    public void bankRefill(ConfirmRefill refill, long operationId, String accName, String user, String pass, String alias) throws SQLException, CloneNotSupportedException {
        Connection c;
        Map<String, Object> params = new HashMap<String, Object> ();
        Long payId;
//        DBUtils dbUtils = new DBUtils();

        c = ConnectionPool.getConnection(user, pass ,alias);
        Transaction_service transactionService = new Transaction_service(c);

        params.put("branch", refill.getBranch());
        params.put("operation_id", operationId);
        params.put("parent_group_id", Constants.PARENT_GROUP_ID);
        params.put("parent_deal_id", Constants.PARENT_DEAL_ID);
        params.put("PARENT_ID", refill.getId());
        params.put(accName, refill.getCard_acct());

//            dbUtils.alterSession(refill.getBranch(), user, pass, alias);

        payId = transactionService.create_pay(operationId,
                                refill.getBranch(),
                                refill.getTransaction_amnt().longValue(),
                                refill.id, Constants.PARENT_GROUP_ID,
                                Long.valueOf(Constants.PARENT_DEAL_ID).intValue(),
                                params);
        transactionService.execute_transaction_action(payId, 19);
        }

    public long getOperationId(String account, Long isCashPayment){
        long operationId = 0;

        if(account.substring(0, 5).equals("22618") &&
                account.substring(17,18).equals("9")){
            operationId = 1003;
        }else if(account.substring(0, 5).equals("22618") &&
                isCashPayment == 1){
            operationId = 1001;
        }else if(account.substring(0, 5).equals("22618") &&
                isCashPayment == 0){
            operationId = 1002;
        }else if(account.substring(0, 5).equals("22619")){
            operationId = 1004;
        }else if(account.substring(0, 5).equals("22620")){
            operationId = 1005;
        }

        return operationId;
    }

    public String getAccNameByOperationId(long operationId){
        String result = "";

        if(operationId == 1001 || operationId == 1002){
            result = "ACC_22618";
        }else if(operationId == 1003){
            result = "ACC_22618_9";
        }else if(operationId == 1004){
            result = "ACC_22619";
        }else{
            result = "ACC_22620";
        }

        return result;
    }

    public void setRefillState(long refillId, long stateId){
        Connection c;
        PreparedStatement ps;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("UPDATE bf_tieto_refill SET state_id = ? WHERE id = ?");
            ps.setLong(1, stateId);
            ps.setLong(2, refillId);
            ps.executeQuery();
            c.commit();
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));
        }
    }

    private static String getCond(List<FilterField> filterFields) {
        if (filterFields.size() > 0) {
            return " and ";
        } else {
            return " where ";
        }
    }

    private static List<FilterField> getFilterFields(ConfirmRefillFilter filter) {
        List<FilterField> filterFields = new ArrayList<FilterField>();

        if (!CheckNull.isEmpty(filter.getId())) {
            filterFields.add(new FilterField(getCond(filterFields) + "id = ?", filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getCard_acct())) {
            filterFields.add(new FilterField(getCond(filterFields) + "card_acct = ?", filter.getCard_acct()));
        }
        if (!CheckNull.isEmpty(filter.getCard())) {
            filterFields.add(new FilterField(getCond(filterFields) + "card = ?", filter.getCard()));
        }
        if (filter.getTransaction_amnt() != null) {
            filterFields
                    .add(new FilterField(getCond(filterFields) + "transaction_amnt = ?", filter.getTransaction_amnt()));
        }
        if (!CheckNull.isEmpty(filter.getBranch())) {
            filterFields.add(new FilterField(getCond(filterFields) + "branch = ?", filter.getBranch()));
        }
        if (!CheckNull.isEmpty(filter.getClient_tieto())) {
            filterFields.add(new FilterField(getCond(filterFields) + "client_tieto = ?", filter.getClient_tieto()));
        }
        if (!CheckNull.isEmpty(filter.getClient_bank())) {
            filterFields.add(new FilterField(getCond(filterFields) + "client_bank = ?", filter.getClient_bank()));
        }
        if (filter.getState() != null) {
            filterFields.add(new FilterField(getCond(filterFields) + "state_id = ?", filter.getState()));
        }
        if (!CheckNull.isEmpty(filter.getPayment_mode())) {
            filterFields.add(new FilterField(getCond(filterFields) + "payment_mode = ?", filter.getPayment_mode()));
        }
        if (!CheckNull.isEmpty(filter.getBank_c())) {
            filterFields.add(new FilterField(getCond(filterFields) + "bank_c = ?", filter.getBank_c()));
        }
        if (!CheckNull.isEmpty(filter.getGroupc())) {
            filterFields.add(new FilterField(getCond(filterFields) + "groupc = ?", filter.getGroupc()));
        }
        if (!CheckNull.isEmpty(filter.getCard_acc_ccy())) {
            filterFields.add(new FilterField(getCond(filterFields) + "card_acc_ccy = ?", filter.getCard_acc_ccy()));
        }
        if (!CheckNull.isEmpty(filter.getTran_type())) {
            filterFields.add(new FilterField(getCond(filterFields) + "tran_type = ?", filter.getTran_type()));
        }
        if (!CheckNull.isEmpty(filter.getTran_ccy())) {
            filterFields.add(new FilterField(getCond(filterFields) + "tran_ccy = ?", filter.getTran_ccy()));
        }
        if (!CheckNull.isEmpty(filter.getClient_name())) {
            filterFields.add(new FilterField(getCond(filterFields) + "client_name = ?", filter.getClient_name()));
        }
        if (!CheckNull.isEmpty(filter.getClient_surname())) {
            filterFields.add(new FilterField(getCond(filterFields) + "client_surname = ?", filter.getClient_surname()));
        }

        return filterFields;
    }

    public static int getTotalSize(ConfirmRefillFilter filter) {
        int result = 0;
        StringBuilder sql = new StringBuilder();

        if (filter == null) {
            filter = new ConfirmRefillFilter();
        }
        List<FilterField> filterFields = getFilterFields(filter);

        sql.append("SELECT COUNT(*) FROM bf_tieto_refill ");

        if (filterFields.size() > 0) {
            for (FilterField filterField : filterFields) {
                sql.append(filterField.getSqlwhere());
            }
        }

        try {
            DBServiceUtils.getSizeByFilter(sql, filterFields);
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));
        }

        return result;
    }

    public static List<ConfirmRefill> getOnePageData(int startItemNumber, int lastItemNumber,
                                                     ConfirmRefillFilter filter) {
        List<ConfirmRefill> result = new ArrayList<ConfirmRefill>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        if (filter == null) {
            filter = new ConfirmRefillFilter();
        }
        List<FilterField> filterFields = getFilterFields(filter);

        sql.append("SELECT * FROM (SELECT p.*, ROWNUM rn FROM bf_tieto_refill p ");
        for (FilterField filterField : filterFields) {
            sql.append(filterField.getSqlwhere());
        }
        sql.append(" ORDER BY p.id) WHERE rn >= ? and rn < ?");

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(sql.toString());

            int i;
            for (i = 0; i < filterFields.size(); ++i) {
                ps.setObject(i + 1, filterFields.get(i).getColobject());
            }
            ps.setInt(++i, startItemNumber);
            ps.setInt(++i, lastItemNumber);

            rs = ps.executeQuery();
            while (rs.next()) {
                ConfirmRefill confirmRefill = new ConfirmRefill();
                confirmRefill.setId(rs.getLong("ID"));
                confirmRefill.setCard_acct(rs.getString("CARD_ACCT"));
                confirmRefill.setCard(rs.getString("CARD"));
                confirmRefill.setTransaction_amnt(BigDecimal.valueOf(rs.getLong("TRANSACTION_AMNT")));
                confirmRefill.setBranch(rs.getString("BRANCH"));
                confirmRefill.setClient_tieto(rs.getString("CLIENT_TIETO"));
                confirmRefill.setClient_bank(rs.getString("CLIENT_BANK"));
                confirmRefill.setState(BigDecimal.valueOf(rs.getLong("STATE_ID")));
                confirmRefill.setPayment_mode(rs.getString("PAYMENT_MODE"));
                confirmRefill.setBank_c(rs.getString("BANK_C"));
                confirmRefill.setGroupc(rs.getString("GROUPC"));
                confirmRefill.setCard_acc_ccy(rs.getString("CARD_ACC_CCY"));
                confirmRefill.setTran_type(rs.getString("TRAN_TYPE"));
                confirmRefill.setTran_ccy(rs.getString("TRAN_CCY"));
                confirmRefill.setClient_name(rs.getString("CLIENT_NAME"));
                confirmRefill.setClient_surname(rs.getString("CLIENT_SURNAME"));
                confirmRefill.setIs_cash_payment(rs.getLong("IS_CASH_PAYMENT"));

                result.add(confirmRefill);
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

        return result;
    }
}
