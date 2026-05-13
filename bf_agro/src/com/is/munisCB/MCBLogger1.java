package com.is.munisCB;

import org.apache.log4j.Logger;

public class MCBLogger1 {
	static Logger logger = null;
	public static Logger getLogger(){
		if (logger == null){
			logger = Logger.getLogger("bf");
		}
		
		return logger;
	}
}
