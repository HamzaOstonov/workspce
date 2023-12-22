package com.is.customer_.ui;

import org.zkoss.zk.ui.Component;

/**
 * Extracts composer from component by attribute
 * Created by root on 06.06.2017.
 * 20:45
 */
public final class CustomerComposerExtractor {
    /**
     *
     * @param component
     * @param attribute
     * @return
     */
    public static CustomerComposerInteractor extract(Component component,
                                                     String attribute){
        return (CustomerComposerInteractor) component.getAttribute(attribute);
    }
}
