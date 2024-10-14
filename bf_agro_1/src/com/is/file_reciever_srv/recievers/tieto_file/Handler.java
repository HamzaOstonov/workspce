package com.is.file_reciever_srv.recievers.tieto_file;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.tieto_file.field_modifiers.Abstract_modifier;
import com.is.file_reciever_srv.simple.reciever_class.Abstract_handler;
import com.is.kernel.parameter.Parameters;
import com.is.utils.CheckNull;

import accounting_transaction_01.Account_does_not_exist_exception;
import accounting_transaction_01.Bf_tr_acc;
import accounting_transaction_01.TransactionService;



public class Handler// extends Abstract_handler
{
	private static Logger logger = ISLogger.getLogger();
	//@Override
	public void run_handler(long file_type) throws Exception
	{
		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();
		
		boolean isFileOk;

		List<Long> files_in_id = Service.get_reciered_files_to_process(file_type, 1l);
		
		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(files_in_id);
		} catch (Exception e22) {
			str1 = " files_in_id=error";
		} finally {
		}
		logger.error("not err files_in_id = " + str1);
		
		//пробежим по файлам
		for (int i = 0; i < files_in_id.size(); i++)
		{
			Connection c = null;
			Statement st = null;
			CallableStatement cs = null;
			PreparedStatement ps_update = null;
			PreparedStatement ps_insert = null;
			isFileOk=true;
			try
			{
				c = ConnectionPool.getConnection();
				
				st = c.createStatement();
		        st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
		        st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+ConnectionPool.getValue("HO_SCHEMA"));
		        cs = c.prepareCall("{ call info.init() }");
		        cs.execute();
				
				List<Record> records = Service.get_records_from_file(files_in_id.get(i), c);
		
				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(records);
				} catch (Exception e22) {
					str1 = " records=error";
				} finally {
				}
				logger.error("not err records = " + str1+", i="+i);
				
