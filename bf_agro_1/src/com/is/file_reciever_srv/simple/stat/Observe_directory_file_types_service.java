package com.is.file_reciever_srv.simple.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import properties.Properties_service;


public class Observe_directory_file_types_service
{
	public static HashMap<Integer, Vector<Integer>> 
						get_pathes_to_observe(Connection c) throws Exception
	{
		HashMap<Integer, Vector<Integer>> res = 
			new HashMap<Integer, Vector<Integer>>();
		//HashMap<Integer, Vector<Integer>> res = null;
		
		List<Long> file_types_to_preceed = Properties_service.getFile_types_to_preceed();
		if(file_types_to_preceed.size() < 1) return res;
		StringBuffer sql = new StringBuffer("select * from fr_directory_file_type t where t.id_file_type in(");
		boolean is_first = true;
		for (Long cur_id : file_types_to_preceed)
		{
			if (!is_first) sql.append(", ");
			sql.append(cur_id.toString());
			is_first = false;
		}
		sql.append(") order by t.id_directory");
		
		//PreparedStatement ps = c.prepareStatement(
		//		"select * from fr_directory_file_type t order by t.id_directory");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		
		int dir_id = 0;
		Vector<Integer> dir_type = new Vector<Integer>();
		if (rs.next())
		{
			dir_id = rs.getInt("id_directory");
			dir_type.add(rs.getInt("id_file_type"));
			
			while(rs.next())
			{
				if (dir_id != rs.getInt("id_directory"))
				{
					res.put(dir_id, dir_type);
					
					dir_type = new Vector<Integer>();
					dir_id = rs.getInt("id_directory");
				}
				dir_type.add(rs.getInt("id_file_type"));
			}
			
			res.put(dir_id, dir_type);
		}
		try{if (ps!=null) ps.close();}catch(Exception e){}
		try{if (rs!=null) rs.close();}catch(Exception e){}
		
		return res;
	}
}
