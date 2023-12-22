package com.is.tf.garantsumchange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class GarantsumchangeService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM tf_garantsumchange order by  p11t20";
	
	public static List<Garantsumchange> getGarantsumchange(String p3t18, Long id_contract)
	{
		
		List<Garantsumchange> list = new ArrayList<Garantsumchange>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tf_garantsumchange where p1t20 = '" + p3t18 + "' and id_contract= " + id_contract + " order by  p11t20");
			// System.out.println("SELECT * FROM tf_garantsumchange where p1t20 = '"+p3t18+"' and id_contract= "+id_contract);
			while (rs.next())
			{
				list.add(new Garantsumchange(
												rs.getLong("id"),
												rs.getString("p0t20"),
												rs.getString("p1t20"),
												rs.getDouble("p3t20"),
												rs.getDouble("p4t20"),
												rs.getDouble("p5t20"),
												rs.getDouble("p6t20"),
												rs.getDouble("p7t20"),
												rs.getString("p8t20"),
												rs.getString("p9t20"),
												rs.getString("p10t20"),
												rs.getInt("p11t20"),
												rs.getString("p12t20"),
												rs.getString("p15t20"),
												rs.getDate("p99t20"),
												rs.getString("p100t20"),
												rs.getString("p101t20")
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
	
	private static List<FilterField> getFilterFields(GarantsumchangeFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t20=?", filter.getP0t20()));
		}
		if (!CheckNull.isEmpty(filter.getP1t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t20=?", filter.getP1t20()));
		}
		
		if (!CheckNull.isEmpty(filter.getP3t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t20=?", filter.getP3t20()));
		}
		if (!CheckNull.isEmpty(filter.getP4t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t20=?", filter.getP4t20()));
		}
		if (!CheckNull.isEmpty(filter.getP5t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t20=?", filter.getP5t20()));
		}
		if (!CheckNull.isEmpty(filter.getP6t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t20=?", filter.getP6t20()));
		}
		if (!CheckNull.isEmpty(filter.getP7t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t20=?", filter.getP7t20()));
		}
		if (!CheckNull.isEmpty(filter.getP8t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t20=?", filter.getP8t20()));
		}
		if (!CheckNull.isEmpty(filter.getP9t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t20=?", filter.getP9t20()));
		}
		if (!CheckNull.isEmpty(filter.getP10t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t20=?", filter.getP10t20()));
		}
		if (!CheckNull.isEmpty(filter.getP11t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t20=?", filter.getP11t20()));
		}
		if (!CheckNull.isEmpty(filter.getP12t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t20=?", filter.getP12t20()));
		}
		if (!CheckNull.isEmpty(filter.getP15t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t20=?", filter.getP15t20()));
		}
		if (!CheckNull.isEmpty(filter.getP99t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p99t20=?", filter.getP99t20()));
		}
		if (!CheckNull.isEmpty(filter.getP100t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t20=?", filter.getP100t20()));
		}
		if (!CheckNull.isEmpty(filter.getP101t20()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p101t20=?", filter.getP101t20()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(GarantsumchangeFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM tf_garantsumchange ");
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
	
	public static List<Garantsumchange> getGarantsumchangesFl(int pageIndex, int pageSize, GarantsumchangeFilter filter)
	{
		
		List<Garantsumchange> list = new ArrayList<Garantsumchange>();
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
				list.add(new Garantsumchange(
												rs.getLong("id"),
												rs.getString("p0t20"),
												rs.getString("p1t20"),
												rs.getDouble("p3t20"),
												rs.getDouble("p4t20"),
												rs.getDouble("p5t20"),
												rs.getDouble("p6t20"),
												rs.getDouble("p7t20"),
												rs.getString("p8t20"),
												rs.getString("p9t20"),
												rs.getString("p10t20"),
												rs.getInt("p11t20"),
												rs.getString("p12t20"),
												rs.getString("p15t20"),
												rs.getDate("p99t20"),
												rs.getString("p100t20"),
												rs.getString("p101t20")

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
	
	public Garantsumchange getGarantsumchange(int garantsumchangeId)
	{
		
		Garantsumchange garantsumchange = new Garantsumchange();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_garantsumchange WHERE id=?");
			ps.setInt(1, garantsumchangeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garantsumchange = new Garantsumchange();
				
				garantsumchange.setId(rs.getLong("id"));
				garantsumchange.setP0t20(rs.getString("p0t20"));
				garantsumchange.setP1t20(rs.getString("p1t20"));
				garantsumchange.setP3t20(rs.getDouble("p3t20"));
				garantsumchange.setP4t20(rs.getDouble("p4t20"));
				garantsumchange.setP5t20(rs.getDouble("p5t20"));
				garantsumchange.setP6t20(rs.getDouble("p6t20"));
				garantsumchange.setP7t20(rs.getDouble("p7t20"));
				garantsumchange.setP8t20(rs.getString("p8t20"));
				garantsumchange.setP9t20(rs.getString("p9t20"));
				garantsumchange.setP10t20(rs.getString("p10t20"));
				garantsumchange.setP11t20(rs.getInt("p11t20"));
				garantsumchange.setP12t20(rs.getString("p12t20"));
				garantsumchange.setP15t20(rs.getString("p15t20"));
				garantsumchange.setP99t20(rs.getDate("p99t20"));
				garantsumchange.setP100t20(rs.getString("p100t20"));
				garantsumchange.setP101t20(rs.getString("p101t20"));
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
		return garantsumchange;
	}
	
	public static Garantsumchange create(Garantsumchange garantsumchange)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_garantsumchange.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garantsumchange.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO tf_garantsumchange (id, p0t20, p1t20, p3t20, p4t20, p5t20, p6t20, p7t20, p8t20, p9t20, p10t20) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, garantsumchange.getId());
			ps.setString(2, garantsumchange.getP0t20());
			ps.setString(3, garantsumchange.getP1t20());
			ps.setDouble(5, garantsumchange.getP3t20());
			ps.setDouble(6, garantsumchange.getP4t20());
			ps.setDouble(7, garantsumchange.getP5t20());
			ps.setDouble(8, garantsumchange.getP6t20());
			ps.setDouble(9, garantsumchange.getP7t20());
			ps.setString(10, garantsumchange.getP8t20());
			ps.setString(11, garantsumchange.getP9t20());
			ps.setString(12, garantsumchange.getP10t20());
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
		return garantsumchange;
	}
	
	public static void update(Garantsumchange garantsumchange)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_garantsumchange SET id=?, p0t20=?, p1t20=?, p3t20=?, p4t20=?, p5t20=?, p6t20=?, p7t20=?, p8t20=?, p9t20=?, p10t20=?  WHERE id=?");
			
			ps.setLong(1, garantsumchange.getId());
			ps.setString(2, garantsumchange.getP0t20());
			ps.setString(3, garantsumchange.getP1t20());
			ps.setDouble(5, garantsumchange.getP3t20());
			ps.setDouble(6, garantsumchange.getP4t20());
			ps.setDouble(7, garantsumchange.getP5t20());
			ps.setDouble(8, garantsumchange.getP6t20());
			ps.setDouble(9, garantsumchange.getP7t20());
			ps.setString(10, garantsumchange.getP8t20());
			ps.setString(11, garantsumchange.getP9t20());
			ps.setString(12, garantsumchange.getP10t20());
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
	
	public static void remove(Garantsumchange garantsumchange, String p3t18, Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_garantsumchange WHERE p1t20=? and id_contract=?");
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
	
	//public static com.sbs.service.GarantSumChange create2(com.sbs.service.GarantSumChange garantSumChange, String p3t18, Long id_contract)
	public static void create2(ArrayList<com.sbs.service.GarantSumChange> Garantsumchange, String p3t18, Long id_contract)
	{
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_AccreditivSumchange.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO tf_garantsumchange (id, p0t20, p1t20, p3t20, p4t20, p5t20, p6t20, p7t20, p8t20, p9t20, p10t20, id_contract, p11t20, p12t20, p15t20, p99t20, p100t20) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			for (int i = 0; i < Garantsumchange.size(); i++ )
			{
				ps.setLong(1, aid);
				ps.setLong(2, 0);
				ps.setString(3, p3t18);
				ps.setDouble(4, Garantsumchange.get(i).getP3T20());
				ps.setDouble(5, Garantsumchange.get(i).getP4T20());
				ps.setDouble(6, Garantsumchange.get(i).getP5T20());
				ps.setDouble(7, Garantsumchange.get(i).getP6T20());
				ps.setDouble(8, Garantsumchange.get(i).getP7T20());
				ps.setInt(9, Garantsumchange.get(i).getP8T20());
				ps.setString(10, Garantsumchange.get(i).getP9T20());
				ps.setString(11, Garantsumchange.get(i).getP10T20());
				ps.setLong(12, id_contract);
				ps.setDouble(13, Garantsumchange.get(i).getP11T20());
				ps.setString(14, Garantsumchange.get(i).getP12T20());
				ps.setShort(15, Garantsumchange.get(i).getP15T20());
				ps.setDate(16, new java.sql.Date(Garantsumchange.get(i).getP99T20().getTimeInMillis()));
				ps.setShort(17, Garantsumchange.get(i).getP100T20());
				ps.executeUpdate();
				c.commit();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("GarantSumChange Service ERROR  =  " + CheckNull.getPstr(e));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		//return garantSumChange;
	}
	
}
