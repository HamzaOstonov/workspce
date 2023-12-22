package com.is.tf.calcform;

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

public class CalcformService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Calcform ";
	
	public List<Calcform> getCalcform()
	{
		
		List<Calcform> list = new ArrayList<Calcform>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Calcform");
			while (rs.next())
			{
				list.add(new Calcform(
						rs.getLong("id"),
						rs.getString("p0t16"),
						rs.getString("p1t16"),
						rs.getString("p2t16"),
						rs.getDate("p3t16"),
						rs.getString("p4t16"),
						rs.getDate("p5t16"),
						rs.getString("p6t16"),
						rs.getString("p10t16"),
						rs.getDate("p11t16"),
						rs.getShort("p100t16"),
						rs.getLong("id_contract")
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
	
	private static List<FilterField> getFilterFields(CalcformFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t16=?", filter.getP0t16()));
		}
		if (!CheckNull.isEmpty(filter.getP1t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t16=?", filter.getP1t16()));
		}
		if (!CheckNull.isEmpty(filter.getP2t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t16=?", filter.getP2t16()));
		}
		if (!CheckNull.isEmpty(filter.getP3t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t16=?", filter.getP3t16()));
		}
		if (!CheckNull.isEmpty(filter.getP4t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t16=?", filter.getP4t16()));
		}
		if (!CheckNull.isEmpty(filter.getP5t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t16=?", filter.getP5t16()));
		}
		if (!CheckNull.isEmpty(filter.getP6t16()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t16=?", filter.getP6t16()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(CalcformFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Calcform ");
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
	
	public static List<Calcform> getCalcformsFl(int pageIndex, int pageSize, CalcformFilter filter)
	{
		
		List<Calcform> list = new ArrayList<Calcform>();
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
				list.add(new Calcform(
						rs.getLong("id"),
						rs.getString("p0t16"),
						rs.getString("p1t16"),
						rs.getString("p2t16"),
						rs.getDate("p3t16"),
						rs.getString("p4t16"),
						rs.getDate("p5t16"),
						rs.getString("p6t16"),
						rs.getString("p10t16"),
						rs.getDate("p11t16"),
						rs.getShort("p100t16"),
						rs.getLong("id_contract")
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
	
	public Calcform getCalcform(int calcformId)
	{
		
		Calcform calcform = new Calcform();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_calcform WHERE id=?");
			ps.setInt(1, calcformId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				calcform = new Calcform();
				
				calcform.setId(rs.getLong("id"));
				calcform.setP0t16(rs.getString("p0t16"));
				calcform.setP1t16(rs.getString("p1t16"));
				calcform.setP2t16(rs.getString("p2t16"));
				calcform.setP3t16(rs.getDate("p3t16"));
				calcform.setP4t16(rs.getString("p4t16"));
				calcform.setP5t16(rs.getDate("p5t16"));
				calcform.setP6t16(rs.getString("p6t16"));
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
		return calcform;
	}
	
	public static Calcform create(Calcform calcform)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_CalcForm.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				calcform.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_calcform (id, p0t16, p1t16, p2t16, p3t16, p4t16, p5t16, p6t16 ) VALUES (?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, calcform.getId());
			ps.setString(2, calcform.getP0t16());
			ps.setString(3, calcform.getP1t16());
			ps.setString(4, calcform.getP2t16());
			ps.setDate(5, new java.sql.Date(calcform.getP3t16().getTime()));
			ps.setString(6, calcform.getP4t16());
			ps.setDate(7, new java.sql.Date(calcform.getP5t16().getTime()));
			ps.setString(8, calcform.getP6t16());
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
		return calcform;
	}
	
	public static void update(Calcform calcform)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_calcform SET id=?, p0t16=?, p1t16=?, p2t16=?, p3t16=?, p4t16=?, p5t16=?, p6t16=?,  WHERE id=?");
			
			ps.setLong(1, calcform.getId());
			ps.setString(2, calcform.getP0t16());
			ps.setString(3, calcform.getP1t16());
			ps.setString(4, calcform.getP2t16());
			ps.setDate(5, new java.sql.Date(calcform.getP3t16().getTime()));
			ps.setString(6, calcform.getP4t16());
			ps.setDate(7, new java.sql.Date(calcform.getP5t16().getTime()));
			ps.setString(8, calcform.getP6t16());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_calcform WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.CalcForm calcform, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_CalcForm.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_calcform (id, p1t16, p2t16, p3t16, p4t16, p5t16" +
					", p6t16, id_contract, p10t16, p11t16, p100t16 ) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, calcform.getP1T16());
			ps.setString(++i, String.valueOf(calcform.getP2T16()));
			ps.setDate(++i, calcform.getP3T16() == null ? null : new java.sql.Date(calcform.getP3T16().getTimeInMillis()));
			ps.setString(++i, String.valueOf(calcform.getP4T16()));
			ps.setDate(++i, calcform.getP5T16() == null ? null : new java.sql.Date(calcform.getP5T16().getTimeInMillis()));
			ps.setString(++i, String.valueOf(calcform.getP6T16()));
			ps.setLong(++i, id_contract);
			ps.setString(++i, calcform.getP10T16());
			ps.setDate(++i, calcform.getP5T16() == null ? null : new java.sql.Date(calcform.getP5T16().getTimeInMillis()));
			ps.setString(++i, String.valueOf(calcform.getP100T16()));
			
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
