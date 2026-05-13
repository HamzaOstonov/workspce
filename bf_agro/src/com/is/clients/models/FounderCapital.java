package com.is.clients.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class FounderCapital {	
	
	@Getter @Setter protected long idPersonMap;
	@Getter @Setter protected BigDecimal part_of_capital;
    @Getter @Setter protected BigDecimal sum_a;
    @Getter @Setter protected BigDecimal sum_b;
    @Getter @Setter protected String currency;
    @Getter @Setter protected String is_director;
    @Getter @Setter protected BigDecimal shares_number;
    @Getter @Setter protected String founder_type;
    
//    @Getter @Setter protected String partOld;
//    @Getter @Setter protected BigDecimal sumAOld;
//    @Getter @Setter protected BigDecimal sumBOld;
    
    
    public FounderCapital() {}


	public FounderCapital(long idPersonMap, BigDecimal part_of_capital, BigDecimal sum_a, BigDecimal sum_b, String currency,
			String is_director, BigDecimal shares_number) {
		super();
		this.idPersonMap = idPersonMap;
		this.part_of_capital = part_of_capital;
		this.sum_a = sum_a;
		this.sum_b = sum_b;
		this.currency = currency;
		this.is_director = is_director;
		this.shares_number = shares_number;
	}
    
    

}
