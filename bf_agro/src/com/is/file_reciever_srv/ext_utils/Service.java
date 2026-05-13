package com.is.file_reciever_srv.ext_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.file_reciever_srv.simple.stat.File_out;




public class Service
{
	private static Logger logger = ISLogger.getLogger();
	
	public static ExtFile[] get_new_files_to_send(Connection c) throws SQLException
	{
		Vector<ExtFile> tmp_res = new Vector<ExtFile>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select t.*, types.code type_code from ext_out_files t, SS_EXT_FILE_TYPES types where t.STATE_ID = 1 and types.id = t.FILE_TYPE_ID");
			rs = ps.executeQuery();
			while (rs.next())
			{
				tmp_res.add(new ExtFile(
								0,
								rs.getLong("id"),
								rs.getString("branch"), 
								rs.getString("file_name"), 
								rs.getTimestamp("date_out"), 
								rs.getLong("file_num"), 
								0, 
								0l, 
								"", 
								0,
								rs.getLong("FILE_TYPE_ID"),
								0l)
						);
			}
		}
		catch (SQLException e)
		{
			if(rs!=null){rs.close();}
			if(ps!=null){ps.close();}
			throw e;
		}
		return tmp_res.toArray(new ExtFile[tmp_res.size()]);
	}
	
	public static HashMap<Long, Vector<RecDump>> get_new_files_rec_dump_to_send(ExtFile[]out_files, Connection c) throws SQLException
	{
		HashMap<Long, Vector<RecDump>> res = new HashMap<Long, Vector<RecDump>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			for (int i = 0; i < out_files.length; i++)
			{
				long file_id = out_files[i].getId();
				ps = c.prepareStatement("select rd.* from ext_out_file_records rd where rd.OUT_FILE_ID = ? order by rd.id");
				ps.setLong(1, file_id);
				rs = ps.executeQuery();
				Vector<RecDump> rd = new Vector<RecDump>();
				while (rs.next())
				{
					rd.add(new RecDump(
							rs.getLong("id"),//Long recid, 
							0l,//rs.getLong("file_in_id"),//Long file_in_id, 
							0l,//Long file_type,
							rs.getString("REC_VALUE"),//String rec_dump, 
							rs.getLong("OUT_FILE_ID"),//Long file_out_id, 
							0l,//Long general_id,
							rs.getInt("message_id"),//String error_code, 
							0,//int state, 
							null,//Date v_date, 
							rs.getString("ERR_MESSAGE"),//String err_message
							null
							));
				}
				res.put(file_id, rd);
			}
		}
		catch (SQLException e)
		{
			if(rs!=null){rs.close();}
			if(ps!=null){ps.close();}
			throw e;
		}
		return res;
	}
	
	public static long get_id(Connection c) throws SQLException
	{
		long res = 0;
		PreparedStatement ps = c.prepareStatement("select seq_ext_in_files.nextval res from dual");
		ResultSet rs = ps.executeQuery();
		rs.next();
		res = rs.getLong("res");
		try{if (ps != null) ps.close();}catch(Exception e){}
		try{if (rs != null) rs.close();}catch(Exception e){}
		return res;
	}
	
	public static long get_id() throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			long res = 0;
			PreparedStatement ps = c.prepareStatement("select seq_ext_in_files.nextval res from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getLong("res");
			try{if (ps != null) ps.close();}catch(Exception e){}
			try{if (rs != null) rs.close();}catch(Exception e){}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c != null)c.close();
		}
	}
	
	public static long get_file_type(String in_file_type, long deal_group, Connection c) throws SQLException
	{
		long res = 0;
		PreparedStatement ps = c.prepareStatement("select t.id from ss_ext_file_types t where t.code = ? and t.deal_group_id = ?");
		ps.setString(1, in_file_type);
		ps.setLong(2, deal_group);
		ResultSet rs = ps.executeQuery();
		rs.next();
		res = rs.getLong("id");
		try{if (ps != null) ps.close();}catch(Exception e){}
		try{if (rs != null) rs.close();}catch(Exception e){}
		return res;
	}
	
	public static String get_file_type_code(long id, long deal_group, Connection c) throws SQLException
	{
		String res = "";
		PreparedStatement ps = c.prepareStatement("select t.code from ss_ext_file_types t " +
				"where t.id = ? and t.deal_group_id = ?");
		ps.setLong(1, id);
		ps.setLong(2, deal_group);
		ResultSet rs = ps.executeQuery();
		rs.next();
		res = rs.getString("code");
		try{if (ps != null) ps.close();}catch(Exception e){}
		try{if (rs != null) rs.close();}catch(Exception e){}
		return res;
	}
	
	public static long get_rec_dump_id(Connection c) throws SQLException
	{
		long res = 0;
		PreparedStatement ps = c.prepareStatement("select seq_ext_in_file_records.nextval res from dual");
		ResultSet rs = ps.executeQuery();
		rs.next();
		res = rs.getLong("res");
		try{if (ps != null) ps.close();}catch(Exception e){}
		try{if (rs != null) rs.close();}catch(Exception e){}
		return res;
	}
	
	public static long get_rec_dump_id() throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			long res = 0;
			PreparedStatement ps = c.prepareStatement("select seq_ext_in_file_records.nextval res from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getLong("res");
			try{if (ps != null) ps.close();}catch(Exception e){}
			try{if (rs != null) rs.close();}catch(Exception e){}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c != null) c.close();
		}
	}
	
	public static void save_file(Connection c, ExtFile file_in) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement("insert into ext_in_files " +
				"(fr_id, id, branch, file_name, file_num, FILE_TYPE_ID, date_in, v_date, state_id) values "+
				"(?, ?, ?, ?, ?, ?, sysdate, ?, ?)");
		
	/*	PreparedStatement ps = c.prepareStatement("insert into ext_files_in " +
				"(ext_type, id, branch, file_name, date_in, file_num, doc_cnt, " +
				"doc_amount, last_file_num, state, FILE_TYPE) values " +
				"(?, ?, ?, ?, ?, 0, ?, ?, '0', ?, ?)");*/
		
		ps.setLong(1, file_in.getFr_id());
		ps.setLong(2, file_in.getId());//id, 
		ps.setString(3, file_in.getBranch());//branch,
		ps.setString(4, file_in.getFile_name());//file_name,
		ps.setLong(5, file_in.getFile_num());//file_num,
		ps.setLong(6, file_in.getFile_type());//state
		//ps.setTimestamp(7, new java.sql.Timestamp(file_in.getDate_in().getTime()));//date_in,
		ps.setDate(7, new java.sql.Date(file_in.getDate_in().getTime()));//v_date,
		ps.setInt(8, file_in.getState());//state
		
		ps.execute();
		
		try{if (ps != null) ps.close();}catch(Exception e){}
	}
	
	public static void update_send_file(ExtFile ef, Connection c)
		throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"update ext_out_files t set t.file_name = ?, t.fr_id = ?, t.state_id = 2 where t.id = ?");
			ps.setString(1, ef.getFile_name());
			ps.setLong(2, ef.getFr_id());
			ps.setLong(3, ef.getId());
			ps.execute();
		}
		catch (SQLException e)
		{
			if(ps!=null){ps.close();}
			throw e;
		}
	}
	
	public static void update_rec_dump_error(Connection c, long file_in_id, 
			int error_id, String error_msg) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"update ext_in_file_records t " +
					"set t.message_id = ?, t.err_message = ? " +
					"where t.IN_FILE_ID  = ? and t.message_id = 0");
			ps.setInt(1, error_id);
			if(error_msg == null)
				ps.setNull(2, java.sql.Types.VARCHAR);
			else
				ps.setString(2, error_msg);
			ps.setLong(3, file_in_id);
			ps.execute();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(ps != null)ps.close();}catch(Exception e){}
		}
	}
	
	public static void save_rec_dump(Connection c, RecDump rec_dump, String partition) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement("insert into ext_in_file_records (in_file_id, id, rec_value, message_id, err_message, record_type_id, part_id) " +
				"values (?, ?, ?, ?, ?, ?, ?)");
		
		ps.setLong(1, rec_dump.getFile_in_id());                     
		ps.setLong(2, rec_dump.getRecid());     
		ps.setString(3, rec_dump.getRec_dump());
		ps.setInt(4, rec_dump.getError_id());
		ps.setString(5, rec_dump.getErr_message());
		if (rec_dump.getFile_type_id() == null)
			ps.setNull(6, java.sql.Types.NUMERIC);
		else
			ps.setLong(6, rec_dump.getFile_type_id());
		ps.setString(7, partition);
		
		ps.execute();
		
		try{if (ps != null) ps.close();}catch(Exception e){}
	}
	
	public static long get_10_number_from_36(String num) throws Reciever_exception
	{
		return 36 * get_num_fromstr_36(num.substring(0, 1)) + get_num_fromstr_36(num.substring(1));
	}
	
	public static Date get_date_from_string(String date) throws ParseException, Reciever_exception
	{		
		String day = String.format("%02d", get_num_fromstr_36(date.substring(0, 1)));
		String month = String.format("%02d", get_num_fromstr_36(date.substring(1, 2)));
		String year = "20"+String.format("%02d", get_num_fromstr_36(date.substring(2)));
		
		if (Integer.parseInt(month) > 12) throw new Reciever_exception("could not parse date ("+date+"): wrong month: " + month);
		if (Integer.parseInt(day) > 31) throw new Reciever_exception("could not parse date ("+date+"): wrong day: " + day);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		return sdf.parse(day+"."+month+"."+year);
	}
	
	private static int get_num_fromstr_36(String str) throws Reciever_exception
	{
		int res = 0;
		
		if (str.equals("0")) res = 0;
		else if (str.equals("1")) res = 1;
		else if (str.equals("2")) res = 2;
		else if (str.equals("3")) res = 3;
		else if (str.equals("4")) res = 4;
		else if (str.equals("5")) res = 5;
		else if (str.equals("6")) res = 6;
		else if (str.equals("7")) res = 7;
		else if (str.equals("8")) res = 8;
		else if (str.equals("9")) res = 9;
		
		else if (str.toUpperCase().equals("A")) res = 10;
		else if (str.toUpperCase().equals("B")) res = 11;
		else if (str.toUpperCase().equals("C")) res = 12;
		else if (str.toUpperCase().equals("D")) res = 13;
		else if (str.toUpperCase().equals("E")) res = 14;
		else if (str.toUpperCase().equals("F")) res = 15;
		else if (str.toUpperCase().equals("G")) res = 16;
		else if (str.toUpperCase().equals("H")) res = 17;
		else if (str.toUpperCase().equals("I")) res = 18;
		else if (str.toUpperCase().equals("J")) res = 19;
		else if (str.toUpperCase().equals("K")) res = 20;
		else if (str.toUpperCase().equals("L")) res = 21;
		else if (str.toUpperCase().equals("M")) res = 22;
		else if (str.toUpperCase().equals("N")) res = 23;
		else if (str.toUpperCase().equals("O")) res = 24;
		else if (str.toUpperCase().equals("P")) res = 25;
		else if (str.toUpperCase().equals("Q")) res = 26;
		else if (str.toUpperCase().equals("R")) res = 27;
		else if (str.toUpperCase().equals("S")) res = 28;
		else if (str.toUpperCase().equals("T")) res = 29;
		else if (str.toUpperCase().equals("U")) res = 30;
		else if (str.toUpperCase().equals("V")) res = 31;
		else if (str.toUpperCase().equals("W")) res = 32;
		else if (str.toUpperCase().equals("X")) res = 33;
		else if (str.toUpperCase().equals("Y")) res = 34;
		else if (str.toUpperCase().equals("Z")) res = 35;
		else throw new com.is.file_reciever_srv.exception.Reciever_exception("could not convert from 36 number: wrong number: "+str);
		return res;
	}
	
	private static String get_str_36_from_num(int inp)
	{
		String res = null;
		if (inp <= 9) res = Integer.toString(inp);
		else
		{
			switch (inp)
			{
				case 10: res = "A"; break;
				case 11: res = "B"; break;
				case 12: res = "C"; break;
				case 13: res = "D"; break;
				case 14: res = "E"; break;
				case 15: res = "F"; break;
				case 16: res = "G"; break;
				case 17: res = "H"; break;
				case 18: res = "I"; break;
				case 19: res = "J"; break;
				case 20: res = "K"; break;
				case 21: res = "L"; break;
				case 22: res = "M"; break;
				case 23: res = "N"; break;
				case 24: res = "O"; break;
				case 25: res = "P"; break;
				case 26: res = "Q"; break;
				case 27: res = "R"; break;
				case 28: res = "S"; break;
				case 29: res = "T"; break;
				case 30: res = "U"; break;
				case 31: res = "V"; break;
				case 32: res = "W"; break;
				case 33: res = "X"; break;
				case 34: res = "Y"; break;
				case 35: res = "Z"; break;
			}
		}
		return res;
	}
	
	public static String get_36_from_10_number(long num)
	{
		StringBuffer res = new StringBuffer();
		
		int i = 0;
		
		long tmp = num;
		
		if(num == 0) return "0";
		
		while (tmp > 0)
		{
			long n = tmp % 36;
			tmp = (int)Math.floor(tmp / 36);
			res.append(get_str_36_from_num((int)n));
		}
		res = res.reverse();
		return res.toString();
	}
	
	public static String get_36_date(Date dt)
	{
		String res = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		Calendar cn = Calendar.getInstance();
		cn.setTime(dt);
		
		res = get_36_from_10_number(cn.get(Calendar.DAY_OF_MONTH))+
			get_36_from_10_number(cn.get(Calendar.MONTH)+1)+
			get_36_from_10_number(cn.get(Calendar.YEAR)%2000);
	
		return res;
	}
	
	public static HashMap<Long, String> get_record_types(Connection c, Long file_type_id) 
	throws SQLException
	{
		HashMap res = new HashMap<Long, String>();
		
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
				res.put(rs.getLong("id"), rs.getString("parser_like"));
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
	
	public static HashMap<Long, Long> get_file_type_deal_group(Connection c) throws SQLException
	{
		HashMap res = new HashMap<Long, Long>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement("select t.id, t.deal_group_id from ss_ext_file_types t");
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.put(rs.getLong("id"), rs.getLong("deal_group_id"));
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
	
	public static Long get_record_type (HashMap<Long, String> record_types, 
			String record) throws Reciever_exception
	{
		for(Long record_type_id : record_types.keySet())
		{
			if (record.matches(record_types.get(record_type_id)))
				return record_type_id;
		}
		throw new com.is.file_reciever_srv.exception.Reciever_exception("could not get record type for record: "+
				record);
	}
	
	public static HashMap<String, String> get_data_from_fields(Connection c, long rec_id) throws SQLException
	{
		HashMap<String, String> res = new HashMap<String, String>();
		logger.error("not err get_data_from_fields: rec_id="+ rec_id);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select ct.code, fr.id record_id, ct.id col_id " +
		      ",REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " +
		  "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc " +
		 "where fr.record_type_id = tr.id " +
		   "and fr.record_type_id = rc.record_type_id " +
		   "and rc.column_id      = ct.id " +
		   "and fr.id = ? ");
			
			ps.setLong(1, rec_id);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				res.put(rs.getString("code"), rs.getString("str_value"));
				logger.error("not err get_data_from_fields: "+rs.getString("code")+" : "+rs.getString("str_value")+" : "+ rec_id);				
			}
			
			return res;
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e1){}
			try{if(ps!=null)ps.close();}catch(Exception e1){}
		}
	}
	
	public static long get_created_object_id(Connection c) throws SQLException
	{
		long res = 0;
		PreparedStatement ps = c.prepareStatement("select seq_ext_create_objects.nextval res from dual");
		ResultSet rs = ps.executeQuery();
		rs.next();
		res = rs.getLong("res");
		try{if (ps != null) ps.close();}catch(Exception e){}
		try{if (rs != null) rs.close();}catch(Exception e){}
		return res;
	}
}
