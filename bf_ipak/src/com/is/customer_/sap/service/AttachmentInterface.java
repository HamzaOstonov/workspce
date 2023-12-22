package com.is.customer_.sap.service;

import java.rmi.RemoteException;
import java.util.List;

import com.is.customer_.attachments.Attachment;
import com.is.customer_.core.model.Customer;

/**
 * Created by root on 11.05.2017.
 * 15:10
 */
public interface AttachmentInterface {
    void sendAttachment(Customer customer, Attachment attachment) throws Exception;
    
    void deleteAttachment(String docId) throws RemoteException;
    
    Attachment getAttachmentContent(String docId) throws RemoteException;

    List<Attachment> getAttachments(String idSAP) throws RemoteException;
}
