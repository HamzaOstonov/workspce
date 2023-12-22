package com.is.customer_.ui;

import org.zkoss.zk.ui.Component;

/**
 * Created by root on 06.06.2017.
 * 19:05
 * Прикрепление формы к текущему окну
 */
public interface ComponentProducer {
    /**
     * Прикрепляет форму к текущему компоненту
     * @param component
     * @param path
     */
    void produce(Component component, String path);

    /**
     * Возврат прикрепленной формы
     * @return
     */
    Component getProducedComponent();
}
