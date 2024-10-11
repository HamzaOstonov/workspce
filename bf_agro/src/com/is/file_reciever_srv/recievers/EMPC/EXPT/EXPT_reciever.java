// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.sql.Connection;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EMPC_file_reciever;
import com.is.ConnectionPool;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

public class EXPT_reciever extends Reciever_class
{
    public void Recieve_file(final String input_file, final long fr_file_id) {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final EXPT_service serv = new EXPT_service(c, fr_file_id, input_file);
            serv.process_file();
            EMPC_file_reciever.move_file_to_archive(input_file);
            c.commit();
        }
        catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                }
                catch (Exception ex) {}
            }
            ISLogger.getLogger().error((Object)("recieving EMPC file " + input_file + " error " + CheckNull.getPstr(e)));
        }
        finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (c != null) {
            try {
                c.close();
            }
            catch (Exception ex3) {}
        }
    }
}
