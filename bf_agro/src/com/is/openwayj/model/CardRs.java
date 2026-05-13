package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardRs {

	    @JsonProperty("contract_number")
	    private String contractNumber;

	    @JsonProperty("cbs_number")
	    private String cbsNumber;

	    @JsonProperty("contract_category")
	    private String contractCategory;

	    @JsonProperty("card_expiry")
	    private String cardExpiry;

	    @JsonProperty("first_name")
	    private String firstName;

	    @JsonProperty("last_name")
	    private String lastName;

	    @JsonProperty("cvc")
	    private String cvc;

	    @JsonProperty("cvc2")
	    private String cvc2;
	    
	    @JsonProperty("contract_number")
		public String getContractNumber() {
			return contractNumber;
		}
	    @JsonProperty("cbs_number")
		public String getCbsNumber() {
			return cbsNumber;
		}
	    @JsonProperty("contract_category")
		public String getContractCategory() {
			return contractCategory;
		}
	    @JsonProperty("card_expiry")
		public String getCardExpiry() {
			return cardExpiry;
		}
	    @JsonProperty("first_name")
		public String getFirstName() {
			return firstName;
		}
	    @JsonProperty("last_name")
		public String getLastName() {
			return lastName;
		}
	    @JsonProperty("cvc")
		public String getCvc() {
			return cvc;
		}

		@JsonProperty("contract_number")
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
	    @JsonProperty("cbs_number")
		public void setCbsNumber(String cbsNumber) {
			this.cbsNumber = cbsNumber;
		}
	    @JsonProperty("contract_category")
		public void setContractCategory(String contractCategory) {
			this.contractCategory = contractCategory;
		}
	    @JsonProperty("card_expiry")
		public void setCardExpiry(String cardExpiry) {
			this.cardExpiry = cardExpiry;
		}
	    @JsonProperty("first_name")
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	    @JsonProperty("last_name")
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    @JsonProperty("cvc")
		public void setCvc(String cvc) {
			this.cvc = cvc;
		}
	    @JsonProperty("cvc2")
	    public void setCvc2(String cvc2) {
			this.cvc2 = cvc2;
		}
	    @JsonProperty("cvc2")
		public String getCvc2() {
			return cvc2;
		}
	    
}




