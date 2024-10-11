package com.is.file_reciever_srv.main;

import org.apache.log4j.Logger;

import com.is.ISLogger;


public class Timer_contayner
{
	public static File_timer ft;
	private static Logger logger = ISLogger.getLogger();	
	
	public static void run()
	{
		ft = new File_timer();
        logger.info("starting service...");
        ft.run();
        logger.info("service started.");
	}
	
	public static void restart()
	{
        logger.info("stopping service...");
        ft.restart();
        logger.info("starting service...");
        logger.info("service started.");
	}
}
