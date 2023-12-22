package com.is.tf.Commission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tf.currency.RefCurrencyData;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class CommissionService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Commission ";
	
	public List<Commission> getCommission()
	{
		
		List<Commission> list = new ArrayList<Commission>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Commission");
			while (rs.next())
			{
				list.add(new Commission(
												rs.getLong("id"),
												rs.getInt("p0t27"),
												rs.getString("p1t27"),
												rs.getString("p2t27"),
												rs.getDouble("p3t27"),
												rs.getDouble("p4t27"),
												rs.getDouble("p5t27"),
												rs.getInt("p6t27"),
												rs.getDate("p7t27"),
												rs.getLong("id_contract"),
												rs.getString("p100t27"),
												rs.getString("p8t27"),
												rs.getDouble("p10t27"),
												rs.getString("p11t27"),
												rs.getString("p12t27"),
												rs.getString("p13t27"),
												rs.getDate("p14t27"),
												rs.getString("p9t27"))
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
	
	private static List<FilterField> getFilterFields(CommissionFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t27=?", filter.getP0t27()));
		}
		if (!CheckNull.isEmpty(filter.getP1t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t27=?", filter.getP1t27()));
		}
		if (!CheckNull.isEmpty(filter.getP2t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t27=?", filter.getP2t27()));
		}
		if (!CheckNull.isEmpty(filter.getP3t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t27=?", filter.getP3t27()));
		}
		if (!CheckNull.isEmpty(filter.getP4t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t27=?", filter.getP4t27()));
		}
		if (!CheckNull.isEmpty(filter.getP5t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t27=?", filter.getP5t27()));
		}
		if (!CheckNull.isEmpty(filter.getP6t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t27=?", filter.getP6t27()));
		}
		if (!CheckNull.isEmpty(filter.getP7t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t27=?", filter.getP7t27()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		if (!CheckNull.isEmpty(filter.getP100t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t27=?", filter.getP100t27()));
		}
		if (!CheckNull.isEmpty(filter.getP8t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t27=?", filter.getP8t27()));
		}
		if (!CheckNull.isEmpty(filter.getP10t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t27=?", filter.getP10t27()));
		}
		if (!CheckNull.isEmpty(filter.getP11t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t27=?", filter.getP11t27()));
		}
		if (!CheckNull.isEmpty(filter.getP12t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t27=?", filter.getP12t27()));
		}
		if (!CheckNull.isEmpty(filter.getP13t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t27=?", filter.getP13t27()));
		}
		if (!CheckNull.isEmpty(filter.getP14t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t27=?", filter.getP14t27()));
		}
		if (!CheckNull.isEmpty(filter.getP9t27()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t27=?", filter.getP9t27()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(CommissionFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Commission ");
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
	
	public static List<Commission> getCommissionsFl(int pageIndex, int pageSize, CommissionFilter filter)
	{
		
		List<Commission> list = new ArrayList<Commission>();
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
		sql.append(" order by 1");
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
				list.add(new Commission(
												rs.getLong("id"),
												rs.getInt("p0t27"),
												rs.getString("p1t27"),
												rs.getString("p2t27"),
												rs.getDouble("p3t27"),
												rs.getDouble("p4t27"),
												rs.getDouble("p5t27"),
												rs.getInt("p6t27"),
												rs.getDate("p7t27"),
												rs.getLong("id_contract"),
												rs.getString("p100t27"),
												rs.getString("p8t27"),
												rs.getDouble("p10t27"),
												rs.getString("p11t27"),
												rs.getString("p12t27"),
												rs.getString("p13t27"),
												rs.getDate("p14t27"),
												rs.getString("p9t27")));
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
	
	public Commission getCommission(int commissionId)
	{
		
		Commission commission = new Commission();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_commission WHERE id=?");
			ps.setInt(1, commissionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				commission = new Commission();
				
				commission.setId(rs.getLong("id"));
				commission.setP0t27(rs.getInt("p0t27"));
				commission.setP1t27(rs.getString("p1t27"));
				commission.setP2t27(rs.getString("p2t27"));
				commission.setP3t27(rs.getDouble("p3t27"));
				commission.setP4t27(rs.getDouble("p4t27"));
				commission.setP5t27(rs.getDouble("p5t27"));
				commission.setP6t27(rs.getInt("p6t27"));
				commission.setP7t27(rs.getDate("p7t27"));
				commission.setId_contract(rs.getLong("id_contract"));
				commission.setP100t27(rs.getString("p100t27"));
				commission.setP8t27(rs.getString("p8t27"));
				commission.setP10t27(rs.getDouble("p10t27"));
				commission.setP11t27(rs.getString("p11t27"));
				commission.setP12t27(rs.getString("p12t27"));
				commission.setP13t27(rs.getString("p13t27"));
				commission.setP14t27(rs.getDate("p14t27"));
				commission.setP9t27(rs.getString("p9t27"));
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
		return commission;
	}
	
	public static Commission create(Commission commission)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_Commission.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				commission.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_commission (id, p0t27, p1t27, p2t27, p3t27, p4t27, p5t27, p6t27, p7t27,id_contract, p100t27, p8t27, p10t27, p11t27, p12t27, p13t27, p14t27, p9t27 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, commission.getId());
			ps.setInt(2, commission.getP0t27());
			ps.setString(3, commission.getP1t27());
			ps.setString(4, commission.getP2t27());
			ps.setDouble(5, commission.getP3t27());
			ps.setDouble(6, commission.getP4t27());
			ps.setDouble(7, commission.getP5t27());
			ps.setInt(8, commission.getP6t27());
			ps.setDate(9, new java.sql.Date(commission.getP7t27().getTime()));
			ps.setLong(10, commission.getId_contract());
			ps.setString(11, commission.getP100t27());
			ps.setString(12, commission.getP8t27());
			ps.setDouble(13, commission.getP10t27());
			ps.setString(14, commission.getP11t27());
			ps.setString(15, commission.getP12t27());
			ps.setString(16, commission.getP13t27());
			ps.setDate(17, new java.sql.Date(commission.getP14t27().getTime()));
			ps.setString(18, commission.getP9t27());
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
		return commission;
	}
	
	public static void update(Commission commission)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_commission SET id=?, p0t27=?, p2t27=?, p3t27=?, p4t27=?, p5t27=?, p6t27=?, p7t27=?,  p100t27=?, p8t27=?, p10t27=?, p11t27=?, p12t27=?, p13t27=?, p14t27=?, p9t27=?  WHERE id=? and id_contract=? and p1t27=?");
			
			ps.setLong(1, commission.getId());
			ps.setInt(2, commission.getP0t27());
			ps.setString(3, commission.getP2t27());
			ps.setDouble(4, commission.getP3t27());
			ps.setDouble(5, commission.getP4t27());
			ps.setDouble(6, commission.getP5t27());
			ps.setInt(7, commission.getP6t27());
			ps.setDate(8, new java.sql.Date(commission.getP7t27().getTime()));
			ps.setString(9, commission.getP100t27());
			ps.setString(10, commission.getP8t27());
			ps.setDouble(11, commission.getP10t27());
			ps.setString(12, commission.getP11t27());
			ps.setString(13, commission.getP12t27());
			ps.setString(14, commission.getP13t27());
			ps.setDate(15, new java.sql.Date(commission.getP14t27().getTime()));
			ps.setString(16, commission.getP9t27());
			ps.setLong(17, commission.getId_contract());
			ps.setString(18, commission.getP1t27());
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
	
	public static void remove(Commission commission, Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_commission WHERE id_contract=?");
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
	
	public static com.sbs.service.Commission create(com.sbs.service.Commission commission, String P1T27, Long id_contract)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_Commission.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_commission (id, p0t27, p1t27, p2t27, p3t27, p4t27, p5t27, p6t27, id_contract, p100t27, p8t27, p10t27, p13t27, p14t27, p9t27 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, aid);
			ps.setInt(2, commission.getP0T27() != null ? commission.getP0T27() : 0);
			ps.setString(3, P1T27);
			ps.setInt(4, commission.getP2T27() != null ? commission.getP2T27() : 0);
			ps.setDouble(5, commission.getP3T27());
			ps.setDouble(6, commission.getP4T27());
			ps.setDouble(7, commission.getP5T27());
			ps.setInt(8, commission.getP6T27() != null ? commission.getP6T27() : 0);
			// ps.setDate(9,new
			// java.sql.Date(commission.getP7T27().getTimeInMillis()));
			ps.setLong(9, id_contract);
			ps.setShort(10, commission.getP100T27());
			ps.setString(11, commission.getP8T27());
			ps.setDouble(12, commission.getP10T27());
			// ps.setShort(14,commission.getP11T27());
			// ps.setString(15,commission.getP12T27());
			ps.setShort(13, commission.getP13T27());
			ps.setDate(14, new java.sql.Date(commission.getP14T27().getTimeInMillis()));
			ps.setInt(15, commission.getP9T27() != null ? commission.getP9T27() : 0);
			
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
		return commission;
	}
	
	public static RefCurrencyData getRefCurrencyData(String val, List<RefCurrencyData> dp)
	{
		RefCurrencyData res = null;
		if (dp != null)
		{
			for (int i = 0; i < dp.size(); i++)
			{
				if (val.equals(dp.get(i).getKod()))
				{
					res = dp.get(i);
				}
			}
		}
		return res;
	}
	
}
