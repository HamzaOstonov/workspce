package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;
public class DataAccCard {
	 

	    @JsonProperty("rbs_number")
	    private String rbsNumber;

	    @JsonProperty("contract_name")
	    private String contractName;

	    @JsonProperty("comment_text")
	    private String commentText;

	    @JsonProperty("product_code")
	    private String productCode;

	    @JsonProperty("date_open")
	    private String dateOpen;

	    @JsonProperty("add_info1")
	    private String addInfo1;

	  
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
	    @JsonProperty("date_open")
		public String getDateOpen() {
			return dateOpen;
		}
	    @JsonProperty("add_info1")
		public String getAddInfo1() {
			return addInfo1;
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
	    @JsonProperty("date_open")
		public void setDateOpen(String dateOpen) {
			this.dateOpen = dateOpen;
		}
	    @JsonProperty("add_info1")
		public void setAddInfo1(String addInfo1) {
			this.addInfo1 = addInfo1;
		}

}
