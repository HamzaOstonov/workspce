package com.is.clients.ebp.models.legalentitydetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.is.clients.ebp.models.Confirm;

import lombok.ToString;
/**
 * Created by DEN on 30.03.2017.
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegalEntityDetails {
    private Confirm confirm;
    private Client client;
    private Client_name client_name;
    private Client_address client_address;
    private Client_registration client_registration;
    private Client_static client_static;
    private Client_passport client_passport;
    private Client_accountant client_accountant;
    private Client_capital client_capital;
    private Client_founders[] client_founders;

    public LegalEntityDetails() {
    }

    public Confirm getConfirm() {
        return confirm;
    }

    public void setConfirm(Confirm confirm) {
        this.confirm = confirm;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client_name getClient_name() {
        return client_name;
    }

    public void setClient_name(Client_name client_name) {
        this.client_name = client_name;
    }

    public Client_address getClient_address() {
        return client_address;
    }

    public void setClient_address(Client_address client_address) {
        this.client_address = client_address;
    }

    public Client_registration getClient_registration() {
        return client_registration;
    }

    public void setClient_registration(Client_registration client_registration) {
        this.client_registration = client_registration;
    }

    public Client_static getClient_static() {
        return client_static;
    }

    public void setClient_static(Client_static client_static) {
        this.client_static = client_static;
    }

    public Client_passport getClient_passport() {
        return client_passport;
    }

    public void setClient_passport(Client_passport client_passport) {
        this.client_passport = client_passport;
    }

    public Client_accountant getClient_accountant() {
        return client_accountant;
    }

    public void setClient_accountant(Client_accountant client_accountant) {
        this.client_accountant = client_accountant;
    }

    public Client_capital getClient_capital() {
        return client_capital;
    }

    public void setClient_capital(Client_capital client_capital) {
        this.client_capital = client_capital;
    }

    public Client_founders[] getClient_founders() {
        return client_founders;
    }

    public void setClient_founders(Client_founders[] client_founders) {
        this.client_founders = client_founders;
    }
}
