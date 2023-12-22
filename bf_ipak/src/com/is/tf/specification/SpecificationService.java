package com.is.tf.specification;

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

public class SpecificationService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Specification ";
	
	public List<Specification> getSpecification()
	{
		
		List<Specification> list = new ArrayList<Specification>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Specification");
			while (rs.next())
			{
				list.add(new Specification(
						rs.getLong("id"),
						rs.getString("p0t3"),
						rs.getString("p1t3"),
						rs.getString("p2t3"),
						rs.getDate("p3t3"),
						rs.getDouble("p4t3"),
						rs.getString("p5t3"),
						rs.getString("p6t3"),
						rs.getDate("p7t3"),
						rs.getString("p8t3"),
						rs.getString("p11t3"),
						rs.getShort("p12t3"),
						rs.getInt("p13t3"),
						rs.getDate("p14t3"),
						rs.getShort("p100t3")
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
	
	private static List<FilterField> getFilterFields(SpecificationFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t3=?", filter.getP0t3()));
		}
		if (!CheckNull.isEmpty(filter.getP1t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t3=?", filter.getP1t3()));
		}
		if (!CheckNull.isEmpty(filter.getP2t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t3=?", filter.getP2t3()));
		}
		if (!CheckNull.isEmpty(filter.getP3t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t3=?", filter.getP3t3()));
		}
		if (!CheckNull.isEmpty(filter.getP4t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t3=?", filter.getP4t3()));
		}
		if (!CheckNull.isEmpty(filter.getP5t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t3=?", filter.getP5t3()));
		}
		if (!CheckNull.isEmpty(filter.getP6t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t3=?", filter.getP6t3()));
		}
		if (!CheckNull.isEmpty(filter.getP7t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t3=?", filter.getP7t3()));
		}
		if (!CheckNull.isEmpty(filter.getP8t3()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t3=?", filter.getP8t3()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(SpecificationFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Specification ");
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
	
	public static List<Specification> getSpecificationsFl(int pageIndex, int pageSize, SpecificationFilter filter)
	{
		
		List<Specification> list = new ArrayList<Specification>();
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
				list.add(new Specification(
							rs.getLong("id"),
							rs.getString("p0t3"),
							rs.getString("p1t3"),
							rs.getString("p2t3"),
							rs.getDate("p3t3"),
							rs.getDouble("p4t3"),
							rs.getString("p5t3"),
							rs.getString("p6t3"),
							rs.getDate("p7t3"),
							rs.getString("p8t3"),
							rs.getString("p11t3"),
							rs.getShort("p12t3"),
							rs.getInt("p13t3"),
							rs.getDate("p14t3"),
							rs.getShort("p100t3")
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
	
	public Specification getSpecification(int specificationId)
	{
		
		Specification specification = new Specification();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_specification WHERE id=?");
			ps.setInt(1, specificationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				specification = new Specification();
				
				specification.setId(rs.getLong("id"));
				specification.setP0t3(rs.getString("p0t3"));
				specification.setP1t3(rs.getString("p1t3"));
				specification.setP2t3(rs.getString("p2t3"));
				specification.setP3t3(rs.getDate("p3t3"));
				specification.setP4t3(rs.getDouble("p4t3"));
				specification.setP5t3(rs.getString("p5t3"));
				specification.setP6t3(rs.getString("p6t3"));
				specification.setP7t3(rs.getDate("p7t3"));
				specification.setP8t3(rs.getString("p8t3"));
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
		return specification;
	}
	
	public static Specification create(Specification specification)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_specification.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				specification.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_specification (id, p0t3, p1t3, p2t3, p3t3, p4t3, p5t3, p6t3, p7t3, p8t3 ) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, specification.getId());
			ps.setString(2, specification.getP0t3());
			ps.setString(3, specification.getP1t3());
			ps.setString(4, specification.getP2t3());
			ps.setDate(5, new java.sql.Date(specification.getP3t3().getTime()));
			ps.setDouble(6, specification.getP4t3());
			ps.setString(7, specification.getP5t3());
			ps.setString(8, specification.getP6t3());
			ps.setDate(9, new java.sql.Date(specification.getP7t3().getTime()));
			ps.setString(10, specification.getP8t3());
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
		return specification;
	}
	
	public static void update(Specification specification)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_specification SET id=?, p0t3=?, p1t3=?, p2t3=?, p3t3=?, p4t3=?, p5t3=?, p6t3=?, p7t3=?, p8t3=?,  WHERE id=?");
			
			ps.setLong(1, specification.getId());
			ps.setString(2, specification.getP0t3());
			ps.setString(3, specification.getP1t3());
			ps.setString(4, specification.getP2t3());
			ps.setDate(5, new java.sql.Date(specification.getP3t3().getTime()));
			ps.setDouble(6, specification.getP4t3());
			ps.setString(7, specification.getP5t3());
			ps.setString(8, specification.getP6t3());
			ps.setDate(9, new java.sql.Date(specification.getP7t3().getTime()));
			ps.setString(10, specification.getP8t3());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_specification WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Specification specification, String idn, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_specification.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_specification (id, p0t3, p1t3, p3t3, p4t3, p5t3" +
					", p6t3, p7t3, p8t3, id_contract, p11t3, p12t3, p13t3, p14t3, p100t3 " +
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, null);
			ps.setString(++i, idn);
			ps.setDate(++i, specification.getP3T3() != null ? new java.sql.Date(specification.getP3T3().getTimeInMillis()) : null);
			ps.setDouble(++i, specification.getP4T3());
			ps.setString(++i, specification.getP5T3());
			ps.setString(++i, specification.getP6T3());
			ps.setDate(++i, specification.getP7T3() != null ? new java.sql.Date(specification.getP7T3().getTimeInMillis()) : null);
			ps.setInt(++i, specification.getP8T3() != null ? specification.getP8T3() : 0);
			ps.setLong(++i, id_contract);
			ps.setString(++i, specification.getP11T3());
			ps.setString(++i, String.valueOf(specification.getP12T3()));
			ps.setString(++i, specification.getP13T3() + "");
			ps.setDate(++i, specification.getP14T3() == null ? null : new java.sql.Date(specification.getP14T3().getTimeInMillis()));
			ps.setString(++i, specification.getP100T3() + "");
			
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
