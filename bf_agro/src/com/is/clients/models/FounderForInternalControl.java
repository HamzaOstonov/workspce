package com.is.clients.models;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class FounderForInternalControl {
	
	@Getter @Setter private String branch;
    @Getter @Setter private long id_cl_add;
    @Getter @Setter private int type_client;
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String address;
    @Getter @Setter private String part_of_capital;
    @Getter @Setter private BigDecimal sum_a;
    @Getter @Setter private BigDecimal sum_b;
    @Getter @Setter private String doc_type;
    @Getter @Setter private String doc_ser;
    @Getter @Setter private String doc_number;
    @Getter @Setter private Date doc_date;
    @Getter @Setter private String doc_organization;
    @Getter @Setter private String svidet;
    @Getter @Setter private String num_reestr;
    @Getter @Setter private Date date_registr;
    @Getter @Setter private String registr_organ;
    @Getter @Setter private String adres_organ;
    @Getter @Setter private String inn;
    @Getter @Setter private String director_name;
    @Getter @Setter private String phone;
    @Getter @Setter private String phone_work;
    @Getter @Setter private String phone_mobile;
    @Getter @Setter private int state;
    @Getter @Setter private String str;
    @Getter @Setter private Date doc_end_date;
    
    
    public FounderForInternalControl() {
	}
    
    public FounderForInternalControl(FounderMap founderMap) {
    	if(founderMap != null) {
    		this.id = founderMap.getId_founder();
    		this.id_cl_add = founderMap.getId_cl_add();
    		this.branch = founderMap.getBranch();
    	}
    }
}
