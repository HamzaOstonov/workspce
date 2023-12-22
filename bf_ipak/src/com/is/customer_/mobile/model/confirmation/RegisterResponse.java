package com.is.customer_.mobile.model.confirmation;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Dev1 on 15.11.2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    public int res;
    public String err;

    public boolean isSuccessful(){
        return res == 0;
    }
}
