package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.sql.Connection;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EMPC_file_reciever;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.utils.CheckNull;

public class EXPT_reciever extends Reciever_class
{

	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			EXPT_service serv = new EXPT_service(c, fr_file_id, input_file);
			serv.process_file();
			EMPC_file_reciever.move_file_to_archive(input_file);
			c.commit();
		//	c.rollback();
		}
		catch(Exception e)
		{
			if(c!=null) try{c.rollback();} catch(Exception e1){}
			ISLogger.getLogger().error("recieving EMPC file "+input_file+" error "+CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null) try{c.close();} catch(Exception e1){}
		}
	}

}
