package com.is.file_reciever_srv.simple.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;


public class File_service
{
	private static Logger logger = ISLogger.getLogger();
	
	public static File_in create_file_in(String name, int type, Connection c) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement("select seq_fr_file.nextval id, sysdate from dual");
		ResultSet rs = ps.executeQuery();
		rs.next();
		long file_id = rs.getLong("id");
		Timestamp date = rs.getTimestamp("sysdate");
		File_in fl = null;
		fl = new File_in(
				file_id, 
				name, 
				type, 
				date);
		
		ps = c.prepareStatement("insert into fr_file (id, name, date_time) values (?, ?, ?)");
		ps.setLong(1, fl.getId());
		ps.setString(2, fl.getName());
		ps.setTimestamp(3, new Timestamp(fl.getDate_time().getTime()));
		ps.execute();
		
		ps = c.prepareStatement("insert into fr_file_in " +
				"(id, id_file_type) values (?, ?)");
		ps.setLong(1, fl.getId());
		ps.setInt(2, fl.getId_file_type());
		ps.execute();
		try{if(ps != null)ps.close();}catch(Exception e){}
		try{if(rs != null)rs.close();}catch(Exception e){}
		return fl;
	}
	
	public static File_in create_file_in(String name, int type) throws Reciever_exception
	{
		Connection c = null;
		PreparedStatement ps = null;
		File_in fl = null;
		try
		{
			c = ConnectionPool.getConnection();
			fl = create_file_in(name, type, c);
			c.commit();
			return fl;
		}
		catch(SQLException e)
		{
			logger.error(com.is.utils.CheckNull.getPstr(e));
			try{c.rollback();}catch(Exception e1){}
			throw new Reciever_exception(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(ps != null)ps.close();}catch(Exception e){}
			try{if(c != null)c.close();}catch(Exception e){}
		}
	}
	
	public static File_out create_file_out(String name, Connection c) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement("select seq_fr_file.nextval id, sysdate from dual");
		ResultSet rs = ps.executeQuery();
		rs.next();
		long file_id = rs.getLong("id");
		Timestamp date = rs.getTimestamp("sysdate");
		File_out fl = null;
		
		fl = new File_out(
				file_id, 
				name, 
				date);
		
		ps = c.prepareStatement("insert into fr_file (id, name, date_time) values (?, ?, ?)");
		ps.setLong(1, fl.getId());
		ps.setString(2, fl.getName());
		ps.setTimestamp(3, new Timestamp(fl.getDate_time().getTime()));
		ps.execute();
		ps = c.prepareStatement("insert into fr_file_out " +
		"(id) values (?)");
		ps.setLong(1, fl.getId());
		ps.execute();
		try{if(ps != null)ps.close();}catch(Exception e){}
		try{if(rs != null)rs.close();}catch(Exception e){}
		return fl;
	}
	
	public static File_out create_file_out(String name) throws Reciever_exception
	{
		Connection c = null;
		PreparedStatement ps = null;
		File_out fl = null;
		try
		{
			c = ConnectionPool.getConnection();
			fl = create_file_out(name, c);
			c.commit();
			return fl;
		}
		catch(SQLException e)
		{
			logger.error(com.is.utils.CheckNull.getPstr(e));
			try{c.rollback();}catch(Exception e1){}
			throw new Reciever_exception(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(ps != null)ps.close();}catch(Exception e){}
			try{if(c != null)c.close();}catch(Exception e){}
		}
	}
}
