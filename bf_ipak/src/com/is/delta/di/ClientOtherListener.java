package com.is.delta.di;

import com.is.customer_.contact.HandlerEnum;
import com.is.delta.Controller;
import com.is.delta.DELTARecord;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

/**
 * Created by root on 14.06.2017.
 * 17:20
 */
public class ClientOtherListener implements EventListenerInterface{
    private final Controller controller;
    private final DELTARecord record;

    public ClientOtherListener(Controller controller, DELTARecord record){
        this.controller = controller;
        this.record = record;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component component = event.getTarget().getPage().getFirstRoot();

        String src = "/customer/contactperson.zul?" + "state=instant&" + HandlerEnum.ACTION + "="
                + HandlerEnum.ACTION_SHOW_LOCAL_CUSTOMER + "&" + HandlerEnum.SEARCH_CODE + "="
                + record.getClient_id() + "&" + HandlerEnum.SEARCH_BRANCH + "=" + record.getBranch();
        //controller.includeWnd.setVisible(true);
        controller.includeWnd.setVisible(true);
        controller.includeWnd$includePage.setSrc(src);
        controller.includeWnd.setClosable(true);
    }
}
