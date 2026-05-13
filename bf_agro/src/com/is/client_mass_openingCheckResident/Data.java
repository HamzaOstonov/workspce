
package  com.is.client_mass_openingCheckResident; 

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
"DataByPinppRequest"
})
@Generated("jsonschema2pojo")
public class Data {

@JsonProperty("DataByPinppRequest")
private DataByPinppRequest dataByPinppRequest;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("DataByPinppRequest")
public DataByPinppRequest getDataByPinppRequest() {
return dataByPinppRequest;
}

@JsonProperty("DataByPinppRequest")
public void setDataByPinppRequest(DataByPinppRequest dataByPinppRequest) {
this.dataByPinppRequest = dataByPinppRequest;
}

public Data withDataByPinppRequest(DataByPinppRequest dataByPinppRequest) {
this.dataByPinppRequest = dataByPinppRequest;
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

public Data withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}