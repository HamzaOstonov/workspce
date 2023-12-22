package com.is.customer_.search.SAPSearch.boundaries;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 11:05
 */
public interface SearchOutputBoundary {
    List<Response> getResults();
}
