// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.SQLException;
import java.io.IOException;
import java.util.Iterator;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_record;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.sql.Connection;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file;
import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_file_DAO;

public class B_file_DAO extends EMPC_file_DAO
{
    public static void load_records(final String file_name, final B_file b_file, final Connection c) throws IOException, SQLException {
        final File fl = new File(file_name);
        final FileInputStream fis = new FileInputStream(fl);
        final BufferedReader file_reader = new BufferedReader(new InputStreamReader(fis));
        insert(b_file, c);
        String line = file_reader.readLine();
        final HashMap<String, PreparedStatement> prepared_statements = new HashMap<String, PreparedStatement>();
        try {
            for (long line_nmb = 1L; line != null; line = file_reader.readLine(), ++line_nmb) {
                final B_file_record rec = new B_file_record(b_file.getId());
                B_file_record_DAO.read(rec, line);
                B_file_record_DAO.insert(rec, c, prepared_statements, line_nmb);
            }
            for (final PreparedStatement ps : prepared_statements.values()) {
                ps.executeBatch();
            }
        }
        finally {
            for (final PreparedStatement ps2 : prepared_statements.values()) {
                if (ps2 != null) {
                    ps2.close();
                }
            }
        }
        for (final PreparedStatement ps2 : prepared_statements.values()) {
            if (ps2 != null) {
                ps2.close();
            }
        }
    }
    
    public static void insert(final B_file b_file, final Connection c) throws SQLException {
        final PreparedStatement ps = c.prepareStatement("insert into empc_files(id, fr_file_id, file_type_id,state_id) values (?, ?, ?,?)");
        try {
            ps.setLong(1, b_file.getId());
            ps.setLong(2, b_file.getFr_file_id());
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.execute();
        }
        finally {
            if (ps != null) {
                ps.close();
            }
        }
        if (ps != null) {
            ps.close();
        }
    }
}
