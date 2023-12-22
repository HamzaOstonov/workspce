package com.is.customer_.contact.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.ActionsEnum;
import com.is.customer_.contact.Relationship;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.sap.service.RelationshipGateway;
import com.is.customer_.sap.service.RoleInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.transactionLogger.TransactionLogger;
import com.is.customer_.sap.transactionLogger.TransactionLoggerBuilder;
import com.is.utils.CheckNull;

/**
 * Created by root on 10.06.2017.
 * 15:15
 */
public class RelationshipActionService {
    private final static Logger logger = Logger.getLogger(RelationshipActionService.class);

    private RelationshipGateway relationshipGateway = SAPServiceFactory.getInstance().getRelationshipGateway();
    private RoleInterface roleGateway = SAPServiceFactory.getInstance().getRoleService();

    private final SessionAttributes sessionAttributes;

    private RelationshipActionService(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
    }

    public static RelationshipActionService getInstance(SessionAttributes sessionAttributes){
        return new RelationshipActionService(sessionAttributes);
    }

    public Relationship doAction(Customer customer_, Relationship relationship_, int dealId, int actionId) throws Exception {
        Customer customer = (Customer) customer_.clone();
        int state = customer.getState() == null || customer.getState().isEmpty() ? 0 : Integer.parseInt(customer.getState());
        Connection c = null;
        Relationship relationship = new Relationship();
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            //logger.error("Contact Customer " + customer);
            executeAction(c,customer,relationship_,actionId);

            TransactionLogger transactionLogger = new TransactionLoggerBuilder()
                    .setAction(actionId)
                    .setSessionAttributes(sessionAttributes)
                        .build();
            // Deletion
            if (actionId != 2 && relationship_.getMapId() != 0)
                relationship = getRelationshipByMapId(c,relationship_.getMapId());
            else{
                relationship.setPosition(relationship_.getPosition());
                relationship.setClientJId(relationship_.getClientJId());
                relationship.setInnJ(relationship_.getInnJ());
            }
            // Deletion
            if (actionId == 2){
                // Customer is in confirmed state
                if (state  == 3) {
                    try {
                        if (relationshipGateway.isRelationshipExists(customer, relationship_))
                            relationshipGateway.deleteRelationship(customer, relationship_);

                        try {
                            //roleGateway.createRole(customer, ActionsEnum.EVENT_ACTION_DELETE_CONTACT_PERSON.value()); //2018-02-07
                            roleGateway.createRoleForContactPerson(customer, ActionsEnum.EVENT_ACTION_DELETE_CONTACT_PERSON.value());//2018-02-07
                        } catch (Exception e) {
                            logger.error(CheckNull.getPstr(e));
                        }
                    } catch (Exception e) {
                        transactionLogger.logRelations(relationship_, e.getMessage());
                    }
                }
            }

            // Confirmation
            if (actionId == 5 || actionId == 6){
                if (state == 3) {
                    try {
                        if (!relationshipGateway.isRelationshipExists(customer, relationship_))
                            relationshipGateway.createRelationship(customer, relationship_);
                        try {
                            //roleGateway.createRole(customer, ActionsEnum.EVENT_ACTION_CREATE_CONTACT_PERSON.value());//2018-02-07
                            roleGateway.createRoleForContactPerson(customer, ActionsEnum.EVENT_ACTION_CREATE_CONTACT_PERSON.value());//2018-02-07

                        } catch (Exception e) {
                            logger.error(CheckNull.getPstr(e));
                        }
                    } catch (Exception e) {
                        logger.error(CheckNull.getPstr(e));
                        transactionLogger.logRelations(relationship_, e.getMessage());
                    }
                }
            }
            c.commit();
        }
        catch (Exception e){
            c.rollback();
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            ConnectionPool.close(c);
        }
        return relationship;
    }

    private void executeAction(Connection c,Customer customer, Relationship relationship, int action) throws Exception {
        CallableStatement setParam = null;
        CallableStatement doAction = null;
        CallableStatement clearParam = null;
        CallableStatement getParam = null;
        try {
            setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
            doAction = c.prepareCall("{ call kernel.doAction(?,?,?) }");

            clearParam = c.prepareCall("{ call Param.clearparam() }");
            clearParam.execute();

            getParam = c.prepareCall("{? = call Param.getparam('P_ID')}");
            getParam.registerOutParameter(1, Types.VARCHAR);

            if (relationship.getMapId() != 0) {
                setParam.setString(1,"P_ID");
                setParam.setInt(2,relationship.getMapId());
                setParam.execute();
            }

            setParam.setString(1,"P_BRANCH");
            setParam.setString(2,sessionAttributes.getBranch());
            setParam.execute();

            setParam.setString(1,"P_CLIENT_ID");
            setParam.setString(2,relationship.getClientJId());
            setParam.execute();

            setParam.setString(1,"P_PERSON_TYPE");
            setParam.setString(2,"P");
            setParam.execute();

            setParam.setString(1,"P_PERSON_ID");
            setParam.setString(2,customer.getId());
            setParam.execute();

            setParam.setString(1,"P_PERSON_KIND");
            setParam.setString(2,relationship.getPosition());
            setParam.execute();

            setParam.setString(1,"P_PERSON_NAME");
            setParam.setString(2,customer.getFullName());
            setParam.execute();

            doAction.setInt(1, 209);
            doAction.setInt(2, 3);
            doAction.setInt(3, action); // Если 1, то создаем нового клиента

            doAction.execute();
            getParam.execute();

            int id = getParam.getInt(1);

            if (action == 2)
                relationship.setMapId(0);
            else
                relationship.setMapId(id);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            try {
                setParam.close();
                doAction.close();
                clearParam.close();
                getParam.close();
            }
            catch (SQLException e){
                ISLogger.getLogger().error(CheckNull.getPstr(e));
                throw e;
            }
        }
    }

    private Relationship getRelationshipByMapId(Connection connection,int mapId) throws Exception {
        Relationship relationship = new Relationship();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement  = connection.prepareStatement(
                    "SELECT * FROM CLIENT_ADDINFO_PERSON_MAP A " +
                            " WHERE A.ID = ?");
            preparedStatement.setInt(1,mapId);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                relationship.setClientJId(resultSet.getString("client_id"));
                relationship.setPosition(resultSet.getString("person_kind"));
                relationship.setState(resultSet.getInt("state"));
                relationship.setMapId(mapId);
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            GeneralUtils.closeStatement(preparedStatement);
            GeneralUtils.closeResultSet(resultSet);
        }
        return relationship;
    }
}
