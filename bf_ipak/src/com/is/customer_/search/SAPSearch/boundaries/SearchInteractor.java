package com.is.customer_.search.SAPSearch.boundaries;

import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;

import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 10:47
 */
public interface SearchInteractor {
    List<Response> search(Input input)
            throws SearchInteractorImpl.SearchInteractionException;
}
