package com.is.kernel.states;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
	public static HashMap<State_pk, State> get_states(Connection c) throws SQLException
	{
		HashMap<State_pk, State> res = new HashMap<State_pk, State>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from states");
			rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(new State_pk(
								rs.getLong("deal_group_id"),
								rs.getLong("deal_id"),
								rs.getLong("id")), 
						new State(rs.getLong("deal_group_id"),
								rs.getLong("deal_id"),
								rs.getLong("id"),
								rs.getLong("name_id"),
								(rs.getLong("is_not_exists")==1)));
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
