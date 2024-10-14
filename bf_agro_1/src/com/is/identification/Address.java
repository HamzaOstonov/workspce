
package com.is.identification;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "permanent_address",
    "temporary_address",
    "permanent_registration",
    "temporary_registration"
})
@Generated("jsonschema2pojo")
public class Address {

    @JsonProperty("permanent_address")
    private String permanentAddress;
    @JsonProperty("temporary_address")
    private Object temporaryAddress;
    @JsonProperty("permanent_registration")
    private PermanentRegistration permanentRegistration;
    @JsonProperty("temporary_registration")
    private TemporaryRegistration temporaryRegistration;
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
     * @param permanentRegistration
     * @param temporaryAddress
     * @param permanentAddress
     * @param temporaryRegistration
     */
    public Address(String permanentAddress, Object temporaryAddress, PermanentRegistration permanentRegistration, TemporaryRegistration temporaryRegistration) {
        super();
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.permanentRegistration = permanentRegistration;
        this.temporaryRegistration = temporaryRegistration;
    }

    @JsonProperty("permanent_address")
    public String getPermanentAddress() {
        return permanentAddress;
    }

    @JsonProperty("permanent_address")
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @JsonProperty("temporary_address")
    public Object getTemporaryAddress() {
        return temporaryAddress;
    }

    @JsonProperty("temporary_address")
    public void setTemporaryAddress(Object temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    @JsonProperty("permanent_registration")
    public PermanentRegistration getPermanentRegistration() {
        return permanentRegistration;
    }

    @JsonProperty("permanent_registration")
    public void setPermanentRegistration(PermanentRegistration permanentRegistration) {
        this.permanentRegistration = permanentRegistration;
    }

    @JsonProperty("temporary_registration")
    public TemporaryRegistration getTemporaryRegistration() {
        return temporaryRegistration;
    }

    @JsonProperty("temporary_registration")
    public void setTemporaryRegistration(TemporaryRegistration temporaryRegistration) {
        this.temporaryRegistration = temporaryRegistration;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("permanentAddress");
        sb.append('=');
        sb.append(((this.permanentAddress == null)?"<null>":this.permanentAddress));
        sb.append(',');
        sb.append("temporaryAddress");
        sb.append('=');
        sb.append(((this.temporaryAddress == null)?"<null>":this.temporaryAddress));
        sb.append(',');
        sb.append("permanentRegistration");
        sb.append('=');
        sb.append(((this.permanentRegistration == null)?"<null>":this.permanentRegistration));
        sb.append(',');
        sb.append("temporaryRegistration");
        sb.append('=');
        sb.append(((this.temporaryRegistration == null)?"<null>":this.temporaryRegistration));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
