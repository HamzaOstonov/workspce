package com.is.file_reciever_srv.simple.reciever_class;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.is.ISLogger;


public abstract class Abstract_handler
{
	protected static Logger logger = ISLogger.getLogger();
	public abstract void run_handler() throws Exception;
}
