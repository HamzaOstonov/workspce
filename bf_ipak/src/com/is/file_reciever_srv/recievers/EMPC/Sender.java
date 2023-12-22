package com.is.file_reciever_srv.recievers.EMPC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;
import com.is.file_reciever_srv.simple.reciever_class.Sender_class;


public class Sender extends Sender_class
{

	
	@Override
	public void run()
	{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT id FROM empc_files where state_id != ?");
    		ps.setLong(1, 2);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			Expt_records_processing recordsProcessing = new Expt_records_processing();
    			recordsProcessing.process_file(rs.getLong("id"));
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
