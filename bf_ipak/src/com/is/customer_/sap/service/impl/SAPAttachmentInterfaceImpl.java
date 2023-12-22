package com.is.customer_.sap.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.customer_.attachments.Attachment;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.InternalControl;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.sap.service.AttachmentInterface;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import client.NCI.com.ipakyulibank.client_p.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerContent;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutProxy;
import content_server.NCI.com.ipakyulibank.ContentServerFileDeleteRequest;
import content_server.NCI.com.ipakyulibank.ContentServerFileDeleteResponse;
import content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out;
import content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutProxy;
import content_server.NCI.com.ipakyulibank.ContentServerReqest;
import content_server.NCI.com.ipakyulibank.ContentServerResponce;
import content_server.NCI.com.ipakyulibank.ContentServer_ReqestOut;
import content_server.NCI.com.ipakyulibank.ContentServer_ReqestOutProxy;

/**
 * Created by root on 11.05.2017.
 * 15:32
 */
public class SAPAttachmentInterfaceImpl extends AbstractEndpointService
        implements AttachmentInterface{
    private Logger logger = ISLogger.getLogger();

    @Override
    public void sendAttachment(Customer customer, Attachment attachment) throws Exception {
    	//logger.error("not err zagruzka impl " + attachment.getFileName()+" - "+CustomerUtils.toCalendar(new Date()));
    	
    	BPAttachmentsAttachment[] attachments = {new BPAttachmentsAttachment
                (
                attachment.getData(),
                attachment.getDescription(),
                attachment.getDoc_type(),
                attachment.getFileName(),
                attachment.getFileName(),
                null,//attachment.getUrl(),
                attachment.getDoc_number(),
                attachment.getDoc_date(),
                CustomerUtils.toCalendar(new Date()))};

        Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        
        Customer_ReqestOut ro = pp.getCustomer_ReqestOut();

        BusinessPartnerContent cn = mapCustomerToContent(customer);
        cn.setOperation("1");
        cn.setOperation_origin("3"); //2017-12-21
        
        BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, attachments, null);
        
        BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
        
        String error = "";
        if (resp.getMessages() != null)
            for (int i = 0; i < resp.getMessages().length; i++) {
                if (resp.getMessages()[i].getTYPE().equals("E"))
                    error += resp.getMessages()[i].getMESSAGE();
            }
        
        if (!error.isEmpty())
            throw new Exception("SAP Error " + error);
    }

    @Override
    public void deleteAttachment(String docId) throws RemoteException {
    	//logger.error("not err OK _username="+_username+ ". _password="+_password+" . _contentDeleteEndpoint="+_contentDeleteEndpoint+ " ." );
    	ContentServerFileDelete_OutProxy proxy = new ContentServerFileDelete_OutProxy(_contentDeleteEndpoint, _username, _password);
    	ContentServerFileDelete_Out request = proxy.getContentServerFileDelete_Out();
    	ContentServerFileDeleteResponse response = request.contentServerFileDelete_Out(new ContentServerFileDeleteRequest(docId));
    	//logger.error("not err OK docId="+docId);
    	//logger.error("not err response.getSTATUS()="+response.getSTATUS());
    }

    
    private Attachment mapResponseToAttachment(ContentServerResponce response,String url) {
        Attachment attachment = new Attachment();
        attachment.setFileName(response.getDoc_name());
        //attachment.setFileType(response.getDoc_type());
        attachment.setDoc_type(response.getDoc_type());
        return attachment;
    }

    @Override
    public Attachment getAttachmentContent(String docId) throws RemoteException {
        ContentServer_ReqestOutProxy proxy = new ContentServer_ReqestOutProxy(_contentEndpoint, _username, _password);
        ContentServer_ReqestOut request = proxy.getContentServer_ReqestOut();
        ContentServerResponce response = request.contentServer_ReqestOut(new ContentServerReqest(docId));;
        Attachment attachment = new Attachment(response.getData(),null,response.getDoc_name(),null,null,null,null);
        return attachment;
    }

    @Override
    public List<Attachment> getAttachments(String idSAP) throws RemoteException {
        Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut customerRequest = proxy.getCustomer_ReqestOut();
        BusinessParnerComplex businessPartnerComplex = customerRequest.getDetails(
                new BusinessPartnerContentReqest(null, null, idSAP));
        BPAttachmentsAttachment[] attachments = businessPartnerComplex.getAttachments();

        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if (attachments != null)
            for (int i = 0; i < attachments.length; i++){
                BPAttachmentsAttachment bpAttachment = attachments[i];
                Attachment attachment = new Attachment();
                attachment.setDoc_type(bpAttachment.getType());
                attachment.setDoc_date(bpAttachment.getDoc_date());
                attachment.setDoc_number(bpAttachment.getDoc_number());
                attachment.setFileName(bpAttachment.getFilename() == null ?
                        bpAttachment.getName(): bpAttachment.getFilename());
                attachment.setDescription(bpAttachment.getDescription());
                attachment.setUrl(bpAttachment.getURL());
                attachment.setCreatedAt(CustomerUtils.toDate(bpAttachment.getCreated_at()));
                attachmentList.add(attachment);
            }
        return attachmentList;
    }

    private BusinessPartnerContent mapCustomerToContent(Customer customer) {
        BusinessPartnerContent content = new BusinessPartnerContent();
        content.setBranch(customer.getBranch());
        content.setId_client(customer.getId());
        content.setId_client_sap(customer.getIdSap());
        content.setName(customer.getFullName());
        content.setCode_country(customer.getCode_country());
        content.setCode_form(customer.getCode_form());
        content.setCode_resident(customer.getCode_resident());
        content.setCode_subject(customer.getCode_subject());
        content.setCode_type(customer.getCode_type());
        content.setDate_open(customer.getDate_open());
        content.setDate_close(customer.getDate_close());
        content.setP_birth_place(customer.getP_birth_place());
        content.setP_birthday(customer.getP_birthday());
        content.setP_capacity_status_date(customer.getP_capacity_status_date());
        content.setP_capacity_status_place(customer.getP_capacity_status_place());
        content.setP_code_adr_distr(customer.getP_code_adr_distr());
        content.setP_code_adr_region(customer.getP_code_adr_region());
        content.setP_code_bank(customer.getP_code_bank());
        content.setP_code_birth_distr(customer.getP_code_birth_distr());
        content.setP_code_birth_region(customer.getP_code_birth_region());
        content.setP_code_capacity(customer.getP_code_capacity());
        content.setP_code_citizenship(customer.getP_code_citizenship());
        content.setP_code_class_credit(customer.getP_code_class_credit());
        content.setP_code_gender(customer.getP_code_gender());
        content.setP_code_nation(customer.getP_code_nation());
        content.setP_code_tax_org(customer.getP_code_tax_org());
        content.setP_email_address(customer.getP_email_address());
        content.setP_family(customer.getP_family());
        content.setP_first_name(customer.getP_first_name());
        content.setP_patronymic(customer.getP_patronymic());
        //content.setP_inps(customer.getP_inps());
        content.setP_inps(customer.getP_pinfl());
        content.setP_num_certif_capacity(customer.getP_num_certif_capacity());
        content.setP_pension_sertif_serial(customer.getP_pension_sertif_serial());
        content.setP_number_tax_registration(customer.getP_number_tax_registration());
        content.setP_passport_date_registration(customer.getP_passport_date_registration());
        content.setP_passport_date_expiration(customer.getP_passport_date_expiration());
        content.setP_passport_number(customer.getP_passport_number());
        content.setP_passport_place_registration(customer.getP_passport_place_registration());
        content.setP_passport_serial(customer.getP_passport_serial());
        content.setP_passport_type(customer.getP_passport_type());
        content.setP_phone_home(customer.getP_phone_home());
        content.setP_phone_mobile(customer.getP_phone_mobile());
        content.setP_post_address(customer.getP_post_address());
        content.setP_type_document(customer.getP_type_document());
        content.setSign_registr(customer.getSign_registr());
        content.setState((customer.getState() == null || customer.getState().isEmpty()) ? 0 :
                Integer.parseInt(customer.getState()));
        content.setFirst_name(customer.getP_first_name_local());
        content.setLast_name(customer.getP_family_local());
        content.setMid_name(customer.getP_patronymic_local());
        content.setDul_country_code(
                customer.getP_type_document() != null &&
                        !customer.getP_type_document().equals("4") ?
                        "860" :
                        customer.getP_code_citizenship());
        content.setDul_region(customer.getP_pass_place_region());
        content.setDul_district(customer.getP_pass_place_district());
        content.setAdr_district(customer.getP_post_address_quarter());
        content.setAdr_street(customer.getP_post_address_street());
        content.setAdr_building(customer.getP_post_address_house());
        content.setAdr_room(customer.getP_post_address_flat());

        InternalControl internalControl = customer.getInternalControl();
        content.setUvk_valid_from(internalControl != null ? internalControl.getValidDateFrom() : null);
        content.setUvk_valid_to(internalControl != null ? internalControl.getValidDateTo() : null);
        content.setUvk_reason(internalControl != null ? internalControl.getReason() : null);
        content.setUvk_risk_lavel(internalControl != null ? internalControl.getRiskLevel() : null);
        content.setSom_opers(internalControl != null ? internalControl.getS_notes() : null);
        content.setPod_opers(internalControl != null ? internalControl.getP_notes() : null);
        content.setProfile_author(getProfileAuthor());
        return content;
    }
}
