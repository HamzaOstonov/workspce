package com.is.db_utils;

import com.is.ConnectionPool;
import com.is.db_utils.ConnectionUtils;
import com.is.utils.FilterField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBServiceUtils {
    public static int getSizeByFilter(StringBuilder sql, List<FilterField> filterFields) throws SQLException {
        int result = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        c = ConnectionPool.getConnection();
        ps = c.prepareStatement(sql.toString());

        for (int i = 0; i < filterFields.size(); i++) {
            ps.setObject(i + 1, filterFields.get(i).getColobject());
        }

        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt(1);
        }

        ConnectionUtils.closeConnection(rs, ps, c);

        return result;
    }
}
