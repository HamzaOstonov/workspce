package com.is.file_reciever_view;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.account.Account;
import com.is.account.AccountFilter;
import com.is.file_reciever_view.simple.File_filter;
import com.is.file_reciever_view.simple.Fr_file;
import com.is.file_reciever_view.simple.Fr_file_in;
import com.is.file_reciever_view.simple.Fr_file_out;

public class File_service
{
	private static String filter_sql_part_1 = 
		"select * from ( " +
		  "select dat.*, rownum rwn from ( " +
		  "select * from (select * from fr_file fr where ";
	
	private static String filter_sql_part_2 = 
		 " order by fr.date_time desc) " +
		  ")dat) " +
		"where rwn <= ? and rwn > ?";
	
	public static List<Fr_file> getFilesFl(int pageIndex, int pageSize, File_filter filter, String alias)
	{
		List<Fr_file> res = new ArrayList<Fr_file>();
		
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps_fr_in = null;
		PreparedStatement ps_fr_out = null;
		ResultSet rs = null;
		ResultSet rs_fr_in = null;
		ResultSet rs_fr_out = null;
		try
		{
			c = ConnectionPool.getConnection();
			int from = pageIndex;
			int to = pageIndex + pageSize;
			
			ps_fr_in = c.prepareStatement("select * from fr_file_in t where t.id = ?");
			ps_fr_out = c.prepareStatement("select * from fr_file_out t where t.id = ?");
			
			int args_counter = 1;
			String filter_sql = filter_sql_part_1;
			
			//if (filter.getFile_date() != null)
			if (filter.getDate_from() == null)
				filter_sql += "trunc(fr.date_time) = trunc(?) ";
			if (filter.getDate_from() != null)
				filter_sql += "trunc(fr.date_time) > trunc(?) ";
			if (filter.getDate_to() != null)
				filter_sql += "and trunc(fr.date_time) < trunc(?) ";
			if (filter.getFile_type() != null)
				filter_sql += "and fr.id in (select fl_in.id from fr_file_in fl_in, fr_file f where f.id = fl_in.id and fl_in.id_file_type = ?) ";
			
			
			filter_sql += filter_sql_part_2;
			
			ps = c.prepareStatement(filter_sql);
			
			if (filter.getDate_from() == null)
			{
				if (filter.getFile_date() != null)
					ps.setDate(args_counter++, new Date(filter.getFile_date().getTime()));
				else
					ps.setDate(args_counter++, new Date(new java.util.Date().getTime()));
			}
			else
			{
				ps.setDate(args_counter++, new Date(filter.getDate_from().getTime()));
				ps.setDate(args_counter++, new Date(filter.getDate_to().getTime()));
			}
			if (filter.getFile_type() != null)
				ps.setLong(args_counter++, filter.getFile_type());
			
			ps.setInt(args_counter++, to);
			ps.setInt(args_counter++, from);
			
		/*	ps = c.prepareStatement(
			"select * from ( " +
					  "select dat.*, rownum rwn from ( " +
					  "select * from (select * from fr_file fr where trunc(fr.date_time) = trunc(to_date ('01.01.2010', 'dd.mm.yyyy')) order by fr.date_time desc) " +
					  ")dat) " +
					"where rwn <= ? and rwn > ?");
			
			ps.setInt(1, to);
			ps.setInt(2, from);*/
			
			rs = ps.executeQuery();
			
			while (rs.next())
			{
				ps_fr_in.setLong(1, rs.getLong("id"));
				rs_fr_in = ps_fr_in.executeQuery();
				Fr_file tmp = null;
				if (rs_fr_in.next())
				{
					tmp = new Fr_file_in(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getTimestamp("date_time"),
							rs_fr_in.getLong("id_file_type")
							);
				}
				else
				{
					tmp = new Fr_file_out(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getTimestamp("date_time")
							);
				}
				res.add(tmp);
			}
		
		}
		catch(Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(rs_fr_in!=null)rs_fr_in.close();}catch(Exception e){}
			try{if(rs_fr_out!=null)rs_fr_out.close();}catch(Exception e){}
			try{if(ps_fr_in!=null)ps_fr_in.close();}catch(Exception e){}
			try{if(ps_fr_out!=null)ps_fr_out.close();}catch(Exception e){}
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		
		return res;
	}
	
	public static int getTotalSize(Object fl, String alias)
	{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		try
		{
			c = ConnectionPool.getConnection();
			
			String filter_sql = "select count(*) res from fr_file fr where trunc(fr.date_time) = trunc(?) ";
			
			filter_sql = "select count(*) res from fr_file fr where ";
			
			
			if (((File_filter)fl).getDate_from() == null)
				filter_sql += "trunc(fr.date_time) = trunc(?) ";
			if (((File_filter)fl).getDate_from() != null)
				filter_sql += "trunc(fr.date_time) > trunc(?) ";
			if (((File_filter)fl).getDate_to() != null)
				filter_sql += "and trunc(fr.date_time) < trunc(?) ";
			if (((File_filter)fl).getFile_type() != null)
				filter_sql += "and fr.id in (select fl_in.id from fr_file_in fl_in, fr_file f where f.id = fl_in.id and fl_in.id_file_type = ?) ";
			
			ps = c.prepareStatement(filter_sql);
			int args_counter = 1;
			
			if (((File_filter)fl).getDate_from() == null)
			{
				if (((File_filter)fl).getFile_date() != null)
					ps.setDate(args_counter++, new Date(((File_filter)fl).getFile_date().getTime()));
				else
					ps.setDate(args_counter++, new Date(new java.util.Date().getTime()));
			}
			else
			{
				ps.setDate(args_counter++, new Date(((File_filter)fl).getDate_from().getTime()));
				ps.setDate(args_counter++, new Date(((File_filter)fl).getDate_to().getTime()));
			}
			if (((File_filter)fl).getFile_type() != null)
				ps.setLong(args_counter++, ((File_filter)fl).getFile_type());
/*			
			if (((File_filter)fl).getFile_type() != null)
				filter_sql += "and fr.id in (select fl_in.id from fr_file_in fl_in, fr_file f where f.id = fl_in.id and fl_in.id_file_type = ?) ";
			
			ps = c.prepareStatement(filter_sql);
			
			ps.setDate(1, new java.sql.Date(((File_filter)fl).getFile_date().getTime()));
			if (((File_filter)fl).getFile_type() != null)
				ps.setLong(2, ((File_filter)fl).getFile_type());
			
		*/	
			
			rs = ps.executeQuery();
			rs.next();
			res = rs.getInt("res");
		}
		catch(Exception e)
		{
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
	
	public static String get_file_ui_class(long fr_file_type_id)
	{
		String res = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"select fr_ui_classes.ui_class " +
					"from fr_ui_classes, fr_ui_file_type_ui_classes " +
					"where fr_ui_file_type_ui_classes.fr_ui_classes_id = fr_ui_classes.id " +
					"and fr_ui_file_type_ui_classes.file_types_id = ?");
			ps.setLong(1, fr_file_type_id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("ui_class");
			}
			else
				throw new Exception("No user interface class for file with type id "+fr_file_type_id);
		}
		catch(Exception e)
		{
			System.out.println(com.is.utils.CheckNull.getPstr(e));
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
