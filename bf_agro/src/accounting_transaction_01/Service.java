package accounting_transaction_01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
import com.is.currexchange.ExchangeService;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

import general.General;

public class Service
{
	private static SimpleDateFormat sdf_doaction = new SimpleDateFormat("dd.MM.yyyy");
	
	public static HashMap<String, HashMap<Long, Bf_tr_acc>> get_accounts(Connection c) throws Exception
	{
		
		
		HashMap<String, HashMap<Long, Bf_tr_acc>> accounts = new HashMap<String, HashMap<Long, Bf_tr_acc>>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = c.prepareStatement(
					"select * from bf_tr_acc t order by t.branch, t.acc_template_id");
			rs = ps.executeQuery();
			String current_tr_acc_branch = null;
			HashMap<Long,Bf_tr_acc> current_tr_acc = new HashMap<Long, Bf_tr_acc>();
			while (rs.next())
			{
				Bf_tr_acc new_tr_acc = new Bf_tr_acc(
						rs.getLong("id"), 
						rs.getString("branch"), 
						rs.getLong("acc_template_id"), 
						rs.getString("acc_mfo"), 
						rs.getString("account"), 
						rs.getString("acc_name"));
			//	System.out.println(new_tr_acc);
				
				if ((current_tr_acc_branch!=null)&&
						(!(current_tr_acc_branch.equals(new_tr_acc.getBranch()))))
				{
				//	System.out.println("changes"+current_tr_acc_branch+" "+new_tr_acc.getBranch());
					accounts.put(current_tr_acc_branch, current_tr_acc);
					current_tr_acc = new HashMap<Long, Bf_tr_acc>();
					//current_tr_acc_branch = new_tr_acc.getBranch();
				}
					
				current_tr_acc.put(new_tr_acc.getAcc_template_id() ,new_tr_acc);
				current_tr_acc_branch = new_tr_acc.getBranch();
			}
			if (current_tr_acc_branch != null)
				accounts.put(current_tr_acc_branch, current_tr_acc);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		return accounts;
	}
	
