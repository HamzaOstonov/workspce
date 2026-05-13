// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.DAO;

import java.sql.SQLException;

public class EMPC_file_DAO
{
    public static long get_file_id() throws SQLException {
        return Util.get_sequence_next_val("seq_empc_files");
    }
}
