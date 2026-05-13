 package com.is.file_reciever_srv.recievers.visa.onus;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;



import accounting_transaction.TransactionService;


import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;

import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

public class Onus_records_processing
{
	private PreparedStatement terminal = null;
	private PreparedStatement merchant = null;
	private PreparedStatement operation = null;
	private PreparedStatement updateState = null;			
	private PreparedStatement updateFileState = null;
	private PreparedStatement insertError = null;
	private PreparedStatement deleteError = null;
	private Connection c = null,cMFO=null;
	private static HashMap<String, HashMap<String, String>> transactions_config = null;
	private static HashMap<String, String> branchMap=null;
	private static PreparedStatement psBranch = null;
	private static ResultSet rsBranch = null;
	private boolean file_has_errors = false;
	private String branch = "";
	private static final Logger logger = ISLogger.getLogger();
	TransactionService ts = new TransactionService();
	
	public void init(Connection c) throws SQLException
	{
		System.out.println("Update EXPT");
		this.c = c;
		this.operation = c.prepareStatement("select COUNT (*) from BF_TR_TEMPLATE t where t.operation_id= ?");
		this.updateState = c.prepareStatement("update visa_onus_records set state_id = ? where id = ?");			
		this.updateFileState = c.prepareStatement("update visa_files set state_id = ? where id = ?");
		this.insertError = c.prepareStatement("insert into visa_onus_records_prc_errors (record_id, text) values(?, ?)");
		this.deleteError = c.prepareStatement("delete from visa_onus_records_prc_errors where record_id = ?");
	}
	
	public void initMFO(Connection c) throws SQLException
	{
		this.c = c;
		this.terminal = c.prepareStatement("select acc from bf_globuz_acc_tr_all where terminal_id = ?");
		this.merchant = c.prepareStatement("select acc from bf_globuz_merchants_all where merchant = ?");
		
	}
	
	
//	public String getBankId(int humocardsId) {
//
//	    String branchId;
//		Connection c = null;
//
//		try {
//			c = ConnectionPool.getConnection();
//			PreparedStatement ps = c
//					.prepareStatement("SELECT bank_id FROM branch");
//			
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				branchId=rs.getString("bank_id");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			ConnectionPool.close(c);
//		}
//		return branchId;
//	}

	
	
	public void process_file(Long file_id)
	{
//		try
//		{
//			System.out.println("protses FIle");
//			
//		
//			load_config_map();
//			//load_branch(c);
//			//alter_session_init(c, branch);
//			//alter_session_init(c, "01066");
//			c = ConnectionPool.getConnection();
//			this.init(c);			
//			
//			PreparedStatement ps_records = c.prepareStatement("select * from empc_expt_records r " +
//					"where r.empc_file_id = ? and state_id != ? order by iss_mfo");
//			
//			ps_records.setLong(1, file_id);
//			ps_records.setLong(2, 2);
//			ResultSet rs = ps_records.executeQuery();				
//			
//			while(rs.next())
//			{
//				EXPT_record record = new EXPT_record(
//						rs.getLong("id"),
//						rs.getLong("empc_file_id"),
//						rs.getInt("state_id"),
//						rs.getString("record_type"),
//						rs.getLong("line_number"),
//						rs.getString("client"),
//						rs.getString("card_acct"),
//						rs.getString("accnt_ccy"),
//						rs.getString("card"),
//						rs.getString("slip_nr"),
//						rs.getString("ref_number"),
//						rs.getDate("tran_date_time"),
//						rs.getDate("rec_date"),
//						rs.getDate("post_date"),
//						rs.getString("deal_desc"),
//						rs.getString("tran_type"),
//						rs.getString("deb_cred"),
//						rs.getString("tran_ccy"),
//						rs.getLong("tran_amt"),
//						rs.getLong("accnt_amt"),
//						rs.getString("terminal"),
//						rs.getString("mcc_code"),
//						rs.getString("merchant"),
//						rs.getString("abvr_name"),
//						rs.getString("country"),
//						rs.getString("city"),
//						rs.getLong("proc_id"),
//						rs.getLong("internal_no"),
//						rs.getLong("product"),
//						rs.getString("iss_mfo"),
//						rs.getString("term_id"),
//						rs.getString("tranz_acct"));
//				
//				this.process_record(record);
//			}			
//			if(!file_has_errors)
//			{
//				updateFileState.setLong(1, 2);
//				updateFileState.setLong(2, file_id);
//				updateFileState.executeUpdate();
//			}
//			c.commit();
//		}
//		catch(Exception e)
//		{   e.printStackTrace();
//			try {c.rollback();} catch(SQLException exeption){}
//			ISLogger.getLogger().error("Processing GLOBUZ file error: " + CheckNull.getPstr(e));			
//		}
//		finally
//		{
//			if(c != null) try {c.close();} catch (SQLException e) {}
//			
//			try {
//				this.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
	}
	
