package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class Reversal_service
{
public static SimpleDateFormat file_df = new SimpleDateFormat("dd.MM.yyyy");
public static SimpleDateFormat file_sent_df = new SimpleDateFormat("yyyyMMddHH");
private static SimpleDateFormat sdf_dir = new SimpleDateFormat("yyyy\\MM\\dd\\");
private static Logger logger = ISLogger.getLogger();
private static DecimalFormat decimalFormat = new DecimalFormat("#");	


	public static long get_reversal_id() throws Exception
	{
		long res = 0;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select seq_cb_cash_reversals.nextval res from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getLong("res");
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
	
	public static void save_reversal(Cb_cash_request_reversal rev, Connection c) throws Exception
	{
		//logger.info("saving reversal to table");
		try
		{
			PreparedStatement ps = c.prepareStatement(
							"insert into cb_cash_reversals (id, "+
                            "ext_record_id, "+
                            "cb_request_number, "+
                            "request_type_code, "+
                            "oper_day, "+
                            "reciever_bank_code, "+
                            "request_bank_code, "+
                            "reversal_doc_num, "+
                            "reversal_doc_date, "+
                            "amount, "+
                            "additional_info, "+
                            "state_id, " +
                            "in_date) "+
                            "values (? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)");
			
			ps.setLong(1, rev.getId());
			ps.setLong(2, rev.getExt_record_id());
			ps.setString(3, rev.getCb_request_number());
			ps.setString(4, rev.getRequest_type_code());
			ps.setDate(5, new java.sql.Date(rev.getOper_day().getTime()));
			
			if(rev.getReciever_bank_code() != null)
				ps.setString(6, rev.getReciever_bank_code());
			else
				ps.setNull(6, java.sql.Types.VARCHAR);
			
			ps.setString(7, rev.getRequest_bank_code());
			ps.setString(8, rev.getReversal_doc_num());
			ps.setDate(9, new java.sql.Date(rev.getReversal_doc_date().getTime()));
			ps.setLong(10, rev.getAmount());
			
			if(rev.getAdditional_info() != null)
				ps.setString(11, rev.getAdditional_info());
			else
				ps.setNull(6, java.sql.Types.VARCHAR);
			
			ps.setInt(12, rev.getState_id());
		//	logger.info("saving reversal to table complete");
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static Cb_cash_request_reversal parse(String str) throws NumberFormatException, ParseException
	{
		Cb_cash_request_reversal res = null;
		
		String[] splitted_string = str.split(Character.toString((char)29));
		
		res = new Cb_cash_request_reversal(
				null, 
				null, 
				splitted_string[0], 
				splitted_string[1], 
				file_df.parse(splitted_string[2]), 
				splitted_string[3], 
				splitted_string[4], 
				splitted_string[5], 
				file_df.parse(splitted_string[6]),
				Long.parseLong(splitted_string[7]), 
				splitted_string[8], 
				1,
				false, 
				"00000"
				);
		
		return res;
	}
	
	public static void process_reversals(List<Cb_cash_request_reversal> reversals) throws Exception
	{
		for(Cb_cash_request_reversal rev : reversals)
		{
			process_reversal(rev);
		}
	}
	
	public static void process_reversal(Cb_cash_request_reversal rev) throws Exception
	{
		Connection c = null;
		//int current_action_id = 0;
		try
		{
			Cb_cash_request req_to_rev = 
				Request_service.get_request_by_cb_request_number(
						rev.getCb_request_number(), 
						rev.getRequest_type_code(), 
						rev.getRequest_bank_code());
			
			if(req_to_rev == null)
			{
				rev.setError_code("00001");
				rev.setState_id(5);
				update_reversal(rev);
				return;
			}
			
			c = ConnectionPool.getConnection();
			Request_service.alter_session_init(c, rev.getRequest_bank_code());
			
		/*	if(req_to_rev.getState_id() == 1)
			{
				req_to_rev.setAmount(req_to_rev.getAmount() - rev.getAmount());
				Request_service.update_request(req_to_rev, c);
				rev.setState_id(5);
				update_reversal(rev, c);
			}*/
			if(req_to_rev.getState_id() == 1) return;
			else if(req_to_rev.getState_id() == 2)
			{
				long prev_amount = get_paydoc_amount(req_to_rev);
				long new_amount = prev_amount - rev.getAmount();
				
				if(new_amount == 0)
				{
					req_to_rev.setIs_sent_inkassa(true);
					req_to_rev.setState_id(3);
				}
				
				Request_service.update_request(req_to_rev, c);
				update_paydocs(req_to_rev, rev.getAmount(), c);
				rev.setState_id(5);
				update_reversal(rev, c);
			}
			else if(req_to_rev.getState_id() == 3)
			{
				if (ConnectionPool.getValue("cb_cach_request_remove_transactions")!=null &&
						ConnectionPool.getValue("cb_cach_request_remove_transactions").equals("1"))
				{
					long prev_amount = get_paydoc_amount(req_to_rev);
					long new_amount = prev_amount - rev.getAmount();
					
					TransactionService ts = new TransactionService();
					ts.init(c);
					
					Long pay_id = Request_service.get_tr_pay_for_request(req_to_rev.getId());
					logger.info("pay_id: "+pay_id);
					if(pay_id == null) 
						throw new Reciever_exception(
								"could not get pay_id for request " + req_to_rev.getId()
								);
					
					long final_action_id = Long.parseLong(
							ConnectionPool.getValue("cb_cach_request_general_action")
							);
					long current_paydoc_amount = Request_service.get_paydoc_amount(pay_id);
					if(final_action_id == 19)
					{
						accounting_transaction.Service.action_general_doc(pay_id,
								5,
								c);
						accounting_transaction.Service.action_general_doc(pay_id,
								4,
								c);
						accounting_transaction.Service.action_general_doc(pay_id,
								6,
								c);
					}
					else if(final_action_id == 1)
					{
						accounting_transaction.Service.action_general_doc(pay_id,
								6,
								c);
					}
					
					if(new_amount == 0)req_to_rev.setState_id(3);
					else
					{
						
						Request_service.update_paydocs_amount(
								pay_id, 
								current_paydoc_amount - rev.getAmount(), 
								c);
						req_to_rev.setState_id(2);
						Request_service.update_request(req_to_rev, c);
					}
				}
				
				if(req_to_rev.getAmount() == 0)
				{
					req_to_rev.setIs_sent_inkassa(true);
					req_to_rev.setState_id(3);
				}
				rev.setError_code(null);
				rev.setState_id(5);
				update_reversal(rev);
			}
			
			c.commit();
		}
		catch(Exception e)
		{
			/*write_processing_record(
					new Cb_cash_processing_record(
							null,
							request.getId(),
							null,
							"00000",
							e.getMessage(),
							false
							));
			if(c!=null)c.rollback();*/
			logger.error("reversal id "+rev.getId()+" for request id " +
					": "+ rev.getCb_request_number() +CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static List<Cb_cash_request_reversal> get_reversals_to_process() throws Exception
	{
		List<Cb_cash_request_reversal> res = new ArrayList<Cb_cash_request_reversal>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_reversals r where r.state_id = 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.add(new Cb_cash_request_reversal(
						rs.getLong ("id"),
						rs.getLong ("ext_record_id"),
						rs.getString ("cb_request_number"),
						rs.getString ("request_type_code"),
						rs.getDate ("oper_day"),
						rs.getString ("reciever_bank_code"),
						rs.getString ("request_bank_code"),
						rs.getString ("reversal_doc_num"),
						rs.getDate ("reversal_doc_date"),
						rs.getLong ("amount"),
						rs.getString ("additional_info"),
						rs.getInt ("state_id"),
						rs.getInt ("is_sent_in_file") == 1,
						rs.getString ("error_code")
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
	
	public static void update_reversal(Cb_cash_request_reversal req, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_reversals r " +
					"set r.state_id = ?, " +
					"r.is_sent_in_file = ?, " +
					"r.error_code = ? " +
					"where r.id = ?");
			ps.setInt(1, req.getState_id());
			ps.setInt(2, req.getIs_sent_in_file()?1:0);
			
			
			if(req.getError_code() == null || req.getError_code().length() < 1)
			{
				ps.setString(3, "00000");
			}
			else
			{
				ps.setString(3, req.getError_code());
			}
			ps.setLong(4, req.getId());
			ps.execute();
	//		logger.info("updated reversal: "+
	//				" id "+ req.getId());
		}
		catch(Exception e)
		{
			logger.error(CheckNull.getPstr(e));
			throw e;
		}
	}
	
	public static void update_reversal(Cb_cash_request_reversal req) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			update_reversal(req, c);
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void update_request_amnt(Cb_cash_request req) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			update_request(req, c);
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void update_request(Cb_cash_request req, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_requests r set r.amount = ? where r.id = ?");
			
			ps.setLong(1, req.getAmount());
			ps.setLong(2, req.getId());
			
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static void update_paydocs(Cb_cash_request req, long rev_amount, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update bf_tr_paydocs pd " +
					"set pd.summa = pd.summa - ? " +
					"where pd.parent_group_id = 181 " +
					"and pd.parent_id = ? " +
					"and pd.parent_deal_id = 1");
			ps.setLong(1, rev_amount);
			ps.setLong(2, req.getId());
			
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static long get_paydoc_amount(Cb_cash_request request) throws Exception
	 {
		 long res = 0;
			Connection c = null;
			try
			{
				c = ConnectionPool.getConnection();
				
				PreparedStatement ps = c.prepareStatement(
						"select distinct pd.summa " +
						"from bf_tr_paydocs pd " +
						"where pd.parent_group_id = 181 " +
						"and pd.parent_id = ? " +
						"and pd.parent_deal_id = 1");
				
				ps.setLong(1, request.getId());
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					res = rs.getLong("summa");
				}
			}
			catch(Exception e)
			{
				logger.error(CheckNull.getPstr(e));
				throw e;
			}
			finally
			{
				if(c!=null)c.close();
			}
			return res;
	 }
}
