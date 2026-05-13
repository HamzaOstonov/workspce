package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LockCardRequest {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("order_dprt")
    private String orderDprt;

    @JsonProperty("rbs_number")
    private String rbsNumber;

    @JsonProperty("social_number")
    private String socialNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_comment")
    private String statusComment;

    @JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
    @JsonProperty("order_dprt")
	public String getOrderDprt() {
		return orderDprt;
	}
    @JsonProperty("rbs_number")
	public String getRbsNumber() {
		return rbsNumber;
	}
    @JsonProperty("social_number")
	public String getSocialNumber() {
		return socialNumber;
	}
    @JsonProperty("status")
	public String getStatus() {
		return status;
	}
    @JsonProperty("status_comment")
	public String getStatusComment() {
		return statusComment;
	}
	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    @JsonProperty("order_dprt")
	public void setOrderDprt(String orderDprt) {
		this.orderDprt = orderDprt;
	}
    @JsonProperty("rbs_number")
	public void setRbsNumber(String rbsNumber) {
		this.rbsNumber = rbsNumber;
	}
    @JsonProperty("social_number")
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}
    @JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}
    @JsonProperty("status_comment")
	public void setStatusComment(String statusComment) {
		this.statusComment = statusComment;
	}

    
}
