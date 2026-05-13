// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.DAO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.is.ConnectionPool;
import java.util.HashMap;

public class Util
{
    private static HashMap<Integer, String> empc_file_states;
    private static HashMap<Integer, String> empc_file_types;
    
    static {
        Util.empc_file_states = null;
        Util.empc_file_types = null;
    }
    
    public static void update_states_map() throws SQLException {
        Util.empc_file_states = new HashMap<Integer, String>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Util.empc_file_states.put(rs.getInt(""), rs.getString(""));
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
    
    public static void update_map(HashMap<Integer, String> map, final String sql) throws SQLException {
        map = new HashMap<Integer, String>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("id"), rs.getString("name"));
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
    
    public static long get_sequence_next_val(final String sequence) throws SQLException {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("select " + sequence + ".nextval res from dual");
            final ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong("res");
        }
        finally {
            if (c != null) {
                c.close();
            }
        }
    }
}
