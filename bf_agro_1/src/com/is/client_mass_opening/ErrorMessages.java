package com.is.client_mass_opening;

public class ErrorMessages {
	//private int error_code;
	private String error_message;

	public ErrorMessages() {
		
	}
	public ErrorMessages( String error_message) {
		super();
		//this.error_code = error_code;
		this.error_message = error_message;
	}

	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}


}
