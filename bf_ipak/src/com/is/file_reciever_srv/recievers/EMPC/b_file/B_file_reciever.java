package com.is.file_reciever_srv.recievers.EMPC.b_file;

import java.sql.Connection;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EMPC_file_reciever;
import com.is.file_reciever_srv.recievers.EMPC.DAO.EMPC_file_DAO;
import com.is.file_reciever_srv.recievers.EMPC.b_file.DAO.B_file_DAO;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.utils.CheckNull;

public class B_file_reciever extends Reciever_class
{

	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			B_file b_file = new B_file(EMPC_file_DAO.get_file_id(), fr_file_id);
			//B_file_DAO.insert(b_file, c);
			B_file_DAO.load_records(input_file, b_file, c);
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
