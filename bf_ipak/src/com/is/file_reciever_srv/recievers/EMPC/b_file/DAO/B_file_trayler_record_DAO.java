package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_trayler_record;

public class B_file_trayler_record_DAO
{
	public static B_file_trayler_record read(B_file_trayler_record rec, String line) throws SQLException
	{
		return new B_file_trayler_record(
				0l,
				0l,
				rec.getEmpc_file_id(),
				line.length() >= 2 ? line.substring(0, 2).trim() : null,
				line.length() >= 2 ? line.substring(2, 4).trim() : null,
				line.length() >= 2 ? line.substring(4, 6).trim() : null,
				line.length() >= 2 ? line.substring(6, 14).trim() : null,
				line.length() >= 2 ? line.substring(14, 22).trim() : null,
				line.length() >= 2 ? line.substring(22, 23).trim() : null,
				line.length() >= 2 ? line.substring(23, 37).trim() : null,
				line.length() >= 2 ? line.substring(37).trim() : null
				);
	}
	
	public static void insert(B_file_trayler_record rec, PreparedStatement ps_insert_trayler_record, long rec_num) throws SQLException
	{
		ps_insert_trayler_record.setLong(1, rec.getEmpc_file_id());
		ps_insert_trayler_record.setString(2, rec.getMtid());
		ps_insert_trayler_record.setString(3, rec.getRec_centr());
		ps_insert_trayler_record.setString(4, rec.getSend_centr());
		ps_insert_trayler_record.setString(5, rec.getFile());
		ps_insert_trayler_record.setString(6, rec.getNumber());
		ps_insert_trayler_record.setString(7, rec.getSign());
		ps_insert_trayler_record.setString(8, rec.getSum());
		ps_insert_trayler_record.setString(9, rec.getControl());
		ps_insert_trayler_record.setLong(10, rec_num);
		ps_insert_trayler_record.addBatch();
	}
	
	public static PreparedStatement get_ps_insert_trayler_record(Connection c) throws SQLException
	{
		return c.prepareStatement(
				"insert into empc_files_b_traylers (id, " +
				"empc_file_id, " +
				"Mtid, " +
				"Rec_centr, " +
				"Send_centr, " +
				"E_File, " +
				"E_number, " +
				"E_Sign, " +
				"E_Sum, " +
				"Control, " +
				"rec_num) values (seq_empc_files_b_traylers.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	}
}
