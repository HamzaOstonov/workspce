package com.is.customer_.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Dev1 on 14.11.2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileBankClient {
    private String name;
    private String branch;
    private String client_id;

    public MobileBankClient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "MobileBankClient{" +
                "name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", client_id='" + client_id + '\'' +
                '}';
    }
}
