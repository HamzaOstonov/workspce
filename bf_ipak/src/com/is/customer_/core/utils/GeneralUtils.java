package com.is.customer_.core.utils;

import com.is.ISLogger;
import com.is.customer_.core.model.SessionAttributes;
import com.is.utils.CheckNull;
import org.zkoss.zk.ui.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by root on 22.04.2017.
 * 13:31
 */
public class GeneralUtils {

    public static SessionAttributes getSessionAttributes(Session session) {
        SessionAttributes sessionAttributes = new SessionAttributes();
        sessionAttributes.setBranch(session.getAttribute("branch").toString());
        sessionAttributes.setSchema(session.getAttribute("alias").toString());
        sessionAttributes.setUid(Integer.valueOf(session.getAttribute("uid").toString()));
        sessionAttributes.setUsername(session.getAttribute("un").toString());
        sessionAttributes.setPassword(session.getAttribute("pwd").toString());
        return sessionAttributes;
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }
    
    public static void closePStatement(PreparedStatement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }

    public static void rollback(Connection c) {
        try {
            c.rollback();
        }
        catch (Exception e){
            throw new RuntimeException("Failed to rollback transaction");
        }
    }

    public static String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
