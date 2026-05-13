package com.is;

import org.apache.log4j.Logger;

public class LtLogger {
	static Logger logger = null;
	public static Logger getLogger(){
		if (logger == null){
			logger = Logger.getLogger("bf");
		}
		
		return logger;
	}
}
