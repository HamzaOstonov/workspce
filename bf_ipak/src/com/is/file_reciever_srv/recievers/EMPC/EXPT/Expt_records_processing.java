package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class Expt_records_processing
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
		this.terminal = c.prepareStatement("select acc from bf_globuz_acc_tr_all where terminal_id = ?");
		this.merchant = c.prepareStatement("select acc from bf_globuz_merchants_all where merchant = ?");
		this.updateState = c.prepareStatement("update empc_expt_records set state_id = ? where id = ?");			
		this.updateFileState = c.prepareStatement("update empc_files set state_id = ? where id = ?");
		this.insertError = c.prepareStatement("insert into empc_expt_records_prc_errors (record_id, text) values(?, ?)");
		this.deleteError = c.prepareStatement("delete from empc_expt_records_prc_errors where record_id = ?");
	}
	
	public void process_file(Long file_id)
	{
		try
		{
			load_config_map();
			
			c = ConnectionPool.getConnection();
			
			alter_session_init(c, "01066");
			this.init(c);			
			
			PreparedStatement ps_records = c.prepareStatement("select * from empc_expt_records r " +
					"where r.empc_file_id = ? and state_id != ?");
			ps_records.setLong(1, file_id);
			ps_records.setLong(2, 2);
			ResultSet rs = ps_records.executeQuery();				
			
			while(rs.next())
			{
				EXPT_record record = new EXPT_record(
						rs.getLong("id"),
						rs.getLong("empc_file_id"),
						rs.getInt("state_id"),
						rs.getString("record_type"),
						rs.getLong("line_number"),
						rs.getString("client"),
						rs.getString("card_acct"),
						rs.getString("accnt_ccy"),
						rs.getString("card"),
						rs.getString("slip_nr"),
						rs.getString("ref_number"),
						rs.getDate("tran_date_time"),
						rs.getDate("rec_date"),
						rs.getDate("post_date"),
						rs.getString("deal_desc"),
						rs.getString("tran_type"),
						rs.getString("deb_cred"),
						rs.getString("tran_ccy"),
						rs.getLong("tran_amt"),
						rs.getLong("accnt_amt"),
						rs.getString("terminal"),
						rs.getString("mcc_code"),
						rs.getString("merchant"),
						rs.getString("abvr_name"),
						rs.getString("country"),
						rs.getString("city"),
						rs.getLong("proc_id"),
						rs.getLong("internal_no"),
						rs.getLong("product"),
						rs.getString("iss_mfo"),
						rs.getString("term_id"),
						rs.getString("tranz_acct"));
				
				this.process_record(record);
			}			
			if(!file_has_errors)
			{
				updateFileState.setLong(1, 2);
				updateFileState.setLong(2, file_id);
				updateFileState.executeUpdate();
			}
			c.commit();
		}
		catch(Exception e)
		{
			try {c.rollback();} catch(SQLException exeption){}
			ISLogger.getLogger().error("Processing GLOBUZ file error: " + CheckNull.getPstr(e));			
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
	
	public void process_record(EXPT_record record) throws SQLException
	{
		try
		{
		
			if(transactions_config.containsKey(record.getTran_type()))
				if(transactions_config.get(record.getTran_type()).size() == 1)
				{
					TransactionService ts = new TransactionService();
					ts.init(c);
					CallableStatement cs_prep = accounting_transaction.Service.init_get_deal_general(c);
					Long operation_id = (Long)transactions_config.get(record.getTran_type()).values().toArray()[0];	
					
					if(operation_id == null) throw new IndexOutOfBoundsException("Не найдена операция");
					
					if(operation_id != -1) {
					
						Parameters ps = new Parameters();
						ps.put("branch", "01066");
						ps.put("operation_id", operation_id);
						ps.put("ACC_22618", record.getTranz_acct());
						
					
						terminal.setString(1, record.getTerm_id());
						ResultSet rsTerm = terminal.executeQuery();			
						if(rsTerm.next()) {
							ps.put("ACC_23510TERM", rsTerm.getString("ACC"));
						}
						
						String merchantId = "";
						merchantId += record.getMerchant();
						merchantId = merchantId.substring(1);
						merchantId = "J" + merchantId;
						merchant.setString(1, merchantId);
						ResultSet rsMerch = merchant.executeQuery();
						if(rsMerch.next()) {
							ps.put("ACC_20208MERCH", rsMerch.getString("ACC"));
						}
						
						ps.put("parent_group_id", 198l);
						ps.put("parent_deal_id", 1);
						ps.put("SUMMA", record.getTran_amt());
						ps.put("PARENT_ID", record.getId());
						ps.put("cs_prep", cs_prep);
						
						
						long tr_id = 0;
						tr_id = ts.execute_operation(
								operation_id,
								ps, 
								c);
						
						accounting_transaction.Service.action_general_doc(tr_id,
								1,
								c);
					}
					
					updateState.setInt(1, 2);
					updateState.setLong(2, record.getId());
					updateState.executeUpdate();
					
					deleteError.setLong(1, record.getId());
					deleteError.executeUpdate();
				}

		}
		catch(Exception e)
		{
			c.rollback();
			updateState.setInt(1, 3);
			updateState.setLong(2, record.getId());
			updateState.executeQuery();
			
			file_has_errors = true;

			updateFileState.setLong(1, 3);
			updateFileState.setLong(2, record.getEmpc_file_id());
			updateFileState.executeUpdate();
			
			deleteError.setLong(1, record.getId());
			deleteError.executeUpdate();
			
			insertError.setLong(1, record.getId());
			insertError.setString(2, e.getMessage());
			if(insertError.executeUpdate() == 0) {
				System.out.println(("Error text is not inserted."));
			}
		}
		finally
		{
			c.commit();
		}
	}
	
	public void close() throws SQLException
	{
		if(terminal != null) terminal.close();
		if(merchant != null) merchant.close();
		if(updateState != null) updateState.close();
		if(updateFileState != null) updateFileState.close();
		if(insertError != null) insertError.close();
	}

	
	public static void load_config_map() throws SQLException
	{
		if(transactions_config != null) return;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			transactions_config = new HashMap<String, HashMap<String,Long>>();
			PreparedStatement ps = c.prepareStatement("select * from empc_expt_tran_typ_oper");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if (!transactions_config.containsKey(rs.getString("tran_type")))
					transactions_config.put(rs.getString("tran_type"), new HashMap<String, Long>());
				
				transactions_config.get(rs.getString("tran_type")).
						put(rs.getString("other_cond"), rs.getLong("operation_id"));
			}
		}
		finally
		{
			if(c != null) c.close();
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
			if(ps != null) ps.close();
			if(rs != null) rs.close();
			if(st != null) st.close();
		}
	}
	
	public static void updateFileState(Long fileId) {
		long errNumber = 0;		
    	Connection c = null;
    	PreparedStatement ps = null;
    	PreparedStatement updateFileState = null;
    	ResultSet rs = null;
    	
    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT count(*)nr FROM empc_expt_records WHERE empc_file_id = ? AND state_id != ?");
    		
    		ps.setLong(1, fileId);
    		ps.setLong(2, 2);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			errNumber = rs.getLong("nr");
    			if(errNumber == 0) {
    				updateFileState = c.prepareStatement("update empc_files set state_id = ? where id = ?");
    				updateFileState.setLong(1, 2);
    				updateFileState.setLong(2, fileId);
    				updateFileState.executeUpdate();
    				c.commit();
    			}
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		ConnectionPool.close(c);
    	}
		
	}
}
