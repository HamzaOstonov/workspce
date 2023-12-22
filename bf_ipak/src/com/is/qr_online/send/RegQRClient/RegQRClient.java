package com.is.qr_online.send.RegQRClient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "inn", "phone", "dsign" })
public class RegQRClient {

	@JsonProperty("inn")
	private String inn;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("dsign")
	private String dsign;	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public RegQRClient(){
	}

	public RegQRClient(String inn, String phone, String dsign){
	     this.inn = inn;
	     this.phone = phone;
	     this.dsign = dsign;
	}
	
	@JsonProperty("inn")
	public String getInn() {
		return inn;
	}

	@JsonProperty("inn")
	public void setInn(String inn) {
		this.inn = inn;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("dsign")
	public String getDsign() {
		return dsign;
	}

	@JsonProperty("dsign")
	public void setDsign(String dsign) {
		this.dsign = dsign;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	@Override
	public String toString() {
		return "{"
				+ "\"inn\": \"" + inn   + "\" , "
		        + "\"phone\": \"" + phone + "\" , "
		        + "\"dsign\": \"" + dsign + "\"   "
				+ "}";
	}
	
	

}