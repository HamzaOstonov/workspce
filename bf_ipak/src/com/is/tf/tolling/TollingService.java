package com.is.tf.tolling;

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

public class TollingService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Tolling ";
	
	public List<Tolling> getTolling()
	{
		
		List<Tolling> list = new ArrayList<Tolling>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Tolling");
			while (rs.next())
			{
				list.add(new Tolling(
							rs.getLong("id"),
							rs.getString("p0t15"),
							rs.getString("p1t15"),
							rs.getString("p2t15"),
							rs.getDouble("p3t15"),
							rs.getDouble("p4t15"),
							rs.getDouble("p5t15"),
							rs.getDouble("p6t15"),
							rs.getDate("p7t15"),
							rs.getDouble("p12t15"),
							rs.getInt("p13t15"),
							rs.getDate("p14t15"),
							rs.getShort("p100t15")
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
	
	private static List<FilterField> getFilterFields(TollingFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t15=?", filter.getP0t15()));
		}
		if (!CheckNull.isEmpty(filter.getP1t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t15=?", filter.getP1t15()));
		}
		if (!CheckNull.isEmpty(filter.getP2t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t15=?", filter.getP2t15()));
		}
		if (!CheckNull.isEmpty(filter.getP3t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t15=?", filter.getP3t15()));
		}
		if (!CheckNull.isEmpty(filter.getP4t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t15=?", filter.getP4t15()));
		}
		if (!CheckNull.isEmpty(filter.getP5t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t15=?", filter.getP5t15()));
		}
		if (!CheckNull.isEmpty(filter.getP6t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t15=?", filter.getP6t15()));
		}
		if (!CheckNull.isEmpty(filter.getP7t15()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t15=?", filter.getP7t15()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(TollingFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Tolling ");
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
	
	public static List<Tolling> getTollingsFl(int pageIndex, int pageSize, TollingFilter filter)
	{
		
		List<Tolling> list = new ArrayList<Tolling>();
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
				list.add(new Tolling(
						rs.getLong("id"),
						rs.getString("p0t15"),
						rs.getString("p1t15"),
						rs.getString("p2t15"),
						rs.getDouble("p3t15"),
						rs.getDouble("p4t15"),
						rs.getDouble("p5t15"),
						rs.getDouble("p6t15"),
						rs.getDate("p7t15"),
						rs.getDouble("p12t15"),
						rs.getInt("p13t15"),
						rs.getDate("p14t15"),
						rs.getShort("p100t15")
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
	
	public Tolling getTolling(int tollingId)
	{
		
		Tolling tolling = new Tolling();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_tolling WHERE id=?");
			ps.setInt(1, tollingId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				tolling = new Tolling();
				
				tolling.setId(rs.getLong("id"));
				tolling.setP0t15(rs.getString("p0t15"));
				tolling.setP1t15(rs.getString("p1t15"));
				tolling.setP2t15(rs.getString("p2t15"));
				tolling.setP3t15(rs.getDouble("p3t15"));
				tolling.setP4t15(rs.getDouble("p4t15"));
				tolling.setP5t15(rs.getDouble("p5t15"));
				tolling.setP6t15(rs.getDouble("p6t15"));
				tolling.setP7t15(rs.getDate("p7t15"));
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
		return tolling;
	}
	
	public static Tolling create(Tolling tolling)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_tolling.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				tolling.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_tolling (id, p0t15, p1t15, p2t15, p3t15, p4t15, p5t15, p6t15, p7t15 ) VALUES (?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, tolling.getId());
			ps.setString(2, tolling.getP0t15());
			ps.setString(3, tolling.getP1t15());
			ps.setString(4, tolling.getP2t15());
			ps.setDouble(5, tolling.getP3t15());
			ps.setDouble(6, tolling.getP4t15());
			ps.setDouble(7, tolling.getP5t15());
			ps.setDouble(8, tolling.getP6t15());
			ps.setDate(9, new java.sql.Date(tolling.getP7t15().getTime()));
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
		return tolling;
	}
	
	public static void update(Tolling tolling)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_tolling SET id=?, p0t15=?, p1t15=?, p2t15=?, p3t15=?, p4t15=?, p5t15=?, p6t15=?, p7t15=?,  WHERE id=?");
			
			ps.setLong(1, tolling.getId());
			ps.setString(2, tolling.getP0t15());
			ps.setString(3, tolling.getP1t15());
			ps.setString(4, tolling.getP2t15());
			ps.setDouble(5, tolling.getP3t15());
			ps.setDouble(6, tolling.getP4t15());
			ps.setDouble(7, tolling.getP5t15());
			ps.setDouble(8, tolling.getP6t15());
			ps.setDate(9, new java.sql.Date(tolling.getP7t15().getTime()));
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_tolling WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Tolling tolling, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_tolling.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_tolling (id, p1t15, p2t15, p3t15, p4t15, p5t15, p6t15, p7t15" +
					",id_contract , p12t15, p13t15, p14t15, p100t15, p10t15, p11t15" +
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, tolling.getP1T15());
			ps.setString(++i, tolling.getP2T15());
			ps.setDouble(++i, tolling.getP3T15());
			ps.setDouble(++i, tolling.getP4T15());
			ps.setDouble(++i, tolling.getP5T15());
			ps.setDouble(++i, tolling.getP6T15());
			ps.setDate(++i, new java.sql.Date(tolling.getP7T15().getTimeInMillis()));
			ps.setLong(++i, id_contract);
			ps.setDouble(++i, tolling.getP12T15());
			ps.setDouble(++i, tolling.getP13T15());
			ps.setDate(++i, tolling.getP14T15() == null ? null : new java.sql.Date(tolling.getP14T15().getTimeInMillis()));
			ps.setString(++i, tolling.getP100T15() + "");
			ps.setString(++i, tolling.getP10T15() + "");
			ps.setString(++i, tolling.getP11T15() + "");
			
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
	
}
