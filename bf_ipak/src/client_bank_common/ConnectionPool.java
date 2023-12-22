package client_bank_common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPool
{
	public static Connection getConnection() throws SQLException 
	{ 
        Connection c = null;
        try 
        {
	        Context context = new InitialContext();
	        Context envContext = (Context) context.lookup("java:/comp/env");
	        DataSource datasource = (DataSource)envContext.lookup("jdbc/klb_clientbank");
	        if (datasource == null ) { throw new Exception("No DataSource"); }
	        c = datasource.getConnection();
	
	        return c;
	    } catch (Exception e) 
	    {
	    	e.printStackTrace();
	        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        throw new RuntimeException("Database Not Available.");
	    }
	}
	
	public static Connection getConnection_dvs() throws SQLException 
	{ 
        Connection c = null;
        try 
        {
	        Context context = new InitialContext();
	        Context envContext = (Context) context.lookup("java:/comp/env");
	        DataSource datasource = (DataSource)envContext.lookup("jdbc/dvs");
	        if (datasource == null ) { throw new Exception("No DataSource"); }
	        c = datasource.getConnection();
	
	        return c;
	    } catch (Exception e) 
	    {
	    	e.printStackTrace();
	        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        throw new RuntimeException("Database Not Available.");
	    }
	}
}
