package com.is.kernel.deal_group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
	public static HashMap<Long, Deal_group> get_deal_groups(Connection c) throws SQLException
	{
		HashMap<Long, Deal_group> res = new HashMap<Long, Deal_group>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select * from deal_group");
			rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(rs.getLong("id"),
						new Deal_group(
								rs.getLong("id"),
								rs.getString("name")
								));
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
