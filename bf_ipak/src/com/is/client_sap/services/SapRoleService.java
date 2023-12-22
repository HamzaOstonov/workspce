package com.is.client_sap.services;

import com.is.ISLogger;
import org.zkoss.zk.ui.Executions;

import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapEnum;
import com.is.client_sap.abstraction.AbstractSapRoleService;
import com.is.client_sap.exceptions.SapRoleException;

import relationships.NCI.com.ipakyulibank.BPContent;
import relationships.NCI.com.ipakyulibank.IdentNums;
import roles.NCI.com.ipakyulibank.RolesByEventResponceRelationshipsResponce;

public class SapRoleService extends AbstractSapRoleService {

    private SapRoleService() {
    }

    public static SapRoleService instance() {
        return new SapRoleService();
    }

    @Override
    public RolesByEventResponceRelationshipsResponce[] createRole(PersonMap map) throws SapRoleException {
        IdentNums idents = null;
        if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {

            idents = new IdentNums(map.getPerson().getType_document(),
                    null,
                    map.getPerson().getPassport_serial(),
                    map.getPerson().getPassport_number());

        } else if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
            idents = new IdentNums(SapEnum.DOC_TYPE_INN.getSapValue(),
                    map.getLegalEntity()
                            .getNumber_tax_registration(), null, null);
        }

        String custType = map.getPerson_type().
                equals(PersonMapUtil.PERSONTYPE_J) ?
                SapEnum.CLIENT_ORG.getSapValue() : SapEnum.CLIENT_CUST.getSapValue();

        String branchUser = getProfileAuthor();
        BPContent content = new BPContent(custType,
                map.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONKIND_IP) ? map.getPerson_id() :
                        map.getPerson_type().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_J) ? "A" + map.getLegalEntity().getUnion_id() : "A" + map.getPerson().getUnion_id(),
                map.getBranch(),
                branchUser,
                new IdentNums[]{idents});
        return createRole(content, new String[]{role(map)});
    }

    @Override
    public void deleteRole(PersonMap personMap) throws SapRoleException {
        IdentNums idents = null;
        String nciPersonType = personMap.getPerson_type();
        if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J))
            idents = new IdentNums(SapEnum.DOC_TYPE_INN.getSapValue(),
                    personMap.getLegalEntity()
                            .getNumber_tax_registration(), null, null);
        String customer_type = nciPersonType.equals(
                PersonMapUtil.PERSONTYPE_J) ?
                SapEnum.CLIENT_ORG.getSapValue() : SapEnum.CLIENT_CUST.getSapValue();
        String branchUser = personMap.getBranch() + Executions.getCurrent().getSession().getAttribute("uid");
        BPContent content = new BPContent(customer_type,
                personMap.getPerson_type().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_J)
                        ? "A" + personMap.getLegalEntity().getUnion_id() : "A" + personMap.getPerson().getUnion_id(),
                personMap.getBranch(),
                branchUser,
                new IdentNums[]{idents});
        //ISLogger.getLogger().error("SAP ROLE RELATION");
        createRole(content, new String[]{nciPersonType.equalsIgnoreCase(PersonMapUtil.PERSONTYPE_J) ? "BP85" : "BP80)"});
    }


}
