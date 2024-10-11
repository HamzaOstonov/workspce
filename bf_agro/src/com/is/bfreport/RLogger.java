package com.is.bfreport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class RLogger {
	static Logger logger = null;
	public static Logger getLogger(){
		if (logger == null){
			logger = Logger.getLogger("rep");
		}
		logger.setLevel(Level.WARN);
		return logger;
	}
}
