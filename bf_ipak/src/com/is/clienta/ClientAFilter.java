package com.is.clienta;

import java.io.Serializable;

public class ClientAFilter extends ClientA implements Serializable {

    static final long serialVersionUID = 3L;

    private String clientIds;

    public ClientAFilter() {
        super();
    }

    public ClientAFilter(String id_client, String state) {

        this.id_client = id_client;
        this.state = state;
    }

    private ClientAFilter(String clientIds) {
        this.clientIds = clientIds;
    }

    public static ClientAFilter clientIds(String clientIds) {
        return new ClientAFilter(clientIds);
    }

    public String getClientIds() {
        return clientIds;
    }

    public void clearFilterFields() {
        this.id=null;
        this.id_client=null;
        this.id_client_in_sap=null;
        this.name=null;
        this.code_type=null;
        this.code_form=null;
        this.date_open=null;
        this.date_close=null;
        this.state = null;
        this.j_short_name=null;
        this.j_code_tax_org=null;
        this.j_number_tax_registration=null;
        this.j_code_sector=null;
        this.j_opf=null;
        this.j_soato=null;
        this.j_okpo=null;
        this.j_type_activity=null;
        this.date_open1=null;
        this.date_close1=null;
        this.sign_date_open=0;
        this.sign_date_close=0;
        this.sign_registr="0";

    }

    @Override
    public String toString() {
        return "ClientJFilter{" +
                "clientIds='" + clientIds + '\'' +
                ", id=" + id +
                ", branch='" + branch + '\'' +
                ", id_client='" + id_client + '\'' +
                ", id_client_in_sap='" + id_client_in_sap + '\'' +
                ", name='" + name + '\'' +
                ", code_type='" + code_type + '\'' +
                ", code_subject='" + code_subject + '\'' +
                ", sign_registr=" + sign_registr +
                ", state='" + state + '\'' +
                '}';
    }
}
