package com.is.client_sap.abstraction;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.is.ISLogger;
import com.is.base.utils.XmlSerializer;
import com.is.client_sap.Endpoint;
import com.is.client_sap.SapEndpoints;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapUtil;
import com.is.client_sap.exceptions.SapException;
import com.is.client_sap.exceptions.SapOrganizationException;
import com.is.clients.models.SapLogger;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.attachments.Attachment;
import com.is.utils.CheckNull;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerContentReqest;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerResponce;
import client.NCI.com.ipakyulibank.cj.Organization_ReqestOutProxy;
import content_server.NCI.com.ipakyulibank.ContentServerFileDeleteRequest;
import content_server.NCI.com.ipakyulibank.ContentServerFileDeleteResponse;
import content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out;
import content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutProxy;
import search.NCI.com.ipakyulibank.BPSearchReqest;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

public abstract class AbstractSapOrganizationService<T> {

	private final BPSearchResponceOrganization[] BPSearch_EMPTY = new BPSearchResponceOrganization[0];

	protected BPSearchResponceOrganization[] searchOrgRequest(String docId, String docType, String name) {
		BPSearchResponceOrganization[] searchResp = null;
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.ORG_REQ.getSapValue());
		Organization_ReqestOutProxy pp = new Organization_ReqestOutProxy(ep.getEndpoint(), ep.getUsername(),
				ep.getPassword());

