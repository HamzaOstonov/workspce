package com.is.client_sap.services;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import client.NCI.com.ipakyulibank.cj.BusinessPartnerResponce;
import com.is.ISLogger;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.exceptions.SapCustomerException;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.attachments.Attachment;
import com.is.customer_.sap.EmergencyMode;
import com.is.utils.CheckNull;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

public class SapLegalEntityService extends AbstractSapOrganizationService<LegalEntity>{

	private SapLegalEntityService() {}
	
	public static SapLegalEntityService instance() {return new SapLegalEntityService();}
	
	@Override
	public LegalEntity getDetailsByDoc(String docId, String docType) {
		List<BPSearchResponceOrganization>  searchResponse = searchOrganizationByDocId(docId, docType);
		
		if (searchResponse != null && !searchResponse.isEmpty()) {
			return getMappedDetailsBySapId(searchResponse.get(0).getId_client_sap());
		}
		return new LegalEntity();
	}
	
	@Override
	public LegalEntity getMappedDetailsByNciId(String clientId, String branch) {
		return Mappers.mapBocToLegal(getDetailsByNciId(clientId, branch));
	}
	@Override
	public LegalEntity getMappedDetailsBySapId(String idSap) {
		return Mappers.mapBocToLegal(getDetailsBySapId(idSap));
	}
	
	@Override
	public String orgRequestNew(LegalEntity client) throws SapException {
		BusinessOrganizationComplex boc = Mappers.mapLegalToBoc(client, 0);
		BusinessPartnerResponce response = orgRequest(boc);
		return response.getHeader().getId_client_sap();
	}

	@Override
	public String orgRequestEdit(LegalEntity client) throws SapException {
		BusinessOrganizationComplex boc = Mappers.mapLegalToBoc(client, 1);
		BusinessPartnerResponce response = orgRequest(boc);
		return response.getHeader().getId_client_sap();
	}

	@Override
	public String orgRequestNewUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapCustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orgRequestEditUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapCustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendAttacments(LegalEntity client, BPAttachmentsAttachment[] atachments) throws SapCustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createIfAbsent(LegalEntity client) throws SapException {
		String idSap = null;
		if(client.getIdSap() != null) {
			return orgRequestEdit(client);
		}
		int action = 0;
		List<BPSearchResponceOrganization> searchResp = searchOrganizationByDocId(client.getNumber_tax_registration(), SapEnum.DOC_TYPE_INN.getSapValue());
		try {
			if(searchResp.size() > 0) {
				client.setIdSap(searchResp.get(0).getId_client_sap());
				idSap = orgRequestEdit(client);
				action = 1;
			} else {
				idSap = orgRequestNew(client);
				action = 0;
			}
			new SapLogger.Builder(client)
					.action(action)
					.state(SapLogger.STATE_SENT)
			.build()
			.log();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			new SapLogger.Builder(client)
					.action(action)
					.state(SapLogger.STATE_ERROR)
					.message(e.getMessage())
			.build()
			.log();
			if (!EmergencyMode.isErrorConsumed) {
				throw new SapException(e);
			}
		}
		return idSap;
	}

	@Override
	public void createIfAbsentWithoutInn(LegalEntity client) throws SapCustomerException {
	}

	@Override
	public String createIfAbsentUvk(LegalEntity client, Map<String, Object> uvkMap) throws SapCustomerException {
		return null;
	}

	@Override
	public List<Attachment> getAttachments(LegalEntity client) {
		return null;
	}

	@Override
	public void deleteAttachment(LegalEntity client, String docId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
