package com.is.kernel.tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
	public static HashMap<Task_pk, Task> get_tasks(Connection c) throws SQLException
	{
		HashMap<Task_pk, Task> res = new HashMap<Task_pk, Task>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from tasks");
			rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(new Task_pk(
								rs.getLong("group_id"), 
								rs.getLong("id")), 
						new Task(
								rs.getLong("group_id"), 
								rs.getLong("id"), 
								rs.getString("proc_name")));
			}
			return res;
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
	}
}
