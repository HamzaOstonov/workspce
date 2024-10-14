package com.is.file_reciever_srv.recievers.NIKI;

import com.is.ConnectionPool;
import com.is.file_reciever_srv.simple.reciever_class.Sender_class;

public class NIKI_Sender extends Sender_class
{
	@Override
	public void run()
	{
		if (ConnectionPool.getValue("NIKI_lock_manual_send").equals("1")) Service.create_files();
	}
}
