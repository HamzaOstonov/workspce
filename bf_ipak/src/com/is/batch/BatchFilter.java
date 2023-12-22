package com.is.batch;

import com.is.delta.core.FilterInterface;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11.05.2017.
 * 18:23
 */
@Data
public class BatchFilter implements FilterInterface<FilterField>{
    private String branch;
    private String organizationId;
    private String organizationName;
    private String state;

    @Override
    public List<FilterField> getFilterFields() {
        List<FilterField> list = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(organizationId))
            list.add(new FilterField(getCond(list) + "customer=?",organizationId));
        if (!CheckNull.isEmpty(organizationName))
            list.add(new FilterField(getCond(list) + "=?",organizationName));
        if (!CheckNull.isEmpty(state))
            list.add(new FilterField(getCond(list) + "state=?",state));
        if (!CheckNull.isEmpty(branch))
            list.add(new FilterField(getCond(list) + "branch=?",branch));
        return list;
    }

    private String getCond(List<FilterField> list) {
        if (list.size() > 0) {
            return " and ";
        } else
            return " where ";
    }
}
