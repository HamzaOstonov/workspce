package com.is.file_reciever_srv.simple.reciever_class;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;


public class Util
{
	private static Logger logger = ISLogger.getLogger();
	
	public static boolean check_branch(String mfo, Connection c) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = c.prepareStatement("select count(*) res from ss_dblink_branch t where t.branch = ?");
			ps.setString(1, mfo);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("res") > 0;
		}
		catch(SQLException e)
		{
			throw e;
		}		
		finally
		{
			try{if(rs != null)rs.close();}catch(Exception e){}
			try{if(ps != null)ps.close();}catch(Exception e){}
		}
	}
	
	public static long check_whether_file_is_double(String file_name, 
			String table_name, String table_field, Connection c) throws Reciever_exception
	{
		long res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select count(*) res from "+table_name+" where "+table_field+" = ?");
			ps.setString(1, file_name);
			rs = ps.executeQuery();
			rs.next();
			res = rs.getLong("res");
		} catch (SQLException e)
		{
			e.printStackTrace();
			logger.error(com.is.utils.CheckNull.getPstr(e));
			throw new com.is.file_reciever_srv.exception.Reciever_exception("unable to check");
		}
		finally
		{
			try{if(rs != null)rs.close();}catch(Exception e){}
			try{if(ps != null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static String move_file_with_rename_if_same_name(File fl, String desination_dir)
	{
		String file_name_regexp = "([1-9]*_)?"+fl.getName();
		String file_path = fl.getAbsolutePath();
		
		File rdir = new File(desination_dir);
		String[] rfiles = rdir.list();
		int counter = 0;
		for (int i = 0; i < rfiles.length; i++)
			if (rfiles[i].matches(file_name_regexp))
				counter++;
		String destination_name = desination_dir+fl.getName();
		if (counter > 0)
			destination_name = desination_dir+(counter)+"_"+fl.getName();
		
		fl.renameTo(new File(destination_name));
		
		return destination_name;
	}
}
