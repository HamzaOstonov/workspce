
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
    "response_id",
    "comparison_value",
    "result_code",
    "result_note",
    "profile"
})
@Generated("jsonschema2pojo")
public class AnswerMyId {

    @JsonProperty("response_id")
    private String responseId;
    @JsonProperty("comparison_value")
    private Double comparisonValue;
    @JsonProperty("result_code")
    private Integer resultCode;
    @JsonProperty("result_note")
    private String resultNote;
    @JsonProperty("profile")
    private Profile profile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AnswerMyId() {
    }

    /**
     * 
     * @param comparisonValue
     * @param profile
     * @param resultCode
     * @param resultNote
     * @param responseId
     */
    public AnswerMyId(String responseId, Double comparisonValue, Integer resultCode, String resultNote, Profile profile) {
        super();
        this.responseId = responseId;
        this.comparisonValue = comparisonValue;
        this.resultCode = resultCode;
        this.resultNote = resultNote;
        this.profile = profile;
    }

    @JsonProperty("response_id")
    public String getResponseId() {
        return responseId;
    }

    @JsonProperty("response_id")
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @JsonProperty("comparison_value")
    public Double getComparisonValue() {
        return comparisonValue;
    }

    @JsonProperty("comparison_value")
    public void setComparisonValue(Double comparisonValue) {
        this.comparisonValue = comparisonValue;
    }

    @JsonProperty("result_code")
    public Integer getResultCode() {
        return resultCode;
    }

    @JsonProperty("result_code")
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("result_note")
    public String getResultNote() {
        return resultNote;
    }

    @JsonProperty("result_note")
    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    @JsonProperty("profile")
    public Profile getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(Profile profile) {
        this.profile = profile;
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
        sb.append(AnswerMyId.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("responseId");
        sb.append('=');
        sb.append(((this.responseId == null)?"<null>":this.responseId));
        sb.append(',');
        sb.append("comparisonValue");
        sb.append('=');
        sb.append(((this.comparisonValue == null)?"<null>":this.comparisonValue));
        sb.append(',');
        sb.append("resultCode");
        sb.append('=');
        sb.append(((this.resultCode == null)?"<null>":this.resultCode));
        sb.append(',');
        sb.append("resultNote");
        sb.append('=');
        sb.append(((this.resultNote == null)?"<null>":this.resultNote));
        sb.append(',');
        sb.append("profile");
        sb.append('=');
        sb.append(((this.profile == null)?"<null>":this.profile));
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
