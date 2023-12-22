package com.is.delta.di;

import com.is.customer_.contact.HandlerEnum;
import com.is.delta.Controller;
import com.is.delta.DELTARecord;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 24.04.2017.
 * 12:56
 */
public class ClientPListener implements EventListenerInterface {
    private final Controller controller;
    private final DELTARecord record;


    public ClientPListener(Controller includePage, DELTARecord record) {
        this.controller = includePage;
        this.record = record;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component component = event.getTarget().getPage().getFirstRoot();

        String src = "/customer/localcustomer.zul?" + "state=instant&" + HandlerEnum.ACTION + "="
                + HandlerEnum.ACTION_SHOW_LOCAL_CUSTOMER + "&" + HandlerEnum.SEARCH_CODE + "="
                + record.getClient_id() + "&" + HandlerEnum.SEARCH_BRANCH + "=" + record.getBranch();
        //controller.includeWnd.setVisible(true);
        controller.includeWnd.setVisible(true);
        controller.includeWnd$includePage.setSrc(src);
        controller.includeWnd.setClosable(true);
    }

    private Map<?,?> formMap(DELTARecord record) {
        return null;
    }
}
