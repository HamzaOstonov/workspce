package com.is.client_sap.services;

import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import org.apache.log4j.Logger;

import com.is.client_sap.Endpoint;
import com.is.client_sap.SapEndpoints;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.IContentService;
import com.is.utils.CheckNull;

import content_server.NCI.com.ipakyulibank.ContentServerReqest;
import content_server.NCI.com.ipakyulibank.ContentServerResponce;
import content_server.NCI.com.ipakyulibank.ContentServer_ReqestOut;
import content_server.NCI.com.ipakyulibank.ContentServer_ReqestOutProxy;

public class SapContentService implements IContentService {
	private static Logger logger = Logger.getLogger(SapContentService.class);
	
	private SapContentService() {}
	
	public static SapContentService instance() {return new SapContentService();}
	
	public ContentServerResponce getFile(String docId) {
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.CONTENT_SERVER.getSapValue());
		ContentServer_ReqestOutProxy proxy = new ContentServer_ReqestOutProxy(
				ep.getEndpoint(),
				ep.getUsername(), ep.getPassword());
		ContentServer_ReqestOut request = proxy.getContentServer_ReqestOut();
		ContentServerResponce response = null;
		byte[] data = null;
		try {
			response =  request.contentServer_ReqestOut(new ContentServerReqest(docId));
//			data = response.getData();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		}
		return response;
	}
}
