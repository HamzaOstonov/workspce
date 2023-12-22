package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_header_record;

public class B_file_header_record_DAO
{
	public static B_file_header_record read(B_file_header_record rec, String line) throws SQLException
	{
		return new B_file_header_record(
				rec.getEMPC_file_id(),
				0l,
				0, 
				line.length() >= 4 ? line.substring(0, 2).trim() : null,
				line.length() >= 4 ? line.substring(2, 4).trim() : null,
				line.length() >= 6 ? line.substring(4, 6).trim() : null,
				line.length() >= 14 ? line.substring(6, 14).trim() : null,
				line.length() >= 22 ? line.substring(14, 22).trim() : null,
				line.length() >= 23 ? line.substring(22).trim() : null
				);
	}
	
	public static void insert(B_file_header_record rec, PreparedStatement ps_insert_header_record, long rec_num) throws SQLException
	{
		ps_insert_header_record.setLong(1, rec.getEMPC_file_id());
		ps_insert_header_record.setString(2, rec.getMtid());
		ps_insert_header_record.setString(3, rec.getRec_centr());
		ps_insert_header_record.setString(4, rec.getSend_centr());
		ps_insert_header_record.setString(5, rec.getFile());
		ps_insert_header_record.setString(6, rec.getCard_id());
		ps_insert_header_record.setString(7, rec.getVersion());
		ps_insert_header_record.setLong(8, rec_num);
		ps_insert_header_record.addBatch();
	}
	
	public static PreparedStatement get_ps_insert_header_record(Connection c) throws SQLException
	{
		return c.prepareStatement(
				"insert into empc_files_b_headers" +
				"(id, " +
				"empc_file_id, " +
				"mtid, " +
				"Rec_centr, " +
				"Send_centr, " +
				"E_File, " +
				"Card_id, " +
				"E_Version, rec_num) values (seq_empc_files_b_headers.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
	}
}
