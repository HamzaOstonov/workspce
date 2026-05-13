package com.is.kernel.trans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
	public static HashMap<Trans_pk, Trans> get_transes(Connection c) throws SQLException
	{
		HashMap<Trans_pk, Trans> res = new HashMap<Trans_pk, Trans>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from trans");
			rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(new Trans_pk(
								rs.getLong("deal_group_id"), 
								rs.getLong("deal_id"), 
								rs.getLong("action_id"), 
								rs.getLong("begin_state_id")), 
						new Trans(
								rs.getLong("deal_group_id"), 
								rs.getLong("deal_id"), 
								rs.getLong("action_id"), 
								rs.getLong("begin_state_id"), 
								rs.getLong("end_state_id")));
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
