package com.is.tieto_globuz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
	
	public static String getExternalSession() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	    return dateFormat.format(new Date());	
	}
	
	
}
