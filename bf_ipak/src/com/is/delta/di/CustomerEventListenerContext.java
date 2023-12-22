package com.is.delta.di;

import com.is.base.utils.Util;
import com.is.delta.Controller;
import com.is.delta.DELTARecord;

/**
 * Created by root on 24.04.2017.
 * 12:47
 */
public class CustomerEventListenerContext {

    public static CustomerEventListenerContext getInstance(){
        return new CustomerEventListenerContext();
    }

    public EventListenerInterface getImplementation(Controller controller, DELTARecord record){
        if (record.getCustomer_type().equalsIgnoreCase("01")){
            return new ClientPListener(controller,record);
        }
        else if (Util.inStrings(record.getCustomer_type(),"02","06")){
            return new ClientJListener(controller,record);
        }
        else if (record.getCustomer_type().equalsIgnoreCase("04"))
            return new ClientOtherListener(controller,record);
        return null;
    }
}
