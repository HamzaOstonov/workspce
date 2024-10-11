package com.is.SwiftBuffer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.SwiftBuffer.parser.SwiftMessageHelper;
import com.is.SwiftBuffer.parser.SwiftMessageWrapper;
import com.is.utils.CheckNull;

public class FileReader {
	
	private static Logger logger = ISLogger.getLogger();
	private static SimpleDateFormat sdf_dir = new SimpleDateFormat("yyyy\\MM\\dd\\");

	
	public static void Recieve_file(String input_file, long fr_file_id)
	{
		System.out.println("Recieves file: " + input_file);
		
		List<SwiftMessageWrapper> wrappers = SwiftMessageHelper.GetMsgFromFile(input_file);
		System.out.println("GetMsgFromFile: " + wrappers.size());
		
		Connection c = null;
		try
		{
			for(SwiftMessageWrapper wrapper : wrappers)
			{
				if(wrapper.getMessageType()==null) wrapper.setMessageType("999");
				if(!wrapper.getMessageType().equals("103")) continue;
				
				logger.info("SWIFT file reading file "+input_file+"...");
				c = ConnectionPool.getConnection();
				//String HO = ConnectionPool.getValue("swift_ho");
				String HO = ConnectionPool.getValue("SWIFT_BRANCH");
				PreparedStatement ps_insert_into_buffer = 
					c.prepareStatement(
							"insert into swift_buffer " +
							"(branch, id, message_type, application_id, service_id, " +
							"bic_from, bic_to, direction, country_from, country_to, " +
							"value_date, amount, currency, reference, operation_code, " +
							"order_party, ben_party, narrative, detailsofcharges, message_text, " +
							"insert_date, state, deal_id, parent_group_id, parent_id, " +
							"batch_id, file_name, order_party_acc, ben_party_acc, corr_acc) " +
							"values (" +
							"?, ?, ?, ?, ?, " +
							"?, ?, ?, ?, ?, " +
							"?, ?, ?, ?, ?, " +
							"?, ?, ?, ?, ?, " +
							"?, ?, ?, ?, ?, " +
							"?, ?, ?, ?, ? )");
				
				PreparedStatement ps_get_id = c.prepareStatement(
						"select seq_swift_buffer_id.nextval res from dual"
						);
				
				long id = get_swift_buffer_id(ps_get_id);
				
				read_wrapper(
						wrapper, 
						c, 
						id, 
						input_file.substring(input_file.lastIndexOf('/')+input_file.lastIndexOf('\\')+1), 
						ps_insert_into_buffer,
						HO);
				
				File file_to_move = new File(input_file);
				if(file_to_move.exists())
				{
					String SWIFT_arch_dir = ConnectionPool.getValue("SWIFT_arch_dir");
					if(! new File(SWIFT_arch_dir+sdf_dir.format(new java.util.Date())).exists())
						new File(SWIFT_arch_dir+sdf_dir.format(new java.util.Date())).mkdirs();
					file_to_move.renameTo(new File(SWIFT_arch_dir+sdf_dir.format(new java.util.Date())+file_to_move.getName()));
				}
				
				c.commit();
				logger.info("SWIFT file reading file "+input_file+" complete.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(c!=null)try{c.rollback();}catch(Exception e1){logger.error(CheckNull.getPstr(e1));}
			logger.error(CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null)try{c.close();}catch(Exception e){logger.error(CheckNull.getPstr(e));}
		}
	}
	
	private static void read_wrapper(
			SwiftMessageWrapper wrapper, 
			Connection c, 
			long id, 
			String file_name, 
			PreparedStatement ps_insert_into_buffer, String HO) throws SQLException
	{
		ps_insert_into_buffer.setString(1, HO);
		ps_insert_into_buffer.setLong(2, id);
		ps_insert_into_buffer.setString(3, wrapper.getMessageType());
		ps_insert_into_buffer.setString(4, wrapper.getApplicationId());
		ps_insert_into_buffer.setString(5, wrapper.getServiceId());
		ps_insert_into_buffer.setString(6, wrapper.getBicFrom());
		ps_insert_into_buffer.setString(7, wrapper.getBicTo());
		ps_insert_into_buffer.setString(8, wrapper.getDirection());
		ps_insert_into_buffer.setString(9, wrapper.getCountryFrom());
		ps_insert_into_buffer.setString(10, wrapper.getCountryTo());
		ps_insert_into_buffer.setDate(11, new java.sql.Date(wrapper.getValueDate().getTime().getTime()));
		ps_insert_into_buffer.setDouble(12, wrapper.getAmount());
		ps_insert_into_buffer.setString(13, wrapper.getCurrency());
		ps_insert_into_buffer.setString(14, wrapper.getReference());
		ps_insert_into_buffer.setString(15, wrapper.getOperationCode());
		ps_insert_into_buffer.setString(16, wrapper.getOrderParty());
		ps_insert_into_buffer.setString(17, wrapper.getBenParty());
		ps_insert_into_buffer.setString(18, wrapper.getNarrative());
		ps_insert_into_buffer.setString(19, wrapper.getDetailsOfCharges());
		ps_insert_into_buffer.setString(20, wrapper.getMessageText());
		ps_insert_into_buffer.setDate(21, new java.sql.Date((new java.util.Date()).getTime()));
		ps_insert_into_buffer.setInt(22, 0);
		ps_insert_into_buffer.setInt(23, 2);
		ps_insert_into_buffer.setInt(24, 191);
		ps_insert_into_buffer.setLong(25, id);
		ps_insert_into_buffer.setNull(26, java.sql.Types.NUMERIC);
		ps_insert_into_buffer.setString(27, file_name);
		if(wrapper.getOrderParty().equals(wrapper.getOrder_party_acc()))
			ps_insert_into_buffer.setNull(28, java.sql.Types.VARCHAR);
		else
			ps_insert_into_buffer.setString(28, wrapper.getOrder_party_acc());
		ps_insert_into_buffer.setString(29, wrapper.getBen_party_acc());
		ps_insert_into_buffer.setNull(30, java.sql.Types.VARCHAR);
		ps_insert_into_buffer.execute();
	}
	
	private static long get_swift_buffer_id(PreparedStatement ps_get_id) throws Exception
	{
		long res = 0;
		ResultSet rs = null;
		try
		{
			rs = ps_get_id.executeQuery();
			rs.next();
			res = rs.getLong("res");
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			if(rs!=null)rs.close();
		}
	}

}
