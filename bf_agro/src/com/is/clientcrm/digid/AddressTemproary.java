
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
    "AddressTemproary"
})
public class AddressTemproary {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("AddressTemproary")
    private Object addressTemproary;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AddressTemproary() {
    }

    /**
     * 
     * @param addressTemproary
     * @param answere
     */
    public AddressTemproary(Answere answere, Object addressTemproary) {
        super();
        this.answere = answere;
        this.addressTemproary = addressTemproary;
    }

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("AddressTemproary")
    public Object getAddressTemproary() {
        return addressTemproary;
    }

    @JsonProperty("AddressTemproary")
    public void setAddressTemproary(Object addressTemproary) {
        this.addressTemproary = addressTemproary;
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
