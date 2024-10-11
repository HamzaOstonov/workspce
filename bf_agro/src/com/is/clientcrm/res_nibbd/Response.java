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
"client",
"pin",
"doc_type",
"doc_seria",
"doc_number",
"issue_date",
"expiry_date",
"place_name",
"visa_region",
"visa_disctrict",
"last_name",
"first_name",
"patronym",
"birth_date",
"gender",
"citizenship",
"opened"
})
public class Response {

@JsonProperty("client")
private String client;
@JsonProperty("pin")
private String pin;
@JsonProperty("doc_type")
private String docType;
@JsonProperty("doc_seria")
private String docSeria;
@JsonProperty("doc_number")
private String docNumber;
@JsonProperty("issue_date")
private String issueDate;
@JsonProperty("expiry_date")
private String expiryDate;
@JsonProperty("place_name")
private String placeName;
@JsonProperty("visa_region")
private String visaRegion;
@JsonProperty("visa_disctrict")
private String visaDisctrict;
@JsonProperty("last_name")
private String lastName;
@JsonProperty("first_name")
private String firstName;
@JsonProperty("patronym")
private String patronym;
@JsonProperty("birth_date")
private String birthdate;
@JsonProperty("gender")
private String gender;
@JsonProperty("citizenship")
private String citizenship;
@JsonProperty("opened")
private String opened;
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
* @param lastName
* @param gender
* @param docNumber
* @param docType
* @param citizenship
* @param visaRegion
* @param visaDisctrict
* @param opened
* @param birthDate
* @param patronym
* @param expiryDate
* @param firstName
* @param pin
* @param client
* @param docSeria
* @param issueDate
* @param placeName
*/
public Response(String client, String pin, String docType, String docSeria, String docNumber, String issueDate, String expiryDate, String placeName, String visaRegion, String visaDisctrict, String lastName, String firstName, String patronym, String birthdate, String gender, String citizenship, String opened) {
super();
this.client = client;
this.pin = pin;
this.docType = docType;
this.docSeria = docSeria;
this.docNumber = docNumber;
this.issueDate = issueDate;
this.expiryDate = expiryDate;
this.placeName = placeName;
this.visaRegion = visaRegion;
this.visaDisctrict = visaDisctrict;
this.lastName = lastName;
this.firstName = firstName;
this.patronym = patronym;
this.birthdate = birthdate;
this.gender = gender;
this.citizenship = citizenship;
this.opened = opened;
}

@JsonProperty("client")
public String getClient() {
return client;
}

@JsonProperty("client")
public void setClient(String client) {
this.client = client;
}

@JsonProperty("pin")
public String getPin() {
return pin;
}

@JsonProperty("pin")
public void setPin(String pin) {
this.pin = pin;
}

@JsonProperty("doc_type")
public String getDocType() {
return docType;
}

@JsonProperty("doc_type")
public void setDocType(String docType) {
this.docType = docType;
}

@JsonProperty("doc_seria")
public String getDocSeria() {
return docSeria;
}

@JsonProperty("doc_seria")
public void setDocSeria(String docSeria) {
this.docSeria = docSeria;
}

@JsonProperty("doc_number")
public String getDocNumber() {
return docNumber;
}

@JsonProperty("doc_number")
public void setDocNumber(String docNumber) {
this.docNumber = docNumber;
}

@JsonProperty("issue_date")
public String getIssueDate() {
return issueDate;
}

@JsonProperty("issue_date")
public void setIssueDate(String issueDate) {
this.issueDate = issueDate;
}

@JsonProperty("expiry_date")
public String getExpiryDate() {
return expiryDate;
}

@JsonProperty("expiry_date")
public void setExpiryDate(String expiryDate) {
this.expiryDate = expiryDate;
}

@JsonProperty("place_name")
public String getPlaceName() {
return placeName;
}

@JsonProperty("place_name")
public void setPlaceName(String placeName) {
this.placeName = placeName;
}

@JsonProperty("visa_region")
public String getVisaRegion() {
return visaRegion;
}

@JsonProperty("visa_region")
public void setVisaRegion(String visaRegion) {
this.visaRegion = visaRegion;
}

@JsonProperty("visa_disctrict")
public String getVisaDisctrict() {
return visaDisctrict;
}

@JsonProperty("visa_disctrict")
public void setVisaDisctrict(String visaDisctrict) {
this.visaDisctrict = visaDisctrict;
}

@JsonProperty("last_name")
public String getLastName() {
return lastName;
}

@JsonProperty("last_name")
public void setLastName(String lastName) {
this.lastName = lastName;
}

@JsonProperty("first_name")
public String getFirstName() {
return firstName;
}

@JsonProperty("first_name")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

@JsonProperty("patronym")
public String getPatronym() {
return patronym;
}

@JsonProperty("patronym")
public void setPatronym(String patronym) {
this.patronym = patronym;
}

@JsonProperty("birth_date")
public String getBirthDate() {
return birthdate;
}

@JsonProperty("birth_date")
public void setBirthDate(String birthdate) {
this.birthdate = birthdate;
}

@JsonProperty("gender")
public String getGender() {
return gender;
}

@JsonProperty("gender")
public void setGender(String gender) {
this.gender = gender;
}

@JsonProperty("citizenship")
public String getCitizenship() {
return citizenship;
}

@JsonProperty("citizenship")
public void setCitizenship(String citizenship) {
this.citizenship = citizenship;
}

@JsonProperty("opened")
public String getOpened() {
return opened;
}

@JsonProperty("opened")
public void setOpened(String opened) {
this.opened = opened;
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
      + "\"client\":  " + client + ", "
      + "\"pin\":  " + pin + ", "
      + "\"docType\":  " + docType + ", "
      + "\"docSeria\":  " + docSeria + ", "
      + "\"docNumber\":  " + docNumber + ", "
      + "\"issueDate\":  " + issueDate + ", "
      + "\"expiryDate\":  " + expiryDate + ", "
      + "\"placeName\":  " + placeName + ", "
      + "\"visaRegion\":  " + visaRegion + ", "
      + "\"visaDisctrict\":  " + visaDisctrict + ", "
      + "\"lastName\":  " + lastName + ", "
      + "\"firstName\":  " + firstName + ", "
      + "\"patronym\":  " + patronym + ", "
      + "\"birthdate\":  " + birthdate + ", "
      + "\"gender\":  " + gender + ", "
      + "\"citizenship\":  " + citizenship + ", "
      + "\"opened\":  \"" + opened + "\" "
      + "}";
}


}