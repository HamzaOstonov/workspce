package com.is.userreport;

import java.sql.Connection;
import java.util.Map;

import org.zkoss.util.media.AMedia;

public  abstract class UserReport {

	public UserReport() {
		super();

	}

	public  abstract AMedia getRepmd(Map<String, Object> params, String templf, String outfl);
	

}
