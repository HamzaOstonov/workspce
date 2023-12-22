package com.is.client_personmap.controllers;

import com.is.client_personmap.model.PersonMap;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;

/**
 * Created by root on 24.07.2017.
 * 11:12
 */
public class PersonMapDiffer extends GenericForwardComposer{
    private Grid differGrd;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);

        self.addEventListener("onShowDiffer", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                PersonMap personMap = (PersonMap) event.getData();
                showDifference(personMap);
            }
        });
    }

    private void showDifference(PersonMap personMap) {

    }
}
