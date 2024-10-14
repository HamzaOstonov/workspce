package com.is.file_reciever_srv.recievers.tieto_files;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.mail.Session;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class TietoRecordsProcessing
{
	private PreparedStatement terminal = null;
	private PreparedStatement merchant = null;
	private PreparedStatement updateState = null;			
	private PreparedStatement updateFileState = null;
	private PreparedStatement insertError = null;
	private PreparedStatement deleteError = null;
	private Connection c = null;
	private static HashMap<String, HashMap<String, Long>> transactions_config = null;
	private boolean file_has_errors = false;

	public void init(Connection c) throws SQLException
	{
		this.c = c;
		this.updateState = c.prepareStatement("update tieto_expt_records set state_id = ? where id = ?");			
		this.updateFileState = c.prepareStatement("update tieto_files set state_id = ? where id = ?");
		this.insertError = c.prepareStatement("insert into tieto_expt_records_prc_errors (record_id, text) values(?, ?)");
		this.deleteError = c.prepareStatement("delete from tieto_expt_records_prc_errors where record_id = ?");
	}
	
	public void process_file(Long file_id)
	{
		try
		{
			String currentBranch = "";
			
			load_config_map();
			
			c = ConnectionPool.getConnection();	
			
			this.init(c);			
			
			PreparedStatement ps_records = c.prepareStatement("SELECT * FROM tieto_expt_records r WHERE r.tieto_file_id = ? AND r.state_id != 2 ORDER BY mfo_full");
			ps_records.setLong(1, file_id);
			ResultSet rs = ps_records.executeQuery();
			
			while(rs.next())
			{
				if(!currentBranch.equals(rs.getString("mfo_full"))) {
					currentBranch = rs.getString("mfo_full");
					alter_session_init(c, rs.getString("mfo_full"));
				}
				
				TietoRecord record = new TietoRecord(
						rs.getLong("id"),
						rs.getLong("tieto_file_id"),
						rs.getInt("state_id"),
						rs.getString("record_type"),
						rs.getLong("line_number"),
						rs.getString("mfo_short"),
						rs.getString("tranz_acct"),
						rs.getString("fullname"),
						rs.getString("deb_cred"),
						rs.getString("card_number"),
						rs.getString("tran_type"),
						rs.getLong("tran_amt"),
						rs.getString("currency"),
						rs.getDate("tran_date"),
						rs.getString("country_code"),
						rs.getString("mcc_code"),
						rs.getString("mfo_full"),
						rs.getString("merchant_title"),
						rs.getString("passport_info"),
						rs.getLong("comission"),
						rs.getString("merchant_code"),
						rs.getString("terminal_code"),
						rs.getString("city"));
				
				this.process_record(record, currentBranch);
			}			
			if(!file_has_errors)
			{
				updateFileState.setLong(1, Constants.FILE_PROCESSED);
				updateFileState.setLong(2, file_id);
				updateFileState.executeUpdate();
			}
			c.commit();
		}
		catch(Exception e)
		{
			try {c.rollback();} catch(SQLException exeption){}
			ISLogger.getLogger().error("Processing TIETO file error: " + CheckNull.getPstr(e));			
		}
		finally
		{
			if(c != null) try {c.close();} catch (SQLException e) {}
			
			try {
				this.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void process_record(TietoRecord record, String branch) throws SQLException
	{
		ISLogger.getLogger().error("Processing record: " + record.getId());
		try
		{
			if(transactions_config.containsKey(record.getTran_type()))
				if(transactions_config.get(record.getTran_type()).size() == 1)
				{
					if(record.getTranz_acct().substring(17).equals("901") &&
							(record.getTran_type().equals("205") ||
							record.getTran_type().equals("207") ||
							record.getTran_type().equals("227") ||
							record.getTran_type().equals("206") ||
							record.getTran_type().equals("11C"))) {
					
					
						TransactionService ts = new TransactionService();
						ts.init(c);
						CallableStatement cs_prep = accounting_transaction.Service.init_get_deal_general(c);
						long operation_id = (Long)transactions_config.get(record.getTran_type()).values().toArray()[0];
						
						Parameters ps = new Parameters();
						ps.put("branch", branch);
						ps.put("operation_id", operation_id);	
						ps.put("ACC_22618", record.getTranz_acct());
						ps.put("comamount", record.getComission());
					
						ps.put("parent_group_id", Constants.PARENT_GROUP_ID);
						ps.put("parent_deal_id", Constants.PARENT_DEAL_ID); 
						ps.put("SUMMA", record.getTran_amt());
						ps.put("PARENT_ID", record.getId());
						ps.put("cs_prep", cs_prep);
						
						
						// Tags: @FULLNAME@, @AMOUNT@, @RATE@, @DATE@					
						
	//					switch (Constants.Operations.getLong(operation_id)) {
	//					case BUY:
	//					case SELL:
	//					{
	//						ps.put("FULLNAME", record.getFullname());
	//						ps.put("AMOUNT", record.getTran_amt().toString());
	//						ps.put("RATE", TietoService.getRate());
	//					}
	//						break;
	//						
	//					case BUY_SUM:
	//					case SELL_SUM:
	//					{
	//						ps.put("FULLNAME", record.getFullname());
	//						ps.put("AMOUNT", record.getTran_amt().toString());
	//					}
	//						break;
	//						
	//					case CLEARING:
	//					{
	//						ps.put("DATE", record.getTran_date().toString());
	//					}
	//						break;
	//						
	////					case TERMINAL:
	////						
	////						break;
	//						
	//					case VISA_REFILL:
	//					case VISA_WITHDRAW:
	//					{
	//						ps.put("FULLNAME", record.getFullname());
	//						ps.put("DATE", record.getTran_date().toString());
	//					}
	//						break;
	//
	//					default:
	//						break;
	//					}
						
						
						
						long tr_id = 0;
						tr_id = ts.execute_operation(
							operation_id,
							ps, 
							c);
	
						accounting_transaction.Service.action_general_doc(
							tr_id,
							Constants.ACTION_ID_INPUT,
							c);
					
					}
					
					updateState.setLong(1, Constants.RECORD_PROCESSED);
					updateState.setLong(2, record.getId());
					updateState.executeUpdate();
				}
		}
		catch(Exception e)
		{
			c.rollback();
			updateState.setLong(1, Constants.RECORD_ERROR);
			updateState.setLong(2, record.getId());
			updateState.executeQuery();
			
			file_has_errors = true;

			updateFileState.setLong(1, Constants.FILE_PROCESSED_WITH_ERRORS);
			updateFileState.setLong(2, record.getTieto_file_id());
			updateFileState.executeUpdate();
			
			deleteError.setLong(1, record.getId());
			deleteError.executeUpdate();
			
			insertError.setLong(1, record.getId());
			insertError.setString(2, CheckNull.getPstr(e).length()>500?CheckNull.getPstr(e).substring(0, 500):CheckNull.getPstr(e));
			if(insertError.executeUpdate() == 0) {
				System.out.println(("Error text is not inserted."));
			}
			ISLogger.getLogger().error("Processing TIETO record error: " + CheckNull.getPstr(e));

		}
		finally
		{
			c.commit();
		}
	}
	
	public void close() throws SQLException
	{
		if(terminal != null) {
			terminal.close();
		}
		if(merchant != null) {
			merchant.close();
		}
		if(updateState != null) {
			updateState.close();
		}
		if(updateFileState != null) {
			updateFileState.close();
		}
		if(insertError != null) {
			insertError.close();
		}
	}

	
	private static void load_config_map() throws SQLException
	{
		if(transactions_config != null) {
			return;
		}
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			transactions_config = new HashMap<String, HashMap<String,Long>>();
			
			PreparedStatement ps = c.prepareStatement("SELECT * FROM tieto_expt_tran_typ_oper");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if (!transactions_config.containsKey(rs.getString("tran_type"))) {
					transactions_config.put(rs.getString("tran_type"), new HashMap<String, Long>());
				}
				
				transactions_config.get(
						rs.getString("tran_type")).put(rs.getString("other_cond"), rs.getLong("operation_id"));
			}
		}
		finally
		{
			if(c != null) {
				c.close();
			}
		}
	}
	
	public static void alter_session_init(Connection c, String branch) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		try
		{
			ps = c.prepareStatement("SELECT t.user_name res FROM ss_dblink_branch t WHERE t.branch = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			
			String alias = null;
			
			if(rs.next()) {
				alias = rs.getString("res");
			} 
			else {
				throw new com.is.file_reciever_srv.exception.Reciever_exception("Wrong branch: " + branch);
			}
			
			st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET nls_date_format='dd.mm.yyyy'");
			st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
			st.execute("{call info.init()}");
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
		}
	}
}

