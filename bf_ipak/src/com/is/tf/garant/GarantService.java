package com.is.tf.garant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tf.garantsumchange.Garantsumchange;
import com.is.tf.garantsumchange.GarantsumchangeService;
import com.is.tf.garanttimechange.garanttimechange;
import com.is.tf.garanttimechange.garanttimechangeService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class GarantService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Garant ";
	
	public List<Garant> getGarant()
	{
		
		List<Garant> list = new ArrayList<Garant>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Garant");
			while (rs.next())
			{
				list.add(new Garant(
											rs.getLong("id"),
											rs.getInt("p0t18"),
											rs.getString("p1t18"),
											rs.getString("p2t18"),
											rs.getString("p3t18"),
											rs.getDate("p4t18"),
											rs.getString("p5t18"),
											rs.getDouble("p6t18"),
											rs.getDouble("p7t18"),
											rs.getDouble("p8t18"),
											rs.getDouble("p9t18"),
											rs.getDate("p10t18"),
											rs.getString("p11t18"),
											rs.getLong("id_contract"),
											rs.getString("p100t18"),
											rs.getString("p12t18"),
											rs.getString("p13t18"),
											rs.getString("p14t18"),
											rs.getString("p15t18"),
											rs.getDate("p16t18")));
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
	
	private static List<FilterField> getFilterFields(GarantFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t18=?", filter.getP0t18()));
		}
		if (!CheckNull.isEmpty(filter.getP1t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t18=?", filter.getP1t18()));
		}
		if (!CheckNull.isEmpty(filter.getP2t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t18=?", filter.getP2t18()));
		}
		if (!CheckNull.isEmpty(filter.getP3t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t18=?", filter.getP3t18()));
		}
		if (!CheckNull.isEmpty(filter.getP4t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t18=?", filter.getP4t18()));
		}
		if (!CheckNull.isEmpty(filter.getP5t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t18=?", filter.getP5t18()));
		}
		if (!CheckNull.isEmpty(filter.getP6t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t18=?", filter.getP6t18()));
		}
		if (!CheckNull.isEmpty(filter.getP7t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t18=?", filter.getP7t18()));
		}
		if (!CheckNull.isEmpty(filter.getP8t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t18=?", filter.getP8t18()));
		}
		if (!CheckNull.isEmpty(filter.getP9t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t18=?", filter.getP9t18()));
		}
		if (!CheckNull.isEmpty(filter.getP10t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t18=?", filter.getP10t18()));
		}
		if (!CheckNull.isEmpty(filter.getP11t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t18=?", filter.getP11t18()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		if (!CheckNull.isEmpty(filter.getP100t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t18=?", filter.getP100t18()));
		}
		if (!CheckNull.isEmpty(filter.getP12t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t18=?", filter.getP12t18()));
		}
		if (!CheckNull.isEmpty(filter.getP13t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t18=?", filter.getP13t18()));
		}
		if (!CheckNull.isEmpty(filter.getP14t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t18=?", filter.getP14t18()));
		}
		if (!CheckNull.isEmpty(filter.getP15t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t18=?", filter.getP15t18()));
		}
		if (!CheckNull.isEmpty(filter.getP16t18()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t18=?", filter.getP16t18()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(GarantFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Garant ");
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
			ISLogger.getLogger().error("Garant 22=" + CheckNull.getPstr(e) + "===>" + e.getMessage());
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static List<Garant> getGarantsFl(int pageIndex, int pageSize, GarantFilter filter)
	{
		
		List<Garant> list = new ArrayList<Garant>();
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
				list.add(new Garant(
											rs.getLong("id"),
											rs.getInt("p0t18"),
											rs.getString("p1t18"),
											rs.getString("p2t18"),
											rs.getString("p3t18"),
											rs.getDate("p4t18"),
											rs.getString("p5t18"),
											rs.getDouble("p6t18"),
											rs.getDouble("p7t18"),
											rs.getDouble("p8t18"),
											rs.getDouble("p9t18"),
											rs.getDate("p10t18"),
											rs.getString("p11t18"),
											rs.getLong("id_contract"),
											rs.getString("p100t18"),
											rs.getString("p12t18"),
											rs.getString("p13t18"),
											rs.getString("p14t18"),
											rs.getString("p15t18"),
											rs.getDate("p16t18"),
											GarantsumchangeService.getGarantsumchange(rs.getString("p3t18"), rs.getLong("id_contract")),
											garanttimechangeService.getgaranttimechange(rs.getString("p3t18"), rs.getLong("id_contract"))

				));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("Garant 1=" + CheckNull.getPstr(e) + "===>" + e.getMessage());
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public Garant getGarant(int garantId)
	{
		
		Garant garant = new Garant();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_garant WHERE id=?");
			ps.setInt(1, garantId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garant = new Garant();
				
				garant.setId(rs.getLong("id"));
				garant.setP0t18(rs.getInt("p0t18"));
				garant.setP1t18(rs.getString("p1t18"));
				garant.setP2t18(rs.getString("p2t18"));
				garant.setP3t18(rs.getString("p3t18"));
				garant.setP4t18(rs.getDate("p4t18"));
				garant.setP5t18(rs.getString("p5t18"));
				garant.setP6t18(rs.getDouble("p6t18"));
				garant.setP7t18(rs.getDouble("p7t18"));
				garant.setP8t18(rs.getDouble("p8t18"));
				garant.setP9t18(rs.getDouble("p9t18"));
				garant.setP10t18(rs.getDate("p10t18"));
				garant.setP11t18(rs.getString("p11t18"));
				garant.setId_contract(rs.getLong("id_contract"));
				garant.setP100t18(rs.getString("p100t18"));
				garant.setP12t18(rs.getString("p12t18"));
				garant.setP13t18(rs.getString("p13t18"));
				garant.setP14t18(rs.getString("p14t18"));
				garant.setP15t18(rs.getString("p15t18"));
				garant.setP16t18(rs.getDate("p16t18"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("Garant Select=" + CheckNull.getPstr(e) + "===>" + e.getMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return garant;
	}
	
	public static Garant create(Garant garant)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_TF_garant.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				garant.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_garant (id, p0t18, p1t18, p2t18, p3t18, p4t18, p5t18, p6t18, p7t18, p8t18, p9t18, p10t18, p11t18, p100t18, p12t18, p13t18, p14t18, p15t18, p16t18 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, garant.getId());
			ps.setInt(2, garant.getP0t18());
			ps.setString(3, garant.getP1t18());
			ps.setString(4, garant.getP2t18());
			ps.setString(5, garant.getP3t18());
			ps.setDate(6, new java.sql.Date(garant.getP4t18().getTime()));
			ps.setString(7, garant.getP5t18());
			ps.setDouble(8, garant.getP6t18());
			ps.setDouble(9, garant.getP7t18());
			ps.setDouble(10, garant.getP8t18());
			ps.setDouble(11, garant.getP9t18());
			ps.setDate(12, new java.sql.Date(garant.getP10t18().getTime()));
			ps.setString(13, garant.getP11t18());
			ps.setString(14, garant.getP100t18());
			ps.setString(15, garant.getP12t18());
			ps.setString(16, garant.getP13t18());
			ps.setString(17, garant.getP14t18());
			ps.setString(18, garant.getP15t18());
			ps.setDate(19, new java.sql.Date(garant.getP16t18().getTime()));
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("Garant Service ERROR 1=" + CheckNull.getPstr(e) + "===>" + e.getMessage());
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return garant;
	}
	
	public static void update(Garant garant)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE garant SET id=?, p0t18=?, p1t18=?, p2t18=?, p3t18=?, p4t18=?, p5t18=?, p6t18=?, p7t18=?, p8t18=?, p9t18=?, p10t18=?, p11t18=?, p12t18=?, p13t18=?, p14t18=?, p15t18=?, p16t18=?  WHERE garant_id=? and p100t18=? ");
			
			ps.setLong(1, garant.getId());
			ps.setInt(2, garant.getP0t18());
			ps.setString(3, garant.getP1t18());
			ps.setString(4, garant.getP2t18());
			ps.setString(5, garant.getP3t18());
			ps.setDate(6, new java.sql.Date(garant.getP4t18().getTime()));
			ps.setString(7, garant.getP5t18());
			ps.setDouble(8, garant.getP6t18());
			ps.setDouble(9, garant.getP7t18());
			ps.setDouble(10, garant.getP8t18());
			ps.setDouble(11, garant.getP9t18());
			ps.setDate(12, new java.sql.Date(garant.getP10t18().getTime()));
			ps.setString(13, garant.getP11t18());
			ps.setString(14, garant.getP12t18());
			ps.setString(15, garant.getP13t18());
			ps.setString(16, garant.getP14t18());
			ps.setString(17, garant.getP15t18());
			ps.setDate(18, new java.sql.Date(garant.getP16t18().getTime()));
			ps.setString(19, garant.getP100t18());
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
	
	public static void remove(Garant garant, Long id_contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_garant WHERE id_contract=?");
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
	
	public static void create(ArrayList<com.sbs.service.Garant> Garants, String p1t18, Long id_contract)
	{
		Connection c = null;
		PreparedStatement ps = null;
		long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_Garant.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_garant (id, p0t18,p1t18, p2t18, p3t18, p4t18, p5t18, p6t18, p7t18, p8t18, p9t18, p10t18, p11t18, id_contract, p100t18, p12t18, p15t18, p16t18 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			for (int i = 0; i < Garants.size(); i++)
			{
				ps.setLong(1, aid);
				ps.setInt(2, 0);
				ps.setString(3, p1t18);
				ps.setString(4, Garants.get(i).getP2T18());
				ps.setString(5, Garants.get(i).getP3T18());
				ps.setDate(6, Garants.get(i).getP4T18() != null ? new java.sql.Date(Garants.get(i).getP4T18().getTimeInMillis()) : null);
				ps.setString(7, Garants.get(i).getP5T18());
				ps.setDouble(8, Garants.get(i).getP6T18() != null ? Garants.get(i).getP6T18() : 0);
				ps.setDouble(9, Garants.get(i).getP7T18() != null ? Garants.get(i).getP7T18() : 0);
				ps.setDouble(10, Garants.get(i).getP8T18() != null ? Garants.get(i).getP8T18() : 0);
				ps.setDouble(11, Garants.get(i).getP9T18() != null ? Garants.get(i).getP9T18() : 0);
				ps.setDate(12, Garants.get(i).getP10T18() != null ? new java.sql.Date(Garants.get(i).getP10T18().getTimeInMillis()) : null);
				ps.setInt(13, Garants.get(i).getP11T18() != null ? Garants.get(i).getP11T18() : 0);
				ps.setLong(14, id_contract);
				ps.setInt(15, Garants.get(i).getP100T18() != null ? Garants.get(i).getP100T18() : 0);
				ps.setString(16, Garants.get(i).getP12T18());
				ps.setInt(17, Garants.get(i).getP15T18() != null ? Garants.get(i).getP15T18() : 0);
				ps.setDate(18, Garants.get(i).getP16T18() != null ? new java.sql.Date(Garants.get(i).getP16T18().getTimeInMillis()) : null);
				ps.executeUpdate();
				
				GarantsumchangeService.remove(new Garantsumchange(), Garants.get(i).getP3T18(), id_contract);
				if (Garants.get(i).getGarantSumChanges() != null)
				{
					ArrayList<com.sbs.service.GarantSumChange> Garantsumchange = new ArrayList<com.sbs.service.GarantSumChange>();
					for (int ii = 0; ii < Garants.get(i).getGarantSumChanges().length; ii++)
					{
						Garantsumchange.add(Garants.get(ii).getGarantSumChanges()[ii]);
					}
					GarantsumchangeService.create2(Garantsumchange, Garants.get(i).getP3T18(), id_contract);
				}
				
				garanttimechangeService.remove(new garanttimechange(), Garants.get(i).getP3T18(), id_contract);
				if (Garants.get(i).getGarantTimeChanges() != null)
				{
					ArrayList<com.sbs.service.GarantTimeChange> GarantTimeChange = new ArrayList<com.sbs.service.GarantTimeChange>();
					for (int ij = 0; ij < Garants.get(i).getGarantTimeChanges().length; ij++)
					{
						GarantTimeChange.add(Garants.get(ij).getGarantTimeChanges()[ij]);
					}
					garanttimechangeService.create2(GarantTimeChange, Garants.get(i).getP3T18(), id_contract);
				}
				
				c.commit();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("Garant Service ERROR=" + CheckNull.getPstr(e));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		//return garant;
		//return Garants;
	}
	
}
