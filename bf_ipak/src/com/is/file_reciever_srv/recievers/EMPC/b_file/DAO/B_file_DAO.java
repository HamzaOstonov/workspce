package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_file_DAO;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_record;

public class B_file_DAO extends EMPC_file_DAO
{
	public static void load_records(String file_name, B_file b_file, Connection c) throws IOException, SQLException
	{
		File fl = new File(file_name);
		FileInputStream fis = new FileInputStream(fl);
		BufferedReader file_reader = new BufferedReader(new InputStreamReader(fis));
		
		insert(b_file, c);
		
		String line = file_reader.readLine();
		
		HashMap<String , PreparedStatement> prepared_statements = 
			new HashMap<String, PreparedStatement>();
		try
		{
			long line_nmb = 1;
			while(line != null)
			{
				B_file_record rec = new B_file_record(b_file.getId());
				B_file_record_DAO.read(rec, line);
				B_file_record_DAO.insert(rec, c, prepared_statements, line_nmb);
				line = file_reader.readLine();
				line_nmb++;
			}
		//	for(String str : prepared_statements.keySet())
		//	{
		//		ISLogger.getLogger().info(">>" + str);
		//		if(!str.equals("ps_insert_transaction_record"))
		//		prepared_statements.get(str).executeBatch();
		//	}
			for(PreparedStatement ps : prepared_statements.values())
				ps.executeBatch();
		}
		finally
		{
			for(PreparedStatement ps : prepared_statements.values())
				if(ps!=null)ps.close();
		}
	}
	
	public static void insert(B_file b_file, Connection c) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement(
				"insert into empc_files(id, fr_file_id, file_type_id) " +
				"values (?, ?, ?)");
		try
		{
			ps.setLong(1, b_file.getId());
			ps.setLong(2, b_file.getFr_file_id());
			ps.setInt(3, 1);
			ps.execute();
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
}
