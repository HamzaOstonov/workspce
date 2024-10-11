package accounting_transaction_2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Util_DAO
{
	private static HashMap<String, String> schemas = null;
	private static SimpleDateFormat sdf_doaction = new SimpleDateFormat("dd.MM.yyyy");
	private PreparedStatement ps_get_oper_date = null;
	private ResultSet rs_get_oper_date = null;
	private CallableStatement ps_get_name_and_inn = null;
	private CallableStatement ps_get_deal_general = null;
	private CallableStatement cs_clearparam = null;
	private CallableStatement cs_setparam = null;
	private CallableStatement cs_getparam = null;
	private CallableStatement cs_doaction = null;
	public Statement st =null;
	private HashMap<String, Date> oper_dates = new HashMap<String, Date>();
	//private 
	
	private Connection c;
	
	public Util_DAO(Connection c) throws SQLException
	{
		this.c = c;
		if(this.schemas == null) fill_shemas();
		ps_get_oper_date = this.c.prepareStatement("select info.GetDay oper_date from dual");
		ps_get_name_and_inn = this.c.prepareCall("{? = call kernel.getnameandinn(?, ?) }");
		cs_clearparam = this.c.prepareCall("{ call param.ClearAll() }");
		cs_getparam = this.c.prepareCall("{? = call param.GetParam() }");
		cs_getparam.registerOutParameter(1, java.sql.Types.VARCHAR);
		cs_setparam = this.c.prepareCall("{ call param.SetParam(?, ?) }");
		cs_doaction = this.c.prepareCall("{ call kernel.DoAction(?, ?, ?) }");
		st = this.c.createStatement();
	}
	
	public void setparam(String param_name, String value) throws SQLException
	{
		cs_setparam.setString(1, param_name);
		cs_setparam.setString(2, value);
		cs_setparam.addBatch();
	}
	public void setparam(String param_name, Long value) throws SQLException
	{
		cs_setparam.setString(1, param_name);
		cs_setparam.setLong(2, value);
		cs_setparam.addBatch();
	}
	public void setparam(String param_name, Integer value) throws SQLException
	{
		cs_setparam.setString(1, param_name);
		cs_setparam.setInt(2, value);
		cs_setparam.addBatch();
	}
	public void setparam(String param_name, Date value) throws SQLException
	{
		cs_setparam.setString(1, param_name);
		cs_setparam.setString(2, sdf_doaction.format(value));
		cs_setparam.addBatch();
	}
	
	public String getparam_str(String param_name) throws SQLException
	{
		cs_getparam.setString(2, param_name);
		cs_getparam.execute();
		return cs_getparam.getString(1);
	}
	public Date getparam_date(String param_name) throws SQLException, ParseException
	{
		cs_getparam.setString(2, param_name);
		cs_getparam.execute();
		return sdf_doaction.parse(cs_getparam.getString(1));
	}
	
	public void clearparam() throws SQLException
	{
		cs_clearparam.execute();
		cs_setparam.clearBatch();
	}
	
	public void execute_setparam_batch() throws SQLException
	{
		cs_setparam.executeBatch();
	}
	
	public void doaction(Long group_id, Integer a_deal_type_id, Integer a_action_id) throws SQLException
	{
		try
		{
		cs_doaction.setLong(1, group_id);
		cs_doaction.setInt(2, a_deal_type_id);
		cs_doaction.setInt(3, a_action_id);
		cs_doaction.execute();
		}catch(SQLException e)
		{
			throw e;
		}
	}
	
	public Date get_oper_date(String branch) throws SQLException
	{
		if(oper_dates.containsKey(branch)) return oper_dates.get(branch);
		try
		{
			rs_get_oper_date = ps_get_oper_date.executeQuery();
			if(rs_get_oper_date.next())
			{
				oper_dates.put(branch, rs_get_oper_date.getDate("oper_date"));
				return rs_get_oper_date.getDate("oper_date");
			}
			else throw new IllegalArgumentException("oper date not found");
		}
		finally
		{
			if(rs_get_oper_date != null) try{rs_get_oper_date.close();} catch(Exception e) {}
		}
	}
	
	private void fill_shemas() throws SQLException
	{
		PreparedStatement ps_get_schemas = null;
		ResultSet rs_get_schemas = null;
		try
		{
			ps_get_schemas = c.prepareStatement("select b.branch, b.user_name from ss_dblink_branch b");
			rs_get_schemas = ps_get_schemas.executeQuery();
			this.schemas = new HashMap<String, String>();
			while(rs_get_schemas.next())
			{
				this.schemas.put(rs_get_schemas.getString("branch"), rs_get_schemas.getString("user_name"));
			}
		}
		finally
		{
			if(ps_get_schemas != null) try{ps_get_schemas.close();} catch(Exception e) {}
			if(rs_get_schemas != null) try{rs_get_schemas.close();} catch(Exception e) {}
		}
	}
	
	public String get_schema_name(String branch)
	{
		String res = this.schemas.get(branch);
		if(res == null) 
			throw new NoSuchElementException("schema name for "+branch+" not found"); 
		return res;
	}
	
	public String get_name_and_inn(String branch, String account, String current_branch) throws SQLException
	{
		try{
		//if(!branch.equals(current_branch))
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+this.get_schema_name(branch));
		//ps_get_name_and_inn = this.c.prepareCall("{? = call kernel.getnameandinn(?, ?) }");
		ps_get_name_and_inn.registerOutParameter(1, java.sql.Types.VARCHAR);
		ps_get_name_and_inn.setString(2, account);
		ps_get_name_and_inn.setString(3, branch);
		ps_get_name_and_inn.execute();
	    String res =ps_get_name_and_inn.getString(1);
	    //if(!branch.equals(current_branch))
	    	st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+this.get_schema_name(current_branch));
	    if(res != null)
	    	return res;
	    else
	    	throw new IllegalArgumentException("could not get name and inn for account "+account+" in "+branch+" branch");
		}catch(SQLException e){System.out.println(this.get_schema_name(branch)); throw e;}
	}
	
	public Long get_deal_general(String type_doc, String bank_cl, String bank_co) throws SQLException
	{
		
		try
		{
			Long res = 0l;
			
			ps_get_deal_general = this.c.prepareCall("{? = call kernel.GetDealGeneral(?, ?, ?, null) }");
			
			ps_get_deal_general.registerOutParameter(1, java.sql.Types.NUMERIC);
			ps_get_deal_general.setString(2, type_doc);
			ps_get_deal_general.setString(3, bank_cl);
			ps_get_deal_general.setString(4, bank_co);
			ps_get_deal_general.execute();
			res = Long.valueOf(ps_get_deal_general.getLong(1));
		    if(res != null)
		    	return res;
		    else
		    	throw new IllegalArgumentException(
		    			"could not get deal general for type doc "+type_doc+
		    			" bank_cl "+bank_cl+
		    			" bank_co "+bank_co+" branch");
		}
		finally
		{
			try{ps_get_deal_general.close();}catch(Exception e){}
		}
	}
	
	
	
	public void close()
	{
		if(ps_get_oper_date != null) try{ps_get_oper_date.close();} catch(Exception e) {}
		if(ps_get_name_and_inn != null) try{ps_get_name_and_inn.close();} catch(Exception e) {}
		if(ps_get_deal_general != null) try{ps_get_deal_general.close();} catch(Exception e) {}
		if(cs_clearparam != null) try{cs_clearparam.close();} catch(Exception e) {}
		if(cs_getparam != null) try{cs_getparam.close();} catch(Exception e) {}
		if(cs_setparam != null) try{cs_setparam.close();} catch(Exception e) {}
		if(cs_doaction != null) try{cs_doaction.close();} catch(Exception e) {}
		if(st != null) try{st.close();} catch(Exception e) {}
	}
}
