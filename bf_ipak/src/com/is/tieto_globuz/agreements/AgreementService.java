package com.is.tieto_globuz.agreements;

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



public class AgreementService 
{

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" order by id desc ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM bf_globuz_agreement ";
	private static Res res = new Res();

	public static List<RefData> getMerchantId(String alias)
	{
		return com.is.utils.RefDataService.getRefData("select t.merchant data, t.full_name label from BF_GLOBUZ_MERCHANTS_ALL t", alias);
	}
	
	public static List<RefData> getCardType(String alias)
	{
		return com.is.utils.RefDataService.getRefData("select t.type data, t.label label from ss_empc_card_type t", alias);
	}	
	
	public static List<RefData> getAgrTypes(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.id data, t.agreement_type label from ss_globuz_agr_type t", alias);
	}
	
	public static List<RefData> getGlobuzStates(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.state data, t.desc_s label from BF_GLOBUZ_STATES t", alias);
	}

    public static WithMerchant getWithMerchant(String alias, String id)  
    {
        WithMerchant withMerchant = null;
        Connection c = null;

        try 
        {
            c = ConnectionPool.getConnection(alias);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_GLOBUZ_AGREEMENTS_ALL where id_agreement = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	withMerchant = new WithMerchant(
                                rs.getString("card_type"),
                                rs.getString("merchant"),
                                rs.getString("acq_bank"),
                                rs.getString("acq_branch"),
                                rs.getString("tr_ccy"),
                                rs.getString("imprint_fee"),
                                rs.getString("imprint_min"),
                                rs.getString("imprint_max"),
                                rs.getString("pos_fee"),
                                rs.getString("pos_min"),
                                rs.getString("pos_max"),
                                rs.getString("cashback_fee"),
                                rs.getString("casback_min"),
                                rs.getString("cashback_max"),
                                rs.getString("atm_fee"),
                                rs.getString("atm_min"),
                                rs.getString("atm_max"),
                                rs.getString("pos_limit"),
                                rs.getString("pos_limit_act"),
                                rs.getString("imprint_limit"),
                                rs.getString("status"),
                                rs.getString("agr_number"),
                                rs.getDate("agr_date"),
                                rs.getString("algorithm"),
                                rs.getString("order_period"),
                                rs.getString("bank_pos_fee"),
                                rs.getString("bank_pos_min"),
                                rs.getString("bank_pos_max"),
                                rs.getString("bank_imp_fee"),
                                rs.getString("bank_imp_min"),
                                rs.getString("bank_imp_max"),
                                rs.getString("bank_atm_fee"),
                                rs.getString("bank_atm_min"),
                                rs.getString("bank_atm_max"),
                                rs.getString("bank_algorithm"),
                                rs.getString("bank_calc_mode"),
                                rs.getString("bank_account"),
                                rs.getString("action"),
                                rs.getLong("id_agreement"));
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
        
        return withMerchant;
    }
    
    public List<Agreement> getAgreement()  
    {
        List<Agreement> list = new ArrayList<Agreement>();
        Connection c = null;

        try 
        {
            c = ConnectionPool.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM bf_globuz_agreement");
            while (rs.next()) 
            {
                list.add(new Agreement(
                                rs.getLong("id"),
                                rs.getString("merchant"),
                                rs.getString("agreement_type"),
                                rs.getString("action")));
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
  
    private static String getCond(List<FilterField> flfields)
    {
        if(flfields.size()>0)
        {
            return " and ";
        }
        else
        {
        	return " where ";
        }
    }

    private static List<FilterField> getFilterFields(AgreementFilter filter)
    {
	    List<FilterField> flfields = new ArrayList<FilterField>();
	
		

		if(!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getMerchant())) {
			flfields.add(new FilterField(getCond(flfields)+ "merchant=?",filter.getMerchant()));
		}
		if(!CheckNull.isEmpty(filter.getAgreement_type())) {
			flfields.add(new FilterField(getCond(flfields)+ "agreement_type=?",filter.getAgreement_type()));
		}
		if(!CheckNull.isEmpty(filter.getAction())) {
			flfields.add(new FilterField(getCond(flfields)+ "action=?",filter.getAction()));
		}

	
		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
	
		return flfields;
    }

    public static int getCount(AgreementFilter filter)  
    {
	    Connection c = null;
	    int n = 0;
	    List<FilterField> flFields =getFilterFields(filter);
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT count(*) ct FROM bf_globuz_agreement");
	    if(flFields.size()>0)
	    {	
            for(int i=0;i<flFields.size();i++)
            {
                sql.append(flFields.get(i).getSqlwhere());
            }
	    }
	    try 
	    {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql.toString());

            for(int k=0;k<flFields.size();k++)
            {
            	ps.setObject(k+1, flFields.get(k).getColobject());
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

    public static List<Agreement> getAgreementFl(int pageIndex, int pageSize, AgreementFilter filter)  
    {
        List<Agreement> list = new ArrayList<Agreement>();
        Connection c = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields =getFilterFields(filter);

        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
        if(flFields.size()>0)
        {
            for(int i=0;i<flFields.size();i++)
            {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        sql.append(psql2);

        try 
        {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql.toString());
            for(params=0;params<flFields.size();params++)
            {
            	ps.setObject(params+1, flFields.get(params).getColobject());
            }
            params++;
            ps.setInt(params++,v_upperbound);
            ps.setInt(params++,v_lowerbound);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
	            list.add(new Agreement(
	                            rs.getLong("id"),
	                            rs.getString("merchant"),
	                            rs.getString("agreement_type"),
	                            rs.getString("action")));
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

	public static void create(WithMerchant agreement)
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("INSERT INTO BF_GLOBUZ_AGREEMENTS_ALL (CARD_TYPE, MERCHANT, ACQ_BANK, ACQ_BRANCH, TR_CCY, " +
					"IMPRINT_FEE, IMPRINT_MIN, IMPRINT_MAX, POS_FEE, POS_MIN, POS_MAX, CASHBACK_FEE, CASBACK_MIN, CASHBACK_MAX, " +
					"ATM_FEE, ATM_MIN, ATM_MAX, POS_LIMIT, POS_LIMIT_ACT, IMPRINT_LIMIT, " +
					"STATUS, AGR_NUMBER, AGR_DATE, ALGORITHM, ORDER_PERIOD, BANK_POS_FEE, BANK_POS_MIN, BANK_POS_MAX, BANK_IMP_FEE, " +
					"BANK_IMP_MIN, BANK_IMP_MAX, BANK_ATM_FEE, BANK_ATM_MIN, BANK_ATM_MAX, " +
					"BANK_ALGORITHM, BANK_CALC_MODE, BANK_ACCOUNT, ACTION, TRDT, id_agreement) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, agreement.getCard_type());
			ps.setString(2, agreement.getMerchant());
			ps.setString(3, agreement.getAcq_bank());
			ps.setString(4, agreement.getAcq_branch());
			ps.setString(5, agreement.getTr_ccy());
			ps.setString(6, agreement.getImprint_fee());
			ps.setString(7, agreement.getImprint_min());
			ps.setString(8, agreement.getImprint_max());
			ps.setString(9, agreement.getPos_fee());
			ps.setString(10, agreement.getPos_min());
			ps.setString(11, agreement.getPos_max());			
			ps.setString(12, agreement.getCashback_fee());
			ps.setString(13, agreement.getCasback_min());
			ps.setString(14, agreement.getCashback_max());			
			ps.setString(15, agreement.getAtm_fee());
			ps.setString(16, agreement.getAtm_min());
			ps.setString(17, agreement.getAtm_max());			
			ps.setString(18, agreement.getPos_limit());
			ps.setString(19, agreement.getPos_limit_act());
			ps.setString(20, agreement.getImprint_limit());			
			ps.setString(21, agreement.getStatus());
			ps.setString(22, agreement.getAgr_number());
			ps.setDate(23, new java.sql.Date(agreement.getAgr_date().getTime()));
			ps.setString(24, agreement.getAlgorithm());			
			ps.setString(25, agreement.getOrder_period());
			ps.setString(26, agreement.getBank_pos_fee());
			ps.setString(27, agreement.getBank_pos_min());
			ps.setString(28, agreement.getBank_pos_max());			
			ps.setString(29, agreement.getBank_imp_fee());
			ps.setString(30, agreement.getBank_imp_min());
			ps.setString(31, agreement.getBank_imp_max());			
			ps.setString(32, agreement.getBank_atm_fee());
			ps.setString(33, agreement.getBank_atm_min());
			ps.setString(34, agreement.getBank_atm_max());			
			ps.setString(35, agreement.getBank_algorithm());
			ps.setString(36, agreement.getBank_calc_mode());
			ps.setString(37, agreement.getBank_account());
			ps.setString(38, agreement.getAction());
			ps.setDate(39, new java.sql.Date(new Date().getTime()));
			ps.setString(40, lastAgrId());
			
			ps.executeUpdate();
			c.commit();
			
			res.setCode(1);
			res.setName("");
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}

	
    public static void update(WithMerchant maincurrency)  
    {
        Connection c = null;

        try 
        {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE BF_GLOBUZ_AGREEMENTS_ALL SET card_type=?, merchant=?, acq_bank=?, acq_branch=?, tr_ccy=?, imprint_fee=?, imprint_min=?, imprint_max=?, pos_fee=?, pos_min=?, pos_max=?, cashback_fee=?, casback_min=?, cashback_max=?, atm_fee=?, atm_min=?, atm_max=?, pos_limit=?, pos_limit_act=?, imprint_limit=?, status=?, agr_number=?, agr_date=?, algorithm=?, order_period=?, bank_pos_fee=?, bank_pos_min=?, bank_pos_max=?, bank_imp_fee=?, bank_imp_min=?, bank_imp_max=?, bank_atm_fee=?, bank_atm_min=?, bank_atm_max=?, bank_algorithm=?, bank_calc_mode=?, bank_account=? WHERE card_type = ? AND merchant = ? AND acq_bank = ? AND acq_branch = ? AND tr_ccy = ?");
            
            ps.setString(1,maincurrency.getCard_type());
            ps.setString(2,maincurrency.getMerchant());
            ps.setString(3,maincurrency.getAcq_bank());
            ps.setString(4,maincurrency.getAcq_branch());
            ps.setString(5,maincurrency.getTr_ccy());
            ps.setString(6,maincurrency.getImprint_fee());
            ps.setString(7,maincurrency.getImprint_min());
            ps.setString(8,maincurrency.getImprint_max());
            ps.setString(9,maincurrency.getPos_fee());
            ps.setString(10,maincurrency.getPos_min());
            ps.setString(11,maincurrency.getPos_max());
            ps.setString(12,maincurrency.getCashback_fee());
            ps.setString(13,maincurrency.getCasback_min());
            ps.setString(14,maincurrency.getCashback_max());
            ps.setString(15,maincurrency.getAtm_fee());
            ps.setString(16,maincurrency.getAtm_min());
            ps.setString(17,maincurrency.getAtm_max());
            ps.setString(18,maincurrency.getPos_limit());
            ps.setString(19,maincurrency.getPos_limit_act());
            ps.setString(20,maincurrency.getImprint_limit());
            ps.setString(21,maincurrency.getStatus());
            ps.setString(22,maincurrency.getAgr_number());
            ps.setDate(23, new java.sql.Date(maincurrency.getAgr_date().getTime()));
            ps.setString(24,maincurrency.getAlgorithm());
            ps.setString(25,maincurrency.getOrder_period());
            ps.setString(26,maincurrency.getBank_pos_fee());
            ps.setString(27,maincurrency.getBank_pos_min());
            ps.setString(28,maincurrency.getBank_pos_max());
            ps.setString(29,maincurrency.getBank_imp_fee());
            ps.setString(30,maincurrency.getBank_imp_min());
            ps.setString(31,maincurrency.getBank_imp_max());
            ps.setString(32,maincurrency.getBank_atm_fee());
            ps.setString(33,maincurrency.getBank_atm_min());
            ps.setString(34,maincurrency.getBank_atm_max());
            ps.setString(35,maincurrency.getBank_algorithm());
            ps.setString(36,maincurrency.getBank_calc_mode());
            ps.setString(37,maincurrency.getBank_account());
            
            ps.setString(38,maincurrency.getCard_type());
            ps.setString(39,maincurrency.getMerchant());
            ps.setString(40,maincurrency.getAcq_bank());
            ps.setString(41,maincurrency.getAcq_branch());
            ps.setString(42,maincurrency.getTr_ccy());
            
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
    
    public static boolean checkForUniqueness(String card_type, String merchant, String acq_bank, String acq_branch, String tr_ccy)
    {
        Connection c = null;
        boolean isUniq = false;

        try 
        {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT card_type FROM BF_GLOBUZ_AGREEMENTS_ALL WHERE card_type = ? AND merchant = ? AND acq_bank = ? AND acq_branch = ? AND tr_ccy = ?");
            
            ps.setString(1, card_type);
            ps.setString(2, merchant);
            ps.setString(3, acq_bank);
            ps.setString(4, acq_branch);
            ps.setString(5, tr_ccy);

            ResultSet rs = ps.executeQuery();
            if(rs.next() == false)
            {
            	isUniq = true;
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
        
        return isUniq;
    }
	
	public static List<WithMerchant> getAgreements4Send()
	{
		List<WithMerchant> list = new ArrayList<WithMerchant>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from BF_GLOBUZ_AGREEMENTS_ALL a where a.action in ('I', 'U')");
			while (rs.next())
			{
				list.add(new WithMerchant(
					rs.getString("card_type"),
					rs.getString("merchant"),
					rs.getString("acq_bank"),
					rs.getString("acq_branch"),
					rs.getString("tr_ccy"),
					rs.getString("imprint_fee"),
					rs.getString("imprint_min"),
					rs.getString("imprint_max"),
					rs.getString("pos_fee"),
					rs.getString("pos_min"),
					rs.getString("pos_max"),
					rs.getString("cashback_fee"),
					rs.getString("casback_min"),
					rs.getString("cashback_max"),
					rs.getString("atm_fee"),
					rs.getString("atm_min"),
					rs.getString("atm_max"),
					rs.getString("pos_limit"),
					rs.getString("pos_limit_act"),
					rs.getString("imprint_limit"),
					rs.getString("status"),
					rs.getString("agr_number"),
					rs.getDate("agr_date"),
					rs.getString("algorithm"),
					rs.getString("order_period"),
					rs.getString("bank_pos_fee"),
					rs.getString("bank_pos_min"),
					rs.getString("bank_pos_max"),
					rs.getString("bank_imp_fee"),
					rs.getString("bank_imp_min"),
					rs.getString("bank_imp_max"),
					rs.getString("bank_atm_fee"),
					rs.getString("bank_atm_min"),
					rs.getString("bank_atm_max"),
					rs.getString("bank_algorithm"),
					rs.getString("bank_calc_mode"),
					rs.getString("bank_account"),
					rs.getString("action"),
					rs.getLong("id_agreement")
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
	
	
	public static List<On_us> getOn_us4Send()
	{
		List<On_us> list = new ArrayList<On_us>();
		Connection c = null;
		Statement s = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from BF_GLOBUZ_AGR_ON_US_ALL a where a.action in ('I', 'U')");
			while (rs.next())
			{
				list.add(new On_us(
					rs.getLong("id"),
					rs.getString("card_type"),
					rs.getString("merchant"),
					rs.getString("iss_cmi"),
					rs.getString("imprint_fee"),
					rs.getString("imprint_min"),
					rs.getString("imprint_max"),
					rs.getString("pos_fee"),
					rs.getString("pos_min"),
					rs.getString("pos_max"),
					rs.getString("cashback_fee"),
					rs.getString("cashback_min"),
					rs.getString("cashback_max"),
					rs.getString("atm_fee"),
					rs.getString("atm_min"),
					rs.getString("atm_max"),
					rs.getString("tr_ccy"),
					rs.getString("bin"),
					rs.getString("algorithm"),
					rs.getString("action"),
					rs.getLong("id_agreement")
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
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		
		return list;		
	}
	
	
	public static HashMap<String, String> getAgrTypeDesc(String alias)
	{
		HashMap<String, String> _agreementType = new HashMap<String, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			String sql = "select t.id data, t.agreement_type label from ss_globuz_agr_type t";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				_agreementType.put(rs.getString("data"), rs.getString("label"));
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
		return _agreementType;
	}
	
	
	
	
	public static void addAgreement(Agreement agreement)
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("INSERT INTO bf_globuz_agreement (id, MERCHANT, agreement_type, action) " +
					"VALUES (seq_bf_globuz_agreement.nextval, ?, ?, ?)");
			
			ps.setString(1, agreement.getMerchant());
			ps.setString(2, agreement.getAgreement_type());
			ps.setString(3, agreement.getAction());
			
			ps.executeUpdate();
			c.commit();
			
			res.setCode(1);
			res.setName("");
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}
	
	
	private static String lastAgrId() {
		String lastId = null;
		
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("select Max(id)lastId from bf_globuz_agreement");
			if(rs.next()) {
				lastId = rs.getString("lastId");
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
		
		return lastId;
	}
	
	
	
	
	public static void createOnUs(String alias, On_us agreement)
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO BF_GLOBUZ_AGR_ON_US_ALL (id, CARD_TYPE, MERCHANT, "
					+ "iss_cmi, imprint_fee, imprint_min, imprint_max, pos_fee, pos_min, pos_max, cashback_fee, "
					+ "cashback_min, cashback_max, atm_fee, atm_min, atm_max, tr_ccy, bin, algorithm, action, "
					+ "trdt, id_agreement )"
					+ " VALUES (seq_BF_GLOBUZ_AGR_ON_US_ALL.nextval,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?)");
			
			ps.setString(1, agreement.getCard_type());
			ps.setString(2, agreement.getMerchant());
			ps.setString(3, agreement.getIss_cmi());
			ps.setString(4, agreement.getImprint_fee());
			ps.setString(5, agreement.getImprint_min());
			ps.setString(6, agreement.getImprint_max());
			ps.setString(7, agreement.getPos_fee());
			ps.setString(8, agreement.getPos_min());
			ps.setString(9, agreement.getPos_max());			
			ps.setString(10, agreement.getCashback_fee());
			ps.setString(11, agreement.getCashback_min());
			ps.setString(12, agreement.getCashback_max());			
			ps.setString(13, agreement.getAtm_fee());
			ps.setString(14, agreement.getAtm_min());
			ps.setString(15, agreement.getAtm_max());			
			ps.setString(16, agreement.getTr_ccy());
			ps.setString(17, agreement.getBin());
			ps.setString(18, agreement.getAlgorithm());			
			ps.setString(19, agreement.getAction());
			ps.setDate(20, new java.sql.Date(new Date().getTime()));
			ps.setString(21, lastAgrId());
			
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
	
	
    public static On_us getOn_us(String alias, String id)  
    {
        On_us onUs = null;
        Connection c = null;

        try 
        {
            c = ConnectionPool.getConnection(alias);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_GLOBUZ_AGR_ON_US_ALL where id_agreement = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	onUs = new On_us(
            					rs.getLong("id"),
                                rs.getString("card_type"),
                                rs.getString("merchant"),
                                rs.getString("iss_cmi"),
                                rs.getString("imprint_fee"),
                                rs.getString("imprint_min"),
                                rs.getString("imprint_max"),
                                rs.getString("pos_fee"),
                                rs.getString("pos_min"),
                                rs.getString("pos_max"),
                                rs.getString("cashback_fee"),
                                rs.getString("cashback_min"),
                                rs.getString("cashback_max"),
                                rs.getString("atm_fee"),
                                rs.getString("atm_min"),
                                rs.getString("atm_max"),
                                rs.getString("tr_ccy"),
                                rs.getString("bin"),
                                rs.getString("algorithm"),
                                rs.getString("action"),
                                rs.getLong("id_agreement"));
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
        
        return onUs;
    }
    
    
	public static Res updateStsOnUs(List<On_us> agreements, String FileName, String action)
	{
		Connection c = null;
		PreparedStatement ps;
		
		try
		{
			c = ConnectionPool.getConnection();
			for (int i = 0; i < agreements.size(); i++)
			{
				ps = c.prepareStatement("UPDATE BF_GLOBUZ_AGR_ON_US_ALL SET action=?, filename=?, file_date=? " +
						"WHERE id=?");
				
				ps.setString(1, action);
				ps.setString(2, FileName);
				ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
				ps.setLong(4, agreements.get(i).getId());
				
				ps.executeUpdate();
				c.commit();
				
				updateSts(agreements.get(i).getId_agreement(), action);
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
	
	public static Res updateStsWithM(List<WithMerchant> agreements, String FileName, String action)
	{
		Connection c = null;
		PreparedStatement ps;
		
		try
		{
			c = ConnectionPool.getConnection();
			for (int i = 0; i < agreements.size(); i++)
			{
				ps = c.prepareStatement("UPDATE bf_globuz_agreements_all SET action=?, filename=?, file_date=? " +
						"WHERE card_type=? and merchant = ? and acq_bank = ? and acq_branch = ? and tr_ccy = ?");
				
				ps.setString(1, action);
				ps.setString(2, FileName);
				ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
				ps.setString(4, agreements.get(i).getCard_type());
				ps.setString(5, agreements.get(i).getMerchant());
				ps.setString(6, agreements.get(i).getAcq_bank());
				ps.setString(7, agreements.get(i).getAcq_branch());
				ps.setString(8, agreements.get(i).getTr_ccy());
				
				ps.executeUpdate();
				c.commit();
				
				updateSts(agreements.get(i).getId_agreement(), action);
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
	
	public static void updateSts(Long id, String action)
	{
		Connection c = null;
		PreparedStatement ps;
		
		try
		{
			c = ConnectionPool.getConnection();
				ps = c.prepareStatement("UPDATE bf_globuz_agreement SET action=? " +
						"WHERE id = ?");
				
				ps.setString(1, action);
				ps.setLong(2, id);
				
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
		
		return;
	}
}
