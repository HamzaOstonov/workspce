package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
	@Getter
	@Setter
	private ListCardsItem mainInfo;
	@Getter
	@Setter
	private CardBalance balance;
}
