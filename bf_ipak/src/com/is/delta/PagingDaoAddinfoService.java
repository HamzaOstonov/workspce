package com.is.delta;

import com.is.ConnectionPool;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.delta.core.PagingDao;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14.06.2017.
 * 17:01
 */
public class PagingDaoAddinfoService implements PagingDao<DELTARecord> {
    private static final Logger logger = Logger.getLogger(PagingDaoAddinfoService.class);

    private static final String HEADER = "select * from ( ";
    private static final String BODY = " select rownum rownm,t.* from (select c.*," +
            "(select name from client_addinfo_person a " +
            "where a.branch = c.branch and a.id = c.client_id) name " +
            "from delta_cl_load_requests c order by v_date desc) t";
    private static final String FOOTER = " ) b where b.rownm >= ? and b.rownm <= ? order by v_date desc";

    private static final String COUNT = "select count(*) from delta_cl_load_requests ";
    private final SessionAttributes sessionAttributes;

    private PagingDaoAddinfoService(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public List<DELTARecord> getData(Criteria criteria){
        List<DELTARecord> list = new ArrayList<DELTARecord>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            FilterStatement filterStatement = new FilterStatement(criteria);
            String SQLStatement = HEADER;
            SQLStatement += BODY;
            SQLStatement += filterStatement.generateConditions();
            SQLStatement += FOOTER;
            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementParameters(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                DELTARecord record = mapResultSet(resultSet);
                list.add(record);
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
    }

    @Override
    public int getCount(Criteria criteria) {
        int count = 0;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            FilterStatement filterStatement = new FilterStatement(criteria);

            String SQLStatement = COUNT;
            SQLStatement += filterStatement.generateConditions();

            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementWithoutBounds(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return count;
    }

    public static PagingDao getInstance(SessionAttributes sessionAttributes) {
        return new PagingDaoAddinfoService(sessionAttributes);
    }

    private DELTARecord mapResultSet(ResultSet rs) throws SQLException {
        DELTARecord DELTARecord = new DELTARecord();
        DELTARecord.setBranch(rs.getString("branch"));
        DELTARecord.setAction_type(rs.getString("action_type"));
        DELTARecord.setClient_id(rs.getString("client_id"));
        DELTARecord.setCustomer_type(rs.getString("customer_type"));
        DELTARecord.setMessage(rs.getString("message"));
        DELTARecord.setState(rs.getString("state_id"));
        DELTARecord.setUser_id(rs.getInt("user_id"));
        DELTARecord.setName(rs.getString("name"));
        DELTARecord.setV_date(rs.getDate("v_date"));
        return DELTARecord;
    }
}
