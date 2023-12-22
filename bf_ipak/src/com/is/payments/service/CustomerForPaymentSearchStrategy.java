package com.is.payments.service;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractor;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractorImpl;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.search.SAPSearch.services.DBSearchDao;
import com.is.customer_.search.SAPSearch.services.DBSearchDaoImpl;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.payments.dao.CustomerForPaymentSearchDao;
import com.is.payments.model.InputForPaymentSearch;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerForPaymentSearchStrategy implements SearchInteractor{
    private final SessionAttributes sessionAttributes;
    private final Logger logger = ISLogger.getLogger();

    private CustomerForPaymentSearchStrategy(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
    }

    public static SearchInteractor getInstance(SessionAttributes sessionAttributes){
        return new CustomerForPaymentSearchStrategy(sessionAttributes);
    }

    @Override
    public List<Response> search(Input input) throws
            SearchInteractorImpl.SearchInteractionException {
        List<Response> results = new ArrayList<Response>();
        try {
            DBSearchDao dao = CustomerForPaymentSearchDao.getInstance(sessionAttributes);
            DBSearchDao customerSearch = new DBSearchDaoImpl(sessionAttributes);

            results = dao.search(new Criteria.Builder().
                    filter(InputForPaymentSearch.wrap(input))
                    .build());
            List<Response> customerResults = customerSearch.search(new Criteria.Builder().filter(input).build());
            logger.error(results.size() + " " + customerResults.size());
            results.addAll(customerResults);
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        }

        return results;
    }
}
