package com.is.file_reciever_srv.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;


public class Service
{
	public static String get_set_value (Connection c, String id) throws Exception
	{
		String res = null;
		PreparedStatement ps = c.prepareStatement("select value from bf_sets t where id = ?");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) res = rs.getString("value");
		else
		{
			try{if (ps!=null) ps.close();}catch(Exception e){}
			try{if (rs!=null) rs.close();}catch(Exception e){}
			throw(new Reciever_exception("id '"+id+"' not found in bf_sets."));
		}
		
		try{if (ps!=null) ps.close();}catch(Exception e){}
		try{if (rs!=null) rs.close();}catch(Exception e){}
		
		return res;
	}
	
	public static void put_protocol(Connection c, long object_id, String message, long message_id)
	   {
		   CallableStatement cs = null;
		try
		{
			cs = c.prepareCall("{call proc_external_file.put_protocol(?, ?, ?)}");
			cs.setLong(1, object_id);
			cs.setString(2, message);
			cs.setLong(3, message_id);
			cs.execute();
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
		finally
		{
			try{if(cs!=null)cs.close();}catch(Exception e){}
		}
	   }
	   public static void put_protocol(long object_id, String message, long message_id)
	   {
		   Connection c = null;
		   try
		   {
			   c = ConnectionPool.getConnection();
			   put_protocol(c, object_id, message, message_id);
			   c.commit();
		   }
		   catch (Exception e)
		   {
			   ISLogger.getLogger().error(e.getStackTrace());
				e.printStackTrace();
		   }
		   finally
		   {
			   try{if(c!=null)c.close();}catch(Exception e){}
		   }
	   }
}
