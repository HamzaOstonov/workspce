package com.is.customer_;

import org.zkoss.zul.Datebox;

/**
 * Created by root on 17.06.2017.
 * 13:09
 */
public class ExtendedDatebox extends Datebox {
    private boolean datemasking;

    public ExtendedDatebox() {
        super();
    }

    public boolean isDatemasking() {
        return datemasking;
    }

    public void setDatemasking(boolean datemasking) {
        if (datemasking)
            setWidgetListener("onBind",
                    "$.mask.definitions['A']='[A-Z]';" +
                            "jq.mask.definitions['m']='[01]';" +
                            "jq.mask.definitions['d']='[0123]';" +
                            "jq.mask.definitions['y']='[12]';" +
                            "jq(this).mask('9');");
        //setWidgetListener("onBind", "jq(this).mask('99.99.9999');");
        this.datemasking = datemasking;
    }

}
