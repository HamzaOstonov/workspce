
package com.is.identification;

import java.util.HashMap;
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
    "first_name",
    "first_name_en",
    "middle_name",
    "last_name",
    "last_name_en",
    "pinfl",
    "inn",
    "gender",
    "birth_place",
    "birth_country",
    "birth_country_id",
    "birth_country_id_cbu",
    "birth_date",
    "nationality",
    "nationality_id",
    "nationality_id_cbu",
    "citizenship",
    "citizenship_id",
    "citizenship_id_cbu",
    "doc_type",
    "doc_type_id",
    "doc_type_id_cbu",
    "sdk_hash",
    "last_update_pass_data",
    "last_update_inn",
    "last_update_address"
})
@Generated("jsonschema2pojo")
public class CommonData {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("first_name_en")
    private Object firstNameEn;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("last_name_en")
    private Object lastNameEn;
    @JsonProperty("pinfl")
    private String pinfl;
    @JsonProperty("inn")
    private String inn;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("birth_place")
    private String birthPlace;
    @JsonProperty("birth_country")
    private String birthCountry;
    @JsonProperty("birth_country_id")
    private String birthCountryId;
    @JsonProperty("birth_country_id_cbu")
    private String birthCountryIdCbu;
    @JsonProperty("birth_date")
    private String birthDate;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("nationality_id")
    private String nationalityId;
    @JsonProperty("nationality_id_cbu")
    private String nationalityIdCbu;
    @JsonProperty("citizenship")
    private String citizenship;
    @JsonProperty("citizenship_id")
    private String citizenshipId;
    @JsonProperty("citizenship_id_cbu")
    private String citizenshipIdCbu;
    @JsonProperty("doc_type")
    private String docType;
    @JsonProperty("doc_type_id")
    private String docTypeId;
    @JsonProperty("doc_type_id_cbu")
    private String docTypeIdCbu;
    @JsonProperty("sdk_hash")
    private String sdkHash;
    @JsonProperty("last_update_pass_data")
    private String lastUpdatePassData;
    @JsonProperty("last_update_inn")
    private String lastUpdateInn;
    @JsonProperty("last_update_address")
    private String lastUpdateAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CommonData() {
    }

