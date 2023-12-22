package com.is.tf.Commissiongnk;

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

public class CommissiongnkService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Commissiongnk ";
	
	public List<Commissiongnk> getCommissiongnk()
	{
		
		List<Commissiongnk> list = new ArrayList<Commissiongnk>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Commissiongnk");
			while (rs.next())
			{
				list.add(new Commissiongnk(
												rs.getLong("id"),
												rs.getString("p1t28"),
												rs.getString("p0t28"),
												rs.getDate("p2t28"),
												rs.getDouble("p3t28"),
												rs.getString("p4t28"),
												rs.getDouble("p5t28"),
												rs.getString("p6t28"),
												rs.getString("p7t28"),
												rs.getDate("p8t28"),
												rs.getString("p9t28"),
												rs.getString("p10t28"),
												rs.getDate("p13t28"),
												rs.getShort("p100t28")
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
	
	private static List<FilterField> getFilterFields(CommissiongnkFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP1t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t28=?", filter.getP1t28()));
		}
		if (!CheckNull.isEmpty(filter.getP0t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t28=?", filter.getP0t28()));
		}
		if (!CheckNull.isEmpty(filter.getP2t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t28=?", filter.getP2t28()));
		}
		if (!CheckNull.isEmpty(filter.getP3t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t28=?", filter.getP3t28()));
		}
		if (!CheckNull.isEmpty(filter.getP4t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t28=?", filter.getP4t28()));
		}
		if (!CheckNull.isEmpty(filter.getP5t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t28=?", filter.getP5t28()));
		}
		if (!CheckNull.isEmpty(filter.getP6t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t28=?", filter.getP6t28()));
		}
		if (!CheckNull.isEmpty(filter.getP7t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t28=?", filter.getP7t28()));
		}
		if (!CheckNull.isEmpty(filter.getP8t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t28=?", filter.getP8t28()));
		}
		if (!CheckNull.isEmpty(filter.getP9t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t28=?", filter.getP9t28()));
		}
		
		if (!CheckNull.isEmpty(filter.getP10t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t28=?", filter.getP10t28()));
		}
		if (!CheckNull.isEmpty(filter.getP13t28()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t28=?", filter.getP13t28()));
		}
		
		/*
		 * if(!CheckNull.isEmpty(String.valueOf(filter.getP100t28()))){
		 * flfields.add(new FilterField(getCond(flfields)+ "p100t28=?",
		 * filter.getP100t28())); }
		 */

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(CommissiongnkFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Commissiongnk ");
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
	
	public static List<Commissiongnk> getCommissiongnksFl(int pageIndex, int pageSize, CommissiongnkFilter filter)
	{
		
		List<Commissiongnk> list = new ArrayList<Commissiongnk>();
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
		sql.append(" order by 1");
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
				list.add(new Commissiongnk(
												rs.getLong("id"),
												rs.getString("p1t28"),
												rs.getString("p0t28"),
												rs.getDate("p2t28"),
												rs.getDouble("p3t28"),
												rs.getString("p4t28"),
												rs.getDouble("p5t28"),
												rs.getString("p6t28"),
												rs.getString("p7t28"),
												rs.getDate("p8t28"),
												rs.getString("p9t28"),
												rs.getString("p10t28"),
												rs.getDate("p13t28"),
												rs.getShort("p100t28")
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
	
	public Commissiongnk getCommissiongnk(int commissiongnkId)
	{
		
		Commissiongnk commissiongnk = new Commissiongnk();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_commissiongnk WHERE id=?");
			ps.setInt(1, commissiongnkId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				commissiongnk = new Commissiongnk();
				
				commissiongnk.setId(rs.getLong("id"));
				commissiongnk.setP1t28(rs.getString("p1t28"));
				commissiongnk.setP0t28(rs.getString("p0t28"));
				commissiongnk.setP2t28(rs.getDate("p2t28"));
				commissiongnk.setP3t28(rs.getDouble("p3t28"));
				commissiongnk.setP4t28(rs.getString("p4t28"));
				commissiongnk.setP5t28(rs.getDouble("p5t28"));
				commissiongnk.setP6t28(rs.getString("p6t28"));
				commissiongnk.setP7t28(rs.getString("p7t28"));
				commissiongnk.setP8t28(rs.getDate("p8t28"));
				commissiongnk.setP9t28(rs.getString("p9t28"));
				commissiongnk.setP10t28(rs.getString("p10t28"));
				commissiongnk.setP13t28(rs.getDate("p13t28"));
				commissiongnk.setP100t28(rs.getShort("p100t28"));
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
		return commissiongnk;
	}
	
	public static Commissiongnk create(Commissiongnk commissiongnk)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_commissiongnk.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				commissiongnk.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_commissiongnk (id, p1t28, p0t28, p2t28, p3t28, p4t28, p5t28, p6t28, p7t28, p8t28, p9t28, p10t28, p13t28, p100t28 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, commissiongnk.getId());
			ps.setString(2, commissiongnk.getP1t28());
			ps.setString(3, commissiongnk.getP0t28());
			ps.setDate(4, new java.sql.Date(commissiongnk.getP2t28().getTime()));
			ps.setDouble(5, commissiongnk.getP3t28());
			ps.setString(6, commissiongnk.getP4t28());
			ps.setDouble(7, commissiongnk.getP5t28());
			ps.setString(8, commissiongnk.getP6t28());
			ps.setString(9, commissiongnk.getP7t28());
			ps.setDate(10, new java.sql.Date(commissiongnk.getP8t28().getTime()));
			ps.setString(11, commissiongnk.getP9t28());
			ps.setString(12, commissiongnk.getP10t28());
			ps.setDate(13, new java.sql.Date(commissiongnk.getP13t28().getTime()));
			ps.setShort(14, commissiongnk.getP100t28());
			
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
		return commissiongnk;
	}
	
	public static void update(Commissiongnk commissiongnk)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_commissiongnk SET id=?, p1t28=?, p0t28=?, p2t28=?, p3t28=?, p4t28=?, p5t28=?, p6t28=?, p7t28=?, p8t28=?, p9t28=?, p10t28=?, p13t28=?, p100t28=?  WHERE id=?");
			
			ps.setLong(1, commissiongnk.getId());
			ps.setString(2, commissiongnk.getP1t28());
			ps.setString(3, commissiongnk.getP0t28());
			ps.setDate(4, new java.sql.Date(commissiongnk.getP2t28().getTime()));
			ps.setDouble(5, commissiongnk.getP3t28());
			ps.setString(6, commissiongnk.getP4t28());
			ps.setDouble(7, commissiongnk.getP5t28());
			ps.setString(8, commissiongnk.getP6t28());
			ps.setString(9, commissiongnk.getP7t28());
			ps.setDate(10, new java.sql.Date(commissiongnk.getP8t28().getTime()));
			ps.setString(11, commissiongnk.getP9t28());
			ps.setString(12, commissiongnk.getP10t28());
			ps.setDate(13, new java.sql.Date(commissiongnk.getP13t28().getTime()));
			ps.setShort(14, commissiongnk.getP100t28());
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
	
	public static void remove(Commissiongnk commissiongnk, Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_commissiongnk t WHERE t.id_contract=?");
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
	
	public static com.sbs.service.CommissionGNK create(com.sbs.service.CommissionGNK commissiongnk, String P1t28, Long id_contract)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_commissiongnk.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_commissiongnk (id, p1t28, p0t28, p2t28, p3t28, p4t28, p5t28, p6t28, p7t28, p8t28, p9t28, id_contract, p10t28, p13t28, p100t28 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, aid);
			ps.setString(2, P1t28);
			ps.setInt(3, commissiongnk.getP0T28() != null ? commissiongnk.getP0T28() : 0);
			ps.setDate(4, new java.sql.Date(commissiongnk.getP2T28().getTimeInMillis()));
			ps.setDouble(5, commissiongnk.getP3T28());
			ps.setString(6, commissiongnk.getP4T28());
			ps.setDouble(7, commissiongnk.getP5T28());
			ps.setString(8, commissiongnk.getP6T28());
			ps.setString(9, commissiongnk.getP7T28());
			ps.setDate(10, new java.sql.Date(commissiongnk.getP8T28().getTimeInMillis()));
			ps.setInt(11, commissiongnk.getP9T28() != null ? commissiongnk.getP9T28() : 0);
			ps.setLong(12, id_contract);
			ps.setString(13, commissiongnk.getP10T28());
			ps.setDate(14, new java.sql.Date(commissiongnk.getP13T28().getTimeInMillis()));
			ps.setShort(15, commissiongnk.getP100T28());
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
		return commissiongnk;
	}
}
