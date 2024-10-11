package com.is.client_mass_opening;

public class SuccessMessage {
	private String successmessage;
	private ClientResident clientResident;
	
	
	
	
	
	public SuccessMessage(String successmessage) {
		super();
		this.successmessage = successmessage;
		
	}
	
	public SuccessMessage(String successmessage,ClientResident clientResident) {
		this.successmessage = successmessage;
		this.clientResident = clientResident;
	}

	public String getSuccessmessage() {
		return successmessage;
	}

	public void setSuccessmessage(String successmessage) {
		this.successmessage = successmessage;
	}

	public ClientResident getClientResident() {
		return clientResident;
	}

	public void setClientResident(ClientResident clientResident) {
		this.clientResident = clientResident;
	}

	
	

}
