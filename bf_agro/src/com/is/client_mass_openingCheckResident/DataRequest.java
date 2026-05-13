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
"Data"
})
@Generated("jsonschema2pojo")
public class DataRequest {

private static final Personaldata personaldata = null;
@JsonProperty("Data")
private Data data;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
private DataByPinppRequest DataByPinppRequestObject;
private DataRequest DataObject;

@JsonProperty("Data")
public Data getData() {
return data;
}

@JsonProperty("Data")
public void setData(Data data) {
this.data = data;
}

public DataRequest withData(Data data) {
this.data = data;
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

public DataRequest withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

public void setDataByPinppRequest(DataByPinppRequest DataByPinppRequestObject) {
	  this.DataByPinppRequestObject = DataByPinppRequestObject;
	 }

public void setData(DataRequest DataObject) {
	   this.DataObject = DataObject;
	  }

public Personaldata getPersonaldata() {
return personaldata;
}


}
