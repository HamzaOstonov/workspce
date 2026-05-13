package com.is.globalTieto.bankModels;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class BankClient {
	@Getter
	@Setter
    private Long id;
	@Getter
	@Setter
    private String branch;
	@Getter
	@Setter
    private String id_client;
	@Getter
	@Setter
    private String name;
	@Getter
	@Setter
    private String code_country;
	@Getter
	@Setter
    private String code_type;
	@Getter
	@Setter
    private String code_resident;
	@Getter
	@Setter
    private String code_subject;
	@Getter
	@Setter
    private String code_form;
	@Getter
	@Setter
    private Date date_open;
	@Getter
	@Setter
    private Date date_close;
	@Getter
	@Setter
    private Double state;
	@Getter
	@Setter
    private Double kod_err;
	@Getter
	@Setter
    private String file_name;
	@Getter
	@Setter
    private int sign_registr;
}
