package com.is.customer_.ui;

/**
 * Created by root on 06.06.2017.
 * 23:05
 */
public abstract class AbstractCustomerComponentFacade implements ComponentProducer,CustomerComposerInteractor {
    protected ComponentProducer producer;
    protected CustomerComposerInteractor interactor;

    public AbstractCustomerComponentFacade(){}

    public AbstractCustomerComponentFacade(ComponentProducer producer,CustomerComposerInteractor interactor){
        this.producer = producer;
        this.interactor = interactor;
    }

    public ComponentProducer getProducer() {
        return producer;
    }

    public void setProducer(ComponentProducer producer) {
        this.producer = producer;
    }

    public CustomerComposerInteractor getInteractor() {
        return interactor;
    }

    public void setInteractor(CustomerComposerInteractor interactor) {
        this.interactor = interactor;
    }

    public void detachComponent(){
        this.getProducedComponent().detach();
    }
}