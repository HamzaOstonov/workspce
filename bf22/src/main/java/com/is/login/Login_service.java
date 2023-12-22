package com.is.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.RefData;

public class Login_service
{
	public static List<RefData> get_branches()
	{
		return getRefData("select b.branch data, b.branch label from ss_dblink_branch b");
	}
	
	public static List<RefData> getRefData(String sql)
	  {
	    List list = new LinkedList();
	    Connection c = null;
	    try
	    {
	      c = ConnectionPool.getConnection();
	      Statement s = c.createStatement();
	      ResultSet rs = s.executeQuery(sql);
	      while (rs.next())
	        list.add(
	          new RefData(rs.getString("data"), 
	          rs.getString("label")));
	    }
	    catch (SQLException e) {
	      ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    } finally {
	      ConnectionPool.close(c);
	    }
	    return list;
	  }
}
