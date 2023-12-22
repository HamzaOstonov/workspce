package com.is.tf.transcost;

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

public class TranscostService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Transcost ";
	
	public List<Transcost> getTranscost()
	{
		
		List<Transcost> list = new ArrayList<Transcost>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Transcost");
			while (rs.next())
			{
				list.add(new Transcost(
											rs.getLong("id"),
											rs.getString("p0t11"),
											rs.getString("p1t11"),
											rs.getString("p2t11"),
											rs.getString("p3t11"),
											rs.getDouble("p4t11")));
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
	
	private static List<FilterField> getFilterFields(TranscostFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t11()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t11=?", filter.getP0t11()));
		}
		if (!CheckNull.isEmpty(filter.getP1t11()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t11=?", filter.getP1t11()));
		}
		if (!CheckNull.isEmpty(filter.getP2t11()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t11=?", filter.getP2t11()));
		}
		if (!CheckNull.isEmpty(filter.getP3t11()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t11=?", filter.getP3t11()));
		}
		if (!CheckNull.isEmpty(filter.getP4t11()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t11=?", filter.getP4t11()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(TranscostFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Transcost ");
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
	
	public static List<Transcost> getTranscostsFl(int pageIndex, int pageSize, TranscostFilter filter)
	{
		
		List<Transcost> list = new ArrayList<Transcost>();
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
				list.add(new Transcost(
											rs.getLong("id"),
											rs.getString("p0t11"),
											rs.getString("p1t11"),
											rs.getString("p2t11"),
											rs.getString("p3t11"),
											rs.getDouble("p4t11")));
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
	
	public Transcost getTranscost(int transcostId)
	{
		
		Transcost transcost = new Transcost();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_transcost WHERE id=?");
			ps.setInt(1, transcostId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				transcost = new Transcost();
				
				transcost.setId(rs.getLong("id"));
				transcost.setP0t11(rs.getString("p0t11"));
				transcost.setP1t11(rs.getString("p1t11"));
				transcost.setP2t11(rs.getString("p2t11"));
				transcost.setP3t11(rs.getString("p3t11"));
				transcost.setP4t11(rs.getDouble("p4t11"));
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
		return transcost;
	}
	
	public static Transcost create(Transcost transcost)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_TF_transcost.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				transcost.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_transcost (id, p0t11, p1t11, p2t11, p3t11, p4t11, ) VALUES (?,?,?,?,?,?,)");
			
			ps.setLong(1, transcost.getId());
			ps.setString(2, transcost.getP0t11());
			ps.setString(3, transcost.getP1t11());
			ps.setString(4, transcost.getP2t11());
			ps.setString(5, transcost.getP3t11());
			ps.setDouble(6, transcost.getP4t11());
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
		return transcost;
	}
	
	public static Res create(com.sbs.service.TransCost transcost, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_TF_transcost.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_transcost (id, p3t11, p4t11, ) VALUES (?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, transcost.getP3T11());
			ps.setDouble(++i, transcost.getP4T11());
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
	
	public static void update(Transcost transcost)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_transcost SET id=?, p0t11=?, p1t11=?, p2t11=?, p3t11=?, p4t11=?,  WHERE id=?");
			
			ps.setLong(1, transcost.getId());
			ps.setString(2, transcost.getP0t11());
			ps.setString(3, transcost.getP1t11());
			ps.setString(4, transcost.getP2t11());
			ps.setString(5, transcost.getP3t11());
			ps.setDouble(6, transcost.getP4t11());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_transcost WHERE id_contract=?");
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
