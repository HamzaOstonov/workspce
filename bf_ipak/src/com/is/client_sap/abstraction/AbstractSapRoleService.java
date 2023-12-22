package com.is.client_sap.abstraction;

import java.rmi.RemoteException;
import java.util.Date;

import com.is.ISLogger;
import com.is.base.utils.XmlSerializer;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.RoleLogger;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.Endpoint;
import com.is.client_sap.SapEndpoints;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapUtil;
import com.is.client_sap.exceptions.SapRoleException;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import com.is.utils.CheckNull;

import relationships.NCI.com.ipakyulibank.BPContent;
import roles.NCI.com.ipakyulibank.RolesByEventReqest;
import roles.NCI.com.ipakyulibank.RolesByEventResponceRelationshipsResponce;
import roles.NCI.com.ipakyulibank.RolesByEvent_OutProxy;

public abstract class AbstractSapRoleService extends AbstractEndpointService{

    protected RolesByEventResponceRelationshipsResponce[] createRole(BPContent content, String[] roles) throws SapRoleException {
        Endpoint ep = SapEndpoints.getEndpoint(SapEnum.ROLE_REQ.getSapValue());
        RolesByEvent_OutProxy roleProxy = new RolesByEvent_OutProxy(
                ep.getEndpoint(),
                ep.getUsername(), ep.getPassword());

        RolesByEventResponceRelationshipsResponce[] resp = null;
        try {
            RolesByEventReqest req = new RolesByEventReqest(content, roles, null);
            resp = roleProxy.rolesByEvent_Out(req);
            XmlSerializer.write(resp, SapUtil.SAP_LOG_FOLDER + "roleResponce" +
                    ClientUtil.formatDateWithTime(new Date()) + ".xml");
            if (resp.length == 0 || !resp[0].getStatus().equals("0")) {
                throw new RuntimeException("Произошла Ошибка при присвоении роли: " + resp[0].getStatus() + " " + resp[0].getStatus_details() + " " + resp[0].getBp_extnum());
            }
        } catch (RemoteException e) {
            ISLogger.getLogger().error(e.getStackTrace());
            e.printStackTrace();
            throw new SapRoleException(SapUtil.PROXY_ERROR);
        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            RoleLogger.logErrorRole(content, roles);
        }
        return resp;
    }

    protected String role(PersonMap personMap) {
        String role = null;
        if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)
                && personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
            role = SapEnum.ROLE_EVENT_FOUNDER_LE.getSapValue();
        } else if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)
                && personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
            role = SapEnum.ROLE_EVENT_FOUNDER_I.getSapValue();
        } else if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_IP)) {
            role = SapEnum.ROLE_EVENT_IP.getSapValue();
        }
        return role;
    }

    public abstract RolesByEventResponceRelationshipsResponce[] createRole(PersonMap personMap) throws SapRoleException;

    public abstract void deleteRole(PersonMap personMap) throws SapRoleException;
}
