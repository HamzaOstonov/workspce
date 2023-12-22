package com.is.client_sap.stubServices;

import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.exceptions.SapRelationException;
import com.is.clients.models.SapLogger;
import com.is.delta_relations.DeltaRelation;

import relationships.NCI.com.ipakyulibank.BPRelResp;

public class SapRelationStubService extends AbstractSapRelationService{

	private SapRelationStubService() {}
	
	public static SapRelationStubService instance() {return new SapRelationStubService();}

	@Override
	public void createRelation(PersonMap personMap) throws SapRelationException, Exception {
		new DeltaRelation.Builder().creatingRelation(personMap).state(SapLogger.STATE_EMM_MODE).build().log();
	}

	@Override
	public void deleteRelation(PersonMap personMap) throws SapRelationException, Exception {
		new DeltaRelation.Builder().deletingRelation(personMap).state(SapLogger.STATE_EMM_MODE).build().log();
	}

	@Override
	public String getIPcode(String idClient, String branch) throws Exception {
		return "";
	}

	@Override
	public BPRelResp getIPcodeByIdNci(String idClient, String branch) throws Exception {
		return new BPRelResp();
	}
	
	@Override
	public BPRelResp getIPcodeByIdSap(String idSap) throws Exception {
		return new BPRelResp();
	}

	@Override
	public void modifyRelation(PersonMap personMap) throws SapRelationException, Exception {
		new DeltaRelation.Builder().modifyingRelation(personMap).state(SapLogger.STATE_EMM_MODE).build().log();
	}

}
