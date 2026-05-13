package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRefillAction {
	@Setter
	@Getter
	private Long id;
	@Setter
	@Getter
	private String description;
	@Setter
	@Getter
	private String method;
}
