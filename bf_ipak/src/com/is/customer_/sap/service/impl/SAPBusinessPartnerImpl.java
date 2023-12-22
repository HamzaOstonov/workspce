package com.is.customer_.sap.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.InternalControl;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import com.is.customer_.sap.service.exception.SAPDuplicationException;
import com.is.customer_.sap.service.exception.SAPException;

import client.NCI.com.ipakyulibank.client_p.BAPIRET2;
import client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerContent;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutProxy;

/**
 * Created by root on 09.05.2017.
 * 16:59
 */
@SuppressWarnings("Duplicates")
public class SAPBusinessPartnerImpl extends AbstractEndpointService
        implements BusinessPartnerInterface {
    @Override
    public Customer create(Customer customer) throws
            SAPException,
            RemoteException {
        if (customer.getId() == null || customer.getId().contains("null"))
            throw new RuntimeException("Customer Id is Null");
        /*if (customer.getName() == null || customer.getName().trim().isEmpty())
            throw new RuntimeException("Customer name cannot be Null");*/

        Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut ro = pp.getCustomer_ReqestOut();
        BusinessPartnerContent cn = mapCustomerToContent(customer);
        cn.setOperation("1");
        cn.setOperation_origin("2"); //2017.12.21
        BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, null, null);
        BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
        BAPIRET2[] responses = resp.getMessages();

        try {
            parseExceptions(responses);
        }
        catch (I014Exception e){
            customer.setI014(true);
        }

        BusinessPartnerResponceHeader header = resp.getHeader();

        if (header.getId_client_sap() == null)
            throw new SAPException("\nSAP CRM Id has not been assigned");

        customer.setIdSap(header.getId_client_sap());

        return customer;
    }

    private void parseExceptions(BAPIRET2[] responses) throws SAPException {
        List<Customer> duplicatedCustomers = new ArrayList<Customer>();
        if (responses != null) {
            for (int i = 0; i < responses.length; i++) {
                BAPIRET2 response = responses[i];
                if (response != null && response.getTYPE().equalsIgnoreCase("E")) {
                    if (response.getID() != null &&
                            response.getID().equalsIgnoreCase("ZBP_NCI")
                            && response.getNUMBER() != null
                            && response.getNUMBER().equalsIgnoreCase("009")) {
                        Customer duplication = new Customer();
                        duplication.setIdSap(response.getMESSAGE_V1());
                        duplicatedCustomers.add(duplication);
                    } else
                        throw new SAPException("\n SAP Ошибка \n" + response.getMESSAGE());
                }
                // Если признак существует то после этого вызвать процедуру
                if (response != null && response.getTYPE().equalsIgnoreCase("I")) {
                    if (response.getID() != null &&
                            response.getID().equalsIgnoreCase("ZBP_NCI")
                            && response.getNUMBER() != null
                            && response.getNUMBER().equalsIgnoreCase("014")) {
                        throw new I014Exception();
                    }
                }
            }
        }
        if (duplicatedCustomers.size() > 0)
            throw new SAPDuplicationException("Нашлись дупликаты ", duplicatedCustomers);
    }

    @Override
    public void update(Customer customer) throws SAPException, RemoteException {
        if (customer.getId() == null || customer.getId().contains("null"))
            throw new RuntimeException("Customer Id is Null");
        Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut ro = pp.getCustomer_ReqestOut();
        BusinessPartnerContent cn = mapCustomerToContent(customer);
        cn.setOperation("1");
        cn.setOperation_origin("2"); //2017-12-21
        BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, null, null);
        BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
        BAPIRET2[] responses = resp.getMessages();
        parseExceptions(responses);
    }

    @Override
    public Customer get(String branch, String id, String idSAP) throws SAPException, RemoteException {
        Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut customerRequest = proxy.getCustomer_ReqestOut();
        BusinessParnerComplex businessPartnerComplex = customerRequest
                .getDetails(new BusinessPartnerContentReqest(id, branch, idSAP));
        BusinessPartnerContent businessPartnerContent = businessPartnerComplex.getGeneral();
        return mapContentToCustomer(businessPartnerContent);
    }

    // 05-03-2018
    @Override
    public Customer create(Customer customer, String operation, String origin) throws SAPException, RemoteException {
        Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut ro = pp.getCustomer_ReqestOut();
        BusinessPartnerContent cn = mapCustomerToContent(customer);
        cn.setOperation(operation);
        cn.setOperation_origin(origin); //2017.12.21
        BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, null, null);
        BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
        BAPIRET2[] responses = resp.getMessages();

        parseExceptions(responses);

        BusinessPartnerResponceHeader header = resp.getHeader();

        if (header.getId_client_sap() == null)
            throw new SAPException("\nSAP CRM Id has not been assigned");

        customer.setIdSap(header.getId_client_sap());

        return customer;
    }

    // 05-03-2018
    @Override
    public void update(Customer customer, String operation, String origin) throws RemoteException, SAPException {
        Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut ro = pp.getCustomer_ReqestOut();
        BusinessPartnerContent cn = mapCustomerToContent(customer);
        cn.setOperation(operation);
        cn.setOperation_origin(origin); //2017-12-21
        BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, null, null);
        BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
        BAPIRET2[] responses = resp.getMessages();
        parseExceptions(responses);
    }

    protected Customer mapContentToCustomer(BusinessPartnerContent content) {
        Customer customer = new Customer();
        customer.setIdSap(content.getId_client_sap());
        customer.setBranch(content.getBranch()); //
        customer.setCode_country(content.getCode_country());
        customer.setCode_form(content.getCode_form()); //
        customer.setCode_resident(content.getCode_resident());
        customer.setCode_subject(content.getCode_subject()); //
        customer.setCode_type(content.getCode_type()); //
        customer.setDate_close(content.getDate_close()); //
        customer.setDate_open(content.getDate_open()); //
        // clientP.setFile_name(null);
        // clientP.setId(null);
        customer.setId(content.getId_client()); //
        customer.setKod_err(0); //
        // clientP.setName(content.getName());
        customer.setP_birth_place(content.getP_birth_place());
        customer.setP_birthday(content.getP_birthday());
        customer.setP_capacity_status_date(content.getP_capacity_status_date());
        customer.setP_capacity_status_place(content.getP_capacity_status_place());
        customer.setP_code_adr_distr(content.getP_code_adr_distr());
        customer.setP_code_adr_region(content.getP_code_adr_region());
        // clientP.setP_code_bank(null);
        customer.setP_code_birth_distr(content.getP_code_birth_distr()); //
        customer.setP_code_birth_region(content.getP_code_birth_region()); //
        customer.setP_code_capacity(content.getP_code_capacity());
        customer.setP_code_citizenship(content.getP_code_citizenship());
        customer.setP_code_class_credit(content.getP_code_class_credit()); //
        customer.setP_code_gender(content.getP_code_gender()); //
        customer.setP_code_nation(content.getP_code_nation());
        customer.setP_code_tax_org(content.getP_code_tax_org());
        customer.setP_email_address(content.getP_email_address());
        customer.setP_family(content.getP_family());
        customer.setP_first_name(content.getP_first_name());
        customer.setP_first_name_local(content.getFirst_name());
        customer.setP_inps(content.getP_inps());
        customer.setP_pinfl(content.getP_inps());        
        customer.setP_family_local(content.getLast_name());
        customer.setP_num_certif_capacity(content.getP_num_certif_capacity());
        customer.setP_number_tax_registration(content.getP_number_tax_registration());
        customer.setP_pass_place_district(content.getDul_district());
        customer.setP_pass_place_region(content.getDul_region());
        customer
                .setP_passport_date_expiration(content.getP_passport_date_expiration());
        customer
                .setP_passport_date_registration(content.getP_passport_date_registration());
        customer.setP_passport_number(content.getP_passport_number());
        customer
                .setP_passport_place_registration(content.getP_passport_place_registration());
        customer.setP_passport_serial(content.getP_passport_serial());
        customer.setP_passport_type(content.getP_passport_type());
        customer.setP_patronymic(content.getP_patronymic());
        customer.setP_patronymic_local(content.getMid_name());
        customer.setP_pension_sertif_serial(content.getP_pension_sertif_serial());
        customer.setP_phone_home(content.getP_phone_home());
        customer.setP_phone_mobile(content.getP_phone_mobile());
        customer.setP_post_address(content.getP_post_address());
        customer.setP_post_address_quarter(content.getAdr_district());
        customer.setP_post_address_street(content.getAdr_street());
        customer.setP_post_address_house(content.getAdr_building());
        customer.setP_post_address_flat(content.getAdr_room());
        customer.setP_type_document(content.getP_type_document());

        return customer;
    }

    protected BusinessPartnerContent mapCustomerToContent(Customer customer) {
        BusinessPartnerContent content = new BusinessPartnerContent();
        content.setBranch(customer.getBranch());
        content.setId_client(customer.getId());
        content.setId_client_sap(customer.getIdSap());
        //content.setName(customer.getName() != null ? customer.getName().toUpperCase() : customer.getName());
        content.setCode_country(customer.getCode_country());
        //content.setCode_form(customer.getCode_form());
        content.setCode_resident(customer.getCode_resident());
        //content.setCode_subject(customer.getCode_subject());
        //content.setCode_type(customer.getCode_type());
        //content.setDate_open(customer.getDate_open());
        //content.setDate_close(customer.getDate_close());
        content.setP_birth_place(customer.getP_birth_place() != null ? customer.getP_birth_place().toUpperCase() : customer.getP_birth_place());
        content.setP_birthday(customer.getP_birthday());
        content.setP_capacity_status_date(customer.getP_capacity_status_date());
        content.setP_capacity_status_place(customer.getP_capacity_status_place() != null ?
                customer.getP_capacity_status_place().toUpperCase() : customer.getP_capacity_status_place());
        content.setP_code_adr_distr(customer.getP_code_adr_distr());
        content.setP_code_adr_region(customer.getP_code_adr_region());
        //content.setP_code_bank(customer.getP_code_bank());
        content.setP_code_birth_distr(customer.getP_code_birth_distr());
        content.setP_code_birth_region(customer.getP_code_birth_region());
        content.setP_code_capacity(customer.getP_code_capacity());
        content.setP_code_citizenship(customer.getP_code_citizenship());
        content.setP_code_class_credit(customer.getP_code_class_credit());
        content.setP_code_gender(customer.getP_code_gender());
        content.setP_code_nation(customer.getP_code_nation());
        content.setP_code_tax_org(customer.getP_code_tax_org());
        content.setP_email_address(customer.getP_email_address() != null ?
                customer.getP_email_address().toLowerCase() : null);
        content.setP_family(customer.getP_family() != null ? customer.getP_family().toUpperCase() : customer.getP_family());
        content.setP_first_name(customer.getP_first_name() != null ? customer.getP_first_name().toUpperCase() : customer.getP_first_name());
        content.setP_patronymic(customer.getP_patronymic() != null ? customer.getP_patronymic().toUpperCase() : customer.getP_patronymic());
        //content.setP_inps(customer.getP_inps());
        content.setP_inps(customer.getP_pinfl());
        content.setP_num_certif_capacity(customer.getP_num_certif_capacity());
        content.setP_pension_sertif_serial(customer.getP_pension_sertif_serial() != null ? customer.getP_pension_sertif_serial().toUpperCase() :
                customer.getP_pension_sertif_serial());
        content.setP_number_tax_registration(customer.getP_number_tax_registration());
        content.setP_passport_date_registration(customer.getP_passport_date_registration());
        content.setP_passport_date_expiration(customer.getP_passport_date_expiration());
        content.setP_passport_number(customer.getP_passport_number());
        content.setP_passport_place_registration(customer.getP_passport_place_registration() != null ? customer.getP_passport_place_registration().toUpperCase() : customer.getP_passport_place_registration());
        content.setP_passport_serial(customer.getP_passport_serial() != null ? customer.getP_passport_serial().toUpperCase() : customer.getP_passport_serial());
        content.setP_passport_type(customer.getP_passport_type());
        content.setP_phone_home(customer.getP_phone_home());
        content.setP_phone_mobile(customer.getP_phone_mobile());
        content.setP_post_address(customer.getP_post_address());
        content.setP_type_document(customer.getP_type_document());
        //content.setSign_registr(customer.getSign_registr());
        //content.setState((customer.getState() == null || customer.getState().isEmpty()) ? 0 :
        //Integer.parseInt(customer.getState()));
        content.setFirst_name(customer.getP_first_name_local() != null ? customer.getP_first_name_local().toUpperCase() : customer.getP_first_name_local());
        content.setLast_name(customer.getP_family_local() != null ? customer.getP_family_local().toUpperCase() : customer.getP_family_local());
        content.setMid_name(customer.getP_patronymic_local() != null ? customer.getP_patronymic_local().toUpperCase() : customer.getP_patronymic_local());
        content.setDul_country_code(
                customer.getP_type_document() != null &&
                        !customer.getP_type_document().equals("4") ?
                        "860" :
                        customer.getP_code_citizenship());
        content.setDul_region(customer.getP_pass_place_region());
        content.setDul_district(customer.getP_pass_place_district());
        content.setAdr_district(customer.getP_post_address_quarter() != null ?
                customer.getP_post_address_quarter().toUpperCase() : customer.getP_post_address_quarter());
        content.setAdr_street(customer.getP_post_address_street() != null ? customer.getP_post_address_street().toUpperCase() : customer.getP_post_address_street());
        content.setAdr_building(customer.getP_post_address_house() != null ? customer.getP_post_address_house().toUpperCase() : customer.getP_post_address_house());
        content.setAdr_room(customer.getP_post_address_flat() != null ? customer.getP_post_address_flat().toUpperCase() : customer.getP_post_address_flat());

        InternalControl internalControl = customer.getInternalControl();
        content.setUvk_init_date(internalControl != null ? internalControl.getInitDate() : null);
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
