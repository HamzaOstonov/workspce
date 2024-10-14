package com.is.clients.ebp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 27.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Confirm {
    private String message;
    private String code;

    public Confirm() {
    }

    public Confirm(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
