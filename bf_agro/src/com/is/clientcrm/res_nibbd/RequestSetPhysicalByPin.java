package com.is.clientcrm.res_nibbd;


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
"pin",
"client",
"gender",
"birth_date",
"phone",
"email"
})
public class RequestSetPhysicalByPin {

@JsonProperty("pin")
private String pin;
@JsonProperty("client")
private String client;
@JsonProperty("gender")
private String gender;
@JsonProperty("birth_date")
private String birth_date;
@JsonProperty("phone")
private String phone;
@JsonProperty("email")
private String email;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public RequestSetPhysicalByPin() {
}

/**
*
* @param pin
* @param gender
* @param phone
* @param client
* @param birthDate
* @param email
*/
public RequestSetPhysicalByPin(String pin, String client, String gender, String birth_date, String phone, String email) {
super();
this.pin = pin;
this.client = client;
this.gender = gender;
this.birth_date = birth_date;
this.phone = phone;
this.email = email;
}

public RequestSetPhysicalByPin(String pin, String client, String gender, String birth_date, String phone) {
super();
this.pin = pin;
this.client = client;
this.gender = gender;
this.birth_date = birth_date;
this.phone = phone;
}

public RequestSetPhysicalByPin(String pin, String client, String gender, String birth_date) {
super();
this.pin = pin;
this.client = client;
this.gender = gender;
this.birth_date = birth_date;
}
@JsonProperty("pin")
public String getPin() {
return pin;
}

@JsonProperty("pin")
public void setPin(String pin) {
this.pin = pin;
}

@JsonProperty("client")
public String getClient() {
return client;
}

@JsonProperty("client")
public void setClient(String client) {
this.client = client;
}

@JsonProperty("gender")
public String getGender() {
return gender;
}

@JsonProperty("gender")
public void setGender(String gender) {
this.gender = gender;
}

@JsonProperty("birth_date")
public String getBirthDate() {
return birth_date;
}

@JsonProperty("birth_date")
public void setBirthDate(String birthDate) {
this.birth_date = birthDate;
}

@JsonProperty("phone")
public String getPhone() {
return phone;
}

@JsonProperty("phone")
public void setPhone(String phone) {
this.phone = phone;
}

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
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
public String toString() {
	return "{"
			+ "\"pin\": \"" + pin   + "\" , "
	        + "\"client\": \"" + client + "\" , "
	        + "\"gender\": \"" + gender + "\" ,  "
	        + "\"birth_date\": \"" + birth_date + "\" ,  "
	        + "\"phone\": \"" + phone + "\"    "
			+ "}";
}


}