	public String getBranch(String branchRecord,String currentBranch){
		
		if (branchRecord.equals(currentBranch)){return "1";}
		else {
			String headerCurBranch=branchMap.get(currentBranch);
			String headerRecordBranch=branchMap.get(branchRecord);
			if(headerCurBranch.equals(headerRecordBranch)){return "2";}
			else {return "3";}
			
		}
		
	}
	
	public Long getOperationId (EXPT_record record){
		
				
		String termType=record.getTerm_id().substring(2,3);
		String oper=record.getTerm_id().substring(3,4);
		String cardBranch="1";
		String termBranch=getBranch(record.getMerchant().substring(0,5), record.getIss_mfo());
		String trType=record.getTran_type();
		String mcc=record.getMcc_code();
		
		
//		System.out.println("termType:"+termType);
//		System.out.println("oper:"+oper);
//		System.out.println("cardBranch:"+cardBranch);
//		System.out.println("termBranch:"+termBranch);
//		System.out.println("trType:"+trType);
//		System.out.println("mcc:"+mcc);
		
		Long operId=0L;
		for (Map.Entry entry : transactions_config.entrySet()) {
		    //System.out.println("Key: " + entry.getKey());
			
			
		   
		    HashMap<String, String> value = (HashMap<String, String>)entry.getValue();
		   
		   
		    
		    	
		    	if((termType.equals(value.get("termType"))) 
		    			&& (oper.equals(value.get("oper"))) 
		    			&& (cardBranch.equals(value.get("cardBranch")))
		    			&& (termBranch.equals(value.get("termBranch")))
		    			&& (trType.equals(value.get("trType")))
		    			&& (mcc.equals(value.get("mcc"))))
		    			
		    			{
		    		   
		    		  	operId= Long.valueOf(entry.getKey().toString());
		    		
		    		     break;
		    			}
		    	
		    	
		}
		return operId;
	}
	
	
	public void process_record(EXPT_record record) throws SQLException
	{
//		try
//		{
//			
//	
//			this.cMFO = ConnectionPool.getConnection();
//			this.initMFO(cMFO);	
//			ts.init(cMFO);
//			if(!this.branch.equals(record.getIss_mfo()))
//				
//			{
//				
//				this.branch=record.getIss_mfo();
//				alter_session_init(cMFO, record.getIss_mfo());
//				this.initMFO(cMFO);	
//				ts.init(cMFO);
//			}
//			
//			CallableStatement cs_prep = accounting_transaction.Service.init_get_deal_general(cMFO);
//				
//			Long operation_id=getOperationId(record);
//			
//			
//			System.out.println("operation_id:"+operation_id);
//				
//			
//			if(operation_id == 0) 
//				
//			{
//				ISLogger.getLogger().error("Не найдена операция");
//				System.out.println("Не найдена операция");
//			}
//			
//			else{
//			
//				System.err.println("record.getIss_mfo() "+record.getIss_mfo());
//				
//				Parameters ps = new Parameters();
//				ps.put("branch", record.getIss_mfo());
//				ps.put("operation_id", operation_id);
//				ps.put("ACC_CARD", record.getTranz_acct());
//				
//				terminal.setString(1, record.getTerm_id());
//				
//				System.out.println("record.getTerm_id()="+record.getTerm_id());  
//				
//				System.out.println("");
//				ResultSet rsTerm = terminal.executeQuery();
//				
//				
//				if(rsTerm.next()) {
//					
//					ps.put("ACC_TERM", rsTerm.getString("ACC"));
//					
//					System.out.println("ACC_TERM="+rsTerm.getString("ACC"));
//					
//					
//				}
//				
//				String merchantId = "";
//				merchantId += record.getMerchant();
//				merchantId = merchantId.substring(1);
//				merchantId = "J" + merchantId;
//				
//				System.out.println("merchantId="+merchantId);
//				
//				merchant.setString(1, merchantId);
//				ResultSet rsMerch = merchant.executeQuery();
//				
//				if(rsMerch.next()) {
//					ps.put("ACC_MERCH", rsMerch.getString("ACC"));
//					System.out.println("ACC_MERCH="+rsMerch.getString("ACC"));
//				}
//				
//				ps.put("parent_group_id", 198l);
//				ps.put("parent_deal_id", 6);
//				ps.put("SUMMA", record.getTran_amt());
//				ps.put("PARENT_ID", record.getId());
//				ps.put("cs_prep", cs_prep);
//				
//			
//				
//				long tr_id = 0;
//				tr_id = ts.execute_operation(
//						operation_id,
//						ps, 
//						cMFO);
//					
//				accounting_transaction.Service.action_general_doc(tr_id,1,cMFO);
//			
//			updateState.setInt(1, 2);
//			updateState.setLong(2, record.getId());
//			updateState.executeUpdate();
//			
//			deleteError.setLong(1, record.getId());
//			deleteError.executeUpdate();
//			
//			}
//		
//
//		}
//		catch(Exception e)
//		{
//			
//			cMFO.rollback();
//			e.printStackTrace();
//			logger.error(CheckNull.getPstr(e));
//			updateState.setInt(1, 3);
//			updateState.setLong(2, record.getId());
//			updateState.executeQuery();
//			
//			file_has_errors = true;
//
//			updateFileState.setLong(1, 3);
//			updateFileState.setLong(2, record.getEmpc_file_id());
//			updateFileState.executeUpdate();
//			
//			deleteError.setLong(1, record.getId());
//			deleteError.executeUpdate();
//			
//			insertError.setLong(1, record.getId());
//			insertError.setString(2, e.getMessage());
//			if(insertError.executeUpdate() == 0) {
//				System.out.println(("Error text is not inserted."));
//			}
//		}
//		finally
//		{
//			cMFO.commit();
//		}
		
		
		
	///////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		
	}
	
