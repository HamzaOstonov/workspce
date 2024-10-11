// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC;

import java.util.Date;
import com.is.ConnectionPool;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_reciever;
import com.is.file_reciever_srv.recievers.EMPC.b_file.B_file_reciever;
import com.is.ISLogger;
import java.io.File;
import java.text.SimpleDateFormat;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

public class EMPC_file_reciever extends Reciever_class
{
    private static SimpleDateFormat path_df;
    
    static {
        EMPC_file_reciever.path_df = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator);
    }
    
    public void Recieve_file(final String input_file, final long fr_file_id) {
        ISLogger.getLogger().info((Object)("EMPC file reciever: Recievers file - " + input_file));
        Reciever_class bRec_cl = null;
        Reciever_class exptRec_cl = null;
        if (input_file.substring(input_file.lastIndexOf(File.separator) + 1, input_file.lastIndexOf(File.separator) + 2).equals("B")) {
            bRec_cl = new B_file_reciever();
        }
        if (input_file.substring(input_file.lastIndexOf(File.separator) + 1, input_file.lastIndexOf(File.separator) + 5).equals("EXPT")) {
            exptRec_cl = new EXPT_reciever();
        }
    }
    
    public static void move_file_to_archive(final String in_file) {
        final String arch_dest = String.valueOf(ConnectionPool.getValue("empc_file_arch_dir")) + EMPC_file_reciever.path_df.format(new Date()) + in_file.substring(in_file.lastIndexOf(File.separator) + 1);
        new File(arch_dest).getParentFile().mkdirs();
        new File(in_file).renameTo(new File(arch_dest));
        ISLogger.getLogger().error((Object)("renamed from " + in_file + " to " + arch_dest));
    }
}
