package com.is.bpri;


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
"claim_id",
"katm_sir"
})
public class Response {

@JsonProperty("claim_id")
private String claimId;
@JsonProperty("katm_sir")
private String katmSir;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Response() {
}

/**
*
* @param claimId
* @param katmSir
*/
public Response(String claimId, String katmSir) {
super();
this.claimId = claimId;
this.katmSir = katmSir;
}

@JsonProperty("claim_id")
public String getClaimId() {
return claimId;
}

@JsonProperty("claim_id")
public void setClaimId(String claimId) {
this.claimId = claimId;
}

@JsonProperty("katm_sir")
public String getKatmSir() {
return katmSir;
}

@JsonProperty("katm_sir")
public void setKatmSir(String katmSir) {
this.katmSir = katmSir;
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
