package com.is.tf.Incoterms;

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

public class IncotermsService
{
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM tf_incoterms ";
	
	public List<Incoterms> getIncoterms()
	{
		
		List<Incoterms> list = new ArrayList<Incoterms>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tf_incoterms");
			while (rs.next())
			{
				list.add(new Incoterms(
						rs.getLong("id"),
						rs.getString("p0t9"),
						rs.getString("p1t9"),
						rs.getString("p2t9"),
						rs.getString("p3t9"),
						rs.getString("p7t9"),
						rs.getInt("p8t9"),
						rs.getDate("p9t9"),
						rs.getShort("p100t9")
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
	
	private static List<FilterField> getFilterFields(IncotermsFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t9()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t9=?", filter.getP0t9()));
		}
		if (!CheckNull.isEmpty(filter.getP1t9()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t9=?", filter.getP1t9()));
		}
		if (!CheckNull.isEmpty(filter.getP2t9()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t9=?", filter.getP2t9()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(IncotermsFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM tf_incoterms ");
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
	
	public static List<Incoterms> getIncotermssFl(int pageIndex, int pageSize, IncotermsFilter filter)
	{
		
		List<Incoterms> list = new ArrayList<Incoterms>();
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
				list.add(new Incoterms(
						rs.getLong("id"),
						rs.getString("p0t9"),
						rs.getString("p1t9"),
						rs.getString("p2t9"),
						rs.getString("p3t9"),
						rs.getString("p7t9"),
						rs.getInt("p8t9"),
						rs.getDate("p9t9"),
						rs.getShort("p100t9")
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
	
	public Incoterms getIncoterms(int incotermsId)
	{
		
		Incoterms incoterms = new Incoterms();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tf_incoterms WHERE id=?");
			ps.setInt(1, incotermsId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				incoterms = new Incoterms();
				
				incoterms.setId(rs.getLong("id"));
				incoterms.setP0t9(rs.getString("p0t9"));
				incoterms.setP1t9(rs.getString("p1t9"));
				incoterms.setP2t9(rs.getString("p2t9"));
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
		return incoterms;
	}
	
	public static Incoterms create(Incoterms incoterms)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_incoterms.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				incoterms.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO tf_incoterms (id, p0t9, p1t9, p2t9, ) VALUES (?,?,?,?,)");
			
			ps.setLong(1, incoterms.getId());
			ps.setString(2, incoterms.getP0t9());
			ps.setString(3, incoterms.getP1t9());
			ps.setString(4, incoterms.getP2t9());
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
		return incoterms;
	}
	
	public static Res create(com.sbs.service.Incoterms incoterms, Long id_contract) throws Exception
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_tf_incoterms.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO tf_incoterms (id, p2t9, id_contract, p3t9, p7t9, p8t9, p9t9, p100t9 " +
					" ) VALUES (?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, incoterms.getP2T9());
			
			ps.setLong(++i, id_contract);
			ps.setString(++i, incoterms.getP3T9());
			ps.setString(++i, incoterms.getP7T9());
			ps.setInt(++i, incoterms.getP8T9());
			ps.setDate(++i, incoterms.getP9T9()==null?null:new java.sql.Date(incoterms.getP9T9().getTimeInMillis()));
			ps.setString(++i, String.valueOf(incoterms.getP100T9()));
			
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
	
	public static void update(Incoterms incoterms)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_incoterms SET id=?, p0t9=?, p1t9=?, p2t9=?,  WHERE incoterms_id=?");
			
			ps.setLong(1, incoterms.getId());
			ps.setString(2, incoterms.getP0t9());
			ps.setString(3, incoterms.getP1t9());
			ps.setString(4, incoterms.getP2t9());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_incoterms WHERE id_contract=?");
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
