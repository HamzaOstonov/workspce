package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"order_dprt",
"client_type",
"client_category",
"currency",
"contract_name",
"contract_number",
"cbs_number",
"date_open",
"first_name",
"last_name",
"card_expiry",
"sequence_number",
"additional_info",
"contract_category",
"status"
})

public class Contract {

	@JsonProperty("order_dprt")
	private String orderDprt;
	@JsonProperty("client_type")
	private String clientType;
	@JsonProperty("client_category")
	private String clientCategory;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("contract_name")
	private String contractName;
	@JsonProperty("contract_number")
	private String contractNumber;
	@JsonProperty("cbs_number")
	private String cbsNumber;
	@JsonProperty("date_open")
	private String dateOpen;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("card_expiry")
	private String cardExpiry;
	@JsonProperty("sequence_number")
	private String sequenceNumber;
	@JsonProperty("additional_info")
	private Object additionalInfo;
	@JsonProperty("contract_category")
	private String contractCategory;
	@JsonProperty("status")
	private ContractStatus status;

	@JsonProperty("order_dprt")
	public String getOrderDprt() {
	return orderDprt;
	}

	@JsonProperty("order_dprt")
	public void setOrderDprt(String orderDprt) {
	this.orderDprt = orderDprt;
	}

	@JsonProperty("client_type")
	public String getClientType() {
	return clientType;
	}

	@JsonProperty("client_type")
	public void setClientType(String clientType) {
	this.clientType = clientType;
	}

	@JsonProperty("client_category")
	public String getClientCategory() {
	return clientCategory;
	}

	@JsonProperty("client_category")
	public void setClientCategory(String clientCategory) {
	this.clientCategory = clientCategory;
	}

	@JsonProperty("currency")
	public String getCurrency() {
	return currency;
	}

	@JsonProperty("currency")
	public void setCurrency(String currency) {
	this.currency = currency;
	}

	@JsonProperty("contract_name")
	public String getContractName() {
	return contractName;
	}

	@JsonProperty("contract_name")
	public void setContractName(String contractName) {
	this.contractName = contractName;
	}

	@JsonProperty("contract_number")
	public String getContractNumber() {
	return contractNumber;
	}

	@JsonProperty("contract_number")
	public void setContractNumber(String contractNumber) {
	this.contractNumber = contractNumber;
	}

	@JsonProperty("cbs_number")
	public String getCbsNumber() {
	return cbsNumber;
	}

	@JsonProperty("cbs_number")
	public void setCbsNumber(String cbsNumber) {
	this.cbsNumber = cbsNumber;
	}

	@JsonProperty("date_open")
	public String getDateOpen() {
	return dateOpen;
	}

	@JsonProperty("date_open")
	public void setDateOpen(String dateOpen) {
	this.dateOpen = dateOpen;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
	return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
	this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
	return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
	this.lastName = lastName;
	}

	@JsonProperty("card_expiry")
	public String getCardExpiry() {
	return cardExpiry;
	}

	@JsonProperty("card_expiry")
	public void setCardExpiry(String cardExpiry) {
	this.cardExpiry = cardExpiry;
	}

	@JsonProperty("sequence_number")
	public String getSequenceNumber() {
	return sequenceNumber;
	}

	@JsonProperty("sequence_number")
	public void setSequenceNumber(String sequenceNumber) {
	this.sequenceNumber = sequenceNumber;
	}

	@JsonProperty("additional_info")
	public Object getAdditionalInfo() {
	return additionalInfo;
	}

	@JsonProperty("additional_info")
	public void setAdditionalInfo(Object additionalInfo) {
	this.additionalInfo = additionalInfo;
	}

	@JsonProperty("contract_category")
	public String getContractCategory() {
	return contractCategory;
	}

	@JsonProperty("contract_category")
	public void setContractCategory(String contractCategory) {
	this.contractCategory = contractCategory;
	}

	@JsonProperty("status")
	public ContractStatus getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(ContractStatus status) {
	this.status = status;
	}

}
