package com.is.customer_.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.utils.CheckNull;

/**
 * Created by root on 05.06.2017.
 * 15:41
 */
public class ActionUtils {
    private final static Logger logger = Logger.getLogger(ActionUtils.class);

    public static final String CLIENT_ADDINFO_SQL = "select d.*," +
            "       (select a.name" +
            "          from action_client_addinfo a" +
            "         where a.deal_id = d.deal_id" +
            "           and a.id = d.action_id) name" +
            "  from trans_client_addinfo d" +
            " where d.deal_id = ?" +
            "   and d.state_begin = ? order by d.deal_id, d.action_id ";

    /*private static final String CUSTOMER_SQL = "select d.*,\n" +
            "       (select a.name\n" +
            "          from action_client a\n" +
            "         where a.deal_id = d.deal_id\n" +
            "           and a.id = d.action_id) name\n" +
            "  from trans_client d\n" +
            " where d.deal_id = ?\n" +
            "   and d.state_begin != ?\n" +
            "   and d.action_id not in (28,29,30) " +
            " order by action_id";*/

    private static final String CUSTOMER_SQL = "select vtc.*, aa.name from v_trans_client vtc, " +
            "action_client aa " +
            "where vtc.deal_id = ? " +
            "and vtc.state_begin = ? " +
            "and aa.deal_id = vtc.deal_id " +
            "and aa.id = vtc.action_id " +
            "order by vtc.deal_id, vtc.action_id";

    
    /*private static List<Action> getActions(int deal_id, int state,final String SQL){
        List<Action> actions = new ArrayList<Action>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection();

            preparedStatement = c.prepareStatement(SQL);

            preparedStatement.setInt(1,deal_id);
            preparedStatement.setInt(2,state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                actions.add(mapResultSetToAction(resultSet));
            }
        }
        catch (SQLException e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            GeneralUtils.closeStatement(preparedStatement);
            GeneralUtils.closeResultSet(resultSet);
            ConnectionPool.close(c);
        }
        return actions;
    }*/

    private static List<Action> getActions(int deal_id, int state, String un, String pw, String alias, final String SQL){
        List<Action> actions = new ArrayList<Action>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(un, pw, alias);
            preparedStatement = c.prepareStatement(SQL);
            preparedStatement.setInt(1,deal_id);
            preparedStatement.setInt(2,state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                actions.add(mapResultSetToAction(resultSet));
            }
        }
        catch (SQLException e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            GeneralUtils.closeStatement(preparedStatement);
            GeneralUtils.closeResultSet(resultSet);
            ConnectionPool.close(c);
        }
        return actions;
    }

    private static Action mapResultSetToAction(ResultSet resultSet) throws SQLException {
        Action action = new Action();
        action.setAction_id(resultSet.getInt("action_id"));
        action.setName(resultSet.getString("name"));
        action.setDeal_id(resultSet.getInt("deal_id"));
        action.setState_begin(resultSet.getInt("state_begin"));
        action.setState_end(resultSet.getByte("state_end"));
        return action;
    }

    public static List<Action> getContactActions(int state, String un, String pw, String alias){
        return getActions(1, state, un, pw, alias, CLIENT_ADDINFO_SQL);
    }

    public static List<Action> getRelationshipActions(int state, String un, String pw, String alias){
        return getActions(3, state, un, pw, alias, CLIENT_ADDINFO_SQL);
    }

    public static List<Action> getCustomerActions(int state, String un, String pw, String alias){
        return getActions(2, state, un, pw, alias, CUSTOMER_SQL);
    }
}