	public void close() throws SQLException
	{
		if(terminal != null) terminal.close();
		if(merchant != null) merchant.close();
		if(updateState != null) updateState.close();
		if(updateFileState != null) updateFileState.close();
		if(insertError != null) insertError.close();
	}

	
	public void load_branch(Connection c) throws SQLException{

		try
		{
			System.out.println("load_branch");
			this.init(c);
		
		if(branchMap==null){
		
		
		
		 branchMap=new HashMap<String, String>();
 		
//					 
 			
 			psBranch = c.prepareStatement("select bank_id,header_id from s_mfo order by bank_id");
 			rsBranch = psBranch.executeQuery();
 			
 			while(rsBranch.next())
				 {
 				branchMap.put(rsBranch.getString("bank_id"), rsBranch.getString("header_id"));
				 }
 			
 			if(psBranch != null) psBranch.close();
			if(rsBranch != null) rsBranch.close();
			 ConnectionPool.close(c);
 			
 		}	
		}
 		
 		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	public  void load_config_map() throws SQLException
	{
			
		
		
		try{
		
			
			if(transactions_config == null) 
			{
				this.c = ConnectionPool.getConnection();
				this.cMFO = ConnectionPool.getConnection();
		
			transactions_config = new HashMap<String, HashMap<String,String>>();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM HUMO_OPER_TYPE");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if (!transactions_config.containsKey(rs.getString("id")))
				transactions_config.put(rs.getString("id"), new HashMap<String, String>());
			
				transactions_config.get(rs.getString("id")).put("termType", rs.getString("TERM_TYPE"));
				transactions_config.get(rs.getString("id")).put("oper", rs.getString("OPER"));
				
				
				transactions_config.get(rs.getString("id")).put("cardBranch", rs.getString("CARD_BRANCH"));
				transactions_config.get(rs.getString("id")).put("termBranch", rs.getString("TERM_BRANCH"));
				transactions_config.get(rs.getString("id")).put("trType", rs.getString("TR_TYPE_EXPT"));
				transactions_config.get(rs.getString("id")).put("mcc", rs.getString("MCC"));
				
	
				
			}
//			
//			for (Map.Entry entry : transactions_config.entrySet()) {
//			    System.out.println("Key: " + entry.getKey());
//			   
//			    HashMap<String, String> value = (HashMap<String, String>)entry.getValue();
//			    for (Map.Entry entryValue : value.entrySet()) {
//			    	
//			    	System.out.println(entryValue.getKey() + ":  " + entryValue.getValue());
//			    }
//			    
//			    
//			    System.out.println();
//			}
//			
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();

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
    				System.out.println("Update 531");
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