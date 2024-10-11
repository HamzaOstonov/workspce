package com.is.file_reciever.ui_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.is.ConnectionPool;

public class Service
{
	public static String get_file_State(long fr_file_id)
	{
		String res = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"select base_names.name state_name " +
					"from ext_in_files in_files, ss_ext_file_states states, ss_lang_base_names base_names " +
					"where in_files.fr_id = ? " +
					"and states.id = in_files.state_id " +
					"and states.file_type_id = in_files.file_type_id " +
					"and base_names.id = states.name_id");
			ps.setLong(1, fr_file_id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("state_name");
			}
			else
			{
				res = "Ошибка загрузки файла";
			}
		}
		catch(Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if (c!= null) c.close();} catch (Exception e){}
			try{if (ps!= null) ps.close();} catch (Exception e){}
			try{if (rs!= null) rs.close();} catch (Exception e){}
		}
		
		return res;
	}
	
	public static String get_file_records_details(long fr_file_id)
	{
		String res = "";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"select cols.name, t.str_value from ext_in_record_cols t, ss_ext_record_cols cols where "+
"t.record_id in (select rec.id from ext_in_file_records rec where rec.in_file_id in (select ext_in_files.id from ext_in_files where ext_in_files.fr_id = ?)) "+
"and cols.id = t.col_id");
			ps.setLong(1, fr_file_id);
			rs = ps.executeQuery();
			while (rs.next())
			{
				res += rs.getString("name") + ": " + rs.getString("str_value") + "\n";
			}
		}
		catch(Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if (c!= null) c.close();} catch (Exception e){}
			try{if (ps!= null) ps.close();} catch (Exception e){}
			try{if (rs!= null) rs.close();} catch (Exception e){}
		}
		
		return res;
	}
}
