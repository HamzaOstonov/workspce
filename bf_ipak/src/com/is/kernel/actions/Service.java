package com.is.kernel.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
	public static HashMap<Action_pk, Action> get_actions(Connection c) throws SQLException
	{
		HashMap<Action_pk, Action> res = new HashMap<Action_pk, Action>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from actions");
			rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(new Action_pk(
								rs.getLong("deal_group_id"), 
								rs.getLong("deal_id"), 
								rs.getLong("id")),
						new Action(
								rs.getLong("deal_group_id"), 
								rs.getLong("deal_id"), 
								rs.getLong("id"),
								rs.getLong("name_id")));
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
