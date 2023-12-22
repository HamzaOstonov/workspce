package com.is.tieto_visae.tieto;

import java.util.Date;


/**
 * @author D
 * TSM_CLIENT_ID string (20) Идентификатор TSM клиента.
 * TSM_AUTH_CODE string (20) Авторизационный код TSM карточки.
 * TSM_AUTH_EXPIRY dateTime Срок годности авторизационного кода. 
 */

public class TSMData {

	
	
	private String TSM_CLIENT_ID;
	
	
	private String TSM_AUTH_CODE;
	
	
	public String getTSM_CLIENT_ID() {
		return TSM_CLIENT_ID;
	}


	public void setTSM_CLIENT_ID(String tSM_CLIENT_ID) {
		TSM_CLIENT_ID = tSM_CLIENT_ID;
	}


	public String getTSM_AUTH_CODE() {
		return TSM_AUTH_CODE;
	}


	public void setTSM_AUTH_CODE(String tSM_AUTH_CODE) {
		TSM_AUTH_CODE = tSM_AUTH_CODE;
	}


	public Date getTSM_AUTH_EXPIRY() {
		return TSM_AUTH_EXPIRY;
	}


	public void setTSM_AUTH_EXPIRY(Date tSM_AUTH_EXPIRY) {
		TSM_AUTH_EXPIRY = tSM_AUTH_EXPIRY;
	}


	public TSMData(String tSM_CLIENT_ID, String tSM_AUTH_CODE,
			Date tSM_AUTH_EXPIRY) {
		super();
		TSM_CLIENT_ID = tSM_CLIENT_ID;
		TSM_AUTH_CODE = tSM_AUTH_CODE;
		TSM_AUTH_EXPIRY = tSM_AUTH_EXPIRY;
	}


	public TSMData() {
		super();
	}


	private Date TSM_AUTH_EXPIRY;
	
}
