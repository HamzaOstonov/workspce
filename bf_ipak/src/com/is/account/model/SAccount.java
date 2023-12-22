package com.is.account.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class SAccount {
	@Getter @Setter private String nci_id;
    @Getter @Setter private String destin;
    @Getter @Setter private String code_b;
    @Getter @Setter private String name_s;
    @Getter @Setter private String type;
    @Getter @Setter private String sect_code;
    @Getter @Setter private String kod_acc;
    @Getter @Setter private String rever_code;
    @Getter @Setter private String kod_k;
    @Getter @Setter private String kod_r;
    @Getter @Setter private Date date_open;
    @Getter @Setter private Date date_close;
    @Getter @Setter private String act;
    @Getter @Setter private String pr_nibbd;
    
    
}
