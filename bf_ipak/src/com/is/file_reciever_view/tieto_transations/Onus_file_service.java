package com.is.file_reciever_view.tieto_transations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import accounting_transactions_new.Account_does_not_exist_exception;
import accounting_transactions_new.TransactionService;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.ext_utils.Service;
import com.is.file_reciever_srv.recievers.tieto_file.Dynamic_parameter;
import com.is.file_reciever_srv.recievers.tieto_file.Record;
import com.is.file_reciever_srv.recievers.tieto_file.Ti_file_rec_type_operation;
import com.is.file_reciever_srv.recievers.tieto_file.field_modifiers.Abstract_modifier;
import com.is.kernel.parameter.Parameters;
import com.is.tieto.files.Ti_file_onus_tr_accmapping;

public class Onus_file_service
{
	private String current_branch = "";
	private static Logger logger = ISLogger.getLogger();
	
	public void run_handler() throws Exception
	{
		List<Long> files_in_id = 
			com.is.file_reciever_srv.recievers.tieto_file.Service.get_reciered_files_to_process(
					18l, 1l);
		
		for (int i = 0; i < files_in_id.size(); i++)
		{
			Connection c = null;
			Statement st = null;
			CallableStatement cs = null;
			PreparedStatement ps_update = null;
			try
			{
				c = ConnectionPool.getConnection();
				
				st = c.createStatement();
		        st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
		        st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+ConnectionPool.getValue("HO_SCHEMA"));
		        cs = c.prepareCall("{ call info.init() }");
		        cs.execute();
				
				List<Record> records = 
					com.is.file_reciever_srv.recievers.tieto_file.Service.get_records_from_file(
							files_in_id.get(i), c);
				
				
				TransactionService ts = new TransactionService();
				ts.init(c);
				current_branch = ConnectionPool.getValue("HO");
				CallableStatement cs_prep = accounting_transactions_new.Service.init_get_deal_general(c);
				
				List<Ti_file_onus_tr_accmapping> mappings = 
					com.is.tieto.files.Service.get_Ti_file_onus_tr_accmappings();
				HashMap<Onus_file_bin_mapping_pk, Ti_file_onus_tr_accmapping> mappings_bin_map = 
					new HashMap<Onus_file_bin_mapping_pk, Ti_file_onus_tr_accmapping>();
				HashMap<Onus_mapping_pk, Ti_file_onus_tr_accmapping> mappings_map = 
					new HashMap<Onus_mapping_pk, Ti_file_onus_tr_accmapping>();
				for(Ti_file_onus_tr_accmapping mapping : mappings)
				{
					mappings_map.put(new Onus_mapping_pk(mapping), mapping);
					mappings_bin_map.put(new Onus_file_bin_mapping_pk(mapping), mapping);
				}
					
				
				for(int j = 0; j < records.size(); j++)
				{
					Record cur_rec = records.get(j);
					
					HashMap<String, String> string_params = 
						com.is.file_reciever_srv.ext_utils.Service.get_data_from_fields(c, cur_rec.getId());
					
					String[] splitted_string = cur_rec.getRec_value().split(";");
					
					Ti_file_onus_tr_accmapping mapping = null;
					
					if(mappings_bin_map.containsKey(
							new Onus_file_bin_mapping_pk(splitted_string[0], splitted_string[6])))
						mapping = mappings_bin_map.get(
								new Onus_file_bin_mapping_pk(splitted_string[0], splitted_string[6]));
					else
						mapping = mappings_map.get(
								new Onus_mapping_pk(splitted_string[1], splitted_string[6]));
					
					current_branch = mapping.getOperation_branch();
					Parameters ps = new Parameters();
					ps.put("branch", current_branch);
					
					long operation_id = 0l;
					
					if(splitted_string[2].equals("40230600") &&
							(mapping.getOperation_id()!=null &&
									mapping.getOperation_id()!=0l)&&
									splitted_string[3].startsWith("22618840"))
						operation_id = mapping.getOperation_id();
					
					else if(splitted_string[2].equals("40230600") &&
							(mapping.getOperation_corporativ()!=null &&
									mapping.getOperation_corporativ()!=0l)&&
									splitted_string[3].startsWith("22620840"))
						operation_id = mapping.getOperation_corporativ();
					
					else if(splitted_string[2].equals("62104501") &&
							(mapping.getCup_operation_id()!=null &&
									mapping.getCup_operation_id()!=0l)&&
									splitted_string[3].startsWith("22618840"))
						operation_id = mapping.getCup_operation_id();
						
					else 
					{
						if((mapping.getOperation_id()!=null && mapping.getOperation_id()!=0l))
							operation_id = mapping.getOperation_id();
						else if((mapping.getCup_operation_id()!=null && mapping.getCup_operation_id()!=0l))
							operation_id = mapping.getCup_operation_id();
						else if((mapping.getOperation_corporativ()!=null && mapping.getOperation_corporativ()!=0l))
							operation_id = mapping.getOperation_corporativ();
					}
							
								
					ps.put("operation_id", operation_id);
					ps.put("parent_group_id", 183l);
					ps.put("PARENT_ID", cur_rec.getId());
					ps.put("cs_prep", cs_prep);
					ps.put("account_10107", mapping.getAccount_10107());
					ps.put("account_17401", mapping.getAccount_23510());
					ps.put("acc_23508_510", mapping.getAcc_23508_510());
					ps.put("CARD_ACC", splitted_string[3]);
					ps.put("summa", Math.round((Double.parseDouble(splitted_string[4])*100)));
					ps.put("amount_comission", Math.round((Double.parseDouble(splitted_string[5])*100)));
					ps.put("purpose", mapping.getName());
					
					
					long tr_id = 0;
					long object_id = com.is.file_reciever_srv.ext_utils.Service.get_created_object_id(c);
					try
					{
						tr_id = ts.execute_operation(
								operation_id,
								ps, 
								c);
						//tr_id = ts.execute_operation(1000l, ps, c);
						
						ps_update = c.prepareStatement("insert into ext_created_objects (id, in_record_id, branch, deal_group_id, obj_id, v_date) " +
								"values (" +
								"?, ?, ?, 185, ?, systimestamp)");
						
						ps_update.setLong(1, object_id);
						ps_update.setLong(2, cur_rec.getId());//in_record_id,
						ps_update.setString(3, current_branch);//branch,
						ps_update.setLong(4, tr_id);//obj_id,
						ps_update.execute();
					}
					catch(Account_does_not_exist_exception a_exc)
					{
						com.is.file_reciever_srv.common.Service.put_protocol(
								c, 
								object_id, "—чет не найден: "+a_exc.getMessage(),
								32);
						System.out.println("—чет не найден: "+a_exc.getMessage());
						//logger.error(Util.getPstr(a_exc));
						continue;
					}
					
					System.out.println("tr_id for record id = "+cur_rec.getId()+" = "+ tr_id);
				}
				
				ps_update.close();
				ps_update = null;
				
				ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 2 where t.id = ?");
				ps_update.setLong(1, files_in_id.get(i));
				ps_update.execute();
				
				c.commit();
				accounting_transactions_new.Service.close_get_deal_general(cs_prep);
				logger.info("Tieto file handler: end processing transactions from file");
			}
			catch(Exception e)
			{
				try{c.rollback();}catch(Exception e1){}
				System.out.println(com.is.utils.CheckNull.getPstr(e));
				throw e;
			}
			finally
			{
				try{if(ps_update!=null)ps_update.close();}catch(Exception e){}
				try{if(cs!=null)cs.close();}catch(Exception e){}
				try{if(st!=null)st.close();}catch(Exception e){}
				try{if(c!=null)c.close();}catch(Exception e){}
			}
		}
	}
}
