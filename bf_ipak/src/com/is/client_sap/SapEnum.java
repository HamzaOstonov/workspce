package com.is.client_sap;

import java.util.HashMap;
import java.util.Map;


public enum SapEnum {
    CLIENT_CUST("1"),
    CLIENT_ORG("2"),
    DOC_TYPE_INN("51"),
    DOC_TYPE_REG("52"),
    DOC_TYPE_NIBBD("53"),
    ROLE_EVENT_FOUNDER_LE("BP50", "J"),
    ROLE_EVENT_FOUNDER_I("BP50", "P"),
    ROLE_EVENT_IP("BP45"),
    REL_TYPE_CONTACT("BUR001"),
    REL_TYPE_DIRECTOR("ZIY050", "1"),
    REL_TYPE_ACCOUNTANT("ZIY051", "2"),
    REL_TYPE_FOUNDER("BURC01", "3"),
    REL_TYPE_IP("ZIY006", "4"),
    POSITION_DIR("0001"),
    POSITION_ACC("0002"),
    PAFKT_IP("ZIY006"),
    PAFKT_DIR("0001", "1"),
    PAFKT_ACC("0002", "2"),
    ROLE_CLIENT_NO_ACCOUNT("ZIY001"),
    UN_PROP("SAP_WS_ENDPOINT_USERNAME"),
    PW_PROP("SAP_WS_ENDPOINT_PASSWORD"),
    ORG_REQ("SAP_ORG_ENDPOINT"),
    CUSTOMER_REQ("SAP_CUST_ENDPOINT"),
    RELATION_REQ("SAP_REL_ENDPOINT"),
    CONTENT_SERVER("SAP_CONTENT_SERVER"),
    CONTENT_DELETE_SERVER("SAP_CONTENTDELETE_SRV"),    
    ROLE_REQ("SAP_ROLE_ENDPOINT");


    private String sapValue;
    private String nci;
    private static Map<String, SapEnum> lookup = new HashMap<String, SapEnum>();
    private static Map<String, SapEnum> nciSap = new HashMap<String, SapEnum>();

    static {
        for (SapEnum item : SapEnum.values()) {
            lookup.put(item.getSapValue(), item);
        }
        for (SapEnum item : SapEnum.values()) {
            nciSap.put(item.getNci(), item);
        }
    }

    private SapEnum(String sapValue) {
        this.sapValue = sapValue;
    }

    private SapEnum(String sapValue, String nci) {
        this.sapValue = sapValue;
        this.nci = nci;
    }

    public String getSapValue() {
        return sapValue;
    }

    public String getNci() {
        return nci;
    }

    public static SapEnum getEnum(String value) {
        return lookup.get(value);
    }

    public static SapEnum getByNciKey(String ncikey) {
        return nciSap.get(ncikey);
    }

    public static String getNci(String value) {
        SapEnum en = getEnum(value);
        return en != null ? en.getNci() : null;
    }

    public static void main(String[] args) {
        System.out.println(getNci("0002"));
    }
}
