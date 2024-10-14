
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
    "common_data",
    "doc_data",
    "contacts",
    "address",
    "authentication_method"
})
@Generated("jsonschema2pojo")
public class Profile {

    @JsonProperty("common_data")
    private CommonData commonData;
    @JsonProperty("doc_data")
    private DocData docData;
    @JsonProperty("contacts")
    private Contacts contacts;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("authentication_method")
    private Object authenticationMethod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Profile() {
    }

    /**
     * 
     * @param address
     * @param authenticationMethod
     * @param commonData
     * @param docData
     * @param contacts
     */
    public Profile(CommonData commonData, DocData docData, Contacts contacts, Address address, Object authenticationMethod) {
        super();
        this.commonData = commonData;
        this.docData = docData;
        this.contacts = contacts;
        this.address = address;
        this.authenticationMethod = authenticationMethod;
    }

    @JsonProperty("common_data")
    public CommonData getCommonData() {
        return commonData;
    }

    @JsonProperty("common_data")
    public void setCommonData(CommonData commonData) {
        this.commonData = commonData;
    }

    @JsonProperty("doc_data")
    public DocData getDocData() {
        return docData;
    }

    @JsonProperty("doc_data")
    public void setDocData(DocData docData) {
        this.docData = docData;
    }

    @JsonProperty("contacts")
    public Contacts getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("authentication_method")
    public Object getAuthenticationMethod() {
        return authenticationMethod;
    }

    @JsonProperty("authentication_method")
    public void setAuthenticationMethod(Object authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
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
        sb.append(Profile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("commonData");
        sb.append('=');
        sb.append(((this.commonData == null)?"<null>":this.commonData));
        sb.append(',');
        sb.append("docData");
        sb.append('=');
        sb.append(((this.docData == null)?"<null>":this.docData));
        sb.append(',');
        sb.append("contacts");
        sb.append('=');
        sb.append(((this.contacts == null)?"<null>":this.contacts));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("authenticationMethod");
        sb.append('=');
        sb.append(((this.authenticationMethod == null)?"<null>":this.authenticationMethod));
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
