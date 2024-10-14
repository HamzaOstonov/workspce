package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.file_reciever_srv.file_checks.string_checks;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class Request_service
{
	public static SimpleDateFormat file_df = new SimpleDateFormat("dd.MM.yyyy");
	public static SimpleDateFormat file_sent_df = new SimpleDateFormat("yyyyMMddHH");
	private static SimpleDateFormat sdf_dir = new SimpleDateFormat("yyyy\\MM\\dd\\");
	private static Logger logger = ISLogger.getLogger();
	private static DecimalFormat decimalFormat = new DecimalFormat("#");
	
	public static long get_request_id() throws Exception
	{
		long res = 0;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select seq_cb_cash_requests.nextval res from dual");
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
	
	public static long get_recieved_approval_id() throws Exception
	{
		long res = 0;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select seq_cb_cash_rec_approval.nextval res from dual");
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
	
	public static void save_request(Cb_cash_request req, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"insert into cb_cash_requests (id, "+
                              "ext_record_id, "+
                              "cb_request_number, "+
                              "request_type_code, "+
                              "oper_day, "+
                              "reciever_bank_code, "+
                              "request_bank_code, "+
                              "request_number, "+
                              "request_date, "+
                              "amount, "+
                              "additional_info, "+
                              "state_id) "+
							  "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setLong(1, req.getId());
			ps.setLong(2, req.getExt_record_id());
			ps.setString(3, req.getCb_request_number());
			ps.setString(4, req.getRequest_type_code());
			ps.setDate(5, new java.sql.Date(req.getOper_day().getTime()));
			
			if(req.getReciever_bank_code() != null)
				ps.setString(6, req.getReciever_bank_code());
			else
				ps.setNull(6, java.sql.Types.VARCHAR);
			
			ps.setString(7, req.getRequest_bank_code());
			ps.setString(8, req.getRequest_number());
			ps.setDate(9, new java.sql.Date(req.getRequest_date().getTime()));
			ps.setLong(10, req.getAmount());
			
			if(req.getAdditional_info() != null)
				ps.setString(11, req.getAdditional_info());
			else
				ps.setNull(6, java.sql.Types.VARCHAR);
			
			ps.setInt(12, req.getState_id());
			//ps.setTimestamp(13, new java.sql.Timestamp(req.getIn_date().getTime()));
			//logger.info("OBJEC_TO_INSERT "+req.toString());
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static void update_request(Cb_cash_request req, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_requests t set " +
					"t.state_id = ?, " +
					"t.is_sent_in_file = ?, " +
					"t.is_sent_inkassa = ?, " +
					"t.error_code = ? " +
					"where t.id = ?");
			
			ps.setInt(1, req.getState_id());
			ps.setInt(2, req.isIs_sent_in_file()?1:0);
			ps.setInt(3, req.isIs_sent_inkassa()?1:0);
			
			if(req.getError_code() == null || req.getError_code().length() < 1)
				ps.setNull(4, java.sql.Types.VARCHAR);
			else ps.setString(4, req.getError_code());
			
			ps.setLong(5, req.getId());
			
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static void update_request(Cb_cash_request req) throws Exception
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
	
	public static Cb_cash_request parse(String str) throws NumberFormatException, ParseException
	{
		Cb_cash_request res = null;
		
		String[] splitted_string = str.split(Character.toString((char)29));
		
		res = new Cb_cash_request(
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
				null,
				false,
				false,
				null,
				new java.util.Date()
				);
		
		return res;
	}
	
	public static void save_approval(Cb_cash_inq_approval approval,
			Connection c) throws Exception
	{
			PreparedStatement ps = c.prepareStatement(
					"insert into cb_cash_rec_approvals " +
					"(id, ext_record_id, reciever_bank_oper_date, " +
					"request_bank_code, reciever_bank_code, " +
					"request_bank_inv_id, reciever_bank_inv_id, " +
					"amount, inv_number, inv_date, req_number, " +
					"req_date, in_date, state_id) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, systimestamp, ?)");
			
			ps.setLong(1, approval.getId());
			ps.setLong(2, approval.getExt_record_id());
			ps.setDate(3, new java.sql.Date(
					approval.getReciever_bank_oper_date().getTime()));
			ps.setString(4, approval.getRequest_bank_code());
			ps.setString(5, approval.getReciever_bank_code());
			if(approval.getRequest_bank_inv_id()==null)
				ps.setNull(6, java.sql.Types.NUMERIC);
				else ps.setLong(6, approval.getRequest_bank_inv_id());
			if(approval.getReciever_bank_inv_id()==null)
				ps.setNull(7, java.sql.Types.NUMERIC);
				else ps.setLong(7, approval.getReciever_bank_inv_id());
			ps.setLong(8, approval.getAmount());
			ps.setString(9, approval.getInv_number());
			ps.setDate(10, new java.sql.Date(
					approval.getInv_date().getTime()));
			ps.setString(11, approval.getReq_number());
			ps.setDate(12, new java.sql.Date(
					approval.getReq_date().getTime()));
			ps.setInt(13, approval.getState_id());
			
			ps.execute();
			
			
	}
	
	public static void update_approval(Cb_cash_inq_approval approval,
			Connection c) throws Exception
	{
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_rec_approvals t set t.state_id = ? where t.id = ?");
			
			ps.setInt(1, approval.getState_id());
			ps.setLong(2, approval.getId());
			
			ps.execute();
	}
	
	public static Cb_cash_inq_approval parse_approval(String str) 
		throws NumberFormatException, ParseException
	{
		Cb_cash_inq_approval res = null;
		
		String[] splitted_string = str.split(Character.toString((char)29));
		
		res = new Cb_cash_inq_approval(
				null, 
				file_df.parse(splitted_string[0]), 
				splitted_string[2], 
				splitted_string[1],
				splitted_string[3].length()>0?Long.parseLong(splitted_string[3]):null, 
				splitted_string[4].length()>0?Long.parseLong(splitted_string[4]):null, 
				Long.parseLong(splitted_string[5]), 
				splitted_string[6], 
				file_df.parse(splitted_string[7]), 
				splitted_string[8], 
				file_df.parse(splitted_string[9]), 
				new java.util.Date(), 
				0,
				null);
		
		return res;
	}
	
	public static void lock_account(String account, String branch) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void unlock_account(String account, String branch) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void process_requests(List<Cb_cash_request> requests) throws Exception
	{
		for(Cb_cash_request req : requests)
		{
			ISLogger.getLogger().info("processing request id " + req.getId());
			process_request(req);
		}
	}
	
	public static void do_transactions(List<Long> tr_pays) throws Exception
	{
		Connection c = null;
		Cb_cash_request req = null;
		for(Long tr_pay : tr_pays)
		{
			try
			{
				c = ConnectionPool.getConnection();
				req = get_tr_pay_request(tr_pay);
				alter_session_init(c, req.getRequest_bank_code());
				accounting_transaction.Service.action_general_doc(tr_pay,
						Integer.parseInt(ConnectionPool.getValue(
								"cb_cach_request_general_action")),
						c);
				update_request_by_tr_pay(tr_pay, c);
				c.commit();
			}
			catch (Exception e)
			{
				if(c!=null)c.rollback();
				if(req!=null && req.getRequest_bank_code()!=null && req.getId()!=null)
				{
					/*if(e.getMessage().toUpperCase().contains("ÎÂÅÐÄÐÀÔÒ"))
					{
						long duration = new java.util.Date().getTime() - req.getIn_date().getTime();
						long hours_from_rec = TimeUnit.MILLISECONDS.toHours(duration);
						if(hours_from_rec > 3) 
						{
							req.setError_code("00001");
							req.setState_id(3);
							update_request(req);
						}
						else
						{
							req.setError_code("00001");
							req.setState_id(2);
							update_request(req);
						}
					}*/
					logger.error("request id "+req.getId()+ " branch "+req.getRequest_bank_code()
							+" " + CheckNull.getPstr(e));
					write_processing_record(
							new Cb_cash_processing_record(
									null,
									req.getId(),
									null,
									"00000",
									e.getMessage(),
									false
									));
				}
				else
					logger.error(CheckNull.getPstr(e));
			}
			finally
			{
				if(c!=null)c.close();
			}
		}
	}
	
	public static void process_request(Cb_cash_request request) throws Exception
	{
		Connection c = null;
		//int current_action_id = 0;
		try
		{
			//ISLogger.getLogger().info("processing request id " + request.getId()+" prc");
			c = ConnectionPool.getConnection();
			alter_session_init(c, request.getRequest_bank_code());
			//ISLogger.getLogger().info("processing request id " + request.getId()+" ts");
			TransactionService ts = new TransactionService();
			ts.init(c);
			CallableStatement cs_prep = accounting_transaction.Service.init_get_deal_general(c);
			long operation_id = 2000l;
			
			//ISLogger.getLogger().info("processing request id " + request.getId()+" type >" 
			//		+ request.getRequest_type_code()+"<");
			
			if(request.getRequest_type_code().equals("1")) operation_id = 2000l;
			if(request.getRequest_type_code().equals("2")) operation_id = 2006l;
			if(request.getRequest_type_code().equals("3")) operation_id = 2007l;
			if(request.getRequest_type_code().equals("4")) operation_id = 2008l;
			//ISLogger.getLogger().info("processing request id " + request.getId()+" operation " + operation_id);
			Parameters ps = new Parameters();
			ps.put("branch", request.getRequest_bank_code());
			ps.put("operation_id", operation_id);
			ps.put("parent_group_id", 181l);
			ps.put("parent_deal_id", 1);
			ps.put("SUMMA", request.getAmount());
			ps.put("PARENT_ID", request.getId());
			ps.put("cs_prep", cs_prep);
			//long object_id = com.is.file_reciever_srv.ext_utils.Service.get_created_object_id(c);
			//ISLogger.getLogger().info("processing request id " + request.getId()+" executing ");
				long tr_id = 0;
				tr_id = ts.execute_operation(
						operation_id,
						ps, 
						c);
				//logger.info("tr_pay_id: " + tr_id);
				
				request.setIs_sent_in_file(false);
				request.setIs_sent_inkassa(false);
				request.setError_code(null);
				request.setState_id(2);
				update_request(request, c);
				//ISLogger.getLogger().info("processing request id " + request.getId()+" executing ready ");
			c.commit();
			do_transactions(get_tr_pays_to_do_transactions());
			//ISLogger.getLogger().info("processing request id " + request.getId()+" transactions done ");
			c.commit();
		}
		catch(Exception e)
		{
			write_processing_record(
					new Cb_cash_processing_record(
							null,
							request.getId(),
							null,
							"00000",
							e.getMessage(),
							false
							));
			if(c!=null)c.rollback();
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static void write_processing_record(
			Cb_cash_processing_record processing_record, 
			Connection c
			) throws Exception
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into cb_cash_request_processing " +
					"(id, request_id, action_id, code, message, critical) " +
					"values (seq_cb_cash_req_proc.nextval, ?, ?, ?, ?, ?)");
			ps.setLong(1, processing_record.getRequest_id());
			if(processing_record.getAction_id()!=null)
				ps.setInt(2, processing_record.getAction_id());
			else
				ps.setNull(2, java.sql.Types.NUMERIC);
			
			if(processing_record.getCode()!=null)
				ps.setString(3, processing_record.getCode());
			else
				ps.setNull(3, java.sql.Types.VARCHAR);
			
			ps.setString(4, processing_record.getMessage());
			ps.setInt(5, processing_record.isCritical()?1:0);
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
	
	public static void write_processing_record(
			Cb_cash_processing_record processin_record
			) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			write_processing_record(processin_record, c);
			c.commit();
		}
		catch(Exception e)
		{
			if(c!=null)c.rollback();
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null)c.close();
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
	
	public static boolean is_request_sent_inkass(Cb_cash_request req)
	{
		return false;
	}
	
	public static void create_out_files() throws Exception
	{
		List<String> branches_to_send_files = get_branches_to_send_files();
		for(String current_branch : branches_to_send_files)
		{
			try
			{
				create_file(current_branch);
			}
			catch(Exception e)
			{
				logger.error(CheckNull.getPstr(e));
			}
		}
	}
	
	public static void create_file(String branch) throws Exception
	{
		Date oper_date = get_operdate(branch);
		logger.info("creating cb_cash_answ file for branch:"+branch);
		List<Cb_cash_request> requests_to_send = get_requests_to_send(branch);
		String tmp_out_direcory = ConnectionPool.getValue("cb_cash_requests_out_tmp");
		String out_direcory = ConnectionPool.getValue("cb_cash_requests_out");
		String arch_out_direcory = ConnectionPool.getValue("cb_cash_requests_out_arch") + 
		sdf_dir.format(oper_date);
		
		if(!new File(tmp_out_direcory).exists())new File(tmp_out_direcory).mkdirs();
		if(!new File(out_direcory).exists())new File(out_direcory).mkdirs();
		if(!new File(arch_out_direcory).exists())new File(arch_out_direcory).mkdirs();
		//logger.info("oper_date: "+oper_date);
		//logger.info("get reice_number ");
		long prev_reice_number = get_reice_number(branch, 1, oper_date);
		//logger.info("reice_number: "+prev_reice_number);
		long reice_number = prev_reice_number + 1;
		//logger.info("new reice_number: "+reice_number);
		String str_reice_number = get_character_string_from_number(
				reice_number,
				2);
		//logger.info("reice_number: "+str_reice_number);
		String file_name = "K#" + 
						branch.substring(1) + 
						str_reice_number + 
						"." + 
						get_36_date(oper_date);
		//logger.info("file_name "+file_name);
		String arch_name = "KM" + file_name.substring(2);
		//logger.info("arch_name "+arch_name);
		StringBuffer file_content = new StringBuffer();
		
		int string_counter = 0;
		//long file_summ = 0;
		for(Cb_cash_request req : requests_to_send)
		{
			string_counter ++;
			StringBuffer line_buffer = new StringBuffer();
			
			line_buffer.append(req.getCb_request_number()).
						append(Character.toString((char)29)).
						append(Long.toString(req.getId())).
						append(Character.toString((char)29)).
						append(file_df.format(oper_date)).
						append(Character.toString((char)29)).
						append(branch).
						append(Character.toString((char)29)).
						append(
								(req.getError_code()!=null&&
										req.getError_code().length()>0)?
												req.getError_code():
													"00000"
													).
						append(Character.toString((char)29));
			
			//long control_summ = string_checks.get_control_sum(line_buffer.toString());
			//file_summ += control_summ;
			
			//logger.info("line "+line_buffer.toString());
			file_content.append(line_buffer).
						//append(Character.toString((char)29)).
						//append(decimalFormat.format(control_summ)).
						//append(Character.toString((char)29)).
						append("\n");
			
		}
		
	/*	String control_string = "0"+					Character.toString((char)29)+
			file_sent_df.format(new java.util.Date())+	Character.toString((char)29)+
			get_character_string_from_number(
					prev_reice_number,
					2)+									Character.toString((char)29)+
					Integer.toString(string_counter)+	Character.toString((char)29)+
					Long.toString(file_summ)+			Character.toString((char)29)+"\n";
		*/
		logger.info("creating cb_cash_answ file "+tmp_out_direcory + file_name);
		BufferedWriter out = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(tmp_out_direcory + file_name)
                ));
		
		//out.write(control_string);
		out.write(file_content.toString());
		out.write(Character.toString((char)12));
		out.close();
		//logger.info(" file ready "+tmp_out_direcory + file_name);
		logger.info("file cb_cash_answ created: " + tmp_out_direcory + file_name);
		
		//logger.info("archivating "+tmp_out_direcory + file_name);
		Process p = Runtime.getRuntime().exec(
				"arj32.exe a -e " + tmp_out_direcory + arch_name + 
				" " + tmp_out_direcory + file_name);
		p.waitFor();
		//logger.info("archived ");
		new File(tmp_out_direcory + file_name).delete();
		//logger.info("deleted ");
		FileUtils.copyFile(
				new File(tmp_out_direcory + arch_name), 
				new File(arch_out_direcory + arch_name)
				);
		//logger.info("copied");
		new File(tmp_out_direcory + arch_name).renameTo(new File(out_direcory + arch_name));
		//logger.info("updaing");
		update_file(
				requests_to_send, 
				reice_number, 
				oper_date, 
				branch, 
				1
				);
		//logger.info("updated");
	}
	
	public static void update_file(
			List<Cb_cash_request> requests, 
			long reice_number, 
			Date sent_date, String branch, int file_type) throws Exception
	{
	//	logger.info("savig reis: "+reice_number+" branch "+branch+" file_type "+file_type+
	//			" sent_date "+sent_date);
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			for(Cb_cash_request req : requests)
			{
				req.setIs_sent_in_file(true);
				update_request(req, c);
			}
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_out_files_reices t " +
					"set t.last_reice_number = ?, t.last_reice_date = ? " +
					"where t.file_type_id = ? and t.branch = ?");
			ps.setLong(1, reice_number);
			ps.setDate(2, new java.sql.Date(sent_date.getTime()));
			ps.setInt(3, file_type);
			ps.setString(4, branch);
			ps.execute();
			c.commit();
		}
		catch(Exception e)
		{
		//	logger.error("error savig reis: "+CheckNull.getPstr(e));
			throw e;
		}
		finally
		{
			if(c!=null)c.close();
		}
	}
	
	public static Long get_reice_number(String branch, int file_type_id, Date date) throws Exception
	{
		Long res = null;
		
		Connection c = null;
		try
		{
			//logger.info("ges_reie_number proc");
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_out_files_reices t " +
					"where t.file_type_id = ? and t.branch = ?");
			ps.setInt(1, file_type_id);
			ps.setString(2, branch);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				//logger.info("ges_reie_number proc "+file_df.format(rs.getDate("last_reice_date")) + " " +
				//		file_df.format(date));
				if(!file_df.format(rs.getDate("last_reice_date")).equals(
						file_df.format(date)))
					res = 0l;
				else res = rs.getLong("last_reice_number");
				//logger.info("ges_reie_number proc res" + res);
				return res;
			}
			else throw new Reciever_exception(
					"no last reice number for file type " + file_type_id +
					" and branch " + branch);
			
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
	
	public static List<Cb_cash_request> get_requests_to_send(String branch) throws Exception
	{
		List<Cb_cash_request> res = new ArrayList<Cb_cash_request>();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_requests t " +
					"where " +
					"t.is_sent_in_file = 0 and t.request_bank_code = ?");
			ps.setString(1, branch);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.add(new Cb_cash_request(
						rs.getLong("id"),
						rs.getLong("ext_record_id"),
						rs.getString("cb_request_number"),
						rs.getString("request_type_code"),
						rs.getDate("oper_day"),
						rs.getString("reciever_bank_code"),
						rs.getString("request_bank_code"),
						rs.getString("request_number"),
						rs.getDate("request_date"),
						rs.getLong("amount"),
						rs.getString("additional_info"),
						rs.getInt("state_id"),
						rs.getInt("is_sent_in_file") == 1,
						rs.getInt("is_sent_inkassa") == 1,
						rs.getString("error_code"),
						rs.getTimestamp("in_date")
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
	
	public static List<String> get_branches_to_send_files() throws Exception
	{
		List<String> res = new ArrayList<String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select t.request_bank_code res " +
					"from cb_cash_requests t " +
					"where t.is_sent_in_file = 0 " +
					"group by t.request_bank_code");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.add(rs.getString("res"));
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
	
	public static String get_character_string_from_number(Long number, Integer length)
	{
		String res = null;
		String num_36 = get_36_from_10_number(number);
		if(length == null) res = num_36;
		else 
		{
			res = StringUtils.repeat("0", length - num_36.length()) + num_36;
		}
		return res;
	}
	
	public static String get_36_from_10_number(long num)
	{
		StringBuffer res = new StringBuffer();
		
		int i = 0;
		
		long tmp = num;
		
		if(num == 0) return "0";
		
		while (tmp > 0)
		{
			long n = tmp % 36;
			tmp = (int)Math.floor(tmp / 36);
			res.append(get_str_36_from_num((int)n));
		}
		res = res.reverse();
		return res.toString();
	}
	
	private static String get_str_36_from_num(int inp)
	{
		String res = null;
		if (inp <= 9) res = Integer.toString(inp);
		else
		{
			switch (inp)
			{
				case 10: res = "A"; break;
				case 11: res = "B"; break;
				case 12: res = "C"; break;
				case 13: res = "D"; break;
				case 14: res = "E"; break;
				case 15: res = "F"; break;
				case 16: res = "G"; break;
				case 17: res = "H"; break;
				case 18: res = "I"; break;
				case 19: res = "J"; break;
				case 20: res = "K"; break;
				case 21: res = "L"; break;
				case 22: res = "M"; break;
				case 23: res = "N"; break;
				case 24: res = "O"; break;
				case 25: res = "P"; break;
				case 26: res = "Q"; break;
				case 27: res = "R"; break;
				case 28: res = "S"; break;
				case 29: res = "T"; break;
				case 30: res = "U"; break;
				case 31: res = "V"; break;
				case 32: res = "W"; break;
				case 33: res = "X"; break;
				case 34: res = "Y"; break;
				case 35: res = "Z"; break;
			}
		}
		return res;
	}
	
	public static String get_36_date(Date dt)
	{
		String res = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		Calendar cn = Calendar.getInstance();
		cn.setTime(dt);
		
		res = get_36_from_10_number(cn.get(Calendar.DAY_OF_MONTH))+
			get_36_from_10_number(cn.get(Calendar.MONTH)+1)+
			get_36_from_10_number(cn.get(Calendar.YEAR)%2000);
	
		return res;
	}
	
	public static Date get_operdate(String branch) throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			alter_session_init(c, branch);
			return get_day(c);
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
	
	public static Date get_day(Connection c) throws Exception
	{
		CallableStatement ps = null;
		ResultSet rs = null;
		Date res = null;
		try
		{
			ps = c.prepareCall("{? = call info.getDay() }");
			ps.registerOutParameter(1, java.sql.Types.DATE);
			
			ps.execute();
			res = ps.getDate(1);
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)rs.close();
			if(ps != null)ps.close();
		}
	}
	
	public static Date get_RKC() throws Exception
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			return get_day(c);
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
	
	public static String get_RKC(Connection c) throws Exception
	{
		CallableStatement ps = null;
		ResultSet rs = null;
		String res = null;
		try
		{
			ps = c.prepareCall("{? = call info.GetHeaderId() }");
			ps.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			ps.execute();
			res = ps.getString(1);
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)rs.close();
			if(ps != null)ps.close();
		}
	}
	
	public static List<Cb_cash_request> get_requests_to_proceed() throws Exception
	{
		List<Cb_cash_request> res = new ArrayList<Cb_cash_request>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_requests t " +
					"where t.state_id = 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.add(new Cb_cash_request(
						rs.getLong("id"),
						rs.getLong("ext_record_id"),
						rs.getString("cb_request_number"),
						rs.getString("request_type_code"),
						rs.getDate("oper_day"),
						rs.getString("reciever_bank_code"),
						rs.getString("request_bank_code"),
						rs.getString("request_number"),
						rs.getDate("request_date"),
						rs.getLong("amount"),
						rs.getString("additional_info"),
						rs.getInt("state_id"),
						rs.getInt("is_sent_in_file") == 1,
						rs.getInt("is_sent_inkassa") == 1,
						rs.getString("error_code"),
						rs.getTimestamp("in_date")
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
	
	public static List<Long> get_tr_pays_to_do_transactions() throws Exception
	{
		List<Long> res = new ArrayList<Long>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select " +
					"distinct t.id res from bf_tr_pay t, " +
					"cb_cash_requests r, bf_tr_paydocs pd " +
					"where r.state_id = 2 and " +
					"t.parent_group_id = 181 and " +
					"pd.pay_id = t.id and " +
					"pd.parent_group_id = 181 and " +
					"pd.parent_id = r.id and pd.parent_deal_id = 1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res.add(rs.getLong("res"));
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
	
	public static Cb_cash_request get_tr_pay_request(long tr_pay_id) throws Exception
	{
		Cb_cash_request res = null;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_requests rq where rq.id = " +
					"(select distinct pd.parent_id from bf_tr_paydocs pd " +
					"where pd.pay_id = ? and pd.parent_group_id = 181 and pd.parent_deal_id = 1)");
			ps.setLong(1, tr_pay_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res = new Cb_cash_request(
						rs.getLong("id"),
						rs.getLong("ext_record_id"),
						rs.getString("cb_request_number"),
						rs.getString("request_type_code"),
						rs.getDate("oper_day"),
						rs.getString("reciever_bank_code"),
						rs.getString("request_bank_code"),
						rs.getString("request_number"),
						rs.getDate("request_date"),
						rs.getLong("amount"),
						rs.getString("additional_info"),
						rs.getInt("state_id"),
						rs.getInt("is_sent_in_file") == 1,
						rs.getInt("is_sent_inkassa") == 1,
						rs.getString("error_code"),
						rs.getTimestamp("in_date")
						);
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
	
	public static Long get_tr_pay_for_request(long request_id) throws Exception
	{
		Long res = null;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from( " +
					"select pd.pay_id res from bf_tr_paydocs pd where pd.parent_group_id = 181 " + 
					"and pd.parent_id = ? " +
					"and pd.parent_deal_id = 1 " +
					"order by pd.id desc) where rownum = 1");
			ps.setLong(1, request_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getLong("res");
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
	
	public static void update_request_by_tr_pay(Long tr_pay_id, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update cb_cash_requests r " +
					"set r.state_id = 3 " +
					"where r.id = (" +
					"select distinct pd.parent_id " +
					"from bf_tr_paydocs pd where pd.pay_id = ? " +
					"and pd.parent_group_id = 181 and pd.parent_deal_id = 1)");
			ps.setLong(1, tr_pay_id);
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static void update_paydocs_amount(Long tr_pay_id, long amount, Connection c) throws Exception
	{
		try
		{
			PreparedStatement ps = c.prepareStatement(
					"update bf_tr_paydocs pd set pd.summa = ? where pd.pay_id = ?"
					);
			ps.setLong(1, amount);
			ps.setLong(2, tr_pay_id);
			ps.execute();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static Long get_paydoc_amount(Long tr_pay_id) throws Exception
	{
		Long res = null;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select pd.summa res from bf_tr_paydocs pd where pd.pay_id = ?");
			ps.setLong(1, tr_pay_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res = rs.getLong("res");
			}
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(c != null)c.close();
		}
	}
	
	public static Cb_cash_request get_request_by_cb_request_number
	(String cb_request_number, String request_type_code, String branch) throws Exception
	{
		Cb_cash_request res = null;
		
		Connection c = null;
		try
		{
			//logger.info("req_rev select cb_request_number " + cb_request_number);
			//logger.info("req_rev select request_type_code " + request_type_code);
			//logger.info("req_rev select branch " + branch);
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select * from cb_cash_requests t " +
					"where t.cb_request_number = ? and t.request_type_code = ? and " +
					"t.request_bank_code = ?");
			ps.setString(1, cb_request_number);
			ps.setString(2, request_type_code);
			ps.setString(3, branch);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				//logger.info("req_rev select line ");
				res = new Cb_cash_request(
						rs.getLong("id"),
						rs.getLong("ext_record_id"),
						rs.getString("cb_request_number"),
						rs.getString("request_type_code"),
						rs.getDate("oper_day"),
						rs.getString("reciever_bank_code"),
						rs.getString("request_bank_code"),
						rs.getString("request_number"),
						rs.getDate("request_date"),
						rs.getLong("amount"),
						rs.getString("additional_info"),
						rs.getInt("state_id"),
						rs.getInt("is_sent_in_file") == 1,
						rs.getInt("is_sent_inkassa") == 1,
						rs.getString("error_code"),
						rs.getTimestamp("in_date")
						);
				//logger.info("req_rev select line req " + res.toString());
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
}
