// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EMPC_file_reciever;
import com.is.file_reciever_srv.recievers.EMPC.b_file.DAO.B_file_DAO;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file;
import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_file_DAO;
import com.is.ConnectionPool;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

public class B_file_reciever extends Reciever_class
{
    public void Recieve_file(final String input_file, final long fr_file_id) throws SQLException {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final B_file b_file = new B_file(EMPC_file_DAO.get_file_id(), fr_file_id);
            B_file_DAO.load_records(input_file, b_file, c);
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
            ISLogger.getLogger().error((Object)("recieving B file " + input_file + " error " + CheckNull.getPstr(e)));
            ISLogger.getLogger().error((Object)("\u043e\u0448\u0438\u0431\u043a\u0430 \u0438\u043d\u0441\u0435\u0440\u0442 empc_files_b_transactions " + e));
            final Connection con = ConnectionPool.getConnection();
            PreparedStatement psError = null;
            psError = con.prepareStatement("insert into HUMO_B_ERROR (empc_file_id,error) values (?, ?)");
            psError.setLong(1, EMPC_file_DAO.get_file_id());
            psError.setString(2, e.toString());
            psError.execute();
            con.commit();
            con.close();
            psError.close();
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
