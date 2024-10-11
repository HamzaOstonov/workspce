package com.is;

import org.apache.log4j.Logger;

public class ISLogger {
	static Logger logger = null;
	static Logger tietoLogger = null;
	public static Logger getLogger(){
		if (logger == null){
			logger = Logger.getLogger("bfLogger");
		}
		
		return logger;
	}
	public static Logger getTLogger(){
		if (tietoLogger == null){
			tietoLogger = Logger.getLogger("tietoLogger");
		}
		
		return tietoLogger;
	}
}
