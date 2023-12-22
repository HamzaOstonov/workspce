package com.is.client_sap.stubServices;

import com.is.client_sap.SapUtil;
import com.is.client_sap.abstraction.IContentService;
import com.is.client_sap.exceptions.SapException;

import content_server.NCI.com.ipakyulibank.ContentServerResponce;

public class SapContentStubService implements IContentService {

	private SapContentStubService() {}
	
	public static SapContentStubService instance() {return new SapContentStubService();}

	@Override
	public ContentServerResponce getFile(String docId) throws SapException {
		throw new SapException(SapUtil.SAP_DISABLED);
	}

}
