package com.is.file_reciever_srv.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.ISLogger;

public class Fr_log_service
{	
	public static void save_log(long file_id, Long message_id, String message)
	{
		try
		{
			if (message_id == null) message_id = -1l;
			save_log_in(file_id, message_id, message);
		}
		catch(Exception e)
		{
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	}
	
	private static void save_log_in(long file_id, long message_id, String message) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("insert into fr_log_in " +
					"(time, file_id, message, message_id) values (sysdate, ?, ?, ?)");
			ps.setLong(1, file_id);
			ps.setString(2, message);
			if(message_id != -1)ps.setLong(3, message_id);
			else ps.setNull(3, java.sql.Types.NUMERIC);
			ps.execute();
			c.commit();
		} catch (SQLException e)
		{
			if(c!=null)c.rollback();
			throw new Exception("could not write to log:\n"+com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
}
