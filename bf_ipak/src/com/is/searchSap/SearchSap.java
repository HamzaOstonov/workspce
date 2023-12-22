package com.is.searchSap;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutProxy;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.endpoint.SAPEnpointService;

public class SearchSap {
	// private final static String _ENDPOINT =
	// "http://10.10.12.54:50100/XISOAPAdapter/MessageServlet?senderParty=&senderService=NCI01_T&receiverParty=&receiverService=&interface=Customer_ReqestOut&interfaceNamespace=urn:ipakyulibank.com:NCI:client";
	private static final String _ENDPOINT = SAPEnpointService.getEndpoint().getCustomerEndpoint();
	private final static String _USERNAME = SAPEnpointService.getEndpoint().getUsername();
	private final static String _PASSWORD = SAPEnpointService.getEndpoint().getPassword();

	private SearchSap() {
	};

	public static SearchSap getInstance() {
		return new SearchSap();
	}

	public List<SearchResponse> getSearchResults(UserForm userForm) throws RemoteException {
		List<SearchResponse> list = new ArrayList<SearchResponse>();

		if (EmergencyMode.isTrue)
			return list;

		Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_ENDPOINT, _USERNAME, _PASSWORD);

		Customer_ReqestOut customerOut = proxy.getCustomer_ReqestOut();

		BusinessPartnerSearchReqestReqest searchRequest = new BusinessPartnerSearchReqestReqest(
				userForm.getDocumentType(), userForm.getDocumentNumber(),
				userForm.getFirstName(),
				userForm.getMiddleName(),
				userForm.getLastName(), userForm.getBirthday());

		BusinessPartnerSearchReqestReqest[] search = { searchRequest };

		BusinessPartnerSearchResponce response = customerOut.search(search);

		BusinessPartnerResponceHeader[] results = response.getPartners();

		BigInteger count = response.getPartners_count();
		if (results != null) {
			for (int i = 0, length = results.length; i < length; i++) {
				BusinessPartnerResponceHeader result = results[i];

				SearchResponse searchResult = new SearchResponse();
				searchResult.setCount(count.intValue());
				searchResult.setBranch(result.getBranch());
				searchResult.setBirthDay(result.getBirthday());
				searchResult.setLastNameGlobal(result.getSurname_global());
				searchResult.setFirstNameGlobal(result.getName_global());
				searchResult.setMiddleNameGlobal(result.getMid_name_global());
				searchResult.setLastNameLocal(result.getSurname_local());
				searchResult.setFirstNameLocal(result.getName_local());
				searchResult.setMiddleNameLocal(result.getMidname_local());
				searchResult.setNciId(result.getId_client_nci());
				searchResult.setSapId(result.getId_client_sap());

				list.add(searchResult);
			}
		}
		return list;
	}
}
