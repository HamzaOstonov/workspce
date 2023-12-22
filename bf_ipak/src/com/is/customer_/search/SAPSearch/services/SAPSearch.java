package com.is.customer_.search.SAPSearch.services;

import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 11:39
 */
public interface SAPSearch {
    List<Response> search(Input input) throws RemoteException;

}
