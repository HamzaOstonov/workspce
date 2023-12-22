package com.is.qr_online.send;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "respond", "inquire", "query_id" })
public class ResponseHeader {

	@JsonProperty("respond")
	private String respond;
	@JsonProperty("inquire")
	private String inquire;
	@JsonProperty("query_id")
	private String queryId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("respond")
	public String getRespond() {
		return respond;
	}

	@JsonProperty("respond")
	public void setRespond(String respond) {
		this.respond = respond;
	}

	@JsonProperty("inquire")
	public String getInquire() {
		return inquire;
	}

	@JsonProperty("inquire")
	public void setInquire(String inquire) {
		this.inquire = inquire;
	}

	@JsonProperty("query_id")
	public String getQueryId() {
		return queryId;
	}

	@JsonProperty("query_id")
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}