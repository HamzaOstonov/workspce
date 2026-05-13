package com.is.providers;

import org.apache.log4j.Logger;

public class PLogger {
	static Logger logger = null;
	public static Logger getLogger(){
		if (logger == null){
			logger = Logger.getLogger("munis");
		}
		
		return logger;
	}
}
