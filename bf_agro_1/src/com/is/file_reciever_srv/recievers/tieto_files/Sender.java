package com.is.file_reciever_srv.recievers.tieto_files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.simple.reciever_class.Sender_class;

public class Sender extends Sender_class
{
	
	@Override
	public void run()
	{	
		ISLogger.getLogger().error("Run STARTED");
				
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT id FROM tieto_files where state_id != ?");
    		ps.setLong(1, 2);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			TietoRecordsProcessing recordsProcessing = new TietoRecordsProcessing();
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
