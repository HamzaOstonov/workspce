package com.is.file_reciever_srv.simple.stat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import properties.Properties_service;


public class Sender_class_service
{
	public static String[] get_sender_classes(Connection c) 
	throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<String> res = new Vector<String>();
		
		try
		{
			List<Long> senders_to_proceed = Properties_service.getSenders_to_proceed();
			if(senders_to_proceed.size() < 1) return new String[0];
			
			StringBuffer sql = new StringBuffer("select t.sender_class from FR_FILE_SENDER t where t.id in (");
			boolean is_first = true;
			for (Long cur_id : senders_to_proceed)
			{
				if (!is_first) sql.append(", ");
				sql.append(cur_id.toString());
				is_first = false;
			}
			
			sql.append(")");
			
			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next())
				res.add(rs.getString("sender_class"));
		}
		catch (SQLException e){throw e;}
		finally
		{
			try{if (ps!=null) ps.close();}catch(Exception e){}
			try{if (rs!=null) rs.close();}catch(Exception e){}
		}
		return res.toArray(new String[res.size()]);
	}
}
