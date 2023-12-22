package com.is.client_sap.abstraction;

import java.util.List;

import com.is.client_personmap.model.Person;
import com.is.client_sap.exceptions.SapException;
import com.is.customer_.core.model.Customer;

public interface ICustomerService {

	public List<Customer> searchBusinessPartners(Customer customer) throws Exception;
	
	public Person getDetailsByIdSap(String idSap);
	
	public Person getDetailsByIdNci(String branch, String idNci);
	
	public void createPartner(Customer bp) throws SapException;
	
	public void updatePartner(Customer bp) throws SapException;
	
	public void createPerson(Person person) throws SapException;
	
	public void updatePerson(Person person) throws SapException;
	
	public void process(Person person) throws SapException;
	
}
