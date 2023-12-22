package com.is.nibbd.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class NibbdFilter extends Nibbd {
	
	@Getter @Setter private Date date_from;
	@Getter @Setter private Date date_to;
	public NibbdFilter() {
		super();
	}
}
