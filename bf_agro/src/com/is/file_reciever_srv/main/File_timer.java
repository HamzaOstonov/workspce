package com.is.file_reciever_srv.main;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.simple.reciever_class.Sender_class;
import com.is.file_reciever_srv.simple.stat.Observe_directory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;



public class File_timer
{
	static Timer timer = null;
	private static Logger logger = ISLogger.getLogger();
	private static int startingTime;
	private static int timeout;
	private static Observe_directory[] observe_directories;
	private static String[] sender_classes;
	private static HashMap<Integer, Vector<Integer>> types_in_directories;
	private static HashMap<Integer, String> file_types_patterns;
	private static HashMap<Integer, String> type_reciever_classes;
	
	public void run()
	{
		if (!init_variables()) return;
		timer = new Timer();
		timer.schedule(new TimerTask() 
		{
			public void run()
			{
				Big_reader.read(observe_directories, types_in_directories, 
						file_types_patterns, type_reciever_classes);
				
				run_senders();
			}
		}, startingTime, timeout);
	}
	
	public void restart()
	{
		timer.cancel();
		timer = null;
		run();
	}
	
	private boolean init_variables()
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			startingTime = 1000 * Integer.parseInt(com.is.file_reciever_srv.common.Service.get_set_value(c, "ext_timer_starting_time"));
			timeout = 1000 * Integer.parseInt(com.is.file_reciever_srv.common.Service.get_set_value(c, "ext_timer_timeout"));
			observe_directories = 
				com.is.file_reciever_srv.simple.stat.Observe_directory_service.get_pathes_to_observe(c);
			types_in_directories = 
				com.is.file_reciever_srv.simple.stat.Observe_directory_file_types_service.get_pathes_to_observe(c);
			file_types_patterns = 
				com.is.file_reciever_srv.simple.stat.File_type_service.get_file_types(c);
			type_reciever_classes = 
				com.is.file_reciever_srv.simple.stat.Type_reciever_class.get_type_reciever_classes(c);
			sender_classes = 
				com.is.file_reciever_srv.simple.stat.Sender_class_service.get_sender_classes(c);
		} catch (Exception e)
		{
			logger.error(com.is.utils.CheckNull.getPstr(e));
			return false;
		}finally
		{
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		return true;
	}
	
	private void run_senders()
	{
		Sender_class sc = null;
		//logger.info("running senders");
		for (int i = 0; i < sender_classes.length; i++)
		{
			try
			{
				sc = (Sender_class)Class.forName(sender_classes[i]).newInstance();
				sc.run();
			} catch (Exception e)
			{
				logger.error("sender class \""+sender_classes[i] +
						"\" could not run");
				logger.error(com.is.utils.CheckNull.getPstr(e));
			}
		}
	}
}
