package com.is.customer_.ui;

import org.zkoss.zk.ui.Component;

/**
 * Created by root on 06.06.2017.
 * 19:05
 * ������������ ����� � �������� ����
 */
public interface ComponentProducer {
    /**
     * ����������� ����� � �������� ����������
     * @param component
     * @param path
     */
    void produce(Component component, String path);

    /**
     * ������� ������������� �����
     * @return
     */
    Component getProducedComponent();
}
