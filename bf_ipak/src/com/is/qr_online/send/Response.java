package com.is.qr_online.send;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.is.qr_online.send.RegQRClient.ResponseQrClient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "header", "result", "response" })
public class Response {

	@JsonProperty("header")
	private ResponseHeader header;
	@JsonProperty("result")
	private ResponseResult result;
	@JsonProperty("response")
	private ResponseQr response;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Response(ResponseHeader header,ResponseResult result,ResponseQr response){
		this.header=header;
		this.result=result;
		this.response=response;
	}
	public Response(){
		
	}
	
	
	@JsonProperty("header")
	public ResponseHeader getHeader() {
		return header;
	}

	@JsonProperty("header")
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}

	@JsonProperty("result")
	public ResponseResult getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(ResponseResult result) {
		this.result = result;
	}

	@JsonProperty("response")
	public ResponseQr getResponse() {
		return response;
	}

	@JsonProperty("response")
	public void setResponse(ResponseQr response) {
		this.response = response;
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