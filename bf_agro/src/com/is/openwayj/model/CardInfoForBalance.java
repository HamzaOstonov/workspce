package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardInfoForBalance {
	@JsonProperty("rbs_number")
	private String rbsNumber;
	
	@JsonProperty("rbs_number")
	public String getRbsNumber() {
	return rbsNumber;
	}

	@JsonProperty("rbs_number")
	public void setRbsNumber(String rbsNumber) {
	this.rbsNumber = rbsNumber;
	}
}
