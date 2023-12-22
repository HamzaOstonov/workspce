package com.is.customer_.local.service;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.clients.addinfo.ParameterService;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.InternalControl;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.RefDataService;

/**
 * Created by root on 09.06.2017.
 * 11:40
 */
public class InternalControlService {
    private static final Logger logger = Logger.getLogger(InternalControlService.class);

    public static void correct(Map<String,Object> map) throws Exception {
        //logger.error("Debug... Internal Control Service");
        //logger.error("Debug... Internal Control Map " + map.toString());
        BusinessPartnerInterface service = SAPServiceFactory.getInstance().getBusinessPartnerService();

        String alias = (String) map.get("alias");
        String idClient = (String)map.get("id_client");
        Integer level = (Integer) map.get("uvk_level");
        String reason = (String) map.get("uvk_level_reason");
        Date date = (Date) map.get("uvk_date_to");
        Date dateFrom = (Date) map.get("uvk_date_from");
        String s_note = (String) map.get("so_note");
        String p_note = (String) map.get("po_note");
        Date risk_date = (Date) map.get("risk_date");
        // Set internal control attributes
        Customer businessPartner = service.get(getBranchByAlias(alias),idClient,null);
        InternalControl internalControl = new InternalControl();
        internalControl.setRiskLevel(String.valueOf(level));
        internalControl.setReason(reason);
        internalControl.setValidDateFrom(dateFrom);
        internalControl.setValidDateTo(date);
        internalControl.setS_notes(s_note);
        internalControl.setP_notes(p_note);
        internalControl.setInitDate(dateFrom);
        internalControl.setRisk_date(risk_date);
        businessPartner.setInternalControl(internalControl);

        // 05-03-2018
        service.update(businessPartner,"1","1");
    }

    private static String getBranchByAlias(String alias){
        return RefDataService.getRefData("SELECT BRANCH DATA, NAME LABEL FROM " +
                        "SS_DBLINK_BRANCH A " +
                        "WHERE A.USER_NAME = ?",
                alias,
                alias).get(0).
                getData();
    }


    public static InternalControl getInternalControlForm(Connection c,
                                                         Customer customer,
                                                         String schema) throws Exception {
        Map<String, Object> map = ParameterService.getAddinfo(c,
                customer.getBranch(),
                customer.getId()
                , customer.getCode_subject()
                , customer.getCode_type()
                ,schema);
        String alias = (String) map.get("alias");
        String idClient = (String) map.get("id_client");
        Integer level = (Integer) map.get("uvk_level");
        String reason = (String) map.get("uvk_level_reason");
        Date date = (Date) map.get("uvk_date_to");
        Date dateFrom = (Date) map.get("uvk_date_from") == null
                ? customer.getDate_open() : (Date) map.get("uvk_date_from");
        String s_note = (String) map.get("so_note");
        String p_note = (String) map.get("po_note");
        Date risk_date = (Date) map.get("risk_date");
        // Set internal control attributes
        InternalControl internalControl = new InternalControl();
        internalControl.setRiskLevel(String.valueOf(level));
        internalControl.setReason(reason);
        internalControl.setInitDate(dateFrom);
        internalControl.setValidDateFrom(dateFrom);
        internalControl.setValidDateTo(date);
        internalControl.setS_notes(s_note);
        internalControl.setP_notes(p_note);
        // Hard coded, if customer is closed, risk level changes to low
        //internalControl.setRiskLevel("1");

        return internalControl;
    }
}
