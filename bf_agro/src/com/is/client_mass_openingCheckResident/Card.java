
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
"branch",
"account",
"bankClientId",
"humoClientId",
"expiry",
"cardNumber",
"phone",
"cardholderName",
"cardType"
})
@Generated("jsonschema2pojo")
public class Card {

@JsonProperty("branch")
private String branch;
@JsonProperty("account")
private String account;
@JsonProperty("bankClientId")
private String bankClientId;
@JsonProperty("humoClientId")
private String humoClientId;
@JsonProperty("expiry")
private String expiry;
@JsonProperty("cardNumber")
private String cardNumber;
@JsonProperty("phone")
private Object phone;
@JsonProperty("cardholderName")
private String cardholderName;
@JsonProperty("cardType")
private String cardType;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("branch")
public String getBranch() {
return branch;
}

@JsonProperty("branch")
public void setBranch(String branch) {
this.branch = branch;
}

@JsonProperty("account")
public String getAccount() {
return account;
}

@JsonProperty("account")
public void setAccount(String account) {
this.account = account;
}

@JsonProperty("bankClientId")
public String getBankClientId() {
return bankClientId;
}

@JsonProperty("bankClientId")
public void setBankClientId(String bankClientId) {
this.bankClientId = bankClientId;
}

@JsonProperty("humoClientId")
public String getHumoClientId() {
return humoClientId;
}

@JsonProperty("humoClientId")
public void setHumoClientId(String humoClientId) {
this.humoClientId = humoClientId;
}

@JsonProperty("expiry")
public String getExpiry() {
return expiry;
}

@JsonProperty("expiry")
public void setExpiry(String expiry) {
this.expiry = expiry;
}

@JsonProperty("cardNumber")
public String getCardNumber() {
return cardNumber;
}

@JsonProperty("cardNumber")
public void setCardNumber(String cardNumber) {
this.cardNumber = cardNumber;
}

@JsonProperty("phone")
public Object getPhone() {
return phone;
}

@JsonProperty("phone")
public void setPhone(Object phone) {
this.phone = phone;
}

@JsonProperty("cardholderName")
public String getCardholderName() {
return cardholderName;
}

@JsonProperty("cardholderName")
public void setCardholderName(String cardholderName) {
this.cardholderName = cardholderName;
}

@JsonProperty("cardType")
public String getCardType() {
return cardType;
}

@JsonProperty("cardType")
public void setCardType(String cardType) {
this.cardType = cardType;
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