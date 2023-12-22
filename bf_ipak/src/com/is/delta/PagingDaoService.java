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
 * Created by root on 22.04.2017.
 * 18:28
 */
@SuppressWarnings("ALL")
public class PagingDaoService implements PagingDao<DELTARecord> {
    private Logger logger = Logger.getLogger(PagingDaoService.class);
    //private final String COUNT_STATEMENT = "SELECT COUNT(*) FROM DELTA_CL_LOAD_REQUESTS";
    private static String migrationDate = DictionaryService.getMigrationDate();
    private final String HEADER = "select * from (";
    private final String FOOTER_ = ") b where b.rownm >= ? and b.rownm <= ?";

    private final String BODY =
            "select rownum rownm, t.*\n" +
                    "  from (select a.branch,\n" +
                    "               a.client_id,\n" +
                    "               a.customer_type,\n" +
                    "               a.idSAP," +
                    "               TO_NUMBER(a.action_type) action_type,\n" +
                    "               a.state_id,\n" +
                    "               a.v_date,\n" +
                    "               a.message,\n" +
                    "               a.user_id,\n" +
                    "               (select s.name\n" +
                    "                  from client_p s\n" +
                    "                 where s.branch = a.branch\n" +
                    "                   and s.id = a.client_id) name\n" +
                    "          from delta_cl_load_requests a\n" +
                    "         where a.customer_type = '01' and (a.state_id = 3 or a.state_id = 4)\n" +
                    "        union all\n" +
                    "        select h.branch,\n" +
                    "               h.id,\n" +
                    "               '01',\n" +
                    "               null," +
                    "               h.action_id action_type,\n" +
                    "               1,\n" +
                    "               h.date_time v_date,\n" +
                    "               '',\n" +
                    "               h.emp_id    user_id,\n" +
                    "               h.name\n" +
                    "          from client_p_history h\n" +
                    "         where not exists\n" +
                    "         (select 'x'\n" +
                    "                  from delta_cl_load_requests d\n" +
                    "                 where d.branch = h.branch\n" +
                    "                   and d.client_id = h.id\n" +
                    "                   and d.customer_type = '01'\n" +
                    "                      --and d.v_date > constant_time \n" +
                    "                   and d.v_date > (select max(a.date_time)\n" +
                    "                                     from client_p_history a\n" +
                    "                                    where a.branch = h.branch\n" +
                    "                                      and a.id = h.id\n" +
                    "                                      and a.action_id = h.action_id)\n" +
                    "                   and state_id = 2\n) " +
                    "           and h.action_id in (2, 19, 20)\n" +
                    /*"           and h.id like '99%'" +*/
                    "           and h.date_time > " + "to_date('" + migrationDate + "','dd.mm.yyyy')" +
                    "           and h.code_type = '08'" +
                    "           and h.date_time = (select max(a.date_time)\n" +
                    "                                from client_p_history a\n" +
                    "                               where a.branch = h.branch\n" +
                    "                                 and a.id = h.id\n)" +
                    ") t\n";

    private final String BODY_COUNT =
            "select count(*)\n" +
                    "  from (select a.branch,\n" +
                    "               a.client_id,\n" +
                    "               a.customer_type,\n" +
                    "               TO_NUMBER(a.action_type) action_type,\n" +
                    "               a.state_id,\n" +
                    "               a.v_date,\n" +
                    "               a.message,\n" +
                    "               a.user_id,\n" +
                    "               (select s.name\n" +
                    "                  from client_p s\n" +
                    "                 where s.branch = a.branch\n" +
                    "                   and s.id = a.client_id) name\n" +
                    "          from delta_cl_load_requests a\n" +
                    "         where a.customer_type = '01' and (a.state_id = 3 or a.state_id = 4)\n" +
                    "        union all\n" +
                    "        select h.branch,\n" +
                    "               h.id,\n" +
                    "               '01',\n" +
                    "               h.action_id action_type,\n" +
                    "               1,\n" +
                    "               h.date_time v_date,\n" +
                    "               '',\n" +
                    "               h.emp_id    user_id,\n" +
                    "               h.name\n" +
                    "          from client_p_history h\n" +
                    "         where not exists\n" +
                    "         (select 'x'\n" +
                    "                  from delta_cl_load_requests d\n" +
                    "                 where d.branch = h.branch\n" +
                    "                   and d.client_id = h.id\n" +
                    "                   and d.customer_type = '01'\n" +
                    "                   and d.v_date > (select max(a.date_time)\n" +
                    "                                     from client_p_history a\n" +
                    "                                    where a.branch = h.branch\n" +
                    "                                      and a.id = h.id\n" +
                    "                                      and a.action_id = h.action_id)\n" +
                    "           and d.state_id = 2)\n" +
                    "           and h.action_id in (2, 19, 20)\n" +
                    /*"        --and h.branch = '00444'\n" +*/
                    /*"        and h.id like '99%'" +*/
                    "           and h.date_time > " + "to_date('" + migrationDate + "','dd.mm.yyyy')" +
                    "           and h.code_type = '08'" +
                    "           and h.date_time = (select max(a.date_time)\n" +
                    "                                from client_p_history a\n" +
                    "                               where a.branch = h.branch\n" +
                    "                                 and a.id = h.id)\n" +
                    "          )t \n";

    private final SessionAttributes sessionAttributes;

    public PagingDaoService(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public static PagingDaoService getInstance(SessionAttributes sessionAttributes) {
        return new PagingDaoService(sessionAttributes);
    }

    @Override
    public List<DELTARecord> getData(Criteria criteria) {
        List<DELTARecord> list = new ArrayList<DELTARecord>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());

            FilterStatement filterStatement = new FilterStatement(criteria);
            String SQLStatement = HEADER;
            SQLStatement += BODY;
            SQLStatement += filterStatement.generateConditions();
            SQLStatement += FOOTER_;
            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementParameters(preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DELTARecord DELTARecord = mapResultSet(rs);
                list.add(DELTARecord);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(rs);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
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

    @Override
    public int getCount(Criteria criteria) {
        int count = 0;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());

            FilterStatement filterStatement = new FilterStatement(criteria);

            String SQLStatement = BODY_COUNT;
            SQLStatement += filterStatement.generateConditions();

            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementWithoutBounds(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return count;
    }
}
