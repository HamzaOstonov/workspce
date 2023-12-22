package com.is.tf.expcondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class ExpconditionService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM tf_expcondition ";
	
	public List<Expcondition> getExpcondition()
	{
		
		List<Expcondition> list = new ArrayList<Expcondition>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(msql);
			while (rs.next())
			{
				list.add(new Expcondition(
							rs.getLong("id"),
							rs.getString("p0t13"),
							rs.getString("p1t13"),
							rs.getDate("p2t13"),
							rs.getString("p3t13"),
							rs.getString("p4t13"),
							rs.getDate("p5t13"),
							rs.getString("p6t13"),
							rs.getString("p7t13"),
							rs.getLong("id_contract"),
							rs.getString("p10t13"),
							rs.getString("p11t13"),
							rs.getDate("p12t13"),
							rs.getShort("p100t13")
							));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	private static String getCond(List<FilterField> flfields)
	{
		if (flfields.size() > 0)
		{
			return " and ";
		}
		else return " where ";
	}
	
	private static List<FilterField> getFilterFields(ExpconditionFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t13=?", filter.getP0t13()));
		}
		if (!CheckNull.isEmpty(filter.getP1t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t13=?", filter.getP1t13()));
		}
		if (!CheckNull.isEmpty(filter.getP2t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t13=?", filter.getP2t13()));
		}
		if (!CheckNull.isEmpty(filter.getP3t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t13=?", filter.getP3t13()));
		}
		if (!CheckNull.isEmpty(filter.getP4t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t13=?", filter.getP4t13()));
		}
		if (!CheckNull.isEmpty(filter.getP5t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t13=?", filter.getP5t13()));
		}
		if (!CheckNull.isEmpty(filter.getP6t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t13=?", filter.getP6t13()));
		}
		if (!CheckNull.isEmpty(filter.getP7t13()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t13=?", filter.getP7t13()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(ExpconditionFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM tf_expcondition ");
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			
			for (int k = 0; k < flFields.size(); k++)
			{
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static List<Expcondition> getExpconditionsFl(int pageIndex, int pageSize, ExpconditionFilter filter)
	{
		
		List<Expcondition> list = new ArrayList<Expcondition>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++)
			{
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(new Expcondition(
						rs.getLong("id"),
						rs.getString("p0t13"),
						rs.getString("p1t13"),
						rs.getDate("p2t13"),
						rs.getString("p3t13"),
						rs.getString("p4t13"),
						rs.getDate("p5t13"),
						rs.getString("p6t13"),
						rs.getString("p7t13"),
						rs.getLong("id_contract"),
						rs.getString("p10t13"),
						rs.getString("p11t13"),
						rs.getDate("p12t13"),
						rs.getShort("p100t13")
						));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public Expcondition getExpcondition(int expconditionId)
	{
		
		Expcondition expcondition = new Expcondition();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_expcondition WHERE expcondition_id=?");
			ps.setInt(1, expconditionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				expcondition = new Expcondition();
				
				expcondition.setId(rs.getLong("id"));
				expcondition.setP0t13(rs.getString("p0t13"));
				expcondition.setP1t13(rs.getString("p1t13"));
				expcondition.setP2t13(rs.getDate("p2t13"));
				expcondition.setP3t13(rs.getString("p3t13"));
				expcondition.setP4t13(rs.getString("p4t13"));
				expcondition.setP5t13(rs.getDate("p5t13"));
				expcondition.setP6t13(rs.getString("p6t13"));
				expcondition.setP7t13(rs.getString("p7t13"));
				
				expcondition.setId_contract(rs.getLong("id_contract"));
				expcondition.setP10t13(rs.getString("p10t13"));
				expcondition.setP11t13(rs.getString("p11t13"));
				expcondition.setP12t13(rs.getDate("p12t13"));
				expcondition.setP100t13(rs.getShort("p100t13"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return expcondition;
	}
	
	public static Res create(com.sbs.service.ExpCondition expcondition, Long id_contract) throws Exception
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_expcondition.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO tf_expcondition (id, p1t13, p2t13, p3t13, p4t13, p5t13, p6t13, p7t13" +
					", id_contract, p10t13, p11t13, p12t13, p100t13) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, String.valueOf(expcondition.getP1T13()));
			ps.setDate(++i, expcondition.getP2T13() == null ? null : new java.sql.Date(expcondition.getP2T13().getTimeInMillis()));
			ps.setString(++i, expcondition.getP3T13() == null ? null : String.valueOf(expcondition.getP3T13()));
			ps.setString(++i, expcondition.getP4T13() == null ? null : String.valueOf(expcondition.getP4T13()));
			ps.setDate(++i, expcondition.getP5T13() == null ? null : new java.sql.Date(expcondition.getP5T13().getTimeInMillis()));
			ps.setString(++i, expcondition.getP6T13() == null ? null : String.valueOf(expcondition.getP6T13()));
			ps.setDouble(++i, expcondition.getP7T13() == null ? 0 : expcondition.getP7T13());
			ps.setLong(++i, id_contract);
			ps.setString(++i, expcondition.getP10T13() == null ? null : String.valueOf(expcondition.getP10T13()));
			ps.setString(++i, expcondition.getP11T13() == null ? null : String.valueOf(expcondition.getP11T13()));
			ps.setDate(++i, expcondition.getP12T13() == null ? null : new java.sql.Date(expcondition.getP12T13().getTimeInMillis()));
			ps.setString(++i, String.valueOf(expcondition.getP100T13()));
			
			ps.executeUpdate();
			c.commit();
			res = new Res(1, "Ok");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			res = new Res(0, e.getLocalizedMessage() + " -> " + String.valueOf(e.getStackTrace()));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Expcondition create(Expcondition expcondition) throws Exception
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_expcondition.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				expcondition.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO tf_expcondition (id, p1t13, p2t13, p3t13, p4t13, p5t13, p6t13, p7t13" +
					", p10t13, p11t13, p12t13, p100t13) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, expcondition.getId());
			ps.setString(++i, String.valueOf(expcondition.getP1t13()));
			ps.setDate(++i, expcondition.getP2t13() == null ? null : new java.sql.Date(expcondition.getP2t13().getTime()));
			ps.setString(++i, String.valueOf(expcondition.getP3t13()));
			ps.setString(++i, String.valueOf(expcondition.getP4t13()));
			ps.setDate(++i, expcondition.getP5t13() == null ? null : new java.sql.Date(expcondition.getP5t13().getTime()));
			ps.setString(++i, String.valueOf(expcondition.getP6t13()));
			ps.setDouble(++i, Double.parseDouble(expcondition.getP7t13()));
			ps.setString(++i, String.valueOf(expcondition.getP10t13()));
			ps.setString(++i, String.valueOf(expcondition.getP11t13()));
			ps.setDate(++i, expcondition.getP12t13() == null ? null : new java.sql.Date(expcondition.getP12t13().getTime()));
			ps.setString(++i, String.valueOf(expcondition.getP100t13()));
			
			ps.executeUpdate();
			c.commit();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return expcondition;
	}
	
	public static void update(Expcondition expcondition)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_expcondition SET id=?, p0t13=?, p1t13=?, p2t13=?, p3t13=?, p4t13=?, p5t13=?, p6t13=?, p7t13=?,  WHERE expcondition_id=?");
			
			ps.setLong(1, expcondition.getId());
			ps.setString(2, expcondition.getP0t13());
			ps.setString(3, expcondition.getP1t13());
			ps.setDate(4, new java.sql.Date(expcondition.getP2t13().getTime()));
			ps.setString(5, expcondition.getP3t13());
			ps.setString(6, expcondition.getP4t13());
			ps.setDate(7, new java.sql.Date(expcondition.getP5t13().getTime()));
			ps.setString(8, expcondition.getP6t13());
			ps.setString(9, expcondition.getP7t13());
			ps.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
	}
	
	public static void remove(Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_expcondition WHERE id_contract=?");
			ps.setLong(1, id_contract);
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}
}
