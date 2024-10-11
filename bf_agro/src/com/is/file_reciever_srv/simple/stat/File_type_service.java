package com.is.file_reciever_srv.simple.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import properties.Properties_service;


public class File_type_service
{
	public static HashMap<Integer, String> get_file_types(Connection c) throws Exception
	{
		HashMap<Integer, String> res = new HashMap<Integer, String>();
		
		List<Long> file_types_to_preceed = Properties_service.getFile_types_to_preceed();
		if(file_types_to_preceed.size() < 1) return res;
		StringBuffer sql = new StringBuffer("select * from fr_file_type t where t.id in (");
		boolean is_first = true;
		for (Long cur_id : file_types_to_preceed)
		{
			if (!is_first) sql.append(", ");
			sql.append(cur_id.toString());
			is_first = false;
		}
		
		sql.append(")");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			res.put(rs.getInt("id"), rs.getString("filename_pattern"));
		}
		
		try{if (ps!=null) ps.close();}catch(Exception e){}
		try{if (rs!=null) rs.close();}catch(Exception e){}
		
		return res;
	}
}
