package com.is.tieto.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.RefData;

public class Service
{
	public static List<Ti_file_onus_tr_accmapping> get_Ti_file_onus_tr_accmappings() throws Exception
	{
		List<Ti_file_onus_tr_accmapping> res = new ArrayList<Ti_file_onus_tr_accmapping>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from ti_file_onus_tr_accmapping order by id");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Ti_file_onus_tr_accmapping(
						rs.getString("bin"),
						rs.getLong("id"), 
						rs.getString("terminal"), 
						rs.getString("code"), 
						rs.getString("account_10107"), 
						rs.getString("account_23510"), 
						rs.getString("name"),
						rs.getLong("operation_id"),
						rs.getString("operation_branch"),
						rs.getString("acc_23508_510"),
						rs.getLong("cup_operation_id"),
						rs.getLong("operation_corporativ")
						));
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static List<Ti_file_onus_tr_accmapping> get_Ti_file_onus_bins() throws Exception
	{
		List<Ti_file_onus_tr_accmapping> res = new ArrayList<Ti_file_onus_tr_accmapping>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from ti_file_onus_tr_accmapping order by id");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Ti_file_onus_tr_accmapping(
						rs.getString("bin"),
						rs.getLong("id"), 
						rs.getString("terminal"), 
						rs.getString("code"), 
						rs.getString("account_10107"), 
						rs.getString("account_23510"), 
						rs.getString("name"),
						rs.getLong("operation_id"),
						rs.getString("operation_branch"),
						rs.getString("acc_23508_510"),
						rs.getLong("cup_operation_id"),
						rs.getLong("operation_corporativ")
						));
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static void save(Ti_file_onus_tr_accmapping mapping) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = null;
			
			if(mapping.getId() == null)
			{
				PreparedStatement ps_id = c.prepareStatement(
						"select max(id) res, count(*) cnt from ti_file_onus_tr_accmapping"
						);
				ResultSet rs_id = ps_id.executeQuery();
				rs_id.next();
				if(rs_id.getLong("cnt") == 0l) mapping.setId(1l);
				else mapping.setId(rs_id.getLong("res")+1l);
				
				ps = c.prepareStatement(
						"insert into ti_file_onus_tr_accmapping (" +
						"id, terminal, code, account_10107, account_23510, " +
						"name, operation_id, operation_branch, bin, acc_23508_510, " +
						"cup_operation_id, operation_corporativ) " +
						"values " + 
						"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
						);
				ps.setLong(1, mapping.getId());
				ps.setString(2, mapping.getTerminal());
				ps.setString(3, mapping.getCode());
				ps.setString(4, mapping.getAccount_10107());
				ps.setString(5, mapping.getAccount_23510());
				ps.setString(6, mapping.getName());
				ps.setLong(7, mapping.getOperation_id());
				ps.setString(8, mapping.getOperation_branch());
				ps.setString(9, mapping.getBin());
				ps.setString(10, mapping.getAcc_23508_510());
				ps.setLong(11, mapping.getCup_operation_id());
				ps.setLong(12, mapping.getOperation_corporativ());
				ps.execute();
			}
			else
			{
				ps = c.prepareStatement(
						"update ti_file_onus_tr_accmapping t " +
						"set t.terminal = ?, t.code = ?, t.account_10107 = ?, " +
						"t.account_23510 = ?, t.name = ?, t.operation_id = ?, " +
						"t.operation_branch = ?, bin = ?, acc_23508_510 = ?, " +
						"cup_operation_id = ?, operation_corporativ = ? where t.id = ?"
						);
				ps.setString(1, mapping.getTerminal());
				ps.setString(2, mapping.getCode());
				ps.setString(3, mapping.getAccount_10107());
				ps.setString(4, mapping.getAccount_23510());
				ps.setString(5, mapping.getName());
				ps.setLong(6, mapping.getOperation_id());
				ps.setString(7, mapping.getOperation_branch());
				ps.setString(8, mapping.getBin());
				ps.setString(9, mapping.getAcc_23508_510());
				ps.setLong(10, mapping.getCup_operation_id());
				ps.setLong(11, mapping.getOperation_corporativ());
				ps.setLong(12, mapping.getId());
				
				ps.execute();
			}
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null) c.rollback();
			throw e;
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static long get_tr_operation_id() throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select max(id) res from bf_tr_operations o " +
					"where o.id between 200000 and 201000"
					);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			long res = rs.getLong("res");
			if(!rs.wasNull() && res!=0) return res+1;
			else return 200000l;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static List<RefData> get_tr_operations() throws Exception
	{
		Connection c = null;
		List<RefData> res = new ArrayList<RefData>();
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select o.id, o.descripption from bf_tr_operations o " +
					"where o.parent_group_id = 183"
					);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				res.add(new RefData(rs.getString("id"), rs.getString("descripption")));
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static long create_new_operation(String name) throws Exception
	{
		Connection c = null;
		long res = 0l;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"insert into bf_tr_operations (id, descripption, parent_group_id) " +
					"values (?, ?, ?)"
					);
			res = get_tr_operation_id();
			ps.setLong(1, res);
			ps.setString(2, name);
			ps.setLong(3, 183);
			ps.execute();
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
		return res;
	}
	
}
