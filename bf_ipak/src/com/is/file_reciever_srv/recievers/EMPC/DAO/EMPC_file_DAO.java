package com.is.file_reciever_srv.recievers.EMPC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.ConnectionPool;
import com.is.file_reciever_srv.recievers.EMPC.entity.EMPC_file;

public class EMPC_file_DAO
{
	public static long get_file_id() throws SQLException
	{
		return Util.get_sequence_next_val("seq_empc_files");
	}
}
