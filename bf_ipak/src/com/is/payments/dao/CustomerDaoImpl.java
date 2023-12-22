
package com.is.payments.dao;

import com.is.base.utils.DbUtils;
import com.is.customer_.core.dao.CustomerDao;
import com.is.customer_.core.dao.CustomerDaoException;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by root on 23.05.2017.
 * 17:29
 */
public class CustomerDaoImpl implements CustomerDao {
    private final static Logger logger = Logger.getLogger(CustomerDaoImpl.class);

    private SessionAttributes sessionAttributes;

    public static final String EXIST_STATEMENT = "SELECT COUNT(*) FROM CL_ADD_04 a " +
            "WHERE A.BRANCH = ? AND A.TYPE_DOCUMENT = ? " +
            "AND A.PASSPORT_SERIAL = ? AND A.PASSPORT_NUMBER = ?";

    private static final java.lang.String GET_CUSTOMER_BY_DOCUMENT = "SELECT * FROM CL_ADD_04 " +
            "WHERE BRANCH = ? " +
            "AND TYPE_DOCUMENT = ? " +
            "AND PASSPORT_SERIAL = ? " +
            "AND PASSPORT_NUMBER = ?";

    private static final String NEXT_VAL = "SELECT CL_ADD_SEQ.NEXTVAL FROM DUAL";
    private static final String INSERT_STATEMENT =
            "INSERT INTO CL_ADD_04 (BRANCH,ID,FAMILY,FIRST_NAME,PATRONYMIC,BIRTH_DATE,BIRTH_PLACE,"
                    + "CITIZEN,TYPE_DOCUMENT,PASSPORT_SERIAL,PASSPORT_NUMBER,PASSPORT_PLACE_REGISTRATION,"
                    + "NUMBER_TAX_REGISTRATION,PHONE,POST_ADDRESS_TEMP,RISK_DEGREE,RISK_DEGREE_DETAIL,ADD_DATA,"
                    + "DATE_FIRST_ACC,DATE_CHANGE,DATE_OPEN,EMP_ACCOUNT_OPEN,EMP_ACCOUNT_CONFIRM,STATE,RISK_DATE,"
                    + "DATE_LAST_SAVE,DOC_TYPE,ACCOUNT_OPEN,CLIENT_ID,ID_EXCEL,PASSPORT_DATE_EXPIRATION)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "		  ?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE CL_ADD_04 " +
            "SET FAMILY=?,FIRST_NAME=?,PATRONYMIC=?,BIRTH_DATE=?,BIRTH_PLACE=?,CITIZEN=?,TYPE_DOCUMENT=?," +
            "PASSPORT_SERIAL=?," +
            "PASSPORT_NUMBER=?,PASSPORT_PLACE_REGISTRATION=?," +
            "NUMBER_TAX_REGISTRATION=?,PHONE=?,PASSPORT_DATE_EXPIRATION=? WHERE BRANCH = ? AND ID = ?";

    private Connection connection;

    public CustomerDaoImpl(Connection connection, SessionAttributes sessionAttributes) {
        this.connection = connection;
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public Customer getCustomer(String branch, String id) throws CustomerDaoException, SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CL_ADD_04 A WHERE A.BRANCH = ? AND A.ID = ?");
            //logger.error("Return branch " + branch + " id " + id);
            preparedStatement.setString(1, branch);
            preparedStatement.setString(2, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //logger.error("RETURN");
                return mapResultSetToCustomer(resultSet);
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(preparedStatement);
            DbUtils.closeResultSet(resultSet);
        }
        throw new CustomerDaoException("No such customer ");
    }

