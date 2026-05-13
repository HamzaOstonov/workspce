package com.is.file_reciever_srv.recievers.tieto_files;

import java.io.File;
import java.text.SimpleDateFormat;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

public class TietoFileReciever extends Reciever_class
{
	private static SimpleDateFormat path_df = 
		new SimpleDateFormat(
				"yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator
				);
	
	
	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		Reciever_class rec_cl = null;
		
		if(input_file.substring(
				input_file.lastIndexOf(File.separator)+1, 
				input_file.lastIndexOf(File.separator)+5).equals("EXPT")
				)
			rec_cl = new TietoReciever();
		
		if(rec_cl!=null)rec_cl.Recieve_file(input_file, fr_file_id);
		
	}
	
	public static void move_file_to_archive(String in_file)
	{
		String arch_dest = 
			ConnectionPool.getValue("tieto_file_arch_dir") + 
			path_df.format(new java.util.Date()) + 
			(in_file.substring(in_file.lastIndexOf(File.separator)+1));
		(new File(arch_dest)).getParentFile().mkdirs();
		new File(in_file).renameTo(new File(arch_dest));
		ISLogger.getLogger().error("renamed from "+ in_file + " to " + arch_dest);
	}

}

