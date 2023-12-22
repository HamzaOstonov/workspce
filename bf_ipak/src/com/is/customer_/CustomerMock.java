package com.is.customer_;

import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.core.model.Customer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10.05.2017.
 * 0:24
 */
public class CustomerMock {
    public static List<Customer> getMapping() throws RemoteException{
        List<Customer> list = new ArrayList<Customer>();
        Customer customer = new Customer();
        customer.setIdSap("000001231");
        customer.setBranch("01066");
        customer.setId("99001094");
        Customer customer1 = new Customer();
        customer1.setBranch("01066");
        customer1.setId("99000224");
        list.add(customer);
        list.add(customer1);
        return list;
    }

    public static List<Response> getSearchResponse(){
        List<Response> list = new ArrayList<Response>();
        Response response = new Response();
        response.setSapId("12312312");
        list.add(response);
        return  list;
    }
}
