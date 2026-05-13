package com.is.openwayj.model;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"status",
"status_code",
"status_detail",
"production_status",
"active"
})

public class ContractStatus {
	
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_code")
	private String statusCode;
	@JsonProperty("status_detail")
	private String statusDetail;
	@JsonProperty("production_status")
	private String productionStatus;
	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("status")
	public String getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
	this.status = status;
	}

	@JsonProperty("status_code")
	public String getStatusCode() {
	return statusCode;
	}

	@JsonProperty("status_code")
	public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
	}

	@JsonProperty("status_detail")
	public String getStatusDetail() {
	return statusDetail;
	}

	@JsonProperty("status_detail")
	public void setStatusDetail(String statusDetail) {
	this.statusDetail = statusDetail;
	}

	@JsonProperty("production_status")
	public String getProductionStatus() {
	return productionStatus;
	}

	@JsonProperty("production_status")
	public void setProductionStatus(String productionStatus) {
	this.productionStatus = productionStatus;
	}

	@JsonProperty("active")
	public Boolean getActive() {
	return active;
	}

	@JsonProperty("active")
	public void setActive(Boolean active) {
	this.active = active;
	}

}
