package com.is.openway.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class PhoneList {
	@JsonProperty("Phone")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList < Phone > PhoneListObject ;
	
	@JsonProperty("Phone")
	public void setPhoneListObject(ArrayList < Phone > phoneListObject) {
		PhoneListObject = phoneListObject;
	}
	@JsonProperty("Phone")
	public ArrayList < Phone > getPhoneListObject() {
		return PhoneListObject;
	}

}
