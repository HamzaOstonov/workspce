
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
    "Line1",
    "Line2",
    "Line3",
    "Additional"
})
public class MRZ {

    @JsonProperty("Line1")
    private String line1;
    @JsonProperty("Line2")
    private String line2;
    @JsonProperty("Line3")
    private Object line3;
    @JsonProperty("Additional")
    private Object additional;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MRZ() {
    }

    /**
     * 
     * @param additional
     * @param line3
     * @param line2
     * @param line1
     */
    public MRZ(String line1, String line2, Object line3, Object additional) {
        super();
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.additional = additional;
    }

    @JsonProperty("Line1")
    public String getLine1() {
        return line1;
    }

    @JsonProperty("Line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @JsonProperty("Line2")
    public String getLine2() {
        return line2;
    }

    @JsonProperty("Line2")
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @JsonProperty("Line3")
    public Object getLine3() {
        return line3;
    }

    @JsonProperty("Line3")
    public void setLine3(Object line3) {
        this.line3 = line3;
    }

    @JsonProperty("Additional")
    public Object getAdditional() {
        return additional;
    }

    @JsonProperty("Additional")
    public void setAdditional(Object additional) {
        this.additional = additional;
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
