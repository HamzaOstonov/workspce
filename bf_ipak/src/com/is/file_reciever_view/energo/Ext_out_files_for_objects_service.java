package com.is.file_reciever_view.energo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.file_reciever_view.simple.Ext_out_file;

public class Ext_out_files_for_objects_service
{
	public static List<Ext_out_file> get_out_files(Long object_id)
	{
		List<Ext_out_file> res = new ArrayList<Ext_out_file>();
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select out_files.* " +
					"from ext_out_files out_files, ext_out_file_records out_records, " +
					"ext_out_file_objects out_objects where out_files.id = out_records.out_file_id " +
					"and out_objects.out_record_id = out_records.id " +
					"and out_objects.create_obj_id = ?");
			ps.setLong(1, object_id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				res.add(new Ext_out_file(
						rs.getLong("id"), 
						rs.getLong("fr_id"), 
						rs.getString("branch"), 
						rs.getString("file_name"), 
						rs.getLong("file_num"), 
						rs.getDate("date_out"), 
						rs.getDate("v_date"), 
						rs.getLong("state_id"), 
						rs.getLong("file_type_id")
						));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		
		return res;
	}
}
