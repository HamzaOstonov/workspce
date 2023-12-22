package com.is.client_sap.stubServices;

import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.AbstractSapRoleService;
import com.is.client_sap.exceptions.SapRoleException;

import roles.NCI.com.ipakyulibank.RolesByEventResponceRelationshipsResponce;

public class SapRoleStubService extends AbstractSapRoleService {

	private SapRoleStubService() {}
	
	public static SapRoleStubService instance() {return new SapRoleStubService();}

	@Override
	public RolesByEventResponceRelationshipsResponce[] createRole(PersonMap personMap) throws SapRoleException {
		return new RolesByEventResponceRelationshipsResponce[0];
	}

    @Override
    public void deleteRole(PersonMap personMap) throws SapRoleException {

    }
}
