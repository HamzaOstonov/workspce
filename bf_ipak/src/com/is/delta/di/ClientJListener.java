package com.is.delta.di;

import org.zkoss.zk.ui.event.Event;

import com.is.delta.Controller;
import com.is.delta.DELTARecord;

public class ClientJListener implements EventListenerInterface {

	private final Controller controller;
    private final DELTARecord record;


    public ClientJListener(Controller includePage, DELTARecord record) {
        this.controller = includePage;
        this.record = record;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        String src = null;
        if(record.getCustomer_type().equals("02")) {
        	src = "client_j.zul?mode=delta&client_id="+record.getClient_id();
        } else if(record.getCustomer_type().equals("06")) {
        	src = "client_j_founder_legal.zul?" + "mode=delta&" + "id="+record.getClient_id();
        }
        controller.includeWnd$includePage.setSrc(src);
        controller.includeWnd.setVisible(true);
        controller.includeWnd.setClosable(true);
    }

}
