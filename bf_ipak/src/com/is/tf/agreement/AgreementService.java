package com.is.tf.agreement;

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

public class AgreementService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Agreement ";
	
	public List<Agreement> getAgreement()
	{
		
		List<Agreement> list = new ArrayList<Agreement>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Agreement");
			while (rs.next())
			{
				list.add(new Agreement(
						rs.getLong("id"),
						rs.getString("p3t5"),
						rs.getDate("p4t5"),
						rs.getDate("p5t5"),
						rs.getString("p6t5"),
						rs.getString("p7t5"),
						rs.getString("p8t5"),
						rs.getString("p9t5"),
						rs.getString("p10t5"),
						rs.getString("p11t5"),
						rs.getString("p12t5"),
						rs.getString("p13t5"),
						rs.getString("p14t5"),
						rs.getString("p15t5"),
						rs.getString("p16t5"),
						rs.getString("p17t5"),
						rs.getString("p18t5"),
						rs.getString("p20t5"),
						rs.getString("p22t5"),
						rs.getDouble("p24t5"),
						rs.getDouble("p25t5"),
						rs.getInt("p26t5"),
						rs.getString("p27t5"),
						rs.getString("p28t5"),
						rs.getDate("p29t5"),
						rs.getString("p30t5"),
						rs.getString("p33t5"),
						rs.getInt("p34t5"),
						rs.getDate("p43t5"),
						rs.getString("p100t5"),
						rs.getLong("id_contract")
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
	
	private static List<FilterField> getFilterFields(AgreementFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP2t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t5=?", filter.getP2t5()));
		}
		if (!CheckNull.isEmpty(filter.getP3t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t5=?", filter.getP3t5()));
		}
		if (!CheckNull.isEmpty(filter.getP4t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t5=?", filter.getP4t5()));
		}
		if (!CheckNull.isEmpty(filter.getP5t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t5=?", filter.getP5t5()));
		}
		if (!CheckNull.isEmpty(filter.getP6t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t5=?", filter.getP6t5()));
		}
		if (!CheckNull.isEmpty(filter.getP7t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t5=?", filter.getP7t5()));
		}
		if (!CheckNull.isEmpty(filter.getP8t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t5=?", filter.getP8t5()));
		}
		if (!CheckNull.isEmpty(filter.getP9t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t5=?", filter.getP9t5()));
		}
		if (!CheckNull.isEmpty(filter.getP10t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t5=?", filter.getP10t5()));
		}
		if (!CheckNull.isEmpty(filter.getP11t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t5=?", filter.getP11t5()));
		}
		if (!CheckNull.isEmpty(filter.getP12t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t5=?", filter.getP12t5()));
		}
		if (!CheckNull.isEmpty(filter.getP13t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t5=?", filter.getP13t5()));
		}
		if (!CheckNull.isEmpty(filter.getP14t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t5=?", filter.getP14t5()));
		}
		if (!CheckNull.isEmpty(filter.getP15t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t5=?", filter.getP15t5()));
		}
		if (!CheckNull.isEmpty(filter.getP16t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t5=?", filter.getP16t5()));
		}
		if (!CheckNull.isEmpty(filter.getP17t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p17t5=?", filter.getP17t5()));
		}
		if (!CheckNull.isEmpty(filter.getP18t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p18t5=?", filter.getP18t5()));
		}
		if (!CheckNull.isEmpty(filter.getP19t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p19t5=?", filter.getP19t5()));
		}
		if (!CheckNull.isEmpty(filter.getP20t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p20t5=?", filter.getP20t5()));
		}
		if (!CheckNull.isEmpty(filter.getP21t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p21t5=?", filter.getP21t5()));
		}
		if (!CheckNull.isEmpty(filter.getP22t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p22t5=?", filter.getP22t5()));
		}
		if (!CheckNull.isEmpty(filter.getP23t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p23t5=?", filter.getP23t5()));
		}
		if (!CheckNull.isEmpty(filter.getP24t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p24t5=?", filter.getP24t5()));
		}
		if (!CheckNull.isEmpty(filter.getP25t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p25t5=?", filter.getP25t5()));
		}
		if (!CheckNull.isEmpty(filter.getP26t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p26t5=?", filter.getP26t5()));
		}
		if (!CheckNull.isEmpty(filter.getP27t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p27t5=?", filter.getP27t5()));
		}
		if (!CheckNull.isEmpty(filter.getP28t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p28t5=?", filter.getP28t5()));
		}
		if (!CheckNull.isEmpty(filter.getP29t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p29t5=?", filter.getP29t5()));
		}
		if (!CheckNull.isEmpty(filter.getP30t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p30t5=?", filter.getP30t5()));
		}
		if (!CheckNull.isEmpty(filter.getP31t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p31t5=?", filter.getP31t5()));
		}
		if (!CheckNull.isEmpty(filter.getP32t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p32t5=?", filter.getP32t5()));
		}
		if (!CheckNull.isEmpty(filter.getP33t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p33t5=?", filter.getP33t5()));
		}
		if (!CheckNull.isEmpty(filter.getP34t5()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p34t5=?", filter.getP34t5()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(AgreementFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Agreement ");
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
	
	public static List<Agreement> getAgreementsFl(int pageIndex, int pageSize, AgreementFilter filter)
	{
		
		List<Agreement> list = new ArrayList<Agreement>();
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
				list.add(new Agreement(
						rs.getLong("id"),
						rs.getString("p3t5"),
						rs.getDate("p4t5"),
						rs.getDate("p5t5"),
						rs.getString("p6t5"),
						rs.getString("p7t5"),
						rs.getString("p8t5"),
						rs.getString("p9t5"),
						rs.getString("p10t5"),
						rs.getString("p11t5"),
						rs.getString("p12t5"),
						rs.getString("p13t5"),
						rs.getString("p14t5"),
						rs.getString("p15t5"),
						rs.getString("p16t5"),
						rs.getString("p17t5"),
						rs.getString("p18t5"),
						rs.getString("p20t5"),
						rs.getString("p22t5"),
						rs.getDouble("p24t5"),
						rs.getDouble("p25t5"),
						rs.getInt("p26t5"),
						rs.getString("p27t5"),
						rs.getString("p28t5"),
						rs.getDate("p29t5"),
						rs.getString("p30t5"),
						rs.getString("p33t5"),
						rs.getInt("p34t5"),
						rs.getDate("p43t5"),
						rs.getString("p100t5"),
						rs.getLong("id_contract")
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
	
	public Agreement getAgreement(int agreementId)
	{
		
		Agreement agreement = new Agreement();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_agreement WHERE id=?");
			ps.setInt(1, agreementId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				agreement = new Agreement();
				
				agreement.setId(rs.getLong("id"));
				agreement.setP2t5(rs.getString("p2t5"));
				agreement.setP3t5(rs.getString("p3t5"));
				agreement.setP4t5(rs.getDate("p4t5"));
				agreement.setP5t5(rs.getDate("p5t5"));
				agreement.setP6t5(rs.getString("p6t5"));
				agreement.setP7t5(rs.getString("p7t5"));
				agreement.setP8t5(rs.getString("p8t5"));
				agreement.setP9t5(rs.getString("p9t5"));
				agreement.setP10t5(rs.getString("p10t5"));
				agreement.setP11t5(rs.getString("p11t5"));
				agreement.setP12t5(rs.getString("p12t5"));
				agreement.setP13t5(rs.getString("p13t5"));
				agreement.setP14t5(rs.getString("p14t5"));
				agreement.setP15t5(rs.getString("p15t5"));
				agreement.setP16t5(rs.getString("p16t5"));
				agreement.setP17t5(rs.getString("p17t5"));
				agreement.setP18t5(rs.getString("p18t5"));
				agreement.setP19t5(rs.getString("p19t5"));
				agreement.setP20t5(rs.getString("p20t5"));
				agreement.setP21t5(rs.getString("p21t5"));
				agreement.setP22t5(rs.getString("p22t5"));
				agreement.setP23t5(rs.getString("p23t5"));
				agreement.setP24t5(rs.getDouble("p24t5"));
				agreement.setP25t5(rs.getDouble("p25t5"));
				agreement.setP26t5(rs.getInt("p26t5"));
				agreement.setP27t5(rs.getString("p27t5"));
				agreement.setP28t5(rs.getString("p28t5"));
				agreement.setP29t5(rs.getDate("p29t5"));
				agreement.setP30t5(rs.getString("p30t5"));
				agreement.setP31t5(rs.getDate("p31t5"));
				agreement.setP32t5(rs.getString("p32t5"));
				agreement.setP33t5(rs.getString("p33t5"));
				agreement.setP34t5(rs.getInt("p34t5"));
				
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
		return agreement;
	}
	
	public static Agreement create(Agreement agreement)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_agreement.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				agreement.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_agreement (id, p3t5, p4t5, p5t5, p6t5, p7t5, p8t5, p9t5, p10t5, p11t5, p12t5, p13t5, p14t5, p15t5, p16t5, p17t5, p18t5, p19t5, p20t5, p21t5, p22t5, p23t5, p24t5, p25t5, p26t5, p27t5, p28t5, p29t5, p30t5, p31t5, p32t5, p33t5, p34t5, p35t5, p36t5, p37t5, p38t5 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(0, agreement.getId());
			ps.setString(1, agreement.getP2t5());
			ps.setString(2, agreement.getP3t5());
			ps.setDate(3, new java.sql.Date(agreement.getP4t5().getTime()));
			ps.setDate(4, new java.sql.Date(agreement.getP5t5().getTime()));
			ps.setString(5, agreement.getP6t5());
			ps.setString(6, agreement.getP7t5());
			ps.setString(7, agreement.getP8t5());
			ps.setString(8, agreement.getP9t5());
			ps.setString(9, agreement.getP10t5());
			ps.setString(10, agreement.getP11t5());
			ps.setString(11, agreement.getP12t5());
			ps.setString(12, agreement.getP13t5());
			ps.setString(13, agreement.getP14t5());
			ps.setString(14, agreement.getP15t5());
			ps.setString(15, agreement.getP16t5());
			ps.setString(16, agreement.getP17t5());
			ps.setString(17, agreement.getP18t5());
			ps.setString(18, agreement.getP19t5());
			ps.setString(19, agreement.getP20t5());
			ps.setString(20, agreement.getP21t5());
			ps.setString(21, agreement.getP22t5());
			ps.setString(22, agreement.getP23t5());
			ps.setDouble(23, agreement.getP24t5());
			ps.setDouble(24, agreement.getP25t5());
			ps.setLong(25, agreement.getP26t5());
			ps.setString(26, agreement.getP27t5());
			ps.setString(27, agreement.getP28t5());
			ps.setDate(28, new java.sql.Date(agreement.getP29t5().getTime()));
			ps.setString(29, agreement.getP30t5());
			ps.setDate(30, new java.sql.Date(agreement.getP31t5().getTime()));
			ps.setString(31, agreement.getP32t5());
			ps.setString(32, agreement.getP33t5());
			// ps.setString(33,agreement.getP34t5());
			
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
		return agreement;
	}
	
	public static void update(Agreement agreement)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_agreement SET id=?, p3t5=?, p4t5=?, p5t5=?, p6t5=?, p7t5=?, p8t5=?, p9t5=?, p10t5=?, p11t5=?, p12t5=?, p13t5=?, p14t5=?, p15t5=?, p16t5=?, p17t5=?, p18t5=?, p19t5=?, p20t5=?, p21t5=?, p22t5=?, p23t5=?, p24t5=?, p25t5=?, p26t5=?, p27t5=?, p28t5=?, p29t5=?, p30t5=?, p31t5=?, p32t5=?, p33t5=?, p34t5=?, p35t5=?, p36t5=?, p37t5=?, p38t5=?,  WHERE id=?");
			
			ps.setLong(0, agreement.getId());
			ps.setString(1, agreement.getP2t5());
			ps.setString(2, agreement.getP3t5());
			ps.setDate(3, new java.sql.Date(agreement.getP4t5().getTime()));
			ps.setDate(4, new java.sql.Date(agreement.getP5t5().getTime()));
			ps.setString(5, agreement.getP6t5());
			ps.setString(6, agreement.getP7t5());
			ps.setString(7, agreement.getP8t5());
			ps.setString(8, agreement.getP9t5());
			ps.setString(9, agreement.getP10t5());
			ps.setString(10, agreement.getP11t5());
			ps.setString(11, agreement.getP12t5());
			ps.setString(12, agreement.getP13t5());
			ps.setString(13, agreement.getP14t5());
			ps.setString(14, agreement.getP15t5());
			ps.setString(15, agreement.getP16t5());
			ps.setString(16, agreement.getP17t5());
			ps.setString(17, agreement.getP18t5());
			ps.setString(18, agreement.getP19t5());
			ps.setString(19, agreement.getP20t5());
			ps.setString(20, agreement.getP21t5());
			ps.setString(21, agreement.getP22t5());
			ps.setString(22, agreement.getP23t5());
			ps.setDouble(23, agreement.getP24t5());
			ps.setDouble(24, agreement.getP25t5());
			ps.setLong(25, agreement.getP26t5());
			ps.setString(26, agreement.getP27t5());
			ps.setString(27, agreement.getP28t5());
			ps.setDate(28, new java.sql.Date(agreement.getP29t5().getTime()));
			ps.setString(29, agreement.getP30t5());
			ps.setDate(30, new java.sql.Date(agreement.getP31t5().getTime()));
			ps.setString(31, agreement.getP32t5());
			ps.setString(32, agreement.getP33t5());
			ps.setString(33, String.valueOf(agreement.getP34t5()));
			
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_agreement WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Agreement agreement, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_Agreement.Nextval id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			int i = 1;
			ps = c.prepareStatement("INSERT INTO TF_agreement (id, p3t5, p4t5, p5t5, p6t5, p7t5, p8t5, p9t5, p10t5, p11t5, p12t5, p13t5, p14t5, p15t5" +
										", p16t5, p17t5, p18t5, p20t5, p22t5, p24t5, p25t5, p26t5, p29t5, p30t5" +
										", p33t5, p34t5, id_contract, p43t5, p100t5, p101t5 ) " +
										"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(i, aid);
			ps.setString(++i, agreement.getP3T5());
			ps.setDate(++i, agreement.getP4T5() != null ? new java.sql.Date(agreement.getP4T5().getTimeInMillis()) : null);
			ps.setDate(++i, agreement.getP5T5() != null ? new java.sql.Date(agreement.getP5T5().getTimeInMillis()) : null);
			ps.setString(++i, agreement.getP6T5());
			ps.setString(++i, agreement.getP7T5());
			ps.setString(++i, agreement.getP8T5());
			ps.setString(++i, agreement.getP9T5());
			ps.setString(++i, agreement.getP10T5());
			ps.setString(++i, agreement.getP11T5());
			ps.setString(++i, agreement.getP12T5());
			ps.setString(++i, agreement.getP13T5());
			ps.setString(++i, agreement.getP14T5());
			ps.setString(++i, agreement.getP15T5());
			ps.setString(++i, agreement.getP16T5());
			ps.setString(++i, agreement.getP17T5());
			ps.setString(++i, agreement.getP18T5());
			ps.setString(++i, agreement.getP20T5());
			ps.setString(++i, agreement.getP22T5());
			ps.setDouble(++i, agreement.getP24T5());
			ps.setDouble(++i, agreement.getP25T5());
			ps.setInt(++i, agreement.getP26T5() != null ? agreement.getP26T5() : 0);
			ps.setDate(++i, agreement.getP29T5() != null ? new java.sql.Date(agreement.getP29T5().getTimeInMillis()) : null);
			ps.setString(++i, String.valueOf(agreement.getP30T5()));
			ps.setDate(++i, agreement.getP33T5() != null ? new java.sql.Date(agreement.getP33T5().getTimeInMillis()) : null);
			ps.setInt(++i, agreement.getP34T5());
			ps.setLong(++i, id_contract);
			ps.setDate(++i, new java.sql.Date(agreement.getP43T5().getTimeInMillis()));
			ps.setString(++i, String.valueOf(agreement.getP100T5()));
			ps.setString(++i, String.valueOf(agreement.getP101T5()));
			
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
