package com.is.customer_.mobile;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.mobile.model.MobileAuthentication;
import com.is.customer_.mobile.model.MobileCustomerResponse;
import com.is.customer_.mobile.model.confirmation.RegisterResponse;
import com.is.utils.CheckNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dev1 on 14.11.2018.
 */
public class MobileCustomerService implements IMobileCustomerService {
    private MobileCustomerGatewayHandlerService _hanlerService = new MobileCustomerGatewayHandlerService();

    private static Logger logger = LogManager.getLogger(MobileCustomerService.class);

    @Override
    public MobileCustomer FindCustomerByBind(String branch, String id) throws Exception {
        return _hanlerService.findByBind(branch, id);
    }

    @Override
    public MobileCustomer RegisterMobileCustomer(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception {
    	   //logger.error("mobile2021 1");
        RegisterResponse response = _hanlerService.register(mobileCustomer, sessionAttributes);
        //logger.error("mobile2021 2");
        if (!response.isSuccessful()) {
        	//logger.error("mobile2021 err");
            throw new MobileException(response.err);
        }
        
        return mobileCustomer;
    }

    @Override
    public MobileCustomer UpdateBinds(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception {
        MobileCustomerResponse response = _hanlerService.update(mobileCustomer, sessionAttributes);

        if (!response.isSuccessful()) {
            throw new MobileException(response.getErr());
        }

        return mobileCustomer;
    }

    @Override
    public MobileCustomer Activate(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception {
        mobileCustomer.setState("2");
        //logger.error("mobile2021 01");
        MobileCustomerResponse response = _hanlerService.updateState(mobileCustomer, sessionAttributes);
        //logger.error("mobile2021 02");
        if (!response.isSuccessful()) {
        	//logger.error("mobile2021 03 err");
            throw new MobileException(response.getErr());
        }

        return mobileCustomer;
    }

    @Override
    public MobileCustomer Deactivate(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception {
        mobileCustomer.setState("3");

        MobileAuthentication authentication = new MobileAuthentication();

        MobileCustomer response = _hanlerService.confirmUpdateState(mobileCustomer, sessionAttributes, authentication);

        return response;
    }

    @Override
    public MobileCustomer ConfirmAction(MobileCustomer mobileCustomer,
                                        List<Customer> customerList,
                                        SessionAttributes sessionAttributes,
                                        String action,
                                        MobileAuthentication authentication) throws IOException, MobileException {
    	//logger.error("mobile2021 w 1");
        if (action.equals(MobileCustomerAction.Save)) {
            return _hanlerService.confirmRegistration(mobileCustomer, customerList, sessionAttributes, authentication);
        }
        //logger.error("mobile2021 w 2");
        if (action.equals(MobileCustomerAction.Activate)) {
            mobileCustomer.setState("2");
            return _hanlerService.confirmUpdateState(mobileCustomer, sessionAttributes, authentication);
        }
        //logger.error("mobile2021 w 3");
        if (action.equals(MobileCustomerAction.Update)) {
            return _hanlerService.confirmUpdate(mobileCustomer, customerList, sessionAttributes, authentication);
        }
        //logger.error("mobile2021 w 4");
        if (action.equals(MobileCustomerAction.Deactivate)){
            mobileCustomer.setState("3");
            return _hanlerService.confirmUpdateState(mobileCustomer, sessionAttributes, authentication);
        }
        //logger.error("mobile2021 w 5");
        return null;
    }
}
