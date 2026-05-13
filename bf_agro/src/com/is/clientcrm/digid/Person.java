
package com.is.clientcrm.digid;

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
    "RequestGuid",
    "Pinpp",
    "SurnameC",
    "NameC",
    "PatronymC",
    "SurnameL",
    "NameL",
    "PatronymL",
    "SurnameE",
    "NameE",
    "BirthDate",
    "Sex",
    "SexName",
    "SexNameUz",
    "BirthCountry",
    "BirthCountryName",
    "BirthCountryNameUz",
    "BirthPlace",
    "Nationality",
    "NationalityName",
    "NationalityNameUz",
    "DocumentType",
    "DocumentTypeName",
    "DocumentTypeNameUz",
    "DocumentSerialNumber",
    "DocumentDateIssue",
    "DocumentDateValid",
    "DocumentIssuedBy",
    "PersonStatus",
    "PersonStatusValue",
    "Citizenship",
    "CitizenshipName",
    "CitizenshipNameUz",
    "Additional"
})
public class Person {

    @JsonProperty("RequestGuid")
    private String requestGuid;
    @JsonProperty("Pinpp")
    private String pinpp;
    @JsonProperty("SurnameC")
    private String surnameC;
    @JsonProperty("NameC")
    private String nameC;
    @JsonProperty("PatronymC")
    private String patronymC;
    @JsonProperty("SurnameL")
    private String surnameL;
    @JsonProperty("NameL")
    private String nameL;
    @JsonProperty("PatronymL")
    private String patronymL;
    @JsonProperty("SurnameE")
    private String surnameE;
    @JsonProperty("NameE")
    private String nameE;
    @JsonProperty("BirthDate")
    private String birthDate;
    @JsonProperty("Sex")
    private String sex;
    @JsonProperty("SexName")
    private Object sexName;
    @JsonProperty("SexNameUz")
    private Object sexNameUz;
    @JsonProperty("BirthCountry")
    private String birthCountry;
    @JsonProperty("BirthCountryName")
    private String birthCountryName;
    @JsonProperty("BirthCountryNameUz")
    private String birthCountryNameUz;
    @JsonProperty("BirthPlace")
    private String birthPlace;
    @JsonProperty("Nationality")
    private String nationality;
    @JsonProperty("NationalityName")
    private String nationalityName;
    @JsonProperty("NationalityNameUz")
    private String nationalityNameUz;
    @JsonProperty("DocumentType")
    private String documentType;
    @JsonProperty("DocumentTypeName")
    private String documentTypeName;
    @JsonProperty("DocumentTypeNameUz")
    private String documentTypeNameUz;
    @JsonProperty("DocumentSerialNumber")
    private String documentSerialNumber;
    @JsonProperty("DocumentDateIssue")
    private String documentDateIssue;
    @JsonProperty("DocumentDateValid")
    private String documentDateValid;
    @JsonProperty("DocumentIssuedBy")
    private String documentIssuedBy;
    @JsonProperty("PersonStatus")
    private Integer personStatus;
    @JsonProperty("PersonStatusValue")
    private String personStatusValue;
    @JsonProperty("Citizenship")
    private String citizenship;
    @JsonProperty("CitizenshipName")
    private String citizenshipName;
    @JsonProperty("CitizenshipNameUz")
    private String citizenshipNameUz;
    @JsonProperty("Additional")
    private Object additional;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Person() {
    }

