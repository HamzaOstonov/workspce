package com.is.tf.shipment;

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

public class ShipmentService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Shipment ";
	
	public List<Shipment> getShipment()
	{
		
		List<Shipment> list = new ArrayList<Shipment>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Shipment");
			while (rs.next())
			{
				list.add(new Shipment(
												rs.getLong("id"),
												rs.getString("p0t14"),
												rs.getDate("p1t14"),
												rs.getString("p2t14"),
												rs.getString("p3t14"),
												rs.getDouble("p4t14")));
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
	
	private static List<FilterField> getFilterFields(ShipmentFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t14()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t14=?", filter.getP0t14()));
		}
		if (!CheckNull.isEmpty(filter.getP1t14()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t14=?", filter.getP1t14()));
		}
		if (!CheckNull.isEmpty(filter.getP2t14()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t14=?", filter.getP2t14()));
		}
		if (!CheckNull.isEmpty(filter.getP3t14()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t14=?", filter.getP3t14()));
		}
		if (!CheckNull.isEmpty(filter.getP4t14()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t14=?", filter.getP4t14()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "Id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(ShipmentFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Shipment ");
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
	
	public static List<Shipment> getShipmentsFl(int pageIndex, int pageSize, ShipmentFilter filter)
	{
		
		List<Shipment> list = new ArrayList<Shipment>();
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
				list.add(new Shipment(
												rs.getLong("id"),
												rs.getString("p0t14"),
												rs.getDate("p1t14"),
												rs.getString("p2t14"),
												rs.getString("p3t14"),
												rs.getDouble("p4t14")));
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
	
	public Shipment getShipment(int shipmentId)
	{
		
		Shipment shipment = new Shipment();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_shipment WHERE id=?");
			ps.setInt(1, shipmentId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				shipment = new Shipment();
				
				shipment.setId(rs.getLong("id"));
				shipment.setP0t14(rs.getString("p0t14"));
				shipment.setP1t14(rs.getDate("p1t14"));
				shipment.setP2t14(rs.getString("p2t14"));
				shipment.setP3t14(rs.getString("p3t14"));
				shipment.setP4t14(rs.getDouble("p4t14"));
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
		return shipment;
	}
	
	public static Shipment create(Shipment shipment)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_shipment.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				shipment.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_shipment (id, p0t14, p1t14, p2t14, p3t14, p4t14 ) VALUES (?,?,?,?,?,?)");
			
			ps.setLong(1, shipment.getId());
			ps.setString(2, shipment.getP0t14());
			ps.setDate(3, new java.sql.Date(shipment.getP1t14().getTime()));
			ps.setString(4, shipment.getP2t14());
			ps.setString(5, shipment.getP3t14());
			ps.setDouble(6, shipment.getP4t14());
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
		return shipment;
	}
	
	public static void update(Shipment shipment)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_shipment SET id=?, p0t14=?, p1t14=?, p2t14=?, p3t14=?, p4t14=?,  WHERE id=?");
			
			ps.setLong(1, shipment.getId());
			ps.setString(2, shipment.getP0t14());
			ps.setDate(3, new java.sql.Date(shipment.getP1t14().getTime()));
			ps.setString(4, shipment.getP2t14());
			ps.setString(5, shipment.getP3t14());
			ps.setDouble(6, shipment.getP4t14());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_shipment WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Shipment shipment, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_shipment.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_shipment (id, p1t14, p2t14, p3t14, p4t14, id_contract, p7t14, p8t14, p100t14" +
					" ) VALUES (?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setDate(++i, shipment.getP1T14() == null ? null : new java.sql.Date(shipment.getP1T14().getTimeInMillis()));
			ps.setString(++i, shipment.getP2T14());
			ps.setString(++i, shipment.getP3T14());
			ps.setDouble(++i, shipment.getP4T14());
			ps.setLong(++i, id_contract);
			ps.setString(++i, String.valueOf(shipment.getP7T14()));
			ps.setDate(++i, shipment.getP8T14() == null ? null : new java.sql.Date(shipment.getP8T14().getTimeInMillis()));
			ps.setString(++i, String.valueOf(shipment.getP100T14()));
			
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
