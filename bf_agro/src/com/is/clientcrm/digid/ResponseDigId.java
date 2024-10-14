
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
    "answere",
    "DocumentReadingTime",
    "ServiceAnswereTime",
    "Person",
    "Address",
    "AddressTemproary",
    "Contacts",
    "Additional",
    "modelMRZ",
    "modelPersonPassport",
    "modelPersonIdCard",
    "Photo"
})
public class ResponseDigId {

    @JsonProperty("answere")
    private Answere answere;
    @JsonProperty("DocumentReadingTime")
    private Double documentReadingTime;
    @JsonProperty("ServiceAnswereTime")
    private Double serviceAnswereTime;
    @JsonProperty("Person")
    private Person person;
    @JsonProperty("Address")
    private Address address;
    @JsonProperty("AddressTemproary")
    private AddressTemproary addressTemproary;
    @JsonProperty("Contacts")
    private Object contacts;
    @JsonProperty("Additional")
    private Additional additional;
    @JsonProperty("modelMRZ")
    private ModelMRZ modelMRZ;
    @JsonProperty("modelPersonPassport")
    private ModelPersonPassport modelPersonPassport;
    @JsonProperty("modelPersonIdCard")
    private Object modelPersonIdCard;
    @JsonProperty("Photo")
    private Photo photo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseDigId() {
    }

    /**
     * 
     * @param addressTemproary
     * @param serviceAnswereTime
     * @param address
     * @param modelMRZ
     * @param documentReadingTime
     * @param person
     * @param additional
     * @param photo
     * @param answere
     * @param modelPersonIdCard
     * @param modelPersonPassport
     * @param contacts
     */
    public ResponseDigId(Answere answere, Double documentReadingTime, Double serviceAnswereTime, Person person, Address address, AddressTemproary addressTemproary, Object contacts, Additional additional, ModelMRZ modelMRZ, ModelPersonPassport modelPersonPassport, Object modelPersonIdCard, Photo photo) {
        super();
        this.answere = answere;
        this.documentReadingTime = documentReadingTime;
        this.serviceAnswereTime = serviceAnswereTime;
        this.person = person;
        this.address = address;
        this.addressTemproary = addressTemproary;
        this.contacts = contacts;
        this.additional = additional;
        this.modelMRZ = modelMRZ;
        this.modelPersonPassport = modelPersonPassport;
        this.modelPersonIdCard = modelPersonIdCard;
        this.photo = photo;
    }

    @JsonProperty("answere")
    public Answere getAnswere() {
        return answere;
    }

    @JsonProperty("answere")
    public void setAnswere(Answere answere) {
        this.answere = answere;
    }

    @JsonProperty("DocumentReadingTime")
    public Double getDocumentReadingTime() {
        return documentReadingTime;
    }

    @JsonProperty("DocumentReadingTime")
    public void setDocumentReadingTime(Double documentReadingTime) {
        this.documentReadingTime = documentReadingTime;
    }

    @JsonProperty("ServiceAnswereTime")
    public Double getServiceAnswereTime() {
        return serviceAnswereTime;
    }

    @JsonProperty("ServiceAnswereTime")
    public void setServiceAnswereTime(Double serviceAnswereTime) {
        this.serviceAnswereTime = serviceAnswereTime;
    }

    @JsonProperty("Person")
    public Person getPerson() {
        return person;
    }

    @JsonProperty("Person")
    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonProperty("Address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("AddressTemproary")
    public AddressTemproary getAddressTemproary() {
        return addressTemproary;
    }

    @JsonProperty("AddressTemproary")
    public void setAddressTemproary(AddressTemproary addressTemproary) {
        this.addressTemproary = addressTemproary;
    }

    @JsonProperty("Contacts")
    public Object getContacts() {
        return contacts;
    }

    @JsonProperty("Contacts")
    public void setContacts(Object contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("Additional")
    public Additional getAdditional() {
        return additional;
    }

    @JsonProperty("Additional")
    public void setAdditional(Additional additional) {
        this.additional = additional;
    }

    @JsonProperty("modelMRZ")
    public ModelMRZ getModelMRZ() {
        return modelMRZ;
    }

    @JsonProperty("modelMRZ")
    public void setModelMRZ(ModelMRZ modelMRZ) {
        this.modelMRZ = modelMRZ;
    }

    @JsonProperty("modelPersonPassport")
    public ModelPersonPassport getModelPersonPassport() {
        return modelPersonPassport;
    }

    @JsonProperty("modelPersonPassport")
    public void setModelPersonPassport(ModelPersonPassport modelPersonPassport) {
        this.modelPersonPassport = modelPersonPassport;
    }

    @JsonProperty("modelPersonIdCard")
    public Object getModelPersonIdCard() {
        return modelPersonIdCard;
    }

    @JsonProperty("modelPersonIdCard")
    public void setModelPersonIdCard(Object modelPersonIdCard) {
        this.modelPersonIdCard = modelPersonIdCard;
    }

    @JsonProperty("Photo")
    public Photo getPhoto() {
        return photo;
    }

    @JsonProperty("Photo")
    public void setPhoto(Photo photo) {
        this.photo = photo;
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
