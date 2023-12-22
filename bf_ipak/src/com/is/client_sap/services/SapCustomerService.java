package com.is.client_sap.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.model.Person;
import com.is.client_sap.Endpoint;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapEndpoints;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.ICustomerService;
import com.is.client_sap.exceptions.SapCustomerException;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import com.is.customer_.sap.service.exception.SAPDuplicationException;
import com.is.customer_.sap.service.exception.SAPException;

import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutProxy;

public class SapCustomerService implements ICustomerService{

	private SapCustomerService() {
		// TODO Auto-generated constructor stub
	}
	
	public static SapCustomerService instance() {return new SapCustomerService();}
	
	@Override
	public List<Customer> searchBusinessPartners(Customer businessPartner) throws Exception {
		List<Customer> list = new ArrayList<Customer>();
		Endpoint endpoint = SapEndpoints.getEndpoint(SapEnum.CUSTOMER_REQ.getSapValue());
		Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(endpoint.getEndpoint(), endpoint.getUsername(), endpoint.getPassword());

		Customer_ReqestOut customerOut = proxy.getCustomer_ReqestOut();

		BusinessPartnerSearchReqestReqest searchRequest = new BusinessPartnerSearchReqestReqest(
				businessPartner.getP_type_document(),
                businessPartner.getP_passport_number(),
                businessPartner.getP_first_name(),
                businessPartner.getP_patronymic(),
                businessPartner.getP_family(),
                businessPartner.getP_birthday());

		BusinessPartnerSearchReqestReqest[] search = { searchRequest };
		
		BusinessPartnerSearchResponce response = customerOut.search(search);

		BusinessPartnerResponceHeader[] results = response.getPartners();

		BusinessPartnerInterface service = SAPServiceFactory.getInstance().getBusinessPartnerService();

		if (results != null) {
			/*Customer add = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null,null, results[0].getId_client_sap());
			for (int i = 0, length = results.length; i < length; i++) {
				BusinessPartnerResponceHeader result = results[i];
				if (result != null) {
					
					add.setBranch(result.getBranch());
					add.setId(result.getId_client_nci());
					add.setIdSap(result.getId_client_sap());
					list.add(add);
				}
			}*/
			for (BusinessPartnerResponceHeader result : results) {
				Customer customer = service.get(null,null, result.getId_client_sap());
				list.add(customer);
			}
		}

		return list;
	}
	@Override
	public void createPartner(Customer bp) throws SapException{
		try {
			Customer created = SAPServiceFactory.getInstance().getBusinessPartnerService().create(bp);
			bp.setIdSap(created.getIdSap());
		} catch (Exception e) {
			throw new SapCustomerException(e); 
		}
	}
	@Override
	public void updatePartner(Customer bp) throws SapException{
		try {
			SAPServiceFactory.getInstance().getBusinessPartnerService().update(bp);
		} catch (Exception e) {
			throw new SapCustomerException(e); 
		}
	}

	@Override
	public void createPerson(Person person) throws SapException {
		try {
			Customer bp = Mappers.mapPersonToBp(person);
			bp.setBranch(person.getBranch());
			Customer created = SAPServiceFactory.getInstance().getBusinessPartnerService().create(bp);
			person.setIdSap(created.getIdSap());
			new SapLogger.Builder(person)
					.action(0)
					.state(SapLogger.STATE_SENT)
			.build()
			.log();
		} catch(Exception e) {
			new SapLogger.Builder(person)
					.action(0)
					.message(e.getMessage())
					.state(SapLogger.STATE_ERROR)
			.build()
			.log();
			if (!EmergencyMode.isErrorConsumed) {
				throw new SapCustomerException(e);
			}
		}
	}

	@Override
	public void updatePerson(Person person) throws SapException {
		try {
			Customer bp = Mappers.mapPersonToBp(person);
			bp.setBranch(person.getBranch());
			SAPServiceFactory.getInstance().getBusinessPartnerService().update(bp);
			new SapLogger.Builder(person)
					.action(1)
					.state(SapLogger.STATE_SENT)
			.build()
			.log();
		} catch(Exception e) {
			new SapLogger.Builder(person)
					.action(1)
					.message(e.getMessage())
					.state(SapLogger.STATE_ERROR)
			.build()
			.log();
			if (!EmergencyMode.isErrorConsumed) {
				throw new SapCustomerException(e);
	}
		}
	}

	@Override
	public void process(Person person) throws SapException {
		try {
			Customer bp = Mappers.mapPersonToBp(person);
			bp.setBranch(person.getBranch());
//			CustomerGate
			String created = SAPServiceFactory.getInstance()
                    .getCustomerGatewayDecorator().process(bp);
			person.setIdSap(created);
			new SapLogger.Builder(person)
					.action(0)
					.state(SapLogger.STATE_SENT)
			.build()
			.log();
		} catch(Exception e) {
			new SapLogger.Builder(person)
					.action(0)
					.message(e.getMessage())
					.state(SapLogger.STATE_ERROR)
			.build()
			.log();
			if (!EmergencyMode.isErrorConsumed) {
                if (e instanceof SAPDuplicationException){
                    Customer duplicationCustomer = ((SAPDuplicationException)e).getList().get(0);
                    throw new FounderDuplicationException(null,duplicationCustomer);
                }
				throw new SapCustomerException(e);
			}

		}
	}

	@Override
	public Person getDetailsByIdSap(String idSap) {
		Person person = null;
		try {
			person = Mappers.mapBpToPerson(SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, idSap));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Person getDetailsByIdNci(String branch, String idNci) {
		Person person = null;
		try {
			person = Mappers.mapBpToPerson(SAPServiceFactory.getInstance().getBusinessPartnerService().get(branch, "A"+idNci, null));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

    public static class FounderDuplicationException extends RuntimeException {
        private final Customer customer;

        public FounderDuplicationException(String message, Customer customer) {
            super(message);
            this.customer = customer;
        }

        public Customer getDuplicationCustomer() {
            return this.customer;
        }
    }
}
