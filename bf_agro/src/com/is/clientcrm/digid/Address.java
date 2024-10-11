
package com.is.clientcrm.digid;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "answere",
    "Address"
})
public class Address {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("Address")
    private Address_Client address;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Address() {
    }

    /**
     * 
     * @param address
     * @param answere
     */
    public Address(Answere answere, Address_Client address) {
        super();
        this.answere = answere;
        this.address = address;
    }

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("Address")
    public Address_Client getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(Address_Client address) {
        this.address = address;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
