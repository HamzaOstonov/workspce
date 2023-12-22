package com.is.kernel.reactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.kernel.actions.Action_pk;


public class Service
{
	public static HashMap<Action_pk, HashMap<Long, Reaction>> get_reactions(Connection c) throws SQLException
	{
		HashMap<Action_pk, HashMap<Long, Reaction>> res = new HashMap<Action_pk, HashMap<Long, Reaction>>();
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from reactions t");
			rs = ps.executeQuery();
			while (rs.next())
			{
				Action_pk current_action_pk = new Action_pk(
						rs.getLong("deal_group_id"), 
						rs.getLong("deal_id"), 
						rs.getLong("id"));
				
				Reaction current_reaction = new Reaction(
						rs.getLong("deal_group_id"), 
						rs.getLong("deal_id"), 
						rs.getLong("id"),
						rs.getLong("task_group_id"), 
						rs.getLong("task_id"), 
						rs.getLong("ord"));
				
				if (res.containsKey(current_action_pk))
				{
					res.get(current_action_pk).put(rs.getLong("ord"), current_reaction);
				}
				else
				{
					res.put(current_action_pk, new HashMap<Long, Reaction>());
					res.get(current_action_pk).put(rs.getLong("ord"), current_reaction);
				}
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
