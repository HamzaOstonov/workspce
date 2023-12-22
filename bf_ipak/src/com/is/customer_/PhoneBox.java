package com.is.customer_;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Textbox;

public class PhoneBox extends Textbox {
    private static final long serialVersionUID = 1L;


    private boolean phonemasking;

    public PhoneBox() {
        super();
    }

    public boolean isPhonemasking() {
        return phonemasking;
    }

    public void setPhonemasking(boolean phonemasking) {
        if (phonemasking)
            setWidgetListener("onBind",
                    "jq(this).mask('+(999) 99 999 99 99');");
        this.phonemasking = phonemasking;
    }

    @Override
    public void setRawValue(Object object){
        if (phonemasking) {
            super.setRawValue(
                    coerceFromString(
                            applyMask(coerceToString(object))
                    ));
            return;
        }
        super.setRawValue(object);
    }
    @Override
    public String getText() throws WrongValueException {
        if (phonemasking)
            return super.getText().replaceAll("\\D+","");

        return super.getText();
    }

    private String applyMask(String value) {
        return isValid(value) ? format(value) : value;
    }

    private boolean isValid(String value) {
        return value != null && value.length() == 12;
    }

    private String format(String value) {
        return String.format("+(%s) %s %s %s %s",
                value.substring(0,3),
                value.substring(3,5),
                value.substring(5,8),
                value.substring(8,10),
                value.substring(10));
    }
}