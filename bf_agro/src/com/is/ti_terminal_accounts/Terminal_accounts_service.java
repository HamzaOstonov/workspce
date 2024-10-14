package com.is.ti_terminal_accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;

public class Terminal_accounts_service
{
	private static HashMap<String, Terminal_account> terminal_accounts = null;
	
	public static Terminal_account get_terminal_account(String terminal_id) throws Exception
	{
		if (terminal_accounts == null) init_terminal_accounts();
		return terminal_accounts.get(terminal_id);
	}
	
	private static void init_terminal_accounts() throws Exception
	{
		terminal_accounts = new HashMap<String, Terminal_account>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select t.terminal_id, t.account_id, t.branch from ti_terminal_trans_acc t");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				terminal_accounts.put(rs.getString("terminal_id"), new Terminal_account(
						rs.getString("terminal_id"), 
						rs.getString("branch"), 
						rs.getString("account_id")
						));
			}
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
	
	public static List<Terminal_account> get_terminal_accounts()
	{
		List<Terminal_account> res = new ArrayList<Terminal_account>();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select t.terminal_id, t.account_id, t.branch from ti_terminal_trans_acc t");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Terminal_account(
						rs.getString("terminal_id"), 
						rs.getString("branch"), 
						rs.getString("account_id")
						));
			}
		}
		catch(Exception e)
		{
			com.is.LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			try{if(c != null) c.close();}catch(Exception e1){}
		}
		return res;
	}
	
	public static void update_terminal_account(
			Terminal_account old_ta,
			Terminal_account new_ta) throws Exception
	{
		if (old_ta.getAccount().equals(new_ta.getAccount())&&
			old_ta.getBranch().equals(new_ta.getBranch())&&
			old_ta.getTerminal_id().equals(new_ta.getTerminal_id()))
			return;
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"update ti_terminal_trans_acc t " +
					"set t.terminal_id = ?, t.account_id = ?, t.branch = ? " +
					"where t.terminal_id = ? and t.account_id = ? and t.branch = ?"
					);
			ps.setString(1, new_ta.getTerminal_id());
			ps.setString(2, new_ta.getAccount());
			ps.setString(3, new_ta.getBranch());
			ps.setString(4, old_ta.getTerminal_id());
			ps.setString(5, old_ta.getAccount());
			ps.setString(6, old_ta.getBranch());
			ps.execute();
			c.commit();
		}
		catch(Exception e)
		{
			if(c != null) c.rollback();
			throw e;
		}
		finally
		{
			try{if(c != null) c.close();}catch(Exception e1){}
		}
	}
	
	public static void create_terminal_account(
			Terminal_account new_ta) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"insert into ti_terminal_trans_acc " +
					"(terminal_id, account_id, branch) values (?, ?, ?)"
					);
			ps.setString(1, new_ta.getTerminal_id());
			ps.setString(2, new_ta.getAccount());
			ps.setString(3, new_ta.getBranch());
			ps.execute();
			c.commit();
		}
		catch(Exception e)
		{
			if(c != null) c.rollback();
			throw e;
		}
		finally
		{
			try{if(c != null) c.close();}catch(Exception e1){}
		}
	}
}
