
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
    "pass_data",
    "issued_by",
    "issued_by_id",
    "issued_date",
    "expiry_date",
    "doc_type",
    "doc_type_id",
    "doc_type_id_cbu"
})
@Generated("jsonschema2pojo")
public class DocData {

    @JsonProperty("pass_data")
    private String passData;
    @JsonProperty("issued_by")
    private String issuedBy;
    @JsonProperty("issued_by_id")
    private String issuedById;
    @JsonProperty("issued_date")
    private String issuedDate;
    @JsonProperty("expiry_date")
    private String expiryDate;
    @JsonProperty("doc_type")
    private String docType;
    @JsonProperty("doc_type_id")
    private String docTypeId;
    @JsonProperty("doc_type_id_cbu")
    private String docTypeIdCbu;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DocData() {
    }

    /**
     * 
     * @param expiryDate
     * @param issuedDate
     * @param docType
     * @param docTypeId
     * @param issuedBy
     * @param docTypeIdCbu
     * @param passData
     * @param issuedById
     */
    public DocData(String passData, String issuedBy, String issuedById, String issuedDate, String expiryDate, String docType, String docTypeId, String docTypeIdCbu) {
        super();
        this.passData = passData;
        this.issuedBy = issuedBy;
        this.issuedById = issuedById;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.docType = docType;
        this.docTypeId = docTypeId;
        this.docTypeIdCbu = docTypeIdCbu;
    }

    @JsonProperty("pass_data")
    public String getPassData() {
        return passData;
    }

    @JsonProperty("pass_data")
    public void setPassData(String passData) {
        this.passData = passData;
    }

    @JsonProperty("issued_by")
    public String getIssuedBy() {
        return issuedBy;
    }

    @JsonProperty("issued_by")
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    @JsonProperty("issued_by_id")
    public String getIssuedById() {
        return issuedById;
    }

    @JsonProperty("issued_by_id")
    public void setIssuedById(String issuedById) {
        this.issuedById = issuedById;
    }

    @JsonProperty("issued_date")
    public String getIssuedDate() {
        return issuedDate;
    }

    @JsonProperty("issued_date")
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    @JsonProperty("expiry_date")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("expiry_date")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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
        sb.append(DocData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("passData");
        sb.append('=');
        sb.append(((this.passData == null)?"<null>":this.passData));
        sb.append(',');
        sb.append("issuedBy");
        sb.append('=');
        sb.append(((this.issuedBy == null)?"<null>":this.issuedBy));
        sb.append(',');
        sb.append("issuedById");
        sb.append('=');
        sb.append(((this.issuedById == null)?"<null>":this.issuedById));
        sb.append(',');
        sb.append("issuedDate");
        sb.append('=');
        sb.append(((this.issuedDate == null)?"<null>":this.issuedDate));
        sb.append(',');
        sb.append("expiryDate");
        sb.append('=');
        sb.append(((this.expiryDate == null)?"<null>":this.expiryDate));
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
