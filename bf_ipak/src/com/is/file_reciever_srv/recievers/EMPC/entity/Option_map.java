package com.is.file_reciever_srv.recievers.EMPC.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.ConnectionPool;

public class Option_map <C>
{
	private HashMap<C, String> map;
	private String sql;
	
	private void update_map() throws SQLException
	{
		map = new HashMap<C, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				map.put((C)rs.getObject("id"), rs.getString("name"));
			}
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	
}
