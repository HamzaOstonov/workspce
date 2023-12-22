package com.is.customer_.contact;

import com.is.ConnectionPool;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import general.General;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 07.06.2017.
 * 18:54
 */
public class PersonMapDbUtils {
    private final static Logger logger = Logger.getLogger(PersonMapDbUtils.class);
    public static final String SQL_REL_MAP =
            "SELECT * FROM CLIENT_ADDINFO_PERSON_MAP A WHERE " +
            "A.BRANCH = ? AND A.CLIENT_ID = ? AND A.PERSON_ID = ? " +
            "AND A.PERSON_TYPE = 'P' AND A.PERSON_KIND = ? and state != 2";

    public static final String SQL_REL_MAP_OR =
            "SELECT * FROM CLIENT_ADDINFO_PERSON_MAP A WHERE " +
                    "A.BRANCH = ? AND A.CLIENT_ID = ? AND A.PERSON_ID = ? " +
                    "AND A.PERSON_TYPE = 'P' AND (A.PERSON_KIND = 1 or 2) and state != 2";

    public static Relationship getRelationship(
            String branch,String personId,String clientJId,String position,String alias){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Relationship relationship = new Relationship();
        try{
            c = ConnectionPool.getConnection(alias);
            preparedStatement = c.prepareStatement(SQL_REL_MAP);
            preparedStatement.setString(1,branch);
            preparedStatement.setString(2,clientJId);
            preparedStatement.setString(3,personId);
            preparedStatement.setString(4,position);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                relationship.setMapId(resultSet.getInt("id"));
                relationship.setClientJId(resultSet.getString("client_id"));
                relationship.setState(resultSet.getInt("state"));
                relationship.setPosition(resultSet.getString("person_kind"));
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            GeneralUtils.closeStatement(preparedStatement);
            GeneralUtils.closeResultSet(resultSet);
            ConnectionPool.close(c);
        }
        return relationship;
    }

    public static List<RefData> getStates(String schema){
        List<RefData> list = new ArrayList<RefData>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement("select * from state_client_addinfo a where a.deal_id = 1");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new RefData(resultSet.getString("id"),
                        resultSet.getString("name")));
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
}
