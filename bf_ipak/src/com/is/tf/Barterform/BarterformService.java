package com.is.tf.Barterform;

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

public class BarterformService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Barterform ";
	
	public List<Barterform> getBarterform()
	{
		
		List<Barterform> list = new ArrayList<Barterform>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Barterform");
			while (rs.next())
			{
				list.add(new Barterform(
						rs.getLong("id"),
						rs.getString("p0t17"),
						rs.getString("p1t17"),
						rs.getDate("p2t17"),
						rs.getString("p3t17"),
						rs.getString("p4t17"),
						rs.getString("p5t17"),
						rs.getString("p8t17"),
						rs.getDate("p9t17"),
						rs.getShort("p100t17")
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
	
	private static List<FilterField> getFilterFields(BarterformFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t17=?", filter.getP0t17()));
		}
		if (!CheckNull.isEmpty(filter.getP1t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t17=?", filter.getP1t17()));
		}
		if (!CheckNull.isEmpty(filter.getP2t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t17=?", filter.getP2t17()));
		}
		if (!CheckNull.isEmpty(filter.getP3t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t17=?", filter.getP3t17()));
		}
		if (!CheckNull.isEmpty(filter.getP4t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t17=?", filter.getP4t17()));
		}
		if (!CheckNull.isEmpty(filter.getP5t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t17=?", filter.getP5t17()));
		}
		if (!CheckNull.isEmpty(filter.getP8t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t17=?", filter.getP8t17()));
		}
		if (!CheckNull.isEmpty(filter.getP9t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t17=?", new java.sql.Date(filter.getP9t17().getTime())));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		
		/*System.out.println("filter.getP100t17()->"+filter.getP100t17());
		
		if (!CheckNull.isEmpty(filter.getP100t17()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t17=?", String.valueOf(filter.getP100t17())));
		}*/
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(BarterformFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Barterform ");
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
	
	public static List<Barterform> getBarterformsFl(int pageIndex, int pageSize, BarterformFilter filter)
	{
		
		List<Barterform> list = new ArrayList<Barterform>();
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
				list.add(new Barterform(
							rs.getLong("id"),
							rs.getString("p0t17"),
							rs.getString("p1t17"),
							rs.getDate("p2t17"),
							rs.getString("p3t17"),
							rs.getString("p4t17"),
							rs.getString("p5t17"),
							rs.getString("p8t17"),
							rs.getDate("p9t17"),
							rs.getShort("p100t17")
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
	
	public Barterform getBarterform(int barterformId)
	{
		
		Barterform barterform = new Barterform();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_barterform WHERE id=?");
			ps.setInt(1, barterformId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				barterform = new Barterform();
				
				barterform.setId(rs.getLong("id"));
				barterform.setP0t17(rs.getString("p0t17"));
				barterform.setP1t17(rs.getString("p1t17"));
				barterform.setP2t17(rs.getDate("p2t17"));
				barterform.setP3t17(rs.getString("p3t17"));
				barterform.setP4t17(rs.getString("p4t17"));
				barterform.setP5t17(rs.getString("p5t17"));
				barterform.setP4t17(String.valueOf(rs.getDate("p9t17")));
				barterform.setP5t17(String.valueOf(rs.getInt("p100t17")));
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
		return barterform;
	}
	
	public static Barterform create(Barterform barterform)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_barterform.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				barterform.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_barterform (id, p0t17, p1t17, p2t17, p3t17, p4t17, p5t17 ) VALUES (?,?,?,?,?,?,?)");
			
			ps.setLong(1, barterform.getId());
			ps.setString(2, barterform.getP0t17());
			ps.setString(3, barterform.getP1t17());
			ps.setDate(4, new java.sql.Date(barterform.getP2t17().getTime()));
			ps.setString(5, barterform.getP3t17());
			ps.setString(6, barterform.getP4t17());
			ps.setString(7, barterform.getP5t17());
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
		return barterform;
	}
	
	public static void update(Barterform barterform)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE barterform SET id=?, p0t17=?, p1t17=?, p2t17=?, p3t17=?, p4t17=?, p5t17=?,  WHERE barterform_id=?");
			
			ps.setLong(1, barterform.getId());
			ps.setString(2, barterform.getP0t17());
			ps.setString(3, barterform.getP1t17());
			ps.setDate(4, new java.sql.Date(barterform.getP2t17().getTime()));
			ps.setString(5, barterform.getP3t17());
			ps.setString(6, barterform.getP4t17());
			ps.setString(7, barterform.getP5t17());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_barterform WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.BarterForm barterform, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_BarterForm.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			int i = 0;
			ps = c.prepareStatement("INSERT INTO TF_barterform (id, p1t17, p2t17, p3t17, p4t17, p5t17" +
					", id_contract, p8t17, p9t17, p100t17  )" +
					" VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, barterform.getP1T17());
			ps.setDate(++i, barterform.getP2T17()==null?null:new java.sql.Date(barterform.getP2T17().getTimeInMillis()));
			ps.setString(++i, String.valueOf(barterform.getP3T17()));
			ps.setString(++i, String.valueOf(barterform.getP4T17()));
			ps.setString(++i, String.valueOf(barterform.getP5T17()));
			ps.setLong(++i, id_contract);
			ps.setString(++i, barterform.getP8T17());
			ps.setDate(++i, barterform.getP9T17()==null?null:new java.sql.Date(barterform.getP9T17().getTimeInMillis()));
			ps.setString(++i, String.valueOf(barterform.getP100T17()));
			
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
