package com.is.tf.Goods;

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

public class GoodsService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM TF_Goods ";
	
	public List<Goods> getGoods()
	{
		
		List<Goods> list = new ArrayList<Goods>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Goods");
			while (rs.next())
			{
				list.add(new Goods(
											rs.getLong("id"),
											rs.getInt("p0t4"),
											rs.getString("p5t4"),
											rs.getString("p7t4"),
											rs.getDouble("p8t4"),
											rs.getString("p9t4"),
											rs.getDouble("p10t4"),
											rs.getString("p11t4"),
											rs.getDouble("p12t4"),
											rs.getDouble("p13t4"),
											rs.getString("p14t4"),
											rs.getString("p15t4"),
											rs.getString("p16t4"),
											rs.getString("p17t4"),
											rs.getString("p18t4"),
											rs.getString("p19t4"),
											rs.getString("p20t4"),
											rs.getDate("p21t4"),
											rs.getString("p22t4"),
											rs.getString("p23t4")));
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
	
	private static List<FilterField> getFilterFields(GoodsFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t4=?", filter.getP0t4()));
		}
		if (!CheckNull.isEmpty(filter.getP5t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t4=?", filter.getP5t4()));
		}
		if (!CheckNull.isEmpty(filter.getP7t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t4=?", filter.getP7t4()));
		}
		if (!CheckNull.isEmpty(filter.getP8t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t4=?", filter.getP8t4()));
		}
		if (!CheckNull.isEmpty(filter.getP9t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t4=?", filter.getP9t4()));
		}
		if (!CheckNull.isEmpty(filter.getP10t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t4=?", filter.getP10t4()));
		}
		if (!CheckNull.isEmpty(filter.getP11t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t4=?", filter.getP11t4()));
		}
		if (!CheckNull.isEmpty(filter.getP12t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t4=?", filter.getP12t4()));
		}
		if (!CheckNull.isEmpty(filter.getP13t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t4=?", filter.getP13t4()));
		}
		if (!CheckNull.isEmpty(filter.getP14t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t4=?", filter.getP14t4()));
		}
		if (!CheckNull.isEmpty(filter.getP15t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t4=?", filter.getP15t4()));
		}
		if (!CheckNull.isEmpty(filter.getP16t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t4=?", filter.getP16t4()));
		}
		if (!CheckNull.isEmpty(filter.getP17t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p17t4=?", filter.getP17t4()));
		}
		if (!CheckNull.isEmpty(filter.getP18t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p18t4=?", filter.getP18t4()));
		}
		if (!CheckNull.isEmpty(filter.getP19t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p19t4=?", filter.getP19t4()));
		}
		if (!CheckNull.isEmpty(filter.getP20t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p20t4=?", filter.getP20t4()));
		}
		if (!CheckNull.isEmpty(filter.getP21t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p21t4=?", filter.getP21t4()));
		}
		if (!CheckNull.isEmpty(filter.getP22t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p22t4=?", filter.getP22t4()));
		}
		if (!CheckNull.isEmpty(filter.getP23t4()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p23t4=?", filter.getP23t4()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(GoodsFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Goods ");
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
	
	public static List<Goods> getGoodssFl(int pageIndex, int pageSize, GoodsFilter filter)
	{
		
		List<Goods> list = new ArrayList<Goods>();
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
				list.add(new Goods(
											rs.getLong("id"),
											rs.getInt("p0t4"),
											rs.getString("p5t4"),
											rs.getString("p7t4"),
											rs.getDouble("p8t4"),
											rs.getString("p9t4"),
											rs.getDouble("p10t4"),
											rs.getString("p11t4"),
											rs.getDouble("p12t4"),
											rs.getDouble("p13t4"),
											rs.getString("p14t4"),
											rs.getString("p15t4"),
											rs.getString("p16t4"),
											rs.getString("p17t4"),
											rs.getString("p18t4"),
											rs.getString("p19t4"),
											rs.getString("p20t4"),
											rs.getDate("p21t4"),
											rs.getString("p22t4"),
											rs.getString("p23t4")));
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
	
	public Goods getGoods(int goodsId)
	{
		
		Goods goods = new Goods();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_goods WHERE id=?");
			ps.setInt(1, goodsId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				goods = new Goods();
				
				goods.setId(rs.getLong("id"));
				goods.setP0t4(rs.getInt("p0t4"));
				goods.setP5t4(rs.getString("p5t4"));
				goods.setP7t4(rs.getString("p7t4"));
				goods.setP8t4(rs.getDouble("p8t4"));
				goods.setP9t4(rs.getString("p9t4"));
				goods.setP10t4(rs.getDouble("p10t4"));
				goods.setP11t4(rs.getString("p11t4"));
				goods.setP12t4(rs.getDouble("p12t4"));
				goods.setP13t4(rs.getDouble("p13t4"));
				goods.setP14t4(rs.getString("p14t4"));
				goods.setP15t4(rs.getString("p15t4"));
				goods.setP16t4(rs.getString("p16t4"));
				goods.setP17t4(rs.getString("p17t4"));
				goods.setP18t4(rs.getString("p18t4"));
				goods.setP19t4(rs.getString("p19t4"));
				goods.setP20t4(rs.getString("p20t4"));
				goods.setP21t4(rs.getDate("p21t4"));
				goods.setP22t4(rs.getString("p22t4"));
				goods.setP23t4(rs.getString("p23t4"));
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
		return goods;
	}
	
	public static Goods create(Goods goods)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_goods.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				goods.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_goods (id, p0t4, p5t4, p7t4, p8t4, p9t4, p10t4, p11t4, p12t4, p13t4, p14t4, p15t4, p16t4, p17t4, p18t4, p19t4, p20t4, p21t4, p22t4, p23t4 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, goods.getId());
			ps.setInt(2, goods.getP0t4());
			ps.setString(3, goods.getP5t4());
			ps.setString(4, goods.getP7t4());
			ps.setDouble(5, goods.getP8t4());
			ps.setString(6, goods.getP9t4());
			ps.setDouble(7, goods.getP10t4());
			ps.setString(8, goods.getP11t4());
			ps.setDouble(9, goods.getP12t4());
			ps.setDouble(10, goods.getP13t4());
			ps.setString(11, goods.getP14t4());
			ps.setString(12, goods.getP15t4());
			ps.setString(13, goods.getP16t4());
			ps.setString(14, goods.getP17t4());
			ps.setString(15, goods.getP18t4());
			ps.setString(16, goods.getP19t4());
			ps.setString(17, goods.getP20t4());
			ps.setDate(18, new java.sql.Date(goods.getP21t4().getTime()));
			ps.setString(19, goods.getP22t4());
			ps.setString(20, goods.getP23t4());
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
		return goods;
	}
	
	public static void update(Goods goods)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_goods SET id=?, p0t4=?, p5t4=?, p7t4=?, p8t4=?, p9t4=?, p10t4=?, p11t4=?, p12t4=?, p13t4=?, p14t4=?, p15t4=?, p16t4=?, p17t4=?, p18t4=?, p19t4=?, p20t4=?, p21t4=?, p22t4=?, p23t4=?,  WHERE goods_id=?");
			
			ps.setLong(1, goods.getId());
			ps.setInt(2, (goods.getP0t4()));
			ps.setString(3, goods.getP5t4());
			ps.setString(4, goods.getP7t4());
			ps.setDouble(5, goods.getP8t4());
			ps.setString(6, goods.getP9t4());
			ps.setDouble(7, goods.getP10t4());
			ps.setString(8, goods.getP11t4());
			ps.setDouble(9, goods.getP12t4());
			ps.setDouble(10, goods.getP13t4());
			ps.setString(11, goods.getP14t4());
			ps.setString(12, goods.getP15t4());
			ps.setString(13, goods.getP16t4());
			ps.setString(14, goods.getP17t4());
			ps.setString(15, goods.getP18t4());
			ps.setString(16, goods.getP19t4());
			ps.setString(17, goods.getP20t4());
			ps.setDate(18, new java.sql.Date(goods.getP21t4().getTime()));
			ps.setString(19, goods.getP22t4());
			ps.setString(20, goods.getP23t4());
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
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_goods WHERE id_contract=?");
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
	
	public static Res create(com.sbs.service.Goods goods, Long id_contract)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_goods.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = (rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_goods (id, p0t4, p5t4, p7t4, p8t4, p9t4, p10t4, p11t4, p12t4, p13t4, p14t4, p15t4, p16t4, p17t4, p18t4, p19t4, p20t4, p21t4, p22t4, p23t4, id_contract) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, aid);
			//ps.setInt(2, P0T4);
			ps.setString(3, goods.getP5T4());
			ps.setString(4, goods.getP7T4());
			ps.setDouble(5, goods.getP8T4());
			ps.setString(6, goods.getP9T4());
			ps.setDouble(7, goods.getP10T4());
			ps.setString(8, goods.getP11T4());
			ps.setDouble(9, goods.getP12T4());
			ps.setDouble(10, goods.getP13T4());
			ps.setString(11, goods.getP14T4());
			ps.setString(12, goods.getP15T4());
			ps.setString(13, goods.getP16T4());
			ps.setString(14, goods.getP17T4());
			ps.setString(15, goods.getP18T4());
			ps.setString(16, goods.getP19T4());
			// ps.setString(17,goods.getP20T4());
			// ps.setDate(18,new
			// java.sql.Date(goods.getP21T4().getTimeInMillis()));
			//ps.setString(19, P22T4);
			//ps.setString(20, P23T4);
			ps.setLong(1, id_contract);
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
