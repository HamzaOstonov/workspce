package com.is.customer_.search.SAPSearch.boundaries;

import com.is.ISLogger;
import com.is.customer_.search.SAPSearch.services.DBSearchDao;
import com.is.customer_.search.SAPSearch.services.DBSearchDaoImpl;
import com.is.customer_.search.SAPSearch.services.SAPSearch;
import com.is.customer_.search.SAPSearch.services.SAPSearchImpl;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.sap.EmergencyMode;
import com.is.delta.core.Criteria;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 11:23
 */
public class SearchInteractorImpl implements SearchInteractor {
    private final static Logger logger = ISLogger.getLogger();

    private DBSearchDao dBsearch;
    private SAPSearch sapSearch;

    public SearchInteractorImpl(SessionAttributes sessionAttributes){
        this.sapSearch = new SAPSearchImpl();
        this.dBsearch = new DBSearchDaoImpl(sessionAttributes);
    }

    @Override
    public List<Response> search(Input input) throws SearchInteractionException{
        List<Response> results = getResultsFromSAP(input);
        results.addAll(getNotSentCustomers(input));
        if (results == null || results.size() == 0)
            results = getResultsFromDB(input);
        return results;
    }

    private List<Response> getNotSentCustomers(Input input) {
        input.setSent(false);
        Criteria criteria = new Criteria.Builder().
                filter(input)
                .build();
        List<Response> results = new ArrayList<Response>();
        try {
            results = dBsearch.search(criteria);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        input.setSent(true);
        return results;
    }

    private List<Response> getResultsFromDB(Input input) throws SearchInteractionException {
        List<Response> results;

        Criteria criteria = new Criteria.Builder().
                filter(input)
                .build();
        try {
            results = dBsearch.search(criteria);
        } catch (Exception e) {
            throw new SearchInteractionException(e.getCause().getMessage());
        }
        return results;
    }

    private List<Response> getResultsFromSAP(Input input) throws SearchInteractionException {
        if (EmergencyMode.isTrue)
            return new ArrayList<Response>();

        List<Response> results;
        try {
            results = sapSearch.search(input);
        } catch (RemoteException e) {
            logger.error(CheckNull.getPstr(e));
            throw new SearchInteractionException("Проблема с подключением к веб сервису SAP CRM");
        }
        return results;
    }

    public void setDBsearch(DBSearchDao dBsearch) {
        this.dBsearch = dBsearch;
    }

    public void setSapSearch(SAPSearch sapSearch) {
        this.sapSearch = sapSearch;
    }

    public class SearchInteractionException extends Exception{
        public SearchInteractionException(String message){
            super(message);
        }
    }
}
