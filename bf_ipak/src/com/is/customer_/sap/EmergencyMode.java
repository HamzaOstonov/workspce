package com.is.customer_.sap;


import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by root on 24.04.2017.
 * 18:50
 */
public final class EmergencyMode {
    public static boolean isTrue = CustomerUtils.isEmergencyModeOn();

    public static boolean isErrorConsumed = false;

    public final static int ERROR = 3;
    public final static int SUCCESS = 2;
    public final static int EMERGENCY = 4;
    public final static int UNDEFINED = 5;

    public static void turnOnEmergencyMode() {
        isTrue = true;
        switchMode(true);
    }

    private static void switchMode(boolean b) {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        try {
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement("UPDATE BF_SETS SET VALUE = ? WHERE ID = 'SAP_IS_ON'");
            preparedStatement.setString(1, b == true ? "1" : "0");
            preparedStatement.executeUpdate();
            c.commit();
        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
    }

    public static void turnOffEmergencyMode() {
        isTrue = false;
        switchMode(false);
    }
}
