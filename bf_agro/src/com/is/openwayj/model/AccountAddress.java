package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountAddress {


	 @JsonProperty("order_dprt")
 	    private String orderDprt;

	    @JsonProperty("address_type")
	    private String addressType;

	    @JsonProperty("postal_code")
	    private String postalCode;

	    
		 @JsonProperty("order_dprt")
		public String getOrderDprt() {
			return orderDprt;
		}

		 @JsonProperty("address_type")
		public String getAddressType() {
			return addressType;
		}

		 @JsonProperty("postal_code")
		public String getPostalCode() {
			return postalCode;
		}

		 @JsonProperty("order_dprt")
		public void setOrderDprt(String orderDprt) {
			this.orderDprt = orderDprt;
		}

		 @JsonProperty("address_type")
		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}
		 
		 @JsonProperty("postal_code")
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
	    
}
