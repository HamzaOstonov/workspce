package com.is.file_reciever_srv.main;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.simple.reciever_class.Abstract_handler;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;
import properties.Properties_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;




public class Proc_t
{
	static Timer timer = null;
	private static Logger logger = ISLogger.getLogger();
	private static int startingTime;
	private static int timeout;
	
	public static void run()
	{
		timer = new Timer();
		timer.schedule(new TimerTask() 
		{
			public void run()
			{
				try
				{
					run_proc();
				}
				catch (Exception e)
				{
					logger.error(CheckNull.getPstr(e));
				}
			}
		}, 5000, 1000*60*10);
	}
	
	private static void run_proc() throws Exception
	{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			//List<Long> st_id = CB_cash_recieve_approval.Service.get_rec_id(c);
			
			ps = c.prepareStatement(
					"select t.handler_class from ss_ext_deal_groups t where t.handler_class is not null group by t.handler_class");
			rs = ps.executeQuery();
			List<String> handlers = new ArrayList<String>();
			List<String> handlers_from_settings = Properties_service.getHandlers_to_proceed();
			while(rs.next())
			{
				handlers.add(rs.getString("handler_class"));
			}
			c.close();
			c = null;
			for (String handler_class : handlers)
			if (handlers_from_settings.contains(handler_class))
			try
			{
				//logger.error("calling handler:"+rs.getString("handler_class"));
				Abstract_handler handler = (Abstract_handler) Class.forName(
						handler_class).newInstance();
			
				handler.run_handler();
			}
			catch(Exception e)
			{
				logger.error("Handler class "+handler_class+
						" has thrown exception:"+CheckNull.getPstr(e));
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
			try{if(c!=null)c.close();}catch(Exception e){}
		}
	}
}
