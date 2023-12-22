package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransCode {
	@JsonProperty("MsgCode")
	 private String MsgCode;


	 // Getter Methods 
	@JsonProperty("MsgCode")
	 public String getMsgCode() {
	  return MsgCode;
	 }

	 // Setter Methods 
	@JsonProperty("MsgCode")
	 public void setMsgCode(String MsgCode) {
	  this.MsgCode = MsgCode;
	 }
	}