
 package com.is.client_mass_openingCheckResident;

import java.util.LinkedHashMap;
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
"row"
})
@Generated("jsonschema2pojo")
public class Personaldata {

@JsonProperty("row")
private Row row;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("row")
public Row getRow() {
return row;
}

@JsonProperty("row")
public void setRow(Row row) {
this.row = row;
}

public Personaldata withRow(Row row) {
this.row = row;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public Personaldata withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
