package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseAddress {

	@JsonProperty("email")
    private String email;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("address_line1")
    private String addressLine1;

    @JsonProperty("address_line2")
    private String addressLine2;

    @JsonProperty("date_from")
    private String dateFrom;

    
    @JsonProperty("email")
	public String getEmail() {
		return email;
	}
    @JsonProperty("city")
	public String getCity() {
		return city;
	}
    @JsonProperty("postal_code")

	public String getPostalCode() {
		return postalCode;
	}
    @JsonProperty("address_line1")
	public String getAddressLine1() {
		return addressLine1;
	}
    @JsonProperty("address_line2")
	public String getAddressLine2() {
		return addressLine2;
	}
    @JsonProperty("date_from")
	public String getDateFrom() {
		return dateFrom;
	}

    @JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}
    @JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}
    @JsonProperty("postal_code")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
    @JsonProperty("address_line1")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
    @JsonProperty("address_line2")
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
    @JsonProperty("date_from")
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}


    
	    	    
	    
	    
}
