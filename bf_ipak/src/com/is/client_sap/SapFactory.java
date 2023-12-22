package com.is.client_sap;

import com.is.client_personmap.model.LegalEntity;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.abstraction.AbstractSapRoleService;
import com.is.client_sap.abstraction.IContentService;
import com.is.client_sap.abstraction.ICustomerService;
import com.is.client_sap.services.SapContentService;
import com.is.client_sap.services.SapCustomerService;
import com.is.client_sap.services.SapLegalEntityService;
import com.is.client_sap.services.SapOrgClientService;
import com.is.client_sap.services.SapRelationService;
import com.is.client_sap.services.SapRoleService;
import com.is.client_sap.stubServices.SapContentStubService;
import com.is.client_sap.stubServices.SapCustomerStubService;
import com.is.client_sap.stubServices.SapLegalEntityStubService;
import com.is.client_sap.stubServices.SapOrgClientStubService;
import com.is.client_sap.stubServices.SapRelationStubService;
import com.is.client_sap.stubServices.SapRoleStubService;
import com.is.clients.models.ClientJ;
import com.is.customer_.core.utils.CustomerUtils;

public class SapFactory {
	private static SapFactory instance;
	
	private SapFactory() {}
	
	public static SapFactory instance() {
		if(instance == null) {
			instance = new SapFactory();
		}
		return instance;
	}
	
	
	public AbstractSapRelationService getRelationService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapRelationStubService.instance();
		} else {
			return SapRelationService.instance();
		}
	}
	
	public AbstractSapOrganizationService<ClientJ> getOrganizationService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapOrgClientStubService.instance();
		} else {
			return SapOrgClientService.instance();
		}
	}
	
	public AbstractSapOrganizationService<ClientJ> getSapOrganizationService() {
			return SapOrgClientService.instance();
	}

	public AbstractSapOrganizationService<ClientJ> getLocalOrganizationService() {
			return SapOrgClientStubService.instance();
	}
	
	public AbstractSapOrganizationService<LegalEntity> getLegalEntityService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapLegalEntityStubService.instance();
		} else {
			return SapLegalEntityService.instance();
		}
	}
	public AbstractSapRoleService getRoleService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapRoleStubService.instance();
		} else {
			return SapRoleService.instance();
		}
	}
	
	public IContentService getContentService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapContentStubService.instance();
		} else {
			return SapContentService.instance();
		}
	}
	
	public ICustomerService getCustomerService() {
		if(CustomerUtils.isEmergencyModeOn()) {
			return SapCustomerStubService.instance();
		} else {
			return SapCustomerService.instance();
		}
	}
}
