package com.is.clients.services;

import com.is.customer_.sap.service.AttachmentInterface;
import com.is.customer_.sap.service.SAPServiceFactory;

import java.rmi.RemoteException;

public class ClientJAttachmentService {
    private AttachmentInterface attachmentGateway;

    public ClientJAttachmentService(){
        attachmentGateway = SAPServiceFactory.getInstance().getAttachmentService();
    }

    public void delete(String id) throws RemoteException {
        attachmentGateway.deleteAttachment(id);
    }
}
