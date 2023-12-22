package com.is.delta_relations;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class DeltaRelationFilter extends DeltaRelation {
	
	@Getter @Setter private Date dateFrom;
	@Getter @Setter private Date dateTo;
	
	public DeltaRelationFilter() {
		super();
	}

}
