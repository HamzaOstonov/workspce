package com.is;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.sql.*;

import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

public class ConnectionPool implements Serializable
{
	String message = "Not Connected";
	static final long serialVersionUID = 2L;
	
	public static Connection getConnection(String branch) throws SQLException
	{
		Connection c = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/myoracle");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			c = datasource.getConnection();
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + branch);
			
			return c;
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			throw new RuntimeException("Database Not Available.");
		}
	}
	
	public static Connection getConnection(String login, String password)
	{
		Connection c = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/myoracle");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			
			c = datasource.getConnection(login, password);
			
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		return c;
	}
	
	public static Connection getConnection_cliring() throws SQLException
	{
		Connection c = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/clearing");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			c = datasource.getConnection();
			Statement st = c.createStatement();
			st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
			// c.setAutoCommit(false);
			
			// return datasource.getConnection();
			return c;
		}
		catch (Exception e)
		{
			// com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e))
			// ;
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			throw new RuntimeException("Database Not Available.");
		}
	}
	
	public static Connection getConnection(String login, String password, String branch)
	{
		Connection c = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/myoracle");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			
			c = datasource.getConnection(login, password);
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + branch);
			
			c.setAutoCommit(false);
			CallableStatement cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		return c;
	}
	
	public static Connection getConnection(int user_id, String branch) throws SQLException
	{
		Connection c = null;
		String schema = "";
		ISLogger.getLogger().error(">>> getconnection uid:" + user_id + " branch:" + branch + "<<<");
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext
					.lookup("jdbc/myoracle");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			c = datasource.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select u.*,s.user_name scheme from v_bf_bank_users u, ss_dblink_branch s where u.id = " + user_id + " and u.branch = '" + branch + "' and s.branch = u.branch ");
			if (rs.next())
			{
				schema = rs.getString("scheme");
			}
			st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + schema);
			c.setAutoCommit(false);
			CallableStatement cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			return c;
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			throw new RuntimeException("Database Not Available.");
		}
	}
	
	public static void close(Connection connection)
	{
		try
		{
			if (connection != null)
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	}
	
    public static void close(PreparedStatement pStatement) {
		try {
			if (pStatement != null) {
				pStatement.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}
	
	public static void close(CallableStatement cs) {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public static void close(ResultSet rSet) {
		try {
			if (rSet != null) {
				rSet.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}
	
	
	public String getMessage()
	{
		return message;
	}
	
	public static Connection getTConnection() throws SQLException
	{
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/tieto");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			return datasource.getConnection();
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			throw new RuntimeException("Database Not Available.");
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource datasource = (DataSource) envContext.lookup("jdbc/myoracle");
			if (datasource == null)
			{
				throw new Exception("No DataSource");
			}
			return datasource.getConnection();
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			throw new RuntimeException("Database Not Available.");
		}
	}
	
	public static void doAction(Connection c, ResultSet rs, int group, int deal, int actionid) throws SQLException
	{
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		String cn;
		CallableStatement cs = c.prepareCall("{ call Param.SetParam(?,?) }");
		CallableStatement acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
		CallableStatement ccs = c.prepareCall("{ call Param.clearparam() }");
		if (rs.next())
		{
			ccs.execute();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
			{
				cn = rs.getMetaData().getColumnName(i);
				System.out.println(cn + "  " + rs.getMetaData().getColumnTypeName(i));
				if (rs.getString(cn) != null)
				{
					cs.setString(1, cn);
					if (rs.getMetaData().getColumnTypeName(i).equals("DATE"))
					{
						cs.setString(2, bdf.format(rs.getDate(cn)));
					}
					else
					{
						cs.setString(2, rs.getString(cn));
					}
					cs.execute();
				}
			}
			
			acs.setInt(1, group);
			acs.setInt(2, deal);
			acs.setInt(3, actionid);
			acs.execute();
			
		}
	}
	
	public static String getValue(String key)
	{
		Connection c = null;
		String res = "";
		try
		{
			c = getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT value FROM BF_SETS WHERE id=?");
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("value");
			}
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			close(c);
		}
		return res;
	}
	
	public static String getValue(String key, String branch)
	{
		Connection c = null;
		String res = "";
		try
		{
			c = getConnection(branch);
			PreparedStatement ps = c.prepareStatement("SELECT value FROM BF_SETS WHERE id=?");
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("value");
			}
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			close(c);
		}
		return res;
	}
	
	public static Connection getConnection_mssql() throws SQLException {
	    try {
	      Context context = new InitialContext();
	      Context envContext = (Context) context.lookup("java:/comp/env");
	      DataSource datasource = (DataSource) envContext
	          .lookup("jdbc/ms_sql_db");
	      if (datasource == null) {
	        throw new Exception("No DataSource");
	      }

	      Connection c = datasource.getConnection();

	      return c;
	    } catch (Exception e) {
	      ISLogger.getLogger().error(CheckNull.getPstr(e));
	      //e.printStackTrace();
	      throw new RuntimeException("Database Not Available.");
	    }
	  }

}