    /**
     * 
     * @param personStatus
     * @param documentDateValid
     * @param birthCountryName
     * @param documentType
     * @param patronymL
     * @param nationalityName
     * @param additional
     * @param patronymC
     * @param pinpp
     * @param birthPlace
     * @param nationalityNameUz
     * @param documentDateIssue
     * @param sexName
     * @param personStatusValue
     * @param birthCountry
     * @param citizenshipName
     * @param nameL
     * @param documentTypeName
     * @param documentSerialNumber
     * @param nameE
     * @param nameC
     * @param birthCountryNameUz
     * @param sex
     * @param citizenship
     * @param surnameC
     * @param birthDate
     * @param citizenshipNameUz
     * @param surnameE
     * @param documentIssuedBy
     * @param sexNameUz
     * @param nationality
     * @param surnameL
     * @param documentTypeNameUz
     * @param requestGuid
     */
    public Person(String requestGuid, String pinpp, String surnameC, String nameC, String patronymC, String surnameL, String nameL, String patronymL, String surnameE, String nameE, String birthDate, String sex, Object sexName, Object sexNameUz, String birthCountry, String birthCountryName, String birthCountryNameUz, String birthPlace, String nationality, String nationalityName, String nationalityNameUz, String documentType, String documentTypeName, String documentTypeNameUz, String documentSerialNumber, String documentDateIssue, String documentDateValid, String documentIssuedBy, Integer personStatus, String personStatusValue, String citizenship, String citizenshipName, String citizenshipNameUz, Object additional) {
        super();
        this.requestGuid = requestGuid;
        this.pinpp = pinpp;
        this.surnameC = surnameC;
        this.nameC = nameC;
        this.patronymC = patronymC;
        this.surnameL = surnameL;
        this.nameL = nameL;
        this.patronymL = patronymL;
        this.surnameE = surnameE;
        this.nameE = nameE;
        this.birthDate = birthDate;
        this.sex = sex;
        this.sexName = sexName;
        this.sexNameUz = sexNameUz;
        this.birthCountry = birthCountry;
        this.birthCountryName = birthCountryName;
        this.birthCountryNameUz = birthCountryNameUz;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.nationalityName = nationalityName;
        this.nationalityNameUz = nationalityNameUz;
        this.documentType = documentType;
        this.documentTypeName = documentTypeName;
        this.documentTypeNameUz = documentTypeNameUz;
        this.documentSerialNumber = documentSerialNumber;
        this.documentDateIssue = documentDateIssue;
        this.documentDateValid = documentDateValid;
        this.documentIssuedBy = documentIssuedBy;
        this.personStatus = personStatus;
        this.personStatusValue = personStatusValue;
        this.citizenship = citizenship;
        this.citizenshipName = citizenshipName;
        this.citizenshipNameUz = citizenshipNameUz;
        this.additional = additional;
    }

    @JsonProperty("RequestGuid")
    public String getRequestGuid() {
        return requestGuid;
    }

    @JsonProperty("RequestGuid")
    public void setRequestGuid(String requestGuid) {
        this.requestGuid = requestGuid;
    }

    @JsonProperty("Pinpp")
    public String getPinpp() {
        return pinpp;
    }

    @JsonProperty("Pinpp")
    public void setPinpp(String pinpp) {
        this.pinpp = pinpp;
    }

    @JsonProperty("SurnameC")
    public String getSurnameC() {
        return surnameC;
    }

    @JsonProperty("SurnameC")
    public void setSurnameC(String surnameC) {
        this.surnameC = surnameC;
    }

    @JsonProperty("NameC")
    public String getNameC() {
        return nameC;
    }

    @JsonProperty("NameC")
    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    @JsonProperty("PatronymC")
    public String getPatronymC() {
        return patronymC;
    }

    @JsonProperty("PatronymC")
    public void setPatronymC(String patronymC) {
        this.patronymC = patronymC;
    }

    @JsonProperty("SurnameL")
    public String getSurnameL() {
        return surnameL;
    }

    @JsonProperty("SurnameL")
    public void setSurnameL(String surnameL) {
        this.surnameL = surnameL;
    }

    @JsonProperty("NameL")
    public String getNameL() {
        return nameL;
    }

    @JsonProperty("NameL")
    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    @JsonProperty("PatronymL")
    public String getPatronymL() {
        return patronymL;
    }

    @JsonProperty("PatronymL")
    public void setPatronymL(String patronymL) {
        this.patronymL = patronymL;
    }

    @JsonProperty("SurnameE")
    public String getSurnameE() {
        return surnameE;
    }

    @JsonProperty("SurnameE")
    public void setSurnameE(String surnameE) {
        this.surnameE = surnameE;
    }

    @JsonProperty("NameE")
    public String getNameE() {
        return nameE;
    }

