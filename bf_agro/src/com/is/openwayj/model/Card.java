package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {

	    @JsonProperty("order_dprt")
	    private String orderDprt;

	    @JsonProperty("rbs_number")
	    private String rbsNumber;

	    @JsonProperty("contract_name")
	    private String contractName;

	    @JsonProperty("comment_text")
	    private String commentText;

	    @JsonProperty("product_code")
	    private String productCode;

	    @JsonProperty("first_name")
	    private String firstName;

	    @JsonProperty("last_name")
	    private String lastName;

	    @JsonProperty("date_open")
	    private String dateOpen;

	    
	    @JsonProperty("order_dprt")
	    public String getOrderDprt() {
			return orderDprt;
		}
	    @JsonProperty("rbs_number")
		public String getRbsNumber() {
			return rbsNumber;
		}
	    @JsonProperty("contract_name")
		public String getContractName() {
			return contractName;
		}
	    @JsonProperty("comment_text")
		public String getCommentText() {
			return commentText;
		}
	    @JsonProperty("product_code")
		public String getProductCode() {
			return productCode;
		}
	    @JsonProperty("first_name")
		public String getFirstName() {
			return firstName;
		}
	    @JsonProperty("last_name")
		public String getLastName() {
			return lastName;
		}
	    @JsonProperty("date_open")
		public String getDateOpen() {
			return dateOpen;
		}

	    @JsonProperty("order_dprt")
	    public void setOrderDprt(String orderDprt) {
			this.orderDprt = orderDprt;
		}
	    @JsonProperty("rbs_number")
		public void setRbsNumber(String rbsNumber) {
			this.rbsNumber = rbsNumber;
		}
	    @JsonProperty("contract_name")
		public void setContractName(String contractName) {
			this.contractName = contractName;
		}
	    @JsonProperty("comment_text")
		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}
	    @JsonProperty("product_code")
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
	    @JsonProperty("first_name")
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	    @JsonProperty("last_name")
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    @JsonProperty("date_open")
		public void setDateOpen(String dateOpen) {
			this.dateOpen = dateOpen;
		}
	    
	    
	    
}
