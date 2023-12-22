package com.is.customer_.contact;

import java.util.Map;

/**
 * Created by root on 11.05.2017.
 * 11:16
 */
public class ContactPersonContext {
    private final Map<String,?> param;

    private ContactPersonContext(Map param){
        this.param = param;
    }

    public static ContactPersonContext getInstance(Map param){
        return new ContactPersonContext(param);
    }

    public boolean isCreateAction() {
        String[] action = (String[]) param.get(HandlerEnum.ACTION);
        if (action != null && action.length > 0 &&
                action[0].equalsIgnoreCase(HandlerEnum.ACTION_CREATE_CONTACT_PERSON))
            return true;
        return false;
    }

    public boolean isSAPCustomer(){
        return parseArray(HandlerEnum.CONTACT_SAP_ID) != null ? true : false;
    }

    public String getIDSap(){
        return parseArray(HandlerEnum.CONTACT_SAP_ID);
    }

    private String parseArray(String key) {
        if (!isArrayEmpty((String[]) param.get(key)))
            return ((String[]) param.get(key))[0];
        return null;
    }

    private boolean isArrayEmpty(String[] array) {
        if (array == null || array.length == 0 || array[0] == null)
            return true;
        return false;
    }


    public String getContactPersonBranch() {
        return parseArray(HandlerEnum.CONTACT_PERSON_BRANCH);
    }

    public String getContactPersonId() {
        return parseArray(HandlerEnum.CONTACT_PERSON_ID);
    }

    public String getContactJId() {
        return parseArray(HandlerEnum.CONTACT_CLIENTJID);
    }

    public Map<?,?> getParams(){
        return this.param;
    }

    public String getPosition() {
        return parseArray(HandlerEnum.CONTACT_POSITION);
    }

    public String getInnJ() {
        return parseArray(HandlerEnum.CONTACT_INN);
    }
}
