package com.is.tieto_globuz.merchants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class MerchantService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from bf_globuz_merchants_all ";
	private static Cust cust;
	
	public static List<Merchant> getMerchant()
	{
		List<Merchant> list = new ArrayList<Merchant>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(msql);
			while (rs.next())
			{
					list.add(new Merchant(
							rs.getString("merchant"),
							rs.getString("parent"),
							rs.getString("abrv_name"),
							rs.getString("fax"),
							rs.getString("full_name"),
							rs.getString("cntry"),
							rs.getString("city"),
							rs.getString("reg_nr"),
							rs.getString("street"),
							rs.getString("post_ind"),
							rs.getString("phone"),
							rs.getString("cont_person"),
							rs.getString("mcc"),
							rs.getString("p_cntry"),
							rs.getString("p_city"),
							rs.getString("p_street"),
							rs.getString("p_post_ind"),
							rs.getString("mrc_phone"),
							rs.getString("report_crit"),
							rs.getString("e_mail"),
							rs.getString("add_info"),
							rs.getString("report_crit2"),
							rs.getString("user_field"),
							rs.getString("action"),
							rs.getString("acc")
						));
				
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public static List<Merchant> getMerchant4Send()
	{
		List<Merchant> list = new ArrayList<Merchant>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from bf_globuz_merchants_all a where a.action in ('I', 'U')");
			while (rs.next())
			{
					list.add(new Merchant(
							rs.getString("merchant"),
							rs.getString("parent"),
							rs.getString("abrv_name"),
							rs.getString("fax"),
							rs.getString("full_name"),
							rs.getString("cntry"),
							rs.getString("city"),
							rs.getString("reg_nr"),
							rs.getString("street"),
							rs.getString("post_ind"),
							rs.getString("phone"),
							rs.getString("cont_person"),
							rs.getString("mcc"),
							rs.getString("p_cntry"),
							rs.getString("p_city"),
							rs.getString("p_street"),
							rs.getString("p_post_ind"),
							rs.getString("mrc_phone"),
							rs.getString("report_crit"),
							rs.getString("e_mail"),
							rs.getString("add_info"),
							rs.getString("report_crit2"),
							rs.getString("user_field"),
							rs.getString("action"),
							rs.getString("acc")
						));
				
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	
	public static String getLastFileName()
	{
		Connection c = null;
		String lastFileName = "";
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select res.f_date, decode(res.tab, " +
"'a1', (select a.filename from BF_GLOBUZ_AGREEMENTS_ALL a where a.file_date = res.f_date and rownum = 1), " +
"'a2', (select a.filename from BF_GLOBUZ_AGR_ON_US_ALL a where a.file_date = res.f_date and rownum = 1),  " +
"'a3', (select a.filename from bf_globuz_merchants_all a where a.file_date = res.f_date and rownum = 1),  " +
"'a4', (select a.filename from BF_GLOBUZ_ACC_TR_ALL a where a.file_date = res.f_date and rownum = 1) " +
") name from " +
"(select * from( " +
"(select * from  " +
"(select max(a1.file_date) f_date, 'a1' tab from BF_GLOBUZ_AGREEMENTS_ALL a1 union all " +
"select max(a2.file_date) f_date, 'a2' tab from BF_GLOBUZ_AGR_ON_US_ALL a2 union all " +
"select max(a3.file_date) f_date, 'a3' tab from bf_globuz_merchants_all a3 union all " +
"select max(a4.file_date) f_date, 'a4' tab from BF_GLOBUZ_ACC_TR_ALL a4) order by f_date desc)) " +
"where rownum = 1)res");
			
			while (rs.next())
			{
				lastFileName = rs.getString("name");
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return lastFileName;
	}
	
	public static String createFileName()
	{
		Connection c = null;
		String FileName = "";
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select max(t.filename) lastFileName from BF_GLOBUZ_MERCHANTS_ALL t " +
					"where t.filename is not null and t.file_date = trunc(sysdate)");
			while (rs.next())
			{
				FileName = rs.getString("newFileName");
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return FileName;
	}
	
	private static String getCond(List<FilterField> flfields)
	{
		if (flfields.size() > 0)
		{
			return " and ";
		}
		else return " where ";
	}
	
	public static Merchant getMerchantDetails(String NIBBD, String alias, String branch)
	{
		Merchant merchant = new Merchant();
		Connection c = null;
		String sql = "select * from client_j t where t.state = 2 and t.id = ?";
		
		String _merchant = "";
		String _parent = "";
		
		//_merchant = "J" + ConnectionPool.getValue("HO", alias).substring(1, 5) + NIBBD + "01";
		_merchant = "J" + branch.substring(1, 5) + NIBBD + "01";
		_parent = _merchant;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			ps.setString(1, NIBBD);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				merchant.setMerchant(_merchant);
				merchant.setParent(_parent);
				merchant.setAbrv_name(rs.getString("short_name"));
				merchant.setFax(rs.getString("fax"));
				merchant.setFull_name(rs.getString("name"));
				
				merchant.setCntry("UZB");
				merchant.setCity("Tashkent");
				
				merchant.setReg_nr(rs.getString("number_registration_doc"));
				merchant.setStreet(rs.getString("place_regist_name"));
				
				merchant.setPost_ind("100000");
				
				merchant.setPhone(rs.getString("phone"));
				merchant.setCont_person(rs.getString("place_regist_name"));
				
				merchant.setMcc("");
				merchant.setP_cntry("UZB");
				merchant.setP_city("Tashkent");
				
				merchant.setP_street(rs.getString("place_regist_name"));
				
				merchant.setP_post_ind("100000");
				
				merchant.setMrc_phone(rs.getString("phone"));
				
				merchant.setReport_crit("1");
				
				merchant.setE_mail(rs.getString("email"));
				merchant.setAdd_info(rs.getString("post_address"));
				
				merchant.setReport_crit2("");
				merchant.setUser_field("");
				merchant.setAction("I");
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return merchant;
	}
	
	private static List<FilterField> getFilterFields(MerchantFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getMerchant()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(merchant) like ?", "%" + filter.getMerchant().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getParent()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(parent) like ?", "%" + filter.getParent().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getAbrv_name()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(abrv_name) like ?", "%" + filter.getAbrv_name().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getFax()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(fax) like ?", "%" + filter.getFax().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getFull_name()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(full_name) like ?", "%" + filter.getFull_name().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getCntry()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(cntry) like ?", "%" + filter.getCntry().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getCity()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(city) like ?", "%" + filter.getCity().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getReg_nr()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(reg_nr) like ?", "%" + filter.getReg_nr().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getStreet()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(street) like ?", "%" + filter.getStreet().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getPost_ind()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(post_ind) like ?", "%" + filter.getPost_ind().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getPhone()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(phone) like ?", "%" + filter.getPhone().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getCont_person()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(cont_person) like ?", "%" + filter.getCont_person().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getMcc()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(mcc) like ?", "%" + filter.getMcc().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_cntry()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(p_cntry) like ?", "%" + filter.getP_cntry().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_city()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(p_city) like ?", "%" + filter.getP_city().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_street()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(p_street) like ?", "%" + filter.getP_street().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_post_ind()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(p_post_ind) like ?", "%" + filter.getP_post_ind().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getMrc_phone()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(mrc_phone) like ?", "%" + filter.getMrc_phone().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getReport_crit()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(report_crit) like ?", "%" + filter.getReport_crit().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getE_mail()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(e_mail) like ?", "%" + filter.getE_mail().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getAdd_info()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(add_info) like ?", "%" + filter.getAdd_info().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getReport_crit2()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(report_crit2) like ?", "%" + filter.getReport_crit2().toLowerCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getUser_field()))
		{
			flfields.add(new FilterField(getCond(flfields) + "lower(user_field) like ?", "%" + filter.getUser_field().toLowerCase() + "%"));
		}
		
		// flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(MerchantFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bf_globuz_merchants_all ");
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
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static int getCountNew()
	{
		
		Connection c = null;
		int n = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bf_globuz_merchants_all a where a.action in ('I', 'U') ");
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static List<Merchant> getMerchantsFl(int pageIndex, int pageSize, MerchantFilter filter)
	{
		
		List<Merchant> list = new ArrayList<Merchant>();
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
				list.add(new Merchant(
							rs.getString("merchant"),
							rs.getString("parent"),
							rs.getString("abrv_name"),
							rs.getString("fax"),
							rs.getString("full_name"),
							rs.getString("cntry"),
							rs.getString("city"),
							rs.getString("reg_nr"),
							rs.getString("street"),
							rs.getString("post_ind"),
							rs.getString("phone"),
							rs.getString("cont_person"),
							rs.getString("mcc"),
							rs.getString("p_cntry"),
							rs.getString("p_city"),
							rs.getString("p_street"),
							rs.getString("p_post_ind"),
							rs.getString("mrc_phone"),
							rs.getString("report_crit"),
							rs.getString("e_mail"),
							rs.getString("add_info"),
							rs.getString("report_crit2"),
							rs.getString("user_field"),
							rs.getString("action"),
							rs.getString("acc")
							));
			}throw new SQLException("test69");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			//ISLogger.getTLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public Merchant getMerchant(int merchantId)
	{
		
		Merchant merchant = new Merchant();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_globuz_merchants_all WHERE merchant=?");
			ps.setInt(1, merchantId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				merchant = new Merchant();
				
				merchant.setMerchant(rs.getString("merchant"));
				merchant.setParent(rs.getString("parent"));
				merchant.setAbrv_name(rs.getString("abrv_name"));
				merchant.setFax(rs.getString("fax"));
				merchant.setFull_name(rs.getString("full_name"));
				merchant.setCntry(rs.getString("cntry"));
				merchant.setCity(rs.getString("city"));
				merchant.setReg_nr(rs.getString("reg_nr"));
				merchant.setStreet(rs.getString("street"));
				merchant.setPost_ind(rs.getString("post_ind"));
				merchant.setPhone(rs.getString("phone"));
				merchant.setCont_person(rs.getString("cont_person"));
				merchant.setMcc(rs.getString("mcc"));
				merchant.setP_cntry(rs.getString("p_cntry"));
				merchant.setP_city(rs.getString("p_city"));
				merchant.setP_street(rs.getString("p_street"));
				merchant.setP_post_ind(rs.getString("p_post_ind"));
				merchant.setMrc_phone(rs.getString("mrc_phone"));
				merchant.setReport_crit(rs.getString("report_crit"));
				merchant.setE_mail(rs.getString("e_mail"));
				merchant.setAdd_info(rs.getString("add_info"));
				merchant.setReport_crit2(rs.getString("report_crit2"));
				merchant.setUser_field(rs.getString("user_field"));
				merchant.setAcc(rs.getString("acc"));
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return merchant;
	}
	
	public static Res create(Merchant merchant)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			
			c = ConnectionPool.getConnection();
			
			
			PreparedStatement checkPs = c.prepareStatement("select merchant from bf_globuz_merchants_all where merchant = ?");
			checkPs.setString(1, merchant.getMerchant());
			ResultSet checkRs = checkPs.executeQuery();
			
			if(checkRs.next()) {
				String currentId = merchant.getMerchant();
				merchant.setMerchant(currentId.substring(0, 13) + "02");
				merchant.setParent(currentId.substring(0, 13) + "02");
			}
			
			
			
			ps = c.prepareStatement("INSERT INTO bf_globuz_merchants_all (merchant, parent, abrv_name, fax, full_name, cntry, city, reg_nr, street, post_ind, phone, cont_person, mcc, p_cntry, p_city, p_street" +
					", p_post_ind, mrc_phone, report_crit, e_mail" +
					", add_info, report_crit2, user_field, trdt, action, acc ) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, merchant.getMerchant());
			ps.setString(2, merchant.getParent());
			ps.setString(3, merchant.getAbrv_name());
			ps.setString(4, merchant.getFax());
			ps.setString(5, merchant.getFull_name());
			ps.setString(6, merchant.getCntry());
			ps.setString(7, merchant.getCity());
			ps.setString(8, merchant.getReg_nr());
			ps.setString(9, merchant.getStreet());
			ps.setString(10, merchant.getPost_ind());
			ps.setString(11, merchant.getPhone());
			ps.setString(12, merchant.getCont_person());
			ps.setString(13, merchant.getMcc());
			ps.setString(14, merchant.getP_cntry());
			ps.setString(15, merchant.getP_city());
			ps.setString(16, merchant.getP_street());
			ps.setString(17, merchant.getP_post_ind());
			ps.setString(18, merchant.getMrc_phone());
			ps.setString(19, merchant.getReport_crit());
			ps.setString(20, merchant.getE_mail());
			ps.setString(21, merchant.getAdd_info());
			ps.setString(22, merchant.getReport_crit2());
			ps.setString(23, merchant.getUser_field());
			ps.setDate(24, new java.sql.Date(new Date().getTime()));
			ps.setString(25, merchant.getAction());
			ps.setString(26, merchant.getAcc());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("Успешно");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("e.getErrorCode() => "  + e.getErrorCode());
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static void update(Merchant merchant)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE bf_globuz_merchants_all SET merchant=?, parent=?, abrv_name=?, fax=?, full_name=?, cntry=?, city=?, reg_nr=?, street=?, post_ind=?, phone=?, cont_person=?, mcc=?, p_cntry=?, p_city=?, p_street=?, p_post_ind=?, mrc_phone=?, report_crit=?, e_mail=?, add_info=?, report_crit2=?, user_field=?, trdt=?, action=?, acc=?  WHERE merchant=?");
			
			ps.setString(1, merchant.getMerchant());
			ps.setString(2, merchant.getParent());
			ps.setString(3, merchant.getAbrv_name());
			ps.setString(4, merchant.getFax());
			ps.setString(5, merchant.getFull_name());
			ps.setString(6, merchant.getCntry());
			ps.setString(7, merchant.getCity());
			ps.setString(8, merchant.getReg_nr());
			ps.setString(9, merchant.getStreet());
			ps.setString(10, merchant.getPost_ind());
			ps.setString(11, merchant.getPhone());
			ps.setString(12, merchant.getCont_person());
			ps.setString(13, merchant.getMcc());
			ps.setString(14, merchant.getP_cntry());
			ps.setString(15, merchant.getP_city());
			ps.setString(16, merchant.getP_street());
			ps.setString(17, merchant.getP_post_ind());
			ps.setString(18, merchant.getMrc_phone());
			ps.setString(19, merchant.getReport_crit());
			ps.setString(20, merchant.getE_mail());
			ps.setString(21, merchant.getAdd_info());
			ps.setString(22, merchant.getReport_crit2());
			ps.setString(23, merchant.getUser_field());
			ps.setDate(24, new java.sql.Date(new Date().getTime()));
			ps.setString(25, merchant.getAction());
			ps.setString(26, merchant.getAcc());
			ps.setString(27, merchant.getMerchant());
			ps.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
	}
	
	public static Res updateSts(List<Merchant> merchant, String FileName, String action)
	{
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps;
		
		try
		{
			c = ConnectionPool.getConnection();
			for (int i = 0; i < merchant.size(); i++)
			{
				ps = c.prepareStatement("UPDATE bf_globuz_merchants_all SET action=?, filename=?, file_date=? " +
						"WHERE merchant=?");
				
				ps.setString(1, action);
				ps.setString(2, FileName);
				ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
				ps.setString(4, merchant.get(i).getMerchant());
				
				ps.executeUpdate();
				c.commit();
			}
			res.setCode(1);
			res.setName(FileName + " sent");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getMessage());			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static void remove(Merchant merchant)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM bf_globuz_merchants_all WHERE MERCHANT=?");
			ps.setString(1, merchant.getMerchant());
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}
	
	public static HashMap<String, String> getActionDesc(String alias)
	{
		HashMap<String, String> _actionDesc = new HashMap<String, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			String sql = "select t.state data, t.desc_s label from BF_GLOBUZ_STATES t";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				_actionDesc.put(rs.getString("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return _actionDesc;
	}
	
	public static List<RefData> getListMcc(String branch)
	{
		// return com.is.utils.RefDataService.getRefData("select t.mcc data, t.mcc_name_ru label from bf_globuz_mcc_codes t order by 2", branch);
		return com.is.utils.RefDataService.getRefData("select t.mcc data, t.label label from ss_empc_merch_mcc t order by 1", branch);	
	}
	
	public static List<Cust> findClients(String alias, String name)
	{
		
		List<Cust> list = new ArrayList<Cust>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select t.id, t.name from client_j t " +
					"where t.state = 2 and lower(t.name) like '%" + name.toLowerCase() + "%' order by 2");
			
			while (rs.next())
			{
				cust = new Cust();
				cust.setNibbd(rs.getString("id"));
				cust.setFull_Name(rs.getString("name"));
				list.add(cust);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return list;
		
	} 


	public static String getNameCountry(String code)
	{
		Connection c = null;
		String nameCountry = "";
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select alpha_3 from s_str where code_str = ?");
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				nameCountry = rs.getString("alpha_3");
			}		
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return nameCountry;
	}
}
