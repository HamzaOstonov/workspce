package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class Approval_service
{
	private static Logger logger = ISLogger.getLogger();
	private static List<String> cb_branches = null;
	
	public static void process_cb_approvals(List<Cb_cash_inq_approval> approvals) throws Exception
	{
		if(cb_branches == null) cb_branches = get_cb_branches();
		for(Cb_cash_inq_approval approval : approvals)
		{
			if(cb_branches.contains(approval.getReciever_bank_code()))
			{
				if(approval.getState_id() == 1)
					process_cb_approval(approval);
			}
		}
	}
	
	public static List<Cb_cash_inq_approval> get_cb_approvals_to_process() throws Exception
	{
		List<Cb_cash_inq_approval> res = new ArrayList<Cb_cash_inq_approval>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select app.* from cb_cash_rec_approvals app, s_mfo o " +
					"where o.bank_type = '001' " +
					"and app.reciever_bank_code = o.bank_id and app.state_id = 1 " +
					"and trunc(app.in_date) >= trunc(sysdate)");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(new Cb_cash_inq_approval(
						rs.getLong("id"), 
						rs.getDate("reciever_bank_oper_date"), 
						rs.getString("request_bank_code"), 
						rs.getString("reciever_bank_code"), 
						rs.getLong("request_bank_inv_id"), 
						rs.getLong("reciever_bank_inv_id"), 
						rs.getLong("amount"), 
						rs.getString("inv_number"), 
						rs.getDate("inv_date"), 
						rs.getString("req_number"), 
						rs.getDate("req_date"), 
						rs.getDate("in_date"), 
						rs.getInt("state_id"), 
						rs.getLong("ext_record_id")
						));
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	/*
	public static void process_cb_approval_template(Cb_cash_inq_approval approval)
	 throws SQLException
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			alter_session_init(c, approval.getRequest_bank_code());
			
			
			
			accounting_transaction.Service.action_general_doc(tr_id,
					1,
					c);
			approval.setState_id(4);
			Request_service.update_approval(approval, c);
			
			c.commit();
		}
		catch(SQLException e)
		{
			if(c!=null) c.rollback();
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null) c.close();
		}
	}*/
	
	public static void process_cb_approval(Cb_cash_inq_approval approval) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			alter_session_init(c, approval.getRequest_bank_code());
			
			do_transaction_old(approval, c);
			
			approval.setState_id(4);
			Request_service.update_approval(approval, c);
			
			c.commit();
			
		}
		catch(Exception e)
		{
			if(c!=null) c.rollback();
			if(e.getMessage().toLowerCase().contains("овердрафт")) 
				logger.error("branch: "+approval.getRequest_bank_code()+" "+e.getMessage());
			else logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static List<String> get_cb_branches() throws Exception
	{
		List<String> res = new ArrayList<String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select o.bank_id from s_mfo o where o.bank_type = '001'");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(rs.getString("bank_id"));
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static void alter_session_init(Connection c, String branch) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		try
		{
			ps = c.prepareStatement(
			"select t.user_name res from ss_dblink_branch t where t.branch = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			String alias = null;
			if(rs.next()) alias = rs.getString("res");
			else throw new com.is.file_reciever_srv.exception.Reciever_exception("Wrong branch: "+branch);
			
			st = c.createStatement();
			st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+alias);
			st.execute("{call info.init()}");
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps!=null) ps.close();
			if(rs!=null) rs.close();
			if(st!=null) st.close();
		}
	}
	
	private static void do_transaction_old(Cb_cash_inq_approval approval, Connection c) throws Exception
	{
		HashMap<String, String> params = new HashMap<String, String>();
		String account_10311 = get_10311_account(approval.getReciever_bank_code(), c);
		
		SimpleDateFormat sdf_f = new SimpleDateFormat("ddMMyyyy");
		
		params.put("REC_ID", Long.toString(approval.getId()));
		params.put("PACKAGE_NAME", get_approval_file_name(approval.getId()));
		params.put("BRANCH", approval.getRequest_bank_code());
		params.put("BANK_ACCOUNT", account_10311);
		params.put("RKC_CODE", approval.getRequest_bank_code());
		params.put("REG_NMB", approval.getInv_number());
		params.put("REG_DATE", sdf_f.format(approval.getInv_date()));
		params.put("COMMAND_NMB", approval.getReq_number());
		params.put("COMMAND_DATE", sdf_f.format(approval.getReq_date()));
		params.put("AMOUNT", Long.toString(approval.getAmount()));
		params.put("REC_ID", Long.toString(approval.getId()));
		params.put("PACKAGE_NAME", get_approval_file_name(approval.getId()));
		do_transaction(params, c);
	}
	
	private static String get_approval_file_name(Long approval_id) throws Exception
	{
		String res = null;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select ff.name from cb_cash_rec_approvals app, ext_in_file_records fr, ext_in_files f, fr_file ff "+
					"where app.id = ? and ff.id = f.fr_id and f.id = fr.in_file_id and fr.id = app.ext_record_id");
			
			ps.setLong(1, approval_id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getString("name").contains("\\"))
					res = rs.getString("name").substring(rs.getString("name").lastIndexOf('\\'));
				else 
					res = rs.getString("name").substring(rs.getString("name").lastIndexOf('/'));
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c!=null) c.close();
		}
	}
	
	public static void do_transaction(HashMap<String, String> params, Connection c) throws Exception
	{
		
		PreparedStatement ps = null;
		PreparedStatement ps_select = null;
		CallableStatement ps_getparam = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select t.user_name res from ss_dblink_branch t where t.branch = ?");
			ps.setString(1, params.get("BRANCH"));
			rs = ps.executeQuery();
			String alias = null;
			if(rs.next()) alias = rs.getString("res");
			else throw new com.is.file_reciever_srv.exception.Reciever_exception("Wrong branch");
			
			ps_getparam = c.prepareCall("{? = call Param.getparam('ID') }");
			ps_getparam.registerOutParameter(1, java.sql.Types.NUMERIC);
			
			ps = c.prepareStatement("{call param.setParam(?, ?)}");
			
			st = c.createStatement();
            st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+alias);
			
			st.execute("{call info.init()}");
			
			ps_select = c.prepareStatement("select account.id value from account " +
					"where branch = info.GetBranch and client = substr(?, 10, 8) " +
					"and acc_bal = '10109' and currency = '000' and id_order = '001'");
			ps_select.setString(1, params.get("BANK_ACCOUNT"));
			rs = ps_select.executeQuery();
			String account_from_table = null;
			if(rs.next()) account_from_table = rs.getString("value");
			else throw new com.is.file_reciever_srv.exception.Reciever_exception("The 10109 tietoAccount not found");
			
			st.execute("{call param.SaveParam(181)}");
			st.execute("{call param.ClearParam()}");
			st.execute("{call param.setParam('BRANCH', info.getBranch())}");
			st.execute("{call param.setParam('D_DATE',   info.getDay())}");
			st.execute("{call param.setParam('BANK_CL',  info.getBranch())}");	
			
			ps.setString(1, "ACC_CL"); ps.setString(2, params.get("BANK_ACCOUNT"));
			ps.execute();
			
			st.execute("{call param.setParam('NAME_CL',  kernel.getnameandinn('"+
					params.get("BANK_ACCOUNT")+"'))}");
			st.execute("{call param.setParam('BANK_CO',  info.getBranch())}");
			st.execute("{call param.setParam('PDC',  'D')}");
			st.execute("{call param.setParam('ACC_CO',   '"+account_from_table+"')}");
			st.execute("{call param.setParam('NAME_CO',  kernel.getnameandinn('"+account_from_table+"'))}");
			
			ps.setString(1, "PURPOSE"); 
			ps.setString(2, 
					"00615 Подтверждение от "+
					params.get("RKC_CODE")+" РКЦ ЦБ: "+
					params.get("PACKAGE_NAME")+", опись № "+
					params.get("REG_NMB")+" от "+
					params.get("REG_DATE")+", распоряжение № "+
					params.get("COMMAND_NMB")+" от "+
					params.get("COMMAND_DATE"));
			ps.execute();
			
			ps.setString(1, "SUMMA");
			ps.setString(2, params.get("AMOUNT"));
			ps.execute();
			
			st.execute("{call param.setParam('V_DATE', info.getDay)}");
			
			st.execute("{call param.setParam('TYPE_DOC', '06')}");
			st.execute("{call param.setParam('ID_TRANSH', '615')}");
			
			st.execute("{call param.setParam('S_DEAL_ID', '106')}");
			st.execute("{call param.setParam('PARENT_GROUP_ID', '181')}");
			
			ps.setString(1, "PARENT_ID");
			ps.setString(2, params.get("REC_ID"));
			ps.execute();
			
			st.execute("{call kernel.doaction(3, 106, 21)}");
			
			ps_getparam.execute();
			long obj_id = ps_getparam.getLong(1);
			st.execute("{call param.LoadParam(181)}");
			
			logger.info("Executing ransaction for file "+
					params.get("PACKAGE_NAME")+" record with id "+params.get("REC_ID")+
					": complete");
		}
		catch (SQLException e)
		{
			throw new com.is.file_reciever_srv.exception.Reciever_exception(params.get("BRANCH")+": "+
					"CB approval file exception "+com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(ps!=null)ps.close();}catch(Exception e1){}
			try{if(st!=null)st.close();}catch(Exception e1){}
			try{if(ps_select!=null)ps_select.close();}catch(Exception e1){}
			try{if(ps_getparam!=null)ps_getparam.close();}catch(Exception e1){}
		}
	}
	
	private static String get_10311_account(String branch, Connection c) throws Exception
	{
		String res = null;
		PreparedStatement ps = c.prepareStatement(
				"select t.id from account t where t.id like '10311000%000"+branch+"001'");
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			res = rs.getString("id");
		}
		else throw new Exception("could not get 10311 tietoAccount for branch"+branch);
		return res;
	}
}
