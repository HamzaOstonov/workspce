package com.is.customer_.search.SAPSearch.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.utils.CheckNull;

/**
 * Created by root on 19.05.2017.
 * 10:56
 */
public class DBSearchDaoImpl implements DBSearchDao {
    private Logger logger = ISLogger.getLogger();

    private SessionAttributes sessionAttributes;

    public DBSearchDaoImpl(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public List<Response> search(Criteria criteria) throws SQLException {
        List<Response> list = new ArrayList<Response>();
        Connection c = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());

            callableStatement = c.prepareCall("{ call info.init()}");
            callableStatement.execute();

            FilterStatement filterStatement = new FilterStatement(criteria);
            String SQL = "SELECT * FROM V_CLIENT_SAP";
            SQL += filterStatement.generateConditions();
            SQL += " and rownum BETWEEN 0 and 50";
            SQL += " and branch=" + sessionAttributes.getBranch();
            SQL += " and code_subject = 'P'";
            // Искать также по статусу не передан в SAP
            if (!((Input)criteria.getFilter()).isSent())
                SQL += "and sign_100 is not null";
            //logger.error("Resulting SQL " + SQL);
            preparedStatement = c.prepareStatement(SQL);
            filterStatement.initStatementWithoutBounds(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Response searchResponse = new Response();
                searchResponse.setBranch(resultSet.getString("branch"));
                searchResponse.setNciId(resultSet.getString("id_client"));
                searchResponse.setLastNameLocal(resultSet.getString("p_family"));
                searchResponse.setFirstNameLocal(resultSet.getString("p_first_name"));
                searchResponse.setMiddleNameLocal(resultSet.getString("p_patronymic"));
                searchResponse.setBirthDay(resultSet.getDate("p_birthday"));
                list.add(searchResponse);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            //DbUtils.closeResultSet(resultSet);
            //DbUtils.closeStmt(preparedStatement);
            //DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }
        return list;
    }
}
