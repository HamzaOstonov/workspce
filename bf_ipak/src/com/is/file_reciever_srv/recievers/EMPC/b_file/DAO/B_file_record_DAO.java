package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_record_DAO;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_header_record;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_record;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_transaction_record;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_trayler_record;

public class B_file_record_DAO extends EMPC_record_DAO
{
	public static void read(B_file_record rec, String line)
	{
		rec.setType(line.substring(0, 2));
		rec.setContent(line);
	}
	
	public static void insert(
			B_file_record rec, 
			Connection c, 
			HashMap<String, PreparedStatement> prepared_statements,
			long line_nmb
			) throws SQLException
	{
		if(rec.getType().equals("00")) // if header
		{
			if(!prepared_statements.containsKey("ps_insert_header_record"))
				prepared_statements.put("ps_insert_header_record",  
						B_file_header_record_DAO.get_ps_insert_header_record(c));
			
			B_file_header_record hrec = new B_file_header_record(rec.getEMPC_file_id());
			hrec = B_file_header_record_DAO.read(hrec, rec.getContent());
			B_file_header_record_DAO.insert(
					hrec,
					prepared_statements.get("ps_insert_header_record"),
					line_nmb
					);
		}
		else if(rec.getType().equals("10")) // if transaction
		{
			if(!prepared_statements.containsKey("ps_insert_transaction_record"))
				prepared_statements.put("ps_insert_transaction_record",  
						B_file_transaction_DAO.get_ps_insert_transaction_record(c));
			
			B_file_transaction_record hrec = new B_file_transaction_record(rec.getEMPC_file_id());
			hrec = B_file_transaction_DAO.read(hrec, rec.getContent());
			B_file_transaction_DAO.insert(
					hrec,
					prepared_statements.get("ps_insert_transaction_record"),
					line_nmb
					);
		}
		else if(rec.getType().equals("99")) // if trayler
		{
			if(!prepared_statements.containsKey("ps_insert_trayler_record"))
				prepared_statements.put("ps_insert_trayler_record",  
						B_file_trayler_record_DAO.get_ps_insert_trayler_record(c));
			
			B_file_trayler_record hrec = new B_file_trayler_record(rec.getEMPC_file_id());
			hrec = B_file_trayler_record_DAO.read(hrec, rec.getContent());
			B_file_trayler_record_DAO.insert(
					hrec,
					prepared_statements.get("ps_insert_trayler_record"),
					line_nmb
					);
		}
	}
}
