package com.is.clientcrm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.is.utils.FilterField;

public class Soato implements Cloneable {
    
    
    public Soato(String kod_soat, String kod_gni, String region, String distr, String distr_name, String region_name) {
		super();
		this.kod_soat = kod_soat;
		this.kod_gni = kod_gni;
		this.region = region;
		this.distr = distr;
		this.distr_name = distr_name;
		this.region_name = region_name;
	}

	private String kod_soat;
    
    
    public String getKod_soat() {
		return kod_soat;
	}

	public void setKod_soat(String kod_soat) {
		this.kod_soat = kod_soat;
	}

	public String getKod_gni() {
		return kod_gni;
	}

	public void setKod_gni(String kod_gni) {
		this.kod_gni = kod_gni;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistr() {
		return distr;
	}

	public void setDistr(String distr) {
		this.distr = distr;
	}

	public String getDistr_name() {
		return distr_name;
	}

	public void setDistr_name(String distr_name) {
		this.distr_name = distr_name;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	private String kod_gni;
    
    
    private String region;
    
    
    private String distr;
    
    
    private String distr_name;
    
    
    private String region_name;

    public Soato() {
    }

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
        list.add(new FilterField(getCond(list) + " ACT=? ","A"));
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
