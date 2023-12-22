package com.is.tf.sender;

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

public class SenderService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Sender ";
	
	public List<Sender> getSender()
	{
		
		List<Sender> list = new ArrayList<Sender>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Sender");
			while (rs.next())
			{
				list.add(new Sender(
								rs.getLong("id"),
								rs.getString("p0t10"),
								rs.getString("p1t10"),
								rs.getString("p2t10"),
								rs.getString("p3t10"),
								rs.getString("p4t10"),
								rs.getString("p5t10"),
								rs.getString("p6t10"),
								rs.getString("p7t10"),
								rs.getString("p8t10"),
								rs.getString("p9t10"),
								rs.getDate("p10t10"),
								rs.getShort("p100t10")
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
	
	private static List<FilterField> getFilterFields(SenderFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t10=?", filter.getP0t10()));
		}
		if (!CheckNull.isEmpty(filter.getP1t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t10=?", filter.getP1t10()));
		}
		if (!CheckNull.isEmpty(filter.getP2t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t10=?", filter.getP2t10()));
		}
		if (!CheckNull.isEmpty(filter.getP3t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t10=?", filter.getP3t10()));
		}
		if (!CheckNull.isEmpty(filter.getP4t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t10=?", filter.getP4t10()));
		}
		if (!CheckNull.isEmpty(filter.getP5t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t10=?", filter.getP5t10()));
		}
		if (!CheckNull.isEmpty(filter.getP6t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t10=?", filter.getP6t10()));
		}
		if (!CheckNull.isEmpty(filter.getP7t10()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t10=?", filter.getP7t10()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(SenderFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Sender ");
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
	
	public static List<Sender> getSendersFl(int pageIndex, int pageSize, SenderFilter filter)
	{
		
		List<Sender> list = new ArrayList<Sender>();
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
				list.add(new Sender(
						rs.getLong("id"),
						rs.getString("p0t10"),
						rs.getString("p1t10"),
						rs.getString("p2t10"),
						rs.getString("p3t10"),
						rs.getString("p4t10"),
						rs.getString("p5t10"),
						rs.getString("p6t10"),
						rs.getString("p7t10"),
						rs.getString("p8t10"),
						rs.getString("p9t10"),
						rs.getDate("p10t10"),
						rs.getShort("p100t10")
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
	
	public Sender getSender(int senderId)
	{
		
		Sender sender = new Sender();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_sender WHERE id=?");
			ps.setInt(1, senderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				sender = new Sender();
				
				sender.setId(rs.getLong("id"));
				sender.setP0t10(rs.getString("p0t10"));
				sender.setP1t10(rs.getString("p1t10"));
				sender.setP2t10(rs.getString("p2t10"));
				sender.setP3t10(rs.getString("p3t10"));
				sender.setP4t10(rs.getString("p4t10"));
				sender.setP5t10(rs.getString("p5t10"));
				sender.setP6t10(rs.getString("p6t10"));
				sender.setP7t10(rs.getString("p7t10"));
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
		return sender;
	}
	
	public static Sender create(Sender sender)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_sender.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				sender.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_sender (id, p0t10, p1t10, p2t10, p3t10, p4t10, p5t10, p6t10, p7t10) VALUES (?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, sender.getId());
			ps.setString(2, sender.getP0t10());
			ps.setString(3, sender.getP1t10());
			ps.setString(4, sender.getP2t10());
			ps.setString(5, sender.getP3t10());
			ps.setString(6, sender.getP4t10());
			ps.setString(7, sender.getP5t10());
			ps.setString(8, sender.getP6t10());
			ps.setString(9, sender.getP7t10());
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
		return sender;
	}
	
	public static void update(Sender sender)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_sender SET id=?, p0t10=?, p1t10=?, p2t10=?, p3t10=?, p4t10=?, p5t10=?, p6t10=?, p7t10=?,  WHERE id=?");
			
			ps.setLong(1, sender.getId());
			ps.setString(2, sender.getP0t10());
			ps.setString(3, sender.getP1t10());
			ps.setString(4, sender.getP2t10());
			ps.setString(5, sender.getP3t10());
			ps.setString(6, sender.getP4t10());
			ps.setString(7, sender.getP5t10());
			ps.setString(8, sender.getP6t10());
			ps.setString(9, sender.getP7t10());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_sender WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Sender sender, Long id_contract)
	{
		
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_sender.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_sender (id, p0t10, p1t10, p2t10, p3t10, p4t10, p5t10, p6t10, p7t10," +
					" id_contract, p8t10, p9t10, p10t10, p100t10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, null);
			ps.setString(++i, sender.getP1T10());
			ps.setString(++i, sender.getP2T10());
			ps.setString(++i, sender.getP3T10());
			ps.setString(++i, sender.getP4T10());
			ps.setString(++i, sender.getP5T10());
			ps.setString(++i, sender.getP6T10());
			ps.setString(++i, sender.getP7T10());
			ps.setLong(++i, id_contract);
			ps.setString(++i, sender.getP8T10());
			ps.setString(++i, String.valueOf(sender.getP9T10()));
			ps.setDate(++i, sender.getP10T10()==null?null:new java.sql.Date(sender.getP10T10().getTimeInMillis()) );
			ps.setString(++i, String.valueOf(sender.getP100T10()));
			
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
