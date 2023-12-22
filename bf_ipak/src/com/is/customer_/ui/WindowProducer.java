package com.is.customer_.ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

/**
 * Created by root on 06.06.2017.
 * 20:47
 */
public class WindowProducer implements ComponentProducer {
    private Window component;

    private WindowProducer() {
    }

    public static ComponentProducer getInstance() {
        return new WindowProducer();
    }

    /**
     * Check if component exists as a child
     * if true produce a new component
     * else returns;
     *
     * @param parent
     * @param path
     */
    @Override
    public void produce(Component parent, String path) {
        if (component != null &&
                parent.hasFellow(component.getId(), true))
            return;
        component = (Window) Executions.createComponents(path, parent, null);
        component.setVisible(false);
        component.setWidth("100%");
        component.setHeight("100%");
        component.setPosition("center,top");
        component.setContentStyle("overflow:auto");
        component.setTitle(" ");
        component.setClosable(true);
        component.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                component.setVisible(false);
                event.stopPropagation();
            }
        });
    }

    @Override
    public Window getProducedComponent() {
        return component;
    }
}
