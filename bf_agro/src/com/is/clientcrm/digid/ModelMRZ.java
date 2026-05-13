
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
    "MRZ"
})
public class ModelMRZ {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("MRZ")
    private MRZ mRZ;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelMRZ() {
    }

    /**
     * 
     * @param mRZ
     * @param answere
     */
    public ModelMRZ(Answere answere, MRZ mRZ) {
        super();
        this.answere = answere;
        this.mRZ = mRZ;
    }

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("MRZ")
    public MRZ getMRZ() {
        return mRZ;
    }

    @JsonProperty("MRZ")
    public void setMRZ(MRZ mRZ) {
        this.mRZ = mRZ;
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
