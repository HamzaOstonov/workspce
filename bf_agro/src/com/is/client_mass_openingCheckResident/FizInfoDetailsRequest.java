
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
"GetDataByPinppRequest"
})
@Generated("jsonschema2pojo")
public class FizInfoDetailsRequest {

@JsonProperty("GetDataByPinppRequest")
private DataRequest getDataByPinppRequest;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("GetDataByPinppRequest")
public DataRequest getGetDataByPinppRequest() {
return getDataByPinppRequest;
}

@JsonProperty("GetDataByPinppRequest")
public void setGetDataByPinppRequest(DataRequest getDataByPinppRequest) {
this.getDataByPinppRequest = getDataByPinppRequest;
}

public FizInfoDetailsRequest withGetDataByPinppRequest(DataRequest getDataByPinppRequest) {
this.getDataByPinppRequest = getDataByPinppRequest;
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

public FizInfoDetailsRequest withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