    @JsonProperty("NameE")
    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    @JsonProperty("BirthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("BirthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("Sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("Sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("SexName")
    public Object getSexName() {
        return sexName;
    }

    @JsonProperty("SexName")
    public void setSexName(Object sexName) {
        this.sexName = sexName;
    }

    @JsonProperty("SexNameUz")
    public Object getSexNameUz() {
        return sexNameUz;
    }

    @JsonProperty("SexNameUz")
    public void setSexNameUz(Object sexNameUz) {
        this.sexNameUz = sexNameUz;
    }

    @JsonProperty("BirthCountry")
    public String getBirthCountry() {
        return birthCountry;
    }

    @JsonProperty("BirthCountry")
    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    @JsonProperty("BirthCountryName")
    public String getBirthCountryName() {
        return birthCountryName;
    }

    @JsonProperty("BirthCountryName")
    public void setBirthCountryName(String birthCountryName) {
        this.birthCountryName = birthCountryName;
    }

    @JsonProperty("BirthCountryNameUz")
    public String getBirthCountryNameUz() {
        return birthCountryNameUz;
    }

    @JsonProperty("BirthCountryNameUz")
    public void setBirthCountryNameUz(String birthCountryNameUz) {
        this.birthCountryNameUz = birthCountryNameUz;
    }

    @JsonProperty("BirthPlace")
    public String getBirthPlace() {
        return birthPlace;
    }

    @JsonProperty("BirthPlace")
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @JsonProperty("Nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("Nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("NationalityName")
    public String getNationalityName() {
        return nationalityName;
    }

    @JsonProperty("NationalityName")
    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    @JsonProperty("NationalityNameUz")
    public String getNationalityNameUz() {
        return nationalityNameUz;
    }

    @JsonProperty("NationalityNameUz")
    public void setNationalityNameUz(String nationalityNameUz) {
        this.nationalityNameUz = nationalityNameUz;
    }

    @JsonProperty("DocumentType")
    public String getDocumentType() {
        return documentType;
    }

    @JsonProperty("DocumentType")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("DocumentTypeName")
    public String getDocumentTypeName() {
        return documentTypeName;
    }

    @JsonProperty("DocumentTypeName")
    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    @JsonProperty("DocumentTypeNameUz")
    public String getDocumentTypeNameUz() {
        return documentTypeNameUz;
    }

    @JsonProperty("DocumentTypeNameUz")
    public void setDocumentTypeNameUz(String documentTypeNameUz) {
        this.documentTypeNameUz = documentTypeNameUz;
    }

    @JsonProperty("DocumentSerialNumber")
    public String getDocumentSerialNumber() {
        return documentSerialNumber;
    }

    @JsonProperty("DocumentSerialNumber")
    public void setDocumentSerialNumber(String documentSerialNumber) {
        this.documentSerialNumber = documentSerialNumber;
    }

    @JsonProperty("DocumentDateIssue")
    public String getDocumentDateIssue() {
        return documentDateIssue;
    }

    @JsonProperty("DocumentDateIssue")
    public void setDocumentDateIssue(String documentDateIssue) {
        this.documentDateIssue = documentDateIssue;
    }

    @JsonProperty("DocumentDateValid")
    public String getDocumentDateValid() {
        return documentDateValid;
    }

    @JsonProperty("DocumentDateValid")
    public void setDocumentDateValid(String documentDateValid) {
        this.documentDateValid = documentDateValid;
    }

    @JsonProperty("DocumentIssuedBy")
    public String getDocumentIssuedBy() {
        return documentIssuedBy;
    }

    @JsonProperty("DocumentIssuedBy")
    public void setDocumentIssuedBy(String documentIssuedBy) {
        this.documentIssuedBy = documentIssuedBy;
    }

    @JsonProperty("PersonStatus")
    public Integer getPersonStatus() {
        return personStatus;
    }

    @JsonProperty("PersonStatus")
    public void setPersonStatus(Integer personStatus) {
        this.personStatus = personStatus;
    }

    @JsonProperty("PersonStatusValue")
    public String getPersonStatusValue() {
        return personStatusValue;
    }

    @JsonProperty("PersonStatusValue")
    public void setPersonStatusValue(String personStatusValue) {
        this.personStatusValue = personStatusValue;
    }

    @JsonProperty("Citizenship")
    public String getCitizenship() {
        return citizenship;
    }

    @JsonProperty("Citizenship")
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @JsonProperty("CitizenshipName")
    public String getCitizenshipName() {
        return citizenshipName;
    }

    @JsonProperty("CitizenshipName")
    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    @JsonProperty("CitizenshipNameUz")
    public String getCitizenshipNameUz() {
        return citizenshipNameUz;
    }

    @JsonProperty("CitizenshipNameUz")
    public void setCitizenshipNameUz(String citizenshipNameUz) {
        this.citizenshipNameUz = citizenshipNameUz;
    }

    @JsonProperty("Additional")
    public Object getAdditional() {
        return additional;
    }

    @JsonProperty("Additional")
    public void setAdditional(Object additional) {
        this.additional = additional;
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
