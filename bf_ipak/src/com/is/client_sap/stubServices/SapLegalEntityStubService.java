package com.is.client_sap.stubServices;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.is.client_personmap.model.LegalEntity;
import com.is.client_sap.SapUtil;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.exceptions.SapCustomerException;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.attachments.Attachment;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;

public class SapLegalEntityStubService extends AbstractSapOrganizationService<LegalEntity> {
	
	private SapLegalEntityStubService() {}
	
	public static SapLegalEntityStubService instance() {return new SapLegalEntityStubService();}
	
	@Override
	public String orgRequestNew(LegalEntity client) throws SapException {
		new SapLogger.Builder().createLegalEntity(client).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String orgRequestEdit(LegalEntity client) throws SapException {
		new SapLogger.Builder().editLegalEntity(client).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String orgRequestNewUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}

	@Override
	public String orgRequestEditUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}

	@Override
	public String sendAttacments(LegalEntity client, BPAttachmentsAttachment[] atachments) throws SapException {
		new SapLogger.Builder().editLegalEntity(client).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String createIfAbsent(LegalEntity client) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}

	@Override
	public void createIfAbsentWithoutInn(LegalEntity client) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}

	@Override
	public String createIfAbsentUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}

	@Override
	public List<Attachment> getAttachments(LegalEntity client) throws SapException {
		throw new SapCustomerException(SapUtil.SAP_DISABLED);
	}
	

	@Override
	public LegalEntity getMappedDetailsBySapId(String idSap) {
		return new LegalEntity();
	}

	@Override
	public LegalEntity getMappedDetailsByNciId(String clientId, String branch) {
		return new LegalEntity();
	}

	@Override
	public LegalEntity getDetailsByDoc(String docId, String docType) {
		return new LegalEntity();
	}

	@Override
	public void deleteAttachment(LegalEntity client, String docId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
