package com.is.tf.garanttimechange;

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

public class garanttimechangeService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM tf_garanttimechange order by p9t19  ";
	
	public static List<garanttimechange> getgaranttimechange(String p3t18, Long id_contract)
	{
		
		List<garanttimechange> list = new ArrayList<garanttimechange>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tf_garanttimechange where p1t19 = '" + p3t18 + "' and id_contract= " + id_contract + " order by p9t19");
			while (rs.next())
			{
				list.add(new garanttimechange(
											rs.getLong("id"),
											rs.getString("p0t19"),
											rs.getString("p1t19"),
											rs.getDate("p2t19"),
											rs.getString("p3t19"),
											rs.getDate("p4t19"),
											rs.getString("p5t19"),
											rs.getString("p6t19"),
											rs.getString("p7t19"),
											rs.getString("p8t19"),
											rs.getInt("p9t19"),
											rs.getString("p10t19"),
											rs.getDate("p99t19"),
											rs.getString("p100t19"),
											rs.getString("p101t19")

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
	
	private static List<FilterField> getFilterFields(garanttimechangeFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t19=?", filter.getP0t19()));
		}
		if (!CheckNull.isEmpty(filter.getP1t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t19=?", filter.getP1t19()));
		}
		if (!CheckNull.isEmpty(filter.getP2t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t19=?", filter.getP2t19()));
		}
		if (!CheckNull.isEmpty(filter.getP3t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t19=?", filter.getP3t19()));
		}
		if (!CheckNull.isEmpty(filter.getP4t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t19=?", filter.getP4t19()));
		}
		if (!CheckNull.isEmpty(filter.getP5t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t19=?", filter.getP5t19()));
		}
		if (!CheckNull.isEmpty(filter.getP6t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t19=?", filter.getP6t19()));
		}
		if (!CheckNull.isEmpty(filter.getP7t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t19=?", filter.getP7t19()));
		}
		if (!CheckNull.isEmpty(filter.getP8t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t19=?", filter.getP8t19()));
		}
		if (!CheckNull.isEmpty(filter.getP9t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t19=?", filter.getP9t19()));
		}
		if (!CheckNull.isEmpty(filter.getP10t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t19=?", filter.getP10t19()));
		}
		if (!CheckNull.isEmpty(filter.getP99t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p99t19=?", filter.getP99t19()));
		}
		if (!CheckNull.isEmpty(filter.getP100t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t19=?", filter.getP100t19()));
		}
		
		if (!CheckNull.isEmpty(filter.getP101t19()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p101t19=?", filter.getP101t19()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(garanttimechangeFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM tf_garanttimechange ");
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
	
	public static List<garanttimechange> getgaranttimechangesFl(int pageIndex, int pageSize, garanttimechangeFilter filter)
	{
		
		List<garanttimechange> list = new ArrayList<garanttimechange>();
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
				list.add(new garanttimechange(
											rs.getLong("id"),
											rs.getString("p0t19"),
											rs.getString("p1t19"),
											rs.getDate("p2t19"),
											rs.getString("p3t19"),
											rs.getDate("p4t19"),
											rs.getString("p5t19"),
											rs.getString("p6t19"),
											rs.getString("p7t19"),
											rs.getString("p8t19"),
											rs.getInt("p9t19"),
											rs.getString("p10t19"),
											rs.getDate("p99t19"),
											rs.getString("p100t19"),
											rs.getString("p101t19")
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
	
	public garanttimechange getgaranttimechange(int garanttimechangeId)
	{
		
		garanttimechange garanttimechange = new garanttimechange();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_garanttimechange WHERE id=?");
			ps.setInt(1, garanttimechangeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garanttimechange = new garanttimechange();
				
				garanttimechange.setId(rs.getLong("id"));
				garanttimechange.setP0t19(rs.getString("p0t19"));
				garanttimechange.setP1t19(rs.getString("p1t19"));
				garanttimechange.setP2t19(rs.getDate("p2t19"));
				garanttimechange.setP3t19(rs.getString("p3t19"));
				garanttimechange.setP4t19(rs.getDate("p4t19"));
				garanttimechange.setP5t19(rs.getString("p5t19"));
				garanttimechange.setP6t19(rs.getString("p6t19"));
				garanttimechange.setP7t19(rs.getString("p7t19"));
				garanttimechange.setP8t19(rs.getString("p8t19"));
				garanttimechange.setP9t19(rs.getInt("p9t19"));
				garanttimechange.setP10t19(rs.getString("p10t19"));
				garanttimechange.setP99t19(rs.getDate("p99t19"));
				garanttimechange.setP100t19(rs.getString("p100t19"));
				garanttimechange.setP101t19(rs.getString("p101t19"));
				
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
		return garanttimechange;
	}
	
	public static garanttimechange create(garanttimechange garanttimechange)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_garanttimechange.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garanttimechange.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO tf_garanttimechange (id, p0t19, p1t19, p2t19, p3t19, p4t19, p5t19, p6t19, p7t19, p8t19, p9t19, p10t19, p99t19, p100t19 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, garanttimechange.getId());
			ps.setString(2, garanttimechange.getP0t19());
			ps.setString(3, garanttimechange.getP1t19());
			ps.setDate(4, new java.sql.Date(garanttimechange.getP2t19().getTime()));
			ps.setString(5, garanttimechange.getP3t19());
			ps.setDate(6, new java.sql.Date(garanttimechange.getP4t19().getTime()));
			ps.setString(7, garanttimechange.getP5t19());
			ps.setString(8, garanttimechange.getP6t19());
			ps.setString(9, garanttimechange.getP7t19());
			ps.setString(10, garanttimechange.getP8t19());
			ps.setInt(11, garanttimechange.getP9t19());
			ps.setString(12, garanttimechange.getP10t19());
			ps.setDate(13, new java.sql.Date(garanttimechange.getP99t19().getTime()));
			ps.setString(14, garanttimechange.getP100t19());
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
		return garanttimechange;
	}
	
	public static void update(garanttimechange garanttimechange)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_garanttimechange SET id=?, p0t19=?, p1t19=?, p2t19=?, p3t19=?, p4t19=?, p5t19=?, p6t19=?, p7t19=?, p8t19=?  WHERE id=?");
			
			ps.setLong(1, garanttimechange.getId());
			ps.setString(2, garanttimechange.getP0t19());
			ps.setString(3, garanttimechange.getP1t19());
			ps.setDate(4, new java.sql.Date(garanttimechange.getP2t19().getTime()));
			ps.setString(5, garanttimechange.getP3t19());
			ps.setDate(6, new java.sql.Date(garanttimechange.getP4t19().getTime()));
			ps.setString(7, garanttimechange.getP5t19());
			ps.setString(8, garanttimechange.getP6t19());
			ps.setString(9, garanttimechange.getP7t19());
			ps.setString(10, garanttimechange.getP8t19());
			ps.setInt(11, garanttimechange.getP9t19());
			ps.setString(12, garanttimechange.getP10t19());
			ps.setDate(13, new java.sql.Date(garanttimechange.getP99t19().getTime()));
			ps.setString(14, garanttimechange.getP100t19());
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
	
	public static void remove(garanttimechange garanttimechange, String p3t18, Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_garanttimechange WHERE p1t19=? and id_contract=?");
			ps.setString(1, p3t18);
			ps.setLong(2, id_contract);
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
	
	// public static com.sbs.service.GarantTimeChange
	// create2(com.sbs.service.GarantTimeChange garanttimechange, String p1t19,
	// Long id_contract)
	public static void create2(ArrayList<com.sbs.service.GarantTimeChange> GarantTimeChange, String p1t19, Long id_contract)
	{
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_garanttimechange.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO tf_garanttimechange (id, p1t19, p2t19, p3t19, p4t19, p5t19, p6t19, p7t19, p8t19, id_contract, p9t19, p10t19, p99t19,p100t19 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			for (int i = 0; i < GarantTimeChange.size() ; i++)
			{
				ps.setLong(1, aid);
				ps.setString(2, p1t19);
				ps.setDate(3, new java.sql.Date(GarantTimeChange.get(i).getP2T19().getTimeInMillis()));
				if (GarantTimeChange.get(i).getP3T19() != null)
				{
					ps.setInt(4, GarantTimeChange.get(i).getP3T19());
				}
				else
				{
					ps.setString(4, null);
				}
				ps.setDate(5, new java.sql.Date(GarantTimeChange.get(i).getP4T19().getTimeInMillis()));
				if (GarantTimeChange.get(i).getP5T19() != null)
				{
					ps.setInt(6, GarantTimeChange.get(i).getP5T19());
				}
				else
				{
					ps.setString(6, null);
				}
				ps.setShort(7, GarantTimeChange.get(i).getP6T19());
				ps.setString(8, GarantTimeChange.get(i).getP7T19());
				ps.setString(9, GarantTimeChange.get(i).getP8T19());
				ps.setLong(10, id_contract);
				ps.setInt(11, GarantTimeChange.get(i).getP9T19());
				ps.setString(12, GarantTimeChange.get(i).getP10T19());
				ps.setDate(13, new java.sql.Date(GarantTimeChange.get(i).getP99T19().getTimeInMillis()));
				ps.setShort(14, GarantTimeChange.get(i).getP100T19());
				ps.executeUpdate();
				c.commit();
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
		//return garanttimechange;
		
	}
}
