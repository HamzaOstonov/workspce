package com.is.clients.ebp.models.legalentitydetails;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * Created by DEN on 12.04.2017.
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_capital {
    private String capital_info;

    public Client_capital(String capital_info) {
        this.capital_info = capital_info;
    }

    public Client_capital() {
    }

    public String getCapital_info() {
        return capital_info;
    }

    public void setCapital_info(String capital_info) {
        this.capital_info = capital_info;
    }
}
