package com.is.file_reciever_srv.recievers.tieto_file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.StringTokenizer;  

import javax.sql.CommonDataSource;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.utils.CheckNull;


public class Service
{
	private static Logger logger = ISLogger.getLogger();
	
	public static void parse()
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"update ext_in_file_records r "+
   "set r.record_type_id = (select id from ss_ext_record_types rt "+
                            "where regexp_like(r.rec_value, rt.parser_like) "+
                              "and rt.id in (select fr.record_type_id from ss_ext_file_records fr, ext_in_files inf "+
                                             "where inf.id            = r.in_file_id "+
                                               "and inf.file_type_id  = fr.file_type_id)) "+
 "where r.in_file_id in (select id from ext_in_files inf2 "+
                         "where inf2.state_id      = 0 "+
                           "and inf2.id            = r.in_file_id)");
			ps.execute();
			c.commit();
		}
		catch (SQLException e)
		{
			try{if(c!=null)c.rollback();}catch(Exception e1){}
			logger.error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			try{if(ps != null)ps.close();}catch(Exception e){}
			try{if(c != null)c.close();}catch(Exception e){}
		}
	}
	
	public static HashMap<Long, Pattern> get_record_types_patterns(Connection c, Long file_type_id) 
	throws SQLException
	{
		HashMap res = new HashMap<Long, Pattern>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select rt.id, rt.parser_like from " +
					"ss_ext_record_types rt, ss_ext_file_records filerec " +
					"where rt.id = filerec.record_type_id and filerec.file_type_id = ?");
			ps.setLong(1, file_type_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
//				logger.error(rs.getLong("id")+": "+rs.getString("parser_like"));
				res.put(rs.getLong("id"), Pattern.compile(rs.getString("parser_like")));
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		
		return res;
	}
	
	public static Long get_record_type_fast (HashMap<Long, Pattern> regex_patterns, 
			String record) throws Reciever_exception
	{
		//Pattern p = Pattern.compile("kjsdhfkjds");
		//Matcher m = p.matcher("ksdlkfjk");
		//if(m.find())
		//Matcher m = 
		for(Long record_type_id : regex_patterns.keySet())
		{
			Pattern p = regex_patterns.get(record_type_id);
			if(p.matcher(record).matches()) {

				if ( record_type_id == 2502 || record_type_id == 2505 ) {   
				    List<String> tokens = new ArrayList<String>();
				    StringTokenizer tokenizer = new StringTokenizer(record, ";");
				    while (tokenizer.hasMoreElements()) {
				        tokens.add(tokenizer.nextToken());
				    }
				    
					if (tokens.get(1).trim().equals(tokens.get(21).substring(3).trim())) //
						return 2502L;
					else 
						return 2505L;
				}
				else if ( record_type_id == 2503 || record_type_id == 2506 ) {   
				    List<String> tokens = new ArrayList<String>();
				    StringTokenizer tokenizer = new StringTokenizer(record, ";");
				    while (tokenizer.hasMoreElements()) {
				        tokens.add(tokenizer.nextToken());
				    }
				    
					if (tokens.get(1).trim().equals(tokens.get(21).substring(1,6).trim())) //
						return 2503L; 
					else 
						return 2506L;
				}
				else
					return record_type_id;
			}
				
		}
		throw new com.is.file_reciever_srv.exception.Reciever_exception("could not get record type for record: "+
				record);
	}
	
	public static List<Long> get_reciered_files_to_process(Long ext_file_type_id, 
			Long state_id) throws SQLException
	{
		Connection c = null;
		List<Long> res = new ArrayList<Long>();
		try
		{
			c = ConnectionPool.getConnection();
			res =  get_reciered_files_to_process(ext_file_type_id, state_id, c);
		}
		catch(SQLException e)
		{
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static List<Long> get_reciered_files_to_process(Long ext_file_type_id, 
			Long state_id, Connection c) throws SQLException
	{
		List<Long> res = new ArrayList<Long>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select t.id, t.file_name from ext_in_files t where t.file_type_id = ? " +
					"and t.state_id = ? order by t.id");
			ps.setLong(1, ext_file_type_id);
			ps.setLong(2, state_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				logger.info("Tieto file handler: got file with transactions: " + 
						rs.getString("file_name") + " id: " + rs.getLong("id") + " processing...");
				res.add(rs.getLong("id"));
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		return res;
	}
	
	public static List<Record> get_records_from_file(Long ext_in_file_id, Connection c) throws SQLException
	{
		List<Record> res = new ArrayList<Record>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select t.id, t.record_type_id, t.rec_value from ext_in_file_records t where t.in_file_id = ? and t.record_type_id is not null order by t.id");
			ps.setLong(1, ext_in_file_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Record(
						rs.getLong("id"),
						rs.getLong("record_type_id"),
						rs.getString("rec_value")
						));
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static HashMap<Long, Ti_file_rec_type_operation> get_file_rec_type_operations()
	{
		HashMap<Long, Ti_file_rec_type_operation> res = new HashMap<Long, Ti_file_rec_type_operation>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			res =  get_file_rec_type_operations(c);
		}
		catch(SQLException e)
		{
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static HashMap<Long, Ti_file_rec_type_operation> get_file_rec_type_operations(
			Connection c) throws SQLException
	{
		HashMap<Long, Ti_file_rec_type_operation> res = new HashMap<Long, Ti_file_rec_type_operation>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select t.id, t.ss_ext_record_type_id, t.bf_tr_operation_id " +
					"from ti_file_rec_type_operations t");
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.put(rs.getLong("ss_ext_record_type_id"), 
						new Ti_file_rec_type_operation(
								rs.getLong("id"), 
								rs.getLong("ss_ext_record_type_id"), 
								rs.getLong("bf_tr_operation_id")
								));
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static List<Dynamic_parameter> get_mapping_parameters(long rec_type_operation_id, Connection c) throws SQLException
	{
		List<Dynamic_parameter> res = new ArrayList<Dynamic_parameter>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select t.param_name, t.in_param_name, modifier_class " +
					"from ti_file_operation_parameters t where t.rec_type_operation_id = ?");
			ps.setLong(1, rec_type_operation_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Dynamic_parameter(
						rs.getString("param_name"), 
						rs.getString("in_param_name"),
						rs.getString("modifier_class")
						));
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
}
