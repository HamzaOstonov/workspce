package com.is.db_utils;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionUtils {
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }

    public static void closeConnection(ResultSet rs, ArrayList<Statement> statements, Connection c) throws SQLException {
        if(rs != null){
            rs.close();
        }
        for (Statement statement: statements) {
            if(statement != null){
                statement.close();
            }
        }
        if(c != null){
            c.close();
        }
    }
}
