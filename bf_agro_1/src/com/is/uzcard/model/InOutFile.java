package com.is.uzcard.model;

import java.util.Date;

public class InOutFile {
	private String appID;
	private String sentFileName;
	private Date sendDate;
	private String receivedFileName;
	private Date receiveDate;
	
	public InOutFile(String appID, String sentFileName, Date sendDate, String receivedFileName, Date receiveDate) {
		super();
		this.appID = appID;
		this.sentFileName = sentFileName;
		this.sendDate = sendDate;
		this.receivedFileName = receivedFileName;
		this.receiveDate = receiveDate;
	}

	public InOutFile() {
		// TODO Auto-generated constructor stub
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getSentFileName() {
		return sentFileName;
	}

	public void setSentFileName(String sentFileName) {
		this.sentFileName = sentFileName;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getReceivedFileName() {
		return receivedFileName;
	}

	public void setReceivedFileName(String receivedFileName) {
		this.receivedFileName = receivedFileName;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	
	
}
