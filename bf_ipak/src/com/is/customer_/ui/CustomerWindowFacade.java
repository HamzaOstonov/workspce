package com.is.customer_.ui;

import com.is.customer_.core.model.Customer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

import java.util.List;

/**
 * Created by root on 06.06.2017.
 * 23:12
 */
public class CustomerWindowFacade extends AbstractCustomerComponentFacade {
    private CustomerWindowFacade(){}

    public CustomerWindowFacade(ComponentProducer producer, CustomerComposerInteractor interactor) {
        super(producer, interactor);
    }

    public static AbstractCustomerComponentFacade newInstance(){
        return new CustomerWindowFacade();
    }

    public static AbstractCustomerComponentFacade newInstance(Component component,String path,String composerAttribute){
        ComponentProducer producer = WindowProducer.getInstance();
        producer.produce(component,path);
        CustomerComposerInteractor interactor =
                CustomerComposerExtractor.
                        extract(producer.getProducedComponent(),composerAttribute);
        return new CustomerWindowFacade(producer,interactor);
    }

    @Override
    public void produce(Component component, String path) {
        producer.produce(component,path);
    }

    @Override
    public Component getProducedComponent() {
        return producer.getProducedComponent();
    }

    @Override
    public void show(List<Customer> customers) throws Exception {
        interactor.show(customers);
    }

    @Override
    public void show(Customer customer) throws Exception {
        ((Window)producer.getProducedComponent()).setVisible(true);
        ((Window)producer.getProducedComponent()).doModal();
        interactor.show(customer);
    }

    @Override
    public void create(Customer customer) throws Exception {
        ((Window)producer.getProducedComponent()).setVisible(true);
        ((Window)producer.getProducedComponent()).doModal();
        interactor.create(customer);
    }

    @Override
    public Customer getCurrentCustomer() throws Exception {
        return interactor.getCurrentCustomer();
    }
}
