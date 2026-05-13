package com.is.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.ConnectionPool;

public class Common
{
	public static HashMap<String, String> getBranches()
	{
		HashMap<String, String> res = new HashMap<String, String>();
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from s_mfo");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.put(rs.getString("bank_id"), rs.getString("bank_name"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
}
