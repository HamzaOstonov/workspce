package com.is.clients.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

import com.is.ISLogger;
import com.is.client_personmap.model.Person;
import com.is.client_sap.Mappers;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.ui.CustomerComposerInteractor;

import java.util.List;

public class ContactPersonSearchInteractor implements CustomerComposerInteractor {
	
	private static final String COMPOSER_ATTRIBUTE = "clientmain$composer";
	private final Window parent;
	private AbstractClientController composer;
	
	
	public ContactPersonSearchInteractor(Window parent) {
		this.parent = parent;
		initDecorator();
	}
	private void initDecorator() {
        composer = (AbstractClientController)
                parent.getAttribute(COMPOSER_ATTRIBUTE);
    }

    @Override
    public void show(List<Customer> customer) throws Exception {

    }

    @Override
	public void show(Customer customer) throws Exception {
		Customer detailedCustomer = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, customer.getIdSap());
		ISLogger.getLogger().error("ContactPersonSearchInteractor   customer.getIdSap = "+customer.getIdSap());
		ISLogger.getLogger().error("ContactPersonSearchInteractor   customer = "+customer);
		Person person = Mappers.mapBpToPerson(detailedCustomer);
		
		composer.showContactPerson(person);
	}

	@Override
	public void create(Customer customer) throws Exception {

	}
	
	@Override
	public Customer getCurrentCustomer() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
