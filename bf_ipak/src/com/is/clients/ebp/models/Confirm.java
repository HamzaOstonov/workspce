package com.is.clients.ebp.models;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;
//import com.fasterxml.jackson.ann

/**
 * Created by DEN on 27.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Confirm {
    private static final String SUCCESSFUL = "0";

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

    public void isSuccessful() {
        if (code != null && !code.equals(SUCCESSFUL))
            throw new ConfirmException(message);
    }

    private class ConfirmException extends RuntimeException{
        private final String message;

        public ConfirmException(String message){
            super(message);
            this.message = message;
        }

        @Override
        public String toString(){
            return message;
        }
    }
}
