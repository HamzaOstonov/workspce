package com.is.clients.ebp.models.individualdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.clients.ebp.models.Confirm;

/**
 * Created by DEN on 28.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualDetails {
    private Confirm confirm;
    private Client client;
    private Client_name client_name;
    private Client_address client_address;
    private Client_registration client_registration;
    private Client_static client_static;

    public IndividualDetails() {
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
}
