package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCardBody {

	    @JsonProperty("contract_number")
	    private String contract_number;

	    @JsonProperty("cbs_number")
	    private String cbs_number;

	    @JsonProperty("card_active")
	    private String card_active;
	    
	    @JsonProperty("sms_active")
	    private String sms_active;
	    
	    @JsonProperty("sms_phone")
	    private String sms_phone;
	    
	    @JsonProperty("balance")
	    private String balance;
	    
	    @JsonProperty("lock_balance")
	    private String lock_balance;
	    
	    @JsonProperty("currency")
	    private String currency;
	    
	    @JsonProperty("expiry")
	    private String expiry;
	    
	    @JsonProperty("contract_name")
	    private String contract_name;
	    
	    @JsonProperty("first_name")
	    private String first_name;
	    
	    @JsonProperty("last_name")
	    private String last_name;
	    @JsonProperty("contract_number")
		public String getContract_number() {
			return contract_number;
		}
	    @JsonProperty("cbs_number")
		public String getCbs_number() {
			return cbs_number;
		}
	    @JsonProperty("card_active")
		public String getCard_active() {
			return card_active;
		}
	    @JsonProperty("sms_active")
		public String getSms_active() {
			return sms_active;
		}
	    @JsonProperty("sms_phone")
		public String getSms_phone() {
			return sms_phone;
		}
	    @JsonProperty("balance")
		public String getBalance() {
			return balance;
		}
	    @JsonProperty("lock_balance")
		public String getLock_balance() {
			return lock_balance;
		}
	    @JsonProperty("currency")
		public String getCurrency() {
			return currency;
		}
	    @JsonProperty("expiry")
		public String getExpiry() {
			return expiry;
		}
	    @JsonProperty("contract_name")
		public String getContract_name() {
			return contract_name;
		}
	    @JsonProperty("first_name")
		public String getFirst_name() {
			return first_name;
		}
	    @JsonProperty("last_name")
		public String getLast_name() {
			return last_name;
		}
		@JsonProperty("contract_number")
		public void setContract_number(String contract_number) {
			this.contract_number = contract_number;
		}
		 @JsonProperty("cbs_number")
		public void setCbs_number(String cbs_number) {
			this.cbs_number = cbs_number;
		}
		  @JsonProperty("card_active")
		public void setCard_active(String card_active) {
			this.card_active = card_active;
		}
		  @JsonProperty("sms_active")
		public void setSms_active(String sms_active) {
			this.sms_active = sms_active;
		}
		  @JsonProperty("sms_phone")
		public void setSms_phone(String sms_phone) {
			this.sms_phone = sms_phone;
		}
		  @JsonProperty("balance")
		public void setBalance(String balance) {
			this.balance = balance;
		}
		  @JsonProperty("lock_balance")
		public void setLock_balance(String lock_balance) {
			this.lock_balance = lock_balance;
		}
		  @JsonProperty("currency")
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		  @JsonProperty("expiry")
		public void setExpiry(String expiry) {
			this.expiry = expiry;
		}
		  @JsonProperty("contract_name")
		public void setContract_name(String contract_name) {
			this.contract_name = contract_name;
		}
		  @JsonProperty("first_name")
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		  @JsonProperty("last_name")
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

	   

}
