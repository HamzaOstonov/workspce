package com.is.file_reciever_srv.recievers.EMPC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.ConnectionPool;

public class Util
{
	private static HashMap<Integer, String> empc_file_states = null;
	private static HashMap<Integer, String> empc_file_types = null;
	
	public static void update_states_map() throws SQLException
	{
		empc_file_states = new HashMap<Integer, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				empc_file_states.put(rs.getInt(""), rs.getString(""));
			}
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void update_map(HashMap<Integer, String> map, String sql) throws SQLException
	{
		map = new HashMap<Integer, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				map.put(rs.getInt("id"), rs.getString("name"));
			}
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
//	public static HashMap<Integer, String> getMap(HashMap<Integer, String> map) throws SQLException
//	{
//		if(map == null) update_map(map, );
//		return map;
//	}
	
	public static long get_sequence_next_val(String sequence) throws SQLException
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select "+sequence+".nextval res from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getLong("res");
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
}
