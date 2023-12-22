package com.is.clients.addinfo;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by root on 01.08.2017.
 * 12:08
 */
public class AddinfoUtils {
    private static final Logger logger = Logger.getLogger(AddinfoUtils.class);

    public static String getLicensingAuthorityDistrict(String branch,String id,String schema){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String district = null;
        try{
            c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement(
                    "select distr_name from s_distr a " +
                    "where a.distr = " +
                    "(select a.distr from client_j a where a.branch = ? and a.id = ?)");
            preparedStatement.setString(1,branch);
            preparedStatement.setString(2,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                district = resultSet.getString(1);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return district;
    }
}
