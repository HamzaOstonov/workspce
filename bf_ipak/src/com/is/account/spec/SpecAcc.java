package com.is.account.spec;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class SpecAcc {
	@Getter @Setter private String branch;
	@Getter @Setter private String id_special;
	@Getter @Setter private String acc;
	@Getter @Setter private String code_currency;
	@Getter @Setter private String branch_slave;
	@Getter @Setter private BigDecimal sum_acc;
	@Getter @Setter private String prim;
	@Getter @Setter private String name;
	@Getter @Setter private String acc_bal;
	@Getter @Setter private String client;
	@Getter @Setter private String currency;
	@Getter @Setter private String id_order;
    
    public SpecAcc() {
		// TODO Auto-generated constructor stub
	}

	

	public SpecAcc(String branch, String id_special, String acc,
			String code_currency, String branch_slave, BigDecimal sum_acc,
			String prim, String name, String acc_bal, String currency,
			String id_order) {
		super();
		this.branch = branch;
		this.id_special = id_special;
		this.acc = acc;
		this.code_currency = code_currency;
		this.branch_slave = branch_slave;
		this.sum_acc = sum_acc;
		this.prim = prim;
		this.name = name;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.id_order = id_order;
	}

}
