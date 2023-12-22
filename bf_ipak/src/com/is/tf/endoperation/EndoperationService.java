package com.is.tf.endoperation;

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

public class EndoperationService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Endoperation ";
	
	public List<Endoperation> getEndoperation()
	{
		
		List<Endoperation> list = new ArrayList<Endoperation>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Endoperation");
			while (rs.next())
			{
				list.add(new Endoperation(
						rs.getLong("id"),
						rs.getString("p0t12"),
						rs.getDate("p1t12"),
						rs.getString("p2t12"),
						rs.getDate("p3t12"),
						rs.getLong("id_contract"),
						rs.getShort("p100t12")
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
	
	private static List<FilterField> getFilterFields(EndoperationFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t12()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t12=?", filter.getP0t12()));
		}
		if (!CheckNull.isEmpty(filter.getP1t12()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t12=?", filter.getP1t12()));
		}
		if (!CheckNull.isEmpty(filter.getP2t12()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t12=?", filter.getP2t12()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(EndoperationFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM Endoperation ");
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
	
	public static List<Endoperation> getEndoperationsFl(int pageIndex, int pageSize, EndoperationFilter filter)
	{
		
		List<Endoperation> list = new ArrayList<Endoperation>();
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
				list.add(new Endoperation(
						rs.getLong("id"),
						rs.getString("p0t12"),
						rs.getDate("p1t12"),
						rs.getString("p2t12"),
						rs.getDate("p3t12"),
						rs.getLong("id_contract"),
						rs.getShort("p100t12")
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
	
	public Endoperation getEndoperation(int endoperationId)
	{
		
		Endoperation endoperation = new Endoperation();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_endoperation WHERE id=?");
			ps.setInt(1, endoperationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				endoperation = new Endoperation();
				
				endoperation.setId(rs.getLong("id"));
				endoperation.setP0t12(rs.getString("p0t12"));
				endoperation.setP1t12(rs.getDate("p1t12"));
				endoperation.setP2t12(rs.getString("p2t12"));
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
		return endoperation;
	}
	
	public static Endoperation create(Endoperation endoperation)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_endoperation.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				endoperation.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_endoperation (id, p0t12, p1t12, p2t12 ) VALUES (?,?,?,?)");
			
			ps.setLong(1, endoperation.getId());
			ps.setString(2, endoperation.getP0t12());
			ps.setDate(3, new java.sql.Date(endoperation.getP1t12().getTime()));
			ps.setString(4, endoperation.getP2t12());
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
		return endoperation;
	}
	
	public static void update(Endoperation endoperation)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_endoperation SET id=?, p0t12=?, p1t12=?, p2t12=?,  WHERE id=?");
			
			ps.setLong(1, endoperation.getId());
			ps.setString(2, endoperation.getP0t12());
			ps.setDate(3, new java.sql.Date(endoperation.getP1t12().getTime()));
			ps.setString(4, endoperation.getP2t12());
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
	
	public static void remove(long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_endoperation WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.EndOperation endoperation, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_endoperation.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_endoperation (id, p1t12, p2t12, p3t12, id_contract, p100t12 ) " +
					"VALUES (?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setDate(++i, endoperation.getP1T12() == null ? null : new java.sql.Date(endoperation.getP1T12().getTimeInMillis()));
			ps.setString(++i, endoperation.getP2T12());
			ps.setDate(++i, endoperation.getP3T12() == null ? null : new java.sql.Date(endoperation.getP3T12().getTimeInMillis()));
			ps.setLong(++i, id_contract);
			ps.setString(++i, endoperation.getP100T12()+"");
			
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
