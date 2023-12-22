package com.is.customer_.sap.service.impl;

import com.is.ISLogger;
import com.is.customer_.ActionsEnum;
import com.is.customer_.contact.Relationship;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.RelationshipGateway;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import relationships.NCI.com.ipakyulibank.BPContent;
import relationships.NCI.com.ipakyulibank.BPRel;
import relationships.NCI.com.ipakyulibank.BPRelReqest;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.IdentNums;
import relationships.NCI.com.ipakyulibank.RelationshipsReqest;
import relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce;
import relationships.NCI.com.ipakyulibank.Relationships_OutProxy;

/**
 * Created by root on 05.06.2017.
 * 17:17
 */
public class RelationshipGatewayImpl extends AbstractEndpointService implements RelationshipGateway{
    private BusinessPartnerInterface customerGateway = SAPServiceFactory.getInstance().getBusinessPartnerService();

    @Override
    public void createRelationship(Customer contactPerson,Relationship relationship) throws Exception {
        Relationships_OutProxy proxy = new Relationships_OutProxy(_relationshipEndpoint, _username, _password);
        contactPerson.setId(contactPerson.getUnion_id()); //2018-02-07
        RelationshipsReqest relationshipsRequest = formRelationshipRequest(contactPerson,relationship,
                ActionsEnum.RELATIONSHIP_MODE_CREATE.value());
        RelationshipsResponceRelationshipsResponce[] out = proxy.relationships_Out(relationshipsRequest);
        isSuccessful(out);
    }

    @Override
    public void deleteRelationship(Customer contactPerson,Relationship relationship) throws Exception {
        Relationships_OutProxy proxy = new Relationships_OutProxy(_relationshipEndpoint, _username, _password);
        contactPerson.setId(contactPerson.getUnion_id()); //2018-02-07
        RelationshipsReqest relationshipsRequest = formRelationshipRequest(contactPerson,relationship,
                ActionsEnum.RELATIONSHIP_MODE_DELETE.value());
        RelationshipsResponceRelationshipsResponce[] out =  proxy.relationships_Out(relationshipsRequest);
        isSuccessful(out);
    }

    private void isSuccessful(RelationshipsResponceRelationshipsResponce[] out) throws Exception {
        if (out != null) {
            for (int i = 0; i < out.length; i++) {
                String status = out[i].getStatus();
                if (status.equalsIgnoreCase("0")) {
                    break;
                }
                else
                    throw new Exception("SAP\n" + out[i].getStatus_details());
            }
        }
    }

    private RelationshipsReqest formRelationshipRequest(Customer contactPerson, Relationship relationship,String mode) {
        BPContent contentJ = formOrganizationContent(contactPerson,relationship);
        BPContent contentP = formCustomerContent(contactPerson,relationship);
        //BPContpers position = new BPContpers(relationship.getPosition());
        BPRel businessPartnerRelationship = new BPRel(
                mode,
                mapToSap(relationship.getPosition()),
                "",
                getProfileAuthor(),
                null,
                null);
        return new RelationshipsReqest(contentJ, contentP,
                businessPartnerRelationship);
    }

    private BPContent formOrganizationContent(Customer contactPerson,Relationship relationship) {
        IdentNums[] numsJ = {new IdentNums(ActionsEnum.TYPE_TAX_NUMBER.value(),
                relationship.getInnJ(),
                null, null)};
        return new BPContent(
                ActionsEnum.TYPE_BUSINESS_PARTNER_J.value(),
                "J" + relationship.getClientJId(),
                contactPerson.getBranch(),
                getProfileAuthor(),
                numsJ);
    }

    private BPContent formCustomerContent(Customer contactPerson,Relationship relationship) {
        IdentNums[] numsP = {new IdentNums(contactPerson.getP_type_document(), null,
                contactPerson.getP_passport_serial(), contactPerson.getP_passport_number())};
        return new BPContent(ActionsEnum.TYPE_BUSINESS_PARTNER.value(),
                "A" + contactPerson.getId(),
                contactPerson.getBranch(),
                getProfileAuthor(),
                numsP);
    }

    private String mapToSap(String position) {
        String positionToReturn  = null;

        if (position.equalsIgnoreCase("1"))
            positionToReturn = ActionsEnum.TYPE_RELATIONSHIP_ACCOUNTANT.value();
        else
            positionToReturn = ActionsEnum.TYPE_RELATIONSHIP_DIRECTOR.value();

        return positionToReturn;
    }

    private String mapFromSap(String position){
        String positionToReturn = null;
        if (position.equalsIgnoreCase(ActionsEnum.TYPE_RELATIONSHIP_ACCOUNTANT.value()))
            positionToReturn = "1";
        if (position.equalsIgnoreCase(ActionsEnum.TYPE_RELATIONSHIP_DIRECTOR.value()))
            positionToReturn = "2";

        return positionToReturn;
    }

    public boolean isRelationshipExists(Customer contactPerson,Relationship relationship) throws Exception {
        String branch = contactPerson.getBranch();
        String idJ = "J" + relationship.getClientJId();
        //String personId = "A" + contactPerson.getId(); //hamza 2018-02-04
        String personId = "A" + contactPerson.getUnion_id(); //hamza 2018-02-04
        String idSAP = customerGateway.get(branch,personId,null).getIdSap() ;

        Relationships_OutProxy proxy = new Relationships_OutProxy(_relationshipEndpoint, _username, _password);
        BPRelReqest[] request = {new BPRelReqest(
                ActionsEnum.TYPE_BUSINESS_PARTNER_J.value(),idJ,branch,null)};

        BPRelResp[] relationships = proxy.getDetails(request);
        if (relationships != null)
            for (int i = 0; i < relationships.length; i++){
                BPRelResp response = relationships[i];
                if (idSAP != null && idSAP.equalsIgnoreCase(response.getBp_id_02())
                        && relationship.getPosition().equalsIgnoreCase(
                        mapFromSap(response.getBp_relationships().getRel_type())))
                    return true;
            }

        return false;
    }
}
