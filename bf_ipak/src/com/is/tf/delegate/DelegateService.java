package com.is.tf.delegate;

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

public class DelegateService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Delegate ";
	
	public List<Delegate> getDelegate()
	{
		
		List<Delegate> list = new ArrayList<Delegate>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Delegate");
			while (rs.next())
			{
				list.add(new Delegate(
											rs.getLong("id"),
											rs.getString("p0t30"),
											rs.getString("p1t30"),
											rs.getString("p2t30"),
											rs.getString("p3t30"),
											rs.getString("p4t30"),
											rs.getDate("p5t30"),
											rs.getString("p6t30"),
											rs.getDate("p7t30"),
											rs.getString("p8t30"),
											rs.getString("p9t30"),
											rs.getString("p10t30"),
											rs.getString("p11t30"),
											rs.getString("p12t30"),
											rs.getString("p13t30"),
											rs.getString("p14t30"),
											rs.getString("p15t30"),
											rs.getString("p16t30"),
											rs.getString("p17t30"),
											rs.getString("p18t30"),
											rs.getString("p19t30"),
											rs.getString("p20t30")));
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
	
	private static List<FilterField> getFilterFields(DelegateFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t30=?", filter.getP0t30()));
		}
		if (!CheckNull.isEmpty(filter.getP1t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t30=?", filter.getP1t30()));
		}
		if (!CheckNull.isEmpty(filter.getP2t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t30=?", filter.getP2t30()));
		}
		if (!CheckNull.isEmpty(filter.getP3t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t30=?", filter.getP3t30()));
		}
		if (!CheckNull.isEmpty(filter.getP4t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t30=?", filter.getP4t30()));
		}
		if (!CheckNull.isEmpty(filter.getP5t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t30=?", filter.getP5t30()));
		}
		if (!CheckNull.isEmpty(filter.getP6t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t30=?", filter.getP6t30()));
		}
		if (!CheckNull.isEmpty(filter.getP7t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t30=?", filter.getP7t30()));
		}
		if (!CheckNull.isEmpty(filter.getP8t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t30=?", filter.getP8t30()));
		}
		if (!CheckNull.isEmpty(filter.getP9t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t30=?", filter.getP9t30()));
		}
		if (!CheckNull.isEmpty(filter.getP10t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t30=?", filter.getP10t30()));
		}
		if (!CheckNull.isEmpty(filter.getP11t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t30=?", filter.getP11t30()));
		}
		if (!CheckNull.isEmpty(filter.getP12t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t30=?", filter.getP12t30()));
		}
		if (!CheckNull.isEmpty(filter.getP13t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t30=?", filter.getP13t30()));
		}
		if (!CheckNull.isEmpty(filter.getP14t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t30=?", filter.getP14t30()));
		}
		if (!CheckNull.isEmpty(filter.getP15t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t30=?", filter.getP15t30()));
		}
		if (!CheckNull.isEmpty(filter.getP16t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t30=?", filter.getP16t30()));
		}
		if (!CheckNull.isEmpty(filter.getP17t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p17t30=?", filter.getP17t30()));
		}
		if (!CheckNull.isEmpty(filter.getP18t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p18t30=?", filter.getP18t30()));
		}
		if (!CheckNull.isEmpty(filter.getP19t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p19t30=?", filter.getP19t30()));
		}
		if (!CheckNull.isEmpty(filter.getP20t30()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p20t30=?", filter.getP20t30()));
		} 
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(DelegateFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Delegate ");
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
	
	public static List<Delegate> getDelegatesFl(int pageIndex, int pageSize, DelegateFilter filter)
	{
		
		List<Delegate> list = new ArrayList<Delegate>();
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
				list.add(new Delegate(
											rs.getLong("id"),
											rs.getString("p0t30"),
											rs.getString("p1t30"),
											rs.getString("p2t30"),
											rs.getString("p3t30"),
											rs.getString("p4t30"),
											rs.getDate("p5t30"),
											rs.getString("p6t30"),
											rs.getDate("p7t30"),
											rs.getString("p8t30"),
											rs.getString("p9t30"),
											rs.getString("p10t30"),
											rs.getString("p11t30"),
											rs.getString("p12t30"),
											rs.getString("p13t30"),
											rs.getString("p14t30"),
											rs.getString("p15t30"),
											rs.getString("p16t30"),
											rs.getString("p17t30"),
											rs.getString("p18t30"),
											rs.getString("p19t30"),
											rs.getString("p20t30")));
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
	
	public Delegate getDelegate(int delegateId)
	{
		
		Delegate delegate = new Delegate();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_delegate WHERE id=?");
			ps.setInt(1, delegateId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				delegate = new Delegate();
				
				delegate.setId(rs.getLong("id"));
				delegate.setP1t30(rs.getString("p0t30"));
				delegate.setP0t30(rs.getString("p1t30"));
				delegate.setP2t30(rs.getString("p2t30"));
				delegate.setP3t30(rs.getString("p3t30"));
				delegate.setP4t30(rs.getString("p4t30"));
				delegate.setP5t30(rs.getDate("p5t30"));
				delegate.setP6t30(rs.getString("p6t30"));
				delegate.setP7t30(rs.getDate("p7t30"));
				delegate.setP8t30(rs.getString("p8t30"));
				delegate.setP9t30(rs.getString("p9t30"));
				delegate.setP10t30(rs.getString("p10t30"));
				delegate.setP11t30(rs.getString("p11t30"));
				delegate.setP12t30(rs.getString("p12t30"));
				delegate.setP13t30(rs.getString("p13t30"));
				delegate.setP14t30(rs.getString("p14t30"));
				delegate.setP15t30(rs.getString("p15t30"));
				delegate.setP16t30(rs.getString("p16t30"));
				delegate.setP17t30(rs.getString("p17t30"));
				delegate.setP18t30(rs.getString("p18t30"));
				delegate.setP19t30(rs.getString("p19t30"));
				delegate.setP20t30(rs.getString("p20t30"));
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
		return delegate;
	}
	
	public static Delegate create(Delegate delegate)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_TF_delegate.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				delegate.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_delegate (id, p0t30, p1t30, p2t30, p3t30, p4t30, p5t30, p6t30, p7t30, p8t30, p9t30, p10t30, p11t30, p12t30, p13t30, p14t30, p15t30, p16t30, p17t30, p18t30, p19t30, p20t30 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, delegate.getId());
			ps.setString(2, delegate.getP0t30());
			ps.setString(3, delegate.getP1t30());
			ps.setString(4, delegate.getP2t30());
			ps.setString(5, delegate.getP3t30());
			ps.setString(6, delegate.getP4t30());
			ps.setDate(7, new java.sql.Date(delegate.getP5t30().getTime()));
			ps.setString(8, delegate.getP6t30());
			ps.setDate(9, new java.sql.Date(delegate.getP7t30().getTime()));
			ps.setString(10, delegate.getP8t30());
			ps.setString(11, delegate.getP9t30());
			ps.setString(12, delegate.getP10t30());
			ps.setString(13, delegate.getP11t30());
			ps.setString(14, delegate.getP12t30());
			ps.setString(15, delegate.getP13t30());
			ps.setString(16, delegate.getP14t30());
			ps.setString(17, delegate.getP15t30());
			ps.setString(18, delegate.getP16t30());
			ps.setString(19, delegate.getP17t30());
			ps.setString(20, delegate.getP18t30());
			ps.setString(21, delegate.getP19t30());
			ps.setString(22, delegate.getP20t30());
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
		return delegate;
	}
	
	public static void update(Delegate delegate)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_delegate SET id=?, p1t30=?, p0t30=?, p2t30=?, p3t30=?, p4t30=?, p5t30=?, p6t30=?, p7t30=?, p8t30=?, p9t30=?, p10t30=?, p11t30=?, p12t30=?, p13t30=?, p14t30=?, p15t30=?, p16t30=?, p17t30=?, p18t30=?, p19t30=?, p20t30=?,  WHERE id=?");
			
			ps.setLong(1, delegate.getId());
			ps.setString(2, delegate.getP0t30());
			ps.setString(3, delegate.getP1t30());
			ps.setString(4, delegate.getP2t30());
			ps.setString(5, delegate.getP3t30());
			ps.setString(6, delegate.getP4t30());
			ps.setDate(7, new java.sql.Date(delegate.getP5t30().getTime()));
			ps.setString(8, delegate.getP6t30());
			ps.setDate(9, new java.sql.Date(delegate.getP7t30().getTime()));
			ps.setString(10, delegate.getP8t30());
			ps.setString(11, delegate.getP9t30());
			ps.setString(12, delegate.getP10t30());
			ps.setString(13, delegate.getP11t30());
			ps.setString(14, delegate.getP12t30());
			ps.setString(15, delegate.getP13t30());
			ps.setString(16, delegate.getP14t30());
			ps.setString(17, delegate.getP15t30());
			ps.setString(18, delegate.getP16t30());
			ps.setString(19, delegate.getP17t30());
			ps.setString(20, delegate.getP18t30());
			ps.setString(21, delegate.getP19t30());
			ps.setString(22, delegate.getP20t30());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_delegate WHERE id_contract=?");
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
	
	 public static Res create(com.sbs.service.Delegate delegate, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_delegate.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_delegate (id, p0t30, p2t30, p3t30, p4t30, p5t30, " +
					"p6t30, p7t30, p8t30, p9t30, p10t30, p12t30, p13t30, p14t30, p15t30, p16t30, " +
					"p17t30, p18t30, p19t30, p20t30, id_contract ) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setInt(++i, delegate.getP0T30() != null ? delegate.getP0T30() : 0);
			ps.setInt(++i, delegate.getP2T30() != null ? delegate.getP2T30() : 0);
			ps.setString(++i, delegate.getP3T30());
			ps.setString(++i, delegate.getP4T30());
			ps.setDate(++i, new java.sql.Date(delegate.getP5T30().getTimeInMillis()));
			ps.setString(++i, delegate.getP6T30());
			ps.setDate(++i, new java.sql.Date(delegate.getP7T30().getTimeInMillis()));
			ps.setString(++i, delegate.getP8T30());
			ps.setString(++i, delegate.getP9T30());
			ps.setString(++i, delegate.getP10T30());
			ps.setString(++i, delegate.getP12T30());
			ps.setString(++i, delegate.getP13T30());
			ps.setString(++i, delegate.getP14T30());
			ps.setString(++i, delegate.getP15T30());
			ps.setInt(++i, delegate.getP16T30());
			ps.setString(++i, delegate.getP17T30());
			ps.setString(++i, delegate.getP18T30());
			ps.setString(++i, delegate.getP19T30());
			ps.setInt(++i, delegate.getP20T30());
			ps.setLong(++i, id_contract);
			
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
