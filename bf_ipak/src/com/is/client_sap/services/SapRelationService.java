package com.is.client_sap.services;

import com.is.ISLogger;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.exceptions.SapException;
import com.is.client_sap.exceptions.SapRelationException;
import com.is.clients.models.SapLogger;
import com.is.customer_.contact.Relationship;
import com.is.customer_.sap.EmergencyMode;
import com.is.delta_relations.DeltaRelation;

import relationships.NCI.com.ipakyulibank.BPContent;
import relationships.NCI.com.ipakyulibank.BPRelReqest;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.BPShareholder;
import relationships.NCI.com.ipakyulibank.IdentNums;

public class SapRelationService extends AbstractSapRelationService {

    private SapRelationService() {
    }

    public static SapRelationService instance() {
        return new SapRelationService();
    }

    @Override
    public void createRelation(PersonMap personMap) throws SapRelationException, Exception {
        sendRelation(personMap, REL_MODE_CREATE);
        SapFactory.instance().getRoleService().createRole(personMap);
    }

    @Override
    public void deleteRelation(PersonMap personMap) throws SapRelationException, Exception {
        sendRelation(personMap, REL_MODE_DELETE);
        SapFactory.instance().getRoleService().deleteRole(personMap);
    }

    @Override
    public void modifyRelation(PersonMap personMap) throws SapRelationException, Exception {
        sendRelation(personMap, REL_MODE_MODIFY);
        /*if (personMap.getCapital() != null
                &&
                personMap.getCapital()
                        .getPart_of_capital()
                        .equals(BigDecimal.ZERO)){
            SapFactory.instance().getRoleService().deleteRole(personMap);
        }*/
    }

    private void sendRelation(PersonMap personMap, String action) throws SapRelationException, Exception {
        BPContent content2 = null;
        IdentNums identNums = null;
        BPShareholder shareHolder = null;
        if (personMap.getCapital() != null) {
            shareHolder = new BPShareholder(
                    personMap.getCapital().getPart_of_capital(),
                    personMap.getCapital().getSum_a(),
                    personMap.getCapital().getCurrency(),
                    personMap.getCapital().getIs_director(),
                    personMap.getCapital().getShares_number());
        }

        if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
            identNums = new IdentNums(SapEnum.DOC_TYPE_INN.getSapValue(),
                    personMap.getLegalEntity().getNumber_tax_registration()
                    , null, null);

        } else if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
            identNums = new IdentNums(
                    personMap.getPerson().getType_document(),
                    null,
                    personMap.getPerson().getPassport_serial(),
                    personMap.getPerson().getPassport_number());
        }
        // Проверка для ип
        if (personMap.getPerson_kind() != null && personMap.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONKIND_IP)) {
            content2 = makeCustContent(personMap,
                    personMap.getPerson_id() != null ?
                            personMap.getPerson_id() : personMap.getIdSap()
                    , personMap.getBranch());
        } else {
            String union_id = personMap.getPerson_type().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_J)
                    ? personMap.getLegalEntity().getUnion_id() : personMap.getPerson().getUnion_id();
            content2 = makeCustContent(personMap,
                    union_id != null ? union_id
                            : personMap.getIdSap()
                    , personMap.getBranch(), identNums);
        }
        if (SapEnum.getByNciKey(personMap.getPerson_kind()) == null) {
            throw new SapException("undefined person kind");
        }
        try {
//			if(personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
            sendRelation(
                    content2,
                    makeOrgContent(personMap),
                    SapEnum.getByNciKey(personMap.getPerson_kind()).getSapValue(),
                    null,
                    shareHolder,
                    action);
//			} else {
//				sendRelation(makeOrgContent(personMap)
//						,content2, SapEnum.getByNciKey(personMap.getPerson_kind()).getSapValue(), null, shareHolder, action);
//			}
        } catch (Exception e) {
            new DeltaRelation.Builder(personMap)
                    .action(action)
                    .state(SapLogger.STATE_ERROR)
                    .message(e.getMessage())
                    .build()
                    .log();
            if (!EmergencyMode.isErrorConsumed) {
                throw new SapException(e);
            }
        }

    }

    @Override
    public String getIPcode(String idClient, String branch) throws Exception {
        BPRelResp[] rels = getRelations(new BPRelReqest(SapEnum.CLIENT_ORG.getSapValue(), "J" + idClient, branch, null));
        for (BPRelResp rel : rels) {
            if (rel.getBp_relationships().equals(SapEnum.REL_TYPE_IP.getSapValue())) {
                return rel.getBp_id_02();
            }
        }
        return null;
    }

    @Override
    public BPRelResp getIPcodeByIdNci(String idClient, String branch) throws Exception {
        BPRelResp[] rels = getRelations(new BPRelReqest(SapEnum.CLIENT_ORG.getSapValue(), idClient, branch, null));
        for (BPRelResp rel : rels) {
            if (rel.getBp_relationships().getRel_type().equals(SapEnum.REL_TYPE_IP.getSapValue())) {
                return rel;
            }
        }
        return null;
    }

    @Override
    public BPRelResp getIPcodeByIdSap(String idSap) throws Exception {
        BPRelResp[] rels = getRelations(new BPRelReqest(SapEnum.CLIENT_ORG.getSapValue(), null, null, idSap));
        for (BPRelResp rel : rels) {
            if (rel.getBp_relationships().getRel_type().equals(SapEnum.REL_TYPE_IP.getSapValue())) {
                return rel;
            }
        }
        return null;
    }


    private BPContent makeOrgContent(PersonMap personMap) {
        return makeOrgContent(personMap.getClient_id(),
                personMap.getNumber_tax_registration(),
                personMap.getBranch());
    }

    private BPContent makeOrgContent(Relationship relationship, String branch) {
        return makeOrgContent(relationship.getClientJId(), relationship.getInnJ(), branch);
    }

    public static void main(String[] args) {
        PersonMap personMap = PersonMap.individFounderInstance("00444");
        personMap.setCapital(new FounderCapital("000", null, null));
        personMap.setId(12);
        personMap.setPerson_id("222");
        personMap.setClient_id("04123012");
        personMap.setPrson_name("LOOOL ");
        Person person = new Person();
        person.setPassport_serial("aa");
        person.setPassport_number("1212123");
        person.setType_document("6");
        personMap.setPerson(person);

        SapRelationService service = SapRelationService.instance();
        try {
            service.modifyRelation(personMap);
        } catch (SapRelationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
