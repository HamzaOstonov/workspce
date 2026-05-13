package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRs {

	  @JsonProperty("contract_number")
	    private String contractNumber;

	    @JsonProperty("cbs_number")
	    private String cbsNumber;

	    @JsonProperty("contract_name")
	    private String contractName;

	    @JsonProperty("contract_category")
	    private String contractCategory;

	    
	    @JsonProperty("contract_number")
		public String getContractNumber() {
			return contractNumber;
		}
	    @JsonProperty("cbs_number")
		public String getCbsNumber() {
			return cbsNumber;
		}
	    @JsonProperty("contract_name")
		public String getContractName() {
			return contractName;
		}
	    @JsonProperty("contract_category")
		public String getContractCategory() {
			return contractCategory;
		}

		@JsonProperty("contract_number")
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
	    @JsonProperty("cbs_number")
		public void setCbsNumber(String cbsNumber) {
			this.cbsNumber = cbsNumber;
		}
	    @JsonProperty("contract_name")
		public void setContractName(String contractName) {
			this.contractName = contractName;
		}
	    @JsonProperty("contract_category")
		public void setContractCategory(String contractCategory) {
			this.contractCategory = contractCategory;
		}
	    
}
