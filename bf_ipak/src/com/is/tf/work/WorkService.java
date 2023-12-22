package com.is.tf.work;

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

public class WorkService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Work ";
	
	public List<Work> getWork()
	{
		
		List<Work> list = new ArrayList<Work>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(msql);
			while (rs.next())
			{
				list.add(new Work(
							rs.getLong("id"),
							rs.getString("p0t6"),
							rs.getString("p1t6"),
							rs.getString("p2t6"),
							rs.getString("p3t6"),
							rs.getString("p4t6"),
							rs.getString("p5t6"),
							rs.getString("p6t6"),
							rs.getString("p7t6"),
							rs.getString("p8t6"),
							rs.getString("p9t6"),
							rs.getShort("p10t6"),
							rs.getDate("p99t6"),
							rs.getShort("p100t6")
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
	
	private static List<FilterField> getFilterFields(WorkFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t6=?", filter.getP0t6()));
		}
		if (!CheckNull.isEmpty(filter.getP1t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t6=?", filter.getP1t6()));
		}
		if (!CheckNull.isEmpty(filter.getP2t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t6=?", filter.getP2t6()));
		}
		if (!CheckNull.isEmpty(filter.getP3t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t6=?", filter.getP3t6()));
		}
		if (!CheckNull.isEmpty(filter.getP4t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t6=?", filter.getP4t6()));
		}
		if (!CheckNull.isEmpty(filter.getP5t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t6=?", filter.getP5t6()));
		}
		if (!CheckNull.isEmpty(filter.getP6t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t6=?", filter.getP6t6()));
		}
		if (!CheckNull.isEmpty(filter.getP7t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t6=?", filter.getP7t6()));
		}
		if (!CheckNull.isEmpty(filter.getP8t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t6=?", filter.getP8t6()));
		}
		if (!CheckNull.isEmpty(filter.getP9t6()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t6=?", filter.getP9t6()));
		}
		if (!CheckNull.isEmpty(filter.getId_contact()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contact()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(WorkFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Work ");
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
	
	public static List<Work> getWorksFl(int pageIndex, int pageSize, WorkFilter filter)
	{
		
		List<Work> list = new ArrayList<Work>();
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
				list.add(new Work(
						rs.getLong("id"),
						rs.getString("p0t6"),
						rs.getString("p1t6"),
						rs.getString("p2t6"),
						rs.getString("p3t6"),
						rs.getString("p4t6"),
						rs.getString("p5t6"),
						rs.getString("p6t6"),
						rs.getString("p7t6"),
						rs.getString("p8t6"),
						rs.getString("p9t6"),
						rs.getShort("p10t6"),
						rs.getDate("p99t6"),
						rs.getShort("p100t6")
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
	
	public Work getWork(int workId)
	{
		
		Work work = new Work();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(msql + " WHERE id=?");
			ps.setInt(1, workId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				work = new Work();
				
				work.setId(rs.getLong("id"));
				work.setP0t6(rs.getString("p0t6"));
				work.setP1t6(rs.getString("p1t6"));
				work.setP2t6(rs.getString("p2t6"));
				work.setP3t6(rs.getString("p3t6"));
				work.setP4t6(rs.getString("p4t6"));
				work.setP5t6(rs.getString("p5t6"));
				work.setP6t6(rs.getString("p6t6"));
				work.setP7t6(rs.getString("p7t6"));
				work.setP8t6(rs.getString("p8t6"));
				work.setP9t6(rs.getString("p9t6"));
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
		return work;
	}
	
	public static Work create(Work work)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_work.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				work.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_work (id, p0t6, p1t6, p2t6, p3t6, p4t6, p5t6, p6t6, p7t6, p8t6, p9t6, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");
			
			ps.setLong(1, work.getId());
			ps.setString(2, work.getP0t6());
			ps.setString(3, work.getP1t6());
			ps.setString(4, work.getP2t6());
			ps.setString(5, work.getP3t6());
			ps.setString(6, work.getP4t6());
			ps.setString(7, work.getP5t6());
			ps.setString(8, work.getP6t6());
			ps.setString(9, work.getP7t6());
			ps.setString(10, work.getP8t6());
			ps.setString(11, work.getP9t6());
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
		return work;
	}
	
	public static Res create(com.sbs.service.Work work, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_work.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_work " +
					"(id, p2t6, p3t6, p4t6, p5t6, p6t6, p7t6, p8t6, id_contract, p10t6, p99t6, p100t6 ) VALUES " +
					"(?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, work.getP2T6());
			ps.setString(++i, work.getP3T6());
			ps.setString(++i, work.getP4T6());
			ps.setString(++i, work.getP5T6());
			ps.setString(++i, work.getP6T6());
			ps.setString(++i, work.getP7T6());
			
			ps.setDouble(++i, work.getP8T6());
			
			ps.setLong(++i, id_contract);
			ps.setString(++i, work.getP10T6() + "");
			ps.setDate(++i, work.getP99T6() == null ? null : new java.sql.Date(work.getP99T6().getTimeInMillis()));
			ps.setString(++i, work.getP100T6() + "");
			
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
	
	public static void update(Work work)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_work SET id=?, p0t6=?, p1t6=?, p2t6=?, p3t6=?, p4t6=?, p5t6=?, p6t6=?, p7t6=?, p8t6=?, p9t6=?,  WHERE id=?");
			
			ps.setLong(1, work.getId());
			ps.setString(2, work.getP0t6());
			ps.setString(3, work.getP1t6());
			ps.setString(4, work.getP2t6());
			ps.setString(5, work.getP3t6());
			ps.setString(6, work.getP4t6());
			ps.setString(7, work.getP5t6());
			ps.setString(8, work.getP6t6());
			ps.setString(9, work.getP7t6());
			ps.setString(10, work.getP8t6());
			ps.setString(11, work.getP9t6());
			
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_work WHERE id_contract=?");
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
