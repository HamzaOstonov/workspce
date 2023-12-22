package com.is.customer_.sap.transactionLogger;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.customer_.contact.Relationship;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.sap.EmergencyMode;
import com.is.utils.CheckNull;
import lombok.Data;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by root on 10.05.2017.
 * 13:27
 */
@Data
public class TransactionLogger {
    private final Logger logger = Logger.getLogger(TransactionLogger.class);
    private SessionAttributes sessionAttributes;
    private Customer customer;
    private int action;
    private int state;
    private String customer_type;
    private String message;

    public TransactionLogger(SessionAttributes sessionAttributes, Customer customer, int action, int state,
                             String customer_type, String message) {
        this.sessionAttributes = sessionAttributes;
        this.customer = customer;
        this.action = action;
        this.state = state;
        this.customer_type = customer_type;
        this.message = message;
    }

    private void log() throws Exception {
        //logger.error("Transaction Logger Debug... " + this.toString());
        Connection c = null;
        CallableStatement callableStatement = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();
            callableStatement = c.prepareCall
                    ("{call delta_cl_load_service.log(?,?,?,?,?,?,?)}");
            callableStatement.setString(1, customer.getBranch());
            callableStatement.setString(2, customer.getId());;
            callableStatement.setString(3, customer_type);
            callableStatement.setString(4, customer.getIdSap());
            callableStatement.setInt(5, action);
            callableStatement.setInt(6, state);
            callableStatement.setString(7, message);
            callableStatement.execute();
            c.commit();
        }
        catch (Exception e){
            c.rollback();
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally{
            DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }
    }

    public void logSuccessTransaction() throws Exception {
        this.setState(EmergencyMode.SUCCESS);
        log();
    }

    public void logErrorTransaction(String message) throws Exception{
        this.setState(EmergencyMode.ERROR);
        this.setMessage(message);
        log();
    }

    public void logEmergencyTransaction() throws Exception {
        this.setState(EmergencyMode.EMERGENCY);
        log();
    }

    public void logRelations(Relationship relationship,String message) throws Exception {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(
                    sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();

            preparedStatement = c.prepareStatement("select seq_delta_relations.nextval from dual");
            resultSet =  preparedStatement.executeQuery();

            int id = 0;
            if (resultSet.next())
                id = resultSet.getInt(1);
            preparedStatement.close();

            preparedStatement = c.prepareStatement(
                    "insert into delta_relations(id, branch, action,id_person_map, message,state,user_id, v_date) " +
                            "values(?,?,?,?,?,1,info.getEmpId,sysdate)");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, sessionAttributes.getBranch());
            preparedStatement.setInt(3, action);
            preparedStatement.setLong(4, relationship.getMapId());
            preparedStatement.setString(5, message);

            preparedStatement.executeUpdate();
            c.commit();
        }
        catch (Exception e){
            c.rollback();
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(callableStatement);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
    }

    public boolean isEmergencyModeOn(){
        return EmergencyMode.isTrue;
    }

    public boolean isErrorConsumed(){
        return EmergencyMode.isErrorConsumed;
    }
}
