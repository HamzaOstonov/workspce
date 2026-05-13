package com.is.soato;

import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 22.04.2017.
 * 22:44
 */
public class FilterStatement {
    private Criteria criteria;
    private List<FilterField> filterFields;
    private FilterInterface filter;

    public FilterStatement(Criteria filterCriteria) {
        this.criteria = filterCriteria;
        this.filter = filterCriteria.getFilter();
        this.filterFields = filter.getFilterFields();
    }

    public PreparedStatement initStatementWithoutBounds(PreparedStatement preparedStatement) throws SQLException {
        setFilterParameters(preparedStatement);
        return preparedStatement;
    }

    public PreparedStatement initStatementParameters(PreparedStatement preparedStatement) throws SQLException {
        int lowerBound = criteria.getPageIndex() * criteria.getPageSize() + 1;
        //int upperBound = criteria.getPageSize() * lowerBound - 1;
        int upperBound = (criteria.getPageIndex() + 1) * criteria.getPageSize();

        int index = setFilterParameters(preparedStatement);
        preparedStatement.setInt(++index, lowerBound);
        preparedStatement.setInt(++index, upperBound);

        return preparedStatement;
    }

    private int setFilterParameters(PreparedStatement preparedStatement) throws SQLException {
        int index = 0;
        for (index = 0; index < filterFields.size(); index++) {
            Object filterObject = filterFields.get(index).getColobject();
            if (filterObject instanceof java.util.Date) {
                preparedStatement.setDate(index + 1, CheckNull.d2sql((java.util.Date) filterObject));
                continue;
            }
            preparedStatement.setObject(index + 1, filterObject);
        }
        return index;
    }

    public String generateConditions() {
        return appendConditions(filter.getFilterFields());
    }

    private String appendConditions(List<FilterField> filterFields) {
        StringBuilder builder = new StringBuilder();
        if (filterFields.size() > 0) {
            for (int i = 0; i < filterFields.size(); i++) {
                builder.append(filterFields.get(i).getSqlwhere());
            }
        }
        return builder.toString();
    }
}
