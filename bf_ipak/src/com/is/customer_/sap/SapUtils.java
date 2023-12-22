package com.is.customer_.sap;

import com.is.ISLogger;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

/**
 * Created by root on 13.07.2017.
 * 15:07
 */
public class SapUtils {
    private static final Logger logger = Logger.getLogger(SapUtils.class);

    public static boolean isCustomerExists(String id,String branch){
        try {
            String idSAP = SAPServiceFactory.getInstance()
                    .getBusinessPartnerService().get(branch, id, null).getIdSap();

            return idSAP == null || idSAP.isEmpty() ? false : true;
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            return false;
        }
    }
}
