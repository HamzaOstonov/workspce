package com.is.tf.exchangepayment;

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

public class ExchangePaymentService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_ExchangePayment ";
	
	public List<ExchangePayment> getExchangePayment()
	{
		
		List<ExchangePayment> list = new ArrayList<ExchangePayment>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_ExchangePayment");
			while (rs.next())
			{
				list.add(new ExchangePayment(
											rs.getLong("id"),
											rs.getString("p1t51"),
											rs.getString("p0t51"),
											rs.getDate("p2t51"),
											rs.getDouble("p3t51"),
											rs.getDouble("p4t51"),
											rs.getDouble("p5t51"),
											rs.getDouble("p6t51"),
											rs.getDouble("p7t51"),
											rs.getString("p8t51")));
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
	
	private static List<FilterField> getFilterFields(ExchangePaymentFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP1t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t51=?", filter.getP1t51()));
		}
		if (!CheckNull.isEmpty(filter.getP0t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t51=?", filter.getP0t51()));
		}
		if (!CheckNull.isEmpty(filter.getP2t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t51=?", filter.getP2t51()));
		}
		if (!CheckNull.isEmpty(filter.getP3t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t51=?", filter.getP3t51()));
		}
		if (!CheckNull.isEmpty(filter.getP4t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t51=?", filter.getP4t51()));
		}
		if (!CheckNull.isEmpty(filter.getP5t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t51=?", filter.getP5t51()));
		}
		if (!CheckNull.isEmpty(filter.getP6t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t51=?", filter.getP6t51()));
		}
		if (!CheckNull.isEmpty(filter.getP7t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t51=?", filter.getP7t51()));
		}
		if (!CheckNull.isEmpty(filter.getP8t51()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t51=?", filter.getP8t51()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(ExchangePaymentFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_ExchangePayment ");
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
	
	public static List<ExchangePayment> getExchangePaymentsFl(int pageIndex, int pageSize, ExchangePaymentFilter filter)
	{
		
		List<ExchangePayment> list = new ArrayList<ExchangePayment>();
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
				list.add(new ExchangePayment(
											rs.getLong("id"),
											rs.getString("p1t51"),
											rs.getString("p0t51"),
											rs.getDate("p2t51"),
											rs.getDouble("p3t51"),
											rs.getDouble("p4t51"),
											rs.getDouble("p5t51"),
											rs.getDouble("p6t51"),
											rs.getDouble("p7t51"),
											rs.getString("p8t51")));
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
	
	public ExchangePayment getExchangePayment(int exchangepaymentId)
	{
		
		ExchangePayment exchangepayment = new ExchangePayment();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_exchangepayment WHERE id=?");
			ps.setInt(1, exchangepaymentId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				exchangepayment = new ExchangePayment();
				
				exchangepayment.setId(rs.getLong("id"));
				exchangepayment.setP1t51(rs.getString("p1t51"));
				exchangepayment.setP0t51(rs.getString("p0t51"));
				exchangepayment.setP2t51(rs.getDate("p2t51"));
				exchangepayment.setP3t51(rs.getDouble("p3t51"));
				exchangepayment.setP4t51(rs.getDouble("p4t51"));
				exchangepayment.setP5t51(rs.getDouble("p5t51"));
				exchangepayment.setP6t51(rs.getDouble("p6t51"));
				exchangepayment.setP7t51(rs.getDouble("p7t51"));
				exchangepayment.setP8t51(rs.getString("p8t51"));
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
		return exchangepayment;
	}
	
	public static ExchangePayment create(ExchangePayment exchangepayment)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_exchangepayment.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				exchangepayment.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_exchangepayment (id, p1t51, p0t51, p2t51, p3t51, p4t51, p5t51, p6t51, p7t51, p8t51 ) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, exchangepayment.getId());
			ps.setString(2, exchangepayment.getP1t51());
			ps.setString(3, exchangepayment.getP0t51());
			ps.setDate(4, new java.sql.Date(exchangepayment.getP2t51().getTime()));
			ps.setDouble(5, exchangepayment.getP3t51());
			ps.setDouble(6, exchangepayment.getP4t51());
			ps.setDouble(7, exchangepayment.getP5t51());
			ps.setDouble(8, exchangepayment.getP6t51());
			ps.setDouble(9, exchangepayment.getP7t51());
			ps.setString(10, exchangepayment.getP8t51());
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
		return exchangepayment;
	}
	
	public static void update(ExchangePayment exchangepayment)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE TF_exchangepayment SET id=?, p1t51=?, p0t51=?, p2t51=?, p3t51=?, p4t51=?, p5t51=?, p6t51=?, p7t51=?, p8t51=?,  WHERE id=?");
			
			ps.setLong(1, exchangepayment.getId());
			ps.setString(2, exchangepayment.getP1t51());
			ps.setString(3, exchangepayment.getP0t51());
			ps.setDate(4, new java.sql.Date(exchangepayment.getP2t51().getTime()));
			ps.setDouble(5, exchangepayment.getP3t51());
			ps.setDouble(6, exchangepayment.getP4t51());
			ps.setDouble(7, exchangepayment.getP5t51());
			ps.setDouble(8, exchangepayment.getP6t51());
			ps.setDouble(9, exchangepayment.getP7t51());
			ps.setString(10, exchangepayment.getP8t51());
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
	
	public static void remove(ExchangePayment exchangepayment)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_exchangepayment WHERE id=?");
			ps.setLong(1, exchangepayment.getId());
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
	
	public static com.sbs.service.ExchangePayment create(com.sbs.service.ExchangePayment exchangepayment, String p1t51, Long id_contract)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_exchangepayment.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_exchangepayment (id, p1t51, p0t51, p2t51, p3t51, p4t51, p5t51, p6t51, p7t51, p8t51, id_contract ) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, aid);
			ps.setString(2, p1t51);
			// ps.setInt(3,exchangepayment.getP0T51()!=null?
			// exchangepayment.getP0T51():0);
			ps.setDate(4, new java.sql.Date(exchangepayment.getP2T51().getTimeInMillis()));
			ps.setDouble(5, exchangepayment.getP3T51());
			ps.setDouble(6, exchangepayment.getP4T51());
			ps.setDouble(7, exchangepayment.getP5T51());
			ps.setDouble(8, exchangepayment.getP6T51());
			ps.setDouble(9, exchangepayment.getP7T51());
			ps.setInt(10, Integer.parseInt(exchangepayment.getP8T51()));
			ps.setLong(11, id_contract);
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
		return exchangepayment;
	}
	
}