				HashMap<Long, Ti_file_rec_type_operation> file_rec_type_operations = 
					Service.get_file_rec_type_operations();
				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(file_rec_type_operations);
				} catch (Exception e22) {
					str1 = " file_rec_type_operations=error";
				} finally {
				}
				logger.error("not err file_rec_type_operations = " + str1+", i="+i);
				
				
				if(records.size() == 0) throw new Exception("ѕустой файл");
				
				TransactionService ts = new TransactionService();
				ts.init(c);
				//String current_branch = ConnectionPool.getValue("HO");
				String current_branch = "00394";
				CallableStatement cs_prep = accounting_transaction.Service.init_get_deal_general(c);
				//пробежим по строкам файла
				for(int j = 0; j < records.size(); j++)
				{
					Record cur_rec = records.get(j);
					
					try {
						str1 = objectMapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(cur_rec);
					} catch (Exception e22) {
						str1 = " cur_rec=error";
					} finally {
					}
					logger.error("not err cur_rec = " + str1+", i="+i+", j="+j);
					HashMap<String, String> string_params = 
						com.is.file_reciever_srv.ext_utils.Service.get_data_from_fields(c, cur_rec.getId());
					logger.error("not err string_params = " + string_params+", i=" +i+ ", j=" + j +", :  cur_rec.getId()=" +  cur_rec.getId());
					
					///*************
					Ti_file_rec_type_operation current_operation = 
						file_rec_type_operations.get(cur_rec.getRecord_type_id());
					
					Parameters ps = new Parameters();
					//ps.put("branch", current_branch);
					if(current_operation == null)
					{
						System.out.println("null .........");
					}
					ps.put("operation_id", current_operation.getBf_tr_operation_id());
					ps.put("parent_group_id", 183l);
					ps.put("PARENT_ID", cur_rec.getId());
					ps.put("cs_prep", cs_prep);
					
					List<Dynamic_parameter> dynamic_params = 
						Service.get_mapping_parameters(current_operation.getId(), c);
					
					
					for (Dynamic_parameter current_param : dynamic_params)
					{
						try {
							str1 = objectMapper.writerWithDefaultPrettyPrinter()
									.writeValueAsString(current_param);
						} catch (Exception e22) {
							str1 = " current_param=error";
						} finally {
						}
						logger.error("not err current_param = " + str1+", i="+i+", j="+j);
						
						if (current_param.getModifier_class() != null)
						{
							Abstract_modifier field_modifier = (Abstract_modifier)(Class.
								forName(current_param.getModifier_class()).newInstance());
							
							ps.put(current_param.getPut_param_name(), 
									field_modifier.modify(
											string_params, 
											current_param.getPut_param_name(),
											current_param.getGet_param_name()
											));
						}
						else
							ps.put(current_param.getPut_param_name(), 
								string_params.get(current_param.getGet_param_name()));
					}
					//hamza 2019-01-09
			        if ( ps.contains("A_BANK") && ps.get("A_BANK")!=null && ps.get("A_BANK")!="")
			        {
			        	ps.put("branch", ps.get("A_BANK"));
			        	logger.error("not err 5");
			        }
			        else 
			        {
			        	ps.put("branch", current_branch);
			        	logger.error("not err 6");
			        }
			        //( ps.contains("A_BANK_CARD") && ps.get("A_BANK_CARD")!=null && ps.get("A_BANK_CARD")!="")
			        
			        //pd.purpose := replace(upper(pd.purpose),
                    //          '<@CLFIO@>',
                    //          recpay.cl_name);
			        //if ( ps.contains("CARD_ACC") && ps.get("CARD_ACC")!=null && ps.get("CARD_ACC")!="")
			        //ps.put("CLFIO", "");
			        
					long tr_id = 0;
					long object_id = com.is.file_reciever_srv.ext_utils.Service.get_created_object_id(c);
					
					if(cur_rec == null)throw new Exception("Record is null");
					
					System.out.println(" record id = "+cur_rec.getId());
					
					try
					{
						tr_id = ts.execute_operation(
								file_rec_type_operations.get(cur_rec.getRecord_type_id()).getBf_tr_operation_id(),
								ps, 
								c);
						
						ps_insert = c.prepareStatement("insert into ext_created_objects (id, in_record_id, branch, deal_group_id, obj_id, v_date) " +
								"values (" +
								"?, ?, ?, 185, ?, systimestamp)");
						
						ps_insert.setLong(1, object_id);
						ps_insert.setLong(2, cur_rec.getId());//in_record_id,
						ps_insert.setString(3, current_branch);//branch,
						ps_insert.setLong(4, tr_id);//obj_id,
						ps_insert.execute();
					}
					catch(Account_does_not_exist_exception a_exc)
					{
						com.is.file_reciever_srv.common.Service.put_protocol(
								c, 
								object_id, "—чет не найден: "+a_exc.getMessage(),
								32);
						System.out.println("—чет не найден: "+a_exc.getMessage());
						logger.error("—чет не найден: "+a_exc.getMessage());
						//logger.error(Util.getPstr(a_exc));
						isFileOk=false;
						try{if(ps_insert!=null)ps_insert.close();}catch(Exception eii){}
						ps_insert = c.prepareStatement("update ext_in_file_records t set t.err_message = substr(?, 1, 254) where t.id = ?");
						ps_insert.setString(1, "—чет не найден: "+a_exc.getMessage());
						ps_insert.setLong(2, cur_rec.getId());
						ps_insert.execute();
						continue;
					}
					
					System.out.println("tr_id for record id = "+cur_rec.getId()+" = "+ tr_id);
				}  // конец строки одного файла
				
				try{if(ps_insert!=null)ps_insert.close();}catch(Exception eii){}
				if (isFileOk) {
				ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 2 where t.id = ?");
				ps_update.setLong(1, files_in_id.get(i));
				ps_update.execute();
				}
				c.commit();
				accounting_transaction.Service.close_get_deal_general(cs_prep);
				logger.info("Tieto file handler: end processing transactions from file");
				
				
			}
			catch(Exception e)
			{
				try{c.rollback();}catch(Exception e1){}
				System.out.println(com.is.utils.CheckNull.getPstr(e));
				logger.error(com.is.utils.CheckNull.getPstr(e));
				throw e;
			}
			finally
			{
				try{if(ps_update!=null)ps_update.close();}catch(Exception e){}
				try{if(cs!=null)cs.close();}catch(Exception e){}
				try{if(st!=null)st.close();}catch(Exception e){}
				try{if(c!=null)c.close();}catch(Exception e){}
			}
		}// конец одного файла по циклу
	}

}
