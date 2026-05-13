// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.SQLException;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_trayler_record;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_transaction_record;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_header_record;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.sql.Connection;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_record;
import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_record_DAO;

public class B_file_record_DAO extends EMPC_record_DAO
{
    public static void read(final B_file_record rec, final String line) {
        rec.setType(line.substring(0, 2));
        rec.setContent(line);
    }
    
    public static void insert(final B_file_record rec, final Connection c, final HashMap<String, PreparedStatement> prepared_statements, final long line_nmb) throws SQLException {
        if (rec.getType().equals("00")) {
            if (!prepared_statements.containsKey("ps_insert_header_record")) {
                prepared_statements.put("ps_insert_header_record", B_file_header_record_DAO.get_ps_insert_header_record(c));
            }
            B_file_header_record hrec = new B_file_header_record(rec.getEMPC_file_id());
            hrec = B_file_header_record_DAO.read(hrec, rec.getContent());
            B_file_header_record_DAO.insert(hrec, prepared_statements.get("ps_insert_header_record"), line_nmb);
        }
        else if (rec.getType().equals("10")) {
            if (!prepared_statements.containsKey("ps_insert_transaction_record")) {
                prepared_statements.put("ps_insert_transaction_record", B_file_transaction_DAO.get_ps_insert_transaction_record(c));
            }
            B_file_transaction_record hrec2 = new B_file_transaction_record(rec.getEMPC_file_id());
            hrec2 = B_file_transaction_DAO.read(hrec2, rec.getContent());
            B_file_transaction_DAO.insert(hrec2, prepared_statements.get("ps_insert_transaction_record"), line_nmb);
        }
        else if (rec.getType().equals("99")) {
            if (!prepared_statements.containsKey("ps_insert_trayler_record")) {
                prepared_statements.put("ps_insert_trayler_record", B_file_trayler_record_DAO.get_ps_insert_trayler_record(c));
            }
            B_file_trayler_record hrec3 = new B_file_trayler_record(rec.getEMPC_file_id());
            hrec3 = B_file_trayler_record_DAO.read(hrec3, rec.getContent());
            B_file_trayler_record_DAO.insert(hrec3, prepared_statements.get("ps_insert_trayler_record"), line_nmb);
        }
    }
}
