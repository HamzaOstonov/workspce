package com.is.qr_online.send;
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
"qr_id",
"descr",
"qr"
})
public class ResponseQr {

@JsonProperty("qr_id")
private String qrId;
@JsonProperty("descr")
private String descr;
@JsonProperty("qr")
private String qr;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("qr_id")
public String getQrId() {
return qrId;
}

@JsonProperty("qr_id")
public void setQrId(String qrId) {
this.qrId = qrId;
}

@JsonProperty("descr")
public String getDescr() {
return descr;
}

@JsonProperty("descr")
public void setDescr(String descr) {
this.descr = descr;
}

@JsonProperty("qr")
public String getQr() {
return qr;
}

@JsonProperty("qr")
public void setQr(String qr) {
this.qr = qr;
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