    @Override
    public Customer createCustomer(Customer customer) throws CustomerDaoException, SQLException {
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall("{call info.init()}");
            callableStatement.execute();
            callableStatement.close();

            /*callableStatement = connection.prepareCall("{ " +
                    "call stoplist.reaction(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1,null);//customer.getBranch());
            callableStatement.setString(2,null);//customer.getId());
            callableStatement.setString(3, "%" + customer.getFullName() + "%");
            callableStatement.setString(4, null);//customer.getP_passport_serial());
            callableStatement.setString(5, null);//customer.getP_passport_number());
            callableStatement.setString(6, null);//customer.getP_type_document());
            callableStatement.setString(7, "%" + customer.getP_family() + "%");
            callableStatement.setString(8, "%" + customer.getP_first_name() + "%");
            callableStatement.setString(9, "%" + customer.getP_patronymic() + "%");
            callableStatement.execute();

            logger.error("STOP LIST PARAMS " + customer.getFullName() +
                    " " + customer.getP_family()
                    + " " + customer.getP_first_name()
                    + " " + customer.getP_patronymic());
*/
            preparedStatement = connection.prepareStatement(EXIST_STATEMENT);
            preparedStatement.setString(1, sessionAttributes.getBranch());
            preparedStatement.setString(2, customer.getP_type_document());
            preparedStatement.setString(3, customer.getP_passport_serial());
            preparedStatement.setString(4, customer.getP_passport_number());
            resultSet = preparedStatement.executeQuery();

            int fetchSize = 0;
            if (resultSet.next())
                fetchSize = resultSet.getInt(1);

            if (fetchSize > 0) {
                preparedStatement.close();
                resultSet.close();
                String idSap = customer.getIdSap();
                preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_DOCUMENT);
                preparedStatement.setString(1, customer.getBranch());
                preparedStatement.setString(2, customer.getP_type_document());
                preparedStatement.setString(3, customer.getP_passport_serial());
                preparedStatement.setString(4, customer.getP_passport_number());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Customer resultCustomer = mapResultSetToCustomer(resultSet);
                    customer.setBranch(resultCustomer.getBranch());
                    customer.setId(resultCustomer.getId());
                    customer.setIdSap(idSap);
                }
            } else {
                resultSet.close();
                preparedStatement.close();

                preparedStatement = connection.prepareStatement(NEXT_VAL);
                resultSet = preparedStatement.executeQuery();

                String maxId = null;
                if (resultSet.next())
                    maxId = resultSet.getString(1);
                customer.setId(maxId);

                resultSet.close();
                preparedStatement.close();

                preparedStatement = connection.prepareStatement(INSERT_STATEMENT);
                insertCustomer(preparedStatement, customer);

                callableStatement = connection.prepareCall("CALL PARAM.SETPARAM(?,?)");
                callableStatement.setObject(1, "P_ID");
                callableStatement.setObject(2, maxId);
                callableStatement.execute();
                callableStatement.close();
                callableStatement = connection.prepareCall("CALL PROC_CLIENT_ADD.PROC_CLIENT_ADD_04()");
                callableStatement.execute();
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
            throw e;
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
        }
        return customer;
    }

    private void insertCustomer(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, sessionAttributes.getBranch());
        preparedStatement.setString(2, customer.getId());
        preparedStatement.setString(3, customer.getP_family());
        preparedStatement.setString(4, customer.getP_first_name());
        preparedStatement.setString(5, customer.getP_patronymic());
        preparedStatement.setDate(6, CheckNull.d2sql(customer.getP_birthday()));
        preparedStatement.setString(7, customer.getP_birth_place());
        preparedStatement.setString(8, customer.getP_code_citizenship());
        preparedStatement.setString(9, customer.getP_type_document());
        preparedStatement.setString(10, customer.getP_passport_serial());
        preparedStatement.setString(11, customer.getP_passport_number());
        preparedStatement.setString(12, customer.getP_passport_place_registration());
        preparedStatement.setString(13, customer.getP_number_tax_registration());
        preparedStatement.setString(14, customer.getP_phone_mobile());
        preparedStatement.setString(15, null); // post_address_temp
        preparedStatement.setString(16, "1"); // risk_degree
        preparedStatement.setString(17, null); // risk_degree_detail
        preparedStatement.setString(18, null); // add_data
        preparedStatement.setString(19, null); // date_first_acc
        preparedStatement.setString(20, null); // date_change
        Date date_open = CheckNull.d2sql(CustomerUtils.getOperDate(sessionAttributes));
        preparedStatement.setDate(21, date_open); // date_open
        preparedStatement.setString(22, null); // emp_account_open
        preparedStatement.setString(23, null); // emp_account_confirm
        preparedStatement.setInt(24, 2); // state
        preparedStatement.setDate(25, date_open); // risk_date
        preparedStatement.setString(26, null); // date_last_save
        preparedStatement.setString(27, null); // doc_type
        preparedStatement.setString(28, null); // account_open
        preparedStatement.setString(29, customer.getId()); // client_id
        preparedStatement.setString(30, null); // id_excel
        preparedStatement.setDate(31, CheckNull.d2sql(customer.getP_passport_date_expiration())); //
        preparedStatement.executeUpdate();
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setBranch(resultSet.getString("branch"));
        customer.setId(resultSet.getString("id"));
        customer.setP_family(resultSet.getString("family"));
        customer.setP_first_name(resultSet.getString("first_name"));
        customer.setP_patronymic(resultSet.getString("patronymic"));
        customer.setP_family_local(customer.getP_family());
        customer.setP_first_name_local(customer.getP_first_name());
        customer.setP_patronymic_local(customer.getP_patronymic());
        customer.setP_birthday(resultSet.getDate("birth_date"));
        customer.setP_birth_place(resultSet.getString("birth_place"));
        customer.setP_code_citizenship(resultSet.getString("citizen"));
        customer.setP_type_document(resultSet.getString("type_document"));
        customer.setP_passport_serial(resultSet.getString("passport_serial"));
        customer.setP_passport_number(resultSet.getString("passport_number"));
        customer.setP_passport_place_registration(resultSet.getString("passport_place_registration"));
        customer.setP_number_tax_registration(resultSet.getString("number_tax_registration"));
        customer.setP_passport_date_expiration(resultSet.getDate("passport_date_expiration"));
        customer.setP_passport_date_registration(resultSet.getDate("passport_date_registration"));
        // Только для разовых клиентов, очистить после применения
        customer.setCode_subject("N");
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerDaoException, SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, customer.getP_family());
            preparedStatement.setString(2, customer.getP_first_name());
            preparedStatement.setString(3, customer.getP_patronymic());
            preparedStatement.setDate(4, CheckNull.d2sql(customer.getP_birthday()));
            preparedStatement.setString(5, customer.getP_birth_place());
            preparedStatement.setString(6, customer.getP_code_citizenship());
            preparedStatement.setString(7, customer.getP_type_document());
            preparedStatement.setString(8, customer.getP_passport_serial());
            preparedStatement.setString(9, customer.getP_passport_number());
            preparedStatement.setString(10, customer.getP_passport_place_registration());
            preparedStatement.setString(11, customer.getP_number_tax_registration());
            preparedStatement.setString(12, customer.getP_phone_mobile());
            preparedStatement.setDate(13, CheckNull.d2sql(customer.getP_passport_date_expiration())); //
            preparedStatement.setString(14, sessionAttributes.getBranch());
            preparedStatement.setString(15, customer.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new CustomerDaoException(e.getMessage());
        } finally {
            DbUtils.closeStmt(preparedStatement);
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws CustomerDaoException, SQLException {
        throw new RuntimeException(new Throwable("Delete Customer is not implemented"));
    }
}
