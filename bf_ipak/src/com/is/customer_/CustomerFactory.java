package com.is.customer_;

import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.customer_.local.service.CustomerActionService;

/**
 * Created by root on 17.07.2017.
 * 12:01
 */
public class CustomerFactory {
    private final SessionAttributes sessionAttributes;

    private CustomerFactory(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
    }

    public static CustomerFactory getInstance(SessionAttributes sessionAttributes){
        return new CustomerFactory(sessionAttributes);
    }

    public CustomerActionInterface getCustomerActionService(){
        return CustomerActionService.getInstance(sessionAttributes);
    }


}
