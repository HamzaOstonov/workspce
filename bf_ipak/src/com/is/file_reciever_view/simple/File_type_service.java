package com.is.file_reciever_view.simple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.is.utils.RefData;

public class File_type_service
{
	public static HashMap<Long, File_type> getFile_types (Connection c)
	{
		HashMap<Long, File_type> res = new HashMap<Long, File_type>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select t.id, t.filename_pattern, t.comment_ from fr_file_type t");
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.put(rs.getLong("id"), 
						new File_type(
								rs.getLong("id"), 
								rs.getString("filename_pattern"), 
								rs.getString("comment_")
								));
			}
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(
					"Could not read file types: "+
					com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
}
