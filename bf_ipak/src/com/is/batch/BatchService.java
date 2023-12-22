package com.is.batch;

import com.is.ConnectionPool;
import com.is.account.model.Account;
import com.is.base.utils.DbUtils;
import com.is.customer_.ActionsEnum;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.service.CustomerActionService;
import com.is.customer_.local.validator.CustomerValidator;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.sbs.service.Result;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06.05.2017.
 * 17:12
 */
public class BatchService {
    private Logger logger = Logger.getLogger(BatchService.class);
    private SessionAttributes sessionAttributes;
    private final String HEADER = "select * from (";
    private final String FOOTER_ = ") b where b.rownm >= ? and b.rownm <= ?";
    private final String BODY = "select rownum rownm, t.* from (SELECT a.*," +
            "(select c.name from client c where c.id_client = a.customer) organizationName FROM CARD_CUSTOMER_TEMP a) t";
    private final String BODY_DECODED =
            "select rownum rownm, t.*\n" +
                    "  from (select a.*,\n" +
                    "  (select s.state from client_p s where s.id = a.id_client) state_decoded,\n" +
                    "  (select c.name from client c where c.id_client = a.customer) organizationName" +
                    "          from card_customer_temp a) t;\n";

    private BatchService(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public static BatchService getInstance(SessionAttributes sessionAttributes) {
        return new BatchService(sessionAttributes);
    }

    public List<Batch> getData(Criteria criteria) {
        List<Batch> list = new ArrayList<Batch>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            FilterStatement filterStatement = new FilterStatement(criteria);
            String SQLStatement = HEADER;
            SQLStatement += BODY;
            SQLStatement += filterStatement.generateConditions();
            SQLStatement += FOOTER_;

            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementParameters(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Batch batch = mapResultSet(resultSet);
                list.add(batch);
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
    }

    private Batch mapResultSet(ResultSet resultSet) throws SQLException {
        Batch batch = new Batch();
        batch.setId(resultSet.getLong("id_seq"));
        batch.setBranch(resultSet.getString("branch"));
        batch.setState(resultSet.getString("state"));
        batch.setId_client(resultSet.getString("id_client"));
        batch.setEmbossed_ch_name(resultSet.getString("embossed_ch_name"));
        batch.setBirth_date(resultSet.getDate("birth_date"));
        batch.setOrganizationId(resultSet.getString("customer"));
        batch.setOrganizationName(resultSet.getString("organizationName"));
        batch.setFirst_name(resultSet.getString("I_RUS"));
        batch.setSurname(resultSet.getString("F_RUS"));
        batch.setSecond_name(resultSet.getString("O_RUS"));
        batch.setP_id_series(resultSet.getString("p_id_series"));
        batch.setP_id_number(resultSet.getString("p_id_number"));
        batch.setMessage(resultSet.getString("mess"));
        return batch;
    }

    public int getCount(Criteria criteria) {
        int count = 0;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            FilterStatement filterStatement = new FilterStatement(criteria);

            String SQLStatement = "SELECT COUNT(*) FROM CARD_CUSTOMER_TEMP";
            SQLStatement += filterStatement.generateConditions();

            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementWithoutBounds(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return count;
    }

    public Customer getCustomer(Batch batch) {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            preparedStatement = c.prepareStatement(
                    "SELECT * FROM CARD_CUSTOMER_TEMP t where t.id_seq = ?");
            preparedStatement.setLong(1, batch.getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                customer = mapResultSetToCustomer(resultSet);

            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return customer;
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new CustomerBuilder()
                .setBranch(resultSet.getString("branch"))
                .setId(resultSet.getString("id_client"))
                //.setState(resultSet.getString("state"))
                .setP_family(resultSet.getString("surname"))
                .setP_first_name(resultSet.getString("first_name"))
                .setP_patronymic(resultSet.getString("second_name"))
                .setP_birthday(resultSet.getDate("birth_date"))
                .setP_code_gender(resultSet.getString("sex"))
                .setCode_resident(resultSet.getString("residence"))
                .setP_type_document(resultSet.getString("TYPE_DOCUMENT"))
                .setP_passport_serial(resultSet.getString("P_ID_SERIES"))
                .setP_passport_number(resultSet.getString("P_ID_NUMBER"))
                .setP_passport_place_registration(resultSet.getString("P_ID_AUTHORITY"))
                .setP_passport_date_registration(resultSet.getDate("P_ID_ISSUE_DATE"))
                .setP_passport_date_expiration(resultSet.getDate("PASSPORT_DATE_EXPIRATION"))
                .setP_code_adr_region(resultSet.getString("region"))
                .setP_code_adr_distr(resultSet.getString("code_distr"))
                //.setP_phone_home(resultSet.getString("PRIMARY_PHONE"))
                //.setP_phone_mobile(resultSet.getString("MOBILE_PHONE"))
                //.setP_email_address(resultSet.getString("EMAIL"))
                .setP_code_nation(resultSet.getString("code_nation"))
                .setP_inps(resultSet.getString("INPS"))
                .setName(resultSet.getString("FIO"))
                .setP_birth_place(resultSet.getString("BIRTH_PLACE"))
                .setCode_country(resultSet.getString("STR"))
                .setP_code_citizenship(resultSet.getString("STR"))
                .setP_code_tax_org(resultSet.getString("GNI"))
                .setP_number_tax_registration(resultSet.getString("INN"))
                .setP_code_capacity(resultSet.getString("STATUSTS"))
                .setP_pension_sertif_serial(resultSet.getString("TSSERIA"))
                .setP_num_certif_capacity(resultSet.getString("TSNUMBER"))
                .setP_capacity_status_place(resultSet.getString("TSMESTOVID"))
                .setP_capacity_status_date(resultSet.getDate("TSDATE"))
                /*.setP_family_local(resultSet.getString("F_RUS"))
                .setP_first_name_local(resultSet.getString("I_RUS"))
                .setP_patronymic_local(resultSet.getString("O_RUS"))*/
                .setP_family_local(resultSet.getString("surname"))
                .setP_first_name_local(resultSet.getString("first_name"))
                .setP_patronymic_local(resultSet.getString("second_name"))
                .setP_pass_place_region(resultSet.getString("regdoc_region"))
                .setP_pass_place_district(resultSet.getString("regdoc_distr"))
                .setP_post_address_quarter(resultSet.getString("kvartal"))
                .setP_post_address_house(resultSet.getString("dom"))
                .setP_post_address_flat(resultSet.getString("kvartira"))
                .setP_post_address_street(resultSet.getString("address_line1"))
                .setP_post_address(
                        String.format("%s %s",
                                resultSet.getString("address_line1"),
                                resultSet.getString("address_line2")))
                .createCustomer();
        return customer;
    }

    public void update(Batch batch) throws Exception {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            preparedStatement =
                    c.prepareStatement("UPDATE CARD_CUSTOMER_TEMP SET " +
                            "ID_CLIENT = ?,STATE = ?,MESS = null WHERE ID_SEQ = ?");
            preparedStatement.setString(1, batch.getId_client());
            preparedStatement.setString(2, batch.getState());
            preparedStatement.setLong(3, batch.getId());
            preparedStatement.execute();
            c.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            c.rollback();
            throw e;
        } finally {
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
    }

    public void packageOpen() throws Exception {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        CustomerActionService service = CustomerActionService.getInstance(sessionAttributes);
        CustomerValidator customerValidator = CustomerValidator.getInstance(null, sessionAttributes);
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            preparedStatement = c.prepareStatement("" +
                    "SELECT * FROM CARD_CUSTOMER_TEMP WHERE (STATE IS NULL OR STATE = 0) and branch = ?");
            preparedStatement.setString(1, sessionAttributes.getBranch());
            resultSet = preparedStatement.executeQuery();

            List<Customer> list = new ArrayList<Customer>();
            updateStatement = c.prepareStatement("UPDATE CARD_CUSTOMER_TEMP " +
                    "SET ID_CLIENT = ?,MESS = ?,STATE = ? WHERE ID_SEQ = ?");
            //int count = 0;
            while (resultSet.next()) {
                // count += 1;
                //if (count == 100) {
                //updateStatement.executeBatch();
                // count = 0;
                //}

                Customer customer = mapResultSetToCustomer(resultSet);
                String idSEQ = resultSet.getString("ID_SEQ");
                customer.initBaseAttributes(sessionAttributes.getBranch());
                customerValidator.setCustomer(customer);
                try {
                    if (customerValidator.validate()) {
                        Customer customer_ = service.executeAction(customer,
                                ActionsEnum.ACTION_CREATE_BUSINESS_PARTNER.action());
                        // logger.error("Success " + customerValidator.getMessage());
                        updateMessage(updateStatement,
                                customer_.getId(),
                                null,
                                idSEQ,
                                1);
                    } else
                        throw new Exception(customerValidator.getMessage());
                } catch (Exception e) {
                    logger.error("Failure ");
                    updateMessage(updateStatement,
                            customer.getId(),
                            e.getMessage(),
                            idSEQ,
                            0);
                } finally {
                    c.commit();
                }
            }
            //updateStatement.executeBatch();

        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(updateStatement);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
    }

    private void updateMessage(PreparedStatement preparedStatement,
                               String id,
                               String message,
                               String id_seq,
                               int state) throws SQLException {
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, message);
        preparedStatement.setInt(3, state);
        preparedStatement.setString(4, id_seq);
        preparedStatement.executeUpdate();
    }

    public void packageConfirmation() throws Exception {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        CustomerActionService service = CustomerActionService.getInstance(sessionAttributes);
        CustomerValidator customerValidator = CustomerValidator.getInstance(null, sessionAttributes);
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            preparedStatement = c.prepareStatement("SELECT * FROM CARD_CUSTOMER_TEMP WHERE STATE = 1 " +
                    "and branch = ?");
            preparedStatement.setString(1, sessionAttributes.getBranch());
            resultSet = preparedStatement.executeQuery();

            List<Customer> list = new ArrayList<Customer>();
            updateStatement = c.prepareStatement("UPDATE CARD_CUSTOMER_TEMP " +
                    "SET ID_CLIENT = ?,MESS = ?,STATE = ? WHERE ID_SEQ = ?");
            //int count = 0;
            while (resultSet.next()) {
                //count += 1;
                //if (count == 100) {
                //updateStatement.executeBatch();
                //count = 0;
                //}
                Customer customer = mapResultSetToCustomer(resultSet);
                customer = CustomerActionService.
                        getInstance(sessionAttributes).
                        getCustomer(c, customer.getBranch(), customer.getId());
                customerValidator.setCustomer(customer);
                String idSEQ = resultSet.getString("ID_SEQ");
                //logger.error("Customer Confirmation ID_SEQ " + idSEQ);
                try {
                    if (customerValidator.validate()) {
                        //logger.error("Customer Package Confirmation " + customer);
                        customer = service.executeAction(customer,
                                ActionsEnum.ACTION_CONFIRM_BUSINESS_PARTNER.action());
                        updateMessage(updateStatement,
                                customer.getId(),
                                null,
                                idSEQ,
                                2);
                    } else
                        throw new Exception(customerValidator.getMessage());
                } catch (Exception e) {
                    logger.error("Package Confirmation error " + e.getMessage());
                    updateMessage(updateStatement,
                            customer.getId(),
                            e.getMessage(),
                            idSEQ,
                            1);
                } finally {
                    c.commit();
                }
            }
            //updateStatement.executeBatch();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(updateStatement);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
    }

    public List<RefData> getTypeAccounts(String schema) {
        return RefDataService.getRefData("select m.id_card data,c.name label from ss_uzcard_card_mask m,ss_uzcard_card c where m.id_card=c.id_card", schema);
    }

    public Account getBalanceAccount(String key, String schema) {
        Account account = new Account();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement("select * from ss_uzcard_card_mask m where m.id_card=?");
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = new Account();
                account.setAcc_bal(resultSet.getString("acc_bal"));
                account.setId_order(resultSet.getString("acc_num"));
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return account;
    }

    public void packageAccount(Account account) throws Exception {
        Connection c = null;
        CallableStatement callableStatement = null;
        CallableStatement params = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();

            callableStatement = c.prepareCall("{call kernel.doAction(?,?,?)}");
            params = c.prepareCall("{call param.clearparam()}");
            params.execute();

            params = c.prepareCall("{call param.setParam(?,?)}");


            preparedStatement = c.prepareStatement("SELECT * FROM CARD_CUSTOMER_TEMP WHERE STATE = 2 and branch = ?");
            preparedStatement.setString(1, sessionAttributes.getBranch());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = mapResultSetToCustomer(resultSet);
                params.setString(1, "P_ACC_BAL");
                params.setString(2, account.getAcc_bal());
                params.execute();

                params.setString(1, "P_ID_ORDER");
                params.setString(2, account.getId_order());
                params.execute();

                params.setString(1, "ID_CLIENT");
                params.setString(2, customer.getId());
                params.execute();

                params.setString(1,"BRANCH");
                params.setString(2,customer.getBranch());
                params.execute();

                params.setString(1,"NAME");
                params.setString(2,customer.getName());
                params.execute();

                callableStatement.setInt(1, 208);
                callableStatement.setInt(2, 2);
                callableStatement.setInt(3, 1);

                callableStatement.execute();
                c.commit();
            }


        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            try {
                c.rollback();
            } catch (Exception e1) {

            }
            throw e;
        } finally {
            DbUtils.closeStmt(params);
            DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }
    }

    public void packageAccountConfirmation() throws Exception {
        Connection c = null;
        CallableStatement callableStatement = null;
        CallableStatement params = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();

            callableStatement = c.prepareCall("{call kernel.doAction(?,?,?)}");
            params = c.prepareCall("{call param.clearparam()}");
            params.execute();

            params = c.prepareCall("{call param.setParam(?,?)}");


            preparedStatement = c.prepareStatement("select s.branch,s.account " +
                    "from uzcard_batch_account s, account b, card_customer_temp c " +
                    "where c.branch = s.branch " +
                    "and c.id_client = s.id_client " +
                    "and s.account = b.id " +
                    "and b.state = 1");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Customer customer = mapResultSetToCustomer(resultSet);
                //params.setString(1, "P_ACC_BAL");
                //params.setString(2, account.getAcc_bal());
                //params.execute();

                //params.setString(1, "P_ID_ORDER");
                //params.setString(2, account.getId_order());
                //params.execute();

                //params.setString(1, "ID_CLIENT");
                //params.setString(2, customer.getId());
                params.setString(1,"BRANCH");
                params.setString(2,resultSet.getString("branch"));
                params.execute();

                params.setString(1,"ACCOUNT_ID");
                params.setString(2,resultSet.getString("ACCOUNT"));
                params.execute();

                callableStatement.setInt(1, 208);
                callableStatement.setInt(2, 2);
                callableStatement.setInt(3, 2);

                callableStatement.execute();
                c.commit();
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            try {
                c.rollback();
            } catch (Exception e1) {

            }
            throw e;
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(params);
            DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }
    }
}

