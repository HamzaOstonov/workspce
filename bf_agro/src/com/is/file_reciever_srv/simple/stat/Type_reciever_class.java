package com.is.file_reciever_srv.simple.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Type_reciever_class
{
	public static HashMap<Integer, String> get_type_reciever_classes(Connection c) 
		throws SQLException
	{
		HashMap<Integer, String> res = new HashMap<Integer, String>();
		PreparedStatement ps = c.prepareStatement(
				"select ft.id_file_type file_type_id, " +
				"rc.reciever_class reciever_class " +
				"from fr_file_reciever rc, fr_file_type_reciever ft " +
				"where rc.id = ft.id_reciever");
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			res.put(rs.getInt("file_type_id"), rs.getString("reciever_class"));
		try{if (ps!=null) ps.close();}catch(Exception e){}
		try{if (rs!=null) rs.close();}catch(Exception e){}
		return res;
	}
}
