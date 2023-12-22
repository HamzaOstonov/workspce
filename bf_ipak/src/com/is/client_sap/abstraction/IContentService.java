package com.is.client_sap.abstraction;

import com.is.client_sap.exceptions.SapException;

import content_server.NCI.com.ipakyulibank.ContentServerResponce;

public interface IContentService {
	
	public ContentServerResponce getFile(String docId) throws SapException;
	
	
}
