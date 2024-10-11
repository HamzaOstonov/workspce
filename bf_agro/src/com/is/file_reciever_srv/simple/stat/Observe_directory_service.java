package com.is.file_reciever_srv.simple.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Observe_directory_service
{
	public static Observe_directory[] get_pathes_to_observe(Connection c) throws Exception
	{
		Observe_directory[] res = null;
		PreparedStatement ps = c.prepareStatement("select * from fr_files_directory");
		ResultSet rs = ps.executeQuery();
		Vector<Observe_directory> tmp_res = new Vector<Observe_directory>();
		while(rs.next())
		{
			tmp_res.add(new Observe_directory(
					rs.getInt("id"),
					rs.getString("dir"),  
					rs.getString("comment_")));
		}
		try{if (ps!=null) ps.close();}catch(Exception e){}
		try{if (rs!=null) rs.close();}catch(Exception e){}
		res = tmp_res.toArray(new Observe_directory[tmp_res.size()]);
		return res;
	}
}
