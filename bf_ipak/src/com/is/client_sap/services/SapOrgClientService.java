package com.is.client_sap.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.NCI.com.ipakyulibank.cj.BAPIRET2;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerResponce;
import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.ClientJ;
import com.is.customer_.attachments.Attachment;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

public class SapOrgClientService extends AbstractSapOrganizationService<ClientJ> {
    private Logger logger = Logger.getLogger(SapOrgClientService.class);

    private SapOrgClientService() {
    }

    public static SapOrgClientService instance() {
        return new SapOrgClientService();
    }

    @Override
    public ClientJ getDetailsByDoc(String docId, String docType) {
        List<BPSearchResponceOrganization> searchResponse = searchOrganizationByDocId(docId, docType);

        if (searchResponse != null && !searchResponse.isEmpty()) {
            return getMappedDetailsBySapId(searchResponse.get(0).getId_client_sap());
        }
        return new ClientJ();
    }

    @Override
    public ClientJ getMappedDetailsByNciId(String clientId, String branch) {
        return Mappers.mapToClientJ(getDetailsByNciId(clientId, branch));
    }

    @Override
    public ClientJ getMappedDetailsBySapId(String idSap) {
        return Mappers.mapToClientJ(getDetailsBySapId(idSap));
    }

    @Override
    public String orgRequestNew(ClientJ client) throws SapException {
        BusinessOrganizationComplex boc = Mappers.makeBOC(client, 0);
        BusinessPartnerResponce response = orgRequest(boc);
        return response.getHeader().getId_client_sap();

    }

    @Override
    public String orgRequestEdit(ClientJ client) throws SapException {
        BusinessOrganizationComplex boc = Mappers.makeBOC(client, 1);
        BusinessPartnerResponce response = orgRequest(boc);
        logger.info("Response " + response);
        if (response.getMessages() != null) {
            for (BAPIRET2 message : response.getMessages()) {
                if (message.getTYPE().equalsIgnoreCase("I")
                        && message.getID().equalsIgnoreCase("ZBP_NCI")
                        && message.getNUMBER().equalsIgnoreCase("014"))
                    client.setI014(true);
            }
        }
        return response.getHeader().getId_client_sap();
    }

    @Override
    public String orgRequestNewUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
        BusinessOrganizationComplex boc = Mappers.makeBOC(client, 0);
        Mappers.setUvk(boc, uvkMap);
        boc.getGeneral().setOperation_origin("1"); //2017-12-21
        BusinessPartnerResponce response = orgRequest(boc);
        return response.getHeader().getId_client_sap();
    }

    @Override
    public String orgRequestEditUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
        BusinessOrganizationComplex boc = Mappers.makeBOC(client, 1);
        Mappers.setUvk(boc, uvkMap);
        boc.getGeneral().setOperation_origin("1"); //2017-12-21
        BusinessPartnerResponce response = orgRequest(boc);
        return response.getHeader().getId_client_sap();
    }

    @Override
    public String sendAttacments(ClientJ client,
                                 BPAttachmentsAttachment[] atachments) throws SapException {
        BusinessOrganizationComplex boc = Mappers.makeBOC(client, 1);
        boc.getGeneral().setOperation_origin("3"); //2017-12-21
        boc.setAttachments(atachments);
        BusinessPartnerResponce response = orgRequest(boc);
        return response.getHeader().getId_client_sap();
    }

    @Override
    public String createIfAbsent(ClientJ client) throws SapException {
        String idSap = null;
        if (client.getId_sap() != null) {
            return orgRequestEdit(client);
        }

        List<BPSearchResponceOrganization> searchResp = searchOrganizationByDocId(client.getJ_number_tax_registration(), SapEnum.DOC_TYPE_INN.getSapValue());
        if (searchResp.size() > 0) {
            client.setId_sap(searchResp.get(0).getId_client_sap());
            idSap = orgRequestEdit(client);
        } else {
            client.setI014(true);
            idSap = orgRequestNew(client);
        }
        return idSap;
    }

    @Override
    public void createIfAbsentWithoutInn(ClientJ client) throws SapException {

        ClientJ clone = null;
        try {
            clone = client.clone();
        } catch (Exception e) {
            throw new SapException(e);
        }
        clone.setJ_number_tax_registration(null);
        if (client.getId_sap() != null) {
            orgRequestEdit(clone);
        } else {
            if (client.getId_client() != null) {
                List<BPSearchResponceOrganization> results = searchOrganization(client.getId_client(), "53", null);
                if (results != null) {
                    for (BPSearchResponceOrganization result : results) {
                        clone.setId_sap(result.getId_client_sap());
                    }
                }
            }
            orgRequestNew(clone);
        }

    }

    @Override
    public String createIfAbsentUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
        String idSap = null;
        if (client.getId_sap() != null) {
            return orgRequestEditUvk(client, uvkMap);
        }

        List<BPSearchResponceOrganization> searchResp = searchOrganizationByDocId(client.getJ_number_tax_registration(), SapEnum.DOC_TYPE_INN.getSapValue());
        if (searchResp.size() > 0) {
            client.setId_sap(searchResp.get(0).getId_client_sap());
            idSap = orgRequestEditUvk(client, uvkMap);
        } else {
            idSap = orgRequestNewUvk(client, uvkMap);
        }
        return idSap;
    }

    public List<Attachment> getAttachments(ClientJ client) {
        BusinessOrganizationComplex details = null;
        BPAttachmentsAttachment[] atachments;
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        try {
            details = getDetailsByNciId(client.getId_client(), client.getBranch());

            if (details.getAttachments() != null) {
                atachments = details.getAttachments();

                for (int i = 0; i < atachments.length; i++) {
                    BPAttachmentsAttachment bpAttachment = atachments[i];
                    Attachment attachment = new Attachment();
                    attachment.setDoc_type(bpAttachment.getType());
                    attachment.setDoc_date(bpAttachment.getDoc_date());
                    attachment.setDoc_number(bpAttachment.getDoc_number());
                    attachment.setFileName(bpAttachment.getFilename() == null ?
                            bpAttachment.getName() : bpAttachment.getFilename());
                    attachment.setDescription(bpAttachment.getDescription());
                    attachment.setUrl(bpAttachment.getURL());
                    attachment.setCreatedAt((bpAttachment.getCreated_at().getTime()));
                    attachmentList.add(attachment);
                }

            }
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        }
        return attachmentList;
    }

    private BPSearchResponceOrganization findInList(List<BPSearchResponceOrganization> list, ClientJ client) {
        for (BPSearchResponceOrganization item : list) {
            if (item.getId_client_nci().equals("J" + client.getId_client())
                    || item.getId_client_nci().equals(client.getId_client())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void deleteAttachment(ClientJ client, String docId) throws RemoteException {
        // TODO Auto-generated method stub
        deleteAttachmentRequest(docId);


    }

}
