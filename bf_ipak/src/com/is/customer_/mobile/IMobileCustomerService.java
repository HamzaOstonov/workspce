package com.is.customer_.mobile;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.mobile.model.MobileAuthentication;
import com.is.customer_.mobile.model.MobileCustomerResponse;
import org.apache.axis.session.Session;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dev1 on 14.11.2018.
 */
public interface IMobileCustomerService {
    MobileCustomer FindCustomerByBind(String branch, String id) throws Exception;

    //MobileCustomer RegisterMobileCustomer(MobileCustomer mobileCustomer, Customer customer) throws Exception;

    MobileCustomer RegisterMobileCustomer(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception;

    MobileCustomer UpdateBinds(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception;

    MobileCustomer Activate(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception;

    MobileCustomer Deactivate(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws Exception;

    MobileCustomer ConfirmAction(MobileCustomer mobileCustomer, List<Customer> customerList,
                                 SessionAttributes sessionAttributes,
                                 final String action, MobileAuthentication authentication) throws IOException, MobileException;
}
