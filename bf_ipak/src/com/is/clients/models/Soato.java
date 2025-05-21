package com.is.clients.models;

import com.is.delta.core.FilterInterface;
import com.is.utils.FilterField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class Soato implements FilterInterface<FilterField>,Cloneable {
    @Getter
    @Setter
    private String kod_soat;
    @Getter
    @Setter
    private String kod_gni;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String distr;
    @Getter
    @Setter
    private String distr_name;
    @Getter
    @Setter
    private String region_name;

    public Soato() {
    }

    @Override
    public List<FilterField> getFilterFields() {
        List<FilterField> list = new ArrayList<FilterField>();
        if (!StringUtils.isEmpty(kod_soat)){
            list.add(new FilterField(getCond(list)+" kod_soat=? ",kod_soat));
        }
        if (!StringUtils.isEmpty(kod_gni)){
            list.add(new FilterField(getCond(list)+" kod_gni=? ",kod_gni));
        }
        if (!StringUtils.isEmpty(region)){
            list.add(new FilterField(getCond(list)+" region_id=? ",region));
        }
        if (!StringUtils.isEmpty(distr)){
            list.add(new FilterField(getCond(list)+" distr=? ",distr));
        }
        list.add(new FilterField(getCond(list) + " ACTive=? ","A"));
        return list;
    }

    private String getCond(List<FilterField> filterFields) {
        if (filterFields.size() > 0) {
            return " and ";
        } else
            return " where ";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
