package com.is.tf.declaration;

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

public class DeclarationService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Declaration ";
	
	public List<Declaration> getDeclaration()
	{
		
		List<Declaration> list = new ArrayList<Declaration>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Declaration");
			while (rs.next())
			{
				list.add(new Declaration(
						rs.getLong("id"),
						rs.getString("p2t48"),
						rs.getString("p3t48"),
						rs.getDate("p4t48"),
						rs.getString("p5t48"),
						rs.getString("p6t48"),
						rs.getDate("p7t48"),
						rs.getDate("p8t48"),
						rs.getString("p9t48"),
						rs.getDouble("p10t48"),
						rs.getString("p11t48"),
						rs.getString("p12t48"),
						rs.getString("p16t48"),
						rs.getString("p17t48"),
						rs.getDouble("p18t48"),
						rs.getString("p19t48")));
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
	
	private static List<FilterField> getFilterFields(DeclarationFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP1t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t48=?", filter.getP1t48()));
		}
		if (!CheckNull.isEmpty(filter.getP2t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t48=?", filter.getP2t48()));
		}
		if (!CheckNull.isEmpty(filter.getP3t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t48=?", filter.getP3t48()));
		}
		if (!CheckNull.isEmpty(filter.getP4t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t48=?", filter.getP4t48()));
		}
		if (!CheckNull.isEmpty(filter.getP5t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t48=?", filter.getP5t48()));
		}
		if (!CheckNull.isEmpty(filter.getP6t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t48=?", filter.getP6t48()));
		}
		if (!CheckNull.isEmpty(filter.getP7t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t48=?", filter.getP7t48()));
		}
		if (!CheckNull.isEmpty(filter.getP8t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t48=?", filter.getP8t48()));
		}
		if (!CheckNull.isEmpty(filter.getP9t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t48=?", filter.getP9t48()));
		}
		if (!CheckNull.isEmpty(filter.getP10t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t48=?", filter.getP10t48()));
		}
		if (!CheckNull.isEmpty(filter.getP11t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t48=?", filter.getP11t48()));
		}
		if (!CheckNull.isEmpty(filter.getP12t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t48=?", filter.getP12t48()));
		}
		if (!CheckNull.isEmpty(filter.getP13t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t48=?", filter.getP13t48()));
		}
		if (!CheckNull.isEmpty(filter.getP14t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t48=?", filter.getP14t48()));
		}
		if (!CheckNull.isEmpty(filter.getP15t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t48=?", filter.getP15t48()));
		}
		if (!CheckNull.isEmpty(filter.getP16t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t48=?", filter.getP16t48()));
		}
		if (!CheckNull.isEmpty(filter.getP17t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p17t48=?", filter.getP17t48()));
		}
		if (!CheckNull.isEmpty(filter.getP18t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p18t48=?", filter.getP18t48()));
		}
		if (!CheckNull.isEmpty(filter.getP19t48()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p19t48=?", filter.getP19t48()));
		}
		if (!CheckNull.isEmpty(filter.getId_contract()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_contract=?", filter.getId_contract()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(DeclarationFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Declaration ");
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
	
	public static List<Declaration> getDeclarationsFl(int pageIndex, int pageSize, DeclarationFilter filter)
	{
		
		List<Declaration> list = new ArrayList<Declaration>();
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
				list.add(new Declaration(
						rs.getLong("id"),
						rs.getString("p2t48"),
						rs.getString("p3t48"),
						rs.getDate("p4t48"),
						rs.getString("p5t48"),
						rs.getString("p6t48"),
						rs.getDate("p7t48"),
						rs.getDate("p8t48"),
						rs.getString("p9t48"),
						rs.getDouble("p10t48"),
						rs.getString("p11t48"),
						rs.getString("p12t48"),
						rs.getString("p16t48"),
						rs.getString("p17t48"),
						rs.getDouble("p18t48"),
						rs.getString("p19t48")
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
	
	public Declaration getDeclaration(int declarationId)
	{
		
		Declaration declaration = new Declaration();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_declaration WHERE id=?");
			ps.setInt(1, declarationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				declaration = new Declaration();
				
				declaration.setId(rs.getLong("id"));
				declaration.setP1t48(rs.getString("p1t48"));
				declaration.setP2t48(rs.getString("p2t48"));
				declaration.setP3t48(rs.getString("p3t48"));
				declaration.setP4t48(rs.getDate("p4t48"));
				declaration.setP5t48(rs.getString("p5t48"));
				declaration.setP6t48(rs.getString("p6t48"));
				declaration.setP7t48(rs.getDate("p7t48"));
				declaration.setP8t48(rs.getDate("p8t48"));
				declaration.setP9t48(rs.getString("p9t48"));
				declaration.setP10t48(rs.getDouble("p10t48"));
				declaration.setP11t48(rs.getString("p11t48"));
				declaration.setP12t48(rs.getString("p12t48"));
				declaration.setP13t48(rs.getString("p13t48"));
				declaration.setP14t48(rs.getDate("p14t48"));
				declaration.setP15t48(rs.getString("p15t48"));
				declaration.setP16t48(rs.getString("p16t48"));
				declaration.setP17t48(rs.getString("p17t48"));
				declaration.setP18t48(rs.getDouble("p18t48"));
				declaration.setP19t48(rs.getString("p19t48"));
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
		return declaration;
	}
	
	public static Declaration create(Declaration declaration)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_declaration.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				declaration.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_declaration (id, p1t48, p2t48, p3t48, p4t48, p5t48, p6t48, p7t48, p8t48, p9t48, p10t48, p11t48, p12t48, p13t48, p14t48, p15t48, p16t48, p17t48, p18t48, p19t48 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, declaration.getId());
			ps.setString(2, declaration.getP1t48());
			ps.setString(3, declaration.getP2t48());
			ps.setString(4, declaration.getP3t48());
			ps.setDate(5, new java.sql.Date(declaration.getP4t48().getTime()));
			ps.setString(6, declaration.getP5t48());
			ps.setString(7, declaration.getP6t48());
			ps.setDate(8, new java.sql.Date(declaration.getP7t48().getTime()));
			ps.setDate(9, new java.sql.Date(declaration.getP8t48().getTime()));
			ps.setString(10, declaration.getP9t48());
			ps.setDouble(11, declaration.getP10t48());
			ps.setString(12, declaration.getP11t48());
			ps.setString(13, declaration.getP12t48());
			ps.setString(14, declaration.getP13t48());
			ps.setDate(15, new java.sql.Date(declaration.getP14t48().getTime()));
			ps.setString(16, declaration.getP15t48());
			ps.setString(17, declaration.getP16t48());
			ps.setString(18, declaration.getP17t48());
			ps.setDouble(19, declaration.getP18t48());
			ps.setString(20, declaration.getP19t48());
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
		return declaration;
	}
	
	public static void update(Declaration declaration)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_declaration SET id=?, p1t48=?, p2t48=?, p3t48=?, p4t48=?, p5t48=?, p6t48=?, p7t48=?, p8t48=?, p9t48=?, p10t48=?, p11t48=?, p12t48=?, p13t48=?, p14t48=?, p15t48=?, p16t48=?, p17t48=?, p18t48=?, p19t48=?,  WHERE id=?");
			
			ps.setLong(1, declaration.getId());
			ps.setString(2, declaration.getP1t48());
			ps.setString(3, declaration.getP2t48());
			ps.setString(4, declaration.getP3t48());
			ps.setDate(5, new java.sql.Date(declaration.getP4t48().getTime()));
			ps.setString(6, declaration.getP5t48());
			ps.setString(7, declaration.getP6t48());
			ps.setDate(8, new java.sql.Date(declaration.getP7t48().getTime()));
			ps.setDate(9, new java.sql.Date(declaration.getP8t48().getTime()));
			ps.setString(10, declaration.getP9t48());
			ps.setDouble(11, declaration.getP10t48());
			ps.setString(12, declaration.getP11t48());
			ps.setString(13, declaration.getP12t48());
			ps.setString(14, declaration.getP13t48());
			ps.setDate(15, new java.sql.Date(declaration.getP14t48().getTime()));
			ps.setString(16, declaration.getP15t48());
			ps.setString(17, declaration.getP16t48());
			ps.setString(18, declaration.getP17t48());
			ps.setDouble(19, declaration.getP18t48());
			ps.setString(20, declaration.getP19t48());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_declaration WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Declaration declaration, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			int i = 0;
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_declaration.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_declaration (id, p2t48, p3t48, p4t48, p5t48" +
					", p6t48, p7t48, p8t48, p9t48, p10t48, p11t48, p12t48,  p16t48, p17t48, p18t48, p19t48, id_contract) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(++i, aid);
			ps.setString(++i, declaration.getP2T48());
			ps.setString(++i, declaration.getP3T48());
			ps.setDate(++i, new java.sql.Date(declaration.getP4T48().getTimeInMillis()));
			ps.setString(++i, declaration.getP5T48());
			ps.setString(++i, declaration.getP6T48());
			ps.setDate(++i, declaration.getP7T48() != null ? new java.sql.Date(declaration.getP7T48().getTimeInMillis()) : null);
			ps.setDate(++i, declaration.getP8T48() != null ? new java.sql.Date(declaration.getP8T48().getTimeInMillis()) : null);
			ps.setString(++i, declaration.getP9T48());
			ps.setDouble(++i, declaration.getP10T48() != null ? declaration.getP10T48() : null);
			ps.setString(++i, declaration.getP11T48());
			ps.setString(++i, declaration.getP12T48());
			ps.setString(++i, declaration.getP16T48() != null ? declaration.getP16T48() : null);
			ps.setString(++i, declaration.getP17T48() != null ? declaration.getP17T48() : null);
			ps.setDouble(++i, declaration.getP18T48() != null ? declaration.getP18T48() : null);
			ps.setString(++i, declaration.getP19T48() != null ? declaration.getP19T48() : null);
			ps.setLong(++i, id_contract);
			
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
