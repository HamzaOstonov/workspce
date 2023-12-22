package com.is.clientcrm.res_gsp;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"result_code",
"result_desc",
"result_sql"
})
public class Res_Gsp {

@JsonProperty("result_code")
private String resultCode;
@JsonProperty("result_desc")
private String resultDesc;
@JsonProperty("result_sql")
private String resultSql;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Res_Gsp() {
}

/**
*
* @param resultCode
* @param resultSql
* @param resultDesc
*/
public Res_Gsp(String resultCode, String resultDesc, String resultSql) {
super();
this.resultCode = resultCode;
this.resultDesc = resultDesc;
this.resultSql = resultSql;
}

@JsonProperty("result_code")
public String getResultCode() {
return resultCode;
}

@JsonProperty("result_code")
public void setResultCode(String resultCode) {
this.resultCode = resultCode;
}

@JsonProperty("result_desc")
public String getResultDesc() {
return resultDesc;
}

@JsonProperty("result_desc")
public void setResultDesc(String resultDesc) {
this.resultDesc = resultDesc;
}

@JsonProperty("result_sql")
public String getResultSql() {
return resultSql;
}

@JsonProperty("result_sql")
public void setResultSql(String resultSql) {
this.resultSql = resultSql;
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
public String toString(){
	return "{"
			+ "\"resultCode\": \"" + resultCode   + "\" , "
	        + "\"resultDesc\": \"" + resultDesc + "\" , "
	        + "\"resultSql\": \"" + resultSql + "\"   "
			+ "}";
	
}

}