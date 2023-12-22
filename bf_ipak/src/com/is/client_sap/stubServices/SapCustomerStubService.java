package com.is.client_sap.stubServices;

import java.util.ArrayList;
import java.util.List;

import com.is.client_personmap.model.Person;
import com.is.client_sap.abstraction.ICustomerService;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.core.model.Customer;

public class SapCustomerStubService implements ICustomerService {

	private SapCustomerStubService() {}
	
	public static SapCustomerStubService instance() {return new SapCustomerStubService();}
	
	@Override
	public List<Customer> searchBusinessPartners(Customer businessPartner) throws Exception {
		return new ArrayList<Customer>();
	}

	@Override
	public void createPartner(Customer bp) throws SapException {
	}

	@Override
	public void updatePartner(Customer bp) throws SapException {
	}

	@Override
	public void createPerson(Person person) throws SapException {
		new SapLogger.Builder().editPerson(person).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE)
		.build().log();
	}

	@Override
	public void updatePerson(Person person) throws SapException {
		new SapLogger.Builder().editPerson(person).action(SapLogger.ACTION_EDIT).state(SapLogger.STATE_EMM_MODE)
		.build().log();
	}

	@Override
	public void process(Person person) throws SapException {
		new SapLogger.Builder().editPerson(person).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE)
		.build().log();
	}

	@Override
	public Person getDetailsByIdSap(String idSap) {
		return new Person();
	}

	@Override
	public Person getDetailsByIdNci(String branch, String idNci) {
		return new Person();
	}

}
