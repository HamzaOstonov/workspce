package com.is.customer_.search.SAPSearch.services;

import com.is.customer_.search.SAPSearch.model.Response;
import com.is.delta.core.Criteria;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 10:56
 */
public interface DBSearchDao {
    List<Response> search(Criteria criteria) throws SQLException;
}