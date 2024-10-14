package com.is.file_reciever_srv.main;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.common.Fr_log_service;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.file_reciever_srv.simple.stat.File_service;
import com.is.file_reciever_srv.simple.stat.Type_reciever_class;



public class Directory_reader
{
	private static Logger logger = ISLogger.getLogger();
	
	public static void read_dir(String dir, 
			Vector<Integer> allowed_types, 
			HashMap<Integer, String> file_types_patterns,
			HashMap<Integer, String> type_reciever_classes)
	{
		File fl_c = new File(dir);
	    fl_c.mkdirs();
		File rdir = new File(dir);
		String[] rfiles = rdir.list();
		if ((rfiles != null)&&(rfiles.length > 0))
		for (int i = 0; i < rfiles.length; i++) 
		{
			try
			{
				for (int j = 0; j < allowed_types.size(); j++)
				{
					if (rfiles[i].matches(file_types_patterns.get(allowed_types.get(j))))
					{
						logger.info(dir + rfiles[i]+" : got file matching "+
								file_types_patterns.get(allowed_types.get(j)));
						logger.info(dir + rfiles[i]+" : starts recieving file");
						com.is.file_reciever_srv.simple.stat.File_in fl = null;
						try
						{
							 fl = File_service.create_file_in(dir + rfiles[i], allowed_types.get(j));
						} catch (Reciever_exception e)
						{
							logger.error(dir + rfiles[i]+" : file was not created.\n"+com.is.utils.CheckNull.getPstr(e));
							throw new Exception();
						}
						Fr_log_service.save_log(fl.getId(), null, "starts recieving file");
						read_file(dir + rfiles[i], 
								type_reciever_classes.get(allowed_types.get(j)), fl.getId());
						Fr_log_service.save_log(fl.getId(), null, "file recieved");
						logger.info(dir + rfiles[i]+" : file recieved");
					}
				}
			}catch(Exception e){continue;}
		}
	}
	
	private static boolean read_file(String input_file, String reciever_class, long fr_file_id)
	{
		Reciever_class rc = null;
		try
		{
			rc = (Reciever_class)Class.forName(reciever_class).newInstance();
		} catch (Exception e)
		{
			logger.error(input_file+" : reciever class \""+reciever_class +
					"\" for file not found");
			logger.error(com.is.utils.CheckNull.getPstr(e));
			return false;
		}
		
		rc.Recieve_file(input_file, fr_file_id);
		
		return true;
	}
}