	public static HashMap<Long, Bf_tr_paytype> get_bf_tr_paytypes(Connection c) throws Exception
	{
		HashMap<Long, Bf_tr_paytype> paydocs = new HashMap<Long, Bf_tr_paytype>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = c.prepareStatement("select * from bf_tr_paytype");
			rs = ps.executeQuery();
			while(rs.next())
			{
				paydocs.put(rs.getLong("id"), new Bf_tr_paytype(
						rs.getLong("id"), 
						rs.getString("name"), 
						rs.getLong("deal_group_id")
						));
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		return paydocs;
	}
	
	public static HashMap<Long, List<Tr_template>> get_tr_template(Connection c) 
		throws Exception
	{
		HashMap<Long, List<Tr_template>> tr_template = new HashMap<Long, List<Tr_template>>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = c.prepareStatement(
					"select * from bf_tr_template t order by t.operation_id, t.ord");
			rs = ps.executeQuery();
			Long current_operation = null;
			List<Tr_template> current_operation_template = new ArrayList<Tr_template>();
			while (rs.next())
			{
				Tr_template new_template = new Tr_template(
						rs.getLong("id"), 
						rs.getLong("operation_id"), 
						rs.getLong("acc_dt"), 
						rs.getLong("acc_ct"), 
						rs.getString("currency"), 
						rs.getString("doc_type"), 
						rs.getString("cash_code"), 
						rs.getString("purpose"), 
						rs.getString("purpose_code"), 
						rs.getLong("ord"), 
						rs.getLong("id_transh_purp"), 
						rs.getLong("pay_type"), 
						rs.getLong("trans_type"), 
						rs.getDouble("perc_for_tr"), 
						rs.getString("pdc"), 
						rs.getString("amount")
						);
				
				
				if ((current_operation!=null)&&
						(current_operation != new_template.getOperation_id()))
				{
					
					tr_template.put(current_operation, current_operation_template);
					current_operation_template = new ArrayList<Tr_template>();
					//current_operation = new_template.getOperation_id();
				}
					
					current_operation_template.add(new_template);
					current_operation = new_template.getOperation_id();
			}
			if (current_operation != null)
				tr_template.put(current_operation, current_operation_template);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		return tr_template;
	}
	
	public static Long create_tr_pay(Parameters parameters, Connection c) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long tr_pay_id = null;
		try
		{
			ps = c.prepareStatement("select seq_bf_tr_pay.nextval res from dual");
			rs = ps.executeQuery();
			rs.next();
			tr_pay_id = rs.getLong("res");
			ps.close();
			
			ps = c.prepareStatement(
					"insert into bf_tr_pay (id, branch, operation_id, amount, card_acc, " +
					"cur_acc, date_created, parent_group_id, state, sub_id, " +
					"amount_t, subbranch_id) " +
					"values (?, ?, ?, 0, '1', '1', sysdate, ?, 1, 0, 0, '1')");
			ps.setLong(1, tr_pay_id);
			ps.setString(2, (String)parameters.get("branch"));
			ps.setLong(3, (Long)parameters.get("operation_id"));
			ps.setLong(4, (Long)parameters.get("parent_group_id"));
			
			ps.execute();
			c.commit();//-------!!!!!!!!!!!!!!
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return tr_pay_id;
	}
	
	public static Long create_tr_paydoc(Parameters parameters, long ord, Connection c) throws Exception
	{

		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		com.is.ISLogger.getLogger().error("AAAAAAAAAA2 "+parameters.getParametersHashmap().toString());
		try
		{	
			
			//items.forEach((k, v) -> {
	        //    System.out.format("key: %s, value: %d%n", k, v);
	        //});
			
			//try {
			//	str1 = objectMapper.writerWithDefaultPrettyPrinter()
			//			.writeValueAsString(parameters);
			//} catch (Exception e22) {
			//	str1 = " str1=error parameters create_tr_paydoc "+ e22.getMessage();
			//} finally {
			//}
			//com.is.ISLogger.getLogger().error(
			//		"** not err parameters create_tr_paydoc ************** " + str1);

			if (!account_exists((String)parameters.get("BANK_CL"), (String)parameters.get("ACC_CL"), c))
				throw new Account_does_not_exist_exception((String)parameters.get("BANK_CL")+"-"+(String)parameters.get("ACC_CL"));
			if (!account_exists((String)parameters.get("BANK_CO"),(String)parameters.get("ACC_CO"), c))
				throw new Account_does_not_exist_exception((String)parameters.get("BANK_CO")+"-"+(String)parameters.get("ACC_CO"));
			
			ps = c.prepareStatement("select seq_bf_tr_paydocs.nextval res from dual");
			rs = ps.executeQuery();
			rs.next();
			id = rs.getLong("res");
			ps.close();
			
			ps = c.prepareStatement(
					"insert into bf_tr_paydocs " +
					"(id, pay_id, branch, d_date, " +
					"bank_cl, acc_cl, name_cl, bank_co, " +
					"acc_co, name_co, summa, purpose, " +
					"type_doc, pdc, parent_group_id, parent_id, " +
					"cash_code, id_transh_purp, schema_name, ord, " +
					"g_branch, g_docid, purp_code, pay_type, " +
					"trans_type, acc_dt_id, acc_ct_id, deal_group_id, " +
					"deal_id) " +
					"values (" +
					"?, ?, ?, ?, " +
					"?, ?, ?, ?, " +
					"?, ?, ?, ?, " +
					"?, ?, ?, ?, " +
					"?, ?, null, ?, " +
					"null, null, ?, 0, " +
					"0, 0, 0, ?, " +
					"?)");
			
			ps.setLong(1, id);
			ps.setLong(2, (Long)parameters.get("tr_pay_id"));
			ps.setString(3, (String)parameters.get("branch"));
			ps.setDate(4, new java.sql.Date(((java.util.Date)parameters.get("D_DATE")).getTime()));
			ps.setString(5, (String)parameters.get("BANK_CL"));
			ps.setString(6, (String)parameters.get("ACC_CL"));
			ps.setString(7, (String)parameters.get("NAME_CL"));
			ps.setString(8, (String)parameters.get("BANK_CO"));
			ps.setString(9, (String)parameters.get("ACC_CO"));
			ps.setString(10, (String)parameters.get("NAME_CO"));
			ps.setString(11, Long.toString((Long)parameters.get("SUMMA")));
			ps.setString(12, (String)parameters.get("PURPOSE_CODE") + (String)parameters.get("PURPOSE"));
			ps.setString(13, (String)parameters.get("TYPE_DOC"));
			ps.setString(14, (String)parameters.get("PDC"));
			ps.setLong(15, (Long)parameters.get("PARENT_GROUP_ID"));
			ps.setLong(16, (Long)parameters.get("PARENT_ID"));
			ps.setString(17, (String)parameters.get("CASH_CODE"));
			//ps.setString(18, (String)parameters.get("PURPOSE_CODE"));
			ps.setString(18, (String)parameters.get("id_transh"));
			ps.setLong(19, ord);
			ps.setString(20, (String)parameters.get("PURPOSE_CODE"));
			ps.setLong(21, (Long)parameters.get("deal_group_id"));
			if ((Long)parameters.get("deal_group_id") == 3)
			{
				ps.setLong(22, get_deal_general(
						(String)parameters.get("TYPE_DOC"),
						(String)parameters.get("BANK_CL"),
						(String)parameters.get("BANK_CO"),
						c, (CallableStatement)parameters.get("cs_prep")));
			}
			else ps.setLong(22, (Long)parameters.get("deal_id"));
			
			ps.execute();
			c.commit();///-------!!!!!!!!!!!!!!!!!!!!!!!!!!
		}
		catch(Exception e)
		{
			ISLogger.getLogger().info("AAAAAAAAAA3"+CheckNull.getPstr(e));
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return id;
	}
	
	public static String get_name_and_inn(String account, String branch, Connection c) throws Exception
	{
		CallableStatement ps = null;
		ResultSet rs = null;
		String res = null;
		try
		{
			ps = c.prepareCall("{? = call kernel.getnameandinn(?,?) }");
			ps.registerOutParameter(1, java.sql.Types.VARCHAR);
			ps.setString(2, account);
			ps.setString(3, branch);			
			
			ps.execute();
			res = ps.getString(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static Date get_day(CallableStatement ps_getday) throws Exception
	{
		CallableStatement ps = ps_getday;
		Date res = null;
		try
		{
			ps.execute();
			res = ps.getDate(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		return res;
	}
	
	public static Date get_day(Connection c) throws Exception
	{
		CallableStatement ps = null;
		Date res = null;
		try
		{
			ps = c.prepareCall("{? = call info.getDay() }");
			ps.registerOutParameter(1, java.sql.Types.DATE);
			ps.execute();
			res = ps.getDate(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static Long getCrossCourse(
			String curr_orig, 
			String curr_eq, 
			String curr_cross, 
			Long course_type, 
			java.sql.Date date, 
			Connection c) throws Exception
	{
		CallableStatement ps = null;
		Long res = null;
		try
		{
			ps = c.prepareCall("{? = call info.getCrossCourse(?, ?, ?, ?, ?) }");
			ps.registerOutParameter(1, java.sql.Types.NUMERIC);
			ps.setString(2, curr_orig);
			ps.setString(3, curr_eq);
			ps.setString(4, curr_cross);
			ps.setLong(5, course_type);
			ps.setDate(6, date);
			ps.execute();
			res = ps.getLong(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static Long getCrossCourse(
			String curr_orig, 
			String curr_eq, 
			String curr_cross, 
			Long course_type, 
			java.sql.Date date, 
			CallableStatement ps_crosscourse) throws Exception
	{
		CallableStatement ps = ps_crosscourse;
		Long res = null;
		try
		{
			ps.setString(2, curr_orig);
			ps.setString(3, curr_eq);
			ps.setString(4, curr_cross);
			ps.setLong(5, course_type);
			ps.setDate(6, date);
			ps.execute();
			res = ps.getLong(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		return res;
	}
	
	public static Long get_deal_general(String type_doc, String bank_cl, String bank_co, Connection c) throws Exception
	{
		CallableStatement ps = null;
		ResultSet rs = null;
		Long res = null;
		try
		{
			ps = c.prepareCall("{? = call kernel.GetDealGeneral(?, ?, ?, null) }");
			ps.registerOutParameter(1, java.sql.Types.NUMERIC);
			ps.setString(2, type_doc);
			ps.setString(3, bank_cl);
			ps.setString(4, bank_co);
			ps.execute();
			res = ps.getLong(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static CallableStatement init_get_deal_general(Connection c) throws SQLException
	{
		CallableStatement ps = c.prepareCall("{? = call kernel.GetDealGeneral(?, ?, ?, null) }");
		ps.registerOutParameter(1, java.sql.Types.NUMERIC);
		return ps;
	}
	public static void close_get_deal_general(CallableStatement cs) throws SQLException
	{
		if(cs!=null)cs.close();
	}
	
	public static Long get_deal_general(String type_doc, String bank_cl, 
			String bank_co, Connection c, CallableStatement ps) throws Exception
	{
		Long res = null;
		ps.setString(2, type_doc);
		ps.setString(3, bank_cl);
		ps.setString(4, bank_co);
		ps.execute();
		res = ps.getLong(1);
		return res;
	}
	
	public static Long get_deal_general(String type_doc, String bank_cl, String bank_co, Connection c, HashMap<String, Object> initialised_statements) throws Exception
	{
		CallableStatement ps = (CallableStatement)initialised_statements.get("dg_ps");
		ResultSet rs = null;
		Long res = null;
		try
		{
			ps.setString(2, type_doc);
			ps.setString(3, bank_cl);
			ps.setString(4, bank_co);
			ps.execute();
			res = ps.getLong(1);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
//			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static HashMap<String, Object> init_before_action_general_doc(Connection c) throws SQLException
	{
		HashMap<String, Object> inited = new HashMap<String, Object>();
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps_setparam = null;
		CallableStatement ps_getparam = null;
		PreparedStatement st = null;
		CallableStatement st_param = null;
		CallableStatement dg_ps = null;
		PreparedStatement st_clear_param = null;
		CallableStatement ps_crosscourse = null;
		CallableStatement ps_getday = null;
		
		ps_setparam = c.prepareStatement("{call param.setParam(?, ?)}");
		ps = c.prepareStatement("select * from bf_tr_paydocs t where t.pay_id = ? order by t.ord");
		st_param = c.prepareCall("{call param.ClearParam()}");
		ps_getparam = c.prepareCall("{? = call Param.getparam('ID') }");
		ps_getparam.registerOutParameter(1, java.sql.Types.NUMERIC);
		st = c.prepareCall("{call kernel.doaction(?, ?, ?)}");
		ps1 = c.prepareStatement("update bf_tr_paydocs t " +
			"set t.g_docid = ?, t.g_branch = ? where t.id = ?");
		st_clear_param = c.prepareCall("{call param.ClearParam()}");
		dg_ps = c.prepareCall("{? = call kernel.GetDealGeneral(?, ?, ?, null) }");
		dg_ps.registerOutParameter(1, java.sql.Types.NUMERIC);
		ps_crosscourse = c.prepareCall("{? = call info.getCrossCourse(?, ?, ?, ?, ?) }");
		ps_crosscourse.registerOutParameter(1, java.sql.Types.NUMERIC);
		ps_getday = c.prepareCall("{? = call info.getDay() }");
		ps_getday.registerOutParameter(1, java.sql.Types.DATE);
		
		inited.put("ps_setparam", ps_setparam);
		inited.put("ps", ps);
		inited.put("st_clear_param", st_clear_param);
		inited.put("st_param", st_param);
		inited.put("ps_getparam", ps_getparam);
		inited.put("st", st);
		inited.put("ps1", ps1);
		inited.put("dg_ps", dg_ps);
		inited.put("ps_crosscourse", ps_crosscourse);
		inited.put("ps_getday", ps_getday);
		
		return inited;
	}
	
	public static void close_afer_action_general_doc(
			HashMap<String, Object> initialised_statements) throws SQLException
	{
		((PreparedStatement)initialised_statements.get("ps_setparam")).close();
		((PreparedStatement)initialised_statements.get("ps")).close();
		((CallableStatement)initialised_statements.get("st_param")).close();
		((CallableStatement)initialised_statements.get("ps_getparam")).close();
		((PreparedStatement)initialised_statements.get("st")).close();
		((PreparedStatement)initialised_statements.get("ps1")).close();
		((PreparedStatement)initialised_statements.get("st_clear_param")).close();
		((CallableStatement)initialised_statements.get("ps_crosscourse")).close();
		((CallableStatement)initialised_statements.get("ps_getday")).close();
	}
	
	
	public static List<General> action_general_doc(
			long action_id, Connection c, 
			HashMap<String, Object> initialised_statements, ResultSet paydocs_rs) throws Exception
	{
		List<General> documents = new ArrayList<General>();
		
		PreparedStatement ps = ((PreparedStatement)initialised_statements.get("ps"));
		PreparedStatement ps1 = ((PreparedStatement)initialised_statements.get("ps1"));
		PreparedStatement ps_setparam = ((PreparedStatement)initialised_statements.get("ps_setparam"));
		ResultSet rs = null;
		Savepoint save = null;
		CallableStatement ps_getparam = ((CallableStatement)initialised_statements.get("ps_getparam"));
		PreparedStatement st = ((PreparedStatement)initialised_statements.get("st"));
		PreparedStatement st_clear_param = ((PreparedStatement)initialised_statements.get("st_clear_param"));
		CallableStatement st_param = ((CallableStatement)initialised_statements.get("st_param"));
		CallableStatement ps_crosscourse = ((CallableStatement)initialised_statements.get("ps_crosscourse"));
		CallableStatement ps_getday = ((CallableStatement)initialised_statements.get("ps_getday"));
		
		try
		{
		//	ps_setparam = c.prepareStatement("{call param.setParam(?, ?)}");
			
		//	st_param = c.createStatement();
			
		//	ps = c.prepareStatement("select * from bf_tr_paydocs t where t.pay_id = ? order by t.ord");
//			ps.setLong(1, tr_pay_id);
			rs = paydocs_rs;
//			rs = ps.executeQuery();
		//	save = c.setSavepoint();
			while (rs.next())
			{
				
				try
				{
				//st_param.execute("{call param.SaveParam(181)}");
				st_param.execute();//("{call param.ClearParam()}");
				
				if ((action_id != 1l)&&((rs.getString("g_docid")==null)||(rs.getString("g_docid").length()==0)))
					continue;
				
				if ((rs.getString("g_docid")!=null)&&(rs.getString("g_docid").length()>0))
				{
					ps_setparam.setString(1, "ID"); ps_setparam.setString(2, rs.getString("g_docid"));
					ps_setparam.execute();
				}
				
/*				ps_setparam.setString(1, "BRANCH"); ps_setparam.setString(2, rs.getString("branch"));
				ps_setparam.execute();
				ps_setparam.setString(1, "D_DATE"); ps_setparam.setString(2, sdf_doaction.format(get_day(ps_getday)));
				ps_setparam.execute();
				ps_setparam.setString(1, "BANK_CL"); ps_setparam.setString(2, rs.getString("bank_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ACC_CL"); ps_setparam.setString(2, rs.getString("acc_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CL_ACC"); ps_setparam.setString(2, rs.getString("acc_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CL_CURRENCY"); ps_setparam.setString(2, rs.getString("acc_cl").substring(5,8));
				ps_setparam.execute();
				ps_setparam.setString(1, "NAME_CL"); ps_setparam.setString(2, rs.getString("name_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "BANK_CO"); ps_setparam.setString(2, rs.getString("bank_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PDC"); ps_setparam.setString(2, rs.getString("pdc"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ACC_CO"); ps_setparam.setString(2, rs.getString("acc_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CO_ACC"); ps_setparam.setString(2, rs.getString("acc_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CO_CURRENCY"); ps_setparam.setString(2, rs.getString("acc_co").substring(5,8));
				ps_setparam.execute();
				ps_setparam.setString(1, "NAME_CO"); ps_setparam.setString(2, rs.getString("name_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PURPOSE"); ps_setparam.setString(2, rs.getString("purpose"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CASH_CODE"); ps_setparam.setString(2, rs.getString("CASH_CODE"));
				ps_setparam.execute();
				ps_setparam.setString(1, "SUMMA"); ps_setparam.setString(2, Long.toString(rs.getLong("summa")));
				ps_setparam.execute();
				ps_setparam.setString(1, "CL_SUMMA"); ps_setparam.setString(2, Long.toString(rs.getLong("summa")));
				ps_setparam.execute();*/
				ps_setparam.setString(1, "CO_SUMMA"); ps_setparam.setString(2, Long.toString(
						rs.getLong("summa") * getCrossCourse(
								rs.getString("acc_cl").substring(5,8), 
								rs.getString("acc_co").substring(5,8), 
								"000", 
								1l, 
								get_day(ps_getday), 
								ps_crosscourse))
						);
				ps_setparam.execute();
				ps_setparam.setString(1, "V_DATE"); ps_setparam.setString(2, sdf_doaction.format(get_day(ps_getday)));
				ps_setparam.execute();
				ps_setparam.setString(1, "TYPE_DOC"); ps_setparam.setString(2, rs.getString("type_doc"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ID_TRANSH"); ps_setparam.setString(2, rs.getString("id_transh_purp"));
				ps_setparam.execute();
				ps_setparam.setString(1, "S_DEAL_ID"); ps_setparam.setString(2, Long.toString(rs.getLong("deal_id")));
				ps_setparam.execute();
				ps_setparam.setString(1, "DEAL_ID"); ps_setparam.setString(2, Long.toString(rs.getLong("deal_id")));
				ps_setparam.execute();
				ps_setparam.setString(1, "FX_DEAL"); ps_setparam.setString(2, Long.toString(rs.getLong("deal_id")));
				ps_setparam.execute();
				ps_setparam.setString(1, "PARENT_GROUP_ID"); ps_setparam.setString(2, rs.getString("parent_group_id"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PARENT_ID"); ps_setparam.setString(2, rs.getString("parent_id"));
				ps_setparam.execute();
				
				ps_setparam.setString(1, "PURPOSE1"); ps_setparam.setString(2, rs.getString("purpose").substring(0, 
						rs.getString("purpose").length()>35?35:rs.getString("purpose").length()));
				ps_setparam.execute();
				if (rs.getString("purpose").length() > 35)
				{
					ps_setparam.setString(1, "PURPOSE2"); ps_setparam.setString(2, rs.getString("purpose").substring(35, 
							rs.getString("purpose").length()>70?70:rs.getString("purpose").length()));
					ps_setparam.execute();
				}
				if (rs.getString("purpose").length() > 70)
				{
					ps_setparam.setString(1, "PURPOSE3"); ps_setparam.setString(2, rs.getString("purpose").substring(70,  
							rs.getString("purpose").length()>105?105:rs.getString("purpose").length()));
					ps_setparam.execute();
				}
				if (rs.getString("purpose").length() > 105)
				{
					ps_setparam.setString(1, "PURPOSE4"); ps_setparam.setString(2, rs.getString("purpose").substring(105,  
							rs.getString("purpose").length()>140?140:rs.getString("purpose").length()));
					ps_setparam.execute();
				}
				
				
				st.setLong(1, rs.getLong("deal_group_id"));
				if (rs.getLong("deal_group_id") == 14)
					st.setLong(2, 1);
				else st.setLong(2, rs.getLong("deal_id"));
				st.setLong(3, action_id);
		////		st.execute();
//				st.close();
//				st = null;
				
		////		ps_getparam.execute();
		////		long obj_id = ps_getparam.getLong(1);
				long obj_id = 8l;
				st_clear_param.execute();
				System.out.println("obj_id: "+obj_id+"deal_group:"+rs.getLong("deal_group_id"));
				if(rs.getLong("g_docid") == 0)
				{
					ps1.setLong(1, obj_id);
					ps1.setString(2, rs.getString("branch"));
					ps1.setLong(3, rs.getLong("id"));
					
					ps1.execute();
					General gn = new General();
					gn.setId(obj_id);
					gn.setBranch(rs.getString("branch"));
					gn.setDeal_group_id(rs.getLong("deal_group_id"));
					documents.add(gn);
				}
//				System.out.println("===========================================");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			}
		}
		catch(Exception e)
		{
//			if (save != null) c.rollback(save);
			throw e;
		}
		finally
		{
//			try{if(ps1!=null)ps1.close();}catch(Exception e){} //
//			try{if(ps_setparam!=null)ps_setparam.close();}catch(Exception e){}  //
//			try{if(ps_getparam!=null)ps_getparam.close();}catch(Exception e){}  //
//			try{if(st!=null)st.close();}catch(Exception e){}
			try{if(rs!=null)rs.close();}catch(Exception e){}    //
//			try{if(ps!=null)ps.close();}catch(Exception e){}    //
//			try{if(st_param!=null)st_param.close();}catch(Exception e){}
		}
		return documents;
	}
	
	
	public static List<General> action_general_doc(
			Long tr_pay_id, long action_id, Connection c) throws Exception
	{
		List<General> documents = new ArrayList<General>();
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps_setparam = null;
		ResultSet rs = null;
		CallableStatement ps_getparam = null;
		PreparedStatement st = null;
		Statement st_param = null;
		
		try
		{
			ps_setparam = c.prepareStatement("{call param.setParam(?, ?)}");
			
			st_param = c.createStatement();
			
			ps = c.prepareStatement("select * from bf_tr_paydocs t where t.pay_id = ? order by t.ord");
			ps.setLong(1, tr_pay_id);
			rs = ps.executeQuery();
			while (rs.next())
			{
				//st_param.execute("{call param.SaveParam(181)}");
				st_param.execute("{call param.ClearParam()}");
				
				ps_setparam.setString(1, "BRANCH"); ps_setparam.setString(2, rs.getString("branch"));
				ps_setparam.execute();
				ps_setparam.setString(1, "D_DATE"); ps_setparam.setString(2, sdf_doaction.format(rs.getDate("d_date")));
				ps_setparam.execute();
				ps_setparam.setString(1, "BANK_CL"); ps_setparam.setString(2, rs.getString("bank_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ACC_CL"); ps_setparam.setString(2, rs.getString("acc_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "NAME_CL"); ps_setparam.setString(2, rs.getString("name_cl"));
				ps_setparam.execute();
				ps_setparam.setString(1, "BANK_CO"); ps_setparam.setString(2, rs.getString("bank_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PDC"); ps_setparam.setString(2, rs.getString("pdc"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ACC_CO"); ps_setparam.setString(2, rs.getString("acc_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "NAME_CO"); ps_setparam.setString(2, rs.getString("name_co"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PURPOSE"); ps_setparam.setString(2, rs.getString("purpose"));
				ps_setparam.execute();
				ps_setparam.setString(1, "CASH_CODE"); ps_setparam.setString(2, rs.getString("CASH_CODE"));
				ps_setparam.execute();
				ps_setparam.setString(1, "SUMMA"); ps_setparam.setString(2, rs.getString("summa"));
				ps_setparam.execute();
				ps_setparam.setString(1, "V_DATE"); ps_setparam.setString(2, sdf_doaction.format(rs.getDate("d_date")));
				ps_setparam.execute();
				//System.out.println("V_DATE "+rs.getDate("d_date"));
				//System.out.println("V_DATE "+sdf_doaction.format(rs.getDate("d_date")));
				ps_setparam.setString(1, "TYPE_DOC"); ps_setparam.setString(2, rs.getString("type_doc"));
				ps_setparam.execute();
				ps_setparam.setString(1, "ID_TRANSH"); ps_setparam.setString(2, rs.getString("id_transh_purp"));
				ps_setparam.execute();
				ps_setparam.setString(1, "S_DEAL_ID"); ps_setparam.setString(2,Long.toString(rs.getLong("deal_id")));
//						Long.toString(get_deal_general(
//								rs.getString("type_doc"),
//								rs.getString("bank_cl"),
//								rs.getString("bank_co"),
//								c)));
				ps_setparam.execute();
				ps_setparam.setString(1, "PARENT_GROUP_ID"); ps_setparam.setString(2, rs.getString("parent_group_id"));
				ps_setparam.execute();
				ps_setparam.setString(1, "PARENT_ID"); ps_setparam.setString(2, rs.getString("parent_id"));
				ps_setparam.execute();
				
				System.out.println("deal_group_id "+rs.getLong("deal_group_id")+" deal_id "+rs.getLong("deal_id")+" action_id "+action_id);
				
				if (rs.getInt("deal_group_id")==14){
					ps_setparam.setString(1, "CO_ACC"); ps_setparam.setString(2, rs.getString("acc_co"));
					ps_setparam.execute();
					ps_setparam.setString(1, "CO_NAME"); ps_setparam.setString(2, rs.getString("name_co"));
					ps_setparam.execute();
					ps_setparam.setString(1, "CL_ACC"); ps_setparam.setString(2, rs.getString("acc_cl"));
					ps_setparam.execute();
					ps_setparam.setString(1, "CL_NAME"); ps_setparam.setString(2, rs.getString("name_cl"));
					ps_setparam.execute();
					ps_setparam.setString(1, "CL_VDATE"); ps_setparam.setString(2, sdf_doaction.format(rs.getDate("d_date")));
					ps_setparam.execute();
					ps_setparam.setString(1, "CO_VDATE"); ps_setparam.setString(2, sdf_doaction.format(rs.getDate("d_date")));
					ps_setparam.execute();

					ps_setparam.setString(1, "CL_CURRENCY"); ps_setparam.setString(2, rs.getString("acc_cl").substring(5, 8));
					ps_setparam.execute();
					ps_setparam.setString(1, "CO_CURRENCY"); ps_setparam.setString(2, rs.getString("acc_co").substring(5, 8));
					ps_setparam.execute();
					int course =ExchangeService.GetCource("840",ExchangeService.GetCourceType(rs.getInt("deal_id")));
					ps_setparam.setString(1, "CL_SUMMA"); ps_setparam.setString(2, rs.getLong("summa")*course+"");
					ps_setparam.execute();
					ps_setparam.setString(1, "CO_SUMMA"); ps_setparam.setString(2, rs.getString("summa"));
					ps_setparam.execute();
					ps_setparam.setString(1, "COURSE"); ps_setparam.setString(2, course+"");
					ps_setparam.execute();
					
					ps_setparam.setString(1, "FX_DEAL"); ps_setparam.setString(2, rs.getString("deal_id"));
					ps_setparam.execute();
					
					int slen = rs.getString("purpose").length();
					System.out.println("ln "+slen +"  "+rs.getString("purpose"));
					ps_setparam.setString(1, "PURPOSE1"); ps_setparam.setString(2, rs.getString("purpose").substring(0,slen>35 ? 35 : slen));
					ps_setparam.execute();
					if (slen>35){
					ps_setparam.setString(1, "PURPOSE2"); ps_setparam.setString(2, rs.getString("purpose").substring(35,slen>70 ? 70 : slen));
					ps_setparam.execute();
					}
					if (slen>70){
					ps_setparam.setString(1, "PURPOSE3"); ps_setparam.setString(2, rs.getString("purpose").substring(70,slen>105 ? 105 : slen));
					ps_setparam.execute();
					}
					if (slen>105){
					ps_setparam.setString(1, "PURPOSE4"); ps_setparam.setString(2, rs.getString("purpose").substring(105,slen>140 ? 140 : slen));
					ps_setparam.execute();
					}

					 
					
				}
				
				
				ps_getparam = c.prepareCall("{? = call Param.getparam('ID') }");
				ps_getparam.registerOutParameter(1, java.sql.Types.NUMERIC);
				
				st = c.prepareCall("{call kernel.doaction(?, ?, ?)}");
				
				st.setLong(1, rs.getLong("deal_group_id"));
				st.setLong(2, rs.getInt("deal_group_id")==14  ? 1 :rs.getLong("deal_id"));
				st.setLong(3, action_id);
				st.execute();
				st.close();
				st = null;
				
				ps_getparam.execute();
				long obj_id = ps_getparam.getLong(1);
				ps_getparam.close();
				ps_getparam = null;
				//System.out.println("general "+obj_id);
				
				//st_param.execute("{call param.LoadParam(181)}");
				
				if(rs.getLong("g_docid") == 0)
				{
					ps1 = c.prepareStatement("update bf_tr_paydocs t " +
							"set t.g_docid = ?, t.g_branch = ? where t.id = ?");
					ps1.setLong(1, obj_id);
					ps1.setString(2, rs.getString("branch"));
					ps1.setLong(3, rs.getLong("id"));
					
					ps1.execute();
					ps1.close();
					ps1 = null;
					General gn = new General();
					gn.setId(obj_id);
					gn.setBranch(rs.getString("branch"));
					documents.add(gn);
				}
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(ps1!=null)ps1.close();}catch(Exception e){} //
			try{if(ps_setparam!=null)ps_setparam.close();}catch(Exception e){}  //
			try{if(ps_getparam!=null)ps_getparam.close();}catch(Exception e){}  //
			try{if(st!=null)st.close();}catch(Exception e){}
			try{if(rs!=null)rs.close();}catch(Exception e){}    //
			try{if(ps!=null)ps.close();}catch(Exception e){}    //
			try{if(st_param!=null)st_param.close();}catch(Exception e){}
		}
		return documents;
	}
	
	public static boolean account_exists(String account, Connection c) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try
		{
			ps = c.prepareStatement("select count(*) res from account t where t.id = ?");
			ps.setString(1, account);
			ps.execute();
			rs = ps.executeQuery();
			rs.next();
			res = (rs.getInt("res") > 0);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		return res;
	}
	
	public static boolean account_exists(String branch, String account, Connection c) throws Exception
	{
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		boolean res = false;
		try
		{
			//ps1 = c.prepareStatement("select * from ss_dblink_branch t where t.branch = ?");
			//ps1.setString(1, branch);
			//rs1 = ps1.executeQuery();
			//String us = "iy00444";
			//if (rs1.next()) {
			//	us = rs1.getString("user_name");
			//}
			//ps = c.prepareStatement("select count(*) res from "+us+".account t where t.id = ?");
			ps = c.prepareStatement("select count(*) res from account t where t.branch=? and t.id = ?");
			ps.setString(1, branch);
			ps.setString(2, account);
			ps.execute();
			rs = ps.executeQuery();
			rs.next();
			res = (rs.getInt("res") > 0);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
			try{if(rs1!=null)rs1.close();}catch(Exception e){}
			try{if(ps1!=null)ps1.close();}catch(Exception e){}
		}
		return res;
	}
	
}
