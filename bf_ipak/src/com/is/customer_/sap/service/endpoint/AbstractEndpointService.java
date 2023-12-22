package com.is.customer_.sap.service.endpoint;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

import com.is.clients.TimerSession;

/**
 * Created by root on 10.05.2017.
 * 11:26
 */
public abstract class AbstractEndpointService {
    private final static String HEAD_OFFICE = "00444";

    protected String _username = SAPEnpointService.getEndpoint().getUsername();
    protected String _password = SAPEnpointService.getEndpoint().getPassword();
    protected String _contentEndpoint = SAPEnpointService.getEndpoint().getContentEndpoint();
    protected String _relationshipEndpoint = SAPEnpointService.getEndpoint().getRelationshipEndpoint();
    protected String _customerEndpoint = SAPEnpointService.getEndpoint().getCustomerEndpoint();
    protected String _rolesEndpoint = SAPEnpointService.getEndpoint().getRolesEndpoint();
    protected String _contentDeleteEndpoint = SAPEnpointService.getEndpoint().getContentDeleteEndpoint();    

    

    protected static String getProfileAuthor(){
        if (Executions.getCurrent() != null) {
            Session session = Executions.getCurrent().getDesktop().getSession();
            return String.format("%s%s",
                        session.getAttribute("branch").toString(),
                        session.getAttribute("uid").toString());
        }
        else if(TimerSession.getAttribute("branch") != null && TimerSession.getAttribute("uid") != null)
        {
        	return String.format("%s%s",
        			TimerSession.getAttribute("branch").toString(),
        			TimerSession.getAttribute("uid").toString());
        }
        return String.format("%s%s",HEAD_OFFICE,SAPEnpointService.getEndpoint().getUsername());
    }
}
