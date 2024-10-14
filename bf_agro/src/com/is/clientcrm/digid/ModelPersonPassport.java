
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
    "PersonPassport"
})
public class ModelPersonPassport {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("PersonPassport")
    private PersonPassport personPassport;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelPersonPassport() {
    }

    /**
     * 
     * @param personPassport
     * @param answere
     */
    public ModelPersonPassport(Answere answere, PersonPassport personPassport) {
        super();
        this.answere = answere;
        this.personPassport = personPassport;
    }

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("PersonPassport")
    public PersonPassport getPersonPassport() {
        return personPassport;
    }

    @JsonProperty("PersonPassport")
    public void setPersonPassport(PersonPassport personPassport) {
        this.personPassport = personPassport;
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
