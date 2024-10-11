
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
    "PersonPhoto"
})
public class Photo {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("PersonPhoto")
    private String personPhoto;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("PersonPhoto")
    public String getPersonPhoto() {
        return personPhoto;
    }

    @JsonProperty("PersonPhoto")
    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
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
