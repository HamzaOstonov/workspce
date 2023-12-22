package com.is.delta;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class DELTARecord implements Cloneable {
    private long id;
    protected String idSAP;
    protected String client_id;
    protected String branch;
    protected String action_type;
    protected String id_sap;
    protected String state;
    protected Date v_date;
    protected String message;
    protected String name;
    protected Date date;
    protected int user_id;
    protected String customer_type;

    @Override
    public DELTARecord clone() throws CloneNotSupportedException {
        return (DELTARecord) super.clone();
    }
}