		BPSearchReqest req = new BPSearchReqest(docId, docType, name);
		try {
			searchResp = pp.search(req);
		}
		catch (RemoteException e){

		}
		catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return searchResp == null ? BPSearch_EMPTY : searchResp;
	}

	protected BusinessOrganizationComplex getDetails(String nciId, String branch, String idSap) {
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.ORG_REQ.getSapValue());
		Organization_ReqestOutProxy pp = new Organization_ReqestOutProxy(ep.getEndpoint(), ep.getUsername(),
				ep.getPassword());
		BusinessOrganizationComplex details = null;
		BusinessPartnerContentReqest req = new BusinessPartnerContentReqest(nciId, branch, idSap);
		try {
			details = pp.getDetails(req);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return details;

	}

	/**
	 * if request successfully accepted returns idSap, otherwise throws
	 * Excpetion
	 * 
	 * @throws Exception
	 * @return String idSap
	 */
	protected BusinessPartnerResponce orgRequest(BusinessOrganizationComplex boc) throws SapException {
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.ORG_REQ.getSapValue());
		Organization_ReqestOutProxy pp = new Organization_ReqestOutProxy(ep.getEndpoint(), ep.getUsername(),
				ep.getPassword());
		BusinessPartnerResponce response = null;
		try {
			response = pp.organization_Reqest(boc);
			XmlSerializer.write(response,
					SapUtil.SAP_LOG_FOLDER + "orgResponse" + ClientUtil.formatDateWithTime(new Date()) + ".xml");

			if (response == null || response.getMessages() != null) {
				throw new RuntimeException(SapUtil.splitMsgFromBapiret(response.getMessages()));
			}
			if (boc.getAttachments() == null || boc.getAttachments().length == 0) {
				new SapLogger.Builder().idClient(boc.getGeneral().getId_client().substring(1))
						.branch(boc.getGeneral().getBranch())
						.customer_type(typeForDelta(boc.getGeneral().getId_client()))
						.idSap(boc.getGeneral().getId_client_sap())
						.action(Integer.parseInt(boc.getGeneral().getOperation())).state(SapLogger.STATE_SENT).build()
						.log();
			}
		} catch (RemoteException e) {
			ISLogger.getLogger().error("z :" + e.getStackTrace());
			throw new SapOrganizationException(SapUtil.PROXY_ERROR);
		} catch (Exception e) {
			ISLogger.getLogger().error("z1: " + CheckNull.getPstr(e));
			throw new RuntimeException(e.getMessage());

			/*
			 * if (boc.getAttachments() == null || boc.getAttachments().length
			 * == 0) { new SapLogger.Builder()
			 * .idClient(boc.getGeneral().getId_client().substring(1))
			 * .branch(boc.getGeneral().getBranch())
			 * .idSap(boc.getGeneral().getId_client_sap())
			 * .customer_type(typeForDelta(boc.getGeneral().getId_client()))
			 * .action(Integer.parseInt(boc.getGeneral().getOperation()))
			 * .message(e.getMessage()) .state(SapLogger.STATE_ERROR) .build()
			 * .log(); if (!EmergencyMode.isErrorConsumed) { throw new
			 * SapOrganizationException(SapUtil.SAP_ERROR+e.getMessage()); } }
			 */
		}
		return response;
	}

	protected void deleteAttachmentRequest(String documentId) throws RemoteException {
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.CONTENT_DELETE_SERVER.getSapValue());
		// Organization_ReqestOutProxy pp = new Organization_ReqestOutProxy(
		// ep.getEndpoint(),ep.getUsername(),ep.getPassword());
		// BusinessPartnerResponce response = null;
		try {
			// response = pp.organization_Reqest(boc);
			// XmlSerializer.write(response,
			// SapUtil.SAP_LOG_FOLDER+"orgResponse"+ClientUtil.formatDateWithTime(new
			// Date())+".xml");
			// if(response == null || response.getMessages() != null) {
			// throw new
			// RuntimeException(SapUtil.splitMsgFromBapiret(response.getMessages()));

			ContentServerFileDelete_OutProxy proxy = new ContentServerFileDelete_OutProxy(ep.getEndpoint(),
					ep.getUsername(), ep.getPassword());
			ContentServerFileDelete_Out request = proxy.getContentServerFileDelete_Out();
			ContentServerFileDeleteResponse response = request
					.contentServerFileDelete_Out(new ContentServerFileDeleteRequest(documentId));

		} catch (RemoteException e) {
			ISLogger.getLogger().error("z :" + e.getStackTrace());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public List<BPSearchResponceOrganization> searchOrganization(String docId, String docType, String name) {
		BPSearchResponceOrganization[] resp = searchOrgRequest(docId, docType, name);
		//ISLogger.getLogger().error("not err. ya zdes. sap poisk. docId="+docId+" docType="+docType+ " name="+name);
		return resp != null ? Arrays.asList(resp) : null;
	}

	public List<BPSearchResponceOrganization> searchOrganizationByDocId(String docId, String docType) {
		BPSearchResponceOrganization[] resp = searchOrgRequest(docId, docType, null);
		return resp != null ? Arrays.asList(resp) : null;
	}

	public List<BPSearchResponceOrganization> searchOrganizationByName(String name) {
		BPSearchResponceOrganization[] resp = searchOrgRequest(null, null, name);
		return resp != null ? Arrays.asList(resp) : null;
	}

	public BusinessOrganizationComplex getDetailsBySapId(String idSap) {
		return getDetails(null, null, idSap);
	}

	public BusinessOrganizationComplex getDetailsByNciId(String clientId, String branch) {
		return getDetails("J" + clientId, branch, null);
	}

	public BusinessOrganizationComplex getDetailsByInn(String clientInn) {
		List<BPSearchResponceOrganization> searchResponse = searchOrganizationByDocId(clientInn,
				SapEnum.DOC_TYPE_INN.getSapValue());
		BusinessOrganizationComplex boc = null;
		if (!searchResponse.isEmpty()) {
			boc = getDetailsBySapId(searchResponse.get(0).getId_client_sap());
		}
		return boc;
	}

	public abstract T getMappedDetailsBySapId(String idSap);

	public abstract T getMappedDetailsByNciId(String clientId, String branch);

	public abstract T getDetailsByDoc(String docId, String docType);

	public abstract String orgRequestNew(T client) throws SapException;

	public abstract String orgRequestEdit(T client) throws SapException;

	public abstract String orgRequestNewUvk(T client, Map<String, Object> uvkMap) throws SapException;

	public abstract String orgRequestEditUvk(T client, Map<String, Object> uvkMap) throws SapException;

	// public abstract String orgRequestFounderNew(LegalEntity founder) throws
	// SapCustomerException;
	// public abstract String orgRequestFounderEdit(LegalEntity founder) throws
	// SapCustomerException;

	public abstract String sendAttacments(T client, BPAttachmentsAttachment[] atachments) throws SapException;

	public abstract String createIfAbsent(T client) throws SapException;

	// public abstract String createIfAbsentFounder(LegalEntity client) throws
	// SapCustomerException;

	public abstract void createIfAbsentWithoutInn(T client) throws SapException;

	public abstract String createIfAbsentUvk(T client, Map<String, Object> uvkMap) throws SapException;

	public abstract List<Attachment> getAttachments(T client) throws SapException;

	public abstract void deleteAttachment(T client, String docId) throws RemoteException;

	private String typeForDelta(String id) {
		String prefix = id.substring(0, 1);
		return prefix.equals("J") ? SapLogger.CUSTOMER_TYPE_CLIENT_J : SapLogger.CUSTOMER_TYPE_LEGAL_FOUNDER;
	}

}