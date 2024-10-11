package com.is.file_reciever_srv.recievers.NIKI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Service
{
	private static Logger logger = ISLogger.getLogger();
	private static SimpleDateFormat sdf_dir = new SimpleDateFormat("yyyy\\MM\\dd\\");
	
	public static void create_files()
	{
		//logger.info("NIKI 5435243532 create_files");
		try
		{
			List<NIKI_file> files_to_send = get_files_to_send();
			for (NIKI_file nf : files_to_send)
			{
				create_file(nf);
			}
		}
		catch(Exception e)
		{
			logger.error("NIKI sender has thrown exeption: " + CheckNull.getPstr(e));
		}
	}
	
	public static void create_file(NIKI_file nf) throws SQLException
	{
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		try
		{
			c = ConnectionPool.getConnection();
			st = c.createStatement();
			//logger.info("NIKI 5435243532 creating file id" + nf.getId());
			st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
			st.executeUpdate(
					"ALTER SESSION SET CURRENT_SCHEMA=" + 
					get_schema_name(c, nf.getBranch())
					);
			cs = c.prepareCall("{ call info.init() }");
	        cs.execute();
	        //logger.info("NIKI 5435243532 creating file 1 id" + nf.getId());
			String tmp_out_dir = ConnectionPool.getValue("NIKI_file_out_tmp_dir");
			String out_dir = ConnectionPool.getValue("NIKI_file_out_dir");
			String arch_out_dir = ConnectionPool.getValue("NIKI_file_out_arch_dir");
			
			if (! new File(tmp_out_dir).exists()) new File(tmp_out_dir).mkdirs();
			if (! new File(out_dir).exists()) new File(out_dir).mkdirs();
			if (! new File(arch_out_dir).exists()) new File(arch_out_dir).mkdirs();
			//logger.info("NIKI 5435243532 creating file 2 id" + nf.getId());
			long reis = get_reis(c);
			//logger.info("NIKI 5435243532 creating file 3 id" + nf.getId() + " reis "+ reis);
			String text_file_name = get_file_name(
					ConnectionPool.getValue("HO"),
					get_day(c), 
					get_character_string_from_number(reis, 3)
					);
			//logger.info("NIKI 5435243532 creating file id" + nf.getId());
			String arch_file_name = get_arch_file_name(
					ConnectionPool.getValue("HO"),
					get_day(c), 
					get_character_string_from_number(reis, 3)
					);
			
			String tmp_out_file = tmp_out_dir + text_file_name;
			String arch_tmp_out_file = tmp_out_dir + arch_file_name;
			
			logger.info("creating file " + tmp_out_file);
			
			BufferedWriter out = new BufferedWriter( new OutputStreamWriter(
	                new FileOutputStream(tmp_out_file)
	                ));
			
			boolean full = get_data_from_procedure(
					nf.getBranch(),
					get_file_id(c),
					get_day(c),
					text_file_name,
					reis,
					c,
					out
					);
			
			out.close();
			
			if(!full)
			{
				logger.error("Empty NIKI file");
				update_file_state(c, nf);
				c.commit();
				new File("tmp_out_file").delete();
				return;
			}
			
			
			logger.info("file created: " + tmp_out_file);
			
			Process p = Runtime.getRuntime().exec(
					"arj32.exe a -e " + arch_tmp_out_file + 
					" " + tmp_out_file);
			p.waitFor();
			new File("tmp_out_file").delete();
			logger.info(tmp_out_file + " archivated to " + arch_tmp_out_file);
			new File(tmp_out_file).delete();
			if (! new File(arch_out_dir + 
					sdf_dir.format(new java.util.Date())).exists())
				new File(arch_out_dir + 
						sdf_dir.format(new java.util.Date())).mkdirs();
			
			FileUtils.copyFile(
					new File(arch_tmp_out_file), 
					new File(
							arch_out_dir + 
							sdf_dir.format(new java.util.Date()) + 
							arch_file_name)
					);
			
			logger.info(arch_tmp_out_file + " copied to " + arch_out_dir + 
					sdf_dir.format(new java.util.Date()) + 
					arch_file_name);
					
			new File(arch_tmp_out_file).renameTo(new File(out_dir + arch_file_name));
			logger.info(arch_tmp_out_file + " moved to " + out_dir + arch_file_name);
			
			update_file_state(c, nf);
			
			c.commit();
		}
		catch(Exception e)
		{
			if( c!= null) c.rollback();
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(st != null) st.close();
			if(cs != null) cs.close();
			if(c != null) c.close();
		}
	}
	
	public static boolean get_data_from_procedure(
			String branch, 
			long file_id,
			Date d_date,
			String file_name,
			long reis,
			Connection c,
			BufferedWriter out) throws Exception
	{
		CallableStatement call = null;
		ResultSet rs = null;
		long line_number = 0l;
		try
		{
			call =c.prepareCall ("{call niki_java.get_file(?, ?, ?, ?, ?, ?)}");
			//call =c.prepareCall ("{call cw_out_dummy_pkg.A_send_cw_oper(?)}");
			call.registerOutParameter (1, OracleTypes.CURSOR);
			
			call.setString(2, branch);
			call.setLong(3, file_id);
			call.setDate(4, new java.sql.Date(d_date.getTime()));
			call.setString(5, file_name);
			call.setLong(6, reis);
			
			
			call.execute();
			
			rs = (ResultSet)call.getObject(1);
			
			//if (rs.isBeforeFirst())
			{
				int cc = rs.getMetaData().getColumnCount();
				StringBuffer stb = new StringBuffer();
				
				while(rs.next())
				{
					line_number ++;
					
					for (int i = 1; i <= cc; i ++)
		        	{
						stb.append((rs.getString(i)).equals("null")?"":rs.getString(i));
		        	}
					stb.append("\n");
					if (line_number % 10000 == 0)
		        	{
						out.write(stb.toString());
						stb.setLength(0);
		        	}
				}
				out.write(stb.toString());
			}
			return line_number > 0;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(call != null) call.close();
			if(rs != null) rs.close();
		}
	}
	
	public static String get_arch_file_name(String branch, Date date, String reis_36)
	{
		return "I#" + branch.substring(1) + get_36_date(date) + "." + reis_36;
	}
	
	public static String get_file_name(String branch, Date date, String reis_36)
	{
		return "#I" + branch.substring(1) + get_36_date(date) + "." + reis_36;
	}
	
	public static List<NIKI_file> get_files_to_send() throws Exception
	{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NIKI_file> res = new ArrayList<NIKI_file>();
		
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"select t.branch, t.niki_java_file_id id, t.state_code state " +
					"from niki_java_serv t where t.state_code = 1"
					);
			rs = ps.executeQuery();
			int cnt = 0;
			while (rs.next())
			{
				cnt++;
				//logger.info("NIKI 5435243532 got file to send id " + rs.getLong("id") +
				//		" branch " + rs.getString("branch"));
				res.add(new NIKI_file(
						rs.getLong("id"),
						rs.getString("branch"),
						rs.getInt("state")
						));
			}
			//logger.info("NIKI 5435243532 got file snd num " + cnt);
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
			if(c != null) c.close();
		}
	}
	
	public static String get_36_reis(Connection c) throws Exception
	{
		Long reis = get_reis(c);
		String reis_36 = get_character_string_from_number(reis, 3);
		return reis_36;
	}
	
	public static Long get_file_id(Connection c) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long res = null;
		
		try
		{
			ps = c.prepareStatement("select seq_ldr_niki_file.nextval res from dual");
			rs = ps.executeQuery();
			rs.next();
			res = rs.getLong("res");
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}
	}
	
	public static Long get_reis(Connection c) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long reis = null;
		
		try
		{
			ps = c.prepareStatement("select max(t.number_reis) res from ldr_go_file t where t.oper_date = ?");
			ps.setDate(1, new java.sql.Date(get_day(c).getTime()));
			rs = ps.executeQuery();
			
			if(rs.next())
				reis = rs.getLong("res") + 1;
			else
				reis = 1l;
			
			if(reis >= 15012l && reis <= 15047l)
				reis += 36;
			
			return reis;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}
	}
	
	public static String get_schema_name(Connection c, String branch) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String res = null;
		
		try
		{
			ps = c.prepareStatement("select t.user_name res from ss_dblink_branch t where t.branch = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			
			rs.next();
			res = rs.getString("res");
			
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}
	}
	
	public static void update_file_state(Connection c, NIKI_file nf) throws Exception
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"update niki_java_serv t set t.state_code = 2 " +
					"where t.niki_java_file_id = ?"
					);
			ps.setLong(1, nf.getId());
			ps.executeQuery();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) ps.close();
		}
	}
	
	public static Date get_day(Connection c) throws Exception
	{
		CallableStatement ps = null;
		ResultSet rs = null;
		Date res = null;
		try
		{
			ps = c.prepareCall("{? = call info.getDay() }");
			ps.registerOutParameter(1, java.sql.Types.DATE);
			
			ps.execute();
			res = ps.getDate(1);
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)rs.close();
			if(ps != null)ps.close();
		}
	}
	
	public static String get_character_string_from_number(Long number, Integer length)
	{
		String res = null;
		String num_36 = get_36_from_10_number(number);
		if(length == null) res = num_36;
		else 
		{
			res = StringUtils.repeat("0", length - num_36.length()) + num_36;
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
	
	public static String get_36_date(Date dt)
	{
		String res = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
		
		Calendar cn = Calendar.getInstance();
		cn.setTime(dt);
		
		res = get_36_from_10_number(cn.get(Calendar.DAY_OF_MONTH))+
			get_36_from_10_number(cn.get(Calendar.MONTH)+1);
	
		return res;
	}
}

