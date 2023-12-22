package com.is.tf.Act;

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

public class ActService
{
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Act ";
	
	public List<Act> getAct()
	{
		
		List<Act> list = new ArrayList<Act>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Act");
			while (rs.next())
			{
				list.add(new Act(
								rs.getLong("id"),
								rs.getString("p0t7"),
								rs.getDate("p1t7"),
								rs.getString("p2t7"),
								rs.getString("p3t7"),
								rs.getDouble("p4t7"),
								rs.getDouble("p5t7"),
								rs.getLong("id_contract"),
								rs.getString("p6t7"),
								rs.getDate("p9t7"),
								rs.getShort("p100t7")
								)

								);
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
	
	private static List<FilterField> getFilterFields(ActFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getP1t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t7=?", new java.sql.Date(filter.getP1t7().getTime())));
			System.out.println("p1t7="+ new java.sql.Date(filter.getP1t7().getTime()));
		}
		if (!CheckNull.isEmpty(filter.getP2t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t7=?", filter.getP2t7()));
		}
		if (!CheckNull.isEmpty(filter.getP3t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t7=?", filter.getP3t7()));
		}
		if (!CheckNull.isEmpty(filter.getP4t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t7=?", filter.getP4t7()));
		}
		if (!CheckNull.isEmpty(filter.getP5t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t7=?", filter.getP5t7()));
		}
		if (!CheckNull.isEmpty(filter.getP6t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t7=?", filter.getP6t7()));
		}
		if (!CheckNull.isEmpty(filter.getP9t7()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t7=?", new java.sql.Date(filter.getP9t7().getTime())));
		}
		
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(ActFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Act ");
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
	
	public static List<Act> getActsFl(int pageIndex, int pageSize, ActFilter filter)
	{
		
		List<Act> list = new ArrayList<Act>();
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
				list.add(new Act(
						rs.getLong("id"),
						rs.getString("p0t7"),
						rs.getDate("p1t7"),
						rs.getString("p2t7"),
						rs.getString("p3t7"),
						rs.getDouble("p4t7"),
						rs.getDouble("p5t7"),
						rs.getLong("id_contract"),
						rs.getString("p6t7"),
						rs.getDate("p9t7"),
						rs.getShort("p100t7")
						)

						);
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
	
	public Act getAct(int actId)
	{
		
		Act act = new Act();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_Act WHERE id=?");
			ps.setInt(1, actId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				act = new Act();
				
				act.setId(rs.getLong("id"));
				act.setP0t7(rs.getString("p0t7"));
				act.setP1t7(rs.getDate("p1t7"));
				act.setP2t7(rs.getString("p2t7"));
				act.setP3t7(rs.getString("p3t7"));
				act.setP4t7(rs.getDouble("p4t7"));
				act.setP5t7(rs.getDouble("p5t7"));
				
				act.setId_contract(rs.getLong("id_contract"));
				act.setP6t7(rs.getString("p6t7"));
				act.setP9t7(rs.getDate("p9t7"));
				act.setP100t7(rs.getShort("p100t7"));
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
		return act;
	}
	
	public static Act create(Act act)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_TF_act.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				act.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_Act (id, p0t7, p1t7, p2t7, p3t7, p4t7, p5t7 ) VALUES (?,?,?,?,?,?,?)");
			
			ps.setLong(1, act.getId());
			ps.setString(2, act.getP0t7());
			ps.setDate(3, new java.sql.Date(act.getP1t7().getTime()));
			ps.setString(4, act.getP2t7());
			ps.setString(5, act.getP3t7());
			ps.setDouble(6, act.getP4t7());
			ps.setDouble(7, act.getP5t7());
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
		return act;
	}
	
	public static void update(Act act)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE tf_act SET id=?, p0t7=?, p1t7=?, p2t7=?, p3t7=?, p4t7=?, p5t7=?, p6t7=?  WHERE id=?");
			
			ps.setLong(1, act.getId());
			ps.setString(2, act.getP0t7());
			ps.setDate(3, new java.sql.Date(act.getP1t7().getTime()));
			ps.setString(4, act.getP2t7());
			ps.setString(5, act.getP3t7());
			ps.setDouble(6, act.getP4t7());
			ps.setDouble(7, act.getP5t7());
			ps.setString(8, act.getP6t7());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM tf_act WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Act act, Long id_contract) throws Exception
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_Act.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_Act (id, p0t7, p1t7, p2t7, p3t7, p4t7, p5t7, id_contract, p6t7, p9t7, p100t7 ) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, aid);
			ps.setString(2, null);
			ps.setDate(3, new java.sql.Date(act.getP1T7().getTimeInMillis()));
			ps.setString(4, act.getP2T7());
			ps.setString(5, act.getP3T7());
			ps.setDouble(6, act.getP4T7());
			ps.setDouble(7, CheckNull.isEmpty(act.getP5T7())?0:act.getP5T7());
			ps.setLong(8, id_contract);
			
			ps.setString(9, act.getP6T7());
			ps.setDate(10, new java.sql.Date(act.getP9T7().getTimeInMillis()));
			ps.setString(11, String.valueOf(act.getP100T7()));
			
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