    /**
     * 
     * @param pinfl
     * @param lastName
     * @param gender
     * @param lastUpdatePassData
     * @param lastNameEn
     * @param citizenshipId
     * @param birthPlace
     * @param docTypeId
     * @param birthCountry
     * @param nationalityIdCbu
     * @param citizenshipIdCbu
     * @param birthCountryId
     * @param birthCountryIdCbu
     * @param docType
     * @param nationalityId
     * @param citizenship
     * @param inn
     * @param birthDate
     * @param firstName
     * @param firstNameEn
     * @param nationality
     * @param docTypeIdCbu
     * @param lastUpdateInn
     * @param middleName
     * @param lastUpdateAddress
     * @param sdkHash
     */
    public CommonData(String firstName, Object firstNameEn, String middleName, String lastName, Object lastNameEn, String pinfl, String inn, String gender, String birthPlace, String birthCountry, String birthCountryId, String birthCountryIdCbu, String birthDate, String nationality, String nationalityId, String nationalityIdCbu, String citizenship, String citizenshipId, String citizenshipIdCbu, String docType, String docTypeId, String docTypeIdCbu, String sdkHash, String lastUpdatePassData, String lastUpdateInn, String lastUpdateAddress) {
        super();
        this.firstName = firstName;
        this.firstNameEn = firstNameEn;
        this.middleName = middleName;
        this.lastName = lastName;
        this.lastNameEn = lastNameEn;
        this.pinfl = pinfl;
        this.inn = inn;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.birthCountry = birthCountry;
        this.birthCountryId = birthCountryId;
        this.birthCountryIdCbu = birthCountryIdCbu;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.nationalityId = nationalityId;
        this.nationalityIdCbu = nationalityIdCbu;
        this.citizenship = citizenship;
        this.citizenshipId = citizenshipId;
        this.citizenshipIdCbu = citizenshipIdCbu;
        this.docType = docType;
        this.docTypeId = docTypeId;
        this.docTypeIdCbu = docTypeIdCbu;
        this.sdkHash = sdkHash;
        this.lastUpdatePassData = lastUpdatePassData;
        this.lastUpdateInn = lastUpdateInn;
        this.lastUpdateAddress = lastUpdateAddress;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("first_name_en")
    public Object getFirstNameEn() {
        return firstNameEn;
    }

    @JsonProperty("first_name_en")
    public void setFirstNameEn(Object firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    @JsonProperty("middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middle_name")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("last_name_en")
    public Object getLastNameEn() {
        return lastNameEn;
    }

    @JsonProperty("last_name_en")
    public void setLastNameEn(Object lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    @JsonProperty("pinfl")
    public String getPinfl() {
        return pinfl;
    }

    @JsonProperty("pinfl")
    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    @JsonProperty("inn")
    public String getInn() {
        return inn;
    }

    @JsonProperty("inn")
    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("birth_place")
    public String getBirthPlace() {
        return birthPlace;
    }

    @JsonProperty("birth_place")
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @JsonProperty("birth_country")
    public String getBirthCountry() {
        return birthCountry;
    }

    @JsonProperty("birth_country")
    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    @JsonProperty("birth_country_id")
    public String getBirthCountryId() {
        return birthCountryId;
    }

    @JsonProperty("birth_country_id")
    public void setBirthCountryId(String birthCountryId) {
        this.birthCountryId = birthCountryId;
    }

    @JsonProperty("birth_country_id_cbu")
    public String getBirthCountryIdCbu() {
        return birthCountryIdCbu;
    }

    @JsonProperty("birth_country_id_cbu")
    public void setBirthCountryIdCbu(String birthCountryIdCbu) {
        this.birthCountryIdCbu = birthCountryIdCbu;
    }

    @JsonProperty("birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birth_date")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("nationality_id")
    public String getNationalityId() {
        return nationalityId;
    }

    @JsonProperty("nationality_id")
    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    @JsonProperty("nationality_id_cbu")
    public String getNationalityIdCbu() {
        return nationalityIdCbu;
    }

    @JsonProperty("nationality_id_cbu")
    public void setNationalityIdCbu(String nationalityIdCbu) {
        this.nationalityIdCbu = nationalityIdCbu;
    }

    @JsonProperty("citizenship")
    public String getCitizenship() {
        return citizenship;
    }

    @JsonProperty("citizenship")
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @JsonProperty("citizenship_id")
    public String getCitizenshipId() {
        return citizenshipId;
    }

    @JsonProperty("citizenship_id")
    public void setCitizenshipId(String citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    @JsonProperty("citizenship_id_cbu")
    public String getCitizenshipIdCbu() {
        return citizenshipIdCbu;
    }

    @JsonProperty("citizenship_id_cbu")
    public void setCitizenshipIdCbu(String citizenshipIdCbu) {
        this.citizenshipIdCbu = citizenshipIdCbu;
    }

    @JsonProperty("doc_type")
    public String getDocType() {
        return docType;
    }

    @JsonProperty("doc_type")
    public void setDocType(String docType) {
        this.docType = docType;
    }

    @JsonProperty("doc_type_id")
    public String getDocTypeId() {
        return docTypeId;
    }

    @JsonProperty("doc_type_id")
    public void setDocTypeId(String docTypeId) {
        this.docTypeId = docTypeId;
    }

    @JsonProperty("doc_type_id_cbu")
    public String getDocTypeIdCbu() {
        return docTypeIdCbu;
    }

    @JsonProperty("doc_type_id_cbu")
    public void setDocTypeIdCbu(String docTypeIdCbu) {
        this.docTypeIdCbu = docTypeIdCbu;
    }

    @JsonProperty("sdk_hash")
    public String getSdkHash() {
        return sdkHash;
    }

    @JsonProperty("sdk_hash")
    public void setSdkHash(String sdkHash) {
        this.sdkHash = sdkHash;
    }

    @JsonProperty("last_update_pass_data")
    public String getLastUpdatePassData() {
        return lastUpdatePassData;
    }

    @JsonProperty("last_update_pass_data")
    public void setLastUpdatePassData(String lastUpdatePassData) {
        this.lastUpdatePassData = lastUpdatePassData;
    }

    @JsonProperty("last_update_inn")
    public String getLastUpdateInn() {
        return lastUpdateInn;
    }

    @JsonProperty("last_update_inn")
    public void setLastUpdateInn(String lastUpdateInn) {
        this.lastUpdateInn = lastUpdateInn;
    }

    @JsonProperty("last_update_address")
    public String getLastUpdateAddress() {
        return lastUpdateAddress;
    }

    @JsonProperty("last_update_address")
    public void setLastUpdateAddress(String lastUpdateAddress) {
        this.lastUpdateAddress = lastUpdateAddress;
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
        StringBuilder sb = new StringBuilder();
        sb.append(CommonData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("firstNameEn");
        sb.append('=');
        sb.append(((this.firstNameEn == null)?"<null>":this.firstNameEn));
        sb.append(',');
        sb.append("middleName");
        sb.append('=');
        sb.append(((this.middleName == null)?"<null>":this.middleName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("lastNameEn");
        sb.append('=');
        sb.append(((this.lastNameEn == null)?"<null>":this.lastNameEn));
        sb.append(',');
        sb.append("pinfl");
        sb.append('=');
        sb.append(((this.pinfl == null)?"<null>":this.pinfl));
        sb.append(',');
        sb.append("inn");
        sb.append('=');
        sb.append(((this.inn == null)?"<null>":this.inn));
        sb.append(',');
        sb.append("gender");
        sb.append('=');
        sb.append(((this.gender == null)?"<null>":this.gender));
        sb.append(',');
        sb.append("birthPlace");
        sb.append('=');
        sb.append(((this.birthPlace == null)?"<null>":this.birthPlace));
        sb.append(',');
        sb.append("birthCountry");
        sb.append('=');
        sb.append(((this.birthCountry == null)?"<null>":this.birthCountry));
        sb.append(',');
        sb.append("birthCountryId");
        sb.append('=');
        sb.append(((this.birthCountryId == null)?"<null>":this.birthCountryId));
        sb.append(',');
        sb.append("birthCountryIdCbu");
        sb.append('=');
        sb.append(((this.birthCountryIdCbu == null)?"<null>":this.birthCountryIdCbu));
        sb.append(',');
        sb.append("birthDate");
        sb.append('=');
        sb.append(((this.birthDate == null)?"<null>":this.birthDate));
        sb.append(',');
        sb.append("nationality");
        sb.append('=');
        sb.append(((this.nationality == null)?"<null>":this.nationality));
        sb.append(',');
        sb.append("nationalityId");
        sb.append('=');
        sb.append(((this.nationalityId == null)?"<null>":this.nationalityId));
        sb.append(',');
        sb.append("nationalityIdCbu");
        sb.append('=');
        sb.append(((this.nationalityIdCbu == null)?"<null>":this.nationalityIdCbu));
        sb.append(',');
        sb.append("citizenship");
        sb.append('=');
        sb.append(((this.citizenship == null)?"<null>":this.citizenship));
        sb.append(',');
        sb.append("citizenshipId");
        sb.append('=');
        sb.append(((this.citizenshipId == null)?"<null>":this.citizenshipId));
        sb.append(',');
        sb.append("citizenshipIdCbu");
        sb.append('=');
        sb.append(((this.citizenshipIdCbu == null)?"<null>":this.citizenshipIdCbu));
        sb.append(',');
        sb.append("docType");
        sb.append('=');
        sb.append(((this.docType == null)?"<null>":this.docType));
        sb.append(',');
        sb.append("docTypeId");
        sb.append('=');
        sb.append(((this.docTypeId == null)?"<null>":this.docTypeId));
        sb.append(',');
        sb.append("docTypeIdCbu");
        sb.append('=');
        sb.append(((this.docTypeIdCbu == null)?"<null>":this.docTypeIdCbu));
        sb.append(',');
        sb.append("sdkHash");
        sb.append('=');
        sb.append(((this.sdkHash == null)?"<null>":this.sdkHash));
        sb.append(',');
        sb.append("lastUpdatePassData");
        sb.append('=');
        sb.append(((this.lastUpdatePassData == null)?"<null>":this.lastUpdatePassData));
        sb.append(',');
        sb.append("lastUpdateInn");
        sb.append('=');
        sb.append(((this.lastUpdateInn == null)?"<null>":this.lastUpdateInn));
        sb.append(',');
        sb.append("lastUpdateAddress");
        sb.append('=');
        sb.append(((this.lastUpdateAddress == null)?"<null>":this.lastUpdateAddress));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
