// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.entity;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.is.ConnectionPool;
import java.util.HashMap;

public class Option_map<C>
{
    private HashMap<C, String> map;
    private String sql;
    
    private void update_map() throws SQLException {
        this.map = new HashMap<C, String>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement(this.sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.map.put((C)rs.getObject("id"), rs.getString("name"));
            }
        }
        finally {
            if (c != null) {
                c.close();
            }
        }
        if (c != null) {
            c.close();
        }
    }
}
