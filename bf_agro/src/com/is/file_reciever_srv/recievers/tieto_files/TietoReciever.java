package com.is.file_reciever_srv.recievers.tieto_files;

import java.sql.Connection;

import com.is.ConnectionPool;
import com.is.ISLogger;

import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.utils.CheckNull;

public class TietoReciever extends Reciever_class
{

	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			TietoService serv = new TietoService(c, fr_file_id, input_file);
			serv.process_file();
			TietoFileReciever.move_file_to_archive(input_file);
			c.commit();
		//	c.rollback();
		}
		catch(Exception e)
		{
			if(c!=null) try{c.rollback();} catch(Exception e1){}
			ISLogger.getLogger().error("recieving Tieto file "+input_file+" error "+CheckNull.getPstr(e));
		}
		finally
		{
			if(c!=null) try{c.close();} catch(Exception e1){}
		}
	}

}
