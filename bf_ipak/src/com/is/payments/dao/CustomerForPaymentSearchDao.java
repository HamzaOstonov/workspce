package com.is.payments.dao;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.search.SAPSearch.services.DBSearchDao;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerForPaymentSearchDao implements DBSearchDao {
    private final String SQL = "Select * from cl_add_04 a ";
    private final SessionAttributes sessionAttributes;
    private final Logger logger = Logger.getLogger(CustomerForPaymentSearchDao.class);

    private CustomerForPaymentSearchDao(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public static DBSearchDao getInstance(SessionAttributes sessionAttributes) {
        return new CustomerForPaymentSearchDao(sessionAttributes);
    }

    @Override
    public List<Response> search(Criteria criteria) throws SQLException {
        List<Response> responseList = new ArrayList<Response>();

        Connection connection = null;
        CallableStatement init = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getConnection(sessionAttributes.getSchema());
            init = connection.prepareCall("{call info.init()}");
            init.execute();

            FilterStatement filterStatement = new FilterStatement(criteria);
            String generatedSQL = SQL;
            generatedSQL += filterStatement.generateConditions();

            if (criteria.getFilter().getFilterFields() != null
                    && criteria.getFilter().getFilterFields().size() > 0)
                generatedSQL += " and branch = " + sessionAttributes.getBranch();

            preparedStatement = connection.prepareStatement(generatedSQL);
            filterStatement.initStatementWithoutBounds(preparedStatement);
            //logger.error("RESULTING SQL " + generatedSQL);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Response response =
                        new Response(
                                0,
                                resultSet.getString("branch"),
                                resultSet.getString("id"),
                                null,
                                resultSet.getString("family"),
                                resultSet.getString("first_name"),
                                resultSet.getString("patronymic"),
                                resultSet.getString("family"),
                                resultSet.getString("first_name"),
                                resultSet.getString("patronymic"),
                                resultSet.getDate("birth_date"));
                responseList.add(response);
            }

        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(init);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(connection);
        }
        return responseList;
    }
}
